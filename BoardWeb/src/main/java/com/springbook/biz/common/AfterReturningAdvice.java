package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

import com.springbook.biz.user.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AfterReturningAdvice {
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
