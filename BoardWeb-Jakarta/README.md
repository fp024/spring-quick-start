# Jakarta Servlet 5.0 적용

> 처음은 JPA 3.0을 적용시켜보고 싶어서였지만... 기왕 바꿔 보는거 다 올려봤다. 😅
>
> * 주요 업그레이드된 라이브러리
>   * Spring 6.x, Hibernate 6.x, Jetty 11.x



## 변경간 특이사항

### 0. 주요 버전 정의

프로젝트 구조를 부모 - 자식 관계로 만들어 두었기 때문에...  버전을 재정의 할 것만 하위 프로젝트에 프로퍼티로 정의 했다.

```xml
<properties>
  <org.springframework-version>6.0.0-M5</org.springframework-version>
  <!-- Hibernate 6.x를 정식 지원하지 않는 것 같긴하지만.. 6.1.2.Final 최신으로 사용해보자! -->
  <hibernate.version>6.1.2.Final</hibernate.version>
  <java-time-jsptags.version>2.0.0</java-time-jsptags.version>
  <jetty.version>11.0.11</jetty.version>
</properties>
```



### 1. 스프링 마일스톤 레파지토리를 추가

스프링 6버전이 아직 마일스톤 단계여서 Maven 공식에서는 라이브러리가 없음 아래와 같이 레파지토리 추가함.

```xml
  <repositories>
    <!-- Spring 마일스톤 버전을 사용하기 위해 레파지토리 설정이 필요함. -->
    <repository>
      <id>spring-milestones-repo</id>
      <name>Spring Milestones Repository</name>
      <url>https://repo.spring.io/milestone</url>
    </repository>
  </repositories>
```



### 2.  javax 관련 모듈을 jakarta 모듈로 변경

jetty 컨테이너에서 제공이 되는 것은 provided 스코프로 하고 대부분 쓰던거 맞춰서 바꿈.

```xml
    <!-- @Inject -->
    <!--
    <dependency>
      <groupId>javax.inject</groupId>
      <artifactId>javax.inject</artifactId>
      <version>1</version>
    </dependency>
    -->
    <dependency>
      <groupId>jakarta.inject</groupId>
      <artifactId>jakarta.inject-api</artifactId>
      <version>2.0.1</version>
    </dependency>

    <!-- Servlet -->
    <!--
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    -->
    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>5.0.0</version>
      <scope>provided</scope>
    </dependency>
    <!--
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>javax.servlet.jsp-api</artifactId>
      <version>2.3.3</version>
      <scope>provided</scope>
    </dependency>
    -->
    <dependency>
      <groupId>jakarta.servlet.jsp</groupId>
      <artifactId>jakarta.servlet.jsp-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <!--
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
      <scope>runtime</scope>
    </dependency>
    -->
    <dependency>
      <groupId>jakarta.servlet.jsp.jstl</groupId>
      <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
      <version>2.0.0</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>org.glassfish.web</groupId>
      <artifactId>jakarta.servlet.jsp.jstl</artifactId>
      <version>2.0.0</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>jakarta.xml.bind</groupId>
      <artifactId>jakarta.xml.bind-api</artifactId>
      <version>4.0.0</version>
      <scope>provided</scope>
    </dependency>
```



### 3.  web.xml에 정의 변경

3.0기준으로 정의했던 부분 5.0으로 바꿈.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app
  xmlns="https://jakarta.ee/xml/ns/jakartaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
  version="5.0"
  metadata-complete="false"
