<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<script type="text/javascript"
   src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">

   $(document).ready(function() {
	   
	   $(document).on("keyup",".onlyText",function(){
			regexp = /[@\#$%<>&\=_\’]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
	 
	   $("#searchInitBtn").click(function() {
			location.href="<c:url value='/${educationId}/eduQna' />";
		});
	   
	   $("#searchBtn").click( function() {
			
			if( $("#searchType option:selected").val() == "1"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			
			if( $("#searchType option:selected").val() == "2"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			movePage('0');
		});
	   
	   $("#writeQna").click(function() {
			location.href="<c:url value='/writeEduQna/${educationId}' />";
		});
   });
</script>
<style>
  table {
    width: 50%;
    border-collapse: collapse;
  }
  th, td {
    border-top: 1px solid #bcbcbc;
    border-bottom: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	강의 QnA
	<table>
		<tr>
			<td>제목</td>
			<td>아이디</td>
			<td>작성일</td>
		</tr>
		<c:forEach items="${ eduQnaListVO.eduQnaList }" var="eduQna">
				<tr>
					
					<td><a href="/detailOfEduQna/${eduQna.eduQnaId}/${eduQna.educationId}" >${eduQna.title }</a></td>
					<td>${eduQna.memberId }</td>
					<td>${eduQna.createDate }</td>

				</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<form name="searchForm" id="searchForm">
					<div style = "text-align:center;">
						<c:if test="${ eduQnaListVO ne null }">
							${eduQnaListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div>
					<div style="text-align: right;">
						<select id=searchType name="searchType">
							<c:if test="${not empty searchType }">
								<c:if test="${searchType eq 2 }">
								<option id="title" value="2" selected="selected">제목</option>
								<option id="id" value="1">아이디</option>
								</c:if>
								<c:if test="${searchType eq 1 }">
								<option id="title" value="2">제목</option>
								<option id="id" value="1" selected="selected">아이디</option>
								</c:if>
							</c:if>
							<c:if test="${empty searchType }">
								<option id="title" value="2">제목</option>
								<option id="id" value="1">아이디</option>
							</c:if>
						</select>
						<input type="text" class="onlyText" id="searchKeyword" name="searchKeyword" value="${ eduQnaSearchVO.searchKeyword }"/>
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
						<input type="button" id="writeQna" value="QnA등록" />
					</div>
				</form>
			</td>
		</tr>	
	</table>
	
	
</body>
</html>