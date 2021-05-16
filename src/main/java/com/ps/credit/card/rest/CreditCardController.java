package com.ps.credit.card.rest;

import java.util.List;

import javax.validation.Valid;

import com.ps.credit.card.entity.CreditCard;
import com.ps.credit.card.rest.dto.CreditCardDTO;
import com.ps.credit.card.service.CreditCardService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/credit-card")
@RequiredArgsConstructor
@Slf4j
public class CreditCardController {

    private final CreditCardService creditCardService;

    @ApiOperation(
            value = "Add credit card details of a customer"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful update of credit card details"),
        @ApiResponse(code = 400, message = "Error in credit card object "),
        @ApiResponse(code = 412, message = "Precondition failed for credit card details")
    })
    @PostMapping("/add-card")
    public ResponseEntity<CreditCard> saveCardDetails(@RequestBody @Valid CreditCardDTO creditCard) {

       var creditCardDetails = creditCardService.saveCreditCardDetails(creditCard);

       if (creditCardDetails != null) {
           return ResponseEntity.ok().body(creditCardDetails);
       }

       return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();

    }

    @ApiOperation(
            value = "Retrieve all existing credit card details"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful return of credit card details"),
            @ApiResponse(code = 204, message = "No content")
    })
    @GetMapping("/get-cards")
    public ResponseEntity<List<CreditCardDTO>> getAllCardsDetails() {

        var creditCardDetails = creditCardService.getAll();

        return CollectionUtils.isEmpty(creditCardDetails) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok().body(creditCardDetails);
    }

}
