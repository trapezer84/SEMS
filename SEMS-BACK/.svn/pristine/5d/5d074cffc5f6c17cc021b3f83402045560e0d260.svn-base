package com.ktds.sems.cooperation.web;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.cooperation.service.CooperationService;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

@Controller
public class CooperationController {

	private CooperationService cooperationService;

	public void setCooperationService(CooperationService cooperationService) {
		this.cooperationService = cooperationService;
	}
	
	@RequestMapping("/registerCoo")
	public ModelAndView viewRegistCooPage() {
		return cooperationService.viewRegistCooPage();
	}
	
	@RequestMapping(value="/doRegisterCoo", method = RequestMethod.POST)
	public ModelAndView doRegisterCoo(@Valid CooperationVO cooperation, Errors errors) {
		return cooperationService.doRegisterCoo(cooperation, errors);
	}
	
	@RequestMapping(value="/modifyCoo/{cooperationId}")
	public ModelAndView viewModifyCooPage(@PathVariable String cooperationId) {
		return cooperationService.viewModifyCooPage(cooperationId);
	}

	@RequestMapping(value="/doModifyCoo", method = RequestMethod.POST)
	public ModelAndView doModifyCoo(@Valid CooperationVO cooperation, Errors errors) {
		return cooperationService.doModifyCoo(cooperation, errors);
	}
	
	@RequestMapping("/isExistCooperationTitle")
	public void isExistCooperationTitle(@RequestParam String title, HttpServletResponse response) {
		cooperationService.isExistCooperationTitle(title, response);
	}
	
	
	@RequestMapping("/cooList")
	public ModelAndView viewCooListPage(CooperationSearchVO cooperationSearchVO, @RequestParam(required=false, defaultValue="0") int pageNo) {
		return cooperationService.getAllCooperationList(cooperationSearchVO,pageNo);
	}
	
	@RequestMapping("/cooDetail/{cooperationId}")
	public ModelAndView viewCooDetailPage(@PathVariable String cooperationId) {
		return cooperationService.getOneCooperation(cooperationId);
	}
	
	@RequestMapping("/cooDelete/{cooperationId}")
	public String doDeleteCooperation(@PathVariable String cooperationId){
		return cooperationService.doDeleteCooperation(cooperationId);
	}
	
	@RequestMapping("/initCooSearch")
	public String doInitCooSearch(HttpSession session){
		session.setAttribute("_SEARCH_", null);
		return "redirect:/cooList";
	}
	
}
