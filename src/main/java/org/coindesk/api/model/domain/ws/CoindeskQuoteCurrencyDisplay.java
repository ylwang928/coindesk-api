package org.coindesk.api.model.domain.ws;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CoindeskQuoteCurrencyDisplay {
	public static final Map<String, String> CURR_MAP = new TreeMap<String, String>();
	
	static {
		CURR_MAP.put("USD", "美金");
		CURR_MAP.put("GBP", "英鎊");
		CURR_MAP.put("EUR", "歐元");
	}
	
	protected String code;
	protected String codeName;
	
	// @JsonProperty("rate_float")
	protected Double rate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
	protected Date updatedAt;
	
	public CoindeskQuoteCurrencyDisplay() {}
	
	public CoindeskQuoteCurrencyDisplay(CoindeskQuoteCurrency currency, Date updatedAt) {
		this.code = currency.getCode();
		this.codeName = CURR_MAP.get(this.code);
		this.rate = currency.getRateFloat();
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
