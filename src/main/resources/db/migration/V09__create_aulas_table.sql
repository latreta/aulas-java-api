CREATE TABLE aulas (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cadeira_id BIGINT(20) NOT NULL,
	professor_id BIGINT(20) NOT NULL,
	sala_id BIGINT(20) NOT NULL,
	FOREIGN KEY (cadeira_id) REFERENCES cadeiras(id),
	FOREIGN KEY (professor_id) REFERENCES docentes(id),
	FOREIGN KEY (sala_id) REFERENCES salas(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;