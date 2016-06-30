<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀 게시판 댓글</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#update").click(function() {
			if (confirm("정말 등록하시겠습니까?") == true) {
				if ($.trim($('#title').val()) == '') {
					alert("타이틀을 입력하시오.");
					$('#title').focus();
					return false;
				}
				if ($.trim($('#descript').val()) == '') {
					alert("내용을 입력하시오.");
					$('#descript').focus();
					return false;
				}
				
				/* if ($.trim($('#file').val()) == '') {
					alert("파일을 선택하시오.");
					$('#file').focus();
					return false;
				} */
				
				var form = $("#modifyForm");
				form.submit();
			}
		});
		
		<c:if test="${not empty teamBBS.fileName }">
		$("#file").hide();
		</c:if>
		/* $(".checkDelete").click(function() {
			var isChecked = $(this).prop("checked");
			if (isChecked) {
				$(".uploadedFile").css({
					"text-decoration" : "line-through"
				});
				$("#file").show();
				$("#checkFileUpdate").val('0');
			} else {
				$(".uploadedFile").css({
					"text-decoration" : "none"
				});
				$("#file").hide();
			}
		}); */
	});
</script>

</head>
<body>
	<form:form id="modifyForm" commandName="teamBBS" method="POST" action="/team/doModifyAction" enctype="multipart/form-data">
		<table>
			<tr>
				<td>
					<input type="hidden" id="teamBBSId" name="teamBBSId" value="${ teamBBS.teamBBSId }"/>
					<c:if test="${ teamBBS.isNotice eq 'Y' }">
					공지사항 : <input type="checkbox" id="isNotice" name="isNotice" checked="checked">
					</c:if>
					<c:if test="${ teamBBS.isNotice eq 'N' }">
					공지사항 : <input type="checkbox" id="isNotice" name="isNotice">
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="title" id="title" size="50" style="width:550px;" value="${ teamBBS.title }" />
				</td>
			</tr>
			<tr>
				<td>
					<textarea name="descript" rows="20" id="descript" cols="49" style="width:550px;" >${ teamBBS.descript }</textarea>
				</td>
			</tr>
			<tr>
				<td>
					파일 이름 : ${ FileName }
					<input type="file" name="file" />
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" id="update" value="저장" />
				</td>
			</tr>
		</table>
	</form:form>
	
	
</body>
</html>