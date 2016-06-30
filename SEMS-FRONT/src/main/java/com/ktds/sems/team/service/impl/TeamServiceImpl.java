package com.ktds.sems.team.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.member.vo.MemberListVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.MinutesListVO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSListVO;
import com.ktds.sems.team.vo.TeamBBSReplyListVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamListVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;
import com.ktds.sems.team.vo.TeamsListsVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.Paging;

public class TeamServiceImpl implements TeamService{
	private Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
	private TeamBiz teamBiz;
	private FileBiz fileBiz;

	public void setFileBiz(FileBiz fileBiz) {
		this.fileBiz = fileBiz;
	}
	public void setTeamBiz(TeamBiz teamBiz) {
		this.teamBiz = teamBiz;
	}
	
	@Override
	public ModelAndView getAllMyTeamList(int pageNo, HttpSession session) {
		
		TeamListVO teamListVO = new TeamListVO();
		Paging paging = new Paging(15,15);

		MemberVO member = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = member.getId();
		
		teamListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int totalMyTeamCount = teamBiz.getTotalMyTeamCount(session);
		if( totalMyTeamCount == 0 ){
			totalMyTeamCount++;
		}
		paging.setTotalArticleCount(totalMyTeamCount);

		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());	
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<TeamVO> teamList = teamBiz.getAllMyTeamList(searchVO, memberId);
		teamListVO.setTeamList(teamList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("myPage/myTeamList");
		if(teamListVO.getTeamList().size() > 0 ){
			view.addObject("teamListVO", teamListVO);
		}
		return view;
	}

	@Override
	public ModelAndView getOneMyTeamDetail(String teamId) {
	
		ModelAndView view = new ModelAndView();
		TeamVO team = teamBiz.getOneMyTeamDetail(teamId);
		view.addObject("team", team);
		view.setViewName("myPage/teamDetail");		
	
		return view;
	}

	@Override
	public ModelAndView getAllTeamListPage(TeamSearchVO teamSearchVO) {
		
		if(teamSearchVO == null) {
			teamSearchVO = new TeamSearchVO();
			teamSearchVO.setPageNo(0);
		}
		
		TeamListVO teamListVO = new TeamListVO();
		Paging paging = new Paging();
		teamListVO.setPaging(paging);
		
		paging.setPageNumber(teamSearchVO.getPageNo() + "");
		
		int totalTeamCount = teamBiz.getTotalTeamCount();
		paging.setTotalArticleCount(totalTeamCount);
		
		teamSearchVO.setStartIndex(paging.getStartArticleNumber());
		teamSearchVO.setEndIndex(paging.getEndArticleNumber());	
		
		List<TeamVO> teamList = teamBiz.getAllTeamList(teamSearchVO);
		teamListVO.setTeamList(teamList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("team/teamList");
		view.addObject("teamListVO",teamListVO);
		view.addObject("teamSearchVO", teamSearchVO);
		
		return view;
		
	}

	@Override
	public ModelAndView addNewTeamBBSArticle(TeamBBSVO teamBBS, Errors errors, MultipartHttpServletRequest request,  HttpSession session, HttpServletResponse response) {
	
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		if(errors.hasErrors()){
			view.setViewName("/team/teamBBS/write");
		}else{
			teamBBS.setMemberId(memberVO.getId());
			// 공지사항 체크박스 선택유무
			if (teamBBS.getIsNotice() != null){
				if(teamBBS.getIsNotice().equals("on")){
					teamBBS.setIsNotice("Y");
				} 
			} else {
				teamBBS.setIsNotice("N");	
			}
			
			// 로그인 했을 시
			if( memberVO.getId() != null ) {
				boolean success = teamBiz.addNewTeamBBSArticle(teamBBS, request);
				logger.info("결과는 : " + success);
				
				if(success){
					view.setViewName("redirect:/team/teamBBS/board");
				}else { 
					throw new RuntimeException("팀별 게시판 글쓰기 실패");
				}
			// 로그인 안했을 시
			} else {
				try {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "권한없는 접속.");
				} catch (IOException io) {
					throw new RuntimeException(io.getMessage());
				}
			}
		}
		return view;
	}

