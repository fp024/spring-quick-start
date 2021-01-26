package com.springbook.biz.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AfterThrowingAdvice {	
	public void exceptionLog() {
		logger.error("[예외 처리] 비즈니스 로직 수행 중 예외 발생");
	}
}
