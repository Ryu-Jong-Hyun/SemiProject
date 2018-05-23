 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>sponG</title>
      <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
      <style>
         img#img{position: absolute; left: 537px; top: 200px; border: 1px solid black; width: 500px; height: 400px;}              
         input#pick{position: absolute; left: 545px; top: 210px; border: none; font-size: 20px;} 
         input#prj_gc{position: absolute; left: 820px; top: 210px;  font-size: 20px; border: none; width: 200px; }     
         #a{position: absolute; left: 490px; top: 370px; font-size: 20px;}             
         #b{position: absolute; left: 1050px; top: 370px; font-size: 20px;}       
                                         
         #b1{position: absolute; left: 1410px; top: 750px; font-size: 20px;}
         #b2{position: absolute; left: 1410px; top: 980px; font-size: 20px;}                                            
      .in{                 
         width : 230px;    
         padding : 5px 0;   
         display: inline-block;       
         float : left;       
      }
      .out{       
         width : 1200px;   
         position: absolute;     
         left: 200px;
         top: 700px;               
      }     
      
      .in2{                       
         width : 230px;
         padding : 5px 0;
         display: inline-block;       
         float : left;
      }
      .out2{      
         width : 1200px;            
         position: absolute;     
         left: 200px;                 
         top: 930px;                              
      }  
      #title1{position: absolute; left: 400px; top: 200px;}           
      #title2{position: absolute; left: 200px; top: 650px;}                 
      #title3{position: absolute; left: 200px; top: 880px;}                 
        #hr1{position: absolute;  top: 1200px; width: 1600px;}               
        #hr2{position: absolute;  top: 650px; width: 1600px;}           
        #hr3{position: absolute;  top: 870px; width: 1600px;}                                                   
      </style>                                     
   </head>                   
   <body>                                             
                               
       <jsp:include page="mainFrame.jsp" />         
       
<input id="b" type="button" onclick="click1()" value="▶">             
<input id="a" type="button" onclick="click2()" value="◀">      
<input id="b1" type="button" onclick="dateClick1()" value="+">          
<input id="b2" type="button" onclick="dueClick1()" value="+">          
<h3 id="title1">▶인기프로젝트</h3>     
<h3 id="title2">▶새로운프로젝트</h3>
<h3 id="title3">▶마감앞둔프로젝트</h3>
<hr id="hr1"/>
<hr id="hr2"/>
<hr id="hr3"/>   
  
                  
      <c:set var="i">1</c:set>       
       <c:forEach items="${dto}" var="spon">  
      <div id="listTable${i}" class="in">        
            <c:if test="${spon.prj_photo != null}">
               <a href="detail?prj_no=${spon.prj_no}"><img id="img" src="./upload/${spon.prj_photo}"/></a>       
            </c:if>
            <input id="pick" type="text" value="픽 ${spon.prj_picks}"/></td>
            <input  type="text" id="prj_gc" size="3"  value="목표금액까지 : ${spon.prj_gc}%" readonly/>
       </div>   
      <c:set var="i">${i+1}</c:set>         
      </c:forEach>

              
      
      <c:set var="ii">1</c:set>       
      <div class="out">
         <c:forEach items="${dto1}" var="spon1">
         <div id="date${ii}" class="in">        
            <c:if test="${spon1.prj_photo != null}">         
               <a href="detail?prj_no=${spon1.prj_no}"><img border="1px solid black" id="dateImg" width="200" height="140" src="./upload/${spon1.prj_photo}"/></a>    
            </c:if>                              
            <c:set var="ii">${ii+1}</c:set>
         </div>
            </c:forEach>    
      </div>

      <c:set var="iii">1</c:set>       
      <div class="out2">
         <c:forEach items="${dto2}" var="spon2">    
         <div id="due${iii}" class="in2">        
            <c:if test="${spon2.prj_photo != null}">        
               <a href="detail?prj_no=${spon2.prj_no}"><img border="1px solid black" id="dateImg" width="200" height="140" src="./upload/${spon2.prj_photo}"/></a>    
            </c:if>                         
            <c:set var="iii">${iii+1}</c:set>
         </div>     
            </c:forEach>    
      </div>      
   
               
                            
                   
                
             
         
    
   </body>
   <script>
   var msg = "${msg}";
   if(msg != ""){
      alert(msg);
   }    
   
 var size = "${size}";                
 var z = 1;                   
                      
   $(document).ready(function(){      
      for(var i=0; i<=size; i++){    
           $("#listTable"+i).hide();                     
      }
      $("#listTable1").show();       
   });
                                              
         
      
     function click1(){
      for(var i=1; i<=size; i++){       
           $("#listTable"+i).hide();             
      }                          
      if(z<size){        
      $("#listTable"+(z=z+1)).show();                              
        console.log(z);
      }else{
         z = 0;           
         $("#listTable"+(z=z+1)).show();                              
           console.log(z);        
      }
     }
          
     function click2(){           
       for(var i=1; i<=size; i++){       
            $("#listTable"+i).hide();             
       }                                 
       if(z>1){                                             
       $("#listTable"+(z=z-1)).show();            
       console.log(z);       
       }else{                                   
          z = size; 
           $("#listTable"+z).show();  
           console.log(z);    
       }
     }
     
        
