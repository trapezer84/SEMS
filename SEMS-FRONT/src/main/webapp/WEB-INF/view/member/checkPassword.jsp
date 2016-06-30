<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#checkPasswordBtn").click(function() {
			if($("#password").val() == "") {
				alert("비밀번호를 입력하세요!");
				$("#password").focus();
				return;
			}
			
			
			$.post("<c:url value="/member/myPage/doCheckPassword" />", $("#checkPasswordForm").serialize(), function(data){
				
				 if ( data =="NO" ){
			           alert("비밀번호 오류입니다. 비밀번호를 확인해 주세요.");
			           location.href="<c:url value="/member/myPage/checkPassword"/>";
			        }
				 else if ( data =="OK" ){
					 $("#checkPasswordForm").submit();
				 }
			    else if(data == "OVER") {
			       alert("비밀번호가 지속 실패하여, 계정이 잠겼습니다. 운영자에게 문의하세요.");
			       location.href="<c:url value="/"/>";
			    }		 
			});
		});	

	});

</script>
</head>
<body>
비밀번호 확인하기

<form id="checkPasswordForm" name="checkPasswordForm" method="post" action="<c:url value="/member/myPage/modify"/>">

	<input type="password" name="password" id="password" size="50" placeholder="비밀번호를 입력하세요" />
	<input type="button" id="checkPasswordBtn" value="확인" />

</form>

</body>
</html>