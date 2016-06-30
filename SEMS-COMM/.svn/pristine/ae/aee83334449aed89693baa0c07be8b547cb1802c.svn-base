package com.ktds.sems.education.service.impl;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.JsonResponseVO;
import com.ktds.sems.education.vo.TimeVO;

public class EducationServiceImpl implements EducationService {
	
	private EducationBiz educationBiz;

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	@Override
	public String validCategoryId(String categoryId, String categoryType) {
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId(categoryId);
		categoryVO.setCategoryType(categoryType);
		
		boolean isExist = educationBiz.validCategoryId(categoryVO);
		if ( isExist ) {
			return "true";
		}
		else {
			return "false";
		}
	}

	@Override
	public String modifyEduCost(CostVO cost) {
		String result = "";
		
		if (cost.getCdId() == null || cost.getCdNm() == null) {
			return "NO_DATA";
		}
		boolean isExistCostNm = educationBiz.isExistCostNm(cost);
		
		if (isExistCostNm) {
			result = "FAIL_V";
		}
		else {
			boolean isModify = educationBiz.modifyEduCost(cost);
			if (isModify) {
				result = "OK";
			}
			else {
				result = "FAIL_M";
			}
		}
		
		return result;
	}

	@Override
	public ModelAndView getAllEduCost() {
		ModelAndView view = new ModelAndView();
		view.addObject("costList", educationBiz.getAllEduCost());
		view.setViewName("education/cost");
		return view;
	}




	@Override
	public String insertEduCost(CostVO cost) {
		String result = "";
		
		if (cost.getCdId() == null || cost.getCdNm() == null) {
			return "NO_DATA";
		}

		boolean isExistCost = educationBiz.isExistCost(cost);
		if (isExistCost) {
			result = "FAIL_V";
		}
		else {
			boolean insertResult = educationBiz.insertEduCost(cost);
			if (insertResult) {
				result = "OK";
			}
			else {
				result = "FAIL_I";
			}
		}
		
		return result;
	}
	
	@Override
	public ModelAndView deleteEduCost(String cdId) {
		ModelAndView view = new ModelAndView();
		
		boolean delectResult = educationBiz.deleteEduCost(cdId);
		
		if (delectResult) {
			view.setViewName("redirect:/education/cost");
		}
		return view;
	}

	@Override
	public String validCategoryName(String categoryName, String categoryType) {

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryName(categoryName);
		categoryVO.setCategoryType(categoryType);
		
		boolean isExist = educationBiz.validCategoryName(categoryVO);
		if ( isExist ) {
			return "true";
		}
		else {
			return "false";
		}
	}

	@Override
	public JsonResponseVO addNewCategory(CategoryVO categoryVO, Errors errors) {
		JsonResponseVO jsonResponseVO = new JsonResponseVO();
		
		if ( errors.hasErrors() ) {
			jsonResponseVO.setResult(false);
			jsonResponseVO.setData(errors.getAllErrors());
		}
		else {
			boolean result = educationBiz.addNewCategory(categoryVO);
			if ( result ) {
				jsonResponseVO.setResult(true);
			}
			else {
				jsonResponseVO.setResult(false);
				jsonResponseVO.setData("카테고리를 추가중 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
			}
		}
		
		return jsonResponseVO;
	}

	@Override
	public ModelAndView viewCategoryPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("education/category");
		
		List<CategoryVO> largeCategoryList = educationBiz.getAllLargeCategory();
		view.addObject("largeCategoryList", largeCategoryList);
		
		return view;
	}

	@Override
	public ModelAndView getAllEduTime() {
		ModelAndView view = new ModelAndView();
		view.addObject("timeList", educationBiz.getAllEduTime());
		view.setViewName("education/time");
		return view;
	}

	@Override
	public ModelAndView deleteEduTime(String cdId) {
		ModelAndView view = new ModelAndView();
		boolean result = educationBiz.deleteEduTime(cdId);
		
		if (result) {
			view.setViewName("redirect:/education/time");
		}
		
		return view;
	}

	@Override
	public String modifyEduTime(TimeVO time) {
		String result = "";
		
		if (time.getCdId() == null || time.getCdNm() == null) {
			return "NO_DATA";
		}
		
		boolean isExistTimeNm = educationBiz.isExistTimeNm(time);
		
		if (isExistTimeNm) {
			result = "FAIL_V";
		}
		else {
			boolean isModify = educationBiz.modifyEduTime(time);
			if (isModify) {
				result = "OK";
			}
			else {
				result = "FAIL_M";
			}
		}
		return result;
	}

	@Override
	public String insertEduTime(TimeVO time) {
		String result = "";
		
		if (time.getCdId() == null || time.getCdNm() == null) {
			return "NO_DATA";
		}
		
		boolean isExistTime = educationBiz.isExistTime(time);
				
		if (isExistTime) {
			result = "FAIL_V";
		}
		else {
			boolean insertResult = educationBiz.insertEduTime(time);
			if (insertResult) {
				result = "OK";
			}
			else {
				result = "FAIL_I";
			}
		}
		
		return result;
	}

	@Override
	public JsonResponseVO getChildCategory(String categoryId, String categoryType) {
		
		JsonResponseVO jsonResponseVO = new JsonResponseVO();
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId(categoryId);
		categoryVO.setCategoryType(categoryType);
		
		List<CategoryVO> childCategories = educationBiz.getChildCategory(categoryVO);
		
		if ( childCategories != null ) {
			jsonResponseVO.setResult(true);
			jsonResponseVO.setData(childCategories);
		}
		else {
			jsonResponseVO.setResult(false);
			jsonResponseVO.setData("에러 발생");
		}
		
		return jsonResponseVO;
		
	}

	@Override
	public String deleteCategory(String categoryId, String categoryType) {

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId(categoryId);
		categoryVO.setCategoryType(categoryType);
		
		return educationBiz.deleteCategory(categoryVO) + "";
		
	}

	@Override
	public JsonResponseVO modifyCategory(CategoryVO categoryVO, Errors errors) {
		
		JsonResponseVO jsonResponseVO = new JsonResponseVO();
		
		if ( errors.hasErrors() ) {
			jsonResponseVO.setResult(false);
			jsonResponseVO.setData(errors.getAllErrors());
		}
		else {
			boolean result = educationBiz.modifyCategory(categoryVO);
			if ( result ) {
				jsonResponseVO.setResult(true);
			}
			else {
				throw new RuntimeException("카테고리를 수정중 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
			}
		}
		
		return jsonResponseVO;
	}

}
