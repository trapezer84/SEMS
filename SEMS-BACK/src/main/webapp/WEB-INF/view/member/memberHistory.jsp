<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready( function() {
		
		$("#searchType").hide();
		$("#searchKeyword").hide();
		$("#startDate").hide();
		$("#endDate").hide();
		
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
			
			if( $("#search option:selected").val() == "id"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			else if( $("#search option:selected").val() == "type"){
				if ($("#searchType").val() == ""){
					alert("회원 종류를 선택하세요!");
					return;
				}
			}
			else if( $("#search option:selected").val() == "date"){
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
			
			location.href="<c:url value='/member/loginHistoryInit' />";
			
		});
		
		$("#search").change(function() {
			var option = $("#search option:selected").val();
			if (option == "id") {
				$("#searchKeyword").show();
				$("#searchType").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "type"){
				$("#searchType").show();
				$("#searchKeyword").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			 else if( option == "date"){
				$("#searchType").hide();
				$("#searchKeyword").hide();
				$("#startDate").show();
				$("#endDate").show();
			}
		});
			var option = $("#search option:selected").val();
			if (option == "id") {
				$("#searchKeyword").show();
				$("#searchType").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "type"){
				$("#searchType").show();
				$("#searchKeyword").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			 else if( option == "date"){
				$("#searchType").hide();
				$("#searchKeyword").hide();
				$("#startDate").show();
				$("#endDate").show();
			}
	});
</script>
<style>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    border-top: 1px solid #bcbcbc;
    border-bottom: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
</style>

<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>회원 ID</th>
			<th>로그인 IP</th>
			<th>로그인 시간</th>
			<th>로그아웃 시간</th>
			<th>회원 종류</th>
		</tr>
		<c:forEach items="${ loginHistoryListVO.loginHistoryList }" var="history">
		<tr>
			<td class="menutd">${ history.id }</td>
			<td class="menutd">${ history.lgiIp }</td>
			<td class="menutd">${ history.lgiDt }</td>
			<td class="menutd">${ history.lgoDt }</td>
			<td class="menutd">${ history.memberType }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<form name="searchForm" id="searchForm">
				<div style = "text-align:center;">
					<c:if test="${ loginHistoryListVO ne null }">
						${loginHistoryListVO.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</c:if> 
				</div>
				<div style="text-align: right;">
					<select id="search" name="search" >
						<option value="">선택</option>
						<c:if test="${loginHistorySearchVO.search eq 'id' }">
							<option id="id" value="id" selected="selected">회원 아이디</option>
						</c:if>
						<c:if test="${loginHistorySearchVO.search ne 'id' }">
							<option id="id" value="id" >회원 아이디</option>
						</c:if>
						<c:if test="${loginHistorySearchVO.search eq 'type' }">
							<option id="type" value="type" selected="selected">회원 종류</option>
						</c:if>
						<c:if test="${loginHistorySearchVO.search ne 'type' }">
							<option id="type" value="type" >회원 종류</option>
						</c:if>
						<c:if test="${loginHistorySearchVO.search eq 'date' }">
							<option id="date" value="date" selected="selected">날짜</option>
						</c:if>
						<c:if test="${loginHistorySearchVO.search ne 'date' }">
							<option id="date" value="date" >날짜</option>
						</c:if>
					</select>
					
					<input type="text" class="onlyText" id="searchKeyword" name="searchKeyword" value="${ loginHistorySearchVO.searchKeyword }"/>
					
					<select id="searchType" name="searchType">
						<option value="" selected="selected"></option>
						<c:forEach items="${ typeList }" var="type">
							<c:if test="${ loginHistorySearchVO.searchType eq type.cdId }">
								<option id="memType" value="${ type.cdId }" selected="selected">${ type.cdNm }</option>
							</c:if>
							<c:if test="${ loginHistorySearchVO.searchType ne type.cdId }">
								<option id="memType" value="${ type.cdId }">${ type.cdNm }</option>
							</c:if>
						</c:forEach>
					</select>
					
					<input type="date" name="startDate" id="startDate" value="${loginHistorySearchVO.startDate}" />
					<input type="date" name="endDate" id="endDate" value="${loginHistorySearchVO.endDate}" /> 
					
					<input type="button" id="searchBtn" value="검색" />
					<input type="button" id="searchInitBtn" value="검색 초기화" />
				</div>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>