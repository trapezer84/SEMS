package com.ktds.sems.education.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.validation.constraints.AssertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.BBSReplySearchVO;
import com.ktds.sems.education.vo.BBSReplyVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaListVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EducationFileBBSVO;
import com.ktds.sems.education.vo.EducationQNABBSSearchVO;
import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplyListVO;
import com.ktds.sems.education.vo.EducationQNAReplySearchVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.Testable;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.FileBBSSearchVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.ReportReplySearchVO;
import com.ktds.sems.education.vo.ReportReplyVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class EducationBizTest extends SemsTestCase {

	@Autowired
	private EducationBiz educationBiz;

	@Autowired
	private EducationDAO educationDAO;

	@Before
	public void setUp() {
		testHelper(new Testable() {

			@Override
			public void preparedTest() {
				EducationReportVO educationReportVO = new EducationReportVO();
				educationReportVO.setArticleId("testArticleId");
				educationReportVO.setEducationId("testEducationId");
				educationReportVO.setMemberId("testMemeberId");
				educationReportVO.setTitle("testTitle");
				educationReportVO.setContents("testContents");
				educationReportVO.setStartDate("2016-05-26");
				educationReportVO.setEndDate("2016-05-30");
				educationBiz.doReportWriteAction(educationReportVO);
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
				educationBiz.deleteReport(educationReportVO);
			}
		});
	}

	@Test
	public void hasApplyHistoryTest() {
		String memberId = "test04";
		String educationId = "ED-20160516-000181";
		boolean hasApplyHistory = educationBiz.hasApplyHistory(memberId, educationId);
		assertNotNull(hasApplyHistory);
		assertTrue(hasApplyHistory);
	}

	@Test
	public void isEducationStartedTest() {
		String educationId = "ED-20160516-000181";
		boolean isEducationStarted = educationBiz.isEducationStarted(educationId);
		assertNotNull(isEducationStarted);
		assertTrue(isEducationStarted);
	}

	@Test
	public void doRequestRetractionTest() {
		String educationId = "ED-20160516-000181";
		String retractionMsg = "듣기 싫어요";
		String memberId = "test04";
		boolean result = educationBiz.doRequestRetraction(educationId, retractionMsg, memberId);
		assertNotNull(result);
		assertTrue(result);
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
	public void doReReplyInsertTest() {
		QNAVO qnaVO = new QNAVO();
		String realReplyId = "JUNIT Test replyId44";
		String replyId = "RP-20160513-000086";
		String eduId = "ED-20160513-000130";
		String description = "JUNIT TEST DESCRIPTION";
		// 강의ID
		qnaVO.setEduId(eduId);
		// 대댓글 ID
		qnaVO.setReplyId(realReplyId);
		// 댓글ID
		qnaVO.setParentReplyId(replyId);
		// 내용
		qnaVO.setDescription(description);
		// 답글 쓴 아이디를 집어넣음
		qnaVO.setMbrId("admin01");

		boolean checkBoolean = educationBiz.doReReplyInsert(qnaVO);
		assertTrue(checkBoolean);
		assertTrue(educationBiz.doReReplyDelete(qnaVO));
	}
	
	@Test
	public void getEmailTest() {
		String id = "test02";
		String checkStr = educationBiz.getEmail(id);
		assertNotNull(checkStr);
	}

	@Test
	public void getSelectedQNA() {
		QNAVO qnaVO = new QNAVO();
		String replyId = "RP-20160513-000096";
		qnaVO = educationBiz.getSelectedQNA(replyId);
		assertNotNull(qnaVO);
	}

	@Test
	public void getNextReReplyEvalTest() {
		int checkInt = educationBiz.getNextReReplyEval();
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

	/*
	 * 새로운 Reply Id값으로 설정해야함
	 * 
	 * @Test public void insertReReplyEvalTest(){ ReRplyEvalVO reRplyEvalVO =
	 * new ReRplyEvalVO(); //댓글ID reRplyEvalVO.setReplyId("RP-20160517-000202");
	 * 
	 * // 좋아요 누른 아이디 reRplyEvalVO.setMbrId("test01");
	 * 
	 * // REPLY_EVAL_ID (pk) reRplyEvalVO.setReplyEvalId("JUNIT TEST EVAL ID2");
	 * 
	 * boolean checkBoolean = educationBiz.insertReReplyEval(reRplyEvalVO);
	 * assertTrue(checkBoolean);
	 * assertTrue(educationBiz.deleteReReplyEval(reRplyEvalVO)); }
	 */

	@Test
	public void plusReReplyLikeTest() {
		String replyId = "RP-20160517-000202";
		boolean checkBoolean = educationBiz.plusReReplyLike(replyId);
		assertTrue(checkBoolean);
	}

	/*
	 * 새로운 Reply Id값으로 설정해야함
	 * 
	 * @Test public void insertReReplyEvalByDislikeTest(){ ReRplyEvalVO
	 * reRplyEvalVO = new ReRplyEvalVO(); //댓글ID
	 * reRplyEvalVO.setReplyId("RP-20160517-000202");
	 * 
	 * // 좋아요 누른 아이디 reRplyEvalVO.setMbrId("test01");
	 * 
	 * // REPLY_EVAL_ID (pk) reRplyEvalVO.setReplyEvalId("JUNIT TEST EVAL ID");
	 * 
	 * boolean checkBoolean =
	 * educationBiz.insertReReplyEvalByDislike(reRplyEvalVO);
	 * assertTrue(checkBoolean);
	 * assertTrue(educationBiz.deleteReReplyEval(reRplyEvalVO)); }
	 */

	@Test
	public void plusReReplyDislikeTest() {
		String replyId = "RP-20160517-000202";
		boolean checkBoolean = educationBiz.plusReReplyDislike(replyId);
		assertTrue(checkBoolean);
	}

	@Test
	public void getAllQNAListTest() {

		QNASearchVO qnaSearchVO = new QNASearchVO();
		qnaSearchVO.setId("test02");

		Paging paging = new Paging();
		paging.setPageNumber(0 + "");

		int totalCount = educationBiz.getTotalQNACount(qnaSearchVO);
		paging.setTotalArticleCount(totalCount);

		qnaSearchVO.setPageNo(0);
		qnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		qnaSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<QNAVO> qnaList = educationBiz.getAllQNAList(qnaSearchVO);

		if (qnaList != null) {

			for (QNAVO qnavo : qnaList) {
				assertNotNull(qnavo.getReplyId());
				assertNotNull(qnavo.getEduId());
				assertNotNull(qnavo.getCreatedDate());
				assertNotNull(qnavo.getDescription());
			}

		} else {
			fail("fail");
		}
	}

	@Test
	public void getSelectedQNATest() {
		String replyId = "RP-20160517-000202";
		QNAVO qnaVO = educationBiz.getSelectedQNA(replyId);
		if (qnaVO != null) {
			assertNotNull(qnaVO);
		} else {
			fail("fail");
		}
	}

	@Test
	public void getSelectedQNAAnswerTest() {
		// List<QNAVO>
		String replyId = "RP-20160517-000202";
		List<QNAVO> qnaList = educationBiz.getSelectedQNAAnswer(replyId);

		if (qnaList != null) {

			for (QNAVO qnavo : qnaList) {
				assertNotNull(qnavo.getReplyId());
				assertNotNull(qnavo.getEduId());
				assertNotNull(qnavo.getParentReplyId());
				assertNotNull(qnavo.getCreatedDate());
				assertNotNull(qnavo.getOrderNo());
				assertNotNull(qnavo.getDescription());
				assertNotNull(qnavo.getMbrId());
				assertNotNull(qnavo.getLikeCnt());
				assertNotNull(qnavo.getDislikeCnt());
			}

		} else {
			fail("fail");
		}

	}

	@Test
	public void exportQNAListAsExcelTest() {
		String memberId = "test02";
		boolean test = educationBiz.exportQNAListAsExcel(memberId);
		assertTrue(test);
	}

	@Test
	public void getTotalReportReplyCountTest() {
		ReportReplySearchVO reportReplySearchVO = new ReportReplySearchVO();
		reportReplySearchVO.setMbrId("test02");
		int count = educationBiz.getTotalReportReplyCount(reportReplySearchVO);
		assertNotNull(count);
		assertTrue(count >= 0);
	}

	@Test
	public void getTotalReportReplyCountOfTeacherTest() {
		ReportReplySearchVO reportReplySearchVO = new ReportReplySearchVO();
		reportReplySearchVO.setMbrId("gangsa3");
		int count = educationBiz.getTotalReportReplyCountOfTeacher(reportReplySearchVO);
		assertNotNull(count);
		assertTrue(count > 0);
	}

	@Test
	public void getAllReportReplyTest() {
		ReportReplySearchVO reportReplySearchVO = new ReportReplySearchVO();
		reportReplySearchVO.setMbrId("test02");
		reportReplySearchVO.setStartIndex(1);
		reportReplySearchVO.setEndIndex(10);
		List<ReportReplyVO> reports = educationBiz.getAllReportReply(reportReplySearchVO);
		assertNotNull(reports);
		assertTrue(reports.size() >= 0);
	}

	@Test
	public void getAllReportReplyOfTeacher() {
		ReportReplySearchVO reportReplySearchVO = new ReportReplySearchVO();
		reportReplySearchVO.setMbrId("gangsa3");
		reportReplySearchVO.setStartIndex(1);
		reportReplySearchVO.setEndIndex(10);
		List<ReportReplyVO> reports = educationBiz.getAllReportReplyOfTeacher(reportReplySearchVO);
		assertNotNull(reports);
		assertTrue(reports.size() > 0);
	}

	/**
	 * Education Report Search Title
	 */
	@Test
	public void getAllEducationReportListTest_SearchTitle() {

		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setPageNo(0);

		Paging paging = new Paging();
		paging.setPageNumber(educationReportSearchVO.getPageNo() + "");

		int totalCount = educationBiz.getTotalEducationReportCount(educationReportSearchVO);
		paging.setTotalArticleCount(totalCount);

		educationReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		educationReportSearchVO.setEndIndex(paging.getEndArticleNumber());
		educationReportSearchVO.setEducationId("testEducationId");
		educationReportSearchVO.setSearchType("title");
		educationReportSearchVO.setSearchKeyword("test");

		List<EducationReportVO> educationReportList = educationBiz.getAllEducationReportList(educationReportSearchVO);
		if (educationReportList != null) {
			for (EducationReportVO educationReportVO : educationReportList) {

				assertNotNull(educationReportVO.getArticleId());
				assertNotNull(educationReportVO.getEducationId());
				assertNotNull(educationReportVO.getMemberId());
				assertNotNull(educationReportVO.getTitle());
				assertNotNull(educationReportVO.getStartDate());
				assertNotNull(educationReportVO.getEndDate());

			}
		} else {
			fail("fail");
		}
	}

	/**
	 * Education Report Search startDate
	 */
	@Test
	public void getAllEducationReportListTest_SearchStartDate() {

		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setPageNo(0);

		Paging paging = new Paging();
		paging.setPageNumber(educationReportSearchVO.getPageNo() + "");

		int totalCount = educationBiz.getTotalEducationReportCount(educationReportSearchVO);
		paging.setTotalArticleCount(totalCount);

		educationReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		educationReportSearchVO.setEndIndex(paging.getEndArticleNumber());
		educationReportSearchVO.setEducationId("testEducationId");
		educationReportSearchVO.setSearchType("startDate");
		educationReportSearchVO.setStartDate("2015-05-01");
		educationReportSearchVO.setEndDate("2017-05-01");

		List<EducationReportVO> educationReportList = educationBiz.getAllEducationReportList(educationReportSearchVO);
		if (educationReportList != null) {
			for (EducationReportVO educationReportVO : educationReportList) {

				assertNotNull(educationReportVO.getArticleId());
				assertNotNull(educationReportVO.getEducationId());
				assertNotNull(educationReportVO.getMemberId());
				assertNotNull(educationReportVO.getTitle());
				assertNotNull(educationReportVO.getStartDate());
				assertNotNull(educationReportVO.getEndDate());

			}
		} else {
			fail("fail");
		}
	}

	/**
	 * Education Report Search endDate
	 */
	@Test
	public void getAllEducationReportListTest_SearchEndDate() {

		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setPageNo(0);

		Paging paging = new Paging();
		paging.setPageNumber(educationReportSearchVO.getPageNo() + "");

		int totalCount = educationBiz.getTotalEducationReportCount(educationReportSearchVO);
		paging.setTotalArticleCount(totalCount);

		educationReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		educationReportSearchVO.setEndIndex(paging.getEndArticleNumber());
		educationReportSearchVO.setEducationId("testEducationId");
		educationReportSearchVO.setSearchType("endDate");
		educationReportSearchVO.setStartDate("2015-05-01");
		educationReportSearchVO.setEndDate("2017-05-01");

		List<EducationReportVO> educationReportList = educationBiz.getAllEducationReportList(educationReportSearchVO);
		if (educationReportList != null) {
			for (EducationReportVO educationReportVO : educationReportList) {

				assertNotNull(educationReportVO.getArticleId());
				assertNotNull(educationReportVO.getEducationId());
				assertNotNull(educationReportVO.getMemberId());
				assertNotNull(educationReportVO.getTitle());
				assertNotNull(educationReportVO.getStartDate());
				assertNotNull(educationReportVO.getEndDate());

			}
		} else {
			fail("fail");
		}
	}

	/**
	 * Education Report Search title
	 */
	@Test
	public void getTotalEducationReportCountTest_SearchTitle() {

		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setSearchType("title");
		educationReportSearchVO.setSearchKeyword("test");

		int totalCount = educationBiz.getTotalEducationReportCount(educationReportSearchVO);
		assertTrue(totalCount > 0);
	}

	/**
	 * Education Report Search startDate
	 */
	@Test
	public void getTotalEducationReportCountTest_SearchStartDate() {

		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setSearchType("startDate");
		educationReportSearchVO.setStartDate("2015-05-01");
		educationReportSearchVO.setEndDate("2017-05-01");

		int totalCount = educationBiz.getTotalEducationReportCount(educationReportSearchVO);
		assertTrue(totalCount > 0);
	}

	/**
	 * Education Report Search endDate
	 */
	@Test
	public void getTotalEducationReportCountTest_SearchEndDate() {

		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setSearchType("endDate");
		educationReportSearchVO.setStartDate("2015-05-01");
		educationReportSearchVO.setEndDate("2017-05-01");

		int totalCount = educationBiz.getTotalEducationReportCount(educationReportSearchVO);
		assertTrue(totalCount > 0);
	}

	/**
	 * Education Report Get One Education Report Test
	 */
	@Test
	public void getOneEducationReportTest() {

		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setArticleId("testArticleId");

		EducationReportVO newEducationReportVO = educationBiz.getOneEducationReport(educationReportVO);
		if (newEducationReportVO != null) {
			assertNotNull(newEducationReportVO.getArticleId());
			assertNotNull(newEducationReportVO.getEducationId());
			assertNotNull(newEducationReportVO.getMemberId());
			assertNotNull(newEducationReportVO.getTitle());
			assertNotNull(newEducationReportVO.getStartDate());
			assertNotNull(newEducationReportVO.getEndDate());

		} else {
			fail("fail");
		}
	}

	/**
	 * Education Report Get One Education Report Test - Error
	 */
	@Test
	public void getOneEducationReportTest_Error() {

		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setArticleId("NO_TEST_DATE");

		EducationReportVO newEducationReportVO = educationBiz.getOneEducationReport(educationReportVO);
		if (newEducationReportVO != null) {
			fail("fail");
		}
	}

	@Test
	public void getTotalEduQnaCountTest() {
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		int totalReportCount = educationBiz.getTotalEduQnaCount(eduQnaSearchVO);
		assertTrue(totalReportCount > 0);
	}

	@Test
	public void getAllEduQnaTest() {
		EduQnaListVO eduQnaListVO = new EduQnaListVO();
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		Paging paging = new Paging(20, 20);

		eduQnaListVO.setPaging(paging);
		int totalReportCount = educationBiz.getTotalEduQnaCount(eduQnaSearchVO);
		assertTrue(totalReportCount > 0);

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
		assertTrue(nextEqbSeq > 0);
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
		assertTrue(totalReportCount > 0);
	}

	@Test
	public void getAllEduFileNoticeTest() {

		EduNoticeSearchVO eduNoticeSearchVO = new EduNoticeSearchVO();
		eduNoticeSearchVO.setEducationId(educationDAO.getOneEducationId());

		List<EduNoticeVO> eduNoticeList = educationBiz.getAllEduFileNotice(eduNoticeSearchVO);

		assertNotNull(eduNoticeList);
		if (eduNoticeList != null) {
			assertTrue(eduNoticeList.size() > 0);
		} else {
			fail("Fail...");
		}
	}

	@Test
	public void getOneNoticeTest() {

		String eduNoticeId = educationDAO.getOneEduNoticeId();
		EduNoticeVO noticeVO = educationBiz.getOneNotice(eduNoticeId);
		assertNotNull(noticeVO);

	}

	@Test
	public void getAllQNAReplyListByAtcIdTest() {
		EducationQNAReplySearchVO searchVO = new EducationQNAReplySearchVO();
		searchVO.setAtcId("AT-20160524-000034");
		String atcId = "AT-20160524-000034";
		Paging paging = new Paging();
		paging.setPageNumber(0 + "");

		int totalCount = educationBiz.getTotalQNAReplyCountByAtcId(atcId);
		paging.setTotalArticleCount(totalCount);

		searchVO.setPageNo(0);
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		List<EducationQNAReplyVO> eduQnaReplyList = educationBiz.getAllQNAReplyListByAtcId(searchVO);
		assertNotNull(eduQnaReplyList);

	}

	@Test
	public void getTotalQNAReplyCountByAtcIdTest() {
		String atcId = "AT-20160524-000034";
		int result = educationBiz.getTotalQNAReplyCountByAtcId(atcId);
		assertNotNull(result);
	}

	@Test
	public void plusRecommendReplyTest() {
		String replyId = "ER-20160525-000869";
		boolean result = educationBiz.plusRecommendReply(replyId);
		assertNotNull(result);
		assertTrue(result);
	}

	@Test
	public void plusOpposeReplyTest() {
		String replyId = "ER-20160525-000869";
		boolean result = educationBiz.plusOpposeReply(replyId);
		assertNotNull(result);
		assertTrue(result);
	}

	@Test
	public void updateAdoptReplyTest() {
		String replyId = "ER-20160525-000869";
		boolean result = educationBiz.updateAdoptReply(replyId);
		assertNotNull(result);
		assertTrue(result);
	}

	@Test
	public void checkAdoptReplyTest() {
		String replyId = "ER-20160525-000869";
		boolean result = educationBiz.checkAdoptReply(replyId);
		assertNotNull(result);
	}

	@Test
	public void getAllEducationQNAListTest() {
		EducationQNABBSSearchVO searchVO = new EducationQNABBSSearchVO();
		searchVO.setEducationId("ED-20160519-000233");
		Paging paging = new Paging();
		paging.setPageNumber(0 + "");

		int totalCount = educationBiz.getTotalEducationQNACount(searchVO);
		paging.setTotalArticleCount(totalCount);

		searchVO.setPageNo(0);
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		List<EducationQNABBSVO> eduQnaBBSList = educationBiz.getAllEducationQNAList(searchVO);
		assertNotNull(eduQnaBBSList);

	}

	@Test
	public void getTotalEducationQNACountTest() {
		EducationQNABBSSearchVO searchVO = new EducationQNABBSSearchVO();
		searchVO.setEducationId("ED-20160519-000233");

		int result = educationBiz.getTotalEducationQNACount(searchVO);
		assertNotNull(result);
	}

	@Test
	public void getEducationFileBBSListTest() {
		String educationId = "ED-20160520-000278";

		FileBBSSearchVO searchVO = new FileBBSSearchVO();
		searchVO.setEndIndex(10);
		searchVO.setStartIndex(1);
		searchVO.setEducationId(educationId);

		List<EducationFileBBSVO> educationItems = educationBiz.getEducationFileBBSList(searchVO);

		if (educationItems != null) {
			assertNotNull(educationItems);
		} else {
			fail("실패....");
		}
	}

	@Test
	public void getEducationFileBBSCountListTest() {
		String educationId = "ED-20160520-000278";

		FileBBSSearchVO searchVO = new FileBBSSearchVO();
		searchVO.setEndIndex(10);
		searchVO.setStartIndex(1);
		searchVO.setEducationId(educationId);

		List<EducationFileBBSVO> educationItems = educationBiz.getEducationFileBBSList(searchVO);

		if (educationItems != null) {
			assertNotNull(educationItems);
		} else {
			fail("실패....");
		}
	}

	@Test
	public void getEducationFileBBSCountTest() {
		String educationId = "ED-20160520-000278";

		FileBBSSearchVO searchVO = new FileBBSSearchVO();
		searchVO.setEducationId(educationId);

		int totalBBSCount = educationBiz.getEducationFileBBSCount(searchVO);

		if (totalBBSCount >= 0) {
			assertTrue(totalBBSCount >= 0);
		} else {
			fail("실패....");
		}
	}

	@Test
	public void getOneEducationDetailTest() {
		String educationId = "ED-20160520-000278";

		EducationVO educationVO = educationBiz.getOneEducationDetail(educationId);

		if (educationVO != null) {
			assertNotNull(educationVO);
		} else {
			fail("실패....");
		}
	}

	@Test
	public void generateArticleIdTest() {
		String articleId = educationBiz.generateArticleId();

		if (articleId != null) {
			assertNotNull(articleId);
		} else {
			fail("실패....");
		}
	}

	@Test
	public void getMemberIdByEducationIdTest() {
		String educationId = "ED-20160520-000278";

		String articleWriterId = educationBiz.getMemberIdByEducationId(educationId);

		if (articleWriterId != null) {
			assertNotNull(articleWriterId);
		} else {
			fail("실패....");
		}
	}

	@Test
	public void writeNewFileBBSTest() {
		EducationFileBBSVO educationFileBBSVO = new EducationFileBBSVO();
		String articleId = educationBiz.generateArticleId();
		educationFileBBSVO.setEducationId("ED-20160513-000176");
		educationFileBBSVO.setTitle("타이틀");
		educationFileBBSVO.setContents("내용");
		educationFileBBSVO.setArticleId(articleId);
		educationFileBBSVO.setMemberId("teacher02");

		boolean isSuccess = educationBiz.writeNewFileBBS(educationFileBBSVO);

		if (isSuccess) {
			assertTrue(isSuccess);
		} else {
			fail("실패....");
		}
	}

	@Test
	public void getOneEducationFileBBSTest() {
		String articleId = "FL-20160524-000041";
		
		EducationFileBBSVO educationFileBBS = educationBiz.getOneEducationFileBBS(articleId);

		if (educationFileBBS != null) {
			assertNotNull(educationFileBBS);
		} else {
			fail("실패....");
		}
	}
	
	@Test
	public void getTotalFileBBSReplyCountByArticleIdTest() {
		String articleId = "FL-20160524-000041";
		
		int totalReplyCount = educationBiz.getTotalFileBBSReplyCountByArticleId(articleId);
		
		if (totalReplyCount >= 0) {
			assertTrue(totalReplyCount >= 0);
		} else {
			fail("실패....");
		}
	}
	
	@Test
	public void getAllBBSReplyByArticleTest() {
		BBSReplySearchVO searchVO = new BBSReplySearchVO();
		searchVO.setArticleId("FL-20160524-000041");
		searchVO.setPageNo(0);
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		
		List<BBSReplyVO> replys = educationBiz.getAllBBSReplyByArticle(searchVO);
		
		if (replys != null) {
			assertNotNull(replys);
		} else {
			fail("실패....");
		}
	}
	
	@Test
	public void generateBBSReplyIdTest() {
		String bbsReplyId = educationBiz.generateBBSReplyId();
		
		if (bbsReplyId != null) {
			assertNotNull(bbsReplyId);
		} else {
			fail("실패....");
		}
	}
	
	@Test
	public void writeFileBBSReplyTest() {
		BBSReplyVO bbsReplyVO = new BBSReplyVO();
		String bbsReplyId = educationBiz.generateBBSReplyId();
		bbsReplyVO.setReplyId(bbsReplyId);
		bbsReplyVO.setMemberId("teacher02");
		bbsReplyVO.setOrderNo(0);
		bbsReplyVO.setArticleId("FL-20160524-000041");
		bbsReplyVO.setDescription("내용");
		
		boolean isSuccess = educationBiz.writeFileBBSReply(bbsReplyVO);
		
		if ( isSuccess ) {
			assertTrue(isSuccess);
		} else {
			fail("실패....");
		}
	}
	
	@Test
	public void getNextOrderNoTest() {
		String parentReplyId = "RP-20160527-001177";
		
		int orderNo = educationBiz.getNextOrderNo(parentReplyId);
		
		if ( orderNo >= 0 ) {
			assertTrue(orderNo >= 0);
		} else {
			fail("실패....");
		}
	}
	
	@Test
	public void deleteFileBBSByArticleIdTest() {
		String articleId = "FL-20160524-000041";
		
		boolean isSuccess = educationBiz.deleteFileBBSByArticleId(articleId);
		
		if ( isSuccess ) {
			assertTrue(isSuccess);
		} else {
			fail("실패....");
		}
	}
	
	@Test
	public void modifyFileBBSTest() {
		EducationFileBBSVO educationFileBBSVO = new EducationFileBBSVO();
		educationFileBBSVO.setEducationId("ED-20160513-000176");
		educationFileBBSVO.setTitle("타이틀");
		educationFileBBSVO.setContents("내용");
		educationFileBBSVO.setArticleId("FL-20160524-000041");
		educationFileBBSVO.setMemberId("teacher02");
		
		boolean isSuccess = educationBiz.modifyFileBBS(educationFileBBSVO);
		
		if ( isSuccess ) {
			assertTrue(isSuccess);
		} else {
			fail("실패....");
		}
	}

}
