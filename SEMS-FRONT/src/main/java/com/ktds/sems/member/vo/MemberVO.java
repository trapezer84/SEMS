package com.ktds.sems.member.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class MemberVO {

	@NotEmpty(message = "필수 정보입니다.")
	private String id;

	@NotEmpty(message = "필수 정보입니다.")
	private String password;
	
	private String prevPassword;

	@NotEmpty(message = "필수 정보입니다.")
	private String name;

	@NotEmpty(message = "필수 정보입니다.")
	@Email
	private String email;
	
	private String highestEducationLevel;

	private String universityName;

	private String majorName;

	private String graduationType;

	@NotEmpty(message = "필수 정보입니다.")
	private String birthDate;

	@NotEmpty(message = "필수 정보입니다.")
	private String phoneNumber;

	@NotEmpty(message = "필수 정보입니다.")
	private String memberType;

	private String salt;
	private int loginFailCount;
	private String isAccountLock;
	private String latestLoginDate;
	private String resignDate;
	private String isResign;
	private int modifyFailCount;
	private String isModifyLock;
	private String uuid;
	
	public String getPrevPassword() {
		return prevPassword;
	}

	public void setPrevPassword(String prevPassword) {
		this.prevPassword = prevPassword;
	}

	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}

	public String getIsResign() {
		return isResign;
	}

	public void setIsResign(String isResign) {
		this.isResign = isResign;
	}

	public int getModifyFailCount() {
		return modifyFailCount;
	}

	public void setModifyFailCount(int modifyFailCount) {
		this.modifyFailCount = modifyFailCount;
	}

	public String getIsModifyLock() {
		return isModifyLock;
	}

	public void setIsModifyLock(String isModifyLock) {
		this.isModifyLock = isModifyLock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHighestEducationLevel() {
		return highestEducationLevel;
	}

	public void setHighestEducationLevel(String highestEducationLevel) {
		this.highestEducationLevel = highestEducationLevel;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getGraduationType() {
		return graduationType;
	}

	public void setGraduationType(String graduationType) {
		this.graduationType = graduationType;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public String getIsAccountLock() {
		return isAccountLock;
	}

	public void setIsAccountLock(String isAccountLock) {
		this.isAccountLock = isAccountLock;
	}

	public String getLatestLoginDate() {
		return latestLoginDate;
	}

	public void setLatestLoginDate(String latestLoginDate) {
		this.latestLoginDate = latestLoginDate;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
