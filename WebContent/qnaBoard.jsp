<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		table{
			width : 800px;
			padding: 0 15px;
			
		}
		th{
			border-top: 1px solid black;
			border-bottom: 1px solid black;
		}
		.qusArea{
			position: absolute;
		}
		.registArea{
			position: absolute;
			margin-top: 500px;
			margin-left: 430px;
		}
		#aa{
			border : none;
		}

	</style>
</head>
<body>
	<div class="qusArea">
		<table id="qnaTable">
			<tr>
				<th>유형</th>
				<th>제목</th>
				<th>상태</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</table>
	</div>
	
	<!------------------------ 문의하기  ------------------------>
	  <div class="registArea">
		<form action="question" method="post">
		<select name="choice" id="select">
			<option value=" "selected>문의 유형</option>
			<option value="배송"> 배송 </option>
			<option value="후원"> 후원 </option>
			<option value="기타"> 기타 </option>
		</select>
		<input type="text" name="qusTitle" placeholder="문의 제목" width="300px"/><p>
		<textarea rows="5" cols="100" name="qusContent" placeholder="문의 내용"></textarea>
		<button id="qBtn">문의하기</button>
		</form>
	  </div>
</body>
<script>
	var obj={};
	var qus_no;
	obj.type="POST";
	obj.dataType="JSON";
	obj.error=function(e){console.log(e)};
	
	var loginId = "${sessionScope.loginId}";
	
	function ajaxCall(param){
		console.log(param);
		$.ajax(param);
	}
	
	//Question 리스트
	$(document).ready(function(){
		obj.success = function(data){
			console.log(data.qdto);
				qnaList(data.qdto);
				
		};
		ajaxCall(obj);
	});
	
	function qnaList(qdto){
		console.log(qdto);
		var content="";
		qdto.forEach(function(item, prj_no){
			console.log(item.qus_no);
			content += "<tr><input type='hidden' name='qus_no' value="+item.qus_no+">";
			content += "<td>"+item.qus_cat+"</td>";
			content += "<td><a href='qnaDetail?qus_no="+item.qus_no+"'>"+item.qus_title+"</a></td>";
			content += "<td>"+item.qus_state+"</td>";
			content += "<td id='qId'>"+item.id+"</td>";
			content += "<td>"+item.qus_date+"</td>";
			content += "</tr>";
		});
		$("#qnaTable").append(content);
	}
	
	function ajaxCall(param){
		$.ajax(param);
	}
</script>
</html>