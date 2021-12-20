# spring-quick-start

## Spring Quick Start 도서의 스터디/실습 진행. (저자: 채규태 님)

* [yes24 도서 판매 페이지 링크](http://www.yes24.com/Product/Goods/29173715)



### 테스트 수행시 H2 DB가 서버 모드로 켜져있어야하는 프로젝트

~~BoardWeb은 메모리 DB로 설정하여 일부러 H2 DB를 켤 필요가 없지만, jpa-project와 mybatis-project는 H2가 켜진 상태에서 테스트를 수행해야함.~~

이번에 h2 버전업 하면서 모든 프로젝트 항상 H2가 켜져있어야게 수정했다. 어짜피 DB를 ~/test로 기본 경로로 안쓰면 자동으로 DB를 만들어내지 않음.



## 기타 

* log4j 디펜던시 검사
  * `mvnw dependency:list | findstr log4j`
