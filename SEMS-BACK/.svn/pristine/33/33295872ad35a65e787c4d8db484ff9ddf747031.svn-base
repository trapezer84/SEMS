<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
	table {
		width: 600px;
		height: 100px;
		text-align:center;
	}
	
	th {
		background-color:gray;
		color:white;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀 상세 페이지</title>
</head>
<body>

	<div id="teamMember">
		<table border="1">
			<tr>
				<th colspan="7" align="center">팀원</th>
			</tr>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>대학교명</th>
				<th>생일</th>
				<th>연락처</th>
				<th>소속팀</th>
			</tr>
		<c:forEach items="${ members }" var="member">
			<tr>	
				<td>${ member.id }</td>	
				<td>${ member.name }</td>	
				<td>${ member.email }</td>
				<td>${ member.universityName }</td>
				<td>${ member.birthDate }</td>	
				<td>${ member.phoneNumber}</td>
				<td><a href="<c:url value="/otherTeam/${ member.id }"/>">팀 목록</a></td>	
			</tr>
		</c:forEach>
		</table>
	</div>
	<br/>
	<br/>
	<div id="BBS">
		<table border="1">
			<tr>
				<th colspan=7>팀 게시판</th>
			</tr>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>좋아요</th>
				<th>싫어요</th>
				<th>조회수</th>
			</tr>
			
			<c:if test="${ bbss eq '[]' }">
				<tr>
					<td colspan="7"> 등록된 글이 없습니다. </td>
				</tr>
			</c:if>
			<c:if test="${ bbss ne '[]' }">
				<c:forEach items="${ bbss }" var="bbs">
					<tr>
						<td>${ bbs.teamBBSId }</td>
						<td><a href="<c:url value="/teamBBSDetail/${ bbs.teamBBSId }"/>">${ bbs.title }</a></td>
						<td>${ bbs.memberId }</td>
						<td>${ bbs.createdDate }</td>
						<td>${ bbs.likeCount }</td>
						<td>${ bbs.disLikeCount }</td>
						<td>${ bbs.hits }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>