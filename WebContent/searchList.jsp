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
	
	#select{
	position:absolute;
	top:200px;
	left:500px;
	}
	
	#category{
	position:absolute;
	top:200px;
	left:700px;
	}
	
	#search{
	position:absolute;
	top:200px;
	left:850px;
	}
	
	#btn{
	position:absolute;
	top:200px;
	left:1050px;
	}
	
	#sub{
	position:absolute;
	top:200px;
	left:1150px;
	}
	
	</style>
</head>
<body>
<jsp:include page="mainFrame.jsp" />

<form action="projectArr" method="post">
<select name="choice" id="select">
<option value="-"selected> 정렬방식을 선택해 주세요 </option>
<option value="pick"> 좋아요순 </option>
<option value="date"> 최근순 </option>
<option value="goal"> 목표금액 달성률순 </option>
<option value="due"> 마감일순 </option>
</select>
<input type="submit" name="검색" value="검색" id="sub">
</form>
<form action="searchList" method="post">
<select name="category" id="category">
<option value="NOT"> 카테고리 선택 </option>
<option value="생활"> 생활 </option>
<option value="@@"> @@ </option>
<option value="AA"> AA </option>
<option value="BB"> BB </option>
</select>
<input type="text" name="search" plascholder="검색어 입력" id="search"/>
<input type="submit" name="searchBtn" value="검색시작" id="btn"/>
</form>
	<table>
			<tr>
				<td>카테고리</td>
				<td>프로젝트명</td>
				<td>사진</td>
				<td>좋아요</td>
				<td>시작일</td>
				<td>마감일</td>
				<td>목표금액</td>
				<td>모인금액</td>
			<tr>
		<c:forEach items="${categorylist}" var="search">
			<tr>
				<td>${search.prj_cat}</td>
				<td><a href="detail?prj_no=${search.prj_no}">${search.prj_title}</a></td>
				<td>${search.prj_photo}</td>
				<td>${search.prj_picks}</td>
				<td>${search.prj_date}</td>
				<td>${search.prj_due}</td>
				<td>${search.prj_goal}</td>
				<td>${search.prj_curr}</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script>
</script>
</html>






















