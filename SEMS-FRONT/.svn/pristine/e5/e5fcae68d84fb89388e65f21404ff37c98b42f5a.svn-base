<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<title>출결이력</title>
</head>
<body>

<p>강의를 선택하세요.</p>

	<c:forEach items="${attendClassList}" var="classList">
	<ul>
	 	<li><a href="<c:url value='/member/myPage/attendHistory/${classList.educationId}' />">
	 		${classList.educationTitle}(${classList.startDate} ~ ${classList.endDate})</a></li>
	</ul>
	</c:forEach>

</body>
</html>