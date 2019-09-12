CREATE TABLE aulas (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	turma VARCHAR(10) NOT NULL,
	disciplina_id BIGINT(20) NOT NULL,
	professor_id BIGINT(20) NOT NULL,
	sala_id BIGINT(20) NOT NULL,
	inicio VARCHAR(6) NOT NULL,
	fim VARCHAR(6) NOT NULL,
	FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id),
	FOREIGN KEY (professor_id) REFERENCES discentes(id),
	FOREIGN KEY (sala_id) REFERENCES salas(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;