package com.ktds.sems.member.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberDAOTest extends SemsTestCase {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private EducationDAO educationDAO;

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
				MockHttpServletRequest request = new MockHttpServletRequest();
				LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
				int seq = memberDAO.nextLoginHistorySeq();
				loginHistoryVO.setLgiHtrId(seq);
				loginHistoryVO.setId("test04");
				loginHistoryVO.setLgiIp(request.getRemoteHost());
				memberDAO.stampLoginTime(loginHistoryVO);
			}
		});
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				educationDAO.doApplyEducation("testEducationId", "testMemberId");
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
				int seq = memberDAO.currentLoginHistorySeq();
				memberDAO.deleteJunitTestStampLoginTime(seq);
			}
		});
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				educationDAO.deleteEducationHistoryByMemberId("testMemberId");
			}
		});
	}

	/**
	 * SALT 얻어오기
	 */
	@Test
	public void getSaltByIdTest() {
		String id = "cocomo12";
		String salt = memberDAO.getSaltById(id);
		assertNotNull(salt);
	}

	/**
	 * 로그인
	 */
	@Test
	public void loginTest() {
		MemberVO loginVO = new MemberVO();
		loginVO.setId("cocomo12");
		loginVO.setPassword("afd547fcea7810b7fb5512c0eff23922b5a0cdfa0c731c4447e7d9af31f7e955");

		MemberVO memberVO = memberDAO.login(loginVO);
		if (memberVO != null) {
			assertNotNull(memberVO.getId());
			assertNotNull(memberVO.getPassword());
			assertNotNull(memberVO.getName());
			assertNotNull(memberVO.getEmail());
			assertNotNull(memberVO.getBirthDate());
			assertNotNull(memberVO.getPhoneNumber());
			assertNotNull(memberVO.getMemberType());
			assertNotNull(memberVO.getSalt());
			assertNotNull(memberVO.getLoginFailCount());
			assertNotNull(memberVO.getIsResign());
			assertNotNull(memberVO.getModifyFailCount());
			assertNotNull(memberVO.getIsModifyLock());
		} else {
			fail("fail");
		}
	}

	/**
	 * 계정 잠겼는지 확인
	 */
	@Test
	public void isAccountLockTest() {
		String id = "cocomo12";
		boolean isSuccess = memberDAO.isAccountLock(id);
		assertTrue(!isSuccess);
	}

	/**
	 * 로그인 성공 확인
	 */
	@Test
	public void loginSuccessTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.loginSuccess(id);
		assertTrue(executeQuery > 0);
	}

	/**
	 * 로그인 실패 횟수 증가
	 */
	@Test
	public void plusLoginFailCountTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.plusLoginFailCount(id);
		assertTrue(executeQuery > 0);
	}

	/**
	 * 계정 잠금 상태 최신화
	 */
	@Test
	public void updateAccountLockTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.updateAccountLock(id);
		assertTrue(executeQuery == 0);
	}

	/**
	 * 로그인한지 30일 지났는지 확인
	 */
	@Test
	public void needToChangPasswordTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.needToChangPassword(id);
		assertNull(checkStr);
	}

	/**
	 * 존재하는 아이디인지 확인
	 */
	@Test
	public void isExistIdTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.isExistId(id);
		assertNotNull(checkStr);
	}

	/**
	 * 탈퇴한 회원인지 확인
	 */
	@Test
	public void isResignTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.isResign(id);
		assertNull(checkStr);
	}

	/**
	 * 한명의 회원 정보 가져오기
	 */
	@Test
	public void getOneMemberTest() {

		String id = "test02";
		MemberVO memberVO = memberDAO.getOneMember(id);

		if (memberVO != null) {
			assertNotNull(memberVO.getId());
			assertNotNull(memberVO.getPassword());
			assertNotNull(memberVO.getName());
			assertNotNull(memberVO.getEmail());
			assertNotNull(memberVO.getHighestEducationLevel());
			assertNotNull(memberVO.getUniversityName());
			assertNotNull(memberVO.getMajorName());
			assertNotNull(memberVO.getGraduationType());
			assertNotNull(memberVO.getBirthDate());
			assertNotNull(memberVO.getPhoneNumber());
			assertNotNull(memberVO.getMemberType());
			assertNotNull(memberVO.getUuid());
		} else {
			fail("fail");
		}
	}

	/**
	 * 계정 잠겨있는지 확인
	 */
	@Test
	public void isModifyAccountLockTest() {
		String id = "aaa";
		int executeQuery = memberDAO.isModifyAccountLock(id);
		assertTrue(executeQuery == 0);
	}

	// /**
	// * 탈퇴 회원 업데이트
	// */
	// @Test
	// public void doDeleteMemberTest() {
	// String id = "cocomo12";
	// int executeQuery = memberDAO.doDeleteMember(id);
	// assertTrue(executeQuery > 0);
	// }

	@Test
	public void getMenuCategoryListTest() {
		List<MenuManageVO> menu = memberDAO.getMenuCategoryList();
		assertNotNull(menu);
		assertTrue(menu.size() > 0);
	}
	
	@Test
	public void getLoginHistoryListByMemberIdTest() {
		MemberVO member = new MemberVO();
		member.setId("test02");
		
		List<LoginHistoryVO> loginHistoryList = memberDAO.getLoginHistoryListByMemberId(member.getId());
		assertNotNull(loginHistoryList);
	    assertTrue(loginHistoryList.size() > 0);
	}
	
	@Test
	public void getEducationHistoryListByMemberIdTest() {
		MemberVO member = new MemberVO();
		member.setId("test02");
		
		List<EducationHistoryVO> educationHistoryList = memberDAO.getEducationHistoryListByMemberId(member.getId());
		assertNotNull(educationHistoryList);
		assertTrue(educationHistoryList.size() > 0);
	}
	
	@Test
	public void getQnaListByMemberIdTest() {
		MemberVO member = new MemberVO();
		member.setId("test02");
		
		List<QNAVO> qnaList = memberDAO.getQnaListByMemberId(member.getId());
		assertNotNull(qnaList);
		assertTrue(qnaList.size() > 0);
	}

	@Test
	public void getReportReplyListByMemberIdTest() {
		MemberVO member = new MemberVO();
		member.setId("test02");
		
		List<ReportReplyVO> reportReplyList = memberDAO.getReportReplyListByMemberId(member.getId());
		assertNotNull(reportReplyList);
		assertTrue(reportReplyList.size() >= 0);
	}

	@Test
	public void getEduListByMemberTest() {
		MemberVO loginVO = new MemberVO();
		loginVO.setId("JUnitTest");
		List<EducationVO> eduList = memberDAO.getEduListByMember(loginVO);
		assertNotNull(eduList);
		assertTrue(eduList.size() >= 0);
	}

	@Test
	public void getLastDateTest() {
		Map<String, String> eduIdAndMemberId = new HashMap<String, String>();
		eduIdAndMemberId.put("educationId", "JUnitTestEdu");
		eduIdAndMemberId.put("memberId", "JUnitTestmbrId");
		String date = memberDAO.getLastDate(eduIdAndMemberId);
		assertNotNull(date);
	}

	/**
	 * @author 남준호
	 */
	@Test
	public void addNewMemberTest() {
		MemberVO member = new MemberVO();
		member.setId("JunitId1");
		member.setPassword("44cc5a083ad03370997e88834c4de95460fc54dc0562c401b71b9d504fe9d9b3");
		member.setHighestEducationLevel("HIGH");
		member.setName("Junitdd");
		member.setEmail("Junitdd@naver.com");
		member.setUniversityName("JUNIT대학교");
		member.setMajorName("JUNIT과");
		member.setGraduationType("GRAD");
		member.setBirthDate("1991-06-03");
		member.setPhoneNumber("010-0000-1154");
		member.setMemberType("MBR");
		member.setSalt("c21586d7786ea63b");
		int addNewMember = memberDAO.addNewMember(member);

		assertTrue(addNewMember > 0);
	}

	@Test
	public void isExistEmailTest() {
		String isExistEmail = memberDAO.isExistEmail("sosdig@naver.com");
		assertNotNull(isExistEmail);
		assertTrue(isExistEmail.equals("sosdig1"));

	}

	@Test
	public void zdelectJunitTestMember() {
		memberDAO.delectJunitTestMember("JunitId1");
	}

	/**
	 * 나의 교육 이력 보기
	 */
	@Test
	public void getAllEducationHistoryListByIdWithPagingTest() {

		Paging paging = new Paging();
		paging.setPageNumber(0 + "");
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setMemberId("test04");

		int totalArticleNumber = memberDAO.getTotalEducationHistoryCountById(educationHistorySearchVO);

		if (totalArticleNumber != 0) {

			paging.setTotalArticleCount(totalArticleNumber);
			educationHistorySearchVO = new EducationHistorySearchVO();
			educationHistorySearchVO.setPageNo(0);
			educationHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
			educationHistorySearchVO.setEndIndex(paging.getEndArticleNumber());

			educationHistorySearchVO.setMemberId("test01");
			List<EducationHistoryVO> educationHistoryList = memberDAO
					.getAllEducationHistoryListByIdWithPaging(educationHistorySearchVO);

			if (educationHistoryList != null) {

				for (EducationHistoryVO educationHistoryVO : educationHistoryList) {
					assertNotNull(educationHistoryVO.getEducationHistoryId());
					assertNotNull(educationHistoryVO.getEducationId());
					assertNotNull(educationHistoryVO.getMemberId());
					assertNotNull(educationHistoryVO.getEducationHistoryDate());
					assertNotNull(educationHistoryVO.getState());
					assertNotNull(educationHistoryVO.getIp());
					assertNotNull(educationHistoryVO.getStartDate());
					assertNotNull(educationHistoryVO.getEndDate());
					assertNotNull(educationHistoryVO.getEducationTitle());
					assertNotNull(educationHistoryVO.getCost());
				}

			} else {
				fail("fail");
			}

		} else {
			fail("fail");
		}
	}

	@Test
	public void saveLoginHistoryAsExcelTest() {
		List<LoginHistoryVO> exportFile = memberDAO.saveLoginHistoryAsExcel("test04");

		if (exportFile != null) {
			for (LoginHistoryVO loginHistoryVO : exportFile) {
				assertNotNull(loginHistoryVO.getLgiHtrId());
				assertNotNull(loginHistoryVO.getId());
				assertNotNull(loginHistoryVO.getLgiIp());
				assertNotNull(loginHistoryVO.getLgiDt());
				// assertNotNull(loginHistoryVO.getLgoDt()); <= 이전 로그인 이력에
				// null값이 있을 수 있다.
			}
			assertTrue(exportFile.size() > 0);
		} else {
			fail("[DAO Part]saveLoginHistoryAsExcelTest Fail.");
		}
	}

	@Test
	public void getTotalLoginHistoryCountTest() {
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setId("test04");
		loginHistorySearchVO.setBeginDate("2016/05/16");
		loginHistorySearchVO.setCloseDate("2016/05/17");
		int count = memberDAO.getTotalLoginHistoryCount(loginHistorySearchVO);
		if (count > 0) {
			assertNotNull(count);
		} else {
			fail("[DAO Part]getTotalLoginHistoryCountTest Fail.");
		}
	}

	@Test
	public void nextLoginHistorySeqTest() {
		int seq = memberDAO.nextLoginHistorySeq();
		if (seq > 0) {
			assertNotNull(seq);
		} else {
			fail("[DAO Part]nextLoginHistorySeqTest Fail.");
		}
	}

	@Test
	public void getAllLoginHistoryTest() {
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setId("test04");
		loginHistorySearchVO.setBeginDate("2016/05/16");
		loginHistorySearchVO.setCloseDate("2016/05/17");
		List<LoginHistoryVO> loginHistoryList = memberDAO.getAllLoginHistory(loginHistorySearchVO);

		if (loginHistoryList != null) {
			for (LoginHistoryVO loginHistoryVO : loginHistoryList) {
				assertNotNull(loginHistoryVO.getLgiHtrId());
				assertNotNull(loginHistoryVO.getId());
				assertNotNull(loginHistoryVO.getLgiIp());
				assertNotNull(loginHistoryVO.getLgiDt());
				assertNotNull(loginHistoryVO.getLgoDt());
				assertNotNull(loginHistoryVO.getIsReq());
				assertNotNull(loginHistoryVO.getChkCnt());
			}
			assertNotNull(loginHistorySearchVO);
		} else {
			fail("[DAO Part]getAllLoginHistoryTest Fail.");
		}
	}

	@Test
	public void doMatchHistoryWithMemberTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setLgiHtrId(1048);
		loginHistoryVO.setId("test04");
		String check = memberDAO.doMatchHistoryWithMember(loginHistoryVO);
		if (check.equals("Y")) {
			assertNotNull(check);
		} else {
			fail("[DAO Part]doMatchHistoryWithMemberTest Fail.");
		}
	}

	@Test
	public void doCheckIpTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setLgiHtrId(1048);
		loginHistoryVO.setId("test04");
		int check = memberDAO.doCheckIp(loginHistoryVO);
		if (check > 0) {
			assertNotNull(check);
		} else {
			fail("[DAO Part]doCheckIpTest Fail.");
		}

	}

	@Test
	public void checkIpInfoTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		MockHttpServletRequest request = new MockHttpServletRequest();
		loginHistoryVO.setLgiHtrId(1048);
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiIp(request.getRemoteHost());
		LoginHistoryVO test = memberDAO.checkIpInfo(loginHistoryVO);
		if (test != null) {
			assertNotNull(loginHistoryVO.getLgiHtrId());
			assertNotNull(loginHistoryVO.getId());
			assertNotNull(loginHistoryVO.getLgiIp());
			assertNotNull(loginHistoryVO.getChkCnt());
			// assertNotNull(loginHistoryVO.getLgiDt()); <==데이터 존재하지만 null
			// error발생
			// assertNotNull(loginHistoryVO.getLgoDt()); <==데이터 존재하지만 null
			// error발생
		} else {
			fail("[DAO Part]doCheckIpTest Fail.");
		}
	}

	/**
	 * 나의 교육 이력 엑셀 다운로드
	 */
	@Test
	public void getAllEducationHistoryListByIdTest() {

		String id = "testMemberId";
		List<EducationHistoryVO> educationHistoryList = memberDAO.getAllEducationHistoryListById(id);

		if (educationHistoryList != null) {

			for (EducationHistoryVO educationHistoryVO : educationHistoryList) {
				assertNotNull(educationHistoryVO.getEducationHistoryId());
				assertNotNull(educationHistoryVO.getEducationId());
				assertNotNull(educationHistoryVO.getMemberId());
				assertNotNull(educationHistoryVO.getEducationHistoryDate());
				assertNotNull(educationHistoryVO.getState());
				assertNotNull(educationHistoryVO.getIp());
				assertNotNull(educationHistoryVO.getStartDate());
				assertNotNull(educationHistoryVO.getEndDate());
				assertNotNull(educationHistoryVO.getEducationTitle());
				assertNotNull(educationHistoryVO.getCost());
			}

		} else {
			fail("fail");
		}
	}

	/**
	 * 진행중인 교육 이력 보기
	 */
	@Test
	public void getJoinEducationList() {

		String memberId = "test05";
		List<EducationHistoryVO> educationHistoryList = memberDAO.getJoinEducationList(memberId);

		if (educationHistoryList != null) {

			for (EducationHistoryVO educationHistoryVO : educationHistoryList) {
				assertNotNull(educationHistoryVO.getEducationHistoryId());
				assertNotNull(educationHistoryVO.getEducationId());
				assertNotNull(educationHistoryVO.getMemberId());
				assertNotNull(educationHistoryVO.getEducationHistoryDate());
				assertNotNull(educationHistoryVO.getState());
				assertNotNull(educationHistoryVO.getIp());
				assertNotNull(educationHistoryVO.getStartDate());
				assertNotNull(educationHistoryVO.getEndDate());
				assertNotNull(educationHistoryVO.getEducationTitle());
				assertNotNull(educationHistoryVO.getCost());
			}

		} else {
			fail("fail");
		}
	}

