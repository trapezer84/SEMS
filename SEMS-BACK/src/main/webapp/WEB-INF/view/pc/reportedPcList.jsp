<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용중인 PC 관리</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#searchBtn").click(function(){
			if( $("#searchBtn").val() == "" ) {
				alert("검색어를 입력하세요!");
				return;
			}
			movePage(0);
		});
		
		$(".changeReportedStateBtn").click(function(){
			var reportedPcId = $(this).parent().parent().children(":eq(0)");
			var reportedState = $(this).parent().parent().children(":eq(1)");
			
			$.post("<c:url value="/changeReportedState"/>", {
				"reportedPcId" : reportedPcId.val(),
				"reportedState" : reportedState.val(),
			}, function(data) {
				
				if(data == 'OK') {
					location.href="<c:url value='/reportedPcList' />";
				} else if(data == 'NO') {
					alert("더 이상 상태변경 할 수 없습니다.");
				}
			});
		});
		
	});
</script>
</head>
<body>
<table>
	<tr>
		<th>신고 접수 번호</th>
		<th>PC명</th>
		<th>강의장</th>
		<th>강의장 위치</th>
		<th>신고 카테고리</th>
		<th>신고 상태</th>
		<th>신고 내용</th>
		<th>신고자</th>
		<th>상태변경버튼</th>
	</tr>
	<c:forEach items="${ reportedPcListVO.reportedPcList }" var="reportedPc">
		<tr>
			<input type="hidden" class="reportedPcId" value="${ reportedPc.reportedPcId }" />
			<input type="hidden" class="reportedPcState" value="${ reportedPc.reportedState }" />
			<td>${ reportedPc.reportedPcId }</td>
			<td>${ reportedPc.pcName }</td>
			<td>${ reportedPc.educationPlaceName }</td>
			<td>${ reportedPc.educationLocation }</td>
			<td>${ reportedPc.reportedCategory }</td>
			<td>${ reportedPc.cdNm }</td>
			<td>${ reportedPc.reportedComment }</td>
			<td>${ reportedPc.memberId }</td>
			<td><input type="button" class="changeReportedStateBtn" name="changeReportedStateBtn" value="상태변경" /></td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="8">
			<form id="searchForm">
				<div style="text-align: left;">
					${ reportedPcListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
				</div>
				<div style="text-align: right;">
					<select id="searchType" name="searchType">
						<option value="pcName" ${ selectedSearchType eq 'pcName' ? 'selected' : '' }>PC 명</option>
						<option value="educationPlaceName" ${ reportedPcSearchVO.searchType eq 'educationPlaceName' ? 'selected' : '' }>강의장</option>
						<option value="educationLocation" ${ reportedPcSearchVO.searchType eq 'educationLocation' ? 'selected' : '' }>강의장 위치</option>
						<option value="reportedCategory" ${ reportedPcSearchVO.searchType eq 'reportedCategory' ? 'selected' : '' }>신고 카테고리</option>
						<option value="reportedState" ${ reportedPcSearchVO.searchType eq 'reportedState' ? 'selected' : '' }>신고 상태</option>
						<option value=reportedComment ${ reportedPcSearchVO.searchType eq 'reportedComment' ? 'selected' : '' }>신고 내용</option>
						<option value="memberId" ${ reportedPcSearchVO.searchType eq 'memberId' ? 'selected' : '' }>신고자</option>
					</select>
					
					<input type="text" id="searchKeyword" name="searchKeyword" value="${ reportedPcSearchVO.searchKeyword }" />
					<input type="button" id="searchBtn" value="검색" />
				</div>
			</form>
		</td>
	</tr>
</table>
</body>
</html>