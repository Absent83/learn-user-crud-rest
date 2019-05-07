<%@ page import="com.myhome.springCrudRest.model.Role" %>
<%@ page import="com.myhome.springCrudRest.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Edit user</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>

<body>

<%
    User user = (User) request.getAttribute("userFromServer");
    List<Role> allRoles = (ArrayList<Role>) request.getAttribute("rolesFromServer");
%>

<div class="container">
    <h1>Edit user:</h1>
    <form
            action="/users/edit"
            method="POST"
            class="form-horizontal">

        <div class="form-group">
            <label class="control-label col-sm-2" for="id">Id:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="id" name="id" value="<%=user.getId()%>" readonly
                       required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="username">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" name="username" value="<%=user.getUsername()%>" required placeholder="Enter login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="firstName">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="firstName" name="firstName" value="<%=user.getFirstName()%>" required
                       placeholder="Enter name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">E-mail:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" value="<%=user.getEmail()%>" required
                       placeholder="Enter email">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password" value="<%=user.getPassword()%>" required placeholder="Enter password">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="roles">Roles:</label>
            <div class="col-sm-10">

                <select multiple class="form-control" id="roles" name="roles" required>
                    <%for (Role role : allRoles) {%>
                    <option name="id" value="<%=role.getId()%>"
                            <%=user.getRoles().contains(role) ? "selected" : ""%>>
                        <%=role.getAuthority()%>
                    </option>
                    <%}%>
                </select>

            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
                <a href="/users/list" class="btn btn-default">Back</a>
            </div>
        </div>
    </form>
</div>


<p>contextPath: <%=request.getContextPath()%></p>
<p>contextPath: ${pageContext.request.contextPath}</p>

</body>
</html>