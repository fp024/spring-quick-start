CREATE TABLE board (
	seq 	NUMBER(5) PRIMARY KEY,
	title 	VARCHAR2(200),
	writer 	VARCHAR2(20),
	content	VARCHAR2(2000),
	regdate DATE default SYSDATE,
	cnt 	NUMBER(5) DEFAULT 0
);
