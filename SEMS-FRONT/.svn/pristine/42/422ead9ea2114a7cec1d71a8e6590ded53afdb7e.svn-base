<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {

	$("#searchIDKeyword").hide();
	$("#searchSBTKeyword").hide();
	$("#startDate").hide();
	$("#endDate").hide();

	$("#searchBtn").click(function() {

		if ($("#search option:selected").val() == "id") {
			if ($("#searchIDKeyword").val() == "") {
				alert("회원 아이디에 대한 키워드를 입력해주세요.");

				return;
			}
		}
		
		else if ($("#search option:selected").val() == "subject") {
			if ($("#searchSBTKeyword").val() == "") {
				alert("회의 안건에 대한 키워드를 입력해주세요.");

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

		location.href = "<c:url value='/team/minutesInit' />";

	});
	
	$("#minutesButton").click(function() {

		var teamId = $("#teamId").val();
		location.href = "<c:url value='/team/writeMinutes/' />" + teamId;

	});

	$("#search").change(function() {
		var option = $("#search option:selected").val();

		if (option == "id") {
			$("#searchIDKeyword").show();
			$("#searchSBTKeyword").hide();
			$("#startDate").hide();
			$("#endDate").hide();
		} else if (option == "date") {
			$("#searchIDKeyword").hide();
			$("#searchSBTKeyword").hide();
			$("#startDate").show();
			$("#endDate").show();
		} else if (option == "subject") {
			$("#searchIDKeyword").hide();
			$("#searchSBTKeyword").show();
			$("#startDate").hide();
			$("#endDate").hide();
		}

	});
	var option = $("#search option:selected").val();

	if (option == "id") {
		$("#searchIDKeyword").show();
		$("#searchSBTKeyword").hide();
		$("#startDate").hide();
		$("#endDate").hide();
	} else if (option == "date") {
		$("#searchIDKeyword").hide();
		$("#searchSBTKeyword").hide();
		$("#startDate").show();
		$("#endDate").show();
	} else if (option == "subject") {
		$("#searchIDKeyword").hide();
		$("#searchSBTKeyword").show();
		$("#startDate").hide();
		$("#endDate").hide();
	}
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

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

</head>
<body>

	<div id="minutes">
		<h4>회의록</h4>
		<table id="minutes" border="1">
			<tr>
				<th>회원 ID</th>
				<th>회의 일자</th>
				<th>회의 안건</th>
			</tr>
			<c:forEach items="${ minutesListVO.minutesList }" var="minutes">
				<tr>
					<th>${ minutes.memberId }</th>
					<th><a href="<c:url value='/team/detailMinutes/${ minutes.minutesId }' />">${ minutes.startDate }</a></th>
					<th>${ minutes.minutesAgenda }</th>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3" align="center">
					<form name="searchForm" id="searchForm">
						<div style="text-align: center;">${ minutesListVO.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm") }
						</div>
						<div style="text-align: right;">
								<select id="search" name="search">
									<option value="">선택</option>
									<c:if test="${minutesSearchVO.search eq 'id'}">
										<option value="id" id="id" selected="selected">회원 ID</option>
									</c:if>
									<c:if test="${ minutesSearchVO.search ne 'id' }">
										<option value="id" id="id">회원 ID</option>
									</c:if>
									<c:if test="${ minutesSearchVO.search eq 'date' }">
										<option value="date" id="date" selected="selected">회의 일자</option>
									</c:if>
									<c:if test="${ minutesSearchVO.search ne 'date' }">
										<option value="date" id="date">회의 일자</option>
									</c:if>
									<c:if test="${ minutesSearchVO.search eq 'subject' }">
										<option value="subject" id="subject" selected="selected">회의 안건</option>
									</c:if>
									<c:if test="${ minutesSearchVO.search ne 'subject' }">
										<option value="subject" id="subject">회의 안건</option>
									</c:if>
								</select> 
								<input type="text" id="searchIDKeyword" name="searchIDKeyword" value="${ minutesSearchVO.searchIDKeyword }"/>
								<input type="text" id="searchSBTKeyword" name="searchSBTKeyword" value="${ minutesSearchVO.searchSBTKeyword }"/>
								<input type="date" id="startDate" name="startD	ate" value="${ minutesSearchVO.startDate }"/> 
								<input type="date" id="endDate" name="endDate" value="${ minutesSearchVO.endDate }"/> 
								<input type="hidden" id="teamId" name="teamId" value="${ minutesSearchVO.teamId }"/> 
							
								<input type="button" id="searchBtn" value="검색" /> 
								<input type="button" id="searchInitBtn" value="검색 초기화" />
								<input type="button" id="minutesButton" value="회의록 쓰기" />
							</div>
					</form>
				</td>
			</tr>
		</table>

	</div>

</body>
</html>