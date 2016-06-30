package com.ktds.sems.member.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationCostVO;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationStateVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;
import com.ktds.sems.validator.member.MemberValidator;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class MemberServiceTest extends SemsTestCase {

	@Autowired
	private MemberService memberService;

	/**
	 * @author 김동규
	 * Action - Insert
	 */
	@Before
	public void setUp() {
		testHelper(new Testable() {
			@Override
			public void preparedTest() {

			}
		});
	}
	
	/**
	 * @author 김동규
	 * Action - Delete
	 */
	@After
	public void tearDown() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				
			}
		});
	}
	
	/**
	 * 로그인
	 */
	@Test
	public void loginTest() {

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setPassword("123qwe!@#qwe");

		BindingResult errors = new BeanPropertyBindingResult(memberVO, "loginForm");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.LOGIN_HISTORY, new LoginHistoryVO());

		String checkStr = memberService.login(memberVO, errors, session, request);
		assertNotNull(checkStr);
	}

	/**
	 * 회원탈퇴 신청 시 uuid 삽입
	 */
	@Test
	public void insertUuidForResignTest() {

		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();

		member.setId("JunitResign");

		session.setAttribute("_MEMBER_", member);
		String checkStr = memberService.insertUuidForResign(session);
		assertNotNull(checkStr);
	}

	/**
	 * 탈퇴 신청한 회원에게 이메일 보내기 이메일이 보내지지 않아 Junit에러 발생
	 */
	// @Test
	// public void sendEmailForResignTest() {
	//
	// ModelAndView view = new ModelAndView();
	// MemberVO member = new MemberVO();
	// MockHttpSession session = new MockHttpSession();
	//
	// member.setId("test02");
	// session.setAttribute("_MEMBER_", member);
	//
	// String uuid = "This is uuid sample";
	//
	// view = memberService.sendEmailForResign(session, uuid);
	// assertNotNull(view.getViewName());
	// }

	/**
	 * 탈퇴
	 */
	@Test
	public void loginForResignTest() {

		ModelAndView view = new ModelAndView();
		String resignCode = "This is resignCode sample";
		String id = "This is id sample";

		view = memberService.loginForResign(resignCode, id);
		assertNotNull(view);
	}

	@Test
	public void viewMyPageMenuTest() {
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("test02");
		session.setAttribute("_MEMBER_", member);
		ModelAndView view = memberService.viewMyPageMenu(session);

		if (view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/myPage");
			
			List<MenuManageVO> menuList = (List<MenuManageVO>) view.getModelMap().get("menuList");
			assertNotNull(menuList);
			assertTrue(menuList.size() > 0);
			
			List<LoginHistoryVO> loginHistoryList = (List<LoginHistoryVO>) view.getModelMap().get("loginHistoryList");
			assertNotNull(loginHistoryList);
			assertTrue(loginHistoryList.size() > 0);
			
			List<EducationHistoryVO> educationHistoryList = (List<EducationHistoryVO>) view.getModelMap().get("educationHistoryList");
			assertNotNull(educationHistoryList);
			assertTrue(educationHistoryList.size() > 0);
			
			List<QNAVO> qnaList = (List<QNAVO>) view.getModelMap().get("qnaList");
			assertNotNull(qnaList);
			assertTrue(qnaList.size() > 0);
			
			List<ReportReplyVO> reportReplyList = (List<ReportReplyVO>) view.getModelMap().get("reportReplyList");
			assertNotNull(reportReplyList);
		} else {
			fail("list is null");
		}
	}
	
	//TODO
	@Test
	public void addNewTeacherMemberTest() {
		MemberVO member = new MemberVO();
		member.setId("JunitId1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = "JunitPassword1@";
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("TR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpServletRequest requset = new MockHttpServletRequest();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		
		if(addNewMemberResultString != "OK" && addNewMemberResultString != "NO") {
			fail("fail....");
		} 
	}

	@Test
	public void addNewStudentMemberTest() {
		MemberVO member = new MemberVO();
		member.setId("JunitId1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		MockHttpServletResponse response = new MockHttpServletResponse();

		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		assertNotNull(addNewMemberResultString);

	}

	@Test
	public void addNewMemberTestErrorCaseId() {
		MemberVO member = new MemberVO();
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpServletRequest requset = new MockHttpServletRequest();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		
		if( addNewMemberResultString == "NO") {
			assertTrue(errors.getErrorCount() == 1);
			assertTrue(member.getId() == null);
		}
		else {
			fail("fail....");
		}
		
//		ModelAndView view = memberService.addNewMember(member, repeatPasswrod, errors, session, response, requset);
//		assertNotNull(view);
//
//		if (view != null) {
//			String viewName = view.getViewName();
//			assertNotNull(viewName);
//			assertEquals(viewName, "member/registerStudent");
//
//			List<HighestEducationLevelVO> highestEducationLevelList = (List<HighestEducationLevelVO>) view.getModel().get("highestEducationLevelList");
//			assertNotNull(highestEducationLevelList);
//
//			List<GraduationTypeVO> graduationTypeList = (List<GraduationTypeVO>) view.getModel().get("graduationTypeList");
//			assertNotNull(graduationTypeList);
//
//			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
//			assertNotNull(viewMember);
//			assertTrue(viewMember.getId() == null);
//
//			assertTrue(errors.getErrorCount() == 1);
//		} else {
//			fail("Fail...");
//		}
	}

	@Test
	public void addNewMemberTestErrorCasePassword() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setName("으악");
		String repeatPasswrod = ("JunitPassword1@");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		
		if( addNewMemberResultString == "NO") {
			assertTrue(errors.getErrorCount() == 1);
			assertTrue(member.getPassword() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCaseName() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);

		if( addNewMemberResultString == "NO") {
			assertTrue(errors.getErrorCount() == 1);
			assertTrue(member.getName() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCaseEmail() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		
		if( addNewMemberResultString == "NO") {
			assertTrue(errors.getErrorCount() == 1);
			assertTrue(member.getEmail() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCaseHighestEducationLevel() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		
		if( addNewMemberResultString == "NO") {
			assertTrue(member.getHighestEducationLevel() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCaseUniversityName() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		MockHttpServletResponse response = new MockHttpServletResponse();

		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		
		if( addNewMemberResultString == "NO") {
			assertTrue(member.getUniversityName() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCaseMajorName() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);

		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		if( addNewMemberResultString == "NO") {
			assertTrue(member.getMajorName() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCaseGraduationType() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);

		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		if( addNewMemberResultString == "NO") {
			assertTrue(member.getGraduationType() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCaseBirthDate() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);

		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		if( addNewMemberResultString == "NO") {
			assertTrue(errors.getErrorCount() == 1);
			assertTrue(member.getBirthDate() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCasePhoneNumber() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);

		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		if( addNewMemberResultString == "NO") {
			assertTrue(errors.getErrorCount() == 1);
			assertTrue(member.getPhoneNumber() == null);
		}
		else {
			fail("fail....");
		}
	}

	@Test
	public void addNewMemberTestErrorCaseMemberType() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpServletRequest requset = new MockHttpServletRequest();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
	}

	@Test
	public void addNewMemberTestErrorCaseNotEqualRepeatPasswrod() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("Junit");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpServletRequest requset = new MockHttpServletRequest();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		
//		
//		ModelAndView view = memberService.addNewMember(member, repeatPasswrod, errors, session, response, requset);
//		assertNotNull(view);
//
//		if (view != null) {
//			String viewName = view.getViewName();
//			assertNotNull(viewName);
//			assertEquals(viewName, "member/registerStudent");
//
//			List<HighestEducationLevelVO> highestEducationLevelList = (List<HighestEducationLevelVO>) view.getModel().get("highestEducationLevelList");
//			assertNotNull(highestEducationLevelList);
//
//			List<GraduationTypeVO> graduationTypeList = (List<GraduationTypeVO>) view.getModel().get("graduationTypeList");
//			assertNotNull(graduationTypeList);
//
//			String isEqualsPassword = (String) view.getModelMap().get("isEqualsPassword");
//			assertNotNull(isEqualsPassword);
//
//			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
//			assertNotNull(viewMember);
//
//			assertTrue(memberDAO.isExistId(member.getId()) == null);
//		} else {
//			fail("Fail...");
//		}
	}

	@Test
	public void addNewMemberTestErrorCaseEmptyRepeatPasswrod() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		member.setName("으악");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("휴학");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		String repeatPasswrod = ("JunitPassword1@");

		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpServletRequest requset = new MockHttpServletRequest();
		
		String addNewMemberResultString = memberService.addNewMember(member, repeatPasswrod, errors, response);
		
//		ModelAndView view = memberService.addNewMember(member, errors, null);
//		assertNotNull(view);
//
//		if (view != null) {
//			String viewName = view.getViewName();
//			assertNotNull(viewName);
//			assertEquals(viewName, "member/registerStudent");
//
//			List<HighestEducationLevelVO> highestEducationLevelList = (List<HighestEducationLevelVO>) view.getModel().get("highestEducationLevelList");
//			assertNotNull(highestEducationLevelList);
//
//			List<GraduationTypeVO> graduationTypeList = (List<GraduationTypeVO>) view.getModel().get("graduationTypeList");
//			assertNotNull(graduationTypeList);
//
//			String isEmptyRepeatPassword = (String) view.getModelMap().get("isEmptyRepeatPassword");
//			assertNotNull(isEmptyRepeatPassword);
//
//			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
//			assertNotNull(viewMember);
//
//			assertTrue(memberDAO.isExistId(member.getId()) == null);
//		} else {
//			fail("Fail...");
//		}
	}

	@Test
	public void registerPolicy() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();

		String viewName = memberService.registerPolicy(session);
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

		String viewName = memberService.registerPolicy(session);
		assertNotNull(viewName);

		if (viewName != null) {
			assertEquals(viewName, "member/registErrorPage");
		} else {
			fail("Fail...");
		}
	}

	@Test
	public void registerStudentTest() {
		ModelAndView view = new ModelAndView();
		view = memberService.registerStudent();
				
		if(view != null) {
			String viewName = view.getViewName(); 
			assertNotNull(viewName);
			assertEquals(viewName, "member/registerStudent");
			List<HighestEducationLevelVO> highestEducationLevelList = (List<HighestEducationLevelVO>) view.getModel().get("highestEducationLevelList");
			List<GraduationTypeVO> graduationTypeList = (List<GraduationTypeVO>) view.getModel().get("graduationTypeList");

			if(graduationTypeList != null) {
				for (GraduationTypeVO graduationTypeVO : graduationTypeList) {
					assertNotNull(graduationTypeVO);
				}
			}
			else if (highestEducationLevelList != null) {
				for (HighestEducationLevelVO highestEducationLevelVO : highestEducationLevelList) {
					assertNotNull(highestEducationLevelVO);
				}
			}
			else {
				fail("fail...");
			}
		}
		
	}

	@Test
	public void registerTeacherTest() {
		String viewName = memberService.registerTeacher();

		if (viewName != null) {
			assertEquals(viewName, "member/registerTeacher");
		} else {
			fail("Fail...");
		}
	}

	/**
	 * 나의 교육 이력 보기
	 */
	@Test
	public void getAllEducationHistoryListByIdWithPagingTest() {

		MemberVO memberVO = new MemberVO();
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		memberVO.setId("test01");

		int pageNo = 0;
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, memberVO);

		ModelAndView view = memberService.getAllEducationHistoryListByIdWithPaging(educationHistorySearchVO,pageNo, session);

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
			
			List<EducationCostVO> educationCostList = educationHistoryListVO.getCostList();
			assertNotNull(educationCostList);
			assertTrue(educationCostList.size() > 0);
			
			List<EducationStateVO> educationStateList = educationHistoryListVO.getStatList();
			assertNotNull(educationStateList);
			assertTrue(educationStateList.size() > 0);
			
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
		ModelAndView view = memberService.saveLoginHistoryAsExcel(session);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/member/loginHistory");
		} else {
			fail("[Service Part]saveLoginHistoryAsExcelTest Fail.");
		}

	}

	@Test
	public void viewLoginHistoryPageTest() {
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setId("test04");
		session.setAttribute(Session.MEMBER, loginHistorySearchVO);

		ModelAndView view = memberService.viewLoginHistoryPage(loginHistorySearchVO, 0, session, request);

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
			assertNotNull(loginHistoryList);
			assertTrue(loginHistoryList.size() > 0);
		} else {
			fail("[Service Part]viewLoginHistoryPageTest Fail.");
		}
	}

	@Test
	public void doRequestIpHistoryTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		MockHttpSession session = new MockHttpSession();
		if( loginHistoryVO.getId() == null || loginHistoryVO.getLgiHtrId() <= 0 ) {
			loginHistoryVO.setId("test04");
			loginHistoryVO.setLgiHtrId(1048);
			session.setAttribute(Session.MEMBER, loginHistoryVO);
			ModelAndView view = memberService.doRequestIpHistory(loginHistoryVO.getLgiHtrId(), session);
			if (view != null) {
				String viewName = view.getViewName();
				assertNotNull(viewName);
				assertEquals(viewName, "redirect:/member/loginHistory");
			} else {
				fail("[Service Part]doRequestIpHistoryTest Fail.");
			}
		}else {
			ModelAndView view = memberService.doRequestIpHistory(loginHistoryVO.getLgiHtrId(), session);
			if (view != null) {
				String viewName = view.getViewName();
				assertNotNull(viewName);
				assertEquals(viewName, "redirect:/member/loginHistory");
			} else {
				fail("[Service Part]doRequestIpHistoryTest Fail.");
			}
		}
	}

	@Test
	public void doCheckIpTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiHtrId(1048);
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, loginHistoryVO);

		ModelAndView view = memberService.doCheckIp(loginHistoryVO.getLgiHtrId(), session);
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/checkIP");
			LoginHistoryVO loginHistory = (LoginHistoryVO) view.getModel().get("loginHistory");
			assertNotNull(loginHistory);
		} else {
			fail("[Service Part]doCheckIpTest Fail.");
		}
	}
	
	@Test
	public void dropCourseApply(){
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("JUnit");
		session.setAttribute("_MEMBER_", sessionMember);
		String educationId = "JUnit";
		String courseDropReason = "JUnit";
		
		String dropCourseApply = memberService.dropCourseApply(educationId, session, courseDropReason);
		
		assertEquals(dropCourseApply, "OK");
	}
	
	@Test
	public void writeResignCourse(){
		
		ModelAndView view = new ModelAndView();
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("JunitMbrId");
		session.setAttribute("_MEMBER_", sessionMember);
		String educationId = "JunitEduId";
		view.setViewName("myPage/resignCourseWrite");
		
		assertEquals(view.getViewName(),memberService.writeResignCourse(educationId, session).getViewName());
	}
	
	@Test
	public void doResign(){
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		memberVO.setPassword("123qwe!@#qwe");
		String resignCode = "3226f65a-5aec-4fe0-a56a-26f4b5d546b2";
		BindingResult errors = new BeanPropertyBindingResult(memberVO, "loginForm");
		
		assertNotNull(memberService.doResign(memberVO, errors, resignCode));
	}
	
	@Test
	public void loginForResign(){
		ModelAndView view = new ModelAndView();
		
		String resignCode = "3226f65a-5aec-4fe0-a56a-26f4b5d546b2";
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		
		view.setViewName("member/loginForResign");
		
		assertEquals(view.getViewName(), memberService.loginForResign(resignCode, memberVO.getId()).getViewName());
	}
	
	@Test
	public void insertUuidForResign(){
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		session.setAttribute("_MEMBER_", memberVO);
		
		String uuid = UUID.randomUUID().toString();
		memberVO.setUuid(uuid);
		
		assertNotNull(memberService.insertUuidForResign(session));
		
	}
	
	/**
	 * 나의 교육 이력 엑셀 다운로드
	 */
	@Test
	public void eduationHistoryExportExcelTest() {

		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		
		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute(Session.MEMBER, memberVO);

		String returnString = memberService.eduationHistoryExportExcel(mockSession);
		assertNotNull(returnString);
	}
	@Test
	public void modifyMemberInfoTest() {
		MemberVO memberVO = new MemberVO();
		Errors errors = null;
		String graduationType = "";
		String helCodeName = "";

		if (memberVO.getId() == null) {
			String salt = SHA256Util.generateSalt();
			memberVO.setId("test04");
			memberVO.setName("cain");
			memberVO.setEmail("cain@naver.com");
			memberVO.setHighestEducationLevel("UNIV");
			memberVO.setGraduationType("GRAD");
			memberVO.setBirthDate("1988-02-20");
			memberVO.setPhoneNumber("010-7336-6004");
			memberVO.setPassword("123qwe!@#qwe");
			String newPassword = SHA256Util.getEncrypt(memberVO.getPassword(), salt);
			memberVO.setPassword(newPassword);
			memberVO.setSalt(salt);
			
			ModelAndView view = memberService.modifyMemberInfo(memberVO, errors, graduationType, helCodeName);
			if( view != null) {
				String viewName = view.getViewName();
				assertNotNull(viewName);
				assertEquals(viewName, "redirect:/member/myPage");
			} else {
				fail("[Service Part]modifyMemberInfoTest Fail.");
			}
		} else {
			ModelAndView view = memberService.modifyMemberInfo(memberVO, errors, graduationType, helCodeName);
			if( view != null) {
				String viewName = view.getViewName();
				assertNotNull(viewName);
				assertEquals(viewName, "redirect:/member/myPage");
			} else {
				fail("[Service Part]modifyMemberInfoTest Fail.");
			}
		}
	}
	
	@Test
	public void getCourseListTest() {
		ModelAndView view = new ModelAndView();
		int pageNo = 0;
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("oph312");
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("_MEMBER_", memberVO);
		
		EducationSearchVO educationSearchVO = new EducationSearchVO();
		educationSearchVO.setSearchKeyword("ll");
		view = memberService.getCourseList(session, pageNo, educationSearchVO);
		
		if ( view != null ) {
			List<EducationVO> myEducationList = (List<EducationVO>)view.getModel().get("educationListVO");
			EducationSearchVO educationSearchVO2 = (EducationSearchVO)view.getModel().get("educationSearchVO");
			EducationListVO preEducationListVO = (EducationListVO)view.getModel().get("preEducationListVO");

			assertNotNull(view);
			assertEquals(view.getViewName(), "myPage/myEduCourseInfo");
			assertNotNull(myEducationList);
			assertTrue(myEducationList.size() >= 0);
			assertNotNull(educationSearchVO2);
			assertNotNull(preEducationListVO);
			
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void getAllAttendClassListByIdTest(){
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("_MEMBER_", memberVO);
		
		ModelAndView view = memberService.getAllAttendClassListById(session);
		
		if( view != null ) { 
			List<EducationVO> attendClassList = (List<EducationVO>) view.getModelMap().get("attendClassList");
			assertNotNull(view);
			assertEquals(view.getViewName(), "member/attendHistory");
			assertNotNull(attendClassList);
			assertTrue(attendClassList.size() >= 0);
		}
		else { 
			fail("Fail...");
		}
	}
		
	@Test
	public void getAllAttendHistoryTest(){
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("_MEMBER_", memberVO);	
		
		String educationId = "ED-20160513-000166";
		ModelAndView view = memberService.getAllAttendHistory(session, educationId);
		
		if( view != null ) { 
			EducationVO eduInfo = (EducationVO) view.getModelMap().get("eduInfo");
			Map<String, List<String>> attendList = (Map<String, List<String>>) view.getModelMap().get("attendList");
			assertNotNull(view);
			assertEquals(view.getViewName(), "member/attendHistoryDetail");
			assertNotNull(eduInfo);
			assertNotNull(attendList);
			assertTrue(attendList.size() >= 0 );
		
		}
		else{
			fail("Fail...");
		}
		
	}
	
}
