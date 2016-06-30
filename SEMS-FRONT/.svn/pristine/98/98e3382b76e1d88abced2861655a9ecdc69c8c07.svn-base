package com.ktds.sems.team.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ktds.sems.file.dao.FileDAO;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

import kr.co.hucloud.utilities.SHA256Util;

public class TeamBizImpl implements TeamBiz {

	private Logger logger = LoggerFactory.getLogger(TeamBizImpl.class);

	private TeamDAO teamDAO;
	private FileDAO fileDAO;

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}
	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	@Override
	public List<TeamVO> getAllMyTeamList(TeamSearchVO searchVO, String memberId) {
		return teamDAO.getAllMyTeamList(searchVO, memberId);
	}
	
	@Override
	public int getMyTotalTeamCount() {
		return teamDAO.getMyTotalTeamCount();
	}


	@Override
	public TeamVO getOneMyTeamDetail(String teamId) {
		return teamDAO.getOneMyTeamDetail(teamId);
	}
	
	@Override
	public int getTotalTeamCount() {
		return teamDAO.getTotalteamCount();
	}

	@Override
	public List<TeamVO> getAllTeamList(TeamSearchVO searchVO) {
		return teamDAO.getAllTeamList(searchVO);
	}

	@Override
	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS, MultipartHttpServletRequest request) {

		String teamBBSId = String.valueOf(teamDAO.getNextTeamBBSSeq());
		String sysdate = teamDAO.getSysDate();
		
		// 따로 teamId을 받아와야하나 테스트용으로 만듬
		String teamId = "cannon";

		teamBBS.setTeamId(teamId);

		teamBBSId = lpad(teamBBSId, 6, "0");
		teamBBSId = "TBBS" + '-' + sysdate + '-' + teamBBSId;
		teamBBS.setTeamBBSId(teamBBSId);

		
		List<MultipartFile> files = request.getFiles("file");
		
		FileVO fileVO = null;
		
		if (files != null && files.size() > 0 && !files.get(0).getOriginalFilename().equals("")) {
			for (MultipartFile multipartFile : files) {
				
				fileVO = new FileVO();

				String salt = SHA256Util.generateSalt();
				String originalFileName = multipartFile.getOriginalFilename();
				fileVO.setFileName(originalFileName);
				
				String[] fileName = originalFileName.split("\\."); 
				String fileExtension = "." + fileName[fileName.length-1];
				String newFilePath = "D:\\" + SHA256Util.getEncrypt(originalFileName, salt) + fileExtension;
				fileVO.setFileLocation(newFilePath);

				fileVO.setArticleId(teamBBSId);
				logger.info("파일테스트 : " + multipartFile);
				File uploadFile = new File(fileVO.getFileLocation());
				fileDAO.doWriteFile(fileVO);
				try {
					multipartFile.transferTo(uploadFile);
				} catch (IllegalStateException e) {
					throw new RuntimeException(e.getMessage());
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}

		logger.info(teamBBS.getTeamBBSId());
		logger.info(teamBBS.getTeamId());
		logger.info(teamBBS.getTitle());
		logger.info(teamBBS.getDescript());
		logger.info(teamBBS.getMemberId());
		logger.info(teamBBS.getCreatedDate());
		logger.info(teamBBS.getIsNotice());
		
		return teamDAO.addNewTeamBBSArticle(teamBBS) > 0;
	}

	@Override
	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO) {
		return teamDAO.getTeamBBSList(searchVO);
	}

	@Override
	public List<TeamBBSVO> doSearchList(TeamBBSVO teamBBSVO, TeamSearchVO searchVO) {
		return teamDAO.doSearchList(teamBBSVO,searchVO);
	}

	@Override
	public String getStartYear() {
		return teamDAO.getStartYear();
	}

	@Override
	public String getEndYear() {
		return teamDAO.getEndYear();
	}

	@Override
	public int getSearchedBBSCount() {
		return teamDAO.getSearchedBBSCount();
	}

	@Override
	public List<TeamsListVO> getOneTeamDetail(String teamId) {
		return teamDAO.getOneTeamDetail(teamId);
	}

	@Override
	public TeamBBSVO getTeamBBS(String teamBBSId) {
		return teamDAO.getTeamBBS(teamBBSId);
	}

	@Override
	public boolean addHitsRecord(TeamBBSVO bbs) {

		boolean success = false;

		String bbbsHistoryId = String.valueOf(teamDAO.getNextBBSHistorySeq());
		String sysdate = teamDAO.getSysDate();
		
		bbbsHistoryId = lpad(bbbsHistoryId, 6, "0");
		bbbsHistoryId = "BHTR" + '-' + sysdate + '-' + bbbsHistoryId;
		bbs.setBbsHistoryId(bbbsHistoryId);
		
		int bbsCount =  teamDAO.isAlreadyCheckBBS(bbs);
		// 처음으로 들렸던 기록이라면 기록한다.
		if( bbsCount == 0 ) {
			success =  (teamDAO.addHitsRecord(bbs) > 0);
		} 
		return success;
	}

	@Override
	public boolean checkDislikeByTeamBBSVO(TeamBBSVO bbs) {
		return teamDAO.checkDislikeByTeamBBSVO(bbs) > 0;
	}

	@Override
	public boolean addLikeRecord(TeamBBSVO bbs) {
		return teamDAO.addLikeRecord(bbs) > 0;
	}

	@Override
	public boolean checkLikeByTeamBBSVO(TeamBBSVO bbs) {
		return teamDAO.checkLikeByTeamBBSVO(bbs) > 0;
	}

	@Override
	public boolean addDislikeRecord(TeamBBSVO bbs) {
		return teamDAO.addDislikeRecord(bbs) > 0;
	}

	public String getFileInfo(String teamBBSId) {
		return teamDAO.getFileInfo(teamBBSId);
	}
	@Override
	public void doModifyAction(TeamBBSVO teamBBS) {
		teamDAO.doModifyAction(teamBBS);
	}

	@Override
	public String getSaltById(String sessionId) {
		return teamDAO.getSaltById(sessionId);
	}

	@Override
	public String getPasswordById(String sessionId) {
		return teamDAO.getPasswordById(sessionId);
	}

	@Override
	public void doDeleteBBS(String teamBBSId) {
		teamDAO.doDeleteBBS(teamBBSId);
	}

	@Override
	public boolean isReplyByTeamBBSId(String teamBBSId) {
		return teamDAO.isReplyByTeamBBSId(teamBBSId).size() > 0;
	}

	
	@Override
	public String getLikeState(TeamBBSVO bbs) {
		return teamDAO.getLikeState(bbs);
	}
	@Override
	public String getDislikeState(TeamBBSVO bbs) {
		return teamDAO.getDislikeState(bbs);
	}
	@Override
	public boolean addLikeCount(TeamBBSVO bbs) {
		return teamDAO.addLikeCount(bbs) > 0;
	}
	@Override
	public boolean addDislikeCount(TeamBBSVO bbs) {
		return teamDAO.addDislikeCount(bbs) > 0;
	}
	@Override
	public boolean writeBBSReply(TeamBBSReplyVO replyVO) {
		String teamBBSReplyId = String.valueOf(teamDAO.getNextTeamBBSReplySeq());
		String sysdate = teamDAO.getSysDate();
		teamBBSReplyId = lpad(teamBBSReplyId, 6, "0");
		teamBBSReplyId = "TBRP" + '-' + sysdate + '-' + teamBBSReplyId;
		replyVO.setReplyId(teamBBSReplyId);
		return teamDAO.writeBBSReply(replyVO) > 0;
	}
	@Override
	public List<TeamBBSReplyVO> getTeamBBSReplies(TeamSearchVO searchVO) {
		return teamDAO.getTeamBBSReplies(searchVO);
	}
	@Override
	public boolean writeBBSReReply(TeamBBSReplyVO replyVO) {
		String teamBBSReplyId = String.valueOf(teamDAO.getNextTeamBBSReplySeq());
		String sysdate = teamDAO.getSysDate();
		teamBBSReplyId = lpad(teamBBSReplyId, 6 ,"0");
		teamBBSReplyId = "TBRP" + '-' + sysdate + '-' + teamBBSReplyId;
		replyVO.setReplyId(teamBBSReplyId);
		
		logger.info("orderNobefore"+replyVO.getParentReplyId());
		int orderNo  = 0;
		
		if( teamDAO.isExistedOrderNoByParentId(replyVO.getParentReplyId()) == 0){
			orderNo = 0;
		}else{
			orderNo = teamDAO.getNextOrderNoByParentId(replyVO.getParentReplyId());
		}
		logger.info("orderNo"+orderNo);
		replyVO.setOrderNo(orderNo);
		
		return teamDAO.writeBBSReReply(replyVO) > 0;
	}
	
	@Override
	public boolean writeNewMinutes(MinutesVO minutesVO) {
		
		int  nextMinutesId = teamDAO.nextMinutesSeq();
		
		String MinutesId = "MINU-" + lpad(nextMinutesId + "", 3, "0"); 
		minutesVO.setMinutesId(MinutesId);
		System.out.println("Biz MinuteId"+MinutesId);
		
		return teamDAO.insertNewMinutes(minutesVO) > 0;
	}

	@Override
	public boolean bulidTeam(String educationId, String teamName) {
		return teamDAO.bulidTeam(educationId, teamName);
	}

	@Override
	public List<MinutesVO> getAllMinutes(MinutesSearchVO minutesSearchVO) {
		return teamDAO.getAllMinutes(minutesSearchVO);
	}

	@Override
	public int getTotalMinutesCount(MinutesSearchVO minutesSearchVO) {
		return teamDAO.getTotalMinutesCount(minutesSearchVO);
	}
	
	@Override
	public boolean insertMember(TeamsListVO teamsListVO) {
		return teamDAO.insertMember(teamsListVO);
	}

	@Override
	public List<MinutesVO> getAllMinutesList(MinutesSearchVO minutesSearchVO) {
		return teamDAO.getAllMinutesList(minutesSearchVO);
	}
	
	@Override
	public int getTotalMinutesCountForAdmin(MinutesSearchVO minutesSearchVO) {
		return teamDAO.getTotalMinutesCountForAdmin(minutesSearchVO);
	}
	
	@Override
	public List<MemberVO> getAllEduMember(String educationId) {
		return teamDAO.getAllEduMember(educationId);
	}
	@Override
	public void doDeleteTeamListByMemberId(String memberId) {
		teamDAO.doDeleteTeamListByMemberId(memberId);
	}
	private String lpad(String source, int length, String defValue) {
		
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for ( int i = 0 ; i < needLength ; i++ ) {
			source = defValue + source;
		}
		return source;
	}

	@Override
	public List<TeamBBSReplyVO> getTeamBBSReReplies(String parentReplyId) {
		return teamDAO.getTeamBBSReReplies(parentReplyId);
	}
	@Override
	public int getReplyCountByTeamBBSId(String teamBBSId) {
		return teamDAO.getReplyCountByTeamBBSId(teamBBSId);
	}
	
	@Override
	public MinutesVO getOneDetailMinutes(String minutesId) {
		return teamDAO.getOneDetailMinutes(minutesId);
	}
	@Override
	public String getOneDetailMinutesDate(String minutesId) {
		return teamDAO.getOneDetailMinutesDate(minutesId);
	}
	@Override
	public void doDeleteTeamByTeamName(String teamName) {
		teamDAO.doDeleteTeamByTeamName(teamName);
	}
	@Override
	public int getTotalMyTeamCount(HttpSession session) {
		return teamDAO.getTotalMyTeamCount(session);
	}
	@Override
	public List<FileVO> getFileListInfo(String teamBBSId) {
		return teamDAO.getFileListInfo(teamBBSId);
	}
	@Override
	public int getSearchedBBSCountByTeamId(String teamId) {
		return teamDAO.getSearchedBBSCountByTeamId(teamId);
	}

}
