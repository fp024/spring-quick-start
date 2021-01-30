<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title><spring:message code="message.user.login.title"/></title>
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
		<h1 style="text-align: center;"><spring:message code="message.user.login.title"/></h1>
		<p style="text-align: center;">
			<a href="login.do?lang=en">[<spring:message code="message.user.login.language.en"/>]</a>
			<a href="login.do?lang=ko">[<spring:message code="message.user.login.language.ko"/>]</a>
		</p>
		<form action="login.do" method="post">
		    <table border="1">
		        <caption></caption>
		        <colgroup>
		            <col style="width: 100px">
		            <col style="width: 100px">
		        </colgroup>
		        <tr>
		            <th scope="row"><spring:message code="message.user.login.id"/></th>
		            <td>
		                <input name="id" type="text" size="10" value="${userVO.id }">
		            </td>
		        </tr>
		        <tr>
		            <th scope="row"><spring:message code="message.user.login.password"/></th>
		            <td>
		                <input name="password" type="password" size="10" value="${userVO.password }">
		            </td>
		        </tr>
		        <tr>
		            <td colspan="2">
		                <input type="submit" value="<spring:message code="message.user.login.loginBtn"/>" style="display:block; margin:auto">
		            </td>
		        </tr>
		    </table>
		</form>
	</body>
</html>