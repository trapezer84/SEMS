package com.ktds.sems.education.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaListVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationTypeVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EducationDAOTest extends SemsTestCase {

	@Autowired
	private EducationDAO educationDAO;
	
	/**
	 * @author 김동규 
	 * Action - insert
	 */
	@Before
	public void setUp() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				EducationVO educationVO = new EducationVO();
				educationVO.setEducationId("ED-20160519-000251");
				educationVO.setEducationCategory("ZCS");
				educationVO.setEducationTitle("JUNIT...");
				educationVO.setMemberId("JUNIT...");
				educationVO.setMaxMember(30);
				educationVO.setEducationLocation("JUNIT...");
				educationVO.setEducationCurriculum("JUNIT...");
				educationVO.setEducationIntroduce("JUNIT...");
				educationVO.setStartDate("2016-05-01");
				educationVO.setEndDate("2016-05-04");
				educationVO.setStartTime("01:00");
				educationVO.setEndTime("01:00");
				educationVO.setEducationType("TIMM");
				educationVO.setCost("CSTC");
				educationDAO.insertNewEducation(educationVO);
			}
		});
	}
	
	/**
	 * @author 김동규 
	 * Action - Delete
	 */
	@After
	public void tearDown() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				educationDAO.doActionDelete("ED-20160519-000251");
			}
		});
	}
	
