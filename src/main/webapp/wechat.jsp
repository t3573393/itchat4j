<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>我的计算器</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery-1.11.3/jquery.min.js" />"></script>
 <!-- 包括所有已编译的插件 -->
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript">
$(function(){
	$("#loginBtn").click(function(){
		$("#myImg").attr("src", "login/img?t="+ Math.random());
	});
	
	$("#reloginBtn").click(function(){
		$("#myImg").attr("src", "relogin/img?t="+ Math.random());
	});
});
</script>
</head>
<body>
	<button name="loginBtn" id="loginBtn"  class="btn btn-default">login</button>
	<button name="reloginBtn" id="reloginBtn" class="btn btn-default">relogin</button>
	<img id="myImg"/>
	<div>${info}</div>
	
</body>
</html>