<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready( function() {
		
		$("#doWrite").click( function() {
			var form = $("#writeForm");
			form.attr("method","POST");
			form.attr("action","<c:url value='/attendanceHistory/memberDetail/doModifyState/'/>");
			form.submit();
		});		
	});
</script>
<title>Insert title here</title>
</head>
<body>
수정사유

	<form id="writeForm">
		<input type="text" id="comment" name="comment">
		<input type="hidden" id="educationId" name="educationId" value="${ educationId }">
		<input type="hidden" id="memberId" name="memberId" value="${ memberId }">
		<input type="hidden" id="attendTime" name="attendTime" value="${ attendTime }">
		<input type="submit" id="doWrite" value="등록하기" />
	</form>
</body>
</html>