## 프로젝트 실행 방법

* 실행 명령
    ```bash
    mvnw clean jetty:run
    ```
    
* 접속 주소
  
  * http://localhost:8080/board_web/
  
* DB
  
  * h2 DB Embbed Memory 방식
  * 프로젝트 종료되면 데이터 없어짐
  * 프로젝트 시작될 때, 기본 유저 2명 INSERT




## 서평

* AOP 관련 예제가 점진적으로 이해하기 쉽게 되어있음.
* JDBC 진행방식도 자세히 되어있음.
* 레거시 코드를 근래의 프레임워크로 전환할 일을 맡게 될 수도 있는데, 
책에서 Model 1, Model 2 아키텍처 기반으로 개발 흐름을 보여줘서 레거시 코드의 신규 프레임워크 전환시 전반적인 이해에 도움이 많이 될 것으로 보입니다.
* 스프링 최신 버전을 경험했을 때, 어노테이션 기반으로만 하다보면 어떻게 돌아가는지 잘 파악이 안될 때가 있는데, DAY 03의 후반부에 원리가 잘 설명이 되어 좋았습니다. 



## 의견
* 개정판이 나온다면...
	* DataSource를 commons-dbcp에서 HikariCP로 변경해도 좋을 것 같습니다..
	* Tomcat 8.x로 진행하다보니까 Servlet 3.0으로 진행이 되어서.. 서블릿 클래스 생성시
	`@WebServlet(name = "action", urlPatterns = { "*.do" })` 와 같은 어노테이션 붙더라구요. web.xml에 특별히 기입할 필요는 없었습니다. 간단하게 언급이 되면 좋을 것 같습니다.
	* lombok을 사용하고 있고, log4j.xml 설정도 설명하셨으므로, 중반 이후 부터는 Sysout 콘솔 로그들을 @Slf4j 붙여서 로깅으로 출력되도록 하는것이 좋을 것 같습니다.
	* day 4 까지 VO에 대한 데이터 타입을 java.sql.Date로 쓰는데, 처음부터 java.util.Date로 쓰고 날짜 포멧 출력하는 JSTL는 먼저 그냥 알려주고, h2의 데이터도 시간까지 저장(DATE대신 TIMESTAMP 컬럼타입사용)하는게 나을 것 같은 생각이 듭니다.

## 중요 문구
* 스프링 컨테이너가 생성하는 Command 객체의 이름은 클래스 이름의 첫 글자를 소문자로 변경한 이름이 자동으로 설정된다. (p377)
* 리턴이 Map일 때 JSP에서 ${요소이름.value} ${요소이름.key} 와 같이 사용하는 것...

## 정오표
* p554: 마지막 단락에서, '데이터베이서 연동' -> '데이터베이스 연동'


## 기타
* Eclipse에서의 JPA 프로젝트는 persistence.xml 가 반드시 있을 것으로 간주하고 없으면 ERROR 알림을 나타는데, 
  "No persistence.xml file found in project”   프로젝트의 JPA 이하의 Error/Warnings 항목에서 무시처리해주면 된다.
  https://stackoverflow.com/questions/20287026/how-to-tell-eclipse-to-ignore-no-persistence-xml-file-found-in-project