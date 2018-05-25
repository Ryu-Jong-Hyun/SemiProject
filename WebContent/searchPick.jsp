<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
         table, td, th{
            border : 1px solid black;
            border-collapse: collapse;
         }
         table{
            width: 300px;
            height: 50px;
         }
         th{
            width: 50px;
         }
         .f{
            width: 300px;
            height: 50px;
         }
         .s{
            width: 300px;
            height: 200px;
         }
         
         .test{
               width : 350px;
               height: 350px;
               padding : 5px 0;
               display: inline-block;
               float : left;
            }
            
            #ta2{
            background-color: #2BA5BA;
         }
      </style>
   </head>
   <body>
      <jsp:include page="myPageTab.jsp" />
      <form action ="" method ="post">
         <c:forEach items="${picklist}" var="project">
            <div class="test" onclick="test()">
            <table>
               <tr class="f">
                  <th>번호</th>
                  <td>${project.prj_no}</td>
               </tr>
               <tr class="f">
                  <th>제목</th>
                  <td>${project.prj_title}</td>
               </tr>
               <tr class="s">
                  <th>사진</th>
                  <td style="text-align: center;"><a href="detail?prj_no=${project.prj_no}"><img width = 200px" height="150px" id="img" src="./upload/${project.prj_photo}"/></a></td>
               </tr>
            </table>
            </div>
            </c:forEach>
         <br/>
      </form>
   </body>
	<script>
		function test(){
			console.log("ㄴㅇㄹㅇㄴㄹ");
		}
	</script>
</html>