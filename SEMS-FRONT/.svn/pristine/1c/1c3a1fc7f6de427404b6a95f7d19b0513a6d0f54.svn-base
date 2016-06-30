<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready( function() {
		
		$("#startDate").hide();
		$("#endDate").hide();
		$("#searchKeyword").hide();
		
		$("#writeBtn").click(function() {
			location.href="<c:url value='/education/viewReportWrite/${educationId}' />";
		});
		
		$("#searchBtn").click( function() {
			
			if( $("#searchType option:selected").val() == "title"){
				if ($("#searchEduName").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			else if( $("#searchType option:selected").val() == "startDate"){
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
					
				if (startDate == "" || endDate == "") {
					// 검색 기간 입력 되지 않은 경우
					if (startDate == "") {
						alert("검색시작일을 지정해주세요.");
						$("#startDate").focus();
						return;
					}
					
					if (endDate == "") {
						alert("검색 마지막일을 지정해주세요.");
						$("#endDate").focus();
						return;
					}
				} 
				else{
					// 검색 기간 입력 되었지만
					// 검색 시작일이 더 클 경우
					if(startDate > endDate){
						alert("검색 기간이 잘못 설정되었습니다.");
						return;
					}
				}
			}
			else if( $("#searchType option:selected").val() == "endDate"){
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
					
				if (startDate == "" || endDate == "") {
					// 검색 기간 입력 되지 않은 경우
					if (startDate == "") {
						alert("검색시작일을 지정해주세요.");
						$("#startDate").focus();
						return;
					}
					
					if (endDate == "") {
						alert("검색 마지막일을 지정해주세요.");
						$("#endDate").focus();
						return;
					}
				} 
				else{
					// 검색 기간 입력 되었지만
					// 검색 시작일이 더 클 경우
					if(startDate > endDate){
						alert("검색 기간이 잘못 설정되었습니다.");
						return;
					}
				}
			}
			else{
				alert("검색조건을 입력하세요.");
			}
			
			movePage('0');
			
		});
		
		/* $("#searchInitBtn").click(function() {
			
			location.href="<c:url value='/member/myPage/educationHistoryInit' />";
			
		}); */
		
		$("#searchType").change(function() {
			var option = $("#searchType option:selected").val();
			if (option == "title") {
				$("#searchKeyword").show();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "startDate" || option == "endDate"){
				$("#searchKeyword").hide();
				$("#startDate").show();
				$("#endDate").show();
			}
		});
		
		var option = $("#searchType option:selected").val();
		if (option == "title") {
			$("#searchKeyword").show();
			$("#startDate").hide();
			$("#endDate").hide();
		}
		else if( option == "startDate" || option == "endDate"){
			$("#searchKeyword").hide();
			$("#startDate").show();
			$("#endDate").show();
		}
		
	});
</script>
</head>
<body>

	<table border="1" style="text-align: center;">
		<tr>
			<th>강사 명</th>
			<th>과제 명</th>
			<th>시작 일</th>
			<th>마감 일</th>
		</tr>
		<c:forEach items="${educationReportListVO.educationReportList }" var="educationReportList" >
			<tr>
				<td>${educationReportList.memberId }</td>
				<td>
					<a href="<c:url value='/education/detailReport/${educationReportList.articleId }' />">
						${educationReportList.title }
					</a>
				</td>
				<td>${educationReportList.startDate }</td>
				<td>${educationReportList.endDate }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">
				<form id="pagingForm">
					${ educationReportListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
					<div style="text-align: right;">
						<select id="searchType" name="searchType">
							<option value="">선택</option>
							<c:if test="${educationReportSearchVO.searchType eq 'title' }">
								<option id="title" value="title" selected="selected">제목</option>
							</c:if>
							<c:if test="${educationReportSearchVO.searchType ne 'title' }">
								<option id="title" value="title" >제목</option>
							</c:if>
							<c:if test="${educationReportSearchVO.searchType eq 'startDate' }">
								<option id="eduStartDate" value="startDate" selected="selected">과제 시작일</option>
							</c:if>
							<c:if test="${educationReportSearchVO.searchType ne 'startDate' }">
								<option id="eduStartDate" value="startDate" >과제 시작일</option>
							</c:if>
							<c:if test="${educationReportSearchVO.searchType eq 'endDate' }">
								<option id="eduEndDate" value="endDate" selected="selected">과제 마감일</option>
							</c:if>
							<c:if test="${educationReportSearchVO.searchType ne 'endDate' }">
								<option id="eduEndDate" value="endDate" >과제 마감일</option>
							</c:if>
						</select>
					
						<input type="text" id="searchKeyword" name="searchKeyword" value="${educationReportSearchVO.searchKeyword }"/>
						
						<input type="date" name="startDate" id="startDate" value="${educationReportSearchVO.startDate}" />
						<input type="date" name="endDate" id="endDate" value="${educationReportSearchVO.endDate}" /> 
						
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="writeBtn" value="과제 등록" />
					</div>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>