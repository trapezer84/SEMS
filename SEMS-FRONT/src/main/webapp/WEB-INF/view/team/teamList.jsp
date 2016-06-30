<!-- @author 이기연-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link href="/resources/css/eduDetail.css" rel="stylesheet">
<head>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#searchBtn").click(function(){
			if( $("#searchKeyword").val() == "" ) {
				alert("검색어를 입력하세요!");
				return;
			}
			movePage('0');
		});
		
		$("#searchInitBtn").click(function(){
			location.href="<c:url value='/teamList' />"
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀 리스트</title>
</head>
<body>
		<table>
			<tr>
				<td> 팀 번호 </td>
				<td> 팀 이름 </td>
				<td> 팀 생성날짜 </td>
				<td> 교육 아이디 </td>
			</tr>
 	<c:forEach items="${ teamListVO.teamList }" var="team">
		<tr>
		<td><a href="<c:url value='/team/teamDetail/${ team.teamId }'/>">${ team.teamId } </a></td>
		<td>${ team.teamNumber } </td> 
		<td>${ team.teamDate } </td>
		<td>${ team.educationId } </td>
		</tr>
		
		</c:forEach>
		<tr>
			<td colspan="4">
				<form id="searchForm">
					<div style="text-align: left;" >
							${teamListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
					</div>
					<div style="text-align: right;">
						<select id="searchType" name="searchType" >
							<option value="teamId" ${ teamSearchVO.searchType eq "teamId" ? "selected" : "" }>팀 번호</option>
							<option value="teamNumber" ${ teamSearchVO.searchType eq "teamNumber" ? "selected" : "" }>팀이름</option>
							<option value="educationId" ${ teamSearchVO.searchType eq "educationId" ? "selected" : "" }>교육 아이디 </option>
						</select>
						
						<input type="date" id="startDate" name="startDate" value="${ teamSearchVO.startDate }" />
						<input type="date" id="endDate" name="endDate" value="${ teamSearchVO.endDate }" />
						
						<input type="text" id="searchKeyword" name="searchKeyword" value="${teamSearchVO.searchKeyword }" />
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>
	
	</table>

</body>
</html>