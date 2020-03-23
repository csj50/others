<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	pageContext:<%=pageContext.getAttribute("test") %>
	<br/>
	application:<%=application.getAttribute("test") %>
	<br/>
	被include指令包含<%@ include file="include/date.jsp" %>
	<br/>
	被include动作包含<jsp:include page="include/date.jsp" />
</body>
</html>