<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table align="center" border="1">
		<tr>
			<th>번호</th>
			<th>사용자 아이디</th>
			<th>IP주소</th>
			<th>로그인시간</th>
			<th>로그아웃시간</th>
		</tr>
			<tr align="center">
				<td>${loginHistory.lgiHtrId}</td>
				<td>${loginHistory.id}</td>
				<td>${loginHistory.lgiIp}</td>
				<td>${loginHistory.lgiDt}</td>
				<td>${loginHistory.lgoDt}</td>
			</tr>
	</table>


</body>
</html>