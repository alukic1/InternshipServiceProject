<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg  bg-body-tertiary">
    <div class="container-fluid">
    
        <div class="collapse navbar-collapse">

            <ul class="navbar-nav me-auto">

                <li class="nav-item">
                    <a class="nav-link" href="internships.jsp">Internships</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="applications.jsp">Applications</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="reviews.jsp">Reviews</a>
                </li>

            </ul>

				<button class="btn btn-primary" onClick="changePass()">Change password</button>
                <button class="btn btn-danger" onClick="logout()">Logout</button>   
                
    <div id="formPassDiv" style="display:none;">
    <h4 id="formPassTitle">Change password</h4>

    <input type="hidden" id="idPass">

    <input type="password" class="form-control mb-2" placeholder="New password" id="newPassword" >

    <button class="btn btn-primary" onclick="savePass()">Save</button>
    <button class="btn btn-secondary" onclick="closePassForm()">Cancel</button>
</div>
<script>

const API_URL = "http://localhost:8080/api/auth/company/change_pass";

function logout(){
	sessionStorage.setItem("companyId", null);
    sessionStorage.setItem("companyName", null);
    sessionStorage.setItem("companyEmail", null);

    window.location.href = "login.jsp";
}

function changePass(){
	document.getElementById("formPassDiv").style.display="block";
}

function closePassForm(){
	document.getElementById("formPassDiv").style.display="none";
}

function savePass(){
    const pass = document.getElementById("newPassword").value;
    
    const mail = sessionStorage.getItem("companyEmail");
    const data = {
        email: mail,
        password: pass
    };
    
    fetch(API_URL, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    }).then(response => {
        if(response.ok){
            alert("Password successfully changed.");
            closePassForm();
            }
        	
        else
            alert("There has been an issue with changing your password. Try again.");
    });
}

</script>
        </div>
    </div>
</nav>
</body>