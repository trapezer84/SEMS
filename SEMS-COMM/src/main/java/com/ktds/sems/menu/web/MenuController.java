package com.ktds.sems.menu.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.menu.service.MenuService;
import com.ktds.sems.menu.vo.MenuManageVO;

@Controller
public class MenuController {
	
	private MenuService menuService;

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping("/menu")
	public ModelAndView viewMenuPage() {
		return menuService.viewMenuManagePage();
	}
	
	/**
	 * 기능 대체로 사용 안함
	 * 
	 * @author 유병훈
	 * 
	 * @param sortNumber
	 * @param codeId
	 * @return
	 */
	/*@RequestMapping("/doMenuUpdate")
	public ModelAndView doMenuUpdate(@Valid MenuManageVO menuManageVO, Errors errors, HttpServletRequest request) {
	
		return menuService.doMenuUpdate(menuManageVO, errors, request);
	}*/
	
	@RequestMapping("/upMenuList/{sortNumber}/{codeId}")
	public String upMenuList(@PathVariable int sortNumber, @PathVariable int codeId) {
		
		return menuService.upMenuList(sortNumber, codeId);
	}
	
	@RequestMapping("/downMenuList/{sortNumber}/{codeId}")
	public String downMenuList(@PathVariable int sortNumber, @PathVariable int codeId) {
		
		return menuService.downMenuList(sortNumber, codeId);
	}
	
}
