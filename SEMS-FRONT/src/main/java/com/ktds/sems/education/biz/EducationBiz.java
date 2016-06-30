package com.ktds.sems.education.biz;

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

public interface EducationBiz {

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public int getTotalEducationCount();

	public EducationVO getOneEducationDetail(String educationId);

	public List<EducationVO> getMemberRegInfo(String id);

	public boolean writeNewComment(QNAVO qnaVO);

	public boolean doApplyEducation(String educationId, String id);

	public boolean doCancelEducation(String educationId, String id);

	public int getSearchedEducationCount(EducationVO educationVO);

	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO);

	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO);

	public String getNowDate();

	public int getNextReplySeq();

	public String isApplyMemberByEducationId(String educationId, String id);

	public int getEduReplyCount(String educationId);

	public List<String> getTypeName();

	public List<String> getCostName();

	public String doTransTypeId(String educationType);

	public String doTransCostId(String cost);

	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO);

	public int getTotalQNACount(QNASearchVO qnaSearchVO);

	public boolean doReReplyInsert(QNAVO qnaVO);

	public QNAVO getSelectedQNA(String replyId);

	public List<QNAVO> getSelectedQNAAnswer(String replyId);

	public boolean exportQNAListAsExcel(String memberId);

	public boolean hasApplyHistory(String memberId, String educationId);

	public boolean isEducationStarted(String educationId);

	public String getEmail(String id);

	public void sendEmailByReReply(QNAVO questionVO, QNAVO answerVO, String email);

	public int getNextReReplyEval();

	public boolean plusReReplyLike(String replyId);

	public boolean insertReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public boolean checkReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public String getStartYear();

	public String getEndYear();

	public boolean insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO);

	public boolean plusReReplyDislike(String replyId);

	public boolean doRequestRetraction(String educationId, String retractionMsg, String memberId);
	
	public boolean doReReplyDelete(QNAVO qnaVO);
	
	public boolean deleteReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public int getTotalMemberNumber(String educationId);

	public boolean doReserveEducation(String educationId, String id);

	public boolean updateStateToApply(String educationId);
	
	public List<EducationFileBBSVO> getEducationFileBBSList(FileBBSSearchVO searchVO);

	public String generateArticleId();

	public String getMemberIdByEducationId(String educationId);

	public boolean writeNewFileBBS(EducationFileBBSVO educationFileBBSVO);
	
	public List<EducationVO> getMyEducationList(String id);

	public List<EducationQNABBSVO> getAllEducationQNAList(EducationQNABBSSearchVO searchVO);

	public void addQNABBS(EducationQNABBSVO eduBBS);

	public List<EducationReportVO> getAllEducationReportList(EducationReportSearchVO educationReportSearchVO);

	public int getTotalEducationReportCount(EducationReportSearchVO educationReportSearchVO);

	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId);

	public void doReportWriteAction(EducationReportVO educationReportVO);

	public int getNextReportSeq();

	public void addHitsByAtcId(String atcId);

	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO);
	
	public List<MemberVO> getAllMemberOfEducation(String educationId);

	public boolean addRequestRetractionHistory(String educationId, String retractionMsg, String memberId, String ip);

	public boolean checkEndDate(String educationId, String id);

	public int getTotalReportReplyCount(ReportReplySearchVO reportReplySearchVO);

	public List<ReportReplyVO> getAllReportReply(ReportReplySearchVO reportReplySearchVO);
	
	public int getTotalReportReplyCountOfTeacher(ReportReplySearchVO reportReplySearchVO);
	
	public List<ReportReplyVO> getAllReportReplyOfTeacher(ReportReplySearchVO reportReplySearchVO);

	public EducationReportVO getOneEducationReport(EducationReportVO educationReportVO);

	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(EducationQNAReplySearchVO searchVO);

	public void doReportSubmit(ReportReplyVO reportReplyVO);

	public int getNextReportReplySeq();

	public List<ReportReplyVO> getAllReportByArticleId(String articleId, ReportReplySearchVO searchVO);

	public int getReportReplyCount(String articleId);

	public String getNowDateTime();
	
	public void modifyReport(EducationReportVO educationReportVO);

	public void deleteReport(EducationReportVO educationReportVO);

	public String checkEndDate(String articleId);

	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);

	public List<EducationHistoryVO> getJCEducationHistory(EducationHistorySearchVO eduHistorySearchVO);

	public int getTotalEducationQNACount(EducationQNABBSSearchVO searchVO);

	public int getTotalQNAReplyCountByAtcId(String atcId);

	public int getEducationFileBBSCount(FileBBSSearchVO searchVO);

	public EducationFileBBSVO getOneEducationFileBBS(String articleId);

	public void addHitsEducationFileBBSByArticleId(BBSHistoryVO bbsHistoryVO);

	public boolean isEducationClassMember(Map<String, String> map);

	public boolean isEducationClassTeacher(Map<String, String> map);
	
	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO);

	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO);

	public boolean confirmMemberOfEdu(String educationId, String memberId);

	public boolean insertEduQna(EduQnaVO eduQnaVO);

	public int getNextEqbSeq();

	public EduQnaVO detailOfEduQna(String eduQnaId);
	
	public boolean addHitsToEduQna(String eduQnaId);

	public boolean addQnaEduReplyLike(String replyId);

	public boolean addQnaEduReplyDisLike(String replyId);

	public void sendEmailInEduQna(String toEmail, String fromEmail, EduQnaVO eduQnaVO, EducationQNAReplyVO eduBBSReplyVO);

	public int getTotalQnaEduReplyCount(String eduQnaId);

	public boolean plusRecommendReply(String replyId);

	public boolean plusOpposeReply(String replyId);

	public boolean updateAdoptReply(String replyId);

	public boolean checkAdoptReply(String replyId);

	public String getEducationIdByFileBBSArticleId(String articleId);

	public boolean writeFileBBSReply(BBSReplyVO bbsReplyVO);

	public String generateBBSReplyId();

	public List<BBSReplyVO> getAllBBSReplyByArticle(BBSReplySearchVO searchVO);

	public int getTotalFileBBSReplyCountByArticleId(String articleId);

	public int getNextOrderNo(String parentReplyId);

	public boolean deleteFileBBSByArticleId(String articleId);

	public boolean modifyFileBBS(EducationFileBBSVO educationFileBBSVO);

	public int getNextEduBrdHtrId();

	public boolean insertEduBBSAccess(EducationBoardHistoryVO educationBoardHistoryVO);

	public List<String> getEduBBSAccessMemberList(String educationId);

	public boolean insertEducationState(String educationId, String memberId);

	public boolean insertEduStateToReserve(String educationId, String id);

	public List<EduNoticeVO> getAllEduFileNotice(EduNoticeSearchVO eduNoticeSearchVO);

	public EduNoticeVO getOneNotice(String eduNoticeId);

	public void addhits(String eduNoticeId);
}
