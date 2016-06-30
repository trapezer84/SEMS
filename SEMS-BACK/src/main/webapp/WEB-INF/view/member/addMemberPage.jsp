<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>

<style type="text/css">
.inputButton {
	border:none;
	border-radius:5px;
	padding:6px 12px;
	font-weight:bold;
	text-transform:uppercase;
	color:#FFFFFF;
	background-color:#E05149;
}
</style>

<script type="text/javascript">
	$(document).ready(function () {
		
		$("#studentForm").hide();
		
		var isCheckedPassword = false;
		var isCheckedId = false;
		var isValidPassword = false;
		var isCheckedEmail = false;
		var isCheckedPhoneNumber = false;
		
		$("#id").blur(function () {
			if($("#id").val()=="") {
				$("#messageById").text("");
				return;
			}
			$.post("<c:url value="/checkValidationById" />", { "id" : $("#id").val() }, function(data) {
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageById").text("사용할 수 있는 아이디 입니다.").css("color", "green");
					isCheckedId = true;
				} else if (data == "EXIST") {
					$("#messageById").text("이미 사용중이거나 탈퇴한 회원입니다!").css("color", "red");
					isCheckedId = false;
				} else if (data == "NO") {
					$("#messageById").text("영문, 숫자 조합의 5~20 글자이어야 합니다!").css("color", "red");
					isCheckedId = false;
				}
			});
		});
		
		$("#id").focus(function () {
			$("#messageById").text("");
		});
		
		$("#password").blur(function () {
			$.post("<c:url value="/checkValidationByPassword" />", { "password" : $("#password").val() }, function(data) {
				if($("#password").val()=="") {
					$("#messageByPassword").text("");
					return;
				}
				
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageByPassword").text("안전한 비밀번호 입니다.").css("color", "green");
					isValidPassword = true;
				} else if (data == "NO") {
					$("#messageByPassword").text("영문, 숫자, 특수문자 조합의 10~16 글자이어야 합니다!").css("color", "red");
					isValidPassword = false;
				}
			});
			
			$("#repeatPassword").val("");
		});
		
		$("#password").focus(function () {
			$("#messageByPassword").text("");
		});
		
		$("#repeatPassword").blur(function () {
			$.post("<c:url value="/checkValidationByRepeatPassword" />", { "password" : $("#password").val(), "repeatPassword" : $("#repeatPassword").val() }, function(data) {
				if($("#repeatPassword").val()=="") {
					$("#messageByRepeatPassword").text("");
					return;
				}
				
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageByRepeatPassword").text("일치합니다.").css("color", "green");
					isCheckedPassword = true;
				} else if (data == "NO") {
					$("#messageByRepeatPassword").text("비밀번호가 일치하지 않습니다.").css("color", "red");
					isCheckedPassword = false;
				}
			});
		});
		
		$("#repeatPassword").focus(function () {
			$("#messageByRepeatPassword").text("");
		});
		
		$("#email").blur ( function () {
			if($("#email").val()=="") {
				$("#messageByEmail").text("");
				return;
			}
			$.post("<c:url value="/checkValidationByEmail" />", { "email" : $("#email").val() }, function(data) {
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageByEmail").text("사용할 수 있는 이메일 입니다.").css("color", "green");
					isCheckedEmail = true;
				}  else if (data == "NO") {
					$("#messageByEmail").text("올바른 이메일을 입력하세요!").css("color", "red");
					isCheckedEmail = false;
				}
				else if (data == "EXIST") {
					$("#messageByEmail").text("이미 사용중이거나 탈퇴한 회원의 이메일입니다!").css("color", "red");
					isCheckedEmail = false;
				}
			});
		});
		
		$("#email").focus(function () {
			$("#messageByEmail").text("");
		});
		
		$("#phoneNumber").blur(function () {
			$.post("<c:url value="/checkValidationByPhoneNumber" />", { "phoneNumber" : $("#phoneNumber").val() }, function(data) {
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageByPhoneNumber").text("사용할 수 있는 전화번호 입니다.").css("color", "green");
					isCheckedPhoneNumber = true;
				} else if (data == "NO") {
					$("#messageByPhoneNumber").text("정확한 전화번호를 입력하세요!").css("color", "red");
					isCheckedPhoneNumber = false;
				}
			});
		});
		
		$("#phoneNumber").focus(function () {
			$("#messageByPhoneNumber").text("");
		});
		
		$("#registerButton").click( function () {
			
			var isCheckedMemberType = $('input:radio[name=memberType]').is(':checked');
			if ( isCheckedMemberType == false) {
				alert("회원권한을 선택해주세요.")
				return;
			}
			
			var selectMemberType = $(':radio[name="memberType"]:checked').val();
			
			if ( selectMemberType == "MBR" || selectMemberType == "STD") {
				
				var universityName = $("#universityName").val();
				if (universityName == "") {
					alert("학교를 입력하세요.");
					$("#messageByUniversityName").html("학교를 입력하세요.");
					return;
				}
				
				var majorName = $("#majorName").val();
				if (majorName == "") {
					alert("학과를 입력하세요.");
					$("#messageByMajorName").html("학과를 입력하세요.");
					return;
				}
				
				var graduationType = $('input:radio[name=graduationType]').is(':checked');
				if ( graduationType == false) {
					alert("졸업구분을 선택하세요");
					return;
				}
				
				var highestEducationLevel = $('input:radio[name=highestEducationLevel]').is(':checked');
				if ( highestEducationLevel == false ) {
					alert("최종학력을 선택하세요.");
					return;
				}
			}
			
			if(isCheckedId == false) {
				alert("아이디를 확인해주세요.");
				return;
			}
			
			if(isCheckedEmail == false) {
				alert("이메일을 확인해주세요.");
				return;
			}
			
			if(isValidPassword == false) {
				alert("비밀번호를 확인해주세요.");
				return;
			}
			
			if(isCheckedPassword == false) {
				alert("비밀번호 확인을 확인해주세요.");
				return;
			}
			
			if(isCheckedPhoneNumber == false) {
				alert("전화번호를 확인을 확인해주세요.");
				return;
			}
			
			var form = $("#registerForm");
			form.attr("action", "<c:url value="/doRegisterAction" />");
			form.submit();
		});
		
		$('#years, #months, #days').change(function () {
			$("#birthDate").val();
			
			var target = document.getElementById("years");
			var date = "";
			var year = target.options[target.selectedIndex].text;
			date += year + "-";
			
			target = document.getElementById("months");
			var month = target.options[target.selectedIndex].text;
			if ( month.length == 1) {
				month = "0" + month;
			}
			date += month + "-";
			
			target = document.getElementById("days");
			var day = target.options[target.selectedIndex].text;
			if ( day.length == 1) {
				day = "0" + day;
			}
			date += day;
			
			$("#birthDate").val(date);
		});
		
		if( $(".memberType").val() == 'MBR' || $(".memberType").val() == 'STD') {
			$("#studentForm").show();
		}
		
		$('input[type=radio][name=memberType]').change(function() {
			
			if( this.value == 'MBR' || this.value == 'STD' ) {
				$("#studentForm").show();	
			}
			else {
				$("#studentForm").hide();
				$("input:radio[name='highestEducationLevel']").prop("checked", false);
				$("input:radio[name='graduationType']").prop("checked", false);
				$("#universityName").val("");
				$("#majorName").val("");
			}
		
			
		});
		
		
		
	});
