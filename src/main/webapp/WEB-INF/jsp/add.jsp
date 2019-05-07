<%@ page import="com.myhome.springCrudRest.model.Role" %>
<%@ page import="java.util.AbstractList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Add user</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>

<body>

<%
    List<Role> allRoles = (AbstractList<Role>) request.getAttribute("rolesFromServer");
%>

<div class="container">
    <h1>Add user:</h1>

    <form class="form-horizontal" action="/users/add" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="username">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" name="username" required placeholder="Enter login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="firstName">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="firstName" name="firstName" required placeholder="Enter name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">E-mail:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" required placeholder="Enter email">
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
            <label class="control-label col-sm-2" for="roles">Role:</label>
            <div class="col-sm-10">
                <select multiple class="form-control" id="roles" name="roles" required>
                    <%for (Role role : allRoles) {%>
                    <option name="id" value="<%=role.getId()%>">
                        <%=role.getAuthority()%>
                    </option>
                    <%}%>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Add</button>
                <a href="/users/list" class="btn btn-default">Back</a>
            </div>
        </div>
    </form>
</div>


<p>contextPath: <%=request.getContextPath()%></p>
<p>contextPath: ${pageContext.request.contextPath}</p>

</body>
</html>