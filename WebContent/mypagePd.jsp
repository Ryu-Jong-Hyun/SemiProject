<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<style>
		table, tr, td{
			width: 220px;
			border: 1px solid black;
        	border-collapse: collapse;
        	padding: 5px 10px;
        	text-align: center;
        	
		}
		#prj_no{
			width : 20px;
		}
		button{
			float: right;
		}
		.out{
			width : 700px;
			/* margin : 0 auto; */
			position: absolute;
			left: 30%;
			top: 30%;
		}
		a#a{
			position: absolute;
			left: 560px;
			top: 250px
		}
		
		a#b{
			position: absolute;
			left: 600px;
			top: 250px
		}
		
		.in{
			width : 230px;
			padding : 5px 0;
			display: inline-block;
			float : left;
		}
		
		
		#ta1{
				background-color: #2BA5BA;
			}          
	</style>
</head>
<body>
		<jsp:include page="myPageTab2.jsp" />
		 	<div class="out">     
		<c:forEach items="${dto}" var="board">            
			<div class="in">
		  	 	<table>
		  	 	<tr>
					<td>${board.prj_state}</td> 
					<td><a href="detail?prj_no=${board.prj_no}">${board.prj_title}</a></td>
				</tr>
					<tr>
					<c:if test="${board.prj_photo != null}">
						<td colspan= "2"><img width="200" height="140" src="./upload/${board.prj_photo}"/></td>
					</c:if>
					</tr>
					</table>
			 </div>
		</c:forEach>
		</div>
			<a id="b" href="listNext?start=${start}&end=${end}">다음</a>
			<a id="a" href="listBack?start=${start}&end=${end}">이전</a>
</body>
<script>

</script>
</html>




