package com.ktds.sems.cooperation.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ktds.sems.cooperation.biz.CooperationBiz;
import com.ktds.sems.cooperation.dao.CooperationDAO;
import com.ktds.sems.cooperation.vo.CooperationVO;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationTypeVO;

public class CooperationBizImpl implements CooperationBiz{

	private CooperationDAO cooperationDAO;

	public void setCooperationDAO(CooperationDAO cooperationDAO) {
		this.cooperationDAO = cooperationDAO;
	}

	@Override
	public int getTotalCooperationCount(CooperationSearchVO cooperationSearchVO) {
		
		return cooperationDAO.getTotalCooperationCount(cooperationSearchVO);
	}
	
	public boolean doRegisterCoo(CooperationVO cooperation) {
		
		int nextRegisterCooperationId = cooperationDAO.nextCooSeq();
		String nowDate = cooperationDAO.nowDate();
		
		String cooperationId = "CO-" + nowDate + "-" + lpad(nextRegisterCooperationId + "", 6, "0");
		
		cooperation.setCooperationId(cooperationId);
		
		int registerSuccess = cooperationDAO.doRegisterCoo(cooperation);
		return registerSuccess > 0;
	}

	@Override
	public int getTotalCooperationCount() {
		return cooperationDAO.getTotalCooperationCount();
	}

	@Override
	public List<CooperationVO> getAllCooperation(CooperationSearchVO searchVO) {
		return cooperationDAO.getAllCooperation(searchVO);
	}

	@Override
	public CooperationVO getOneCooperation(String cooperationId) {
		return cooperationDAO.getOneCooperation(cooperationId);
	}

	@Override
	public boolean doDeleteCooperation(String cooperationId) {
		return cooperationDAO.doDeleteCooperation(cooperationId) > 0;
	}
	
	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;		
	}

	@Override
	public boolean isExistCooperationTitle(String title) {
		String existTitle = cooperationDAO.isExistCooperationTitle(title);
		if ( existTitle!= null ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean doModifyCoo(CooperationVO newCooperation) {
		String cooperationId = newCooperation.getCooperationId();
		
		CooperationVO oldCooperation = getOneCooperation(cooperationId);
		CooperationVO changeCooperation = new CooperationVO();
		changeCooperation.setCooperationId(cooperationId);
		
		int changeDataCount = 0;
		
		if ( !oldCooperation.getCooperationTitle().equals(newCooperation.getCooperationTitle()) ){
			changeCooperation.setCooperationTitle(newCooperation.getCooperationTitle());
			changeDataCount++;
		}
		if ( !oldCooperation.getCooperationLocation().equals(newCooperation.getCooperationLocation()) ){
			changeCooperation.setCooperationLocation(newCooperation.getCooperationLocation());
			changeDataCount++;
		}
		if ( !oldCooperation.getCooperationNumber().equals(newCooperation.getCooperationNumber()) ){
			changeCooperation.setCooperationNumber(newCooperation.getCooperationNumber());
			changeDataCount++;
		}
		if ( !oldCooperation.getRepresentativeName().equals(newCooperation.getRepresentativeName()) ){
			changeCooperation.setRepresentativeName(newCooperation.getRepresentativeName());
			changeDataCount++;
		}
		if ( !oldCooperation.getManagerPhoneNumber().equals(newCooperation.getManagerPhoneNumber()) ){
			changeCooperation.setManagerPhoneNumber(newCooperation.getManagerPhoneNumber());
			changeDataCount++;
		}
		if ( !oldCooperation.getCooperationPhoneNumber().equals(newCooperation.getCooperationPhoneNumber()) ){
			changeCooperation.setCooperationPhoneNumber(newCooperation.getCooperationPhoneNumber());
			changeDataCount++;
		}
		if ( !oldCooperation.getManagerEmail().equals(newCooperation.getManagerEmail()) ){
			changeCooperation.setManagerEmail(newCooperation.getManagerEmail());
			changeDataCount++;
		}
		if ( !oldCooperation.getCooperationType().equals(newCooperation.getCooperationType()) ){
			changeCooperation.setCooperationType(newCooperation.getCooperationType());
			changeDataCount++;
		}
		
		if ( changeDataCount > 0 ) {
			cooperationDAO.doModifyCoo(changeCooperation);
		}
		return true;
	}

	@Override
	public List<CooperationTypeVO> getCooTypeList() {
		return cooperationDAO.getCooTypeList(); 
	}
}