	@Override
	public ModelAndView viewTeamBBSPage(String teamId, int pageNo) {
		
		TeamBBSListVO searchedListVO = new TeamBBSListVO();
		Paging paging = new Paging(15,15);
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int searchedBBSCount = teamBiz.getSearchedBBSCountByTeamId(teamId);
		if(searchedBBSCount == 0 ){
			searchedBBSCount ++;
		}
		paging.setTotalArticleCount(searchedBBSCount);
		
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		searchVO.setTeamId(teamId);
		
		String startYear = teamBiz.getStartYear();
		String endYear = teamBiz.getEndYear();
		
		ModelAndView view = new ModelAndView();
		List<TeamBBSVO> teamBBSList = teamBiz.getTeamBBSList(searchVO);
		searchedListVO.setTeamBBSList(teamBBSList);
		
	//	logger.info("teamBBSList사이즈"+teamBBSList.size());
		view.addObject("fromYear", startYear);
		view.addObject("toYear", endYear);
		view.addObject("TeamBBSListVO",searchedListVO );
		view.setViewName("team/teamBoard");
		return view;
	}
	
	@Override
	public ModelAndView checkPassword(String teamBBSId, String type) {
		ModelAndView view = new ModelAndView();

		view.addObject("teamBBSId", teamBBSId);
		view.addObject("type", type);
		view.setViewName("team/checkPassword");

		return view;
	}

	@Override
	public ModelAndView viewModifyPage(String teamBBSId) {
		ModelAndView view = new ModelAndView();
		String FileName = teamBiz.getFileInfo(teamBBSId);

		TeamBBSVO teamBBS = teamBiz.getTeamBBS(teamBBSId);
		view.addObject("teamBBS",teamBBS );
		view.addObject("FileName", FileName);
		view.setViewName("team/modifyBBS");
		return view;
	}

	@Override
	public ModelAndView getOneTeamDetail(String teamListId) {
		ModelAndView view = new ModelAndView();
		TeamsListsVO teamsListsVO = new TeamsListsVO();

		teamsListsVO.setTeamsListsVO(teamBiz.getOneTeamDetail(teamListId));

		view.setViewName("team/teamDetail");
		view.addObject("teamsListsVO",teamsListsVO);
		return view;
	}
	
