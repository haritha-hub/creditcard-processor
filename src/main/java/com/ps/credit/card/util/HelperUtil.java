package com.ps.credit.card.util;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
public class HelperUtil {

    public static boolean luhnCheck(String creditCardNumber) {
        log.debug("Luhn check started", creditCardNumber);
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

    public static boolean validateCardDetails(String creditCardNumber) {
        boolean isValid = true;
        log.debug("Validation check started", creditCardNumber);
        if(isNull(creditCardNumber) || creditCardNumber.length() < 13 || creditCardNumber.length() >19){
            log.error("Invalid credit card details");
            isValid = false;
        }
        return isValid;
    }
}
