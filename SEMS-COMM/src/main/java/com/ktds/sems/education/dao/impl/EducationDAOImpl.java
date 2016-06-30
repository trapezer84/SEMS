package com.ktds.sems.education.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO{
	
	@Override
	public int validCategoryId(CategoryVO categoryVO) {
		return getSqlSession().selectOne("EducationDAO.validCategoryId", categoryVO);
	}

	@Override
	public CostVO getEduCostByCdId(String cdId) {
		return getSqlSession().selectOne("EducationDAO.getEduCostByCdId", cdId);
	}

	@Override
	public int modifyEduCost(CostVO modifyCost) {
		return getSqlSession().update("EducationDAO.modifyEduCost", modifyCost);
	}

	@Override
	public List<CostVO> getAllEduCost() {
		return getSqlSession().selectList("EducationDAO.getAllEduCost");
	}

	@Override
	public int deleteEduCost(String cdId) {
		return getSqlSession().delete("EducationDAO.deleteEduCost", cdId);
	}

	@Override
	public int insertEduCost(CostVO cost) {
		return getSqlSession().insert("EducationDAO.insertEduCost", cost);
	}

	@Override
	public int validCategoryName(CategoryVO categoryVO) {
		return getSqlSession().selectOne("EducationDAO.validCategoryName", categoryVO);
	}

	@Override
	public int addNewCategory(CategoryVO categoryVO) {
		return getSqlSession().insert("EducationDAO.addNewCategory", categoryVO);
	}

	@Override
	public List<CategoryVO> getAllLargeCategory() {
		return getSqlSession().selectList("EducationDAO.getAllLargeCategory");
	}

	@Override
	public int isExistCost(CostVO cost) {
		return getSqlSession().selectOne("EducationDAO.isExistCost", cost);
	}

	@Override
	public int isExistCostNm(CostVO cost) {
		return getSqlSession().selectOne("EducationDAO.isExistCostNm", cost);
	}

	@Override
	public List<TimeVO> getAllEduTime() {
		return getSqlSession().selectList("EducationDAO.getAllEduTime");
	}

	@Override
	public int deleteEduTime(String cdId) {
		return getSqlSession().delete("EducationDAO.deleteEduTime", cdId);
	}

	@Override
	public int isExistTimeNm(TimeVO time) {
		return getSqlSession().selectOne("EducationDAO.isExistTimeNm", time);
	}

	@Override
	public int modifyEduTime(TimeVO time) {
		return getSqlSession().update("EducationDAO.modifyEduTime", time);
	}

	@Override
	public TimeVO getEduTimeByCdId(String cdId) {
		return getSqlSession().selectOne("EducationDAO.getEduTimeByCdId", cdId);
	}

	@Override
	public int isExistTime(TimeVO time) {
		return getSqlSession().selectOne("EducationDAO.isExistTime", time);
	}

	@Override
	public int insertEduTime(TimeVO time) {
		return getSqlSession().insert("EducationDAO.insertEduTime", time);
	}

	@Override
	public List<CategoryVO> getChildCategory(CategoryVO categoryVO) {
		return getSqlSession().selectList("EducationDAO.getChildCategory", categoryVO);
	}

	@Override
	public int deleteCategory(CategoryVO categoryVO) {
		return getSqlSession().delete("EducationDAO.deleteCategory", categoryVO);
	}

	@Override
	public int modifyCategory(CategoryVO categoryVO) {
		return getSqlSession().update("EducationDAO.modifyCategory", categoryVO);
	}
}
