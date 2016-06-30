package com.ktds.sems.education.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class EducationReportListVO {

	private List<EducationReportVO> educationReportList;
	private Paging paging;
	
	public List<EducationReportVO> getEducationReportList() {
		return educationReportList;
	}
	public void setEducationReportList(List<EducationReportVO> educationReportList) {
		this.educationReportList = educationReportList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
	
}
