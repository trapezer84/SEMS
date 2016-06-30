<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teacher 수정 페이지</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$(document).on("keyup",".onlyText",function(){
			regexp = /[@\#$%<>&\=_\’]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		$("#annual").keyup(function(event) {
			reg = /[^0-9]/gi;
	        v = $(this).val();
	        if (reg.test(v)) {
	        	alert("숫자만 입력 하시오.");
	            $(this).val(v.replace(reg, ''));
	            $(this).focus();
	        }
	        if ( v == '0' ) {
	        	alert("0이상의 숫자만 입력 하시오.");
	            $(this).val('');
	            $(this).focus();
	            return;
	        }
	        if ( v > 100 ) {
	        	alert("너무 큰 숫자의 입력입니다.");
	            $(this).focus();
	            return;
	        }
		});
		$(document).on("click","#update",function(){
			
			if (confirm("정말 수정하시겠습니까?") == true) {
				
				if ($("#compCheckedBox").val() == '1') {
					if ($.trim($('#companyName').val()) == '') {
						alert("소속업체를 입력하시오.");
						$('#companyName').focus();
						return false;
					}
				}				
				if ($("#preCheckedBox").val() == '1') {
					if ($.trim($('#businessNumber').val()) == '') {
						alert("사업자번호를 입력하시오.");
						$('#businessNumber').focus();
						return false;
					}
				}
				if ($.trim($('#annual').val()) == '') {
					alert("연차를 입력하시오.");
					$('#annual').focus();
					return false;
				}
				
				if ( $("#EduHisTable tr").length > 1 ) {
					for ( var i = 1; i < $("#EduHisTable tr").length; i++  ) {
						
						if ($.trim($('.eduStartDate'+(i-1)).val()) == '') {
							alert("강의시작 날짜를 입력하시오.");
							$('.eduStartDate'+(i-1)).focus();
							return false;
						}
						if ($.trim($('.eduEndDate'+(i-1)).val()) == '') {
							alert("종강 날짜를 입력하시오.");
							$('.eduEndDate'+(i-1)).focus();
							return false;
						}
						if ($.trim($('.educationName'+(i-1)).val()) == '') {
							alert("강의명을 입력하시오.");
							$('.educationName'+(i-1)).focus();
							return false;
						}
						if ($.trim($('.educationLocation'+(i-1)).val()) == '') {
							alert("강의장을 입력하시오.");
							$('.educationLocation'+(i-1)).focus();
							return false;
						}
						 var startDate = $(".eduStartDate"+(i-1)).val();
					     var startDateArr = startDate.split('-');
					        
					     var endDate = $( ".eduEndDate"+(i-1) ).val();
					     var endDateArr = endDate.split('-');
					        
					     var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
					     var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
					        
					     if(startDateCompare.getTime() > endDateCompare.getTime()) {
					           
					         alert("강의 시작날짜와 종료날짜를 확인해 주세요.");
					         $('.eduStartDate'+(i-1)).focus();
					         return false;
					     }
					}
					
				}
				if ( $("#BookTable tr").length > 1 ) {
					for ( var i = 1; i < $("#BookTable tr").length; i++  ) {
						
						if ($.trim($('.bookName'+(i-1)).val()) == '') {
							alert("책이름을 입력하시오.");
							$('.bookName'+(i-1)).focus();
							return false;
						}
						if ($.trim($('.bookCompany'+(i-1)).val()) == '') {
							alert("출판사를 입력하시오.");
							$('.bookCompany'+(i-1)).focus();
							return false;
						}
					}
				}						
				if ( $("#ProHisTable tr").length > 1 ) {
					for ( var i = 1; i < $("#ProHisTable tr").length; i++  ) {
						if ($.trim($('.proStartDate'+(i-1)).val()) == '') {
							alert("프로젝트 시작 날짜를 입력하시오.");
							$('.proStartDate'+(i-1)).focus();
							return false;
						}
						if ($.trim($('.proEndDate'+(i-1)).val()) == '') {
							alert("프로젝트 종료 날짜를 입력하시오.");
							$('.proEndDate'+(i-1)).focus();
							return false;
						}
						if ($.trim($('.projectName'+(i-1)).val()) == '') {
							alert("프로젝트명을 입력하시오.");
							$('.projectName'+(i-1)).focus();
							return false;
						}
						if ($.trim($('.projectLocation'+(i-1)).val()) == '') {
							alert("프로젝트 회사를 입력하시오.");
							$('.projectLocation'+(i-1)).focus();
							return false;
						}
						var startDate2 = $(".proStartDate"+(i-1)).val();
					    var startDateArr2 = startDate2.split('-');
					        
					    var endDate2 = $( ".proEndDate"+(i-1) ).val();
					    var endDateArr2 = endDate2.split('-');
					        
					    var startDateCompare2 = new Date(startDateArr2[0], startDateArr2[1], startDateArr2[2]);
					    var endDateCompare2 = new Date(endDateArr2[0], endDateArr2[1], endDateArr2[2]);
					        
					    if(startDateCompare2.getTime() > endDateCompare2.getTime()) {
					           
					        alert("프로젝트 시작날짜와 종료날짜를 확인해 주세요.");
					        $('.proStartDate'+(i-1)).focus();
					        return false;
					    }
					    
					}
				}		
				
			} else {
				return false;;
			}
		});
		
		<c:if test="${not empty teacherVO.companyName &&  teacherVO.companyName ne '프리랜서'}">
		$("#generalBusinessNumber").hide();
		$("#preCheckedBox").val('0');
		</c:if>
		$(".pre").click(function() {
			var isChecked = $(this).prop("checked");
			if (isChecked) {
				$("#companyMember").hide();
				$("#generalBusinessNumber").show();
				$("#compCheckedBox").val('0');
				$("#companyName").val('프리랜서');
				$(".noPre").prop("checked", false);
			}
		});
		<c:if test="${teacherVO.companyName eq '프리랜서'}">
		$("#companyMember").hide();
		$("#compCheckedBox").val('0');
		</c:if>
		$(".noPre").click(function() {
			var isChecked = $(this).prop("checked");
			if (isChecked) {
				$("#generalBusinessNumber").hide();
				$("#companyMember").show();
				$("#preCheckedBox").val('0');
				$("#businessNumber").val('');
				$(".pre").prop("checked", false);
			}
		});
		$("#addEduHis").click(function(){
			var count = $("#EduHisTable tr").length-1;
			var contents = '';
			
			contents +='<tr>';
			contents +='<td><input type="date" class="eduStartDate'+count+'" name="educationHistoryList[' + count + '].startDate"></td>';
			contents +='<td><input type="date" class="eduEndDate'+count+'" name="educationHistoryList[' + count + '].endDate"></td>';
			contents +='<td><input type="text" class="educationName'+count+' onlyText" name="educationHistoryList[' + count + '].educationName" maxlength="50"></td>';
			contents +='<td><input type="text" class="educationLocation'+count+' onlyText" name="educationHistoryList[' + count + '].educationLocation" maxlength="50"></td>';
			contents +='<td><input type="button" class="removeEduHisTr" value="삭제"/></td>';
			contents +='</tr>';
	
			$("#EduHisTable").append(contents);
		});
		
		$("#addBook").click(function(){
			var count = $("#BookTable tr").length-1;
			var contents = '';
			
			contents +='<tr>';
			contents +='<td><input type="text" class="bookName'+count+' onlyText" name="teacherBookList[' + count + '].bookName" maxlength="50"></td>';
			contents +='<td><input type="text" class="bookCompany'+count+' onlyText" name="teacherBookList[' + count + '].bookCompany" maxlength="20"></td>';
			contents +='<td><input type="button" class="removeBookHisTr" value="삭제"/></td>';
			contents +='</tr>';
	
			$("#BookTable").append(contents);
		});
		
		$("#addProHis").click(function(){
			var count = $("#ProHisTable tr").length-1;
			var contents = '';
		
			contents +='<tr>';
			contents +='<td><input type="date" class="proStartDate'+count+'" name="projectHistoryList[' + count + '].startDate"></td>';
			contents +='<td><input type="date" class="proEndDate'+count+'" name="projectHistoryList[' + count + '].endDate"></td>';
			contents +='<td><input type="text" class="projectName'+count+' onlyText" name="projectHistoryList[' + count + '].projectName" maxlength="50"></td>';
			contents +='<td><input type="text" class="projectLocation'+count+' onlyText" name="projectHistoryList[' + count + '].projectLocation" maxlength="50"></td>';
			contents +='<td><input type="button" class="removeProHisTr" value="삭제"/></td>';
			contents +='</tr>';
	
			$("#ProHisTable").append(contents);
		});
		
		$(document).on("click",".removeEduHisTr",function(){
			var index = $(this).parent().parent().index()-1;
			var count = $("#EduHisTable tr").length;
			for( var i = index; i<=count-3; i++) {
				$(".eduStartDate"+(i+1)).attr('class','eduStartDate'+i);
				$(".eduEndDate"+(i+1)).attr('class','eduEndDate'+i);
				$(".educationName"+(i+1)).attr('class','educationName'+i+' onlyText');
				$(".educationLocation"+(i+1)).attr('class','educationLocation'+i+' onlyText');
				$(".eduStartDate"+i).attr('name','educationHistoryList['+i+'].startDate');
				$(".eduEndDate"+i).attr('name','educationHistoryList['+i+'].endDate');
				$(".educationName"+i).attr('name','educationHistoryList['+i+'].educationName');
				$(".educationLocation"+i).attr('name','educationHistoryList['+i+'].educationLocation');
			}
			$(this).parent().parent().remove();
		});
		$(document).on("click",".removeBookHisTr",function(){
			var index = $(this).parent().parent().index()-1;
			var count = $("#BookTable tr").length;
			for( var i = index; i<=count-3; i++) {
				$(".bookName"+(i+1)).attr('class','bookName'+i+' onlyText');
				$(".bookCompany"+(i+1)).attr('class','bookCompany'+i+' onlyText');
				$(".bookName"+i).attr('name','teacherBookList['+i+'].bookName');
				$(".bookCompany"+i).attr('name','teacherBookList['+i+'].bookCompany');
			}
			$(this).parent().parent().remove();
		});
		
		$(document).on("click",".removeProHisTr",function(){
			var index = $(this).parent().parent().index()-1;
			var count = $("#ProHisTable tr").length;
			for( var i = index; i<=count-3; i++) {
				$(".proStartDate"+(i+1)).attr('class','proStartDate'+i);
				$(".proEndDate"+(i+1)).attr('class','proEndDate'+i);
				$(".projectName"+(i+1)).attr('class','projectName'+i+' onlyText');
				$(".projectLocation"+(i+1)).attr('class','projectLocation'+i+' onlyText');
				$(".proStartDate"+i).attr('name','projectHistoryList['+i+'].startDate');
				$(".proEndDate"+i).attr('name','projectHistoryList['+i+'].endDate');
				$(".projectName"+i).attr('name','projectHistoryList['+i+'].projectName');
				$(".projectLocation"+i).attr('name','projectHistoryList['+i+'].projectLocation');
			}
			$(this).parent().parent().remove();
		});
		
		
		$(".removeHisTr").click(function(){
			if (confirm("정말 삭제하시겠습니까?") == true) {
				
				var id = $(this).parent().children().eq(0).val();
				var type = $(this).parent().children().eq(1).val();
				location.href="/backend/deleteTeacherBookEduProHistory/"+id+"/"+type+"/${teacherVO.memberId}";
			}else{
				return;
			}			
		});

	});
