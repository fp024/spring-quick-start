package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.view.controller.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("로그아웃 처리");
		// 1. 브라우저와 연결된 세션 객체를 강제 종료한다.
		request.getSession().invalidate();
		// 2. 화면 네비게이션
		return "login";
	}

}
