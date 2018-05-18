<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<jsp:include page="adminMpFrame.jsp" />
	<form action="projectNo" method="post">
		<table>
		<tr>
			<td>거절할 프로젝트번호 : <input type = "text" value=${prj_no} size="1" name="prj_no" readonly></td>
		</tr>
		<tr>
			<td><h1>거절사유를 작성해주세요.</h1></td>
		</tr>
		<tr>
			<td><textarea rows="15" cols="50"  id="textarea" placeholder="내용을 입력하세요" name="msg" id="noresize" onkeyup="chkword(this, 500)"></textarea></td>
		</tr>
		<tr>
			<td><button id = "btn">메세지 전송</button></td>
		</tr>
		</table>
		
		</form>
</body>
</html>
<script>
$("#btn").click(function(){
alert("거절메세지 전송");
});






function chkword(obj, maxByte) {//byte 조건 제한
	 
    var strValue = obj.value;
    var strLen = strValue.length;
    var totalByte = 0;
    var len = 0;
    var oneChar = "";
    var str2 = "";

    for (var i = 0; i < strLen; i++) {
        oneChar = strValue.charAt(i);
        if (escape(oneChar).length > 4) {
            totalByte += 3;
        } else {
            totalByte++;
        }

        // 입력한 문자 길이보다 넘치면 잘라내기 위해 저장
        if (totalByte <= maxByte) {
            len = i + 1;
        }
    }

    // 넘어가는 글자는 자른다.
    if (totalByte > maxByte) {
        alert("허용 가능한 글자수를 초과하셨습니다.");
        str2 = strValue.substr(0, len);
        obj.value = str2;
        chkword(obj, 4000);
    }
}






</script>