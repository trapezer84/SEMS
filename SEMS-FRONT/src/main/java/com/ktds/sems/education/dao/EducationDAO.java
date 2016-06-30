package com.ktds.sems.education.dao;

import java.util.List;
import java.util.Map;

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

public interface EducationDAO {

	public int getTotalEducationCount();

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public EducationVO getOneEducationDetail(String educationId);

	public List<EducationVO> getMemberRegInfo(String id);

	public int doApplyEducation(String educationId, String id);

	public int getSearchedEducationCount(EducationVO educationVO);

	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO);

	public int insertNewComment(QNAVO qnaVO);

	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO);

	public String getNowDate();

	public int getNextReplySeq();

	public int doCancelEducation(String educationId, String id);

	public String isApplyMemberByEducationId(String educationId, String id);

	public int getEduReplyCount(String educationId);

	public List<String> getCostName();

	public List<String> getTypeName();

	public String doTransCostId(String cost);

	public String doTransTypeId(String educationType);

	public int getTotalQNACount(QNASearchVO QNASearchVO);

	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO);

	public int doReReplyInsert(QNAVO qnaVO);

	public QNAVO getSelectedQNA(String replyId);

	public List<QNAVO> getSelectedQNAAnswer(String replyId);

	public List<QNAVO> exportQNAListAsExcel(String memberId);

	public List<EducationVO> getApplyHistory(String memberId, String educationId);

	public String getEmail(String id);

	public int getNextReReplyEval();

	public int plusReReplyLike(String replyId);

	public int insertReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public int checkReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public String getStartYear();

	public String getEndYear();
	
	public int insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO);

	public int plusReReplyDislike(String replyId);

	public int doRequestRetraction(String educationId, String retractionMsg, String memberId);

	public int doReReplyDelete(QNAVO qnaVO);

	public int deleteReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public int getTotalMemberNumber(String educationId);

	public int doReserveEducation(String educationId, String id);

	public int updateStateToApply(String educationId);
	
	public List<EducationFileBBSVO> getEducationFileBBSList(FileBBSSearchVO searchVO);

	public String getArticleSEQ();

	public String getMemberIdByEducationId(String educationId);

	public int writeNewFileBBS(EducationFileBBSVO educationFileBBSVO);

	public List<EducationVO> getMyEducationList(String id);

	public List<EducationQNABBSVO> getAllEducationQNAList(EducationQNABBSSearchVO searchVO);

	public void addQNABBS(EducationQNABBSVO eduBBS);

	public List<EducationReportVO> getAllEducationReportList(EducationReportSearchVO educationReportSearchVO);

	public int getTotalEducationReportCount(EducationReportSearchVO educationReportSearchVO);

	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId);

	public int getNextReportSeq();

	public void doReportWriteAction(EducationReportVO educationReportVO);

	public void addHitsByAtcId(String atcId);

	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO);
	
	public List<MemberVO> getAllMemberOfEducation(String educationId);

	public int addRequestRetractionHistory(String educationId, String retractionMsg, String memberId, String ip);

	public int checkEndDate(String educationId, String id);

	public int getTotalReportReplyCount(ReportReplySearchVO reportReplySearchVO);

	public List<ReportReplyVO> getAllReportReply(ReportReplySearchVO reportReplySearchVO);
	
	public int getTotalReportReplyCountOfTeacher(ReportReplySearchVO reportReplySearchVO);
	
	public List<ReportReplyVO> getAllReportReplyOfTeacher(ReportReplySearchVO reportReplySearchVO);

	public EducationReportVO getOneEducationReport(EducationReportVO educationReportVO);

	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(EducationQNAReplySearchVO searchVO);

	public int getNextReportReplySeq();

	public void doReportSubmit(ReportReplyVO reportReplyVO);

	public int getReportReplyCount(String articleId);

	public List<ReportReplyVO> getAllReportByArticleId(String articleId, ReportReplySearchVO searchVO);

	public String getNowDateTime();
	
	public int getEducationFileBBSCount(FileBBSSearchVO searchVO);
	
	public int getTotalEducationQNACount(EducationQNABBSSearchVO searchVO);

	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);

	public List<EducationHistoryVO> getJCEduHistoryHistory(EducationHistorySearchVO eduHistorySearchVO);

	public void modifyReport(EducationReportVO educationReportVO);

	public void deleteReport(EducationReportVO educationReportVO);

	public String checkEndDate(String articleId);

	public int getTotalQNAReplyCountByAtcId(String atcId);

	public EducationFileBBSVO getOneEducationFileBBS(String articleId);

	public boolean isExistedHitMemberId(BBSHistoryVO bbsHistoryVO);

	public int plusRecommendReply(String replyId);

	public int plusOpposeReply(String replyId);

	public int updateAdoptReply(String replyId);

	public int checkAdoptReply(String replyId);

	public int getBBSHistorySeq();

	public int addHitsEducationFileBBSByArticleId(String articleId);

	public int addBBSHistoryHitByArticleId(BBSHistoryVO bbsHistoryVO);
	
	public String getEducationClassMember(Map<String, String> map);

	public String getEducationClassTeacher(Map<String, String> map);

	public String getEducationIdByFileBBSArticleId(String articleId);

	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO);

	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO);

	public int confirmMemberOfEdu(String educationId, String memberId);

	public int insertEduQna(EduQnaVO eduQnaVO);

	public int getNextEqbSeq();

	public EduQnaVO detailOfEduQna(String eduQnaId);

	public int addHitsToEduQna(String eduQnaId);
	
	public int addQnaEduReplyLike(String replyId);
	
	public int addQnaEduReplyDisLike(String replyId);

	public int getTotalQnaEduReplyCount(String eduQnaId);

	public int writeFileBBSReply(BBSReplyVO bbsReplyVO);

	public List<BBSReplyVO> getAllBBSReplyByArticle(BBSReplySearchVO searchVO);

	public int getTotalFileBBSReplyCountByArticleId(String articleId);

	public int getNextOrderNo(String parentReplyId);

	public int deleteFileBBSByArticleId(String articleId);

	public int modifyFileBBS(EducationFileBBSVO educationFileBBSVO);
	
	public int getNextEduBrdHtrId();

	public int insertEduBBSAccess(EducationBoardHistoryVO educationBoardHistoryVO);

	public List<String> getEduBBSAccessMemberList(String educationId);

	public int insertEducationState(String educationId, String memberId);

	public int insertEduStateToReserve(String educationId, String id);

	public List<EduNoticeVO> getAllEduFileNotice(EduNoticeSearchVO eduNoticeSearchVO);

	public EduNoticeVO getOneNotice(String eduNoticeId);

	public void addhits(String eduNoticeId);

	public String getOneEduNoticeId();

	public String getOneEducationId();

	public void deleteEducationHistoryByMemberId(String memberId);

}
