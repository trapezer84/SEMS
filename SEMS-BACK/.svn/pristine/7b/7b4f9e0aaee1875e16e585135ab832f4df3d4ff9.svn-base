package com.ktds.sems.member.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class PersonalInfoReadVO {
	
	private String id;
	
	@NotEmpty(message = "열람 신청 아이디는 필수값입니다.")
	private String memberId;
	
	@NotEmpty(message = "열람 대상 아이디는 필수값입니다.")
	private String targetMemberId;
	private String readDate;
	
	@NotEmpty(message = "열람 사유는 필수값입니다.")
	private String description;
	private String memberType;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTargetMemberId() {
		return targetMemberId;
	}
	public void setTargetMemberId(String targetMemberId) {
		this.targetMemberId = targetMemberId;
	}
	public String getReadDate() {
		return readDate;
	}
	public void setReadDate(String readDate) {
		this.readDate = readDate;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
}
