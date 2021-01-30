<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>기본 에러 화면</title>
		<style type="text/css">
			table {
	            border-spacing: 0;
	            width: 100%;
	            margin: auto;
	        }
	
	        table th, table td {
	            padding: 0
	        }
	
	        table th {
	            background-color: orange;
	        }
	
	        table caption {
	            display: none
	        }
		</style>
	</head>
	<body style="background-color: #FFFFFF; color: #000000" >

	<!-- 타이틀 시작 -->
	<table border="1">
		<caption>타이틀/본문</caption>
		<tr>
			<th scope="row" style="height: 30px"><strong>기본 에러 화면 입니다.</strong></th>
		</tr>
		<tr>
			<td style="border-left: 0px; border-right: 0px; height: 20px;"></td>
		<tr>
		<tr>
			<td style="padding-top: 100px; padding-bottom: 100px; text-align: center">
				Message: ${exception.message}
			</td>
		</tr>		
	</table>
	</body>
</html>