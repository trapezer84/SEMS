package com.ktds.sems.education.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.BBSReplyVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EducationFileBBSVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.FileBBSSearchVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplySearchVO;
import com.ktds.sems.education.vo.ReportReplyVO;

public interface EducationService {

	public ModelAndView getOneEducationDetail(String educationId, HttpSession session, int pageNo);
	
	public ModelAndView getAllEducationList(int pageNo);

	public String doApplyEducation(EducationVO educationVO, HttpSession session);
	
	public ModelAndView writeNewComment(HttpSession session, QNAVO qnaVO, Errors errors, String educationId);

	public ModelAndView doSearchList(EducationVO educationVO, int pageNo);

	public String doCancelEducation(String educationId, HttpSession session);

	public void doDownloadFile( String educationId, HttpServletRequest request, HttpServletResponse response);

	public String doTransCostId(String cost);

	public String doTransTypeId(String educationType);

	public ModelAndView showMyQNAList(QNASearchVO qnaSearchVO, HttpSession session);

	public ModelAndView showMyQNADetail(String replyId, HttpSession session);

	public void exportQNAListAsExcel(HttpSession session);

	public String doReReplyInsert(String replyId, String eduId, String id, String description, HttpSession session);

	public ModelAndView viewRequestRetractionPage(HttpSession session, String educationId);

	public String plusReReplyLike(String replyId, HttpSession session);

	public String plusReReplyDislike(String replyId, HttpSession session);

	public String doRequestRetraction(HttpServletRequest request, HttpSession session);

	public String doReserveEducation(String educationId, HttpSession session);
	
	public ModelAndView showEducationFileBBSPage(String educationId, FileBBSSearchVO searchVO, int pageNo);

	public ModelAndView doWriteEducationFileBBSAction(EducationFileBBSVO educationFileBBSVO, MultipartHttpServletRequest request, HttpSession session);

	public ModelAndView showWriteFileBBSPage(String educationId);

	public ModelAndView getAllEducationQNAList(int PageNo, String educationId, String searchKeyword, String searchType, HttpSession session);

	public ModelAndView doQNAWrite(EducationQNABBSVO eduBBS, Errors errors, HttpSession session, String educationId);

	public ModelAndView viewReportListPage(EducationReportSearchVO educationReportSearchVO);

	public ModelAndView viewReportWrite(String educationId, HttpSession session);

	public ModelAndView doReportWriteAction(EducationReportVO educationReportVO, Errors errors, MultipartHttpServletRequest request, HttpSession session);
	
	public ModelAndView doQNAReplyWriteAction(EducationQNAReplyVO eduBBSReplyVO, Errors errors, HttpSession session);
	
	public ModelAndView doReportSubmit(ReportReplyVO reportReplyVO, MultipartHttpServletRequest request, HttpSession session);

	public ModelAndView viewDetailEducationReport(EducationReportVO educationReportVO, HttpSession session, int pageNo);
	
	public ModelAndView getAllReportReply(ReportReplySearchVO reportReplySearchVO, int pageNo, HttpSession session);

	public ModelAndView modifyReport(EducationReportVO educationReportVO, HttpSession session);

	public ModelAndView showDetailEducationFileBBS(String articleId, int pageNo, HttpSession session);

	public ModelAndView doModifyReport(EducationReportVO educationReportVO, HttpSession session, MultipartHttpServletRequest request);

	public void checkEndDate(String articleId, HttpServletResponse response);

	public ModelAndView getJCEduHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo);

	public String deleteReport(EducationReportVO educationReportVO, HttpSession session);

	public ModelAndView viewEduBoardQNADetailPage(String atcId, int pageNo, HttpSession session);

	public ModelAndView getEduBoardByEducationId(String educationId, HttpSession session);

	public String plusRecommendReply(String replyId, HttpSession session);

	public String plusOpposeReply(String replyId, HttpSession session);

	public String doAdoptReply(String replyId);

	public void downloadEducationFile(String fileId, HttpServletRequest request, HttpServletResponse response);
	
	public ModelAndView viewWriteEduQna(String educationId, HttpSession session);

	public ModelAndView doWriteEduQnaAction(EduQnaVO eduQnaVO, HttpSession session);

	public ModelAndView detailOfEduQna(String eduQnaId, String educationId, HttpSession session, int pageNo);

	public ModelAndView doEduQnaReplyAction(EducationQNAReplyVO eduBBSReplyVO, Errors errors, HttpSession session, String educationId);

	public String addQnaEduReplyLike(String replyId, HttpSession session);

	public String addQnaEduReplyDisLike(String replyId, HttpSession session);
	
	public ModelAndView getAllQnaArticle(EduQnaSearchVO qnaArticleSearchVO, int pageNo);

	public void checkClassAttend(String fileId, HttpServletRequest request, HttpServletResponse response);

	public ModelAndView writeFileBBSReply(BBSReplyVO bbsReplyVO, HttpSession session);

	public void writeFileBBSReReply(BBSReplyVO bbsReplyVO, HttpSession session, HttpServletResponse response);

	public ModelAndView deleteFileBBS(EducationFileBBSVO educationFileBBSVO, HttpSession session);

	public ModelAndView modifyFileBBS(EducationFileBBSVO educationFileBBSVO, MultipartHttpServletRequest request, String fileDelete , HttpSession session);

	public ModelAndView showModifyFileBBS(EducationFileBBSVO educationFileBBSVO, HttpSession session);

	public void doReReplyDelete(QNAVO qnaVO);

	public ModelAndView viewNoticeDetailPage(String educationId, String eduNoticeId);

	public void doDownloadFileList(String educationId, String fileId, HttpServletRequest request,
			HttpServletResponse response);

}
