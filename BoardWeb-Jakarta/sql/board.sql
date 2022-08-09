CREATE TABLE board (
	seq 	NUMBER(5) PRIMARY KEY,
	title 	VARCHAR2(200),
	writer 	VARCHAR2(20),
	content	VARCHAR2(2000),
	regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	cnt 	NUMBER(5) DEFAULT 0
);

-- DATE가 일단위로 밖에 저장이 안되어서, TIMESTAMP로 바꾼다.
-- ALTER TABLE BOARD ALTER COLUMN REGDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
-- 그리고 DAO에서 읽을 때, Result의 getDate()대신 getTimestamp() 메서드를 통해 시간을 읽어야한다.
