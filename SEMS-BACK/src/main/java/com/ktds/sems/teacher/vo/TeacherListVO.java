package com.ktds.sems.teacher.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class TeacherListVO {

	private Paging paging;
	private List<TeacherVO> teacherList;

	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	public List<TeacherVO> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<TeacherVO> teacherList) {
		this.teacherList = teacherList;
	}
	
}
