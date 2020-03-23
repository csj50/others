<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>隐式对象</title>
</head>
<body>
	<%=config.getInitParameter("test") %>
	<%
		String cfg = config.getInitParameter("test");
		pageContext.setAttribute("tret", cfg);
		application.setAttribute("test", cfg);
		
	%>
</body>
</html>