var size1 = "${size1}";                
var a = 1;      
var b = 2;
var c = 3;
var d = 4;
var e = 5;
    $(document).ready(function(){      
      for(var i=0; i<=size1; i++){          
           $("#date"+i).hide();                     
      }
      $("#date1").show();       
      $("#date2").show();          
      $("#date3").show();
      $("#date4").show();     
      $("#date5").show();      
      
   });
    
    function dateClick1(){
      for(var i=1; i<=size; i++){       
           $("#date"+i).hide();     
                  
      }  
      a=a+5;     
      console.log(a);         
      if(a<=size1){  // 6=<7                  
         $("#date"+a).show();               
         b=b+5;
            if(b<=size1){    // 7=<7  
               $("#date"+b).show();     
               c=c+5;
                  if(c<=size1){
                  $("#date"+c).show();
                  d=d+5;
                     if(d<=size1){
                     $("#date"+d).show();
                     e=e+5;    
                        if(e<=size1){     
                        $("#date"+e).show();                 
                        }
                    }     
               }
            }
       }else{
          $("#date1").show();       
          $("#date2").show();          
          $("#date3").show();
          $("#date4").show();     
          $("#date5").show();
          a = 1;          
          b = 2
          c = 3;
          d = 4;
          e = 5;      
       }      
     }
     
    
 
    
    var size2 = "${size2}";                      
    var f = 1;            
    var g = 2;
    var h = 3;
    var k = 4;
    var j = 5;
    
    $(document).ready(function(){      
      for(var i=0; i<=size1; i++){          
           $("#due"+i).hide();                     
      }     
      $("#due1").show();       
      $("#due2").show();          
      $("#due3").show();     
      $("#due4").show();     
      $("#due5").show();           
      
   });
    
    function dueClick1(){   
      for(var i=1; i<=size; i++){           
           $("#due"+i).hide();         
                  
      }  
      f=f+5;           
      console.log(f);         
      if(f<=size2){  // 6=<7                  
         $("#due"+f).show();               
         g=g+5;
            if(g<=size2){    // 7=<7  
               $("#due"+g).show();     
               h=h+5;
                  if(h<=size2){
                  $("#due"+h).show();
                  k=k+5;
                     if(k<=size2){    
                     $("#due"+k).show();
                     j=j+5;    
                        if(j<=size2){     
                        $("#due"+j).show();                      
                        }
                    }     
               }
            }       
       }else{
          $("#due1").show();       
          $("#due2").show();          
          $("#due3").show();
          $("#due4").show();     
          $("#due5").show();
          f = 1;          
          g = 2;
          h = 3;
          k = 4;
          j = 5;      
       }      
     }
    
             
     
   </script>
</html>