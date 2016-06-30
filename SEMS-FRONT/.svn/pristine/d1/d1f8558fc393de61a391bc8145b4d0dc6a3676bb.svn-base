<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#initSearchBtn").click(function () {
			var eduId = $("#eduId").val();
			
			location.href = "<c:url value='/eduBoard/QNAList/'/>"+eduId;
		});	
		
		
		$("#questionBtn").click(function() {
			var eduId = $("#eduId").val();
			
			location.href = "<c:url value='/eduBoard/QNAWrite/' />"+eduId;
		});
		
		$("#searchBtn").click(function () {
			
			var eduId = $("#eduId").val();
			
			if( $("#searchKeyword" ).val() == "" ) {
				alert("검색어를 입력하세요!");
				return;
			}
			
			$("#searchForm").attr("action", "<c:url value="/eduBoard/QNAList/eduId"/>");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
			
			movePage('0');
			
		});
			
	});

</script>
<title>강의게시판 - 마이게시판 - 질문/답변 게시판</title>
</head>
<body>

	<input type="hidden" id="eduId" value="${eduId}">

	
	<table border="1">
		<tr>
			<td>질문 번호</td>
			<td>질문 제목</td>
			<td>아이디</td>
			<td>작성 시간</td>
			<td>조회수</td>
		</tr>
		<c:forEach items="${educationQNABBSListVO.educationQnaBbsList}" var="qnaBBS">
		<tr>
			<td>${qnaBBS.atcId}</td>
			<td>
				<a href="<c:url value='/eduBoard/QNADetail/${qnaBBS.atcId}'/>">
					${qnaBBS.title}
				</a>
			</td>
			<td>${qnaBBS.mbrId}</td>
			<td>${qnaBBS.createDate}</td>
			<td>${qnaBBS.hits}</td>
		</tr>
		</c:forEach>
		<td colspan="5" align="center">
		<form id="searchForm">
			<div style="text-align: center;">
			${educationQNABBSListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm") }
			</div>
			<div>
				<select id ="searchType" name ="searchType">
				<c:if test="${searchVO.searchType eq '' }">
				<option value="selectTitleDesc" selected="selected">제목</option>
				<option value="selectMbrId">아이디</option>
				</c:if>
				<c:if test="${searchVO.searchType eq 'selectTitleDesc' }">
				<option value="selectTitleDesc" selected="selected">제목</option>
				<option value="selectMbrId">아이디</option>
				</c:if>
				<c:if test="${searchVO.searchType eq 'selectMbrId' }">
				<option value="selectTitleDesc">제목</option>
				<option value="selectMbrId" selected="selected">아이디</option>
				</c:if>
				</select>
				<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
				<input type="button" id="searchBtn" value="검색" />
				<input type="button" id="initSearchBtn" value="검색초기화" />
			</div>
		</form>
		</td>
	</table>
	
	<input type="button" id="questionBtn" value="질문하기"/>
	
</body>
</html>