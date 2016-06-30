package com.ktds.sems.pc.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class PcDAOTest extends SemsTestCase {

	@Autowired
	private PcDAO pcDAO;
	
	@Test
	public void getMyReportedPcListTest() {
		
		ReportedPcSearchVO reportedPcSearchVO = new ReportedPcSearchVO();
		reportedPcSearchVO.setPageNo(0);
		
		Paging paging = new Paging();
		reportedPcSearchVO.setMemberId("cocomo12");
		
		paging.setPageNumber(reportedPcSearchVO.getPageNo() + "");
		int totalCount = pcDAO.getTotalMyReportedPcCount(reportedPcSearchVO);
		paging.setTotalArticleCount(totalCount);
		
		reportedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportedPcSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<ReportedPcVO> reportedPcList = pcDAO.getMyReportedPcList(reportedPcSearchVO);
		
		if(reportedPcList != null) {
			
			for (ReportedPcVO reportedPcVO : reportedPcList) {
				assertNotNull(reportedPcVO.getReportedPcId());
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void reportProblemPcTest() {
	
		ReportedPcVO reportedPcVO = new ReportedPcVO();
		reportedPcVO.setReportedCategory("computer");
		reportedPcVO.setReportedComment("JUnit test...");
		reportedPcVO.setPcId("1");
		reportedPcVO.setReportedPcId("JUnit test");
		reportedPcVO.setMemberId("cocomo12");
		
		int check = pcDAO.reportProblemPc(reportedPcVO);
		assertTrue(check > 0);
	}
	
	@Test
	public void getEduListByMemberTest(){
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMember01");
		List<EducationVO> eduList = pcDAO.getEduListByMember(memberVO);
		
		assertNotNull(eduList);
	}
	
	@Test
	public void getUsedPcListByMemberTest(){
		MemberVO memberVO = new MemberVO();
		memberVO.setId("JunitTest");
		List<UsedPcVO> usedPcList = pcDAO.getUsedPcListByMember(memberVO);
		
		assertNotNull(usedPcList);
	}
	
	@Test
	public void doRegisterMyPcTest(){
		UsedPcVO usedPcVO = new UsedPcVO();
		usedPcVO.setPcId("JunitTest");
		usedPcVO.setEducationId("JunitTest");
		usedPcVO.setMemberId("JunitTest");
		int result = pcDAO.doRegisterMyPc(usedPcVO);
		
		assertNotNull(result);
	}
	
	@Test
	public void getEduListExceptUsedTest(){
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMember01");
		List<EducationVO> result = pcDAO.getEduListExceptUsed(memberVO);
		
		assertNotNull(result);
	}
	
	@Test
	public void getPcIdByIpTest() {
		String result = pcDAO.getPcIdByIp("10.225.152.167");
		assertNotNull(result);
	}
}
