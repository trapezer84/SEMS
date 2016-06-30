package com.ktds.sems.menu.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

import com.ktds.sems.menu.vo.MenuManageVO;

public interface MenuBiz {

	public List<MenuManageVO> getMenuCategoryList();

	//public boolean doMenuUpdate(MenuManageVO menuManageVO);

	//public MenuManageVO getOneMenuCategory(String codeId);

	public void upMenuList(int sortNumber, int codeId);

	public void downMenuList(int sortNumber, int codeId);

}
