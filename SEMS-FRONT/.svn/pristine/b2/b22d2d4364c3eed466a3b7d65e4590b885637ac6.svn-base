package com.ktds.sems.team.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamsListVO;
import com.ktds.sems.team.vo.TeamsListsVO;

import kr.co.hucloud.utilities.web.Paging;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.MinutesListVO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSVO;

@Transactional
public class TeamServiceTest extends SemsTestCase {
	
	@Autowired
	private TeamService teamService;
	@Autowired
	private TeamBiz teamBiz;
	@Autowired
	private TeamDAO teamDAO;

	@Before
	public void setUp() {
		
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				
				// Team_List 테이블에 데이터 넣기
				String[] insertMemberIds = {"testMemberId"};
				String educationId = "testEducationId";
				String teamName = "testTeamName";
				String teamListId = "testTeamListId";
				
				teamService.massiveInsertMember(insertMemberIds, educationId, teamName, teamListId);
				
				EducationVO educationVO = new EducationVO();
				educationVO.setEducationId("Junit");
				educationVO.setEducationCategory("Juni");
				educationVO.setEducationTitle("Junit");
				educationVO.setMemberId("Junit");
				educationVO.setMaxMember(10);
				educationVO.setEducationLocation("Junit");
				educationVO.setEducationCurriculum("Junit");
				educationVO.setEducationIntroduce("Junit");
				educationVO.setStartDate("Junit");
				educationVO.setEndDate("Junit");
				educationVO.setStartTime("Junit");
				educationVO.setEndTime("Junit");
				educationVO.setEducationType("Juni");
				educationVO.setCost("Juni");
				
				teamDAO.insertNewEducation(educationVO);
			}
		});
	}
	
	@After
	public void tearDown() {
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				
				// 넣은 Team_List 데이터 지우기
				String memberId = "testMemberId";
				String teamName = "testTeamName";
				teamService.doDeleteTeamListByMemberId(memberId, teamName);
				
				String educationId = "Junit";
				teamDAO.deleteEducation(educationId);
			}
		});
	}
	
	/**
	 * 전씨
	 */
	@Test
	public void viewModifyPageTest(){
		String teamBBSId = "TBBS-20160501-000050";
		TeamBBSVO teamBBS = teamBiz.getTeamBBS(teamBBSId);
		assertNotNull(teamBBS);
		
		ModelAndView view = teamService.viewModifyPage(teamBBSId);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/modifyBBS");
		}
		else {
			fail("fail");
		}
	}
	
	/**
	 * 전씨
	 */
	@Test
	public void checkPasswordTest() {
		
		String teamBBSId = "TBBS-20160501-000050";
		String type = "modify";
		
		ModelAndView view = teamService.checkPassword(teamBBSId, type);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/checkPassword");
		}
		else {
			fail("fail");
		}
				
	}
	
	/**
	 * 전씨
	 */
	@Test
	public void getSaltByIdTest(){
		String sessionId = "test02";
		
		String view = teamService.getSaltById(sessionId);
		assertNotNull(view);
	}


	/**
	 * 전씨
	 */
	@Test
	public void getPasswordByIdTest(){
		String sessionId = "test02";
		
		String view = teamService.getPasswordById(sessionId);
		assertNotNull(view);
	}
	

	/**
	 * 전씨
	 */
	@Test
	public void isReplyByTeamBBSIdTest() {
		String teamBBSId = "TBBS-20160512-000054";
		
		String result = teamService.isReplyByTeamBBSId(teamBBSId);
		assertNotNull(result);
	}
	
	/**
	 * 전씨
	 */
	@Test
	public void doModifyActionTest() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);
		
		TeamBBSVO teamBBS = new TeamBBSVO();
		
		teamBBS.setTeamBBSId("TBBS-20160501-000053");
		teamBBS.setTeamId("JUnit");
		teamBBS.setTitle("JUnit");
		teamBBS.setMemberId("JUnit");
		teamBBS.setIsNotice("Y");
		teamBBS.setDescript("JUnit");
		teamBBS.setCreatedDate("2016/05/24 오전 10:00:55");
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\152.png");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		
		ModelAndView view = teamService.doModifyAction(teamBBS, request, session);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/team/teamBBS/board");
		}
		else {
			fail("fail");
		}
	}
	
	@Test
	public void getAllTeamListPageTest() {

		TeamSearchVO teamSearchVO = new TeamSearchVO();
		teamSearchVO.setPageNo(0);
		teamSearchVO.setSearchType("teamId");
		teamSearchVO.setSearchKeyword("0");

		ModelAndView view = teamService.getAllTeamListPage(teamSearchVO);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/teamList");

		} else {
			fail("fail");
		}
	}

	@Test
	public void getOneTeamDetail() {

		String teamListId = "teamListId";

		ModelAndView view = teamService.getOneTeamDetail(teamListId);
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
			TeamsListsVO teamsListsVO = (TeamsListsVO) view.getModel().get("teamsListsVO");
			List<TeamsListVO> teamsListList = teamsListsVO.getTeamsListsVO();

			if( teamsListList != null) {
				for (TeamsListVO teamsListVO : teamsListList) {
					assertNotNull(teamsListVO.getTeamListId());
				}
			} else {
				fail("fail");
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void getAllMyTeamList() {

		TeamSearchVO teamSearchVO = new TeamSearchVO();
		teamSearchVO.setPageNo(0);
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("test02");
		session.setAttribute(Session.MEMBER, member);

		ModelAndView view = teamService.getAllMyTeamList(0, session);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "myPage/myTeamList");
			


		} else {
			fail("fail");
		}
	}
	

	@Test
	public void getOneMyTeamDetail() {
		String teamId = "JUNIT TEST";
		ModelAndView view = teamService.getOneMyTeamDetail(teamId);
		
		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "myPage/teamDetail");
			


		} else {
			fail("fail");
		}
	}

	
	@Test
	public void getAllEduMember(){
		
		ModelAndView view = new ModelAndView();
		String educationId = "ED-20160519-000241";
		MockHttpSession session = new MockHttpSession();
		
		MemberTypeVO memberType = new MemberTypeVO();
		memberType.setCdId("TR");
		session.setAttribute(Session.MEMBER_TYPE, memberType.getCdId());
		
		view.setViewName("education/registTeam");
		
		if (teamService.getAllEduMember(educationId, session) != null) {
			assertEquals(view.getViewName(), 
					teamService.getAllEduMember(educationId, session).getViewName());
		} else {
			fail("fail");
		}
	}
	