>
```



### 4.  파일업로드 관련하여 commons-fileupload 대신 서블릿 컨테이너에서 제공해주는 방식으로 바꿈.

* web.xml 

  `multipart-config` 태그 부분 추가

  ```xml
    <servlet>
      <servlet-name>action</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/config/presentation-layer.xml</param-value>
      </init-param>
  
      <multipart-config>
        <!-- 20MB가 넘는 상황이 발생할 때, 임시로 파일을 저장할 공간 -->
        <location>C:\\upload\\temp</location>
        <!-- 업로드되는 파일의 최대 크기 -->
        <max-file-size>20971520</max-file-size> <!-- 1MB * 20 -->
        <!-- 한번에 올릴 수 있는 최대 크기 -->
        <max-request-size>41943040</max-request-size> <!-- 40MB -->
        <!-- 파일이 메모리에 기록되는 임계값  -->
        <file-size-threshold>20971520</file-size-threshold> <!-- 20MB -->
      </multipart-config>
    </servlet>
  ```

* presentation-layer.xml

  `CommonsMultipartResolver`는 이제 스프링 6에서는 완전히 제거됨. 이젠 컨테이너에서 제공해주는거 그냥 쓰라는 것 같음..😅

  ```xml
  <!-- 파일 업로드 설정 -->
  <bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver" />
  ```

* commons-fileupload 디펜던시 제거

  

### 5. java 소스에서  javax 패키지 관련해서 jakarta로 바꿈

이런 종류의 클래스들 jakarta 패키지로 바꿈.

```java
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
...
```



### 6.  Hibernate 관련 변경

* Hibernate 6.1.x부터는 hibernate-entitmanager 를 따로 디펜던시하지 말고 core만 디펜던시하면 되는 듯.. 

  ```xml
      <!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
      <!--
      <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>${hibernate.version}</version>
      </dependency>
      -->
      <!-- hibernate-core 6.x 부터는 core만 있으면 되는듯, -->
      <!-- 스프링6-M5의 기본 디펜던시 버전
      <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core-jakarta</artifactId>
        <version>5.6.10.Final</version>
      </dependency>
      -->
      <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>${hibernate.version}</version>
      </dependency>
  ```

* Embedded DB 초기화 SQL 스크립트 실행시 다중라인도 인식 시키기위해 추가한 클래스가 이름이 바뀜.

  ```xml
  <!-- SQL 스크립트에 여러 줄의 단일 SQL 문장 포함시 입력 오류가 발생해서 아래 내용 추가 -->
  <!-- https://softwarecave.org/2017/02/12/database-schema-creation-in-jpa-using-sql-scripts/ -->
  <!--<prop key="hibernate.hbm2ddl.import_files_sql_extractor">org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor</prop>-->
  <!-- Hibernate 6을 사용하게 되면 아래 클래스를 사용할 것... -->
  <prop key="hibernate.hbm2ddl.import_files_sql_extractor">org.hibernate.tool.schema.internal.script.MultiLineSqlScriptExtractor</prop>
  
  ```

* Hibernate 6에서 없는 클래스를 import하는 부분이있어 방식을 바꿈.

  원래 `HibernateJpaVendorAdapter` 를 빈으로 설정해서 알려줬는데... 이 클래스에서 Hibernate 6에는 없는 클래스를 import 하고 있길레`SpringHibernateJpaPersistenceProvider`를 전달하도록 바꿈.

  ```xml
  <!-- 스프링과 JPA 연동 설정 -->
  <!-- <bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" /> -->
  <bean id="jpaPersistenceProvider" class="org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider" />
  
  
  <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
    <property name="persistenceProvider" ref="jpaPersistenceProvider" />
    <!-- ... -->
  </bean>
  ```

  

### 7. 🎈  이상한 문제인데... 그냥 해결한 문제

`NestedIOException` 클래스를 못찾는다고 하면서 SqlSessionFactory 빈이나, JPA관련해서 초기화 오류가남...

==> `org.springframework.core.NestedIOException` 클래스를 패키지명 맞춰서 프로젝트에 포함시키고 나서 해결됨.

```java
package org.springframework.core;

import java.io.IOException;
import javax.annotation.Nullable;

/**
 * Spring 6에서 이 클래스가 제거되었는데... 사용처가 있는지? 프로그램 시작때 ClassNotFound 예외로 빈들이 생성되지 않는다.
 *
 * <p>이 클래스를 프로젝트에 강제로 추가해주었을 때, 해결은 하였다.
 * https://github.com/spring-projects/spring-framework/commit/2fb1dd177b7b056f30a9de0739d8afdef37d72aa?diff=unified#diff-cae26b2a33fc578495f60916876a609d0270cf0ef6680944a2bce9d5795cbcb2
 */
public class NestedIOException1 extends IOException {

  static {
    // Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
    // issues on OSGi when calling getMessage(). Reported by Don Brown; SPR-5607.
    NestedExceptionUtils.class.getName();
  }

  /**
   * Construct a {@code NestedIOException} with the specified detail message.
   *
   * @param msg the detail message
   */
  public NestedIOException1(String msg) {
    super(msg);
  }

  /**
   * Construct a {@code NestedIOException} with the specified detail message and nested exception.
   *
   * @param msg the detail message
   * @param cause the nested exception
   */
  public NestedIOException1(@Nullable String msg, @Nullable Throwable cause) {
    super(msg, cause);
  }

  /** Return the detail message, including the message from the nested exception if there is one. */
  @Override
  @Nullable
  public String getMessage() {
    return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
  }
}

```

이부분은 나중에 바뀔수도 있겠다. mybatis-spring의 문제인지? 뭔지 정확하게 모르겠는데...





## 기타

* Servlet 5.0 적용과는 별개의 문제이긴한데.. `LocalDateTime` 관련해서 `Gson`컨버터를 사용자 정의하지 않아서 오류가 나는 부분이 있었다 관련해서 추가함.



## 의견

* Servlet 5.0 업그레이드 과정에서 문제가 많을 줄 알았는데... 큰 문제없이 끝난것 같다..🎉

