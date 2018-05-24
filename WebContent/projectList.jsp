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
	
	#Paging{
	text-align:center;
	}
	
	</style>
</head>
<body>
<jsp:include page="mainFrame.jsp" />
    
<form action="projectArr?no=1" method="post">
<select name="choice" id="select">
	<option value="-"selected> ${select} </option>        
	<option value="pick"> 좋아요순 </option>
	<option value="date"> 최근순 </option>
	<option value="goal"> 목표금액 달성률순 </option>
	<option value="due"> 마감일순 </option>
</select>
<input type="submit" name="검색" value="정렬" id="sub">
</form>


<form action="searchList" method="post">
<select name="category" id="category">
	<option value="NOT"> 카테고리 선택 </option>
	<option value="문화·예술"> 문화·예술 </option>
	<option value="푸드"> 푸드 </option>
	<option value="취미·반려동물"> 취미·반려동물 </option>
	<option value="패션·잡화"> 패션·잡화 </option>
	<option value="뷰티"> 뷰티 </option>
	<option value="여행·레저"> 여행·레저 </option>
	<option value="생활·리빙"> 패션·잡화 </option>
	<option value="소셜·캠페인"> 소셜·캠페인 </option>
</select>
<input type="text" name="search" plascholder="검색어 입력" id="search"/>
<input type="submit" name="searchBtn" value="검색" id="btn"/>
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
		<c:forEach items="${dto}" var="spon">
			<tr>
				<td>${spon.prj_cat}</td>
				<td><a href="detail?prj_no=${spon.prj_no}">${spon.prj_title}</a></td>
				<td colspan= "1"><img width="200" height="140" src="./upload/${spon.prj_photo}"/></td>                 
				<td>${spon.prj_picks}</td>
				<td>${spon.prj_date}</td>
				<td>${spon.prj_due}</td>
				<td>${spon.prj_goal}</td>
				<td>${spon.prj_curr}</td>
			</tr>
		</c:forEach>
			<tr>
			<td id = "Paging" colspan="8">      
				<div>	
					<c:set var="no">${newLo.no}</c:set>
					<c:set var="firstPage">${newLo.firstPage}</c:set>
					<c:set var="lastPage">${newLo.lastPage}</c:set>
					<c:set var="idx">${newLo.idx}</c:set>
					<c:set var="dataCnt">${newLo.dataCnt}</c:set>
					<c:set var="pageCnt">${newLo.pageCnt}</c:set>
					<c:set var="x">${newLo.x}</c:set>
					<c:if test="${firstPage!=1}">
						<a href=".${addr}?no=1"><<</a>
						<a href=".${addr}?no=${firstPage-1}&choice=&${choice}"><</a>
					</c:if>
					<c:if test="${dataCnt!=0}">
						<c:forEach var="i" begin="1" end="${lastPage-firstPage+1}" step="1">
							<a href="${addr}?no=${firstPage+i-1}&choice=${choice}">${firstPage+i-1}</a>
						</c:forEach>
					</c:if>
					<c:if test="${dataCnt>(x*lastPage)}">
						<a href="${addr}?no=${lastPage+1}&choice=${choice}">></a>       
						<a href="${addr}?no=${pageCnt}&choice=${choice}">>></a>                                                                                     
					</c:if>
				</div>        
			</td>
		</tr>
	</table>
</body>
<script>
</script>
</html>

          




















