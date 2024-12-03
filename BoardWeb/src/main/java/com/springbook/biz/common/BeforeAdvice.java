package com.springbook.biz.common;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Aspect
@Order(value = 1)
public class BeforeAdvice {
  @Before("PointcutCommon.allPointcut()")
  public void beforeLog(JoinPoint jp) {
    String method = jp.getSignature().getName();
    Object[] args = jp.getArgs();
    logger.info("[사전 처리] {}() 메서드 ARGS 정보: {}", method, Arrays.toString(args));
  }
}
