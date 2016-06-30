package com.ktds.sems.member.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberBizTest extends SemsTestCase {

	@Autowired
	private MemberBiz memberBiz;

	/**
	 * @author 김동규 
	 * Action - insert
	 */
	@Before
	public void setUp() {
		testHelper(new Testable() {
			// stampLoginTimeTest Setting - insert loginHistoryVO
			@Override
			public void preparedTest() {

			}
		});
	}
	
	/**
	 * @author 김동규 
	 * Action - delete
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
	 * 계정 잠겨있는지 확인
	 * 
	 */
	@Test
	public void isAccountLockTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.isAccountLock(id);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인
	 * 
	 */
	@Test
	public void loginTest() {

		MockHttpSession session = new MockHttpSession();
		LoginHistoryVO historyVO = new LoginHistoryVO();
		historyVO.setId("213231213");
		session.setAttribute("_LOGIN_HISTORY_", historyVO);
		
		MockHttpServletRequest reqeust = new MockHttpServletRequest();

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setPassword("123qwe!@#qwe");

		boolean isSuccess = memberBiz.login(session, memberVO, reqeust);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인 성공 확인
	 * 
	 */
	@Test
	public void loginSuccessTest() {

		String id = "cocomo12";
		boolean isSuccess = memberBiz.loginSuccess(id);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인 실패 횟수 증가
	 * 
	 */
	@Test
	public void plusLoginFailCountTest() {

		String id = "cocomo12";
		boolean isSuccess = memberBiz.plusLoginFailCount(id);
		assertTrue(isSuccess);
	}

	/**
	 * 계정 잠금상태 최신화
	 * 
	 */
	@Test
	public void updateAccountLockTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.updateAccountLock(id);
		assertTrue(isSuccess);
	}

	/**
	 * 30일이 지나서 비밀번호 바꿔야 하는지 확인
	 * 
	 */
	@Test
	public void needToChangPasswordTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.needToChangPassword(id);
		assertTrue(isSuccess);
	}

	/**
	 * 존재하는 아이디 인지 확인
	 * 
	 */
	@Test
	public void isExistIdTest() {
		String id = "cocomo12";
		boolean isSuccess = memberBiz.isExistId(id);
		assertTrue(isSuccess);
	}

	/**
	 * 탈퇴한 회원인지 확인
	 * 
	 */
	@Test
	public void isResignTest() {
		String id = "cocomo12";
		boolean isSuccess = !memberBiz.isResign(id);
		assertTrue(isSuccess);
	}
	
	@Test
	public void getSaltByIdTest(){
		String id = "aaa12";
		String result = memberBiz.getSaltById(id);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void getPasswordByIdTest(){
		String id = "aaa12";
		String result = memberBiz.getPasswordById(id);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void isModifyAccountLockTest(){
		String id = "aaa";
		boolean isSuccess = !memberBiz.isModifyAccountLock(id);
		assertTrue(isSuccess);
 	}
	
	@Test
	public void getMenuCategoryListTest() {
		List<MenuManageVO> menu = memberBiz.getMenuCategoryList();
	    assertNotNull(menu);
	    assertTrue(menu.size() > 0);
	}
	
	@Test
	public void getLoginHistoryListByMemberIdTest() {
		MemberVO member = new MemberVO();
		member.setId("test02");
		
		List<LoginHistoryVO> loginHistoryList = memberBiz.getLoginHistoryListByMemberId(member.getId());
		assertNotNull(loginHistoryList);
	    assertTrue(loginHistoryList.size() > 0);
	}
	
	@Test
	public void getEducationHistoryListByMemberIdTest() {
		MemberVO member = new MemberVO();
		member.setId("test02");
		
		List<EducationHistoryVO> educationHistoryList = memberBiz.getEducationHistoryListByMemberId(member.getId());
		assertNotNull(educationHistoryList);
		assertTrue(educationHistoryList.size() > 0);
	}
	
	@Test
	public void getQnaListByMemberIdTest() {
		MemberVO member = new MemberVO();
		member.setId("test02");
		
		List<QNAVO> qnaList = memberBiz.getQnaListByMemberId(member.getId());
		assertNotNull(qnaList);
		assertTrue(qnaList.size() > 0);
	}

	@Test
	public void getReportReplyListByMemberIdTest() {
		MemberVO member = new MemberVO();
		member.setId("test02");
		
		List<ReportReplyVO> reportReplyList = memberBiz.getReportReplyListByMemberId(member.getId());
		assertNotNull(reportReplyList);
		assertTrue(reportReplyList.size() >= 0);
	}
	
	@Test
	public void addNewMemberTest(){
		MemberVO member = new MemberVO();
		member.setId("JunitIdTest1");
		member.setPassword("44cc5a083ad03370997e88834c4de95460fc54dc0562c401b71b9d504fe9d9b3");
		member.setHighestEducationLevel("HIGH");
		member.setName("으악");
		member.setEmail("junit1@naver.com");
		member.setUniversityName("JUNIT대학교");
		member.setMajorName("JUNIT과");
		member.setGraduationType("GRAD");
		member.setBirthDate("1991-06-03");
		member.setPhoneNumber("010-0000-1154");
		member.setMemberType("MBR");
		member.setSalt("c21586d7786ea63b");
		
		boolean checkAddNewMember = memberBiz.addNewMember(member);
		assertTrue(checkAddNewMember);
	}
	
	@Test
	public void isVerifyIdTest(){
		String id = "JunitIdTest1";
		boolean checkVerifyId = memberBiz.isVerifyId(id);
		assertTrue(checkVerifyId);
	}
	@Test
	public void isVerifyPasswordTest(){
		String pw = "123qwe!@#qwe";
		boolean checkVerifyPw = memberBiz.isVerifyPassword(pw);
		assertTrue(checkVerifyPw);
	}
	@Test
	public void isVerifyPhoneNumberTest(){
		String phoneNumber = "010-0000-1154";
		boolean checkVerifyPn = memberBiz.isVerifyPhoneNumber(phoneNumber);
		assertTrue(checkVerifyPn);
	}
	@Test
	public void isVerifyPhoneNumberTestError(){
		String phoneNumber = "01000-0000-1154";
		boolean checkVerifyPn = memberBiz.isVerifyPhoneNumber(phoneNumber);
		assertTrue(!checkVerifyPn);
	}
	@Test
	public void isVerifyEmailTest(){
		String email = "junit1@naver.com";
		boolean checkVerifyEmail = memberBiz.isVerifyEmail(email);
		assertTrue(checkVerifyEmail);
	}
	@Test
	public void isVerifyEmailTestError(){
		String email = "junit1!naver.comcom";
		boolean checkVerifyEmail = memberBiz.isVerifyEmail(email);
		assertTrue(!checkVerifyEmail);
	}
	@Test
	public void isExistEmailTest(){
		String email = "sosdig@naver.com";
		boolean isExistEmail = memberBiz.isExistEmail(email);
		assertTrue(isExistEmail);
	}
	
	@Test
	public void checkValidationByNameTest() {
		String name = "테스트";
		boolean check = memberBiz.checkValidationByName(name);
		assertTrue(check);
	}
	
	@Test
	public void checkValidationByNameTestError() {
		String name = "테스트!!!!";
		boolean check = memberBiz.checkValidationByName(name);
		assertTrue(!check);
	}
	
	@Test
	public void checkValidationByUniversityNameTest() {
		String universityName = "가나다대학교";
		boolean check = memberBiz.checkValidationByUniversityName(universityName);
		assertTrue(check);
	}
	
	@Test
	public void checkValidationByUniversityNameTestError() {
		String universityName = "가나다대학교★";
		boolean check = memberBiz.checkValidationByUniversityName(universityName);
		assertTrue(!check);
	}
		
	@Test
	public void checkValidationByMajorNameTest() {
		String majorName = "컴퓨터공학과";
		boolean check = memberBiz.checkValidationByMajorName(majorName);
		assertTrue(check);
	}
	
	@Test
	public void checkValidationByMajorNameTestError() {
		String majorName = "컴퓨터공학과★";
		boolean check = memberBiz.checkValidationByMajorName(majorName);
		assertTrue(!check);
	}
		
	//오름차순에서 맨마지막에 실행하기위해
	@Test
	public void doDeleteTest(){
		String id = "sosdig1";
		boolean doDeleteTest = memberBiz.doDeleteMember(id);
		assertTrue(doDeleteTest);
	}
	
	/**
	 *	나의 교육 이력 보기 
	 */
	@Test
	public void getAllEducationHistoryListByIdWithPagingTest() {
		
		Paging paging = new Paging();
		paging.setPageNumber(0 + "");
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setMemberId("test04");
		
		int totalArticleNumber = memberBiz.getTotalEducationHistoryCountById(educationHistorySearchVO);
		
		if(totalArticleNumber != 0) {
			
			paging.setTotalArticleCount(totalArticleNumber);
			educationHistorySearchVO = new EducationHistorySearchVO();
			educationHistorySearchVO.setPageNo(0);
			educationHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
			educationHistorySearchVO.setEndIndex(paging.getEndArticleNumber());
			
			educationHistorySearchVO.setMemberId("test01");
			List<EducationHistoryVO> educationHistoryList = memberBiz.getAllEducationHistoryListByIdWithPaging(educationHistorySearchVO);
			
			if(educationHistoryList != null) {
				
				for (EducationHistoryVO educationHistoryVO : educationHistoryList) {
					assertNotNull(educationHistoryVO.getEducationHistoryId());
					assertNotNull(educationHistoryVO.getEducationId());
					assertNotNull(educationHistoryVO.getMemberId());
					assertNotNull(educationHistoryVO.getEducationHistoryDate());
					assertNotNull(educationHistoryVO.getState());
					assertNotNull(educationHistoryVO.getIp());
					assertNotNull(educationHistoryVO.getStartDate());
					assertNotNull(educationHistoryVO.getEndDate());
				}
				
			} else {
				fail("fail");
			}
			
		} else {
			fail("fail");
		}

	}

	@Test
	public void viewLoginHistoryPageTest() {
		
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setId("test04");

		int totalLoginHistoryCount = memberBiz.getTotalLoginHistoryCount(loginHistorySearchVO);
		
		if(totalLoginHistoryCount != 0) {
			Paging paging = new Paging();
			paging.setTotalArticleCount(totalLoginHistoryCount);
			paging.setPageNumber(0 + "");
			loginHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
			loginHistorySearchVO.setEndIndex(paging.getEndArticleNumber());
			loginHistorySearchVO.setLgiHtrId(1048);
			loginHistorySearchVO.setBeginDate("2016/05/17");
			loginHistorySearchVO.setCloseDate("2016/05/17");
			
			List<LoginHistoryVO> loginHistoryList = memberBiz.getAllLoginHistory(loginHistorySearchVO);
			if(loginHistoryList != null) {
				for (LoginHistoryVO loginHistoryVO : loginHistoryList) {
					assertNotNull(loginHistoryVO.getLgiHtrId());
					assertNotNull(loginHistoryVO.getId());
					assertNotNull(loginHistoryVO.getLgiIp());
					assertNotNull(loginHistoryVO.getLgiDt());
//					assertNotNull(loginHistoryVO.getLgoDt());
//					assertNotNull(loginHistoryVO.getIsReq());
					assertNotNull(loginHistoryVO.getChkCnt());
				}
			}
			else {
				fail("[Biz Part] viewLoginHistoryPageTest < loginHistoryList Fail.");
			}
			
			LoginHistoryListVO loginHistoryListVO = new LoginHistoryListVO();
			loginHistoryListVO.setLoginHistoryList(loginHistoryList);
			loginHistoryListVO.setPaging(paging);
			assertNotNull(paging);
		}else {
			fail("[Biz Part] viewLoginHistoryPageTest < totalLoginHistoryCount Fail.");
		}
	}
	
	@Test
	public void doRequestIpHistoryTest(){
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiHtrId(1048);
		
		boolean memberCheck = memberBiz.doMatchHistoryWithMember(loginHistoryVO);
		assertTrue(memberCheck);
	}
	
	@Test
	public void doCheckIpTest(){
		
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiHtrId(1048);
		
		boolean checkIp = memberBiz.doCheckIp(loginHistoryVO);
		boolean memberCheck = memberBiz.doMatchHistoryWithMember(loginHistoryVO);
		
		assertTrue(checkIp);
		assertTrue(memberCheck);
	}
	
	/**
	 * 나의 교육 이력 엑셀 다운로드
	 */
	@Test
	public void eduationHistoryExportExcelTest() {
		
		String id = "test02";
		boolean isSuccess = memberBiz.eduationHistoryExportExcel(id);
		
		assertTrue(isSuccess);
	}
	
	/**
	 * 진행중인 교육 이력만 보기
	 */
	@Test
	public void getJoinEducationListTest() {
		String memberId = "test02";
		List<EducationHistoryVO> educationHistoryList = memberBiz.getJoinEducationList(memberId);
		
		if( educationHistoryList != null ) {
			
			for (EducationHistoryVO educationHistoryVO : educationHistoryList) {
				assertNotNull(educationHistoryVO.getEducationHistoryId());
				assertNotNull(educationHistoryVO.getEducationId());
				assertNotNull(educationHistoryVO.getMemberId());
				assertNotNull(educationHistoryVO.getEducationHistoryDate());
				assertNotNull(educationHistoryVO.getState());
				assertNotNull(educationHistoryVO.getIp());
				assertNotNull(educationHistoryVO.getStartDate());
				assertNotNull(educationHistoryVO.getEndDate());
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void stampLoginTimeTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		if (loginHistoryVO.getId() == null || loginHistoryVO.getLgiHtrId() <= 0 || loginHistoryVO.getLgiIp() == null ) {
			loginHistoryVO.setLgiHtrId(1048);
			loginHistoryVO.setId("test04");
			loginHistoryVO.setLgiIp(request.getRemoteHost());
			session.setAttribute(Session.LOGIN_HISTORY, loginHistoryVO);
			
			boolean check = memberBiz.stampLoginTime(session, request, loginHistoryVO);
			assertTrue(check);
			if( check ) {
				assertNotNull(check);
				
			} else {
				fail("[Biz Part] stampLoginTimeTest Fail.");
			}
		} else {
			boolean check = memberBiz.stampLoginTime(session, request, loginHistoryVO);
			assertTrue(check);
			if( check ) {
				assertNotNull(check);
				
			} else {
				fail("[Biz Part] stampLoginTimeTest Fail.");
			}
		}
	}
	
	@Test
	public void stampLogoutTimeTest() {
		MockHttpSession session = new MockHttpSession();
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setLgiHtrId(1048);
		session.setAttribute(Session.LOGIN_HISTORY, loginHistoryVO);
		
		boolean check = memberBiz.stampLogoutTime(session);
		assertTrue(check);
		if( check ) {
			assertNotNull(check);
		} else {
			fail("[Biz Part] stampLogoutTimeTest Fail.");
		}
	}
	
	@Test
	public void dropCourseApply(){
		EducationHistoryVO educationHistory = new EducationHistoryVO();
		educationHistory.setCmnt("JUnit!!!!!!!!test!!");
		educationHistory.setEducationId("ED-20160516-000185");
		educationHistory.setMemberId("test02");
		
		boolean dropCourseApply = memberBiz.dropCourseApply(educationHistory);
		assertTrue(dropCourseApply);
	}
	
	@Test
	public void getOneEducationByIdAndEducationId(){
		EducationHistoryVO educationHistory = new EducationHistoryVO();
		String setEducationId = "ED-20160516-000185";
		String setMemberId = "test02";

		educationHistory = memberBiz.getOneEducationByIdAndEducationId(setEducationId, setMemberId);
		
		assertNotNull(educationHistory);
	}
	
	/*
	@Test
	public void getCourseList() {
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		
		educationHistorySearchVO.setMemberId("test02");
		educationHistorySearchVO.setEndIndex(5);
		educationHistorySearchVO.setStartIndex(0);
		
//		assertNotNull(memberBiz.getCourseList(educationHistorySearchVO));
	}
	*/
	@Test
	public void doResign(){
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		memberVO.setPassword("123qwe!@#qwe");
		
		assertTrue(memberBiz.doResign(memberVO));
	}
	
	@Test
	public void checkRegistStateFalseTest() {
		String id = "registerFailed";
		assertTrue(!memberBiz.checkRegistState(id));
	}
	
	@Test
	public void checkRegistStateTrueTest() {
		String id = "test02";
		assertTrue(memberBiz.checkRegistState(id));
	}
	
	@Test
	public void checkValidationCourseAccesTrueTest() {
		//boolean
		String memberId = "cocomo12";
		assertTrue(!memberBiz.checkValidationCourseAccess(memberId));
	}
	
	@Test
	public void checkValidationCourseAccesFailTest() {
		//boolean
		String memberId = "newId";
		assertTrue(memberBiz.checkValidationCourseAccess(memberId));
	}
	
	@Test
	public void getCourseCountByIdTest() {
		String id = "oph312";
		assertTrue(memberBiz.getCourseCountById(id) >= 0);
	}
	
	@Test
	public void getCourseListTest() {
		String id = "oph312";
		assertTrue(memberBiz.getCourseList(id).size() >= 0);
	}
	
	@Test
	public void getPreCourseListTest() {
		EducationSearchVO educationSearchVO = new EducationSearchVO();
		educationSearchVO.setPageNo(0);
		educationSearchVO.setMemberId("oph312");
		educationSearchVO.setStartIndex(1);
		educationSearchVO.setEndIndex(10);
		assertTrue(memberBiz.getPreCourseList(educationSearchVO).size() >= 0);
	}
	
	@Test
	public void getAllAttendClassListByIdTest(){
		
		MemberVO loginVO = new MemberVO();
		loginVO.setId("test02");
		List<EducationVO> resultList = memberBiz.getAllAttendClassListById(loginVO);
		assertNotNull(resultList);
		assertTrue( resultList.size() >= 0 );
	
	}
	
	@Test
	public void getOneEducationInfoTest(){
		
		Map<String, String> eduIdAndMemberId = new HashMap<String, String>();
		eduIdAndMemberId.put("educationId", "ED-20160513-000166");
		eduIdAndMemberId.put("memberId", "test02");
		
		EducationVO educationVO = memberBiz.getOneEducationInfo(eduIdAndMemberId);
		assertNotNull(educationVO);
		
	}
	
	@Test
	public void getAllAttendHistoryTest(){
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		
		String educationId = "ED-20160513-000166";
		Map<String, List<String>> resultList = memberBiz.getAllAttendHistory(memberVO, educationId);
		assertNotNull(resultList);
		assertTrue(resultList.size() >= 0 );
		
	}

}
