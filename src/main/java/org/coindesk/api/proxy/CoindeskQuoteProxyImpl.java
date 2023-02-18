package org.coindesk.api.proxy;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.coindesk.api.model.domain.ws.CoindeskQuote;
import org.coindesk.api.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CoindeskQuoteProxyImpl implements CoindeskQuoteProxy {
	private static final Logger log = LoggerFactory.getLogger(CoindeskQuoteProxyImpl.class);
	
	@Autowired
	private ObjectMapper mapper;
	
	@Value("${httpclient.ws.coindesk.url.quote}")
	private String apiUrl;

	@Override
	public CoindeskQuote getQuote() {
		try {
			String respBody = HttpUtils.getHttpResp(apiUrl);
			
			return StringUtils.isNotBlank(respBody) ? mapper.readValue(respBody, CoindeskQuote.class) : null;
		} catch (IOException e) {
			log.error("Failed to get quote from coindesk: ", e);
			
			return null;
		}
	}

}
