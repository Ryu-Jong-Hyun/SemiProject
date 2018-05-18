<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>비밀번호 변경</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style></style>
	</head>
	<body>
		<h3>비밀번호 재설정</h3>
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userId"  value="<%=request.getParameter("id")%>" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>변경할 비밀번호</th>
				<td><input type="password" name="userPw"/></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="pwChk"/></td>
			</tr>
			<tr>
    			<td colspan="2">
    					<button id="pwChange">저장</button>
    			</td>
    		</tr>
		</table>
	</body>
	<script>
		var obj={};//초기화	
		obj.type="post";
		obj.dataType="json";
		obj.error=function(e){console.log(e)};
		
		$("#pwChange").click(function(){
			if($("input[name='userPw']").val()==""){//비밀번호 입력 확인
				alert("비밀 번호를 확인 해 주세요!");
				$("input[name='userPw']").focus();//포커스 이동
			}else if($("input[name='pwChk']").val()!=$("input[name='userPw']").val()){//이름 입력 확인
				alert("비밀번호 일치 확인 해주세요!");
				$("input[name='pwChk']").focus();//포커스 이동
			}else{
				obj.url="./pwChange";
				obj.data={
					id:$("input[name='userId']").val(),
					pw:$("input[name='userPw']").val()
				}
				obj.success = function(data){
					if(data.success == 1){
						alert("비밀번호가 변경되었습니다.");
						self.close();
					}else{
						alert("비밀번호 변경 실패.");
					}
				}
				console.log(obj);
				ajaxCall(obj);
			}				
		});
		
		function ajaxCall(obj){
			$.ajax(obj);
		}
	</script>
</html>