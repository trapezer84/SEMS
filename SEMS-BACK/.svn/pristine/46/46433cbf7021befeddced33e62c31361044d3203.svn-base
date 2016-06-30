package com.ktds.sems.education.service;

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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EduFileListVO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduFileVO;
import com.ktds.sems.education.vo.EduNoticeListVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportListVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class EducationServiceTest extends SemsTestCase {

	@Autowired
	private EducationService educationService;
	
	@Autowired
	private FileBiz fileBiz;
	
	@Autowired
	private EducationDAO educationDAO;
	
	@Test
	public void getOneEducationForUpdateTest(){
		String educationId = "ED-20160513-000173";
		ModelAndView view = educationService.getOneEducationForUpdate(educationId);
		String viewName = view.getViewName();
		assertNotNull(view);
		assertTrue(viewName == "education/update");
	}

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
		educationVO.setStartDate("2016-08-20");
		educationVO.setEndDate("2016-08-20");
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		assertNotNull(view);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/detail/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/detail/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
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
		
		ModelAndView view = educationService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	/*
	 * D:\\핸드폰.xlsx 해당 파일이 있어야 insert 가능합니다.
	 * 
	 */

	public void writeNewEducationTest() {
		// List 보는건 차 후에 test 시도

		// EducationId 가 없을 경우 ..
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT!!!!!!.");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
	
		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("D:\\test.xlsx");
		String name = "file";
		String originalFileName = "test";
		String contentType = "text/plain";
		

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = request.getSession();
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		

		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/list");
		} else {
			fail("Fail");
		}
	}
	
	@Test
	public void doWriteActionTestWithError() {
		// List 보는건 차 후에 test 시도

		// EducationCategory 가 없을 경우 ..
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError2() {
		// List 보는건 차 후에 test 시도

		// setEducationTitle 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);
		
		

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError3() {
		// List 보는건 차 후에 test 시도

		// MemberId 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}



	public void doWriteActionTestWithError5() {
		// List 보는건 차 후에 test 시도

		// EducationLocation 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError6() {
		// List 보는건 차 후에 test 시도

		// EducationCurriculum 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError7() {
		// List 보는건 차 후에 test 시도

		// EducationIntroduce 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError8() {
		// List 보는건 차 후에 test 시도

		// StartDate 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰.xlsx";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError9() {
		// List 보는건 차 후에 test 시도

		// EndDate 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰.xlsx";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError10() {
		// List 보는건 차 후에 test 시도

		// StartTime 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\test.xlsx");
		String name = "file";
		String originalFileName = "test";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError11() {
		// List 보는건 차 후에 test 시도

		// EndTime 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError12() {
		// List 보는건 차 후에 test 시도

		// EducationType 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError13() {
		// List 보는건 차 후에 test 시도

		// Cost 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
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
	
	@Test
	public void getAllEducationHistoryTest() {
		
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setMemberId("양지한");
		
		ModelAndView view = educationService.getAllEducationHistory(educationHistorySearchVO, 0);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduManage");
		
		EducationHistoryListVO EduHistoryListVO = (EducationHistoryListVO) view.getModel().get("eduHistoryListVO");
		assertNotNull(EduHistoryListVO);
		
		Paging paging = EduHistoryListVO.getPaging();
		assertNotNull(paging);
		assertTrue(paging.getTotalArticleCount() > 0);
		
		List<EducationHistoryVO> eduHistory = EduHistoryListVO.getEducationHistoryList();
		assertNotNull(eduHistory);
		assertTrue(eduHistory.size() > 0);
		}
		else {
			fail("getAllEducationHistoryTest Fail");
		}
	}
	
	@Test
	public void getJCEduHistoryTest() {
		
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setMemberId("test04");
		
		ModelAndView view = educationService.getJCEduHistory(educationHistorySearchVO, 0);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/checkApplicant");
		
		EducationHistoryListVO EduHistoryListVO = (EducationHistoryListVO) view.getModel().get("eduHistoryListVO");
		assertNotNull(EduHistoryListVO);
		
		Paging paging = EduHistoryListVO.getPaging();
		assertNotNull(paging);
		assertTrue(paging.getTotalArticleCount() > 0);
		
		List<EducationHistoryVO> eduHistory = EduHistoryListVO.getEducationHistoryList();
		assertNotNull(eduHistory);
		assertTrue(eduHistory.size() > 0);
		}
		else {
			fail("getJCEduHistoryTest Fail");
		}
	}
	
	@Test
	public void getAllReportArticle() {

		EduReportSearchVO eduReportSearchVO = new EduReportSearchVO();
		eduReportSearchVO.setEducationId("ED-20160519-000233");
		ModelAndView view = educationService.getAllReportArticle(eduReportSearchVO, 0);
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduReportPage");
		
			EduReportListVO eduReportListVO = (EduReportListVO) view.getModel().get("eduReportListVO");
			assertNotNull(eduReportListVO);
			
			Paging paging = eduReportListVO.getPaging();
			assertNotNull(paging);
			assertTrue(paging.getTotalArticleCount() > 0);
			
			List<EduReportVO> eduReport = eduReportListVO.getEduReportList();
			assertNotNull(eduReport);
			assertTrue(eduReport.size() > 0);
		}
		else {
			fail("getAllReportArticle Fail");
		}
	}

	@Test
	public void getAllEduFileArticle() {
		
		EduFileSearchVO eduFileSearchVO = new EduFileSearchVO();
		EduNoticeSearchVO eduNoticeSearchVO = new EduNoticeSearchVO();
		eduFileSearchVO.setEducationId("ED-20160513-000176");
		eduNoticeSearchVO.setEducationId("ED-20160513-000176");
		ModelAndView view = educationService.getAllEduFileArticle(eduFileSearchVO, 0, eduNoticeSearchVO);
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduFilePage");
			
			EduFileListVO eduFileListVO = (EduFileListVO) view.getModel().get("eduFileListVO");
			assertNotNull(eduFileListVO);
			
			EduNoticeListVO eduNoticeListVO = (EduNoticeListVO) view.getModel().get("eduNoticeListVO");
			assertNotNull(eduNoticeListVO);
			
			Paging paging = eduFileListVO.getPaging();
			assertNotNull(paging);
			assertTrue(paging.getTotalArticleCount() > 0);
			
			List<EduFileVO> eduFile = eduFileListVO.getEduFileList();
			assertNotNull(eduFile);
			assertTrue(eduFile.size() > 0);
			
			List<EduNoticeVO> eduFileNotice  = eduNoticeListVO.getEduNoticeList();
			assertNotNull(eduFileNotice);
			assertTrue(eduFileNotice.size() > 0);
			
		}
		else {
			fail("getAllFileArticle Fail");
		}
	}
	
	@Test
	public void viewReportHistoryPageTest(){
		EduReportSearchVO reportSearchVO = new EduReportSearchVO();
		int pageNo = 0;
		
		ModelAndView view = educationService.viewReportHistoryPage(reportSearchVO, pageNo);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/eduReportHistory");
		EduReportListVO reportListVO = (EduReportListVO)view.getModel().get("reportListVO");
		assertNotNull(reportListVO);
		assertTrue(reportListVO.getEduReportList().size() > 0);
	}
	
	@Test
	public void writeEduFileNoticeActionTest(){
		
		EduNoticeVO eduNoticeVO = new EduNoticeVO();
		eduNoticeVO.setEducationId(educationDAO.getOneEducationId());
		eduNoticeVO.setMemberId("JUNIT");
		eduNoticeVO.setTitle("JUNIT 공지 테스ㅡㅌ");
		eduNoticeVO.setContents("ㅋㅋㅋㅋㅋㅋㅋㅋ");
		eduNoticeVO.setCreateDate("2016/05/24");
		eduNoticeVO.setNoticeType("normal");
		
		MockHttpSession session = new MockHttpSession();
		session.getAttribute(Session.MEMBER);

		BindingResult errors = new BeanPropertyBindingResult(eduNoticeVO, "registerForm");
		ModelAndView view = educationService.writeEduFileNoticeAction(eduNoticeVO, errors, session);

		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/{educationId}/eduFile");
	
	}
	
	@Test
	public void viewNoticeDetailPageTest(){
		
		EduNoticeVO eduNoticeVO = new EduNoticeVO();
		String eduNoticeId =  educationDAO.getOneEduNoticeId();
		
		MemberVO memberVO = new MemberVO();
		String memberId = memberVO.getId();
		
		ModelAndView view = educationService.viewNoticeDetailPage(memberId, eduNoticeId);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/eduFileNoticeDetail");
		
	}
	
	@Test
	public void doDeleteEduNoticeTest(){
		
		EduNoticeVO eduNoticeVO = new EduNoticeVO();
		eduNoticeVO.setEducationId("ED-20160513-000176");
		eduNoticeVO.setMemberId("JUNIT");
		eduNoticeVO.setTitle("JUNIT 공지 테스ㅡㅌ");
		eduNoticeVO.setContents("ㅋㅋㅋㅋㅋㅋㅋㅋ");
		eduNoticeVO.setCreateDate("2016/05/24");
		eduNoticeVO.setNoticeType("normal");
		
		String educationId = "ED-20160513-000176";
		String eduNoticeId = educationDAO.getOneEduNoticeId();
		String deleteResult = educationService.doDeleteEduNotice(educationId , eduNoticeId);
		assertNotNull(deleteResult);
		assertEquals(deleteResult, "redirect:/{educationId}/eduFile");
		
	}

	@Test
	public void massiveDeleteNoticeTest(){
		
		String[] deleteNoiceIds = {"IMP-20160524-000013", "IMP-20160525-000038"};
		String educationId = educationDAO.getOneEducationId();
		String result = educationService.massiveDeleteNotice(educationId, deleteNoiceIds);
		assertEquals(result, "redirect:/{educationId}/eduFile");
		
	}
	
	@Test
	public void viewModifyNoticePageTest(){
		
		EduNoticeVO eduNoticeVO = new EduNoticeVO();
		String eduNoticeId = educationDAO.getOneEduNoticeId();
		ModelAndView view = educationService.viewModifyNoticePage(eduNoticeId);
		
		String viewName = view.getViewName();
		assertNotNull(view);
		assertTrue(viewName == "education/modifyEduFileNotice");

	}

	@Test
	public void doEduFileNoticeModifyTest(){
		
		EduNoticeVO eduNoticeVO = new EduNoticeVO();
		eduNoticeVO.setEducationId("ED-20160513-000176");
		eduNoticeVO.setEduNoticeId("IMP-20160526-000045");
		eduNoticeVO.setMemberId("JUNIT");
		eduNoticeVO.setTitle("JUNIT 공지 테스ㅡㅌ");
		eduNoticeVO.setContents("ㅋㅋㅋㅋㅋㅋㅋㅋ");
		eduNoticeVO.setCreateDate("2016/05/24");
		eduNoticeVO.setNoticeType("normal");
		
		String educationId= "ED-20160513-000176";
		String eduNoticeId ="IMP-20160526-000045";
		
		BindingResult errors = new BeanPropertyBindingResult(eduNoticeVO, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		session.getAttribute(Session.MEMBER);
		ModelAndView view = educationService.doEduFileNoticeModify(educationId, eduNoticeId, eduNoticeVO, errors, session);
		
		if( view != null){
			 String viewName = view.getViewName();
			 assertNotNull(viewName);
			 assertEquals(viewName, "redirect:/"+educationId+"/eduFileNotice/detail/"+eduNoticeId);
		}else {
			fail("fail");
		}
	}
	
	@Test
	public void getAllQnaArticleTest() {
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		int pageNo = 0;
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		ModelAndView view = educationService.getAllQnaArticle(eduQnaSearchVO, pageNo);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertTrue(viewName == "education/eduQnaPage");
	}
	
	@Test
	public void viewWriteEduQnaTest() {
		MockHttpSession session = new MockHttpSession();
		String educationId = "ED-20160513-000166";
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		
		session.setAttribute(Session.MEMBER, memberVO);
		
		ModelAndView view = educationService.viewWriteEduQna(educationId, session);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertTrue(viewName == "education/eduQnaWrite");
	}
	
	@Test
	public void doWriteEduQnaActionTest() {
		EduQnaVO eduQnaVO = new EduQnaVO();
		MockHttpSession session = new MockHttpSession();
		String educationId = "ED-20160513-000166";
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		memberVO.setMemberType("MBR");
		
		session.setAttribute(Session.MEMBER, memberVO);
		eduQnaVO.setMemberId(memberVO.getId());
		eduQnaVO.setEduQnaId("EQ-20160525-000073");
		eduQnaVO.setEducationId(educationId);
		eduQnaVO.setTitle("JUNIT TEST");
		eduQnaVO.setContents("JUNIT TEST");
		
		ModelAndView view = educationService.doWriteEduQnaAction(eduQnaVO, session);
		String viewName = view.getViewName();
		assertNotNull(viewName);
	}
	
	@Test
	public void detailOfEduQnaTest() {
		String eduQnaId = "EQ-20160525-000073";
		String educationId = "ED-20160513-000166";
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		memberVO.setMemberType("MBR");
		
		session.setAttribute(Session.MEMBER, memberVO);
		int pageNo = 0;
		
		ModelAndView view = educationService.detailOfEduQna(eduQnaId, educationId, session, pageNo);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertTrue(viewName == "education/eduQnaDetail");
	}
	
	@Test
	public void doEduQnaReplyActionTest() {
		EducationQNAReplyVO eduBBSReplyVO = new EducationQNAReplyVO();
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		memberVO.setMemberType("MBR");
		
		session.setAttribute(Session.MEMBER, memberVO);
		String educationId = "ED-20160513-000166";
		
		BindingResult errors = new BeanPropertyBindingResult(eduBBSReplyVO,"replyWriteForm");
		
		eduBBSReplyVO.setAtcId("EQ-20160525-000073");
		eduBBSReplyVO.setDescription("JUNIT TEST");
		ModelAndView view = educationService.doEduQnaReplyAction(eduBBSReplyVO, errors, session, educationId);
		String viewName = view.getViewName();
		assertNotNull(viewName);
	}
	
	@Test
	public void addQnaEduReplyLikeTest() {
		String replyId = "ER-20160525-000906";
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		memberVO.setMemberType("MBR");
		
		session.setAttribute(Session.MEMBER, memberVO);
		String result = educationService.addQnaEduReplyLike(replyId, session);
		assertTrue(result == "OK");
	}
	
	@Test
	public void addQnaEduReplyDisLikeTest() {
		String replyId = "ER-20160525-000906";
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		memberVO.setMemberType("MBR");
		
		session.setAttribute(Session.MEMBER, memberVO);
		String result = educationService.addQnaEduReplyDisLike(replyId, session);
		assertTrue(result == "OK");
	}
	
	@Test
	public void getAllMemberListTest() {
		ModelAndView view = educationService.getAllMemberList();
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/attendanceAllMemberList");
		
		List<MemberVO> memberList = (List<MemberVO>)view.getModel().get("memberList");
		assertNotNull(memberList);
		assertTrue(memberList.size() > 0);
	}
	
	/*@Test
	public void getOneMemberAttendanceTest1() {
		
		String memberId = "test02";
		
		ModelAndView view = educationService.getOneMemberAttendance(memberId, 0);
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/attendanceOneMemberList");
		
		List<List<AttendVO>> AllEduAllAttendanceList = (List<List<AttendVO>>)view.getModel().get("AllEduAllAttendanceList");
		assertNotNull(AllEduAllAttendanceList);
		assertTrue(AllEduAllAttendanceList.size() > 0);
	}
	
	@Test
	public void getOneMemberAttendanceTest2() {
		String memberId = "test03";
		
		ModelAndView view = educationService.getOneMemberAttendance(memberId, 0);
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/attendanceHistory/memberList");
	}
	
	@Test
	public void getAllStartedEducationListTest() {
		ModelAndView view = educationService.getAllStartedEducationList();
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/attendanceAllEducationList");
		
		List<EducationVO> educationList = (List<EducationVO>)view.getModel().get("educationList");
		assertNotNull(educationList);
		assertTrue(educationList.size() > 0);
	}
	
	@Test
	public void getOneEducationAttendanceTest1() {
		String educationId = "ED-20160516-000181";
		
		ModelAndView view = educationService.getOneEducationAttendance(educationId, 0);
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/attendanceOneEduList");
		
		List<AttendVO> attendanceList = (List<AttendVO>)view.getModel().get("attendanceList");
		assertNotNull(attendanceList);
		assertTrue(attendanceList.size() > 0);
	}
	
	@Test
	public void getOneEducationAttendanceTest2() {
		String educationId = "ED-20160513-000174";
		
		ModelAndView view = educationService.getOneEducationAttendance(educationId, 0);
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/attendanceHistory/educationList");
	}
	
	@Test
	public void getAllTeamListTest() {
		ModelAndView view = educationService.getAllTeamList();
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/attendanceAllTeamList");
		
		List<TeamVO> teamList = (List<TeamVO>)view.getModel().get("teamList");
		assertNotNull(teamList);
		assertTrue(teamList.size() > 0);
	}
	
	@Test
	public void getOneTeamAttendance1() {
		String educationId = "ED-20160513-000166";
		String teamId = "2";
		String teamName = "Spring";
		
		ModelAndView view = educationService.getOneTeamAttendance(educationId, teamId, teamName, 0);
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/attendanceOneTeamList");
		
		List<AttendVO> attendanceList = (List<AttendVO>)view.getModel().get("attendanceList");
		assertNotNull(attendanceList);
		assertTrue(attendanceList.size() > 0);
	}
	
	@Test
	public void getOneTeamAttendance2() {
		String educationId = "";
		String teamId = "";
		String teamName = "";
		
		ModelAndView view = educationService.getOneTeamAttendance(educationId, teamId, teamName, 0);
		assertNotNull(view);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/attendanceHistory/teamList");
	}*/
}
