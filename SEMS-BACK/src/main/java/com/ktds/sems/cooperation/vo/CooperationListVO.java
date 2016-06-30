package com.ktds.sems.cooperation.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class CooperationListVO {

	private Paging paging;
	private List<CooperationVO> cooperationList;
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public List<CooperationVO> getCooperationList() {
		return cooperationList;
	}
	public void setCooperationList(List<CooperationVO> cooperationList) {
		this.cooperationList = cooperationList;
	}
	
	
	
}
