package com.ktds.sems.education.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class EducationHistoryListVO {

	private List<EducationHistoryVO> educationHistoryList;
	private Paging paging;
	
	private List<EducationStateVO> statList;
	private List<EducationCostVO> costList;
	

	public List<EducationStateVO> getStatList() {
		return statList;
	}

	public void setStatList(List<EducationStateVO> statList) {
		this.statList = statList;
	}

	public List<EducationCostVO> getCostList() {
		return costList;
	}

	public void setCostList(List<EducationCostVO> costList) {
		this.costList = costList;
	}

	public List<EducationHistoryVO> getEducationHistoryList() {
		return educationHistoryList;
	}

	public void setEducationHistoryList(List<EducationHistoryVO> educationHistoryList) {
		this.educationHistoryList = educationHistoryList;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

}
