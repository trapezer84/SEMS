package com.ktds.sems.education.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class EducationQNABBSVO {
	
	private String atcId;
	private String eduId;
	private String mbrId;
	private String isDelete;
	
	@NotEmpty(message = "필수 정보 입니다.")
	private String title;
	
	@NotEmpty(message = "필수 정보 입니다.")
	private String contents;
	
	private String createDate;
	private String modifyDate;
	private int hits;
	
	public String getAtcId() {
		return atcId;
	}
	public void setAtcId(String atcId) {
		this.atcId = atcId;
	}
	public String getEduId() {
		return eduId;
	}
	public void setEduId(String eduId) {
		this.eduId = eduId;
	}
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	
	
	
	
}
