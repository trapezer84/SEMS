package com.ktds.sems.menu.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ktds.sems.menu.biz.MenuBiz;
import com.ktds.sems.menu.dao.MenuDAO;
import com.ktds.sems.menu.vo.MenuManageVO;

public class MenuBizImpl implements MenuBiz{

	private MenuDAO menuDAO;
	
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}


	@Override
	public List<MenuManageVO> getMenuCategoryList() {
		return menuDAO.getMenuCategoryList();
	}

	/**
	 * 기능 대체로 사용 안함
	 * 
	 * @author 유병훈
	 */
	/*@Override
	public boolean doMenuUpdate(MenuManageVO menuManageVO) {
		return menuDAO.doMenuUpdate(menuManageVO) > 0;
	*/

	/**
	 * 기능 대체로 사용 안함
	 * 
	 * @author 유병훈
	 */
	/*@Override
	public MenuManageVO getOneMenuCategory(String codeId) {
		return menuDAO.getOneMenuCategory(codeId);
	}*/


	@Override
	public void upMenuList(int sortNumber, int codeId) {
		
		Map<String, Integer> sortNo = new HashMap<String, Integer>();
		sortNo.put("sortNumber", sortNumber);
		sortNo.put("codeId", codeId);
		sortNo.put("prev", sortNumber-1);
		
		menuDAO.downMenuList(sortNo);
		menuDAO.upMenuList(sortNo);
	}


	@Override
	public void downMenuList(int sortNumber, int codeId) {

		Map<String, Integer> sortNo = new HashMap<String, Integer>();
		sortNo.put("sortNumber", sortNumber);
		sortNo.put("next", sortNumber+1);
		sortNo.put("codeId", codeId);
		
		menuDAO.upMenuList(sortNo);
		menuDAO.downMenuList(sortNo);
	}

}
