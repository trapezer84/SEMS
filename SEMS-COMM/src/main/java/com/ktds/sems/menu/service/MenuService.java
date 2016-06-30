package com.ktds.sems.menu.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.menu.vo.MenuManageVO;


public interface MenuService {

	public ModelAndView viewMenuManagePage();

	//public ModelAndView doMenuUpdate(MenuManageVO menuManageVO, Errors errors, HttpServletRequest request);

	public String upMenuList(int sortNumber, int codeId);

	public String downMenuList(int sortNumber, int codeId);

}
