<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.inputButton {
	border:none;
	border-radius:5px;
	padding:6px 12px;
	font-weight:bold;
	text-transform:uppercase;
	color:#FFFFFF;
	background-color:#E05149;
	cursor: pointer;
}
</style>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function () {

		$("#massiveInsertBtn").click(function() {
			
			var isCheckedId = false;
			if($("#teamName").val() == ""){
				alert('팀 명을 입력하세요.');
				return;
			}
			
			if($("#teamName").val != ''){
				isCheckedId = true;
			}
			
			var isChecked = false;
			$(".selectMemberId").each(function (index, data) {
				if(data.checked){
					isChecked = data.checked;
				}
			});
			
			if(!isChecked) {
				alert("등록할 대상이 없습니다.")
				return;
			}

			if (confirm("정말 등록하시겠습니까?")) {
				var form = $("#registerForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/buildTeam" />");
				form.submit();
			}

		});

	});
</script>
<title>Insert title here</title>
</head>
<body>

	<form name="registerForm" id="registerForm">
		팀명 : <input type="text" id="teamName" name="teamName" value="${ team.teamId }" tabindex="1" maxlength="20"/>
		<br/><span id="messageById"></span>
		<form:errors path="teamName" />
		<br/>
		팀원 : 
		<c:if test="${memberListVO.memberList.size() eq 0}">
			등록할수있는 멤버가 없습니다.
		</c:if>
		<c:if test="${memberListVO.memberList.size() gt 0}">
		<c:forEach items="${ memberListVO.memberList }" var="member">
			<tr>
				<td>
					<input class="selectMemberId" name="selectMemberId" value="${member.id}" type="checkbox"/>
				</td>
				<td>
				</td>
				<td>${member.id}</td>		
			</tr>
		</c:forEach>
		</c:if>
		
		<br/>
		<br/>
		<input type="hidden" value="${educationId}" name="educationId" />
		<input id="massiveInsertBtn" class="inputButton" type="button" value="가입"/>
	</form>
	
</body>
</html>