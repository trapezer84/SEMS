<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육참가 취소신청</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/> "></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#retractionBtn").click(function(){
			var retractionMessage = $("#retractionMessage").val();
			if ( retractionMessage == null || retractionMessage.length == 0 ) {
				alert("취소 사유를 작성해 주세요.");
				$("#retractionMessage").focus();
				return;
			}
			var retractionForm = $("#retractionForm");
			retractionForm.attr("method", "post");
			retractionForm.attr("action", "<c:url value="/education/doRetraction"/>");
			retractionForm.submit();
		});
		
		$(".onlyText").on("keyup paste", function(event) {
			var regexp = /[!@\#$%<>&\()\=\’]/gi;
			var engregexp = /[\+*^a-zA-Z0-9-_\\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;
			var noengregexp = /[^\+*^a-zA-Z0-9-_\\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
			
			var tmpStr = $(this).val();
			var tmpStr2 = $(this).val();
			tmpStr = tmpStr.replace(engregexp, '');
			tmpStr2 = tmpStr2.replace(noengregexp, '');
			if ( (tmpStr.length * 3) + tmpStr2.length > 4000 ) {
				alert("글자 수가 너무 큽니다.");
				while(v.length > 0){
					v = v.substring(0, v.length - 1);
					tmpStr = v;
					tmpStr2 = v;
					tmpStr = tmpStr.replace(engregexp, '');
					tmpStr2 = tmpStr2.replace(noengregexp, '');
					if ( (tmpStr.length * 3) + tmpStr2.length <= 4000 ) {
						break;
					}
				}
				$(this).val(v);
			} 
		});
		
	});
</script>
</head>
<body>

	<form id="retractionForm">
		<input type="hidden" name="educationId" value="${ educationId }">
		<textarea id="retractionMessage" class="onlyText" name="retractionMessage"></textarea>
		<input type="button" id="retractionBtn" value="취소하기">
	</form>

</body>
</html>