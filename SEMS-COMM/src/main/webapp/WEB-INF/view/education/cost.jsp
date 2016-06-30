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

	$(document).ready(function () {
		
		var message = $("#message").val();
		if ( message == "FAIL" ) {
			alert("삭제 실패 했습니다.");
		}
		
		$(".modifyEduCostBtn").click(function () {
			
			var root = $(this).parent().parent().children(":eq(1)").children();
			
			if (root.val() == "") {
				alert("수정할 이름을 입력하세요.");
				location.href = "<c:url value="/education/cost"/>";
				return;
			}
			
			if ( root.val().length > 5) {
				alert("입력 범위를 초과했습니다. 5자 이하로 입력하세요.");
				return;
			}
			
			
			if ( confirm("입력한 내용으로 수정하시겠습니까?") == true ) {
				
				$.post(
						"<c:url value="/education/modifyEduCost"/>"
						, {
							"cdNm" : root.val()
							, "cdId" : $(this).attr("id")
						}
						, function(data) {
							if(data == "OK") {
								alert("수정되었습니다.");
							}
							else if(data == "FAIL_V"){
								alert("중복된 데이터로 수정할 수 없습니다.");
							}
							else if(data == "FAIL_M"){
								alert("수정 중 오류 발생했습니다. 잠시 후 다시 시도해 주세요.");
							}
							else if(data == "NO_DATA") {
								alert("수정할 내용이 모두 입력되지 않았습니다. 입력 후 다시 시도해 주세요.");
							}
							else if(data == "DATAL_F") {
								alert("입력한 데이터의 길이가 알맞지 않습니다.");
							}
							location.href = "<c:url value="/education/cost"/>";
						}
				)
			}
		});
		
		$(".deleteEduCostBtn").click( function () {
			var root = $(this).parent().parent().children(":eq(0)").children();
			var cdId = root.val();
			
			if ( confirm("해당 교육 비용 코드를 삭제 하시겠습니까?") == true ) {
				location.href = "<c:url value="/education/deleteEduCost/"/>"+cdId;
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
		
		$("#insertEduBtn").click( function () {
			
			if ($("#newCode").val().length != 4) {
				alert("4자리 코드를 입력하세요.");
				return;
			}
			
			if ( $("#newCost").val() == "" || $("#newCost").val().length >5) {
				alert("1자 이상 5자 이하로 입력하세요.");
				return;
			}
			
			if ( confirm("해당 교육 비용 코드를 추가 하시겠습니까?") == true ) {
				$.post(
						"<c:url value="/education/insertEduCost"/>"
						, {
							"cdNm" : $("#newCost").val()
							, "cdId" : $("#newCode").val()
						}
						, function(data) {
							if(data == "OK") {
								alert("등록되었습니다.");
							}
							else if(data == "FAIL_V"){
								alert("중복된 데이터를 넣을 수 없습니다.");
							}
							else if(data == "FAIL_I"){
								alert("등록 중 오류 발생했습니다. 잠시 후 다시 시도해 주세요.");
							}
							else if(data == "NO_DATA") {
								alert("코드 또는 교육 비용이 입력되지 않았습니다. 모두 입력 후 다시 시도해 주세요.");
							}
							else if(data == "DATAL_F") {
								alert("입력한 데이터의 길이가 알맞지 않습니다.");
							}
							location.href = "<c:url value="/education/cost"/>";
						}
				)
			}
		
		});
		
	});
</script>

</head>
<body>

<div>
	<input type="hidden" id="message" name="message" value="${message}" />
	<table>
		<c:forEach items="${costList}" var="cost">
			<tr>
				<td><input type="hidden" id="cdNm${cost.cdId}" name="cdNm" value="${cost.cdId}" />${cost.cdId}</td>
				<td><input type="text" class="cdNm onlyText noPaste" id="cdNm${cost.cdId}" name="cdNm" value="${cost.cdNm}" /></td>
				<td><span class="modifyEduCostBtn" id="${cost.cdId}">수정</span></td>
				<td><span class="deleteEduCostBtn" id="deleteEduCostBtn${cost.cdId}">삭제</span></td>
			</tr>
		</c:forEach>
			<tr>
				<td>코드 : <input type="text" id="newCode" class="onlyEngText noPaste" name="newCode" /></td>
				<td>비용 : <input type="text" id="newCost" class="onlyText noPaste" name="newCost" /></td>
				<td colspan="2"><span id="insertEduBtn">추가</span></td>
			</tr>
	</table>
</div>

</body>
</html>