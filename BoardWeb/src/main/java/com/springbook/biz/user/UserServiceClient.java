package com.springbook.biz.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceClient.class);

  public static void main(String[] args) {

    // 1. Spring 컨테이너를 구동한다.
    AbstractApplicationContext container =
        new GenericXmlApplicationContext("applicationContext.xml");

    // 2. Spring 컨테이너로 부터 UserServiceImpl 객체를 Lookup한다
    UserService userService = (UserService) container.getBean("userService");

    // 3. 로그인 기능 테스트
    UserVO vo = new UserVO();
    vo.setId("test");
    vo.setPassword("test123");

    UserVO user = userService.getUser(vo);

    if (user == null) {
      LOGGER.info("로그인 실패");
    } else {
      LOGGER.info("{} 로그인 환영합니다.", user.getName());
    }

    // 4. Spring 컨테이너를 종료한다.
    container.close();
  }
}
