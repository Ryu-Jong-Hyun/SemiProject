<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<th>프로젝트번호</th>
			</tr>
			<c:forEach items="${list}" var="project">
				<tr>
					<td><a href="detail?prj_no=${project.prj_no}">${project.prj_title}</a></td>
				</tr>
			</c:forEach>
		<tr>
			<td><a href = "mypageChk">마이페이지</a></td>
		</tr>
		<tr>
			<td><a href = "project">프로젝트</a>
		</tr>
		</table>
		
	</body>
	<script>
	</script>
</html>