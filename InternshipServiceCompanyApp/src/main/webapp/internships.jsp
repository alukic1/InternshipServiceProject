<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
    <jsp:useBean id="userBean" class="ip.is.companyapp.beans.UserBean" scope="session"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internships</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container py-3">
<%@include file="WEB-INF/header.jsp" %>
<h3 class="mb-3">Internships</h3>

<button class="btn btn-primary w-100 mb-3" onclick="openAddForm()">
    Add Internship
</button>

<div id="tableDiv">
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Technologies</th>
            <th>Requirements</th>
            <th>Start date</th>
            <th>End date</th>
            
        </tr>
    </thead>
    <tbody id="internshipsTable"></tbody>
</table>
</div>

<div id="formDiv" style="display:none;">
    <h4 id="formTitle">Add Internship</h4>

    <input type="hidden" id="id">

    <input class="form-control mb-2" placeholder="Name" id="name">
    <input class="form-control mb-2" placeholder="Description" id="description">
    <input class="form-control mb-2" placeholder="Technologies" id="technologies">
    <input class="form-control mb-2" placeholder="Requirements" id="requirements">
    <input class="form-control mb-2" type="date" id="startDate">
    <input class="form-control mb-2" type="date" id="endDate">
    
    <button class="btn btn-primary" onclick="save()">Save</button>
    <button class="btn btn-secondary" onclick="closeForm()">Cancel</button>
</div>

<script>

const API = "http://localhost:8080/api/internships";
const companyId = <%= userBean.getCompanyId() %>;

function loadInternships() {
    fetch(API + "/company/" + companyId)
        .then(res => res.json())
        .then(data => {
            let table = document.getElementById("internshipsTable");
            table.innerHTML = "";

            data.forEach(i => {
                const tr = document.createElement("tr");

                [i.name, i.description, i.technologies, i.requirements, i.startDate, i.endDate].forEach(text => {
                    const td = document.createElement("td");
                    td.textContent = text;
                    tr.appendChild(td);
                });

                const tdActions = document.createElement("td");
                const editBtn = document.createElement("button");
                editBtn.textContent = "Edit";
                editBtn.className = "btn btn-primary btn-sm";
                editBtn.onclick = () => edit(i.id);

                const deleteBtn = document.createElement("button");
                deleteBtn.textContent = "Delete";
                deleteBtn.className = "btn btn-danger btn-sm";
                deleteBtn.onclick = () => deleteInternship(i.id);

                tdActions.appendChild(editBtn);
                tdActions.appendChild(deleteBtn);

                tr.appendChild(tdActions);
                table.appendChild(tr);
            });
        });
}

function openAddForm() {
	document.getElementById("tableDiv").style.display = "none";
    document.getElementById("formDiv").style.display = "block";
    document.getElementById("formTitle").innerText = "Add Internship";
    document.getElementById("id").value ='';
    document.getElementById("name").value = '';
    document.getElementById("description").value = '' ;
    document.getElementById("technologies").value = '';
    document.getElementById("startDate").value = '';
    document.getElementById("endDate").value = '';
    document.getElementById("requirements").value = '';
}

function closeForm() {
    document.getElementById("formDiv").style.display = "none";
    document.getElementById("tableDiv").style.display = "block";
}

function save() {
    const id = document.getElementById("id").value;
	const companyId = {id: <%= userBean.getCompanyId() %>};
    const data = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        technologies: document.getElementById("technologies").value,
        startDate: document.getElementById("startDate").value,
        endDate: document.getElementById("endDate").value,
        requirements: document.getElementById("requirements").value,
        company: companyId
    };

    let method = id ? "PUT" : "POST";
    console.log(method);
    let url = id ? API + "/" + id : API;

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    }).then(() => {
        loadInternships();
        closeForm();
    });
}

function edit(id) {
    fetch(API + "/" + id)
        .then(res => res.json())
        .then(i => {
            openAddForm();
            document.getElementById("id").value = i.id;
            document.getElementById("name").value = i.name;
            document.getElementById("description").value = i.description;
            document.getElementById("technologies").value = i.technologies;
            document.getElementById("startDate").value = i.startDate;
            document.getElementById("endDate").value = i.endDate;
            document.getElementById("requirements").value = i.requirements;
        });
}

function deleteInternship(id) {
    fetch(API + "/" + id, { method: "DELETE" })
        .then(() => loadInternships());
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