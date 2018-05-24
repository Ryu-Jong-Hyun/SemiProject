<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
      <style>
         #prjDetail th, td{
            border: 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
         }
         input[type=text]{
            font-size: 12pt;
            font-family:"돋움";
            border:3px; 
            text-align:center;
         }
         textarea {
            resize: none;
         }
         table{
            position:absolute;
            top:100px;
            left:350px;
         }
         .aa{
            border: none;          
         }
         .qnaPlace{
            position : absolute;
            margin-top  : 800px;
            width : 100%;
         }
      </style>
   </head>
   <body>
   <jsp:include page="loginBox.jsp"/> 
      <jsp:include page="mainFrame.jsp" />
      <!-- 투자자목록 받아오기(보네) -->
      <c:forEach items="${sponlist}" var="sponlist">
         <input type="hidden" class="sponClass" value="${sponlist.id}">
      </c:forEach>
      <!-- 투자 form 시작 -->
      <form name="spon" action="spon" method="post">
      <input type="hidden" id="prj_no" name="prj_no" value="${info.prj_no}" readonly/><!-- 프로젝트번호 -->
      <input type="hidden" name="loginId" value="${sessionScope.loginId}"/><!-- 로그인아이디 -->
      <input type="hidden" id="total" name="total" value="${total}"/><!-- 현재 보유 가상화폐 잔액 -->
      <input type="hidden" id ="spon_item" name="spon_item" /><!-- 리워드 아이템 -->
      <!-- 상세보기 테이블 -->
      <table id="prjDetail">
         <tr>
            <td><input type="text" id="prj_cat" readonly/></td>
            <td><input type="text" id="prj_title" readonly/></td>
            <td><input type="text" id="prj_state" readonly/></td>
            <td><input type="text" id="pd_id" readonly/></td>
            <td><a href="sponsorListCheck?prj_no=${info.prj_no}">투자자목록</a></td>
         </tr>
         <tr>
            <c:if test="${info.prj_photo != null}"><!-- 사진 띄워주기 -->
               <td rowspan="7" colspan="3"><img width="300" height="300" src="./upload/${info.prj_photo}"/></td>
            </c:if>
         </tr> 
         <tr>
            <td colspan="2">목표금액까지 : <input type="text" id="prj_gc" size="3"  readonly/>%</td>
         </tr>
         <tr>
            <td colspan="2"><input type="text" id="prj_date" size="15" readonly/>~<input type="text" id="prj_due" size="15"  readonly/></td>
         </tr>
         <tr>
            <td colspan="2"><input type="text" id="prj_dday" size="10" readonly/>D-Day</td>
         </tr>
         <tr>
            <td colspan="2">모인금액 : <input type="text" id="prj_curr"  size="10" readonly/>밥</td>
         </tr>
         <tr>
            <td colspan="2">PICK :<input type="text" id="prj_picks" size="5"  readonly/></td>
         </tr>
         <tr>
            <td colspan="2">목표금액 : <input type="text" id="prj_goal" size="10"  readonly/>밥</td>
         </tr>
         <tr>
            <td rowspan="12" colspan="3"><textarea rows="30" cols="80" id="prj_content" readonly></textarea></td>
            <td><input type=button id="spon" onclick="fn_spon()" value="투자하기"/></td>
            <td><inpu type="button" id="pick"/>PICK</td>
         </tr>
         <tr>
            <td><input id="bob" name="bob" type = "text" placeholder="투자금액" onkeyPress="onlyNum(this)" onchange="fn_bobChange()"></td>
            <td><input type="button" id="reward" value="해당되는 리워드 확인"/></td>
         </tr>
         <c:set var="i">1</c:set>
         <c:forEach items="${rwd}" var="spon">
         <tr>
            <td colspan="2"><input id="item${i}" type="text" value="${spon.rw_item}" size="30" readonly/></td>                
         </tr>
         <tr class="ss">
            <td class="aa"><input id="min${i}" type="text" value="${spon.rw_min}밥" size="8" readonly/><input type="text" value="       ~" size="8" readonly/></td>                 
            <td class="aa"><input id="max${i}" type="text" value="${spon.rw_max}밥" size="8" readonly/></td>      
         <c:set var="i">${i+1}</c:set>
         </tr>           
         </c:forEach>
      </table>
      </form><!-- 투자 form 종료 -->
      <!--윤영 -QnA -->
      <!-- 문의하기 팝업(div) -->
      <div class="qnaPlace">
         <hr><strong>Q&A</strong><hr>
         <jsp:include page="qnaBoard.jsp" /> <!-- 리스트&등록하기 -->
      </div>
   </body>
   <script>
      var spon_item_chk ="";//리워드 아이템 항목 변수
      var itemChk = false;//리워드 확인 버튼 클릭 여부
      var loginId="${sessionScope.loginId}";//로그인 아이디(기획자 아이디인지, 이미 투자한사람 아이디인지 확인)
      var pd_id="";//기획자 아이디
      var obj = {};
      var idx;
      obj.type="POST";
      obj.dataType="JSON";
      obj.error=function(e){console.log(e)};
      function ajaxCall(param){
         console.log(param);
         $.ajax(param);
      }
      //ready function
      $(document).ready(function(){
         obj.url="./projectDetail";
         obj.success = function(data){
            console.log(data);
            printInfo(data.dto);
            qnaList(data.qdto); 
         };
         ajaxCall(obj);
         function printInfo(info){//상세보기 테이블 출력
            idx = info.idx;
            pd_id = info.pd_id;
            $("#prj_cat").val(info.prj_cat);
            $("#pd_id").val(info.pd_id);
            $("#prj_content").val(info.prj_content);
            $("#prj_title").val(info.prj_title);
            $("#prj_goal").val(info.prj_goal);
            $("#prj_curr").val(info.prj_curr);
            $("#prj_due").val(info.prj_due);
            $("#prj_date").val(info.prj_date);
            $("#prj_picks").val(info.prj_picks);
            $("#prj_state").val(info.prj_state);
            $("#prj_no").val(info.prj_no);
            $("#prj_gc").val(info.prj_gc);
            $("#prj_dday").val(info.prj_dday);
            console.log("현재금액 : ", $("#total").val());//현재금액 확인
            //기획자면 투자하기 버튼 비활성화
            if(loginId==pd_id){
               console.log("pd_id같음, 투자하기 버튼 비활성화");
               $("#spon").hide();
            }
            //이미 투자한 프로젝트 접근시 투자하기 버튼 비활성화
            for(i=0; i<$('.sponClass').length; i++){
               if($(".sponClass")[i].value==loginId){
                  $("#spon").hide();
               }
            }
         }
      });
      //픽하기
      $("#pick").click(function(){
         obj.url="./pick";
         obj.data={
               "prj_no":$("#prj_no").val()
         };
         obj.success = function(data){
            console.log(data);
            if(data.chk==0){
               if(data.pick > 0){
                  alert("찜");
               }else{
                  alert("찜 취소");   
               }
               $("#prj_picks").val(data.showP);
            }else{
               alert("로그인 후에 가능 합니다.");   
            }
         };
         ajaxCall(obj);
      });
       //투자금액 숫자만 받기 (금액은 자기가 가진 금액보다 작게 따로 설정)
       function onlyNum(obj){
          if (event.keyCode >= 48 && event.keyCode <= 57) {//숫자키만 입력
             return true;
          } else {
             event.preventDefault(); 
          }
       }
      //해당되는 리워드 확인 버튼 클릭시
      $("#reward").click(function(){
         var bob = $("#bob").val();
         bob = parseInt(bob);
         itemChk = true;//체크 유무 확인
         var item1 = document.getElementById("item1");
         var item2 = document.getElementById("item2");
         var item3 = document.getElementById("item3");
         var item4 = document.getElementById("item4");
         var item5 = document.getElementById("item5");
         

         //해당 리워드 색상 변경
         if(parseInt($("#min1").val())<=bob && parseInt($("#max1").val())>=bob){
            item1.style.color = "blue"; 
            spon_item_chk=$("#item1").val();

         }else if(parseInt($("#min2").val())<=bob && parseInt($("#max2").val())>=bob){
            item2.style.color = "blue";
            spon_item_chk=$("#item2").val();
         }else if(parseInt($("#min3").val())<=bob && parseInt($("#max3").val())>=bob){
            item3.style.color = "blue";
            spon_item_chk=$("#item3").val();
         }else if(parseInt($("#min4").val())<=bob && parseInt($("#max4").val())>=bob){
            item4.style.color = "blue";
            spon_item_chk=$("#item4").val();
         }else if(parseInt($("#min5").val())<=bob){
            item5.style.color = "blue"; 
            spon_item_chk=$("#item5").val();
         }
         
         //최대 금액보다 초과 조건
         
         if(item2==null && parseInt($("#min1").val())<=bob){
            item1.style.color = "blue"; 
            spon_item_chk=$("#item1").val();
         }else if(item3==null && parseInt($("#min2").val())<=bob){
            item2.style.color = "blue"; 
            spon_item_chk=$("#item2").val();
         }else if(item4==null && parseInt($("#min3").val())<=bob){
            item3.style.color = "blue"; 
            spon_item_chk=$("#item3").val();
         }else if(item5==null && parseInt($("#min4").val())<=bob){
            item4.style.color = "blue"; 
            spon_item_chk=$("#item4").val();
         }
      });
      //투자 금액 변경시 체크했던 항목 다시 변경
      function fn_bobChange(){
         itemChk=false;
         document.getElementById("item1").style.color="black";
         document.getElementById("item2").style.color="black";
         document.getElementById("item3").style.color="black";
         document.getElementById("item4").style.color="black";
         document.getElementById("item5").style.color="black";
      }
      //보네 - 투자하기
   
      function fn_spon(){
         if(parseInt($("#total").val()) < parseInt($("#bob").val())){
            alert("자신이 보유한 밥보다 클 수 없습니다. 현재 잔액은"+parseInt($("#total").val())+"밥 입니다.");
            return false;
         }
         console.log("현재금액",$("#total").val());
         if(itemChk==true && spon_item_chk!=""){//리워드 항목 체크 완료시
            console.log("itemChk완료됐음. 투자 시작");
            //프로젝트 번호
            var prj_no =$("#prj_no").val();
            console.log("prj_no:", prj_no);
            //로그인아이디
            console.log("loginId:", loginId);
            //해당 리워드
            console.log("spon_item_chk", spon_item_chk);
            $("#spon_item").val(spon_item_chk);
            console.log("spon_item", $("#spon_item").val());
            //투자한 금액
            var spon_don = $("#bob").val();
            console.log("spon_don:", spon_don);
            if(confirm("투자하시겠습니까?")){
               document.spon.submit(); /* 투자 submit */
            }else{
               alert("취소되었습니다.");
            }
         }else if(itemChk==true && (parseInt($("#min1").val())>$("#bob").val())){
            if(confirm("받을 리워드 상품이 없습니다. 그래도 투자하시겠습니까?")){
               spon_item_chk="받을 리워드 없음";
               console.log("spon_item_chk",spon_item_chk);
               alert("받을 리워드 없음으로 설정하였습니다. 투자하기 버튼을 다시 클릭해주세요.");
            }else{
               alert("취소되었습니다.");
            }         
         }else{
            alert("투자금액을 입력하고 해당되는 리워드를 먼저 확인해주세요!");
         }
      }
      //결과 띄워주기
      var sponMsg = "${sponMsg}";
      if(sponMsg != ""){
         alert(sponMsg);
      }
   </script>
</html>