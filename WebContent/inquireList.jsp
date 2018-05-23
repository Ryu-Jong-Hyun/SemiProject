<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			Table, th, tr, td{
				width : 200;
				border: 1px solid black;
        		border-collapse: collapse;
        		padding: 5px 10px;
        		text-align: center;
			}
			#ta2{
				background-color: lime;
			}
		</style>
</head>
<body>
	<jsp:include page="adminMpFrame.jsp" />
	<table>
			<tr>
				<th>문의 유형</th>
				<th>문의 제목</th>
				<th>ID</th>
				<th>상태</th>
				<td>문의 날짜</td>
			</tr>
		<c:forEach items="${inquireList}" var="board">
			<tr>
				<td>${board.inq_cat}</td>
				<td><a href="inquireDetail?inq_no=${board.inq_no}">${board.inq_title}</a></td>
				<td>${board.id}</td>
				<td>${board.inq_state}</td>
				<td>${board.inq_date}</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script>
	var msg = "${msg}";

	if(msg != ""){
		alert(msg);
	} 
</script>
</html>