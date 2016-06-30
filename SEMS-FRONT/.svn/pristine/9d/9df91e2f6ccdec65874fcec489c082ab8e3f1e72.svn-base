package com.ktds.sems.team.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSListVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class TeamDAOTest extends SemsTestCase {
	@Autowired
	private TeamDAO teamDAO;

	@Before
	public void setUp() {

		testHelper(new Testable() {

			@Override
			public void preparedTest() {

				if(!teamDAO.bulidTeam("testEducationId", "testTeamName") ) {
					throw new RuntimeException("일시적인 에러가 발생했습니다.");
				}

				TeamsListVO teamsListVO = new TeamsListVO();
				teamsListVO.setTeamListId("testTeamListId");
				teamsListVO.setMbrId("testMemberId");

				if(!teamDAO.insertMember(teamsListVO) ) {
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
				teamDAO.doDeleteTeamListByMemberId("testMemberId");
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

		List<TeamVO> teamList = teamDAO.getAllTeamList(searchVO);
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
	public void getSaltByIdTest() {
		String sessionId = "test02";
		
		String result = teamDAO.getSaltById(sessionId);
		assertNotNull(result);
	}
	
	@Test
	public void getPasswordByIdTest() {
		String sessionId = "test02";
		
		String result = teamDAO.getPasswordById(sessionId);
		assertNotNull(result);
	}
	
	@Test
	public void isReplyByTeamBBSIdTest() {
		String teamBBSId = "TBBS-20160512-000054";
		
		List<String> list = teamDAO.isReplyByTeamBBSId(teamBBSId);
		for (String stringCheck : list) {
			assertNotNull(stringCheck);
		}
	}

	@Test
	public void getOneTeamDetailTest(){
		String teamId = "testTeamListId";
		List<TeamsListVO> teamsList = teamDAO.getOneTeamDetail(teamId);
		if (teamsList != null) {
			for (TeamsListVO teamsListVO : teamsList) {
				assertNotNull(teamsListVO.getMbrId());
				assertNotNull(teamsListVO.getTeamId());
				assertNotNull(teamsListVO.getTeamListId());
			}

		}else {
			fail("fail");
		}
	}
	
	@Test
	public void getAllMyTeamList() {
		TeamSearchVO searchVO = new TeamSearchVO();

		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		
		String memberId = "test02";

		List<TeamVO> teamList = teamDAO.getAllMyTeamList(searchVO, memberId);
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
	int count = teamDAO.getMyTotalTeamCount();
	assertNotNull(count);
		
	}	

	@Test
	public void getOneMyTeamDetail() {
	TeamVO team = new TeamVO();
	String teamId = "1";
	team = teamDAO.getOneMyTeamDetail(teamId);
	assertNotNull(team.getTeamName());
	assertNotNull(team.getMemberId());
	assertNotNull(team.getTeamCount());
	
		
	}
	
	

	@Test
	public void getAllEduMember(){
		String educationId = "ED-20160519-000241";
		List<MemberVO> members = new ArrayList<MemberVO>();
		members = teamDAO.getAllEduMember(educationId);

		if (members != null ) {
			assertNotNull(members);
			assertTrue(members.size() >= 0 );
		}
		else {
			fail("Fail...");
		}
	}

	@Test
	public void getTotalMinutesCountForAdmin(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		assertTrue(teamDAO.getTotalMinutesCountForAdmin(minutesSearchVO) > 0);
	}

	@Test
	public void getAllMinutes(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		List<MinutesVO> minutes = new ArrayList<MinutesVO>();

		minutesSearchVO.setEndIndex(10);
		minutesSearchVO.setStartIndex(1);

		minutes = teamDAO.getAllMinutes(minutesSearchVO);

		if (minutes != null ) {
			assertNotNull(minutes);
			assertTrue(minutes.size() > 0 );
		} else {
			fail("Fail...");
		}
	}
	
	@Test
	public void getReplyCountByTeamBBSId( ) {
		String teamBBSId = "TBBS-20160512-000054";
		assertNotNull(teamDAO.getReplyCountByTeamBBSId(teamBBSId));
	}
	
	@Test
	public void getTeamBBSReReplies(){
		String parentReplyId = "TBRP-20160504-000130";
		assertNotNull(teamDAO.getTeamBBSReReplies(parentReplyId));
	}
	
	@Test
	public void writeBBSReReply() {
		TeamBBSReplyVO replyVO = new TeamBBSReplyVO();
		
		String teamBBSReplyId = String.valueOf(teamDAO.getNextTeamBBSReplySeq());
		String sysdate = teamDAO.getSysDate();
		teamBBSReplyId = lpad(teamBBSReplyId, 6 ,"0");
		teamBBSReplyId = "TBRP" + '-' + sysdate + '-' + teamBBSReplyId;
		replyVO.setReplyId(teamBBSReplyId);
		replyVO.setOrderNo(0);
		replyVO.setDescript("TEST");
		replyVO.setTeamBBSId("TEST");
		replyVO.setDepth(0);
		replyVO.setMemberId("test");
		replyVO.setParentReplyId("?");
		assertTrue(teamDAO.writeBBSReReply(replyVO) > 0);
		assertTrue(teamDAO.deleteNewReply() > 0);
	}
	
	@Test
	public void getTeamBBSList() {
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setEndIndex(10);
		searchVO.setStartIndex(0);
		assertNotNull(teamDAO.getTeamBBSList(searchVO));
	}
	
	@Test
	public void writeBBSReply() {
		TeamBBSReplyVO replyVO = new TeamBBSReplyVO();
		
		String teamBBSReplyId = String.valueOf(teamDAO.getNextTeamBBSReplySeq());
		String sysdate = teamDAO.getSysDate();
		teamBBSReplyId = lpad(teamBBSReplyId, 6 ,"0");
		teamBBSReplyId = "TBRP" + '-' + sysdate + '-' + teamBBSReplyId;
		replyVO.setReplyId(teamBBSReplyId);
		replyVO.setOrderNo(0);
		replyVO.setDescript("TEST");
		replyVO.setTeamBBSId("TEST");
		replyVO.setDepth(0);
		replyVO.setMemberId("test");
		replyVO.setParentReplyId("0");
		assertTrue(teamDAO.writeBBSReply(replyVO) > 0);
		
	//	assertNotNull(teamDAO.getLikeState(bbs));
		
		assertTrue(teamDAO.deleteNewReply() > 0);
	}
	
	@Test
	public void addDislikeCount() {
		TeamBBSVO bbs = new TeamBBSVO();
		bbs.setMemberId("test02");
		bbs.setTeamBBSId("TBBS-20160512-000040");
		assertTrue(teamDAO.addDislikeCount(bbs) > 0);
	}
	
	@Test
	public void addLikeCount() {
		TeamBBSVO bbs = new TeamBBSVO();
		bbs.setMemberId("test02");
		bbs.setTeamBBSId("TBBS-20160512-000040");
		assertTrue( teamDAO.addLikeCount(bbs) > 0);
	}
	
	@Test
	public void addLikeRecord() {
		TeamBBSVO bbs = new TeamBBSVO();
		bbs.setMemberId("test02");
		bbs.setTeamBBSId("TBBS-20160512-000040");
		
		assertTrue(teamDAO.addLikeRecord(bbs) > 0);
		assertNotNull(teamDAO.getLikeState(bbs));
		assertTrue(teamDAO.checkLikeByTeamBBSVO(bbs) > 0);
		assertTrue(teamDAO.initLikeRecord(bbs) > 0);
		
	}
	
	@Test
	public void addDislikeRecord() {
		TeamBBSVO bbs = new TeamBBSVO();
		bbs.setMemberId("test02");
		bbs.setTeamBBSId("TBBS-20160512-000040");
		
		assertTrue(teamDAO.addDislikeRecord(bbs) > 0);
		assertNotNull(teamDAO.getDislikeState(bbs));
		assertTrue(teamDAO.checkDislikeByTeamBBSVO(bbs) > 0);
		assertTrue(teamDAO.initLikeRecord(bbs) > 0);
	}
	
	@Test
	public void addHitsRecord() {
		TeamBBSVO bbs = new TeamBBSVO();
		bbs.setMemberId("test05");
		bbs.setBbsHistoryId("testest");
		bbs.setTeamBBSId("TBBS-20160512-000040");
		assertTrue(teamDAO.addHitsRecord(bbs) > 0);	
		assertTrue(teamDAO.removeHitsRecord(bbs.getBbsHistoryId()) > 0);
	}
	
	@Test 
	public void getTeamBBS(){
		String teamBBSId = "TBBS-20160512-000040";
		assertNotNull(teamDAO.getTeamBBS(teamBBSId));
	}
	
	private String lpad(String source, int length, String defValue) {
		
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for ( int i = 0 ; i < needLength ; i++ ) {
			source = defValue + source;
		}
		return source;
	}
	

	@Test
	public void getSearchedBBSCountTest(){
		assertNotNull(teamDAO.getSearchedBBSCount());
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
		
		assertNotNull(teamDAO.doSearchList(teamBBSVO, searchVO));
	}
	
	@Test
	public void getStartYearTest(){
		assertNotNull(teamDAO.getStartYear());
	}
	
	@Test
	public void getEndYearTest(){
		assertNotNull(teamDAO.getEndYear());
	}
	
	@Test
	public void getFileInfoTest(){
		String teamBBSId = "TBBS-20160512-000054";
		assertNotNull(teamDAO.getFileInfo(teamBBSId));
	}
	
	@Test
	public void insertNewMinutesTest() {
		
		MinutesVO minutesVO = new MinutesVO();
		
		String minutesId = "tEST-043";
		String memberId = "test02";
		
		minutesVO.setMinutesId(minutesId);
		minutesVO.setMemberId(memberId);
		minutesVO.setMinutesAgenda("Junit_Test");
		minutesVO.setAttendance("Junit_Test");
		minutesVO.setMinutesPlace("Junit_Test");
		minutesVO.setMinutesContent("Junit_Test");
		minutesVO.setDecisionSubject("Junit_Test");
		minutesVO.setRemarks("Junit_Test");
		minutesVO.setTeamId("20");
		minutesVO.setStartDate("2016-05-17 10:00");
		minutesVO.setEndDate("2016-05-17 15:00");
		
		int result = teamDAO.insertNewMinutes(minutesVO);
		assertTrue(result>0);
		assertTrue(teamDAO.deleteTestMinutes(minutesId) > 0);
		
	}
	
	@Test
	public void getTotalMinutesCountTest() {
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		minutesSearchVO.setSearchSBTKeyword("ㅎㅎ");
		assertNotNull(teamDAO.getTotalMinutesCount(minutesSearchVO));
	}
	
	@Test
	public void getAllMinutesListTest() {
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		minutesSearchVO.setSearchSBTKeyword("ㅎㅋ");
		minutesSearchVO.setMemberId("test01");
		assertNotNull(teamDAO.getAllMinutesList(minutesSearchVO));
	}
	
	@Test
	public void getOneDetailMinutesTest() {
		String minutesId = "MINU-047";
		assertNotNull(teamDAO.getOneDetailMinutes(minutesId));
	}
	
	@Test
	public void getOneDetailMinutesDateTest() {
		String minutesId = "MINU-047";
		assertNotNull(teamDAO.getOneDetailMinutesDate(minutesId));
	}
	
}
