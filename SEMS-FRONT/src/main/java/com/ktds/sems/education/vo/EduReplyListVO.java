package com.ktds.sems.education.vo;

import java.util.ArrayList;
import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class EduReplyListVO {
	private List<QNAVO> qnaList;
	private Paging paging;

	public List<QNAVO> getQnaList() {
		List<QNAVO> qnas = new ArrayList<QNAVO>();
		qnas.addAll(qnaList);
		return qnas;
		
	}
	public void setQnaList(List<QNAVO> qnaList) {
		List<QNAVO> qnas = new ArrayList<QNAVO>();
		qnas.addAll(qnaList);
		this.qnaList = qnas;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
