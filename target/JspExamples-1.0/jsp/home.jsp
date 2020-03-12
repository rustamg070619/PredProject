<%--
  Created by IntelliJ IDEA.
  User: rustemrustem
  Date: 12.03.2020
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <h1 align="center">Список пользователей</h1>
</div>
<hr>
<div class="form-style-2">
    <table align="center">
        <tr>
            <th align="left">Id</th>
            <th align="left">Имя</th>
            <th align="left">Фамилия</th>

            <c:forEach items="${usersFromServer}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>
                <form method="post" action="/delete">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" name="id" value="Удалить">
                </form>
            </td>
            <td>
                <form method="post" action="/update">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" name="id" value="Редактировать">
                </form>
            </td>

        </tr>
        </c:forEach>
        <tr>
            <td>
                <form method="get" action="/addUser">
                    <button>Добавить</button>
                </form>
            </td>
        </tr>
        </tr>
    </table>
</div>
</body>
</html>
