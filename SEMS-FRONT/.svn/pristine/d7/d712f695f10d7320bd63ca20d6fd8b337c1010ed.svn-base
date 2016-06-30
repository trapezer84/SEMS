<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet"/>
<style type="text/css">
.inputButton {
	border:none;
	border-radius:5px;
	padding:6px 12px;
	font-weight:bold;
	text-transform:uppercase;
	color:#FFFFFF;
	background-color:#E05149;
	cursor: pointer;
}
</style>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function () {
		
		$("#startDate").hide();
		$("#endDate").hide();
		$("#searchApplyState").hide();
		$("#searchCost").hide();
		$("#searchEduName").hide();
		
		$("#initSearch").click(function () {
			location.href= "/education/fileBBS/${educationId}";
		});
		
		$("#searchBtn").click( function() {
			if( $("#searchType option:selected").val() == "title"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			else if( $("#searchType option:selected").val() == "contents"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 선택하세요!");
					return;
				}
			}
			else if( $("#searchType option:selected").val() == "date"){
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
					
				if (startDate == "" || endDate == "") {
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
		
		$("#searchType").change(function() {
			var option = $("#searchType option:selected").val();
			if (option == "title") {
				$("#searchKeyword").show();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "contents"){
				$("#searchKeyword").show();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "date"){
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
		else if( option == "contents"){
			$("#searchKeyword").show();
			$("#startDate").hide();
			$("#endDate").hide();
		}
		else if( option == "date"){
			$("#searchKeyword").hide();
			$("#startDate").show();
			$("#endDate").show();
		}
		
		$("#qnaButton").click(function() {
			var educationId = $(this).parent().children().eq(0).val();
			location.href="<c:url value='/"+educationId+"/eduQna' />";
			
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>강의 자료 게시판</h2>
	<hr/>
	<table  border=1 style="width: 65%">
		<tr>
			<th>
				강사명
			</th>
			<th style="width: 200px;">
				제목
			</th>
			<th>
				작성일
			</th>
			<th>
				수정일
			</th>
			<th>
				조회수
			</th>
		</tr>
		<c:forEach items="${eduNoticeListVO.eduNoticeList}" var="notice">
			<tr style="background-color: #e2c241; text-align: center;">
				<c:if test="${notice.noticeType eq 'normal' }">
					<th>공지</th>
				</c:if>
				<c:if test="${notice.noticeType ne 'normal' }">
					<th> 전체 공지</th>
				</c:if>
				<td><a href="<c:url value='/${notice.educationId}/eduFileNotice/detail/${notice.eduNoticeId}' />">${notice.title }</a></td>
				<td>${ notice.createDate}</td>
				<td>${ notice.modifyDate}</td>
				<td >${ notice.hits}</td>
			</tr>
		</c:forEach>
		<c:forEach items="${educationFileBBSList.educationFileBBSVOs}" var="educationFileBBS">
		<tr text-align: center; >
			<td>
				
				${educationFileBBS.memberId}
			</td>
			<td>
				<a href="<c:url value='/education/fileBBS/detail/${educationFileBBS.articleId}'/>">
					${educationFileBBS.title}
				</a>
			</td>
			<td>
				${educationFileBBS.createDate}
			</td>
			<td>
				${educationFileBBS.modifyDate}
			</td>
			<td>
				${educationFileBBS.hits}
			</td>
		</tr>
		</c:forEach>
		<form name="searchForm" id="searchForm" >
			<tr>
				<td colspan="6" align="center">
						${educationFileBBSList.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
				</td>
			</tr>
			<tr>
				<td colspan="6" align="right">
					<select id="searchType" name="searchType">
						<option value="">선택</option>
						<c:if test="${searchVO.searchType eq 'title' }">
							<option id="title" value="title" selected="selected">제목</option>
						</c:if>
						<c:if test="${searchVO.searchType ne 'title' }">
							<option id="title" value="title" >제목</option>
						</c:if>
						<c:if test="${searchVO.searchType eq 'contents' }">
							<option id="contents" value="contents" selected="selected">내용</option>
						</c:if>
						<c:if test="${searchVO.searchType ne 'contents' }">
							<option id="contents" value="contents" >내용</option>
						</c:if>
						<c:if test="${searchVO.searchType eq 'date' }">
							<option id="date" value="date" selected="selected">등록 날짜</option>
						</c:if>
						<c:if test="${searchVO.searchType ne 'date' }">
							<option id="date" value="date" >등록 날짜</option>
						</c:if>
					</select>
					
					<input type="text" id="searchKeyword" class="onlyText" name="searchKeyword" value="${ searchVO.searchKeyword }"/>
					
					<input type="date" name="startDate" id="startDate" value="${searchVO.startDate}" />
					<input type="date" name="endDate" id="endDate" value="${searchVO.endDate}" /> 
					
					<input type="button" value="검색" id="searchBtn" class="inputButton"/>
					<input type="button" value="검색 초기화" id="initSearch" class="inputButton"/>
				</td>
			</tr>
		</form>
	</table>
	<hr/>
	<c:if test="${sessionScope._MEMBER_.id eq teacherId}">
		<form action="<c:url value='/education/writeFileBBS' />" method="post">
			<input type="hidden" name="educationId" value="${educationId}"/>
			<input type="submit" class="inputButton" value="글쓰기"/>
		</form>
	</c:if>
</body>
</html>