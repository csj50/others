<%@ page 
	language="java"
	contentType="text/html; charset=UTF-8"
	isErrorPage="true"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误页面</title>
</head>
<body>
	<h3>此功能目前不支持！详细信息请与管理员联系</h3>
	<%
		response.setStatus(200);//状态码可以选择200-203，手动设置一个状态码，表示本页面没有错误，可以正常显示
	%>
	<%=exception %>
</body>
</html>