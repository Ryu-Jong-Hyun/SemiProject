 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style></style>
	</head>
	<body>
		<form action="login" method="post">
        <table>
            <tr>
                <th>ID</th>
                <td>
                	<input type="text" name="userId" placeholder="아이디"/>
                </td>
                <td rowspan="2">
                    <input type="submit" value="로그인"/>
                </td>   
             </tr>
            <tr>
                <th>PW</th>
                <td>
                	<input type="password" name="userPw" placeholder="비밀번호"/>
                </td>
            </tr>
            </table>
            </form>         
	</body>
	<script>
	var msg = "${msg}";
	
	if(msg != ""){
		alert(msg);
	}    
	

	</script>
</html>