package com.springbook.biz.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeforeAdvice {
	public void beforeLog() {
		logger.info("[사전처리] 비즈니스 로직 수행 전 동작");
	}
}
