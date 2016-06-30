package com.ktds.sems.education.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.JsonResponseVO;
import com.ktds.sems.education.vo.TimeVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EducationServiceTest extends SemsTestCase {

	@Autowired
	private EducationService educationService;
	
	// costTest
	@Test
	public void getAllEduCostTest() {
		ModelAndView view = educationService.getAllEduCost();
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/cost");
			
			List<CostVO> costList = (List<CostVO>) view.getModelMap().get("costList");
			assertNotNull(costList);
			assertTrue(costList.size() >= 0);
		}
		else {
			fail("view is null");
		}
	}
	
	@Test
	public void ainsertEduCostTestError2() {
		CostVO cost = new CostVO();
		cost.setCdId("TEST");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "NO_DATA");
	}
	
	@Test
	public void ainsertEduCostTestError3() {
		CostVO cost = new CostVO();
		cost.setCdNm("TEST");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "NO_DATA");
	}
	

	public void ainsertEduCostTestError4() {
		CostVO cost = new CostVO();
		cost.setCdId("TEST1");
		cost.setCdNm("TEST1");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "DATAL_F");
	}
	

	public void ainsertEduCostTestError5() {
		CostVO cost = new CostVO();
		cost.setCdId("TEST1");
		cost.setCdNm("TEST1");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "DATAL_F");
	}
	
	@Test
	public void ainsertEduCostTestError6() {
		CostVO cost = new CostVO();
		cost.setCdId("CSTC");
		cost.setCdNm("유료");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "FAIL_V");
	}
	
	@Test
	public void ainsertEduCostTestError7() {
		CostVO cost = new CostVO();
		cost.setCdId("CSTC");
		cost.setCdNm("유");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "FAIL_V");
	}
	
	@Test
	public void ainsertEduCostTestError8() {
		CostVO cost = new CostVO();
		cost.setCdId("CSTS");
		cost.setCdNm("유료");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "FAIL_V");
	}
	
	@Test
	public void modifyEduCostTestError5() {
		CostVO cost = new CostVO();
		cost.setCdId("TES2");
		cost.setCdNm("TES1");
		
		String modifyEduCost = educationService.modifyEduCost(cost);
		assertNotNull(modifyEduCost);
		assertEquals(modifyEduCost, "OK");
	}
	
	public void modifyEduCostTestError1() {
		CostVO cost = new CostVO();
		cost.setCdId("TEST1");
		cost.setCdNm("TEST1");
		
		String modifyEduCost = educationService.modifyEduCost(cost);
		assertNotNull(modifyEduCost);
		assertEquals(modifyEduCost, "DATAL_F");
	}
	
	@Test
	public void modifyEduCostTestError2() {
		CostVO cost = new CostVO();
		cost.setCdId("CSTA");
		cost.setCdNm("TES2");
		
		String modifyEduCost = educationService.modifyEduCost(cost);
		assertNotNull(modifyEduCost);
		assertEquals(modifyEduCost, "FAIL_V");
	}
	
	@Test
	public void modifyEduCostTestError3() {
		CostVO cost = new CostVO();
		cost.setCdId("TES2");
		
		String modifyEduCost = educationService.modifyEduCost(cost);
		assertNotNull(modifyEduCost);
		assertEquals(modifyEduCost, "NO_DATA");
	}
	@Test
	public void modifyEduCostTestError4() {
		CostVO cost = new CostVO();
		cost.setCdNm("TES2");
		
		String modifyEduCost = educationService.modifyEduCost(cost);
		assertNotNull(modifyEduCost);
		assertEquals(modifyEduCost, "NO_DATA");
	}
	
	@Test
	public void zdeleteEduCostTest() {
		String cdId = "TES2";
		
		ModelAndView view = educationService.deleteEduCost(cdId);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
			assertEquals(viewName, "redirect:/education/cost");
		}
		else {
			fail("view is null");
		}
	}
	
	@Test
	public void ainsertEduCostTestError1() {
		CostVO cost = new CostVO();
		cost.setCdId("TES2");
		cost.setCdNm("TES2");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "OK");
	}
	
	// timeTest
	@Test
	public void insertEduTimeTestError1() {
		TimeVO time = new TimeVO();
		time.setCdId("TES2");
		time.setCdNm("TES2");
		
		String insertEduTime = educationService.insertEduTime(time);
		assertNotNull(insertEduTime);
		assertEquals(insertEduTime, "OK");
	}
	
	@Test
	public void insertEduTimeTestError2() {
		TimeVO time = new TimeVO();
		time.setCdId("TES2");
		
		String insertEduTime = educationService.insertEduTime(time);
		assertNotNull(insertEduTime);
		assertEquals(insertEduTime, "NO_DATA");
	}
	
	@Test
	public void insertEduTimeTestError3() {
		TimeVO time = new TimeVO();
		time.setCdNm("TES2");
		
		String insertEduTime = educationService.insertEduTime(time);
		assertNotNull(insertEduTime);
		assertEquals(insertEduTime, "NO_DATA");
	}
	

	public void insertEduTimeTestError4() {
		CostVO cost = new CostVO();
		cost.setCdId("TEST1");
		cost.setCdNm("TEST1");
		
		String insertEduCost = educationService.insertEduCost(cost);
		assertNotNull(insertEduCost);
		assertEquals(insertEduCost, "DATAL_F");
	}

	@Test
	public void insertEduTimeTestError6() {
		TimeVO time = new TimeVO();
		time.setCdId("TIMD");
		time.setCdNm("오전");
		
		String insertEduTime = educationService.insertEduTime(time);
		assertNotNull(insertEduTime);
		assertEquals(insertEduTime, "FAIL_V");
	}
	
	@Test
	public void insertEduTimeTestError7() {
		TimeVO time = new TimeVO();
		time.setCdId("TIMD");
		time.setCdNm("오");

		String insertEduTime = educationService.insertEduTime(time);
		assertNotNull(insertEduTime);
		assertEquals(insertEduTime, "FAIL_V");
	}
		
	@Test
	public void modifyEduTimeTestError5() {
		TimeVO time = new TimeVO();
		time.setCdId("TES2");
		time.setCdNm("TES1");
		
		String modifyEduTime = educationService.modifyEduTime(time);
		assertNotNull(modifyEduTime);
		assertEquals(modifyEduTime, "OK");
	}
	
	@Test
	public void modifyEduTimeTestError2() {
		TimeVO time = new TimeVO();
		time.setCdId("TIMA");
		time.setCdNm("TES2");
		
		String modifyEduTime = educationService.modifyEduTime(time);
		assertNotNull(modifyEduTime);
		assertEquals(modifyEduTime, "FAIL_V");
	}
	
	@Test
	public void modifyEduTimeTestError3() {
		TimeVO time = new TimeVO();
		time.setCdId("TES2");
		
		String modifyEduTime = educationService.modifyEduTime(time);
		assertNotNull(modifyEduTime);
		assertEquals(modifyEduTime, "NO_DATA");
	}
	
	@Test
	public void modifyEduTimeTestError4() {
		TimeVO time = new TimeVO();
		time.setCdNm("TES2");
	
		String modifyEduTime = educationService.modifyEduTime(time);
		assertNotNull(modifyEduTime);
		assertEquals(modifyEduTime, "NO_DATA");
	}
	
	@Test
	public void zdeleteEduTestTest() {
		String cdId = "TES2";
		
		ModelAndView view = educationService.deleteEduTime(cdId);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
			assertEquals(viewName, "redirect:/education/time");
		}
		else {
			fail("view is null");
		}
	}
	public void validCategoryIdTest(){
		String categoryId = "ZCS";
		String categoryType = "medium";
		String isExist = educationService.validCategoryId(categoryId, categoryType);
		assertTrue(isExist == "true");
	}
	
	@Test
	public void validCategoryNameTest(){
		String categoryName = "데이터베이스";
		String categoryType = "medium";
		String isExist = educationService.validCategoryName(categoryName, categoryType);
		assertTrue(isExist == "true");
	}
	
	@Test
	public void addNewCategoryTest(){
		String categoryId = "PAID";
		String categoryType = "large";
		List<CategoryVO> categories = (ArrayList) educationService.getChildCategory(categoryId, categoryType).getData();
		int prevCategoryCount = categories.size();
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
		categoryVO.setCategoryName("Test");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO response = educationService.addNewCategory(categoryVO, errors);
		assertNotNull(response);
		assertTrue(response.getResult());
		
		List<CategoryVO> categories2 = (ArrayList) educationService.getChildCategory(categoryId, categoryType).getData();
		int nextCategoryCount = categories2.size();
		assertTrue(nextCategoryCount - prevCategoryCount == 1);
	}
	
	@Test
	public void deleteCategoryTest(){
		String categoryId = "PAID";
		String categoryType = "large";
		List<CategoryVO> categories = (ArrayList) educationService.getChildCategory(categoryId, categoryType).getData();
		int prevCategoryCount = categories.size();
		
		String deleteCategoryId = "TEST";
		String deleteCategoryType = "medium";
		
		String response = educationService.deleteCategory(deleteCategoryId, deleteCategoryType);
		assertNotNull(response);
		assertTrue(Boolean.parseBoolean(response));
		
		categories = (ArrayList) educationService.getChildCategory(categoryId, categoryType).getData();
		int nextCategoryCount = categories.size();
		assertTrue(prevCategoryCount - nextCategoryCount == 1);
	}
	
	@Test
	public void addNewCategoryTestWithError1(){
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
//		categoryVO.setCategoryId("TEST");
		categoryVO.setCategoryName("Test");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO response = educationService.addNewCategory(categoryVO, errors);
		assertNotNull(response);
		assertTrue(!response.getResult());
		
		assertTrue(errors.getErrorCount() == 1);
		
	}
	
	@Test
	public void addNewCategoryTestWithError2(){
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
//		categoryVO.setCategoryName("Test");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO response = educationService.addNewCategory(categoryVO, errors);
		assertNotNull(response);
		assertTrue(!response.getResult());
		
		assertTrue(errors.getErrorCount() == 1);
		
	}
	
	@Test
	public void addNewCategoryTestWithError3(){
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
//		categoryVO.setCategoryId("TEST");
//		categoryVO.setCategoryName("Test");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO response = educationService.addNewCategory(categoryVO, errors);
		assertNotNull(response);
		assertTrue(!response.getResult());
		
		assertTrue(errors.getErrorCount() == 2);
		
	}
	
	@Test
	public void viewCategoryPage(){
		ModelAndView view = educationService.viewCategoryPage();
		String viewName = view.getViewName();
		assertNotNull(view);
		assertTrue(viewName == "education/category");
	}
	
	@Test
	public void getChildCategoryTest(){
		String categoryId = "ZCS";
		String categoryType = "large";
		JsonResponseVO jsonResponseVO = educationService.getChildCategory(categoryId, categoryType);
		assertTrue(jsonResponseVO.getResult());
	}
	
	@Test
	public void modifyCategoryTest(){
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("ZCS");
		categoryVO.setCategoryName("데이터베이스");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO jsonResponseVO = educationService.modifyCategory(categoryVO, errors);
		assertNotNull(jsonResponseVO);
		assertTrue(jsonResponseVO.getResult());
	}
	
	public class CategoryVOValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return CategoryVO.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			CategoryVO categoryVO = (CategoryVO) target;
			
			String categoryId = categoryVO.getCategoryId();
			if ( categoryId == null || categoryId.length() == 0 ) {
				errors.rejectValue("categoryId", "field.required", "error default message");
			}
			
			String categoryName = categoryVO.getCategoryName();
			if ( categoryName == null || categoryName.length() == 0 ) {
				errors.rejectValue("categoryName", "field.required", "error default message");
			}
		}
		
	}
	
}
