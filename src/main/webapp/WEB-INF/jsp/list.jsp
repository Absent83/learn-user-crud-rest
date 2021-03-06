<%@ page import="com.myhome.springCrudRest.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>List of users</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>

<body>

<%
    List<User> users = (ArrayList<User>) request.getAttribute("usersFromServer");
%>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1>List of users</h1>
        </div>
        <div class="col-sm-4 offset-sm-8 align-self-center">
            <a href="/profile" class="btn btn-default">Profile</a>
            <a href="/logout" class="btn btn-default">Logout</a>
        </div>
    </div>

    <p>Count: <%=users.size()%> users</p>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Id</th>
            <th>Login</th>
            <th>Name</th>
            <th>E-mail</th>
            <th>Roles</th>
            <th>Password</th>
            <th></th>
            <th>
                <form>
                    <button type="submit" formaction="/users/add" formmethod="get">Add</button>
                </form>
            </th>
        </tr>
        </thead>
        <tbody>
        <%
            for (User user : users) {
        %>
        <tr>
            <td><%=user.getId()%>
            </td>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getFirstName()%>
            </td>
            <td><%=user.getEmail()%>
            </td>
            <td><%=user.getRoles().stream().map(x -> x.getAuthority()).reduce("", (x,y) -> x+y+"\n")%>
            </td>
            <td><%=user.getPassword()%>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/users/edit" method="get">
                    <input type="hidden" name="userId" value="<%=user.getId()%>"/>
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form>
                    <button type="submit" formaction="/users/delete" formmethod="post" name="userId"
                            value="<%=user.getId()%>">Delete
                    </button>
                </form>
            </td>
        </tr>
        <%}%>

        </tbody>
    </table>
</div>



<p>contextPath: <%=request.getContextPath()%></p>
<p>contextPath: ${pageContext.request.contextPath}</p>

</body>
</html>