</script>
</head>
<body>

	<div
		style="width: 40%; height: 100%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<form:form commandName="teacherVO" method="post"
			action="/backend/doTeacherInfoModifyAction">
		강사 수정
		<br />
		<br />
	     강사명(아이디) : ${teacherVO.name }(${teacherVO.memberId})
			<br />
			<br />
		<div
		style="width: 90%; height: 40%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<div id="companyMember">
	     소속업체 :  <input type="text" class="onlyText" id="companyName"
				name="companyName" value="${teacherVO.companyName }" maxlength="30"/>
				<input type="checkbox" name="pre"
					class="tip pre" title="프리랜서" value="true" />
				<input type="hidden" name="compCheckedBox" id="compCheckedBox" value="1" />
			<br />
			<br />
		</div>
		<div id="generalBusinessNumber">
	     개인 사업자 번호 : <input type="text" class="onlyText" id="businessNumber"
				name="businessNumber" value="${teacherVO.businessNumber }" maxlength="30" />
					<input type="checkbox" name="noPre"
						class="tip noPre" title="사업체소속" value="true" />
					<input type="hidden" name="preCheckedBox" id="preCheckedBox" value="1" />
		</div>
			<br />
			<br />
	     연차 : <input type="text" class="onlyText"
				id="annual" name="annual"
				value="${teacherVO.annual }" maxlength="20"/>년
			<br />
			<form:errors path="annual" />
			<br />	
			
		</div>
		<br />
	   	강의 이력(<input type="button" id="addEduHis" value="추가"/>)  : 
			<table border="1" id="EduHisTable">
				<tr>
					<th>시작날짜</th>
					<th>종료날짜</th>
					<th>교육명</th>
					<th>교육장소</th>
					<th>삭제</th>
				</tr>
				<c:forEach items="${educationHistoryVO}" var="list" varStatus="status">
				<tr>
					<td>
						<input type="date" id="eduStartDate" class="eduStartDate${status.index} onlyText"
							name="educationHistoryList[${status.index}].startDate"
							value="${list.startDate }" >
					</td>
					<td>
						<input type="date" id="eduEndDate" class="eduEndDate${status.index} onlyText"
							name="educationHistoryList[${status.index}].eduEndDate"
							value="${list.endDate }" >
					</td>
					<td>
						<input type="text" id="educationName" class="educationName${status.index} onlyText"
							name="educationHistoryList[${status.index}].educationName"
							value="${list.educationName }" maxlength="50">
					</td>
					<td>
						<input type="text" id="educationLocation" class="educationLocation${status.index} onlyText"
							name="educationHistoryList[${status.index}].educationLocation"
							value="${list.educationLocation }" maxlength="50">
					</td>
					<td>
						<input type="hidden" id="eduHisId" 
							name="educationHistoryList[${status.index}].id"
							value="${list.id }" >
						<input type="hidden" id="type" name="type" value="edu" >
						<input type="button" class="removeHisTr" value="삭제"/>
					</td>
				</tr>
				</c:forEach>
			</table>			
			<br />
	     집필 서적(<input type="button" id="addBook" value="추가"/>)  :  
	    	<table border="1" id="BookTable">
				<tr>
					<th>책이름</th>
					<th>출판사</th>
					<th>삭제</th>
				</tr>
				<c:forEach items="${teacherBookVO}" var="list" varStatus="status">
				
				<tr>
					<td>
						<input type="text" id="bookName" class="bookName${status.index} onlyText"
							name="teacherBookList[${status.index}].bookName"
							value="${list.bookName }" maxlength="50">
					</td>
					<td>
						<input type="text" id="bookCompany" class="bookCompany${status.index} onlyText"
							name="teacherBookList[${status.index}].bookCompany"
							value="${list.bookCompany }" maxlength="20">
					</td>
					<td>
						<input type="hidden" id="bookHisId" 
							name="teacherBookList[${status.index}].id"
							value="${list.id }" >
						<input type="hidden" id="type" name="type" value="book" >
						<input type="button" class="removeHisTr" value="삭제"/>
					</td>
				</tr>
				</c:forEach>
			</table>
			<br />
	     프로젝트 이력(<input type="button" id="addProHis" value="추가"/>)  :  
	     	<table border="1" id="ProHisTable">
				<tr>
					<th>시작날짜</th>
					<th>종료날짜</th>
					<th>프로젝트명</th>
					<th>회사이름</th>
					<th>삭제</th>
				</tr>
				<c:forEach items="${projectHistoryVO}" var="list" varStatus="status">
				
				<tr>
					<td>
						<input type="date" id="startDate" class="proStartDate${status.index} onlyText"
							name="projectHistoryList[${status.index}].startDate" 
							value="${list.startDate }" >
					</td>
					<td>
						<input type="date" id="endDate" class="proEndDate${status.index} onlyText"
							name="projectHistoryList[${status.index}].endDate" 
							value="${list.endDate }" >
					</td>
					<td>
						<input type="text" id="projectName" class="projectName${status.index} onlyText"
							name="projectHistoryList[${status.index}].projectName" 
							value="${list.projectName }" maxlength="50">
					</td>
					<td>
						<input type="text" id="projectLocation" class="projectLocation${status.index} onlyText"
							name="projectHistoryList[${status.index}].projectLocation" 
							value="${list.projectLocation }" maxlength="50">
					</td>
					<td>
						<input type="hidden" id="proHisId" 
							name="projectHistoryList[${status.index}].id"
							value="${list.id }" >
						<input type="hidden" id="type" name="type" value="pro" >
						<input type="button" class="removeHisTr" value="삭제"/>
					</td>
				</tr>
				</c:forEach>
			</table>			
			<br />
			<input type="hidden" id="memberId" name="memberId" value="${teacherVO.memberId}">
			<input type="submit" id="update" value="수정하기" />
		</form:form>	
			
	</div>
</body>
</html>