package com.ktds.sems.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	/* Graduate */
	@RequestMapping("/grdtPage")
	public ModelAndView viewGrdtPage() {
		return memberService.viewGrdtPage();
	}

	@RequestMapping("/doGrdtDelete/{cdId}")
	public String doGrdtDelete(@PathVariable String cdId) {
		return memberService.doGrdtDelete(cdId);
	}

	@RequestMapping("/doGrdtModify")
	public void doGrdtModify(HttpServletRequest request, HttpServletResponse response) {

		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");

		String status = memberService.doGrdtModify(cdId, cdNm);
		AjaxUtil.sendResponse(response, status);
	}

	@RequestMapping("/doGrdtInsert")
	public void doGrdtInsert(HttpServletRequest request, HttpServletResponse response) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");

		String status = memberService.doGrdtInsert(cdId, cdNm);
		AjaxUtil.sendResponse(response, status);
	}

	/* Member Type */
	@RequestMapping("/mbrTpPage")
	public ModelAndView viewMbrTpPage() {
		return memberService.viewMbrTpPage();
	}

	@RequestMapping("/doInsertMbrTp")
	public void doInsertMbrTp(HttpServletRequest request, HttpServletResponse response) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		String status = memberService.doInsertMbrTp(cdId, cdNm);
		AjaxUtil.sendResponse(response, status);
	}

	@RequestMapping("/doMbrTpDelete/{cdId}")
	public String doMbrTpDelete(@PathVariable String cdId) {
		return memberService.doMbrTpDelete(cdId);
	}

	@RequestMapping("/doMbrTpModify")
	public void doMbrTpModify(HttpServletRequest request, HttpServletResponse response) {

		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		String status = memberService.doMbrTpModify(cdId, cdNm);
		AjaxUtil.sendResponse(response, status);
	}



	/* 로그인 */
	@RequestMapping(value = ("/login"), method = RequestMethod.POST)
	public void login(@Valid MemberVO memberVO, Errors errors, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		String loginStatus = memberService.login(memberVO, errors, session, request);
		AjaxUtil.sendResponse(response, loginStatus);
	}

	@RequestMapping("/logout")
	public String logout (HttpSession session) {
		memberService.logout(session);
		return "redirect:/";
	}
	
	@RequestMapping("/main")
	public String viewMainPage() {
		return "/common/main";
	}
	
	/* Highest Education */
	@RequestMapping("/highestEduPage")
	public ModelAndView viewHighestEduPage() {
		return memberService.viewHighestEduPage();
	}

	@RequestMapping("/doHighestEduDelete/{cdId}")
	public String doHighestEduDelete(@PathVariable String cdId) {
		return memberService.doHighestEduDelete(cdId);
	}

	@RequestMapping("/doHighestEduModify")
	public void doHighestEduModify(HttpServletRequest request, HttpServletResponse response) {

		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");

		memberService.doHighestEduModify(cdId, cdNm);
	}

	@RequestMapping("/doHighestEduInsert")
	public void doHighestEduInsert(HttpServletRequest request, HttpServletResponse response) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");

		String status = memberService.doHighestEduInsert(cdId, cdNm);
		AjaxUtil.sendResponse(response, status);
	}
	
	/* Code Management */
	@RequestMapping("/codeMngPage")
	public ModelAndView viewCodeMngPage() {
		return memberService.viewCodeMngPage();
	}

	@RequestMapping("/doCodeMngDelete/{cdId}")
	public String doCodeMngDelete(@PathVariable String cdId) {
		return memberService.doCodeMngDelete(cdId);
	}

	@RequestMapping("/doCodeMngModify")
	public void doCodeMngModify(CodeMngVO codeMngVO, HttpServletResponse response) {
		String status = memberService.doCodeMngModify(codeMngVO);
		AjaxUtil.sendResponse(response, status);
	}

	@RequestMapping("/doCodeMngInsert")
	public void doCodeMngInsert(CodeMngVO codeMngVO, HttpServletResponse response) {
		String status = memberService.doCodeMngInsert(codeMngVO);
		AjaxUtil.sendResponse(response, status);
	}
}
