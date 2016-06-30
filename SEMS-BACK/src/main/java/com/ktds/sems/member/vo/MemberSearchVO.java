package com.ktds.sems.member.vo;

public class MemberSearchVO {

	private int pageNo;
	private int startIndex;
	private int endIndex;
	
	private String searchKeyword;
	private String searchType;
	private String connLock;
	private String isRgsn;
	private String modLock;
	private String optionSelected;
	
	public String getConnLock() {
		return connLock;
	}
	public void setConnLock(String connLock) {
		this.connLock = connLock;
	}
	public String getIsRgsn() {
		return isRgsn;
	}
	public void setIsRgsn(String isRgsn) {
		this.isRgsn = isRgsn;
	}
	public String getModLock() {
		return modLock;
	}
	public void setModLock(String modLock) {
		this.modLock = modLock;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
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
	public String getOptionSelected() {
		return optionSelected;
	}
	public void setOptionSelected(String optionSelected) {
		this.optionSelected = optionSelected;
	}
	
	
	
}
