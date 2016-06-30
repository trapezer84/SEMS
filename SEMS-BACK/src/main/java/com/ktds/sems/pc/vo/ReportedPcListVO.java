package com.ktds.sems.pc.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class ReportedPcListVO {

	private Paging paging;
	private List<ReportedPcVO> reportedPcList;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<ReportedPcVO> getReportedPcList() {
		return reportedPcList;
	}

	public void setReportedPcList(List<ReportedPcVO> reportedPcList) {
		this.reportedPcList = reportedPcList;
	}

}
