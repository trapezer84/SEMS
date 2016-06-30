package com.ktds.sems.member.biz;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

public interface MemberBiz {

	public boolean addNewMember(MemberVO member);

	public boolean isAccountLock(String id);

	public boolean login(HttpSession session, MemberVO memberVO, HttpServletRequest request);

	public boolean loginSuccess(String id);

	public boolean plusLoginFailCount(String id);

	public boolean updateAccountLock(String id);

	public MemberVO getOneMember(String id);

	public void resetModifyLockAndCount(String id);

	public void plusModifyFailCount(String sessionId);

	public void updateModifyAccountLock(String sessionId);

	public boolean isModifyAccountLock(String sessionId);

	public String getNowDate();

	public boolean needToChangPassword(String id);

	public void saveLoginHistoryAsExcel(String memberId);

	public boolean stampLoginTime(HttpSession session, HttpServletRequest request, MemberVO loginVO);

	public boolean stampLogoutTime(HttpSession session);

	public int getTotalLoginHistoryCount(LoginHistorySearchVO loginHistorySearchVO);

	public boolean isVerifyId(String id);
	
	public boolean isVerifyPassword(String password);

	public boolean isExistEmail(String email);
	
	public boolean isExistId(String id);

	public void attendCheck(MemberVO loginVO);

	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO);

	public void modifyMemberInfo(MemberVO member);

	public String getSaltById(String id);

	public String getPasswordById(String id);

	public boolean isResign(String id);

	public boolean changePassword(MemberVO memberVO);

	public void insertUuidForResign(MemberVO member);

	public void sendEmailForResign(String email, String id, String uuid);

	public boolean doDeleteMember(String id);

	public boolean doResign(MemberVO loginVO);

	public boolean isVerifyPhoneNumber(String phoneNumber);

	boolean isVerifyEmail(String email);

	public List<MenuManageVO> getMenuCategoryList();

	public boolean isTeacher(String id);

	public int delectJunitTestMember(String id);

	public int getTotalEducationHistoryCountById(EducationHistorySearchVO educationHistorySearchVO);

	public List<EducationHistoryVO> getAllEducationHistoryListByIdWithPaging(EducationHistorySearchVO educationHistorySearchVO);

	public boolean doMatchHistoryWithMember(LoginHistoryVO loginHistoryVO);

	public void doRequestIpHistory(int lgiHtrId);

	public boolean doCheckIp(LoginHistoryVO loginHistoryVO);

	public LoginHistoryVO checkIpInfo(LoginHistoryVO loginHistoryVO);

	public boolean eduationHistoryExportExcel(String id);

	public List<EducationHistoryVO> getJoinEducationList(String memberId);

	public boolean checkValidationByName(String name);

	public boolean checkValidationByUniversityName(String universityName);

	public boolean checkValidationByMajorName(String majorName);

	public boolean isAdmin(String id);

	public List<EducationStateVO> getStatList();

	public List<EducationCostVO> getCostList();
	
	public EducationHistoryVO getOneEducationByIdAndEducationId(String educationId, String id);

	public boolean dropCourseApply(EducationHistoryVO educationHistory);
	
	public List<EducationVO> getCourseList(String memberId);

	public int getCourseCountById(String id);
	
	public List<MemberTypeVO> getMemberTypes();
	
	public List<HighestEducationLevelVO> getHighestEducationLevels();

	public List<GraduationTypeVO> getGraduationTypes();

	public String getSelectMemberTypeCodeName(String memberType);

	public List<EducationVO> getAllAttendClassListById(MemberVO loginVO);

	public List<EducationVO> getEduListByMember(MemberVO memberVO);

	public List<LoginHistoryVO> getLoginHistoryListByMemberId(String id);

	public List<EducationHistoryVO> getEducationHistoryListByMemberId(String id);

	public List<QNAVO> getQnaListByMemberId(String id);

	public boolean checkRegistState(String id);

	public boolean isValidCourseDropReason(String courseDropReason);

	public boolean checkValidationCourseAccess(String memberId);

	public boolean isVerifyLeave(String id);

	public boolean updateLeaveClass(String memberId);

	public Map<String, List<String>> getAllAttendHistory(MemberVO memberVO, String educationId);

	public List<ReportReplyVO> getReportReplyListByMemberId(String id);

	public List<EducationVO> getPreCourseList(EducationSearchVO educationSearchVO);

	public EducationVO getOneEducationInfo(Map<String, String> eduIdAndMemberId);

}
