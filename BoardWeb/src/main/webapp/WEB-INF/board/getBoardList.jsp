<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<style type="text/css">
		table {
		    border-spacing: 0;
		}
		
		table th, table td {
		    padding: 0
		}
		
		body {
		    text-align: center;
		}
	</style>
	<title>글 목록</title>
</head>
<body>
	<h1>글 목록</h1>
	<h3><span style="color: red;">테스트</span>님 환영합니다...<a href="logout.do">Log-out</a></h3>
	
	<!-- 검색시작 -->
	<div style="margin: auto; display: block">
    <form action="getBoardList.do" method="post">
        <select name="searchCondition">
            <option value="TITLE">제목</option>
            <option value="CONTENT">내용</option>
        </select>
        <input name="searchKeyword" type="text">
        <button type="submit">검색</button>
    </form>
    </div>
	
	
	<table border="1" style="width: 700px; margin: auto">
	    <caption style="display:none">게시글 목록</caption>
	    <colgroup>
	        <col style="width:100px">
	        <col style="width:200px">
	        <col style="width:150px">
	        <col style="width:150px">
	        <col style="width:100px">
	    </colgroup>
	    <tr>
	        <th scope="col" style="background-color: orange;">번호</th>
	        <th scope="col" style="background-color: orange;">제목</th>
	        <th scope="col" style="background-color: orange;">작성자</th>
	        <th scope="col" style="background-color: orange;">등록일</th>
	        <th scope="col" style="background-color: orange;">조회수</th>
	    </tr>
	
		<c:forEach var="board" items="${boardList}">
	    <tr>
	        <td>${board.seq}</td>
	        <td><a href="getBoard.do?seq=${board.seq}">${board.title}</a></td>
	        <td>${board.writer}</td>
	        <td><fmt:formatDate value="${board.regDate}" pattern="yyy-MM-dd HH:mm"/></td>
	        <td>${board.cnt}</td>
	    </tr>
	    </c:forEach>
	    
	</table>
	<br>
	<a href="insertBoard.jsp">새글 등록</a>
</body>
</html>