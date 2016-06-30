<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>회의록 상세 정보</h3>
	<br />
	<br />

	<table border="1">
		<tr>
			<td>회의 ID</td>
			<td>${ minutesVO.minutesId }</td>
		</tr>
		<tr>
			<td>팀 ID</td>
			<td>${ minutesVO.teamId }</td>
		</tr>
		<tr>
			<td>멤버 ID</td>
			<td>${ minutesVO.memberId }</td>
		</tr>
		<tr>
			<td>회의 안건</td>
			<td>${ minutesVO.minutesAgenda }</td>
		</tr>
		<tr>
			<td>참석자</td>
			<td>${ minutesVO.attendance }</td>
		</tr>
		<tr>
			<td>회의 장소</td>
			<td>${ minutesVO.minutesPlace }</td>
		</tr>
		<tr>
			<td>회의 내용</td>
			<td>${ minutesVO.minutesContent }</td>
		</tr>
		<tr>
			<td>결정 사항</td>
			<td>${ minutesVO.decisionSubject }</td>
		</tr>
		<tr>
			<td>비 고</td>
			<td>${ minutesVO.remarks }</td>
		</tr>
		<tr>
			<td>회의 일자</td>
			<td>
				${ minutesVO.minutesDate } <br/>
				${ minutesVO.startDate } ~ ${minutesVO.endDate }
			</td>
		</tr>
		
	</table>

</body>
</html>