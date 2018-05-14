<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
	position:absolute;
	top:100px;
	left:600px;
	}

textarea { resize: none; }

</style>
</head>
<body>
	<form action="projectNo" method="post">
		<table>
		<tr>
			<td>거절할 프로젝트번호 : <input type = "text" value=${prj_no} size="1" name="prj_no" readonly></td>
		</tr>
		<tr>
			<td><h1>거절사유를 작성해주세요.</h1></td>
		</tr>
		<tr>
			<td><textarea rows="25" cols="50" width="200" height="100" id="textarea" placeholder="내용을 입력하세요" name="msg" id="noresize"></textarea></td>
		</tr>
		<tr>
			<td><button>메세지 전송</button></td>
		</tr>
		</table>
		</form>
</body>
</html>