package com.ktds.sems.pc.web;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationPlaceSearchVO;
import com.ktds.sems.pc.service.PcService;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class PcController {

	private PcService pcService;

	public void setPcService(PcService pcService) {
		this.pcService = pcService;
	}

	@RequestMapping("/usedPcList")
	public ModelAndView viewUsedPcListPage(UsedPcSearchVO usedPcSearchVO) {
		return pcService.getUsedPcList(usedPcSearchVO);
	}
	
	@RequestMapping("/reportedPcList")
	public ModelAndView viewReportedPcListPage(ReportedPcSearchVO reportedPcSearchVO) {
		return pcService.getReportedPcListWithPaging(reportedPcSearchVO);
	}
	
	@RequestMapping("/changeReportedState")
	public void changeReportedState(HttpServletResponse response, ReportedPcVO reportedPcVO) {
		String data = pcService.changeReportedState(reportedPcVO);
		AjaxUtil.sendResponse(response, data);
	}
	
	@RequestMapping("/eduPlaceSet")
	public ModelAndView educationPlaceSetting(HttpSession session){
		return pcService.educationPlaceSetting(session);
	}
	
	@RequestMapping(value="/doRegistClass", method=RequestMethod.POST)
	public ModelAndView doRegistClass(PcVO pcVO, HttpSession session){
		return pcService.doRegistClass(pcVO, session);
	}
	
	@RequestMapping("/eduPlaceList")
	public ModelAndView viewEducationPlaceList(EducationPlaceSearchVO eduPlaceSearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo) {
		return pcService.viewEducationPlaceList(eduPlaceSearchVO, pageNo);
	}
	
	@RequestMapping("/doActionDeleteEduPlace/{educationPlaceId}")
	public ModelAndView doActionDeleteEduPlace(@PathVariable String educationPlaceId,HttpSession session, HttpServletResponse response) {
		return pcService.doActionDeleteEduPlace(educationPlaceId, session, response);
	}

	@RequestMapping("/doActionDeleteEduPC/{pcId}")
	public ModelAndView doActionDeleteEduPC(@PathVariable String pcId,HttpSession session, HttpServletResponse response) {
		return pcService.doActionDeleteEduPC(pcId, session, response);
	}
	
}
