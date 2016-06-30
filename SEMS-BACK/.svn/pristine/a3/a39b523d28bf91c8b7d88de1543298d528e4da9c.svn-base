package com.ktds.sems.team.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.vo.TeamBBSRplVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchListVO;
import com.ktds.sems.team.vo.TeamSearchVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class TeamDAOTest extends SemsTestCase {

	@Autowired
	private TeamDAO teamDAO;
	
	@Test
	public void selectAllTeamCountTest() {
		int pageNo = 0;
		
		TeamSearchListVO teamSearchListVO = new TeamSearchListVO();
		Paging paging = new Paging(15, 15);
		teamSearchListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int teamCount = teamDAO.selectAllTeamCount(teamSearchListVO);
		
		assertNotNull(teamCount);
	}
	
	@Test
	public void  selectAllTeamsTest() {
		int teamCount = 0;
		TeamSearchVO teamSearchVO = new TeamSearchVO();
		Paging paging = new Paging();
		
		paging.setTotalArticleCount(teamCount);
		teamSearchVO.setStartIndex(paging.getStartArticleNumber());
		teamSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<TeamVO> teamList = teamDAO.selectAllTeams(teamSearchVO);
		
		assertNotNull(teamList);
	}

	@Test
	public void  selectMemberInfoByTeamIdTest() {
		String teamId = "1";
		List<MemberVO> memberList = teamDAO.selectMemberInfoByTeamId(teamId);
		
		assertNotNull(memberList);
	}

	
	@Test
	public void selectTeamBBSByTeamIdTest() {
		String teamId = "1";
		List<TeamBBSVO> bbsList = teamDAO.selectTeamBBSByTeamId(teamId);

		assertNotNull(bbsList);
	}

	
	@Test
	public void selectOneTeamBBSDetailByTeamBBSIdTest() {
		String teamBBSId = "39";
		TeamBBSVO bbsVO = teamDAO.selectOneTeamBBSDetailByTeamBBSId(teamBBSId);
		
		assertNotNull(bbsVO);
	}
	
	
	@Test
	public void selectAllTeamBBSRpleByTeamBBSIdTest() {
		String teamBBSId = "39";
		List<TeamBBSRplVO> bbsRplVO = teamDAO.selectAllTeamBBSRpleByTeamBBSId(teamBBSId);

		assertNotNull(bbsRplVO);
	}

	
	@Test
	public void selectAllTeamByMemberIdTest() {
		String memberId = "test02";
		List<TeamVO> teamList = teamDAO.selectAllTeamByMemberId(memberId);

		assertNotNull(teamList);
	}
}
