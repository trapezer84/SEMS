package com.ktds.sems.team.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

public class TeamDAOImpl  extends SqlSessionDaoSupport implements TeamDAO{

	@Override
    public List<TeamVO> getAllMyTeamList(TeamSearchVO searchVO, String memberId) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("searchVO", searchVO);
		return getSqlSession().selectList("teamDAO.getAllMyTeamList", paramMap);
	}

	@Override
	public int getMyTotalTeamCount() {
	return getSqlSession().selectOne("teamDAO.getMyTotalTeamCount");
	}	

	@Override
	public TeamVO getOneMyTeamDetail(String teamId) {
	return getSqlSession().selectOne("teamDAO.getOneMyTeamDetail", teamId);
	}

	@Override
	public int getTotalteamCount() {
		return getSqlSession().selectOne("teamDAO.getTotalteamCount");
	}

	@Override
	public int addNewTeamBBSArticle(TeamBBSVO TeamBBSVO) {
		return getSqlSession().insert("teamDAO.addNewTeamBBS", TeamBBSVO);
	}

	@Override
	public int getNextTeamBBSSeq() {
		return getSqlSession().selectOne("teamDAO.getNextTeamBBSSeq");
	}

	@Override
	public String getSysDate() {
		return getSqlSession().selectOne("teamDAO.getSysDate");
	}

	@Override
	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO) {
		return getSqlSession().selectList("teamDAO.getTeamBBSList", searchVO);
	}

	@Override
	public int getSearchedBBSCount() {
		return getSqlSession().selectOne("teamDAO.getSearchedBBSCount");
	}

	@Override
	public List<TeamBBSVO> doSearchList(TeamBBSVO teamBBSVO, TeamSearchVO searchVO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teamBBSVO", teamBBSVO);
		paramMap.put("searchVO", searchVO );
		return getSqlSession().selectList("teamDAO.doSearchList", paramMap);
	}

	@Override
	public String getStartYear() {
		return getSqlSession().selectOne("teamDAO.getStartYear");
	}

	@Override
	public String getEndYear() {
		return getSqlSession().selectOne("teamDAO.getEndYear");
	}

	@Override
	public List<TeamsListVO> getOneTeamDetail(String teamId) {
		return getSqlSession().selectList("teamDAO.getOneTeamDetail", teamId);
	}
	
	@Override
	public TeamBBSVO getTeamBBS(String teamBBSId) {
		return getSqlSession().selectOne("teamDAO.getTeamBBS", teamBBSId);
	}

	@Override
	public int addHitsRecord(TeamBBSVO TeamBBSVO) {
		return getSqlSession().insert("teamDAO.addHitsRecord", TeamBBSVO);
	}

	@Override
	public int isAlreadyCheckBBS(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.isAlreadyCheckBBS",TeamBBSVO);
	}

	@Override
	public int getNextBBSHistorySeq() {
		return getSqlSession().selectOne("teamDAO.getNextBBSHistorySeq");
	}

	@Override
	public int checkDislikeByTeamBBSVO(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.checkDislikeByTeamBBSVO", TeamBBSVO);
	}

	@Override
	public int addLikeRecord(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.addLikeRecord", TeamBBSVO);
	}

	@Override
	public int checkLikeByTeamBBSVO(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.checkLikeByTeamBBSVO", TeamBBSVO);
	}

	@Override
	public int addDislikeRecord(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.addDislikeRecord", TeamBBSVO);
	}

	@Override
	public String getFileInfo(String teamBBSId) {
		return getSqlSession().selectOne("teamDAO.getFileInfo", teamBBSId);
	}
	
	@Override
	public void doModifyAction(TeamBBSVO teamBBS) {
		getSqlSession().update("teamDAO.doModifyAction", teamBBS);
	}

	@Override
	public String getSaltById(String sessionId) {
		return getSqlSession().selectOne("teamDAO.getSaltById", sessionId);
	}

	@Override
	public String getPasswordById(String sessionId) {
		return getSqlSession().selectOne("teamDAO.getPasswordById", sessionId);
	}

	@Override
	public void doDeleteBBS(String teamBBSId) {
		getSqlSession().delete("teamDAO.doDeleteBBS", teamBBSId);
	}

	@Override
	public List<String> isReplyByTeamBBSId(String teamBBSId) {
		return getSqlSession().selectList("teamDAO.isReplyByTeamBBSId", teamBBSId);
	}

	@Override
	public String getLikeState(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.getLikeState", TeamBBSVO);
	}

	@Override
	public String getDislikeState(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.getDislikeState", TeamBBSVO);
	}

	@Override
	public int addLikeCount(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.addLikeCount", TeamBBSVO);
	}

	@Override
	public int addDislikeCount(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.addDislikeCount", TeamBBSVO);
	}

	@Override
	public int writeBBSReply(TeamBBSReplyVO TeamBBSReplyVO) {
		return getSqlSession().insert("teamDAO.writeBBSReply", TeamBBSReplyVO);
	}

	@Override
	public List<TeamBBSReplyVO> getTeamBBSReplies(TeamSearchVO searchVO) {
		return getSqlSession().selectList("teamDAO.getTeamBBSReplies", searchVO);
	}

	@Override
	public int getNextTeamBBSReplySeq() {
		return getSqlSession().selectOne("teamDAO.getNextTeamBBSReplySeq");
	}

	@Override
	public int writeBBSReReply(TeamBBSReplyVO TeamBBSReplyVO) {
		return getSqlSession().insert("teamDAO.writeBBSReReply", TeamBBSReplyVO);
	}

	@Override
	public int getNextOrderNoByParentId(String parentReplyId) {
		return getSqlSession().selectOne("teamDAO.getNextOrderNoByParentId", parentReplyId);
	}

	@Override
	public int insertNewMinutes(MinutesVO minutesVO) {
		return getSqlSession().insert("teamDAO.insertNewMinutes", minutesVO);
	}

	@Override
	public int nextMinutesSeq() {
		return getSqlSession().selectOne("teamDAO.nextMinutesSeq");
	}

	@Override
	public boolean bulidTeam(String educationId, String teamName) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("teamName", teamName);
		return getSqlSession().insert("teamDAO.bulidTeam", paramMap) > 0;
	}

	@Override
	public int getTotalMinutesCount(MinutesSearchVO minutesSearchVO) {
		return getSqlSession().selectOne("teamDAO.getTotalMinutesCount", minutesSearchVO);
	}
	
	@Override
	public List<MemberVO> getAllEduMember(String educationId) {
		return getSqlSession().selectList("teamDAO.getAllEduMember", educationId);
	}
	
	@Override
	public List<MinutesVO> getAllMinutesList(MinutesSearchVO minutesSearchVO) {
		return getSqlSession().selectList("teamDAO.getAllMinutesList", minutesSearchVO);
	}
	
	@Override
	public List<MinutesVO> getAllMinutes(MinutesSearchVO minutesSearchVO) {
		return getSqlSession().selectList("teamDAO.getAllMinutes", minutesSearchVO);
	}
	
	@Override
	public boolean insertMember(TeamsListVO teamsListVO) {
		return getSqlSession().insert("teamDAO.insertMember", teamsListVO) > 0;
	}

	@Override
	public int getTotalMinutesCountForAdmin(MinutesSearchVO minutesSearchVO) {
		return getSqlSession().selectOne("teamDAO.getTotalMinutesCountForAdmin", minutesSearchVO);
	}

	@Override
	public void doDeleteTeamListByMemberId(String memberId) {
		getSqlSession().delete("teamDAO.doDeleteTeamListByMemberId", memberId);
	}
	
	@Override
	public MinutesVO getOneDetailMinutes(String minutesId) {
		return getSqlSession().selectOne("teamDAO.getOneDetailMinutes", minutesId);
	}

	@Override
	public int isExistedOrderNoByParentId(String parentReplyId) {
		return getSqlSession().selectOne("teamDAO.isExistedOrderNoByParentId", parentReplyId);
	}

	@Override
	public List<TeamBBSReplyVO> getTeamBBSReReplies(String parentReplyId) {
		return getSqlSession().selectList("teamDAO.getTeamBBSReReplies", parentReplyId);
	}
	
	@Override
	public String getOneDetailMinutesDate(String minutesId) {
		return getSqlSession().selectOne("teamDAO.getOneDetailMinutesDate", minutesId);
	}

	@Override
	public int getReplyCountByTeamBBSId(String teamBBSId) {
		return getSqlSession().selectOne("teamDAO.getReplyCountByTeamBBSId", teamBBSId);
	}

	@Override
	public int deleteNewReply() {
		return getSqlSession().delete("teamDAO.deleteNewReply");
	}

	@Override
	public int initLikeRecord(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.initLikeRecord", TeamBBSVO);
	}

	@Override
	public int removeHitsRecord(String bbsHistoryId) {
		return getSqlSession().delete("teamDAO.removeHitsRecord",bbsHistoryId);
	}

	@Override
	public void doDeleteTeamByTeamName(String teamName) {
		getSqlSession().delete("teamDAO.doDeleteTeamByTeamName", teamName);
	}

	@Override
	public void insertNewEducation(EducationVO educationVO) {
		getSqlSession().insert("teamDAO.insertNewEducation", educationVO);
	}

	@Override
	public void deleteEducation(String educationId) {
		getSqlSession().delete("teamDAO.deleteEducation", educationId);
	}
	
	@Override
	public int deleteTestMinutes(String minutesId) {
		return getSqlSession().delete("teamDAO.deleteTestMinutes",minutesId);
	}
	
	@Override
	public List<FileVO> getFileListInfo(String teamBBSId) {
		return getSqlSession().selectList("teamDAO.getFileListInfo", teamBBSId);
	}


	@Override
	public int getSearchedBBSCountByTeamId(String teamId) {
		
		return getSqlSession().update("teamDAO.getSearchedBBSCountByTeamId", teamId);
	}

	@Override
	public List<TeamVO> getAllTeamList(TeamSearchVO searchVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalMyTeamCount(HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = member.getId();
		return getSqlSession().selectOne("teamDAO.getTotalMyTeamCount", memberId);
	}

}
