package com.ktds.sems.team.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.TeamBBSRplVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchListVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public class TeamDAOImpl extends SqlSessionDaoSupport implements TeamDAO {

	@Override
	public int selectAllTeamCount(TeamSearchListVO teamSearchListVO) {
		return getSqlSession().selectOne("TeamDAO.selectAllTeamCount", teamSearchListVO);
	}

	@Override
	public List<TeamVO> selectAllTeams(TeamSearchVO teamSearchVO) {
		return getSqlSession().selectList("TeamDAO.selectAllTeams", teamSearchVO);
	}

	@Override
	public List<MemberVO> selectMemberInfoByTeamId(String teamId) {
		return getSqlSession().selectList("TeamDAO.selectMemberInfoByTeamId", teamId);
	}

	@Override
	public List<TeamBBSVO> selectTeamBBSByTeamId(String teamId) {
		return getSqlSession().selectList("TeamDAO.selectTeamBBSByTeamId", ""+teamId);
	}

	@Override
	public TeamBBSVO selectOneTeamBBSDetailByTeamBBSId(String teamBBSId) {
		return getSqlSession().selectOne("TeamDAO.selectOneTeamBBSDetailByTeamBBSId", teamBBSId);
	}

	@Override
	public List<TeamBBSRplVO> selectAllTeamBBSRpleByTeamBBSId(String teamBBSId) {
		return getSqlSession().selectList("TeamDAO.selectAllTeamBBSRpleByTeamBBSId", teamBBSId);
	}

	@Override
	public List<TeamVO> selectAllTeamByMemberId(String memberId) {
		return getSqlSession().selectList("TeamDAO.selectAllTeamByMemberId", memberId);
	}
	
}
