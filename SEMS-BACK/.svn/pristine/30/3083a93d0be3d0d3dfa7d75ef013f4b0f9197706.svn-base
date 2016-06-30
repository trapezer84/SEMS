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
		<th>교육명</th>
		<th>PC명</th>
		<th>IP</th>
		<th>사용자</th>
	</tr>
	<c:forEach items="${ usedPcListVO.usedPcList }" var="usedPc">
	<tr>
		<td>${ usedPc.educationTitle }</td>
		<td>${ usedPc.pcName }</td>
		<td>${ usedPc.ip }</td>
		<td>${ usedPc.memberId }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="4">
			<form id="searchForm">
				<div style="text-align: left;">
					<c:if test="${ not empty usedPcListVO }" >
						${ usedPcListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
					</c:if>
				</div>
				<div style="text-align: right;">
					<select id="searchType" name="searchType">
						<option value="pcName" ${ usedPcSearchVO.searchType eq 'pcName' ? 'selected' : '' }>PC 명</option>
						<option value="educationTitle" ${ usedPcSearchVO.searchType eq 'eduName' ? 'selected' : '' }>교육 명</option>
						<option value="ip" ${ usedPcSearchVO.searchType eq 'ip' ? 'selected' : '' }>IP</option>
						<option value="memberId" ${ usedPcSearchVO.searchType eq 'memberId' ? 'selected' : '' }>사용자</option>
					</select>
					
					<input type="text" id="searchKeyword" name="searchKeyword" value="${ usedPcSearchVO.searchKeyword }" />
					<input type="button" id="searchBtn" value="검색" />
				</div>
			</form>
		</td>
	</tr>
</table>
</body>
</html>