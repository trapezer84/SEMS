package com.ktds.sems.education.biz.impl;

import java.util.List;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

public class EducationBizImpl implements EducationBiz {
	
	private EducationDAO educationDAO;

	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}
	
	@Override
	public boolean validCategoryId(CategoryVO categoryVO) {
		return educationDAO.validCategoryId(categoryVO) > 0;
	}

	@Override
	public boolean modifyEduCost(CostVO cost) {
		CostVO modifyCost = new CostVO();
		CostVO origCost = new CostVO();
		
		origCost = educationDAO.getEduCostByCdId(cost.getCdId());
		if ( cost.getCdId().equals(origCost.getCdId())) {
			modifyCost.setCdId(origCost.getCdId());
			
			if ( !cost.getCdNm().equals(origCost.getCdNm()) ) {
				modifyCost.setCdNm(cost.getCdNm());
			}
		}
		return educationDAO.modifyEduCost(modifyCost) > 0;
	}

	@Override
	public CostVO getEduCostByCdId(String cdId) {
		return educationDAO.getEduCostByCdId(cdId);
	}

	@Override
	public List<CostVO> getAllEduCost() {
		return educationDAO.getAllEduCost();
	}

	@Override
	public boolean deleteEduCost(String cdId) {
		return educationDAO.deleteEduCost(cdId) > 0;
	}

	@Override
	public boolean insertEduCost(CostVO cost) {
		return educationDAO.insertEduCost(cost) > 0;
	}


	@Override
	public boolean validCategoryName(CategoryVO categoryVO) {
		return educationDAO.validCategoryName(categoryVO) > 0;
	}

	@Override
	public boolean addNewCategory(CategoryVO categoryVO) {
		return educationDAO.addNewCategory(categoryVO) > 0;
	}

	@Override
	public List<CategoryVO> getAllLargeCategory() {
		return educationDAO.getAllLargeCategory();
	}

	@Override
	public boolean isExistCost(CostVO cost) {
		int isExistCost = educationDAO.isExistCost(cost);
		
		if ( isExistCost > 0 ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExistCostNm(CostVO cost) {
		int isExistCostNm = educationDAO.isExistCostNm(cost);
		
		if ( isExistCostNm > 0 ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<TimeVO> getAllEduTime() {
		return educationDAO.getAllEduTime();
	}

	@Override
	public boolean deleteEduTime(String cdId) {
		return educationDAO.deleteEduTime(cdId) > 0;
	}

	@Override
	public boolean isExistTimeNm(TimeVO time) {
		int isExistTimeNm = educationDAO.isExistTimeNm(time);
		
		if ( isExistTimeNm > 0 ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean modifyEduTime(TimeVO time) {
		TimeVO modifyTime = new TimeVO();
		TimeVO origTime = new TimeVO();
		
		origTime = educationDAO.getEduTimeByCdId(time.getCdId());
		if ( time.getCdId().equals(origTime.getCdId())) {
			modifyTime.setCdId(origTime.getCdId());
			
			if ( !time.getCdNm().equals(origTime.getCdNm()) ) {
				modifyTime.setCdNm(time.getCdNm());
			}
		}
		
		return educationDAO.modifyEduTime(time) > 0;
	}

	@Override
	public boolean isExistTime(TimeVO time) {
		int isExistTime = educationDAO.isExistTime(time);
		
		if ( isExistTime > 0 ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean insertEduTime(TimeVO time) {
		return educationDAO.insertEduTime(time) > 0;
	}

	@Override
	public List<CategoryVO> getChildCategory(CategoryVO categoryVO) {
		return educationDAO.getChildCategory(categoryVO);
	}

	@Override
	public boolean deleteCategory(CategoryVO categoryVO) {
		boolean result = false;
		if ( categoryVO.getCategoryType().equals("large") ) {
			result = deleteLargeCategory(categoryVO);
		}
		else if ( categoryVO.getCategoryType().equals("medium") ) {
			result = deleteMediumCategory(categoryVO);
		}
		else if ( categoryVO.getCategoryType().equals("small") ) {
			result = deleteSmallCategory(categoryVO);
		}
		
		return result;
	}

	@Override
	public boolean modifyCategory(CategoryVO categoryVO) {
		return educationDAO.modifyCategory(categoryVO) > 0;
	}

	private boolean deleteLargeCategory(CategoryVO categoryVO) {
		
		List<CategoryVO> childCategories = getChildCategory(categoryVO);

		for (CategoryVO tmpCategoryVO : childCategories) {
			tmpCategoryVO.setCategoryType("medium");
			if ( !deleteMediumCategory(tmpCategoryVO) ) {
				return false;
			}
		}
		
		return educationDAO.deleteCategory(categoryVO) > 0;
	}

	private boolean deleteMediumCategory(CategoryVO categoryVO) {
		List<CategoryVO> childCategories = getChildCategory(categoryVO);

		for (CategoryVO tmpCategoryVO : childCategories) {
			tmpCategoryVO.setCategoryType("small");
			if ( !deleteSmallCategory(tmpCategoryVO) ) {
				return false;
			}
		}

		return educationDAO.deleteCategory(categoryVO) > 0;
	}

	private boolean deleteSmallCategory(CategoryVO categoryVO) {
		return educationDAO.deleteCategory(categoryVO) > 0;
	}
	
}
