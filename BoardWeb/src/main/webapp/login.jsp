<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<style type="text/css">
		        table {
		            border-spacing: 0;
		            margin: auto
		        }
		
		        table caption {
		            display: none
		        }
		
		        table th, table td {
		            padding: 0
		        }
		
		        table th {
		            background-color: orange;
		        }
		</style>
	</head>
	<body>
		<h1 style="text-align: center;">로그인</h1>
		<form action="login.do" method="post">
		    <table border="1">
		        <caption>로그인 폼</caption>
		        <colgroup>
		            <col style="width: 100px">
		            <col style="width: 100px">
		        </colgroup>
		        <tr>
		            <th scope="row">아이디</th>
		            <td>
		                <input name="id" type="text" size="10">
		            </td>
		        </tr>
		        <tr>
		            <th scope="row">비밀번호</th>
		            <td>
		                <input name="password" type="password" size="10">
		            </td>
		        </tr>
		        <tr>
		            <td colspan="2">
		                <input type="submit" value="로그인" style="display:block; margin:auto">
		            </td>
		        </tr>
		    </table>
		</form>
	</body>
</html>