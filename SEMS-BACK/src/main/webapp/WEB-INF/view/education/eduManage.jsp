<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<script type="text/javascript"
   src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">

   $(document).ready(function() {
	 
	    $("#searchDate").hide();
	   
		$("#search").on("change", function(){
			if( $("#search option:selected").val() == "date"){
				$("#searchDate").show();
				$("#searchKeyword").hide();
		    }else{
			    $("#searchDate").hide();
				$("#searchKeyword").show();
			}
		});
	   
	   $("#searchInitBtn").click(function() {
			location.href="<c:url value='educationHistory' />";
		});
	   $("#searchBtn").click( function() {
			if( $("#search option:selected").val() == "educationName"){
				$("#searchType").val($("#search option:selected").val());
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}	
			}
			if( $("#search option:selected").val() == "memberId"){
				$("#searchType").val($("#search option:selected").val());
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			if( $("#search option:selected").val() == "date"){
				$("#searchType").val($("#search option:selected").val());
				if ($("#searchDate").val() == ""){
					alert("날짜를 입력하세요");
					return;
				}
			}
			movePage('0');
		});
		
      $("#joinAplyBtn").click(function() {
            var eduHistoryId = $("#eduHistoryId").val();
                                 location.href = "<c:url value='/joinAply/"+eduHistoryId+"'/>";
                              });

      $("#joinCnclBtn").click(function() {
    	    var educationHistoryId = $("#historyId2").val();
			var memberId = $("#memberId2").val();
			var description = $("#description").val();
		
			if( description == null || description ==""){
				alert("내용을 입력해주세요.")
				return;
			}
			console.log(educationHistoryId);
			console.log(memberId);
			location.href = "<c:url value='/joinCncl/"+educationHistoryId+"/"+memberId+"/"+description+"'/>";
	   }); 
		
		$(".joinaply").click(function(){
			 $("#historyId").val($(this).data('id'));
			 $("#memberId").val($(this).data('whatever'));
	   });
		
		$(".cnclaply").click(function(){
			 $("#historyId2").val($(this).data('id'));
			 $("#memberId2").val($(this).data('whatever'));
	   });
   });
