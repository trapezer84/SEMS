package com.ktds.sems.pc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;

@Transactional
public class PcServiceTest extends SemsTestCase {

	@Autowired
	private PcService pcService;

	@Before
	public void setUp() {

		// 테스트 멤버 생성
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
	public void getMyReportedPcListTest() {

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("junitTest1316");
		session.setAttribute(Session.MEMBER, memberVO);

		ReportedPcSearchVO reportedPcSearchVO = new ReportedPcSearchVO();
		reportedPcSearchVO.setPageNo(0);
		reportedPcSearchVO.setSearchType("reportedPcId");
		reportedPcSearchVO.setSearchKeyword("0");

		ModelAndView view = pcService.getMyReportedPcList(session, reportedPcSearchVO);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "/myPage/pc/myReportedPcList");

		} else {
			fail("fail");
		}
	}

	@Test
	public void viewReportPcPageTest() {

		String pcId = "testPcNumber";

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("junitTest1316");
		session.setAttribute(Session.MEMBER, memberVO);

		MockHttpServletRequest request = new MockHttpServletRequest();
		ModelAndView view = pcService.viewReportPcPage(pcId, session, request);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "myPage/pc/reportMyPc");
		} else {
			fail("fail....");
		}

	}

	@Test
	public void reportProblemPcTest() {
		ReportedPcVO reportedPcVO = new ReportedPcVO();
		reportedPcVO.setReportedCategory("JUnit");
		reportedPcVO.setReportedComment("JUnit test...");
		reportedPcVO.setPcId("testPcNumber");

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("junitTest1316");
		session.setAttribute(Session.MEMBER, memberVO);

		String reportProblemPc = pcService.reportProblemPc(reportedPcVO, response, session);
		assertNotNull(reportProblemPc);
	}

	@Test
	public void viewMyPcPageTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("junitTest1316");
		session.setAttribute(Session.MEMBER, memberVO);

		ModelAndView view = pcService.viewMyPcPage(session, request);
		assertNotNull(view);
		assertEquals(view.getViewName(), "myPage/pc/myPc");
	}

	@Test
	public void doRegisterMyPcTest1() {
		String educationId = "JunitTest";
		String eduLocation = "JunitTest";
		String usedPcIp = "00.00JunitTest";

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("junitTest1316");
		session.setAttribute(Session.MEMBER, memberVO);

		String result = pcService.doRegisterMyPc(educationId, eduLocation, usedPcIp, session);
		assertNotNull(result);
		assertEquals(result, "FAIL");
	}
	
	@Test
	public void doRegisterMyPcTest2() {
		String educationId = "JunitTest";
		String eduLocation = "JunitTest";
		String usedPcIp = "10.225.152.167";
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("junitTest1316");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String result = pcService.doRegisterMyPc(educationId, eduLocation, usedPcIp, session);
		assertNotNull(result);
		assertEquals(result, "OK");
	}
	
	@Test
	public void doDeleteMyPcTest() {
		String pcId = "JunitTest";
		String result = pcService.doDeleteMyPc(pcId);
		assertEquals(result, "redirect:/member/myPc");
	}
	
	@Test
	public void getEduLocationByIdTest() {
		String educationId = "JunitTest";
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("junitTest1316");
		session.setAttribute(Session.MEMBER, memberVO);
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		pcService.getEduLocationById(educationId, response, session);
	}

}
