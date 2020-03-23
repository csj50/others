<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%! 
	int count = 5;
	int add(int a, int b) {
		return a + b;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP脚本</title>
</head>
<body>
	<%
		int result = add(5, 12);
	%>
	count:<%= count %>
	<br/>
	result:<%= result %>
	<br/>
	循环1:
	<%
		for (int i=0; i<count; i++) {
			out.print(i + "<br/>");
		}
	%>
	循环2：
	<% for (int i=0; i<count; i++) {%>
		<br/>i：<%= i %>
	<% } %>
</body>
</html>