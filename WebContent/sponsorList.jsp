<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
	table{position: absolute; left: 30%; top: 200px;}
	
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
		padding : 5px 20px;
		text-align: center;
		
	}
	th{background-color: black; color: white; font-size: 15px;}
	
	</style>
</head>
<body>
	<jsp:include page="loginBox.jsp" />
    <jsp:include page="mainFrame.jsp" />
	<table>
			<tr>
				<th>투자자아이디</th>
				<th>투자금액</th>
				<th>날짜</th>
			</tr>
		<c:forEach items="${list}" var="spon">
			<tr>
				<td>${spon.id}</td>
				<td>${spon.spon_don}</td>
				<td>${spon.spon_date}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>