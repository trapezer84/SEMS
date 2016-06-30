package com.ktds.sems.team.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.TeamSearchVO;

@Controller
public class TeamController {

	private TeamService teamService;

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
	
	@RequestMapping("/teamList")
	public ModelAndView getAllTeams(TeamSearchVO teamSearchVO ,@RequestParam(required = false, defaultValue = "0") int pageNo) {
		ModelAndView view = teamService.getAllTeams(teamSearchVO ,pageNo);
		return view;
	}
	
	@RequestMapping("/teamDetail/{teamId}")
	public ModelAndView getOneTeamDetail(@PathVariable String teamId) {
		ModelAndView view = teamService.getOneTeamDetail(teamId);
		return view;
	}
	
	@RequestMapping("/teamBBSDetail/{teamBBSId}")
	public ModelAndView getOneTeamBBSDetail(@PathVariable String teamBBSId) {
		ModelAndView view = teamService.getOneTeamBBSDetail(teamBBSId);
		
		return view;
	}
	
	@RequestMapping("otherTeam/{memberId}")
	public ModelAndView getAllTeamByMemberId(@PathVariable String memberId) {
		ModelAndView view = teamService.getAllTeamByMemberId(memberId);
		return view;
	}
}
