package com.ktds.sems.education.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class EducationDAOTest extends SemsTestCase{

	@Autowired
	private EducationDAO educationDAO;
	
	@Before
	public void setUp() {
		// TestCase 준비
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				CostVO cost = new CostVO();
				cost.setCdId("TES1");
				cost.setCdNm("TES1");
				educationDAO.insertEduCost(cost);
			}
		});
		
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				TimeVO time = new TimeVO();
				time.setCdId("TES1");
				time.setCdNm("TES1");
				educationDAO.insertEduTime(time);
			}
		});
		
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				CategoryVO categoryVO = new CategoryVO();
				categoryVO.setParentCategoryId("PAID");
				categoryVO.setCategoryType("medium");
				categoryVO.setCategoryId("TEST");
				categoryVO.setCategoryName("pppTestppp");
				educationDAO.addNewCategory(categoryVO);
			}
		});
	}
	
	@After
	public void tearDown() {
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				educationDAO.deleteEduTime("TES1");
				educationDAO.deleteEduCost("TES1");
				
				CategoryVO categoryVO = new CategoryVO();
				categoryVO.setParentCategoryId("PAID");
				categoryVO.setCategoryType("medium");
				categoryVO.setCategoryId("TEST");
				educationDAO.deleteCategory(categoryVO);
			}
		});
	}
	
	@Test
	public void modifyEduCostTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES2");
		
		int modifyEduCost = educationDAO.modifyEduCost(cost);
		assertTrue(modifyEduCost >0);
	}
	
	@Test
	public void getAllEduCostTest() {
		List<CostVO> costList = educationDAO.getAllEduCost();
		assertNotNull(costList);
		assertTrue(costList.size() >=0);
	}

	
	
	@Test
	public void getEduCostByCdIdTest() {
		CostVO cost = educationDAO.getEduCostByCdId("TES1");
		assertNotNull(cost);
	}
	
	@Test
	public void bisExistCostTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		int isExistCost = educationDAO.isExistCost(cost);
		assertTrue(isExistCost >0);
	}
	
	@Test
	public void bisExistCostNmTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		int isExistCostNm = educationDAO.isExistCostNm(cost);
		assertTrue(isExistCostNm > 0);
	}
	
	@Test
	public void modifyEduTimeTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES2");
		
		int modifyEduTime = educationDAO.modifyEduTime(time);
		assertTrue(modifyEduTime >0);
	}
	
	@Test
	public void getAllEduTimeTest() {
		List<TimeVO> timeList = educationDAO.getAllEduTime();
		assertNotNull(timeList);
		assertTrue(timeList.size() >=0);
	}

	@Test
	public void getEduTimeByCdIdTest() {
		TimeVO time = educationDAO.getEduTimeByCdId("TES1");
		assertNotNull(time);
	}
	
	@Test
	public void bisExistTimeTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES1");
		
		int isExistTime = educationDAO.isExistTime(time);
		assertTrue(isExistTime >0);
	}
	
	@Test
	public void bisExistTimeNmTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES1");
		
		int isExistTimeNm = educationDAO.isExistTimeNm(time);
		assertTrue(isExistTimeNm > 0);
	}
	
	@Test
	public void validCategoryIdTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId("ZCS");
		categoryVO.setCategoryType("medium");
		
		int result = educationDAO.validCategoryId(categoryVO);
		assertTrue(result == 1);
	}
	
	@Test
	public void validCategoryNameTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryName("데이터베이스");
		categoryVO.setCategoryType("medium");
		
		int result = educationDAO.validCategoryName(categoryVO);
		assertTrue(result == 1);
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void addNewCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
		categoryVO.setCategoryName("pppTestppp");
		int result = educationDAO.addNewCategory(categoryVO);
		assertTrue(result == 1);
	}
	
	@Test
	public void deleteCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
		int result = educationDAO.deleteCategory(categoryVO);
		assertTrue(result == 1);
	}
	
	@Test
	public void getAllLargeCategoryTest(){
		List<CategoryVO> largeCategories = educationDAO.getAllLargeCategory();
		assertNotNull(largeCategories);
	}
	
	@Test
	public void getChildCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryType("large");
		categoryVO.setCategoryId("ZCS");
		List<CategoryVO> childCategories = educationDAO.getChildCategory(categoryVO);
		assertNotNull(childCategories);
	}
	
	@Test
	public void modifyCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("ZCS");
		categoryVO.setCategoryName("데이터베이스");
		int result = educationDAO.modifyCategory(categoryVO);
		assertTrue(result == 1);
	}
	
}
