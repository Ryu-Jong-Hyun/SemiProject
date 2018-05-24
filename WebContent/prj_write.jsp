<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <!-- 제이쿼리 중복으로 주석 처리, 최종 확인 후 삭제 할 것 -->
      <!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
      <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" /> 
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
      <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
      <style>
         .wr{
            border: 1px solid black;
         }
         #testphoto{
            display : none;
         }
         #infoRw{
            display : none;
         }
         #page1,#page2,#page3{
            border : 1px solid black;
            min-width: 700px;
            min-height : 570px;
            text-align : center;
         font-size: 14pt;
         font-family:"굴림";
         font-weight: 600;
         }
         select{
         width : 200px;
         height : 25px;
         }
         input[type='text']{
            width : 300px;
            height : 25px;
         }
      textarea{
         width : 500px;
            height : 350px;
            resize : none;

      }
      table, th, td{
         border-collapse: collapse;
         padding : 1px 3px;
         text-align : center;
         width : 30%;
         margin : auto;
      }
      table, th{
         border: 1px solid grey;
      }
      #infoRw{
         background-color : white;
         font-size : 12px;
         font-weight : 100;
         position : absolute;
         top : 170px;
         left : 500px;
      }
      .ui-datepicker{
         font-size: 25px;
         width: 245px;
      }
      #ui-datepicker-div, #prj_due{
         width : 300px;
      }
      .btnClass1, .btnPrev, .btnNext, #btnSave{
          background-color : white;
         border : 0;
         outline : 0;
         font-weight : 400;
         font-size : 20px;
      }
      .btnClass1{
         width : 33%;
         height : 50px;
         border : 1px solid grey;   
         margin : 0;      
      }
      #prevSpan{
         position : absolute;
         top : 50%;
         left : 100px;
      }
      #nextSpan{
         position : absolute;
         top : 50%;
         right : 100px;
      }
      #savebtn{
      	position : absolute;
         top : 80%;
         right : 100px;
      }
      #btnSave{
         border : 1px solid grey;
         padding : 5px;
         background-color : lightblue;
      }
      
      </style>
   </head>
   <body>
         <jsp:include page="loginBox.jsp" />
      <jsp:include page="mainFrame.jsp" />
      <form name ="prj_write" action="prj_write" method="post" enctype="multipart/form-data">
      
      
         <input type="button" class="btnClass1" id="btn1" onclick="click1()" value="1단계">
         <input type="button" class="btnClass1" id="btn2" onclick="click2()" value="2단계">
         <input type="button" class="btnClass1" id="btn3" onclick="click3()" value="3단계">
         
         <!-- 페이지에 상관없이 버튼을 담은 span을 가장 위로 띄워줌 -->
         <div id="prevSpan" style="display: none">
            <input type="button" class="btnPrev" onclick="prevBtn()" value="◀ 이전단계">
         </div>
         <div id="nextSpan">
            <input type="button" class="btnNext" onclick="nextBtn()" value="다음단계 ▶">
         </div>
         <div id="savebtn">
            <input type="button" id="btnSave" onclick="save()" value="저장"/>
         </div>
         
         
         <!-- 1단계 -->
         <br/><div id="page1">
            <br/>카테고리 : <select name="prj_cat"> 
               <option value="카테고리 선택">카테고리 선택</option>
               <option value="문화·예술">문화·예술</option>
               <option value="푸드">푸드</option>
               <option value="취미·반려동물">취미·반려동물</option>
               <option value="패션·잡화">패션·잡화</option>
               <option value="뷰티">뷰티</option>
               <option value="여행·레저">여행·레저</option>
               <option value="생활·리빙">생활·리빙</option>
               <option value="소셜·캠페인"> 소셜·캠페인</option>
            </select>
            <br/><br/>프로젝트 이름 : <input type="text" name="prj_title" onkeyup="chkword(this, 500)" placeholder="                        30자 이내를 추천합니다."><br/>
            <br/>프로젝트 소개글 : <br/><textarea rows="5" name="prj_content" onkeyup="conChkword(this, 1000)"
                  placeholder="                       1000자까지 입력 가능합니다."></textarea><br/>
            <br/>사진 : <input type="file" id="prj_photo" name="prj_photo" onchange="imgPhotoChk(this)"/>
            <input type="text" id="testPhoto" readOnly/>
         </div>
         
         <!-- 2단계 -->
         <div id="page2">
            <br/>목표금액 : <input type="text" name="prj_goal" onkeyPress="onlyNum(this)"/>코인<br/>
            <br/><div id="one"><!-- 리워드 -->
               <table>
                  <tr>
                     <td colspan="2">리워드 아이템<br/><input type="text" id="rw_name" name="rw_name" onkeyup="chkword(this, 200)"/></td>
            </tr>
            <tr>
                 <td>리워드 최소금액<br/><input type="text" onkeyPress="onlyNum(this)" onkeyup="chkword(this, 10)" /></td>
                     <td>리워드 최대금액<br/><input type="text" onkeyPress="onlyNum(this)" onkeyup="chkword(this, 10)" /></td>
            </tr>
               </table>          
               
            </div>
            <div id="infoRw">
               리워드 안내<br/><br/>
               리워드는 투자자가 받을 수 있는 상품입니다.<br/>
               리워드 최소 개수는 1개, 최대 개수는 5개입니다.<br/>
               리워드 금액은 낮은 순서부터 높은 순서로, 중복 없이 설정해주세요.<br/>
               특정 사유 없이 위 사항이 지켜지지 않을 경우,<br/>
               해당 프로젝트 승인 거절 사유가 될 수 있습니다.<br/>
            </div>
            <input type="button" onclick="addRwBox()" value="리워드박스추가"/>
            <input type="button" onclick="delRwBox()" value="리워드박스삭제"/>
            <input type="hidden" id ="arrRwSave" name="rwVal"/><!-- 리워드 저장 -->

         </div>
         
         <!-- 3단계 -->
         <div id="page3">
            
            <br/>은행 : <select name="prj_bank">
               <option value="은행 선택">은행 선택</option>
               <option value="국민은행">국민은행</option>
               <option value="신한은행">신한은행</option>
               <option value="하나은행">하나은행</option>
               <option value="기업은행">기업은행</option>
               <option value="우리은행">우리은행</option>
               <option value="씨티은행">씨티은행</option>   
            </select><br/>
           <br/><br/>계좌번호 : <input type="text" name="prj_account" onkeyup="chkword(this, 100)" onkeyPress="hypNum(this)" placeholder="                  숫자와 '-'만 입력 가능합니다."/><br/>
            <br/><br/>마감일 : <input type="text" id="prj_due" name="prj_due" readOnly/><br/>
            <input type="hidden" name="pd_id" value="${sessionScope.loginId}"/><!-- 로그인 아이디 저장 -->
         </div>
      </form>
   </body>
   <script>
   var chkval="";//null인지 체크하고 담는 변수
      $(document).ready(function(){//기본실행

         click1();//1페이지 띄우기

         /* datepicker 설정 */
         $("#prj_due").datepicker({
            dateFormat: "yy-mm-dd"
         }); 
         $.datepicker.setDefaults({//datepicker 설정
            prevText: '이전 달',
            nextText: '다음 달',
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //월 이름
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //
            dayNames: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            showMonthAfterYear: true,
            yearSuffix: '년'
         });  
      }); 
   
      /* 1단계~3단계  페이지 설정*/
      var pageNum = 1;
      function prevBtn(){
    	  nextSpan.style.display = "block";
         pageNum --;
         if(pageNum<1){
            pageNum=1;
         }
         if(pageNum == 1){
            click1();
            prevSpan.style.display = "none";
         }else if(pageNum == 2){
            click2();
         }else if(pageNum == 3){
            click3();
         }
      }
      function nextBtn(){
    	 prevSpan.style.display = "block";
         pageNum ++;
         if(pageNum>3){
            pageNum=3;
         }
         if(pageNum == 1){
            click1(); 
         }else if(pageNum == 2){
            click2();
            
         }else if(pageNum == 3){
            click3();
            nextSpan.style.display = "none";
         }
      }
      function click1(){
         pageNum=1;
         $("#page2").hide();
         $("#page3").hide();
         $("#page1").show();
         console.log("click1 작동");
         $("#btn1").css("background-color", "skyblue");
         $("#btn2").css("background-color", "white");
         $("#btn3").css("background-color", "white");
      }
      function click2(){
         pageNum=2;
         $("#page1").hide();
         $("#page3").hide();
         $("#page2").show();
         console.log("click2 작동");
         $("#btn1").css("background-color", "white");
         $("#btn2").css("background-color", "skyblue");
         $("#btn3").css("background-color", "white");
      }
      function click3(){
         pageNum=3;
         $("#page1").hide();
         $("#page2").hide();
         $("#page3").show();
         console.log("click3 작동");
         $("#btn1").css("background-color", "white");
         $("#btn2").css("background-color", "white");
         $("#btn3").css("background-color", "skyblue");
      }

      /*리워드*/
      var arrRw=[];//리워드 담을 배열 선언
      var tCnt=1;//리워드 박스 개수
      var htmlRw = "<table><tr><td colspan='2'>리워드 아이템<br/><input type='text' name='rw_name' onkeyup='chkword(this, 200)'>"+
      "</td></tr><tr><td>리워드 최소금액<input type='text' onkeyPress='onlyNum(this)' onkeyup='chkword(this, 10)'>"+
      "</td><td>리워드 최대금액<input type='text' onkeyPress='onlyNum(this)' onkeyup='chkword(this, 10)'>"+
      "</td></tr></table>";

      //리워드 도움말
      $("#rw_name").hover(function(e){
          $("#infoRw").show();
          mouseX = e.pageX;
          mouseY = e.pageY;
          $("#infoRw").css("left", mouseX+"px").css("top", mouseY+"px");

          
         console.log("리워드 안내");
  
      },function(){
         $("#infoRw").hide();
      });
      function addRwBox(){//리워드 박스 추가
         $("#one").append(htmlRw);
         tCnt++;
         console.log(tCnt);
         if(tCnt>=6){
            alert("리워드 개수는 5개까지 가능합니다.");
            tCnt=5;
            $('table').last().remove(); 
         }
      }
      function delRwBox(){//리워드 박스 삭제
         if(tCnt>1){
            $('table').last().remove();
            tCnt--;
            console.log(tCnt);
         }else{
            alert("최소한 1개의 리워드는 존재해야 합니다.");
            tCnt=1;
         }
      }
      

      /* 리워드 저장부분 */
      function rwChk(){
         console.log("실행중");
          $('td').find('input').each(function(i, e){
              console.log("리워드 : "+$(this).val());
              if($(this).val() == ""){//리워드 null일 경우
                 selRw = $(this);          
                 chkval = "Y";
                 console.log(chkval);
                 return false;//여러번 중복해서 띄우지 않게
              }else{//리워드 null 아닐경우
                 chkval = "N";
                 arrRw.push($(this).val());//배열에 담기
              }
           }); 
           $("#arrRwSave").val(arrRw);//hidden에 담기
      }
      
      
      function save(){

          var selRw="";//this를 담는 변수. 후에 focus위해 설정
          var fileSize;//사진 크기
          var maxSize = 5 * 1024 * 1024;//5MB 사진 최대 크기

         console.log("실행중");
          $('td').find('input').each(function(i, e){
              console.log("리워드 : "+$(this).val());
              if($(this).val() == ""){//리워드 null일 경우
                 selRw = $(this);          
                 chkval = "Y";
                 console.log(chkval);
                 return false;//여러번 중복해서 띄우지 않게
              }else{//리워드 null 아닐경우
                 chkval = "N";
                 arrRw.push($(this).val());//배열에 담기
              }
           }); 
           $("#arrRwSave").val(arrRw);//hidden에 담기

          /*null값 없도록 확인*/                 
          if($("select[name='prj_cat']").val()=="카테고리 선택"){//카테고리
             alert("프로젝트 카테고리를 설정해주세요!");
             $("input[name='prj_cat']").focus();
             click1();
          }else if($("input[name='prj_title']").val()==""){//제목
             alert("프로젝트 제목을 작성해주세요!");
             $("input[name='prj_title']").focus();
             click1();
          }else if($("textarea[name='prj_content']").val()==""){//내용
             alert("프로젝트 소개글을 작성해주세요!");
             $("input[name='prj_content']").focus();
             click1();
          }else if($("#prj_photo").val()=="" && $("#testPhoto").val()==""){//사진 없으면
             if(confirm("사진을 등록하지 않을 경우 기본 이미지로 저장됩니다. 계속하시겠습니까?")){
                $("#testPhoto").val("기본 이미지로 저장됩니다.");
                alert("기본 이미지로 저장되었습니다. 저장 버튼을 다시 클릭해주세요");
                return false;
             }else{
                click1();
                $("input[name='prj_photo']").focus();
             }
          }else if($("#prj_photo").val()!="" && $("#testPhoto").val()!=""){
             fileSize= document.getElementById("prj_photo").files[0].size;
             if(fileSize>maxSize){//사진이 큰 경우
                alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다. ");
                click1();
                $("#prj_photo").val("");
                $("#prj_photo").focus();
             }
          }else if($("input[name='prj_goal']").val()==""){//목표금액
             alert("프로젝트 목표금액을 설정해주세요!");
             $("input[name='prj_goal']").focus();
             click2();
             console.log("photo 값 : "+$("input[name='prj_photo']").val());
          }else if($("input[name='prj_due']").val()==""){//마감일
             alert("프로젝트 마감일을 설정해주세요!");
             $("input[name='prj_due']").focus();
             click2();
          }else if(chkval == "Y"){
             alert("리워드는 공백이 들어갈 수 없습니다.");
             click2();
             selRw.focus();           
          }else if($("select[name='prj_bank']").val()=="은행 선택"){//은행
             alert("입금 받으실 은행명을 설정해주세요!");
             $("input[name='prj_bank']").focus();
             click3();
          }else if($("input[name='prj_account']").val()==""){//계좌
             alert("입금 받으실 은행의 계좌번호를 입력해주세요!");
             $("input[name='prj_account']").focus();
             click3();
          }else{
             arrRw=[];
             if(confirm("저장하시겠습니까?")){
                rwChk();
             document.prj_write.submit(); //조건에 안걸리면 submit
             }else{
                alert("취소되었습니다.");
             }
          }               
       }
      
      /*글자수 제한*/
         /*onlyNum 이 끝났다는 표시 flag
         chkword 실행시 flag 값을 확인
         끝났으면 실행, 안끝났으면 
      */
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
         }
      }
      
      function conChkword(obj, maxByte) {//글자수 조건 제한
         var strValue = obj.value;
         var strLen = strValue.length;
         var totalByte = 0;
         var len = 0;
         var oneChar = "";
         var str2 = "";
         for (var i = 0; i < strLen; i++) {
            oneChar = strValue.charAt(i);
            if (escape(oneChar).length > 0) {
               totalByte += 3;
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
         }

      }
      /*사진 확장자 제한*/

      var thumbext;
      function imgPhotoChk(photoEvt){
         var thumbext = photoEvt.value; //파일을 추가한 input 박스의 값

         thumbext = thumbext.slice(thumbext.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
         if(thumbext != "jpg" && thumbext != "png" &&  thumbext != "bmp"){ //확장자를 확인합니다.
            alert("확장자는 이미지 파일(jpg, png, bmp)만 등록 가능합니다.");
            $("#prj_photo").val("");
         }
      }
      
      /*숫자만 넣기*/
      function onlyNum(obj){

         if (event.keyCode >= 48 && event.keyCode <= 57) { //숫자키만 입력
            return true;
         } else {
            event.preventDefault(); 
         alert("숫자만 입력해주세요!'");
            return false;
         } 
      }
      /*숫자와 하이픈 넣기*/
      function hypNum(){
         console.log(event);
         console.log(this.event);
         if ((event.keyCode >= 48 && event.keyCode <= 57) || event.keyCode == 45) { /*숫자키와 하이픈 입력 */
            return true;
         } else {
            event.preventDefault();
         alert("숫자와 '-'만 입력해주세요!'");
            return false;          
         } 
      }
      
      /*성공 실패 메시지 출력*/
      var msg = "${msg}";
      if(msg != ""){
         alert(msg);
      }

      
   </script>
</html>