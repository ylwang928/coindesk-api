package org.coindesk.api.model.domain.entity.self;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.coindesk.api.model.domain.ws.CoindeskQuoteCurrency;
import org.coindesk.api.util.DateUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "currency_quote")
public class CoindeskCurrencyQuoteEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	
	@Column(name = "curr_code")
	protected String currCode;
	
	@Column(name = "symbol")
	protected String symbol;
	
	@Column(name = "rate")
	protected Double rate;
	
	@Column(name = "description")
	protected String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "updated_at")
	protected Date updatedAt;
	
	public CoindeskCurrencyQuoteEntity() {}
	
	public CoindeskCurrencyQuoteEntity(String isoDate, CoindeskQuoteCurrency currency) {
		this.currCode = currency.getCode();
		this.symbol = currency.getSymbol();
		this.rate = currency.getRateFloat();
		this.description = currency.getDescription();
		this.updatedAt = DateUtils.parseISOFullDate(isoDate);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
