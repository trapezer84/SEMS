<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".resignButton").click(function() {
			if( $("#password").val() == "" ) {
				alert("Password를 입력하세요!");
				$("#password").focus();
				return;
			}
			
			$.post("<c:url value='/doResign'/>", $("#resignForm").serialize(), function(data) {
				if(data == "OK") {
					alert("아이디를 삭제합니다.");
					location.href="<c:url value='/logout'/>";
				} else if (data == "FAIL") {
					alert("회원탈퇴 시도하지마세요. 딴 사람이잖아요.");
					$("#password").focus();
				} else if (data == "NO") {
					alert("아이디 혹은 비밀번호를 확인해 주세요.");
					$("#password").focus();
				} 
			});
		});
		
		$(".login .loginButton, .login .registButton").keypress(function (e) {
            if (e.keyCode === 13) {
                $(this).click();
            }
        });
	});
</script>
<div class="resign">
	<div class="wrapper">
		탈퇴하시려면 비밀번호를 입력하세요. <br />
		<form id="resignForm" name="resignForm">
			ID : ${id}<br />
			<input type="hidden" name="resignCode" value="${resignCode}" />
			<input type="hidden" name="id" id="id" value="${id}" />
			<input type="password" name="password" id="password" placeholder="Password" />
			<span class="button resignButton" style="cursor: pointer;">탈퇴하기</span>
		</form>
	</div>
</div>
