<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.inputButton {
	border: none;
	border-radius: 5px;
	padding: 6px 12px;
	font-weight: bold;
	text-transform: uppercase;
	color: #FFFFFF;
	background-color: #E05149;
}
</style>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$(".onlyText").keyup(function(event) {
			regexp = /[@\#$%<>&\()\=_\’]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});

		$("#massiveSelectCheckBox").click(function() {
			var isChecked = $(this).prop("checked");
			$(".deleteTeacherId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".deleteTeacherId").each(function (index, data) {
				if(data.checked){
					isChecked = data.checked;
				}
			});
			
			if(!isChecked) {
				alert("삭제할 대상을 선택하세요.")
				return;
			}
			
			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#teacherListForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/massiveDeleteTeacher" />");
				form.submit();
			}
		});
		
/* 		$("#massiveDeleteBtn").click(function(){
			if (confirm("정말로 삭제하시겠습니까?")) {
				location.href = "<c:url value='/massiveDeleteTeacher'/>";
			}
		});
 */
		$("#initSearch").click(function() {
			location.href = "<c:url value='/teacher/teaacherList'/>";
		});

		$("#searchBtn").click(function() {

			if ($("#searchKeyword").val() == "") {
				alert("검색어를 입력하세요!");
				return;
			}

			movePage('0');

		});

	});
</script>


<title>강사 리스트</title>
</head>
<body>
	<h1>강사 리스트</h1>
	<table border=1 style="width: 40%">
		<tr>
			<th style="width: 15px"><input type="checkbox"
				id="massiveSelectCheckBox" /></th>
			<th>강사 명</th>
			<th>소속 업체</th>
			<th>연차</th>
		</tr>
		<form name="teacherListForm" id="teacherListForm">
			<c:forEach items="${ searchedListVO.teacherList }" var="teacher">
				<tr>
					<td><input class="deleteTeacherId" name="deleteTeacherId"
						value="${teacher.memberId}" type="checkbox" /></td>
					<td><a href="<c:url value='/teacher/detail/${teacher.memberId}' />">${ teacher.name }</a></td>
					
					<c:if test="${ not empty teacher.companyName  }">
						<td>${ teacher.companyName }</td>
					</c:if>
					<c:if test="${ empty teacher.companyName  }">
						<td>프리랜서</td>
					</c:if>
					<td>${ teacher.annual }</td>
				</tr>
			</c:forEach>
		</form>
		<tr>
			<td colspan="7" align="center">
				<form id="searchForm">
					<div>
						<c:if test="${ searchedListVO ne null }">
								${searchedListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						<br />
						</c:if>
					</div>
					<div style="float: right;">
						<select name="searchType" class="inputButton">
							<c:if test="${searchVO.searchType eq '1' }">
								<option value="1" selected="selected">강사명</option>
								<option value="2">소속업체</option>
							</c:if>
							<c:if test="${searchVO.searchType eq '2' }">
								<option value="1">강사명</option>
								<option value="2" selected="selected">소속업체</option>
							</c:if>
						</select> 
						<input type="text" id="searchKeyword" class="inputButton" 
									name="searchKeyword" value="${searchVO.searchKeyword }" /> 
						<input type="button" id="searchBtn" class="inputButton" value="검색" />
						<input type="button"  value="검색 초기화" id="initSearch" class="inputButton"  name="initSearch" />
							
					</div>
		<div>
			<input id="massiveDeleteBtn" class="inputButton" type="button"
				value="일괄삭제" style="cursor: pointer;" />
		</div>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>