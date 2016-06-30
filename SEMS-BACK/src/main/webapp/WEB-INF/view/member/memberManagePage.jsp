<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">


</script>
<title>회원 관리 페이지</title>
</head>
<body>
	
	<a href="<c:url value="/memberManage/addMember"/>">등록</a> <br/>
	<a href="<c:url value="/memberManage/memberList"/>">조회</a> <br/>
	
</body>
</html>