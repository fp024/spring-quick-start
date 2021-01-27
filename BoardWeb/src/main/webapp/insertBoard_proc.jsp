<%@ page import="com.springbook.biz.board.impl.BoardDAO" %>
<%@ page import="com.springbook.biz.board.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 1. 사용자 입력 정보 추출
	request.setCharacterEncoding("UTF-8"); 
		// Tomcat 8.5의 server.xml의 HTTP/1.1 커넥터에 URIEncoding=UTF-8을 
		// 붙여줘야하나 했는데.. 따로 명시 않아도 등록에 이슈가 없음.
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	
	
	// 2. DB 연동 처리
	BoardVO vo = new BoardVO();
	vo.setTitle(title);
	vo.setWriter(writer);
	vo.setContent(content);
	
	BoardDAO dao = new BoardDAO();
	dao.insertBoard(vo);
	
	// 3. 화면 네비게이션
	response.sendRedirect("getBoardList.jsp");

%>