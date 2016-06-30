package com.ktds.sems.team.service.impl;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.TeamBBSRplVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchListVO;
import com.ktds.sems.team.vo.TeamSearchVO;

import kr.co.hucloud.utilities.web.Paging;

public class TeamServiceImpl implements TeamService {

	private TeamBiz teamBiz;
	
	public void setTeamBiz(TeamBiz teamBiz) {
		this.teamBiz = teamBiz;
	}

	@Override
	public ModelAndView getAllTeams(TeamSearchVO teamSearchVO, int pageNo) {
		TeamSearchListVO teamSearchListVO = new TeamSearchListVO();
		Paging paging = new Paging(15, 15);
		teamSearchListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int teamCount = teamBiz.getAllTeamCount(teamSearchListVO);
		if ( teamCount == 0 ) {
			teamCount++;
		}
		paging.setTotalArticleCount(teamCount);
		teamSearchVO.setStartIndex(paging.getStartArticleNumber());
		teamSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		ModelAndView view = new ModelAndView();
		List<TeamVO> teamList = teamBiz.getAllTeams(teamSearchVO);
		teamSearchListVO.setTeamList(teamList);
		view.setViewName("/team/teamList");
		view.addObject("teamSearchListVO", teamSearchListVO);
		
		
		
		return view;
	}

	@Override
	public ModelAndView getOneTeamDetail(String teamId) {
		List<MemberVO> members = teamBiz.getAllMemberInfoByTeamId(teamId);
		List<TeamBBSVO> bbss = teamBiz.getTeamBBSByTeamId(teamId);
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("members", members);
		view.addObject("bbss", bbss);
		view.setViewName("team/teamDetail");
		return view;
	}

	@Override
	public ModelAndView getOneTeamBBSDetail(String teamBBSId) {
		TeamBBSVO bbsVO = teamBiz.getOneTeamBBSDetail(teamBBSId);
		List<TeamBBSRplVO> bbsRplVO = teamBiz.getAllTeamBBSRpl(teamBBSId);
		
		ModelAndView view = new ModelAndView();
 		view.addObject("bbsVO", bbsVO);
 		view.addObject("bbsRplVO", bbsRplVO);
		view.setViewName("team/teamBBSDetail");
 		
		return view;
	}

	@Override
	public ModelAndView getAllTeamByMemberId(String memberId) {
		List<TeamVO> allTeam = teamBiz.getAllTeamBymemberId(memberId);
		
		ModelAndView view = new ModelAndView();
		view.addObject("allTeam", allTeam);
		view.addObject("memberId", memberId);
		view.setViewName("team/otherTeams");
		return view;
	}

	
	
}
