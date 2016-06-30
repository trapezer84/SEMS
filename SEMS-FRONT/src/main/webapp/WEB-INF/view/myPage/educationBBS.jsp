<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>

	<div>
		<span>${educationVO.educationTitle} 게시판</span>
	</div>
	
	<br/>
	<br/>

	<div>
		<span>강의 자료 <a href="<c:url value="/education/fileBBS/${educationVO.educationId}" />">더보기</a></span>
		<table>
			<tr></tr>
			<c:forEach items="${educationItems}" var="item">
				<tr>
					<td>></td>
					<td>
					<a href="<c:url value='/education/fileBBS/detail/${item.articleId}'/>">
						${item.title}</a>
					</td>
					<td>${item.createDate}</td>
				</tr>
			</c:forEach>
		
		</table>
	</div>
	
	<br/>
	<br/>
	
	<div>
		<span>질문/답변 <a href="<c:url value="/eduBoard/QNAList/${educationVO.educationId}" />">더보기</a></span>
		<table>
			<tr></tr>
			<c:forEach items="${educationQNAList}" var="QNA">
				<tr>
					<td>></td>
					<td><a href="<c:url value='/eduBoard/QNADetail/${QNA.atcId}'/>">
					${QNA.title}
				</a></td>
					<td>${QNA.contents}</td>
					<td>${QNA.createDate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<br/>
	<br/>
	
	<div>
		<span>과제 <a href="<c:url value="/education/reportList/${educationVO.educationId}" />">더보기</a></span>
		<table>
			<tr></tr>
			<c:forEach items="${educationReportList}" var="report">
				<tr>
					<td>></td>
					<td><a href="<c:url value='/education/detailReport/${report.articleId }' />">
						${report.title }
					</a></td>
					<td>${report.contents}</td>
					<td>${report.startDate}</td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
	
	<br/>
	<br/>
	
	<div>
		<span>시험 <a href="<c:url value="/eduBoard/${educationVO.educationId}" />">더보기</a></span>
	</div>
	
	<br/>
	<br/>
	
	<div>
		<span>강의 평가 <a href="<c:url value="/eduBoard/${educationVO.educationId}" />">더보기</a></span>
	</div>
	
		<br/>
	<br/>
	
	<div>
		<span>최근 접속 사용자 </span>
		<table>
			<tr></tr>
			<c:forEach items="${memberList}" var="member">
				<tr>
					<td>${member}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</div>

</body>
</html>