<%--
  Created by IntelliJ IDEA.
  User: NICK
  Date: 03.04.2019
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en-US">
<html>

<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>



<body>

<div class="container">
    <h1>Login</h1>

    <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">Login or password is incorrect</div>
    </c:if>


    <form class="form-horizontal" action="/login" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="username">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" name="username" required placeholder="Enter login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password" required
                       placeholder="Enter password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>
</div>


</body>
</html>
