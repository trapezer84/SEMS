<!-- 이기연 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		var isCheckedReportedCategory = false;
		var isCheckedReportedComment = false;

		$("#reportPcBtn").click(function() {
 		 	if( $("#reportedCategory").val() == null || $("#reportedCategory").val() == "default") {
				alert("고장난 항목을 고르세요.")
				$("#reportedCategory").focus();
				return;
			}
				
			if( $("#reportedComment").val() == "") {
				alert("내용을 입력하세요.")
				$("#reportedComment").focus();
				return;
			}
			
			$.post("<c:url value='/myPc/reportPage/report'/>", $("#reportPc").serialize(), function(data) {
				if(data == "OK") {
					alert("신고가 접수되었습니다.");
					window.open("about:blank", "_self").close();
					//location.href="<c:url value='/main'/>";
				} else if (data == "NO") {
					alert("신고 접수를 실패했습니다. 다시 시도해주세요.");
				} 
			});
		});
		
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>PC ${pcId}번 고장 신고</h1>

<form id="reportPc" name="reportPc">
<table border="1">
	<tr>
		<th>고장난 항목</th>
		<th>고장이 난 부분(상세기록)</th>
	</tr>
	
	<tr>
		<td>
			<select name="reportedCategory" id="reportedCategory">
				<option value="default">선택</option>
				<option value="computer">컴퓨터</option>
				<option value="keyboard">키보드</option>
				<option value="mouse">마우스</option>
				<option value="desk">책상</option>
				<option value="ect">기타</option>
			</select>
		</td>
		<td>
			<textarea name="reportedComment" id="reportedComment" rows="20" cols="50"></textarea>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="button" id="reportPcBtn" value="신고하기" /> 
			<input type="hidden" name="pcId" id="pcId" value="${pcId}" /> 
		</td>
	</tr>
</table>
</form>
</body>
</html>