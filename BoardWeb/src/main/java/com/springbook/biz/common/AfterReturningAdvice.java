package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Aspect
public class AfterReturningAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")	
	public void getPointcut() {
		// 포인트 컷 선언 메서드는 참조 메서드로만 지정.. 메서드 내용을 지정하지 않음.
	}
	
	@AfterReturning(pointcut = "getPointcut()", returning = "returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		
		if (returnObj instanceof UserVO) {
			UserVO user = (UserVO)returnObj;
			if("Admin".equals(user.getRole())) {
				logger.info("{} 로그인(admin)", user.getName());
			}
		}
		logger.info("[사후 처리] {}() 메서드 리턴값: {}", method, returnObj);
	}
}
