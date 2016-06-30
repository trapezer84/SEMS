<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>교육 상세 조회</title>
</head>
<body>
	
		<div id="table">
			<table id="table_one">
				<tr>
					<th colspan="2">팀 상세 조회</th>
				</tr>
				<tr>
					<td>팀 명</td>
					<td>${ team.teamName }</td>
				</tr>
				<tr>
					<td>팀원 수</td>
					<td>${ team.teamCount }</td>
				</tr>
				<tr>
					<td>팀원 아이디</td>
					<td>${ team.memberId }</td>
				</tr>
			</table>
			

	
		</div>
	

	
</body>
</html>