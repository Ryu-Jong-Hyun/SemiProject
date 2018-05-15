<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			#prjDetail th, td{
				border: 1px solid black;
				border-collapse: collapse;
				padding : 5px 10px;
			}
			
			
			
			
			
			
			.qnaPlace{
				position : absolute;
				display : none;
				width : 100%;
				height: 700px;
			}
			#qnaTable{
				border-top: 1px solid black;
				border-bottom: 1px solid black;
				padding: 0 10px;
			}
			#qnaTable th{
				margin-top : 20px;
				padding: 0 40px;
			}
		</style>
</head>
<body>
	<table id="prjDetail">
		<tr>
			<th>프로젝트번호</th>
			<td><input type="text" id="prj_no" readonly/></td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td><input type="text" id="prj_cat" readonly/></td>
		</tr>
		<tr>
			<th>프로젝트제목</th>
			<td><input type="text" id="prj_title" readonly/></td>
		</tr>
		<tr>
			<th>프로젝트내용</th>
			<td><textarea rows="15" id="prj_content" readonly></textarea></td>
		</tr>
		<tr>
			<th>투자자목록</th>
			<td><a href="sponsorListCheck?prj_no=${info.prj_no}">투자자목록</a></td>
		</tr>	
		<tr>
			<th>기획자아이디</th>
			<td><input type="text" id="pd_id" readonly/></td>
		</tr>
		<tr>
			<th>기간(시작)</th>
			<td><input type="text" id="prj_date" readonly/></td>
		</tr>
		<tr>
			<th>기간(끝)</th>
			<td><input type="text" id="prj_due" readonly/></td>
		</tr>
		<tr>
			<th>목표금액</th>
			<td><input type="text" id="prj_goal" readonly/></td>
		</tr>
		<tr>
			<th>모인금액</th>
			<td><input type="text" id="prj_curr" readonly/></td>
		</tr>
		<tr>
			<th>현재상태</th>
			<td><input type="text" id="prj_state" readonly/></td>
		</tr>
		<tr>
			<th>찜갯수</th>
			<td><input type="text" id="prj_picks" readonly/></td>
		</tr>
		
		
		<c:if test="${info.prj_photo != null}">
		<tr>
			<th>이미지</th>
			<td><img width="500" src="./upload/${info.prj_photo}"/></td>
		</tr>
		</c:if>

	</table>
	
	<button id="pick">찜하기</button>
	
	
	
	<!--윤영 -QnA -->
	<button id="qnaBtn">QnA</button><p>
	<div class="qnaPlace">
		<hr>QnA<hr>
		<table id="qnaTable">
			<tr>
				<th>문의유형</th>
				<th>문의/답변</th>
				<th>상태</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${qnaList}" var="board">
			<tr>
				<th>${board.qus_cat}</th>
				<td>${board.qus_title}</td>
				<td>${board.qus_state}</td>
				<td>${board.id}</td>
				<td>${board.qus_date}</td>
			</tr>
			</c:forEach>
		</table>	
	</div>
</body>


	<script>
	var obj = {};
	var idx;
	obj.type="POST";
	obj.dataType="JSON";
	obj.error=function(e){console.log(e)};
	
	function ajaxCall(param){
		console.log(param);
		$.ajax(param);
	}
	
	//상세보기
	$(document).ready(function(){
		obj.url="./projectDetail";
		obj.success = function(data){
			console.log(data)
				printInfo(data.dto);
		};
		ajaxCall(obj);
});
	
	function printInfo(info){
		idx = info.idx;
		$("#prj_cat").val(info.prj_cat);
		$("#pd_id").val(info.pd_id);
		$("#prj_content").val(info.prj_content);
		$("#prj_title").val(info.prj_title);
		$("#prj_title").val(info.prj_title);
		$("#prj_goal").val(info.prj_goal);
		$("#prj_curr").val(info.prj_curr);
		$("#prj_due").val(info.prj_due);
		$("#prj_date").val(info.prj_date);
		$("#prj_picks").val(info.prj_picks);
		$("#prj_state").val(info.prj_state);
		$("#prj_no").val(info.prj_no);
	}
	
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}    
	
	$("#pick").click(function(){
		obj.url="./pick";
		obj.data={
				"prj_no":$("#prj_no").val()
		};
		obj.success = function(data){
			console.log(data);
if(data.chk==0){
		if(data.pick > 0){
			alert("찜");
		}else{
			alert("찜 취소");	
		}
}else{
	alert("로그인 후에 가능 합니다.");	
}
	};
	ajaxCall(obj);
});

	
	
		/*윤영 -QnA 게시판*/
		$("#qnaBtn").click(function(){
			$(".qnaPlace").css("display", "inline")
		});
	</script>
</html>






