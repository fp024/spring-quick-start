<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>글 상세</title>
  <style type="text/css">
    table {
      border-spacing: 0;
      width: 700px;
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
<body>
<h1 style="text-align: center">글 상세</h1>
<p style="text-align: center;"><a href="logout.do">Log-out</a></p>
<form action="updateBoard" method="post">
  <table border="1">
    <caption>글 상세</caption>
    <colgroup>
      <col style="width: 80px">
      <col>
    </colgroup>

    <tr>
      <th scope="row">제목</th>
      <td>
        <input name="seq" type="hidden" value="${board.seq}">
        <input name="title" type="text" value="${board.title}">
      </td>
    </tr>
    <tr>
      <th scope="row">작성자</th>
      <td>${board.writer}</td>
    </tr>
    <tr>
      <th scope="row">내용</th>
      <td>
        <textarea name="content" rows="10" cols="60">${board.content}</textarea>
      </td>
    </tr>
    <tr>
      <th scope="row">등록일</th>
      <td><javatime:format value="${board.regDate}"
                           pattern="yyyy-MM-dd HH:mm:ss"></javatime:format></td>
    </tr>
    <tr>
      <th scope="row">조회수</th>
      <td>${board.cnt}</td>
    </tr>
    <tr>
      <td colspan="2">
        <button type="submit" style="display:block; margin:auto">게시글 수정</button>
      </td>
    </tr>
  </table>
</form>
<div style="text-align: center">
  <a href="insertBoard">[글 등록]</a>
  <button type="button" onclick="deleteBoard('${board.seq}')">[글 삭제]</button>
  <a href="getBoardList">[글 목록]</a>
</div>
<script type="text/javascript">
  function deleteBoard(seq) {
    const form = document.createElement("form");
    form.action = '/deleteBoard'
    form.method = 'post'
    const input = document.createElement("input");
    input.type = 'hidden';
    input.name = 'seq';
    input.value = seq;
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
  }
</script>
</body>

</html>