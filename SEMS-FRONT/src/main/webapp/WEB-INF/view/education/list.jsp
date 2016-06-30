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
			location.href="<c:url value='/educationList'/>";
		});
		
		$("#searchBtn").click(function() {
			
			var startYear = $("#startYear").val();
			var startMonth = $("#startMonth").val();
			
			startMonth = fillString(startMonth);
			
			if (startYear == "" || startYear.length == 0) {
				alert("시작 년도를 선택하세요.");
				$("#startYear").focus();
				return;
			}
			if (startMonth == "" || startMonth.length == 0) {
				alert("시작 월을 선택하세요.");
				$("#startMonth").focus();
				return;
			}
			
			var endYear = $("#endYear").val();
			var endMonth = $("#endMonth").val();

			endMonth = fillString(endMonth);
			
			if (endYear == "" || endYear.length == 0) {
				alert("종료 년도를 선택하세요.");
				$("#endYear").focus();
				return;
			}
			if (endMonth == "" || endMonth.length == 0) {
				alert("종료 월을 선택하세요.");
				$("#endMonth").focus();
				return;
			}
			
			var startSearchDate = startYear + startMonth;
			var endSearchDate = endYear + endMonth;
			
			startSearchDate = parseInt(startSearchDate);
			endSearchDate = parseInt(endSearchDate);
			
			if(startSearchDate > endSearchDate) {
				alert("기간 범위 오류");
				return;
			}
			
			$("#searchForm").attr("action", "<c:url value="/searchList"/>");
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
		<th>기간</th>
		<td>
			<select id="startYear" name="startYear" >
				<option value="" selected="selected"></option>
				<c:forEach var="startYear" begin="${fromYear}" end="${toYear}" step="1">
					<c:if test="${ searchKeyword.startYear eq  startYear }">
						<option value="${ startYear }" selected="selected">${ startYear }</option>
					</c:if> 
					<c:if test="${ searchKeyword.startYear ne startYear }">
						<option value="${ startYear }" >${ startYear }</option>
					</c:if> 
				</c:forEach>
			</select>년
			
			<select id="startMonth" name="startMonth" >
				<option value="" selected="selected"></option>
				<c:forEach var="startMonth" begin="01" end="12" step="1">
					<c:if test="${ searchKeyword.startMonth eq  startMonth }">
						<option value="${ startMonth }" selected="selected">${ startMonth }</option>
					</c:if> 
					<c:if test="${ searchKeyword.startMonth ne startMonth }">
						<option value="${ startMonth }" >${ startMonth }</option>
					</c:if> 
				</c:forEach>
			</select>월
			~
			<select id="endYear" name="endYear" >
				<option value="" selected="selected"></option>
				<c:forEach var="endYear" begin="${fromYear}" end="${toYear}" step="1">
					<c:if test="${ searchKeyword.endYear eq  endYear }">
						<option value="${ endYear }" selected="selected">${ endYear }</option>
					</c:if> 
					<c:if test="${ searchKeyword.endYear ne endYear }">
						<option value="${ endYear }" >${ endYear }</option>
					</c:if> 
				</c:forEach>
			</select>년
			-
			<select id="endMonth" name="endMonth">
				<option value="" selected="selected" ></option>
				<c:forEach var="endMonth" begin="01" end="12" step="1">
					<c:if test="${ searchKeyword.endMonth eq  endMonth }">
						<option value="${ endMonth }" selected="selected">${ endMonth }</option>
					</c:if> 
					<c:if test="${ searchKeyword.endMonth ne endMonth }">
						<option value="${ endMonth }" >${ endMonth }</option>
					</c:if> 
				</c:forEach>
			</select>월
		</td>
	</tr>
	<tr>
		<th>교육명</th>
		<td>
			<input type="text" class="onlyText" name="eduName" id="eduName" value="${searchKeyword.educationTitle }" />
		</td>
	</tr>
	<tr>
		<th>교육 형태</th>
		<td>

			<select name="educationType" id="educationType" >
				<option value="" selected="selected"></option>
				<c:forEach items="${ typeName }" var="tpName">
					<c:if test="${searchKeyword.educationType eq tpName}">
						<option value="${ tpName }" selected="selected">${ tpName }</option>
					</c:if>
					<c:if test="${searchKeyword.educationType ne tpName}">
					<option value="${ tpName }">${ tpName }</option>
					</c:if>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>비용</th>
		<td>
			<select name="cost" id="cost">
					<option value="" selected="selected"></option>
				<c:forEach items="${ costName }" var="csName">
					<c:if test="${searchKeyword.cost eq csName}">
					<option value="${ csName }" selected="selected">${ csName }</option>
					</c:if>
					<c:if test="${searchKeyword.cost ne csName}">
					<option value="${ csName }">${ csName }</option>
					</c:if>
				</c:forEach>
			</select>
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
		<th>교육 이름</th>
		<th>교육분야</th>
		<th>강의 기간</th>
		<th>강의 형태</th>
		<th>비용</th>
	</tr>
	 <c:forEach items="${ educationListVO.educationList }" var="education">
	<tr>
		<td><a href="<c:url value='/eduDetail/${education.educationId}'/>">${ education.educationTitle }</a></td>
		<td>${ education.educationCategory }</td>
		<td>${ education.startDate } ~ ${ education.endDate }</td>
		<td>${ education.typeName }</td>
		<td>${ education.costName }</td>
	</tr>
	</c:forEach> 
	
	<c:forEach items="${ searchedListVO.educationList }" var="education">
	<tr>
		<td><a href="<c:url value='/eduDetail/${education.educationId}'/>">${ education.educationTitle }</a></td>
		<td>${ education.educationCategory }</td>
		<td>${ education.startDate } ~ ${ education.endDate }</td>
		<td>${ education.typeName }</td>
		<td>${ education.costName }</td>
	</tr>
	</c:forEach>

  	<tr>
		<td colspan="5" align="center">
			<c:if test="${ educationListVO ne null }">
			${educationListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
			</c:if>
			<c:if test="${ searchedListVO ne null }">
			${searchedListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
			</c:if>
		</td>
	</tr>  

</table>
</form>
	<div class="clear"></div>
		<div class="calendar">
			<jsp:include page="/WEB-INF/view/education/calendar.jsp"></jsp:include>
	</div>
</body>
</html>