package com.ktds.sems.team.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class TeamController {
	private Logger logger = LoggerFactory.getLogger(TeamController.class);
	private TeamService teamService;

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	@RequestMapping("/myTeamDetail/{teamId}")
	public ModelAndView getOneMyTeamDetail(@PathVariable String teamId){
		return teamService.getOneMyTeamDetail(teamId);
	}

	@RequestMapping("/myTeamList")
	public ModelAndView getAllMyTeamList(@RequestParam(required=false, defaultValue="0") int pageNo, HttpSession session){	
		return teamService.getAllMyTeamList(pageNo, session);
	}
	
	@RequestMapping("/team/teamBBS/board/{teamId}")
	public ModelAndView viewTeamBBSPage(@PathVariable String teamId, @RequestParam(required = false, defaultValue = "0") int pageNo) {
		return teamService.viewTeamBBSPage(teamId, pageNo);
	}
	

	@RequestMapping("/team/teamBBS/write")
	public ModelAndView viewWriteTeamBBSPage(HttpSession session, HttpServletResponse response) {
		return teamService.viewWriteTeamBBSPage(session, response);
	}
	

	@RequestMapping(value ="/team/teamBBS/doWrite", method = RequestMethod.POST)
	public ModelAndView doWriteTeamBBSAction(@Valid TeamBBSVO teamBBSVO, Errors errors, MultipartHttpServletRequest request, HttpSession session, HttpServletResponse response) {
		return teamService.addNewTeamBBSArticle(teamBBSVO, errors, request , session, response);
	}
	
	@RequestMapping("/team/teamBBS/detail/{teamBBSId}")
	public ModelAndView viewTeamBBSDetailPage(@PathVariable String teamBBSId, @RequestParam(required = false, defaultValue = "0") int pageNo 
			, HttpSession session){
		return teamService.viewTeamBBSDetailPage(teamBBSId, pageNo ,session);
	}
	/**
	 * 민
	 * @param teamBBSId
	 * @return
	 */
	@RequestMapping("/team/checkBBSModify/{teamBBSId}")
	public ModelAndView checkPasswordModify(@PathVariable String teamBBSId){
		String type = "modify";
		return teamService.checkPassword(teamBBSId, type);
	}
	
	@RequestMapping("/team/checkBBSDelete/{teamBBSId}")
	public ModelAndView checkPasswordDelete(@PathVariable String teamBBSId){
		String type = "delete";
		return teamService.checkPassword(teamBBSId, type);
	}
	
	@RequestMapping("/team/checkReply")
	public void isReplyByTeamBBSId(HttpServletRequest request, HttpServletResponse response){
		String teamBBSId = request.getParameter("teamBBSId");
		//TODO 이거해야댐
		String status = teamService.isReplyByTeamBBSId(teamBBSId);
		AjaxUtil.sendResponse(response, status);
	}
	/**
	 * 민
	 * @param teamBBSId
	 * @return
	 */
	@RequestMapping("/team/doModifyBBS/{teamBBSId}")
	public ModelAndView viewModifyPage(@PathVariable String teamBBSId){
		return teamService.viewModifyPage(teamBBSId);
	}
	/**
	 * 민
	 * @param teamBBS
	 * @return
	 */
	@RequestMapping("/team/doModifyAction")
	public ModelAndView doModifyAction(TeamBBSVO teamBBS, MultipartHttpServletRequest request, HttpSession session){
		if (teamBBS.getIsNotice() != null){
			if(teamBBS.getIsNotice().equals("on")){
				teamBBS.setIsNotice("Y");	
			}
		}else{
			teamBBS.setIsNotice("N");
		}
		return teamService.doModifyAction(teamBBS, request, session);
	}
	
	@RequestMapping("/teamList")
	public ModelAndView viewTeamListPage(TeamSearchVO teamSearchVO) {
		return teamService.getAllTeamListPage(teamSearchVO);
	}
	
	@RequestMapping("/team/teamDetail/{teamId}")
	public ModelAndView getOneTeamDetail(@PathVariable String teamId){
		return teamService.getOneTeamDetail(teamId);
		
	}
	@RequestMapping("/team/teamBBS/searchBBSList")
	public ModelAndView doSearchList(@RequestParam String createdYear, @RequestParam String createdMonth, @RequestParam String teamBBSId,
				@RequestParam String modifiedYear, @RequestParam String modifiedMonth, @RequestParam String title, @RequestParam String memberId, 
				@RequestParam String descript, @RequestParam String likeCount, @RequestParam String disLikeCount,  @RequestParam(required=false, defaultValue="0") String pageNo){
		
		if(pageNo.length() > 1) {
			pageNo = "0";
		}
		
		TeamBBSVO teamBBSVO = new TeamBBSVO();
		
		if( createdMonth.length() > 0 ) {
			if( createdMonth.length() == 1 ) {
				createdMonth = "0" + createdMonth;
			}
			teamBBSVO.setCreatedDate(createdYear + "/" + createdMonth);
		}
		else {
			teamBBSVO.setCreatedDate(null);
		}
		if ( modifiedMonth.length() > 0 ){
			if(modifiedMonth.length() == 1) {
				modifiedMonth = "0" + modifiedMonth;
			}
			teamBBSVO.setModifiedDate(modifiedYear + "/" + modifiedMonth);
		}
		else {
			teamBBSVO.setModifiedDate(null);
		}
		if(title.equals("")) {
			title = null;
		}
		if(memberId.equals("")){
			memberId = null;
		}
		if(descript.equals("")){
			descript = null;
		}
		if(likeCount.equals("")){
			likeCount = null;
		}
		if(disLikeCount.equals("")){
			disLikeCount = null;
		}
		
		if(title != null){
			title=title.trim().toLowerCase();
			logger.info(title);
		}
		if(memberId != null){
			teamBBSVO.setMemberId(memberId);
		}
		
		if(descript != null){
			teamBBSVO.setDescript(descript);
		}
		
		if(likeCount != null){
			teamBBSVO.setLikeCount(Integer.parseInt(likeCount));
		}
		
		if(disLikeCount != null){
			teamBBSVO.setDisLikeCount(Integer.parseInt(disLikeCount));
		}
		teamBBSVO.setTitle(title);
		teamBBSVO.setTeamBBSId(teamBBSId);
		
		return teamService.doSearchList( teamBBSVO , Integer.parseInt(pageNo) );
	}

	@RequestMapping("/team/teamBBS/like/{teamBBSId}")
	public String doLikeBBSAction(@PathVariable String teamBBSId, HttpSession session) {
		return teamService.doLikeBBSAction(teamBBSId, session);
	}
	
	@RequestMapping("/team/teamBBS/dislike/{teamBBSId}")
	public String doDislikeBBSAction(@PathVariable String teamBBSId, HttpSession session) {
		return teamService.doDislikeBBSAction(teamBBSId, session);
	}

	@RequestMapping("/team/doCheckPassword")
	public void doCheckPassword(@RequestParam String password, HttpSession session,
			HttpServletResponse response) {

		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		String sessionId = member.getId();
		String originSalt = teamService.getSaltById(sessionId);
		String inputPassword = SHA256Util.getEncrypt(password, originSalt);
		
		String originPassword = teamService.getPasswordById(sessionId);
		if (inputPassword.equals(originPassword)) {
			AjaxUtil.sendResponse(response, "OK");
		} else {
			AjaxUtil.sendResponse(response, "NO");
		}

	}
	
	@RequestMapping("/team/doDeleteBBS/{teamBBSId}")
	public String doDeleteBBS(@PathVariable String teamBBSId) {
		return teamService.doDeleteBBS(teamBBSId);
	}

	
	@RequestMapping("/searchInitBtn")
	public String teamSearchInit(){
		return "redirect:/teamList";
	}
	@RequestMapping("/team/writeMinutes/{teamId}")
	public ModelAndView viewWriteMinutesPage(@PathVariable String teamId) {
		return teamService.viewWriteMinutesPage(teamId);
	}
	
	@RequestMapping(value ="/team/teamBBS/detail/doWriteReply", method = RequestMethod.POST)
	public ModelAndView doWriteBBSReplyAction(@Valid TeamBBSReplyVO teamBBSReplyVO , HttpSession session ){
		return teamService.doWriteBBSReplyAction(teamBBSReplyVO,  session);
	}
	
	@RequestMapping(value="/team/doWriteAction/{teamId}", method = RequestMethod.POST)
	public ModelAndView doWriteAction(@PathVariable String teamId, @Valid MinutesVO minutesVO, Errors errors, HttpSession session){
		return teamService.writeNewMinutes(teamId, minutesVO, errors, session);
	}
	
	@RequestMapping("/team/listMinutes/{teamId}")
	public ModelAndView viewListMinutesPage(@PathVariable String teamId, MinutesSearchVO  minutesSearchVO, @RequestParam(required=false, defaultValue="0") int pageNo) {
		
		return teamService.viewListMinutes(teamId, minutesSearchVO, pageNo);
	}
	
	@RequestMapping("/member/minutesInit")
	public ModelAndView minutesInit() {
		return teamService.minutesInit();
	}
	
	@RequestMapping(value="/team/teamBBS/detail/doGetReReply", method = RequestMethod.POST)
	public ModelAndView viewReReplyPage(@RequestParam String teamBBSId, HttpSession session,@RequestParam String parentReplyId ){
		return teamService.viewReReplyPage(teamBBSId,session,parentReplyId  );
	}

	@RequestMapping("/{educationId}/registTeam")
	public ModelAndView viewEduFilePage(HttpSession session, @PathVariable String educationId){
		return teamService.getAllEduMember(educationId, session);
	}
	
	@RequestMapping("/buildTeam")
	public ModelAndView massiveInsertMember(HttpServletRequest request){
		String[] insertMemberIds = request.getParameterValues("selectMemberId");
		String educationId = request.getParameter("educationId");
		String teamName = request.getParameter("teamName");
		String teamListId = null;
		
		return teamService.massiveInsertMember(insertMemberIds, educationId, teamName, teamListId);
	}
	
	@RequestMapping("/minutesList")
	public ModelAndView viewMinutesPage(MinutesSearchVO minutesSearchVO, @RequestParam(required = false, defaultValue = "0") int pageNo, HttpSession session){
		return teamService.getAllMinutes(minutesSearchVO, pageNo, session);
	}
	
	@RequestMapping("/minutesListInit")
	public ModelAndView minutesListInit() {
		return teamService.minutesListInit();
	}
	
	@RequestMapping("/team/detailMinutes/{minutesId}")
	public ModelAndView getOneDetailMinutes(@PathVariable String minutesId, HttpSession session) {
		return teamService.getOneDetailMinutes(minutesId, session);
	}
}
