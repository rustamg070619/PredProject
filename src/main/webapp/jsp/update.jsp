<%--
  Created by IntelliJ IDEA.
  User: rustemrustem
  Date: 12.03.2020
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:set scope="request" var="userFirstName" value="${id}"/>
<c:set scope="request" var="userFirstName" value="${firstName}"/>
<c:set scope="request" var="userLastName" value="${lastName}"/>

<form method="post" action="<c:url value="/update"/>">
    <label for="firstName">Имя
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="put" value="put">
        <input type="text" id="firstName" name="firstName" value="${userFirstName}">
    </label>
    <label for="lastName">Фамилия
        <input type="text" id="lastName" name="lastName" value="${userLastName}">
    </label>
    <input type="submit" value="Изменить пользователя">
</form>

</body>
</html>
