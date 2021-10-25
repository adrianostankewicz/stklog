CREATE TABLE client (
	id bigserial NOT NULL PRIMARY KEY,
	first_name character varying [255] NOT NULL,
	last_name character varying [255] NOT NULL,
	doc character varying [255] NOT NULL,
	email character varying [255] NOT NULL,
	cellphone character varying [255] NOT NULL,
	birth_date timestamp NOT NULL 
);
