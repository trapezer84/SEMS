<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>

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

<script type="text/javascript">
	$(document).ready(function () {
		$("#studentButton").click( function () {
			location.href = "<c:url value="/register/student" />";
		});
		$("#teacherButton").click( function () {
			location.href = "<c:url value="/register/teacher" />";
		});
	});

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div style="width: 970px; text-align: center;">
		<h1>
			약관 동의
		</h1>
		<hr>
		
		<input id="studentButton" class="inputButton" type="button" value="학생"/>
		<input id="teacherButton" class="inputButton" type="button" value="강사"/>

	</div>
	

</body>
</html>