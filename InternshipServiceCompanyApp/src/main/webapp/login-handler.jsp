<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="ip.is.companyapp.beans.UserBean" scope="session"/>

<%
String id = request.getParameter("id");
String name = request.getParameter("name");
String email = request.getParameter("email");

if(id != null){
    userBean.setCompanyId(Long.parseLong(id));
    userBean.setCompanyName(name);
    userBean.setCompanyEmail(email);
    userBean.setLoggedIn(true);
}

response.sendRedirect("internships.jsp");
%>
