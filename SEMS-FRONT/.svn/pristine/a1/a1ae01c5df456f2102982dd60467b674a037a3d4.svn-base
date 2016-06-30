<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>

<style type="text/css">
#modifyBtn {
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

$(document).ready(function() {
	
	var checkError = 0;
	
	$("#phoneNumber").blur(function () {
		$.post("<c:url value="/checkValidationByPhoneNumber" />", { "phoneNumber" : $("#phoneNumber").val() }, function(data) {
			if (!data) {
				alert("통신 실패");
			} else if (data == "OK") {
				$("#messageByPhoneNumber").text("사용할 수 있는 전화번호 입니다.").css("color", "green");
				checkError = 0;
			} else if (data == "NO") {
				$("#messageByPhoneNumber").text("정확한 전화번호를 입력하세요!").css("color", "red");
				checkError += 1;
			}
		});
	});
	
	$("#phoneNumber").focus(function () {
		$("#messageByPhoneNumber").text("");
	});
	
	
	$("#email").blur( function () {
		if($("#email").val()=="") {
			$("#messageByEmail").text("");
			return;
		}
		$.post("<c:url value="/checkValidationByEmail" />", { "email" : $("#email").val() }, function(data) {
			if (!data) {
				alert("통신 실패");
			} else if (data == "OK") {
				$("#messageByEmail").text("사용할 수 있는 이메일 입니다.").css("color", "green");
				checkError = 0;
			}  else if (data == "NO") {
				$("#messageByEmail").text("올바른 이메일을 입력하세요!").css("color", "red");
				checkError += 1;
			}
			else if (data == "EXIST") {
				$("#messageByEmail").text("이미 사용중이거나 탈퇴한 회원입니다!").css("color", "red");
				checkError += 1;
			}
		});
	});
	
	$("#name").keyup(function(event) {
         regexp = /[\+*^!@\#$%<>&\()\=\’ \\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;

         v = $(this).val();
         if (regexp.test(v)) {
            alert("특수문자를 포함할 수 없습니다.");
            $(this).val(v.replace(regexp, ''));
         }
         
      });
	
	$("#email").focus(function () {
		$("#messageByEmail").text("");
	});	
	
	$("#password").keyup(function () {
		$.post("<c:url value="/checkValidationByPassword" />", { "password" : $("#password").val() }, function(data) {
			if($("#password").val()=="") {
				$("#messageByPassword").text("");
				return;
			}
			
			if (!data) {
				alert("통신 실패");
			} else if (data == "OK") {
				$("#messageByPassword").text("안전한 비밀번호 입니다.").css("color", "green");
				checkError = 0;
			} else if (data == "NO") {
				$("#messageByPassword").text("영문, 숫자, 특수문자 조합의 10~16 글자이어야 합니다!").css("color", "red");
				checkError += 1;
			}
		});
	});
	
	
	
	$("#password").focus(function () {
		$("#messageByPassword").text("");
	});	
	
	
	$("#modifyBtn").click(function(){
		
		if ( $("#name").val() == "") {
			alert("이름을 입력하세요.")
			$("#name").focus();
			return;
		}
		
		if( checkError != 0 ) { 
			alert("항목을 수정하세요.");
			return;
		}
		
		var form = $("#memberInfoForm");
		form.attr("action", "<c:url value="/member/myPage/doModifyAction" />");
		form.submit();
		alert("수정이 완료되었습니다.");
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
   
   /* 기존 생년월일을 짤라서 select box 에 표시 */
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
</head>
<body>

<form:form id="memberInfoForm" commandName="member" method="post">

	아이디 : ${member.id}  <br/>
	<br />
		
	비밀번호 : <input type="password" name="password" id="password" tabindex="1" maxlength="16"/>
	<br /><span id="messageByPassword"></span>
	<form:errors path="password"></form:errors>
	<br />
	
	이름 : <input type="text" name="name" id="name" value="${member.name}" placeholder="이름을 입력하세요." tabindex="1" maxlength="10"/>
	<br /><span id="messageByName"></span>
	<form:errors path="name" /><br/>
	<br />
		
	이메일 : <input type="text" name="email" id="email"  value="${member.email}" placeholder="이메일을 입력하세요. " tabindex="2"  maxlength="30"/> 
	<br /><span id="messageByEmail"></span>
	<form:errors path="email" />
	<br />
	
	<c:if test="${selectedMemberTypeCodeId eq 'STD' || selectedMemberTypeCodeId eq 'MBR'}">
	대학교 : ${member.universityName}  <br />	
	<br />
	전공 : ${member.majorName} <br /> 	
	<br />
	</c:if>
	생년월일 : 
	<select id="years" name="years" tabindex="5"></select>&nbsp;년
	<select id="months" name="months" tabindex="6"></select>&nbsp;월
    <select id="days" name="days" tabindex="7"></select>&nbsp;일
	<input type="hidden" id="birthDate" name="birthDate" value="${ member.birthDate }" />
	<br />
	<br />
	전화번호 : <input type="text" name="phoneNumber" id="phoneNumber"  value="${member.phoneNumber}" placeholder="전화번호를 입력하세요." tabindex="8" /> 
	<br /><span id="messageByPhoneNumber"></span>
	<form:errors path="phoneNumber"></form:errors>
	<br />
	
	회원구분 : ${selectedMemberTypeCodeName} <br />
	<br />
	<c:if test="${selectedMemberTypeCodeId eq 'STD' || selectedMemberTypeCodeId eq 'MBR'}">
	졸업구분 : 
	<c:forEach items="${graduationTypeList}" var="graduationType">
				<c:if test="${graduationType.cdId eq selectedGraduationTypeCodeId}">
				<input type="radio" class="graduationType" name="graduationType" value="${graduationType.cdId}" checked="checked"/>${graduationType.cdNm}
				</c:if>
				<c:if test="${graduationTypeCodeName ne selectedGraduationTypeCodeId}">
				<input type="radio" class="graduationType" name="graduationType" value="${graduationType.cdId}"/>${graduationType.cdNm}
				</c:if>
	</c:forEach>
	<br/><br/>
	최종학력 : 
	<c:forEach items="${highestEducationLevelList}" var="helCodeName">
				<c:if test="${helCodeName.cdId eq selectedHighestEducationLevelCodeId}">
				<input type="radio" class="helCodeName" name="helCodeName" value="${helCodeName.cdId}" checked="checked"/>${helCodeName.cdNm}
				</c:if>
				<c:if test="${helCodeName ne selectedHighestEducationLevelCodeId}">
				<input type="radio" class="helCodeName" name="helCodeName" value="${helCodeName.cdId}"/>${helCodeName.cdNm}
				</c:if>
	</c:forEach>
	</c:if>
	<input type="hidden" name="id" id="id" value="${member.id}"/> <br/><br/>
	<input type="hidden" name="memberType" id="memberType" value="${member.memberType}"/> 
	<input type="button" id="modifyBtn" value="수정 완료" />
	
</form:form>
</body>
</html>