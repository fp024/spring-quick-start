package com.springbook.biz.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Aspect
public class AfterAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {
		// 포인트 컷 선언 메서드는 참조 메서드로만 지정.. 메서드 내용을 지정하지 않음.
	}

	@After(value = "allPointcut()")
	public void finallyLog() {
		logger.info("[사후 처리] 비즈니스 로직 수행 후 무조건 동작");
	}
}
