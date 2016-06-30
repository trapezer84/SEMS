<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

		$("#showMemberTypeDiv").hide();
		$("#searchType").hide();
		$("#searchKeyword").hide();
		$("#isRgsn").hide();
		$("#connLock").hide();
		$("#modLock").hide();
		
		var option = $("#optionSelected").val();
		$("#search option:selected").val(option);
		if (option == "id") {
			$("#search option:selected").text("아이디");
			$("#searchKeyword").show();
		}
		else if( option == "type"){
			$("#search option:selected").text("회원타입");
			$("#searchType").show();
		}
		else if( option == "connLockT"){
			$("#search option:selected").text("접속잠김여부");
			$("#connLock").show();
		}
		else if( option == "isRgsnT"){
			$("#search option:selected").text("탈퇴여부");
			$("#isRgsn").show();
		}
		else if( option == "modLockT"){
			$("#search option:selected").text("수정잠김여부");
			$("#modLock").show();
		}
		
		
		$("#massiveSelectCheckBox").click(function () {
			var isChecked = $(this).prop("checked");
			$(".deleteMemberId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".deleteMemberId").each(function (index, data) {
				if(data.checked){
					isChecked = data.checked;
				}
			});
			
			if(!isChecked) {
				alert("삭제할 대상을 선택하세요.")
				return;
			}
			
			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#memberListForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/doMassiveDeleteMember" />");
				form.submit();
			}
		});
		
		$("#changePasswordButton").click(function () {
			var checkCount = 0;
			var memberId = 0;
			var member;
			
			$(".deleteMemberId").each(function (index, data) {
				if(data.checked){
					checkCount = checkCount + 1;
					memberId = $(this).val();
					memberLoginLock = $(this).parent().parent().children(":eq(4)");
					memberModifyLock = $(this).parent().parent().children(":eq(6)");
				}
			});
			
			if ( checkCount > 1 ) {
				alert("하나만 선택하세요.");
			}
			else if (checkCount == 1) {
				if ( confirm("정말로 비밀번호를 변경하시겠습니까?") ) {
					$.post("<c:url value="/memberManage/sendAndChangePassword" />", { "memberId" : memberId }, function(data) {
						if (!data) {
							alert("인터넷 연결이 끊겼습니다.");
						} 
						else if (data == "NO") {
							alert("비밀번호 전송에 실패하였습니다.");
						}
						else if (data == "OK") {
							alert("비밀번호 전송에 성공하였습니다.");
							memberLoginLock.text("N");
							memberModifyLock.text("N");
						}
					});
				}
			}
			else {
				alert("비밀번호를 변경할 대상을 선택하세요.")
			}
		});
		
		$("#showMemberTypeButton").click(function () {
			var checkCount = 0;
			var memberId = 0;
			
			$(".deleteMemberId").each(function (index, data) {
				if(data.checked){
					checkCount = checkCount + 1;
					memberId = $(this).val();
				}
			});
			
			if ( checkCount > 0 ) {
				$("#showMemberTypeDiv").show();
			}
			else {
				alert("회원권한을 변경할 아이디를 체크하세요!")
			}
		});
		
		$("#modifyMemberTypeButton").on("click", function () {
			var form = $("#memberListForm");
			form.attr("action", "<c:url value="/memberManage/doModifyMemberTypeAction"/> ");
			form.attr("method", "POST");
			form.submit();
		});
		
		$("#searchBtn").click( function() {
			if( $("#search option:selected").val() == "id"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			else if( $("#search option:selected").val() == "type"){
				if ($("#searchType").val() == ""){
					alert("회원 종류를 선택하세요!");
					return;
				}
			}
			else if( $("#search option:selected").val() == "connLockT") {
				if ($("#connLock").val() == "") {
					alert("접속 잠김 여부를 선택하세요!");
					return;
				}
			}
			else if( $("#search option:selected").val() == "isRgsnT") {
				if ($("#isRgsn").val() == "") {
					alert("탈퇴 여부를 선택하세요!");
					return;
				}
			}
			else if( $("#search option:selected").val() == "modLockT") {
				if ($("#modLock").val() == "") {
					alert("수정 잠김 여부를 선택하세요!");
					return;
				}
			}
			else{
				alert("검색조건을 입력하세요.");
			}
			movePage('0');	
		});
		
		$("#searchInitBtn").click(function() {
			
			location.href="<c:url value='/member/memberListInit' />";
			
		});
		
		$("#search").change(function() {
			var option = $("#search option:selected").val();
			if (option == "id") {
				$("#optionSelected").val(option);
				$("#searchKeyword").show();
				$("#searchType").hide();
				$("#modLock").hide();
				$("#isRgsn").hide();
				$("#connLock").hide();
			}
			else if( option == "type"){
				$("#optionSelected").val(option);
				$("#searchType").show();
				$("#searchKeyword").hide();
				$("#modLock").hide();
				$("#isRgsn").hide();
				$("#connLock").hide();
			}
			else if( option == "connLockT"){
				$("#optionSelected").val(option);
				$("#connLock").show();
				$("#searchType").hide();
				$("#searchKeyword").hide();
				$("#modLock").hide();
				$("#isRgsn").hide();
			}
			else if( option == "isRgsnT"){
				$("#optionSelected").val(option);
				$("#isRgsn").show();
				$("#searchType").hide();
				$("#searchKeyword").hide();
				$("#connLock").hide();
				$("#modLock").hide();
			}
			else if( option == "modLockT"){
				$("#optionSelected").val(option);
				$("#modLock").show();
				$("#searchType").hide();
				$("#searchKeyword").hide();
				$("#isRgsn").hide();
				$("#connLock").hide();
			}
		});
	});
