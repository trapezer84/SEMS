package com.ktds.sems.member.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.validator.PersonalInfoValidator;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberListVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class MemberServiceTest extends SemsTestCase {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDAO memberDAO;
	
	@Test	
	public void modifyMemberTypeTest(){
		
		String memberType = "강사";
		List<String> memberIds = new ArrayList<String>();
		memberIds.add("sosdig1");
		
		ModelAndView view = memberService.modifyMemberType(memberType, memberIds);
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/memberManage/memberList");
		}
		else{
			fail("Fail...");
		}
	}

	@Test	
	public void modifyMemberTypeTestErrorCaseMemberType(){
		
		List<String> deleteMemberId = new ArrayList<String>();
		deleteMemberId.add("sosdig1");
		ModelAndView view = memberService.modifyMemberType( null , deleteMemberId);
		
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/memberManage/memberList");
		}
		else{
			fail("Fail....");
		}		
		
	}

	@Test	
	public void modifyMemberTypeTestErrorCaseMemberIds(){
		
		String memberType = "강사";
		ModelAndView view = memberService.modifyMemberType( memberType , null);
		
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/memberManage/memberList");
		}
		else{
			fail("Fail....");
		}		
		
	}
	
	//@Test
	public void addNewMemberTest() {
		
		MemberVO member = new MemberVO();
		MemberVO sessionMember = new MemberVO();
		BindingResult errors = new BeanPropertyBindingResult(member, "member");
		MockHttpSession session = new MockHttpSession();
		
		sessionMember.setId("cocomo12");
		session.setAttribute("_MEMBER_", sessionMember);
		
		member.setId("jewel1324");
		member.setPassword("asdfasdf123!");
		member.setName("JUNIT홍길동");
		member.setEmail("JUNIT@naver.com");
		member.setPhoneNumber("010-1234-1234");
		member.setBirthDate("1999-12-01");
		member.setMemberType("강사");
		member.setUniversityName("A대학");
		member.setMajorName("B학과");
		member.setHighestEducationLevel("고졸");
		
		ModelAndView view = memberService.addNewMember(member, errors, session);
		
		if ( view != null ) {
			assertNotNull(view);
			memberService.memberDeleteById("jewel1324");
		}
		else {
			fail("view is null");
		}
	}
	
	@Test
	public void viewMemberListPageTest(){
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberService.getAllMemberList(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}
	}
	
	@Test
	public void viewMemberListPageTestSearchTypeId() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("test");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberService.getAllMemberList(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getId().contains("test"));
			}

			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}
	
	
	@Test
	public void viewMemberListPageTestSearchTypeMemberType() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("강사");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberService.getAllMemberList(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getMemberType().equals("강사"));
			}
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}
	
	@Test
	public void viewMemberListPageTestSearchTypeIsRgsn() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("Y");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberService.getAllMemberList(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getIsResign().equals("Y"));
			}
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}	
	
	@Test
	public void viewMemberListPageTestSearchTypeConnLock() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("Y");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberService.getAllMemberList(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getIsAccountLock().equals("Y"));
			}
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}	
	@Test
	public void viewMemberListPageTestSearchTypeModLock() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("Y");
		
		ModelAndView view = memberService.getAllMemberList(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getIsModifyLock().equals("Y"));
			}
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}
	
	@Test
	public void massiveDeleteMemberTest () {
		MemberVO member = new MemberVO();
		member.setPassword("4c5b7ab6a121aae1acda84fc71ed4b135e9f8eb7f1a25013515845e9c7ddc9f8");
		member.setSalt("9ca0645b12e961ac");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("UNIV");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("ABST");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		for ( int i = 1; i < 6; i++) {
			member.setId("Junitaa" + i);
			memberDAO.addNewMember(member);
		}
		String[] deleteMemberIds = {"Junitaa1", "Junitaa2", "Junitaa3", "Junitaa4", "Junitaa5"};
		
		String viewName = memberService.massiveDeleteMember(deleteMemberIds);
		assertNotNull(viewName);
		if ( viewName != null ) {
			assertEquals(viewName, "redirect:/memberManage/memberList");
		}
		else { 
			fail("Fail...");
		}
	}
	
	/**
	 * getMemberDetailById
	 * memberDeleteById
	 * requestMemberDetail
	 * doWriteMemberDetailInfo
	 * personalInfoReadVO
	 * lpad
	 * 
	 */
	@Test
	public void memberDeleteByIdTestError1() {
		MemberVO member = new MemberVO();
		member.setPassword("4c5b7ab6a121aae1acda84fc71ed4b135e9f8eb7f1a25013515845e9c7ddc9f8");
		member.setSalt("9ca0645b12e961ac");
		member.setName("Junitaa");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("UNIV");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("ABST");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		member.setId("JunitPE");

		memberDAO.addNewMember(member);
		
		ModelAndView view = memberService.memberDeleteById(member.getId());
		
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName , "redirect:/memberManage/memberList");
		}
		else {
			fail("Fail....");
		}
		
	}
	
	@Test
	public void memberDeleteByIdTestError2() {
		String id = "";
		
		ModelAndView view = memberService.memberDeleteById(id);
		
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName , "redirect:/memberManage/memberList");
		}
		else {
			fail("Fail....");
		}
		
	}
	
	@Test
	public void doWriteMemberDetailInfo() {
		PersonalInfoReadVO personalInfoReadVO = new PersonalInfoReadVO();
		personalInfoReadVO.setMemberId("cocomo12");
		personalInfoReadVO.setTargetMemberId("oph312");
		personalInfoReadVO.setMemberType("MBR");
		personalInfoReadVO.setDescription("desc");
		personalInfoReadVO.setReadDate("Junitdate");
		
		BindingResult errors = new BeanPropertyBindingResult(personalInfoReadVO, "requestMemberDetailInfoForm");
		PersonalInfoValidator userValidator = new PersonalInfoValidator();
		userValidator.validate(personalInfoReadVO, errors);
		
		ModelAndView view = memberService.doWriteMemberDetailInfo(personalInfoReadVO, errors);
		assertNotNull(view);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberDetailPage");

		}
		else {
			fail("Fail.....");
		}
		memberDAO.deleteMemberDetailInfo(personalInfoReadVO.getMemberId());
	}
	
	@Test
	public void getAllAdminHistoryTest() {
		
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setId("test04");
		ModelAndView view = memberService.getAllAdminHistory(loginHistorySearchVO, 0);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/adminHistory");
		
		LoginHistoryListVO loginHistoryListVO = (LoginHistoryListVO) view.getModel().get("loginHistoryListVO");
		LoginHistorySearchVO loginSearchVO = (LoginHistorySearchVO) view.getModel().get("loginHistorySearchVO");
		assertNotNull(loginHistoryListVO);
		assertNotNull(loginSearchVO);
		
		Paging paging = loginHistoryListVO.getPaging();
		assertNotNull(paging);
		assertTrue(paging.getTotalArticleCount() > 0);
		
		List<LoginHistoryVO> loginHistory = loginHistoryListVO.getLoginHistoryList();
		assertNotNull(loginHistory);
		assertTrue(loginHistory.size() > 0);
		}
		else {
			fail("getAllAdminHistoryTest Fail");
		}
	}
	
	@Test
	public void getAllMemberHistoryTest() {
		
		MockHttpSession session = new MockHttpSession();
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setId("admin01");
		loginHistorySearchVO.setMemberType("ADM");
		session.setAttribute(Session.MEMBER_TYPE, "ADM");
		
		ModelAndView view = memberService.getAllMemberHistory(loginHistorySearchVO, 0, session);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberHistory");
		
		List<String> typeList = (List<String>) view.getModel().get("typeList");
		LoginHistoryListVO loginHistoryListVO = (LoginHistoryListVO) view.getModel().get("loginHistoryListVO");
		LoginHistorySearchVO loginSearchVO = (LoginHistorySearchVO) view.getModel().get("loginHistorySearchVO");
		assertNotNull(typeList);
		assertNotNull(loginHistoryListVO);
		assertNotNull(loginSearchVO);
		
		Paging paging = loginHistoryListVO.getPaging();
		assertNotNull(paging);
		assertTrue(paging.getTotalArticleCount() > 0);
		
		List<LoginHistoryVO> loginHistory = loginHistoryListVO.getLoginHistoryList();
		assertNotNull(loginHistory);
		assertTrue(loginHistory.size() > 0);
		
		}
		else {
			fail("getAllMemberHistoryTest Fail");
		}
	}
	
	@Test
	public void loginHistoryInitTest() {
		ModelAndView view = memberService.loginHistoryInit();
		assertNotNull(view);
	}
	
	@Test
	public void adminHistoryInitTest() {
		ModelAndView view = memberService.adminHistoryInit();
		assertNotNull(view);
	}
	
	@Test
	public void getMemberTypesTest() {
		List<MemberTypeVO> memberTypes = memberService.getMemberTypes();
		
		assertNotNull(memberTypes);
		assertTrue(memberTypes.size() > 0);
	}
	
	@Test
	public void getHighestEducationLevelsTest() {
		List<HighestEducationLevelVO> highestEducationLevels = memberService.getHighestEducationLevels();
		
		assertNotNull(highestEducationLevels);
		assertTrue(highestEducationLevels.size() > 0);
	}
	
	@Test
	public void getGraduationTypesTest() {
		List<GraduationTypeVO> graduationTypes = memberService.getGraduationTypes();
		
		assertNotNull(graduationTypes);
		assertTrue(graduationTypes.size() > 0);
	}
	
}
