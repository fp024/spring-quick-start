<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<br>
			<a href="javascript:test();">글 목록 변환 처리(글등록 후 브라우저 콘솔로그를 확인하세요.😅)</a>
		<hr>		
	</body>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
	
	
	<script type="text/javascript">
	function test() {
		 $.ajax({
		 	      url:'dataTransform.do', 
		 	      type:'post',
				  data: {title:"제목", searchKeyword:"검색키워드"},
		 	      success: function(data) {
					console.log(data);
					console.log(data.boardList[0]);
		 	      }
		    });
	}
	</script>
	
</html>