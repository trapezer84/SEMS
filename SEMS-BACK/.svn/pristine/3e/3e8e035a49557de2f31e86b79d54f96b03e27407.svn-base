<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>협력사 리스트</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#searchTypeName").hide();
		
		$("#searchBtn").click(function(){
			
			if( $("#searchType option:selected").val() == "1"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			
			movePage('0');
			
		});
		
		$("#initSearchBtn").click(function(){
			location.href = '<c:url value="/initCooSearch"/>';
		});
		
		$("#cooRegisterBtn").click(function(){
			location.href ="<c:url value='/registerCoo' />";
		});
		
		$("#searchKeyword").keydown(function(event){
			if(event.keyCode == 13) {
				event.preventDefault();
				return false;
			}
		});
		
		$("#searchType").click(function(){
			if ( $("#searchType option:selected").val() == '1' ) {
				$("#searchKeyword").show();
				$("#searchTypeName").hide();
			}
			
			if ( $("#searchType option:selected").val() == '2') {
				$("#searchTypeName").show();
				$("#searchKeyword").hide();
			}
		});
		
		var option = $("#searchType option:selected").val();
		if (option == "1") {
			$("#searchKeyword").show();
			$("#searchTypeName").hide();

		}
		else if( option == "2"){
			$("#searchTypeName").show();
			$("#searchKeyword").hide();
		}
		
		
	});
</script>
</head>
<body>
	<div>
		<table border="1" style="text-align: center; border-collapse: collapse;">
			<tr>
				<th>COO_ID</th>
				<th>협약사 명</th>
				<th>협약사 소재지</th>
				<th>사업자 번호</th>
				<th>대표 명</th>
				<th>담당자 전화번호</th>
				<th>회사 전화번호</th>
				<th>담당자 이메일</th>
				<th>협력사/협약사 선택지</th>
			</tr>
			<c:forEach items="${cooperationListVO.cooperationList }" var="cooperation">
			<tr>
				<td>${cooperation.cooperationId }</td>
				<td><a href="<c:url value='/cooDetail/${cooperation.cooperationId }' />"><c:out value="${cooperation.cooperationTitle }"/></a></td>
				<td><c:out value="${cooperation.cooperationLocation }"/></td>
				<td><c:out value="${cooperation.cooperationNumber }"/></td>
				<td><c:out value="${cooperation.representativeName }"/></td>
				<td><c:out value="${cooperation.managerPhoneNumber }"/></td>
				<td><c:out value="${cooperation.cooperationPhoneNumber }"/></td>
				<td><c:out value="${cooperation.managerEmail }"/></td>
				<td><c:out value="${cooperation.cooperationType }"/></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="9">
					<form id="searchForm">
						${cooperationListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm") }
						<div style="float:right;">
							<select id="searchType" name="searchType">
							<c:if test="${searchVO.searchType eq '1' }">
								<option value="1" selected="selected">협약사명</option>
							</c:if>
							<c:if test="${searchVO.searchType ne '1' }">
								<option value="1">협약사명</option>
							</c:if>
							<c:if test="${searchVO.searchType eq '2' }">
								<option value="2" selected="selected">협력사/협약사</option>
							</c:if>
							<c:if test="${searchVO.searchType ne '2' }">
								<option value="2">협력사/협약사</option>
							</c:if>
							</select>
							
							<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword }"/>
							
							<select id="searchTypeName" name="searchTypeName" >
								<c:forEach items="${typeNameList }" var="typeNameList" >
								<c:if test="${searchVO.searchTypeName eq typeNameList.codeId }">
										<option value="${ typeNameList.codeId }" selected="selected">${ typeNameList.codeName }</option>
								</c:if>
								<c:if test="${searchVO.searchTypeName ne typeNameList.codeId }">
										<option value="${ typeNameList.codeId }">${ typeNameList.codeName }</option>
								</c:if>
								</c:forEach>
							</select>
							<input type="button" id="searchBtn" value="검색" />
							<input type="button" id="initSearchBtn" value="검색초기화" />
							<input type="button" id="cooRegisterBtn" value="등록하기" />
						</div>
					</form>
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>