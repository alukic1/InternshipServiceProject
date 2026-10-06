<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="userBean" class="ip.is.companyapp.beans.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review students</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-3">
<%@include file="WEB-INF/header.jsp" %>
<h3 class="mb-3">Review students</h3>


<div class="form-floating mb-3">
  <select class="form-select" id="internshipSelect" onchange="loadStudents()">
    <option value="">Select internship</option>
  </select>
  <label for="internshipSelect">Select internship</label>
</div>

<div class="form-floating mb-3">
  <select class="form-select" id="studentSelect" onchange="loadDiaries()">
    <option value="">Select student</option>
  </select>
  <label for="studentSelect">Select student</label>
</div>

<div id="formDiv" style="display:none;">
    <h4 id="formTitle">Add review</h4>

    <input type="hidden" id="id">
	<input type="hidden" id="studentId">
	<input type="hidden" id="internshipId">
    <input class="form-control mb-2" placeholder="Comment" id="comment">
    
    <button class="btn btn-success" onclick="save()">Save</button>
    <button class="btn btn-secondary" onclick="closeForm()">Cancel</button>
</div>

<div id=diariesDiv></div>

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

function loadStudents(){
	const internshipId = document.getElementById("internshipSelect").value;
	
	fetch(API + "/students/internship/" + internshipId + "/accepted")
    .then(res => res.json())
    .then(data => {
        const select = document.getElementById("studentSelect");
        data.forEach(i => {
            const option = document.createElement("option");
            option.value = i.id;
            option.textContent = i.fullname;
            select.appendChild(option);
        });
    })
    .catch(err => console.error("Error loading students:", err));
}

function loadDiaries(){
	const internshipId = document.getElementById("internshipSelect").value;
	const studentId = document.getElementById("studentSelect").value;
	
	const div = document.getElementById("diariesDiv");
	div.innerHTML = "";
	console.log("internship" + internshipId);
	console.log("student " + studentId);
	if (!internshipId || !studentId) return;
	
	fetch(API + "/students/" + studentId + "/diary/internship/" + internshipId)
    .then(res => res.json())
    .then(data => {
        if (data.length === 0) {
            div.textContent = "No diary entries yet.";
            return;
        }

        const btnReview = document.createElement("button");
        btnReview.className = "btn btn-outline-primary";
        btnReview.textContent = "Add review";
        btnReview.onclick = () => openForm(studentId, internshipId);
        div.appendChild(btnReview);
        
        data.forEach(app => {
            const card = document.createElement("div");
            card.className = "card mb-2 p-2";

            const weekP = document.createElement("p");
            weekP.innerHTML = `<b>Week: </b>` + app.weekNumber;
            card.appendChild(weekP);
            
            const startP = document.createElement("p");
            startP.innerHTML = `<b>Start date: </b>` + app.startDate;
            card.appendChild(startP);
            
            const endP = document.createElement("p");
            endP.innerHTML = `<b>End date: </b>` + app.endDate;
            card.appendChild(endP);
            
            const descP = document.createElement("p");
            descP.innerHTML = `<b>Description: </b>` + app.description;
            card.appendChild(descP);

            div.appendChild(card);
        });
    })
    .catch(err => console.error("Error loading diaries:", err));
	
}

function openForm(studentId, internshipId){
	document.getElementById("formDiv").style.display = "block";
	document.getElementById("formTitle").innerText = "Add review";
	document.getElementById("studentId").value = studentId;
	document.getElementById("internshipId").value = internshipId;
}

function save(){
	const data = {
			student : {id : document.getElementById("studentId").value},
			internship : {id : document.getElementById("internshipId").value},
			comment : document.getElementById("comment").value,
			reviewerId : companyId,
			reviewerType : "COMPANY"		
	}
	
	fetch(API + "/students/review", { method: "POST", 
	        headers: { "Content-Type": "application/json" },
			body: JSON.stringify(data)}).then(response => {
				if(response.ok)
					alert("Review successfully added.");
				else
					alert("You have alerady added review for this student for this internship.");
    closeForm();
});
}

function closeForm() {
    document.getElementById("formDiv").style.display = "none";
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