package com.ktds.sems.education.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class EducationController {

	private EducationService educationService;
	
	
	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}

	@RequestMapping("/eduregister")
	public ModelAndView viewEduWritePage() {
		return educationService.getAllEduCode();
	}

	@RequestMapping("/doWriteAction")
	public ModelAndView doWriteAction(@Valid EducationVO educationVO, Errors errors,
			MultipartHttpServletRequest request) {
		return educationService.writeNewEducation(educationVO, errors, request);
	}

	@RequestMapping("/educationModify/{educationId}")
	public ModelAndView viewEducationModifyPage(@PathVariable String educationId) {
		ModelAndView view = educationService.getOneEducationForUpdate(educationId);
		return view;
	}

	@RequestMapping("/doEducationModifyAction")
	public ModelAndView doEducationModifyAction(@Valid EducationVO educationVO, Errors errors,
			MultipartHttpServletRequest request) {
		return educationService.modifyNewEducation(educationVO, errors, request);
	}

	@RequestMapping("/educationHistory")
	public ModelAndView viewEduHistoryManagePage(EducationHistorySearchVO eduHistorySearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo) {
		ModelAndView view = educationService.getAllEducationHistory(eduHistorySearchVO, pageNo);
		return view;
	}

	@RequestMapping("/checkEduApplicant")
	public ModelAndView viewCheckApplicantPage(EducationHistorySearchVO eduHistorySearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo) {
		// JC (JOIN_CMPL)
		ModelAndView view = educationService.getJCEduHistory(eduHistorySearchVO, pageNo);
		return view;
	}

	@RequestMapping("/joinAply/{educationHistoryId}")
	public ModelAndView doJoinApply(@PathVariable String educationHistoryId ) {
		ModelAndView view = educationService.applyJoinEducationByMemberId(educationHistoryId);
		return view;
	}
	
	/*@RequestMapping("/rejectionMail/{memberId}")
	public String rejectionMailAction(@PathVariable String memberId, String textMail){
		
		return educationService.rejectionMailAction(memberId, textMail);
	}*/

	@RequestMapping("/joinCncl/{educationHistoryId}/{memberId}/{description}")
	public ModelAndView doCnclJoin(@PathVariable String educationHistoryId, @PathVariable String memberId, @PathVariable String description) {
		educationService.rejectionMailAction(educationHistoryId, memberId, description);
		ModelAndView view = educationService.cancelJoinEducationByMemberId(educationHistoryId, memberId, description);
		return view;
	}
	
	@RequestMapping("/{educationId}/eduQna")
	public ModelAndView viewQnAPage(EduQnaSearchVO eduQnaSearchVO, @PathVariable String educationId, @RequestParam(required = false, defaultValue = "0") int pageNo){
		eduQnaSearchVO.setEducationId(educationId);
		return educationService.getAllQnaArticle(eduQnaSearchVO, pageNo);
	}
	
	@RequestMapping("/{educationId}/eduReport")
	public ModelAndView viewEduReportPage(EduReportSearchVO eduReportSearchVO, @PathVariable String educationId, @RequestParam(required = false, defaultValue = "0") int pageNo){
		eduReportSearchVO.setEducationId(educationId);
		return educationService.getAllReportArticle(eduReportSearchVO, pageNo);
	}

	@RequestMapping("/{educationId}/eduFile")
	public ModelAndView viewEduFilePage(@PathVariable String educationId,  EduNoticeSearchVO eduNoticeSearchVO, EduFileSearchVO eduFileSearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo){
		eduFileSearchVO.setEducationId(educationId);
		return educationService.getAllEduFileArticle(eduFileSearchVO, pageNo, eduNoticeSearchVO);
	}

	public void changeEducationApplyState(String educationHistoryId) {
		educationService.changeEducationApplyState(educationHistoryId);
	}
	
	@RequestMapping("/doActionDeleteEDU/{educationId}")
	public ModelAndView doActionDelete(@PathVariable String educationId, HttpSession session) {
		return educationService.doActionDelete(educationId, session);
	}
	
	@RequestMapping("/education/reportHistory")
	public ModelAndView viewReportHistoryPage(EduReportSearchVO reportSearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo){
		return educationService.viewReportHistoryPage(reportSearchVO, pageNo);
	}
	
	@RequestMapping("/{educationId}/EduFileNoticeWrtie")
	public ModelAndView viewWriteEduFileNoticeWritePage(@PathVariable String educationId, EduNoticeVO eduNoticeVO) { 
		ModelAndView view = new ModelAndView();
		eduNoticeVO.setEducationId(educationId);
		view.setViewName("education/EduFileNoticeWrtie");
		return view;
	}
	
	@RequestMapping("/{educationId}/doEdufileNoticeWriteAction")
	public ModelAndView doWriteEduFileNoticeAction(@PathVariable String educationId, @Valid EduNoticeVO eduNoticeVO, 
			Errors errors, HttpSession session) {
		eduNoticeVO.setEducationId(educationId);
		MemberVO memberVO = (MemberVO)session.getAttribute(Session.MEMBER);
		eduNoticeVO.setMemberId(memberVO.getId());
		return educationService.writeEduFileNoticeAction(eduNoticeVO , errors, session);
	}
	
	@RequestMapping("/{educationId}/eduFileNotice/detail/{eduNoticeId}")
	public ModelAndView viewNoticeDetailPage(@PathVariable String educationId, @PathVariable String eduNoticeId){
		return educationService.viewNoticeDetailPage(educationId , eduNoticeId );
	}
	
	@RequestMapping("/{educationId}/eduFileNotice/delete/{eduNoticeId}")
	public String doDeleteEduNotice(@PathVariable String educationId, @PathVariable String eduNoticeId){
		return educationService.doDeleteEduNotice(educationId, eduNoticeId);
	}
	
	@RequestMapping("/{educationId}/massiveDeleteNotice")
	public String massiveDeleteNotice(@PathVariable String educationId, HttpServletRequest request){
		String[] deleteNoiceIds = request.getParameterValues("deleteNoticeId");
		return educationService.massiveDeleteNotice(educationId, deleteNoiceIds);
	}
	
	@RequestMapping("/{educationId}/modify/{eduNoticeId}")
	public ModelAndView viewModifyEduFileNoticePage(@PathVariable String educationId, @PathVariable String eduNoticeId) {
		return educationService.viewModifyNoticePage(eduNoticeId);
	}
	
	@RequestMapping("/{educationId}/doEduFileNoticeModify/{eduNoticeId}")
	public ModelAndView doEduFileNoticeModifyAction(@PathVariable String educationId, @PathVariable String eduNoticeId,
			@Valid EduNoticeVO eduNoticeVO, Errors errors, HttpSession session) {
		return educationService.doEduFileNoticeModify(educationId, eduNoticeId, eduNoticeVO, errors, session );
	}
	
	@RequestMapping("/attendanceHistory")
	public ModelAndView viewAttendanceHistory() {
		ModelAndView view = new ModelAndView();
		view.setViewName("education/attendanceHistory");
		return view;
	}
	
	@RequestMapping("/attendanceHistory/memberList")
	public ModelAndView viewAttendanceHistoryMember() {
		return educationService.getAllMemberList();
	}
	
	@RequestMapping("/attendanceHistory/memberDetail/{memberId}")
	public ModelAndView viewAttendanceHistoryOneMember(EducationHistorySearchVO searchVO, @PathVariable String memberId, @RequestParam(required=false, defaultValue="0") int pageNo) {
		return educationService.getOneMemberAttendance(searchVO, memberId, pageNo);
	}
	
	@RequestMapping("/attendanceHistory/memberDetail/modifyState/{educationId}/{memberId}/{attendTime}")
	public ModelAndView viewModifyStatePage(@PathVariable String educationId, @PathVariable String memberId, @PathVariable String attendTime) {
		return educationService.viewModifyStatePage(educationId, memberId, attendTime);
	}
	
	@RequestMapping("/attendanceHistory/memberDetail/doModifyState/")
	public ModelAndView doModifyState(@RequestParam("educationId") String educationId, @RequestParam("memberId") String memberId, @RequestParam("attendTime") String attendTime, @RequestParam("comment") String comment) {
		return educationService.writeModifyStateComment(educationId, memberId, attendTime, comment);
	}
	
	@RequestMapping("/attendanceHistory/educationList")
	public ModelAndView viewAttendanceHistoryEducation() {
		return educationService.getAllStartedEducationList();
	}
	
	@RequestMapping("/attendanceHistory/educationDetail/{educationId}")
	public ModelAndView viewAttendanceHistoryOneEducation(EducationHistorySearchVO searchVO, @PathVariable String educationId, @RequestParam(required=false, defaultValue="0") int pageNo) {
		return educationService.getOneEducationAttendance(searchVO, educationId, pageNo);
	}
	
	@RequestMapping("/attendanceHistory/teamList")
	public ModelAndView viewAttendanceTeamMember() {
		return educationService.getAllTeamList();
	}
	
	@RequestMapping("/attendanceHistory/teamDetail/{educationId}/{teamId}/{educationTitle}")
	public ModelAndView viewAttendanceHistoryOneTeam(EducationHistorySearchVO searchVO, @PathVariable String educationId
			, @PathVariable String teamId, @PathVariable String educationTitle, @RequestParam(required=false, defaultValue="0") int pageNo){
		return educationService.getOneTeamAttendance(searchVO, educationId, teamId, educationTitle, pageNo);
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
	
	@RequestMapping("/showEducationList")
	public ModelAndView viewEducationListPage(EducationSearchVO searchVO) {
		System.out.println("검색어" + searchVO.getSearchKeyword() + "타입" + searchVO.getSearchType());
		return educationService.getAllEucationList(searchVO);
	}
	
	@RequestMapping("/showDetailEducation/{educationId}")
	public ModelAndView viewDetailEducationPage(@PathVariable String educationId) {
		return educationService.getOneEducation(educationId);
	}
	
}