</script>

<script type="text/javascript">
	$(function () {
		for (i = new Date().getFullYear() ; i > 1900; i--) {
			$('#years').append($('<option />').val(i).html(i));
		}

		for (i = 1; i < 13; i++) {
			$('#months').append($('<option />').val(i).html(i));
		}
		updateNumberOfDays();
		
		var birthDate;
		<c:if test="${member.birthDate ne null}">
			birthDate = "${member.birthDate}";
		</c:if>
		var yyyy;
		var mm;
		var dd;
		
		if (birthDate != null && birthDate != "" ) {
			var splitBirthDate = birthDate.split("-");
			yyyy = splitBirthDate[0];
			mm = splitBirthDate[1];
			dd = splitBirthDate[2];
			
			try{
				yyyy = parseInt(yyyy);
				mm = parseInt(mm);
				dd = parseInt(dd);
			} catch (NumberFormatException) {
				yyyy = 2016;
				mm = 1;
				dd = 1;
			}
		}
		else {
			yyyy = 2016;
			mm = 1;
			dd = 1;
		}
		
		$("#years").val(yyyy).attr("selected", "selected");
		$("#months").val(mm).attr("selected", "selected");
		$("#days").val(dd).attr("selected", "selected");
		
		if ( mm < 10 ) {
			mm = "0" + mm;
		}
		
		if ( dd < 10 ) {
			dd = "0" + dd;
		}
		
		$("#birthDate").val(yyyy + "-" + mm + "-" + dd);
		
		$('#years, #months').change(function () {
			updateNumberOfDays();
		});
	});

	function updateNumberOfDays() {
		$('#days').html('');
		month = $('#months').val();
		year = $('#years').val();
		days = daysInMonth(month, year);

		for (i = 1; i < days + 1 ; i++) {
			$('#days').append($('<option />').val(i).html(i));
		}
	}

	function daysInMonth(month, year) {
		return new Date(year, month, 0).getDate();
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form id="registerForm" commandName="member" method="post">
		아이디 : <input type="text" id="id" name="id" value="${ member.id }" tabindex="1" maxlength="20"/>
		<br/><span id="messageById"></span>
		<form:errors path="id" /><br/>
		
		비밀번호 : <input type="password" id="password" name="password" value="${ member.password }" tabindex="2" maxlength="16"/>
		<br/><span id="messageByPassword"></span>
		<form:errors path="password" /><br/>
		
		비밀번호 재확인: <input type="password" id="repeatPassword" tabindex="3" maxlength="16"/>
		<br/><span id="messageByRepeatPassword"></span><br/>
		
		이름 : <input type="text" id="name" name="name" value="${ member.name }" tabindex="4" maxlength="10"/>
		<br/><form:errors path="name" /><br/>
		
		이메일 : <input type="email" id="email" name="email" value="${ member.email }" tabindex="5" maxlength="30"/>
		<br/><span id="messageByEmail"></span>
		<form:errors path="email"/><br/>
		
		생년월일 : 
		<select id="years" name="years" tabindex="6"></select>&nbsp;년
		<select id="months" name="months" tabindex="7"></select>&nbsp;월
		<select id="days" name="days" tabindex="8"></select>&nbsp;일
		<input type="hidden" id="birthDate" name="birthDate" value="${ member.birthDate }" maxlength="12"/>
		<br/><form:errors path="birthDate" /><br/>
		
		전화 번호 : <input type="text" id="phoneNumber" name="phoneNumber" value="${ member.phoneNumber }" placeholder="000-0000-0000형식으로 입력해주새요." tabindex="20" maxlength="13"/>
		<br/><span id="messageByPhoneNumber"></span>
		<form:errors path="phoneNumber" /><br/>
		
		회원권한 : 
		<c:forEach items="${memberTypeList}" var="memberType">
			<input type="radio" class="memberType" name="memberType" value="${memberType.cdId}"/>${memberType.cdNm}
		</c:forEach>
		
		<br/>
		<br/>
		
		<div id="studentForm">
		학교 : <input type="text" id="universityName" name="universityName" value="${ member.universityName }" maxlength="20" />
		<br/>
			<c:if test="${isEmptyUniversityName ne null}">
				<span id="messageByUniversityName">학교를 입력하세요!</span>
			</c:if>
		<br/>
		
		학과 : <input type="text" id="majorName" name="majorName" value="${ member.majorName }" maxlength="20"/>
		<br/>
			<c:if test="${isEmptyMajorName ne null}">
				<span id="messageByMajorName" style="color: red;">학과를 입력하세요!</span>
			</c:if>
		<br/>
		
		졸업구분 : 
		<c:forEach items="${graduationTypeList}" var="graduationType">
			<input type="radio" class="graduationType" name="graduationType" value="${graduationType.cdId}"/>${graduationType.cdNm}
		</c:forEach>
		<br/>
			<c:if test="${isEmptyGraduationType ne null}">
				<span id="messageByMajorName" style="color: red;">졸업구분을 선택하세요!</span>
			</c:if>
		<br/>
		
		최종학력 : 
		<c:forEach items="${highestEducationLevelList}" var="helCodeName">
			<input type="radio" class="highestEducationLevel" name="highestEducationLevel" value="${helCodeName.cdId}"/>${helCodeName.cdNm}
		</c:forEach>
		<br/>
			<c:if test="${isEmptyHighestEducationLevel ne null}">
				<span id="messageByMajorName" style="color: red;">최종학력을 선택하세요!</span>
			</c:if>
		<br/>
		</div>

		<input id="registerButton" class="inputButton" type="button" value="가입"/>
	</form:form>

</body>
</html>