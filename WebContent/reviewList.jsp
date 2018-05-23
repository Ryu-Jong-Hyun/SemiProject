<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
		Table, th, tr, td{
			width : 600;
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
	<jsp:include page="myPageTab2.jsp" />
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
					<td><a href="reviewDetail?rev_no=${board.rev_no}">${board.rev_title}</a></td>
					<td>${board.rev_date}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>