package com.ktds.sems.education.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduFileVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationQNAReplySearchVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationTypeVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO {

	@Override
	public int nextEduSeq() {
		return getSqlSession().selectOne("EducationDAO.nextEduSeq");
	}
	
	@Override
	public String nowDate() {
		return getSqlSession().selectOne("EducationDAO.nowDate");
	}

	@Override
	public int insertNewEducation(EducationVO educationVO) {
		return getSqlSession().insert("EducationDAO.insertNewEducation", educationVO);
	}
	
	@Override
	public EducationVO getOneEducation(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getOneEducation", educationId);
	}
	
	@Override
	public EducationVO getOneEducationByAllCondition(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getOneEducationByAllCondition", educationId);
	}

	@Override
	public int modifyNewEducation(EducationVO changedEducationVO) {
		return getSqlSession().update("EducationDAO.modifyNewEducation", changedEducationVO);
	}

	@Override
	public List<CostVO> costCodeList() {
		return getSqlSession().selectList("EducationDAO.costCodeList");
	}

	@Override
	public List<EducationTypeVO> typeCodeList() {
		return getSqlSession().selectList("EducationDAO.typeCodeList");
	}

	@Override
	public List<CategoryVO> categoryCodeList() {
		return getSqlSession().selectList("EducationDAO.categoryCodeList");
	}

	@Override
	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEducationHistory",eduHistorySearchVO);
	}

	@Override
	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectOne("EducationDAO.getAllEduHistoryCount",eduHistorySearchVO);
	}

	@Override
	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		logger.info(eduHistorySearchVO.getSearchKeyword());
		return getSqlSession().selectOne("EducationDAO.getJCEduHistoryCount", eduHistorySearchVO);
	}

	@Override
	public List<EducationHistoryVO> getJCEduHistoryHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectList("EducationDAO.getJCEduHistoryHistory", eduHistorySearchVO);
	}

	@Override
	public int applyJoinEducationByMemberId(String educationHistoryId, String changeState) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationHistoryId", educationHistoryId);
		paramMap.put("changeState", changeState);
		return getSqlSession().update("EducationDAO.applyJoinEdcationByMemberId", paramMap);
	}

	@Override
	public int cancelJoinEducationByMemberId(String educationHistoryId, String changeState) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationHistoryId", educationHistoryId);
		paramMap.put("changeState", changeState);
		return getSqlSession().update("EducationDAO.cancelJoinEducationByMemberId", paramMap);
	}

	@Override
	public String getStateByEducationHistroyId(String educationHistoryId) {
		return getSqlSession().selectOne("EducationDAO.getStateByEducationHistroyId", educationHistoryId);
	}

	@Override
	public int changeEducationApplyState(String educationHistoryId) {
		return getSqlSession().update("EducationDAO.changeEducationApplyState", educationHistoryId);
	}

	@Override
	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduReportCount", eduReportSearchVO);
	}
	
	@Override
	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduReport", eduReportSearchVO);
	}

	@Override
	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduQnaCount", eduQnaSearchVO);
	}

	@Override
	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduQna", eduQnaSearchVO);
	}

	@Override
	public int getTotalEduFileCount(EduFileSearchVO eduFileSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduFileCount", eduFileSearchVO);
	}

	@Override
	public List<EduFileVO> getAllEduFile(EduFileSearchVO eduFileSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduFile", eduFileSearchVO);
	}

	@Override
	public String doActionDeleteBeforeCheck(MemberVO memberVO) {
		return getSqlSession().selectOne("EducationDAO.doActionDeleteBeforeCheck", memberVO);
	}

	@Override
	public void doActionDelete(String educationId) {
		getSqlSession().delete("EducationDAO.doActionDelete", educationId);
	}

	@Override
	public List<EducationVO> attendedLectureUserList(String educationId) {
		return getSqlSession().selectList("EducationDAO.attendedLectureUserList", educationId);
	}

	@Override
	public MemberVO emailNoticeForUser(String memberId) {
		return getSqlSession().selectOne("EducationDAO.emailNoticeForUser", memberId);
	}

	@Override
	public int nextEduNoticeSeq() {
		return getSqlSession().selectOne("EducationDAO.nextEduNoticeSeq");
	}

	@Override
	public int insertNewEduFileNotice(EduNoticeVO eduNoticeVO) {
		return getSqlSession().insert("EducationDAO.insertNewEduFileNotice", eduNoticeVO);
	}


	@Override
	public List<EduNoticeVO> getAllEduFileNotice(EduNoticeSearchVO eduNoticeSearchVO) {
		List<EduNoticeVO> eduNoticeVOs = getSqlSession().selectList("EducationDAO.getAllEduFileNotice", eduNoticeSearchVO);
		return eduNoticeVOs;
	}

	@Override
	public EduNoticeVO getOneNotice(String eduNoticeId) {
		return getSqlSession().selectOne("EducationDAO.getOneNotice", eduNoticeId);
	}
	@Override
	public void addhits(String eduNoticeId) {
		getSqlSession().update("EducationDAO.addhits", eduNoticeId);
	}

	@Override
	public int doDeleteEduNotice(String eduNoticeId) {
		return getSqlSession().delete("EducationDAO.doDeleteEduNotice", eduNoticeId);
	}

	@Override
	public int doEduFileNoticeModify(EduNoticeVO changeEduNoticeVO) {
		return getSqlSession().update("EducationDAO.doEduFileNoticeModify", changeEduNoticeVO);
	}

	@Override
	public List<TeacherVO> teacherVOList() {
		return getSqlSession().selectList("EducationDAO.teacherVOList");
	}

	@Override
	public int getTotalEduReportHisotryCount(EduReportSearchVO reportSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduReportHisotryCount", reportSearchVO);
	}

	@Override
	public List<EduReportVO> getAllEduReportHistory(EduReportSearchVO reportSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduReportHistory", reportSearchVO);
	}

	@Override
	public String getOneEduNoticeId() {
		return getSqlSession().selectOne("EducationDAO.getOneEduNoticeId");
	}

	@Override
	public String getOneEducationId() {
		return getSqlSession().selectOne("EducationDAO.getOneEducationId");
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return getSqlSession().selectList("EducationDAO.getAllMemberList");
	}

	@Override
	public List<EducationVO> getJoinEducation(String memberId) {
		return getSqlSession().selectList("EducationDAO.getJoinEducation", memberId);
	}

	@Override
	public List<AttendVO> getOneMemberAttendance(String memberId) {
		return getSqlSession().selectList("EducationDAO.getOneMemberAttendance", memberId);
	}

	@Override
	public List<EducationVO> getAllStartedEducationList() {
		return getSqlSession().selectList("EducationDAO.getAllStartedEducationList");
	}

	@Override
	public List<MemberVO> getAllMemberListByEduId(String educationId) {
		return getSqlSession().selectList("EducationDAO.getAllMemberListByEduId", educationId);
	}

	@Override
	public List<TeamVO> getAllTeamList() {
		return getSqlSession().selectList("EducationDAO.getAllTeamList");
	}

	@Override
	public List<MemberVO> getAllMemberListByTeamId(String educationId, String teamId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("teamId", teamId);
		return getSqlSession().selectList("EducationDAO.getAllMemberListByTeamId", paramMap);
	}

	@Override
	public int confirmMemberOfEdu(String educationId, String memberId) {
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("educationId", educationId);
		hashMap.put("memberId", memberId);
		return getSqlSession().selectOne("EducationDAO.confirmMemberOfEdu",hashMap);
	}

	@Override
	public int insertEduQna(EduQnaVO eduQnaVO) {
		return getSqlSession().insert("EducationDAO.insertEduQna", eduQnaVO);
	}

	@Override
	public int getNextEqbSeq() {
		return getSqlSession().selectOne("EducationDAO.nextEqbSeq");
	}

	@Override
	public EduQnaVO detailOfEduQna(String eduQnaId) {
		return getSqlSession().selectOne("EducationDAO.detailOfEduQna", eduQnaId);
	}

	@Override
	public int addHitsToEduQna(String eduQnaId) {
		return getSqlSession().update("EducationDAO.addHitsToEduQna", eduQnaId);
	}

	@Override
	public int addQnaEduReplyLike(String replyId) {
		return getSqlSession().update("EducationDAO.addQnaEduReplyLike", replyId);
	}

	@Override
	public int addQnaEduReplyDisLike(String replyId) {
		return getSqlSession().update("EducationDAO.addQnaEduReplyDisLike", replyId);
	}

	@Override
	public int getTotalQnaEduReplyCount(String eduQnaId) {
		return getSqlSession().selectOne("EducationDAO.getTotalQnaEduReplyCount", eduQnaId);
	}
	
	@Override
	public String getNowDate() {
		return getSqlSession().selectOne("EducationDAO.getNowDate");
	}

	@Override
	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(EducationQNAReplySearchVO searchVO) {
		return getSqlSession().selectList("EducationDAO.getAllQNAReplyListByAtcId", searchVO);
	}

	@Override
	public int getNextReplySeq() {
		return getSqlSession().selectOne("EducationDAO.getNextReplySeq");
	}

	@Override
	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO) {
		getSqlSession().insert("EducationDAO.addQNAReply", eduBBSReplyVO);
		
	}

	@Override
	public String getEmail(String memberId) {
		return getSqlSession().selectOne("EducationDAO.getEmail", memberId);
	}

	@Override
	public int getNextReReplyEval() {
		return getSqlSession().selectOne("EducationDAO.getNextReReplyEval");
	}

	@Override
	public int checkReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().selectOne("EducationDAO.checkReReplyEval", reRplyEvalVO);
	}

	@Override
	public int insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().insert("EducationDAO.insertReReplyEvalByDislike",reRplyEvalVO);
	}
	
	@Override
	public List<EducationVO> getAllEucationList(EducationSearchVO searchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEucationList", searchVO);
	}

	@Override
	public int getTotalEducationCount(EducationSearchVO searchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEducationCount", searchVO);
	}

	@Override
	public int doModifyStateComment(AttendVO attendVO) {
		return getSqlSession().insert("EducationDAO.doModifyStateComment", attendVO);
	}

	@Override
	public int nextAttendSeq() {
		return getSqlSession().selectOne("EducationDAO.nextAttendSeq");
	}
	
}