<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">

	$(document).ready(function () {
		
		$("#writeRequestMemberDetailBtn").click(function() {
			
			var form = $("#requestMemberDetailInfoForm");
			form.attr("action", "<c:url value="/memberDetail" />");
			form.submit();
		});	
		
		$("#cancelBtn").click(function() {
			location.href = "<c:url value="/memberManage/memberList"/>";
		});
		
	});
	
</script>
</head>
<body>

<c:set var="sessionId" value="${sessionScope._MEMBER_.id}"></c:set>

	<form:form id="requestMemberDetailInfoForm" commandName="personalInfoReadVO" method="post">
		<span>개인 정보 열람 사유서 작성</span>
		<input type="hidden" id="memberId" name="memberId" value="${sessionId}" />
		<input type="hidden" id="memberType" name="memberType" value="${member.memberType}" />
		<input type="hidden" id="targetMemberId" name="targetMemberId" value="${member.id}" />
		<div><span>열람 신청자 : ${sessionId}</span></div>
		<form:errors path="memberId" /><br/>
		<div><span>열람 대상 : ${member.id}</span></div>
		<form:errors path="targetMemberId" /><br/>
		<div><span>열람 사유 : </span>
				<textarea id="description" name="description" placeholder="열람 사유를 작성하세요."></textarea>
				<form:errors path="description" /><br/>
		</div>
		<input type="button" id="writeRequestMemberDetailBtn" value="작성"/>
		<input type="button" id="cancelBtn" value="취소"/>
	</form:form>

</body>
</html>