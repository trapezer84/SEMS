package com.ktds.sems.cooperation.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.cooperation.dao.CooperationDAO;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationTypeVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

public class CooperationDAOImpl extends SqlSessionDaoSupport implements CooperationDAO{

	@Override
	public int getTotalCooperationCount(CooperationSearchVO cooperationSearchVO) {
		return getSqlSession().selectOne("CooperationDAO.getTotalCooperationCount", cooperationSearchVO);
	}
	public int nextCooSeq() {
		return getSqlSession().selectOne("CooperationDAO.nextCooSeq");
	}

	@Override
	public String nowDate() {
		return getSqlSession().selectOne("CooperationDAO.nowDate");
	}

	@Override
	public int doRegisterCoo(CooperationVO cooperationVO) {
		return getSqlSession().insert("CooperationDAO.doRegisterCoo", cooperationVO);
	}

	@Override
	public String isExistCooperationTitle(String cooperationTitle) {
		return getSqlSession().selectOne("CooperationDAO.isExistCooperationTitle", cooperationTitle);
	}
	
	@Override
	public int getTotalCooperationCount() {
		return getSqlSession().selectOne("CooperationDAO.getTotalCooperationCount");
	}

	@Override
	public List<CooperationVO> getAllCooperation(CooperationSearchVO searchVO) {
		return getSqlSession().selectList("CooperationDAO.getAllCooperation", searchVO);
	}

	@Override
	public CooperationVO getOneCooperation(String cooperationId) {
		return getSqlSession().selectOne("CooperationDAO.getOneCooperation", cooperationId);
	}

	@Override
	public int doDeleteCooperation(String cooperationId) {
		return getSqlSession().delete("CooperationDAO.doDeleteCooperation", cooperationId);
	}

	@Override
	public void doModifyCoo(CooperationVO cooperation) {
		getSqlSession().update("CooperationDAO.doModifyCoo", cooperation);
	}
	@Override
	public List<CooperationTypeVO> getCooTypeList() {
		return getSqlSession().selectList("CooperationDAO.getCooTypeList");
	}
	
	@Override
	public String getOneCooperationId() {
		return getSqlSession().selectOne("CooperationDAO.getOneCooperationId");
	}
	
}
