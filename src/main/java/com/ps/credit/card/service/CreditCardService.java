package com.ps.credit.card.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ps.credit.card.entity.CreditCard;
import com.ps.credit.card.repository.CreditCardRepository;
import com.ps.credit.card.rest.dto.CreditCardDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCard saveCreditCardDetails(final CreditCardDTO creditCardDTO) {

        var isCreditCardValid =   creditCardNumberValidation(creditCardDTO.getCardNumber().trim());

        if (isCreditCardValid) {
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

    private boolean creditCardNumberValidation(final String creditCardNumber) {

        int extraChars = creditCardNumber.length() - 10;

        if (extraChars < 0) {
            throw new IllegalArgumentException("Number length must be at least 10 characters!");
        }

        int s1 = 0, s2 = 0;

        String reverse = new StringBuffer(creditCardNumber).reverse().toString();
        for (int i = 0; i < reverse.length(); i++) {
            int digit = Character.digit(reverse.charAt(i), 10);
            if (i % 2 == 0) {//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            } else {//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if (digit >= 5) {
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }
}
