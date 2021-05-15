package com.ps.credit.card.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "credit_card_details")
public class CreditCard {

    @Id
    @Column(name = "cc_number", nullable = false)
    private String creditCardNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "cc_balance", nullable = false)
    private Double balance;

    @Column(name = "cc_limit", nullable = false)
    private Double limit;

}
