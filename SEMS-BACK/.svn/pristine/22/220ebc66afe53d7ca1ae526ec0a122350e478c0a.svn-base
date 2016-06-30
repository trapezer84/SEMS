package com.ktds.sems.teacher.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

@Transactional
public class TeacherBizTest extends SemsTestCase{

	@Autowired
	private TeacherBiz teacherBiz;
	
	@Autowired
	private TeacherDAO teacherDAO;
	
	@Test
	public void getTeacherInfoTest(){
		String memberId = "teacher01";
		TeacherVO teacherVO = teacherBiz.getTeacherInfo(memberId);
		assertNotNull(teacherVO);
	}
	
	@Test
	public void getTeacherEducationHistoryTest(){
		String memberId = "teacher01";
		List<EducationHistoryVO> teacherEducationHistories = teacherBiz.getTeacherEducationHistory(memberId);
		assertNotNull(teacherEducationHistories);
	}
	
	@Test
	public void getTeacherProjectHistoryTest(){
		String memberId = "teacher01";
		List<ProjectHistoryVO> teacherProjectHistories = teacherBiz.getTeacherProjectHistory(memberId);
		assertNotNull(teacherProjectHistories);
	}
	
	@Test
	public void getTeacherBookTest(){
		String memberId = "teacher01";
		List<TeacherBookVO> teacherBooks = teacherBiz.getTeacherBook(memberId);
		assertNotNull(teacherBooks);
	}
	
	@Test
	public void getEducationHistoryTest(){
		String memberId = "teacher01";
		List<EducationVO> educationHistories = teacherBiz.getEducationHistory(memberId);
		assertNotNull(educationHistories);
	}
	
	@Test
	public void getTeacherEducationGradeTest(){
		String memberId = "teacher01";
		double grade = teacherBiz.getTeacherEducationGrade(memberId);
		assertNotNull(grade);
	}
	
	@Test
	public void getTotalTeacherCountTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		String searchType = "";
		String searchKeyword ="";
		
		Map<String,String> searchInfo = new HashMap<String,String>();
		searchInfo.put("searchType", searchType);
		searchInfo.put("searchKeyword", searchKeyword);
		
