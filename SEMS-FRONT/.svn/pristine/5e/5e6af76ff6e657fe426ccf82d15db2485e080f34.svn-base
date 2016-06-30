<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
   src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">

   $(document).ready(function() {
	   $(document).on("keyup",".onlyText",function(){
			regexp = /[@\#$%<>&\=_\’]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
	   $("#listBtn").click(function() {
			location.href="<c:url value='/${eduQnaVO.educationId}/eduQna'/>";
		});
		
	   $("#replyBtn").click(function() {
			var memberType = '${memberType}';
		   
			if( $("#description").val() == "" ) {
				alert("내용을 작성하세요.");
				return;
			}
			if( memberType == 'TR' || memberType == 'ADM' ) {
				var form = $("#replyWriteForm");
				form.attr("action", "<c:url value="/eduQnaReply/${eduQnaVO.educationId}"/>");
				form.submit();
				alert("답변이 등록되었습니다.");
				
			}else {
				alert("강사/관리자 외 답변을 등록하실 수 없습니다.");
				return;
			}
			
			
		});
		
			//댓글 추천 버튼 클릭시
			$(".likeBtn").click(function (){
				var memberType = '${memberType}';
				var replyId =  $(this).parent().children().eq(0).val();
				
				if ( confirm("추천을 누르시겠습니까? 이후, 반대로 바꿀수 없습니다.") == true ) {
					if( memberType == 'MBR' ) {
						$.post("<c:url value="/addQnaEduReplyLike"/>", {
							"replyId" : replyId
						}, function(data) {
							if(data == "OK") {
								alert("추천을 누르셨습니다.");
								location.href="<c:url value="/detailOfEduQna/${eduQnaVO.eduQnaId}/${eduQnaVO.educationId}"/>";
							}
							else if(data == "FAIL"){
								alert("이미 한번이상 누르셨습니다.");
								location.href="<c:url value="/detailOfEduQna/${eduQnaVO.eduQnaId}/${eduQnaVO.educationId}"/>";
							}
						});
					}else{
						alert("학생만 누를 수 있습니다.");
						return;
					}
					
				}
				else {
					return;
				}
			});
			
			//댓글 반대 버튼 클릭시
			$(".dislikeBtn").click(function (){
				var memberType = '${memberType}';
				var replyId = $(this).parent().children().eq(0).val();
				
				if ( confirm("반대를 누르시겠습니까? 이후, 추천으로 바꿀수 없습니다.") == true ) {
					if( memberType == 'MBR' ) {
						$.post("<c:url value="/addQnaEduReplyDisLike"/>", {
							"replyId" : replyId
						}, function(data) {
							if(data == "OK") {
								alert("반대를 누르셨습니다.");
								location.href="<c:url value="/detailOfEduQna/${eduQnaVO.eduQnaId}/${eduQnaVO.educationId}"/>";
							}
							else if(data == "FAIL"){
								alert("이미 한번이상 누르셨습니다.");
								location.href="<c:url value="/detailOfEduQna/${eduQnaVO.eduQnaId}/${eduQnaVO.educationId}"/>";
							}
						});
					}else{
						alert("학생만 누를 수 있습니다.");
						return;
					}
					
				}
				else {
					return;
				}
			});
	  
   });
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
	
		<tr>
			<th>제목</th>
			<th>내용</th>
			<th>아이디</th>
			<th>작성일</th>
			<th>수정일</th>
			<th>조회수</th>
		</tr>
		<tr>
			<td>${eduQnaVO.title }</td>
			<td>${eduQnaVO.contents }</td>
			<td>${eduQnaVO.memberId }</td>
			<td>${eduQnaVO.createDate }</td>
			<td>${eduQnaVO.modifyDate }</td>
			<td>${eduQnaVO.hits }</td>
		</tr>
		
	</table>
	
	<br/><br/>
	<c:if test="${ qnaReplyList.qnaReplyList.size() gt 0 }">
	<c:forEach items="${qnaReplyList.qnaReplyList}" var="qnaReplyList">
	<div>
		<span>
			작성자 : ${qnaReplyList.mbrId}
		</span><br/>
		<span> 
			날짜 : ${qnaReplyList.createdDate}
		</span><br/>
		<span>
			내용	: ${qnaReplyList.description} 
		</span><br/>
		<span>
			좋아요 : ${qnaReplyList.likeCnt }
			싫어요 : ${qnaReplyList.dislikeCnt }
		</span><br/>
		<span>
		<input type="hidden" id="replyId" name="replyId" value="${qnaReplyList.replyId }"/>
		<input type="button" class="likeBtn" value="추천"/>
		<input type="button" class="dislikeBtn" value="반대"/>
		</span>
	</div><br/>
	</c:forEach>
	<form id="pagingForm">
	${qnaReplyList.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
	</form>
	</c:if>
	
	<form:form id="replyWriteForm" commandName="eduBBSReplyVO" method="post">
	<input type="hidden" name="atcId" value="${eduQnaVO.eduQnaId}"/>
	<textarea id="description" class="onlyText" name="description" cols="40" rows="3" placeholder="내용을 입력하세요." >${eduBBSreplyVO.description}</textarea> 
	<br />
		<form:errors path="description" />
	<br />
	<input type="button" id="replyBtn" value="답변 쓰기"/>
	</form:form>
	<input type="button" id="listBtn" value="질문 리스트로"/>
	
	
</body>
</html>