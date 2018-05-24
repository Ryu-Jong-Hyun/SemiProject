<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		#prj_no{
			width : 20px;
		}
		.out{
			width : 800px;
			height: 1000px;
			margin : 5px 5px;
		}
		.in{
			width : 230px;
			padding : 5px 0;
			display: inline-block;
			float : left;
		}
		
		 #popupTable{
			width: 230px;
			border: 1px solid black;
        	border-collapse: collapse;
        	padding: 5px 10px;
        	margin-left: 70px;
        	margin-top: 10px;
		}
		.popup{
			display : none;
			background-color : yellow;
			width: 500px;
			height: 300px;														
			position: absolute;
			left: 500px;
			top : 300px;
			z-index: 3;
			text-align: center;
		}
		#ta4{
				background-color: #2BA5BA;
			}
		</style>
	</head>
	<body>
	<jsp:include page="myPageTab.jsp" />
	
	<!-- successList -->
	 	<div class="out">	
	 	  <c:forEach items="${successList1}" var="board">
	 		<div class="in">
	 		  <table>
				<tr>
					<td id="prj_no">${board.prj_no}</td>
					<td id="prj_title">${board.prj_title}</td>
				</tr>
				<tr>
					<td colspan="2"><a href="detail?prj_no=${board.prj_no}">${board.prj_photo}</a></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="button" class="reviewForm"  id='${board.prj_no}/${board.prj_title}/${board.pd_id}' value="후기 작성">
					</td>
				</tr>
				 </table>
			   </div> 
			</c:forEach>
			<c:if test="${fn:length(successList1)==0}">
				<span>투자한 프로젝트  또는 성공한 프로젝트 가 없습니다.</span>
			</c:if>
		</div>
		

		<!--////////////////////////팝업 ///////////////////////////-->
		<div class="popup">
		<input type="button" id="close" value="X">
			  <table id="popupTable">
			  	<tr> 
					<td colspan="2"><input type="text"  id="popTitle" readonly>
									<input type="hidden"  id="popupPrj_no">
									<input type="hidden" id="popupPd_id">
					</td>
				</tr>
				<tr>
					<th>후기제목 :</th> 
					<td><input type="text" id="revTitle"/></td>
				</tr>
				<tr>
					<td colspan="2"><textarea class="edit" cols="50" rows ="10" id="revContent" ></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" id="rBtn" value="확인">
					</td>
				</tr>
			</table>
		</div>
	</body>
	<script>
		/* 후기 등록하기(Ajax) */
		var obj={};//초기화   
	    obj.type="post";
	    obj.dataType="json";
	    obj.error=function(e){console.log(e)};

		//'후기작성'버튼 클릭시
		$(".reviewForm").click(function(){
			var id = $(this).attr('id');
			var idArr = [];
			idArr = id.split('/');
			$(".popup").css("display", "inline"); //후기 작성 팝업창 보임
			$("#popupPrj_no").val(idArr[0]);//prj_no
			$("#popTitle").val(idArr[1]); //제목
			$("#popupPd_id").val(idArr[2]);//pd_id
			
			console.log($("#popupPd_id"));
		});
		$("#close").click(function(){
			$(".popup").css("display", "none");
		}); 
		
		$("#rBtn").click(function(){
			console.log("서버 전송!!");
			obj.url="review";
	        obj.data={
	        		prj_no:$("#popupPrj_no").val(),
	        		pd_id:$("#popupPd_id").val(),
	        		revTitle:$("#revTitle").val(),
	        		revContent:$("#revContent").val()
	       			
	        }
	    
	        obj.success = function(data){
	           if(data.success >= 1){
	              alert("후기 등록 완료.");
	              location.href="successList1";
	           }else{
	              alert("후기 등록 실패.");
	           }
	        }
	        ajaxCall(obj);
		});
		
		
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}	
		
		function ajaxCall(obj){
	         $.ajax(obj);
	      }
	</script>
</html>