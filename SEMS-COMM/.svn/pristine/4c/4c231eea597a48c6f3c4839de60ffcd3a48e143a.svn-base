<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>졸업 구분 코드 설정 페이지</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.12.1.js"/> "></script>
<script type="text/javascript">

	$(document).ready(function (){
		
		$("#mainBtn").click(function(){
			location.href="<c:url value="/"/>";
		});
		
		$("#cancleBtn").click(function (){
			$("#cdId").val("");
			$("#cdNm").val("");
			
		});
		
		//수정버튼 클릭시
		$(".grdtModifyBtn").click(function (){

			var cdId =  $(this).parent().parent().children(":eq(0)");
			var cdNm = $(this).parent().parent().children(":eq(2)").children();
			
			if(cdNm.val()==""){
				alert("CODE NAME을 써주세요.");
				return;
			}
			
			if(cdNm.val().length > 10){
				alert("CODE NAME은 10글자 이하로 써주세요.");
				return;
			}
			

			if ( confirm("입력한 내용으로 수정하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doGrdtModify"/>", {
					"cdId" : cdId.val(),
					"cdNm" : cdNm.val()
				}, function(data) {
					if(data == "OK") {
						alert("수정되었습니다.");
						location.href="<c:url value="/grdtPage"/>";
					}
					else if(data == "FAIL"){
						alert("중복된 데이터로 수정할 수 없습니다.");
						location.href="<c:url value="/grdtPage"/>";
					}
				});
			}
			else {
				return;
			}
		});
		
		
		//삭제버튼 클릭시
		$(".grdtDeleteBtn").click(function (){

			var cdId =  $(this).parent().parent().children(":eq(0)");
			
			if ( confirm("삭제하시겠습니까?") == true ) {
				location.href = "<c:url value="/doGrdtDelete/"/>"+ cdId.val();
			}
			else  {
				return;
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

		//추가버튼 클릭시
		$("#grdtInsertBtn").click(function (){
			var cdId =  $("#cdId").val();
			var cdNm = $("#cdNm").val();
			
			if(cdId==""){
				alert("CODE ID를 써주세요.");
				return;
			}
			if(cdId.length > 4){
				alert("CODE ID는 4글자 이하로 써주세요.");
				return;
			}
			
			if(cdNm==""){
				alert("CODE NAME을 써주세요.");
				return;
			}
			
			if(cdNm.length > 10){
				alert("CODE NAME은 10글자 이하로 써주세요.");
				return;
			}
			
			if ( confirm("등록하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doGrdtInsert"/>", {
					"cdId" : cdId,
					"cdNm" : cdNm
				}, function(data) {
					if(data == "OK") {
						alert("등록되었습니다.");
						location.href="<c:url value="/grdtPage"/>";
					}
					else if(data == "FAIL"){
						alert("중복된 데이터를 넣을 수 없습니다.");
						location.href="<c:url value="/grdtPage"/>";
					}
				});
			}
			else {
				return;
			}
		});
		

	});
	
</script>

</head>
<body>

	<table>
		<th>Code Id</th>
		<th>Code Name</th>
		
		<c:forEach items="${grtdTpList}" var="graduation">
			<tr>
				<input type="hidden" id="${graduation.cdId}" name="cdId" value="${graduation.cdId}"/>
				<td style="text-align:center">${graduation.cdId}</td>
				<td>
					<input style="text-align:center" type="text" id="${graduation.cdNm}" class="onlyText noPaste" name="cdNm" value="${graduation.cdNm}"/>
				</td>
				
				<td><input type="button" class="grdtModifyBtn" value="수정"/></td>
				<td><input type="button" class="grdtDeleteBtn" value="삭제"/></td>
			</tr>
		</c:forEach>
	
		<tr>
			<td>
				<input type="text" id="cdId" class="onlyEngText noPaste" name="newCdId" value=""/>
			</td>
			<td>
				<input type="text" id="cdNm" class="onlyText noPaste" name="newCdNm" value=""/>
			</td>
			<td><input type="button" id="grdtInsertBtn" value="추가" /></td>
			<td><input type="button" id="cancleBtn" value="취소" /></td>
		</tr>
		<br/>
	</table>
		
		<input type="button" id="mainBtn" value="처음으로"/>
	
	
	
</body>
</html>