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
			width: 220px;
			border: 1px solid black;
        	border-collapse: collapse;
        	padding: 5px 10px;
        	text-align: center;
        	
		}
		#prj_no{
			width : 20px;
		}
		button{
			float: right;
		}
		.out{
			width : 750px;
			margin : 5px 5px;
		}

		.in{
			width : 230px;
			padding : 5px 0;
			display: inline-block;
			float : left;
		}
		
		#popupOut{
			position:absolute;
			display: none;
			opacity : 0.5;
			background-color: black;
			width: 100%;
			height: 100%;
			z-index: 1;
		}

		.popup{
			display : none;
			background-color : white;
			width: 500px;
			height: 300px;														
			position: absolute;
			left: 500px;
			top : 300px;
			z-index: 2;ll
		}
		
		#ta1{
				background-color: lime;
			}
	</style>
</head>
<body>
		<jsp:include page="myPageTab2.jsp" />
		<div id="popupOut">
		</div>
	 	<div class="out">
		<c:forEach items="${dto}" var="board">
			<div class="in">
		  	 	<table>
					<td rowspan="2">진행</td>
					<td>${board.prj_no}</td> 
					<td><a href="detail?prj_no=${board.prj_no}">${board.prj_title}</a></td>
					<tr>
						<td colspan= "2">${board.prj_photo}</td>
					</tr> 
					</table>
			 </div>
		</c:forEach>
		</div>

		<div id="popupOut">
		</div>
	 	<div class="out">
		<c:forEach items="${dto2}" var="board">
			<div class="in">
		  	 	<table>
					<td rowspan="2">대기</td>
					<td>${board.prj_no}</td> 
					<td><a href="detail?prj_no=${board.prj_no}">${board.prj_title}</a></td>
					<tr>
						<td colspan= "2">${board.prj_photo}</td>
					</tr> 
					</table>
			 </div>
		</c:forEach>
		</div>	

		<div id="popupOut">
		</div>
	 	<div class="out">
		<c:forEach items="${dto3}" var="board">
			<div class="in">
		  	 	<table>
					<td rowspan="2">완료</td>
					<td>${board.prj_no}</td> 
					<td><a href="detail?prj_no=${board.prj_no}">${board.prj_title}</a></td>
					<tr>
						<td colspan= "2">${board.prj_photo}</td>
					</tr> 
					</table>
			 </div>
		</c:forEach>
		</div>

</body>
<script>

</script>
</html>




