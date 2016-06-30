<!-- 이기연 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${qnaVO.replyId} detail page......
<table border="1">
	<tr>
		<th colspan="2">문의 내용</th>
	</tr>
	<tr>
		<td colspan="2">${qnaVO.description}</td>
	</tr>
	<tr>
		<th>답변 사항</th>
		<th>답변자</th>
	</tr>
	<c:forEach items="${qnaListVO.qnaList}" var="qnaAnswerVO">
	<tr>
		<td>${qnaAnswerVO.description}</td>
		<td>${qnaAnswerVO.mbrId}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>