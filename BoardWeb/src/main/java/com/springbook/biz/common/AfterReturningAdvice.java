package com.springbook.biz.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AfterReturningAdvice {
	public void afterLog() {
		logger.info("[사후 처리] 비즈니스 로직 수행 후 동작");
	}
}
