package org.coindesk.api.proxy;

import org.coindesk.api.model.domain.ws.CoindeskQuote;

public interface CoindeskQuoteProxy {
	CoindeskQuote getQuote();
}
