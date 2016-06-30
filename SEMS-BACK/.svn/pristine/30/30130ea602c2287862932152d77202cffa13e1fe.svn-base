<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#writeEduPlace").click( function() {
			location.href="<c:url value='/eduPlaceSet' />"
		});
		
		
	});
</script>
</head>
<body>
	<table border="1">
		<tr>
			<td>강의실 명</td>
			<td>강의실 위치</td>
			<td>PC 갯수</td>
		</tr>
		<c:forEach items="${ eduListVO.eduPlaceList }" var="place">
				<tr>
					<td>${ place.educationPlaceName }</td>
					<td>${ place.educationLocation }</td>
					<td>${ place.pcCount }</td>
				</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="center">
				<form name="searchForm" id="searchForm">
					<div style = "text-align:center;">
						<c:if test="${ eduListVO ne null }">
							${eduListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div>
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right"> 
				<input type="button" name="writeEduPlace" id="writeEduPlace" value="강의실 등록" />
			</td>
		</tr>	
	</table>
</body>
</html>