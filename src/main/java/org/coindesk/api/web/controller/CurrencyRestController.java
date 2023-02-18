package org.coindesk.api.web.controller;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

import org.coindesk.api.model.domain.entity.self.CoindeskCurrencyQuoteEntity;
import org.coindesk.api.model.service.CoindeskCurrencyQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/curr")
public class CurrencyRestController {
	private static final Logger log = LoggerFactory.getLogger(CurrencyRestController.class);
	
	@Autowired private CoindeskCurrencyQuoteService quoteService;
	
	@PostConstruct
	private void init() {
		log.info("Create a controller [{}] as bean.", getClass().getSimpleName());
	}
	
	@RequestMapping(value = "/findOne/{id}", method = { RequestMethod.GET })
	public CoindeskCurrencyQuoteEntity findOne(@PathParam("id") Long id) {
		log.info("Find the CoindeskCurrencyQuoteEntity with given id: {}", id);
		
		return quoteService.findOne(id).get();
	}
	
	@RequestMapping(value = "/findAll", method = { RequestMethod.GET })
	public Iterable<CoindeskCurrencyQuoteEntity> findAll() {
		return quoteService.findAll();
	}
	
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public CoindeskCurrencyQuoteEntity save(@RequestBody CoindeskCurrencyQuoteEntity entity) {
		return quoteService.save(entity);
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.PUT })
	public CoindeskCurrencyQuoteEntity update(@RequestBody CoindeskCurrencyQuoteEntity entity) {
		return quoteService.save(entity);
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.DELETE })
	public void delete(@RequestBody CoindeskCurrencyQuoteEntity entity) {
		quoteService.delete(entity);
	}

}
