package com.springbook.view.controller;

import lombok.Setter;

/**
 * Controller가 리턴한 View 이름에 접두사와 접미사를 결합하여, 
 * 최종으로 실행될 View 경로와 파일명을 완성한다.
 */
public class ViewResolver {
	@Setter
	private String prefix;
	@Setter
	private String suffix;

	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
}
