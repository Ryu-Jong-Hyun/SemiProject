<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
		table{
			width : 400px;
			height : 200px;
		}
		table, tr, td{
			border: 1px solid black;
        	border-collapse: collapse;
        	padding: 5px 10px;
		}
	</style>
</head>
<body>
<jsp:include page="myPageTab2.jsp" />
	<form action="inquire" method="post">
			<table>
				<tr>
					<td> 
						<select name="choice" id="select">
						<option value=""selected>문의유형</option>
						<option value="프로젝트"> 프로젝트 </option>
						<option value="입금"> 입금 </option>
						<option value="기타"> 기타 </option>
						</select> 
					</td>
				</tr>
				<tr>
					<td>제목 : <input type="text" name="inqTitle"/></td>
				</tr>
				<tr>
					<td><textarea class="edit" cols="50" rows ="10" name="inqContent" ></textarea></td>
				</tr>
				<tr>
					<td><button>확인</button></td>
				</tr>
			</table>
	</form>
</body>
<script>
</script>
</html>