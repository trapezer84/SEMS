package com.ktds.sems.team.biz.impl;

import java.util.List;

import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.TeamBBSRplVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchListVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public class TeamBizImpl implements TeamBiz{

	private TeamDAO teamDAO;
	
	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	@Override
	public int getAllTeamCount(TeamSearchListVO teamSearchListVO) {
		return teamDAO.selectAllTeamCount(teamSearchListVO);
	}

	@Override
	public List<TeamVO> getAllTeams(TeamSearchVO teamSearchVO) {
		return teamDAO.selectAllTeams(teamSearchVO);
	}

	@Override
	public List<MemberVO> getAllMemberInfoByTeamId(String teamId) {
		return teamDAO.selectMemberInfoByTeamId(teamId);
	}

	@Override
	public List<TeamBBSVO> getTeamBBSByTeamId(String teamId) {
		return teamDAO.selectTeamBBSByTeamId(teamId);
	}

	@Override
	public TeamBBSVO getOneTeamBBSDetail(String teamBBSId) {
		return teamDAO.selectOneTeamBBSDetailByTeamBBSId(teamBBSId);
	}

	@Override
	public List<TeamBBSRplVO> getAllTeamBBSRpl(String teamBBSId) {
		return teamDAO.selectAllTeamBBSRpleByTeamBBSId(teamBBSId);
	}

	@Override
	public List<TeamVO> getAllTeamBymemberId(String memberId) {
		return teamDAO.selectAllTeamByMemberId(memberId);
	}

	
}
