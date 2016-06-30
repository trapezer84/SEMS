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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.BBSReplyVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReplyListVO;
import com.ktds.sems.education.vo.EducationFileBBSVO;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.FileBBSSearchVO;
import com.ktds.sems.education.vo.QNAListVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class EducationServiceTest extends SemsTestCase {

	@Autowired
	private FileBiz fileBiz;
	@Autowired
	private EducationBiz educationBiz;
	@Autowired
	private EducationService educationService;
	@Autowired
	private EducationDAO educationDAO;

	@Before
	public void setUp() {
		testHelper(new Testable() {

			@Override
			public void preparedTest() {

				MockHttpSession session = new MockHttpSession();

				MemberVO loginMember = new MemberVO();
				loginMember.setId("testMemberId");
				session.setAttribute(Session.MEMBER, loginMember);

				EducationReportVO educationReportVO = new EducationReportVO();
				educationReportVO.setArticleId("testArticleId");
				educationReportVO.setEducationId("testEducationId");
				educationReportVO.setMemberId(loginMember.getId());
				educationReportVO.setTitle("testTitle");
				educationReportVO.setContents("testContents");
				educationReportVO.setStartDate("2016-05-25");
				educationReportVO.setEndDate("2016-05-26");

				BindingResult errors = new BeanPropertyBindingResult(educationReportVO, "reportWriteForm");

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

				ModelAndView view = educationService.doReportWriteAction(educationReportVO, errors, request, session);
				if (view != null) {
					String viewName = view.getViewName();
					assertNotNull(viewName);
					assertEquals(viewName, "redirect:/education/reportList/" + educationReportVO.getEducationId());
				} else {
					fail("fail");
				}
			}
			
		});
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {

				// ReportReplyVO
				ReportReplyVO reportReplyVO = new ReportReplyVO();
				reportReplyVO.setBbsId("testArticleId");
				reportReplyVO.setRptRplId("testRptId");
				
				// Session
				MemberVO memberVO = new MemberVO();
				memberVO.setId("testMemberId");
				MockHttpSession session = new MockHttpSession();
				session.setAttribute(Session.MEMBER, memberVO);
				
				// Request
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
				
				educationService.doReportSubmit(reportReplyVO, request, session);
			}
		});
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				
				String replyId = "testReplyId";
				String eduId = "testEduId";
				String id = "testId";
				String description = "testDescription";
				
				MemberVO member = new MemberVO();
				member.setId("testMemberId");
				MockHttpSession session = new MockHttpSession();
				session.setAttribute(Session.MEMBER, member);
				
				educationService.doReReplyInsert(replyId, eduId, id, description, session);
			}
		});
	}
	
	@After
	public void tearDown() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				
				EducationReportVO educationReportVO = new EducationReportVO();
				educationReportVO.setArticleId("testArticleId");
				educationReportVO.setMemberId("testMemberId");
				
				MemberVO memberVO = new MemberVO();
				memberVO.setId("testMemberId");
				MockHttpSession session = new MockHttpSession();
				session.setAttribute("_MEMBER_", memberVO);
				
				educationService.deleteReport(educationReportVO, session);
			}
		});
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {

				QNAVO qnaVO = new QNAVO();
				qnaVO.setReplyId("testReplyId");
				educationService.doReReplyDelete(qnaVO);
			}
		});
	}

	// @Test
	public void doCancelEducation() {

		String educationId = "ED-20160510-000011";
		String loginMember = "cocomo12";

		// 성공하면 result에 true
		boolean result = educationBiz.doCancelEducation(educationId, loginMember);

		// 실패하면 에러
		// True 면 에러
		assertTrue(result);
	}

	/*
	 * @Test public void getOneEducationDetail(){ String educationId =
	 * "ED-20160510-000004"; int totalEduReplyCount =
	 * educationBiz.getEduReplyCount(educationId); int pageNo = 3;
	 * EduReplyListVO eduReplyListVO = new EduReplyListVO(); Paging paging = new
	 * Paging(10,10);
	 * 
	 * eduReplyListVO.setPaging(paging); paging.setPageNumber(pageNo + "");
	 * 
	 * totalEduReplyCount = educationBiz.getEduReplyCount(educationId);
	 * 
	 * paging.setTotalArticleCount(totalEduReplyCount);
	 * 
	 * EducationSearchVO searchVO = new EducationSearchVO();
	 * searchVO.setStartIndex(paging.getStartArticleNumber());
	 * searchVO.setEndIndex(paging.getEndArticleNumber());
	 * 
	 * EducationVO education = educationBiz.getOneEducationDetail(educationId);
	 * 
	 * List<FileVO> fileList = fileBiz.getOneFileId(educationId);
	 * 
	 * List<QNAVO> qnas = educationBiz.getAllCommentByEducationId(educationId,
	 * searchVO);
	 * 
	 * eduReplyListVO.setQnaList(qnas);
	 * 
	 * MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
	 * boolean isApply = true; if
	 * (educationBiz.isApplyMemberByEducationId(educationId,
	 * loginMember.getId()) > 0 ){ isApply = false; } else{ isApply = true; } }
	 */

	@Test
	public void getOneEducationDetailTest() {
		EducationVO educationVO = new EducationVO();
		String educationId = "ED-20160510-000011";
		educationBiz.getOneEducationDetail(educationId);
	}

	public void getAllCommentByEducationIdTest() {

	}

	@Test
	public void getAllEducationList() {
		EducationListVO educationListVO = new EducationListVO();
		Paging paging = new Paging(15, 15);
		educationListVO.setPaging(paging);
		paging.setPageNumber(0 + "");

		int totalEducationCount = educationBiz.getTotalEducationCount();
		assertTrue(totalEducationCount > 0);

		paging.setTotalArticleCount(totalEducationCount);
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		List<EducationVO> educationList = educationBiz.getAllEducationList(searchVO);
		assertTrue(educationList.size() > 0);

	}

	@Test
	public void doSearchList() {
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationTitle("2");

		EducationListVO searchedListVO = new EducationListVO();
		Paging paging = new Paging(15, 15);

		searchedListVO.setPaging(paging);
		paging.setPageNumber(0 + "");

		int searchedEducationCount = educationBiz.getSearchedEducationCount(educationVO);
		assertTrue(searchedEducationCount > 0);

		paging.setTotalArticleCount(searchedEducationCount);

		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		// searchList 에러
		List<EducationVO> searchedEducations = educationBiz.doSearchList(educationVO, searchVO);
		assertTrue(searchedEducations.size() > 0);
	}

	@Test
	public void getOneEducationDetail() {

		String educationId = "ED-20160512-000066";

		EduReplyListVO eduReplyListVO = new EduReplyListVO();
		Paging paging = new Paging(10, 10);

		eduReplyListVO.setPaging(paging);
		paging.setPageNumber(0 + "");

		int totalEduReplyCount = educationBiz.getEduReplyCount(educationId);
		assertTrue(totalEduReplyCount > 0);
		paging.setTotalArticleCount(totalEduReplyCount);

		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		educationId = "ED-20160519-000227";
		EducationVO education = educationBiz.getOneEducationDetail(educationId);
		assertNotNull(education);
		// List<FileVO> fileList = fileBiz.getOneFileId(educationId);
		educationId = "ED-20160512-000066";
		List<QNAVO> qnas = educationBiz.getAllCommentByEducationId(educationId, searchVO);
		assertTrue(qnas.size() > 0);
		eduReplyListVO.setQnaList(qnas);

		// 이미 신청된 회원인지 비교해서 boolean 값 보내기
		// MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		String loginMember = "huhu";
		// assertTrue(educationBiz.isApplyMemberByEducationId(educationId,
		// loginMember) > 0 );

	}

	@Test
	public void viewRequestRetractionPageTest() {

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("egipyo21");
		session.setAttribute(Session.MEMBER, memberVO);

		String educationId = "ED-20160524-000288";

		ModelAndView view = educationService.viewRequestRetractionPage(session, educationId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/retraction");
	}

	@Test(expected = RuntimeException.class)
	public void viewRequestRetractionPageTestWithError1() {

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("egipyo21");
		session.setAttribute(Session.MEMBER, memberVO);

		String educationId = "NO-EXIST-EDUCATION-ID";

		educationService.viewRequestRetractionPage(session, educationId);
	}

	@Test
	public void viewRequestRetractionPageTestWithError2() {

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);

		String educationId = "ED-20160516-000181";

		ModelAndView view = educationService.viewRequestRetractionPage(session, educationId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/member/myPage/course");
	}

	@Test
	public void doRequestRetractionTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "ED-20160524-000288");
		request.setParameter("retractionMessage", "하기싫어요");

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("egipyo21");
		session.setAttribute(Session.MEMBER, memberVO);

		String result = educationService.doRequestRetraction(request, session);

		assertNotNull(result);
		assertEquals(result, "redirect:/member/myPage");
	}

	@Test(expected = RuntimeException.class)
	public void doRequestRetractionTestWithError1() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "NO-EXIST-EDUCATION-ID");

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);

		educationService.doRequestRetraction(request, session);
	}

	@Test
	public void doRequestRetractionTestWithError2() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "ED-20160527-000344");
		request.setParameter("retractionMessage", "하기싫어요");

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("egipyo21");
		session.setAttribute(Session.MEMBER, memberVO);

		String result = educationService.doRequestRetraction(request, session);
		assertNotNull(result);
		assertEquals(result, "redirect:/member/myPage/course");
	}

	@Test
	public void doReReplyInsertTest() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);

		String replyId = "RP-20160513-000096";
		String eduId = "ED-20160513-000130";
		String id = "test02";
		String description = "JUNIT TEST DESCRIPTION";

		String checkStr = educationService.doReReplyInsert(replyId, eduId, id, description, session);
		assertNotNull(checkStr);
	}
	
	/**
	 * 민정
	 */
	@Test
	public void doReportSubmitTest() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);
		
		ReportReplyVO reportReplyVO = new ReportReplyVO();
		
		reportReplyVO.setRptRplId("JUnit Test");
		reportReplyVO.setBbsId("Junit");
		reportReplyVO.setMbrId("Junit");
		reportReplyVO.setCreatedDate("Junit");
		
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
		
		ModelAndView view = educationService.doReportSubmit(reportReplyVO, request, session);
		assertNotNull(view);
	}

	@Test
	public void plusReReplyLikeTest() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);
		String replyId = "RP-20160513-000094";

		String checkStr = educationService.plusReReplyLike(replyId, session);
		assertNotNull(checkStr);
	}

	@Test
	public void plusReReplyDislikeTest() {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);

		String replyId = "RP-20160517-000204";
		String checkStr = educationService.plusReReplyDislike(replyId, session);
		assertNotNull(checkStr);
	}

	@Test
	public void showMyQNAListTest() {

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMemberId");
		session.setAttribute(Session.MEMBER, memberVO);

		ModelAndView view = educationService.showMyQNAList(null, session);
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "myPage/myQNAList");
			QNAListVO qnaListVO = (QNAListVO) view.getModel().get("qnaListVO");
			if(qnaListVO != null) {
				assertNotNull(qnaListVO.getPaging());
				List<QNAVO> qnaList = qnaListVO.getQnaList();
				
				if(qnaList != null) {
					
					for (QNAVO qnavo : qnaList) {
						assertNotNull(qnavo.getReplyId());
					}
				} else {
					fail("fail");
				}
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void modifyReportTest() {
		
		// MockHttpSession
		MockHttpSession session = new MockHttpSession();
		
		// EducationReportVO
		MemberVO loginMember = new MemberVO();
		loginMember.setId("testMemberId");
		session.setAttribute(Session.MEMBER, loginMember);

		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setArticleId("testArticleId");
		educationReportVO.setEducationId("testEducationId");
		educationReportVO.setMemberId("testMemberId");
		educationReportVO.setTitle("testTitle");
		educationReportVO.setContents("testContents");
		educationReportVO.setStartDate("2016-05-25");
		educationReportVO.setEndDate("2016-05-26");
		
		ModelAndView view = educationService.modifyReport(educationReportVO, session);
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/reportWrite");
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void modifyReportErrorTest() {
		
		// MockHttpSession
		MockHttpSession session = new MockHttpSession();
		
		// EducationReportVO
		MemberVO loginMember = new MemberVO();
		loginMember.setId("newTestMemberId");
		session.setAttribute(Session.MEMBER, loginMember);

		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setArticleId("testArticleId");
		educationReportVO.setEducationId("testEducationId");
		educationReportVO.setMemberId("testMemberId");
		educationReportVO.setTitle("testTitle");
		educationReportVO.setContents("testContents");
		educationReportVO.setStartDate("2016-05-25");
		educationReportVO.setEndDate("2016-05-26");
		
		ModelAndView view = educationService.modifyReport(educationReportVO, session);
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/detailReport/" + educationReportVO.getArticleId());
			
		} else {
			fail("fail");
		}
	}
		
	@Test
	public void doModifyReportTest() {
		// MockHttpSession
		MockHttpSession session = new MockHttpSession();
		
		// EducationReportVO
		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setArticleId("testArticleId");
		educationReportVO.setEducationId("testEducationId");
		educationReportVO.setMemberId("testMemberId");
		educationReportVO.setTitle("testTitle");
		educationReportVO.setContents("testContents");
		educationReportVO.setStartDate("2016-05-25");
		educationReportVO.setEndDate("2016-05-26");
		
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
		
		ModelAndView view = educationService.doModifyReport(educationReportVO, session, request);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/detailReport/" + educationReportVO.getArticleId());
		}
		else {
			fail("fail");
		}
		
	}
	
	@Test
	public void deleteReportErrorTest() {
		
		// MockHttpSession
		MockHttpSession session = new MockHttpSession();
		
		// EducationReportVO
		MemberVO loginMember = new MemberVO();
		loginMember.setId("newTestMemberId");
		session.setAttribute(Session.MEMBER, loginMember);

		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setArticleId("testArticleId");
		educationReportVO.setEducationId("testEducationId");
		educationReportVO.setMemberId("testMemberId");
		educationReportVO.setTitle("testTitle");
		educationReportVO.setContents("testContents");
		educationReportVO.setStartDate("2016-05-25");
		educationReportVO.setEndDate("2016-05-26");
		
		String view = educationService.deleteReport(educationReportVO, session);
		assertNotNull(view);
		assertEquals(view, "redirect:/education/detailReport/" + educationReportVO.getArticleId());
		
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
	public void viewNoticeDetailPageTest(){
		
		String eduNoticeId =  educationDAO.getOneEduNoticeId();
		
		MemberVO memberVO = new MemberVO();
		String memberId = memberVO.getId();
		
		ModelAndView view = educationService.viewNoticeDetailPage(memberId, eduNoticeId);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/eduFileNoticeDetail");
	}
	
	@Test
	public void viewEduBoardQNADetailPageTest() {
		
		String atcId = "AT-20160524-000034";
		int pageNo = 0;
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("test02");
		session.setAttribute("_MEMBER_", member);
		
		ModelAndView view = educationService.viewEduBoardQNADetailPage(atcId, pageNo, session);
		assertNotNull(view);
		
	}
	
	@Test
	public void doQNAWriteTest() {
		
		String educationId = "ED-20160519-000233";

		EducationQNABBSVO eduBBS = new EducationQNABBSVO();
		MockHttpSession session = new MockHttpSession();
		
		eduBBS.setTitle("JUNIT_TITLE");
		eduBBS.setContents("JUNIT_CONTENTS");
		BindingResult errors = new BeanPropertyBindingResult(eduBBS, "eduBBS");
		
		MemberVO member = new MemberVO();
		member.setId("test02");
		session.setAttribute("_MEMBER_", member);
		
		ModelAndView view = educationService.doQNAWrite(eduBBS, errors, session, educationId);
		assertNotNull(view);
		
	}
	
	@Test
	public void getAllEducationQNAListTest() {
		int pageNo = 0;
		String educationId = "ED-20160519-000233"; 
		String searchKeyword = "test";
		String searchType = "selectMbrId";
		MockHttpSession session = new MockHttpSession();
		
		
		ModelAndView view = educationService.getAllEducationQNAList(pageNo, educationId, searchKeyword, searchType, session);
		assertNotNull(view);
	}
	
	@Test
	public void doAdoptReplyTest() {
		String replyId = "RP-20160524-000830";
		
		String result = educationService.doAdoptReply(replyId);
		assertTrue(result == "OK");
	}
	
	@Test 
	public void plusOpposeReplyTest() {
		String replyId = "RP-20160524-000830";
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("test02");
		session.setAttribute("_MEMBER_", member);
		
		String result = educationService.plusOpposeReply(replyId, session);
		assertTrue(result == "OK");
	}
	
	@Test
	public void plusRecommendReplyTest() {
		String replyId = "RP-20160524-000830";
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("test02");
		session.setAttribute("_MEMBER_", member);
		
		String result = educationService.plusRecommendReply(replyId, session);
		assertTrue(result == "OK");
	}
	
	@Test
	public void showEducationFileBBSPageTest () {
		FileBBSSearchVO searchVO = new FileBBSSearchVO();
		String educationId = "ED-20160513-000176";
		int pageNo = 0;
		
		ModelAndView view = educationService.showEducationFileBBSPage(educationId, searchVO, pageNo);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/educationFileBBS");
			
			assertNotNull(view.getModelMap().get("searchVO"));
			assertNotNull(view.getModelMap().get("educationFileBBSList"));
			assertNotNull(view.getModelMap().get("teacherId"));
			assertNotNull(view.getModelMap().get("eduNoticeListVO"));
		}
		else {
			fail("실패....");
		}
	}
	
	@Test
	public void getEduBoardByEducationIdTest () {
		String educationId = "ED-20160520-000278";
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		
		member.setId("oph312");
		session.setAttribute(Session.MEMBER, member);
		
		ModelAndView view = educationService.getEduBoardByEducationId(educationId, session);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "myPage/educationBBS");
			
			assertNotNull(view.getModelMap().get("memberList"));
			assertNotNull(view.getModelMap().get("educationVO"));
			assertNotNull(view.getModelMap().get("educationItems"));
			assertNotNull(view.getModelMap().get("educationQNAList"));
			assertNotNull(view.getModelMap().get("educationReportList"));
		}
		else {
			fail("실패....");
		}
		
	}
	
	@Test
	public void doWriteEducationFileBBSActionTest () {
		EducationFileBBSVO educationFileBBSVO = new EducationFileBBSVO();
		educationFileBBSVO.setEducationId("ED-20160513-000176");
		educationFileBBSVO.setTitle("타이틀");
		educationFileBBSVO.setContents("내용");
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("teacher02");
		session.setAttribute(Session.MEMBER, member);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		ModelAndView view = educationService.doWriteEducationFileBBSAction(educationFileBBSVO, request, session);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/fileBBS/" + educationFileBBSVO.getEducationId());
		}
		else {
			fail("실패....");
		}
	}
	
	@Test
	public void showWriteFileBBSPageTest () {
		String educationId = "ED-20160513-000176";
		
		ModelAndView view = educationService.showWriteFileBBSPage(educationId);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/writeEducationFileBBS");
		}
		else {
			fail("실패....");
		}
	}
	
	@Test
	public void showDetailEducationFileBBSTest () {
		String articleId = "FL-20160524-000041";
		int pageNo = 0;
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("teacher02");
		session.setAttribute(Session.MEMBER, member);
		
		ModelAndView view = educationService.showDetailEducationFileBBS(articleId, pageNo, session);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/detailEducationFileBBS");
			
			assertNotNull(view.getModelMap().get("replyList"));
			assertNotNull(view.getModelMap().get("fileList"));
			assertNotNull(view.getModelMap().get("educationFileBBS"));
		}
		else {
			fail("실패....");
		}
	}
	
	@Test
	public void writeFileBBSReplyTest () {
		BBSReplyVO bbsReplyVO = new BBSReplyVO();
		bbsReplyVO.setArticleId("FL-20160524-000041");
		bbsReplyVO.setOrderNo(0);
		bbsReplyVO.setDescription("내용");
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("teacher02");
		session.setAttribute(Session.MEMBER, member);
		
		ModelAndView view = educationService.writeFileBBSReply(bbsReplyVO, session);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/fileBBS/detail/" + bbsReplyVO.getArticleId());
		}
		else {
			fail("실패....");
		}
	}

	@Test
	public void deleteFileBBSTest () {
		EducationFileBBSVO educationFileBBSVO = new EducationFileBBSVO();
		educationFileBBSVO.setMemberId("teacher02");
		educationFileBBSVO.setEducationId("ED-20160513-000176");
		educationFileBBSVO.setArticleId("FL-20160524-000041");
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("teacher02");
		session.setAttribute(Session.MEMBER, member);
		
		ModelAndView view = educationService.deleteFileBBS(educationFileBBSVO, session);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/fileBBS/" + educationFileBBSVO.getEducationId());
		}
		else {
			fail("실패....");
		}
	}
	
	@Test
	public void modifyFileBBSTest () {
		EducationFileBBSVO educationFileBBSVO = new EducationFileBBSVO();
		educationFileBBSVO.setArticleId("FL-20160524-000041");
		educationFileBBSVO.setTitle("수정");
		educationFileBBSVO.setContents("수정");
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		String fileDelete = "N";
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("teacher02");
		session.setAttribute(Session.MEMBER, member);
		
		ModelAndView view = educationService.modifyFileBBS(educationFileBBSVO, request, fileDelete, session);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/fileBBS/detail/" + educationFileBBSVO.getArticleId());
		}
		else {
			fail("실패....");
		}
	}
	
	@Test
	public void showModifyFileBBSTest () {
		EducationFileBBSVO educationFileBBSVO = new EducationFileBBSVO();
		educationFileBBSVO.setMemberId("teacher02");
		educationFileBBSVO.setArticleId("FL-20160524-000041");
		educationFileBBSVO.setTitle("수정");
		educationFileBBSVO.setContents("수정");
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("teacher02");
		session.setAttribute(Session.MEMBER, member);
		
		ModelAndView view = educationService.showModifyFileBBS(educationFileBBSVO, session);
		
		if (view != null) {
			assertNotNull(view);
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/writeEducationFileBBS");
			
			assertNotNull(view.getModelMap().get("educationFileBBSVO"));
			assertNotNull(view.getModelMap().get("isModify"));
			assertNotNull(view.getModelMap().get("fileList"));
		}
		else {
			fail("실패....");
		}
	}
	
	
	
	
}
