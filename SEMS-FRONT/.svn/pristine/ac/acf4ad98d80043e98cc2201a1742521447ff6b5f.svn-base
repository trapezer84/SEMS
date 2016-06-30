package com.ktds.sems.education.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EducationBoardHistoryVO;
import com.ktds.sems.education.vo.BBSHistoryVO;
import com.ktds.sems.education.vo.BBSReplySearchVO;
import com.ktds.sems.education.vo.BBSReplyVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EducationFileBBSVO;
import com.ktds.sems.education.vo.EducationQNABBSSearchVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplySearchVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.FileBBSSearchVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.ReportReplySearchVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.vo.MemberVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO {
	
	private Logger logger = LoggerFactory.getLogger(EducationDAOImpl.class);	
	
	@Override
	public int getTotalEducationCount() {
		return getSqlSession().selectOne("EducationDAO.getTotalEducationCount");
	}

	@Override
	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEducationList", searchVO);
	}

	@Override
	public EducationVO getOneEducationDetail(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getOneEducationDetail", educationId);
	}

	@Override
	public int getSearchedEducationCount(EducationVO educationVO) {
		logger.info(educationVO.getStartDate());
		logger.info(educationVO.getEndDate());
		logger.info("타이틀"+educationVO.getEducationTitle());
		logger.info("COST"+educationVO.getCost());
		logger.info("교육타입"+educationVO.getEducationType());
		
		return getSqlSession().selectOne("EducationDAO.getSearchedEducationCount", educationVO);
		
	}

	@Override
	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("educationVO", educationVO);
		paramMap.put("searchVO", searchVO );

