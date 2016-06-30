<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="/resources/css/eduDetail.css" rel="stylesheet">
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">

$(document).ready(function() {
	
		$(".onlyText").keyup(function(event) {
			regexp = /[@\#$%<>&\()\=_\’]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});

		
		$("#initSearch").click(function() {
			location.href="<c:url value='/TeamList'/>";
		});
		
		$("#searchBtn").click(function() {
			
			var searchYear = $("#searchYear").val();
			var searchMonth = $("#searchMonth").val();
			
			searchMonth = fillString(searchMonth);
			
			if (searchYear == "" || searchYear.length == 0) {
				alert("검색 년도를 선택하세요.");
				$("#searchYear").focus();
				return;
			}
			
			if (searchMonth == "" || searchMonth.length == 0) {
				alert("시작 월을 선택하세요.");
				$("#searchMonth").focus();
				return;
			}
			
			$("#searchForm").attr("action", "<c:url value="/searchList"/>");		//searchList
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		});
	});
	
	function fillString(str) {

		if (str.length == 1) {
			str = "0" + str;
		}

		return str;
	}
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="searchForm" id="searchForm" >

<table>
	<tr>
		<th>강의명</th>
		<td>
			<input type="text" class="onlyText" name="eduName" id="eduName" value="${searchKeyword.educationName }" />
		</td>
	</tr>
	<tr>
		<th>팀 명</th>
		<td>
			<input type="text" class="onlyText" name="teamName" id="teamName" value="${searchKeyword.teamName }" />
		</td>
	</tr>
	<tr>
		<th>팀원 수</th>
		<td>
	<input type="text" class="onlyText" name="teamNumber" id="teamNumber" value="${searchKeyword.teamCount }" />
		</td>
	</tr>
	<tr>
		<th>팀 생성일</th>
		<td>
			<select id="searchYear" name="searchYear" >
				<option value="" selected="selected"></option>
				<c:forEach var="searchYear" begin="2013" end="2025" step="1">
					<c:if test="${ searchKeyword.searchYear eq  searchYear }">
						<option value="${ searchYear }" selected="selected">${ searchYear }</option>
					</c:if> 
					<c:if test="${ searchKeyword.searchYear ne searchYear }">
						<option value="${ searchYear }" >${ searchYear }</option>
					</c:if> 
				</c:forEach>
			</select>년
			
			<select id="searchMonth" name="searchMonth" >
				<option value="" selected="selected"></option>
				<c:forEach var="searchMonth" begin="01" end="12" step="1">
					<c:if test="${ searchKeyword.searchMonth eq  searchMonth }">
						<option value="${ searchMonth }" selected="selected">${ searchMonth }</option>
					</c:if> 
					<c:if test="${ searchKeyword.searchMonth ne searchMonth }">
						<option value="${ searchMonth }" >${ searchMonth }</option>
					</c:if> 
				</c:forEach>
			</select>월
		</td>
	</tr>	
	<tr>
		<td colspan="2">
			<input type="hidden" value="0" id="searchPageNo" />
			<input type="button" value="검색" id="searchBtn"/>
			<input type="button"  value="검색 초기화" id="initSearch" name="initSearch" />
		</td>
	</tr>
</table>

<table border="1">
	<tr>
		<th>팀 명</th>
		<th>강의 명</th>
		<th>팀원 수</th>
		<th>팀 생성일</th>
	</tr>
	 <c:forEach items="${ teamListVO.teamList }" var="team">
	<tr>
		<td><a href="<c:url value='/myTeamDetail/${team.teamId}'/>">${ team.teamName }</a></td>
		<td>${ team.educationName }</td>
		<td>${ team.teamCount }</td>
		<td>${ team.teamDate }</td>
	</tr>
	</c:forEach> 

  	<tr>
		<td colspan="5" align="center">
			${teamListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
		</td>
	</tr>  

</table>
</form>
	
</body>
</html>