package com.springbook.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	/**
	 * DispatcherServlet으로 부터 넘어온 요청처리 및 View 이름 반환
	 */
	String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
