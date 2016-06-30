package com.ktds.sems.member.vo;

public class LoginHistoryVO extends MemberVO{

	private int lgiHtrId;
	private String lgiIp;
	private String lgiDt;
	private String lgoDt;
	private String isReq;
	private int chkCnt;
	
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
	public String getIsReq() {
		return isReq;
	}
	public void setIsReq(String isReq) {
		this.isReq = isReq;
	}
	public int getChkCnt() {
		return chkCnt;
	}
	public void setChkCnt(int chkCnt) {
		this.chkCnt = chkCnt;
	}
		
}
