<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>협력사 상세정보</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		// 수정버튼 클릭
		 $("#cooModifyBtn").click(function(){
			location.href = "<c:url value='/modifyCoo/${cooperationVO.cooperationId}'/>";
		}); 		
		
		$("#cooDeleteBtn").click(function(){
			if (confirm("정말로 삭제하시겠습니까?")) {
				location.href = "<c:url value='/cooDelete/${cooperationVO.cooperationId}'/>";
			}
		});
		
		$("#cooListBtn").click(function(){
			location.href = "<c:url value="/cooList" />";
		});
		
	});

</script>
</head>
<body>
	<div style="width:30%; height:100%; border:thin; border-style:double; border-radius: 5px;">
		${cooperationVO.cooperationId }<br/>
		<hr>
		<table>
			<tr>
				<td>협약사 명 : </td>
				<td> ${cooperationVO.cooperationTitle }</td>
			</tr>
			<tr>
				<td>협약사 소재지 : </td>
				<td> ${cooperationVO.cooperationLocation }</td>
			</tr>
			<tr>
				<td>사업자 번호 : </td>
				<td> ${cooperationVO.cooperationNumber }</td>
			</tr>
			<tr>
				<td>대표 명 : </td>
				<td> ${cooperationVO.representativeName }</td>
			</tr>
			<tr>
				<td>담당자 전화번호 : </td>
				<td> ${cooperationVO.managerPhoneNumber }</td>
			</tr>
			<tr>
				<td>회사 전화번호 : </td>
				<td> ${cooperationVO.cooperationPhoneNumber }</td>
			</tr>
			<tr>
				<td>담당자 이메일 : </td>
				<td> ${cooperationVO.managerEmail }</td>
			</tr>
			<tr>
				<td>협력사/협약사 선택지 : </td>
			<c:forEach items="${cooTypeList}" var="cooType">
					<c:if test="${cooperationVO.cooperationType eq cooType.codeId}" >
						<td> ${cooType.codeName}</td>
					</c:if>
				</c:forEach> 
			</tr>
		</table>
		<hr>
		<input type="button" id="cooModifyBtn" value="수정" />  /
		<input type="button" id="cooDeleteBtn" value="삭제" />  /
		<input type="button" id="cooListBtn" value="목록" />
	</div>
</body>
</html>