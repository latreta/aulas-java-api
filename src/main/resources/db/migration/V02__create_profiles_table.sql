CREATE TABLE profiles (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO profiles (id, description) VALUES (0, 'ROLE_ADMIN');
INSERT INTO profiles (id, description) VALUES (11, 'ROLE_FUNCIONARIO');
INSERT INTO profiles (id, description) VALUES (12, 'ROLE_PROFESSOR');
INSERT INTO profiles (id, description) VALUES (13, 'ROLE_ALUNO');