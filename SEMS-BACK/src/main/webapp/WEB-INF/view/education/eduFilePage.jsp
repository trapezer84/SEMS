<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<style type="text/css">
.inputButton {
	border: none;
	border-radius: 5px;
	padding: 6px 12px;
	font-weight: bold;
	text-transform: uppercase;
	color: #FFFFFF;
	background-color: #E05149;
}
</style>
<script type="text/javascript"
   src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
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
	 
	   $("#searchInitBtn").click(function() {
			location.href="<c:url value='/${educationId}/eduFile' />";
		});
	   
	   $("#searchBtn").click( function() {
			
		/* 	if( $("#searchType option:selected").val() == "1"){
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
			} */
			if ($("#searchKeyword").val() == "") {
				alert("검색어를 입력하세요!");
				return;
			}
			movePage('0');
		});
	   
	   $("#noticeWrite").click(function(){
		   location.href="<c:url value='/${educationId}/EduFileNoticeWrtie' />";
	   });
	   
	   
	   $("#massiveSelectCheckBox").click(function() {
			var isChecked = $(this).prop("checked");
			$(".deleteNoticeId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".deleteNoticeId").each(function (index, data) {
				if(data.checked){
					isChecked = data.checked;
				}
			});
			
			if(!isChecked) {
				alert("삭제할 대상을 선택하세요.")
				return;
			}
			
			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#noticeListForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/${educationId}/massiveDeleteNotice" />");
				form.submit();
			}
		});
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>강의 자료 게시판 </h1>
	<hr>
	<table border=1 style="width: 70%">
		<tr>	
			<td style="width: 15px"><input type="checkbox"
				id="massiveSelectCheckBox" /></td>
			<td>eduFileId</td>
			<td>educationId</td>
			<td>memberId</td>
			<td>title</td>
			<td>createDate</td>
			<td>modifyDate</td>
			<td>hits</td>
		</tr>
		<form name="noticeListForm" id="noticeListForm">
		<c:forEach items="${eduNoticeListVO.eduNoticeList}" var="notice">
			<tr style="background-color: #e2c241;">
				<td><input class="deleteNoticeId" name="deleteNoticeId"
				value="${notice.eduNoticeId}" type="checkbox" /></td>
				<td>${ notice.eduNoticeId}</td>
				<td>${ notice.educationId}</td>
				<td>${ notice.memberId}</td>
				<td><a href="<c:url value='/${notice.educationId}/eduFileNotice/detail/${notice.eduNoticeId}' />">${notice.title }</a></td>
				<td>${ notice.createDate}</td>
				<td>${ notice.modifyDate}</td>
				<td>${ notice.hits}</td>
				
			</tr>
		</c:forEach>
		
		<c:forEach items="${ eduFileListVO.eduFileList }" var="eduFile">
				<tr>
				 	<!-- 여기는 관리자가 강의자료 게시판 글을 전체 삭제 할때 알아서 
				 			사용하시면 됩니다!!!! -->
					<td><input class="deleteEduFileId" name="deleteEduFileId"
					value="${eduFile.eduFileId}" type="checkbox" /></td>
					<td>${eduFile.eduFileId }</td>
					<td>${eduFile.educationId }</td>
					<td>${eduFile.memberId }</td>
					<td>${eduFile.title }</td>
					<td>${eduFile.createDate }</td>
					<td>${eduFile.modifyDate }</td>
					<td>${eduFile.hits }</td>
				</tr>
		</c:forEach>
		<tr>
			<td colspan="9" align="center">
				<form name="searchForm" id="searchForm">
				 <div style = "text-align:center;">
						<c:if test="${ eduFileListVO ne null }">
							${eduFileListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div> 
					<div style="text-align: right;">
						<select id="search" name="search" class="inputButton">
							<c:if test="${eduFileSearchVO.searchType eq '1' }">
								<option value="1" selected="selected">아이디</option>
								<option value="2">제목</option>
							</c:if>
							<c:if test="${eduFileSearchVO.searchType eq '2' }">
								<option value="1" >아이디</option>
								<option value="2" selected="selected">제목</option>
							</c:if>
						</select>
						<input type="text" id="searchKeyword"  class="inputButton" name="searchKeyword" value="${ eduFileSearchVO.searchKeyword }"/>
						<input type="button" id="searchBtn" class="inputButton" value="검색" />
						<input type="button" id="searchInitBtn" class="inputButton" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>	
		
	</table> 
	<br/>
						<input type="button" id="noticeWrite" class="inputButton" value="공지사항 글쓰기" />
						<input id="massiveDeleteBtn" class="inputButton" type="button"
													value="공지 일괄삭제" style="cursor: pointer;" />
	
	
</body>
</html>