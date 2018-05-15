<%@page import="semi.one.dao.ProjectDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			#adminMp{
				position:relative;
				width: 300px;
				height: 100%;
				background-color: yellow;
				float: left;
			}
			#loginId{
				position: absolute;
				top: 20px;
				left: 100px;
				background-color: orange;
			}
			#loginId a{
			border: 1px solid yellow;
			}
			
			#me{
				position:relative;
				top: 200px;
				background-color: aqua;
			}
			#me .cat{
				padding: 20px 0;
				text-align: center;
				font-size: large;
				border: 1px solid yellow;
			}
		</style>
	</head>
	<body>
		<jsp:include page="loginBox.jsp" />
    	<jsp:include page="mainFrame.jsp" />
		<div id="adminMp">
			<div id="loginId">
				<a href="./memberUpdateForm?id=${sessionScope.loginId}">${loginId}</a>
			</div>
			<div id="me">
				<div class="cat"><a href="myAdmin">프로젝트 승인</a></div>
				<div class="cat"><a href="#">문의 답변</a></div>
				<div class="cat"><a href="#">성공기획</a></div>
			</div>
		</div>
	</body>
	<script>
		<%
		String loginId = (String) request.getSession().getAttribute("loginId");
		ProjectDAO dao = new ProjectDAO();
		Boolean chk = dao.mypageAdmin(loginId);
		if(!chk){
			%>
			alert("관리자가 아닙니다");
			location.href="main.jsp";
			<%
			
		}
		%>
	</script>
</html>