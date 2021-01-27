package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Aspect
public class AroundAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {
		// 포인트 컷 선언 메서드는 참조 메서드로만 지정.. 메서드 내용을 지정하지 않음.
	}
	
	@Around(value = "allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object object = pjp.proceed();
		
		stopWatch.stop();

		logger.info("{}() 메서드 수행에 걸린 시간 : {}(ms)초", method, stopWatch.getTotalTimeMillis());

		return object;
	}
}
