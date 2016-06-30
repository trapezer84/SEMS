package com.ktds.sems.education.biz;

import java.util.List;

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

public interface EducationBiz {

	public boolean writeNewEducation(EducationVO educationVO );

	public EducationVO getOneEducation(String educationId);

	public boolean modifyNewEducation(EducationVO educationVO);
	
	public List<CostVO> costCodeList();

	public List<EducationTypeVO> typeCodeList();

	public List<CategoryVO> categoryCodeList();

	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO);

	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);

	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);

	public List<EducationHistoryVO> getJCEducationHistory(EducationHistorySearchVO eduHistorySearchVO);
	
	public boolean applyJoinEducationByMemberId(String educationHistoryId, String changeState);

	public boolean cancelJoinEducationByMemberId(String educationHistoryId, String changeState);

	public String getStateByEducationHistroyId(String educationHistoryId);

	public void sendEmailRejection(String educationHistoryId, String memberId, String description, String email, String name);

	public int changeEducationApplyState(String educationHistoryId);

	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO);

	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO);

	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO);

	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO);

	public int getTotalEduFileCount(EduFileSearchVO eduFileSearchVO);

	public List<EduFileVO> getAllEduFile(EduFileSearchVO eduFileSearchVO);

	public boolean writeEduFileNoticeAction(EduNoticeVO eduNoticeVO);


	public boolean doActionDeleteBeforeCheck(MemberVO memberVO);

	public void doActionDelete(String educationId);

	public void emailNoticeForUser(String educationId);
	
	public List<TeacherVO> teacherVOList();

	public int getTotalEduReportHisotryCount(EduReportSearchVO reportSearchVO);

	public List<EduReportVO> getAllEduReportHistory(EduReportSearchVO reportSearchVO);
	
	public List<EduNoticeVO> getAllEduFileNotice( EduNoticeSearchVO eduNoticeSearchVO);

	public EduNoticeVO getOneNotice(String eduNoticeId);

	public void addhits(String eduNoticeId);

	public boolean doDeleteEduNotice(String eduNoticeId);

	public boolean doEduFileNoticeModify(EduNoticeVO eduNoticeVO);
	
	public List<MemberVO> getAllMemberList();

	public List<EducationVO> getJoinEducation(String memberId);

	public List<AttendVO> getOneMemberAttendance(String memberId);

	public List<EducationVO> getAllStartedEducationList();

	public List<MemberVO> getAllMemberListByEduId(String educationId);

	public List<TeamVO> getAllTeamList();

	public List<MemberVO> getAllMemberListByTeamId(String educationId, String teamId);

	public boolean confirmMemberOfEdu(String educationId, String memberId);

	public boolean insertEduQna(EduQnaVO eduQnaVO);

	public int getNextEqbSeq();

	public EduQnaVO detailOfEduQna(String eduQnaId);
	
	public boolean addHitsToEduQna(String eduQnaId);

	public boolean addQnaEduReplyLike(String replyId);

	public boolean addQnaEduReplyDisLike(String replyId);

	public void sendEmailInEduQna(String toEmail, String fromEmail, EduQnaVO eduQnaVO, EducationQNAReplyVO eduBBSReplyVO);

	public int getTotalQnaEduReplyCount(String eduQnaId);

	public String getNowDate();

	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(EducationQNAReplySearchVO searchVO);

	public int getNextReplySeq();

	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO);

	public String getEmail(String memberId);

	public int getNextReReplyEval();

	public boolean checkReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public boolean insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO);
	
	public List<EducationVO> getAllEucationList(EducationSearchVO searchVO);
	
	public EducationVO getOneEducationByAllCondition(String educationId);

	public int getTotalEducationCount(EducationSearchVO searchVO);

	public boolean doModifyStateComment(AttendVO attendVO);

	public int nextAttendSeq();
}
