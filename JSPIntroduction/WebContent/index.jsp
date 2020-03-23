<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP简介（包含指令、注释、小脚本、EL表达式、动作、标签）</title>
</head>
<body>
  <!-- HTML 注释 -->
  <%-- JSP  注释 --%>
  <%
    int age = 28;
  %>
    年龄：<%=age %><br/>
    ${'hello EL'}<br/>
    <jsp:include page="include.jsp"></jsp:include>
    <f:formatDate value="<%=new java.util.Date() %>" pattern="yyyy-MM-dd HH:mm:ss"/>
</body>
</html>