</script>
<title>MemberListPage</title>
</head>
<body>
	
	<form name="memberListForm" id="memberListForm">
		<table style="text-align:center">
			<tr>
				<th style="width: 15px">
					<input type="checkbox" id="massiveSelectCheckBox" />
				</th>
				<th>아이디</th>
				<th>이름</th>
				<th>회원타입</th>
				<th>접속잠김여부</th>
				<th>탈퇴여부</th>
				<th>수정잠김여부</th>
			</tr>
			
			<c:forEach items="${ memberListVO.memberList }" var="member">
				<tr>
					<td>
						<input class="deleteMemberId" name="deleteMemberId" value="${member.id}" type="checkbox"/>
					</td>
					<td>
						<a href="<c:url value="/requestMemberDetail/${member.id}"/>">${ member.id }</a>
					</td>
					<td>${member.name}</td>		
					<td>${member.memberType}</td>	
					<td>${member.isAccountLock}</td>
					<td>${member.isResign}</td>	
					<td>${member.isModifyLock}</td>	
				</tr>
			</c:forEach>

			<tr>
				<td colspan="7" align="center">

						<div style="text-align:center">
							<c:if test="${ memberListVO ne null }">
								${memberListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "memberListForm")}
							</c:if> 
						</div>
						<div style="text-align: right;">
							<select id="search" name="search">
								<option value="">선택</option>
								<option id="id" value="id">아이디+이름</option>
								<option id="type" value="type">회원 종류</option>
								<option id="connLockT" value="connLockT">접속잠김</option>
								<option id="isRgsnT" value="isRgsnT">탈퇴여부</option>
								<option id="modLockT" value="modLockT">수정잠김</option>
							</select>
							
							<input type="text" id="searchKeyword" name="searchKeyword" value="${ memberSearchVO.searchKeyword }"/>
							
							<select id="searchType" name="searchType">
						   <option value="" selected="selected"></option>
								<c:forEach items="${ memberTypeList }" var="type">
									<c:if test="${ memberSearchVO.searchType eq type.cdId}">
										<option id="memType" value="${type.cdId}" selected="selected">${ type.cdNm }</option>
									</c:if>
									<c:if test="${ memberSearchVO.searchType ne type.cdId}">
										<option id="memType" value="${type.cdId}">${ type.cdNm }</option>
									</c:if>
								</c:forEach>
							</select>
							
							<select id="connLock" name="connLock">
								<option value="" selected="selected"></option>
								<c:forEach items="${ searchTypeList }" var="type">
									<c:if test="${ memberSearchVO.connLock eq type}">
										<option id="sType" value="${ type }" selected="selected">${ type }</option>
									</c:if>
									<c:if test="${ memberSearchVO.connLock ne type}">
										<option id="sType" value="${ type }">${ type }</option>
									</c:if>
								</c:forEach>
							</select>
							
							<select id="isRgsn" name="isRgsn">
								<option value="" selected="selected"></option>
								<c:forEach items="${ searchTypeList }" var="type">
									<c:if test="${ memberSearchVO.isRgsn eq type}">
										<option id="sType" value="${ type }" selected="selected">${ type }</option>
									</c:if>
									<c:if test="${ memberSearchVO.isRgsn ne type}">
										<option id="sType" value="${ type }">${ type }</option>
									</c:if>
								</c:forEach>
							</select>
							
							<select id="modLock" name="modLock">
								<option value="" selected="selected"></option>
								<c:forEach items="${ searchTypeList }" var="type">
									<c:if test="${ memberSearchVO.modLock eq type}">
										<option id="sType" value="${ type }" selected="selected">${ type }</option>
									</c:if>
									<c:if test="${ memberSearchVO.modLock ne type}">
										<option id="sType" value="${ type }">${ type }</option>
									</c:if>
								</c:forEach>
							</select>
							<input id="optionSelected" name ="optionSelected" type="hidden" value="${memberSearchVO.optionSelected}"/>
							<input type="button" id="searchBtn" value="검색" />
							<input type="button" id="searchInitBtn" value="검색 초기화" />
						</div>

				</td>
			</tr>
	
		</table>
	
	<div>
		<input id="massiveDeleteBtn" class="inputButton" type="button" value="일괄삭제" />
		<input id="changePasswordButton" class="inputButton" type="button" value="비밀번호 변경" />
		<input id="showMemberTypeButton" class="inputButton" type="button" value="회원권한 변경" />
	</div>
	<div id="showMemberTypeDiv">
		<br/><br/>
			<c:forEach items="${memberTypeList}" var="memberType">
				<input type="radio" class="memberType" name="memberType" value="${memberType.cdId}"/>${memberType.cdNm}
			</c:forEach>
			<input id="modifyMemberTypeButton" class="inputButton" type="button" value="변경" />
	</div>
	</form>
</body>
</html>