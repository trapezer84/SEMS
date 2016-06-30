<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready( function() {
		
		$("#searchBtn").click( function() {
			var startDate = $("#startDate").val();
			
			if (startDate == "") {
				alert("검색시작일을 지정해주세요.");
				$("#startDate").focus();
				return;
			}
			movePage('0');
		});

		$("#searchInitBtn").click(function() {
			location.href="<c:url value='/minutesListInit' />";
		});
		
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

	<table>
		<tr>
			<th>minutesId</th>
			<th>memberId</th>
			<th>minutesAgenda</th>
			<th>attendance</th>
			<th>minutesPlace</th>
			<th>minutesContent</th>
			<th>decisionSubject</th>
			<th>remarks</th>
			<th>teamId</th>
			<th>startDate</th>
			<th>endDate</th>
		</tr>
		
		<c:forEach items="${ minutesListVO.minutesList }" var="minutes">
			<tr>
				<td class="menutd">${ minutes.minutesId }</td>
				<td class="menutd">${ minutes.memberId }</td>
				<td class="menutd">${ minutes.minutesAgenda }</td>
				<td class="menutd">${ minutes.attendance }</td>
				<td class="menutd">${ minutes.minutesPlace }</td>
				<td class="menutd">${ minutes.minutesContent }</td>
				<td class="menutd">${ minutes.decisionSubject }</td>
				<td class="menutd">${ minutes.remarks }</td>
				<td class="menutd">${ minutes.teamId }</td>
				<td class="menutd">${ minutes.startDate }</td>
				<td class="menutd">${ minutes.endDate }</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="5" align="center">
				<form name="searchForm" id="searchForm">
					<div style="text-align: center;">
						${ minutesListVO.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</div>
					<div style="text-align: right;">
						<input type="date" name="startDate" id="startDate" value="${minutesSearchVO.startDate}" />
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="전체 조회" />
					</div>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>