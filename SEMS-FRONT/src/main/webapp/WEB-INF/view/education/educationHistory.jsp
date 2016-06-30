<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육 이력</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready( function() {
		
		$("#startDate").hide();
		$("#endDate").hide();
		$("#searchApplyState").hide();
		$("#searchCost").hide();
		$("#searchEduName").hide();
		
		$(".onlyText").keyup(function(event) {
			regexp = /[@\#$%<>&\()\=_\’]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		
		$("#searchBtn").click( function() {
			
/* 			var startDate = $("#startDate").val();
			var closeDate = $("#endDate").val();
				
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
			} */
			
			if( $("#searchType option:selected").val() == "eduName"){
				if ($("#searchEduName").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			else if( $("#searchType option:selected").val() == "cost"){
				if ($("#searchCost").val() == ""){
					alert("비용 종류를 선택하세요!");
					return;
				}
			}
			else if( $("#searchType option:selected").val() == "applyState"){
				if ($("#searchApplyState").val() == ""){
					alert("신청상태를 선택하세요!");
					return;
				}
			}
			else if( $("#searchType option:selected").val() == "applyDate"){
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
		
		$("#searchInitBtn").click(function() {
			
			location.href="<c:url value='/member/myPage/educationHistoryInit' />";
			
		});
		
		$("#searchType").change(function() {
			var option = $("#searchType option:selected").val();
			if (option == "eduName") {
				$("#searchEduName ").show();
				$("#searchCost ").hide();
				$("#searchApplyState").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "cost"){
				$("#searchCost").show();
				$("#searchEduName").hide();
				$("#searchApplyState").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "applyDate"){
				$("#searchCost").hide();
				$("#searchEduName").hide();
				$("#searchApplyState").hide();
				$("#startDate").show();
				$("#endDate").show();
			}
			else if( option == "applyState"){
				$("#searchCost").hide();
				$("#searchEduName").hide();
				$("#searchApplyState").show();
				$("#startDate").hide();
				$("#endDate").hide();
			}
		});
		
		var option = $("#searchType option:selected").val();
		if (option == "eduName") {
			$("#searchEduName ").show();
			$("#searchCost ").hide();
			$("#searchApplyState").hide();
			$("#startDate").hide();
			$("#endDate").hide();
		}
		else if( option == "cost"){
			$("#searchCost").show();
			$("#searchEduName").hide();
			$("#searchApplyState").hide();
			$("#startDate").hide();
			$("#endDate").hide();
		}
		else if( option == "applyDate"){
			$("#searchCost").hide();
			$("#searchEduName").hide();
			$("#searchApplyState").hide();
			$("#startDate").show();
			$("#endDate").show();
		}
		else if( option == "applyState"){
			$("#searchCost").hide();
			$("#searchEduName").hide();
			$("#searchApplyState").show();
			$("#startDate").hide();
			$("#endDate").hide();
		}
		
		$("#qnaButton").click(function() {
			var educationId = $(this).parent().children().eq(0).val();
			location.href="<c:url value='/"+educationId+"/eduQna' />";
			
		});
		
	});
</script>
</head>
<body>

<h3>현재 참가 중 교육</h3>
<table border="1">
<tr>
		<th>교육 명</th>
		<th>비용</th>
		<th>신청 날짜</th>
		<th>코멘트</th>
		<th>피드백</th>
		<th>교육 시작일</th>
		<th>교육 종료일</th>
		<th>IP</th>
		<th>QnA</th>
	</tr>
	<c:forEach items="${ joinEducationList }" var="joinEducationVO">
		<tr>
			<td><a href="<c:url value='/eduDetail/${ joinEducationVO.educationId}' />">${ joinEducationVO.educationTitle }</a></td>
			<td>${ joinEducationVO.cost }</td>
			<td>${ joinEducationVO.educationHistoryDate }</td>
			<td>${ joinEducationVO.cmnt }</td>
			<td>${ joinEducationVO.fdbk }</td>
			<td>${ joinEducationVO.startDate }</td>
			<td>${ joinEducationVO.endDate }</td>
			<td>${ joinEducationVO.ip }</td>
			<td>
				<input type="hidden" value="${ joinEducationVO.educationId }">
				<input type="button" id="qnaButton" value="QnA">
			</td>
		</tr>
	</c:forEach>	
</table>
<br/><br/>
<h3>교육 히스토리</h3>
<table border="1">
	<tr>
		<th>교육 명</th>
		<th>비용</th>
		<th>신청 날짜</th>
		<th>신청 상태</th>
		<th>코멘트</th>
		<th>피드백</th>
		<th>교육 시작일</th>
		<th>교육 종료일</th>
		<th>IP</th>
	</tr>
	<c:forEach items="${ educationHistoryListVO.educationHistoryList }" var="educationHistoryVO">
		<tr align="center">
			<td>${ educationHistoryVO.educationTitle }</td>
			<td>${ educationHistoryVO.costName }</td>
			<td>${ educationHistoryVO.educationHistoryDate }</td>
			<td>${ educationHistoryVO.cdNm }</td>
			<td>${ educationHistoryVO.cmnt }</td>
			<td>${ educationHistoryVO.fdbk }</td>
			<td>${ educationHistoryVO.startDate }</td>
			<td>${ educationHistoryVO.endDate }</td>
			<td>${ educationHistoryVO.ip }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="10">
			<form id="pagingForm" onkeydown="if(event.keyCode==13) return false;">
				${ educationHistoryListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
			<div style="text-align: right;">
					<select id="searchType" name="searchType">
						<option value="">선택</option>
						<c:if test="${educationHistorySearchVO.searchType eq 'eduName' }">
							<option id="eduName" value="eduName" selected="selected">교육 명</option>
						</c:if>
						<c:if test="${educationHistorySearchVO.searchType ne 'eduName' }">
							<option id="eduName" value="eduName" >교육 명</option>
						</c:if>
						<c:if test="${educationHistorySearchVO.searchType eq 'cost' }">
							<option id="eduName" value="cost" selected="selected">비용</option>
						</c:if>
						<c:if test="${educationHistorySearchVO.searchType ne 'cost' }">
							<option id="eduName" value="cost" >비용</option>
						</c:if>
						<c:if test="${educationHistorySearchVO.searchType eq 'applyDate' }">
							<option id="eduName" value="applyDate" selected="selected">신청 날짜</option>
						</c:if>
						<c:if test="${educationHistorySearchVO.searchType ne 'applyDate' }">
							<option id="eduName" value="applyDate" >신청 날짜</option>
						</c:if>
						<c:if test="${educationHistorySearchVO.searchType eq 'applyState' }">
							<option id="eduName" value="applyState" selected="selected">신청 상태</option>
						</c:if>
						<c:if test="${educationHistorySearchVO.searchType ne 'applyState' }">
							<option id="eduName" value="applyState" >신청 상태</option>
						</c:if>
					</select>
					
					<input type="text" id="searchEduName" class="onlyText" name="searchEduName" value="${ educationHistorySearchVO.searchEduName }"/>
					
					<select id="searchCost" name="searchCost">
						<option value="" selected="selected"></option>
						<c:forEach items="${ educationHistoryListVO.costList }" var="eduCost">
							<c:if test="${ educationHistorySearchVO.searchCost eq eduCost.codeId}">
								<option id="eduCostType" value="${ eduCost.codeId }" selected="selected">${ eduCost.codeName }</option>
							</c:if>
							<c:if test="${ educationHistorySearchVO.searchCost ne eduCost.codeId}">
								<option id="eduCostType" value="${ eduCost.codeId }">${ eduCost.codeName }</option>
							</c:if>
						</c:forEach>
					</select>
					
					<select id="searchApplyState" name="searchApplyState">
						<option value="" selected="selected"></option>
						<c:forEach items="${ educationHistoryListVO.statList }" var="applyState">
							<c:if test="${ educationHistorySearchVO.searchApplyState eq applyState.codeId}">
								<option id="applyStateType" value="${ applyState.codeId }" selected="selected">${ applyState.codeName }</option>
							</c:if>
							<c:if test="${ educationHistorySearchVO.searchApplyState ne applyState.codeId}">
								<option id="applyStateType" value="${ applyState.codeId }">${ applyState.codeName }</option>
							</c:if>
						</c:forEach>
					</select>
					
					<input type="date" name="startDate" id="startDate" value="${educationHistorySearchVO.startDate}" />
					<input type="date" name="endDate" id="endDate" value="${educationHistorySearchVO.endDate}" /> 
					
					<input type="button" id="searchBtn" value="검색" />
					<input type="button" id="searchInitBtn" value="검색 초기화" />
				</div>
			</form>
		</td>
	</tr>
	<tr>
		<td  colspan="10">
			<span><a href="<c:url value="/member/myPage/educationHistory/exportExcel"/>" >다운로드</a></span>
		</td>
	</tr>
</table>

</body>
</html>