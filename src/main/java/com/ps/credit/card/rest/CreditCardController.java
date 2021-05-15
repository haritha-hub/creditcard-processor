package com.ps.credit.card.rest;

import java.util.List;

import com.ps.credit.card.entity.CreditCard;
import com.ps.credit.card.service.CreditCardService;

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
    @PostMapping
    public ResponseEntity saveCardDetails(@RequestBody CreditCard creditCard) {

        try {
            boolean isCreditCardValid = check(creditCard.getCreditCardNumber());
            if (isCreditCardValid) {
                creditCardService.saveDetails(creditCard);
                log.debug("Inserted credit card details successfully");
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(
            value = "Retrieve credit card details of a customer"
    )
    @GetMapping("/getCards")
    public ResponseEntity<List<CreditCard>> getAllCardsDetails() {

        try {
            List<CreditCard> creditCardList = creditCardService.getAll();

            if(creditCardList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(creditCardList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean check(String creditCardNumber) {
        log.debug("Starting Validation of given credit card number", creditCardNumber);
        // this only works if you are certain all input will be at least 10 characters
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
