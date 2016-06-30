package com.ktds.sems.pc.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class UsedPcListVO {

	private Paging paging;
	private List<UsedPcVO> usedPcList;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<UsedPcVO> getUsedPcList() {
		return usedPcList;
	}

	public void setUsedPcList(List<UsedPcVO> usedPcList) {
		this.usedPcList = usedPcList;
	}

}
