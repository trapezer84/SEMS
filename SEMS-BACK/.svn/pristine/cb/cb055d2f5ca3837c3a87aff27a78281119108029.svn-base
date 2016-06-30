package com.ktds.sems.teacher.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.biz.TeacherBiz;
import com.ktds.sems.teacher.service.TeacherService;
import com.ktds.sems.teacher.vo.TeacherListVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

import kr.co.hucloud.utilities.web.Paging;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;

public class TeacherServiceImpl implements TeacherService {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherBiz teacherBiz;

	public void setTeacherBiz(TeacherBiz teacherBiz) {
		this.teacherBiz = teacherBiz;
	}

	@Override
	public ModelAndView viewDetail(String memberId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("teacher/detail");
		TeacherVO teacherVO = teacherBiz.getTeacherInfo(memberId);
		if ( teacherVO == null ) {
			throw new RuntimeException("존재하지 않는 강사입니다.");
		}
		view.addObject("teacherInfo", teacherVO);
		view.addObject("teacherEducationHistory", teacherBiz.getTeacherEducationHistory(memberId));
		view.addObject("teacherProjectHistory", teacherBiz.getTeacherProjectHistory(memberId));
		view.addObject("teacherBook", teacherBiz.getTeacherBook(memberId));
		view.addObject("educationHistory", teacherBiz.getEducationHistory(memberId));
		view.addObject("teacherEducationGrade", teacherBiz.getTeacherEducationGrade(memberId));

		return view;
	}

	@Override
	public ModelAndView getAllTeaacherList(int pageNo, HttpServletRequest request) {

		MockHttpSession session = new MockHttpSession();
		TeacherListVO searchedListVO = new TeacherListVO();
		Paging paging = new Paging();

		searchedListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");

		int totalTeacherCount = teacherBiz.getTotalTeacherCount(request);
		paging.setTotalArticleCount(totalTeacherCount);

		TeacherSearchVO searchVO = new TeacherSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		if (request.getParameter("searchKeyword") != null) {
			searchVO.setPageNo(pageNo);
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
			searchVO.setSearchType(request.getParameter("searchType"));
		} else {
			searchVO = (TeacherSearchVO) session.getAttribute("_SEARCH_");
			searchVO = new TeacherSearchVO();
			searchVO.setStartIndex(paging.getStartArticleNumber());
			searchVO.setEndIndex(paging.getEndArticleNumber());
			searchVO.setPageNo(0);
			searchVO.setSearchKeyword("");
			searchVO.setSearchType("1");
		}

		session.setAttribute("_SEARCH_", searchVO);

		List<TeacherVO> teacherList = teacherBiz.getAllTeacher(searchVO);
		searchedListVO.setTeacherList(teacherList);

		ModelAndView view = new ModelAndView();
		view.setViewName("teacher/teaacherList");
		view.addObject("searchedListVO", searchedListVO);
		view.addObject("searchVO", searchVO);

		return view;
	}

	@Override
	public ModelAndView doDeleteTeacher(String memberId) {

		ModelAndView view = new ModelAndView();

		if (memberId == null || memberId.length() == 0) {
			view.setViewName("redirect:/teacher/teaacherList");
		} else {
			boolean deleteResult = teacherBiz.doDeleteTeacher(memberId);
			
			if (teacherBiz.getTeacherEducationHistory(memberId) != null) {
				teacherBiz.doDeleteEducationHistory(memberId);
			}
			if (teacherBiz.getTeacherProjectHistory(memberId) != null){
				teacherBiz.doDeleteProjectHistory(memberId);
			}
			if (teacherBiz.getTeacherBook(memberId) != null){
				teacherBiz.doDeleteTeacherBook(memberId);
			}

			if (!deleteResult) {
				view.addObject("massage", "삭제 실패!");
				view.setViewName("/teacher/detail/" + memberId);
			} else {
				view.addObject("massage", "삭제 성공!");
				view.setViewName("teacher/teaacherList");
			}
		}
		return view;
	}

