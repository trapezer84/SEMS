package com.ktds.sems.teacher.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

public class TeacherVO{

	private String memberId;
	private String name;
	private String companyName;
	private String businessNumber;
	
	private List<TeacherBookVO> teacherBookList;
	private List<ProjectHistoryVO> projectHistoryList;
	private List<EducationHistoryVO> educationHistoryList;

	@NotNull(message="연차를 입력 해주세요!")
	private int annual;
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public int getAnnual() {
		return annual;
	}
	public void setAnnual(int annual) {
		this.annual = annual;
	}
	public List<TeacherBookVO> getTeacherBookList() {
		return teacherBookList;
	}
	public void setTeacherBookList(List<TeacherBookVO> teacherBookList) {
		this.teacherBookList = teacherBookList;
	}
	public List<ProjectHistoryVO> getProjectHistoryList() {
		return projectHistoryList;
	}
	public void setProjectHistoryList(List<ProjectHistoryVO> projectHistoryList) {
		this.projectHistoryList = projectHistoryList;
	}
	public List<EducationHistoryVO> getEducationHistoryList() {
		return educationHistoryList;
	}
	public void setEducationHistoryList(List<EducationHistoryVO> educationHistoryList) {
		this.educationHistoryList = educationHistoryList;
	}
	
	
}
