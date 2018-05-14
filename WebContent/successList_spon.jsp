<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
		/*successList*/
		table, tr, td{
			width: 220px;
			border: 1px solid black;
        	border-collapse: collapse;
        	padding: 5px 10px;
		}
		#popupTable{
			width: 250px;
			border: 1px solid black;
        	border-collapse: collapse;
        	padding: 5px 10px;
        	margin-left: 70px;
        	margin-top: 20px;
		}
		#prj_no{
			width : 20px;
		}
		button{
			float: right;
		}
		.out{
			width : 500px;
			margin : 5px 5px;
		}
		.in{
			width : 230px;
			padding : 5px 0;
			display: inline-block;
			float : left;
		}
		
		#popupOut{
			position:absolute;
			display: none;
			opacity : 0.5;
			background-color: black;
			width: 100%;
			height: 100%;
			z-index: 1;
		}

		.popup{
			display : none;
			background-color : white;
			width: 500px;
			height: 300px;														
			position: absolute;
			left: 500px;
			top : 300px;
			z-index: 2;
			text-align: center;
		}
		</style>
	</head>
	<body>
	<!-- successList -->
		<div id="popupOut">
		</div>
	 	<div class="out">
		  <c:forEach items="${successList1}" var="board">
		  	<div class="in">
		  	 <table>
				<tr>
					<td id="prj_no">${board.prj_no}</td> 
					<td id="prj_title">${board.prj_title}</td>	
				</tr>
				<tr>
					<td colspan="2">${board.prj_photo}</td>
				</tr>
				<tr>
					<td colspan="2"><button class="reviewForm" value="${board.prj_title}">후기 작성</button></td>
				</tr>
			  </table>
			  </div>
		   	</c:forEach>
		</div> 
		<div class="popup">
			<form action="review" method="post">
			  <table id="popupTable">
			  	<tr> 
					<td colspan="2"><input id="popTitle" type="text" readonly></td>
				</tr>
				<tr>
					<th>후기제목 :</th> 
					<td><input type="text" id="revTitle"/></td>
				</tr>
				<tr>
					<td colspan="2"><textarea class="edit" cols="50" rows ="10" id="revContent" ></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><button>확인</button></td>
				</tr>
			</table>
		</form>
		</div>
	</body>
	<script>
		$(".reviewForm").click(function(){
			$("#popupOut").css("display", "inline");
			$(".popup").css("display", "inline");
			var title = $(this).val();
			$("#popTitle").val(title);
		});
			
	</script>
</html>