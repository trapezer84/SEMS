<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".hide").hide();
		
		$(".writeReply").click(function(){
			
			//this 현재클릭한 놈의 부모부모부모 즉 table을 찾아감
			var table = $(this).parent().parent().parent();
			console.log(table.text());
	
			var groupId = table.children(":eq(1)").children(":eq(0)").html();
			var parentReplyId = table.children(":eq(1)").children(":eq(1)").html();
			var depth = table.children(":eq(2)").children(":eq(0)").html();
			var orderNo = table.children(":eq(2)").children(":eq(1)").html();
			var replyId = table.children(":eq(3)").children(":eq(0)").html();
			var teamBBSId = $("#teamBBSId").val();
			
			$("#depth").val(parseInt(depth)+ 1);
			$("#parentReplyId").val(replyId);
			$("#groupId").val(groupId);
			$("#orderNo").val(orderNo);
		 
			$.ajax({
	            type: "POST",
	            url: "/team/teamBBS/detail/doGetReReply",
	            data:{ "parentReplyId" : replyId,
						"teamBBSId" : teamBBSId
	            },
	            success: function(response) {
	                $("#subViewDiv").html( response );
	            }
	        });
			

			var form = $("#formWrapper").html();
			$("#formWrapper").detach();
			
			if( form == undefined ){
				$(".formAppender").each(function(index, data){
					if( data.innerHTML != "" ){
						form = data.innerHTML;
					}
				});
				
				$(".formAppender").html("");
			}
			
			var formAppender = table.parent().parent().children(":eq(1)");
			formAppender.html(form);
			formAppender.show();
			
		});
		
		$("#checkBBS").click(function(){
			$.post("<c:url value="/team/checkReply" />"
					, { "teamBBSId" : $("#teamBBSId").val() }
					, function(data) {
						if(!data) {
							alert("통신 실패");
						}
						else if( data == "NO" ){
							alert("댓글이 존재하므로 지울 수 없습니다.");
							return;
						}
						else if( data == "OK" ){
							location.href = "<c:url value='/team/checkBBSDelete/${teamBBS.teamBBSId }' />";
						}
					});
		});
	});
	

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀별 게시판 상세 페이지</title>
</head>
<body>
	
	<table>
		<tr> 
			<td>게시글 아이디</td>
			<td>멤버 아이디</td>
			<td>게시글 제목</td>
			<td>게시글 내용</td>
			<td>작성일</td>
			<td>수정일</td>
			<td>공지여부 Y/N</td>
			<!-- <td>첨부파일List(다운로드)</td> -->
		</tr>
		<tr>
			<td>${teamBBS.teamBBSId }</td>
			<td>${teamBBS.memberId }</td>
			<td>${teamBBS.title }</td>
			<td>${teamBBS.descript }</td>
			<td>${teamBBS.createdDate }</td>
			<td>${teamBBS.modifiedDate }</td>
			<td>${teamBBS.isNotice}</td>
				<!-- EducationController에 있는 "/downloadFile"로 teamBBSId 전송 및 처리 -->
		<c:forEach items="${fileName}" var="fileList">
			<td><a href="/downloadFileList/${teamBBS.teamBBSId}&${fileList.fileId}">${fileList.fileName}</a></td>
		</c:forEach>
			 
		</tr>	
	</table>
	<c:if test="${teamBBS.likeState eq 'Y' }">
		<td>이미 좋아요 체크</td>
		<td>싫어요</td>
	</c:if>
	<c:if test="${teamBBS.dislikeState eq 'Y' }">
		<td>좋아요</td>
		<td>이미 싫어요 체크</td>
	</c:if>
	<c:if test="${teamBBS.likeState eq 'N' && teamBBS.dislikeState eq 'N'  }">
		<td><a href="<c:url value='/team/teamBBS/like/${teamBBS.teamBBSId}'/>">좋아요</a></td>
		<td><a href="<c:url value='/team/teamBBS/dislike/${teamBBS.teamBBSId}'/>">싫어요</a></td>
	</c:if>
	
	 <div>
  	<c:forEach items="${teamBBSReplyListVO.teamBBSReplyList}" var="reply">
   		<div style="padding-left:${ reply.depth * 20}px">
   		  <table width ="100%" border="2px">
			<tr>
				<td width="150px">${ reply.memberId }</td>			
				<td width="*">
					${ reply.descript }<br/>
					<span class="writeReply" style="cursor:pointer;">댓글 보기</span>
				</td>			
			</tr>
			
			<tr class="hide" >
				<td class="groupId">${ reply.groupId }</td>			
				<td class="parentReplyId">${ reply.parentReplyId }</td>	
			</tr>
			<tr class="hide">
				<td class="depth">${ reply.depth }</td>
				<td class="orderNo">${ reply.orderNo }</td>
			</tr>
			<tr class="hide">
				<td colspan="2" class="replyId">${ reply.replyId }</td>
			</tr>
	 	  </table>
	 	  <div class="hide formAppender"></div>
	 	 </div>
 	</c:forEach>
	</div>
	
	<div id="formWrapper" class="hide">
		<table border="1">
			<div id="subViewDiv"></div>
				
			<tr>
				
				<td>
				<form:form id="writeReplyForm" commandName="teamBBSReplyVO" method="POST" action="/team/teamBBS/detail/doWriteReply">
					
						<input type="hidden" id="replyId" name="replyId">
						<input type="hidden" id="teamBBSId" name="teamBBSId" value="<c:out value="${teamBBS.teamBBSId}" />">
						<textarea name="descript" id="descript" rows="5" cols="20"></textarea>
						<input type="hidden" id="depth"  name="depth" value="0"/>
						<input type="hidden" id="parentReplyId" name="parentReplyId" value="0"/>
				 		<input type="hidden" id="groupId" name="groupId" value ="0"/>
	   					<input type="hidden" id="orderNo" name="orderNo" value ="0"/>
						<input type="submit" value="등록" />
						<input type="button" id="cancelBtn" value="취소" />
				
				</form:form>
				</td>
			</tr>
		</table>
	</div>
	
	<br>
	<form:form id="replyForm" commandName="teamBBSReplyVO" method="POST" action="/team/teamBBS/detail/doWriteReply">
	<table border="1">
		<tr>
			<td>
					<input type="hidden" id="replyId" name="replyId">
					<input type="hidden" id="teamBBSId" name="teamBBSId" value="<c:out value="${teamBBS.teamBBSId}" />">
					<textarea name="descript" id="descript" rows="5" cols="20"></textarea>
					<input type="hidden" id="depth"  name="depth" value="0"/>
					<input type="hidden" id="parentReplyId" name="parentReplyId" value="0"/>
			 		<input type="hidden" id="groupId" name="groupId" value ="0"/>
   					<input type="hidden" id="orderNo" name="orderNo" value ="0"/>
					<input type="submit" value="등록" />
					<input type="button" id="cancelBtn" value="취소" />
			</td>
		</tr>
		
	</table>
		${teamBBSReplyListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "replyForm")}
		
	</form:form>
	
	<c:if test="${ teamBBS.memberId eq memberId }">
		<a href="<c:url value='/team/checkBBSModify/${teamBBS.teamBBSId }' />">수정</a> /
		<input type="hidden" id="teamBBSId" name="teamBBSId" value="${ teamBBS.teamBBSId }" />
		<input type="button" id="checkBBS" name="checkBBS" value="삭제"/>
		<%-- <a href="<c:url value='/team/checkBBSDelete/${teamBBS.teamBBSId }' />">삭제</a> --%>
	</c:if>
</body>
</html>