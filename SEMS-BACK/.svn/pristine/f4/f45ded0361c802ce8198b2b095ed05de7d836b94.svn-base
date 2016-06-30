package com.ktds.sems.education.biz;

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
import com.ktds.sems.common.Session;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaListVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EducationBizTest extends SemsTestCase {

	@Autowired
	private EducationBiz educationBiz;
	
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
				educationBiz.writeNewEducation(educationVO);
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
				educationBiz.doActionDelete("ED-20160519-000251");
			}
		});
	}
	
	@Test
	public void getOneEducationTest() {
		String educationId = "ED-20160513-000173";
		EducationVO educationVO = educationBiz.getOneEducation(educationId);
		assertNotNull(educationVO);
	}
	
	// educationId 값 재설정
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
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute(Session.MEMBER_TYPE, "ADM");
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
		
		boolean result = educationBiz.modifyNewEducation(educationVO);
		assertEquals(result, true);
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
	public void getStateByEducationHistroyIdTest(){
		String educationHistoryId = "56";
		assertNotNull(educationBiz.getStateByEducationHistroyId(educationHistoryId));
	}
	
	@Test
	public void applyJoinEducationByMemberIdTest(){
		String educationHistoryId = "36";
		String changeState = "EDU_CL_C";
		assertTrue(educationBiz.applyJoinEducationByMemberId(educationHistoryId, changeState));
	}
	
	@Test
	public void cancelJoinEducationByMemberIdTest(){
		String educationHistoryId = "33";
		String changeState = "EDU_JN_A";
		assertTrue(educationBiz.cancelJoinEducationByMemberId(educationHistoryId, changeState));
	}
	
	@Test
	public void getTotalEduFileCount(){
		EduFileSearchVO eduFileSearchVO = new EduFileSearchVO();
		eduFileSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationBiz.getTotalEduFileCount(eduFileSearchVO));
	}
	
	@Test
	public void getTotalEduQnaCount(){
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationBiz.getTotalEduQnaCount(eduQnaSearchVO));
	}
	
	@Test
	public void getTotalEduReportCount(){
		EduReportSearchVO eduReportSearchVO = new EduReportSearchVO();
		eduReportSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationBiz.getTotalEduReportCount(eduReportSearchVO));
	}
	
	@Test
	public void getAllEduFile(){
		EduFileSearchVO eduFileSearchVO = new EduFileSearchVO();
		eduFileSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationBiz.getAllEduFile(eduFileSearchVO));
	}
	
	@Test
	public void getAllEduQna(){
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationBiz.getAllEduQna(eduQnaSearchVO));
	}
	
	@Test
	public void getAllEduReport(){
		EduReportSearchVO eduReportSearchVO = new EduReportSearchVO();
		eduReportSearchVO.setEducationId("ED-20160519-000233");
		assertNotNull(educationBiz.getAllEduReport(eduReportSearchVO));
	}
	
	@Test
	public void getTotalEduReportHisotryCountTest(){
		EduReportSearchVO reportSearchVO = new EduReportSearchVO();
		int reportHistoryCount = educationBiz.getTotalEduReportHisotryCount(reportSearchVO);
		assertNotNull(reportHistoryCount);
		assertTrue(reportHistoryCount > 0);
	}
	
	@Test
	public void getAllEduReportHistoryTest(){
		EduReportSearchVO reportSearchVO = new EduReportSearchVO();
		Paging paging = new Paging(10,10);
		int totalReportCount = educationBiz.getTotalEduReportHisotryCount(reportSearchVO);
		paging.setPageNumber(0 + "");
		paging.setTotalArticleCount(totalReportCount);
		
		reportSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportSearchVO.setEndIndex(paging.getEndArticleNumber());
		List<EduReportVO> reports = educationBiz.getAllEduReportHistory(reportSearchVO);
		assertNotNull(reports);
		assertTrue(reports.size() > 0);
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
		
		boolean result = educationBiz.writeEduFileNoticeAction(eduNoticeVO);
		assertTrue(result);
	}
	
	@Test
	public void getAllEduFileNoticeTest(){
		
		EduNoticeSearchVO eduNoticeSearchVO = new EduNoticeSearchVO();
		eduNoticeSearchVO.setEducationId(educationDAO.getOneEducationId());
		
		List<EduNoticeVO> eduNoticeList = educationBiz.getAllEduFileNotice(eduNoticeSearchVO);
		
		assertNotNull(eduNoticeList);
		if ( eduNoticeList != null){
			assertTrue(eduNoticeList.size() > 0);
		}else{
			fail("Fail...");
		}

	}
	
	@Test
	public void getOneNoticeTest(){
		
		String eduNoticeId = educationDAO.getOneEduNoticeId();
		EduNoticeVO noticeVO = 	educationBiz.getOneNotice(eduNoticeId);
		assertNotNull(noticeVO);
		
	}
	
	
	@Test
	public void doDeleteEduNoticeTest(){
		
		String eduNoticeId = educationDAO.getOneEduNoticeId();
		boolean result =  educationBiz.doDeleteEduNotice(eduNoticeId);
		assertTrue(result);
		
	}
	
	@Test
	public void doEduFileNoticeModifyTest(){
		
		String eduNoticeId = educationDAO.getOneEduNoticeId();
		
		EduNoticeVO eduNoticeVO = new EduNoticeVO();
		eduNoticeVO.setEduNoticeId(eduNoticeId);
		eduNoticeVO.setEducationId("IMP-20160526-000045");
		eduNoticeVO.setMemberId("JUNIT");
		eduNoticeVO.setTitle("JUNIT 공지 테스ㅡㅌ");
		eduNoticeVO.setContents("ㅋㅋㅋㅋㅋㅋㅋㅋ");
		eduNoticeVO.setCreateDate("2016/05/24");
		eduNoticeVO.setNoticeType("normal");
		
		boolean result = educationBiz.doEduFileNoticeModify(eduNoticeVO);
		assertTrue(result);
	}
	@Test
	public void getAllMemberListTest() {
		List<MemberVO> memberList = educationBiz.getAllMemberList();
		assertNotNull(memberList);
	}
	
	@Test
	public void getJoinEducationTest1() {
		String memberId = "test02";
		List<EducationVO> educationList = educationBiz.getJoinEducation(memberId);
		assertNotNull(educationList);
	}
	
	@Test
	public void getJoinEducationTest2() {
		String memberId = "test03";
		List<EducationVO> educationList = educationBiz.getJoinEducation(memberId);
		assertNotNull(educationList);
	}
	
	@Test
	public void getOneMemberAttendanceTest1() {
		String memberId = "test02";
		List<AttendVO> attendList = educationBiz.getOneMemberAttendance(memberId);
		assertNotNull(attendList);
	}
	
	@Test
	public void getOneMemberAttendanceTest2() {
		String memberId = "test03";
		List<AttendVO> attendList = educationBiz.getOneMemberAttendance(memberId);
		assertNotNull(attendList);
	}
	
	@Test
	public void getAllStartedEducationListTest() {
		List<EducationVO> educationList = educationBiz.getAllStartedEducationList();
		assertNotNull(educationList);
	}
	
	@Test
	public void getAllMemberListByEduIdTest1() {
		String educationId = "ED-20160516-000181";
		List<MemberVO> memberList = educationBiz.getAllMemberListByEduId(educationId);
		assertNotNull(memberList);
	}
	
	@Test
	public void getAllMemberListByEduIdTest2() {
		String educationId = "ED-20160513-000174";
		List<MemberVO> memberList = educationBiz.getAllMemberListByEduId(educationId);
		assertNotNull(memberList);
	}
	
	@Test
	public void getAllTeamListTest() {
		List<TeamVO> teamList = educationBiz.getAllTeamList();
		assertNotNull(teamList);
	}
	
	@Test
	public void getAllMemberListByTeamIdTest1() {
		String educationId = "ED-20160513-000166";
		String teamId = "2";
		
		List<MemberVO> memberList = educationBiz.getAllMemberListByTeamId(educationId, teamId);
		assertNotNull(memberList);
	}
	
	@Test
	public void getAllMemberListByTeamIdTest2() {
		String educationId = "";
		String teamId = "";
		
		List<MemberVO> memberList = educationBiz.getAllMemberListByTeamId(educationId, teamId);
		assertNotNull(memberList);
	}
	
	@Test
	public void getTotalEduQnaCountTest() {
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		int totalReportCount = educationBiz.getTotalEduQnaCount(eduQnaSearchVO);
		assertTrue(totalReportCount>0);
	}
	
	@Test
	public void getAllEduQnaTest() {
		EduQnaListVO eduQnaListVO = new EduQnaListVO();
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		Paging paging = new Paging(20,20);
		
		eduQnaListVO.setPaging(paging);
		int totalReportCount = educationBiz.getTotalEduQnaCount(eduQnaSearchVO);
		assertTrue(totalReportCount>0);
		
		paging.setPageNumber(0 + "");
		paging.setTotalArticleCount(totalReportCount);
		
		eduQnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduQnaSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<EduQnaVO> eduQna = educationBiz.getAllEduQna(eduQnaSearchVO);
		eduQnaListVO.setEduQnaList(eduQna);
		assertTrue(eduQna.size() > 0);

	}
	
	@Test
	public void confirmMemberOfEduTest() {
		String memberId = "test02";
		String educationId = "ED-20160513-000166";
		
		boolean confirmMemberOfEdu = educationBiz.confirmMemberOfEdu(educationId, memberId);
		assertTrue(confirmMemberOfEdu);
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
		
		boolean result = educationBiz.insertEduQna(eduQnaVO);
		assertTrue(result);
	}
	
	@Test
	public void getNextEqbSeqTest() {
		int nextEqbSeq = educationBiz.getNextEqbSeq();
		assertTrue(nextEqbSeq>0);
	}
	
	@Test
	public void detailOfEduQnaTest() {
		EduQnaVO eduQnaVO = educationBiz.detailOfEduQna("EQ-20160525-000073");
		assertNotNull(eduQnaVO);		
	}
	
	@Test
	public void addHitsToEduQnaTest() {
		boolean result = educationBiz.addHitsToEduQna("EQ-20160525-000073");
		assertTrue(result);
	}
	
	@Test
	public void addQnaEduReplyLikeTest() {
		boolean resultTwo = educationBiz.addQnaEduReplyLike("ER-20160525-000904");
		assertTrue(resultTwo);
	}
	
	@Test
	public void addQnaEduReplyDisLikeTest() {
		boolean resultTwo = educationBiz.addQnaEduReplyDisLike("ER-20160525-000904");
		assertTrue(resultTwo);
	}
	
	@Test
	public void getTotalQnaEduReplyCountTest() {
		int totalReportCount = educationBiz.getTotalQnaEduReplyCount("EQ-20160525-000073");
		assertTrue(totalReportCount>0);
	}
	
	@Test
	public void getNowDateTest() {
		String checkStr = educationBiz.getNowDate();
		assertNotNull(checkStr);
	}
	
	@Test
	public void getNextReplySeq() {
		int checkInt = educationBiz.getNextReplySeq();
		assertTrue(checkInt > 0);
	}
	
	@Test
	public void checkReReplyEvalTest() {
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		// 댓글ID
		reRplyEvalVO.setReplyId("RP-20160517-000202");

		// 좋아요 누른 아이디
		reRplyEvalVO.setMbrId("test01");

		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId("RE-20160517-000015");

		boolean checkBoolean = educationBiz.checkReReplyEval(reRplyEvalVO);
		assertTrue(checkBoolean);
	}
}
