CREATE TABLE blocos (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(5) UNIQUE NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO blocos (name) VALUES ('A');
INSERT INTO blocos (name) VALUES ('B');
INSERT INTO blocos (name) VALUES ('C');
INSERT INTO blocos (name) VALUES ('I');
INSERT INTO blocos (name) VALUES ('J');
INSERT INTO blocos (name) VALUES ('K');
INSERT INTO blocos (name) VALUES ('LIP');