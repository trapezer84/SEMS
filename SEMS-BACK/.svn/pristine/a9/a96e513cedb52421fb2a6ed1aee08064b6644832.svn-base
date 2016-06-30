package com.ktds.sems.member.web;

import java.util.List;

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

import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {

	private MemberService memberService;
	private Logger logger = LoggerFactory.getLogger(MemberController.class);	
	

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/main")
	public ModelAndView viewMainPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("common/main");
		return view;
	}
	
	@RequestMapping("/login")
	public void login(@Valid MemberVO loginVO, Errors errors, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String loginStatus = memberService.login(loginVO, errors, session, request);
		AjaxUtil.sendResponse(response, loginStatus);
	}

	@RequestMapping("/memberManage")
	public ModelAndView viewMemberManagePage() {

		ModelAndView view = new ModelAndView();
		view.setViewName("member/memberManagePage");

		return view;
	}

	@RequestMapping("/memberManage/addMember")
	public ModelAndView viewAddMemberPage() {

		ModelAndView view = new ModelAndView();
		
		List<HighestEducationLevelVO> highestEducationLevelList = memberService.getHighestEducationLevels();
		List<GraduationTypeVO> graduationTypeList = memberService.getGraduationTypes();
		List<MemberTypeVO> memberTypeList = memberService.getMemberTypes();
		
		view.setViewName("member/addMemberPage");
		view.addObject("highestEducationLevelList", highestEducationLevelList);
		view.addObject("graduationTypeList", graduationTypeList);
		view.addObject("memberTypeList", memberTypeList);
		

		return view;
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

	@RequestMapping("/checkValidationByEmail")
	public void checkValidationByEmail(@RequestParam String email, HttpServletResponse response) {
		memberService.checkValidationByEmail(email, response);
	}

	@RequestMapping("/checkValidationByPhoneNumber")
	public void checkValidationByPhoneNumber(@RequestParam String phoneNumber, HttpServletResponse response) {
		memberService.checkValidationByPhoneNumber(phoneNumber, response);
	}

	@RequestMapping("/memberManage/memberList")
	public ModelAndView viewMemberListPage(MemberSearchVO memberSearchVO, @RequestParam(required=false, defaultValue="0") int pageNo){
		return memberService.getAllMemberList(memberSearchVO, pageNo);
	}
	
	@RequestMapping("/memberHistory")
	public ModelAndView viewHistoryPage(LoginHistorySearchVO loginHistorySearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo, HttpSession session){
		return memberService.getAllMemberHistory(loginHistorySearchVO ,pageNo, session);
	}

	@RequestMapping(value = "/doRegisterAction", method = RequestMethod.POST)
	public ModelAndView registerNewMember(@Valid MemberVO member, Errors errors, HttpSession session) {
		return memberService.addNewMember(member, errors, session);
	}
	
	@RequestMapping("/doMassiveDeleteMember")
	public String massiveDeleteMember(HttpServletRequest request){
		String[] deleteMemberIds = request.getParameterValues("deleteMemberId");
		for (String string : deleteMemberIds) {
			logger.info("Controller로 넘어온 ID : " + string);
		}
		return memberService.massiveDeleteMember(deleteMemberIds);
	}
	
	@RequestMapping("/memberManage/sendAndChangePassword")
	public void doSendAndChangePasswordAction (String memberId, HttpServletResponse response) {
		memberService.sendAndChangePassword(memberId, response);
	}
	
	@RequestMapping(value="/memberManage/doModifyMemberTypeAction", method = RequestMethod.POST)
	public ModelAndView doModifyMemberTypeAction (@RequestParam String memberType, @RequestParam List<String> deleteMemberId) {
		return memberService.modifyMemberType(memberType, deleteMemberId);
	}
	
	@RequestMapping("/member/loginHistoryInit")
	public ModelAndView loginHistoryInit() {
		return memberService.loginHistoryInit();
	}
	
	@RequestMapping("/member/adminHistoryInit")
	public ModelAndView adminHistoryInit() {
		return memberService.adminHistoryInit();
	}
	
	@RequestMapping("/memberDelete/{id}")
	public ModelAndView memberDeleteById(@PathVariable String id) {
		return memberService.memberDeleteById(id);
	}

	@RequestMapping("/requestMemberDetail/{memberId}")
	public ModelAndView requestMemberDetail(@PathVariable String memberId) {
		return memberService.requestMemberDetail(memberId);
	}
	
	@RequestMapping(value = "/memberDetail", method = RequestMethod.POST)
	public ModelAndView doWriteMemberDetailInfo(@Valid PersonalInfoReadVO personalInfoReadVO, Errors errors) {
		return memberService.doWriteMemberDetailInfo(personalInfoReadVO, errors);
	}

	@RequestMapping("/adminHistory")
	public ModelAndView viewAdminHistory(LoginHistorySearchVO loginHistorySearchVO, @RequestParam(required=false, defaultValue="0") int pageNo) {
		//logger.info("pageNO : " + pageNo);
		return memberService.getAllAdminHistory(loginHistorySearchVO, pageNo);
	}
	
	@RequestMapping("/member/memberListInit")
	public ModelAndView memberListInit() {
		return memberService.memberListInit();
	}

}
