package com.springbook.biz.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Aspect
public class AfterAdvice {
	@After(value = "PointcutCommon.allPointcut()")
	public void finallyLog() {
		logger.info("[사후 처리] 비즈니스 로직 수행 후 무조건 동작");
	}
}
