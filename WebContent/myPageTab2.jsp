<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			#mptab{
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
			
			.ty{
				position:relative;
				top: 100px;
				left: 80px;
				background-color: aqua;
				padding: 10px;
			}
			#ty2{
				background-color: lime;
			}
			#sp,#pd{
				position:relative;
				top: 200px;
				background-color: aqua;
			}
	
			.cat{
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
		<div id="mpTab">
			<div id="loginId">
				<a href="./memberUpdateForm">${loginId}</a>
				<a href="cashCharge.jsp">충전</a>
			</div>
			<div>
				<span class="ty" id="ty1"><a href="coinListForm?no=1">투자자</a></span> <!-- 병합 할때 이걸로 --> 
				<span class="ty" id="ty2"><a href="myProject">기획자</a></span>
			</div>
			<div id="pd">
				<div class="cat" id="ta1"><a href="myProject">내 프로젝트</a></div>
				<div class="cat" id="ta2"><a href="successList2">후기 모아 보기</a></div>
				<div class="cat" id="ta3"><a href="myInquireList">문의 게시판</a></div>
			</div>
		</div>	
	</body>
	<script>

	</script>
</html>