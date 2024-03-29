CREATE TABLE salas (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(5) NOT NULL,
	block_id BIGINT(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY(block_id) REFERENCES blocos(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;