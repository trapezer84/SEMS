<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>코드 설정 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#mainBtn").click(function(){
			location.href="<c:url value="/main"/>";
		});
		
		$("#cancleBtn").click(function (){
			$("#cdId").val("");
			$("#cdNm").val("");
			$("#cdTp").val("");
			$("#cdTp2").val("");
		});
		
		//수정버튼 클릭시
		$(".codeMngModifyBtn").click(function (){
			var cdId =  $(this).parent().parent().children(":eq(0)");
			var cdNm = $(this).parent().parent().children(":eq(2)").children();
			var cdTp =  $(this).parent().parent().children(":eq(3)");
			var cdTp2 = $(this).parent().parent().children(":eq(5)").children();
			
			/* cdNm */
			if(cdNm.val()==""){
				alert("CODE NAME을 써주세요.");
				return;
			}
			
			var newRegExp = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9-_]/gi;
			var engregexp = /[a-zA-Z0-9-_]/gi;
			var noengregexp = /[^a-zA-Z0-9-_]/gi;

			var v = cdNm.val();
			if (newRegExp.test(v)) {
				alert("코드 타이틀에 특수문자를 포함할 수 없습니다.");
				return;
			}
			
			var tmpStr = cdNm.val();
			var tmpStr2 = cdNm.val();
			tmpStr = tmpStr.replace(engregexp, '');
			tmpStr2 = tmpStr2.replace(noengregexp, '');
			if ( (tmpStr.length * 3) + tmpStr2.length > 6 ) {
				alert("코드 타이틀은 6글자 이하로 써주세요.");
				return;
			}
			
			/* cdTp2 */
			if(cdTp2.val()==""){
				alert("CODE 세부 카테고리를 써주세요.");
				return;
			}
			
			if (newRegExp.test(cdTp2.val())) {
				alert("코드 세부 카테고리에 특수문자를 포함할 수 없습니다.");
				return;
			}
			
			if ( confirm("입력한 내용으로 수정하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doCodeMngModify"/>", {
					"cdId" : cdId.val(),
					"cdNm" : cdNm.val(),
					"cdTp" : cdTp.val(),
					"cdTp2" : cdTp2.val()
				}, function(data) {
					
					if(data == 'OK') {
						alert("수정되었습니다.");
						location.href="<c:url value='/codeMngPage' />";
					}
				});
			}
			else {
				return;
			}
		});
		
		
		//삭제버튼 클릭시
		$(".codeMngDeleteBtn").click(function (){
			
			var root = $(this).parent().parent().children(":eq(0)");
			
			if ( confirm("삭제하시겠습니까?") == true ) {
				location.href = "/comm/doCodeMngDelete/"+ root.val();
			}
			else {
				return;
			} 
		});
		
		
		$(".onlyText").keyup(function(event) {
			var regexp = /[\+*^!@\#$%<>&\()\=\’ \\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;
			var newRegExp = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9-_]/gi;
			var engregexp = /[a-zA-Z0-9-_]/gi;
			var noengregexp = /[^a-zA-Z0-9-_]/gi;

			v = $(this).val();
			if (newRegExp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
			
			var tmpStr = $(this).val();
			var tmpStr2 = $(this).val();
			tmpStr = tmpStr.replace(engregexp, '');
			tmpStr2 = tmpStr2.replace(noengregexp, '');
			if ( tmpStr.length + tmpStr2.length > 6 ) {
				alert("글자 수가 너무 큽니다. 최대 6글자 입력가능합니다.");
				while(v.length > 0){
					v = v.substring(0, v.length - 1);
					tmpStr = v;
					tmpStr2 = v;
					tmpStr = tmpStr.replace(engregexp, '');
					tmpStr2 = tmpStr2.replace(noengregexp, '');
					if ( tmpStr.length + tmpStr2.length <= 6 ) {
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
		});
		
		$(".insertEight").keyup(function(event){
			v = $(this).val();
			if ( v.length > 8 ) {
				alert("글자수가 8을 넘을 수 없습니다.");
				$(this).val(v.substring(0, 8));
			}
		});
		$(".insertFour").keyup(function(event){
			v = $(this).val();
			if ( v.length > 4 ) {
				alert("글자수가 4를 넘을 수 없습니다.");
				$(this).val(v.substring(0, 4));
			}
		});
		
		
		//추가버튼 클릭시
		$("#codeMngInsertBtn").click(function (){
			var cdId =  $("#cdId").val();
			var cdNm = $("#cdNm").val();
			var cdTp = $("#cdTp").val();
			var cdTp2 = $("#cdTp2").val();
			
			if(cdId==""){
				alert("코드 아이디를 써주세요.");
				return;
			}
			if(cdNm==""){
				alert("코드 타이틀을 써주세요.");
				return;
			}
			
			/* tp */
			if(cdTp==""){
				alert("코드 카테고리를 써주세요.");
				return;
			}
			
			/* tp2 */
			if(cdTp2==""){
				alert("코드 세부 카테고리를 써주세요.");
				return;
			}
			
			if ( confirm("등록하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doCodeMngInsert"/>", {
					"cdId" : cdId,
					"cdNm" : cdNm,
					"cdTp" : cdTp,
					"cdTp2" : cdTp2
				}, function(data) {
					location.href="<c:url value="/codeMngPage"/>";
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
		<th>코드 아이디</th>
		<th>코드 타이틀</th>
		<th>코드 카테고리</th>
		<th>코드 세부 카테고리</th>
		
		<c:forEach items="${codeMngList}" var="codeMng">
			<tr>
				<input type="hidden" id="${codeMng.cdId}" name="cdId" value="${codeMng.cdId}"/>
				<td style="text-align:center">${codeMng.cdId}</td>
				<td>
					<input class="onlyText" style="text-align:center" type="text" id="${codeMng.cdNm}" name="cdNm" value="${codeMng.cdNm}"/>
				</td>
				<input type="hidden" id="${codeMng.cdTp}" name="cdTp" value="${codeMng.cdTp}"/>
				<td style="text-align:center">${codeMng.cdTp}</td>
				<td>
					<input style="text-align:center" type="text" id="${codeMng.cdTp2}" name="cdTp2" value="${codeMng.cdTp2}"/>
				</td>
				<td><input type="button" class="codeMngModifyBtn" value="수정"/></td>
				<td><input type="button" class="codeMngDeleteBtn" value="삭제"/></td>
			</tr>
		</c:forEach>
	
		<tr>
			<td>
				<input type="text" id="cdId" class="onlyEngText insertEight" name="newCdId" value=""/>
			</td>
			<td>
				<input type="text" id="cdNm" class="onlyText" name="newCdNm" value=""/>
			</td>
			<td>
				<input type="text" id="cdTp" class="onlyEngText insertFour" name="newCdTp" value=""/>
			</td>
			<td>
				<input type="text" id="cdTp2" class="onlyEngText insertEight" name="newCdTp2" value=""/>
			</td>
			<td><input type="button" id="codeMngInsertBtn" value="추가" /></td>
			<td><input type="button" id="cancleBtn" value="취소" /></td>
		</tr>
		<br/>
	</table>
		
		<input type="button" id="mainBtn" value="처음으로"/>
	
	
	
</body>
</html>