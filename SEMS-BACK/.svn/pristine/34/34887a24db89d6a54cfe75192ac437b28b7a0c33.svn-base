package com.ktds.sems.team.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.team.vo.TeamSearchVO;

@Transactional
public class TeamServiceTest extends SemsTestCase {

	@Autowired
	private TeamService teamService;
	
	@Test
	public void getAllTeamsTest(){
		
		TeamSearchVO teamSearchVO = new TeamSearchVO();
		int pageNo = 0;
		
		ModelAndView view = teamService.getAllTeams(teamSearchVO, pageNo);
		
		assertNotNull(view);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "/team/teamList");
		} else {
			fail("fail...");
		}

	}
	
	@Test
	public void getOneTeamDetailTest() {
		String teamId = "1";
		
		ModelAndView view = teamService.getOneTeamDetail(teamId);
		
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/teamDetail");
		}
		else {
			fail("fail...");
		}
		
	}
	
	@Test
	public void getOneTeamBBSDetailTest() {
		String teamBBSId = "39";
		
		ModelAndView view = teamService.getOneTeamBBSDetail(teamBBSId);
		
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/teamBBSDetail");
		}
		else {
			fail("fail...");
		}
	}
	
	@Test
	public void getAllTeamByMemberIdTest() {
		String memberId = "test04";
		
		ModelAndView view = teamService.getAllTeamByMemberId(memberId);
		
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/otherTeams");
		}
		else {
			fail("fail...");
		}
	}

}
