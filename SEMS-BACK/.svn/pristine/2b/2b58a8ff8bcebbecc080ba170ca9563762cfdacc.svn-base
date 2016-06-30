package com.ktds.sems.cooperation.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationTypeVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

public interface CooperationBiz {

	public int getTotalCooperationCount(CooperationSearchVO cooperationSearchVO);
	
	public boolean doRegisterCoo(CooperationVO cooperation);

	public boolean isExistCooperationTitle(String cooperationTitle);

	public int getTotalCooperationCount();

	public List<CooperationVO> getAllCooperation(CooperationSearchVO searchVO);

	public CooperationVO getOneCooperation(String cooperationId);

	public boolean doDeleteCooperation(String cooperationId);

	public boolean doModifyCoo(CooperationVO cooperation);

	public List<CooperationTypeVO> getCooTypeList();

}
