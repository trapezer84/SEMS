package com.ktds.sems.member.vo;

public class LoginHistoryVO extends MemberVO{

	private int lgiHtrId;
	private String lgiIp;
	private String lgiDt;
	private String lgoDt;
	
	public int getLgiHtrId() {
		return lgiHtrId;
	}
	public void setLgiHtrId(int lgiHtrId) {
		this.lgiHtrId = lgiHtrId;
	}
	public String getLgiIp() {
		return lgiIp;
	}
	public void setLgiIp(String lgiIp) {
		this.lgiIp = lgiIp;
	}
	public String getLgiDt() {
		return lgiDt;
	}
	public void setLgiDt(String lgiDt) {
		this.lgiDt = lgiDt;
	}
	public String getLgoDt() {
		return lgoDt;
	}
	public void setLgoDt(String lgoDt) {
		this.lgoDt = lgoDt;
	}
}
