<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>Main Page</title>
	</head>
	<body style="text-align: center;">
		<h1>게시판 프로그램</h1>
		<hr>
			<a href="login.do">로그인</a><br><br><br>
			<a href="getBoardList.do">글 목록 바로가기</a>
			<a href="dataTransform.do">글 목록 변환 처리</a>
		<hr>		
	</body>
</html>