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

	</style>
</head>
	<body>
		<jsp:include page="adminMpFrame.jsp" />
			<form action="" method="post">
				<table>
					<tr>
						<th>아이디</th>
						<th>프로젝트명</th>
						<th>은행</th>
						<th>계좌번호</th>
						<th>금액</th>
						<th></th>
					<tr>
					<c:forEach items="${adminSuccessList}" var="adminSuccessList">
						<tr>
							<td>${adminSuccessList.pd_id}</td>
							<td>${adminSuccessList.prj_title}</td>
							<td>${adminSuccessList.prj_bank}</td>
							<td>${adminSuccessList.prj_account}</td>
							<td>${adminSuccessList.prj_curr}</td>
							<td><input type="button" id="smbtn" onclick= "sendMoney()" value="승인"/></td>
						</tr>
					</c:forEach>
				</table>
			</form>
	</body>
	<script>
		function sendMoney(){
	      	  var check = confirm("승인을 완료하시겠습니까?");
	      	  var smbtn = document.getElementById('smbtn');
	      	  
	      	  if(check){
	      		  smbtn.disabled = true;
	      		  smbtn.value = "완료";
	      	  }

		}
	</script>
</html>






















