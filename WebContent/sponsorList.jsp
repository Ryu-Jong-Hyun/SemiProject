<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
		padding : 5px 10px;
	}
	</style>
</head>
<body>
	<table>
			<tr>
				<td>투자자아이디</td>
				<td>투자금액</td>
				<td>날짜</td>
			<tr>
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