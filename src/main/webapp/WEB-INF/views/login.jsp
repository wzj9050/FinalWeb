<%@page contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login in</title>
</head>
<body>
<form action="/doLogin" method="post">
    Username：<input type="text" name="userName"/>
    Password：<input type="password" name="password"/>
    <input type="submit" value="Login">
</form>
</body>
</html>