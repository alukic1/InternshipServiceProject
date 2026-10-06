<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="userBean" class="ip.is.companyapp.beans.UserBean" scope="session"/>
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

<div class="mb-3">
    <input type="email" id="email" class="form-control" placeholder="Email">
</div>

<div class="mb-3">
    <input type="password" id="password" class="form-control" placeholder="Password">
</div>

<button class="btn btn-primary" onclick="login()">Login</button>

<div id="error" class="text-danger mt-3"></div>

<script>
const API = "http://localhost:8080/api/auth/company";

function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(API, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(res => {
        return res.json();
    })
    .then(data => {
    	document.getElementById("error").innerText = "";
        sessionStorage.setItem("companyId", data.id);
        sessionStorage.setItem("companyName", data.name);
        sessionStorage.setItem("companyEmail", data.email);

        window.location.href = "login-handler.jsp?id=" 
            + data.id + "&name=" 
            + encodeURIComponent(data.name) + "&email=" 
            + encodeURIComponent(data.email);
    })
    .catch(err => {
        document.getElementById("error").innerText = "Wrong email or password";
    });
}
</script>

</body>
</html>