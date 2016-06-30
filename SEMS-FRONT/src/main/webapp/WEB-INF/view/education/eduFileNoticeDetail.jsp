<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
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
<title>공지사항</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#noticeListBtn").click(function(){
			location.href = "<c:url value='/education/fileBBS/${educationId}' />";
		});
		
	});

</script>
</head>
<body>

<!--  <h1>공지사항</h1> -->
    <form id = addRecommend >
	<table>
		<tr style="float: left;">
			<td >작성자:${eduNoticeVO.memberId} | 작성일:${eduNoticeVO.createDate} | 조회수: ${eduNoticeVO.hits} </td>
			<br>
		</tr>
	
		<tr>
			<td class="center" ><h1>${eduNoticeVO.title }</h1></td>
		</tr>
		<tr>
			<td>${eduNoticeVO.contents}</td>
		</tr>	
	
		
	</table>
</form>
			   	<div style=" float: right;">
					<input type="button" id="noticeListBtn" class="inputButton"  value="목록" />
			    </div>
	<br/><br/><hr>
		

</body>
</html>