package com.ktds.sems.cooperation.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

@Transactional
public class CooperationDAOTest extends SemsTestCase {

	@Autowired
	private CooperationDAO cooperationDAO;
	
	@Test
	public void getTotalCooperationTest() {
		CooperationSearchVO searchVO = new CooperationSearchVO();
		searchVO.setEndIndex(5);
		searchVO.setPageNo(1);
		searchVO.setSearchKeyword("e");
		searchVO.setSearchType("1");
		searchVO.setStartIndex(1);
		int result = cooperationDAO.getTotalCooperationCount(searchVO);
		assertNotNull(result);
	}
	
	@Test
	public void nextCooSeqTest() {
		int result = cooperationDAO.nextCooSeq();
		assertNotNull(result);
	}
	@Test
	public void nowDateTest() {
		String result = cooperationDAO.nowDate();
		assertNotNull(result);
	}
	@Test
	public void doRegisterCooTest() {
		int nextRegisterCooperationId = cooperationDAO.nextCooSeq();
		String nowDate = cooperationDAO.nowDate();
		String cooperationId = "JunitTest"+nowDate+nextRegisterCooperationId;
		
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setCooperationId(cooperationId);
		cooperationVO.setCooperationTitle("JunitTest");
		cooperationVO.setCooperationLocation("JunitTest");
		cooperationVO.setCooperationNumber("JunitTest");
		cooperationVO.setRepresentativeName("JunitTest");
		cooperationVO.setManagerPhoneNumber("JunitTest");
		cooperationVO.setCooperationPhoneNumber("JunitTest");
		cooperationVO.setManagerEmail("JunitTest");
		cooperationVO.setCooperationType("COO_COPR");
		int result = cooperationDAO.doRegisterCoo(cooperationVO);
		assertNotNull(result);
	}
	@Test
	public void isExistCooperationTitleTest() {
		String result = cooperationDAO.isExistCooperationTitle("JunitTest");
		assertNotNull(result);
	}
	@Test
	public void getTotalCooperationCountTest() {
		int result = cooperationDAO.getTotalCooperationCount();
		assertNotNull(result);
	}
	@Test
	public void getAllCooperationTest() {
		CooperationSearchVO searchVO = new CooperationSearchVO();
		searchVO.setPageNo(10);
		searchVO.setStartIndex(1);
		searchVO.setEndIndex(10);
		searchVO.setSearchKeyword("e");
		searchVO.setSearchKeyword("1");
		List<CooperationVO> resultList = cooperationDAO.getAllCooperation(searchVO);
		assertNotNull(resultList);
		assertTrue(resultList.size()>0);
	}
	@Test
	public void getOneCooperationTest() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		CooperationVO cooperationVO = cooperationDAO.getOneCooperation(cooperationId);
		assertNotNull(cooperationVO);
	}
	
	@Test
	public void doDeleteCooperation() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		int result = cooperationDAO.doDeleteCooperation(cooperationId);
		assertNotNull(result);
	}
	
	
	
	

}
