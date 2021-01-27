package com.springbook.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Order(value = 2) // 공통 레벨에 해서는 순서없이 랜덤이기 때문에... 순서를 정의하기 위해서는 @Order 를 사용해서 정의  
@Aspect 
public class LogAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")	
	public void allPointcut() {
		// 포인트 컷 선언 메서드는 참조 메서드로만 지정.. 메서드 내용을 지정하지 않음.
	}
	
	@Before("allPointcut()")
	public void printLog() {
		logger.info("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