/*		paramMap.put("startIndex", String.valueOf(searchVO.getStartIndex()));
		paramMap.put("endIndex", String.valueOf(searchVO.getEndIndex()));*/
		
		return getSqlSession().selectList("EducationDAO.doSearchList", paramMap);
	}
	
	@Override
	public int insertNewComment(QNAVO qnaVO) {
		// <!-- Not Null 상태의 Columns 들의 데이터 전달 필요 ( 현재 고정 입력 값 지정 상태 )-->
		return getSqlSession().insert("EducationDAO.insertNewComment", qnaVO);
	}
	
	@Override
	public int doCancelEducation(String educationId, String id) {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().delete("EducationDAO.doCancelEducation", paramMap);
	}

	@Override
	public List<EducationVO> getMemberRegInfo(String id) {
		return getSqlSession().selectList("EducationDAO.getMemberRegInfo", id);
	}

	@Override
	public int doApplyEducation(String educationId, String id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().insert("EducationDAO.doApplyEducation", paramMap);
	}
	@Override
	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("startIndex", searchVO.getStartIndex()+"");
		paramMap.put("endIndex", searchVO.getEndIndex()+"");
		
		return getSqlSession().selectList("EducationDAO.getAllCommentByEducationId", paramMap);
	}
	@Override
	public String getNowDate() {
		return getSqlSession().selectOne("EducationDAO.getNowDate");
	}

	@Override
	public int getNextReplySeq() {
		return getSqlSession().selectOne("EducationDAO.getNextReplySeq");
	}

	@Override
	public String isApplyMemberByEducationId(String educationId, String id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().selectOne("EducationDAO.isApplyMemberByEducationId", paramMap);
	}

	@Override
	public int getEduReplyCount(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getEduReplyCount", educationId);
	}

	@Override
	public List<String> getCostName() {
		return getSqlSession().selectList("EducationDAO.getCostName");
	}

	@Override
	public List<String> getTypeName() {
		return getSqlSession().selectList("EducationDAO.getTypeName");
	}

	@Override
	public String doTransCostId(String cost) {
		return getSqlSession().selectOne("EducationDAO.doTransCostId", cost);
	}

	@Override
	public String doTransTypeId(String educationType) {
		return getSqlSession().selectOne("EducationDAO.doTransTypeId", educationType);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public int getTotalQNACount(QNASearchVO qnaSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalQNACount", qnaSearchVO);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllQNAList", qnaSearchVO);
	}

	@Override
	public int doReReplyInsert(QNAVO qnaVO) {
		return getSqlSession().insert("EducationDAO.doReReplyInsert", qnaVO);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public QNAVO getSelectedQNA(String replyId) {
		return getSqlSession().selectOne("EducationDAO.getSelectedQNA" ,replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getSelectedQNAAnswer(String replyId) {
		return getSqlSession().selectList("EducationDAO.getSelectedQNAAnswer" ,replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> exportQNAListAsExcel(String memberId) {
		return getSqlSession().selectList("EducationDAO.exportQNAListAsExcel", memberId);
	}
	
	@Override
	public List<EducationVO> getApplyHistory(String memberId, String educationId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("educationId", educationId);
		return getSqlSession().selectList("EducationDAO.getApplyHistory", map);
	}

	@Override
	public String getEmail(String id) {
		return getSqlSession().selectOne("EducationDAO.getEmail", id);
	}

	@Override
	public int getNextReReplyEval() {
		return getSqlSession().selectOne("EducationDAO.getNextReReplyEval");
	}

	@Override
	public int plusReReplyLike(String replyId) {
		return getSqlSession().update("EducationDAO.plusReReplyLike", replyId);
	}

	@Override
	public int insertReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().insert("EducationDAO.insertReReplyEval", reRplyEvalVO);
	}

	@Override
	public int checkReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().selectOne("EducationDAO.checkReReplyEval", reRplyEvalVO);
	}

	@Override
	public String getStartYear() {
		return getSqlSession().selectOne("EducationDAO.getStartYear");
	}

	@Override
	public String getEndYear() {
		return getSqlSession().selectOne("EducationDAO.getEndYear");
	}

	@Override
	public int insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().insert("EducationDAO.insertReReplyEvalByDislike",reRplyEvalVO);
	}

	@Override
	public int plusReReplyDislike(String replyId) {
		return getSqlSession().update("EducationDAO.plusReReplyDislike", replyId);
	}

	@Override
	public int doRequestRetraction(String educationId, String retractionMsg, String memberId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("educationId", educationId);
		map.put("retractionMsg", retractionMsg);
		map.put("memberId", memberId);
		return getSqlSession().update("EducationDAO.doRequestRetraction", map);
	}
	
	/**
	 * @author 206-002 공정민
	 */
	@Override
	public int getTotalMemberNumber(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getTotalMemberNumber", educationId);
	}

	@Override
	public int doReserveEducation(String educationId, String id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().insert("EducationDAO.doReserveEducation", paramMap);
	}

	@Override
	public int doReReplyDelete(QNAVO qnaVO) {
		
		return getSqlSession().delete("EducationDAO.doReReplyDelete",qnaVO);
	}

	@Override
	public int deleteReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().delete("EducationDAO.deleteReReplyEval", reRplyEvalVO);
	}

	@Override
	public int updateStateToApply(String educationId) {		
		return getSqlSession().update("EducationDAO.updateStateToApply", educationId);
	}
	
	@Override
	public List<EducationFileBBSVO> getEducationFileBBSList(FileBBSSearchVO searchVO) {
		return getSqlSession().selectList("EducationDAO.getEducationFileBBSList", searchVO);
	}

	@Override
	public String getArticleSEQ() {
		return getSqlSession().selectOne("EducationDAO.getArticleSEQ");
	}

	@Override
	public String getMemberIdByEducationId(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getMemberIdByEducationId", educationId);
	}

	@Override
	public int writeNewFileBBS(EducationFileBBSVO educationFileBBSVO) {
		return getSqlSession().insert("EducationDAO.writeNewFileBBS", educationFileBBSVO);
	}

	@Override
	public List<EducationVO> getMyEducationList(String id) {
		return getSqlSession().selectList("EducationDAO.getMyEducationList", id);
	}


	@Override
	public List<EducationQNABBSVO> getAllEducationQNAList(EducationQNABBSSearchVO searchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEducationQNAList", searchVO);
	}

	@Override
	public void addQNABBS(EducationQNABBSVO eduBBS) {
		getSqlSession().insert("EducationDAO.addQNABBS", eduBBS);
	}

	@Override
	public List<EducationReportVO> getAllEducationReportList(EducationReportSearchVO educationReportSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEducationReportList", educationReportSearchVO);
	}

	@Override
	public int getTotalEducationReportCount(EducationReportSearchVO educationReportSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEducationReportCount", educationReportSearchVO);
	}

	@Override
	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId) {
		return getSqlSession().selectOne("EducationDAO.getOneQNABBSByAtcId", atcId);
	}

	@Override
	public int getNextReportSeq() {
		return getSqlSession().selectOne("EducationDAO.getNextReportSeq");
	}

	@Override
	public void doReportWriteAction(EducationReportVO educationReportVO) {
		getSqlSession().insert("EducationDAO.doReportWriteAction", educationReportVO);
	}

	@Override
	public void addHitsByAtcId(String atcId) {
		getSqlSession().update("EducationDAO.addHitsByAtcId", atcId);
	}

	@Override
	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO) {
		getSqlSession().insert("EducationDAO.addQNAReply", eduBBSReplyVO);
	}
	
	@Override
	public List<MemberVO> getAllMemberOfEducation(String educationId) {
		return getSqlSession().selectList("EducationDAO.getAllMemberOfEducation", educationId);
	}

	@Override
	public int addRequestRetractionHistory(String educationId, String retractionMsg, String memberId, String ip) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("educationId", educationId);
		map.put("retractionMsg", retractionMsg);
		map.put("memberId", memberId);
		map.put("ip", ip);
		return getSqlSession().insert("EducationDAO.addRequestRetractionHistory", map);
	}

	@Override
	public int checkEndDate(String educationId, String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("educationId", educationId);
		map.put("memberId", id);
		return getSqlSession().selectOne("EducationDAO.checkEndDate", map);
	}

	@Override
	public int getTotalReportReplyCount(ReportReplySearchVO reportReplySearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalReportReplyCount", reportReplySearchVO);
	}

	@Override
	public List<ReportReplyVO> getAllReportReply(ReportReplySearchVO reportReplySearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllReportReply",reportReplySearchVO);
	}
	
	@Override
	public int getTotalReportReplyCountOfTeacher(ReportReplySearchVO reportReplySearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalReportReplyCountOfTeacher", reportReplySearchVO);
	}
	
	@Override
	public List<ReportReplyVO> getAllReportReplyOfTeacher(ReportReplySearchVO reportReplySearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllReportReplyOfTeacher",reportReplySearchVO);
	}

	@Override
	public EducationReportVO getOneEducationReport(EducationReportVO educationReportVO) {
		return getSqlSession().selectOne("EducationDAO.getOneEducationReport", educationReportVO);
	}

	@Override
	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(EducationQNAReplySearchVO searchVO) {
		return getSqlSession().selectList("EducationDAO.getAllQNAReplyListByAtcId", searchVO);
	}

	@Override
	public int getNextReportReplySeq() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("EducationDAO.getNextReportReplySeq");
	}

	@Override
	public void doReportSubmit(ReportReplyVO reportReplyVO) {
		getSqlSession().insert("EducationDAO.doReportSubmit", reportReplyVO);
	}

	@Override
	public int getReportReplyCount(String articleId) {
		return getSqlSession().selectOne("EducationDAO.getReportReplyCount", articleId);
	}

	@Override
	public List<ReportReplyVO> getAllReportByArticleId(String articleId, ReportReplySearchVO searchVO) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("articleId", articleId);
		paramMap.put("startIndex", searchVO.getStartIndex()+"");
		paramMap.put("endIndex", searchVO.getEndIndex()+"");
		return getSqlSession().selectList("EducationDAO.getAllReportByArticleId", paramMap);
	}

	@Override
	public String getNowDateTime() {
		return getSqlSession().selectOne("EducationDAO.getNowDateTime");
	}
	
	@Override
	public int getEducationFileBBSCount(FileBBSSearchVO searchVO) {
		return getSqlSession().selectOne("EducationDAO.getEducationFileBBSCount", searchVO);
	}

	@Override
	public EducationFileBBSVO getOneEducationFileBBS(String articleId) {
		return getSqlSession().selectOne("EducationDAO.getOneEducationFileBBS", articleId);
	}

	@Override
	public boolean isExistedHitMemberId(BBSHistoryVO bbsHistoryVO) {
		return getSqlSession().selectOne("EducationDAO.isExistedHitMemberIdByArtileId", bbsHistoryVO) != null;
	}

	@Override
	public int getTotalEducationQNACount(EducationQNABBSSearchVO searchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEducationQNACount", searchVO);
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
	public void modifyReport(EducationReportVO educationReportVO) {
		getSqlSession().update("EducationDAO.modifyReport", educationReportVO);
	}

	@Override
	public void deleteReport(EducationReportVO educationReportVO) {
		getSqlSession().delete("EducationDAO.deleteReport", educationReportVO);
	}

	@Override
	public String checkEndDate(String articleId) {
		return getSqlSession().selectOne("EducationDAO.reportCheckEndDate", articleId);
	}

	@Override
	public int getTotalQNAReplyCountByAtcId(String atcId) {
		return getSqlSession().selectOne("EducationDAO.getTotalQNAReplyCountByAtcId", atcId);
	}

	@Override
	public int plusRecommendReply(String replyId) {
		return getSqlSession().update("EducationDAO.plusRecommendReply", replyId);
	}

	@Override
	public int plusOpposeReply(String replyId) {
		return getSqlSession().update("EducationDAO.plusOpposeReply", replyId);
	}

	@Override
	public int updateAdoptReply(String replyId) {
		return getSqlSession().update("EducationDAO.updateAdoptReply", replyId);
	}

	@Override
	public int checkAdoptReply(String replyId) {
		return getSqlSession().selectOne("EducationDAO.checkAdoptReply" ,replyId);
	}

	@Override
	public int getBBSHistorySeq() {
		return getSqlSession().selectOne("EducationDAO.getBBSHistorySeq");
	}

	@Override
	public int addHitsEducationFileBBSByArticleId(String articleId) {
		return getSqlSession().update("EducationDAO.addHitsEducationFileBBSByArticleId", articleId);
	}

	@Override
	public int addBBSHistoryHitByArticleId(BBSHistoryVO bbsHistoryVO) {
		return getSqlSession().insert("EducationDAO.addBBSHistoryHitByArticleId", bbsHistoryVO);
	}
	
	@Override
	public String getEducationClassMember(Map<String, String> map) {
		return getSqlSession().selectOne("EducationDAO.getEducationClassMember", map);
	}

	@Override
	public String getEducationClassTeacher(Map<String, String> map) {
		return getSqlSession().selectOne("EducationDAO.getEducationClassTeacherByArticleId", map);
	}

	@Override
	public String getEducationIdByFileBBSArticleId(String articleId) {
		return getSqlSession().selectOne("EducationDAO.getEducationIdByFileBBSArticleId", articleId);
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
	public int writeFileBBSReply(BBSReplyVO bbsReplyVO) {
		return getSqlSession().insert("EducationDAO.writeFileBBSReply", bbsReplyVO);
	}

	@Override
	public List<BBSReplyVO> getAllBBSReplyByArticle(BBSReplySearchVO searchVO) {
		return getSqlSession().selectList("EducationDAO.getAllBBSReplyByArticle", searchVO);
	}

	@Override
	public int getTotalFileBBSReplyCountByArticleId(String articleId) {
		return getSqlSession().selectOne("EducationDAO.getTotalFileBBSReplyCountByArticleId", articleId);
	}

	@Override
	public int getNextOrderNo(String parentReplyId) {
		return getSqlSession().selectOne("EducationDAO.getNextOrderNo", parentReplyId);
	}

	@Override
	public int deleteFileBBSByArticleId(String articleId) {
		return getSqlSession().update("EducationDAO.deleteFileBBSByArticleId", articleId);
	}

	@Override
	public int modifyFileBBS(EducationFileBBSVO educationFileBBSVO) {
		return getSqlSession().update("EducationDAO.modifyFileBBS", educationFileBBSVO);
	}

	@Override
	public int getNextEduBrdHtrId() {
		return getSqlSession().selectOne("EducationDAO.getNextEduBrdHtrId");
	}

	@Override
	public int insertEduBBSAccess(EducationBoardHistoryVO educationBoardHistoryVO) {
		return getSqlSession().insert("EducationDAO.insertEduBBSAccess", educationBoardHistoryVO);
	}

	@Override
	public List<String> getEduBBSAccessMemberList(String educationId) {
		return getSqlSession().selectList("EducationDAO.getEduBBSAccessMemberList", educationId);
	}

	@Override
	public int insertEducationState(String educationId, String memberId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("memberId", memberId);
		
		return getSqlSession().insert("EducationDAO.insertEducationState", paramMap);
	}

	@Override
	public int insertEduStateToReserve(String educationId, String id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().insert("EducationDAO.insertEduStateToReserve", paramMap);
	}

	@Override
	public List<EduNoticeVO> getAllEduFileNotice(EduNoticeSearchVO eduNoticeSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduFileNotice", eduNoticeSearchVO);
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
	public String getOneEduNoticeId() {
		return getSqlSession().selectOne("EducationDAO.getOneEduNoticeId");
	}

	@Override
	public String getOneEducationId() {
		return getSqlSession().selectOne("EducationDAO.getOneEducationId");
	}

	@Override
	public void deleteEducationHistoryByMemberId(String memberId) {
		getSqlSession().delete("EducationDAO.deleteEducationHistoryByMemberId", memberId);
	}
}
