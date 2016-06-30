<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register My Computer</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {		
		
		$("#educationTitle").click(function(event) {
			var educationId = $("#educationTitle").val();
			if( educationId!="empty" ){
				
				$.post("<c:url value="/getEduLocationById"/>", { 
						"educationId" : educationId
						}, function(data) {
							if (!data) {
								alert("인터넷 연결이 끊겼습니다.");
							} 
							else {
								$("#eduLocation").val(data);
								}
						});
			}
			
		});	
		
		$("#registerMyPcBtn").click(function() {
			
			var educationId =  $("#educationTitle").val();
			var educationTitle = $("#educationTitle").text();
			var eduLocation = $("#eduLocation").val();
			var usedPcIp = $("#usedPcIp").val();
			
			if ( confirm(educationTitle+" PC를 등록하시겠습니까?") == true ) {				
				$.post("<c:url value="/doRegisterMyPc"/>", {
					"educationId" : educationId,
					"eduLocation" : eduLocation,
					"usedPcIp" : usedPcIp
				}, function(data) {
					if(data == "OK") {
						alert("등록되었습니다.");
						location.href="<c:url value="/member/myPc"/>";
					}
					else if(data == "FAIL"){
						alert("등록되지 않은 IP입니다.");
						location.href="<c:url value="/member/myPc"/>";
					}
				});
			}
			else {
				return;
			}
			
		});
		$(".deleteMyPcBtn").click(function(){
			var myPcId = $(this).parent().children(":eq(0)");	
	
			if (confirm("삭제하시겠습니까?") == true) {
				location.href = "<c:url value="/doDeleteMyPc/"/>" + myPcId.val();
			} else {
				return;
			}
		});
		
		
	});
</script>

</head>
<body>
	<table border="1">
		<tr>
			<th>수강 중인 강의</th>
			<th>강의실</th>
			<th>내 PC IP</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${usedPcList}" var="usedPc">	
		<tr>
			<td>${usedPc.educationTitle}</td>
			<td>${usedPc.educationLocation}</td>
			<td>${usedPc.ip}</td>
			<td>
				<a 
				href="<c:url value='/myPc/reportPage/${usedPc.pcId}'/>"
				onclick="window.open(this.href, 'Place Detail','toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizeable=no, width=530, height=520');return false;"
					target="_blank">
					<input type="button" id="reportPC" value="신고"/>
				</a>
			</td>
			<td>
				<input type="hidden" class="myPcId" value="${usedPc.usedPc}">
				<input type="button" class="deleteMyPcBtn" value="삭제"/>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td>
				<select id="educationTitle" name="educationTitle" tabindex="1">	 
					<c:if test="${eduListByMember.isEmpty()}">
					  <option value="empty">강의없음</option>
					</c:if>
					<c:forEach items="${eduListByMember}" var="eduList">	
					  <option value="${eduList.educationId}">${eduList.educationTitle}</option>
					</c:forEach>				  
				</select>	
			</td>
			<td>
				<input type="text" id="eduLocation" name="eduLocation" value=""/>
			</td>
			<td>
				<input type="text" id="usedPcIp" value="${myPcIp}"/>
			</td>
			<td colspan="2">
			<c:if test="${!eduListByMember.isEmpty()}">
				<input type="button" id="registerMyPcBtn" value="등록"/>
			</c:if>
			</td>
		</tr>
	</table>
</body>
</html>