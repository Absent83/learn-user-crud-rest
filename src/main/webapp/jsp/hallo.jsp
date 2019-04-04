<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Hallo</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>

<body>


<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1>Hallo</h1>
        </div>
        <div class="col-sm-4 offset-sm-8 align-self-center">
            <div class="btn btn-default">userAuthorizedLogin: <%=request.getAttribute("userAuthorizedLogin").toString()%></div>
            <a href="/logout" class="btn btn-default">Logout</a>
        </div>
    </div>
</div>



<p>contextPath: <%=request.getContextPath()%></p>
<p>contextPath: ${pageContext.request.contextPath}</p>

</body>
</html>