		int totalCount = teacherBiz.getTotalTeacherCount(request);
		assertTrue(totalCount >= 0);
	}
	@Test
	public void getAllTeacherTest() {
		TeacherSearchVO searchVO = new TeacherSearchVO();
		searchVO.setSearchKeyword("");
		searchVO.setSearchType("");
		searchVO.setMemberId("");
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		
		List<TeacherVO> techerList = teacherBiz.getAllTeacher(searchVO);
		assertNotNull(techerList);
		if (techerList != null){
			assertTrue(techerList.size() >0);
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
		int isInsert = teacherDAO.doInsertNewTeacher(teacherVO);

		if (isInsert > 0) {
			boolean result = teacherBiz.doDeleteTeacher(teacherVO.getMemberId());
			assertTrue(result);
		}
			
	}
	
	
	@Test
	public void doDeleteEducationHistoryTest(){
		
		EducationHistoryVO teacherEduHistoryVO = new EducationHistoryVO();
		teacherEduHistoryVO.setId("TEH-20160519-000001");
		teacherEduHistoryVO.setMemberId("junit");
		teacherEduHistoryVO.setStartDate("r");
		teacherEduHistoryVO.setEndDate("r");
		teacherEduHistoryVO.setEducationName("r");
		teacherEduHistoryVO.setEducationLocation("r");
		int isInsert = teacherDAO.doInsertTeacherEduHis(teacherEduHistoryVO);
		
		if ( isInsert > 0){
			boolean result = teacherBiz.doDeleteEducationHistory(teacherEduHistoryVO.getMemberId());
			assertTrue(result);
		}
		
	}
	
	
	
	@Test
	public void doDeleteProjectHistoryTest(){
		
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000001");
		projectHistoryVO.setMemberId("junit");
		projectHistoryVO.setStartDate("d");
		projectHistoryVO.setEndDate("d");
		projectHistoryVO.setProjectName("d");
		projectHistoryVO.setProjectLocation("d");
		int isInsert = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
		
		if ( isInsert > 0 ){
			boolean result = teacherBiz.doDeleteProjectHistory(projectHistoryVO.getMemberId());
			System.out.println(result);
			assertTrue(result);
		}
		
	}
	
	
	
	@Test
	public void doDeleteTeacherBookTest(){
		
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		teacherBookVO.setId("TB-20160519-000001");
		teacherBookVO.setMemberId("junit");
		teacherBookVO.setBookName("d");
		teacherBookVO.setBookCompany("d");
		int isInsert =  teacherDAO.doInsertTeacherBookHis(teacherBookVO);
	
		if ( isInsert > 0){
			boolean result = teacherBiz.doDeleteTeacherBook(teacherBookVO.getMemberId());
			assertTrue(result);
		}

	}
	/*
	 * 윤후
	 */
	@Test
	public void getOneTeacherBookInfoTest() {
		String memberId = "teacher01";
		List<TeacherBookVO> teacherBookList = teacherBiz.getOneTeacherBookInfo(memberId);
		if (teacherBookList != null){
			for (TeacherBookVO teacherBookVO : teacherBookList) {
				assertNotNull(teacherBookVO.getId());
			}
		}
		else{
			fail("Fail...");
		}
	}
	/*
	 * 윤후
	 */
	@Test
	public void getOneTeacherProjectHistoryVOTest(){
		String memberId = "teacher01";
		List<ProjectHistoryVO> projectHistoryList = teacherBiz.getOneTeacherProjectHistoryVO(memberId);
		if (projectHistoryList != null){
			for (ProjectHistoryVO projectHistoryVO : projectHistoryList) {
				assertNotNull(projectHistoryVO.getId());
			}
		}
		else{
			fail("Fail...");
		}
	}
	/*
	 * 윤후
	 */
	@Test
	public void getgetOneEducationHistoryVOTest() {
		String memberId = "teacher01";
		List<EducationHistoryVO> educationHistoryList = teacherBiz.getOneEducationHistoryVO(memberId);
		if(educationHistoryList != null) {
			for (EducationHistoryVO educationHistoryVO : educationHistoryList) {
				assertNotNull(educationHistoryVO.getId());
			}
			
		} else {
			fail("fail");
		}
	}
	/*
	 * 윤후
	 */
	@Test
	public void getTeacherMemberInfo() {
		List<MemberVO> memberList = teacherBiz.getTeacherMemberInfo();
		if(memberList != null) {
			for (MemberVO memberVO : memberList) {
				assertNotNull(memberVO.getId());
			}
			
		} else {
			fail("fail");
		}
	} 
	/*
	 * 윤후
	 */
	@Test
	public void doInsertNewTeacherTest() {
		// 1. TeacherVO
		TeacherVO teacherVO = new TeacherVO();
		
		teacherVO.setAnnual(1);
		teacherVO.setMemberId("teacher05");
		teacherVO.setBusinessNumber("JUNIT");
		teacherVO.setCompanyName("JUNIT");
		
		List<TeacherBookVO> teacherBookList = new ArrayList<TeacherBookVO>();
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		teacherBookVO.setId("TB-20160519-000028");
		teacherBookVO.setMemberId("testTeacher01");
		teacherBookVO.setBookName("test");
		teacherBookVO.setBookCompany("test");
		teacherBookList.add(teacherBookVO);
		teacherVO.setTeacherBookList(teacherBookList);
		
		List<ProjectHistoryVO> projectHistoryList = new ArrayList<ProjectHistoryVO>();
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000017");
		projectHistoryVO.setMemberId("testTeacher01");
		projectHistoryVO.setStartDate("today");
		projectHistoryVO.setEndDate("today");
		projectHistoryVO.setProjectName("test");
		projectHistoryVO.setProjectLocation("test");
		projectHistoryList.add(projectHistoryVO);
		teacherVO.setProjectHistoryList(projectHistoryList);
		
		List<EducationHistoryVO> educationHistoryList = new ArrayList<EducationHistoryVO>();
		EducationHistoryVO educationHistoryVO = new EducationHistoryVO();
		educationHistoryVO.setId("TEH-20160519-000016");
		educationHistoryVO.setMemberId("testTeacher01");
		educationHistoryVO.setStartDate("today");
		educationHistoryVO.setEndDate("today");
		educationHistoryVO.setEducationName("test");
		educationHistoryVO.setEducationLocation("test");
		educationHistoryList.add(educationHistoryVO);
		teacherVO.setEducationHistoryList(educationHistoryList);
		
		boolean result = teacherBiz.doInsertNewTeacher(teacherVO);
		assertTrue(result);
		
	}
	/*
	 * 윤후
	 */
	@Test
	public void doTeacherInfoModifyActionTest() {
		TeacherVO teacherVO = new TeacherVO();
		
		teacherVO.setAnnual(1); 
		teacherVO.setMemberId("teacher04");
		teacherVO.setBusinessNumber("JUNIT...TT^TT");
		teacherVO.setCompanyName("JUNIT...TT^TT");
		teacherVO.setName("JUNIT...TT^TT");
		
		List<TeacherBookVO> teacherBookList = new ArrayList<TeacherBookVO>();
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		teacherBookVO.setId("TB-20160519-000028");
		teacherBookVO.setMemberId("testTeacher01");
		teacherBookVO.setBookName("test");
		teacherBookVO.setBookCompany("test");
		teacherBookList.add(teacherBookVO);
		teacherVO.setTeacherBookList(teacherBookList);
		
		List<ProjectHistoryVO> projectHistoryList = new ArrayList<ProjectHistoryVO>();
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000017");
		projectHistoryVO.setMemberId("testTeacher01");
		projectHistoryVO.setStartDate("today");
		projectHistoryVO.setEndDate("today");
		projectHistoryVO.setProjectName("test");
		projectHistoryVO.setProjectLocation("test");
		projectHistoryList.add(projectHistoryVO);
		teacherVO.setProjectHistoryList(projectHistoryList);
		
		List<EducationHistoryVO> educationHistoryList = new ArrayList<EducationHistoryVO>();
		EducationHistoryVO educationHistoryVO = new EducationHistoryVO();
		educationHistoryVO.setId("TEH-20160519-000016");
		educationHistoryVO.setMemberId("testTeacher01");
		educationHistoryVO.setStartDate("today");
		educationHistoryVO.setEndDate("today");
		educationHistoryVO.setEducationName("test");
		educationHistoryVO.setEducationLocation("test");
		educationHistoryList.add(educationHistoryVO);
		teacherVO.setEducationHistoryList(educationHistoryList);
		
		boolean result = teacherBiz.doInsertNewTeacher(teacherVO);
		assertTrue(result);
	}
	@Test
	public void deleteTeacherBookEduProHistory	() {
		String id = "TB-20160519-000028";
		String type = "book";
		
		boolean result = teacherBiz.deleteTeacherBookEduProHistory(id, type);
		assertTrue(result);
	}
	
}
