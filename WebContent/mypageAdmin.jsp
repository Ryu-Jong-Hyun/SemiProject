<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
		padding : 30px 45px;
	}
	table{
	position:absolute;
	top:240px;
	left:500px;
	}
	#ta1{
				background-color: lime;
			}
	</style>
</head>
<body>
	<jsp:include page="adminMpFrame.jsp" />
	<form method="post">
	<table>
	<h1><승인 대기중인 프로젝트></h1>    

				<c:forEach items="${dto}" var="board">
			<tr>	
				<td colspan= "1"><img width="200" height="140" src="./upload/${board.prj_photo}"/></td>        
				<td><a href="detail?prj_no=${board.prj_no}">${board.prj_content}</a></td>     
				<td><a id = "btn" href="projectOk?prj_no=${board.prj_no}">승인</a></td>
				<td><a href="projectNoMsg?prj_no=${board.prj_no}">거절</a></td>
			</tr>
				</c:forEach>
	</table>
	</form>
</body>
<script>
$("#btn").click(function(){
	alert("승인완료");
	});
</script>
</html>




