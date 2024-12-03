package com.springbook.biz.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service
// @Aspect
public class AroundAdvice {
  @Around(value = "PointcutCommon.allPointcut()")
  public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
    String method = pjp.getSignature().getName();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    Object object = pjp.proceed();

    stopWatch.stop();

    logger.info("{}() 메서드 수행에 걸린 시간 : {}(ms)초", method, stopWatch.getTotalTimeMillis());

    return object;
  }
}
