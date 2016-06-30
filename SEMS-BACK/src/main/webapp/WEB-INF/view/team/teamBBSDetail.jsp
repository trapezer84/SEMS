<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
	table {
			width:400px;
			height:80px;
	}
	th {
		background-color:gray;
		color:white;
	}
	
	td {
		text-align:center;
		}
	td#descript {
		height:400px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀 게시판 상세보기</title>
</head>
<body>

	<table border="1">
			<c:if test="${ bbsVO eq null }">
			<tr>
				<td colspan="11">등록된 글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${ bbsVO ne null }">
		<tr>
			<th colspan="4">제목</th>
		</tr>
		<tr>
			<td colspan="4">${ bbsVO.title }</td>
		</tr>
		<tr>
			<th colspan="2">팀번호</th>
			<th>글번호</th>
			<th>조회수</th>
		</tr>
		<tr>
				<td colspan="2">${ bbsVO.teamId }</td>
				<td>${ bbsVO.teamBBSId }</td>
				<td>${ bbsVO.hits }</td>
		</tr>
		<tr>
			<th>글쓴이</th>
			<th>좋아요</th>
			<th>싫어요</th>
			<th>공지여부</th>
		</tr>
		<tr>
			<td>${ bbsVO.memberId }</td>
			<td>${ bbsVO.likeCount }</td>
			<td>${ bbsVO.disLikeCount }</td>
			<td>${ bbsVO.isNotice }</td>
		</tr>
		<tr>
			<th colspan="2">작성한 날짜</th>
			<th colspan="2">수정한 날짜</th>
		</tr>
		<tr>
			<td colspan="2">${ bbsVO.createdDate }</td>
			<td colspan="2">
				<c:if test="${ bbsVO.modifiedDate eq null}">수정 이력 없음</c:if>
				<c:if test="${ bbsVO.modifiedDate ne null}">${ bbsVO.modifiedDate }</c:if>
				</td>
			
		</tr>
		<tr>
		 <th colspan="4">내용</th>
		</tr>
		<tr>
			<td id="descript" colspan="4">${ bbsVO.descript }</td>
		</tr>
		</c:if>
	</table>
	<br/>
	<br/>
	<br/>	
	<table border="1">
		<tr>
			<th>댓글ID</th>
			<th>팀게시글ID</th>
			<th>댓글쓴이</th>
			<th>글</th>
			<th>댓글그룹ID</th>
			<th>상위댓글ID</th>
			<th>DEPTH</th>
			<th>좋아요</th>
			<th>싫어요</th>
			<th>순번</th>
		</tr>
		<c:if test="${ bbsRplVO eq '[]' }">
			<tr>
				<td colspan="10"> 등록된 글이 없습니다. </td>	
			</tr>
		</c:if>
		<c:if test="${ bbsRplVO ne '[]' }">
		<c:forEach items="${ bbsRplVO }" var="bbsRpl">
				<tr>
					<td>${ bbsRpl.replyId }</td>
					<td>${ bbsRpl.teamBBSId }</td>
					<td>${ bbsRpl.mbrId }</td>
					<td>${ bbsRpl.descript }</td>
					<td>${ bbsRpl.groupId }</td>
					<td>${ bbsRpl.parentReplyId }</td>
					<td>${ bbsRpl.depth }</td>
					<td>${ bbsRpl.likeCnt }</td>
					<td>${ bbsRpl.disLikeCount }</td>
					<td>${ bbsRpl.orderNo }</td>
				</tr>
		</c:forEach>
		</c:if>
	</table>

</body>
</html>