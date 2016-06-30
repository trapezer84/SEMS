<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.12.1.js"/> "></script>
<script type="text/javascript">

	$(document).ready(function (){
		
		$(".deleteEduTimeBtn").click(function () {
			var root = $(this).parent().parent().children(":eq(0)").children();
			var cdId = root.val();
			
			if ( confirm("해당 교육 시간 코드를 삭제 하시겠습니까?") == true ) {
				location.href = "<c:url value="/education/deleteEduTime/"/>"+cdId;
			}
		});
		
		$(".modifyEduTimeBtn").click( function () {
			var root = $(this).parent().parent().children(":eq(1)").children();
			
			if (root.val()=="") {
				alert("수정할 교육 시간을 입력하세요.");
				location.href = "<c:url value="/education/time"/>";
				return;
			}
			
			if (root.val().length > 5) {
				alert("5자 이하의 교육 시간을 입력하세요.");
				location.href = "<c:url value="/education/time"/>";
				return;
			}
			
			if ( confirm("해당 데이터를 수정하시겠습니까?")==true ) {
				$.post(
					"<c:url value="/education/modifyEduTime"/>"
					, {
						"cdNm": root.val()
						, "cdId": $(this).attr("id")
					}
					, function (data) {
						if ( data == "OK") {
							alert("수정 되었습니다.");
						}
						else if ( data == "FAIL_V") {
							alert("중복된 데이터로 수정할 수 없습니다.");	
						}
						else if ( data == "FAIL_M") {
							alert("수정에 실패했습니다. 잠시 후 다시 시도해 주세요.");
						}
						location.href = "<c:url value="/education/time"/>";
					}
				)
			}
		});
						
		$(".onlyText").keyup(function(event) {
			var regexp = /[\+*^!@\#$%<>&\()\=\’ \\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;
			var engregexp = /[a-zA-Z0-9-_]/gi;
			var noengregexp = /[^a-zA-Z0-9-_]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
			
			var tmpStr = $(this).val();
			var tmpStr2 = $(this).val();
			tmpStr = tmpStr.replace(engregexp, '');
			tmpStr2 = tmpStr2.replace(noengregexp, '');
			if ( (tmpStr.length * 3) + tmpStr2.length > 10 ) {
				alert("글자 수가 너무 큽니다.");
				while(v.length > 0){
					v = v.substring(0, v.length - 1);
					tmpStr = v;
					tmpStr2 = v;
					tmpStr = tmpStr.replace(engregexp, '');
					tmpStr2 = tmpStr2.replace(noengregexp, '');
					if ( (tmpStr.length * 3) + tmpStr2.length <= 10 ) {
						break;
					}
				}
				$(this).val(v);
			} 
		});

		$(".onlyEngText").keyup(function(event) {
			regexp = /[\+*^!@\#$%<>&\()\=\’ \\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;
			engregexp = /[^a-zA-Z0-9-_]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
			
			if (engregexp.test(v)) {
				alert("숫자와 영문만 입력할 수 있습니다.");
				$(this).val(v.replace(engregexp, ''));
			}
			
			if ( v.length > 4 ) {
				alert("글자수가 4를 넘을 수 없습니다.");
				$(this).val(v.substring(0, 4));
			}
			
		});
		
		$(".noPaste").on('paste', function(e){
			e.preventDefault();
		});
		
		$("#insertEduBtn").click( function() {
			
			if ($("#newCode").val().length != 4) {
				alert("4자리 코드를 입력하세요.");
				return;
			}
			
			if ( $("#newTime").val() == "" || $("#newTime").val().length >5) {
				alert("1자 이상 5자 이하로 입력하세요.");
				return;
			}
			
			if ( confirm("해당 교육 비용 코드를 추가 하시겠습니까?") == true ) {
				$.post(
						"<c:url value="/education/insertEduTime"/>"
						, {
							"cdNm":$("#newTime").val()
							, "cdId":$("#newCode").val()
						}
						, function (data) {
							if ( data == "OK") {
								alert("추가 되었습니다.");
							}
							else if ( data == "FAIL_V") {
								alert("중복된 데이터를 추가할 수 없습니다.");	
							}
							else if ( data == "FAIL_I") {
								alert("데이터 추가에 실패했습니다. 잠시 후 다시 시도해 주세요.");
							}
							location.href = "<c:url value="/education/time"/>";
						}
				)
			}
			
		});
		
	});

</script>
</head>
<body>

<div>
	<table>
		<c:forEach items="${timeList}" var="time">
			<tr>
				<td><input type="hidden" id="cdId${time.cdId}" value="${time.cdId}"/>${time.cdId}</td>
				<td><input type="text" class="timeNm onlyText noPaste" id="timeNm${time.cdId}" value="${time.cdNm}"/></td>
				<td><span class="modifyEduTimeBtn" id="${time.cdId}">수정</span></td>
				<td><span class="deleteEduTimeBtn" id="deleteEduTimeBtn${time.cdId}">삭제</span></td>
			</tr>
		</c:forEach>
			<tr>
				<td>코드 : <input type="text" id="newCode" class="onlyEngText noPaste" name="newCode" /></td>
				<td>시간 : <input type="text" id="newTime" class="onlyText noPaste" name="newTime" /></td>
				<td colspan="2"><span id="insertEduBtn">추가</span></td>
			</tr>
	</table>
</div>

</body>
</html>