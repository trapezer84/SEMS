package com.ktds.sems.member.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class LoginHistoryListVO {

	private Paging paging;
	private List<LoginHistoryVO> loginHistoryList;
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public List<LoginHistoryVO> getLoginHistoryList() {
		return loginHistoryList;
	}
	public void setLoginHistoryList(List<LoginHistoryVO> loginHistoryList) {
		this.loginHistoryList = loginHistoryList;
	}
}
