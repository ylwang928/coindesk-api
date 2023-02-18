package org.coindesk.api.model.domain.ws;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoindeskQuoteBpi {
	@JsonProperty("USD")
	protected CoindeskQuoteCurrency usd;
	
	@JsonProperty("GBP")
	protected CoindeskQuoteCurrency gbp;
	
	@JsonProperty("EUR")
	protected CoindeskQuoteCurrency eur;
	
	public CoindeskQuoteBpi() {}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public CoindeskQuoteCurrency getUsd() {
		return usd;
	}

	public void setUsd(CoindeskQuoteCurrency usd) {
		this.usd = usd;
	}

	public CoindeskQuoteCurrency getGbp() {
		return gbp;
	}

	public void setGbp(CoindeskQuoteCurrency gbp) {
		this.gbp = gbp;
	}

	public CoindeskQuoteCurrency getEur() {
		return eur;
	}

	public void setEur(CoindeskQuoteCurrency eur) {
		this.eur = eur;
	}

}
