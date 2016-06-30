<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>강의 게시판</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function (){
		
		$("#searchKeyword").hide();
		$("#searchStartDate").hide();
		$("#searchEndDate").hide();
		
		$("#searchType").change(function() {
			var option = $("#searchType option:selected").val();
			if (option == "selectTitleDesc") {
				$("#optionSelected").val(option);
				$("#searchKeyword").show();
				$("#searchStartDate").hide();
				$("#searchEndDate").hide();
			}
			else if( option == "selectDate"){
				$("#optionSelected").val(option);
				$("#searchStartDate").show();
				$("#searchEndDate").show();
				$("#searchKeyword").hide();
			}
		});
		
		$("#initSearchBtn").click(function () {
			location.href = "<c:url value='/member/myPage/course'/>";
		});	
		
		$(".educationId").click(function () {
			var root = $(this).parent().parent().children(":eq(7)").children();
			
			var educationId = root.val();
			location.href = "<c:url value="/eduBoard/" />"+educationId;
		});
		
		$("#searchBtn").click(function () {
			
			var option = $("#searchType option:selected").val();
			
			if (option == "selectTitleDesc") {
				if( $("#searchKeyword" ).val() == "" ) {
					alert("검색어를 입력하세요!");
					return;
				}
			}
			else if( option == "selectDate"){
				var startDate = $("#searchStartDate").val();
				var endDate = $("#searchEndDate").val();
				
				if (startDate > endDate) {
					alert("기간 범위 오류 : 다시 기간을 지정하세요.");
					return;
				}
			}
			
			$("#searchForm").attr("action", "<c:url value="/member/myPage/course"/>");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
			
			movePage('0');
			
		});
		
	});
	</script>

</head>
<body>


<div>
	<span>현재 수강 목록</span>
		<table>
			<tr>
				<th>educationId</th>
				<th>educationTitle</th>
				<th>memberId</th>
				<th>status</th>
				<th>startDate</th>
				<th>endDate</th>
		
			</tr>
			<c:forEach items="${educationListVO}" var="educationVO">
				<tr>
					<td>${ educationVO.educationId }</td>
					<td><span class="educationId" >${ educationVO.educationTitle }</span></td>
					<td>${ educationVO.memberId }</td>
					<td>${ educationVO.status }</td>
					<td>${ educationVO.startDate }</td>
					<td>${ educationVO.endDate }</td>
					<td><a href="<c:url value='/resignCourse/${educationVO.educationId}'/>" target="_blank" 
					onclick="window.open(this.href, 'popupName', 'width=800, height=500, left=50, top=50, statusbar=0, scrollbars=1'); return false;" 
					onkeypress="this.onclick(); return false;" >DROP</a></td>
					<td border=0 ><input type="hidden" id="${ educationVO.educationId }" value="${ educationVO.educationId }" /></td>
				</tr>
			</c:forEach>
		</table>
</div>
<br/>
<span>-------------------------------------------------------------------------------------------</span>
<br/>
<br/>
<div>
	<span>이전 수강 목록</span>
		<table>
			<tr>
				<th>educationId</th>
				<th>educationTitle</th>
				<th>memberId</th>
				<th>status</th>
				<th>startDate</th>
				<th>endDate</th>
		
			</tr>
			<c:forEach items="${ preEducationListVO.educationList }" var="educationVO">
				<tr>
					<td>${ educationVO.educationId }</td>
					<td><span class="educationId" >${ educationVO.educationTitle }</span></td>
					<td>${ educationVO.memberId }</td>
					<td>${ educationVO.status }</td>
					<td>${ educationVO.startDate }</td>
					<td>${ educationVO.endDate }</td>
					<td></td>
					<td><input type="hidden" id="${ educationVO.educationId }" value="${ educationVO.educationId }" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10">
					<form id="pagingForm">
					<div style="text-align: center;">
						${ preEducationListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
					</div>
					<div>
					
				<select id ="searchType" name ="searchType" class="inputButton">
				<option value="" selected="selected">선택</option>
				<option value="selectTitleDesc">교육명</option>
				<option value="selectDate">날짜</option>
				
				<c:if test="${searchVO.searchType eq 'selectTitleDesc' }">
					<option value="">선택</option>
					<option value="selectTitleDesc" selected="selected">제목</option>
					<option value="selectDate">날짜</option>
				</c:if>
				<c:if test="${searchVO.searchType eq 'selectDate' }">
					<option value="">선택</option>
					<option value="selectTitleDesc">제목</option>
					<option value="selectDate" selected="selected">날짜</option>
				</c:if>
				</select>
				<input type="text" id="searchKeyword" name="searchKeyword" class="inputButton" value="${educationSearchVO.searchKeyword}" />
				<input type="date" id="searchStartDate" name="searchStartDate" class="inputButton" value="${educationSearchVO.searchStartDate}"/>
				<input type="date" id="searchEndDate" name="searchEndDate" class="inputButton" value="${educationSearchVO.searchEndDate}"/>
				<input type="button" id="searchBtn"  class="inputButton" value="검색" />
				<input type="button" id="initSearchBtn" class="inputButton" value="검색초기화" />
			</div>
					</form>
				</td>
			</tr>
		</table>
</div>
</body>
</html>