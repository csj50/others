<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	var xmlHttp;
	function createXmlHttpRequest() {
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); //IE5.0, IE6.0
		} else {
			xmlHttp = new XMLHttpRequest(); //IE7.0+以及其他浏览器
		}
	}

	function createQueryString() {
		var userName = document.getElementById("userName").value;
		var userBirth = document.getElementById("userBirth").value;
		var queryString = "userName=" + userName + "&" + "userBirth="
				+ userBirth;
		return queryString;
	}

	/*
	回调函数
	当readyState属性发生改变的时候就会激活此函数来执行
	 */
	function handleStateChange() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var content = xmlHttp.responseText;
				document.getElementById("content").innerHTML = content;
				
			}
		}
	}

	function startGet() {
		createXmlHttpRequest();
		var url = "request.jsp?timestamp=" + new Date().getTime(); //发送get请求防止缓存
		xmlHttp.open("get", url + "&" + createQueryString());
		xmlHttp.send(null);
		xmlHttp.onreadystatechange = handleStateChange; //注册回调函数
	}

	function startPost() {
		createXmlHttpRequest();
		var url = "request.jsp?timestamp=" + new Date().getTime(); //发送post请求防止缓存
		xmlHttp.open("post", url);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(createQueryString());
		xmlHttp.onreadystatechange = handleStateChange;
	}

	//网页加载完毕后立刻执行的操作
	window.onload = function() {
		document.getElementById("btnGet").onclick = startGet;
		document.getElementById("btnPost").onclick = startPost;
	}
</script>

</head>
<body>
	<h2>please input your name and birth</h2>
	<input type="text" name="userName" id="userName" />
	<br />
	<input type="text" name="userBirth" id="userBirth" />
	<br />
	<input type="button" name="btnGet" id="btnGet" value="get" />
	<br />
	<input type="button" name="btnPost" id="btnPost" value="post" />
	<br />
	<!-- 把服务器数据通过dom放到div标签，局部刷新显示 -->
	<div id="content"></div>
</body>
</html>