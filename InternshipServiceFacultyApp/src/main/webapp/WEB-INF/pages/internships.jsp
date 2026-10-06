<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "ip.is.facultyapp.dto.Internship"%>
    <%@page import = "ip.is.facultyapp.dto.Company"%>
     <jsp:useBean id="internshipBean" class="ip.is.facultyapp.beans.InternshipBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internships</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-3">
<jsp:include page="/WEB-INF/header/header.jsp" />
<div id="tableDiv">
<h4>All Internships</h4>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Name</th>
            <th>Company</th>
            <th>Description</th>
            <th>Technologies</th>
            <th>Requirements</th>
            <th>Start date</th>
            <th>End date</th>
        </tr>
    </thead>
    <tbody>
<%
if(internshipBean.getInternships() != null)
for (Internship i : internshipBean.getInternships()) {
%>
<tr>
    <td><%= i.getName() %></td>
    <td><%= i.getCompany().getName() %></td>
    <td><%= i.getDescription() %></td>
    <td><%= i.getTechnologies() %></td>
    <td><%= i.getRequirements() %></td>
    <td><%= i.getStartDate() %></td>
    <td><%= i.getEndDate() %></td>
</tr>
<%
}
%>
</tbody>
</table>
</div>

</body>
</html>