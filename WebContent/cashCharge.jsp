<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>충전하기</title>
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
		<jsp:include page="myPageTab.jsp" />
		<table>
			<tr>
				<th>충전할 금액</th>
				<td><input type="text" name="money"/>원</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2"><button id="charge">충전</button></td>
			</tr>
		</table>
	</body>
	<script>
		var obj={};//초기화	
		obj.type="post";
		obj.dataType="json";
		obj.error=function(e){console.log(e)};
		
		$("#charge").click(function(){
				if($("input[name='money']").val()<1000){
					alert("최소금액:1000원");
				}else{
					console.log("서버 전송");
					obj.url="./charge";
					obj.data={
							id:loginId,
							money:$("input[name='money']").val()
					};
					obj.success = function(data){
						if(data.success == 1){
							alert("충전되었습니다.");
							location.href="coinListForm?no=1";
						}else{
							alert("충전에 실패했습니다");
						}
					}
					console.log(obj);
					ajaxCall(obj);
				}
		});
		
		//전달 받은 오브젝트로 ajax 통신 실행
		function ajaxCall(obj){
			$.ajax(obj);
		}
	</script>
</html>