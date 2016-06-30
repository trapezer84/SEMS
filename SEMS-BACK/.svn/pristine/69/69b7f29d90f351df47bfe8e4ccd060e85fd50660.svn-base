<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>

<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function () {
		
		$("#cooperationType").val("${cooperation.cooperationType}").attr("selected", "selected");
		
		
		$(".message").css("color", "red");
		
	
		$("#doModify").click(function() {
			var form = $("#registerForm");
			form.attr("action", "<c:url value="/doModifyCoo" />");
			form.submit();
		});		

		$("#cancel").click(function() {
			location.href="<c:url value="/cooList"/>";
		});
		
		
		$("#cooperationTitle").blur(function () {
			if($("#cooperationTitle").val()=="") {
				$("#messageByCooperationTitle").text("");
				return;
			}
			$.post("<c:url value="/isExistCooperationTitle" />", { "title" : $("#cooperationTitle").val() }, function(data) {
				if (!data) {
					alert("인터넷 연결이 끊겼습니다.");
				} else if (data == "OK") {
					$("#messageByCooperationTitle").text("OK").css("color", "green");
				} else if (data == "EXIST") {
					$("#messageByCooperationTitle").text("이미 등록된 이름입니다.").css("color", "red");
				}
			});
		});
					
		$("input").on('paste', function(e){
			e.preventDefault();
		});
		
	});
	
	function onlyText(element, limit) {
		var regexp = /[\+*^!\#$%<>&\()\=\’\\/\?,\:\;\''\""\{\}\[\]|\\~`]/gi;
		var engregexp = /[a-zA-Z0-9-_ ]/gi;
		var noengregexp = /[^a-zA-Z0-9-_ ]/gi;

		v = $(element).val();
		if (regexp.test(v)) {
			alert("특수문자를 포함할 수 없습니다.");
			$(element).val(v.replace(regexp, ''));
		}
		
		var tmpStr = $(element).val();
		var tmpStr2 = $(element).val();
		tmpStr = tmpStr.replace(engregexp, '');
		tmpStr2 = tmpStr2.replace(noengregexp, '');
		if ( (tmpStr.length * 3) + tmpStr2.length > limit ) {
			alert("글자 수가 너무 큽니다.");
			while(v.length > 0){
				v = v.substring(0, v.length - 1);
				tmpStr = v;
				tmpStr2 = v;
				tmpStr = tmpStr.replace(engregexp, '');
				tmpStr2 = tmpStr2.replace(noengregexp, '');
				if ( (tmpStr.length * 3) + tmpStr2.length <= limit ) {
					break;
				}
			}
			$(element).val(v);
		} 
	}
	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
협약사 수정페이지 입니다.<br/><br/>

<form:form id="registerForm" commandName="cooperationVO" method="post">

		<input type="hidden" id="cooperationId" name="cooperationId" value="${cooperation.cooperationId}">
		협약사명 : <input type="text" id="cooperationTitle" name="cooperationTitle" value="${cooperation.cooperationTitle}" tabindex="1" maxlength="50" onkeyup="onlyText(this, 50)"/>
		<span class="deleteMessageCooperationTitle" id="messageByCooperationTitle"></span>
		<form:errors class="message" path="cooperationTitle"/><br/><br/>
		
		소재지 : <input type="text" id="cooperationLocation" name="cooperationLocation" value="${cooperation.cooperationLocation}" tabindex="2" maxlength="100" onkeyup="onlyText(this, 100)"/>
		<form:errors class="message" path="cooperationLocation"/><br/><br/>
		
		사업자 번호 : <input type="text" id="cooperationNumber" name="cooperationNumber" value="${cooperation.cooperationNumber}" tabindex="3" maxlength="20" onkeyup="onlyText(this, 20)"/>
		<form:errors class="message" path="cooperationNumber"/><br/><br/>
		
		대표 명 : <input type="text" id="representativeName" name="representativeName" value="${cooperation.representativeName}" tabindex="4" maxlength="20" onkeyup="onlyText(this, 20)"/>
		<form:errors class="message" path="representativeName"/><br/><br/>
		
		담당자 전화번호 : <input type="text" id="managerPhoneNumber" name="managerPhoneNumber" value="${cooperation.managerPhoneNumber}" tabindex="5" maxlength="20" onkeyup="onlyText(this, 20)"/>
		<form:errors class="message" path="managerPhoneNumber"/><br/><br/>
		
		회사 전화번호 : <input type="text" id="cooperationPhoneNumber" name="cooperationPhoneNumber" value="${cooperation.cooperationPhoneNumber}" tabindex="6" maxlength="20" onkeyup="onlyText(this, 20)"/>
		<form:errors class="message" path="cooperationPhoneNumber"/><br/><br/>
		
		담당자 이메일 : <input type="text" id="managerEmail" name="managerEmail" value="${cooperation.managerEmail}" tabindex="7" maxlength="30" onkeyup="onlyText(this, 30)"/>
		<form:errors class="message" path="managerEmail"/><br/><br/>

		협력사/협약사 : <select id="cooperationType" name="cooperationType" tabindex="8">
					<c:forEach items="${cooTypeList}" var="cooType">
					  <option value="${cooType.codeId}" >${cooType.codeName}</option>
					</c:forEach>				  
					</select><br/><br/>
				

		<input type="button" id="doModify" value="수정"/>
		<input type="button" id="cancel" value="취소"/></br>
</form:form>

</body>
</html>