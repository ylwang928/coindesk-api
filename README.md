coindesk-api (currency)
===============================================================================

備註
-------------------------------------------------------------------------------
- 因為 `coindesk-api` 回傳之 `Content-Type` 是 `application/javascript`，似乎無法直接套用 Spring 的 `RestTemplate`，故改用最原始的方式取得 http 回應。
- 因轉換後的 API、欄位中含有中文，故需要在參數檔 `application.yml` 指定 `server.servlet.encoding.charset: UTF-8` 及 `server.servlet.encoding.force-response: true` 來避免亂碼。

