<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style></style>
	</head>
	<body>
	<jsp:include page="loginBox.jsp" />
	<h3>비밀번호 변경</h3>
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userId"  value="<%=request.getParameter("id")%>" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>현재 비밀번호</th>
				<td><input type="password" name="currPw" onkeyup="chkword(this, 20)"/></td>
			</tr>
			<tr>
				<th>변경할 비밀번호</th>
				<td><input type="password" name="newPw" onkeyup="chkword(this, 20)"/></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="pwChk" onkeyup="chkword(this, 20)"/></td>
			</tr>
			<tr>
    			<td colspan="2">
    					<button id="updatePw">저장</button>
    			</td>
    		</tr>
		</table>
	</body>
	<script>
		var obj={};//초기화	
		obj.type="post";
		obj.dataType="json";
		obj.error=function(e){console.log(e)};
		
		$("#updatePw").click(function(){
			if($("input[name='currPw']").val()==""){//비밀번호 입력 확인
				alert("비밀 번호를 확인 해 주세요!");
				$("input[name='currPw']").focus();//포커스 이동
			}else if($("input[name='newPw']").val()==""){//비밀번호 입력 확인
				alert("비밀 번호를 확인 해 주세요!");
				$("input[name='newPw']").focus();//포커스 이동
			}else if($("input[name='pwChk']").val()!=$("input[name='newPw']").val()){//이름 입력 확인
				alert("비밀번호 일치 확인 해주세요!");
				$("input[name='pwChk']").focus();//포커스 이동
			}else{
				obj.url="./updatePw";
				obj.data={
					id:$("input[name='userId']").val(),
					currPw:$("input[name='currPw']").val(),
					newPw:$("input[name='newPw']").val()
				}
				obj.success = function(data){
					if(data.chk==1 && data.success==1){
						alert("비밀번호 변경 성공.");
						self.close();
					}else{
						if(data.chk !=1){
							alert("현재 비밀번호 불일치.");
						}else if(data.success !=1){
							alert("비밀번호 변경 실패.");
						}else{
							alert("오류.");
						}
					}
				}
				console.log(obj);
				ajaxCall(obj);
			}				
		});
		
		 function chkword(obj, maxByte) {//글자수 조건 제한
	          var strValue = obj.value;
	          var strLen = strValue.length;
	          var totalByte = 0;
	          var len = 0;
	          var oneChar = "";
	          var str2 = "";
	          for (var i = 0; i < strLen; i++) {
	             oneChar = strValue.charAt(i);
	             if (escape(oneChar).length > 0) {
	                totalByte += 1;
	             }
	             // 입력한 문자 길이보다 넘치면 잘라내기 위해 저장
	             if (totalByte <= maxByte) {
	                len = i + 1;
	             }
	          }
	          // 넘어가는 글자는 자른다.
	          if (totalByte > maxByte) {
	             alert("허용 가능한 글자수를 초과하셨습니다.");
	             str2 = strValue.substr(0, len);
	             obj.value = str2;
	          }

	       }
		
		function ajaxCall(obj){
			$.ajax(obj);
		}
	</script>
</html>