<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
      <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" /> 
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
      <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
      <style>
         div{
            border: 1px solid black;
         }
         #testphoto{
            display : none;
         }
      </style>
   </head>
   <body>
      <form name ="prj_write" action="prj_write" method="post" enctype="multipart/form-data">
         <input type="button" onclick="click1()" value="1단계">
         <input type="button" onclick="click2()" value="2단계">
         <input type="button" onclick="click3()" value="3단계">
         
         <!-- 1단계 -->
         <div id="page1">
         카테고리 : <select name="prj_cat"> 
            <option value="카테고리 선택">카테고리 선택</option>
            <option value="문화·예술">문화·예술</option>
            <option value="푸드">푸드</option>
            <option value="취미·반려동물">취미·반려동물</option>
            <option value="패션·잡화">패션·잡화</option>
            <option value="뷰티">뷰티</option>
            <option value="여행·레저">여행·레저</option>
            <option value="생활·리빙">생활·리빙</option>
            <option value="소셜·캠페인"> 소셜·캠페인</option>
         </select><br/>
         프로젝트 이름 : <input type="text" name="prj_title"/><br/>
         프로젝트 소개글 : <textarea rows="5" name="prj_content"></textarea><br/>
         사진 : <input type="file" id="prj_photo" name="prj_photo"/><!-- file --><br/>
         <input type="text" id="testPhoto" readOnly/>
         </div>
         
         <!-- 2단계 -->
         <div id="page2">
         목표금액 : <input type="text" name="prj_goal"/>원<br/>
         마감일 : <input type="text" id="prj_due" name="prj_due" readOnly/><br/>
         <div id="one"><!-- 리워드 -->
            <table>
               <tr>
                  <td>리워드 아이템<br/><input type="text" name="rw_name"/></td><!-- 리워드 아이템 -->
                  <td>리워드 최소금액<br/><input type="text"/></td><!-- 리워드 최소금액 -->
                  <td>리워드 최대금액<br/><input type="text"/></td><!-- 리워드 최대금액 -->
               </tr>
            </table>
         </div>
         <input type="button" onclick="addRwBox()" value="리워드박스추가"/>
          <input type="hidden" id ="arrRwSave" name="rwVal"/><!-- 리워드 저장 -->
         </div>
         
         <!-- 3단계 -->
         <div id="page3">
         은행 : <select name="prj_bank">
               <option value="은행 선택">은행 선택</option>
               <option value="국민은행">국민은행</option>
               <option value="신한은행">신한은행</option>
               <option value="하나은행">하나은행</option>
               <option value="기업은행">기업은행</option>
               <option value="우리은행">우리은행</option>
               <option value="씨티은행">씨티은행</option>   
            </select><br/>
         계좌번호 : <input type="text" name="prj_account"/><br/>
         <br/>
         <input type="hidden" name="pd_id" value="${sessionScope.loginId}"/><!-- 로그인 아이디 저장 -->
         </div>
         
         <input type="button" onclick="prevBtn()" value="이전단계">
         <input type="button" onclick="nextBtn()" value="다음단계">

          <input type="button" onclick="save()" value="저장"/>
      </form>
   </body>
   <script>
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
         pageNum --;
         if(pageNum<2){
            pageNum=1;
         }
         if(pageNum == 1){
            click1();
         }else if(pageNum == 2){
            click2();
         }else if(pageNum == 3){
            click3();
         }
         console.log(pageNum+"단계 이동");
         
      }
      function nextBtn(){
         pageNum ++;
         if(pageNum>2){
            pageNum=3;
         }
         console.log(pageNum);
         if(pageNum == 1){
            click1();
         }else if(pageNum == 2){
            click2();
         }else if(pageNum == 3){
            click3();
         }
      }
   
      function click1(){
         pageNum=1;
         $("#page2").hide();
         $("#page3").hide();
         $("#page1").show();

      }
      function click2(){
         pageNum=2;
         $("#page1").hide();
         $("#page3").hide();
         $("#page2").show();

      }
      function click3(){
         pageNum=3;
         $("#page1").hide();
         $("#page2").hide();
         $("#page3").show();

      }
      
      /*리워드*/
      var arrRw=[];//리워드 담을 배열 선언
      var tCnt=1;//리워드 박스 개수
      var htmlRw = "<table><tr><td><input type='text' name='rw_name'></td><td><input type='text'></td><td><input type='text'></td></tr></table>";
      
      function addRwBox(){//리워드 박스 추가
         $("#one").append(htmlRw);
         tCnt++;
         if(tCnt>=6){
            alert("리워드 개수는 5개까지 가능합니다.");
            $('table').last().remove(); 
         }
      }
   
      function save(){
         var chkval="";//null인지 체크하고 담는 변수
         var selRw="";//this를 담는 변수. 후에 focus위해 설정
         /* 리워드 저장부분 */
         $('td').find('input').each(function(i, e){
             console.log("리워드 : "+$(this).val());
             if($(this).val() == ""){//리워드 null일 경우
                selRw = $(this);          
                chkval = "Y";
                return false;//여러번 중복해서 띄우지 않게
             }else{//리워드 null 아닐경우
                arrRw.push($(this).val());//배열에 담기
                console.log("arrRw : "+arrRw);
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
            }else if($("#prj_photo").val()=="" && $("#testPhoto").val()==""){//사진
              if(confirm("사진을 등록하지 않을 경우 기본 이미지로 저장됩니다. 계속하시겠습니까?")){
                 $("#prj_photo").hide();
                 $("#testPhoto").show();
                 $("#testPhoto").val("테스트 이미지로 저장됩니다.");
                 $("#testPhoto").focus();
              }else{
                 click1();
                 $("input[name='prj_photo']").focus();
              }
           }else if($("input[name='prj_goal']").val()==""){//목표금액
               alert("프로젝트 목표금액을 설정해주세요!");
               $("input[name='prj_goal']").focus();
               click2();
               console.log($("input[name='prj_photo']").val());
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
              if(confirm("저장하시겠습니까?")){
                 document.prj_write.submit(); //조건에 안걸리면 submit
              }else{
                 alert("취소되었습니다.");
              }
           }               

          
/*           확장자명 이미지 파일로 한정하기
          크기가 크면 이미지 저장 안됨... 
      리워드박스 삭제*/
      }
		var msg = "${msg}";
		
		if(msg != ""){
			alert(msg);
		}    
   </script>
</html>