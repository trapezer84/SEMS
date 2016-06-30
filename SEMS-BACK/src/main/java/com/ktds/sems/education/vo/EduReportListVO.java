package com.ktds.sems.education.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class EduReportListVO {
	
	private Paging paging;
	private List<EduReportVO> eduReportList;
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public List<EduReportVO> getEduReportList() {
		return eduReportList;
	}
	public void setEduReportList(List<EduReportVO> eduReportList) {
		this.eduReportList = eduReportList;
	}
	
}
