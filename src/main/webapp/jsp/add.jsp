<%@ page import="com.myhome.springCrudRest.model.User" %>
<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Add user</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>

<body>

<div class="container">
    <h1>Add user:</h1>

    <form class="form-horizontal" action="/users/add" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="userName">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="userName" name="userName" required placeholder="Enter name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">E-mail:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" required placeholder="Enter email">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>
</div>


<p>contextPath: <%=request.getContextPath()%>
</p>
<p>contextPath: ${pageContext.request.contextPath}</p>

</body>
</html>