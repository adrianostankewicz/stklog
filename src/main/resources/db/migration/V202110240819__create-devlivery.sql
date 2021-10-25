CREATE TABLE delivery
(
    id bigserial NOT NULL PRIMARY KEY,
    client_id bigint NOT NULL,
    rate bigint NOT NULL,
    status character varying[] NOT NULL,
    request_date timestamp NOT NULL,
    finished_date timestamp NOT NULL,
	
	recipient_name character varying[] NOT NULL,
	recipient_public_place character varying[] NOT NULL,
	recipient_number character varying[] NOT NULL,
	recipient_complement character varying[] NOT NULL,
	recipient_district character varying[] NOT NULL
);

ALTER TABLE delivery ADD FOREIGN KEY (client_id) REFERENCES client;