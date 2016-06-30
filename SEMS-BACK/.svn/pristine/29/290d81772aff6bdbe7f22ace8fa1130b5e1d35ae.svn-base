<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Education 수정 페이지</title>
<script type="text/javascript" src="/backend/resources/js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		var ua = navigator.userAgent.toLowerCase();
		if (ua.indexOf('chrome') == -1 ) // Internet Explorer일 경우
	    {
			$("#startDate").datepicker({ dateFormat: "yy-mm-dd",  minDate: new Date(), maxDate: '+2y' });
			$("#endDate").datepicker({ dateFormat: "yy-mm-dd",  minDate: new Date(), maxDate: '+2y' });
			/*
			http://timepicker.co/ 참조
			*/
			$("#startTime").timepicker({
				 timeFormat: 'HH:mm',
			        startHour: 9,
			        startTime: new Date(0, 0, 0, 8, 20, 0),
			        interval: 10,
			        change: function(time) {
			        	var startTime = $(this).val();
			        	var endTime = $("#endTime").val();
			            
			            if ( ( startTime != null || startTime != '' ) && ( endTime == '' || startTime > endTime ) ) {
			            	$("#endTime").val(startTime);
						}
			        }
			});
			$("#endTime").timepicker({
				 timeFormat: 'HH:mm',
			        startHour: 9,
			        startTime: new Date(0, 0, 0, 8, 20, 0),
			        interval: 10,
			        change: function(time) {
			        	var endTime = $(this).val();
			        	var startTime = $("#startTime").val();
			            
			            if ( ( endTime != null || endTime != '' ) && ( startTime == '' || endTime < startTime ) ) {
			            	$("#startTime").val(endTime);
						}
			        }
			});
	    }
	    else  // Internet Explorer가 아닐경우
	    {
	    }
		
		// 오늘의 날짜를 가져온다.
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		if(dd<10){
			dd='0'+dd
		} 
		if ( mm < 10) {
			mm = '0' + mm
		} 

		today = yyyy + '-' + mm + '-' + dd;
		maxday = yyyy + 2 + '-' + mm + '-' + dd;
		
		//개강 시작과 종료의 최소선택을 오늘로
		$("#startDate").attr("min", today);
		$("#startDate").attr("max", maxday);
		$("#endDate").attr("min", today);
		$("#endDate").attr("max", maxday);
		
		//날짜의 선택이 변경되면 새로고침
		$("#startDate").change(function(){
			if ( $(this).val() != '' && ( $("#endDate").val() == '' || $(this).val() > $("#endDate").val() ) ) {
				$("#endDate").val($(this).val());
			}
		});
		$("#endDate").change(function(){
			if ( $(this).val() != '' && ( $("#startDate").val() == '' || $(this).val() < $("#startDate").val() ) ) {
				$("#startDate").val($(this).val());
			}
		});
		
		//시간의 선택이 변경되면 새로고침
		$("#startTime").change(function(){
			if ( $(this).val() != '' && ( $("#endTime").val() == '' || $(this).val() > $("#endTime").val() ) ) {
				$("#endTime").val($(this).val());
			}
		});
		$("#endTime").change(function(){
			if ( $(this).val() != '' && ( $("#startTime").val() == '' || $(this).val() < $("#startTime").val() ) ) {
				$("#startTime").val($(this).val());
			}
		});

		$(".onlyText").keyup(function(event) {
			regexp = /[@\#$%<>&\()\=_\’]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		
		//소괄호를 허용하는 정규식 
		$(".onlyTextWithBracket").keyup(function(event) {
			regexp = /[@\#$%<>&\=_\’]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		
		$(".noPaste").on('paste', function(e){
			e.preventDefault();
		});
		
		$(".noText").keypress(function(){
			return false;
		});

		$("#maxMember").keyup(function(event) {
			reg = /[^0-9]/gi;
	        v = $(this).val();
	        if (reg.test(v)) {
	        	alert("숫자만 입력 하시오.");
	            $(this).val(v.replace(reg, ''));
	            $(this).focus();
	            return;
	        }
	        if ( v != '' && v == 0 ) {
	        	alert("0이상의 숫자만 입력 하시오.");
	            $(this).val('');
	            $(this).focus();
	            return;
	        }
	        if ( v != '' && v > 2147483647 ) {
	        	alert("너무 큰 숫자의 입력입니다.");
	            $(this).focus();
	            return;
	        }
		});

		$("#update").click(function() {
			if (confirm("정말 등록하시겠습니까?") == true) {
				if ($.trim($('#educationCategory').val()) == '') {
					alert("카테고리를 입력하시오.");
					$('#educationCategory').focus();
					return false;
				}
				if ($.trim($('#educationTitle').val()) == '') {
					alert("강의명을 입력하시오.");
					$('#educationTitle').focus();
					return false;
				}
				if ($.trim($('#memberId').val()) == '') {
					alert("강사명을 입력하시오.");
					$('#memberId').focus();
					return false;
				}
				if ($.trim($('#maxMember').val()) == '') {
					alert("제한 인원수를 입력하시오.");
					$('#maxMember').focus();
					return false;
				}
				if ($.trim($('#educationLocation').val()) == '') {
					alert("강의실 명을 입력하시오.");
					$('#educationLocation').focus();
					return false;
				}
				if ($.trim($('#educationCurriculum').val()) == '') {
					alert("커리큘럼을 입력하시오.");
					$('#educationCurriculum').focus();
					return false;
				}
				if ($("#checkFileUpdate").val() == '0') {
					if ($.trim($('#file').val()) == '') {
						alert("파일을 선택하시오.");
						$('#file').focus();
						return false;
					}
					if ($('#file').val().indexOf('.xlsx') == -1) {
						alert("엑셀 파일만 등록할 수 있습니다.");
						$('#file').focus();
						return false;
					}
				}
				if ($.trim($('#educationIntroduce').val()) == '') {
					alert("강의소개를 입력하시오.");
					$('#educationIntroduce').focus();
					return false;
				}
				if ($.trim($('#startDate').val()) == '') {
					alert("시작 날짜를 입력하시오.");
					$('#startDate').focus();
					return false;
				}
				if ($.trim($('#endDate').val()) == '') {
					alert("종료 날짜을 입력하시오.");
					$('#endDate').focus();
					return false;
				}
				if ($.trim($('#startTime').val()) == '') {
					alert("시작 시간을 입력하시오.");
					$('#startTime').focus();
					return false;
				}
				if ($.trim($('#endTime').val()) == '') {
					alert("종료 시간을 입력하시오.");
					$('#endTime').focus();
					return false;
				}
				if ($.trim($('.educationType').val()) == '') {
					alert("타입을 선택하시오.");
					$('.educationType').focus();
					return false;
				}
				if ($.trim($('.cost').val()) == '') {
					alert("비용을 선택하시오.");
					$('.cost').focus();
					return false;
				}
				
				var updateForm = $("#educationVO");
				updateForm.submit();
			} else {
				return;
			}
		});
		<c:if test="${not empty educationVO.fileName }">
		$("#file").hide();
		</c:if>
		$(".checkDelete").click(function() {
			var isChecked = $(this).prop("checked");
			if (isChecked) {
				$(".uploadedFile").css({
					"text-decoration" : "line-through"
				});
				$("#file").show();
				$("#checkFileUpdate").val('0');
			} else {
				$(".uploadedFile").css({
					"text-decoration" : "none"
				});
				$("#file").hide();
			}
		});

	});
</script>
</head>
<body>

	<div
		style="width: 30%; height: 100%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<form:form commandName="educationVO" method="post"
			action="/backend/doEducationModifyAction"
			enctype="multipart/form-data">
	     	
	     교육 카테고리 : 
	       <select id="educationCategory" name="educationCategory">
				<c:forEach items="${categoryList}" var="category">
					<c:if test="${educationVO.educationCategory eq category.cdId }">
						<option value="${category.cdId}" selected="selected">${category.cdNm}</option>
					</c:if>
					<c:if test="${educationVO.educationCategory ne category.cdId }">
						<option value="${category.cdId}" selected="selected">${category.cdNm}</option>
					</c:if>
				</c:forEach>
			</select>
			<br />
			<form:errors path="educationCategory" />
			<br />
	     교육명 : <input type="text" class="onlyTextWithBracket"
				id="educationTitle" name="educationTitle"
				value="${educationVO.educationTitle }" maxlength="20"/>
			<br />
			<form:errors path="educationTitle" />
			<br />
	     강사명 :  <select id="memberId" name="memberId">
		     		<c:forEach items="${teacherVO}" var="teacher">
		     			<c:if test="${teacher.memberId eq educationVO.memberId }">
		     				<option value="${teacher.memberId}" selected="selected">${teacher.name}(${teacher.memberId})</option>
		     			</c:if>
		     			<c:if test="${teacher.memberId ne educationVO.memberId }">
		     				<option value="${teacher.memberId}">${teacher.name}(${teacher.memberId})</option>
		     			</c:if>
		     		</c:forEach>	     		
	     		</select>
			<br />
			<form:errors path="memberId" />
			<br />
	     정원 : <input type="text" class="onlyText noPaste" id="maxMember"
				name="maxMember" value="${educationVO.maxMember }" min="1" max="2147483647" />
			<br />
			<br />
	     강의실 : <input type="text" class="onlyText"
				id="educationLocation" name="educationLocation"
				value="${educationVO.educationLocation }" maxlength="20"/>
			<br />
			<form:errors path="educationLocation" />
			<br />
	   	커리큘럼 : 
	   	<input type="text" id="educationCurriculum" class="onlyText"
				name="educationCurriculum"
				value="${educationVO.educationCurriculum }" maxlength="100">
		<input type="file" name="file" id="file" tabindex="2"
				style="height: 35px">
			<p style="border: 1px dashed #333; padding: 5px;">
				등록된 파일 :  
				<input type="checkbox" name="isDelete"
					class="tip checkDelete" title="체크하면 삭제합니다." value="true" /> 
				<input type="hidden" name="checkFileUpdate" id="checkFileUpdate" value="1" />
				<span class="uploadedFile">${educationVO.fileName }</span>
			</p>			
			<form:errors path="educationCurriculum" />
			<br />
			<br />
	     교육소개 :  
	    	
	 	   <input type="text" style="height: 100px" id="educationIntroduce"
				class="onlyTextWithBracket" name="educationIntroduce"
				value="${educationVO.educationIntroduce }" />
			<br />
			<form:errors path="educationIntroduce" />
			<br />
	     개강일 :  <input type="date" id="startDate" class="noText" name="startDate"
				value="${educationVO.startDate }" />
			<br />
			<form:errors path="startDate" />
			<br />
	     종강일 :  <input type="date" id="endDate" class="noText" name="endDate"
				value="${educationVO.endDate }" />
			<br />
			<form:errors path="endDate" />
			<br />
	    
	     강의 시간(시작) : <input type="time" id="startTime" class="noText" name="startTime"
				value="${educationVO.startTime }" />
			<br />
			<form:errors path="startTime" />
			<br />
	     강의 시간(종료) :  <input type="time" id="endTime" class="noText" name="endTime"
				value="${educationVO.endTime }" />
			<br />
			<form:errors path="endTime" />
			<br />
	     주간/야간 : 
	   		<c:forEach items="${typeList}" var="type">
	   			<c:if test="${educationVO.educationType eq type.cdId }">
	   				<input type="radio" class="educationType" name="educationType" checked="checked"
					value="${type.cdId}" /> ${type.cdNm}
	   			</c:if>
				<c:if test="${educationVO.educationType ne type.cdId }">
	   				<input type="radio" class="educationType" name="educationType"
					value="${type.cdId}" /> ${type.cdNm}
	   			</c:if>
      	   </c:forEach>
      	   <form:errors path="educationType" />
      	   <br />
	     비용 : 
	     	<c:forEach items="${costList}" var="cost">
		     	<c:if test="${educationVO.cost eq cost.cdId }">
	      			<input type="radio" class="cost" name="cost" value="${cost.cdId}" checked="checked" /> ${cost.cdNm}
	      		</c:if>
	      		<c:if test="${educationVO.cost ne cost.cdId }">
	      			<input type="radio" class="cost" name="cost" value="${cost.cdId}" /> ${cost.cdNm}
	      		</c:if>				
      	   	</c:forEach>
			<form:errors path="cost" />
			<br />

			<input type="hidden" name="educationId"
				value="${educationVO.educationId }" />
			<input type="button" id="update" value="등록하기" />
		</form:form>
	</div>
</body>
</html>