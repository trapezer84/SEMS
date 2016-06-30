<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#searchKeyword").hide();
		$("#startDate").hide();
		$("#endDate").hide();

		$("#searchBtn").click(function() {

			if ($("#search option:selected").val() == "id") {
				if ($("#searchKeyword").val() == "") {
					alert("키워드를 입력해주세요.");

					return;
				}
			}

			else if ($("#startDate") || $("#endDate")) {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();

				if (startDate == "" || endDate == "") {

					if (startDate == "") {
						alert("검색 시작일을 입력해주세요.");
						return;
					}
					if (endDate == "") {
						alert("검색 종료일을 입력해주세요.");
						return;
					}
				} else if (startDate > endDate) {
					alert("검색일을 잘못 입력하셨습니다.");
					return;
				}
			} else {
				alert("검색 조건을 입력을 입력하세요.");
			}
			movePage('0');
		});

		$("#searchInitBtn").click(function() {

			location.href = "<c:url value='/member/adminHistoryInit' />";

		});

		$("#search").change(function() {
			var option = $("#search option:selected").val();

			if (option == "id") {
				$("#searchKeyword").show();
				$("#startDate").hide();
				$("#endDate").hide();
			} else if (option == "date") {
				$("#searchKeyword").hide();
				$("#startDate").show();
				$("#endDate").show();
			}

		});
		var option = $("#search option:selected").val();

		if (option == "id") {
			$("#searchKeyword").show();
			$("#startDate").hide();
			$("#endDate").hide();
		} else if (option == "date") {
			$("#searchKeyword").hide();
			$("#startDate").show();
			$("#endDate").show();
		}
	});
</script>

<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border-top: 1px solid #bcbcbc;
	border-bottom: 1px solid #bcbcbc;
	padding: 5px 10px;
}
</style>

<title>Insert title here</title>
</head>
<body>

	<div id="admin">
		<h3>admin</h3>
			<table id="admin" border="1">
				<tr>
					<th>회원 ID</th>
					<th>로그인 IP</th>
					<th>로그인 시간</th>
					<th>로그아웃 시간</th>
					<th>회원 종류</th>
				</tr>
				<c:forEach items="${ loginHistoryListVO.loginHistoryList }"
					var="history">
					<tr>
						<td>${ history.id }</td>
						<td>${ history.lgiIp }</td>
						<td>${ history.lgiDt }</td>
						<td>${ history.lgoDt }</td>
						<td>${ history.memberType }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="center">
						<form name="searchForm" id="searchForm">
							<div style="text-align: center;">
									${ loginHistoryListVO.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm") }
							</div>
							<div style="text-align: right;">
								<select id="search" name="search">
									<option value="">선택</option>
									<c:if test="${loginHistorySearchVO.search eq 'id'}">
										<option value="id" id="id" selected="selected">회원 아이디</option>
									</c:if>
									<c:if test="${ loginHistorySearchVO.search ne 'id' }">
										<option value="id" id="id">회원 아이디</option>
									</c:if>
									<c:if test="${ loginHistorySearchVO.search eq 'date' }">
										<option value="date" id="date" selected="selected">날짜</option>
									</c:if>
									<c:if test="${ loginHistorySearchVO.search ne 'date' }">
										<option value="date" id="date">날짜</option>
									</c:if>
								</select> 
								<input type="text" id="searchKeyword" name="searchKeyword" value="${ loginHistorySearchVO.searchKeyword }"/> 
								<input type="date" id="startDate" name="startDate" value="${ loginHistorySearchVO.startDate }"/> 
								<input type="date" id="endDate" name="endDate" value="${ loginHistorySearchVO.endDate }"/> 
								<input type="button" id="searchBtn" value="검색" /> 
								<input type="button" id="searchInitBtn" value="검색 초기화" />
							</div>
						</form>
					</td>
				</tr>
			</table>
	</div>
</body>
</html>