	@Override
	public ModelAndView doSearchList(TeamBBSVO teamBBSVO, int pageNo ) {
		TeamBBSListVO searchedListVO = new TeamBBSListVO();
		Paging paging = new Paging(15,15);
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int searchedBBSCount = teamBiz.getSearchedBBSCount();
		if(searchedBBSCount == 0 ){
			searchedBBSCount ++;
		}
		paging.setTotalArticleCount(searchedBBSCount);
		
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	
		
		List<TeamBBSVO> searchedBBS = teamBiz.doSearchList(teamBBSVO, searchVO);
		
		searchedListVO.setTeamBBSList(searchedBBS);
		
		String startYear = teamBiz.getStartYear();
		String endYear = teamBiz.getEndYear();
		ModelAndView view = new ModelAndView();
		
		view.addObject("fromYear", startYear);
		view.addObject("toYear", endYear);
		
		view.addObject("searchKeyword", teamBBSVO);
		view.addObject("searchedListVO", searchedListVO);
		
		view.setViewName("team/teamBoard");
		return view;
	}

	
	@Override
	public ModelAndView viewTeamBBSDetailPage(String teamBBSId, int pageNo, HttpSession session ) {
		
		TeamBBSReplyListVO teamBBSReplyListVO = new TeamBBSReplyListVO();
		Paging paging = new Paging(15,15);
		logger.info("???");
		teamBBSReplyListVO.setPaging(paging);
		paging.setPageNumber(pageNo +"");
		
		int replyCount = teamBiz.getReplyCountByTeamBBSId(teamBBSId);
		if(replyCount == 0 ){
			replyCount ++;
		}
		paging.setTotalArticleCount(replyCount);
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		searchVO.setTeamBBSId(teamBBSId);
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		TeamBBSVO teamBBS = teamBiz.getTeamBBS(teamBBSId); 
		List<TeamBBSReplyVO> replies = teamBiz.getTeamBBSReplies(searchVO);
		
		if(member != null) {
			TeamBBSVO bbs = new TeamBBSVO();
			bbs.setMemberId(member.getId());
			bbs.setTeamBBSId(teamBBSId);
			// 조회수를 추가해준다.
			teamBiz.addHitsRecord(bbs);
			
			// 좋아요 싫어요 정보를 가져온다.
			String likeState = teamBiz.getLikeState(bbs);
			String dislikeState = teamBiz.getDislikeState(bbs);
			logger.info("likeState" + likeState);
			logger.info("dislikeState" + dislikeState);
			logger.info("?aadsadasadsa?");
			teamBBS.setLikeState(likeState);
			teamBBS.setDislikeState(dislikeState);
			logger.info("?aaa?");
		}else{
			throw new RuntimeException("멤버 정보가 없습니다.");
		}
		logger.info("?ccc??");
		ModelAndView view = new ModelAndView();
		logger.info("teamBBS.getFileCount()"+teamBBS.getFileCount());
		if( teamBBS.getFileCount() == 0 ){
			String fileName = teamBiz.getFileInfo(teamBBSId);
			view.addObject("fileName", fileName);
		}else{
			view.addObject("fileName", " ");
		}
		
		logger.info("replies.size()"+replies.size());
		teamBBSReplyListVO.setTeamBBSReplyList(replies);
		view.addObject("teamBBSReplyListVO", teamBBSReplyListVO);
		view.addObject("teamBBS",teamBBS );
		
		view.addObject("memberId", member.getId());
	//	view.addObject("replies", replies);
		view.setViewName("team/detailTeamBBS");
		return view;
	}

	@Override
	public String doLikeBBSAction(String teamBBSId, HttpSession session) {
		// 접속한 멤버 정보를 가져온다. 
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		if(member != null) {
			TeamBBSVO bbs = new TeamBBSVO();
			bbs.setMemberId(member.getId());
			bbs.setTeamBBSId(teamBBSId);
			
			// 먼저 같은 게시글에 싫어요 했는지 체크
			boolean isExistedDislike = teamBiz.checkDislikeByTeamBBSVO(bbs);
			
			// 같은 게시글에 싫어요가 있다면 
			if ( isExistedDislike ) {
				throw new RuntimeException("이미 싫어요로 선택하셨습니다.");
			} else { // 싫어요가 없다면 기록해준다.
				teamBiz.addLikeRecord(bbs);
				teamBiz.addLikeCount(bbs);
			}
			
		}else{
			throw new RuntimeException("멤버 정보가 없습니다.");
		}
		return "redirect:/team/teamBBS/detail/{teamBBSId}";
	}

	@Override
	public String doDislikeBBSAction(String teamBBSId, HttpSession session) {
		// 접속한 멤버 정보를 가져온다. 
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		if(member != null) {
			TeamBBSVO bbs = new TeamBBSVO();
			bbs.setMemberId(member.getId());
			bbs.setTeamBBSId(teamBBSId);
			
			// 먼저 같은 게시글에 좋아요했는지 체크
			boolean isExistedlike = teamBiz.checkLikeByTeamBBSVO(bbs);
			
			// 같은 게시글에 좋아요가 있다면 
			if ( isExistedlike ) {
				throw new RuntimeException("이미 좋아요로 선택하셨습니다.");
			} else { // 싫어요가 없다면 기록해준다.
				teamBiz.addDislikeRecord(bbs);
				teamBiz.addDislikeCount(bbs);
			}
			
		}else{
			throw new RuntimeException("멤버 정보가 없습니다.");
		}
		return "redirect:/team/teamBBS/detail/{teamBBSId}";
	}