//	@Test
//	public void massiveInsertMember(){
//		ModelAndView view = new ModelAndView();
//		String educationId = "JunitTest";
//		String teamName = "JunitTest";
//		String[] insertMemberIds = {"JunitTest","JunitTest2"};
//		String teamListId = "JunitTest";
//		
//		view.setViewName("team/teamBoard");
//		if( teamService.massiveInsertMember(insertMemberIds, educationId, teamName, teamListId) != null ){
//			assertEquals(view.getViewName(), teamService.massiveInsertMember(insertMemberIds, educationId, teamName, teamListId).getViewName());
//		} else {
//			fail("fail");
//		}
//	}

	@Test
	public void getAllMinutes(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		MockHttpSession session = new MockHttpSession();
		ModelAndView view = new ModelAndView();
		MemberTypeVO memberType = new MemberTypeVO();
		memberType.setCdId("TR");
		session.setAttribute(Session.MEMBER_TYPE, memberType.getCdId());
		
		view.setViewName("team/minutesList");
		
		if(teamService.getAllMinutes(minutesSearchVO, 0, session) != null){
			assertEquals(view.getViewName(), teamService.getAllMinutes(minutesSearchVO, 0, session).getViewName());
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void doSearchListTest(){
		TeamBBSVO teamBBSVO = new TeamBBSVO();
		teamBBSVO.setCreatedDate( "2016/05" );
		teamBBSVO.setMemberId("test02");
		teamBBSVO.setDescript("sdgasdgsdg");
		
		assertNotNull(teamService.doSearchList(teamBBSVO, 0));
	}
	
	@Test
	public void viewReReplyPage(){
		MockHttpSession session = new MockHttpSession();
		MemberTypeVO memberType = new MemberTypeVO();
		session.setAttribute(Session.MEMBER_TYPE, memberType.getCdId());
		String teamBBSId = "test";
		String parentReplyId = "test1";
		ModelAndView view = teamService.viewReReplyPage(teamBBSId,session,parentReplyId );
		assertNotNull(view);
	}
	
	@Test
	public void viewTeamBBSPage(){
		String teamId = "JUNIT TEST";
		assertNotNull(teamService.viewTeamBBSPage(teamId, 0));
	}
	
	@Test
	public void viewTeamBBSDetailPage(){
		String teamBBSId = "TBBS-20160512-000047";
		int pageNo = 0;
		MockHttpSession session = new MockHttpSession();
		
		MemberVO member = new MemberVO();
		member.setId("test02");
		session.setAttribute("_MEMBER_", member);
		assertNotNull(teamService.viewTeamBBSDetailPage(teamBBSId, pageNo ,session));
	}
	
	@Test
	public void writeNewMinutesTest() {
		String teamId = "20";
		
		MinutesVO minutesVO = new MinutesVO();
		MockHttpSession session = new MockHttpSession();
		
		minutesVO.setMinutesId("MINU-077");
		minutesVO.setMinutesAgenda("Junit_Test");
		minutesVO.setAttendance("Junit_Test");
		minutesVO.setMinutesPlace("Junit_Test");
		minutesVO.setMinutesContent("Junit_Test");
		minutesVO.setDecisionSubject("Junit_Test");
		minutesVO.setRemarks("Junit_Test");
		minutesVO.setAgendaDate("2016-05-17");
		minutesVO.setStartTime("10:00");
		minutesVO.setEndTime("17:00");
		
		BindingResult errors = new BeanPropertyBindingResult(minutesVO, "minutesVO");
		
		MemberVO member = new MemberVO();
		member.setId("test02");
		session.setAttribute("_MEMBER_", member);
		
		ModelAndView view = teamService.writeNewMinutes( teamId, minutesVO, errors, session);
		assertNotNull(view);
	}
	
	@Test
	public void getOneDetailMinutes() {
		
		String MinutesId = "MINU-054";
		
		ModelAndView view = teamService.getOneDetailMinutes(MinutesId, null);
		
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/detailMinutes");
		}
		else {
			fail("fail...");
		}
		
		
	}
	
}
