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
	
	수강생 아이디 : <c:if test="${ resultList.size() ne 0 }"> ${ resultList.get(0).memberId } </c:if> <br/><br/>
	
	정상 출석 : ○ / 지각 : △ / 조퇴 : ● / 결석 : X<br/><br/>
	
	
	
		
		<table>
			<tr>
				<th>교육명 (교육 아이디)</th>
				<th>날짜</th>
				<th>출결 상태</th>
				<th>수정 사유</th>
			</tr>
	
			<c:forEach items="${ resultList }" var="attend">
				<tr>
					<td>${ attend.educationTitle } (${ attend.educationId })</td>
					<td style="CURSOR:hand;" title="${ attend.leaveTime }">${ attend.attendTime }</td>
					<td>
						<c:if test="${ attend.state eq 'X' }">
							${ attend.state } <a href="<c:url value="/attendanceHistory/memberDetail/modifyState/${ attend.educationId }/${ attend.memberId }/${ attend.attendTime }"/>">변경</a>
						</c:if>
					
						<c:if test="${ attend.state ne 'X' }">
							${ attend.state }
							<c:if test="${ attend.stateComment ne null }">
								( 변경됨 )
							</c:if>
						</c:if>
					</td>
					
					<td>
						<c:if test="${ attend.stateComment ne null }">
							${ attend.stateComment}
						</c:if>
					</td>
				</tr>
			</c:forEach>	
		</table><br/>
	
	<%
		int day = 0;
		int late = 0;
		int absence = 0;
		int earlyLeave = 0;
		int attend = 0;
	%>
	
		<c:forEach items="${ resultList }" var="attend">
			<%
				day++;
			%>
			<c:if test="${ attend.state eq '△' }">
				<%
					late++;
				%>
			</c:if>
			<c:if test="${ attend.state eq 'X' }">
				<%
					absence++;
				%>
			</c:if>
			<c:if test="${ attend.state eq '●' }">
				<%
					earlyLeave++;
				%>
			</c:if>
			<c:if test="${ attend.state eq '○' }">
				<%
					attend++;
				%>
			</c:if>
	</c:forEach>
	
	<table>
		<tr>
			<th>전체 일수</th>
			<th>정상 출석 일수</th>
			<th>지각/조퇴 일수</th>
			<th>결석 일수</th>
		</tr>
		<tr>
			<td>
				<% pageContext.setAttribute("day", day); %>
				${ day }
			</td>
			<td>
				<% pageContext.setAttribute("attend", attend); %>
				${ attend }
			</td>
			<td>
				<% 
					int lateAndEarlyLeave = late + earlyLeave;
					pageContext.setAttribute("lateAndEarlyLeave", lateAndEarlyLeave);
				%>
				${ lateAndEarlyLeave }
			</td>
			<td>
				<% pageContext.setAttribute("absence", absence); %>
				${ absence }
			</td>
		</tr>
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