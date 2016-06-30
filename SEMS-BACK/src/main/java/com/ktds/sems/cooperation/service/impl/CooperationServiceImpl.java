package com.ktds.sems.cooperation.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.cooperation.biz.CooperationBiz;
import com.ktds.sems.cooperation.service.CooperationService;
import com.ktds.sems.cooperation.vo.CooperationListVO;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationTypeVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

import kr.co.hucloud.utilities.web.AjaxUtil;
import kr.co.hucloud.utilities.web.Paging;

public class CooperationServiceImpl implements CooperationService {

	private CooperationBiz cooperationBiz;

	public void setCooperationBiz(CooperationBiz cooperationBiz) {
		this.cooperationBiz = cooperationBiz;
	}

	@Override
	public ModelAndView viewRegistCooPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("cooperation/registerCoo");
		List<CooperationTypeVO> cooTypeList = cooperationBiz.getCooTypeList();
		view.addObject("cooTypeList", cooTypeList);
		return view;
	}

	@Override
	public ModelAndView doRegisterCoo(CooperationVO cooperation, Errors errors) {
		ModelAndView view = new ModelAndView();

		if (errors.hasErrors()) {
			view.setViewName("cooperation/registerCoo");
			List<CooperationTypeVO> cooTypeList = cooperationBiz.getCooTypeList();
			view.addObject("cooTypeList", cooTypeList);
			view.addObject("cooperation", cooperation);
			return view;
		} 
		else {
			
			boolean result = cooperationBiz.doRegisterCoo(cooperation);

			if (result) {
				view.setViewName("redirect:/cooList");
			} 
			else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
			}
		}
		return view;
	}

	@Override
	public void isExistCooperationTitle(String title, HttpServletResponse response) {
		String message = "OK";
		boolean isExistCooperationTitle = cooperationBiz.isExistCooperationTitle(title);

		if (isExistCooperationTitle) {
			message = "EXIST";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public ModelAndView getAllCooperationList(CooperationSearchVO cooperationSearchVO, int pageNo) {
		
		MockHttpSession session = new MockHttpSession();
		CooperationListVO cooperationListVO = new CooperationListVO();
		Paging paging = new Paging();
		cooperationListVO.setPaging(paging);
		
		paging.setPageNumber(pageNo + "");
		
		int totalCooperationCount = cooperationBiz.getTotalCooperationCount(cooperationSearchVO);
		paging.setTotalArticleCount(totalCooperationCount);
		
		cooperationSearchVO.setStartIndex(paging.getStartArticleNumber());
		cooperationSearchVO.setEndIndex(paging.getEndArticleNumber());
		cooperationSearchVO.setPageNo(0);
		
		List<CooperationTypeVO> typeNameList = cooperationBiz.getCooTypeList();
		
		session.setAttribute("_SEARCH_", cooperationSearchVO);
		
		List<CooperationVO> cooperationList = cooperationBiz.getAllCooperation(cooperationSearchVO);
		cooperationListVO.setCooperationList(cooperationList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("cooperation/cooperationList");
		view.addObject("cooperationListVO", cooperationListVO);
		view.addObject("typeNameList", typeNameList);
		view.addObject("searchVO", cooperationSearchVO);
		
		return view;
	}

	@Override
	public ModelAndView getOneCooperation(String cooperationId) {
		
		ModelAndView view = new ModelAndView();
		CooperationVO cooperationVO = cooperationBiz.getOneCooperation(cooperationId);
		
		view.setViewName("cooperation/cooperationDetail");
		view.addObject("cooperationVO", cooperationVO);
		
		List<CooperationTypeVO> cooTypeList = cooperationBiz.getCooTypeList();
		view.addObject("cooTypeList", cooTypeList);
		
		return view;
	}

	@Override
	public String doDeleteCooperation(String cooperationId) {
		
		boolean deleteResult = cooperationBiz.doDeleteCooperation(cooperationId);
		
		if ( deleteResult ) {
			return "redirect:/cooList";
		}
		else {
			throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
		}
		
	}

	@Override
	public ModelAndView viewModifyCooPage(String cooperationId) {
		
		ModelAndView view = new ModelAndView();
		
		CooperationVO cooperation = cooperationBiz.getOneCooperation(cooperationId);
		
		view.setViewName("cooperation/modifyCoo");
		view.addObject("cooperation", cooperation);
		
		List<CooperationTypeVO> cooTypeList = cooperationBiz.getCooTypeList();
		view.addObject("cooTypeList", cooTypeList);
		
		return view;
	}

	@Override
	public ModelAndView doModifyCoo(CooperationVO cooperation, Errors errors) {
		ModelAndView view = new ModelAndView();
		
		if (errors.hasErrors()) {
			view.setViewName("cooperation/modifyCoo");
			view.addObject("cooperation", cooperation);
			return view;
		} 
		else {
			
			boolean result = cooperationBiz.doModifyCoo(cooperation);

			if (result) {
				view.setViewName("redirect:/cooDetail/"+cooperation.getCooperationId());
				view.addObject("cooperationVO", cooperation);
				
			} 
			else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
			}
		}
		return view;
	}
}
