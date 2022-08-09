INSERT INTO users (id, password, name, u_role)
  VALUES ('test', 'test123', '관리자', 'Admin');
INSERT INTO users (id, password, name, u_role)
  VALUES ('user1', 'user1', '홍길동', 'User');

-- javax.persistence.sql-load-script-source 의 속성으로 넣을수 있는 초기화 스크립트가 1개만 가능해서,
-- https://chrysanthium.com/multiple-sql-load-scripts 에 따르면 빌드시점에 병합시키는 부분이 있던데,
-- 내용이 얼마 안되니... 난 그냥 한 파일로 합쳐두자.. 

-- H2 DB에서 role이 DB에의 예약어(이미 문법적인 용도로 사용되고 있기 때문에 식별자로 사용할 수 없는 단어)여서, 
-- "role"로 하더라도 JPA 스크립트로 입력할 때는 오류가 난다. 이에 이름을 u_role로 바꾸고, 
-- 서비스 단에서만 role로 사용한다. 
