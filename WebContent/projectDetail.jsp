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
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
		padding : 20px 10px;
	}
input[type=text]{
  font-size: 12pt;
  font-family:"돋움";
  border:3px; 
  text-align:center;

}

textarea { resize: none; }

	table{
	position:absolute;
	top:100px;
	left:350px;
	}

	
		</style>
</head>

<body>
<jsp:include page="mainFrame.jsp" />
<input type="hidden" id="prj_no" readonly/>
		<table>
		<tr>
			<td><input type="text" id="prj_cat" readonly/></td>
			<td><input type="text" id="prj_title" readonly/></td>
			<td><input type="text" id="prj_state" readonly/></td>
			<td><input type="text" id="pd_id" readonly/></td>
			<td><a href="sponsorListCheck?prj_no=${info.prj_no}">투자자목록</a></td>
		</tr>
		
		<tr>
		<c:if test="${info.prj_photo != null}">
			<td  rowspan="7" colspan="3"><img width="500" src="./upload/${info.prj_photo}"/></td>
		</c:if>
		</tr>
		
		<tr>
			<td colspan="2">목표금액까지 : <input type="text" id="prj_gc" size="3"  readonly/>%</td>
		</tr>
		<tr>
			<td colspan="2"><input type="text" id="prj_date" size="15" readonly/>~<input type="text" id="prj_due" size="15"  readonly/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="text" id="prj_dday" size="10" readonly/>D-Day</td>
		</tr>
		<tr>
			<td colspan="2">모인금액 : <input type="text" id="prj_curr"  size="10" readonly/>원</td>
		</tr>
		<tr>
			<td colspan="2">PICK :<input type="text" id="prj_picks" size="5"  readonly/></td>
		</tr>
		<tr>
			<td colspan="2">목표금액 : <input type="text" id="prj_goal" size="10"  readonly/>원</td>
		</tr>
		<tr>
			<td rowspan="4" colspan="3"><textarea rows="20" cols="80" id="prj_content" readonly></textarea></td>
			<td><input type=button value="투자하기"/></td>
			<td><button id="pick">PICK</button></td>
		</tr>
		<tr>
			<td><input type = text placeholder="투자금액"></td>
			<td><button>확인</button></td>
		</tr>
		<tr>
			<td colspan="2"><input type="text" value="리워드 : " size="8" readonly/>${rwd.rw_item}</td>
		</tr>
		<tr>
			<td colspan="2"><input type="text" value="금액범위 : " size="10" readonly/>${rwd.rw_min}  ~ ${rwd.rw_max}<input type="text" value="원" size="2" readonly/></td>
		</tr>

	</table>
	
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
		$("#prj_goal").val(info.prj_goal);
		$("#prj_curr").val(info.prj_curr);
		$("#prj_due").val(info.prj_due);
		$("#prj_date").val(info.prj_date);
		$("#prj_picks").val(info.prj_picks);
		$("#prj_state").val(info.prj_state);
		$("#prj_no").val(info.prj_no);
		$("#prj_gc").val(info.prj_gc);
		$("#prj_dday").val(info.prj_dday);
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
	</script>
</html>