	@Override
	public String massiveDeleteTeacher(String[] deleteTeacherIds) {
		
		if ( deleteTeacherIds != null || deleteTeacherIds.length > 0 ){
			
			for(String memberId : deleteTeacherIds){
				
				teacherBiz.doDeleteTeacher(memberId);
				teacherBiz.doDeleteProjectHistory(memberId);
				teacherBiz.doDeleteEducationHistory(memberId);
				teacherBiz.doDeleteTeacherBook(memberId);
			}
		}
		return "redirect:/teacher/teaacherList";
		
	}

	@Override
	public ModelAndView getOneTeacherInfoForUpdate(String memberId, HttpSession session) {
		ModelAndView view = new ModelAndView();

		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		if (memberType.equals("ADM")) {
			TeacherVO teacherVO = teacherBiz.getOneTeacherInfo(memberId);

			List<TeacherBookVO> teacherBookVO = teacherBiz.getOneTeacherBookInfo(memberId);
			List<ProjectHistoryVO> projectHistoryVO = teacherBiz.getOneTeacherProjectHistoryVO(memberId);
			List<EducationHistoryVO> educationHistoryVO = teacherBiz.getOneEducationHistoryVO(memberId);

			view.addObject("teacherVO", teacherVO);
			view.addObject("teacherBookVO", teacherBookVO);
			view.addObject("projectHistoryVO", projectHistoryVO);
			view.addObject("educationHistoryVO", educationHistoryVO);

			view.setViewName("teacher/teacherUpdate");
		} else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}

		return view;
	}

	@Override
	public ModelAndView doTeacherInfoModifyAction(TeacherVO teacherVO, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();

		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		if (memberType.equals("ADM")) {
			if (errors.hasErrors()) {
				view.setViewName("teacher/teacherUpdate" + "/" + teacherVO.getMemberId());
				view.addObject("teacherVO", teacherVO);
				view.addObject("teacherBookVO", teacherBiz.getOneTeacherBookInfo(teacherVO.getMemberId()));
				view.addObject("projectHistoryVO", teacherBiz.getOneTeacherProjectHistoryVO(teacherVO.getMemberId()));
				view.addObject("educationHistoryVO", teacherBiz.getOneEducationHistoryVO(teacherVO.getMemberId()));
				return view;
			} else {
				boolean result = teacherBiz.doTeacherInfoModifyAction(teacherVO);
				if (result) {
					view.setViewName("redirect:/teacherModify/" + teacherVO.getMemberId());
				} else {
					throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
				}
			}
		} else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}

	@Override
	public ModelAndView deleteTeacherBookEduProHistory(String id, String type, String memberId, HttpSession session) {
		ModelAndView view = new ModelAndView();

		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);

		if (memberType.equals("ADM")) {
			boolean result = teacherBiz.deleteTeacherBookEduProHistory(id, type);
			view.setViewName("redirect:/teacherModify/" + memberId);
		} else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}

	@Override
	public ModelAndView insertNewTeacher(HttpSession session) {
		ModelAndView view = new ModelAndView();
		List<MemberVO> memberVO = teacherBiz.getTeacherMemberInfo();
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		
		if ( memberType.equals("ADM") ) {
			view.addObject("memberVO", memberVO);
			view.setViewName("teacher/teacherInsert");
		}else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}

	@Override
	public ModelAndView doInsertNewTeacher(TeacherVO teacherVO, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();

		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		if ( memberType.equals("ADM") ) {
			if ( errors.hasErrors() ) {
				view.setViewName("teacher/teacherInsert"+"/"+teacherVO.getMemberId());
				view.addObject("teacherVO", teacherVO);
				return view;
			}
			else{
				boolean result = teacherBiz.doInsertNewTeacher(teacherVO);
				if ( result) {
					view.setViewName("redirect:/teacherModify/" + teacherVO.getMemberId());
				}
				else {
					throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
				}
			}
		}
		else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}

}
