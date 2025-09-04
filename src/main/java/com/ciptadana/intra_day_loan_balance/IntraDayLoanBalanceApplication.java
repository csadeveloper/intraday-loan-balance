package com.ciptadana.intra_day_loan_balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IntraDayLoanBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntraDayLoanBalanceApplication.class, args);
	}

}
