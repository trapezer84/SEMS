<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
/* 	$("#write").click(function() {
		if (confirm("정말 등록하시겠습니까?") == true) {
			if ($.trim($('#file').val()) == '') {
				alert("파일을 선택하시오.");
				$('#file').focus();
				return false;
			}
  			if ($('#file').val().indexOf('.docx') == -1 && $('#file').val().indexOf('.pptx') == -1 && $('#file').val().indexOf('.hwp') == -1 && $('#file').val().indexOf('.pdf') == -1) {
				alert("docx, pptx, hwp, pdf 파일만 등록할 수 있습니다.");
				$('#file').focus();
				return false;
			} 
			$("#ReportReplyVO").submit();
		} else {
			return;
		}
	}); */
	
	$("#write").click(function(){
		$.post("<c:url value="/checkEndDate" />"
				, { "articleId" : $("#articleId").val() }
				, function(data) {
					if(!data) {
						alert("통신 실패");
					}
					else if( data == "NO" ){
						alert("마감일이 지났습니다.");
						/* location.href="<c:url value="/myPage/myReportList" />"; */
						return;
					}
					else if( data == "OK" ){
						if (confirm("정말 등록하시겠습니까?") == true) {
							if ($.trim($('#file').val()) == '') {
								alert("파일을 선택하시오.");
								$('#file').focus();
								return;
							}
							$("#ReportReplyVO").submit();
						} else {
							return;
						}
					}
				});
	});
});

</script>
</head>
<body>
	<input type="hidden" id="articleId" name="articleId"  value="${ educationReportVO.articleId }"/>
	<div style="width: 50%; heght: 100%; border: thin; border-style: double; border-radius: 5px;">
		${ educationReportVO.title } <br />
		<hr>
		강사 명 : ${ educationReportVO.memberId } <br />
		<hr>
		${ educationReportVO.contents } <br />
		<hr>
		시작 일 : ${ educationReportVO.startDate } <br /> 
		마감 일 : ${ educationReportVO.endDate } <br />
		<hr>
		
		<c:if test="${reportFile.size() gt 0 }"> 
			첨부파일 <br/>
			<c:forEach items="${reportFile}" var="file">
				<a href="/downloadFile/${educationReportVO.articleId }" >
					${file.fileName }
				</a>
			</c:forEach>
			<hr>
		</c:if>
		
		<c:if test="${loginMember.memberType eq 'TR' && loginMember.id eq educationReportVO.memberId }">
			<a href="<c:url value='/education/modifyReport/${educationReportVO.articleId }' />">수정</a> /
			<a href="<c:url value='/education/deleteReport/${educationReportVO.articleId }' />">삭제</a> / 
		</c:if>
			<a href="<c:url value='/education/reportList/${educationReportVO.educationId }' />">목록</a>
		<c:if test="${loginMember.memberType eq 'MBR' }">
			<a href="<c:url value='/myPage/myReportList' />">내 과제 이력</a>
		</c:if>
	</div>

	
	
	<c:if test="${ reportReplyListVO.reportReplyList.size() gt 0 }">
				<div id="tableTwo">
					<c:forEach items="${reportReplyListVO.reportReplyList}" var="rprp">
							<div>
								<span>작성자 : </span>
								<span>${ rprp.mbrId }</span>
								<br/>
							</div>
							<div>
								<span>파일 : </span>
								<a href="/downloadFile/${rprp.rptRplId }" >
										${ rprp.fileName }
								</a>
								
							</div>
						<hr/>
					</c:forEach>
				</div>

				<div style="text-algin:center">
					<table>
						<tr>
							<td colspan="6" style="text-align:center">
								<form id="pagingForm">
									<c:if test="${ reportReplyListVO ne null }">
										${reportReplyListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm")}
									</c:if>
								</form>
							</td>
						</tr>
					</table>
				</div>

			</c:if>



<form:form commandName="ReportReplyVO" method="post" action="/doReportSubmit" enctype="multipart/form-data">
	<input type="hidden" id="bbsId" name="bbsId" value="${ educationReportVO.articleId }">
	<input type="file" name="file" id="file" tabindex="2" style="height: 45px">
	<br />
	<br />
	<input type="button" id="write" value="과제등록" />
</form:form>
</body>
</html>