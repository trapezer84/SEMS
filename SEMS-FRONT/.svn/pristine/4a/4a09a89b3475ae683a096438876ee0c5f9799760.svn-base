<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
	
		$("#write").click(function() {
			location.href="<c:url value='/team/teamBBS/write'/>";
		});
		
		$(".onlyText").keyup(function(event) {
			regexp = /[@\#$%<>&\()\=_\’]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		
		$(".onlyNumber").keyup(function(event) {		
			regexp = /[^\d]/gi;
			v = $(this).val();
			if (regexp.test(v)) {
				alert("숫자만 입력할 수 있습니다..");
				$(this).val(v.replace(regexp, ''));
			}
		});
		
		$("#initSearch").click(function() {
			location.href="<c:url value='/team/teamBBS/board'/>";
		});
		
		$("#searchBtn").click(function() {
			
			var createdYear = $("#createdYear").val();
			var createdMonth = $("#createdMonth").val();
			
			var modifiedYear = $("#modifiedYear").val();
			var modifiedMonth = $("#modifiedMonth").val();
			
			createdMonth = fillString(createdMonth);
			
			 if ( createdYear != "" || createdYear.length != 0 ){
				 if ( createdMonth == "" || createdMonth.length == 0 ){
				alert("게시글의 작성된 달을 선택하세요.");
				$("#createdMonth").focus();
				return;
				 }
			}
			if ( createdMonth != "" || createdMonth.length != 0 ){
				if ( createdYear == "" || createdYear.length == 0 ){
				alert("게시글이 작성된 년도를 선택하세요.");
				$("#createdYear").focus();
				return;
				}
			}
			if ( modifiedYear != "" || modifiedYear.length != 0 ){
				if ( modifiedMonth == "" || modifiedMonth.length == 0 ){
				alert("게시글이 수정된 달을 선택하세요.");
				$("#modifiedMonth").focus();
				return;
				}
			}
			if ( modifiedMonth != "" || modifiedMonth.length != 0 ){
				if ( modifiedYear == "" || modifiedYear.length == 0 ){
				alert("게시글이 수정된 년도를 선택하세요.");
				$("#modifiedYear").focus();
				return;
				}
			} 
			
			modifiedMonth = fillString(modifiedMonth);
			
			var createdSearchDate = createdYear + createdMonth;
			var modifiedSearchDate = modifiedYear + modifiedMonth;
			
			createdSearchDate = parseInt(createdSearchDate);
			modifiedSearchDate = parseInt(modifiedSearchDate);
			
			if(createdSearchDate > modifiedSearchDate) {
				alert("기간 범위 오류");
				return;
			}
			
			$("#searchForm").attr("action", "<c:url value="/team/teamBBS/searchBBSList"/>");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		});
		
			function fillString(str) {

				if (str.length == 1) {
					str = "0" + str;
				}

				return str;
			}
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀별 커뮤니티 게시판 </title>
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
</head>
<body>
	
	<form name="searchForm" id="searchForm" >
	
	<table>
		<tr>
			<th>작성일</th>
			<td>
				<select id="createdYear" name="createdYear" >
					<option value="" selected="selected"></option>
					<c:forEach var="createdYear" begin="${fromYear}" end="${toYear}" step="1">
						<option value="${ createdYear }">${ createdYear }</option>
					</c:forEach>
				</select>년
				
				<select id="createdMonth" name="createdMonth" >
					<option value="" selected="selected"></option>
					<c:forEach var="createdMonth" begin="01" end="12" step="1">
					<option value="${ createdMonth }">${ createdMonth }</option>
					</c:forEach>
				</select>월
				
			</td>
		</tr>
		
		<tr>
			<th>글 수정일</th>
			<td>
				<select id="modifiedYear" name="modifiedYear" >
					<option value="" selected="selected"></option>
					<c:forEach var="modifiedYear" begin="${fromYear}" end="${toYear}" step="1">
							<option value="${ modifiedYear }">${ modifiedYear }</option>
					</c:forEach>
				</select>년
				
				<select id="modifiedMonth" name="modifiedMonth" >
					<option value="" selected="selected"></option>
					<c:forEach var="modifiedMonth" begin="01" end="12" step="1">
					<option value="${ modifiedMonth }">${ modifiedMonth }</option>
					</c:forEach>
				</select>월
			</td>
		</tr>
		
		<tr>
			<th>제목</th>
			<td>
				<input type="text" class="onlyText" name="title" id="title" value="${searchKeyword.title }" />
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" class="onlyText" name="memberId" id="memberId" value="${searchKeyword.memberId }" placeholder="작성자는 정확히 입력하세요."/>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<input type="text" name="descript" id="descript" value="${searchKeyword.descript }" />
			</td>
		</tr>
		<tr>
			<th>댓글수</th>
			<td>
				[미구현]
			</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>
				<input type="text" class="onlyNumber" name="likeCount" id="likeCount" value="${searchKeyword.likeCount }" /> 개 이상
			</td>
		</tr>
		<tr>
			<th>반대수</th>
			<td>
				<input type="text" class="onlyNumber" name="disLikeCount" id="disLikeCount" value="${searchKeyword.disLikeCount }" /> 개 이상
			</td>
		</tr>
		<tr>
			<td colspan="9" align="right">
				<input type="hidden" value="0" id="searchPageNo" />
				<input type="button" value="검색" id="searchBtn"/>
				<input type="button"  value="검색 초기화" id="initSearch" name="initSearch" />
			</td>
		</tr>
	</table>
	
		<table>
			<tr>
				<td>제목	</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>수정일</td>
				<td>첨부파일의 수</td>
				<td>댓글 수</td>
				<td>조회 수</td>
				<td>추천 수</td>
				<td>반대 수</td>
			</tr>
			<c:forEach items="${ TeamBBSListVO.teamBBSList }" var="teamBBSVO">
				<input type="hidden" name="teamBBSId" value="${teamBBSVO.teamBBSId}" />
				<tr>
					<td><a href="<c:url value='/team/teamBBS/detail/${teamBBSVO.teamBBSId}'/>">${ teamBBSVO.title }</a></td>
					<td>${teamBBSVO.memberId}</td>
					<td>${teamBBSVO.createdDate}</td>
					<td>${teamBBSVO.modifiedDate}</td>
					<td>${teamBBSVO.fileCount}</td>
					<td>댓글 수 (미완)</td>
					<td>${teamBBSVO.hits}</td>
					<td>${teamBBSVO.likeCount}</td>
					<td>${teamBBSVO.disLikeCount}</td>
				</tr>
			</c:forEach>
			
			<c:forEach items="${ searchedListVO.teamBBSList }" var="teamBBSVO">
				<input type="hidden" name="teamBBSId" value="${teamBBSVO.teamBBSId}" />
				<tr>
					<td><a href="<c:url value='/team/teamBBS/detail/${teamBBSVO.teamBBSId}'/>">${ teamBBSVO.title }</a></td>
					<td>${ teamBBSVO.memberId }</td>
					<td>${ teamBBSVO.createdDate }</td>
					<td>${ teamBBSVO.modifiedDate }</td>
					<td>${ teamBBSVO.fileCount }</td>
					<td>댓글 수 (미완)</td>
					<td>${ teamBBSVO.hits }</td>
					<td>${ teamBBSVO.likeCount }</td>
					<td>${ teamBBSVO.disLikeCount }</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="9" align="center">
					<c:if test="${ TeamBBSListVO ne null }">
					${TeamBBSListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
					</c:if>
					<c:if test="${ searchedListVO ne null }">
					${searchedListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
					</c:if>
				</td>
			</tr>  
		</table>
	</form>
	<div align="right">
	<input type="button" id="write" value="글쓰기" >
	</div>
	<div class="clear"></div>
	
</body>
</html>