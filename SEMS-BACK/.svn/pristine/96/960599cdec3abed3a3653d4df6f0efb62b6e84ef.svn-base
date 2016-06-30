<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
td {
	align : center;
}
</style>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의실 정보등록 및 관리</title>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submit").click(function() {
			
			// 강의장 위치 Validation
			if ( $("#educationLocation").val() == "" ) {
				alert("강의장 위치를 입력하세요");
				$("#educationLocation").focus();
				return false;
			}
			
			// 강의장 명 Validation
			if ( $("#educationPlaceName").val() == "") {
				alert("강의장 명을 입력하세요");
				$("#educationPlaceName").focus();
				return false;
			}
			
			// PC 정보 Validation
			if ( $(".pcName").val() == "" || $(".ip").val() == "" ) {
				alert("PC 정보를 입력하세요");
				return false;
			}
			else {
				// IP Validation
				var regExp = new RegExp("^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$");
				var boxIncrease = $("#pc option:selected").val();
				
				for (var i = 0; i < boxIncrease; i++) {
					var ip = $(".ip"+ i).val();
					var isValidIP = regExp.test(ip);
			           if ( isValidIP == false ) {
			               alert("IP 양식이 바르지 않습니다");
			               return false;
	            	 }
				}
			}
			
			if (confirm("강의실 설정이 완료 되었습니다. 등록하시겠습니까?")) {
				var form = $("#classRoomForm");
				form.attr("action", "<c:url value="/doRegistClass "/>");
				form.submit();
			} else {
				return false;
			}
		});
		$("#cancle").click(function() {
			if (confirm("취소하시겠습니까?\n ※취소시, 등록하시던 데이터가 사라집니다.")) {
				location.href = "<c:url value='/list' />";
			} else {
				return;
			}
		});
		
		$("#pc").change(function() {
			$(".pcInfo").detach();
			
			var boxIncrease = $("#pc option:selected").val();
			if (boxIncrease != null) {
			//	$("#ipClass").detach(boxIncrease);
				for (var i = 0; i < boxIncrease; i++) {
					var tmp = '<div class="pcInfo"> PCName : <input type="text" name="pcList[' + i + '].pcName" class="pcName" size="20" />'+' IP Addr : <input type="text" name="pcList[' + i + '].ip" class="ip' + i + '" size="20" /> </div>';
					$("#ipClass").append(tmp);
				}
			} else {
				throw new RuntimeException("에러가 발생했습니다.");
			}
		});
	});
</script>
</head>
<body>
	<div>
		<form:form commandName="pcVO" method="post" id="classRoomForm">
			<table border="1" style="float: left; margin-left: 27%;">
				<tr>
					<td>강의장 위치</td>
					<td>강의장 명</td>
					<td>PC 대수</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="educationLocation" name="educationLocation" placeholder="location" size="30" />
					</td>
					<td>
						<input type="text" id="educationPlaceName" name="educationPlaceName" placeholder="placeName" size="30" />
					</td>
					<td>
						<select id="pc">
							<c:forEach var="pcCount" begin="0" end="30" step="1">
								<option value="${pcCount}"><c:out value="${pcCount}대" /></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="3"align="center">PC별 IP
				</td>
				</tr>
				<tr>
					<td colspan="3"><span id="ipClass"></span></td>
				</tr>
			</table>
			<div align="left">
				<input type="submit" id="submit" value="submit"><br/>
				<input type="button" id="cancle" value="cancel">
			</div>
		</form:form>
	</div>
</body>
</html>