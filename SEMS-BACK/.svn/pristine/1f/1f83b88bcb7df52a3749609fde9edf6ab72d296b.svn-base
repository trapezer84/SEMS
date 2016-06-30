<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	th {
		background-color:gray;
		color:white;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ memberId }님의 팀 목록</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<th colspan="4" align="center" >${ memberId }님의 팀 목록</td>
		</tr>
		<tr>
			<th>팀아이디</th>
			<th>팀이름</th>
			<th>팀생성일</th>
			<th>교육아이디</th>
		</tr>
		<c:forEach items="${ allTeam }" var="team">
			<tr>
				<td>${ team.teamId }</td>
				<td><a href="<c:url value="/teamDetail/${ team.teamId }"/>">${ team.teamName }</a></td>
				<td>${ team.teamDate }</td>
				<td>${ team.educationId }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>