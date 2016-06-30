package com.ktds.sems.team.dao;

import java.util.List;

import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.vo.TeamBBSRplVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchListVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public interface TeamDAO {

	public int selectAllTeamCount(TeamSearchListVO teamSearchListVO);
	public List<TeamVO> selectAllTeams(TeamSearchVO teamSearchVO);
	public List<MemberVO> selectMemberInfoByTeamId(String teamId);
	public List<TeamBBSVO> selectTeamBBSByTeamId(String teamId);
	public TeamBBSVO selectOneTeamBBSDetailByTeamBBSId(String teamBBSId);
	public List<TeamBBSRplVO> selectAllTeamBBSRpleByTeamBBSId(String teamBBSId);
	public List<TeamVO> selectAllTeamByMemberId(String memberId);

}
