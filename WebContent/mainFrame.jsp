<%@page import="semi.one.service.ProjectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>sponG</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
		.frame{
			width: 1550px;
			height: 75px;
			background-color: aqua;
			position: relative;
			min-width: 1600px;
			max-width: 1600px;
			right: 8px;
			bottom: 8px;
		}
		
		#list1{
			position:absolute;
			left: 10px;
		}
		
		#logo{
			position:absolute;
			top:10px;
			left: 750px;
		}
		
		#list2{
			position:absolute;
			right:10px;
		}
		
		.frame .listT{
			display: inline-block;
			*display: inline;
			zoom : 1;
		}
		
		.frame .listT li{
			float: left;
			list-style-type: none;
		}
		 .listT li a{
			display: block;
			padding: 10px 50px;
			font-size: large;
		}
		a{
			text-decoration:none;
			color: black;
		}
		</style>
	</head>
	<body>

	<div class="frame">
		<div id="list1">
			<ul class="listT">
					<li><a href="./project">프로젝트</a></li>
					<li><a href="prj_write.jsp">기획하기</a></li>
			</ul>
		</div>
		<div  id="logo">
			<a href="main.jsp">
				<img alt="로고" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMmAzvGrpAuwfEOwCrx4g-idg6yRz50lhVOUjrua3sMmya4uNy"
				width="50px">
		    </a>
	    </div>
	    <div id="list2">
		   	<ul class="listT">  
				<c:set var="loginId">${sessionScope.loginId}</c:set>
				<c:choose>
					<c:when test="${loginId ==''}">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="./memberUpdateForm">${loginId}</a></li>
						<li><a href="./logout">로그아웃</a></li>
			    		<li><a href="./mypage">마이페이지</a></li>
			    	</c:otherwise>
				</c:choose>
			</ul>
			<span>&nbsp &nbsp &nbsp &nbsp</span>
		</div>
	</div>
	</body>
	<script>
	<%
	ProjectService service = new ProjectService(request,response);
	service.updatePrjState();
	 %>
	</script>
</html>