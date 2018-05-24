<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
		padding : 5px 10px;
	}
	table{
		position:absolute;
		top:240px;
		left:500px;
	}
	#ta4{
				background-color: #2BA5BA;
			}

	</style>
</head>
	<body>
		<jsp:include page="adminMpFrame.jsp" />
			<form action="" method="post">
				<table>
					<tr>
						<th>프로젝트번호</th>
						<th>입금금액</th>
						<th>입금날짜</th>
					<tr>
					<c:forEach items="${adminApprovalList}" var="adminApprovalList">
						<tr>
							<td>${adminApprovalList.prj_no}</td>
							<td>${adminApprovalList.dpo_don}</td>
							<td>${adminApprovalList.dpo_date}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
	</body>
	<script>
	</script>
</html>






















