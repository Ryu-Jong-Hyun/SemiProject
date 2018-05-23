<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>로그인 페이지</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			#loginbox{
			position: absolute;
			left: 38%;
			top: 200px;
			}
			.inputbox{
				width: 300px;
				height: 50px;
				font-size: 25px;
			}
			.js{
				width: 150px;
				height: 50px;
				font-size: 15px;
			}
			 table, td, th{
	            	border-collapse: collapse;
	            	padding: 5px 10px;
	            	text-align: center;
	      	}
	        #loginBtn{
	        	font-size: 15px;
	        }
		</style>
	</head>
	<body>
	<jsp:include page="mainFrame.jsp" />
	<div id="loginbox">
		<table>
            <tr>
                <td>
                	<input type="text" class="inputbox" id="userId" placeholder="아이디"/>
                </td>
            </tr>
            <tr>
                <td>
                	<input type="password" class="inputbox" id="userPw" placeholder="비밀번호"/>
                </td>
            </tr>
            	<td>
                    <input id="loginBtn" class="inputbox" type="button" value="로그인"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input onclick="join()" class="js"  type="button" value="회원가입"/>
                    <input onclick="search()" class="js" type="button" value="아이디 /비번 찾기"/>
                </td>
            </tr>
            <tr>
        </table>
     </div>		
	</body>
	<script>
		function search(){
			location.href="search.jsp";
		}
		
		function join(){
			location.href="join.jsp";
		}
		
		$("#loginBtn").click(function(){
			$.ajax({
				type:"post",
				url:"./login",
				data:{
					id:$("#userId").val(),
					pw:$("#userPw").val()
				},
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.success){	
						location.href="main";
					}else{
						alert("로그인에 실패 했습니다.");
					}
				},
				error:function(err){
					console.log(err)
				}
			});
		});
	
	</script>
</html>