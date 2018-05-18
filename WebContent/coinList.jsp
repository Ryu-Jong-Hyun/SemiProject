<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회폐 내역 조회</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			table, td, th, input{
			 	text-align: center;
			 	border: none;
			}
			
			#ta1{
				background-color: lime;
			}
		</style>
	</head>
	<body>
	<jsp:include page="myPageTab.jsp" />
		<table>
		<tr>
			<th colspan="3">잔액 : ${balance} 코인</th>
		</tr>
		<tr>
			
			<th>금액</th>
			<th>날짜</th>
			<th>유형</th>
		</tr>
		<c:forEach items="${list}" var="info">
		<tr>
			<c:choose>
		    	<c:when test="${info.coin_list=='투자'}">
					<c:set var="plma">-</c:set>
				</c:when>
		    	<c:otherwise>
		    		<c:set var="plma">+</c:set>
		    	</c:otherwise>
    	</c:choose>
			<td><input type="text" name="coin_don" value="${plma}${info.coin_don}" readonly="readonly"/></td>
			<td><input type="text" name="userAge" value="${info.coin_date}" readonly="readonly"/></td>
			<td><input type="text" name="coin_list" value="${info.coin_list}" readonly="readonly"/></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3">
				<div>	
					<c:set var="no">${newLo.no}</c:set>
					<c:set var="firstPage">${newLo.firstPage}</c:set>
					<c:set var="lastPage">${newLo.lastPage}</c:set>
					<c:set var="idx">${newLo.idx}</c:set>
					<c:set var="dataCnt">${newLo.dataCnt}</c:set>
					<c:set var="pageCnt">${newLo.pageCnt}</c:set>
					<c:set var="x">${newLo.x}</c:set>
					<c:if test="${firstPage!=1}">
						<a href="coinListForm?no=1"><<</a>
						<a href="coinListForm?no=${firstPage-1}"><</a>
					</c:if>
					<c:if test="${dataCnt!=0}">
						<c:forEach var="i" begin="1" end="${lastPage-firstPage+1}" step="1">
							<a href="coinListForm?no=${firstPage+i-1}">${firstPage+i-1}</a>
						</c:forEach>
					</c:if>
					<c:if test="${dataCnt>(x*lastPage)}">
						<a href="coinListForm?no=${lastPage+1}">></a>
						<a href="coinListForm?no=${pageCnt}">>></a>
					</c:if>
				</div>
			</td>
			
		</tr>
	</table>
	</body>
	<script>
		var msg = "${msg}";
		if(msg !=""){//원하는 값이 없을 경우 메인으로 다시보냄
			alert(msg);
			location.href="main";
		}
	</script>
</html>