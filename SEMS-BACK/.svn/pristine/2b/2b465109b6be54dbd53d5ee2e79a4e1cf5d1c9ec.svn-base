package com.ktds.sems.team.biz;

import java.util.List;

import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.vo.TeamBBSRplVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchListVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public interface TeamBiz {

	public int getAllTeamCount(TeamSearchListVO teamSearchListVO);
	public List<TeamVO> getAllTeams(TeamSearchVO teamSearchVO);
	public List<MemberVO> getAllMemberInfoByTeamId(String teamId);
	public List<TeamBBSVO> getTeamBBSByTeamId(String teamId);
	public TeamBBSVO getOneTeamBBSDetail(String teamBBSId);
	public List<TeamBBSRplVO> getAllTeamBBSRpl(String teamBBSId);
	public List<TeamVO> getAllTeamBymemberId(String memberId);

}
