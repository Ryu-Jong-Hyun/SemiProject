<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
		table, td, th{
			border : 1px solid black;
			border-collapse: collapse;
			padding : 5px 10px;
		}
		table{
			width : 500px;
		}
		input[type='text']{
			width: 100%;
			border-width: 0;
		}
		textarea{
			width: 100%;
			resize: none;
			border-width: 0;
		}
		td{
			text-align: center;
		}
		#update, #delete, #qAnswer, #updateOk{
			display: none;
		}
		</style>
</head>
<body>
	<table>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" id="qTitle" value="${qnaDetail.qus_title}" readonly>
				<input type="hidden" id="qno" value="${qnaDetail.qus_no}">
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td id="qId">${qnaDetail.id}</td>
		</tr>
		<tr>
			<td colspan="2"><textarea id="qContent" readonly>${qnaDetail.qus_content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2"><textarea class="comment" rows ="20" id="ansContent" readonly>${answerDetail.ans_content}</textarea></td>
		</tr>
	</table>
	<button id="update">수정</button>
	<button id="updateOk">수정완료</button>
	<button id="delete">삭제</button>
	<button id="qAnswer">답변</button>
</body>
<script>
	var obj={};//초기화	
	obj.type="post";
	obj.dataType="json";
	obj.error=function(e){console.log(e)};
	
	console.log($("#qno").val());
	
	var msg = "${msg}";
	if(msg !=""){//원하는 값이 없을 경우 메인으로 다시보냄
		alert(msg);
	}
	var loginId = "${sessionScope.loginId}";

	if("${sessionScope.loginId}" == $("#qId").text()){
	$("#update").css("display","inline");
	$("#delete").css("display","inline");
	}else if("${sessionScope.loginId}" == "admin"){
		$("#qAnswer").css("display","inline");
		$("#ansContent").attr("readonly",false);
	}
	
	$("#update").click(function(){
		$("#qTitle").attr("readonly",false);
		$("#qContent").attr("readonly",false);
		$("#update").css("display","none");
		$("#updateOk").css("display","inline");
	});
	
	$("#updateOk").click(function(){
		obj.url="qnaUpdate";
		obj.data={
			qTitle : $("#qTitle").val(),
			qno : $("#qno").val(),
			qContent : $("#qContent").val()
		}
		obj.success = function(data){
			if(data.success == 1){
				alert("문의 수정 완료");
				location.href="detail?prj_no="+prj_no;
			}else{
				alert("문의 수정  실패");
			}
		}
		console.log(obj);
		ajaxCall(obj);			
	});
	
	
	$("#qAnswer").click(function(){
		obj.url="qnaAnswer";
		obj.data={
			qTitle : $("#qTitle").val(),
			qno : $("#qno").val(),
			ansContent : $("#ansContent").val()
		}
		obj.success = function(data){
			if(data.success == 1){
				alert("문의 답변 완료");
				//location.href="qnaDetail?qus_no="+$("#qno").val();
				location.href="projectDetail.jsp";
			}else{
				alert("문의 답변  실패");
			}
		}
		console.log(obj);
		ajaxCall(obj);			
	});
	
	$("#delete").click(function(){
		console.log($("#qno").val());
		location.href="qnaDelete?qus_no="+$("#qno").val();
	});
	
	function ajaxCall(param){
		console.log(param);
		$.ajax(param);
	}
</script>
</html>