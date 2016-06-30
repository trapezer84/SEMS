package com.ktds.sems.education.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.service.EducationService;
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

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class EducationController {

	private EducationService educationService;
	private Logger logger = LoggerFactory.getLogger(EducationController.class);	
	
	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}
	
	@RequestMapping("/educationList")
	public ModelAndView viewEducationListPage(@RequestParam(required=false, defaultValue="0") int pageNo){
		logger.info("실행");
		logger.info(""+pageNo);
		return educationService.getAllEducationList(pageNo);
	}
	
	@RequestMapping("/calendar")
	public ModelAndView viewEducationCalendarPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("education/calendar");
		
		return view;
	}

	
	@RequestMapping("/searchList")
	public ModelAndView doSearchList(@RequestParam String startYear, @RequestParam String startMonth, 
				@RequestParam String endYear, @RequestParam String endMonth, @RequestParam String eduName,
				@RequestParam String educationType, @RequestParam String cost, @RequestParam(required=false, defaultValue="0") String pageNo){
		logger.info("검색");
		logger.info(startYear);
		logger.info(startMonth);
		logger.info(endYear);
		logger.info(endMonth);
		logger.info(eduName);
		logger.info(educationType);
		logger.info(cost);
		logger.info(pageNo);
		if(pageNo.length() > 1) {
			pageNo = "0";
		}
//		String pageNo =request.getParameter("pageNo");
		EducationVO educationVO = new EducationVO();
		
		if(startMonth.length() > 0 && endMonth.length() > 0 ) {
			if(startMonth.length() == 1) {
				startMonth = "0" + startMonth;
			}
			if(endMonth.length() == 1) {
				endMonth = "0" + endMonth;
			}
			educationVO.setStartDate(startYear + "-" + startMonth);
			educationVO.setEndDate(endYear + "-" + endMonth);
		}
		else {
			educationVO.setStartDate(null);
			educationVO.setEndDate(null);
		}
		if(eduName.equals("")) {
			eduName = null;
		}
		if(educationType.equals("")){
			educationType = null;
		}
		if(cost.equals("")){
			cost = null;
		}
		
		if(eduName != null){
			eduName=eduName.trim().toLowerCase();
			logger.info(eduName);
		}
		if(cost != null){
	//		String costId = educationService.doTransCostId(cost);
			educationVO.setCost(cost);
		}
		
		if(educationType != null){
//			String typeId = educationService.doTransTypeId(educationType);
			educationVO.setEducationType(educationType);
		}
		educationVO.setEducationTitle(eduName);
		educationVO.setStartYear(startYear);
		educationVO.setStartMonth(startMonth);
		educationVO.setEndYear(endYear);
		educationVO.setEndMonth(endMonth);
		
		return educationService.doSearchList( educationVO , Integer.parseInt(pageNo) );
	}
	
	@RequestMapping("/eduDetail/{educationId}")
	public ModelAndView getOneEducationDetail(@PathVariable String educationId, HttpSession session, @RequestParam(required=false, defaultValue="0") int pageNo){
		return educationService.getOneEducationDetail(educationId, session, pageNo);
	}
	
	@RequestMapping("/doApplyEducation")
	public void doApplyEducation(@RequestParam String educationId, @RequestParam String educationType, @RequestParam String startDate
			, @RequestParam String endDate, @RequestParam String startTime, @RequestParam String maxMember, HttpSession session, HttpServletResponse response){
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationId(educationId);
		educationVO.setEducationType(educationType);
		educationVO.setStartDate(startDate);
		educationVO.setEndDate(endDate);
		educationVO.setStartTime(startTime);
		educationVO.setMaxMember(Integer.parseInt(maxMember));
		
		String applyStatus = educationService.doApplyEducation(educationVO, session);
		AjaxUtil.sendResponse(response, applyStatus);
	}
	
	@RequestMapping("/doReserveEducation")
	public void doReserveEducation(@RequestParam String educationId, HttpSession session, HttpServletResponse response){
		String applyStatus = educationService.doReserveEducation(educationId, session);
		AjaxUtil.sendResponse(response, applyStatus);
	}
	
	@RequestMapping("/doWriteComment")
	public ModelAndView doWriteAction(@Valid QNAVO qnaVO, Errors errors, String educationId, HttpSession session){
		return educationService.writeNewComment(session, qnaVO, errors, educationId);
	}

	@RequestMapping("/downloadFile/{educationId}")
	public void doDownloadFile(@PathVariable String educationId, HttpServletRequest request, HttpServletResponse response){
		educationService.doDownloadFile(educationId, request, response);
	}
	
	@RequestMapping("/downloadFileList/{educationId}&{fileId}")
	public void doDownloadFileList(@PathVariable String educationId, @PathVariable String fileId, HttpServletRequest request, HttpServletResponse response){
		educationService.doDownloadFileList(educationId, fileId, request, response);
	}

	@RequestMapping("/doCancelEducation/{educationId}")
	public String doCancelEducation(@PathVariable String educationId,  HttpSession session) {
		return educationService.doCancelEducation(educationId, session);
	}
	
	@RequestMapping("/myPage/myQNAList")
	public ModelAndView showMyQNAList(QNASearchVO qnaSearchVO,  HttpSession session) {
		return educationService.showMyQNAList(qnaSearchVO, session);
	}
	
	@RequestMapping("/myPage/myQNAListInit")
	public String showMyQNAList(HttpSession session) {
		session.removeAttribute(Session.SEARCH_QNA);
		return "redirect:/myPage/myQNAList";
	}
	
	@RequestMapping("/myPage/myQNADetail/{replyId}")
	public ModelAndView showMyQNADetail(@PathVariable String replyId,  HttpSession session) {
		return educationService.showMyQNADetail(replyId, session);
	}
	
	@RequestMapping("/myPage/myQNAList/exportQNA")
	public String exportQNAListAsExcel(HttpSession session) {
		educationService.exportQNAListAsExcel(session);
		return "redirect:/myPage/myQNAList";
		
	}
	
	@RequestMapping("/doReReplyInsert")
	public void doReReplyInsert(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		String replyId = request.getParameter("replyId");
		String eduId = request.getParameter("eduId");
		String id = request.getParameter("id");
		String description = request.getParameter("description");

		String status = educationService.doReReplyInsert(replyId, eduId, id, description,session);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/education/retraction/{educationId}")
	public ModelAndView viewRequestRetractionPage(HttpSession session, @PathVariable String educationId){
		return educationService.viewRequestRetractionPage(session, educationId);
	}
	
	@RequestMapping("/plusReReplyLike")
	public void plusReReplyLike(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String replyId = request.getParameter("replyId");
		
		String status = educationService.plusReReplyLike(replyId, session);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/plusReReplyDislike")
	public void plusReReplyDislike(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String replyId = request.getParameter("replyId");
		
		String status = educationService.plusReReplyDislike(replyId, session);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/education/doRetraction")
	public String doRequestRetractionAction(HttpServletRequest request, HttpSession session){
		return educationService.doRequestRetraction(request, session);
	}

	@RequestMapping("/eduBoard/QNAList/{educationId}")
	public ModelAndView viewEduBoardQNAListPage(@RequestParam(required=false, defaultValue="0") int pageNo, @PathVariable String educationId, 
			@RequestParam(required = false, defaultValue = "") String searchKeyword, @RequestParam(required = false, defaultValue = "") String searchType, HttpSession session ) {
		return educationService.getAllEducationQNAList(pageNo, educationId, searchKeyword, searchType, session);
	}
	
	@RequestMapping("/education/fileBBS/{educationId}")
	public ModelAndView viewEducationFileBBSPage (@PathVariable String educationId, FileBBSSearchVO searchVO, @RequestParam(required = false, defaultValue = "0") int pageNo) {
		return educationService.showEducationFileBBSPage(educationId, searchVO, pageNo);
	}
	
	@RequestMapping("/education/writeFileBBS")
	public ModelAndView viewWriteFileBBSPage (@RequestParam String educationId) {
		return educationService.showWriteFileBBSPage(educationId);
	}
	
	@RequestMapping(value="/education/doWriteFileBBSAction", method=RequestMethod.POST)
	public ModelAndView doWriteEducationFileBBSAction (EducationFileBBSVO educationFileBBSVO, MultipartHttpServletRequest request, HttpSession session) {
		return educationService.doWriteEducationFileBBSAction(educationFileBBSVO, request, session);
	}
	
	@RequestMapping("/eduBoard/QNAWrite/{educationId}")
	public ModelAndView viewEduBoardQNAWritePage(@PathVariable String educationId) {
		ModelAndView view = new ModelAndView();
		view.addObject("educationId", educationId);
		view.setViewName("myPage/eduBoardQNAWrite");
		return view;
	}
	
	@RequestMapping("/eduBoard/doQNAWrite/{educationId}")
	public ModelAndView doQNAWriteAction(@Valid EducationQNABBSVO eduBBS, Errors errors, HttpSession session, @PathVariable String educationId) {
		return educationService.doQNAWrite(eduBBS, errors, session, educationId);
	}
	
	@RequestMapping("/education/reportList/{educationId}")
	public ModelAndView viewReportListPage(EducationReportSearchVO educationReportSearchVO){
		return educationService.viewReportListPage(educationReportSearchVO);
	}
	@RequestMapping("/education/viewReportWrite/{educationId}")
	public ModelAndView viewReportWrite(@PathVariable String educationId, HttpSession session) {
		return educationService.viewReportWrite(educationId, session);
	}
	@RequestMapping("/eduBoard/QNADetail/{atcId}")
	public ModelAndView viewEduBoardQNADetailPage(@PathVariable String atcId, @RequestParam(required=false, defaultValue="0") int pageNo, HttpSession session) {
		return educationService.viewEduBoardQNADetailPage(atcId, pageNo, session);
	}
	
	@RequestMapping("/education/doReportWriteAction")
	public ModelAndView doReportWriteAction(@Valid EducationReportVO educationReportVO, Errors errors, MultipartHttpServletRequest request, HttpSession session) {
		
		return educationService.doReportWriteAction(educationReportVO, errors, request, session);
	}
	
	@RequestMapping("/doWriteReply")
	public ModelAndView doQNAReplyWriteAction(@Valid EducationQNAReplyVO eduBBSReplyVO, Errors errors, HttpSession session) {
		return educationService.doQNAReplyWriteAction(eduBBSReplyVO, errors, session);
	}
	
	@RequestMapping("/doReportSubmit")
	public ModelAndView doReportSubmit(ReportReplyVO reportReplyVO, MultipartHttpServletRequest request, HttpSession session){
		return educationService.doReportSubmit(reportReplyVO, request, session);
	}
	
	@RequestMapping("/myPage/myReportList")
	public ModelAndView viewMyReportListPage(ReportReplySearchVO reportReplySearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo, HttpSession session){
		return educationService.getAllReportReply(reportReplySearchVO, pageNo, session);
	}
	
	@RequestMapping("/education/detailReport/{articleId}")
	public ModelAndView viewDetailEducationReport(EducationReportVO educationReportVO, HttpSession session, @RequestParam(required = false, defaultValue = "0") int pageNo) {
	return educationService.viewDetailEducationReport(educationReportVO, session, pageNo);
	}
	
	@RequestMapping("/myPage/myReportListInit")
	public String myReportListInit() {
		return "redirect:/myPage/myReportList";
	}
	
	@RequestMapping("/education/fileBBS/detail/{articleId}")
	public ModelAndView viewDetailEducationFileBBSPage (@PathVariable String articleId, @RequestParam(required = false, defaultValue = "0") int pageNo, HttpSession session) {
		return educationService.showDetailEducationFileBBS(articleId, pageNo, session);
	}
	
	@RequestMapping("/checkEduApplicant")
	public ModelAndView viewCheckApplicantPage(EducationHistorySearchVO eduHistorySearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo) {
		// JC (JOIN_CMPL)
		ModelAndView view = educationService.getJCEduHistory(eduHistorySearchVO, pageNo);
		return view;
	}
	
	@RequestMapping("/education/modifyReport/{articleId}")
	public ModelAndView modifyReport(EducationReportVO educationReportVO, HttpSession session) {
		return educationService.modifyReport(educationReportVO, session);
	}
	@RequestMapping("/education/deleteReport/{articleId}")
	public String deleteReport(EducationReportVO educationReportVO, HttpSession session) {
		return	educationService.deleteReport(educationReportVO, session);
	}
	
	@RequestMapping("/education/doModifyReportAction")
	public ModelAndView doModifyReportAction(EducationReportVO educationReportVO, HttpSession session, MultipartHttpServletRequest request) {
		return educationService.doModifyReport(educationReportVO, session, request);
	}
	
	@RequestMapping("/checkEndDate")
	public void checkEndDate(@RequestParam String articleId, HttpServletResponse response) {
		educationService.checkEndDate(articleId, response);
	}

	@RequestMapping("/eduBoard/{educationId}")
	public ModelAndView viewEduBoardPage(@PathVariable String educationId, HttpSession session) {
		return educationService.getEduBoardByEducationId(educationId, session);
	}
	
	@RequestMapping("/plusRecommendReply")
	public void plusRecommendReply(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String replyId = request.getParameter("replyId");
		
		String status = educationService.plusRecommendReply(replyId, session);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/plusOpposeReply")
	public void plusOpposeReply(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String replyId = request.getParameter("replyId");
		
		String status = educationService.plusOpposeReply(replyId, session);
		AjaxUtil.sendResponse(response, status);
	}

	@RequestMapping("/doAdoptReply")
	public void doAdoptReply(HttpServletRequest request, HttpServletResponse response){
		String replyId = request.getParameter("replyId");
		
		String status = educationService.doAdoptReply(replyId);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/downloadEducationFile/{fileId}")
	public void doDownloadEducationFile(@PathVariable String fileId, HttpServletRequest request, HttpServletResponse response){
		educationService.downloadEducationFile(fileId, request, response);
	}
	
	@RequestMapping("/checkClassAttend/{fileId}")
	public void doCheckClassAttend(@PathVariable String fileId, HttpServletRequest request, HttpServletResponse response){
		educationService.checkClassAttend(fileId, request, response);
	}
	
	@RequestMapping("/education/fileBBS/doWriteReply")
	public ModelAndView doWriteFileBBSReplyAction(BBSReplyVO bbsReplyVO, HttpSession session){
		return educationService.writeFileBBSReply(bbsReplyVO, session);
	}
	
	@RequestMapping("/{educationId}/eduQna")
	public ModelAndView viewQnAPage(EduQnaSearchVO eduQnaSearchVO, @PathVariable String educationId, @RequestParam(required = false, defaultValue = "0") int pageNo){
		eduQnaSearchVO.setEducationId(educationId);
		return educationService.getAllQnaArticle(eduQnaSearchVO, pageNo);
	}
	
	@RequestMapping("/writeEduQna/{educationId}")
	public ModelAndView viewWriteEduQna(@PathVariable String educationId, HttpSession session){
		return educationService.viewWriteEduQna(educationId, session);
	}
	
	@RequestMapping("/doWriteEduQnaAction")
	public ModelAndView doWriteEduQnaAction(EduQnaVO eduQnaVO,  HttpSession session){
		return educationService.doWriteEduQnaAction(eduQnaVO, session);
	}
	
	@RequestMapping("/detailOfEduQna/{eduQnaId}/{educationId}")
	public ModelAndView detailOfEduQna(@PathVariable String eduQnaId, @PathVariable String educationId
			, HttpSession session, @RequestParam(required=false, defaultValue="0") int pageNo){
		return educationService.detailOfEduQna(eduQnaId, educationId, session, pageNo);
	}
	
	@RequestMapping("/eduQnaReply/{educationId}")
	public ModelAndView doEduQnaReplyAction(@Valid EducationQNAReplyVO eduBBSReplyVO, @PathVariable String educationId, Errors errors, HttpSession session) {
		return educationService.doEduQnaReplyAction(eduBBSReplyVO, errors, session, educationId);
	}
	
	@RequestMapping("/addQnaEduReplyLike")
	public void addQnaEduReplyLike(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String replyId = request.getParameter("replyId");
		
		String status = educationService.addQnaEduReplyLike(replyId, session);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/addQnaEduReplyDisLike")
	public void addQnaEduReplyDisLike(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String replyId = request.getParameter("replyId");
		
		String status = educationService.addQnaEduReplyDisLike(replyId, session);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/education/fileBBS/doWriteReReply")
	public void doWriteFileBBSReReplyAction (BBSReplyVO bbsReplyVO, HttpSession session, HttpServletResponse response) {
		educationService.writeFileBBSReReply(bbsReplyVO, session, response);
	}

	@RequestMapping("/education/doModifyFileBBSAction")
	public ModelAndView doModifyFileBBSAction (EducationFileBBSVO educationFileBBSVO, MultipartHttpServletRequest request, @RequestParam String fileDelete , HttpSession session) {
		return educationService.modifyFileBBS(educationFileBBSVO, request, fileDelete, session);
	}

	@RequestMapping("/education/modifyFileBBS")
	public ModelAndView viewModifyFileBBSPage (EducationFileBBSVO educationFileBBSVO, HttpSession session) {
		return educationService.showModifyFileBBS(educationFileBBSVO, session);
	}
	
	@RequestMapping("/education/fileBBS/doDelete")
	public ModelAndView doDeleteFileBBSAction(EducationFileBBSVO educationFileBBSVO, HttpSession session) {
		return educationService.deleteFileBBS(educationFileBBSVO, session);
	}
	
	@RequestMapping("/{educationId}/eduFileNotice/detail/{eduNoticeId}")
	public ModelAndView viewNoticeDetailPage(@PathVariable String educationId, @PathVariable String eduNoticeId){
		return educationService.viewNoticeDetailPage(educationId , eduNoticeId );
	}
	
}