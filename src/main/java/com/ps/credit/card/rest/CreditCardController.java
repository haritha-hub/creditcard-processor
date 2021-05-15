package com.ps.credit.card.rest;

import java.util.List;

import com.ps.credit.card.entity.CreditCard;
import com.ps.credit.card.service.CreditCardService;

import com.ps.credit.card.util.HelperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController("/api/credit-card")
@RequiredArgsConstructor
@Slf4j
public class CreditCardController {

    private final CreditCardService creditCardService;

    @ApiOperation(
            value = "Add credit card details of a customer"
    )
    @PostMapping("/addCard")
    public ResponseEntity saveCardDetails(@RequestBody CreditCard creditCard) {
        log.debug("Entering POST /addCard endpoint");
        try {
            if (HelperUtil.luhnCheck(creditCard.getCreditCardNumber()) && HelperUtil.validateCardDetails(creditCard.getCreditCardNumber())) {
                creditCardService.saveDetails(creditCard);
                log.debug("Inserted credit card details successfully");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(
            value = "Retrieve all existing credit card details"
    )
    @GetMapping("/getCards")
    public ResponseEntity<List<CreditCard>> getAllCardsDetails() {
        log.debug("Entering GET /getCards endpoint");
        try {
            List<CreditCard> creditCardList = creditCardService.getAll();
            if (creditCardList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(creditCardList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
