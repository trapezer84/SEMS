package com.ktds.sems.menu.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.menu.vo.MenuManageVO;

public class MenuDAOTest extends SemsTestCase {

   @Autowired
   private MenuDAO menuDAO;
   
   @Test
   public void getMenuCategoryListTest() {
      List<MenuManageVO> menu = menuDAO.getMenuCategoryList();
      assertNotNull(menu);
      assertTrue(menu.size() > 0);
   }

}