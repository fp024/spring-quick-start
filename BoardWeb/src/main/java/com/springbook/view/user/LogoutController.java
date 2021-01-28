package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("로그아웃 처리");
		// 1. 브라우저와 연결된 세션 객체를 강제 종료한다.
		request.getSession().invalidate();

		// 2. 화면 네비게이션
		return new ModelAndView("redirect:login.jsp");
	}

}
