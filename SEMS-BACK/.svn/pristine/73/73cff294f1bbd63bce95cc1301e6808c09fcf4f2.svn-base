<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<style type="text/css">
table, tr, td, th {
	border: 1px;
	border-style: solid;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSearch").click(function() {
			movePage('0');
		});
		
		$("#searchKeyword").keypress(function(e) {
		    if(e.which == 13) {
		    	movePage('0');
		    }
		});
	});
	

	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>교육명</th>
			<th>강사명</th>
			<th>시작 날짜</th>
			<th>종료 날짜</th>
		</tr>
		<c:if test="${educationList.educationList ne null}">
			<c:forEach items="${educationList.educationList}" var="education">
				<tr>
					<td><a
						href='<c:url value="showDetailEducation/${education.educationId}" />'>${education.educationTitle}</a>
					</td>
					<td>${education.memberId}</td>
					<td>${education.startDate}</td>
					<td>${education.endDate}</td>
				</tr>
			</c:forEach>
		</c:if>
		<form:form commandName="educationSearchVO" name="searchForm" id="searchForm">
			<tr>
				<td colspan="4">
					<div style="text-align: center;">
						<c:if test="${ educationList ne null }">
					${educationList.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
				</c:if>
					</div>
				</td>
			</tr>
			<tr style="text-align: center;">
				<td colspan="4"><select name="searchType">
						<c:if test="${searchVO.searchType eq 'title'}">
							<option value="title" selected="selected">제목</option>
							<option value="member">강사명</option>
						</c:if>
						<c:if test="${searchVO.searchType eq 'member'}">
							<option value="title">제목</option>
							<option value="member" selected="selected">강사명</option>
						</c:if>
				</select> 
				<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}"/>
				<input type="button" id="btnSearch" value="검색" /></td>
			</tr>
		</form:form>
	</table>




</body>
</html>