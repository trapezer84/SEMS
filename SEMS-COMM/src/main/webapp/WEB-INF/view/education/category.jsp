<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.12.1.js"/> "></script>
<script type="text/javascript">
	$(document).ready(function(){
		var parentCategoryId = $("#parentCategoryId");
		var categoryId = $("#categoryId");
		var categoryName = $("#categoryName");
		var categoryType = $("#categoryType");
		var categoryIdValid = false;
		var categoryNameValid = false;
		
		function initCategoryIdAndName(){
			categoryId.val('');
			categoryName.val('');
			$("#categoryIdError").empty();
			$("#categoryNameError").empty();
		}
		
		function validCategoryId(){
			if ( categoryId.val() == null || categoryId.val().length < 2 ) {
				$("#categoryIdError").empty();
				$("#categoryIdError").append("카테고리 아이디는 2글자 이상 4글자 이하여야 합니다.");
				categoryIdValid = false;
			}
			else {
				$("#categoryIdError").empty();
				$.post(
						'<c:url value="/education/validCategoryId"/>'
						, 'categoryId=' + categoryId.val() + '&categoryType=' + categoryType.val()
						, function(isExist){
							if ( isExist == 'true' ) {
								$("#categoryIdError").empty();
								$("#categoryIdError").append("이미 존재하는 아이디 입니다.");
								categoryIdValid = false;
							}
							else {
								$("#categoryIdError").empty();
								$("#categoryIdError").append("사용 가능한 아이디 입니다.");
								categoryIdValid = true;
							}
						}
				);
			}
		}
		
		function validCategoryName(categoryName, categoryType){
			if ( categoryName == null || categoryName.length < 1 ) {
				$("#categoryNameError").empty();
				$("#categoryNameError").append("카테고리 이름은 30글자 이하여야 합니다.");
				categoryNameValid = false;
			}
			else {
				$("#categoryNameError").empty();
				$.post(
						'<c:url value="/education/validCategoryName"/>'
						, 'categoryName=' + categoryName + '&categoryType=' + categoryType
						, function(isExist){
							if ( isExist == 'true' ) {
								$("#categoryNameError").empty();
								$("#categoryNameError").append("이미 존재하는 이름 입니다.");
								categoryNameValid = false;
							}
							else {
								$("#categoryNameError").empty();
								$("#categoryNameError").append("사용 가능한 이름 입니다.");
								categoryNameValid = true;
							}
						}
				);
			}
		}
		
		$(".onlyText").keyup(function(event) {
			var regexp = /[\+*^!@\#$%<>&\()\=\’ \\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;
			var engregexp = /[a-zA-Z0-9-_]/gi;
			var noengregexp = /[^a-zA-Z0-9-_]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
			
			var tmpStr = $(this).val();
			var tmpStr2 = $(this).val();
			tmpStr = tmpStr.replace(engregexp, '');
			tmpStr2 = tmpStr2.replace(noengregexp, '');
			if ( (tmpStr.length * 3) + tmpStr2.length > 30 ) {
				alert("글자 수가 너무 큽니다.");
				while(v.length > 0){
					v = v.substring(0, v.length - 1);
					tmpStr = v;
					tmpStr2 = v;
					tmpStr = tmpStr.replace(engregexp, '');
					tmpStr2 = tmpStr2.replace(noengregexp, '');
					if ( (tmpStr.length * 3) + tmpStr2.length <= 30 ) {
						break;
					}
				}
				$(this).val(v);
			} 
		});
		
		$(".onlyEngText").keyup(function(event) {
			regexp = /[\+*^!@\#$%<>&\()\=\’ \\/\?,.\:\;\''\""\{\}\[\]|\\~`]/gi;
			engregexp = /[^a-zA-Z0-9-_]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
			if (engregexp.test(v)) {
				alert("숫자와 영문만 입력할 수 있습니다.");
				$(this).val(v.replace(engregexp, ''));
			}
			
			if ( v.length > 4 ) {
				alert("글자수가 4를 넘을 수 없습니다.");
				$(this).val(v.substring(0, 4));
			}
			
		});
		
		$(".noPaste").on('paste', function(e){
			e.preventDefault();
		});
		
		$("#newLargeCategoryBtn").click(function(){
			initCategoryIdAndName();
			categoryType.val('large');
			categoryId.attr('readonly', false);
			$("#addCategoryBtn").show();
			$("#modifyCategoryBtn").hide();
			var hasNewCategoryContainer = $('#largeCategoryListContainer #newCategoryContainer').length > 0;
			if ( !hasNewCategoryContainer ) {
				$("#largeCategoryListContainer").append($("#newCategoryContainer").detach());
				$("#newCategoryContainer").show();
			}
			else {
				$("#newCategoryContainer").toggle();
			}
		});
		
		$("#newMediumCategoryBtn").click(function(){
			var isParentCategorySelected = $('#largeCategoryList :selected').length > 0;
			if (  isParentCategorySelected ){
				initCategoryIdAndName();
				categoryType.val('medium');
				parentCategoryId.val($('#largeCategoryList').val());
				categoryId.attr('readonly', false);
				$("#addCategoryBtn").show();
				$("#modifyCategoryBtn").hide();
				var hasNewCategoryContainer = $('#mediumCategoryListContainer #newCategoryContainer').length > 0;
				if ( !hasNewCategoryContainer ) {
					$("#mediumCategoryListContainer").append($("#newCategoryContainer").detach());
					$("#newCategoryContainer").show();
				}
				else {
					$("#newCategoryContainer").toggle();
				}
			}
			else {
				alert('대분류를 선택해 주세요.');
			}
		});
		
		$("#newSmallCategoryBtn").click(function(){
			var isParentCategorySelected = $('#mediumCategoryList :selected').length > 0;
			if ( isParentCategorySelected ){
				initCategoryIdAndName();
				categoryType.val('small');
				parentCategoryId.val($('#mediumCategoryList').val());
				categoryId.attr('readonly', false);
				$("#addCategoryBtn").show();
				$("#modifyCategoryBtn").hide();
				var hasNewCategoryContainer = $('#smallCategoryListContainer #newCategoryContainer').length > 0;
				if ( !hasNewCategoryContainer ) {
					$("#smallCategoryListContainer").append($("#newCategoryContainer").detach());
					$("#newCategoryContainer").show();
				}
				else {
					$("#newCategoryContainer").toggle();
				}
			}
			else {
				alert('중분류를 선택해 주세요.');
			}
		});
		
		$("#modifyLargeCategoryBtn").click(function(){
			isCategorySelected = $('#largeCategoryList :selected').length > 0;
			if ( isCategorySelected ) {
				initCategoryIdAndName();
				categoryType.val('large');
				categoryId.val($("#largeCategoryList").val());
				categoryId.attr('readonly', true);
				categoryName.val($("#largeCategoryList option:selected").text());
				
				$("#addCategoryBtn").hide();
				$("#modifyCategoryBtn").show();
				var hasNewCategoryContainer = $('#largeCategoryListContainer #newCategoryContainer').length > 0;
				if ( !hasNewCategoryContainer ) {
					$("#largeCategoryListContainer").append($("#newCategoryContainer").detach());
					$("#newCategoryContainer").show();
				}
				else {
					$("#newCategoryContainer").toggle();
				}
			}
			else {
				alert('수정할 카테고리를 선택해 주세요.');
			}
		});
		
		$("#modifyMediumCategoryBtn").click(function(){
			isCategorySelected = $('#mediumCategoryList :selected').length > 0;
			if ( isCategorySelected ) {
				initCategoryIdAndName();
				categoryType.val('medium');
				categoryId.val($("#mediumCategoryList").val());
				categoryId.attr('readonly', true);
				categoryName.val($("#mediumCategoryList option:selected").text());
				$("#addCategoryBtn").hide();
				$("#modifyCategoryBtn").show();
				var hasNewCategoryContainer = $('#mediumCategoryListContainer #newCategoryContainer').length > 0;
				if ( !hasNewCategoryContainer ) {
					$("#mediumCategoryListContainer").append($("#newCategoryContainer").detach());
					$("#newCategoryContainer").show();
				}
				else {
					$("#newCategoryContainer").toggle();
				}
			}
			else {
				alert('수정할 카테고리를 선택해 주세요.');
			}
		});
		
		$("#modifySmallCategoryBtn").click(function(){
			isCategorySelected = $('#smallCategoryList :selected').length > 0;
			if ( isCategorySelected ) {
				initCategoryIdAndName();
				categoryType.val('small');
				categoryId.val($("#smallCategoryList").val());
				categoryId.attr('readonly', true);
				categoryName.val($("#smallCategoryList option:selected").text());
				$("#addCategoryBtn").hide();
				$("#modifyCategoryBtn").show();
				var hasNewCategoryContainer = $('#smallCategoryListContainer #newCategoryContainer').length > 0;
				if ( !hasNewCategoryContainer ) {
					$("#smallCategoryListContainer").append($("#newCategoryContainer").detach());
					$("#newCategoryContainer").show();
				}
				else {
					$("#newCategoryContainer").toggle();
				}
			}
			else {
				alert('수정할 카테고리를 선택해 주세요.');
			}
		});
		
		function deleteCategoryAjax(categoryType){
			$.post(
					'<c:url value="/education/deleteCategory"/>'
					, 'categoryId=' + $('#'+categoryType+'CategoryList :selected').val() + '&categoryType=' + categoryType
					, function(response){
						
						if ( response == 'true' ) {
							$('#'+categoryType+'CategoryList :selected').remove();
							if ( categoryType == 'large' ) {
								$('#mediumCategoryList').empty();
								$('#smallCategoryList').empty();
							}
							else if ( categoryType == 'medium' ) {
								$('#smallCategoryList').empty();
							}
						}
						else {
							alert('카테고리 삭제중 장애가 발생했습니다.');
						}
						
					}
			);
		}
		
		$("#deleteLargeCategoryBtn").click(function(){
			isCategorySelected = $('#largeCategoryList :selected').length > 0;
			if ( isCategorySelected ) {
				deleteCategoryAjax('large');
			}
			else {
				alert('삭제할 카테고리를 선택해 주세요.');
			}
		});
		
		$("#deleteMediumCategoryBtn").click(function(){
			isCategorySelected = $('#mediumCategoryList :selected').length > 0;
			if ( isCategorySelected ) {
				deleteCategoryAjax('medium');
			}
			else {
				alert('삭제할 카테고리를 선택해 주세요.');
			}
		});
		
		$("#deleteSmallCategoryBtn").click(function(){
			isCategorySelected = $('#smallCategoryList :selected').length > 0;
			if ( isCategorySelected ) {
				deleteCategoryAjax('small');
			}
			else {
				alert('삭제할 카테고리를 선택해 주세요.');
			}
		});
		
		$("#categoryId").keyup(function(){
			validCategoryId();
		});
		
		$("#categoryName").keyup(function(){
			validCategoryName(categoryName.val(), categoryType.val());
		});
		
		$("#addCategoryBtn").click(function(){
			validCategoryId();
			validCategoryName(categoryName.val(), categoryType.val());
			if ( categoryIdValid && categoryNameValid ) {
				$.post(
						'<c:url value="/education/addCategory"/>'
						, $("#newCategoryForm").serialize()
						, function(response){
							if ( response.result ) {
								$('#'+categoryType.val()+'CategoryList').append($("<option></option>")
											                    		.attr("value",categoryId.val())
											                    		.text(categoryName.val())); 
								
								initCategoryIdAndName();
							}
							else {
								for ( var i = 0; i < response.data.length; i++ ) {
									$("#"+response.data[i].field+"Error").append(response.data[i].defaultMessage+"<br/>");
								}
							}
						}
				);			
			}
		});
		
		$("#modifyCategoryBtn").click(function(){
			validCategoryName(categoryName.val(), categoryType.val());
			if ( categoryNameValid ) {
				$.post(
						'<c:url value="/education/modifyCategory"/>'
						, $("#newCategoryForm").serialize()
						, function(response){
							if ( response.result ) {
								$('#'+categoryType.val()+'CategoryList :selected').text(categoryName.val()); 
								
								initCategoryIdAndName();
							}
							else {
								for ( var i = 0; i < response.data.length; i++ ) {
									$("#"+response.data[i].field+"Error").append(response.data[i].defaultMessage+"<br/>");
								}
							}
						}
				);			
			}
		});
		
		$("#largeCategoryList").change(function(){

			$("#newCategoryContainer").hide();
			
			parentCategoryId.val($(this).val());
			$("#mediumCategoryList").empty();
			$("#smallCategoryList").empty();
			
			$.post(
					'<c:url value="/education/getChildCategory"/>'
					, 'categoryId=' + parentCategoryId.val() + '&categoryType=large'
					, function(response){
						if ( response.result ) {
							var mediumCategoryList = $("#mediumCategoryList");
							for ( var i = 0; i < response.data.length; i++ ) {
								mediumCategoryList.append($("<option></option>")
						                    		.attr("value",response.data[i].categoryId)
						                    		.text(response.data[i].categoryName)); 
							}
						}
						else {
							alert('하위 카테고리를 가져오지 못했습니다. 다시 시도해 주세요.');
						}
					}
			);
			
		});
		
		$("#mediumCategoryList").change(function(){
			
			$("#newCategoryContainer").hide();
			
			parentCategoryId.val($(this).val());
			$("#smallCategoryList").empty();
			
			$.post(
					'<c:url value="/education/getChildCategory"/>'
					, 'categoryId=' + parentCategoryId.val() + '&categoryType=medium'
					, function(response){
						if ( response.result ) {
							var smallCategoryList = $("#smallCategoryList");
							for ( var i = 0; i < response.data.length; i++ ) {
								smallCategoryList.append($("<option></option>")
						                    		.attr("value",response.data[i].categoryId)
						                    		.text(response.data[i].categoryName)); 
							}
						}
						else {
							alert('하위 카테고리를 가져오지 못했습니다. 다시 시도해 주세요.');
						}
					}
			);
			
		});
		
		$("#smallCategoryList").change(function(){
			
			$("#newCategoryContainer").hide();
			
		});
		
	});
</script>
<style type="text/css">
	div {
		float: left;
	}
	
	#newCategoryContainer {
		display: none;
	}
</style>
</head>
<body>

	<div id="largeCategoryListContainer">
		<select id="largeCategoryList" size="10">
			<c:if test="${ not empty largeCategoryList }">
				<c:forEach items="${ largeCategoryList }" var="category">
					<option value="${ category.categoryId }">${ category.categoryName }</option>
				</c:forEach>
			</c:if>
		</select>
		<br/>
		<input type="button" id="newLargeCategoryBtn" value="추가">
		<input type="button" id="modifyLargeCategoryBtn" value="변경">
		<input type="button" id="deleteLargeCategoryBtn" value="삭제">
		<br/>
		<div id="newCategoryContainer">
			<form:form commandName="newCategoryForm" method="post">
				<input type="hidden" id="parentCategoryId" name="parentCategoryId" >
				<input type="hidden" id="categoryType" name="categoryType" value="large" >
				<input type="text" id="categoryId" class="onlyEngText noPaste" name="categoryId" placeholder="category id" maxlength="4">
				<span id="categoryIdError"></span><br/>
				<input type="text" id="categoryName" class="onlyText noPaste" name="categoryName" placeholder="category name" maxlength="30">
				<span id="categoryNameError"></span><br/>
				<input type="button" id="addCategoryBtn" value="추가하기">
				<input type="button" id="modifyCategoryBtn" value="수정하기">
			</form:form>
		</div>
	</div>
	
	<div id="mediumCategoryListContainer">
		<select id="mediumCategoryList" size="10">
		</select>
		<br/>
		<input type="button" id="newMediumCategoryBtn" value="추가">
		<input type="button" id="modifyMediumCategoryBtn" value="변경">
		<input type="button" id="deleteMediumCategoryBtn" value="삭제">
		<br/>
	</div>
	
	<div id="smallCategoryListContainer">
		<select id="smallCategoryList" size="10">
		</select>
		<br/>
		<input type="button" id="newSmallCategoryBtn" value="추가">
		<input type="button" id="modifySmallCategoryBtn" value="변경">
		<input type="button" id="deleteSmallCategoryBtn" value="삭제">
		<br/>
	</div>

</body>
</html>