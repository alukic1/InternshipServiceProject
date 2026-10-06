<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "ip.is.facultyapp.dto.Student"%>
     <%@page import = "ip.is.facultyapp.dto.Faculty"%>
    <%@page import = "ip.is.facultyapp.dto.Internship"%>
    <%@page import = "ip.is.facultyapp.dto.Company"%>
    <%@page import = "ip.is.facultyapp.dto.Review"%>
    <%@page import = "ip.is.facultyapp.dto.Diary"%>
    <jsp:useBean id="studentBean" class="ip.is.facultyapp.beans.StudentBean" scope="session"/>
  <jsp:useBean id="facultyBean" class="ip.is.facultyapp.beans.FacultyBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student reviews</title>
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

<h2>Review students</h2>

<form action="Controller" method="get">
    <input type="hidden" name="action" value="tracking"/>

    <select name="studentId" class="form-select mb-2">
        <%
        Faculty f = (Faculty)session.getAttribute("faculty");
        for(Student s : studentBean.getStudents(f.getId())){
        %>
        <option value="<%= s.getId() %>">
            <%= s.getFullname() %>
        </option>
        <%
        }
        %>
    </select>

    <button class="btn btn-primary">Load</button>
</form>

<hr>
<%
Student selectedStudent = (Student) request.getAttribute("selectedStudent");
if(selectedStudent != null){
%>

<h4><%= selectedStudent.getFullname() %></h4>

<button class="btn btn-primary mb-3"
        onclick="document.getElementById('gradeForm').style.display='block'">
    Add Grade
</button>

<div id="gradeForm" style="display:none;">
    <form action="Controller" method="post">
        <input type="hidden" name="action" value="addGrade"/>
        <input type="hidden" name="studentId" value="<%= selectedStudent.getId() %>"/>

		<select name="internshipId" class="form-select mb-2" required>
<%
    for(Diary diary : studentBean.getStudentDiaries(selectedStudent.getId())){
%>
    <option value="<%= diary.getInternship().getId() %>">
        <%= diary.getInternship().getName() %>
    </option>
<%
    }
%>
</select>
        <input type="number" name="grade" min="5" max="" class="form-control mb-2" placeholder="Grade (5-10)" required>
        <textarea name="comment" class="form-control mb-2" placeholder="Comment (optional)"></textarea>

        <button class="btn btn-primary">Save</button>
    </form>
</div>

<hr>

<h4>Diary entries</h4>

<div class="row">
<%
for(Diary diary : studentBean.getStudentDiaries(selectedStudent.getId())){
%>
<div class="col-md-4">
    <div class="card mb-3">
        <div class="card-body">
            <h5><%= diary.getInternship().getName() %></h5>
            <p><b>Week:</b> <%= diary.getWeekNumber() %></p>
            <p><b>Start:</b> <%= diary.getStartDate() %></p>
            <p><b>End:</b> <%= diary.getEndDate() %></p>
            <p><%= diary.getDescription() %></p>
        </div>
    </div>
</div>
<%
}
%>
</div>

<hr>

<h4>Company Reviews</h4>

<div class="row">
<%
for(Review r : studentBean.getCompanyReviewsForStudent(selectedStudent.getId())){
%>
<div class="col-md-4">
    <div class="card mb-3">
        <div class="card-body">
            <h6><%= r.getInternship().getCompany().getName() %></h6>
            <p><%= r.getComment() %></p>
        </div>
    </div>
</div>
<%
}
%>
</div>
<%
}
%>


</body>
</html>