</script>
<style>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    border-top: 1px solid #bcbcbc;
    border-bottom: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<td>교육명</td>
			<td>학생아이디</td>
			<td>신청자아이피</td>
			<td>상태변경일</td>
			<td>신청 상태</td>
			<td>신청(거절/포기) 사유</td>
			<!-- 상태마다 승인과 거절이있다. -->
			<td>승인버튼</td>
			<td>거절버튼</td>
		</tr>
		<c:forEach items="${ eduHistoryListVO.educationHistoryList }"
			var="eduHistory">
			<input type="hidden" id="eduHistoryId"
				value="${eduHistory.educationHistoryId}" />
			<c:if test="${ eduHistory.state eq 'EDU_JN_A' }">
				<tr>
					<td>${eduHistory.educationTitle }</td>
					<td>${eduHistory.memberId }</td>
					<td>${eduHistory.ip }</td>
					<td>${eduHistory.educationHistoryDate }</td>
					<td>${eduHistory.state }</td>
					<td><c:if test="${eduHistory.cmnt ne null }">
						${eduHistory.cmnt }
					</c:if> <c:if test="${eduHistory.fdbk ne null }">
						${eduHistory.fdbk }
					</c:if></td>
					<td>
						<button class="btn btn-primary btn-sm joinaply" data-toggle="modal" data-id="${eduHistory.educationHistoryId}" data-whatever="${eduHistory.memberId }"
		data-target="#JoinAplyModal">교육참여승인</button>
					</td>
					<td>
						<button class="btn btn-primary btn-sm cnclaply" data-toggle="modal" data-id="${eduHistory.educationHistoryId}" data-whatever="${eduHistory.memberId }"
		data-target="#JoinCnclModal">교육참여거절</button>

	<!-- 	data-target="#rejectionMyModal">교육참여거절</button> -->


					</td>
				</tr>
			</c:if>
			<c:if test="${ eduHistory.state eq 'EDU_CL_A' }">
				<tr>
					<td>${eduHistory.educationId }</td>
					<td>${eduHistory.memberId }</td>
					<td>${eduHistory.ip }</td>
					<td>${eduHistory.educationHistoryDate }</td>
					<td>${eduHistory.state }</td>
					<td><c:if test="${eduHistory.cmnt ne null }">
						${eduHistory.cmnt }
					</c:if> <c:if test="${eduHistory.fdbk ne null }">
						${eduHistory.fdbk }
					</c:if></td>
					<td>
						<button class="btn btn-primary btn-sm joinaply" data-toggle="modal" data-id="${eduHistory.educationHistoryId}" data-whatever="${eduHistory.memberId }"
		data-target="#JoinAplyModal">교육취소승인</button>
					</td>
					<td>
						<button class="btn btn-primary btn-sm cnclaply" data-toggle="modal" data-id="${eduHistory.educationHistoryId}" data-whatever="${eduHistory.memberId }"
		data-target="#JoinCnclModal">교육취소거절</button>
					</td>
				</tr>
			</c:if>
			<c:if test="${ eduHistory.state eq 'EDU_GU_A' }">
				<tr>
					<td>${eduHistory.educationId }</td>
					<td>${eduHistory.memberId }</td>
					<td>${eduHistory.ip }</td>
					<td>${eduHistory.educationHistoryDate }</td>
					<td>${eduHistory.state }</td>
					<td><c:if test="${eduHistory.cmnt ne null }">
						${eduHistory.cmnt }
					</c:if> <c:if test="${eduHistory.fdbk ne null }">
						${eduHistory.fdbk }
					</c:if></td>
					<td>
						<button class="btn btn-primary btn-sm joinaply" data-toggle="modal" data-id="${eduHistory.educationHistoryId}" data-whatever="${eduHistory.memberId }"
		data-target="#JoinAplyModal">교육포기승인</button>
					</td>
					<td>
						<button class="btn btn-primary btn-sm cnclaply" data-toggle="modal" data-id="${eduHistory.educationHistoryId}" data-whatever="${eduHistory.memberId }"
		data-target="#JoinCnclModal">교육포기거절</button>
					</td>
				</tr>
			</c:if>

      </c:forEach>
      	<tr>
			<td colspan="8" align="center">
				<form name="searchForm" id="searchForm">
					<div style = "text-align:center;">
						<c:if test="${ eduHistoryListVO ne null }">
							${eduHistoryListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div>
					<div style="text-align: right;">
						<select id="search" name="search">
							<option id="educationName" value="educationName">교육명</option>
							<option id="memberId" value="memberId">멤버아이디</option>
							<option id="date" value="date">날짜</option>
						</select>
						
						<input type="text" id="searchKeyword" name="searchKeyword" value="${ eduHistorySearchVO.searchKeyword }"/>
						<input type="date" name="searchDate" id="searchDate" value="${eduHistorySearchVO.searchDate}"/>
						<input type="hidden" id="searchType" name="searchType" value="${ eduHistorySearchVO.searchType }"/>
					
						
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>	
   </table>

	<div class="modal fade" id="JoinAplyModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<!--  큰창:<div class="modal-dialog modal-lg"> 작은창 :<div class="modal-dialog modal-sm" > <div class="modal-dialog modal-dialog" >  -->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">승인</h4>
				</div>
				<div class="modal-body">정말로 승인 하시겠습니까??
					<input type="hidden" id="historyId" name="historyId" value="" />
					<input type="hidden" id="memberId" name="memberId" value="" />
				</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-primary" id="joinAplyBtn">
					정말	승인</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						취소</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="JoinCnclModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<!--  큰창:<div class="modal-dialog modal-lg"> 작은창 :<div class="modal-dialog modal-sm" > <div class="modal-dialog modal-dialog" >  -->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">거절</h4>
				</div>
				<div class="modal-body">거절 사유와 함께 상태를 변경하겠습니다.<br/>
				<input type="hidden" id="historyId2" name="historyId2" value="" />
				<input type="hidden" id="memberId2" name="memberId2" value="" />
				<textarea id="description" rows="7" cols="34"></textarea>
				</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-primary" id="joinCnclBtn">
					정말	거절</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						취소</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>