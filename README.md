coindesk-api (currency)
===============================================================================

備註
-------------------------------------------------------------------------------
- 因為 `coindesk-api` 回傳之 `Content-Type` 是 `application/javascript`，似乎無法直接套用 Spring 的 `RestTemplate`，故改用最原始的方式取得 http 回應。
- 因轉換後的 API、欄位中含有中文，故需要在參數檔 `application.yml` 指定 `server.servlet.encoding.charset: UTF-8` 及 `server.servlet.encoding.force-response: true` 來避免亂碼。
- 把 `org.coindesk.api.model.domain.ws.CoindeskQuoteBpi` 改為 map 儲存幣別報價結構，以利未來 api 新增幣別欄位時不須調整程式。

Package & Components
-------------------------------------------------------------------------------
- `org.coindesk.api.proxy.CoindeskQuoteProxyImpl`: 負責將 coindesk api 的資料抓回來。
- `org.coindesk.api.business.CoindeskQuoteBusiness`: 跟上面的元件協作，將取回來的資料存到 H2 DB。
- `org.coindesk.api.model`: 放資料庫物件(entity)、存取 DB 的程式及 coindesk api web service 相關資料物件。