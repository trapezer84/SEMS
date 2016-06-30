<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#registerButton").click(function() {
		var form = $("#minutesForm");
		form.attr("action", "<c:url value="/team/doWriteAction/${teamId}" />");
		form.submit();
		alert("회의록이 등록되었습니다.");
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>회의록 작성</h3>
	<br />
	<br />
	<form:form id="minutesForm" commandName="minutesVO" method="POST" >
	회의 일자:	<input type="date" id="agendaDate" name="agendaDate" value="${ minutesVO.agendaDate }" />
		<br />
	회의 시간 : <input type="time" id="startTime" name="startTime" value="${ minutesVO.startTime }" /> 
			 <input type="time" id="endTime" name="endTime" value="${ minutesVO.endTime }" />
		<br/>
	회의 안건: <input type="text" id="minutesAgenda" name="minutesAgenda"
			placeholder="회의 안건을 입력하세요." value="${ minutesVO.minutesAgenda }" />
		<form:errors path="minutesAgenda" />
		<br />
	참석자: <input type="text" id="attendance" name="attendance"
			placeholder="참석자를 입력하세요." value="${ minutesVO.attendance }" />
		<form:errors path="attendance" />
		<br />
	회의 장소: <input type="text" id="minutesPlace" name="minutesPlace"
			placeholder="회의 장소를 입력하세요." value="${ minutesVO.minutesPlace }" />
		<form:errors path="minutesPlace" />
		<br />
	회의 내용: <input type="text" id="minutesContent" name="minutesContent"
			placeholder="회의 내용을 입력하세요." value="${ minutesVO.minutesContent }" />
		<form:errors path="minutesContent" />
		<br />
	결정 사항: <input type="text" id="decisionSubject" name="decisionSubject"
			placeholder="결정 사항을 입력하세요." value="${ minutesVO.decisionSubject }" />
		<form:errors path="decisionSubject" />
		<br />
	비 고: <input type="text" id="remarks" name="remarks"
			placeholder="비고를 입력하세요." value="${ minutesVO.remarks }" />
		<br />

		<input type="submit" id="registerButton" value="등록" />
	</form:form>

</body>
</html>