/*	 ED-20160512-000139 이미 등록돼 있음
	 * 
	 *  
	public void insertNewEducationTest(){
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160512-000139");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		assertTrue(educationDAO.insertNewEducation(educationVO) > 0);
		 
	}*/
	
	@Test
	public void costCodeListTest(){
		List<CostVO> costcode = educationDAO.costCodeList();
		assertNotNull(costcode);
		assertTrue(costcode.size() > 0);
	}
	
	@Test
	public void typeCodeListTest(){
		List<EducationTypeVO> typecode = educationDAO.typeCodeList();
		assertNotNull(typecode);
		assertTrue(typecode.size() > 0);
	}
	
	@Test
	public void categoryCodeListTest(){
		List<CategoryVO>  categorycode = educationDAO.categoryCodeList();
		assertNotNull(categorycode);
		assertTrue(categorycode.size() > 0);
	}
	
	/*
	 * educationId 값 재설정
	
	public void getOneEducationTest() {
		String educationId = "ED-20160518-000206";
		EducationVO educationVO =  educationDAO.getOneEducation(educationId);
		assertNotNull(educationVO);
	}
	*/
	
	@Test
	public void modifyNewEducationTest() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError1() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		//educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError2() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		//educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError3() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		//educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError4() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		//educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError5() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		//educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError6() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		//educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError7() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		//educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError8() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		//educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError9() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		//educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError10() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		//educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError11() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		//educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError12() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		//educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyNewEducationTestWithError14() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-05-01");
		educationVO.setEndDate("2016-05-04");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		//educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		int result = educationDAO.modifyNewEducation(educationVO);
		assertEquals(result, 1);
	}
	
	public class EducationValidator implements Validator {
		
		@Override
		public boolean supports(Class<?> clazz) {
			return EducationVO.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			
			EducationVO educationVO = (EducationVO) target;
			
			String educationCategory = educationVO.getEducationCategory();
			if (educationCategory == null || educationCategory.length() == 0) {
				errors.rejectValue("educationCategory", "field.required", "error default message");
			}
			
			String educationTitle = educationVO.getEducationTitle();
			if (educationTitle == null || educationTitle.length() == 0) {
				errors.rejectValue("educationTitle", "field.required", "error default message");
			}
			
			String memberId = educationVO.getMemberId();
			if (memberId == null || memberId.length() == 0) {
				errors.rejectValue("memberId", "field.required", "error default message");
			}
			
			String educationLocation = educationVO.getEducationLocation();
			if (educationLocation == null || educationLocation.length() == 0) {
				errors.rejectValue("educationLocation", "field.required", "error default message");
			}
			
			String educationCurriculum = educationVO.getEducationCurriculum();
			if (educationCurriculum == null || educationCurriculum.length() == 0) {
				errors.rejectValue("educationCurriculum", "field.required", "error default message");
			}
			
			String educationIntroduce = educationVO.getEducationIntroduce();
			if (educationIntroduce == null || educationIntroduce.length() == 0) {
				errors.rejectValue("educationIntroduce", "field.required", "error default message");
			}
			
			String startDate = educationVO.getStartDate();
			if (startDate == null || startDate.length() == 0) {
				errors.rejectValue("startDate", "field.required", "error default message");
			}
			
			String endDate = educationVO.getEndDate();
			if (endDate == null || endDate.length() == 0) {
				errors.rejectValue("endDate", "field.required", "error default message");
			}
			
			String startTime = educationVO.getStartTime();
			if (startTime == null || startTime.length() == 0) {
				errors.rejectValue("startTime", "field.required", "error default message");
			}
			
			String endTime = educationVO.getEndTime();
			if (endTime == null || endTime.length() == 0) {
				errors.rejectValue("endTime", "field.required", "error default message");
			}
			
			String educationType = educationVO.getEducationType();
			if (educationType == null || educationType.length() == 0) {
				errors.rejectValue("educationType", "field.required", "error default message");
			}
			
			String cost = educationVO.getCost();
			if (cost == null || cost.length() == 0) {
				errors.rejectValue("cost", "field.required", "error default message");
			}
		}
	}
	
	/**
	 * 민정
	 */
	@Test
	public void getAllEduHistoryCountTest(){
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setMemberId("JUnit");
		educationHistorySearchVO.setEndIndex(0);
		educationHistorySearchVO.setPageNo(0);
		educationHistorySearchVO.setSearchKeyword("JUnit");
		educationHistorySearchVO.setStartIndex(0);
		
		int totalCount = educationDAO.getAllEduHistoryCount(educationHistorySearchVO);
		assertTrue(totalCount >= 0);
	}
	
	/**
	 * 민정
	 */
	@Test
	public void getAllEducationHistoryTest(){
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setMemberId("JUnit");
		educationHistorySearchVO.setEndIndex(0);
		educationHistorySearchVO.setPageNo(0);
		educationHistorySearchVO.setSearchKeyword("JUnit");
		educationHistorySearchVO.setStartIndex(0);
		
		List<EducationHistoryVO> educationHistryList = educationDAO.getAllEducationHistory(educationHistorySearchVO);
		assertNotNull(educationHistryList);
	}
	
	/**
	 * 민정
	 */
	@Test
	public void getStateByEducationHistroyIdTest(){
		String educationHistoryId = "33";
		String result = educationDAO.getStateByEducationHistroyId(educationHistoryId);
		
		assertNotNull(result);
	}
	
	/**
	 * 민정
	 */
	@Test
	public void applyJoinEducationByMemberIdTest(){
		String educationHistoryId = "33";
		String changeState ="JUnit";
		
		int result = educationDAO.applyJoinEducationByMemberId(educationHistoryId, changeState);
		assertTrue(result >= 0);		
	}
	
	/**
	 * 민정
	 */
	@Test
	public void cancelJoinEducationByMemberIdTest(){
		String educationHistoryId = "33";
		String changeState ="JUnit";
		
		int result = educationDAO.cancelJoinEducationByMemberId(educationHistoryId, changeState);
		assertTrue(result >= 0);		
	}
	
	@Test
	public void getTotalEduReportCount(){
		EduReportSearchVO eduReportSearchVO = new EduReportSearchVO();
		eduReportSearchVO.setEducationId("ED-20160519-000233");
		
		assertTrue(educationDAO.getTotalEduReportCount(eduReportSearchVO) >= 0);
	}
	
	@Test
	public void getTotalEduFileCount(){
		EduFileSearchVO eduFileSearchVO = new EduFileSearchVO();
		eduFileSearchVO.setEducationId("ED-20160519-000233");
		assertTrue(educationDAO.getTotalEduFileCount(eduFileSearchVO) >= 0);
	}
	
	@Test
	public void getTotalEduQnaCount(){
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160519-000233");
		assertTrue(educationDAO.getTotalEduQnaCount(eduQnaSearchVO) >= 0);
	}

	@Test
	public void getAllEduFile(){
		EduFileSearchVO eduFileSearchVO = new EduFileSearchVO();
		eduFileSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationDAO.getAllEduFile(eduFileSearchVO));
	}
	
	@Test
	public void getAllEduQna(){
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationDAO.getAllEduQna(eduQnaSearchVO));
	}
	
	@Test
	public void getAllEduReport(){
		EduReportSearchVO eduReportSearchVO = new EduReportSearchVO();
		eduReportSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationDAO.getAllEduReport(eduReportSearchVO));
	}
	/**
	 * @author 김동규
	 */
	@Test
	public void doActionDeleteBeforeCheckTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setMemberType("ADM");
		String check = educationDAO.doActionDeleteBeforeCheck(memberVO);
		if(check != null) {
			assertTrue(check.equals("Y"));
		}
		else {
			fail("[DAO Part] doActionDeleteBeforeCheckTest Fail.");
		}
	}
	
	/**
	 * @author 김동규
	 */	
	@Test
	public void attendedLectureUserListTest() {
		String educationId = "ED-20160519-000241";
		List<EducationVO> educationList = educationDAO.attendedLectureUserList(educationId);
		
		for (EducationVO educationVO : educationList) {
			if ( educationVO != null) {
				assertNotNull(educationVO.getMemberId());
			}else {
				fail("[DAO Part] attendedLectureUserListTest Fail.");
			}
		}
	}
