<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".강의게시판").click( function() {
			$.post("<c:url value="/checkValidationCourseAccess" />", { }, function(data) {
				if(!data) {
					alert("인터넷 연결이 끊겼습니다.");
				} else if (data == "NO") {
					alert("진행 중인 교육을 수강하고 있지 않아 접근이 불가능합니다.");
					document.location.href = "<c:url value="/member/mypage" />";
					return;
				} 
			});
		});
		
		$("#leaveClassBtn").click( function(){
			$.post("<c:url value="/doLeaveClass" />", { }, function(data){
				
				if(!data) {
					alert("인터넷 연결이 끊겼습니다.");
				} 
				else if ( data == "NO" ) {
					alert("오류가 발생했습니다. 다시 시도해주십시오.");
					document.location.href = "<c:url value="/member/myPage" />";
					return;
				}
				else if ( data == "OK" ) { 
					alert("현재 강의가 퇴근 처리되었습니다.");
					document.location.href = "<c:url value="/member/myPage" />";
					return;
				}
			});
		});
	});
</script>
<title>회원 정보</title>
</head>
<body>

	<table border="1" style="border-collapse: collapse; text-align: center; float: left;">
		<c:forEach items="${menuList }" var="menuList" >
			<tr>
				<%-- <td><a href="<c:url value='/member/${menuList.url}' />">${menuList.codeName }</a></td> --%>
				<td class="${menuList.codeName}"><a href="<c:url value='/member/${menuList.url }' />">${menuList.codeName }</a></td>
			</tr>
		</c:forEach>
	</table>

	<div style="width:35%; height:100%; border:thin; border-style:double; border-radius: 5px; float:left; margin-left: 10px;">
		<b>로그인 이력</b><div style="float: right; font-size: 12px; padding-top: 8px; padding-right: 2px;">
							<a href="<c:url value='/member/loginHistory' />">더보기</a></div>
		<hr>
		<c:if test="${loginHistoryList.size() gt 0 }">
			<table style="width:100%; text-align: center; margin: auto;">
				<tr>
					<th>로그인 시간</th>
					<th>로그아웃 시간</th>
				</tr>
					<c:forEach items="${loginHistoryList }" var="loginHistoryList" >
					<tr>
						<td>${loginHistoryList.lgiDt }</td>
						<td>${loginHistoryList.lgoDt }</td>
					</tr>
					</c:forEach>
			</table>
		</c:if>
		<c:if test="${loginHistoryList.size() lt 1 }">
			로그인 이력이 없습니다.
		</c:if>
	</div>
	
	<div style="width:40%; height:316px; border:thin; border-style:double; border-radius: 5px; float:left; margin-left: 10px;">
		<b>교육참가 이력</b><div style="float: right; font-size: 12px; padding-top: 8px; padding-right: 2px;">
							<a href="<c:url value='/member/myPage/educationHistory' />">더보기</a></div>
		<hr>
		<c:if test="${educationHistoryList.size() gt 0 }">
			<table style="width:100%; text-align: center; margin: auto;">
				<tr>
					<th>교육 명</th>
					<th>비용</th>
					<th>신청 날짜</th>
					<th>코멘트</th>
					<th>피드백</th>
					<th>교육 시작일</th>
					<th>교육 종료일</th>
				</tr>
				<c:forEach items="${educationHistoryList }" var="educationHistoryList">
				<tr>
					<td>${ educationHistoryList.educationTitle }</td>
					<td>${ educationHistoryList.costName }</td>
					<td>${ educationHistoryList.educationHistoryDate }</td>
					<td>${ educationHistoryList.cmnt }</td>
					<td>${ educationHistoryList.fdbk }</td>
					<td>${ educationHistoryList.startDate }</td>
					<td>${ educationHistoryList.endDate }</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${educationHistoryList.size() lt 1 }">
			교육참가 이력이 없습니다.
		</c:if>
	</div>
	
	<div style="width:35%; height:316px; border:thin; border-style:double; border-radius: 5px; float:left; margin-left: 6.5%; margin-top:10px; clear: both;">
		<b>문의 이력</b><div style="float: right; font-size: 12px; padding-top: 8px; padding-right: 2px;">
						<a href="<c:url value='/myPage/myQNAList' />">더보기</a></div>
		<hr>
		<c:if test="${qnaList.size() gt 0 }">
			<table style="width:100%; text-align: center; margin: auto;">
				<tr>
					<th>강의 명</th>
					<th>문의 내용</th>
					<th>답변 여부</th>
					<th>문의 날짜</th>
				</tr>
				<c:forEach items="${qnaList }" var="qnaList" >
					<tr>
						<td>${ qnaList.educationTitle }</td>
						<td><a
					href="<c:url value='/myPage/myQNADetail/${qnaList.replyId}'/>"
					onclick="window.open(this.href, 'Place Detail','toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizeable=no, width=530, height=820');return false;"
					target="_blank">${ qnaList.description }</a></td>
						<td>${ qnaList.isAnswered }</td>
						<td>${ qnaList.createdDate }</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${qnaList.size() lt 1}">
			문의 이력이 없습니다.
		</c:if>
	</div>
	
	<div style="width:25%; height:316px; border:thin; border-style:double; border-radius: 5px; float:left; margin-left:10px; margin-top:10px;">
		<b>과제 이력</b><div style="float: right; font-size: 12px; padding-top: 8px; padding-right: 2px;">
						<a href="<c:url value='/myPage/myReportList' />">더보기</a></div>
		<hr>
		<c:if test="${reportReplyList.size() gt 0 }">
			<table style="width:100%; text-align: center; margin: auto;">
				<tr>
					<th>강의 명</th>
					<th>과제 명</th>
					<th>제출 일</th>
				</tr>
				<c:forEach items="${reportReplyList }" var="reportReplyList" >
					<tr>
						<td><a href="<c:url value='/eduDetail/${reportReplyList.eduId}'/>">${ reportReplyList.eduTtl }</a></td>
						<td><a href="<c:url value='/education/detailReport/${reportReplyList.atcId}'/>">${ reportReplyList.title }</a></td>
						<td>${ reportReplyList.createdDate }</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${reportReplyList.size() lt 1}">
			과제 이력이 없습니다.
		</c:if>
	</div>


	<c:if test="${leaveVerified eq 'OK' }">
		<input type="button" id="leaveClassBtn" value="현재 교육 퇴근" />
	</c:if>

</body>
</html>