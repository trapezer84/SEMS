package com.ktds.sems.education.dao;

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

public interface EducationDAO {

	public 	int nextEduSeq();
	public String nowDate();
	public int insertNewEducation(EducationVO educationVO);
	public List<CostVO> costCodeList();
	public List<EducationTypeVO> typeCodeList();
	public List<CategoryVO> categoryCodeList();
	
	public EducationVO getOneEducation(String educationId);
	public int modifyNewEducation(EducationVO changedEducationVO);
	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO);
	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);
	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);
	public List<EducationHistoryVO> getJCEduHistoryHistory(EducationHistorySearchVO eduHistorySearchVO);
	public int applyJoinEducationByMemberId(String educationHistoryId, String changeState);
	public int cancelJoinEducationByMemberId(String educationHistoryId, String changeState);
	public String getStateByEducationHistroyId(String educationHistoryId);
	public int changeEducationApplyState(String educationHistoryId);
	
	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO);
	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO);
	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO);
	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO);
	public int getTotalEduFileCount(EduFileSearchVO eduFileSearchVO);
	public List<EduFileVO> getAllEduFile(EduFileSearchVO eduFileSearchVO);
	
	public int nextEduNoticeSeq();
	public int insertNewEduFileNotice(EduNoticeVO eduNoticeVO);
	public String doActionDeleteBeforeCheck(MemberVO memerVO);
	
	public void doActionDelete(String educationId);
	
	public List<EducationVO> attendedLectureUserList(String educationId);
	
	public MemberVO emailNoticeForUser(String memberId);
	public List<TeacherVO> teacherVOList();
	public int getTotalEduReportHisotryCount(EduReportSearchVO reportSearchVO);
	public List<EduReportVO> getAllEduReportHistory(EduReportSearchVO reportSearchVO);
	public List<MemberVO> getAllMemberList();
	public List<EducationVO> getJoinEducation(String memberId);
	public List<AttendVO> getOneMemberAttendance(String memberId);
	public List<EducationVO> getAllStartedEducationList();
	public List<MemberVO> getAllMemberListByEduId(String educationId);
	public List<EduNoticeVO> getAllEduFileNotice(EduNoticeSearchVO eduNoticeSearchVO);
	public EduNoticeVO getOneNotice(String eduNoticeId);
	public void addhits(String eduNoticeId);
	public int doDeleteEduNotice(String eduNoticeId);
	public int doEduFileNoticeModify(EduNoticeVO changeEduNoticeVO);
	public List<TeamVO> getAllTeamList();
	public List<MemberVO> getAllMemberListByTeamId(String educationId, String teamId);
	public int confirmMemberOfEdu(String educationId, String memberId);
	public int insertEduQna(EduQnaVO eduQnaVO);
	public int getNextEqbSeq();
	public EduQnaVO detailOfEduQna(String eduQnaId);
	public int addHitsToEduQna(String eduQnaId);
	public int addQnaEduReplyLike(String replyId);
	public int addQnaEduReplyDisLike(String replyId);
	public int getTotalQnaEduReplyCount(String eduQnaId);
	public String getNowDate();
	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(EducationQNAReplySearchVO searchVO);
	public int getNextReplySeq();
	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO);
	public String getEmail(String memberId);
	public int getNextReReplyEval();
	public int checkReReplyEval(ReRplyEvalVO reRplyEvalVO);
	public int insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO);
	public String getOneEduNoticeId();
	public String getOneEducationId();
	public List<EducationVO> getAllEucationList(EducationSearchVO searchVO);
	public EducationVO getOneEducationByAllCondition(String educationId);
	public int getTotalEducationCount(EducationSearchVO searchVO);
	public int doModifyStateComment(AttendVO attendVO);
	public int nextAttendSeq();
}
