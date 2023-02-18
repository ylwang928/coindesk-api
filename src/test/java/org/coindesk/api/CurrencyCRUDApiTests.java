package org.coindesk.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Date;

import org.coindesk.api.business.CoindeskQuoteBusiness;
import org.coindesk.api.model.domain.entity.self.CoindeskCurrencyQuoteEntity;
import org.coindesk.api.model.domain.ws.CoindeskQuote;
import org.coindesk.api.model.repository.self.CoindeskCurrencyQuoteRepository;
import org.coindesk.api.model.service.CoindeskCurrencyQuoteService;
import org.coindesk.api.proxy.CoindeskQuoteProxy;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CoindeskApiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
		  locations = "classpath:application.yml")
public class CurrencyCRUDApiTests {
	private static final Logger log = LoggerFactory.getLogger(CurrencyCRUDApiTests.class);
	
	@Autowired private MockMvc mvc;
	
	@Autowired private ObjectMapper mapper;
	@Autowired private CoindeskCurrencyQuoteRepository quoteRepo;
	@Autowired private CoindeskCurrencyQuoteService quoteService;
	@Autowired private CoindeskQuoteProxy quoteProxy;
	@Autowired private CoindeskQuoteBusiness quoteBusiness;
	
	@Test
	public void testModel() {
		quoteBusiness.updateQuote();
		
		log.info("All quotes in h2: {}", quoteService.findAll());
	}
	
	// Test Case 1: 測試呼叫查詢幣別對應表資料 API，並顯示其內容。
	@Test
	public void testQueryAllCurrenciesFromDB() throws Exception {
		quoteBusiness.updateQuote();

		MvcResult result = mvc.perform( get("/api/curr/findAll")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON) ).andReturn();
		// int statusCode = result.getResponse().getStatus();
		String respBody = result.getResponse().getContentAsString();
		
		log.info("[Test Case 1] Query all quotes of all currencies: {}", respBody);
		assertNotNull(respBody);
	}
	
	// Test Case 2: 測試呼叫新增幣別對應表資料 API。
	@Test
	public void testAddingCurrencyToDB() throws Exception {
		CoindeskCurrencyQuoteEntity currencyQuoteEntity = new CoindeskCurrencyQuoteEntity();
		currencyQuoteEntity.setCurrCode("USD");
		currencyQuoteEntity.setDescription("USD quote.");
		currencyQuoteEntity.setRate(30.128D);
		currencyQuoteEntity.setSymbol("$");
		currencyQuoteEntity.setUpdatedAt(new Date());
		
		String reqBody = mapper.writeValueAsString(currencyQuoteEntity);
		
		MvcResult result = mvc.perform( post("/api/curr/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(reqBody)
				.accept(MediaType.APPLICATION_JSON) )
				.andReturn();
		// int statusCode = result.getResponse().getStatus();
		String respBody = result.getResponse().getContentAsString();
		
		log.info("[Test Case 2] Save the quote manually: {}", respBody);
		CoindeskCurrencyQuoteEntity currencyQuoteEntity2 = mapper.readValue(respBody, CoindeskCurrencyQuoteEntity.class);
		Long id = currencyQuoteEntity2.getId();
		CoindeskCurrencyQuoteEntity currencyQuoteEntity3 = quoteService.findOne(id).get();
		log.info("Find the quote of currency from DB with given id: [{}] after saving, entity: {}", id, currencyQuoteEntity3);
		
		assertNotNull(currencyQuoteEntity3);
	}
	
	// Test Case 3: 測試呼叫更新幣別對應表資料 API，並顯示其內容。
	@Test
	public void testUpdatingCurrencyToDB() throws Exception {
		Long id = 1L;
		CoindeskCurrencyQuoteEntity currencyQuoteEntity = quoteService.findOne(id).get();
		currencyQuoteEntity.setCurrCode("USD");
		currencyQuoteEntity.setDescription("USD quote.");
		currencyQuoteEntity.setRate(30.128D);
		currencyQuoteEntity.setSymbol("$");
		currencyQuoteEntity.setUpdatedAt(new Date());
		
		String reqBody = mapper.writeValueAsString(currencyQuoteEntity);

		MvcResult result = mvc.perform( put("/api/curr/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(reqBody)
				.accept(MediaType.APPLICATION_JSON) )
				.andReturn();
		// int statusCode = result.getResponse().getStatus();
		String respBody = result.getResponse().getContentAsString();
		
		log.info("[Test Case 3] Updating the quote in DB: {}", respBody);
		CoindeskCurrencyQuoteEntity currencyQuoteEntity2 = mapper.readValue(respBody, CoindeskCurrencyQuoteEntity.class);
		id = currencyQuoteEntity2.getId();
		CoindeskCurrencyQuoteEntity currencyQuoteEntity3 = quoteService.findOne(id).get();
		log.info("Find the quote of currency from DB with given id: [{}] after updating, entity: {}", id, currencyQuoteEntity3);
		
		assertNotNull(currencyQuoteEntity3);
	}
	
	// Test Case 4: 測試呼叫刪除幣別對應表資料 API。
	@Test
	public void testDeletingCurrencyFromDB() throws Exception {
		Long id = 1L;
		CoindeskCurrencyQuoteEntity currencyQuoteEntity = quoteService.findOne(id).get();
		
		String reqBody = mapper.writeValueAsString(currencyQuoteEntity);

		MvcResult result = mvc.perform( delete("/api/curr/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(reqBody)
				.accept(MediaType.APPLICATION_JSON) )
				.andReturn();
		// int statusCode = result.getResponse().getStatus();
		String respBody = result.getResponse().getContentAsString();

		boolean isExist = quoteRepo.existsById(id);
		log.info("[Test Case 4] Delete the quote in DB: {}, isExist?: {}", respBody, isExist);
		
		assertFalse(isExist);
	}
	
	// Test Case 5: 測試呼叫 coindesk API，並顯示其內容。
	@Test
	public void testCoindeskAPI() {
		CoindeskQuote quote = quoteProxy.getQuote();
		
		log.info("[Test Case 5] Quote from coindesk: {}", quote);
		assertNotNull(quote);
	}
	
	// Test Case 6: 測試呼叫資料轉換的 API，並顯示其內容。
	@Test
	public void testCallingApiConversion() throws Exception {
		MvcResult result = mvc.perform( get("/api/quote/list")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON) )
				.andReturn();
		// int statusCode = result.getResponse().getStatus();
		String respBody = result.getResponse().getContentAsString();

		log.info("[Test Case 6] Call the API of conversion: {}", respBody);
		
		assertNotNull(respBody);
	}

}
