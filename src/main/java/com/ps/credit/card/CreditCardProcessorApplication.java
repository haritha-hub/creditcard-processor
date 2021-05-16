package com.ps.credit.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ps.credit.card")
public class CreditCardProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardProcessorApplication.class, args);
	}

}
