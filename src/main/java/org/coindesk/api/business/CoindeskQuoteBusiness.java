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
		log.info("Quote of currency from coindesk: {}", quote);
		String isoDate = quote.getTime().getUpdatedISO();
		CoindeskQuoteBpi bpi = quote.getBpi();
		
		List<CoindeskCurrencyQuoteEntity> entities = new ArrayList<>();
		bpi.getCurrMap().values().forEach(currency -> {
			entities.add(new CoindeskCurrencyQuoteEntity(isoDate, currency));
		});
		quoteService.saveAll(entities);   // 一次存進 DB 較有效率。
	}

}
