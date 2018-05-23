<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <style>
          table, td, th{
            	border-collapse: collapse;
            	padding: 5px 10px;
            	text-align: center;
            }
            .inputTxt{
            	width: 300px;
				height: 50px;
				font-size: 25px;
            }
            #joinbox{
			position: absolute;
			left: 38%;
			top: 200px;
			}
			#idtxt{
				width: 215px;
				height: 50px;
				font-size: 25px;
			}
			#overlay{
				height: 40px;
				font-size: 15px;
            }
            #join{
				height: 40px;
				font-size: 15px;
            }
            
        </style>
    </head>
    <body>
    <jsp:include page="mainFrame.jsp" />
    	<div id="joinbox">
    		<table>
    			<tr>
    				<td>
    					<input id="idtxt" type="text" name="userId" placeholder="아이디" onkeyup="chkword(this, 10)"/>
    					<input id="overlay" type="button" value="중복 체크"/>	
    				</td>
    			</tr>
    			<tr>
    				<td><input class="inputTxt" type="password" name="userPw" placeholder="비밀번호" onkeyup="chkword(this, 20)"/></td>
    			</tr>
    			<tr>
    				<td><input class="inputTxt" type="password" name="pwChk" placeholder="비밀번호 재확인" onkeyup="chkword(this, 20)"/></td>
    			</tr>
    			<tr>
    				<td><input class="inputTxt" type="text" name="userName" placeholder="이름" onkeyup="chkword(this, 5)"/></td>
    			</tr>
    			<tr>
    				<td><input class="inputTxt" type="text" name="email" placeholder="이메일"/></td>
    			</tr>
    			<tr>
    				<td><input class="inputTxt" type="text" name="phone" placeholder="폰 번호" onkeyPress="hypNum(this)"/></td>
    			</tr>
    			<tr>
    				<td><input class="inputTxt" type="text" name="address" placeholder="주소"/></td>
    			</tr>
    			<tr>
    				<td >
    					<button id="join">회원가입</button>
    				</td>
    			</tr>
    		</table>
    	</div>
    </body>
	<script>
		var obj={};//초기화	
		obj.type="post";
		obj.dataType="json";
		obj.error=function(e){console.log(e)};
		
		var overChk = false;//중복체크 값
		
		$("#overlay").click(function(){			
			obj.url="./overlay";
			obj.data = {id:$("input[name='userId']").val()};			
			obj.success=function(d){
				console.log(d.overlay);
				if(d.overlay){
					alert("사용중인 아이디 입니다.");
					$("input[name='userId']").val("");
					$("input[name='userId']").css("color","");	
					overChk = false;
				}else{
					alert("사용 가능한 아이디 입니다.");
					$("input[name='userId']").css("color","green");	
					overChk = true;
				}
			};			
			console.log(obj);
			ajaxCall(obj);
		});
		
		$("#join").click(function(){
			if(!overChk){
				alert("아이디 중복 체크를 실행 해 주세요!");
				$("input[name='userId']").focus();//포커스 이동
			}else{
				if($("input[name='userPw']").val()==""){//비밀번호 입력 확인
					alert("비밀 번호를 확인 해 주세요!");
					$("input[name='userPw']").focus();//포커스 이동
				}else if($("input[name='pwChk']").val()!=$("input[name='userPw']").val()){//이름 입력 확인
					alert("비밀번호 일치 확인 해주세요!");
					$("input[name='pwChk']").focus();//포커스 이동
				}else if($("input[name='userName']").val()==""){//이름 입력 확인
					alert("이름을 확인 해 주세요!");
					$("input[name='userName']").focus();//포커스 이동
				}else if($("input[name='email']").val()==""){//이메일 입력 확인
					alert("이메일을 확인 해 주세요!");
					$("input[name='email']").focus();//포커스 이동
				}else if($("input[name='phone']").val()==""){//이메일 입력 확인
					alert("폰 번호를 확인 해 주세요!");
					$("input[name='phone']").focus();//포커스 이동
				}else if($("input[name='address']").val()==""){//이메일 입력 확인
					alert("주소를 확인 해 주세요!");
					$("input[name='address']").focus();//포커스 이동
				}else{
					console.log("서버 전송");
					obj.url="./join";
					obj.data.id=$("input[name='userId']").val();
					obj.data.pw=$("input[name='userPw']").val();
					obj.data.name=$("input[name='userName']").val();
					obj.data.email=$("input[name='email']").val();
					obj.data.phone=$("input[name='phone']").val();
					obj.data.addr=$("input[name='address']").val();
					obj.success = function(data){
						if(data.success == 1){
							alert("회원 가입이 정상 처리 되었습니다.");
							location.href="main.jsp";
						}else{
							alert("회원 가입에 실패 했습니다.");
						}
					}
					console.log(obj);
					ajaxCall(obj);
				}				
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
	         
         /*숫자와 하이픈 넣기*/
         function hypNum(obj){
            if ((event.keyCode >= 48 && event.keyCode <= 57) || event.keyCode == 45) { /*숫자키와 하이픈 입력 */
               return true;
            } else {
               event.preventDefault();
            } 
         }
	         
		//전달 받은 오브젝트로 ajax 통신 실행
		function ajaxCall(obj){
			$.ajax(obj);
		}
	</script>
</html>