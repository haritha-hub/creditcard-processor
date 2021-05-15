package com.ps.credit.card.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCardDTO {

    @NotEmpty
    @Size(min=10, max = 19)
    private String cardNumber;

    @NotEmpty
    private String accountName;

    @Builder.Default
    private Double balance = 0.0;

    @NotNull
    private Double limit;

}
