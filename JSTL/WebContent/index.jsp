<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL标签库</title>
</head>
<body>
	<c:set value="${'<strong>test</strong>'}" var="test"/>
	pageScope.test：<c:out value="${pageScope.test }"/><br/>
	pageScope.test：<c:out value="${pageScope.test }" escapeXml="false"/><br/>
	requestScope.test：<c:out value="${requestScope.test }"/><br/>
</body>
</html>