//	/**
//	 * @author 김동규
//	 */
//	@Test
//	public void emailNoticeForUserTest() {
//		String memberId = "test04";
//		MemberVO memberVO = educationDAO.emailNoticeForUser(memberId);
//		if (memberVO != null) {
//			assertNotNull(memberId, memberVO.getId());
//			assertNotNull(memberVO.getEmail());
//		}else {
//			fail("[DAO Part] emailNoticeForUserTest Fail.");
//		}
//	}
	
	@Test
	public void getTotalEduReportHisotryCountTest(){
		EduReportSearchVO reportSearchVO = new EduReportSearchVO();
		int reportHistoryCount = educationDAO.getTotalEduReportHisotryCount(reportSearchVO);
		assertNotNull(reportHistoryCount);
		assertTrue(reportHistoryCount > 0);
	}
	
	@Test
	public void getAllEduReportHistoryTest(){
		EduReportSearchVO reportSearchVO = new EduReportSearchVO();
		Paging paging = new Paging(10,10);
		int totalReportCount = educationDAO.getTotalEduReportHisotryCount(reportSearchVO);
		paging.setPageNumber(0 + "");
		paging.setTotalArticleCount(totalReportCount);
		
		reportSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportSearchVO.setEndIndex(paging.getEndArticleNumber());
		List<EduReportVO> reports = educationDAO.getAllEduReportHistory(reportSearchVO);
		assertNotNull(reports);
		assertTrue(reports.size() > 0);
	}
	
	@Test
	public void nextEduNoticeSeqTest(){
		
		int result = educationDAO.nextEduNoticeSeq();
		assertNotNull(result);
	}
	
	@Test
	public void insertNewEduFileNoticeTest(){
		
		int nextEduFileNoticeId = educationDAO.nextEduNoticeSeq();
		String nowDate = educationDAO.nowDate();
		String noticeId = "JunitTest"+nowDate+ nextEduFileNoticeId;
		
		EduNoticeVO eduNoticeVO = new EduNoticeVO();
		eduNoticeVO.setEduNoticeId(noticeId);
		eduNoticeVO.setEducationId(educationDAO.getOneEducationId());
		eduNoticeVO.setMemberId("JUNIT");
		eduNoticeVO.setTitle("JUNIT 공지 테스ㅡㅌ");
		eduNoticeVO.setContents("ㅋㅋㅋㅋㅋㅋㅋㅋ");
		eduNoticeVO.setCreateDate("2016/05/24");
		eduNoticeVO.setNoticeType("normal");
		
		int result = educationDAO.insertNewEduFileNotice(eduNoticeVO);
		assertNotNull(result);
	}
	
	@Test
	public void getAllEduFileNoticeTest(){
		
		EduNoticeSearchVO eduNoticeSearchVO = new EduNoticeSearchVO();
		eduNoticeSearchVO.setEducationId(educationDAO.getOneEducationId());
		
		List<EduNoticeVO> eduNoticeList = educationDAO.getAllEduFileNotice(eduNoticeSearchVO);
		
		assertNotNull(eduNoticeList);
		assertTrue(eduNoticeList.size() > 0);
	
	}
	
	@Test
	public void getOneNoticeTest(){
		
		String eduNoticeId =  educationDAO.getOneEduNoticeId();
		EduNoticeVO eduNotice = educationDAO.getOneNotice(eduNoticeId);
		assertNotNull(eduNotice);
	}
	
	@Test
	public void doDeleteEduNoticeTest(){
		
		String eduNoticeId =  educationDAO.getOneEduNoticeId();
		int result = educationDAO.doDeleteEduNotice(eduNoticeId);
		assertNotNull(result);
	}
	
	@Test
	public void doEduFileNoticeModifyTest(){
	
		String eduNoticeId = educationDAO.getOneEduNoticeId();
		
		EduNoticeVO changeEduNoticeVO = new EduNoticeVO();
		changeEduNoticeVO.setEduNoticeId(eduNoticeId);
		changeEduNoticeVO.setEducationId("IMP-20160526-000045");
		changeEduNoticeVO.setMemberId("JUNIT");
		changeEduNoticeVO.setTitle("JUNIT 공지 테스ㅡㅌ");
		changeEduNoticeVO.setContents("ㅋㅋㅋㅋㅋㅋㅋㅋ");
		changeEduNoticeVO.setCreateDate("2016/05/24");
		changeEduNoticeVO.setNoticeType("normal");
	
		int result = educationDAO.doEduFileNoticeModify(changeEduNoticeVO);
		assertNotNull(result);
	}
	
	@Test
	public void getAllMemberListTest() {
		List<MemberVO> memberList = educationDAO.getAllMemberList();
		assertNotNull(memberList);
	}
	
	@Test
	public void getJoinEducationTest1() {
		String memberId = "test02";
		List<EducationVO> educationList = educationDAO.getJoinEducation(memberId);
		assertNotNull(educationList);
	}
	
	@Test
	public void getJoinEducationTest2() {
		String memberId = "test03";
		List<EducationVO> educationList = educationDAO.getJoinEducation(memberId);
		assertNotNull(educationList);
	}
	
	@Test
	public void getOneMemberAttendanceTest1() {
		String memberId = "test02";
		List<AttendVO> attendList = educationDAO.getOneMemberAttendance(memberId);
		assertNotNull(attendList);
	}
	
	@Test
	public void getOneMemberAttendanceTest2() {
		String memberId = "test03";
		List<AttendVO> attendList = educationDAO.getOneMemberAttendance(memberId);
		assertNotNull(attendList);
	}
	
	@Test
	public void getAllStartedEducationListTest() {
		List<EducationVO> educationList = educationDAO.getAllStartedEducationList();
		assertNotNull(educationList);
	}
	
	@Test
	public void getAllMemberListByEduIdTest1() {
		String educationId = "ED-20160516-000181";
		List<MemberVO> memberList = educationDAO.getAllMemberListByEduId(educationId);
		assertNotNull(memberList);
	}
	
	@Test
	public void getAllMemberListByEduIdTest2() {
		String educationId = "ED-20160513-000174";
		List<MemberVO> memberList = educationDAO.getAllMemberListByEduId(educationId);
		assertNotNull(memberList);
	}
	
	@Test
	public void getAllTeamListTest() {
		List<TeamVO> teamList = educationDAO.getAllTeamList();
		assertNotNull(teamList);
	}
	
	@Test
	public void getAllMemberListByTeamIdTest1() {
		String educationId = "ED-20160513-000166";
		String teamId = "2";
		
		List<MemberVO> memberList = educationDAO.getAllMemberListByTeamId(educationId, teamId);
		assertNotNull(memberList);
	}
	
	@Test
	public void getAllMemberListByTeamIdTest2() {
		String educationId = "";
		String teamId = "";
		
		List<MemberVO> memberList = educationDAO.getAllMemberListByTeamId(educationId, teamId);
		assertNotNull(memberList);
	}
	
	@Test
	public void getTotalEduQnaCountTest() {
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		int result = educationDAO.getTotalEduQnaCount(eduQnaSearchVO);
		assertTrue(result>0);
	}
	
	@Test
	public void getAllEduQnaTest() {
		EduQnaListVO eduQnaListVO = new EduQnaListVO();
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		Paging paging = new Paging(20,20);
		
		eduQnaListVO.setPaging(paging);
		int totalReportCount = educationDAO.getTotalEduQnaCount(eduQnaSearchVO);
		assertTrue(totalReportCount>0);
		
		paging.setPageNumber(0 + "");
		paging.setTotalArticleCount(totalReportCount);
		
		eduQnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduQnaSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EduQnaVO> list = educationDAO.getAllEduQna(eduQnaSearchVO);
		
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void confirmMemberOfEduTest() {
		String memberId = "test02";
		String educationId = "ED-20160513-000166";
		
		int confirmMemberOfEdu = educationDAO.confirmMemberOfEdu(educationId, memberId);
		assertTrue(confirmMemberOfEdu>0);
	}
	
	@Test
	public void insertEduQnaTest() {
		EduQnaVO eduQnaVO = new EduQnaVO();
		String memberId = "test02";
		String eduQnaId = "JUNIT TEST";
		
		eduQnaVO.setMemberId(memberId);
		eduQnaVO.setEduQnaId(eduQnaId);
		eduQnaVO.setEducationId("ED-20160513-000166");
		eduQnaVO.setTitle("JUNIT TEST");
		eduQnaVO.setContents("JUNIT TEST");
		
		int result = educationDAO.insertEduQna(eduQnaVO);
		assertTrue(result>0);
	}
	
	@Test
	public void getNextEqbSeqTest() {
		int nextEqbSeq = educationDAO.getNextEqbSeq();
		assertTrue(nextEqbSeq>0);
	}
	
	@Test
	public void detailOfEduQnaTest() {
		EduQnaVO eduQnaVO = educationDAO.detailOfEduQna("EQ-20160525-000073");
		assertNotNull(eduQnaVO);
	}
	
	@Test
	public void addHitsToEduQnaTest() {
		int result = educationDAO.addHitsToEduQna("EQ-20160525-000073");
		assertTrue(result>0);
	}
	
	@Test
	public void addQnaEduReplyLikeTest() {
		int resultTwo = educationDAO.addQnaEduReplyLike("ER-20160525-000904");
		assertTrue(resultTwo>0);
	}
	
	@Test
	public void addQnaEduReplyDisLikeTest() {
		int resultTwo = educationDAO.addQnaEduReplyDisLike("ER-20160525-000904");
		assertTrue(resultTwo>0);
	}
	
	@Test
	public void getTotalQnaEduReplyCountTest() {
		int totalReportCount = educationDAO.getTotalQnaEduReplyCount("EQ-20160525-000073");
		assertTrue(totalReportCount>0);
	}
	
	@Test
	public void getNowDateTest(){
		assertNotNull(educationDAO.getNowDate());
	}
	
	@Test
	public void getNextReplySeqTest(){
		assertNotNull(educationDAO.getNextReplySeq());
	}
	
	@Test
	public void getEmailTest(){
		assertNotNull(educationDAO.getEmail("test02"));
	}
	
	@Test
	public void getNextReReplyEvalTest(){
		assertNotNull(educationDAO.getNextReReplyEval());
	}
	
	@Test
	public void checkReReplyEvalTest(){
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		//댓글ID
		reRplyEvalVO.setReplyId("RP-20160517-000202");
		
		// 좋아요 누른 아이디
		reRplyEvalVO.setMbrId("test01");
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId("RE-20160517-000015");
		
		assertTrue(educationDAO.checkReReplyEval(reRplyEvalVO) > 0);
	}
}
