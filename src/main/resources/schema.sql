DROP TABLE IF EXISTS coindesk;

CREATE TABLE CITY (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY
    , curr_code    VARCHAR(10)
    , symbol       VARCHAR(50)
    , rate         DECIMAL(20, 4)
    , description  VARCHAR(200)
    , updated_at   TIMESTAMP
);

