<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#applyEdu").click(function(){
			$.post("<c:url value='/doApplyEducation'/>"
					, { "educationId" : $("#eduId").val(),
						"educationType" : $("#eduType").val(),
						"startDate" : $("#startDate").val(),
						"endDate" : $("#endDate").val(),
						"startTime" : $("#startTime").val(),
						"maxMember" : $("#maxMember").val() }
					, function(data){
						if (data == "OK") {
							alert("신청완료!");
							location.href="<c:url value='/educationList'/>";
							document.getElementById("#applyEdu").disabled=true;
						}
						else if( data == "TYPE_FAIL"){
							alert("이미 신청하신 기간의 교육타입(주간/야간)입니다.");
							location.href="<c:url value='/educationList'/>";
						}
						else if( data == "EX_MAX_MEM"){
							if(confirm("정원이 초과됐습니다. 예약 신청하시겠습니까?")) {
								doReserveEducation();
							}
							else {
								location.href="<c:url value='/educationList'/>";
							}
						}
						else if( data == "DATE_FAIL"){
							alert("이미 시작한 교육입니다.");
						}
						else {
							alert("에러가 발생했습니다.");
						}
					});
		});
		
		function doReserveEducation () {
			$.post("<c:url value='/doReserveEducation'/>"
					, { "educationId" : $("#eduId").val() }
					, function(data){
						if (data == "OK") {
							alert("예약 완료!");
							location.href="<c:url value='/educationList'/>";
							document.getElementById("#applyEdu").disabled=true;
						}
						else if( data == "TYPE_FAIL"){
							alert("이미 신청하신 기간의 교육타입(주간/야간)입니다.");
							location.href="<c:url value='/educationList'/>";
						}
						else {
							alert("에러가 발생했습니다.");
						}
					});
		}
		
		$("#cancleEdu").click(function() {
				var educationId = $("#eduId").val();
				location.href = "<c:url value='/doCancelEducation/"+ educationId +"'/>";
			});

			$("#goList").click(function() {
				location.href = "<c:url value='/educationList'/>";
			});

			$(".reReplyBtn").click(function() {
				$(this).parent().parent().parent().children(":eq(2)").slideToggle();
			});

			
			//대댓글(답글) 등록 클릭시
			$(".registReReplyBtn").click(function (){

				var eduId = "<c:out value="${ education.educationId }"/>";
				var id =  $(this).parent().parent().parent().parent().children(":eq(0)").children(":eq(0)").children(":eq(1)").html();
				var replyId =  $(this).parent().parent().parent().parent().children(":eq(0)").children(":eq(0)").children(":eq(2)").val();
				var description = $(this).parent().children(":eq(1)").val();
				
				if(description==""){
					alert("내용을 입력해주세요.");
					return;
				}

				if ( confirm("입력한 내용으로 답글을 다시겠습니까?") == true ) {
					
					$.post("<c:url value="/doReReplyInsert"/>", {
						"replyId" : replyId,
						"eduId" : eduId,
						"id" : id,
						"description" : description
					}, function(data) {
						if(data == "OK") {
							alert("답글이 등록되었습니다.");
							location.href="<c:url value="/eduDetail/${ education.educationId }"/>";
						}
						else if(data == "FAIL"){
							alert("답글 등록이 실패하였습니다.");
							location.href= history.back(-1);
						}
					});
				}
				else {
					return;
				}
			});
			
			//댓글 추천 버튼 클릭시
			$(".likeBtn").click(function (){

				var replyId =  $(this).parent().parent().parent().children(":eq(0)").children(":eq(0)").children(":eq(2)").val();
				
				if ( confirm("추천을 누르시겠습니까? 이후, 반대로 바꿀수 없습니다.") == true ) {
					
					$.post("<c:url value="/plusReReplyLike"/>", {
						"replyId" : replyId
					}, function(data) {
						if(data == "OK") {
							alert("추천을 누르셨습니다.");
							location.href="<c:url value="/eduDetail/${ education.educationId }"/>";
						}
						else if(data == "FAIL"){
							alert("이미 한번이상 누르셨습니다.");
							location.href="<c:url value="/eduDetail/${ education.educationId }"/>";
						}
					});
				}
				else {
					return;
				}
			});
			
			//댓글 반대 버튼 클릭시
			$(".dislikeBtn").click(function (){

				var replyId =  $(this).parent().parent().parent().children(":eq(0)").children(":eq(0)").children(":eq(2)").val();
				
				if ( confirm("반대를 누르시겠습니까? 이후, 추천으로 바꿀수 없습니다.") == true ) {
					
					$.post("<c:url value="/plusReReplyDislike"/>", {
						"replyId" : replyId
					}, function(data) {
						if(data == "OK") {
							alert("반대를 누르셨습니다.");
							location.href="<c:url value="/eduDetail/${ education.educationId }"/>";
						}
						else if(data == "FAIL"){
							alert("이미 한번이상 누르셨습니다.");
							location.href="<c:url value="/eduDetail/${ education.educationId }"/>";
						}
					});
				}
				else {
					return;
				}
			});	
			
			//답변 내용 스크립트 작성 막기(특수문자 불가)
			$(".reReplyContent").keyup(function(event) {
	         regexp = /[\+*^!@\#$%<>&\()\=\’ \\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;

	         v = $(this).val();
	         if (regexp.test(v)) {
	            alert("특수문자를 포함할 수 없습니다.");
	            $(this).val(v.replace(regexp, ''));
	         }
	         
	      });
			
		});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	
