package com.ktds.sems.team.biz;

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
public class TeamBizTest extends SemsTestCase {

	@Autowired
	private TeamBiz teamBiz;

	@Test
	public void getAllTeamCountTest() {
		int pageNo = 0;
		
		TeamSearchListVO teamSearchListVO = new TeamSearchListVO();
		Paging paging = new Paging(15, 15);
		teamSearchListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int teamCount = teamBiz.getAllTeamCount(teamSearchListVO);
		
		assertNotNull(teamCount);
		
	}

	@Test
	public void getAllTeamsTest() {
		int teamCount = 0;
		TeamSearchVO teamSearchVO = new TeamSearchVO();
		Paging paging = new Paging();
		
		paging.setTotalArticleCount(teamCount);
		teamSearchVO.setStartIndex(paging.getStartArticleNumber());
		teamSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<TeamVO> teamList = teamBiz.getAllTeams(teamSearchVO);
		
		assertNotNull(teamList);
	}
	
	@Test
	public void getAllMemberInfoByTeamIdTest() {
		String teamId = "1";
		List<MemberVO> members = teamBiz.getAllMemberInfoByTeamId(teamId);
		
		assertNotNull(members);
	}
	
	@Test
	public void getTeamBBSByTeamIdTest() {
		String teamId = "1";
		List<TeamBBSVO> bbss = teamBiz.getTeamBBSByTeamId(teamId);
		
		assertNotNull(bbss);
	}
	
	@Test
	public void getOneTeamBBSDetailTest() {
		String teamBBSId = "39";
		TeamBBSVO bbsVO = teamBiz.getOneTeamBBSDetail(teamBBSId);
		
		assertNotNull(bbsVO);
	}
	
	@Test
	public void getAllTeamBBSRplTest() {
		String teamBBSId = "39";
		List<TeamBBSRplVO> bbsRplVO = teamBiz.getAllTeamBBSRpl(teamBBSId);
		
		assertNotNull(bbsRplVO);
	}
	
	@Test
	public void getAllTeamBymemberIdTest() {
		String memberId = "test02"; 
		List<TeamVO> allTeam = teamBiz.getAllTeamBymemberId(memberId);
		
		assertNotNull(allTeam);
	}
	
}
