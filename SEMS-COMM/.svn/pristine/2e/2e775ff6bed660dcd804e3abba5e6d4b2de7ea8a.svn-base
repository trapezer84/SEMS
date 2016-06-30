package com.ktds.sems.education.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CategoryVO {
	
	private String parentCategoryId;
	private String categoryType;

	@NotEmpty(message="카테고리 아이디를 작성해 주세요.")
	@Size(min=2, max=4)
	private String categoryId;
	
	@NotEmpty(message="카테고리 이름을 작성해 주세요.")
	@Size(max=30)
	private String categoryName;

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	public String getCategoryType() {
		return categoryType;
	}
	
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
