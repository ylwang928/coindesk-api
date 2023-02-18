package org.coindesk.api.model.domain.ws;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CoindeskQuote {
	protected CoindeskQuoteTime time;
	protected String disclaimer;
	protected String chartName;
	protected CoindeskQuoteBpi bpi;
	
	public CoindeskQuote() {}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public CoindeskQuoteTime getTime() {
		return time;
	}

	public void setTime(CoindeskQuoteTime time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public CoindeskQuoteBpi getBpi() {
		return bpi;
	}

	public void setBpi(CoindeskQuoteBpi bpi) {
		this.bpi = bpi;
	}

}
