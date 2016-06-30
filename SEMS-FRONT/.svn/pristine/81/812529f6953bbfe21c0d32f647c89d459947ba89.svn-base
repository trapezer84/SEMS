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
			/* if( $("#searchKeyword").val() == "" ) {
				alert("검색어를 입력하세요!");
				return;
			} */
			movePage('0');
		});
		
		$("#searchInitBtn").click(function(){
			location.href="<c:url value='/myPage/myQNAListInit' />"
		});
		
	});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육 문의 사항 리스트 보기</title>
</head>
<body>

	<a href="<c:url value='/myPage/myQNAList/exportQNA'/>"><input
		type="button" value="엑셀로 보내기"></a>
	<br />
	<br />
	<table border="1">
		<tr>
			<th>문의 번호</th>
			<th>교육 번호</th>
			<th>문의 날짜</th>
			<th>문의 내용</th>
			<th>답변 여부</th>
		</tr>
		<c:forEach items="${qnaListVO.qnaList}" var="qnaVO">
			<tr>
				<td>${qnaVO.replyId}</td>
				<td>${qnaVO.eduId}</td>
				<td>${qnaVO.createdDate}</td>
				<td><a
					href="<c:url value='/myPage/myQNADetail/${qnaVO.replyId}'/>"
					onclick="window.open(this.href, 'Place Detail','toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizeable=no, width=530, height=820');return false;"
					target="_blank">${qnaVO.description}</a></td>
				<td>${qnaVO.isAnswered}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5">
				<form id="searchForm">
					<div style="text-align: left;" >
						<c:if test="${ not empty qnaListVO }">
							${qnaListVO.paging.getPagingList("pageNo","[@]","이전","다음","searchForm")}
						</c:if>
					</div>
					<div style="text-align: right;">
						<c:set var="selectedSearchType" value="${ sessionScope._SEARCH_QNA_.searchType }" />
						<select id="searchType" name="searchType" >
							<option value="replyId" ${ selectedSearchType eq "replyId" ? "selected" : "" }>문의 번호</option>
							<option value="eduId" ${ selectedSearchType eq "eduId" ? "selected" : "" }>교육 번호</option>
							<option value="description" ${ selectedSearchType eq "description" ? "selected" : "" }>문의 내용</option>
						</select>
						
						<label for="startDate">시작날짜</label>
						<input type="date" id="startDate" name="startDate" value="${ QNASearchVO.startDate }" />
						<label for="endDate">종료날짜</label>
						<input type="date" id="endDate" name="endDate" value="${ QNASearchVO.endDate }" />
						
						<input type="text" id="searchKeyword" name="searchKeyword" value="${ QNASearchVO.searchKeyword }" />
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>