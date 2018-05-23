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
		<a href="./inquireList">문의답변</a>
		
			<table>
			<tr>
				<td>사진</td>
				<td>내용</td>
			</tr>
				<c:forEach items="${dto}" var="board">
			<tr>	
				<td>${board.prj_photo}</td> 
				<td><a href="detail?prj_no=${board.prj_no}">${board.prj_content}</a></td>
				<td><a href="projectOk?prj_no=${board.prj_no}">승인</a></td>
				<td><a href="projectNo?prj_no=${board.prj_no}">거절</a></td>
			</tr>
				</c:forEach>


	</table>
	</body>
</html>