<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userName = request.getParameter("userName");
	String userBirth = request.getParameter("userBirth");
	
	if (request.getMethod().equals("GET")) {
		out.println("Get:"+userName+", your birth is:"+userBirth);
	} else {
		out.println("Post:"+userName+", your birth is:"+userBirth);
	}
	
%>
