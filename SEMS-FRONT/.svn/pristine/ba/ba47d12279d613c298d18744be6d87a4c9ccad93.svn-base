package com.ktds.sems.member.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;

import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class MemberControllerTest extends SemsTestCase {

	@Autowired
	private MemberController memberController;
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void viewModifyPageTest() {
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("test01");
		member.setMemberType("MBR");
		session.setAttribute("_MEMBER_", member);
		ModelAndView view = memberController.viewModifyPage(session);

		assertNotNull(view);

	}

	@Test
	public void doModifyActionTest() {
		MemberVO member = new MemberVO();
		member.setId("aaa12");
		member.setPassword("123qwe!@#qwe");
		member.setName("JUNIT홍길동");
		member.setEmail("JUNIT@naver.com");
		member.setPhoneNumber("010-1234-1234");
		member.setBirthDate("1999-12-01");
		String graduationType = "휴학";
		String helCodeName = "대졸";
		BindingResult errors = new BeanPropertyBindingResult(member, "member");

		ModelAndView view = memberController.doModifyAction(member, errors, graduationType, helCodeName);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/member/myPage");

		} else {
			fail("view is null");
		}
	}

	@Test
	public void viewMyPageTest() {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("_MEMBER_", memberVO);
		
		ModelAndView view = memberController.viewMyPage(session);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/myPage");
		} else {
			fail("view is null");
		}

	}

	@Test
	public void viewCheckPasswordPageTest() {

		ModelAndView view = memberController.viewCheckPasswordPage();

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(view);
			assertEquals(viewName, "member/checkPassword");
		} else {
			fail("view is null");
		}

	}


	@Test
	public void registerPolicy() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();

		String viewName = memberController.viewRegisterPage(session);
		assertNotNull(viewName);

		if (viewName != null) {
			assertEquals(viewName, "member/registerPolicy");
		} else {
			fail("Fail...");
		}
	}

	@Test
	public void registerPolicyErrorCaseExistSession() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		session.setAttribute("_MEMBER_", sessionMember);

		String viewName = memberController.viewRegisterPage(session);
		assertNotNull(viewName);

		if (viewName != null) {
			assertEquals(viewName, "member/registErrorPage");
		} else {
			fail("Fail...");
		}
	}

	@Test
	public void registerStudent() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();

		ModelAndView view = memberController.viewRegisterStudentPage();
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/registerStudent");

			List<HighestEducationLevelVO> highestEducationLevelList = (List<HighestEducationLevelVO>) view.getModel().get("highestEducationLevelList");
			assertNotNull(highestEducationLevelList);

			List<GraduationTypeVO> graduationTypeList = (List<GraduationTypeVO>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
		} else {
			fail("Fail...");
		}
	}

	@Test
	public void registerStudentErrorCaseExistSession() {
/*		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		session.setAttribute("_MEMBER_", sessionMember);

		ModelAndView view = memberController.viewRegisterStudentPage();
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/registErrorPage");
		} else {
			fail("Fail...");
		}*/
	}

	@Test
	public void registerTeacher() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();

		String viewName = memberController.viewRegisterTeacherPage();
		assertNotNull(viewName);

		if (viewName != null) {
			assertEquals(viewName, "member/registerTeacher");
		} else {
			fail("Fail...");
		}
	}

	@Test
	public void registerTeacherErrorCaseExistSession() {
/*		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		session.setAttribute("_MEMBER_", sessionMember);

		String viewName = memberController.viewRegisterTeacherPage();
		assertNotNull(viewName);

		if (viewName != null) {
			assertEquals(viewName, "member/registErrorPage");
		} else {
			fail("Fail...");
		}*/
	}

	/**
	 * 나의 교육 이력 보기
	 */
	@Test
	public void viewEducationHistroyPage() {

		MemberVO memberVO = new MemberVO();
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO(); 
		memberVO.setId("test01");

		int pageNo = 0;
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, memberVO);

		ModelAndView view = memberController.viewEducationHistroyPage(educationHistorySearchVO,pageNo, session);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/educationHistory");

			EducationHistoryListVO educationHistoryListVO = (EducationHistoryListVO) view.getModelMap()
					.get("educationHistoryListVO");
			assertNotNull(educationHistoryListVO);

			List<EducationHistoryVO> educationHistoryList = educationHistoryListVO.getEducationHistoryList();
			assertNotNull(educationHistoryList);
			assertTrue(educationHistoryList.size() > 0);

			Paging paging = educationHistoryListVO.getPaging();
			assertNotNull(paging);
			assertTrue(paging.getTotalArticleCount() > 0);

		} else {
			fail("fail");
		}

	}

	@Test
	public void saveLoginHistoryAsExcelTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");

		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, memberVO);

		ModelAndView view = memberController.saveLoginHistoryAsExcel(session);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/member/loginHistory");
		} else {
			fail("[Controller Part]saveLoginHistoryAsExcelTest Fail.");
		}
	}

	@Test
	public void viewLoginHistoryPageTest() {
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setId("test04");

		int pageNo = 0;
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		session.setAttribute(Session.MEMBER, loginHistorySearchVO);

		ModelAndView view = memberController.viewLoginHistoryPage(loginHistorySearchVO, pageNo, session, request);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/loginHistory");
			LoginHistoryListVO loginHistoryListVO = (LoginHistoryListVO) view.getModel().get("loginHistoryListVO");
			LoginHistorySearchVO loginSearchVO = (LoginHistorySearchVO) view.getModel().get("loginHistorySearchVO");
			assertNotNull(loginHistoryListVO);
			assertNotNull(loginSearchVO);
			Paging paging = loginHistoryListVO.getPaging();
			assertNotNull(paging);
			assertTrue(paging.getTotalArticleCount() > 0);
			List<LoginHistoryVO> loginHistoryList = loginHistoryListVO.getLoginHistoryList();
			
			for (LoginHistoryVO loginHistoryVO : loginHistoryList) {
				assertNotNull(loginHistoryVO.getLgiHtrId());
				assertNotNull(loginHistoryVO.getId());
				assertNotNull(loginHistoryVO.getLgiIp());
				assertNotNull(loginHistoryVO.getLgiDt());
		//		assertNotNull(loginHistoryVO.getLgoDt());
		//		assertNotNull(loginHistoryVO.getIsReq());
		//		assertNotNull(loginHistoryVO.getChkCnt());
			}
			
			assertNotNull(loginHistoryList);
			assertTrue(loginHistoryList.size() > 0);

		} else {
			fail("[Controller Part]viewLoginHistoryPageTest Fail.");
		}
	}

	@Test
	public void doRequestIpHistoryTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiHtrId(1048);
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, loginHistoryVO);

		ModelAndView view = memberController.doRequestIpHistory(loginHistoryVO.getLgiHtrId(), session);
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/member/loginHistory");
		} else {
			fail("[Controller Part]doRequestIpHistoryTest Fail.");
		}
	}

	@Test
	public void doCheckIpTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiHtrId(1048);
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, loginHistoryVO);
	
		ModelAndView view = memberController.doCheckIp(loginHistoryVO.getLgiHtrId(), session);
		if( view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/checkIP");
			LoginHistoryVO loginHistory = (LoginHistoryVO) view.getModel().get("loginHistory");
			assertNotNull(loginHistory);
		} else {
			fail("[Controller Part]doCheckIpTest Fail.");
		}
	}
	/**
	 * 나의 교육 이력 엑셀 다운로드
	 */
	@Test
	public void eduationHistoryExportExcelTest() {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, memberVO);
		
		String returnString = memberController.eduationHistoryExportExcel(session);
		assertNotNull(returnString);
	}

	@Test
	public void writeResignCourseTest(){
		String educationId = "JunitEduId";
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("JunitMbrId");
		session.setAttribute("_MEMBER_", sessionMember);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("myPage/resignCourseWrite");
		
		assertEquals(view.getViewName(), memberController.writeResignCourse(educationId,session).getViewName());

	}

	@Test
	public void loginForResine(){
		
		ModelAndView view = new ModelAndView();
		
		String resignCode = "3226f65a-5aec-4fe0-a56a-26f4b5d546b2";
		String id = "test02";
		String viewName = "member/loginForResign";
		view = memberController.loginForResine(resignCode, id);
		
		assertEquals(viewName, view.getViewName());
	}
	
	@Test
	public void viewMyEduCoursePageTest() {
		ModelAndView view = new ModelAndView();
		int pageNo = 0;
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("oph312");
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("_MEMBER_", memberVO);
		
		EducationSearchVO educationSearchVO = new EducationSearchVO();
		educationSearchVO.setSearchKeyword("ll");
		view = memberController.viewMyEduCoursePage(pageNo, session, educationSearchVO);
		
		if ( view != null ) {
			assertNotNull(view);
		}
		else {
			fail("Fail...");
		}
	}
	

	
}
