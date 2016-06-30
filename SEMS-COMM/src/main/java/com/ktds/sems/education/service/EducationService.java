package com.ktds.sems.education.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.JsonResponseVO;
import com.ktds.sems.education.vo.TimeVO;

import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public interface EducationService {
	
	public JsonResponseVO addNewCategory(CategoryVO categoryVO, Errors errors);
	
	public String validCategoryId(String categoryId, String categoryType);

	public String modifyEduCost(CostVO cost);

	public ModelAndView getAllEduCost();

	public ModelAndView deleteEduCost(String cdId);

	public String insertEduCost(CostVO cost);

	public String validCategoryName(String categoryName, String categoryType);

	public ModelAndView viewCategoryPage();

	public ModelAndView getAllEduTime();

	public ModelAndView deleteEduTime(String cdId);

	public String modifyEduTime(TimeVO time);

	public String insertEduTime(TimeVO time);

	public JsonResponseVO getChildCategory(String categoryId, String categoryType);

	public String deleteCategory(String categoryId, String categoryType);

	public JsonResponseVO modifyCategory(CategoryVO categoryVO, Errors errors);

}
