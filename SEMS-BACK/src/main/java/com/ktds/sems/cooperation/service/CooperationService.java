package com.ktds.sems.cooperation.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

public interface CooperationService {

	public ModelAndView getAllCooperationList(CooperationSearchVO cooperationSearchVO, int pageNo);

	public ModelAndView viewRegistCooPage();

	public ModelAndView doRegisterCoo(CooperationVO cooperationVO, Errors errors);

	public void isExistCooperationTitle(String title, HttpServletResponse response);

	public ModelAndView getOneCooperation(String cooperationId);

	public String doDeleteCooperation(String cooperationId);

	public ModelAndView viewModifyCooPage(String cooperationId);

	public ModelAndView doModifyCoo(CooperationVO cooperation, Errors errors);

}
