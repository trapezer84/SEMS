package com.ktds.sems.education.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.JsonResponseVO;
import com.ktds.sems.education.vo.TimeVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class EducationController {
	
	private EducationService educationService;
	
	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}

	@RequestMapping("/education/category")
	public ModelAndView viewCategoryPage(){
		return educationService.viewCategoryPage();
	}
	
	@RequestMapping("/education/validCategoryId")
	@ResponseBody
	public String validCategoryIdAction(@RequestParam String categoryId, @RequestParam String categoryType){
		return educationService.validCategoryId(categoryId, categoryType);
	}
	
	@RequestMapping("/education/validCategoryName")
	@ResponseBody
	public String validCategoryNameAction(@RequestParam String categoryName, @RequestParam String categoryType){
		return educationService.validCategoryName(categoryName, categoryType);
	}
	
	@RequestMapping("/education/addCategory")
	@ResponseBody
	public JsonResponseVO addNewCategoryAction(@Valid CategoryVO categoryVO, Errors errors){
		return educationService.addNewCategory(categoryVO, errors);
	}
	
	@RequestMapping("/education/cost")
	public ModelAndView viewCostPage() {
		return educationService.getAllEduCost();
	}
	
	@RequestMapping("/education/modifyEduCost")
	@ResponseBody
	public void modifyEduCost(HttpServletRequest request, HttpServletResponse response) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		CostVO cost = new CostVO();
		cost.setCdId(cdId);
		cost.setCdNm(cdNm);
		String status = educationService.modifyEduCost(cost);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/education/deleteEduCost/{cdId}")
	public ModelAndView deleteEduCost(@PathVariable String cdId) {
		return educationService.deleteEduCost(cdId);
	}
	
	
	@RequestMapping("/education/insertEduCost")
	@ResponseBody
	public void insertEduCost(HttpServletRequest request, HttpServletResponse response) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		CostVO cost = new CostVO();
		cost.setCdId(cdId);
		cost.setCdNm(cdNm);
		
		String status = educationService.insertEduCost(cost);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/education/time")
	public ModelAndView viewTimePage() {
		return educationService.getAllEduTime();
	}
	
	@RequestMapping("/education/deleteEduTime/{cdId}")
	public ModelAndView deleteEduTime(@PathVariable String cdId) {
		return educationService.deleteEduTime(cdId);
	}
	
	@RequestMapping("/education/modifyEduTime")
	@ResponseBody
	public void modityEduTime(HttpServletRequest request, HttpServletResponse response) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		TimeVO time = new TimeVO();
		time.setCdId(cdId);
		time.setCdNm(cdNm);
		
		String status = educationService.modifyEduTime(time);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("/education/insertEduTime")
	@ResponseBody
	public void insertEduTime(HttpServletRequest request, HttpServletResponse response) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		TimeVO time = new TimeVO();
		time.setCdId(cdId);
		time.setCdNm(cdNm);
		
		String status = educationService.insertEduTime(time);
		AjaxUtil.sendResponse(response, status);
	}
	

	@RequestMapping("/education/getChildCategory")
	@ResponseBody
	public JsonResponseVO getChildCategory(@RequestParam String categoryId,@RequestParam String categoryType){
		return educationService.getChildCategory(categoryId, categoryType);
	}
	
	@RequestMapping("/education/deleteCategory")
	@ResponseBody
	public String deleteCategoryAction(@RequestParam String categoryId, @RequestParam String categoryType){
		return educationService.deleteCategory(categoryId, categoryType);
	}

	@RequestMapping("/education/modifyCategory")
	@ResponseBody
	public JsonResponseVO modifyCategoryAction(@Valid CategoryVO categoryVO, Errors errors){
		return educationService.modifyCategory(categoryVO, errors);
	}

}
