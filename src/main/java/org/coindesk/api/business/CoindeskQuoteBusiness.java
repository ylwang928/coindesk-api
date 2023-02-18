package org.coindesk.api.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.coindesk.api.model.domain.entity.self.CoindeskCurrencyQuoteEntity;
import org.coindesk.api.model.domain.ws.CoindeskQuote;
import org.coindesk.api.model.domain.ws.CoindeskQuoteBpi;
import org.coindesk.api.model.service.CoindeskCurrencyQuoteService;
import org.coindesk.api.proxy.CoindeskQuoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoindeskQuoteBusiness {
	private static final Logger log = LoggerFactory.getLogger(CoindeskQuoteBusiness.class);
	
	@Autowired
	private CoindeskQuoteProxy proxy;
	
	@Autowired
	private CoindeskCurrencyQuoteService quoteService;
	
	@PostConstruct
	private void init() {
		log.trace("Create a business [{}] as bean.", getClass().getSimpleName());
	}
	
	public void updateQuote() {
		CoindeskQuote quote = proxy.getQuote();
		String isoDate = quote.getTime().getUpdatedISO();
		CoindeskQuoteBpi bpi = quote.getBpi();
		
		List<CoindeskCurrencyQuoteEntity> entities = new ArrayList<>();
		entities.add(new CoindeskCurrencyQuoteEntity(isoDate, bpi.getUsd()));
		entities.add(new CoindeskCurrencyQuoteEntity(isoDate, bpi.getGbp()));
		entities.add(new CoindeskCurrencyQuoteEntity(isoDate, bpi.getEur()));
		quoteService.saveAll(entities);
		
		/*
		quoteService.save(new CoindeskCurrencyQuoteEntity(isoDate, bpi.getUsd()));
		quoteService.save(new CoindeskCurrencyQuoteEntity(isoDate, bpi.getGbp()));
		quoteService.save(new CoindeskCurrencyQuoteEntity(isoDate, bpi.getEur()));
		*/
	}

}
