package com.ktds.sems.pc.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
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
public class PcBizTest extends SemsTestCase {

	@Autowired
	private PcBiz pcBiz;
	
	@Before
	public void setUp() {
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				PcVO pcVO = new PcVO();
				pcVO.setEducationPlaceName("testEducationPlaceName");
				pcVO.setEducationLocation("testEducationLocation");
				
				List<PcVO> pcList = new ArrayList<PcVO>(); 
				PcVO newPcVO = new PcVO();
				newPcVO.setPcName("testPcName");
				newPcVO.setIp("testIp");
				pcList.add(newPcVO);
				pcVO.setPcList(pcList);
				
				pcBiz.doRegistEduPlace(pcVO);
				pcBiz.doRegistPC(pcVO);
			}
		});
	}

	@After
	public void tearDown() {
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				PcVO pcVO = new PcVO();
				pcVO.setPcName("testPcName");
				pcVO.setIp("testIp");
				pcVO.setEducationPlaceName("testEducationPlaceName");
				pcVO.setEducationLocation("testEducationLocation");
				
				PcVO getPcVO = pcBiz.callIdJunitTest(pcVO);
				pcBiz.doActionDeleteEduPlace(getPcVO.getEducationPlaceId());
				pcBiz.doActionDeleteEduPC(getPcVO.getPcId());
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
		paging.setTotalArticleCount(pcBiz.getTotalUsedPcCount(usedPcSearchVO));
		
		usedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		usedPcSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<UsedPcVO> usedPcList = pcBiz.getUsedPcList(usedPcSearchVO);
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
		paging.setTotalArticleCount(pcBiz.getTotalReportedPcCount(reportedPcSearchVO));
		
		reportedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportedPcSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<ReportedPcVO> reportedPcList = pcBiz.getReportedPcListWithPaging(reportedPcSearchVO);
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
		
		boolean isSuccess = pcBiz.changeReportedState(reportedPcVO);
		assertTrue(isSuccess);
	}
	@Test
	public void doRegistEduPlaceTest() {
		PcVO pcVO = new PcVO();
		pcVO.setEducationPlaceName("testEducationPlaceName");
		pcVO.setEducationLocation("testEducationLocation");
		String educationPlaceId = pcBiz.doRegistEduPlace(pcVO);
		if (educationPlaceId != null) {
			assertTrue(pcVO.getEducationPlaceId().equals(educationPlaceId));
		} else {
			fail("[Biz Part] doRegistEduPlaceTest fail.");
		}
	}

}
