package com.ktds.sems.pc.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PcDAOTest extends SemsTestCase {

	@Autowired
	private PcDAO pcDAO;
	
	@Before
	public void setUp() {
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				PcVO pcVO = new PcVO();
				pcVO.setEducationPlaceId("test");
				pcVO.setEducationPlaceName("testEducationPlaceName");
				pcVO.setEducationLocation("testEducationLocation");
				pcVO.setPcId("test");
				pcVO.setPcName("testPcName");
				pcVO.setIp("testIp");
				pcDAO.doRegistEduPlace(pcVO);
				pcDAO.doRegistPC(pcVO);
			}
		});
	}

	@After
	public void tearDown() {
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				PcVO pcVO = new PcVO();
				pcVO.setEducationPlaceId("test");
				pcVO.setEducationPlaceName("testEducationPlaceName");
				pcVO.setEducationLocation("testEducationLocation");
				pcVO.setPcId("test");
				pcVO.setPcName("testPcName");
				pcVO.setIp("testIp");
				pcDAO.doActionDeleteEduPlace(pcVO.getEducationPlaceId());
				pcDAO.doActionDeleteEduPC(pcVO.getPcId());
			}
		});
	}
	
	
	@Test
	public void getUsedPcListTest() {
		UsedPcSearchVO usedPcSearchVO = new UsedPcSearchVO();
		usedPcSearchVO.setPageNo(0);
		usedPcSearchVO.setSearchType("ip");
		usedPcSearchVO.setSearchKeyword("0");
		
		Paging paging = new Paging();
		paging.setPageNumber(usedPcSearchVO.getPageNo() + "");
		paging.setTotalArticleCount(pcDAO.getTotalUsedPcCount(usedPcSearchVO));
		
		usedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		usedPcSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<UsedPcVO> usedPcList = pcDAO.getUsedPcList(usedPcSearchVO);
		if(usedPcList != null) {
			
			for (UsedPcVO usedPcVO : usedPcList) {
				assertNotNull(usedPcVO.getUsedPcId());
			}
			
		} else {
			fail("fail");
		}
		
		
	}

	@Test
	public void getReportedPcListWithPagingTest() {
		ReportedPcSearchVO reportedPcSearchVO = new ReportedPcSearchVO();
		reportedPcSearchVO.setPageNo(0);
		reportedPcSearchVO.setSearchType("pcName");
		reportedPcSearchVO.setSearchKeyword("0");
		
		Paging paging = new Paging();
		paging.setPageNumber(reportedPcSearchVO.getPageNo() + "");
		paging.setTotalArticleCount(pcDAO.getTotalReportedPcCount(reportedPcSearchVO));
		
		reportedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportedPcSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<ReportedPcVO> reportedPcList = pcDAO.getReportedPcListWithPaging(reportedPcSearchVO);
		if(reportedPcList != null) {
			
			for (ReportedPcVO reportedPcVO : reportedPcList) {
				assertNotNull(reportedPcVO.getReportedPcId());
			}
			
		} else {
			fail("fail");
		}
	}

	@Test
	public void changeReportedStateTest() {
		ReportedPcVO reportedPcVO = new ReportedPcVO();
		reportedPcVO.setReportedPcId("RP-20160524-000011");
		reportedPcVO.setReportedState("PC_PB_CH");
		
		int isSuccess = pcDAO.changeReportedState(reportedPcVO);
		assertTrue(isSuccess > 0);
	}
	
}
