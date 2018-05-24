<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	Table, th, tr, td{
		width : 200;
		border: 1px solid black;
     	border-collapse: collapse;
     	padding: 5px 10px;
	}
	#ta2{
		background-color: #2BA5BA;;
	}
</style>
<body>
<jsp:include page="myPageTab2.jsp" />
 	<table>
			<tr>
				<th>제목</th>
				<td>${review.rev_title}</td>
			</tr>
			<tr>
				<th>ID</th>
				<td>${review.id}</td>
			</tr>	
			<tr>
				<th>내용</th>
				<td>${review.rev_content}</td>
			</tr>
		</table>
</body>
</html>