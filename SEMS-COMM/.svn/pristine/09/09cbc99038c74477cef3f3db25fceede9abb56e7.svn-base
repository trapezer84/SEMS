package com.ktds.sems.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.menu.biz.MenuBiz;
import com.ktds.sems.menu.service.MenuService;
import com.ktds.sems.menu.vo.MenuManageVO;

public class MenuServiceImpl implements MenuService{

	private MenuBiz menuBiz;
	
	public void setMenuBiz(MenuBiz menuBiz) {
		this.menuBiz = menuBiz;
	}


	@Override
	public ModelAndView viewMenuManagePage() {
		
		ModelAndView view = new ModelAndView();
		
		List<MenuManageVO> menuList = menuBiz.getMenuCategoryList();
		view.setViewName("menu/menuManage");
		view.addObject("menuList",menuList);
		view.addObject("listSize", menuList.size());
		
		return view;	
		
	}
	
	/**
	 * 기능 대체로 사용 안함
	 * 
	 * @author 유병훈
	 */
	/*@Override
	public ModelAndView doMenuUpdate(MenuManageVO menuManageVO, Errors errors, HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		boolean result = false;
		
		String[] codeId = menuManageVO.getCodeId().split(",");
		String[] sort = request.getParameterValues("sort");
		int i = 0;
		
		if ( errors.hasErrors() ) {
			view.setViewName("menu/menuManage");
			view.addObject("menuManageVO", menuManageVO);
			return view;
		}
		else {
			for (String id : codeId) {
				menuManageVO.setCodeId(id);
				menuManageVO.setSort(Integer.parseInt(sort[i]));
				i++;
				result = menuBiz.doMenuUpdate(menuManageVO);
			}
			
			if ( result ) {
				view.setViewName("redirect:/menu");
			}
			else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
			}
		}

		return view;
	}*/


	@Override
	public String upMenuList(int sortNumber, int codeId) {
		
		menuBiz.upMenuList(sortNumber, codeId);
		
		return "redirect:/menu";
	}


	@Override
	public String downMenuList(int sortNumber, int codeId) {

		menuBiz.downMenuList(sortNumber, codeId);
		
		return "redirect:/menu";
		
	}



}
