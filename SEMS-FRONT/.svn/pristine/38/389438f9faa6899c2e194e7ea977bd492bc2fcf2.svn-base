package com.ktds.sems.education.biz.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.BBSHistoryVO;
import com.ktds.sems.education.vo.BBSReplySearchVO;
import com.ktds.sems.education.vo.BBSReplyVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EducationBoardHistoryVO;
import com.ktds.sems.education.vo.EducationFileBBSVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationQNABBSSearchVO;
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

import kr.co.hucloud.utilities.excel.option.WriteOption;
import kr.co.hucloud.utilities.excel.write.ExcelWrite;

public class EducationBizImpl implements EducationBiz {

	private EducationDAO educationDAO;
	
	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}

	@Override
	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO) {
		return educationDAO.getAllEducationList(searchVO);
	}

	@Override
	public int getTotalEducationCount() {
		return educationDAO.getTotalEducationCount();
	}

	@Override
	public EducationVO getOneEducationDetail(String educationId) {
		return educationDAO.getOneEducationDetail(educationId);
	}
	
	@Override
	public int getSearchedEducationCount(EducationVO educationVO) {
		return educationDAO.getSearchedEducationCount( educationVO);
	}

	@Override
	public List<EducationVO> getMemberRegInfo(String id) {
		return educationDAO.getMemberRegInfo(id);

	}

	@Override
	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO) {
		return educationDAO.doSearchList( educationVO , searchVO);
	}
	
	@Override
	public boolean writeNewComment(QNAVO qnaVO) {
		//qnaVO.getReplyId();
		return educationDAO.insertNewComment(qnaVO) > 0;
	}

	@Override
	public boolean doApplyEducation(String educationId, String id) {
		return educationDAO.doApplyEducation(educationId, id) > 0;
	}
	
	@Override
	public boolean doCancelEducation(String educationId, String id) {
		return educationDAO.doCancelEducation(educationId, id) > 0;
	}

	@Override
	public String getNowDate() {
		return educationDAO.getNowDate();
	}

	@Override
	public int getNextReplySeq() {
		return educationDAO.getNextReplySeq();
	}

	@Override
	public String isApplyMemberByEducationId(String educationId, String id) {
		return educationDAO.isApplyMemberByEducationId(educationId, id);
	}

	@Override
	public int getEduReplyCount(String educationId) {
		return educationDAO.getEduReplyCount(educationId);
	}

	@Override
	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO) {
		return educationDAO.getAllCommentByEducationId(educationId, searchVO);
	}

	@Override
	public List<String> getTypeName() {
		return educationDAO.getTypeName();
	}

	@Override
	public List<String> getCostName() {
		return educationDAO.getCostName();
	}

	@Override
	public String doTransTypeId(String educationType) {
		return educationDAO.doTransTypeId(educationType);
	}

	@Override
	public String doTransCostId(String cost) {
		// TODO Auto-generated method stub
		return educationDAO.doTransCostId(cost);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public int getTotalQNACount(QNASearchVO qnaSearchVO) {
		return educationDAO.getTotalQNACount(qnaSearchVO);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO) {
		return educationDAO.getAllQNAList(qnaSearchVO);
	}
	
	@Override
	public boolean doReReplyInsert(QNAVO qnaVO) {
		return educationDAO.doReReplyInsert(qnaVO) > 0;
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public QNAVO getSelectedQNA(String replyId) {
		return educationDAO.getSelectedQNA(replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getSelectedQNAAnswer(String replyId) {
		return educationDAO.getSelectedQNAAnswer(replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public boolean exportQNAListAsExcel(String memberId) {
		WriteOption wo = new WriteOption();
		wo.setSheetName("교육 문의 내역");
		wo.setFileName("교육 문의 내역.xlsx");
		wo.setFilePath("D:\\");
		List<String> titles = new ArrayList<String>();

		titles.add("문의 아이디");
		titles.add("교육명");
		titles.add("문의 날짜");
		titles.add("문의 내용");
		titles.add("답변 여부");
		wo.setTitles(titles);

		List<String[]> contents = new ArrayList<String[]>();

		// LoginHistory 만들기
		try {
			List<QNAVO> qnaVO = educationDAO.exportQNAListAsExcel(memberId);
			Iterator<QNAVO> tempIterator = qnaVO.iterator();

			// TODO while문으로 null을 만날 때 까지 while문을 돌려야 할 것 같다
			while (tempIterator.hasNext()) {
				// TODO String[] 타입인데... 이걸 수정해바야 할 것 같다.
				// 하나씩 String[]에 담는 것 그리고 add
				QNAVO tempQnaVO = new QNAVO();
				tempQnaVO = tempIterator.next();

				String[] content = new String[5];

				content[0] = tempQnaVO.getReplyId();
				content[1] = tempQnaVO.getEduId();
				content[2] = tempQnaVO.getCreatedDate();
				content[3] = tempQnaVO.getDescription();
				content[4] = tempQnaVO.getIsAnswered();

				contents.add(content);
			}
			wo.setContents(contents);

			File excelFile = ExcelWrite.write(wo);
			
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public boolean hasApplyHistory(String memberId, String educationId) {
		List<EducationVO> educationVO = educationDAO.getApplyHistory(memberId, educationId);
		if (educationVO.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isEducationStarted(String educationId) {
		Date educationStartDate;
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		EducationVO educationVO = educationDAO.getOneEducationDetail(educationId);
		String startDate = educationVO.getStartDate();

		try {
			educationStartDate = dateFormat.parse(startDate);
			int compare = currentDate.compareTo(educationStartDate);
			if ( compare < 0 ) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
		}

		return true;
	}

	@Override
	public String getEmail(String id) {
		return educationDAO.getEmail(id);
	}

	@Override
	public void sendEmailByReReply(QNAVO questionVO, QNAVO answerVO, String email) {
		SendMail sendMail = new SendMail();
		MailVO mailVO = new MailVO();

		mailVO.setFromId("testForSendEmailKtds@gmail.com");
		mailVO.setFromPassword("123qwe!@#qwe");
		mailVO.setSubject("문의하신 질문에 대한 답변입니다.");
		mailVO.setText("<html><body>문의하신분 : " + questionVO.getMbrId() + "<br/> 작성 시간 : " + questionVO.getCreatedDate()
				+ "<br/> 문의 내용 : " + questionVO.getDescription() + "<br/><br/><br/><br/>" + " 답변 작성자: "
				+ answerVO.getMbrId() + "<br/> 작성 시간 : " + answerVO.getCreatedDate() + "<br/> 답변 내용 : "
				+ answerVO.getDescription() + "<br/><br/> 문의해 주셔서 감사합니다." + "</body></html>");

		mailVO.setToId(email);

		sendMail.sendMailToCustomer(mailVO);
	}

	@Override
	public int getNextReReplyEval() {
		return educationDAO.getNextReReplyEval();
	}

	@Override
	public boolean plusReReplyLike(String replyId) {
		return educationDAO.plusReReplyLike(replyId) > 0;
	}

	@Override
	public boolean insertReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.insertReReplyEval(reRplyEvalVO) > 0;
	}

	@Override
	public boolean checkReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.checkReReplyEval(reRplyEvalVO) > 0;
	}

	@Override
	public String getStartYear() {
		return educationDAO.getStartYear();
	}

	@Override
	public String getEndYear() {
		return educationDAO.getEndYear();
	}
	
	@Override
	public boolean insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.insertReReplyEvalByDislike(reRplyEvalVO) > 0;
	}

	@Override
	public boolean plusReReplyDislike(String replyId) {
		return educationDAO.plusReReplyDislike(replyId) > 0;
	}

	@Override
	public boolean doRequestRetraction(String educationId, String retractionMsg, String memberId) {
		return educationDAO.doRequestRetraction(educationId, retractionMsg, memberId) > 0;
	}

	@Override
	public boolean doReReplyDelete(QNAVO qnaVO) {
		return educationDAO.doReReplyDelete(qnaVO) > 0;
	}

	@Override
	public boolean deleteReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.deleteReReplyEval(reRplyEvalVO) > 0;
	}
	

	@Override
	public int getTotalMemberNumber(String educationId) {
		return educationDAO.getTotalMemberNumber(educationId);
	}

	@Override
	public boolean doReserveEducation(String educationId, String id) {
		return educationDAO.doReserveEducation(educationId, id) > 0;
	}

	@Override
	public boolean updateStateToApply(String educationId) {
		return educationDAO.updateStateToApply(educationId) > 0;
	}
	
	@Override
	public List<EducationFileBBSVO> getEducationFileBBSList(FileBBSSearchVO searchVO) {
		return educationDAO.getEducationFileBBSList(searchVO);
	}

	@Override
	public String generateArticleId() {
		String nowDate = educationDAO.getNowDate();
		String articleSEQ = educationDAO.getArticleSEQ();
		
		return "FL-" + nowDate + "-" + lpad(articleSEQ, 6, "0") ;
	}
	
	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;
	}

	@Override
	public String getMemberIdByEducationId(String educationId) {
		return educationDAO.getMemberIdByEducationId(educationId);
	}

	@Override
	public boolean writeNewFileBBS(EducationFileBBSVO educationFileBBSVO) {
		return educationDAO.writeNewFileBBS(educationFileBBSVO) > 0;
	}

	@Override
	public List<EducationVO> getMyEducationList(String id) {
		return educationDAO.getMyEducationList(id);
	}

	@Override
	public List<EducationQNABBSVO> getAllEducationQNAList(EducationQNABBSSearchVO searchVO) {
		return educationDAO.getAllEducationQNAList(searchVO);
	}

	@Override
	public void addQNABBS(EducationQNABBSVO eduBBS) {
		educationDAO.addQNABBS(eduBBS);
	}

	@Override
	public List<EducationReportVO> getAllEducationReportList(EducationReportSearchVO educationReportSearchVO) {
		return educationDAO.getAllEducationReportList(educationReportSearchVO);
	}

	@Override
	public int getTotalEducationReportCount(EducationReportSearchVO educationReportSearchVO) {
		return educationDAO.getTotalEducationReportCount(educationReportSearchVO);
	}

	@Override
	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId) {
		return educationDAO.getOneQNABBSByAtcId(atcId);
	}

	@Override
	public void doReportWriteAction(EducationReportVO educationReportVO) {
		educationDAO.doReportWriteAction(educationReportVO);
	}

	@Override
	public int getNextReportSeq() {
		return educationDAO.getNextReportSeq();
	}

	@Override
	public void addHitsByAtcId(String atcId) {
		educationDAO.addHitsByAtcId(atcId);
	}

	@Override
	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO) {
		educationDAO.addQNAReply(eduBBSReplyVO);
	}
	
	@Override
	public List<MemberVO> getAllMemberOfEducation(String educationId) {
		return educationDAO.getAllMemberOfEducation(educationId);
	}

	@Override
	public boolean addRequestRetractionHistory(String educationId, String retractionMsg, String memberId, String ip) {
		return educationDAO.addRequestRetractionHistory(educationId, retractionMsg, memberId, ip) > 0;
	}

	@Override
	public int getTotalReportReplyCount(ReportReplySearchVO reportReplySearchVO) {
		return educationDAO.getTotalReportReplyCount(reportReplySearchVO);
	}

	@Override
	public List<ReportReplyVO> getAllReportReply(ReportReplySearchVO reportReplySearchVO) {
		return educationDAO.getAllReportReply(reportReplySearchVO);
	}
	
	@Override
	public int getTotalReportReplyCountOfTeacher(ReportReplySearchVO reportReplySearchVO) {
		return educationDAO.getTotalReportReplyCountOfTeacher(reportReplySearchVO);
	}
	
	@Override
	public List<ReportReplyVO> getAllReportReplyOfTeacher(ReportReplySearchVO reportReplySearchVO) {
		return educationDAO.getAllReportReplyOfTeacher(reportReplySearchVO);
	}

	@Override
	public EducationReportVO getOneEducationReport(EducationReportVO educationReportVO) {
		return educationDAO.getOneEducationReport(educationReportVO);
	}

	@Override
	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(EducationQNAReplySearchVO searchVO) {
		return educationDAO.getAllQNAReplyListByAtcId(searchVO);
	}

	@Override
	public void doReportSubmit(ReportReplyVO reportReplyVO) {
		educationDAO.doReportSubmit(reportReplyVO);
	}

	@Override
	public int getNextReportReplySeq() {
		return educationDAO.getNextReportReplySeq();
	}

	@Override
	public List<ReportReplyVO> getAllReportByArticleId(String articleId, ReportReplySearchVO searchVO) {
		return educationDAO.getAllReportByArticleId(articleId, searchVO);
	}

	@Override
	public int getReportReplyCount(String articleId) {
		return educationDAO.getReportReplyCount(articleId);
	}

	@Override
	public String getNowDateTime() {
		return educationDAO.getNowDateTime();
	}
	
	@Override
	public int getEducationFileBBSCount(FileBBSSearchVO searchVO) {
		return educationDAO.getEducationFileBBSCount(searchVO);
	}

	@Override
	public EducationFileBBSVO getOneEducationFileBBS(String articleId) {
		return educationDAO.getOneEducationFileBBS(articleId);
	}

	@Override
	public void addHitsEducationFileBBSByArticleId(BBSHistoryVO bbsHistoryVO) {
		String articleId = bbsHistoryVO.getBbsId();
		boolean isExistHit = educationDAO.isExistedHitMemberId(bbsHistoryVO);
		if ( !isExistHit ) {
			String nowDate = getNowDate();
			int bbsHistorySeq = educationDAO.getBBSHistorySeq();
			String bbsHistoryId = "BHTR-" + nowDate + "-" + lpad(bbsHistorySeq+"", 6, "0");
			bbsHistoryVO.setBbsHistoryId(bbsHistoryId);
			
			educationDAO.addHitsEducationFileBBSByArticleId(articleId);
			educationDAO.addBBSHistoryHitByArticleId(bbsHistoryVO);
		}
	}
	
	@Override
	public int getTotalEducationQNACount(EducationQNABBSSearchVO searchVO) {
		return educationDAO.getTotalEducationQNACount(searchVO);
	}

	@Override
	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		return educationDAO.getJCEduHistoryCount(eduHistorySearchVO);
	}

	@Override
	public List<EducationHistoryVO> getJCEducationHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return educationDAO.getJCEduHistoryHistory(eduHistorySearchVO);
	}

	@Override
	public void modifyReport(EducationReportVO educationReportVO) {
		
		EducationReportVO prevReportVO = educationDAO.getOneEducationReport(educationReportVO);
		EducationReportVO changedReportVO = new EducationReportVO();
		changedReportVO.setArticleId(educationReportVO.getArticleId());
		
		prevReportVO.setStartDate(prevReportVO.getStartDate().replaceAll(" ", "T"));
		prevReportVO.setEndDate(prevReportVO.getEndDate().replaceAll(" ", "T"));
		
		if ( !prevReportVO.getTitle().equals(educationReportVO.getTitle()) ) {
			changedReportVO.setTitle(educationReportVO.getTitle());
		}
		if ( !prevReportVO.getContents().equals(educationReportVO.getContents()) ) {
			changedReportVO.setContents(educationReportVO.getContents());
		} 
		if ( !prevReportVO.getStartDate().equals(educationReportVO.getStartDate()) ) {
			changedReportVO.setStartDate(educationReportVO.getStartDate().replaceAll("T", " "));
		}
		if ( !prevReportVO.getEndDate().equals(educationReportVO.getEndDate()) ) {
			changedReportVO.setEndDate(educationReportVO.getEndDate().replaceAll("T", " "));
		}	
		
		educationDAO.modifyReport(changedReportVO);
	}

	@Override
	public void deleteReport(EducationReportVO educationReportVO) {
		educationDAO.deleteReport(educationReportVO);
	}

	@Override
	public String checkEndDate(String articleId) {
		return educationDAO.checkEndDate(articleId);
	}


	@Override
	public boolean checkEndDate(String educationId, String id) {
		return educationDAO.checkEndDate(educationId, id) > 0;
	}


	@Override
	public int getTotalQNAReplyCountByAtcId(String atcId) {
		return educationDAO.getTotalQNAReplyCountByAtcId(atcId);
	}

	@Override
	public boolean isEducationClassMember(Map<String, String> map) {
		return educationDAO.getEducationClassMember(map) != null;
	}
	
	@Override
	public boolean plusRecommendReply(String replyId) {
		return educationDAO.plusRecommendReply(replyId) > 0;
	}

	@Override
	public boolean plusOpposeReply(String replyId) {
		return educationDAO.plusOpposeReply(replyId) > 0;
	}

	@Override
	public boolean updateAdoptReply(String replyId) {
		return educationDAO.updateAdoptReply(replyId) > 0;
	}

	@Override
	public boolean checkAdoptReply(String replyId) {
		return educationDAO.checkAdoptReply(replyId) > 0;
	}
	
	@Override
	public boolean isEducationClassTeacher(Map<String, String> map) {
		return educationDAO.getEducationClassTeacher(map) != null;
	}

	@Override
	public String getEducationIdByFileBBSArticleId(String articleId) {
		return educationDAO.getEducationIdByFileBBSArticleId(articleId);
	}

	@Override
	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO) {
		return educationDAO.getTotalEduQnaCount(eduQnaSearchVO);
	}

	@Override
	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO) {
		return educationDAO.getAllEduQna(eduQnaSearchVO);
	}

	@Override
	public boolean confirmMemberOfEdu(String educationId, String memberId) {
		return educationDAO.confirmMemberOfEdu(educationId, memberId) > 0;
	}

	@Override
	public boolean insertEduQna(EduQnaVO eduQnaVO) {
		return educationDAO.insertEduQna(eduQnaVO) > 0;
	}

	@Override
	public int getNextEqbSeq() {
		return educationDAO.getNextEqbSeq();
	}

	@Override
	public EduQnaVO detailOfEduQna(String eduQnaId) {
		return educationDAO.detailOfEduQna(eduQnaId);
	}

	@Override
	public boolean addHitsToEduQna(String eduQnaId) {
		return educationDAO.addHitsToEduQna(eduQnaId) > 0;
	}

	@Override
	public boolean addQnaEduReplyLike(String replyId) {
		return educationDAO.addQnaEduReplyLike(replyId) > 0;
	}

	@Override
	public boolean addQnaEduReplyDisLike(String replyId) {
		return educationDAO.addQnaEduReplyDisLike(replyId) > 0;
	}

	@Override
	public void sendEmailInEduQna(String toEmail, String fromEmail, EduQnaVO eduQnaVO , EducationQNAReplyVO eduBBSReplyVO) {
		SendMail sendMail = new SendMail();
		MailVO mailVO = new MailVO();

		mailVO.setFromId(fromEmail);
		mailVO.setFromPassword("123qwe!@#qwe");
		mailVO.setSubject("문의하신 질문에 대한 답변입니다.");
		mailVO.setText("<html><body>문의하신분 : " + eduQnaVO.getMemberId() + "<br/> 작성 시간 : " + eduQnaVO.getCreateDate()
				+ "<br/> 문의 내용 : " + eduQnaVO.getContents() + "<br/><br/><br/><br/>" + " 답변 작성자: "
				+ eduBBSReplyVO.getMbrId()  + "<br/> 답변 내용 : "
				+ eduBBSReplyVO.getDescription() + "<br/><br/> 문의해 주셔서 감사합니다." + "</body></html>");

		mailVO.setToId(toEmail);
		
		sendMail.sendMailToCustomer(mailVO);
		
	}

	@Override
	public int getTotalQnaEduReplyCount(String eduQnaId) {
		return educationDAO.getTotalQnaEduReplyCount(eduQnaId);
	}

	@Override
	public boolean writeFileBBSReply(BBSReplyVO bbsReplyVO) {
		return educationDAO.writeFileBBSReply(bbsReplyVO) > 0;
	}
	
	@Override
	public String generateBBSReplyId() {
		String nowDate = educationDAO.getNowDate();
		int bbsReplyidSEQ = educationDAO.getNextReplySeq();
		
		return "RP-" + nowDate + "-" + lpad(bbsReplyidSEQ+"", 6, "0") ;
	}

	@Override
	public List<BBSReplyVO> getAllBBSReplyByArticle(BBSReplySearchVO searchVO) {
		return educationDAO.getAllBBSReplyByArticle(searchVO);
	}

	@Override
	public int getTotalFileBBSReplyCountByArticleId(String articleId) {
		return educationDAO.getTotalFileBBSReplyCountByArticleId(articleId);
	}

	@Override
	public int getNextOrderNo(String parentReplyId) {
		return educationDAO.getNextOrderNo(parentReplyId);
	}

	@Override
	public boolean deleteFileBBSByArticleId(String articleId) {
		return educationDAO.deleteFileBBSByArticleId(articleId) > 0;
	}

	@Override
	public boolean modifyFileBBS(EducationFileBBSVO educationFileBBSVO) {
		return educationDAO.modifyFileBBS(educationFileBBSVO) > 0;
	}

	@Override
	public int getNextEduBrdHtrId() {
		return educationDAO.getNextEduBrdHtrId();
	}

	@Override
	public boolean insertEduBBSAccess(EducationBoardHistoryVO educationBoardHistoryVO) {
		return educationDAO.insertEduBBSAccess(educationBoardHistoryVO) > 0;
	}

	@Override
	public List<String> getEduBBSAccessMemberList(String educationId) {
		return educationDAO.getEduBBSAccessMemberList(educationId);
	}

	@Override
	public boolean insertEducationState(String educationId, String memberId) {
		return educationDAO.insertEducationState(educationId, memberId) > 0;
	}

	@Override
	public boolean insertEduStateToReserve(String educationId, String id) {
		return educationDAO.insertEduStateToReserve(educationId, id) > 0;
	}

	@Override
	public List<EduNoticeVO> getAllEduFileNotice(EduNoticeSearchVO eduNoticeSearchVO) {
		return educationDAO.getAllEduFileNotice(eduNoticeSearchVO);
	}


	@Override
	public EduNoticeVO getOneNotice(String eduNoticeId) {
		return educationDAO.getOneNotice(eduNoticeId);
	}

	@Override
	public void addhits(String eduNoticeId) {
		educationDAO.addhits(eduNoticeId);
	}

	
}


