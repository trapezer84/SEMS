<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
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
		
		$("#deleteButton").click (function () {
			if ( confirm("정말 삭제 하시겠습니까?") == true ) {
				$("#fileDelete").val("Y");
				$("#deleteFile").hide();
			}
			else {
				return;
			}
		});

		$("#writeButton").click (function () {
			
			if ( $("#title").val() == '' ) {
				alert("제목을 입력하세요!");
				return;
			}
			
			if ( $("#contents").val() == '' ) {
				alert("내용을 입력하세요!");
				return;
			}
			
			$("#writeForm").attr("action", "<c:url value='/education/doWriteFileBBSAction' />" );
			$("#writeForm").submit();
		});
		
		$("#file").change(function () {
			$("#fileName").html("");
			var files = $("#file").val().split(', ');
			for(var i = 0; i < files.length; i++) {
				$("#fileName").append("<div class='deleteFile'>"+files[i]+"</div>");
			}
		});
		
		$("#modifyButton").click(function () {
			
			if ( $("#title").val() == '' ) {
				alert("제목을 입력하세요!");
				return;
			}
			
			if ( $("#contents").val() == '' ) {
				alert("내용을 입력하세요!");
				return;
			}
			
			$("#writeForm").attr("action", "<c:url value='/education/doModifyFileBBSAction' />" );
			$("#writeForm").submit();
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SEMS</title>
</head>
<body>
	<form:form id="writeForm" commandName="educationFileBBSVO" method="POST" enctype="multipart/form-data">
		
		<input id="title" name="title" type="text" class="textInput" placeholder="제목을 입력하세요." style="width: 500px;" value="${educationFileBBSVO.title}"/>
		<br/>
		<br/>
		<textarea id="contents" name="contents" class="textInput" placeholder="내용을 입력하세요." style="width: 500px; height: 400px;">${educationFileBBSVO.contents}</textarea>
		<br/>
		<br/>
		<c:if test="${isModify eq null}">
			<input type="hidden" name="educationId" value="${educationId}"/>
		</c:if>
		<c:if test="${isModify ne null}">
			<input id="deleteButton" class="inputButton" type="button" value="기존 파일 삭제" />
			<span id="deleteFile">
			<c:forEach items="${fileList}" var="file">
				<br/>${file.fileName}
			</c:forEach>
			<br/>
			</span>
			
			<input id="fileDelete" type="hidden"  name="fileDelete" value="N" />
			<input type="hidden" name="articleId" value="${educationFileBBSVO.articleId}" />
		</c:if>

		<input id="file" type="file" name="file" multiple="multiple"/>
		<br/>
		<br/>
		<span id="fileName"></span>
	</form:form>
	<br/>
	<c:if test="${isModify eq null}">
		<input id="writeButton" class="inputButton" type="button" value="등록"/>
	</c:if>
	<c:if test="${isModify ne null}">
		<input id="modifyButton" class="inputButton" type="button" value="수정"/>
	</c:if>
</body>
</html>