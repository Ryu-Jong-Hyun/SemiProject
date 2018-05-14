
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<form action="review" method="post">
			<table>
				<tr>
					<h3>거절 사유</h3>
				</tr>
				<tr>
					<td><textarea class="edit" cols="50" rows ="10" id="Content" ></textarea></td>
				</tr>
				<tr>
					<td><button>확인</button></td>
				</tr>
			</table>
		  </form>
</body>

</html>