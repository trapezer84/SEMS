<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#listBtn").click(function() {
			var educationId = $("#eduId").val();
			//location.href="<c:url value='/eduBoard/QNAList/'/>"+educationId; 
			
			$("#searchForm").attr("action", "<c:url value="/eduBoard/QNAList/"/>"+educationId);
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
			
			
		});
		
		$("#replyBtn").click(function() {
			
			if( $("#description").val() == "" ) { 
				alert("내용을 작성하세요.");
				return;
			}
			
			var mbrId = "<c:out value="${ oneQNABBSByAtcId.mbrId }"/>";
			var sessionId = "<c:out value="${ sessionId }"/>";
			
			if( mbrId == sessionId ) {
				alert("질문자가 답변을 등록하실 수 없습니다.");
				return;
			}
			
			var form = $("#replyWriteForm");
			form.attr("action", "<c:url value="/doWriteReply"/>");
			form.submit();
			alert("답변이 등록되었습니다.");
		});
		
		$(".adoptReplyBtn").click(function() {
			
			var replyId = $(this).parent().parent().children(":eq(0)").children(":eq(0)").val();
			
			if ( confirm("답변을 채택하시겠습니까?") ) {
				
				$.post("<c:url value="/doAdoptReply"/>", 
						{
								"replyId" : replyId
								
						}, 
						function(data){
							if(data=="OK") {
								alert("답변 채택 완료");
								location.reload(true);
							}
							else if(data=="FAIL") {
								alert("통신 실패");
							}
					});
			}
			else {
				return;
			}
			
		});
		
		$(".likeBtn").click(function() {
			
			var replyId = $(this).parent().children(":eq(0)").children(":eq(0)").val();
			var writerId = $(this).parent().children(":eq(0)").children(":eq(1)").val();
			var sessionId = $("#sessionId").val();
			
			if ( sessionId == writerId ) {
				alert("작성자가 추천할 수 없습니다.");
				return;
			} 
			
			
			if ( confirm("추천하시겠습니까? 한 번 추천하면 취소할 수 없습니다.")) {
				
				$.post("<c:url value="/plusRecommendReply"/>", 
					{ "replyId" : replyId }
						, function(data) {
					if(data == "OK") {
						alert("추천을 누르셨습니다.");
						location.reload(true);
					}
					else if(data == "FAIL"){
						alert("이미 한번이상 누르셨습니다.");
						location.reload(true);
					}
				});
			}
			else {
				return;
			}
			
		});
		
		$(".dislikeBtn").click(function() {
			
			var replyId = $(this).parent().children(":eq(0)").children(":eq(0)").val();
			var writerId = $(this).parent().children(":eq(0)").children(":eq(1)").val();
			var sessionId = $("#sessionId").val();
			
			if ( sessionId == writerId ) {
				alert("작성자가 반대할 수 없습니다.");
				return;
			} 
			
			if ( confirm("반대하시겠습니까? 한 번 반대하면 취소할 수 없습니다.")) {
				
				$.post("<c:url value="/plusOpposeReply"/>", 
					{ "replyId" : replyId }
						, function(data) {
					if(data == "OK") {
						alert("반대를 누르셨습니다.");
						location.reload(true);
					}
					else if(data == "FAIL"){
						alert("이미 한번이상 누르셨습니다.");
						location.reload(true);
					}
				});
			}
			else {
				return;
			}
			
		});
		
		
		
		
		
	});
</script>
<title>QNA 상세페이지</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>질문 번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성자</td>
		</tr>
		<tr>
			<td>${oneQNABBSByAtcId.atcId}</td>
			<td>${oneQNABBSByAtcId.title}</td>
			<td>${oneQNABBSByAtcId.contents}</td>
			<td>${oneQNABBSByAtcId.mbrId}</td>
		</tr>
	</table>
	
	 
	<br/><br/>
	<c:if test="${ qnaReplyListVO.qnaReplyList.size() gt 0 }">
	<c:forEach items="${qnaReplyListVO.qnaReplyList}" var="qnaReplyList">
	<div>
		<span>
			작성자 : ${qnaReplyList.mbrId}
			<input type="hidden" class="replyId" name="replyId" value="${qnaReplyList.replyId}" />
			<input type="hidden" class="writerId" name="writerId" value="${qnaReplyList.mbrId}" />
		</span><br/>
		<span> 
			날짜 : ${qnaReplyList.createdDate}
			<c:if test="${ oneQNABBSByAtcId.mbrId eq sessionId && qnaReplyList.mbrId ne sessionId && qnaReplyList.adtRpl eq 'N'}">
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" class="adoptReplyBtn" value="답변 채택" />
			</c:if>
			<c:if test="${ qnaReplyList.adtRpl eq 'Y'}">
			<span style="color:red">☆채택된 답변☆</span>
			</c:if> 
		</span><br/>
		<span>
			내용	: ${qnaReplyList.description} 
			
		</span><br/>
		<input type="button" class="likeBtn" value="추천"/> ${qnaReplyList.likeCnt}
		<input type="button" class="dislikeBtn" value="반대"/> ${qnaReplyList.dislikeCnt}
		
		점수 : ${qnaReplyList.qnaReplyPoint}
	</div><br/>
	</c:forEach>
	<form id="pagingForm">
	${qnaReplyListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
	</form>
	<input type="hidden" id="sessionId" value="${sessionId}" />
	
	</c:if>
	
	
	<form:form id="replyWriteForm" commandName="eduBBSReplyVO" method="post">
	<input type="hidden" id="educationId" value="${oneQNABBSByAtcId.eduId}" />
	<input type="hidden" name="atcId" value="${oneQNABBSByAtcId.atcId}"/>
	<textarea id="description" name="description" cols="40" rows="3" placeholder="내용을 입력하세요." >${eduBBSreplyVO.description}</textarea> 
	<br />
		<form:errors path="description" />
	<br />
	<input type="button" id="replyBtn" value="답변 쓰기"/>
	</form:form>
	<input type="button" id="listBtn" value="질문 리스트로"/>
	<c:if test="${oneQNABBSByAtcId.mbrId eq sessionId}">
	<input type="button" id="deleteBtn" value="삭제" />
	</c:if>
	<form id="searchForm">
	<input type="hidden" id="searchKeyword" name="searchKeyword" value="${searchSessionVO.searchKeyword}"/>
	<input type="hidden" id="searchType" name="searchType" value="${searchSessionVO.searchType}"/>
	<input type="hidden" id="eduId" name="eduId" value="${searchSessionVO.educationId}"/>
	</form>
 	
</body>
</html>