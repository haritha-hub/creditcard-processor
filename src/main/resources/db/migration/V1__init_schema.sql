CREATE TABLE IF NOT EXISTS credit_card_details (
                              cc_number VARCHAR NOT NULL,
                              account_name CLOB NOT NULL,
                              cc_limit CLOB NOT NULL,
                              cc_balance CLOB NOT NULL,
                              primary key (cc_number)
);