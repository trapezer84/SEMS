<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(function(){
		$("#teamBBS").click(function(){
			var teamId = $(".teamId").html();
			//alert(teamId);
			location.href="<c:url value='/team/teamBBS/board/' />"+teamId;
		});
	});
	
	$(document).ready(function(){
		$("#teamListmiutes").click(function(){
			var teamId = $(".teamId").html();
			//alert(teamId);
			location.href="<c:url value='/team/listMinutes/' />"+teamId;
		});
	});


</script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>팀 리스트 번호</td>
				<td>팀 번호</td>
				<td>팀 구성원</td>
			</tr>
			
			<c:forEach items="${ teamsListsVO.teamsListsVO}" var="teamsList">
				<tr>
					<td>${ teamsList.teamListId }</td>
					<td class="teamId">${ teamsList.teamId }</td>
					<td>${ teamsList.mbrId }</td>
				</tr>
			</c:forEach>
			
		</table>
		<input type="button" id="teamBBS" value="팀별 게시판 가기" />
		<input type="button" id="teamListmiutes" value="팀별 회의록 가기" />
	</div>	
</body>
</html>