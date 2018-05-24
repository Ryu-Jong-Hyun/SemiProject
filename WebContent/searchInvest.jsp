<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			table, td, th{
				border : 1px solid black;
				border-collapse: collapse;
				padding : 5px 10px;
			}
			
			.test{
         		width : 230px;
         		padding : 5px 0;
        		 display: inline-block;
         		float : left;
      		}
      		
      		#ta3{
				background-color: #2BA5BA;
			}
		</style>
	</head>
	<body>
		<jsp:include page="myPageTab.jsp" />
		<form action ="" method ="post">
			<c:forEach items="${investlist}" var="project">
				<div class="test" onclick="test()">
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>사진</th>
					</tr>
					<tr>
						<td>${project.prj_no}</td>
						<td>${project.prj_title}</td>
						<td><a href="detail?prj_no=${project.prj_no}"><img width = "50" id="img" src="./upload/${project.prj_photo}"/></a></td>
					</tr>
				</table>
				</div>
				</c:forEach>
			<br/>
		</form>
	</body>
	<script>
		function test(){
			console.log("ㄴㅇㄹㅇㄴㄹ");
		}
	</script>
</html>