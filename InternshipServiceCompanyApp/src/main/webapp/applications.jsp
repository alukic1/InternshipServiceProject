<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="ip.is.companyapp.beans.UserBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internship Applications</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-3">
<%@include file="WEB-INF/header.jsp" %>
<h3 class="mb-3">Applications</h3>

<div class="form-floating mb-3">
  <select class="form-select" id="internshipSelect" onchange="loadApplications()">
    <option value="">Select internship</option>
  </select>
  <label for="internshipSelect">Select internship</label>
</div>

<div id="applicationsDiv"></div>

<script>
const API = "http://localhost:8080/api";
const companyId = <%= userBean.getCompanyId() %>;

function loadInternships() {
    fetch(API + "/internships/company/" + companyId)
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("internshipSelect");
            data.forEach(i => {
                const option = document.createElement("option");
                option.value = i.id;
                option.textContent = i.name;
                select.appendChild(option);
            });
        })
        .catch(err => console.error("Error loading internships:", err));
}

function loadApplications() {
    const internshipId = document.getElementById("internshipSelect").value;
    const div = document.getElementById("applicationsDiv");
    div.innerHTML = "";

    if (!internshipId) return;

    fetch(API + "/internships/" +internshipId + "/applications")
        .then(res => res.json())
        .then(data => {
            if (data.length === 0) {
                div.textContent = "No applications yet.";
                return;
            }

            data.forEach(app => {
                const card = document.createElement("div");
                card.className = "card mb-2 p-2";

                const nameP = document.createElement("p");
                nameP.innerHTML = `<b>Student: </b>`+ app.student.fullname;
                card.appendChild(nameP);

                const emailP = document.createElement("p");
                emailP.innerHTML = `<b>Email: </b>` + app.student.email;
                card.appendChild(emailP);

                const statusP = document.createElement("p");
                statusP.innerHTML = `<b>Status: </b>` + app.status;
                card.appendChild(statusP);

                const cvP = document.createElement("p");
                const cvLink = document.createElement("a");
                cvLink.href = API + "/students/" + app.student.id + "/cv/pdf";
                cvLink.target = "_blank";
                cvLink.textContent = "Download PDF";
                cvP.innerHTML = "<b>CV:</b> ";
                cvP.appendChild(cvLink);
                card.appendChild(cvP);

                if(app.status === "PENDING") {
                    const btnAccept = document.createElement("button");
                    btnAccept.className = "btn btn-outline-primary";
                    btnAccept.textContent = "Accept";
                    btnAccept.onclick = () => accept(app.id);

                    const btnReject = document.createElement("button");
                    btnReject.className = "btn btn-outline-danger";
                    btnReject.textContent = "Reject";
                    btnReject.onclick = () => reject(app.id);

                    card.appendChild(btnAccept);
                    card.appendChild(btnReject);
                }

                div.appendChild(card);
            });
        })
        .catch(err => console.error("Error loading applications:", err));
}

function accept(applicationId) {
    fetch(API + "/internships/application/" + applicationId + "/accept", { method: "PUT" })
        .then(() => loadApplications())
        .catch(err => console.error(err));
}

function reject(applicationId) {
    fetch(API + "/internships/application/" + applicationId + "/reject", { method: "PUT" })
        .then(() => loadApplications())
        .catch(err => console.error(err));
}

function init(){
	if(sessionStorage.getItem("companyId")){
		loadInternships();
		}
	else
		window.location.href = "login.jsp";
}

init();
</script>
</body>
</html>