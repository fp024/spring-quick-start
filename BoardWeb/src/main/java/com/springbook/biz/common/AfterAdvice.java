package com.springbook.biz.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AfterAdvice {
	public void finallyLog() {
		logger.info("[사후 처리] 비즈니스 로직 수행 후 무조건 동작");
	}
}
