package com.ktds.sems.teacher.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherListVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;
import com.ktds.sems.validator.teacher.TeacherVOValidator;

@Transactional
public class TeacherServiceTest extends SemsTestCase{
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherDAO teacherDAO;

	@Test
	public void viewDetailTest(){
		String memberId = "teacher01";
		ModelAndView view = teacherService.viewDetail(memberId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "teacher/detail");
	}

	@Test
	public void getAllTeaacherListTest(){
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		int pageNo = 0;

		
		ModelAndView view = teacherService.getAllTeaacherList(pageNo, request);
		assertNotNull(view);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "teacher/teaacherList");
			
			TeacherSearchVO searchVO = (TeacherSearchVO) view.getModelMap().get("searchVO");
			assertNotNull(searchVO);
			
			TeacherListVO searchedListVO = (TeacherListVO) view.getModelMap().get("searchedListVO");
			assertNotNull(searchedListVO);
		}
		else{
			fail("Fail...");
		}
		
	}
	
	@Test
	public void doDeleteTeacherTest(){
		
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setMemberId("junit");
		teacherVO.setCompanyName("junit");
		teacherVO.setBusinessNumber("1234");
		teacherVO.setAnnual(1);
		 int isResult = teacherDAO.doInsertNewTeacher(teacherVO);
		
		EducationHistoryVO teacherEduHistoryVO = new EducationHistoryVO();
		teacherEduHistoryVO.setId("TEH-20160519-000001");
		teacherEduHistoryVO.setMemberId("junit");
		teacherEduHistoryVO.setStartDate("r");
		teacherEduHistoryVO.setEndDate("r");
		teacherEduHistoryVO.setEducationName("r");
		teacherEduHistoryVO.setEducationLocation("r");
		int isResult2 = teacherDAO.doInsertTeacherEduHis(teacherEduHistoryVO);
		
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000001");
		projectHistoryVO.setMemberId("junit");
		projectHistoryVO.setStartDate("d");
		projectHistoryVO.setEndDate("d");
		projectHistoryVO.setProjectName("d");
		projectHistoryVO.setProjectLocation("d");
		int isResult3 = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
		
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		teacherBookVO.setId("TB-20160519-000001");
		teacherBookVO.setMemberId("junit");
		teacherBookVO.setBookName("d");
		teacherBookVO.setBookCompany("d");
		int isResult4 = teacherDAO.doInsertTeacherBookHis(teacherBookVO);
		
		ModelAndView view = teacherService.doDeleteTeacher(teacherVO.getMemberId());
		assertNotNull(view);
		assertEquals(view.getViewName(), "teacher/teaacherList");
	}
	
	@Test
	public void massiveDeleteTeacherTest(){
		
		String[] deleteTeacherIds = {"teacher03", "teacher02"};
		String result = teacherService.massiveDeleteTeacher(deleteTeacherIds);
		assertEquals(result, "redirect:/teacher/teaacherList");
	}
	/*
	 * 윤후
	 */
	@Test
	public void doTeacherInfoModifyActionTest(){
		
		// 1. TeacherVO
		TeacherVO teacherVO = new TeacherVO();
		
		teacherVO.setAnnual(1); 
		teacherVO.setMemberId("teacher01");
		teacherVO.setBusinessNumber("JUNIT...TT^TT");
		teacherVO.setCompanyName("JUNIT...TT^TT");
		teacherVO.setName("JUNIT...TT^TT");
		
		List<TeacherBookVO> teacherBookList = new ArrayList<TeacherBookVO>();
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		teacherBookVO.setId("TB-20160519-000028");
		teacherBookList.add(teacherBookVO);
		teacherVO.setTeacherBookList(teacherBookList);
		
		List<ProjectHistoryVO> projectHistoryList = new ArrayList<ProjectHistoryVO>();
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000017");
		projectHistoryList.add(projectHistoryVO);
		teacherVO.setProjectHistoryList(projectHistoryList);
		
		List<EducationHistoryVO> educationHistoryList = new ArrayList<EducationHistoryVO>();
		EducationHistoryVO educationHistoryVO = new EducationHistoryVO();
		educationHistoryVO.setId("TEH-20160519-000016");
		educationHistoryList.add(educationHistoryVO);
		teacherVO.setEducationHistoryList(educationHistoryList);
		
		// 2. Errors
		BindingResult errors = new BeanPropertyBindingResult(teacherVO, "teacherUpdateForm");
		TeacherVOValidator validator = new TeacherVOValidator();
		validator.validate(teacherVO, errors);
		
		// 3. Session
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER_TYPE, "ADM");
		
		// 파라미터 3개 확인
		ModelAndView view = teacherService.doTeacherInfoModifyAction(teacherVO, errors, session);
		
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/teacherModify/" + teacherVO.getMemberId());
		} else {
			fail("fail");
		}
	}
	/*
	 * 윤후
	 */
	@Test
	public void deleteTeacherBookEduProHistoryTest(){
		
		String memberId = "teacher01";
		String id = "TB-20160520-000052";
		String type = "book";
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER_TYPE, "ADM");
		
		ModelAndView view  = teacherService.deleteTeacherBookEduProHistory(id, type, memberId, session);
		assertNotNull(view);
	}
	
	/*
	 * 윤후
	 */
	@Test
	public void insertNewTeacherTest(){
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER_TYPE, "ADM");
	
		ModelAndView view = teacherService.insertNewTeacher(session);
		if ( view != null) {
			String viewName = view.getViewName();
			assertNotNull(view);
			assertEquals(viewName,"teacher/teacherInsert");
		} else {
			fail("fail");
		}
	}
	/*
	 * 윤후
	 */
	@Test
	public void doInsertNewTeacherTest(){
		// 1. TeacherVO
		TeacherVO teacherVO = new TeacherVO();
		
		teacherVO.setMemberId("testTeacher01");
		teacherVO.setCompanyName("JUNIT...TT^TT");
		teacherVO.setBusinessNumber("JUNIT...TT^TT");
		teacherVO.setAnnual(1); 
		
		List<TeacherBookVO> teacherBookList = new ArrayList<TeacherBookVO>();
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		teacherBookVO.setId("testBook01");
		teacherBookVO.setMemberId("testTeacher01");
		teacherBookVO.setBookName("test");
		teacherBookVO.setBookCompany("test");
		teacherBookList.add(teacherBookVO);
		teacherVO.setTeacherBookList(teacherBookList);
		
		List<ProjectHistoryVO> projectHistoryList = new ArrayList<ProjectHistoryVO>();
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("testProjectHistory01");
		projectHistoryVO.setMemberId("testTeacher01");
		projectHistoryVO.setStartDate("today");
		projectHistoryVO.setEndDate("today");
		projectHistoryVO.setProjectName("test");
		projectHistoryVO.setProjectLocation("test");
		projectHistoryList.add(projectHistoryVO);
		teacherVO.setProjectHistoryList(projectHistoryList);
		
		List<EducationHistoryVO> educationHistoryList = new ArrayList<EducationHistoryVO>();
		EducationHistoryVO educationHistoryVO = new EducationHistoryVO();
		educationHistoryVO.setId("testEducationHistory01");
		educationHistoryVO.setMemberId("testTeacher01");
		educationHistoryVO.setStartDate("today");
		educationHistoryVO.setEndDate("today");
		educationHistoryVO.setEducationName("test");
		educationHistoryVO.setEducationLocation("test");
		educationHistoryList.add(educationHistoryVO);
		teacherVO.setEducationHistoryList(educationHistoryList);
		
		// 2. Errors
		BindingResult errors = new BeanPropertyBindingResult(teacherVO, "teacherUpdateForm");
		TeacherVOValidator validator = new TeacherVOValidator();
		validator.validate(teacherVO, errors);
		
		// 3. Session
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER_TYPE, "ADM");
		
		// 파라미터 3개 확인
		ModelAndView view = teacherService.doInsertNewTeacher(teacherVO, errors, session);
		
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/teacherModify/" + teacherVO.getMemberId());
		} else {
			fail("fail");
		}
	}
	/*
	 * 윤후
	 */
	@Test
	public void getOneTeacherInfoForUpdateTest(){
		String memberId = "teacher01";
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER_TYPE, "ADM");
		
		ModelAndView view = teacherService.getOneTeacherInfoForUpdate(memberId, session);
		
		if ( view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "teacher/teacherUpdate");
		} else{
			fail("fail");
		}
	}
}

