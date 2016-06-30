<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
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
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet"/>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script> 
<script type="text/javascript">
	window.onload = function() {
		var backButton = document.getElementById("backButton");
		backButton.onclick = function() {
			location.href= "/education/fileBBS/${educationFileBBS.educationId}";
		};
	};
	
	$(document).ready (function () {
		$(".reReplyBtn").click(function() {
			$(this).parent().parent().parent().children(":eq(2)").slideToggle();
		});
		
		$("#writeReplyButton").click(function () {
			if($("#description").val() == '') {
				alert("질문을 입력하세요!");
				return;
			}
			
			$("#writeReplyForm").attr("action", "<c:url value='/education/fileBBS/doWriteReply' />");
			$("#writeReplyForm").submit();
		});
		
		$("#modifyButton").click (function () {
			$("#modifyAndDeleteForm").attr("action", "<c:url value='/education/modifyFileBBS' />");
			$("#modifyAndDeleteForm").submit();
		});
		
		$("#deleteButton").click (function () {
			$("#modifyAndDeleteForm").attr("action", "<c:url value='/education/fileBBS/doDelete' />");
			$("#modifyAndDeleteForm").submit();
		});
		
		$(".registReReplyBtn").click(function (){
			var articleId = "<c:out value="${ educationFileBBS.articleId }"/>";
			var parentReplyId =  $(this).parent().parent().parent().parent().children(":eq(0)").children(":eq(0)").children(":eq(2)").val();
			var description = $(this).parent().children(":eq(1)").val();
			
			if(description==""){
				alert("내용을 입력해주세요.");
				return;
			}

			if ( confirm("입력한 내용으로 답글을 다시겠습니까?") == true ) {
				
				$.post("<c:url value="/education/fileBBS/doWriteReReply"/>", {
					"articleId" : articleId,
					"parentReplyId" : parentReplyId,
					"description" : description
				}, function(data) {
					if(data == "OK") {
						alert("답글이 등록되었습니다.");
						location.href="<c:url value="/education/fileBBS/detail/${educationFileBBS.articleId}"/>";
					}
					else if(data == "NO"){
						alert("답글 등록이 실패하였습니다.");
						location.href= history.back(-1);
					}
				});
			}
			else {
				return;
			}
		});
	});
	
	function download (id) {
		$.post("<c:url value="/checkClassAttend/" />" + id, { }, function(data) {
			if (!data) {
				alert("인터넷 연결이 끊겼습니다.");
			}
			else if (data == "NO") {
				alert("수강 기록이 없는 교육의 자료는 다운 받을수 없습니다.");
			}
			else if (data == "OK") {
				location.href = "<c:url value="/downloadEducationFile/" />" + id;
			}
		});
	};
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>강의 자료</h2>
	<hr/>
	<table>
		<tr>
			<th>
				교육 번호
			</th>
			<td>
				${educationFileBBS.educationId}
			</td>
		</tr>
		<tr>
			<th>
				강의 자료 번호
			</th>
			<td>
				${educationFileBBS.articleId}
			</td>
		</tr>
		<tr>
			<th>
				강사 아이디
			</th>
			<td>
				${educationFileBBS.memberId}
			</td>
		</tr>
		<tr>
			<th>
				제목
			</th>
			<td>
				${educationFileBBS.title}
			</td>
		</tr>
		<tr>
			<th>
				내용
			</th>
			<td>
				${educationFileBBS.contents}
			</td>
		</tr>
		<tr>
			<th>
				작성일
			</th>
			<td>
				${educationFileBBS.createDate}
			</td>
		</tr>
		<tr>
			<th>
				조회수
			</th>
			<td>
				${educationFileBBS.hits}
			</td>
		</tr>
	</table>
	<form:form commandName="educationFileBBSVO" id="modifyAndDeleteForm" method="POST">
		<input type="hidden" name="educationId" value="${educationFileBBS.educationId}" />
		<input type="hidden" name="articleId" value="${educationFileBBS.articleId}" />
		<input type="hidden" name="title" value="${educationFileBBS.title}" />
		<input type="hidden" name="contents" value="${educationFileBBS.contents}" />
		<input type="hidden" name="createDate" value="${educationFileBBS.createDate}" />
		<input type="hidden" name="hits" value="${educationFileBBS.hits}" />
	</form:form>
	<c:forEach items="${fileList}" var="file">
		<a href="javascript:download(${file.fileId});" >
			${file.fileName} <br/>
		</a>
	</c:forEach>
	<input id="backButton" class="inputButton" type="button" value="목록으로"/>
	<c:if test="${sessionScope._MEMBER_.id eq educationFileBBS.memberId}"> 
		<input id="modifyButton" type="button" class="inputButton" value="수정하기" />
		<input id="deleteButton" type="button" class="inputButton" value="삭제하기" />
	</c:if>
	<hr/>
	<div style="width: 500px;">
		<h3>질문등록</h3>
		
		<c:if test="${ replyList.bbsReplys.size() gt 0 }">
		<div id="tableTwo">
			<c:forEach items="${replyList.bbsReplys}" var="reply">
				<c:if test="${reply.parentReplyId eq '0'}">
					<div>
				</c:if>
				<c:if test="${reply.parentReplyId ne '0'}">
					<div style="padding-left:20px">
				</c:if>
					<div>
						<div>
							<span>작성자 : </span>
							<span>${ reply.memberId }</span>
							<input type="hidden" value="${reply.replyId }"/>
							<br/>
						</div>
						<div>
							<span>날짜 : </span>${ reply.createdDate }
						</div>
					</div>
					<div>
						<div>
							<span>내용 : </span>${ reply.description }
							
							<c:if test="${ reply.parentReplyId eq '0'}">
								<div style="text-align:right; cursor:pointer" class="reReplyBtn">&nbsp; + </div>
							</c:if>
						</div>
					</div>
				
					<div class="registNewReReply">
						<div>
							<form id="registReReplyForm">
								<div>답변내용 :</div> 
								<textarea class="reReplyContent" name="reReplyContent" cols="28" rows="5" placeholder="답글을 입력하세요."></textarea>
								<br/>
								<input type="button" class="registReReplyBtn inputButton" value="답변 달기" />
							</form>
						</div>
					</div>
				</div>
				<hr/>
			</c:forEach>
		</div>
	
		<div style="text-algin:center">
			<table>
				<tr>
					<td colspan="6" style="text-align:center">
						<form id="pagingForm">
								${replyList.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm")}
						</form>
					</td>
				</tr>
			</table>
		</div>
		</c:if>
	</div>
	
	<form:form id="writeReplyForm" commandName="bbsReplyVO" method="post">
		<input type="hidden" name="articleId" value="${educationFileBBS.articleId}">
		<textarea id="description" name="description" cols="51" rows="5" placeholder="내용을 입력하세요.">${qnaVO.description}</textarea>
		<br />
		<br />
		<input id="writeReplyButton" type="button" class="inputButton" value="질문하기" />
	</form:form>
	
</body>
</html>