<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제 조회</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#searchBtn").click( function() {
			if ($("#searchKeyword").val() == ""){
				alert("검색어를 입력하세요!");
				return;
			}
			movePage('0');
		});
	});
</script>
</head>
<body>
	<table>
		<tr>
			<td>제출자 아이디</td>
			<td>제출자 이름</td>
			<td>강의 아이디</td>
			<td>강의 이름</td>
			<td>과제 아이디</td>
			<td>과제 이름</td>
			<td>제출일</td>
		</tr>
		<c:forEach items="${ reportListVO.eduReportList }" var="eduReport">
				<tr>
					<td>${eduReport.memberId }</td>
					<td>${eduReport.fileName }</td>
					<td>${eduReport.educationId }</td>
					<td>${eduReport.educationTitle }</td>
					<td>${eduReport.eduReportId }</td>
					<td>${eduReport.title }</td>
					<td>${eduReport.createDate }</td>
				</tr>
		</c:forEach>
		<tr>
			<td colspan="7" align="center">
				<form name="searchForm" id="searchForm">
					<div style = "text-align:center;">
						<c:if test="${ reportListVO ne null }">
							${reportListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div>
					<div style="text-align: right;">
						<select id="searchType" name="searchType">
							<option value="1">교육생 아이디</option>
							<option value="2">교육 아이디</option>
							<option value="2">팀 아이디</option>
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword" value="${ eduReportSearchVO.searchKeyword }"/>
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>	
	</table>
</body>
</html>