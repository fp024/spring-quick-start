<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>
<%@ taglib prefix="spring" 		uri="http://www.springframework.org/tags" %>
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
	<title><spring:message code="message.board.list.mainTitle"/></title>
</head>
<body>
	<h1><spring:message code="message.board.list.mainTitle"/></h1>
	<h3><span style="color: red;">${userName}</span><spring:message code="message.board.list.welcomeMsg"/> <a href="logout">[Log-out]</a></h3>
	
	<!-- 검색시작 -->
	<div style="margin: auto; display: block">
    <form action="getBoardList" method="get">
        <select name="searchCondition">
        	<c:forEach var="condition" items="${conditionMap}">
            	<option value="${condition.value}"><spring:message code="${condition.key}"/></option>
            </c:forEach>
        </select>
        <input name="searchKeyword" type="text">
        <button type="submit"><spring:message code="message.board.list.search.btn"/></button>
    </form>
    </div>
	
	
	<table border="1" style="width: 700px; margin: auto">
	    <caption style="display:none"><spring:message code="message.board.list.mainTitle"/></caption>
	    <colgroup>
	        <col style="width:100px">
	        <col style="width:200px">
	        <col style="width:150px">
	        <col style="width:150px">
	        <col style="width:100px">
	    </colgroup>
	    <tr>
	        <th scope="col" style="background-color: orange;"><spring:message code="message.board.list.table.head.seq"/></th>
	        <th scope="col" style="background-color: orange;"><spring:message code="message.board.list.table.head.title"/></th>
	        <th scope="col" style="background-color: orange;"><spring:message code="message.board.list.table.head.writer"/></th>
	        <th scope="col" style="background-color: orange;"><spring:message code="message.board.list.table.head.regDate"/></th>
	        <th scope="col" style="background-color: orange;"><spring:message code="message.board.list.table.head.cnt"/></th>
	    </tr>
	
		<c:forEach var="board" items="${boardList}">
	    <tr>
	        <td>${board.seq}</td>
	        <td><a href="getBoard?seq=${board.seq}">${board.title}</a></td>
	        <td>${board.writer}</td>
	        <td><javatime:format value="${board.regDate}" pattern="yyyy-MM-dd HH:mm"></javatime:format></td>
	        <td>${board.cnt}</td>
	    </tr>
	    </c:forEach>
	    
	</table>
	<br>
	<a href="insertBoard"><spring:message code="message.board.list.insertBoard"/></a>
</body>
</html>