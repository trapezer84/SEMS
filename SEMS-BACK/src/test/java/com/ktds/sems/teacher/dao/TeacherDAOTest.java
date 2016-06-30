package com.ktds.sems.teacher.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;

@Transactional
public class TeacherDAOTest extends SemsTestCase {

	@Autowired
	private TeacherDAO teacherDAO;
	
	@Test
	public void getTotalTeacherCountTest() {

		Map<String,String> searchInfo = new HashMap<String,String>();
	
		searchInfo.put("searchType", "1");
		searchInfo.put("searchKeyword", "강사1");
		
		int totalCount = teacherDAO.getTotalTeacherCount(searchInfo);
		assertNotNull(totalCount);
		
	}
	@Test
	public void getAllTeacherTest() {
		
		TeacherSearchVO searchVO = new TeacherSearchVO();
		searchVO.setSearchKeyword("");
		searchVO.setSearchType("");
		searchVO.setMemberId("");
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		searchVO.setPageNo(10);
		
		List<TeacherVO> teacherList = teacherDAO.getAllTeacher(searchVO);
		
		assertNotNull(teacherList);
		if ( teacherList != null){
			assertTrue(teacherList.size() >0);
		}else{
			fail("Fail...");
		}
	}
	
	
	@Test
	public void doDeleteTeacherTest() {
		
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setMemberId("junit");
		teacherVO.setCompanyName("junit");
		teacherVO.setBusinessNumber("1234");
		teacherVO.setAnnual(1);
		int isInsert = teacherDAO.doInsertNewTeacher(teacherVO);
		if (isInsert > 0) {
			int result = teacherDAO.doDeleteTeacher(teacherVO.getMemberId());
			assertNotNull(result);
		}

	}
	@Test
	public void doDeleteProjectHistoryTest() {
		
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000001");
		projectHistoryVO.setMemberId("junit");
		projectHistoryVO.setStartDate("d");
		projectHistoryVO.setEndDate("d");
		projectHistoryVO.setProjectName("d");
		projectHistoryVO.setProjectLocation("d");
		
		int isInsert = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
		if ( isInsert > 0 ){
			int result = teacherDAO.doDeleteProjectHistory(projectHistoryVO.getMemberId());
			assertNotNull(result);
		}

	}
	@Test
	public void doDeleteEducationHistoryTest() {
		
		EducationHistoryVO teacherEduHistoryVO = new EducationHistoryVO();
		teacherEduHistoryVO.setId("TEH-20160519-000001");
		teacherEduHistoryVO.setMemberId("junit");
		teacherEduHistoryVO.setStartDate("r");
		teacherEduHistoryVO.setEndDate("r");
		teacherEduHistoryVO.setEducationName("r");
		teacherEduHistoryVO.setEducationLocation("r");
		int isInsert = teacherDAO.doInsertTeacherEduHis(teacherEduHistoryVO);
		if ( isInsert > 0){
			int result = teacherDAO.doDeleteEducationHistory(teacherEduHistoryVO.getMemberId());
			assertNotNull(result);
		}
		

	}
	@Test
	public void doDeleteTeacherBookTest() {
		
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000001");
		projectHistoryVO.setMemberId("junit");
		projectHistoryVO.setStartDate("d");
		projectHistoryVO.setEndDate("d");
		projectHistoryVO.setProjectName("d");
		projectHistoryVO.setProjectLocation("d");
		int isInsert = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
		
		if ( isInsert > 0 ){
			int result = teacherDAO.doDeleteProjectHistory(projectHistoryVO.getMemberId());
			assertNotNull(result);
		}
	}
	
	@Test
	public void getTeacherInfoTest(){
		String memberId = "teacher01";
		TeacherVO teacherVO = teacherDAO.getTeacherInfo(memberId);
		assertNotNull(teacherVO);
	}

	@Test
	public void getTeacherEducationHistoryTest(){
		String memberId = "teacher01";
		List<EducationHistoryVO> teacherEducations = teacherDAO.getTeacherEducationHistory(memberId);
		assertNotNull(teacherEducations);
	}

	@Test
	public void getTeacherProjectHistoryTest(){
		String memberId = "teacher01";
		List<ProjectHistoryVO> teacherProjects = teacherDAO.getTeacherProjectHistory(memberId);
		assertNotNull(teacherProjects);
	}

	@Test
	public void getTeacherBookTest(){
		String memberId = "teacher01";
		List<TeacherBookVO> teacherBooks = teacherDAO.getTeacherBook(memberId);
		assertNotNull(teacherBooks);
	}

	@Test
	public void getTeacherEducationGradeTest(){
		String memberId = "teacher01";
		double teacherGrade = teacherDAO.getTeacherEducationGrade(memberId);
		assertNotNull(teacherGrade);
	}

