CREATE TABLE users (
	id VARCHAR(8) PRIMARY KEY,
	password VARCHAR(8),
	name VARCHAR(20),
	"role" VARCHAR(5)
);

INSERT INTO users (id, password, name, "role") VALUES ('test', 'test123', '관리자', 'Admin');
INSERT INTO users (id, password, name, "role") VALUES ('user1', 'user1', '홍길동', 'User');