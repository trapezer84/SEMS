<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	
	$(document).ready(function () {
			
		$("#deleteBtn").click(function () {
			
			if ( confirm("정말 회원을 삭제 하시겠습니까?") == true ) {
				location.href = "<c:url value="/memberDelete/${member.id}"/>";
			}
			
		});
		
		$("#listBtn").click(function(){
			location.href = "<c:url value="/memberManage/memberList"/>";
		});
		
		
	});
	
</script>
<title></title>
</head>


<body>
		<table>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>EMAIL</th>
				<th>최종학력</th>
				<th>대학교</th>
				<th>학과</th>
				<th>졸업구분</th>
				<th>생년월일</th>
				<th>전화번호</th>
				<th>회원타입</th>
				<th>접속실패횟수</th>
				<th>접속잠김여부</th>
				<th>최근접속날짜</th>
				<th>탈퇴날짜</th>
				<th>탈퇴여부</th>
				<th>수정실패횟수</th>
				<th>수정잠김여부</th>
			</tr>
			<tr>
				<td>${ member.id }</td>
				<td>*****</td>	
				<td>${member.name}</td>	
				<td>${member.email}</td>	
				<td>${member.highestEducationLevel}</td>	
				<td>${member.universityName}</td>	
				<td>${member.majorName}</td>	
				<td>${member.graduationType}</td>	
				<td>${member.birthDate}</td>	
				<td>${member.phoneNumber}</td>	
				<td>${member.memberType}</td>	
				<td>${member.loginFailCount}</td>	
				<td>${member.isAccountLock}</td>	
				<td>${member.latestLoginDate}</td>	
				<td>${member.resignDate}</td>	
				<td>${member.isResign}</td>	
				<td>${member.modifyFailCount}</td>	
				<td>${member.isModifyLock}</td>	
			</tr>
		</table>

<span id="deleteBtn">삭제</span>
<span id="listBtn">리스트보기</span>
</body>
</html>