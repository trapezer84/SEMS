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
		
	});
</script>
</head>
<body>
<table>
	<tr>
		<th>신고번호</th>
		<th>강의실</th>
		<th>PC명</th>
		<th>카테고리</th>
		<th>현황</th>
		<th>신고사유</th>
	</tr>
	<c:forEach items="${ reportedPcListVO.reportedPcList }" var="reportedPc">
	<tr>
		<td>${ reportedPc.reportedPcId }</td>
		<td>${ reportedPc.educationLocation }</td>
		<td>${ reportedPc.pcName }</td>
		<td>${ reportedPc.reportedCategory }</td>
		<td>${ reportedPc.cdNm }</td>
		<td>${ reportedPc.reportedComment }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="6">
			<form id="searchForm">
				<div style="text-align: left;">
					${ reportedPcListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
				</div>
				<div style="text-align: right;">
					<select id="searchType" name="searchType">
						<option value="reportedPcId" ${ reportedPcSearchVO.searchType eq 'reportedPcId' ? 'selected' : '' }>신고번호</option>
						<option value="educationLocation" ${ reportedPcSearchVO.searchType eq 'educationLocation' ? 'selected' : '' }>강의실</option>
						<option value="pcName" ${ reportedPcSearchVO.searchType eq 'pcName' ? 'selected' : '' }>PC 명</option>
						<option value="reportedCategory" ${ reportedPcSearchVO.searchType eq 'reportedCategory' ? 'selected' : '' }>카테고리</option>
						<option value="cdNm" ${ reportedPcSearchVO.searchType eq 'cdNm' ? 'selected' : '' }>현황</option>
						<option value="reportedComment" ${ reportedPcSearchVO.searchType eq 'reportedComment' ? 'selected' : '' }>신고사유</option>
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