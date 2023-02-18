package org.coindesk.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoindeskApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoindeskApiApplication.class, args);
		
		/*
		ApplicationContext context = SpringApplication.run(CoindeskApiApplication.class, args);
		CoindeskQuoteBusiness business = context.getBean(CoindeskQuoteBusiness.class);
		business.updateQuote();
		
		CoindeskCurrencyQuoteRepository currencyQuoteRepo = context.getBean(CoindeskCurrencyQuoteRepository.class);
		System.out.println("Quotes: " + currencyQuoteRepo.findAll());
		*/
	}

}
