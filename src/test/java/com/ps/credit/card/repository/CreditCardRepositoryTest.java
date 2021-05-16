package com.ps.credit.card.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.ps.credit.card.IntegrationTest;
import com.ps.credit.card.entity.CreditCard;
import org.junit.jupiter.api.Test;

public class CreditCardRepositoryTest extends IntegrationTest {

    @Test
    public void canSaveCreditCardDetails() {

        clearDB();

        CreditCard creditCard = CreditCard.builder()
                .creditCardNumber("1111222233334444")
                .accountName("test test")
                .balance(150.0)
                .limit(200.0)
                .build();

        creditCardRepository.save(creditCard);

        var creditCardDetails = creditCardRepository.findAll();

        assertThat(creditCardDetails.size()).isEqualTo(1);
        assertThat(creditCardDetails.get(0).getCreditCardNumber()).isEqualTo(creditCard.getCreditCardNumber());
        assertThat(creditCardDetails.get(0).getAccountName()).isEqualTo(creditCard.getAccountName());
        assertThat(creditCardDetails.get(0).getBalance()).isEqualTo(creditCard.getBalance());
        assertThat(creditCardDetails.get(0).getLimit()).isEqualTo(creditCard.getLimit());
    }

}
