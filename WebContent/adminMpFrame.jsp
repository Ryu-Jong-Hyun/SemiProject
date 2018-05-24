<%@page import="semi.one.dao.MemberDAO"%>
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
			#adminMp a{
				color: white;
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
				background-color: #000042;
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
				<div class="cat" id="ta1"><a href="myAdmin">승인 대기중인 프로젝트</a></div>
				<div class="cat" id="ta3"><a href="adminSuccessList">입금 대기중인 프로젝트</a></div>
				<div class="cat" id="ta2"><a href="inquireList">문의 게시판</a></div>
				<div class="cat" id="ta4"><a href="adminApprovalList">정산 내역</a></div>
			</div>
		</div>
	</body>
	<script>
		<%
		String loginId = (String) request.getSession().getAttribute("loginId");
		MemberDAO dao = new MemberDAO();
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