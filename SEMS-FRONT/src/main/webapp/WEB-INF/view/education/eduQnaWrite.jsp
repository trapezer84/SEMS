<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Education 등록 페이지</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="<c:url value="/resources/js/jquery.timepicker.min.js"/>"></script>
<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.timepicker.min.css"/>">
<script type="text/javascript">
	$(document).ready(function() {
		
		$(document).on("keyup",".onlyText",function(){
			regexp = /[@\#$%<>&\=_\’]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		
		$("#writeQna").click(function(){
			
			if (confirm("정말 등록하시겠습니까?") == true) {
				
				if ($.trim($('#title').val()) == '') {
					alert("제목을 입력하시오.");
					$('#title').focus();
					return false;
				}
				if ($.trim($('#contents').val()) == '') {
					alert("내용을 입력하시오.");
					$('#contents').focus();
					return false;
				}
			}else {
				return false;;
			}
			
		});
		
	});
</script>
</head>
<body>

	질문/답변 게시판

	<div
		style="width: 30%; height: 100%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<form:form commandName="eduQnaVO" method="post"
			action="/doWriteEduQnaAction"> 
	    	<span>주제</span>
	    	<input type="text" class="onlyText" id="title" name="title" maxlength="100"/>
	    	<br />
			<span>내용</span>
			<br />
			<textarea rows="20" cols="20" class="onlyText" id="contents" name="contents"></textarea>
			<br />
			<input type="hidden" id="educationId" name="educationId" value="${educationId }" />
			<input type="submit" id="writeQna" value="등록하기" />
		</form:form>
	</div>
</body>
</html>