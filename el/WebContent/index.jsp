<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL表达式</title>
</head>
<body>
	2 + 3 = ${2 + 3}<br/>
    8 % 3 = ${8 % 3}<br/>
    <hr/>
    3 == 4 = ${3 == 4}<br/>
    3 > 4 = ${3 > 4}<br/>
    <hr/>
    3>4 || 4==4 = ${3>4 || 4==4}<br/>
    ! true = ${!true}<br/>
    <hr/>
    user = ${user}|
    <hr/>
    empty user = ${empty user}<br/>
    <hr/>
    直接获取request：${request }<br/>
    通过pageContext获取request：${pageContext.request.locale }<br/>
    通过pageContext获取servletContext：${pageContext.servletContext.contextPath }<br/>
    <hr/>
    user-agent1：<%=request.getHeader("user-agent") %><br/>
    user-agent2：${header['user-agent'] }<br/>
    <hr/>
    
</body>
</html>