	@Test
	public void getEducationHistoryTest(){
		String memberId = "teacher01";
		List<EducationVO> educations = teacherDAO.getEducationHistory(memberId);
		assertNotNull(educations);
	}
	/**
	 * 윤후
	 */
	@Test
	public void doInsertTeacherBookHisTest() {
		TeacherBookVO teacherBooKVO = new TeacherBookVO();
		
		teacherBooKVO.setBookCompany("test");
		teacherBooKVO.setBookName("test");
		teacherBooKVO.setId("test");
		teacherBooKVO.setMemberId("test");
		
		// OK : 1, NO : 0
		int result = teacherDAO.doInsertTeacherBookHis(teacherBooKVO);
		assertTrue(result > 0);
		
	}
	/**
	 * 윤후
	 */
	@Test
	public void doInsertTeacherProHisTest() {
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		
		projectHistoryVO.setId("test");
		projectHistoryVO.setMemberId("TPH-20160519-000017");
		projectHistoryVO.setProjectName("test");
		projectHistoryVO.setStartDate("test");
		projectHistoryVO.setEndDate("test");
		projectHistoryVO.setProjectLocation("test");
		
		int result = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
		assertTrue(result > 0);
	}
	/**
	 * 윤후
	 */
	@Test
	public void doInsertTeacherEduHisTest() {
		EducationHistoryVO teacherEduHistoryVO = new EducationHistoryVO();
		
		teacherEduHistoryVO.setEducationLocation("test");
		teacherEduHistoryVO.setEducationName("test");
		teacherEduHistoryVO.setEndDate("test");
		teacherEduHistoryVO.setId("test");
		teacherEduHistoryVO.setMemberId("test");
		teacherEduHistoryVO.setStartDate("test");
		
		int result = teacherDAO.doInsertTeacherEduHis(teacherEduHistoryVO);
		assertTrue(result > 0);
	}
	/**
	 * 윤후
	 */
	@Test
	public void doTeacherEducationModifyActionTest() {
		EducationHistoryVO teacherEduHistoryVO = new EducationHistoryVO();
		
		teacherEduHistoryVO.setId("TEH-20160519-000016");
		teacherEduHistoryVO.setEducationLocation("test");
		teacherEduHistoryVO.setEducationName("test");
		teacherEduHistoryVO.setEndDate("test");
		teacherEduHistoryVO.setMemberId("teacher01");
		teacherEduHistoryVO.setStartDate("test");
		
		int result = teacherDAO.doTeacherEducationModifyAction(teacherEduHistoryVO);
		assertTrue(result > 0);
	}
	/**
	 * 윤후
	 */
	@Test
	public void doTeacherProjectModifyAction() {
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		
		projectHistoryVO.setId("TPH-20160519-000017");
		projectHistoryVO.setEndDate("11");
		projectHistoryVO.setProjectLocation("22");
		projectHistoryVO.setProjectName("33");
		projectHistoryVO.setStartDate("44");
		
		int result = teacherDAO.doTeacherProjectModifyAction(projectHistoryVO);
		assertTrue(result > 0);
	}
	/**
	 * 윤후
	 */
	@Test
	public void doTeacherBookModifyAction() {
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		
		teacherBookVO.setId("TB-20160519-000028");
		teacherBookVO.setBookCompany("test");
		teacherBookVO.setBookName("test");
		teacherBookVO.setMemberId("teacher01");
		
		int result = teacherDAO.doTeacherBookModifyAction(teacherBookVO);
		assertTrue(result > 0);
	}
	/**
	 * 윤후
	 */
	@Test
	public void getOneEducationHistoryVO() {
		String memberId = "teacher01";
		List<EducationHistoryVO> teacherEducations = teacherDAO.getOneEducationHistoryVO(memberId);
		if (teacherEducations != null ){
			for (EducationHistoryVO educationHistoryVO : teacherEducations) {
				assertNotNull(educationHistoryVO.getId());
			}
		} else {
			fail("fail");
		}
	}
	/**
	 * 윤후
	 */
	@Test
	public void getOneTeacherProjectHistoryVO() {
		String memberId = "teacher01";
		List<ProjectHistoryVO> teacherProjectHistoryVO = teacherDAO.getOneTeacherProjectHistoryVO(memberId);
		if (teacherProjectHistoryVO != null ){
			for (ProjectHistoryVO projectHistoryVO : teacherProjectHistoryVO) {
				assertNotNull(projectHistoryVO.getId());
			}
		} else {
			fail("fail");
		}
	}
	/**
	 * 윤후
	 */
	@Test
	public void getOneTeacherBookInfo() {
		String memberId = "teacher01";
		List<TeacherBookVO> oneTeacherBookInfo = teacherDAO.getOneTeacherBookInfo(memberId);
		if (oneTeacherBookInfo != null ){
			for (TeacherBookVO teacherBookVO : oneTeacherBookInfo) {
				assertNotNull(teacherBookVO.getId());
			}
		} else {
			fail("fail");
		}
	}
	/**
	 * 윤후
	 */
	@Test
	public void getOneTeacherInfo() {
		String memberId = "teacher01";
		TeacherVO teacherVO = teacherDAO.getOneTeacherInfo(memberId);
		if(teacherVO != null) {
			assertNotNull(teacherVO.getMemberId());
		} else {
			fail("fail");
		}
	}
	/**
	 * 윤후
	 */
	@Test
	public void doDeleteTeacherBook() {
		String memberId = "teacher01";
		int result = teacherDAO.doDeleteTeacherBook(memberId);
		assertTrue(result > 0);
	}
	/**
	 * 윤후
	 */
	@Test
	public void doDeleteEducationHistory() {
		String memberId = "teacher01";
		int result = teacherDAO.doDeleteEducationHistory(memberId);
		assertTrue(result > 0);
	}
	/**
	 * 윤후
	 */
	@Test
	public void doDeleteProjectHistory() {
		String memberId = "teacher01";
		int result = teacherDAO.doDeleteProjectHistory(memberId);
		assertTrue(result > 0);
	}
}
