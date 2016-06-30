<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강사의 과제에 대한 제출</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#searchType").change(function(){
			var searchType = $(this).val();
			if ( searchType == '1' ) {
				$("#keyword").attr('name', 'memberNameKeyword');
			}
			else if ( searchType == '2' ) {
				$("#keyword").attr('name', 'eduNameKeyword');
			}
			else if ( searchType == '3' ) {
				$("#keyword").attr('name', 'teamNameKeyword');
			}
		});
		
		$("#searchBtn").click( function() {
			
			var keyword = $("#keyword").val();
			
			if ( keyword == '' ) {
				alert('검색어를 입력해 주세요.');
				$("#keyword").focus();
				return;
			}

			movePage('0');	
		});
		
		$("#searchInitBtn").click(function(){
			location.href="<c:url value='/myPage/myReportListInit' />"
		});
	});
</script>
</head>
<body>

	<table border="1">
		<tr>
			<th>제출자</th>
			<th>강의명</th>
			<th>과제명</th>
			<th>제출일</th>
		</tr>
		<c:forEach items="${reportReplytListVO.reportReplyList}" var="reportReplyVO">
			<tr>
				<td>${reportReplyVO.memberName}</td>
				<td><a href="<c:url value='/eduDetail/${reportReplyVO.eduId}'/>"> ${reportReplyVO.eduTtl}</a></td>
				<td><a href="<c:url value='/education/detailReport/${reportReplyVO.atcId}'/>"> ${reportReplyVO.title}</a></td>
				<td>${reportReplyVO.createdDate}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">
				<form id="searchForm">
					<div style="text-align: center;" >
						<c:if test="${ not empty reportReplytListVO }">
							${reportReplytListVO.paging.getPagingList("pageNo","[@]","이전","다음","searchForm")}
						</c:if>
					</div>
					<br/>
					<div style="text-align: right;">
						<select id="searchType">
							<option value="1" selected>수강생 이름</option>
							<option value="2">교육 이름</option>
							<option value="3">팀 이름</option>
						</select>
						<div>
							<input type="text" id="keyword" name="memberNameKeyword">
						</div>
						
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>