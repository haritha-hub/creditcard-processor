CREATE TABLE IF NOT EXISTS credit_card (
                              cc_number VARCHAR NOT NULL,
                              account_name CLOB NOT NULL,
                              cc_limit CLOB NOT NULL,
                              cc_balance CLOB NOT NULL DEFAULT 0.0,
                              primary key (cc_number)
);