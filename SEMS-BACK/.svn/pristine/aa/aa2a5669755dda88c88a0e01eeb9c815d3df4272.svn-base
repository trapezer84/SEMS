package com.ktds.sems.teacher.service;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.teacher.vo.TeacherVO;

public interface TeacherService {
	
	
	public ModelAndView viewDetail(String memberId);
	
	public ModelAndView getAllTeaacherList(int pageNo, HttpServletRequest request);

	public ModelAndView doDeleteTeacher(String memberId);

	public String massiveDeleteTeacher(String[] deleteTeacherIds);
	
	public ModelAndView getOneTeacherInfoForUpdate(String memberId, HttpSession session);

	public ModelAndView doTeacherInfoModifyAction(TeacherVO teacherVO, Errors errors, HttpSession session);

	public ModelAndView deleteTeacherBookEduProHistory(String id, String type, String memberId, HttpSession session);

	public ModelAndView insertNewTeacher(HttpSession session);

	public ModelAndView doInsertNewTeacher(TeacherVO teacherVO, Errors errors, HttpSession session);

	


}
