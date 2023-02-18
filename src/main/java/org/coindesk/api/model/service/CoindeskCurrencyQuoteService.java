package org.coindesk.api.model.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.coindesk.api.model.domain.entity.self.CoindeskCurrencyQuoteEntity;
import org.coindesk.api.model.repository.self.CoindeskCurrencyQuoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoindeskCurrencyQuoteService {
	private static final Logger log = LoggerFactory.getLogger(CoindeskCurrencyQuoteService.class);
	
	@Autowired
	private CoindeskCurrencyQuoteRepository currencyQuoteRepo;
	
	@PostConstruct
	private void init() {
		log.trace("Create a service [{}] as bean.", getClass().getSimpleName());
	}
	
	public Iterable<CoindeskCurrencyQuoteEntity> findAll() {
		return currencyQuoteRepo.findAll();
	}
	
	public Optional<CoindeskCurrencyQuoteEntity> findOne(Long id) {
		return currencyQuoteRepo.findById(id);
	}
	
	public CoindeskCurrencyQuoteEntity save(CoindeskCurrencyQuoteEntity entity) {
		return currencyQuoteRepo.save(entity);
	}
	
	public Iterable<CoindeskCurrencyQuoteEntity> saveAll(Iterable<CoindeskCurrencyQuoteEntity> entities) {
		return currencyQuoteRepo.saveAll(entities);
	}
	
	public void delete(CoindeskCurrencyQuoteEntity entity) {
		currencyQuoteRepo.delete(entity);
	}

}
