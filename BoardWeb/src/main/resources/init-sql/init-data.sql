INSERT INTO users (id, password, name, u_role) VALUES ('test', 'test123', '관리자', 'Admin');
INSERT INTO users (id, password, name, u_role) VALUES ('user1', 'user1', '홍길동', 'User');

-- javax.persistence.sql-load-script-source 의 속성으로 넣을수 있는 초기화 스크립트가 1개만 가능해서,
-- https://chrysanthium.com/multiple-sql-load-scripts 에 따르면 빌드시점에 병합시키는 부분이 있던데,
-- 내용이 얼마 안되니... 난 그냥 한 파일로 합쳐두자.. 
