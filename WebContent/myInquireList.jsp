<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			table, th, tr, td{
				width : 800px;
				border: 1px solid black;
        		border-collapse: collapse;
        		padding: 5px 10px;
        		text-align: center;
        		margin-top: 50px;
			}
			button{
				margin : 5px 400px;
			}
			#ta3{
				background-color: lime;
			}
		</style>
</head>
<body>
<jsp:include page="myPageTab2.jsp" />
	<table>
			<tr>
				<th>문의 유형</th>
				<th>문의 제목</th>
				<th>ID</th>
				<th>상태</th>
				<td>문의 날짜</td>
			</tr>
		<c:forEach items="${myInquireList}" var="board">
			<tr>
				<td>${board.inq_cat}</td>
				<td>${board.inq_title}</td>
				<td>${board.id}</td>
				<td>${board.inq_state}</td>
				<td>${board.inq_date}</td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(myInquireList)==0}">
				<span>문의내용이 없습니다.</span>
		</c:if>
	</table>
	<button onclick="inquireForm()">문의하기</button>
</body>
	<script>
		/* function inquireForm(){
			window.open("inquireForm.jsp", "inquireForm", "width=450, height=300, left=500, top=100");
		} */
		
		function inquireForm(){
			location.href="inquireForm.jsp";
		}
		
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
	</script>
</html>