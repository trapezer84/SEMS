<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>

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
		
		$("#prevPassword").focus();
		
		$("#password").blur(function () {
			
			var prevPassword = document.getElementById("prevPassword");
			var password = document.getElementById("password");
			
			if ( $("#prevPassword").val() != "") {

				if ( prevPassword.value != password.value ) {
				
					$.post("/checkValidationByPassword", { "password" : $("#password").val() }, function(data) {
						if($("#password").val() =="") {
							$("#messageByPassword").text("");
							return;
						}
						
						if (!data) {
							alert("통신 실패");
						} else if (data == "OK") {
							$("#messageByPassword").text("안전한 비밀번호 입니다.").css("color", "green");
						} else if (data == "NO") {
							$("#messageByPassword").text("영문, 숫자, 특수문자 조합의 10~16 글자이어야 합니다!").css("color", "red");
						}
					});
				
					$("#repeatPassword").val("");
						
				}
				else {
					alert("현재 비밀번호와 새 비밀번호가 일치합니다");
					
					$("#password").val("");
					$("#password").focus();
				}
			}
			
			
		});
		
		$("#password").focus(function () {
			$("#messageByPassword").text("");
		});
		
		$("#repeatPassword").blur(function () {
			$.post("<c:url value='/checkValidationByRepeatPassword' />", { "password" : $("#password").val(), "repeatPassword" : $("#repeatPassword").val() }, function(data) {
				if($("#repeatPassword").val()=="") {
					$("#messageByRepeatPassword").text("");
					return;
				}
				
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageByRepeatPassword").text("일치합니다.").css("color", "green");
				} else if (data == "NO") {
					$("#messageByRepeatPassword").text("비밀번호가 일치하지 않습니다.").css("color", "red");
				}
			});
		});
		
		$("#repeatPassword").focus(function () {
			$("#messageByRepeatPassword").text("");
		});
		
		$("#changePassword").click(function() {
			
			$.post("<c:url value='/checkPassword' />",{ "id" : $("#id").val(), "prevPassword" : $("#prevPassword").val() }, function(data) {
				
				if (!data) {
					alert("통신 실패");
				} else if (data == "NO") {
					alert("현재 비밀번호가 일치하지 않습니다");
					
					return;
				}
				
			});
			
			var form = $("#changePasswordForm");
			form.attr("action", "<c:url value='/doChangePasswordAction'/>");
			
			form.submit();
		});
		
		
		
	});
</script>

<form:form id="changePasswordForm" commandName="memberVO" method="post" >
		
		<input type="hidden" id="id" name="id" value="${id}" />
		<h3>비밀번호 변경하지 않을 시 로그인 하지 못합니다</h3>
		
		현재 비밀번호 : <input type="password" id="prevPassword" name="prevPassword" tabindex="2" />
		<br/><br/>
		
		새 비밀번호 : <input type="password" id="password" name="password" tabindex="2" />
		<br/><span id="messageByPassword"></span><br/>
		
		비밀번호 재확인: <input type="password" id="repeatPassword" tabindex="3" />
		<br/><span id="messageByRepeatPassword"></span><br/>
		
		<input id="changePassword" class="changePassword" type="button" value="비밀번호 변경"/>	
</form:form>
