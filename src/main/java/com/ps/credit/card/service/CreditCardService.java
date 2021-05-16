package com.ps.credit.card.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ps.credit.card.entity.CreditCard;
import com.ps.credit.card.repository.CreditCardRepository;
import com.ps.credit.card.rest.dto.CreditCardDTO;

import com.ps.credit.card.util.HelperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCard saveCreditCardDetails(final CreditCardDTO creditCardDTO) {

        if (HelperUtil.luhnCheck(creditCardDTO.getCardNumber())) {
          return creditCardRepository.save(mapToCreditCardEntity(creditCardDTO));
        }

        log.info("Validation failed for credit card number: {}", creditCardDTO.getCardNumber());

        return null;
    }

    public List<CreditCardDTO> getAll() {

        var creditCardEntity = creditCardRepository.findAll();

        return creditCardEntity.stream().map(this::mapToCreditCardDTO).collect(Collectors.toList());

    }

    private CreditCard mapToCreditCardEntity(final CreditCardDTO creditCardDTO) {
        return CreditCard.builder()
                .creditCardNumber(creditCardDTO.getCardNumber())
                .accountName(creditCardDTO.getAccountName())
                .balance(creditCardDTO.getBalance())
                .limit(creditCardDTO.getLimit())
                .build();
    }

    private CreditCardDTO mapToCreditCardDTO(final CreditCard creditCard) {
        return CreditCardDTO.builder()
                .cardNumber(creditCard.getCreditCardNumber())
                .accountName(creditCard.getAccountName())
                .balance(creditCard.getBalance())
                .limit(creditCard.getLimit())
                .build();
    }

}
