CREATE TABLE config (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT, 
	secret_key VARCHAR(300) NOT NULL,
	expiration_time VARCHAR(20) NOT NULL
);

INSERT INTO config (id, secret_key, expiration_time) VALUES (1, 'teste', '86400000');