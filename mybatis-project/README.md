## 참고 링크들...

* https://www.infoworld.com/article/2073587/javac-s--xlint-options.html
* MyBatis Generator
	* https://mybatis.org/generator/configreference/javaModelGenerator.html
	
* MyBatis 매퍼
	* https://mybatis.org/spring/ko/mappers.html
	
	
### 정오표
* p506 
	* org.apache.ibatis.session.SqlSession 인터페이스의 메서드들이 SQLException를 체크 예외로 선언하지 않는데... update와 delete에 대해서 확인 후 지워주셔야할 것 같습니다.