package com.ktds.sems.team.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class MinutesListVO {

	private Paging paging;
	private List<MinutesVO> minutesList;
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public List<MinutesVO> getMinutesList() {
		return minutesList;
	}
	public void setMinutesList(List<MinutesVO> minutesList) {
		this.minutesList = minutesList;
	}
	
}
