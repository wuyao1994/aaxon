<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<%@page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Regist</title>
   </head>
<body>
<a href="/login">SignIn</a> &nbsp;<br/>
<c:forEach items="${Errors}"  var="error">
    <span>${error.defaultMessage}</span><br>
</c:forEach>
<br/>
    <form action="/doRegist" name="form" method="post" modelAttribute="form">
        Regist:
        <br/>
        username: <input type="text" name="username">
        <br/>
        password: <input type="password" name="password">
        <br>
        password repeat: <input type="password" name="passwordRepeated">
        <br>
        role:<select name="role">
            <option value="ROLE_USER">USER</option>
            <option value="ROLE_ADMIN">ADMIN</option>
        </select>
        <input type="submit"/>
    </form>
</body>
</html>