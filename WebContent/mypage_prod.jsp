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
		<a href="./successList2">후기 모아보기</a>
		<a href="./myInquireList">문의 게시판</a>
		
		<table>
			<tr>
				<td>진행</td>
				<c:forEach items="${dto}" var="board">
				<td><a href="detail?prj_no=${board.prj_no}">${board.prj_title}</a></td> 
				</c:forEach>
			<tr>
			<tr>
				<td>대기</td>
				<c:forEach items="${dto2}" var="board">
				<td><a href="detail?prj_no=${board.prj_no}">${board.prj_title}</a></td> 
				</c:forEach>
			<tr>
			<tr>
				<td>완료</td>
				<c:forEach items="${dto3}" var="board">
				<td><a href="detail?prj_no=${board.prj_no}">${board.prj_title}</a></td> 
				</c:forEach>
			<tr>
	</table>
	</body>
</html>