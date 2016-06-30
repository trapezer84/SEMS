<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<link href="/resources/css/eduDetail.css" rel="stylesheet">
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#searchBtn").click( function() {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			if (startDate == "" || endDate == "") {
				// 검색 기간 입력 되지 않은 경우
				if (startDate != "") {
					alert("검색 마지막일을 지정해주세요.");
					$("#endDate").focus();
					return;
				}
				
				if (endDate != "") {
					alert("검색 시작일을 지정해주세요.");
					$("#startDate").focus();
					return;
				}
				
				else{
					// 검색 기간 입력 되었지만
					// 검색 시작일이 더 클 경우
					if(startDate > endDate){
						alert("검색 기간이 잘못 설정되었습니다.");
						return;
					}
				}
			} 
			
			movePage('0');	
		});
		
		$("#searchInitBtn").click(function(){
			location.href="<c:url value='/myPage/myReportListInit' />"
		});
		
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내가 제출한 과제</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>강의명</th>
			<th>과제명</th>
			<th>제출일</th>
		</tr>
		<c:forEach items="${reportReplytListVO.reportReplyList}" var="reportReplyVO">
			<tr>
				<td><a href="<c:url value='/eduDetail/${reportReplyVO.eduId}'/>"> ${reportReplyVO.eduTtl}</a></td>
				<td><a href="<c:url value='/education/detailReport/${reportReplyVO.atcId}'/>"> ${reportReplyVO.title}</a></td>
				<td>${reportReplyVO.createdDate}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3">
				<form id="searchForm">
					<div style="text-align: center;" >
						<c:if test="${ not empty reportReplytListVO }">
							${reportReplytListVO.paging.getPagingList("pageNo","[@]","이전","다음","searchForm")}
						</c:if>
					</div>
					<br/>
					<div style="text-align: right;">
						<span>강의명 : </span>
						<input type="text" id="eduNameKeyword" name="eduNameKeyword" value="${ reportReplySearchVO.eduNameKeyword }"/>
						
						<span>과제명 : </span>
						<input type="text" id="reportNameKeyword" name="reportNameKeyword" value="${ reportReplySearchVO.reportNameKeyword }"/>
						
						<span>제출일 - 시작 날짜 : </span>
						<input type="date" id="startDate" name="startDate" value="${ reportReplySearchVO.startDate }" />
						<span>마지막 날짜</span>
						<input type="date" id="endDate" name="endDate" value="${ reportReplySearchVO.endDate }" />
						
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>