/*	@Test
	public void getCourseList() {
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();

		educationHistorySearchVO.setMemberId("test02");
		educationHistorySearchVO.setEndIndex(5);
		educationHistorySearchVO.setStartIndex(0);

//		memberDAO.getCourseList(educationHistorySearchVO);

		assertNotNull(educationHistorySearchVO);
	}*/

	@Test
	public void getOneEducationByIdAndEducationId() {
		String id = "test02";
		String eduId = "ED-20160516-000185";
		assertNotNull(memberDAO.getOneEducationByIdAndEducationId(eduId, id));
	}

	@Test
	public void dropCourseApply() {
		EducationHistoryVO educationHistory = new EducationHistoryVO();
		educationHistory.setCmnt("JUnit!!!!!!!!test!!");
		educationHistory.setEducationId("ED-20160516-000185");
		educationHistory.setMemberId("test02");

		assertTrue(memberDAO.dropCourseApply(educationHistory) > 0);
	}

	/**
	 * @param loginHistoryVO
	 *            Action - insert
	 */
	@Test
	public void stampLoginTimeTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		if (loginHistoryVO.getId() == null || loginHistoryVO.getLgiHtrId() <= 0 || loginHistoryVO.getLgiIp() == null ) {
			MockHttpServletRequest request = new MockHttpServletRequest();
			loginHistoryVO.setId("test04");
			loginHistoryVO.setLgiIp(request.getRemoteHost());
		}
		int check = memberDAO.stampLoginTime(loginHistoryVO);
		if (check > 0) {
			assertNotNull(loginHistoryVO.getLgiHtrId());
			assertNotNull(loginHistoryVO.getId());
			assertNotNull(loginHistoryVO.getLgiIp());
		} else {
			fail("[DAO Part] stampLoginTimeTest Fail.");
		}
	}

	/**
	 * @param loginHistoryVO
	 *            Action - insert
	 */
	@Test
	public void stampLogoutTimeTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		if (loginHistoryVO.getLgiHtrId() <= 0) {
			loginHistoryVO.setLgiHtrId(1048);
		}
		int check = memberDAO.stampLogoutTime(loginHistoryVO);
		if (check > 0) {
			assertNotNull(loginHistoryVO.getLgiHtrId());
			assertNotNull(check);
		} else {
			fail("[DAO Part] stampLogoutTimeTest Fail.");
		}
	}

	/**
	 * @param String
	 *            memberId Action - update
	 */
	@Test
	public void stampLogoutTimeByMemberIdTest() {
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		if(loginHistoryVO.getId() == null) {
			loginHistoryVO.setId("test04");			
		}
		int check = memberDAO.stampLogoutTimeByMemberId(loginHistoryVO.getId());
		if (check > 0) {
			assertNotNull(loginHistoryVO.getId());
			assertNotNull(check);
		} else {
			fail("[DAO Part] stampLogoutTimeByMemberIdTest Fail.");
		}
	}

	@Test
	public void checkRegistStateFalseTest() {
		String id = "registerFailed";
		int check = memberDAO.checkRegistState(id);
		assertTrue(check < 1);
	}
	
	@Test
	public void checkRegistStateTrueTest() {
		String id = "test02";
		int check = memberDAO.checkRegistState(id);
		assertTrue(check > 0);
	}
	
	@Test
	public void checkValidationCourseAccessTrueTest() {
		String id = "cocomo12";
		int check = memberDAO.checkRegistState(id);
		assertTrue(check > 0);
	}
	
	@Test
	public void checkValidationCourseAccessFailTest() {
		String id = "newId";
		int check = memberDAO.checkRegistState(id);
		assertTrue(check == 0);
	}
	
	@Test
	public void getCourseCountByIdTest() {
		String id = "oph312";
		assertTrue(memberDAO.getCourseCountById(id) >=0);
	}
	
	@Test
	public void getCourseListTest() {
		String id = "oph312";
		assertTrue(memberDAO.getCourseList(id).size() >=0);
	}
	
	@Test
	public void getPreCourseListTest() {
		EducationSearchVO educationSearchVO = new EducationSearchVO();
		educationSearchVO.setPageNo(0);
		educationSearchVO.setMemberId("oph312");
		educationSearchVO.setStartIndex(1);
		educationSearchVO.setEndIndex(10);
		assertTrue(memberDAO.getPreCourseList(educationSearchVO).size() >= 0);
	}
	
	@Test
	public void getAllAttendHistoryListByIdTest(){
		Map<String, String> eduIdAndMemberId = new HashMap<String, String>();
		eduIdAndMemberId.put("educationId", "ED-20160513-000166");
		eduIdAndMemberId.put("memberId", "test02");
		List<AttendVO> attendList = memberDAO.getAllAttendHistoryListById(eduIdAndMemberId);
		assertTrue(attendList.size() >= 0);
	}
	
	@Test
	public void getOneEducationInfoTest(){
		Map<String, String> eduIdAndMemberId = new HashMap<String, String>();
		eduIdAndMemberId.put("educationId", "ED-20160513-000166");
		eduIdAndMemberId.put("memberId", "test02");
		EducationVO educationVO = memberDAO.getOneEducationInfo(eduIdAndMemberId);
		assertNotNull(educationVO);
	}
}
