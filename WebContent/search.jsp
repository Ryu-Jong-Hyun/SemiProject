<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>아이디 / 비밀번호 찾기</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			div{
				display: none;
			}
		</style>
	</head>
	<body>
	<jsp:include page="mainFrame.jsp" />
		<button onclick="showId()">아이디 찾기</button>
		<button onclick="showPw()">비밀번호 찾기</button>
		<div id="sId">
			<h3>아이디 찾기</h3>
			<table>
            	<tr>
                	<th>이름</th>
                	<td>
                		<input type="text" id="userName" placeholder="name"/>
                	</td>
           		</tr>
            	<tr>
                	<th>폰 번호</th>
                	<td>
                		<input type="text" id="phone1" placeholder="phone number"/>
               		</td>
            	<tr>
                	<td colspan="2">
                  		<input id="searchId" type="button" value="아이디 찾기"/>
               		</td>
           		</tr>
            	<tr>
                	<td colspan="2">
                	<input id="id" type="text" value="" />
           		</td>
            </tr>
            <tr>
            	<td colspan="2">
                    <input onclick="login()" type="button" value="로그인하러 가기"/>
                </td>
			</tr>
		</table>		
		</div>
		
		<div id="sPw">
		<h3>비밀번호 찾기</h3>
		<table>
            <tr>
                <th>아이디</th>
                <td>
                	<input type="text" id="userId" placeholder="id"/>
                </td>
           	</tr>
            <tr>
                <th>폰 번호</th>
                <td>
                	<input type="text" id="phone2" placeholder="phone number"/>
               	</td>
            <tr>
            <tr>
                <th>이메일</th>
                <td>
                	<input type="text" id="email" placeholder="email@"/>
               	</td>
            <tr>
                <td colspan="2">
                  	<input id="searchPw" type="button" value="비밀번호 찾기"/>
               	</td>
			</tr>
			<tr>
            	<td colspan="2">
                    <input onclick="login()" type="button" value="로그인하러 가기"/>
                </td>
			</tr>
        </table>
		</div>
	</body>
	<script>
		function showId(){
			sPw.style.display = "none";
			sId.style.display = "block";
		}
		function showPw(){
			sId.style.display = "none";
			sPw.style.display = "block";
		}
	
		function login(){
			location.href="login.jsp";
		}
		
		$("#searchId").click(function(){
			//키와 값으로 복수개가 들어간다.
			//type: [post|get], url: 어디로 보낼 것인가? 
			//data: 어떤 파라메터와 값?, dataType: [json|xml|text|html|jsonp]
			//success: 성공시 할 일, error: 실패시 할 일
			$.ajax({
				type:"post",
				url:"./searchId",
				data:{
					name:$("#userName").val(),
					phone:$("#phone1").val()
				},
				dataType:"json",
				success:function(data){//인자 값은 서버에서 주는 메시지
					console.log(data);
					if(data.searchId){
						$("#id").val(data.searchId);
					}else{
						$("#id").val("아이디 찾기 실패 했습니다.");
					}
				},
				error:function(err){//인자 값은 서버에서 주는 에러 메시지
					console.log(err)
				}
			});
		});
		
		$("#searchPw").click(function(){
			var w = window.open("about:blank","pwPopUp","width=500, height=500, left=300, top=100");
			//키와 값으로 복수개가 들어간다.
			//type: [post|get], url: 어디로 보낼 것인가? 
			//data: 어떤 파라메터와 값?, dataType: [json|xml|text|html|jsonp]
			//success: 성공시 할 일, error: 실패시 할 일
			$.ajax({
				type:"post",
				url:"./searchPw",
				data:{
					id:$("#userId").val(),
					phone:$("#phone2").val(),
					email:$("#email").val()
				},
				dataType:"json",
				success:function(data){//인자 값은 서버에서 주는 메시지
					console.log(data);
					if(data.id){
						w.location.href = "pwPopUp.jsp?id="+data.id;
					}else{
						w.location.href = "pwPopUp.jsp";
						w.close();
						alert("비밀번호 찾기 실패");
					}
				},
				error:function(err){//인자 값은 서버에서 주는 에러 메시지
					console.log(err)
				}
			});
		});
	</script>
</html>