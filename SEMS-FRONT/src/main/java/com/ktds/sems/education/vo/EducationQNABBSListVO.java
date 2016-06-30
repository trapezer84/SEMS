package com.ktds.sems.education.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class EducationQNABBSListVO {
	
	private List<EducationQNABBSVO> educationQnaBbsList;
	private Paging paging;
	
	public List<EducationQNABBSVO> getEducationQnaBbsList() {
		return educationQnaBbsList;
	}
	public void setEducationQnaBbsList(List<EducationQNABBSVO> educationQnaBbsList) {
		this.educationQnaBbsList = educationQnaBbsList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
	
}
