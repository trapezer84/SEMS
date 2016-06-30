package com.ktds.sems.education.vo;

public class ReportReplySearchVO extends ReportReplyVO {

	private int pageNo;
	private int startIndex;
	private int endIndex;

	private String eduNameKeyword;
	private String reportNameKeyword;
	private String startDate;
	private String endDate;
	
	private String memberNameKeyword;
	private String teamNameKeyword;
	
	public String getMemberNameKeyword() {
		return memberNameKeyword;
	}
	public void setMemberNameKeyword(String memberNameKeyword) {
		this.memberNameKeyword = memberNameKeyword;
	}
	public String getTeamNameKeyword() {
		return teamNameKeyword;
	}
	public void setTeamNameKeyword(String teamNameKeyword) {
		this.teamNameKeyword = teamNameKeyword;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getEduNameKeyword() {
		return eduNameKeyword;
	}
	public void setEduNameKeyword(String eduNameKeyword) {
		this.eduNameKeyword = eduNameKeyword;
	}
	public String getReportNameKeyword() {
		return reportNameKeyword;
	}
	public void setReportNameKeyword(String reportNameKeyword) {
		this.reportNameKeyword = reportNameKeyword;
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
	
	
	
}
