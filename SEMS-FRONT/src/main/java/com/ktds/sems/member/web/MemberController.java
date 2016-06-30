package com.ktds.sems.member.web;

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
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value = "/doRegisterMemberAction", method = RequestMethod.POST)
	public void doRegisterMemberAction(@Valid MemberVO member, @RequestParam String repeatPassword, Errors errors, HttpServletResponse response) {
		String reportStatus = memberService.addNewMember(member, repeatPassword, errors, response);
		AjaxUtil.sendResponse(response, reportStatus);
	}

	@RequestMapping("/main")
	public String viewMainPage() {
		return "common/main";
	}

	@RequestMapping("/changePassword/{id}")
	public ModelAndView viewChangePasswordPage(@PathVariable String id) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/changePassword");
		view.addObject("id", id);
		
		return view;
	}

	@RequestMapping("/register/policy")
	public String viewRegisterPage(HttpSession session) {
		return memberService.registerPolicy(session);
	}

	@RequestMapping("/register/student")
	public ModelAndView viewRegisterStudentPage() {
		return memberService.registerStudent();
	}

	@RequestMapping("/register/teacher")
	public String viewRegisterTeacherPage() {
		return memberService.registerTeacher();
	}

	@RequestMapping("/checkValidationById")
	public void checkValidationById(@RequestParam String id, HttpServletResponse response) {
		memberService.checkValidationById(id, response);
	}

	@RequestMapping("/checkValidationByPassword")
	public void checkValidationByPassword(@RequestParam String password, HttpServletResponse response) {
		memberService.checkValidationByPassword(password, response);
	}

	@RequestMapping("/checkValidationByRepeatPassword")
	public void checkValidationByRepeatPassword(@RequestParam String password, @RequestParam String repeatPassword,
			HttpServletResponse response) {
		memberService.checkValidationByRepeatPassword(password, repeatPassword, response);
	}

	@RequestMapping("/checkValidationByName")
	public void checkValidationByName(@RequestParam String name, HttpServletResponse response) {
		memberService.checkValidationByName(name, response);
	}
	
	@RequestMapping("/checkValidationByEmail")
	public void checkValidationByEmail(@RequestParam String email, HttpServletResponse response) {
		memberService.checkValidationByEmail(email, response);
	}
	
	@RequestMapping("/checkValidationByPhoneNumber")
	public void checkValidationByPhoneNumber(@RequestParam String phoneNumber, HttpServletResponse response) {
		memberService.checkValidationByPhoneNumber(phoneNumber, response);
	}

	@RequestMapping("/checkValidationByUniversityName")
	public void checkValidationByUniversityName(@RequestParam String universityName, HttpServletResponse response) {
		memberService.checkValidationByUniversityName(universityName, response);
	}
	
	@RequestMapping("/checkValidationByMajorName")
	public void checkValidationByMajorName(@RequestParam String majorName, HttpServletResponse response) {
		memberService.checkValidationByMajorName(majorName, response);
	}
	
	
	@RequestMapping("/loginPage")
	public String viewLoginPage() {
		return "common/login";
	}
	
	@RequestMapping(value = ("/login"), method = RequestMethod.POST)
	public void login(@Valid MemberVO memberVO, Errors errors, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		String loginStatus = memberService.login(memberVO, errors, session, request);
		AjaxUtil.sendResponse(response, loginStatus);
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		memberService.logout(session);
		return "redirect:/loginPage";
	}

	@RequestMapping("/member/myPage")
	public ModelAndView viewMyPage(HttpSession session) {
		return memberService.viewMyPageMenu(session);
	}
	
	@RequestMapping("/member/myPage/checkPassword")
	public ModelAndView viewCheckPasswordPage() {

		ModelAndView view = new ModelAndView();
		view.setViewName("member/checkPassword");
		return view;
	}

	@RequestMapping("/member/myPage/doCheckPassword")
	public ModelAndView doCheckPassword(@RequestParam String password, HttpSession session,
			HttpServletResponse response) {

		ModelAndView view = new ModelAndView();

		// 입력한 패스워드와
		// 테이블의 member.getPassword() 혹은 세션패스워드 를 해독했을때의 암호가
		// 일치한다면 AjaxUtil.sendResponse(response, "OK");

		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		String sessionId = member.getId();
		String originSalt = memberService.getSaltById(sessionId);
		String inputPassword = SHA256Util.getEncrypt(password, originSalt);
		
		boolean isLock = memberService.isModifyAccountLock(sessionId);
		String originPassword = memberService.getPasswordById(sessionId);
		
		if (inputPassword.equals(originPassword) && isLock == false ) {
			/*
			 * 1. MODIFY_FAIL_COUNT를 0 으로 초기화한다. 2. IS_MODIFY_ACCOUNT_LOCK을
			 * 'N'으로 초기화한다.
			 */
			memberService.resetModifyLockAndCount(sessionId);
			AjaxUtil.sendResponse(response, "OK");
			//memberService.modifySuccess(sessionId);
			return null;
			
		} else {

			 /* 1. MODIFY_FAIL_COUNT 를 1 증가시킨다. */
			memberService.plusModifyFailCount(sessionId);
			memberService.updateModifyAccountLock(sessionId);


			 /* 1. MODIFY_FAIL_COUNT 가 3 이상이라면 IS_MODIFY_ACCOUNT_LOCK 'Y'로 수정한다.*/
			memberService.updateModifyAccountLock(sessionId);

			 /* 1. IS_ACCOUNT_LOCK이 'Y'라면 사용자의 이메일로 비밀번호가 3회 이상 틀려 접속이 차단되었음을 알린다. */
			isLock = memberService.isModifyAccountLock(sessionId);


			 /* 2. 메일을 보낸다.*/
			// FIXME 이메일 테스트 시 주석처리 삭제
//			 if( isLock ){
//			 memberService.sendBlockAccountEmail(sessionId);
//			 }

			/* 3. IS_ACCOUNT_LOCK이 'Y'라면 브라우저에게 'OVER' 라고 보낸다. */
			AjaxUtil.sendResponse(response, isLock ? "OVER" : "NO");
			
			if ( isLock ) {
				session.invalidate();
			}

			view.setViewName("/member/checkPassword");
		}

		return view;
	}

	@RequestMapping("/member/myPage/modify")
	public ModelAndView viewModifyPage(HttpSession session) {

		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");

		String sessionId = member.getId();
		return memberService.modifySuccess(sessionId);
	}

	@RequestMapping("/doChangePasswordAction")
	public ModelAndView doChangePasswordAction(MemberVO memberVO) {
		
		return memberService.changePassword(memberVO);
	}

	@RequestMapping("/member/myPage/resignMember")
	public ModelAndView sendEmailForResign(@Valid HttpSession session) {
		return memberService.sendEmailForResign(session, memberService.insertUuidForResign(session));
	}

	@RequestMapping("/member/myPage/doModifyAction")
	public ModelAndView doModifyAction(@Valid MemberVO member, Errors errors, @RequestParam(required = false, defaultValue = "") String graduationType,
			@RequestParam(required = false, defaultValue = "") String helCodeName) {
		return memberService.modifyMemberInfo(member, errors, graduationType, helCodeName);
	}
	
	/**
	 * @author 이기연
	 * @param session
	 */
	@RequestMapping("/member/myPage/saveAsExcel")
	public ModelAndView saveLoginHistoryAsExcel(HttpSession session) {
		return memberService.saveLoginHistoryAsExcel(session);
	}

	@RequestMapping("/member/loginHistory")
	public ModelAndView viewLoginHistoryPage(LoginHistorySearchVO loginHistorySearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo, HttpSession session, HttpServletRequest request) {
		return memberService.viewLoginHistoryPage(loginHistorySearchVO, pageNo, session, request);
	}
	
	@RequestMapping("/member/loginForResign/{resignCode}/{id}")
	public ModelAndView loginForResine(@PathVariable String resignCode, @PathVariable String id) {
		return memberService.loginForResign(resignCode, id);
	}

	@RequestMapping(value = "/doResign", method = RequestMethod.POST)
	public void doResign(@RequestParam String resignCode, @Valid MemberVO memberVO, Errors errors, HttpSession session,
			HttpServletResponse response) {
		String resignStatus = memberService.doResign(memberVO, errors, resignCode);
		AjaxUtil.sendResponse(response, resignStatus);

	}

	@RequestMapping(value = ("/checkPassword"), method = RequestMethod.POST)
	public void checkPrevPassword(@RequestParam String id, @RequestParam String prevPassword, HttpServletResponse response, HttpServletRequest request) {
		String checkPrevPasswordStatus = memberService.doCheckPrevPassword(id, prevPassword, request);
		AjaxUtil.sendResponse(response, checkPrevPasswordStatus);
	}
	
	@RequestMapping(value="/member/doRequestIpHistory/{lgiHtrId}", method=RequestMethod.GET)
	public ModelAndView doRequestIpHistory (@PathVariable int lgiHtrId, HttpSession session) {
		return memberService.doRequestIpHistory(lgiHtrId, session);
	}
	
	/**
	 * 강의포기를 위한 강의리스트 > 구본호
	 * 강의 게시판 > 오평화
	 */
	@RequestMapping("/member/myPage/course")
	public ModelAndView viewMyEduCoursePage (@RequestParam(required=false, defaultValue="0") int pageNo, HttpSession session, EducationSearchVO educationSearchVO) {
		return memberService.getCourseList(session, pageNo, educationSearchVO);
	}
	
	/**
	 * 강의포기 신청서 작성
	 */
	@RequestMapping("/resignCourse/{educationId}")
	public ModelAndView writeResignCourse(@PathVariable String educationId, HttpSession session) {
		return memberService.writeResignCourse(educationId, session);
	}
	
	/**
	 * 강의포기 신청 > 구본호 > 이기연 (SM)
	 */
	@RequestMapping(value = ("/dropCourseApply/{educationId}"), method = RequestMethod.POST)
	public void dropCourseApply(@PathVariable String educationId, HttpSession session, String courseDropReason, @Valid HttpServletResponse response) {
		String checkCourseDropReason = memberService.dropCourseApply(educationId, session, courseDropReason);
		AjaxUtil.sendResponse(response, checkCourseDropReason);
	}
	
	@RequestMapping("/member/loginHistoryInit")
	public ModelAndView loginHistoryInit() {
		return memberService.loginHistoryInit();
	}
	
	/**
	 *	나의 교육 이력 보기 
	 */
	@RequestMapping("/member/myPage/educationHistory")
	public ModelAndView viewEducationHistroyPage(EducationHistorySearchVO educationHistorySearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo, HttpSession session) {
		return memberService.getAllEducationHistoryListByIdWithPaging(educationHistorySearchVO, pageNo, session);
	}
	
	@RequestMapping(value=("/member/doCheckIp/{lgiHtrId}"), method=RequestMethod.GET)
	public ModelAndView doCheckIp(@PathVariable int lgiHtrId, HttpSession session) {
		return memberService.doCheckIp(lgiHtrId, session);
	}
	
	/**
	 *	나의 교육 이력 엑셀 다운로드 
	 */
	@RequestMapping("/member/myPage/educationHistory/exportExcel")
	public String eduationHistoryExportExcel(HttpSession session) {
		return memberService.eduationHistoryExportExcel(session);
	}
	
	@RequestMapping("/invalidAccess")
	public ModelAndView invalidAccess() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/error/invalidAccess");
		return view;
	}
	
	//TODO
	@RequestMapping("/checkRegistState")
	public void checkRegistState(@RequestParam String id, HttpServletResponse response) {
		memberService.checkRegistState(id, response);
	}
	
	@RequestMapping("/member/myPage/educationHistoryInit")
	public String educationHistoryPageInit() {
		return "redirect:/member/myPage/educationHistory";
	}

	@RequestMapping("/member/myPage/attendHistory")
	public ModelAndView viewAttendHistoryPage(HttpSession session) {
		return memberService.getAllAttendClassListById(session);
	}
	
	@RequestMapping("/checkValidationCourseAccess")
	public void checkValidationCourseAccess(HttpServletResponse response, HttpSession session) {
		memberService.checkValidationCourseAccess(response, session);
	}
	
	@RequestMapping("/doLeaveClass")
	public void doLeaveClassAction(HttpServletResponse response, HttpSession session){
		//퇴근 버튼 눌렀을 시 ATD 테이블 업데이트
		memberService.updateLeaveClass(response, session);
	}
	
	@RequestMapping("/member/myPage/attendHistory/{educationId}")
	public ModelAndView doAttendHistoryAction(@PathVariable String educationId, HttpSession session) {
		return memberService.getAllAttendHistory(session, educationId);
	}

}
