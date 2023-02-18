package org.coindesk.api.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.coindesk.api.model.domain.ws.CoindeskQuote;
import org.coindesk.api.model.domain.ws.CoindeskQuoteBpi;
import org.coindesk.api.model.domain.ws.CoindeskQuoteCurrencyDisplay;
import org.coindesk.api.proxy.CoindeskQuoteProxy;
import org.coindesk.api.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quote")
public class CoindeskQuoteRestController {
	private static final Logger log = LoggerFactory.getLogger(CoindeskQuoteRestController.class);
	
	@Autowired private CoindeskQuoteProxy proxy;
	
	@PostConstruct
	private void init() {
		log.info("Create a controller [{}] as bean.", getClass().getSimpleName());
	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET }
	    , consumes = { MediaType.APPLICATION_JSON_VALUE }
	    , produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<CoindeskQuoteCurrencyDisplay> findAll() {
		CoindeskQuote quote = proxy.getQuote();
		String isoDateStr = quote.getTime().getUpdatedISO();
		Date isoDate = DateUtils.parseISOFullDate(isoDateStr);
		CoindeskQuoteBpi bpi = quote.getBpi();
		/*
		bpi.getUsd().setUpdatedAt(isoDate);
		bpi.getGbp().setUpdatedAt(isoDate);
		bpi.getEur().setUpdatedAt(isoDate);
		*/
		
		List<CoindeskQuoteCurrencyDisplay> currencies = new ArrayList<>();
		currencies.add(new CoindeskQuoteCurrencyDisplay(bpi.getUsd(), isoDate));
		currencies.add(new CoindeskQuoteCurrencyDisplay(bpi.getGbp(), isoDate));
		currencies.add(new CoindeskQuoteCurrencyDisplay(bpi.getEur(), isoDate));
		
		return currencies;
	}
	
}
