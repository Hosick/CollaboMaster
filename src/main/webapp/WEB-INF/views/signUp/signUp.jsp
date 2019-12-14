<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hoseok
  Date: 2019-12-13
  Time: 오후 5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <%--버튼 css 적용--%>
    <link rel="stylesheet" type="text/css" href="button.css"/>
    <%--에러 css 적용--%>
    <link rel="stylesheet" type="text/css" href="error.css"/>
    <%--폼 css 적용--%>
    <%--<link rel="stylesheet" type="text/css" href="common.css"/>--%>
</head>
<body>
<div class="container">
    <h1>회원 가입</h1>
    <form:form method="post" modelAttribute="userRegistrationModel">
        <div class="form-group">
            <label>아이디:</label>
            <form:input path="id" class="form-control w200"/>
            <form:errors path="id" class="error"/>
        </div>
        <div class="form-group">
            <label>비밀번호:</label>
            <form:input path="password1" class="form-control w200"/>
            <form:errors path="password1" class="error"/>
        </div>
        <div class="form-group">
            <label>비밀번호 확인:</label>
            <form:input path="password2" class="form-control w200"/>
            <form:errors path="password2" class="error"/>
        </div>
        <div class="form-group">
            <label>이름:</label>
            <form:input path="name" class="form-control w200"/>
            <form:errors path="name" class="error"/>
        </div>
        <div class="form-group">
            <label>이메일:</label>
            <form:input path="email" class="form-control w200"/>
            <form:errors path="email" class="error"/>
        </div>
        <input type="submit" value="회원가입"/>
    </form:form>
</div>
</body>
</html>
