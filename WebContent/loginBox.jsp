<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var loginId = "${sessionScope.loginId}";
	
	if(loginId==""){
		alert("로그인이 필요한 서비스 입니다.");
		location.href="main.jsp";
	}
</script>