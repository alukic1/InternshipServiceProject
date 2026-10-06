<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "ip.is.facultyapp.dto.Student"%>
     <%@page import = "ip.is.facultyapp.dto.Faculty"%>
    <jsp:useBean id="studentBean" class="ip.is.facultyapp.beans.StudentBean" scope="session"/>
   <jsp:useBean id="facultyBean" class="ip.is.facultyapp.beans.FacultyBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-3">
<jsp:include page="/WEB-INF/header/header.jsp" />

<%
if(session.getAttribute("faculty") == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<h2>Students</h2>


<h4>Upload CSV</h4>
<form action="Controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="uploadCSV"/>
    <input type="file" name="csvFile" class="form-control mb-2" accept=".csv" required>
    <button type="submit" class="btn btn-primary">Upload CSV file</button>
</form>
<hr>

<table class="table table-bordered">
<thead>
<tr>
    <th>Full Name</th>
    <th>Email</th>
    <th> </th>
</tr>
</thead>

<tbody>
<%
Faculty f = (Faculty)session.getAttribute("faculty");
for(Student s : studentBean.getStudents(f.getId())){
%>
<tr>
    <td><%= s.getFullname() %></td>
    <td><%= s.getEmail() %></td>

    <td>
        <form action="Controller" method="get" style="display:inline;">
            <input type="hidden" name="action" value="editStudent"/>
            <input type="hidden" name="studentId" value="<%= s.getId() %>"/>
            <button class="btn btn-primary btn-sm">Edit</button>
        </form>

        <form action="Controller" method="post" style="display:inline;">
            <input type="hidden" name="action" value="deleteStudent"/>
            <input type="hidden" name="studentId" value="<%= s.getId() %>"/>
            <button class="btn btn-dark btn-sm"
                onclick="return confirm('Are you sure?')">
                Delete
            </button>
        </form>
    </td>
</tr>
<%
}
%>
</tbody>
</table>

<hr>

<%
Student editStudent = (Student) request.getAttribute("editStudent");
boolean editMode = (editStudent != null);
%>

<h4><%= editMode ? "Edit Student" : "Add Student" %></h4>

<form action="Controller" method="post">
    <input type="hidden" name="action" value="<%= editMode ? "updateStudent" : "addStudent" %>"/>

    <% if(editMode){ %>
        <input type="hidden" name="studentId" value="<%= editStudent.getId() %>"/>
    <% } %>

    <div class="mb-2">
        <input type="text" name="fullname" class="form-control"
               placeholder="Full name"
               value="<%= editMode ? editStudent.getFullname() : "" %>" required>
    </div>

    <div class="mb-2">
        <input type="email" name="email" class="form-control"
               placeholder="Email"
               value="<%= editMode ? editStudent.getEmail() : "" %>" required>
    </div>

    <div class="mb-2">
        <input type="password" name="password" class="form-control"
               placeholder="Password"
               value="" required>
    </div>

    <button class="btn btn-primary">
        <%= editMode ? "Update" : "Add" %>
    </button>
</form>


</body>
</html>