<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>로그인 페이지</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			 table, td, th{
	            	border: 1px solid black;
	            	border-collapse: collapse;
	            	padding: 5px 10px;
	            	text-align: center;
	            	}   
		</style>
	</head>
	<body>
	<jsp:include page="mainFrame.jsp" />
		<table>
            <tr>
                <th>ID</th>
                <td>
                	<input type="text" id="userId" placeholder="아이디"/>
                </td>
            </tr>
            <tr>
                <th>PW</th>
                <td>
                	<input type="password" id="userPw" placeholder="비밀번호"/>
                </td>
            </tr>
            	<td colspan="2">
                    <input id="loginBtn" type="button" value="로그인"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input onclick="join()" type="button" value="회원가입"/>
                    <input onclick="search()" type="button" value="아이디 /비번 찾기"/>
                </td>
            </tr>
            <tr>
        </table>		
	</body>
	<script>
		function search(){
			location.href="search.jsp";
		}
		
		function join(){
			location.href="join.jsp";
		}
		
		$("#loginBtn").click(function(){
			//키와 값으로 복수개가 들어간다.
			//type: [post|get], url: 어디로 보낼 것인가? 
			//data: 어떤 파라메터와 값?, dataType: [json|xml|text|html|jsonp]
			//success: 성공시 할 일, error: 실패시 할 일
			$.ajax({
				type:"post",
				url:"./login",
				data:{
					id:$("#userId").val(),
					pw:$("#userPw").val()
				},
				dataType:"json",
				success:function(data){//인자 값은 서버에서 주는 메시지
					console.log(data);
					if(data.success){
						
						alert("로그인에 성공 했습니다.");	
						location.href="main.jsp";
					}else{
						alert("로그인에 실패 했습니다.");
					}
				},
				error:function(err){//인자 값은 서버에서 주는 에러 메시지
					console.log(err)
				}
			});
		});
	
	</script>
</html>