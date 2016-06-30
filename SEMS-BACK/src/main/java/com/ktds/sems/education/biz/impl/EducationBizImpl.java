package com.ktds.sems.education.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.service.impl.EducationServiceImpl;
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

public class EducationBizImpl implements EducationBiz {

	private EducationDAO educationDAO;
	private Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);	
	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}

	@Override
	public boolean writeNewEducation(EducationVO educationVO) {

		int nextEducationId = educationDAO.nextEduSeq();
		String nowDate = educationDAO.nowDate();
		
		/*
		 * ArticleID의 형식
		 * ED-20160421-000001
		 */
		
		String educationId = "ED-" + nowDate + "-" + lpad(nextEducationId + "", 6, "0");
		
		educationVO.setEducationId(educationId);
		
		return educationDAO.insertNewEducation(educationVO) > 0;
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
	public EducationVO getOneEducation(String educationId) {
		return educationDAO.getOneEducation(educationId);
	}

	@Override
	public boolean modifyNewEducation(EducationVO educationVO) {
		
		EducationVO newEducationVO = educationDAO.getOneEducation(educationVO.getEducationId());
		EducationVO changedEducationVO = new EducationVO();
		changedEducationVO.setEducationId(newEducationVO.getEducationId());

		boolean isEducationCategory = !newEducationVO.getEducationCategory().equals(educationVO.getEducationCategory());
		boolean isEducationTitle = !newEducationVO.getEducationTitle().equals(educationVO.getEducationTitle());
		boolean isMemberId = !newEducationVO.getMemberId().equals(educationVO.getMemberId());
		boolean isMaxMember = !(newEducationVO.getMaxMember()==educationVO.getMaxMember());
		boolean isEducationLocation = !newEducationVO.getEducationLocation().equals(educationVO.getEducationLocation());
		boolean isEducationCurriculum = !newEducationVO.getEducationCurriculum().equals(educationVO.getEducationCurriculum());
		boolean isEducationIntroduce = !newEducationVO.getEducationIntroduce().equals(educationVO.getEducationIntroduce());
		boolean isStartDate = !newEducationVO.getStartDate().equals(educationVO.getStartDate());
		boolean isEndDate = !newEducationVO.getEndDate().equals(educationVO.getEndDate());
		boolean isStartTime = !newEducationVO.getStartTime().equals(educationVO.getStartTime());
		boolean isEndTime = !newEducationVO.getEndTime().equals(educationVO.getEndTime());
		boolean isEducationType = !newEducationVO.getEducationType().equals(educationVO.getEducationType());
		boolean isCost = !newEducationVO.getCost().equals(educationVO.getCost());
		
		if (!isEducationCategory && !isEducationTitle && !isMemberId && !isMaxMember
				&& !isEducationLocation 
				&& !isEducationCurriculum
				&& !isEducationIntroduce 
				&& !isStartDate
				&& !isEndDate 
				&& !isStartTime
				&& !isEndTime
				&& !isEducationType
				&& !isCost) {
			return true;
		}
		if (isEducationCategory) {
			changedEducationVO.setEducationCategory(educationVO.getEducationCategory());
		}
		if (isEducationTitle) {
			changedEducationVO.setEducationTitle(educationVO.getEducationTitle());
		}
		if (isMemberId) {
			changedEducationVO.setMemberId(educationVO.getMemberId());
		}
		if (isMaxMember) {
			changedEducationVO.setMaxMember(educationVO.getMaxMember());
		}
		if (isEducationLocation) {
			changedEducationVO.setEducationLocation(educationVO.getEducationLocation());
		}
		if (isEducationCurriculum) {
			changedEducationVO.setEducationCurriculum(educationVO.getEducationCurriculum());
		}
		if (isEducationIntroduce) {
			changedEducationVO.setEducationIntroduce(educationVO.getEducationIntroduce());
		}
		if (isStartDate) {
			changedEducationVO.setStartDate(educationVO.getStartDate());
		}
		if (isEndDate) {
			changedEducationVO.setEndDate(educationVO.getEndDate());
		}
		if (isStartTime) {
			changedEducationVO.setStartTime(educationVO.getStartTime());
		}
		if (isEndTime) {
			changedEducationVO.setEndTime(educationVO.getEndTime());
		}
		if (isEducationType) {
			changedEducationVO.setEducationType(educationVO.getEducationType());
		}
		if (isEducationType) {
			changedEducationVO.setEducationType(educationVO.getEducationType());
		}
		if (isCost) {
			changedEducationVO.setCost(educationVO.getCost());
		}

		return educationDAO.modifyNewEducation(changedEducationVO) > 0;
	}

	@Override
	public List<CostVO> costCodeList() {
		return educationDAO.costCodeList();
	}

	@Override
	public List<EducationTypeVO> typeCodeList() {
		return educationDAO.typeCodeList();
	}

	@Override
	public List<CategoryVO> categoryCodeList() {
		return educationDAO.categoryCodeList();
	}

	@Override
	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return educationDAO.getAllEducationHistory(eduHistorySearchVO);
	}

	@Override
	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		return educationDAO.getAllEduHistoryCount(eduHistorySearchVO);
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
	public void sendEmailRejection(String educationHistoryId, String memberId, String description, String email, String name) {
		SendMail sendMail = new SendMail();
		MailVO mailVO = new MailVO();
		mailVO.setFromId("testForSendEmailKtds@gmail.com");
		//mailVO.setFromId("semsjhg@gmail.com");
		mailVO.setFromPassword("123qwe!@#qwe");
		mailVO.setSubject("[sems] " + name + "님의 교육 포기/취소 신청에 대한 답변입니다.");
		mailVO.setText(
						memberId + "(" + name + ")" + "님이 신청하신 요청 번호 [" + educationHistoryId + "] 에 대해 검토해본 결과 관리자에 의해 요청이 거절되었음을 알립니다. "
						+ " 요청에 대한 거절 사유: [ " + description + " ]"
					  );
		
		mailVO.setToId(email);
		sendMail.sendMailToCustomer(mailVO);
		
	}

	public boolean applyJoinEducationByMemberId(String educationHistoryId, String changeState) {
		return educationDAO.applyJoinEducationByMemberId(educationHistoryId, changeState) > 0;
	}

	@Override
	public boolean cancelJoinEducationByMemberId(String educationHistoryId, String changeState) {
		return educationDAO.cancelJoinEducationByMemberId(educationHistoryId, changeState) > 0;
	}
	
	@Override
	public String getStateByEducationHistroyId(String educationHistoryId) {
		return educationDAO.getStateByEducationHistroyId(educationHistoryId);
	}

	@Override
	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO) {
		return educationDAO.getTotalEduReportCount(eduReportSearchVO);
	}
	
	@Override
	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO) {
		return educationDAO.getAllEduReport(eduReportSearchVO);
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
	public int getTotalEduFileCount(EduFileSearchVO eduFileSearchVO) {
		return educationDAO.getTotalEduFileCount(eduFileSearchVO);
	}

	@Override
	public List<EduFileVO> getAllEduFile(EduFileSearchVO eduFileSearchVO) {
		return educationDAO.getAllEduFile(eduFileSearchVO);
	}

	@Override
	public int changeEducationApplyState(String educationHistoryId) {
		return educationDAO.changeEducationApplyState(educationHistoryId);
	}

	@Override
	public boolean doActionDeleteBeforeCheck(MemberVO memberVO) {
		if(educationDAO.doActionDeleteBeforeCheck(memberVO).equals("Y")){
			return true;
		}
		return false;
	}

	@Override
	public void doActionDelete(String educationId) {
		educationDAO.doActionDelete(educationId);
	}
	
	// 아래서부터 검증 필요
	@Override
	public void emailNoticeForUser(String educationId) {
		List<EducationVO> attendedLectureUserList = educationDAO.attendedLectureUserList(educationId);
		
		for (EducationVO educationVO : attendedLectureUserList) {
			logger.info("memberId :" + educationVO.getMemberId());
			MemberVO memberVO = educationDAO.emailNoticeForUser(educationVO.getMemberId());
			logger.info("Id :" + memberVO.getId());
			logger.info("Email :" + memberVO.getEmail());
			
			SendMail sendMail = new SendMail();
			MailVO mailVO = new MailVO();
			
			mailVO.setFromId("testForSendEmailKtds@gmail.com");
			mailVO.setFromPassword("123qwe!@#qwe");
			
			mailVO.setSubject("[sems] " +"교육이 폐강되었음을 알려드립니다.");
			mailVO.setText( "교육이 폐강이 되었기에" + "(" + memberVO.getName() + ") 님에게 해당 내용을 알려드리는 바입니다.\n "
					+ "추후 더 좋은 서비스로 찾아뵙도록 하겠습니다. 감사합니다.");
			mailVO.setToId(memberVO.getEmail());
			// TODO  메일 보내기 주석 나중에 풀어주세요.
			//sendMail.sendMailToCustomer(mailVO);
		}
	}

	@Override
	public boolean writeEduFileNoticeAction(EduNoticeVO eduNoticeVO) {
		
		int nexteduNoticeId = educationDAO.nextEduNoticeSeq();
		String nowDate = educationDAO.nowDate();
		
		/*
		 * IMP-20160421-000001
		 */
		
		String eduNoiceId = "IMP-" + nowDate + "-" + lpad(nexteduNoticeId + "", 6, "0");
		
		eduNoticeVO.setEduNoticeId(eduNoiceId);
		eduNoticeVO.setContents(eduNoticeVO.getContents().replaceAll("\n", "<br/>"));
		return educationDAO.insertNewEduFileNotice(eduNoticeVO) > 0;
		
	}


	@Override
	public List<EduNoticeVO> getAllEduFileNotice( EduNoticeSearchVO eduNoticeSearchVO) {
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

	@Override
	public boolean doDeleteEduNotice(String eduNoticeId) {
		return educationDAO.doDeleteEduNotice(eduNoticeId) >0 ;
	}

	@Override
	public boolean doEduFileNoticeModify(EduNoticeVO eduNoticeVO) {
		EduNoticeVO newEduNoticeVO = educationDAO.getOneNotice(eduNoticeVO.getEduNoticeId());
		EduNoticeVO changeEduNoticeVO = new EduNoticeVO();
		changeEduNoticeVO.setEduNoticeId(newEduNoticeVO.getEduNoticeId());
		
		boolean isTitle = !newEduNoticeVO.getTitle().equals(eduNoticeVO.getTitle());
		boolean isContents = !newEduNoticeVO.getContents().equals(eduNoticeVO.getContents());
		boolean isNoticeType = !newEduNoticeVO.getNoticeType().contentEquals(eduNoticeVO.getNoticeType());
		
		if ( !isTitle && !isContents && !isNoticeType){
			return true;
		}
		if (isTitle){
			changeEduNoticeVO.setTitle(eduNoticeVO.getTitle());
		}
		if (isContents){
			changeEduNoticeVO.setContents(eduNoticeVO.getContents());
		}
		if (isNoticeType){
			changeEduNoticeVO.setNoticeType(eduNoticeVO.getNoticeType());
		}
		return educationDAO.doEduFileNoticeModify(changeEduNoticeVO) >0;
	}

	@Override
	public List<TeacherVO> teacherVOList() {
		return educationDAO.teacherVOList();
	}

	@Override
	public int getTotalEduReportHisotryCount(EduReportSearchVO reportSearchVO) {
		return educationDAO.getTotalEduReportHisotryCount(reportSearchVO);
	}

	@Override
	public List<EduReportVO> getAllEduReportHistory(EduReportSearchVO reportSearchVO) {
		return educationDAO.getAllEduReportHistory(reportSearchVO);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return educationDAO.getAllMemberList();
	}

	@Override
	public List<EducationVO> getJoinEducation(String memberId) {
		return educationDAO.getJoinEducation(memberId);
	}

	@Override
	public List<AttendVO> getOneMemberAttendance(String memberId) {
		return educationDAO.getOneMemberAttendance(memberId);
	}

	@Override
	public List<EducationVO> getAllStartedEducationList() {
		return educationDAO.getAllStartedEducationList();
	}

	@Override
	public List<MemberVO> getAllMemberListByEduId(String educationId) {
		return educationDAO.getAllMemberListByEduId(educationId);
	}

	@Override
	public List<TeamVO> getAllTeamList() {
		return educationDAO.getAllTeamList();
	}

	@Override
	public List<MemberVO> getAllMemberListByTeamId(String educationId, String teamId) {
		return educationDAO.getAllMemberListByTeamId(educationId, teamId);
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
	public void sendEmailInEduQna(String toEmail, String fromEmail, EduQnaVO eduQnaVO,
			EducationQNAReplyVO eduBBSReplyVO) {
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
	public String getNowDate() {
		return educationDAO.getNowDate();
	}

	@Override
	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(EducationQNAReplySearchVO searchVO) {
		return educationDAO.getAllQNAReplyListByAtcId(searchVO);
	}

	@Override
	public int getNextReplySeq() {
		return educationDAO.getNextReplySeq();
	}

	@Override
	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO) {
		educationDAO.addQNAReply(eduBBSReplyVO);
		
	}

	@Override
	public String getEmail(String memberId) {
		return educationDAO.getEmail(memberId);
	}

	@Override
	public int getNextReReplyEval() {
		return educationDAO.getNextReReplyEval();
	}

	@Override
	public boolean checkReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.checkReReplyEval(reRplyEvalVO) > 0;
	}

	@Override
	public boolean insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.insertReReplyEvalByDislike(reRplyEvalVO) > 0;
	}
	
	@Override
	public List<EducationVO> getAllEucationList(EducationSearchVO searchVO) {
		return educationDAO.getAllEucationList(searchVO);
	}

	@Override
	public EducationVO getOneEducationByAllCondition(String educationId) {
		return educationDAO.getOneEducationByAllCondition(educationId);
	}

	@Override
	public int getTotalEducationCount(EducationSearchVO searchVO) {
		return educationDAO.getTotalEducationCount(searchVO);
	}

	@Override
	public boolean doModifyStateComment(AttendVO attendVO) {
		return educationDAO.doModifyStateComment(attendVO) > 0;
	}

	@Override
	public int nextAttendSeq() {
		return educationDAO.nextAttendSeq();
	}

}
