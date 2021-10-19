<%--
  Created by IntelliJ IDEA.
  User: Le Linh
  Date: 10/16/2021
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<div style="text-align: center;">
    <h1>User Management</h1>
    <h2>
        <a href="new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Users</a>
    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
<%--                <td><c:out value="${user.userId}"/></td>--%>
<%--                <td><c:out value="${user.name}"/></td>--%>
<%--                <td><c:out value="${user.email}"/></td>--%>
<%--                <td><c:out value="${user.password}"/></td>--%>
                <td>
                    <a href="edit?id=<c:out value='${user.userId}' /> ">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${user.userId}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
