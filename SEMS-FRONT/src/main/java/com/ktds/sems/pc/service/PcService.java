package com.ktds.sems.pc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;

public interface PcService {

	public ModelAndView viewMyPcPage(HttpSession session, HttpServletRequest request);

	public void getEduLocationById(String educationId, HttpServletResponse response, HttpSession session);

	public String doRegisterMyPc(String educationId, String eduLocation, String usedPcIp, HttpSession session);

	public String doDeleteMyPc(String pcId);

	public ModelAndView viewReportPcPage(String educationId, HttpSession session, HttpServletRequest request);

	public String reportProblemPc(ReportedPcVO reportedPcVO, HttpServletResponse response, HttpSession session);

	public ModelAndView getMyReportedPcList(HttpSession session, ReportedPcSearchVO reportedPcSearchVO);

}