</script>
<title>교육 상세 조회</title>
</head>
<body>
	<div id="table_calendar">
		<div id="table">
			<table id="table_one">
				<tr>
					<th colspan="2">교육 상세 조회</th>
				</tr>
				<tr>
					<td>강의 아이디</td>
					<td>${ education.educationId }</td>
				</tr>
				<tr>
					<td>교육 카테고리</td>
					<td>${ education.educationCategory }</td>
				</tr>
				<tr>
					<td>교육명</td>
					<td>${ education.educationTitle }</td>
				</tr>
				<tr>
					<td>강사명</td>
					<td>${ education.memberId }</td>
				</tr>
				<tr>
					<td>정원</td>
					<td>${ education.maxMember }</td>
				</tr>
				<tr>
					<td>강의장소</td>
					<td>${ education.educationLocation }</td>
				</tr>
				<tr>
					<td>커리큘렴 파일</td>
					<td>${ education.educationCurriculum }</td>
				</tr>
				<tr>
					<td>강의 소개</td>
					<td>${ education.educationIntroduce }</td>
				</tr>
				<tr>
					<td>강의 시작 날짜</td>

					<td>${ education.startDate }</td>
				</tr>
				<tr>
					<td>강의 종료 시간</td>
					<td>${ education.endDate }</td>
				</tr>
				<tr>
					<td>강의 형태</td>
					<td>${ education.typeName }</td>
				</tr>
				<tr>
					<td>유료/무료</td>
					<td>${ education.costName }</td>
				</tr>
			</table>
			<br /> 
			<input type="button" value="검색 목록" id="goList" name="goList" />
			<br /> 
			<br />
			<h3>문의사항</h3>
			<hr/>
			<c:if test="${ eduReplyListVO.qnaList.size() gt 0 }">
				<div id="tableTwo">
					<c:forEach items="${eduReplyListVO.qnaList}" var="qna">
						<div style="padding-left:${qna.depth * 20}px">
							<div>
								<div>
									<span>작성자 : </span>
									<span>${ qna.mbrId }</span>
									<input type="hidden" value="${qna.replyId }"/>
									<br/>
								</div>
								<div>
									<span>날짜 : </span>${ qna.createdDate }
								</div>
							</div>
							<div>
								<div>
									<span>내용 : </span>${ qna.description }
									
									<c:if test="${ qna.depth eq 1 and memberType eq 'ADM'|| 'TR'}">
										<div style="text-align:right; cursor:pointer" class="reReplyBtn">&nbsp; + </div>
									</c:if>
								</div>
							</div>
						
							<div class="registNewReReply">
								<div>
									<form id="registReReplyForm">
										<div>답변내용 :</div> 
										<textarea class="reReplyContent" name="reReplyContent" cols="28" rows="5" placeholder="답글을 입력하세요."></textarea>
										<div style="text-align:right; cursor:pointer" class="registReReplyBtn">답글 등록</div>
									</form>
								</div>
							</div>
							<c:if test="${ qna.depth eq 2 }">
								<div>
									<div>
										<span class="likeBtn" style="cursor:pointer">추천 : </span>${ qna.likeCnt } &nbsp;&nbsp;
										<span class="dislikeBtn" style="cursor:pointer">반대 : </span>${ qna.dislikeCnt }
									</div>
								</div>
							</c:if>
						</div>
						<hr/>
					</c:forEach>
				</div>

				<div style="text-algin:center">
					<table>
						<tr>
							<td colspan="6" style="text-align:center">
								<form id="pagingForm">
									<c:if test="${ eduReplyListVO ne null }">
										${eduReplyListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm")}
									</c:if>
								</form>
							</td>
						</tr>
					</table>
				</div>

			</c:if>
			<br />
			<form:form commandName="qnaVO" method="post" action="/doWriteComment">
				<input type="hidden" name="educationId" value="${ education.educationId }">
				<textarea id="description" name="description" cols="51" rows="5" placeholder="내용을 입력하세요.">${qnaVO.description}</textarea>
				<br />
				<form:errors path="description" />
				<br />
				<input type="submit" value="댓글쓰기" />
			</form:form>
			<br /> 
		<input type="hidden" value="${ education.educationId }" id="eduId" />
		<input type="hidden" value="${ education.educationType }" id="eduType" />
		<input type="hidden" value="${ education.startDate }" id="startDate" />
		<input type="hidden" value="${ education.endDate }" id="endDate" />
		<input type="hidden" value="${ education.startTime }" id="startTime" />
		<input type="hidden" value="${ education.maxMember }" id="maxMember" />
		
		<c:if test="${ status eq ''}">
		<input type="button" id="applyEdu" name="applyEdu" value="교육 참가 신청" />
		</c:if>
		
		<c:if test="${ status eq '참가신청'}">
		<input type="button" id="cancleEdu" name="cancleEdu" value="교육 참가 취소" />
		</c:if>
		<c:if test="${ status eq '예약신청'}">
		<input type="button" id="cancleEdu" name="cancleEdu" value="교육 예약 취소" />
		</c:if>
		<br/>
		
		<c:forEach items="${fileList}" var="fileVO">
		파일다운로드: <a href="/downloadFile/${ education.educationId }">${fileVO.fileName}</a>
		</c:forEach>
		</div>
		
		<div id="calendar">
			<jsp:include page="/WEB-INF/view/education/calendar.jsp"></jsp:include>
		</div>
	</div>
	
</body>
</html>