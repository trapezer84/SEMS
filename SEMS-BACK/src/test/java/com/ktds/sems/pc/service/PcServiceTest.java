package com.ktds.sems.pc.service;

import static org.junit.Assert.assertEquals;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationPlaceListVO;
import com.ktds.sems.education.vo.EducationPlaceSearchVO;
import com.ktds.sems.education.vo.EducationPlaceVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PcServiceTest extends SemsTestCase {
	
	@Autowired
	private PcService pcService;
	
	@Before
	public void setUp() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
			}
		});
	}
	
	@After
	public void tearDown() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
			}
		});
	}
	
	@Test
	public void getUsedPcListTest() {
		
		UsedPcSearchVO usedPcSearchVO = new UsedPcSearchVO();
		usedPcSearchVO.setPageNo(0);
		usedPcSearchVO.setSearchType("ip");
		usedPcSearchVO.setSearchKeyword("0");
		
		ModelAndView view = pcService.getUsedPcList(usedPcSearchVO);
		
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "pc/usedPcList");
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
		
		ModelAndView view = pcService.getReportedPcListWithPaging(reportedPcSearchVO);
		
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "pc/reportedPcList");
		} else {
			fail("fail");
		}
	}

	@Test
	public void changeReportedStateTest() {
		ReportedPcVO reportedPcVO = new ReportedPcVO();
		reportedPcVO.setReportedPcId("RP-20160524-000011");
		reportedPcVO.setReportedState("PC_PB_CH");
		
		String data = pcService.changeReportedState(reportedPcVO);
		assertNotNull(data);
	}
	
	@Test
	public void educationPlaceSettingTest() {
		MockHttpSession session = new MockHttpSession();
		ModelAndView view = pcService.educationPlaceSetting(session);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "pc/eduPlaceSet");
		}
		else {
			fail("fail");
		}
	}
	
	@Test
	public void doRegistClassTest() {
		
		PcVO pcVO = new PcVO();
		pcVO.setEducationPlaceName("206호 Test");
		pcVO.setEducationLocation("206호 Test");
		pcVO.setIp("123.123.123.213");
		pcVO.setPcName("TEST");
		
		List<PcVO> pcList = new ArrayList<PcVO>();
		pcList.add(pcVO);
		pcVO.setPcList(pcList);
		
		MockHttpSession session = new MockHttpSession();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setMemberType("ADM");
		
		session.setAttribute(Session.MEMBER, memberVO);
		
		ModelAndView view = pcService.doRegistClass(pcVO, session);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/eduPlaceList");
		}
		else {
			fail("fail");
		}
	}
	
	@Test
	public void viewEducationPlaceList() {
		
		int pageNo = 0;
		EducationPlaceSearchVO eduPlaceSearchVO = new EducationPlaceSearchVO();
		
		ModelAndView view = pcService.viewEducationPlaceList(eduPlaceSearchVO, pageNo);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduPlaceList");
			
			EducationPlaceListVO eduListVO = (EducationPlaceListVO) view.getModelMap().get("eduListVO");
			assertNotNull(eduListVO);
			
			List<EducationPlaceVO> eduPlaceList = eduListVO.getEduPlaceList();
			assertNotNull(eduPlaceList);
			assertTrue(eduPlaceList.size() > 0);
		}
		else {
			fail("fail");
		}
	}
	
	@Test
	public void doActionDeleteEduPlaceTest() {
		
		MockHttpSession session = new MockHttpSession();
		MockHttpServletResponse response = new MockHttpServletResponse();
		String educationPlaceId = "EP-20160526-000013";
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setMemberType("ADM");
		session.setAttribute(Session.MEMBER, memberVO);
		
		ModelAndView view = pcService.doActionDeleteEduPlace(educationPlaceId, session, response);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduPlaceList");
		}
		else{
			fail("fail");
		}
	}
	
	@Test
	public void doActionDeleteEduPCTest() {
		
		MockHttpSession session = new MockHttpSession();
		MockHttpServletResponse response = new MockHttpServletResponse();
		String pcId = "PI-20160526-000026";
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setMemberType("ADM");
		session.setAttribute(Session.MEMBER, memberVO);
		
		ModelAndView view = pcService.doActionDeleteEduPC(pcId, session, response);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduPlaceList");
		}
		else{
			fail("fail");
		}
	}

	
}
