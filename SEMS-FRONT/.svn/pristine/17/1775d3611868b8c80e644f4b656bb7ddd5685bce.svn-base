<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$(".message").css("color", "red");
		
		var isCheckedCourseDropReason = false;
		
		$(".resignButton").click(function() {
			if( $("#courseDropReason").val() == "" ) {
				alert("사유를 입력하세요!");
				$("#courseDropReason").focus();
				return;
			}

			$.post("<c:url value='/dropCourseApply/${educationHistory.educationId}'/>", $("#dropForm").serialize(), function(data) {
				if (!data) {
					alert("인터넷 연결이 끊겼습니다");
				}
				else if(data == "OK") {
					alert("수강포기 신청이 완료되었습니다.");
					window.close();
				}
				else if (data == "NO") {
					$("#messageByResignRequestMsg").text("특수문자를 입력할 수 없습니다.");
				}
				else {
					alert("수강포기 신청을 할 수 없습니다.");
				}
			});
			
		});
		
		$("#courseDropReason").focus(function () {
			$("#messageByResignRequestMsg").text("");
		});
		
		$(".login .loginButton, .login .registButton").keypress(function (e) {
            if (e.keyCode === 13) {
                $(this).click();
            }
        });
	});
</script>
<body>


	강의포기 신청서  <br />
	맴버 아이디 : ${educationHistory.memberId }	<br />
	강의 시작일 : ${educationHistory.startDate }	<br />
	강의 종료일 : ${educationHistory.endDate }	<br />
	강의명 : ${educationHistory.educationTitle }	<br />
	<form id="dropForm" name="dropForm">
		포기사유 : <input type="text" id="courseDropReason" name="courseDropReason" /> 
		<input type="hidden" id="educationId" name="educationId" />
		<span class="button resignButton" style="cursor: pointer;">포기신청</span>
		<span class="message" id="messageByResignRequestMsg"></span>
	</form>

</body>
</html>