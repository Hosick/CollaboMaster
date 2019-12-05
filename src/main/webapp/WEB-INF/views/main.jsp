<%--
  Created by IntelliJ IDEA.
  User: hoseok
  Date: 2019-11-18
  Time: 오후 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Test</title>
</head>
<body>
<h1>Simple Board</h1>
<h1>테스트 : ${postEx.title}</h1>
<c:if test="${user == null}">
    <div class="signin-cont cont">
        <form action="login" method="post" modelAttribute="User">
            <input type="text" name="id" id="id" required="required" placeholder="Id">
            <input type="password" name="password" id="password" required="required" placeholder="비밀번호">
            <input type="submit" value="로그인" class="submit">
        </form>
    </div>
</c:if>

<c:if test="${user != null}">
    <div>
        <h3>${user.name}님 안녕하세요</h3>
        <a href="/">로그아웃</a>
    </div>
</c:if>

<table>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${posts}">
        <tr>
            <td>${post.id}</td>
            <td>${post.title}</td>
            <td>작성자</td>
            <td>작성일</td>
            <td>조회수</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>