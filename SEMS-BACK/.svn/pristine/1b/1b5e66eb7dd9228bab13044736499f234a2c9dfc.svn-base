<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	if ( '${errorCode}' == "NOATD" ) {
		alert("출결 이력이 없습니다.");
	}
</script>
<title>MemberAttendanceListPage</title>
</head>
<body>
	참가 중인 수강생이 있는 교육 목록<br/><br/>
	<table style="text-align:center">
		<tr>
			<th>교육명</th>
			<th>교육 아이디</th>
			<th>시작 날짜</th>
			<th>종료 날짜</th>
			<th>시작 시간</th>
			<th>종료 시간</th>
		</tr>
		
		<c:forEach items="${ educationList }" var="education">
			<tr>
				<td>${education.educationTitle}</td>
				<td>
					<a href="<c:url value="/attendanceHistory/educationDetail/${education.educationId}"/>">${ education.educationId }</a>
				</td>
				<td>${education.startDate}</td>
				<td>${education.endDate}</td>
				<td>${education.startTime}</td>
				<td>${education.endTime}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>