package com.ps.credit.card.service;

import com.ps.credit.card.entity.CreditCard;
import com.ps.credit.card.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public void saveDetails(CreditCard creditCard) {
        creditCardRepository.save(creditCard);
    }

    public List<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }
}
