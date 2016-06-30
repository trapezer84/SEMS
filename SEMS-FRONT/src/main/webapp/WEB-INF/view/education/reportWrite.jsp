<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
$(document).ready( function() {
		
	$("#submitBtn").click(function() {
		
		if ( $("#title").val() == null || $("#title").val() == "" ) {
			alert("과제 제목을 입력하세요");
			$("#title").focus();
			return;
		}
		
		if ( $("#contents").val() == null || $("#contents").val() == "" ) {
			alert("과제 내용을 입력하세요");
			$("#contents").focus();
			return;
		}
		
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
		
		var form = $("#reportWriteForm");
		form.attr("action", "<c:url value="/education/doReportWriteAction" />");
		form.submit();
	});
	
	$("#modifyBtn").click(function() {
		
		if ( $("#title").val() == null || $("#title").val() == "" ) {
			alert("과제 제목을 입력하세요");
			$("#title").focus();
			return;
		}
		
		if ( $("#contents").val() == null || $("#contents").val() == "" ) {
			alert("과제 내용을 입력하세요");
			$("#contents").focus();
			return;
		}
		
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
		
		var form = $("#reportWriteForm");
		form.attr("action", "<c:url value="/education/doModifyReportAction" />");
		form.submit();
	});
	
	
});
</script>
</head>
<body>
	
	<form:form commandName="educationReportVO" method="post"
			id="reportWriteForm" enctype="multipart/form-data">
		
		<input type="hidden" id="educationId" name="educationId" value="${educationReportVO.educationId}" />
		<input type="hidden" id="articleId" name="articleId" value="${educationReportVO.articleId}" />
		
		과제 명 : <input type="text" id="title" name="title" placeholder="과제 명" value="${educationReportVO.title}"/>
		<br/>
		<form:errors path="title" style="color:red;" />
		<br/>
		
		과제 내용 : <textarea id="contents" name="contents" >${educationReportVO.contents}</textarea>
		<br/><br/>		
		
		시작 일 : <input type="datetime-local" id="startDate" name="startDate" value="${educationReportVO.startDate}" />
		<br/>
		<form:errors path="startDate" style="color:red;" />
		<br/>
		
		종료 일 : <input type="datetime-local" id="endDate" name="endDate" value="${educationReportVO.endDate}"/>
		<br/>
		<form:errors path="endDate" style="color:red;" />
		<br/>
		
		<input type="file" name="file" id="file" value="abc.jpg"/>
		<br/><br/>	
		
		<c:if test="${educationReportVO.articleId eq null }">
			<input type="button" id="submitBtn" name="submitBtn" value="과제 등록" />
		</c:if>
		<c:if test="${educationReportVO.articleId ne null }">
			<input type="button" id="modifyBtn" name="modifyBtn" value="과제 수정" />
		</c:if>
				</form:form>
	
</body>
</html>