	@Override
	public ModelAndView doWriteBBSReplyAction(TeamBBSReplyVO replyVO, HttpSession session) {
		MemberVO member= (MemberVO) session.getAttribute("_MEMBER_");
		String id = member.getId();
		logger.info("member"+id);
		logger.info("descript"+replyVO.getDescript());
		logger.info("teambbsId"+replyVO.getTeamBBSId());
		logger.info("groupId"+replyVO.getGroupId());
		logger.info("parentReplyId "+replyVO.getParentReplyId());
		logger.info("depth"+replyVO.getDepth());
		logger.info("orderNo"+replyVO.getOrderNo());
		
		replyVO.setMemberId(id);
		replyVO.setDescript(replyVO.getDescript().replaceAll("\n", "<br/>").replaceAll("\r", ""));
		
		logger.info("Descript"+replyVO.getDescript());
		
		// 대댓글이 아닐시
		if(replyVO.getParentReplyId().equals("0")){
			teamBiz.writeBBSReply(replyVO);
		}else{ //대댓글 일시
			
			teamBiz.writeBBSReReply(replyVO);
		}
		
		return new ModelAndView("redirect:/team/teamBBS/detail/" + replyVO.getTeamBBSId());
	}

	@Override
	public ModelAndView writeNewMinutes(String teamId, MinutesVO minutesVO, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		if (!errors.hasErrors()) {
			view.setViewName("redirect:/team/writeMinutes/"+teamId);
		}
		else {
			if ( memberVO != null ) {
				minutesVO.setMemberId(memberVO.getId());
				minutesVO.setTeamId(teamId);
				minutesVO.setStartDate(minutesVO.getAgendaDate() + " " + minutesVO.getStartTime());
				minutesVO.setEndDate(minutesVO.getAgendaDate() + " " + minutesVO.getEndTime());
			} else {
				throw new RuntimeException("값을 입력해 주세요.");
			}
			
			boolean result = teamBiz.writeNewMinutes(minutesVO);
			
			logger.info("마지막 결과값 : " + result);
			if ( result ) {
				view.setViewName("redirect:/listMinutes");
			}
			else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 이용해주세요.");
			}
		}
		return view;
	}

	@Override
	public ModelAndView viewListMinutes(String teamId, MinutesSearchVO minutesSearchVO, int pageNo) {
		
		MinutesListVO minutesListVO = new MinutesListVO();
		Paging paging = new Paging(10, 10);
		
		minutesListVO.setPaging(paging);
		int totalHistoryCount = teamBiz.getTotalMinutesCount(minutesSearchVO);
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalHistoryCount);

		minutesSearchVO.setStartIndex(paging.getStartArticleNumber());
		minutesSearchVO.setEndIndex(paging.getEndArticleNumber());
		minutesSearchVO.setTeamId(teamId);
		
		List<MinutesVO> minutesList = teamBiz.getAllMinutesList(minutesSearchVO);
		
		minutesListVO.setMinutesList(minutesList);

		ModelAndView view = new ModelAndView();
		view.setViewName("team/listMinutes");
		view.addObject("minutesListVO", minutesListVO);
		view.addObject("minutesSearchVO", minutesSearchVO);
		
		return view;
	}

	@Override
	public ModelAndView minutesInit() {
		
		ModelAndView view =new ModelAndView();
		
		view.addObject("startDate", null);
		view.addObject("endDate", null);
		view.setViewName("redirect:/listMinutes");
		
		return view;
	}

	@Override
	public ModelAndView viewReReplyPage(String teamBBSId, HttpSession session, String parentReplyId) {

		List<TeamBBSReplyVO> reReplies = teamBiz.getTeamBBSReReplies(parentReplyId); 
		
		ModelAndView view = new ModelAndView();
		view.addObject("reReplies", reReplies);
		logger.info("reReplies"+reReplies.size());
		view.setViewName("team/viewReply");
		return view;
	}

	@Override
	public ModelAndView getOneDetailMinutes(String minutesId, HttpSession session) {
		
		if (minutesId != null) {
			MinutesVO minutesVO = teamBiz.getOneDetailMinutes(minutesId);
			
			String startMinutesDate = teamBiz.getOneDetailMinutesDate(minutesId);
			ModelAndView view = new ModelAndView();
			minutesVO.setMinutesDate(startMinutesDate);
			view.addObject("minutesVO", minutesVO);
			view.setViewName("team/detailMinutes");
			
			return view;
		} else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
	}

	@Override
	public ModelAndView getAllMinutes(MinutesSearchVO minutesSearchVO, int pageNo, HttpSession session) {

		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		MinutesListVO minutesListVO = new MinutesListVO();
		Paging paging = new Paging();

		minutesListVO.setPaging(paging);
		int totalMinutesCount = teamBiz.getTotalMinutesCountForAdmin(minutesSearchVO);

		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalMinutesCount);

		minutesSearchVO.setStartIndex(paging.getStartArticleNumber());
		minutesSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<MinutesVO> minutesList = teamBiz.getAllMinutes(minutesSearchVO);
		minutesListVO.setMinutesList(minutesList);

		ModelAndView view = new ModelAndView();

		if( memberType.equals("ADM") || memberType.equals("TR") || memberType == null){
			view.setViewName("team/minutesList");
			view.addObject("minutesListVO", minutesListVO);
			view.addObject("minutesSearchVO", minutesSearchVO);
		}
		else{
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}
	@Override
	public void doDeleteTeamListByMemberId(String memberId, String teamName) {
		teamBiz.doDeleteTeamListByMemberId(memberId);
		teamBiz.doDeleteTeamByTeamName(teamName);
	}

	@Override
	public ModelAndView getAllEduMember(String educationId, HttpSession session) {
		MemberListVO memberListVO = new MemberListVO();
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		ModelAndView view = new ModelAndView();
		if ( memberType.equals("ADM") || memberType.equals("TR") ) {
			List<MemberVO> memberList = teamBiz.getAllEduMember(educationId);
			view.addObject("memberSize",memberList.size());
			memberListVO.setMemberList(memberList);
			view.setViewName("education/registTeam");
			view.addObject("memberListVO", memberListVO);
		}
		else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}

	@Override
	public ModelAndView massiveInsertMember(String[] insertMemberIds, String educationId, String teamName, String teamListId) {

		TeamsListVO teamsListVO = new TeamsListVO();
		if(teamListId != null) {
			teamsListVO.setTeamListId(teamListId);
		}
		
		teamBiz.bulidTeam(educationId, teamName);
		for(String memberId : insertMemberIds){
			
			teamsListVO.setMbrId(memberId);
			boolean isInsertMember = teamBiz.insertMember(teamsListVO);	
			if(!isInsertMember){
				throw new RuntimeException("실패했습니다.");
			}
		}

		ModelAndView view = new ModelAndView();
		view.setViewName("team/teamBoard");
		return view;
	}

	@Override
	public ModelAndView minutesListInit() {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/minutesList");
		return view;
	}

	@Override
	public String getPasswordById(String sessionId) {
		return teamBiz.getPasswordById(sessionId);
	}

	@Override
	public String getSaltById(String sessionId) {
		return teamBiz.getSaltById(sessionId);
	}

	@Override
	public String doDeleteBBS(String teamBBSId) {

		teamBiz.doDeleteBBS(teamBBSId);
		return "redirect:/team/teamBBS/board";
	}
	
	@Override
	public String isReplyByTeamBBSId(String teamBBSId) {
		boolean result = teamBiz.isReplyByTeamBBSId(teamBBSId);

		if( result ) {
			return "NO";
		}
		else{
			return "OK";
		}
	}

	@Override
	public ModelAndView doModifyAction(TeamBBSVO teamBBS, MultipartHttpServletRequest request, HttpSession session) {
		//파일 업데이트
		MultipartFile file = request.getFile("file");

		String fileName = file.getOriginalFilename();
		String salt = SHA256Util.generateSalt();
		String saltFileName = SHA256Util.getEncrypt(fileName, salt);

		String filePath = "D:\\" + saltFileName;


		ModelAndView view = new ModelAndView();

		//이전 내용
		TeamBBSVO prevTeamBBS = teamBiz.getTeamBBS(teamBBS.getTeamBBSId()); 
		TeamBBSVO changeTeamBBS = new TeamBBSVO();
		String prevFileName = teamBiz.getFileInfo(teamBBS.getTeamBBSId());

		// 댓글
		TeamBBSReplyVO replyVO = new TeamBBSReplyVO();
		replyVO.setTeamBBSId(teamBBS.getTeamBBSId()); // 어느게시글에 댓글을 달건지
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		replyVO.setMemberId(member.getId());

		// 내용에 넣을것
		String isTitle = "";
		String isNotice = "";
		String isFileName = "";
		String isDescript = "";

		changeTeamBBS.setIsNotice(teamBBS.getIsNotice());
		changeTeamBBS.setFileName(teamBBS.getFileName());

		if( !prevTeamBBS.getTitle().equals(teamBBS.getTitle()) ){
			changeTeamBBS.setTitle(teamBBS.getTitle());
			isTitle = "제목이 "+ prevTeamBBS.getTitle() + " 에서 " + changeTeamBBS.getTitle() + " 로 변경 되었습니다.<br/>";
		}
		if( !prevTeamBBS.getDescript().equals(teamBBS.getDescript()) ){
			changeTeamBBS.setDescript(teamBBS.getDescript());
			isDescript = "내용이 " + prevTeamBBS.getDescript() + " 에서 " + changeTeamBBS.getDescript() + " 로 변경 되었습니다.<br/>";
		}
		if( !prevTeamBBS.getIsNotice().equals(teamBBS.getIsNotice()) ){
			changeTeamBBS.setIsNotice(teamBBS.getIsNotice());
			isNotice = "공지가 " + prevTeamBBS.getIsNotice() + " 에서 " + changeTeamBBS.getIsNotice() + " 로 변경 되었습니다.<br/>";
		}
		if( !prevFileName.equals(fileName) && !file.isEmpty() ){
			changeTeamBBS.setFileName(teamBBS.getFileName());
			isFileName = "파일이 " + prevFileName + " 에서 " + fileName + " 로 변경 되었습니다.<br/>";
		}

		String Descript = isTitle + isDescript + isNotice + isFileName;
		
		if ( Descript != null && !Descript.equals("") ) {
			replyVO.setDescript(Descript);
			teamBiz.writeBBSReply(replyVO);
		}

		//내용 업데이트
		teamBiz.doModifyAction(teamBBS);

		if ( !file.isEmpty() ) {

			File files = new File(filePath);

			try {
				file.transferTo(files);

				FileVO fileVO = new FileVO();
				fileVO.setArticleId(teamBBS.getTeamBBSId());
				fileVO.setFileName(fileName);
				fileVO.setFileLocation(filePath);

				fileBiz.updateFile(fileVO);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		/*return "redirect:/team/teamBBS/detail/{teamBBSId}";*/
		view.setViewName("redirect:/team/teamBBS/board");
		return view;
	}
	
	@Override
	public ModelAndView viewWriteTeamBBSPage(HttpSession session, HttpServletResponse response) {

		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
			
		if ( memberVO.getId() != null) {
			view.setViewName("team/writeTeamBBS");
		} else {
			try {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "권한없는 접속.");
			} catch (IOException io) {
				throw new RuntimeException(io.getMessage());
			}
		}
		return view;
	}
	
	@Override
	public ModelAndView viewWriteMinutesPage(String teamId) {
		ModelAndView view = new ModelAndView();
		view.setViewName("team/writeMinutes");
		return view;
	}

	

}
