<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>새글 등록</title>
	<style type="text/css">
        table {
            border-spacing: 0;
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
<body>
	<h1 style="text-align: center">글 등록</h1>
	<p style="text-align: center;"><a href="logout_proc.jsp">Log-out</a></p>
	<hr>
	
	<form action="insertBoard_proc.jsp" method="post">
 		<table border="1" style="margin: auto">
	        <caption>새글 등록 양식</caption>
	        <tr>
	            <th scope="row">제목</th>
	            <td>
	                <input type="text" name="title" size="50">
	            </td>
	        </tr>
	        <tr>
	            <th scope="row">작성자</th>
	            <td>
	                <input type="text" name="writer" size="10">
	            </td>
	        </tr>
	        <tr>
	            <th scope="row">내용</th>
	            <td>
	                <textarea name="content" rows="10" cols="50"></textarea>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="2">
	                <button type="submit" style="display:block; margin:auto">새글 등록</button>
	            </td>
	        </tr>
    	</table>
	</form>
	<div style="text-align: center">
    	<a href="getBoardList.jsp">[글 목록]</a>
	</div>
</body>
</html>