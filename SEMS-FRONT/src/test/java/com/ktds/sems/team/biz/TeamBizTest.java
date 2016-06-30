package com.ktds.sems.team.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSListVO;
import com.ktds.sems.team.vo.TeamBBSReplyListVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class TeamBizTest extends SemsTestCase {

	@Autowired
	private TeamBiz teamBiz;
	

	@Before
	public void setUp() {

		testHelper(new Testable() {

			@Override
			public void preparedTest() {

				if(!teamBiz.bulidTeam("testEducationId", "testTeamName") ) {
					throw new RuntimeException("일시적인 에러가 발생했습니다.");
				}
				
				TeamsListVO teamsListVO = new TeamsListVO();
				teamsListVO.setTeamListId("testTeamListId");
				teamsListVO.setMbrId("testMemberId");
				
				if(!teamBiz.insertMember(teamsListVO) ) {
					throw new RuntimeException("일시적인 에러가 발생했습니다.");
				}
			}
		});
	}

	@After
	public void tearDown() {

		testHelper(new Testable() {

			@Override
			public void preparedTest() {

				// 넣은 Team_List 데이터 지우기
				teamBiz.doDeleteTeamListByMemberId("testMemberId");
			}
		});
	}
	
	@Test
	public void getAllTeamListTest() {
		TeamSearchVO searchVO = new TeamSearchVO();
		
		searchVO.setSearchKeyword("");
		searchVO.setSearchType("teamId");
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		
		List<TeamVO> teamList = teamBiz.getAllTeamList(searchVO);
		if (teamList != null){
			for (TeamVO teamVO : teamList) {
				assertNotNull(teamVO.getTeamId());
				assertNotNull(teamVO.getTeamNumber());
				assertNotNull(teamVO.getTeamDate());
				assertNotNull(teamVO.getEducationId());
			}
		}
		else{
			fail("Fail...");
		}
	}
	
	@Test
	public void  getOneMyTeamDetail() {
		String teamId = "1";
		
		TeamVO team = new TeamVO();
		team = teamBiz.getOneMyTeamDetail(teamId);
		assertNotNull(team);
	}

	
	@Test
	public void getSaltByIdTest() {
		String sessionId = "test02";
		
		String result = teamBiz.getSaltById(sessionId);
		assertNotNull(result);
	}
	
	@Test
	public void getPasswordByIdTest() {
		String sessionId = "test02";
		
		String result = teamBiz.getPasswordById(sessionId);
		assertNotNull(result);
	}
	
	@Test
	public void isReplyByTeamBBSIdTest() {
		String teamBBSId = "TBBS-20160512-000054";
		
		boolean result = teamBiz.isReplyByTeamBBSId(teamBBSId);
		assertTrue(result);
	}

	
	@Test
	public void getOneTeamDetailTest(){
		String teamListId = "testTeamListId";
		List<TeamsListVO> teamsList = teamBiz.getOneTeamDetail(teamListId);
		if(teamsList != null) {
			for (TeamsListVO teamsListVO : teamsList) {
				assertNotNull(teamsListVO.getTeamListId());
				assertNotNull(teamsListVO.getTeamId());
				assertNotNull(teamsListVO.getMbrId());
			}
			
		} else {
			fail("fail");
		}
	}
	
	
	@Test
	public void getAllMyTeamList() {
		TeamSearchVO searchVO = new TeamSearchVO();
		
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		
		String memberId = "test02";
		
		List<TeamVO> teamList = teamBiz.getAllMyTeamList(searchVO, memberId);
		if (teamList != null){
			for (TeamVO teamVO : teamList) {
				assertNotNull(teamVO.getTeamId());
				assertNotNull(teamVO.getTeamName());
				assertNotNull(teamVO.getTeamDate());
				assertNotNull(teamVO.getEducationId());
				assertNotNull(teamVO.getEducationName());
				assertNotNull(teamVO.getTeamCount());
			}
		}
		else{
			fail("Fail...");
		}
	}
	

	@Test
	public void getMyTotalTeamCount() {
		
		int count = teamBiz.getMyTotalTeamCount();
		assertNotNull(count);
		
	}
	
	@Test
	public void getAllEduMember(){
		List<MemberVO> members = new ArrayList<MemberVO>();
		String educationId = "junitId";
		
		members = teamBiz.getAllEduMember(educationId);
		if (members != null){
			assertNotNull(members);
			assertTrue(members.size() >=0);
			assertTrue(members.size() >= 0);
			}
		else{
			fail("Fail...");
		}
	}
	
	@Test
	public void getTotalMinutesCountForAdmin(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		assertTrue(teamBiz.getTotalMinutesCountForAdmin(minutesSearchVO) > 0);
	}

	@Test
	public void getAllMinutes(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		List<MinutesVO> minutes = new ArrayList<MinutesVO>();
		
		minutesSearchVO.setEndIndex(10);
		minutesSearchVO.setStartIndex(1);
		
		minutes = teamBiz.getAllMinutes(minutesSearchVO);
		
		if (minutes != null){
			assertNotNull(minutes);
			assertTrue(minutes.size() >0);
		} else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doSearchListTest(){
		TeamBBSVO teamBBSVO = new TeamBBSVO();
		Paging paging = new Paging(15,15);
		TeamBBSListVO searchedListVO = new TeamBBSListVO();
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(0 + "");
		
		teamBBSVO.setCreatedDate( "2016/05" );
		teamBBSVO.setMemberId("test02");
		teamBBSVO.setDescript("sdgasdgsdg");
		
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	
		
		assertNotNull(teamBiz.doSearchList(teamBBSVO, searchVO));
	}
	
	@Test
	public void getSearchedBBSCountTest(){
		assertNotNull(teamBiz.getSearchedBBSCount());
	}
	
	@Test
	public void getStartYearTest(){
		assertNotNull(teamBiz.getStartYear());
	}
	
	@Test
	public void getEndYearTest(){
		assertNotNull(teamBiz.getEndYear());
	}
	
	@Test
	public void getFileInfoTest(){
		String teamBBSId = "TBBS-20160512-000054";
		assertNotNull(teamBiz.getFileInfo(teamBBSId));
	}
	
	@Test
	public void addNewTeamBBSArticle() {
		//TeamBBSVO teamBBS, Errors errors, MultipartHttpServletRequest request,  HttpSession session
	}
	
	@Test
	public void viewTeamBBSPage() {
		TeamBBSListVO searchedListVO = new TeamBBSListVO();
		Paging paging = new Paging(15,15);
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(0 + "");
		
		int searchedBBSCount = teamBiz.getSearchedBBSCount();
		assertTrue(searchedBBSCount > 0);

		paging.setTotalArticleCount(searchedBBSCount);
		
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	
		
		String startYear = teamBiz.getStartYear();
		String endYear = teamBiz.getEndYear();
		assertNotNull(startYear);
		assertNotNull(endYear);
		
		List<TeamBBSVO> teamBBSList = teamBiz.getTeamBBSList(searchVO);
		assertTrue(teamBBSList.size() > 0);
	}
	
	
	@Test
	public void viewReReplyPage() {
		String parentReplyId = "TBRP-20160504-000123";
		List<TeamBBSReplyVO> reReplies = teamBiz.getTeamBBSReReplies(parentReplyId); 
		
		assertTrue(reReplies.size() > 0);
	}
	
	@Test
	public void viewTeamBBSDetailPage( ) {
		
		String teamBBSId = "TBBS-20160512-000047";
		TeamBBSReplyListVO teamBBSReplyListVO = new TeamBBSReplyListVO();
		Paging paging = new Paging(15,15);
		teamBBSReplyListVO.setPaging(paging);
		paging.setPageNumber(0 +"");
		
		int replyCount = teamBiz.getReplyCountByTeamBBSId(teamBBSId);
		assertTrue(replyCount > 0);
		
		paging.setTotalArticleCount(replyCount);
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		searchVO.setTeamBBSId(teamBBSId);
		
		TeamBBSVO teamBBS = teamBiz.getTeamBBS(teamBBSId); 
		List<TeamBBSReplyVO> replies = teamBiz.getTeamBBSReplies(searchVO);
		assertTrue(replies.size() > 0);
		TeamBBSVO bbs = new TeamBBSVO();
		bbs.setMemberId("test02");
		bbs.setTeamBBSId(teamBBSId);
		// 조회수를 추가해준다.
		teamBiz.addHitsRecord(bbs);
		
		// 좋아요 싫어요 정보를 가져온다.
		String likeState = teamBiz.getLikeState(bbs);
		assertNotNull(likeState);
		String dislikeState = teamBiz.getDislikeState(bbs);
		assertNotNull(dislikeState);
		
		teamBBSReplyListVO.setTeamBBSReplyList(replies);


	}
	@Test
	public void writeNewMinutesTest() {
		
		MinutesVO minutesVO = new MinutesVO();
		
		minutesVO.setMemberId("test02");
		minutesVO.setMinutesAgenda("Junit_Test");
		minutesVO.setAttendance("Junit_Test");
		minutesVO.setMinutesPlace("Junit_Test");
		minutesVO.setMinutesContent("Junit_Test");
		minutesVO.setDecisionSubject("Junit_Test");
		minutesVO.setRemarks("Junit_Test");
		minutesVO.setTeamId("20");
		minutesVO.setStartDate("2016-05-17 10:00");
		minutesVO.setEndDate("2016-05-17 15:00");
		
		boolean result = teamBiz.writeNewMinutes(minutesVO);
		
		assertNotNull(result);
	}
	
	@Test
	public void getAllMinutesList() {
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		minutesSearchVO.setId("test02");
		assertNotNull(teamBiz.getAllMinutesList(minutesSearchVO));
	}
	
	@Test
	public void getTotalMinutesCount() {
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		int MinutesCount = teamBiz.getTotalMinutesCount(minutesSearchVO);
		
		assertNotNull(MinutesCount);
		assertTrue(MinutesCount > 0);
	}
	
	@Test
	public void getOneDetailMinutes() {
		String minutesId = "MINU-046";
		MinutesVO minutesVO = teamBiz.getOneDetailMinutes(minutesId);
		assertNotNull(minutesVO);
	}
	
	@Test
	public void getOneDetailMinutesDate() {
		String minutesId = "MINU-046";
		String minutesDate = teamBiz.getOneDetailMinutesDate(minutesId);
		assertNotNull(minutesDate);
	}

	
}