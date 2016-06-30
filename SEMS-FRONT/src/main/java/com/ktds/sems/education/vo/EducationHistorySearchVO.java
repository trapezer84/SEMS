package com.ktds.sems.education.vo;

public class EducationHistorySearchVO {

	private int pageNo;
	private int startIndex;
	private int endIndex;
	private String memberId;
	
	private String searchType;
	private String searchEduName;
	private String searchCost;
	private String searchApplyState;
	private String startDate;
	private String endDate;
	private String searchKeyword;
	private String searchDate;
	

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSearchEduName() {
		return searchEduName;
	}

	public void setSearchEduName(String searchEduName) {
		this.searchEduName = searchEduName;
	}

	public String getSearchCost() {
		return searchCost;
	}

	public void setSearchCost(String searchCost) {
		this.searchCost = searchCost;
	}

	public String getSearchApplyState() {
		return searchApplyState;
	}

	public void setSearchApplyState(String searchApplyState) {
		this.searchApplyState = searchApplyState;
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
