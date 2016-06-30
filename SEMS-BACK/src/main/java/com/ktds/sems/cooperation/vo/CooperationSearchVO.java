package com.ktds.sems.cooperation.vo;

public class CooperationSearchVO {

	private int pageNo;
	private int startIndex;
	private int endIndex;
	
	private String searchType;
	private String searchKeyword;
	private String searchTypeName;
	
	
	public String getSearchTypeName() {
		return searchTypeName;
	}
	public void setSearchTypeName(String searchTypeName) {
		this.searchTypeName = searchTypeName;
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
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
}
