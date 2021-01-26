package com.springbook.biz.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogAdvice {
	public void printLog() {
		logger.info("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
