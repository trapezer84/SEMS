package com.ktds.sems.education.dao;

import java.util.List;
import java.util.Map;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

public interface EducationDAO {
	
	public int validCategoryId(CategoryVO categoryVO);

	public CostVO getEduCostByCdId(String cdId);

	public int modifyEduCost(CostVO modifyCost);

	public List<CostVO> getAllEduCost();

	public int validCategoryName(CategoryVO categoryVO);

	public int addNewCategory(CategoryVO categoryVO);

	public int deleteEduCost(String cdId);

	public int insertEduCost(CostVO cost);

	public List<CategoryVO> getAllLargeCategory();

	public int isExistCost(CostVO cost);

	public int isExistCostNm(CostVO cost);

	public List<TimeVO> getAllEduTime();

	public int deleteEduTime(String cdId);

	public int isExistTimeNm(TimeVO time);

	public int modifyEduTime(TimeVO time);

	public TimeVO getEduTimeByCdId(String cdId);

	public int isExistTime(TimeVO time);

	public int insertEduTime(TimeVO time);

	public List<CategoryVO> getChildCategory(CategoryVO categoryVO);

	public int deleteCategory(CategoryVO categoryVO);

	public int modifyCategory(CategoryVO categoryVO);

}
