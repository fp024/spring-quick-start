package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//@Aspect
public class AfterThrowingAdvice {
	@AfterThrowing(pointcut = "PointcutCommon.allPointcut()", throwing = "exceptObj")
	public void exceptionLog(JoinPoint jp, Exception exceptObj) {

		String method = jp.getSignature().getName();

		logger.info("[예외 처리] {}() 메서드 수행 중 예외 발생", method);

		if (exceptObj instanceof IllegalArgumentException) {
			logger.error("부적합한 값이 입력되었습니다.");
		} else if (exceptObj instanceof NumberFormatException) {
			logger.error("숫자 형식의 값이 아닙니다.");
		} else if (exceptObj instanceof Exception) {
			logger.error("문제가 발생했습니다.");
		}
	}
}
