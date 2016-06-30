<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<style type="text/css">
.inputButton {
	border: none;
	border-radius: 5px;
	padding: 6px 12px;
	font-weight: bold;
	text-transform: uppercase;
	color: #FFFFFF;
	background-color: #E05149;
}
</style>
<script type="text/javascript"
   src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#doWrite").click(function(){
	
			if (confirm("정말 등록 하시겠습니까?") == true){
				
				if ($.trim($('#title').val()) ==''){
					alert("제목을 입력하시오.");
					$('#title').focus();
					return false;
				}
				if ($.trim($('#contents').val()) ==''){
					alert("공지사항 글 내용을 작성하시오.");
					$('#contents').focus();
					return false;
				}
				var form = $("#registerForm");
				form.attr("action", "<c:url value="/${educationId}/doEdufileNoticeWriteAction" />");
				form.submit();
			}
			
		});
	
		$("#reset").click(function(){
			location.reload();
		});
		
		$("#cancel").click(function() {
			location.href="<c:url value='/${educationId}/eduFile' />";
		});

	});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의 자료 게시판 공지사항</title>
</head>
<body>
	<h1>  강의자료 게시판 공지사항 글쓰기 </h1>
	<div
		style="width: 70%; height: 100%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<form:form id="registerForm" commandName="EduNoticeVO" method="post" >
			
		  	<select name="noticeType" style="width: auto;">
			        	<option value="normal" selected>일반 공지사항</option>
			        	<option value="important">전체 공지사항</option>
		 </select> 
		        <br><br>
	     	제목 : <input type="text" name="title" id="title" value="${eduNoticeVO.title }" style="height: 45px; width: 90%;">
	     	<br/>
	   		내용 :  
	<%--    		<input type="text" style="height: 300px; width: 90%;"
				 name="contents"
				value="${eduNoticeVO.contents }" /> --%>
	   		<textarea name="contents" id="contents" style="height: 300px; width: 90%;">${eduNoticeVO.contents }</textarea> 
	   		<div style = "text-align:right;"> 
	   			<input type="button" id="doWrite" class="inputButton"  value="등록하기" />
		     	<input type="button" id="reset" class="inputButton" value="다시 쓰기"/>
				<input type="button" id="cancel" class="inputButton" value="취소"/></br>
		    </div>
	     </form:form>
	</div>



</body>
</html>