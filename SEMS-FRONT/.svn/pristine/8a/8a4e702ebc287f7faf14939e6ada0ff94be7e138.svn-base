package com.ktds.sems.education.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.member.vo.MemberVO;

@Transactional
public class EducationControllerTest extends SemsTestCase {

	@Autowired
	private EducationController educationController;

	@Test
	public void viewRequestRetractionPageTest() {
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("egipyo21");
		session.setAttribute(Session.MEMBER, memberVO);
		String educationId = "ED-20160524-000288";
		ModelAndView view = educationController.viewRequestRetractionPage(session, educationId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/retraction");
	}

	@Test
	public void doRequestRetractionActionTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "ED-20160524-000288");
		request.setParameter("retractionMessage", "하기싫어요");

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("egipyo21");
		session.setAttribute(Session.MEMBER, memberVO);

		String result = educationController.doRequestRetractionAction(request, session);
		assertNotNull(result);
		assertEquals(result, "redirect:/member/myPage");
	}

	
	@Test
	public void showMyQNAListTest() {

		QNASearchVO qnaSearchVO = new QNASearchVO();
		MockHttpSession session = new MockHttpSession();

		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");

		session.setAttribute(Session.MEMBER, memberVO);

		ModelAndView view = educationController.showMyQNAList(qnaSearchVO, session);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);

		} else {
			fail("fail");
		}
	}
	
	@Test
	public void showMyQNADetailTest() {
		String replyId = "RP-20160512-000028";
		MockHttpSession session = new MockHttpSession();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		
		session.setAttribute(Session.MEMBER, memberVO);
		
		ModelAndView view = educationController.showMyQNADetail(replyId, session);
		
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void exportQNAListAsExcelTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, memberVO);
		String viewName = educationController.exportQNAListAsExcel(session);
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/myPage/myQNAList");
	}
	
	// @Test
	public void doWriteActionTest() {
		String educationId = "ED-20160510-000011";
		QNAVO qnaVO = new QNAVO();
		qnaVO.setDescription("댓글달자");

		MockHttpSession session = new MockHttpSession();

		MemberVO mockMbr = new MemberVO();
		mockMbr.setId("pleasure57");
		mockMbr.setPassword("11dd0e95242d3decc81eb693abfb25ce2945132b6b127cbd7175670fdf328c71");
		mockMbr.setName("황성재");
		mockMbr.setEmail("pleasure0507@hanmail.net");
		mockMbr.setHighestEducationLevel("UNIV");
		mockMbr.setUniversityName("백석대학교");
		mockMbr.setMajorName("정보통신학부");
		mockMbr.setGraduationType("EXPT");
		mockMbr.setBirthDate("1988-05-07");
		mockMbr.setPhoneNumber("01024410476");
		mockMbr.setMemberType("MBR");
		mockMbr.setSalt("12971a33944e134f");
		mockMbr.setLoginFailCount(0);
		mockMbr.setIsAccountLock("N");
		mockMbr.setLatestLoginDate("16/05/12");
		mockMbr.setResignDate("");
		mockMbr.setIsResign("N");
		mockMbr.setUuid("");
		mockMbr.setModifyFailCount(0);
		mockMbr.setIsModifyLock("N");

		session.setAttribute(Session.MEMBER, mockMbr);

		BindingResult errors = new BeanPropertyBindingResult(qnaVO, "qnaVO");

		ModelAndView view = educationController.doWriteAction(qnaVO, errors, educationId, session);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/eduDetail/" + educationId);
		} else {
			fail("view is null");
		}
	}
	
   //@Test
   public void doCancelEducationTest() {
      String educationId = "ED-20160513-000169";
      
      MockHttpSession session = new MockHttpSession();
      
      String dummy = educationController.doCancelEducation(educationId, session);
      
      assertNotNull(dummy);
   }
   
	
	@Test
	public void doSearchListTest() {
		String startYear = "2010";
		String startMonth = "01";
		String endYear = "2010";
		String endMonth = "02";
		String eduName = "JAVA Test";
		String educationType = "TMMMasdasdasdsdaM";
		String cost = "000";
		String pageNo = "0";

		ModelAndView view = educationController.doSearchList(startYear, startMonth, endYear, endMonth, eduName,
				educationType, cost, pageNo);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/list");
		} else {
			fail("view is null");
		}

	}

	@Test
	public void viewEducationListPageTest() {
		int pageNo = 0;

		ModelAndView view = educationController.viewEducationListPage(pageNo);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/list");
		} else {
			fail("view is null");
		}
	}

	@Test
	public void getOneEducationDetailTest() {

			int pageNo = 0;
			String educationId = "ED-20160526-000315";
			MockHttpSession session = new MockHttpSession();

			MemberVO mockMbr = new MemberVO();
			mockMbr.setId("pleasure57");
			mockMbr.setPassword("a4a8bfdd643d7a79322d0f3f8f3c7b5fec7d3f2df3f2108f103ab5dc605b6cb1");
			mockMbr.setName("황성재");
			mockMbr.setEmail("ab@aa.com");
			mockMbr.setHighestEducationLevel("TEST");
			mockMbr.setUniversityName("학과");
			mockMbr.setMajorName("학교");
			mockMbr.setGraduationType("TEST");
			mockMbr.setBirthDate("2016-01-01");
			mockMbr.setPhoneNumber("000-0000-0000");
			mockMbr.setMemberType("MBR");
			mockMbr.setSalt("149d23103698ba11");
			mockMbr.setLoginFailCount(0);
			mockMbr.setIsAccountLock("N");
			mockMbr.setLatestLoginDate("2016/05/26 오후 3:43:28");
			mockMbr.setResignDate("");
			mockMbr.setIsResign("N");
			mockMbr.setUuid("");
			mockMbr.setModifyFailCount(0);
			mockMbr.setIsModifyLock("N");

			session.setAttribute(Session.MEMBER, mockMbr);
			
			ModelAndView view = educationController.getOneEducationDetail(educationId, session, pageNo);
			assertNotNull(view);

			if (view != null) {
				String viewName = view.getViewName();
				assertNotNull(viewName);
				assertEquals(viewName, "education/eduDetail");
			} else {
				fail("view is null");
			}

		}
	
   @Test
   public void viewEducationCalendarPageTest(){
      ModelAndView view = educationController.viewEducationCalendarPage();
      assertNotNull(view);
   }
   
   
   //@Test
   public void doDownloadFileTest(){
      String educationId = "ED-20160513-000171";
      MockHttpServletResponse response = new MockHttpServletResponse();
      MockHttpServletRequest request = new MockHttpServletRequest();
      
      educationController.doDownloadFile(educationId, request, response);
      /*
      if (view != null) {
         String viewName = view.getViewName();
         assertNotNull(viewName);
         assertEquals(viewName, "education/list");
      } else {
         fail("view is null");
      }*/
   }
   
   @Test
   public void viewEduBoardQNAListPageTest() {
	   int pageNo = 0;
	   String educationId = "ED-20160519-000233";
	   String searchKeyword = "test";
	   String searchType = "selectMbrId";
	   
	   MockHttpSession session = new MockHttpSession();
	   
	   ModelAndView view = educationController.viewEduBoardQNAListPage(pageNo, educationId, searchKeyword, searchType, session);
	   assertNotNull(view);
   }
   
   @Test
   public void viewEduBoardQNAWritePageTest() {
	   String educationId = "ED-20160519-000233";
	   
	   ModelAndView view = educationController.viewEduBoardQNAWritePage(educationId);
	   assertNotNull(view);
   }
   
   @Test
   public void doQNAWriteActionTest() {
	   
	   EducationQNABBSVO eduBBS = new EducationQNABBSVO();
	   MockHttpSession session = new MockHttpSession();
	   MemberVO sessionMember = new MemberVO();
	   sessionMember.setId("test02");
	   
	   session.setAttribute("_MEMBER_", sessionMember);
	   
	   String educationId = "ED-20160519-000233";
	   
	   eduBBS.setTitle("JUNIT_TEST");
	   eduBBS.setContents("JUNIT_CONTENTS");
	   
	   BindingResult errors = new BeanPropertyBindingResult(eduBBS, "eduBBS");
	   
	   ModelAndView view = educationController.doQNAWriteAction(eduBBS, errors, session, educationId);
	   assertNotNull(view);
	   
   }
   
   @Test
   public void viewEduBoardQNADetailPageTest() {
	   String atcId = "AT-20160524-000034";
	   int pageNo = 0;
	   MockHttpSession session = new MockHttpSession();
	   MemberVO sessionMember = new MemberVO();
	   sessionMember.setId("test02");
	   
	   session.setAttribute("_MEMBER_", sessionMember);
	   
	   ModelAndView view = educationController.viewEduBoardQNADetailPage(atcId, pageNo, session);
	   assertNotNull(view);
   }

	
	
}
