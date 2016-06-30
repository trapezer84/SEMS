package com.ktds.sems.pc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.service.PcService;
import com.ktds.sems.pc.vo.ReportedPcListVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcVO;

import kr.co.hucloud.utilities.web.AjaxUtil;
import kr.co.hucloud.utilities.web.Paging;

public class PcServiceImpl implements PcService{

	private PcBiz pcBiz;

	public void setPcBiz(PcBiz pcBiz) {
		this.pcBiz = pcBiz;
	}

	@Override
	public ModelAndView viewMyPcPage(HttpSession session, HttpServletRequest request) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		List<UsedPcVO> usedPcList = pcBiz.getUsedPcListByMember(memberVO);
		List<EducationVO> eduListExceptUsed = pcBiz.getEduListExceptUsed(memberVO);
		String myPcIp= request.getRemoteHost();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("myPage/pc/myPc");
		view.addObject("usedPcList", usedPcList);
		view.addObject("eduListByMember", eduListExceptUsed);
		view.addObject("myPcIp", myPcIp);
		return view;
	}

	@Override
	public void getEduLocationById(String educationId, HttpServletResponse response, HttpSession session) {
		String location = "";
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		List<EducationVO> eduListByMember = pcBiz.getEduListByMember(memberVO);
		for (EducationVO educationVO : eduListByMember) {
			if(educationVO.getEducationId().equals(educationId)){
				location = educationVO.getEducationLocation();
			}
		}
		AjaxUtil.sendResponse(response, location);
		return;
	}

	@Override
	public String doRegisterMyPc(String educationId, String eduLocation, String usedPcIp, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		String pcId = pcBiz.getPcIdByIp(usedPcIp);
		if ( pcId == null ){
			
			return "FAIL";
		}
		
		else {
			String memberId = memberVO.getId();
			
			UsedPcVO usedPcVO = new UsedPcVO();
			usedPcVO.setPcId(pcId);
			usedPcVO.setEducationId(educationId);
			usedPcVO.setMemberId(memberId);
			
			boolean data = pcBiz.doRegisterMyPc(usedPcVO) > 0;

			System.out.println("biz로감");
			
			if (!data) {
				return "FAIL";
			}
			return "OK";
		}
	}

	@Override
	public String doDeleteMyPc(String pcId) {
		pcBiz.doDeleteMyPc(pcId);
		return "redirect:/member/myPc";
	}

	/**
	 * PC를 신고할 수 있는 팝업 창
	 * 이기연
	 */
	@Override
	public ModelAndView viewReportPcPage(String pcId, HttpSession session, HttpServletRequest request) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("myPage/pc/reportMyPc");
		view.addObject("pcId", pcId);
		return view;
	}

	/**
	 * 실제 고장난 PC를 등록하는 method
	 * 이기연
	 */
	@Override
	public String reportProblemPc(ReportedPcVO reportedPcVO, HttpServletResponse response, HttpSession session) {

		try {
			MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
			String memberId = memberVO.getId();
			
			String nowDate = pcBiz.getNowDate();
			int nextSeq = pcBiz.getNextReportedPcIdSeq();
			String reportedPcId = "RP-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
			
			reportedPcVO.setReportedPcId(reportedPcId);
			reportedPcVO.setMemberId(memberId);
			
			// PC 신고 기록 insert 성공할 경우 
			if (pcBiz.reportProblemPc(reportedPcVO)) {
				return "OK";
			}
			else {
				return "NO";
			}
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;
		
	}

	@Override
	public ModelAndView getMyReportedPcList(HttpSession session, ReportedPcSearchVO reportedPcSearchVO) {
		
		ReportedPcListVO reportedPcListVO = new ReportedPcListVO();
		Paging paging = new Paging();
		reportedPcListVO.setPaging(paging);
		
		if(reportedPcSearchVO == null) {
			reportedPcSearchVO = new ReportedPcSearchVO();
			reportedPcSearchVO.setPageNo(0);
		}
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		reportedPcSearchVO.setMemberId(memberVO.getId());
		
		paging.setPageNumber(reportedPcSearchVO.getPageNo() + "");
		int totalCount = pcBiz.getTotalMyReportedPcCount(reportedPcSearchVO);
		paging.setTotalArticleCount(totalCount);
		
		reportedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportedPcSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<ReportedPcVO> reportedPcList = pcBiz.getMyReportedPcList(reportedPcSearchVO);
		reportedPcListVO.setReportedPcList(reportedPcList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/myPage/pc/myReportedPcList");
		view.addObject("reportedPcListVO", reportedPcListVO);
		view.addObject("reportedPcSearchVO", reportedPcSearchVO);
		
		return view;
	}
	
}
