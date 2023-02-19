package org.coindesk.api.model.domain.ws;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CoindeskQuoteBpi {
	protected Map<String, CoindeskQuoteCurrency> currMap;
	
	/*
	@JsonProperty("USD")
	protected CoindeskQuoteCurrency usd;
	
	@JsonProperty("GBP")
	protected CoindeskQuoteCurrency gbp;
	
	@JsonProperty("EUR")
	protected CoindeskQuoteCurrency eur;
	*/
	
	public CoindeskQuoteBpi() {}
	
	@JsonCreator
	public CoindeskQuoteBpi(Map<String, CoindeskQuoteCurrency> currMap) {
		this.currMap = currMap;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Map<String, CoindeskQuoteCurrency> getCurrMap() {
		return currMap;
	}

	public void setCurrMap(Map<String, CoindeskQuoteCurrency> currMap) {
		this.currMap = currMap;
	}

}
