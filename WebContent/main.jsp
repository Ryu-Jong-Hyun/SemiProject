 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>sponG</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
		
		</style>
	</head>
	<body>
	<jsp:include page="mainFrame.jsp" />
	</body>
	<script>
	var msg = "${msg}";
	
	if(msg != ""){
		alert(msg);
	}    
	</script>
</html>