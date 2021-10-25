CREATE TABLE users (id VARCHAR(8) PRIMARY KEY, password VARCHAR(8), name VARCHAR(20), u_role VARCHAR(5));

-- 테이블 생성 구문도 1줄로 만들어야 JPA가 정상 인식한다.