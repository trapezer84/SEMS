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
<script type="text/javascript">
	$(document).ready(function() {
		var count = 1;
		$("#submit").click(function() {
			
			// 제목 Validation
			if ( $("#title").val() == "" ) {
				alert("제목을 입력해주세요.");
				$("#title").focus();
				return false;
			}
			
			// 내용 Validation
			if ( $("#descript").val() == "") {
				alert("내용을 입력해주세요.");
				$("#descript").focus();
				return false;
			}
			
			if (confirm("게시물을 등록하시겠습니까?")) {
				var form = $("#teamBBSForm");
				form.attr("action", "<c:url value="/team/teamBBS/doWrite"/>");
				form.submit();
			} else {
				return false;
			}
		});
		$("#cancel").click(function() {
			if (confirm("취소하시겠습니까?\n ※취소시, 등록하시던 데이터가 사라집니다.")) {
				location.href = "<c:url value='/team/teamBBS/board' />";
			} else {
				return;
			}
		});
		
		$("#btnAddFile").click(function() {
			if (count <= 2) {
				var tmp = '<input type="file" name="file" id="file" /><br/>';
				$("#fileTransper").append(tmp);
				count++;
			} else {
				alert("최대 3개까지만 등록이 가능합니다.");
			}
		});		
		
		function handleKeyUp(e) {
			e = e || event;
			var key = e.keyCode || e.which;
			if ( key == 13 ) {
				return true;
			} else {
				return false;
			}
		}		
	});
</script>
</head>
<body>
	
	<form:form commandName="teamBBSVO" method="POST" id="teamBBSForm" enctype="multipart/form-data">
		<table>
			<tr>
				<td>	
					공지사항 : <input type="checkbox" id="isNotice" name="isNotice"  tabindex="1">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="title" id="title" size="50" style="width:550px;" placeholder="제목을 입력하세요."  tabindex="2" />
				</td>
			</tr>
			<tr>
				<td>
					<textarea id="descript" name="descript" style="width:550px; height: 550px;" placeholder="내용을 입력하세요." tabindex="3" ></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="file" name="file" id="file" /><input type="button" id="btnAddFile" name="btnAddFile" value="+" tabindex="4" /><br/>
					<span id="fileTransper"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="submit" id="submit" value="submit" onkeyup="return handleKeyUp(event)" tabindex="5" />
					<input type="button" id="cancel" value="cancel" onkeyup="return handleKeyUp(event)" tabindex="6" />
				</td>
			</tr>
		</table>
	</form:form>
	
	
</body>
</html>