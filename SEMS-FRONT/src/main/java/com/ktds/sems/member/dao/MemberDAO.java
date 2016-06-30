package com.ktds.sems.member.dao;

import java.util.List;
import java.util.Map;

import com.ktds.sems.education.vo.EduClassVO;
import com.ktds.sems.education.vo.EducationCostVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationStateVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

public interface MemberDAO {

	public int addNewMember(MemberVO member);

	public int loginHistory(LoginHistoryVO loginHistoryVO);

	public int logoutHistory(LoginHistoryVO loginHistoryVO);

	public String getSaltById(String id);

	public MemberVO login(MemberVO loginVO);

	public boolean isAccountLock(String id);

	public int loginSuccess(String id);

	public int plusLoginFailCount(String id);

	public int updateAccountLock(String id);

	public MemberVO getOneMember(String id);

	public void resetModifyLockAndCount(String id);

	public void plusModifyFailCount(String id);

	public void updateModifyAccountLock(String id);

	public int isModifyAccountLock(String id);

	public String getNowDate();

	public String needToChangPassword(String id);

	public List<LoginHistoryVO> saveLoginHistoryAsExcel(String memberId);

	public int getTotalLoginHistoryCount(LoginHistorySearchVO loginHistorySearchVO);

	public List<EducationVO> getEduListByMember(MemberVO loginVO);

	public void insertAttendByMember(AttendVO attendVO);

	public String getLastDate(Map<String,String> eduIdAndMemberId);

	public void modifyMemberInfo(MemberVO member);

	public String isExistId(String id);

	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO);

	public String isExistEmail(String email);

	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO);
	
	public int stampLogoutTimeByMemberId(String memberId);

	public int nextLoginHistorySeq();

	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO);

	public String getPasswordById(String id);

	public String isResign(String id);

	public void insertUuidForResign(MemberVO member);

	public int doDeleteMember(String id);

	public int changePassword(MemberVO memberVO);

	public List<MenuManageVO> getMenuCategoryList();

	public int isTeacher(String id);
	
	public int delectJunitTestMember(String id);

	public int getTotalEducationHistoryCountById(EducationHistorySearchVO educationHistorySearchVO);

	public List<EducationHistoryVO> getAllEducationHistoryListByIdWithPaging(EducationHistorySearchVO educationHistorySearchVO);

	public String doMatchHistoryWithMember(LoginHistoryVO loginHistoryVO);

	public void doRequestIpHistory(int lgiHtrId);

	public int doCheckIp(LoginHistoryVO loginHistoryVO);

	public LoginHistoryVO checkIpInfo(LoginHistoryVO loginHistoryVO);

	public List<EducationHistoryVO> getAllEducationHistoryListById(String id);

	public void ipCheckCountUpdate(LoginHistoryVO loginHistoryVO);

	public List<EducationHistoryVO> getJoinEducationList(String memberId);

	public int isAdmin(String id);

	public List<EducationStateVO> getStatList();

	public List<EducationCostVO> getCostList();

	public List<EducationVO> getCourseList(String memberId);

	public EducationHistoryVO getOneEducationByIdAndEducationId(String educationId, String id);

	public int dropCourseApply(EducationHistoryVO educationHistory);
	
	public int getCourseCountById(String id);

	public List<GraduationTypeVO> getGraduationTypes();

	public List<HighestEducationLevelVO> getHighestEducationLevels();

	public List<MemberTypeVO> getMemberTypes();

	public String getSelectMemberTypeCodeName(String memberType);

	public List<LoginHistoryVO> getLoginHistoryListByMemberId(String id);

	public List<EducationHistoryVO> getEducationHistoryListByMemberId(String id);

	public List<QNAVO> getQnaListByMemberId(String id);

	public int checkRegistState(String id);

	public int checkValidationCourseAccess(String memberId);

	public List<AttendVO> getAllAttendHistoryListById(Map<String, String> eduIdAndMemberId);

	public int isVerifyLeave(String id);

	public int updateLeaveClass(AttendVO attendVO);

	public AttendVO getNowClassInfoById(String memberId);

	public EducationVO getOneEducationInfo(Map<String, String> eduIdAndMemberId);

	public List<ReportReplyVO> getReportReplyListByMemberId(String id);
	
	public void deleteJunitTestStampLoginTime(int lgiHtrId);
	
	public int currentLoginHistorySeq();

	public List<EducationVO> getPreCourseList(EducationSearchVO educationSearchVO);
	

}
