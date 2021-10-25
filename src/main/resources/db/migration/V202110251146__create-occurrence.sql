CREATE TABLE occurrence (
	id bigserial NOT NULL PRIMARY KEY,
	delivery_id bigint NOT NULL,
	description text NOT NULL,
	registry_date timestamp NOT NULL
);

ALTER TABLE occurrence ADD FOREIGN KEY (delivery_id) REFERENCES delivery;