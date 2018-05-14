<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

		</style>
	</head>
	<body>
		<table>
				<tr>
			    	<td colspan="4">
			    		<%=request.getParameter("prj_title") %>
			    	</td>
			    </tr>
				<tr>
					<th>후기 번호</th>
					<th>ID</th>
					<th>제목</th>
					<th>날짜</th>
				</tr>
			<c:forEach items="${reviewList}" var="board">
				<tr>
					<td>${board.rev_no}</td>
					<td>${board.id}</td>
					<td>${board.rev_title}</td>
					<td>${board.rev_date}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
	<script>
	</script>
</html>