<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "ip.is.facultyapp.dto.Company"%>
    <jsp:useBean id="companyBean" class="ip.is.facultyapp.beans.CompanyBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companies</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-3">
<jsp:include page="/WEB-INF/header/header.jsp" />
<h2>Companies</h2>


<h4>Add Company</h4>

<form action="Controller" method="post">
    <input type="hidden" name="action" value="addCompany"/>

    <div class="mb-2">
        <input type="text" name="name" class="form-control"
               placeholder="Company name" required>
    </div>

    <div class="mb-2">
        <input type="email" name="email" class="form-control"
               placeholder="Email" required>
    </div>

    <div class="mb-2">
        <input type="password" name="password" class="form-control"
               placeholder="Password" required>
    </div>

    <button type="submit" class="btn btn-primary">Add</button>
</form>



<div id="tableDiv">
<h4>All Companies</h4>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Status</th>
            <th> </th>
        </tr>
    </thead>
    <tbody>
<%
for (Company c : companyBean.getCompanies()) {
%>
<tr>
    <td><%= c.getName() %></td>
    <td><%= c.getEmail() %></td>
    <td>
        <%= c.getActive() ? "Active" : "Inactive" %>
    </td>
    <td>
        <form action="Controller" method="post" style="display:inline;">
            <input type="hidden" name="action" value="toggleCompany"/>
            <input type="hidden" name="companyId" value="<%= c.getId() %>"/>
            <button type="submit" class="btn btn-sm btn-primary">
                <%= c.getActive() ? "Deactivate" : "Activate" %>
            </button>
        </form>
        
         <form action="Controller" method="post" style="display:inline;">
            <input type="hidden" name="action" value="deleteCompany"/>
            <input type="hidden" name="companyId" value="<%= c.getId() %>"/>
            <button type="submit" class="btn btn-dark btn-sm"
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
</div>


</body>
</html>