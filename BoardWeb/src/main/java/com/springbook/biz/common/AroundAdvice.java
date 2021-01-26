package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AroundAdvice {
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("[BEFORE]: 비즈니스 메서드 수행 전에 처리할 내용...");

		Object object = pjp.proceed();

		logger.info("[AFTER]: 비즈니스 메서드 수행 후에 처리할 내용...");

		return object;
	}
}
