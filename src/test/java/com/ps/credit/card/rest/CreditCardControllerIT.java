package com.ps.credit.card.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.credit.card.IntegrationTest;
import com.ps.credit.card.entity.CreditCard;
import com.ps.credit.card.rest.dto.CreditCardDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class CreditCardControllerIT extends IntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    private CreditCardDTO creditCardDTO;

    @BeforeEach
    public void setup() {

        clearDB();

        creditCardDTO = CreditCardDTO.builder()
                .cardNumber("1111222233334444")
                .accountName("test test")
                .balance(150.0)
                .limit(250.0)
                .build();

    }

    @Test
    public void testSaveCreditCardDetails() throws Exception {

        mvc().perform(post("/api/credit-card/add-card")
                .content(objectMapper.writeValueAsBytes(creditCardDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountName", equalTo(creditCardDTO.getAccountName())))
                .andExpect(jsonPath("$.creditCardNumber", equalTo(creditCardDTO.getCardNumber())));

    }

    @Test
    public void testGetCreditCardDetailsAsNoContent() throws Exception {

        mvc().perform(get("/api/credit-card/get-cards"))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testAllCardDetails() throws Exception {

        creditCardRepository.save(CreditCard.builder()
                .creditCardNumber(creditCardDTO.getCardNumber())
                .accountName(creditCardDTO.getAccountName())
                .balance(creditCardDTO.getBalance())
                .limit(creditCardDTO.getBalance())
                .build());

        mvc().perform(get("/api/credit-card/get-cards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].accountName", equalTo("test test")));

    }

    @Test
    public void testPreconditionOnCardNumberFailed() throws Exception {

        creditCardDTO.setCardNumber("1111345677890");

        mvc().perform(post("/api/credit-card/add-card")
                .content(objectMapper.writeValueAsBytes(creditCardDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isPreconditionFailed());

    }

    @Test
    public void testBadException() throws Exception {

        creditCardDTO.setCardNumber("111134567789090908900");

        mvc().perform(post("/api/credit-card/add-card")
                .content(objectMapper.writeValueAsBytes(creditCardDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }


}
