package com.springbook.biz.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Log4jAdvice {
  public void printLogging() {
    logger.info("[공통 로그-Log4j] 비즈니스 로직 수행 전 동작");
  }
}
