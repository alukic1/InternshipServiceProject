<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">

<h2>Login</h2>

<form action="Controller" method="post">
    <input type="hidden" name="action" value="login"/>

    <input type="email" name="email" class="form-control mb-2" placeholder="Email" required>

    <input type="password" name="password" class="form-control mb-2" placeholder="Password" required>

    <button class="btn btn-primary">Login</button>
</form>

<%
String error = (String) request.getAttribute("error");
if(error != null){
%>
    <div class="alert alert-danger mt-3"><%= error %></div>
<%
}
%>
</body>
</html>