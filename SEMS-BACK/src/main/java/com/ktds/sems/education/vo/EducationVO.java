package com.ktds.sems.education.vo;

import org.hibernate.validator.constraints.NotEmpty;

import com.ktds.sems.file.vo.FileVO;

public class EducationVO extends FileVO{
	
	private String educationId;
	
	@NotEmpty(message="교육 분야을 선택 해주세요!")
	private String educationCategory;
	
	@NotEmpty(message="교육 명을 입력 해주세요!")
	private String educationTitle;
	
	@NotEmpty(message="강사 명을 선택 해주세요!")
	private String memberId;
	
	private int maxMember;
	
	@NotEmpty(message="강의실 명을 입력 해주세요!")
	private String educationLocation;
	
	@NotEmpty(message="커리큘럼을 입력 해주세요!")
	private String educationCurriculum;
	
	@NotEmpty(message="강의소개를 입력 해주세요!")
	private String educationIntroduce;
	
	@NotEmpty(message="강의시작을 선택 해주세요!")
	private String startDate;
	@NotEmpty(message="강의 종료을 선택 해주세요!")
	private String endDate;
	
	@NotEmpty(message="강의 시작 시간을 선택 해주세요!")
	private String startTime;
	@NotEmpty(message="강의 종료 시간을 선택 해주세요!")
	private String endTime;
	
	@NotEmpty(message="강의 형태를 선택 해주세요!")
	private String educationType;
	
	@NotEmpty(message="강의 비용를 선택 해주세요!")
	private String cost;
	
	private String salt; 
	
	public String getEducationId() {
		return educationId;
	}
	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}
	public String getEducationCategory() {
		return educationCategory;
	}
	public void setEducationCategory(String educationCategory) {
		this.educationCategory = educationCategory;
	}
	public String getEducationTitle() {
		return educationTitle;
	}
	public void setEducationTitle(String educationTitle) {
		this.educationTitle = educationTitle;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getMaxMember() {
		return maxMember;
	}
	public void setMaxMember(int maxMember) {
		this.maxMember = maxMember;
	}
	public String getEducationLocation() {
		return educationLocation;
	}
	public void setEducationLocation(String educationLocation) {
		this.educationLocation = educationLocation;
	}
	public String getEducationCurriculum() {
		return educationCurriculum;
	}
	public void setEducationCurriculum(String educationCurriculum) {
		this.educationCurriculum = educationCurriculum;
	}
	public String getEducationIntroduce() {
		return educationIntroduce;
	}
	public void setEducationIntroduce(String educationIntroduce) {
		this.educationIntroduce = educationIntroduce;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEducationType() {
		return educationType;
	}
	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
