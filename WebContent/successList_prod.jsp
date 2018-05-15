<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
		table, tr, td{
			width : 200;
			border: 1px solid black;
        	border-collapse: collapse;
        	padding: 5px 10px;
        	text-align: center;
		}
		button{
			float: right;
		}
		#prj_no{
			width : 20px;
		}
		.out{
			width : 500px;
			margin : 5px 5px;
		}
		.in{
			width : 230px;
			padding : 5px 0;
			display: inline-block;
			float : left;
		}
		</style>
	</head>
	<body>
	<jsp:include page="myPageTab.jsp" />
	<!-- successList -->
		<div class="out">
		
		 <c:forEach items="${successList2}" var="board">
		  <div class="in">
		  <table>
			<tr>
				<td id="prj_no">${board.prj_no}</td> 
				<td id="prj_title">${board.prj_title}</td>	
			</tr>
			<tr>
				<td colspan="2"><a href="reviewList?prj_no=${board.prj_no}&prj_title=${board.prj_title}">${board.prj_photo}</a></td>
			</tr>
			</table>
		   </div>
		  </c:forEach>
		</div>
	</body>
	<script>

	</script>
</html>