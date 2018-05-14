<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 정보 수정</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
          	table, td, th{
            	border: 1px solid black;
            	border-collapse: collapse;
            	padding: 5px 10px;
            	text-align: center;
            }
            input.inputTxt{
            	width: 100%;
            }
        </style>
    	</head>
    	<body>
    	<jsp:include page="loginBox.jsp" />
    	<jsp:include page="mainFrame.jsp" />
    		<table>
    			<tr>
    				<td>아이디</td>
    				<td>
    					<input type="text" name="userId" value="${info.id}"  readonly="readonly"/>
    				</td>
    			</tr>
    			<tr>
    				<td>비밀번호</td>
    				<td><button id="updatePw">비밀번호 변경</button></td>
    			</tr>
    			<tr>
    				<td>이름</td>
    				<td><input class="inputTxt" type="text" name="userName" value="${info.name}"/></td>
    			</tr>
    			<tr>
    				<td>이메일</td>
    				<td><input class="inputTxt" type="text" name="email" value="${info.email}"/></td>
    			</tr>
    			<tr>
    				<td>폰 번호</td>
    				<td><input class="inputTxt" type="text" name="phone" value="${info.phone}"/></td>
    			</tr>
    			<tr>
    				<td>주소</td>
    				<td><input class="inputTxt" type="text" name="address" value="${info.addr}"/></td>
    			</tr>
    			<tr>
    				<td colspan="2">
    					<button id="memberUpdate">수정</button>
    				</td>
    			</tr>
    		</table>
	</body>
	<script>
		var obj={};//초기화	
		obj.type="post";
		obj.dataType="json";
		obj.error=function(e){console.log(e)};
	
		var msg = "${msg}";
		if(msg !=""){//원하는 값이 없을 경우 메인으로 다시보냄
			alert(msg);
			location.href="main";
		}
		
		$("#memberUpdate").click(function(){
			 
				if($("input[name='userName']").val()==""){//이름 입력 확인
					alert("이름을 확인 해 주세요!");
					$("input[name='userName']").focus();//포커스 이동
				}else if($("input[name='email']").val()==""){//이메일 입력 확인
					alert("이메일을 확인 해 주세요!");
					$("input[name='email']").focus();//포커스 이동
				}else if($("input[name='phone']").val()==""){//이메일 입력 확인
					alert("폰 번호를 확인 해 주세요!");
					$("input[name='email']").focus();//포커스 이동
				}else if($("input[name='address']").val()==""){//이메일 입력 확인
					alert("주소를 확인 해 주세요!");
					$("input[name='address']").focus();//포커스 이동
				}else{
					console.log("서버 전송");
					obj.url="memberUpdate";
					obj.data={
						id:$("input[name='userId']").val(),
						name:$("input[name='userName']").val(),
						email:$("input[name='email']").val(),
						phone:$("input[name='phone']").val(),
						addr:$("input[name='address']").val()
					}
					obj.success = function(data){
						if(data.success == 1){
							alert("회원 정보 수정이 정상 처리 되었습니다.");
							location.href="main.jsp";
						}else{
							alert("회원 정보 수정에 실패 했습니다.");
						}
					}
					console.log(obj);
					ajaxCall(obj);
				}				
		});
		
		$("#updatePw").click(function(){
			var w = window.open("about:blank","updatePwPop","width=500, height=500, left=300, top=100");
			w.location.href = "updatePwPop.jsp?id="+$("input[name='userId']").val();
		});
		
		function ajaxCall(obj){
			$.ajax(obj);
		}
	</script>
</html>