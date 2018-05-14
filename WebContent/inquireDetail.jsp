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
			}
			button {
				margin-top : 10px;
	 		 	margin-left: 170px;
			}
		</style>
	</head>
	<body>
	  <form action="reply" method="post">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="hidden" name="inqTitle" value="${inqDetail.inq_title}">${inqDetail.inq_title}
				<input name="inqNo" type="hidden" value="<%=request.getParameter("inq_no")%>">
				</td>
			</tr>
			<tr>
				<th>ID</th>
				<td>${inqDetail.id}</td>
			</tr>	
			<tr>
				<th>유형</th>
				<td>${inqDetail.inq_cat}</td>
			</tr>	
			<tr>
				<th>내용</th>
				<td>${inqDetail.inq_content}</td>
			</tr>
			<tr>
				<th>답변</th>
				<td><textarea cols="50" rows ="7" name="replyContent"></textarea></td>
			</tr>
		</table>
			<button>답변 등록</button>
	  </form>
	</body>
	<script>
	</script>
</html>