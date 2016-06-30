package com.ktds.sems.member.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class MemberListVO {
	
	private List<MemberVO> memberList;
	private Paging paging;
	
	public List<MemberVO> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberVO> memberList) {
		this.memberList = memberList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
}
