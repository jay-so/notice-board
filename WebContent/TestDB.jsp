<%@page import="sku.mvc.dao.ElectronicsDAOImpl"%>
<%@page import="sku.mvc.dao.ElectronicsDAO"%>
<%@page import="sku.mvc.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	ElectronicsDAO dao = new ElectronicsDAOImpl();
	out.println(dao.selectAll());
%>
</body>
</html>