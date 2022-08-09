package com.springbook.biz.common;

import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Order(value = 2) // 공통 레벨에 해서는 순서없이 랜덤이기 때문에... 순서를 정의하기 위해서는 @Order 를 사용해서 정의
// @Aspect
public class LogAdvice {
  @Before("PointcutCommon.allPointcut()")
  public void printLog() {
    logger.info("[공통 로그] 비즈니스 로직 수행 전 동작");
  }
}
