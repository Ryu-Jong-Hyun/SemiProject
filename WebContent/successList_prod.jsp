<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
			width : 800px;
			margin : 5px 5px;
		}
		.in{
			width : 230px;
			padding : 5px 0;
			display: inline-block;
			float : left;
		}
		#ta2{
				background-color: #2BA5BA;
			}
		
		</style>
	</head>
	<body>
	<jsp:include page="myPageTab2.jsp" />
	<!-- successList -->
		<div class="out">
		 <c:forEach items="${successList2}" var="board">
		  <div class="in">
		  <table>
			<tr>
				<td id="prj_no">${board.prj_no}</td> 
				<td id="prj_title"><a href="reviewList?prj_no=${board.prj_no}&prj_title=${board.prj_title}">${board.prj_title}</a></td>	
			</tr>
			<tr>
				<td colspan="2"><a href="detail?prj_no=${board.prj_no}">${board.prj_photo}</a></td>
			</tr>
			</table>
		   </div>
		  </c:forEach>
		  <c:if test="${fn:length(successList2)==0}">
				<span>후기가 등록된 프로젝트가 없습니다.</span>
			</c:if>
		</div>
	</body>
</html>