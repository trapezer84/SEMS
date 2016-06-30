package com.ktds.sems.cooperation.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.cooperation.dao.CooperationDAO;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

@Transactional
public class CooperationBizTest extends SemsTestCase {

	@Autowired
	private CooperationBiz cooperationBiz;
	@Autowired
	private CooperationDAO cooperationDAO;
	
	@Test
	public void getTotalCooperationCountTest() {
		CooperationSearchVO searchVO = new CooperationSearchVO();
		searchVO.setEndIndex(5);
		searchVO.setPageNo(1);
		searchVO.setSearchKeyword("e");
		searchVO.setSearchType("1");
		searchVO.setStartIndex(1);
		int result = cooperationBiz.getTotalCooperationCount(searchVO);
		assertNotNull(result);
	}
	
	@Test
	public void doRegisterCooTest() {
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setCooperationTitle("JunitTest");
		cooperationVO.setCooperationLocation("JunitTest");
		cooperationVO.setCooperationNumber("JunitTest");
		cooperationVO.setRepresentativeName("JunitTest");
		cooperationVO.setManagerPhoneNumber("JunitTest");
		cooperationVO.setCooperationPhoneNumber("JunitTest");
		cooperationVO.setManagerEmail("JunitTest");
		cooperationVO.setCooperationType("COO_COPR");
		boolean result = cooperationBiz.doRegisterCoo(cooperationVO);
		assertTrue(result);
	}

	@Test
	public void getAllCooperationTest() {
		CooperationSearchVO searchVO = new CooperationSearchVO();
		searchVO.setPageNo(10);
		searchVO.setStartIndex(10);
		searchVO.setEndIndex(10);
		searchVO.setSearchKeyword("e");
		searchVO.setSearchKeyword("1");
		List<CooperationVO> resultList = cooperationBiz.getAllCooperation(searchVO);
		assertNotNull(resultList);
	}
	
	@Test
	public void getOneCooperationTest() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		CooperationVO coo = cooperationBiz.getOneCooperation(cooperationId);
		assertNotNull(coo);
	}
	@Test
	public void doDeleteCooperationTest() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		boolean result = cooperationBiz.doDeleteCooperation(cooperationId);
		assertTrue(result);
	}
	@Test
	public void isExistCooperationTitleTest() {
		String cooperationTitle ="JunitTest";
		boolean result = cooperationBiz.isExistCooperationTitle(cooperationTitle);
		assertTrue(result);
	}
	@Test
	public void doModifyCooTest() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setCooperationId(cooperationId);
		cooperationVO.setCooperationTitle("JunitTest");
		cooperationVO.setCooperationLocation("JunitTest");
		cooperationVO.setManagerEmail("JunitTest");
		cooperationVO.setCooperationType("COO_COPR");
		boolean result = cooperationBiz.doModifyCoo(cooperationVO);
		assertTrue(result);
	}
	
	
}
