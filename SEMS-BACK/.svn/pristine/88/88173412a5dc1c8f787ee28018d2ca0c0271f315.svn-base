<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title> 자료 게시판 수정 페이지 </title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$("#doModify").click(function() {
			
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
			if (confirm("정말 수정 하시겠습니까?") == true){
			var form = $("#registerForm");
			form.attr("action", "<c:url value="/${educationId}/doEduFileNoticeModify/${eduNotice.eduNoticeId}" />");
			form.submit();
			}
		});		

		$("#cancel").click(function() {
			location.href="<c:url value='/${educationId}/eduFile' />";
		});
		
		
	});
</script>
</head>
<body>

자료 게시판 수정 페이지 

<div
		style="width: 70%; height: 100%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<form:form id="registerForm" commandName="EduNoticeVO" method="post" >
				<select name="noticeType" style="width: auto;">
					<c:if test="${ eduNotice.noticeType eq 'normal' }">
			        	<option value="normal" selected>일반 공지사항</option>
			        </c:if>
			        <c:if test="${ eduNotice.noticeType eq 'important'}">
			        	<option value="important" selected >전체 공지사항</option>
			        </c:if>
			    </select> 
			
		  <%--   <c:choose>
				<c:when test="${ noticeType eq normal}">
					<select name="noticeType" style="width: auto;">
				        	<option value="normal" selected>일반 공지사항</option>
				        	<option value="important" >전체 공지사항</option>
				    </select> 
				 </c:when>
				<c:when test="${noticeType eq important }"> 
					<select name="noticeType" style="width: auto;">
				        	<option value="normal" >일반 공지사항</option>
				        	<option value="important" selected >전체 공지사항</option>
				    </select> 
				</c:when>
			</c:choose> --%>
		        <br><br>
	     	제목 : <input type="text" name="title" id="title" value="${eduNotice.title }" style="height: 45px; width: 90%;">
	     	<br/>
	   		내용 :  
	<%--    		<input type="text" style="height: 300px; width: 90%;"
				 name="contents"
				value="${eduNoticeVO.contents }" /> --%>
	   		<textarea name="contents" id="contents" style="height: 300px; width: 90%;">${eduNotice.contents }</textarea> 
	   		<div style = "text-align:right;"> 
	   		
	   			<input type="button" id="doModify" class="inputButton"  value="수정하기" />
				<input type="button" id="cancel" class="inputButton" value="취소"/></br>
		    </div>
	     </form:form>
	</div>
</body>
</html>