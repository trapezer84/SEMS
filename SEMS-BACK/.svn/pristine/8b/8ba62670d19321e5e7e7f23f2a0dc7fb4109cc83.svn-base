package com.ktds.sems.teacher.web;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.service.TeacherService;
import com.ktds.sems.teacher.vo.TeacherVO;

@Controller
public class TeacherController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherService teacherService;
	
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@RequestMapping("/teacher/detail/{memberId}")
	public ModelAndView viewDetailPage(@PathVariable String memberId){
		return teacherService.viewDetail(memberId);
	}
	
	@RequestMapping("/teacher/teaacherList")
	public ModelAndView viewteaacherListPage(@RequestParam(required=false, defaultValue="0") int pageNo, HttpServletRequest request){
		return teacherService.getAllTeaacherList(pageNo, request);
	}
	
	@RequestMapping("/teacherDelete/{memberId}")
	public ModelAndView doDeleteTeacher(@PathVariable String memberId){
		return teacherService.doDeleteTeacher(memberId);
	}

	@RequestMapping("/massiveDeleteTeacher")
	public String massiveDeleteTeacher(HttpServletRequest request){
		String[] deleteTeacherIds = request.getParameterValues("deleteTeacherId");
		for (String string : deleteTeacherIds) {
			logger.info("Controller로 넘어온 ID : " + string);
		}
		return teacherService.massiveDeleteTeacher(deleteTeacherIds);
	}
	
	@RequestMapping("/teacherModify/{memberId}")
	public ModelAndView viewTeacherModifyPage(@PathVariable String memberId, HttpSession session){
		ModelAndView view = teacherService.getOneTeacherInfoForUpdate(memberId, session); 
		return view;
	}

	@RequestMapping("/doTeacherInfoModifyAction")
	public ModelAndView doTeacherInfoModifyAction(@Valid TeacherVO teacherVO, Errors errors, HttpSession session){
		return teacherService.doTeacherInfoModifyAction(teacherVO, errors, session);
	}
	
	@RequestMapping("/deleteTeacherBookEduProHistory/{id}/{type}/{memberId}")
	public ModelAndView deleteTeacherBookEduProHistory(@PathVariable String id, @PathVariable String type, @PathVariable String memberId, HttpSession session){
		return teacherService.deleteTeacherBookEduProHistory(id, type, memberId, session);
	}
	
	@RequestMapping("/insertNewTeacher")
	public ModelAndView insertNewTeacher(HttpSession session){
		return teacherService.insertNewTeacher(session);
	}
	
	@RequestMapping("/doInsertNewTeacher")
	public ModelAndView doInsertNewTeacher(@Valid TeacherVO teacherVO, Errors errors, HttpSession session){
		return teacherService.doInsertNewTeacher(teacherVO, errors, session);
	}
}
