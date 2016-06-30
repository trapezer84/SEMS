<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	table {
	     border-collapse: collapse;
	     text-align: center;
	     width: 800px;
	}
	
	 table, th, td {
	     border: 1px solid black;
	}
</style>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">

	$(document).ready(function(){
		$("#searchBtn").click(function(){
			
			if ( $("#searchStartDate").val() == '' ) {
				alert("검색 시작 날짜를 입력하세요.");
				return;
			}
			else if ( $("#searchEndDate").val() == '' ) {
				alert("검색 끝 날짜를 입력하세요.");
				return;
			}
			
			if ( $("#searchStartDate").val() > $("#searchEndDate").val() ) {
				alert("끝 날짜가 시작 날짜보다 빠릅니다.");
				return;
			}
			
			movePage('0');
			
		});
		
	});
</script>
<title>수강생 출결 이력</title>
</head>
<body>
	수강생 출결 이력<br/><br/>
	
	교육명 : <c:if test="${ attendanceList.size() ne 0 }">${ attendanceList.get(0).educationTitle }</c:if> <br/>
	교육 아이디 : <c:if test="${ attendanceList.size() ne 0 }">${ attendanceList.get(0).educationId }</c:if> <br/><br/>
		
	<table>
		<tr>
			<th>날짜</th>
			<th>전체 출결</th>
		</tr>
		
		<c:forEach items="${ attendanceList }" var="attend">
			<tr>
				<td>${ attend.attendTime }</td>
				<td>${ attend.state }</td>
			</tr>
		</c:forEach>
	</table>
	<form id="searchForm">
		${paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm") }
		<br/>
		<input type="date" id="searchStartDate" name="searchStartDate">
		<input type="date" id="searchEndDate" name="searchEndDate">
		<input type="button" id="searchBtn" value="검색"> 
	</form>
</body>
</html>