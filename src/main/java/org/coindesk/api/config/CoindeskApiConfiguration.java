package org.coindesk.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CoindeskApiConfiguration {

	@Bean
	public ObjectMapper mapper() {
		return new ObjectMapper();
	}
	
}
