package com.ktds.sems.team.vo;

import java.util.ArrayList;
import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class TeamBBSListVO {

	private List<TeamBBSVO> teamBBSList;
	private Paging paging;
	
	public List<TeamBBSVO> getTeamBBSList() {
		List<TeamBBSVO> BBSList = new ArrayList<TeamBBSVO>();
		teamBBSList.addAll(BBSList);
		
		return teamBBSList;
	}
	public void setTeamBBSList(List<TeamBBSVO> teamBBSList) {
		List<TeamBBSVO> BBSList = new ArrayList<TeamBBSVO>();
		BBSList.addAll(teamBBSList);
		
		this.teamBBSList = BBSList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
