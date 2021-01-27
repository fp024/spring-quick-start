package com.springbook.biz.common;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Log4jAdvice {
	public void pringLogging() {
		logger.info("[공통 로그-Log4j] 비즈니스 로직 수행 전 동작");
	}
}
