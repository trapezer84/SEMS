package com.ktds.sems.member.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.EducationCostVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationStateVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	// 준호
	@Override
	public int addNewMember(MemberVO member) {
		return getSqlSession().insert("MemberDAO.addNewMember", member);
	}
	
	@Override
	public int loginHistory(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().insert("MemberDAO.loginHistory", loginHistoryVO);
	}

	@Override
	public int logoutHistory(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().insert("MemberDAO.logoutHistory", loginHistoryVO);
	}

	@Override
	public String getSaltById(String id) {
		return getSqlSession().selectOne("MemberDAO.getSaltById", id);
	}

	@Override
	public MemberVO login(MemberVO loginVO) {
		return getSqlSession().selectOne("MemberDAO.login", loginVO);
	}

	@Override
	public boolean isAccountLock(String id) {
		return ("Y").equals(getSqlSession().selectOne("MemberDAO.isAccountLock", id));
	}

	@Override
	public int loginSuccess(String id) {
		return getSqlSession().update("MemberDAO.loginSuccess", id);
	}

	@Override
	public int plusLoginFailCount(String id) {
		return getSqlSession().update("MemberDAO.plusLoginFailCount", id);
	}

	@Override
	public int updateAccountLock(String id) {
		return getSqlSession().update("MemberDAO.updateAccountLock", id);
	}

	@Override
	public MemberVO getOneMember(String id) {
		return getSqlSession().selectOne("MemberDAO.getOneMember", id);
	}

	@Override
	public void resetModifyLockAndCount(String id) {
		getSqlSession().update("MemberDAO.resetModifyLockAndCount", id);
	}

	@Override
	public void plusModifyFailCount(String id) {
		getSqlSession().update("MemberDAO.plusModifyFailCount", id);
	}

	@Override
	public void updateModifyAccountLock(String id) {
		getSqlSession().update("MemberDAO.updateModifyAccountLock", id);
	}

	@Override
	public int isModifyAccountLock(String id) {
		return getSqlSession().selectOne("MemberDAO.isModifyAccountLock", id);
	}

	@Override
	public String getNowDate() {
		return getSqlSession().selectOne("MemberDAO.getNowDate");
	}

	@Override
	public String needToChangPassword(String id) {
		return getSqlSession().selectOne("MemberDAO.needToChangPassword", id);
	}

	/**
	 * @author 이기연
	 */
	@Override
	public List<LoginHistoryVO> saveLoginHistoryAsExcel(String memberId) {
		return getSqlSession().selectList("MemberDAO.saveLoginHistoryAsExcel", memberId);
	}

	@Override
	public int getTotalLoginHistoryCount(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectOne("MemberDAO.getTotalLoginHistoryCount", loginHistorySearchVO);
	}

	@Override
	public List<EducationVO> getEduListByMember(MemberVO memberVO) {
		return getSqlSession().selectList("MemberDAO.getEduListByMember", memberVO);
	}

	@Override
	public void insertAttendByMember(AttendVO attendVO) {
		getSqlSession().insert("MemberDAO.insertAttendByMember", attendVO);
	}

	@Override
	public String getLastDate(Map<String,String> eduIdAndMemberId) {
		return (String) (getSqlSession().selectOne("MemberDAO.getLastDate", eduIdAndMemberId ) == null ? "" : this.getSqlSession().selectOne("MemberDAO.getLastDate", eduIdAndMemberId));
	}

	/**
	 * @author 이기연
	 */
	@Override
	public void modifyMemberInfo(MemberVO member) {
		getSqlSession().update("MemberDAO.modifyMemberInfo", member);
	}


	// 준호
	@Override
	public String isExistId(String id) {
		return getSqlSession().selectOne("MemberDAO.isExistId", id);
	}
	
	@Override
	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().insert("MemberDAO.stampLoginTime", newLoginHistoryVO);
	}

	// 준호
	@Override
	public String isExistEmail(String email) {
		return getSqlSession().selectOne("MemberDAO.isExistEmail", email);
	}

	/**
	 * @author 이기연
	 */
	@Override
	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().update("MemberDAO.stampLogoutTime", newLoginHistoryVO);
	}
	
	/**
	 * @author 이기연
	 */
	@Override
	public int stampLogoutTimeByMemberId(String memberId) {
		return getSqlSession().update("MemberDAO.stampLogoutTimeByMemberId", memberId);
	}

	@Override
	public int nextLoginHistorySeq() {
		return getSqlSession().selectOne("MemberDAO.nextLoginHistorySeq");
	}

	@Override
	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectList("MemberDAO.getAllLoginHistory", loginHistorySearchVO);
	}

	@Override
	public String getPasswordById(String id) {
		return getSqlSession().selectOne("MemberDAO.getPasswordById", id);
	}

	@Override
	public String isResign(String id) {
		return getSqlSession().selectOne("MemberDAO.isResign", id);
	}


	@Override
	public void insertUuidForResign(MemberVO member) {
		getSqlSession().update("MemberDAO.insertUuidForResign", member);
	}

	@Override
	public int doDeleteMember(String id) {
		return getSqlSession().update("MemberDAO.doDeleteMember", id);
	}

	@Override
	public int changePassword(MemberVO memberVO) {
		return getSqlSession().update("MemberDAO.changePassword", memberVO);
	}

	@Override
	public List<MenuManageVO> getMenuCategoryList() {
		return getSqlSession().selectList("MemberDAO.getMenuCategoryList");
	}

	@Override
	public int isTeacher(String id) {
		return getSqlSession().selectOne("MemberDAO.isTeacher", id);
	}
	
	@Override
	public int delectJunitTestMember(String id) {
		return getSqlSession().delete("MemberDAO.delectJunitTestMember", id);
	}

	@Override
	public int getTotalEducationHistoryCountById(EducationHistorySearchVO educationHistorySearchVO) {
		return getSqlSession().selectOne("MemberDAO.getTotalEducationHistoryCountById", educationHistorySearchVO);
	}

	@Override
	public List<EducationHistoryVO> getAllEducationHistoryListByIdWithPaging(EducationHistorySearchVO educationHistorySearchVO) {
		
		return getSqlSession().selectList("MemberDAO.getAllEducationHistoryListByIdWithPaging", educationHistorySearchVO);
	}

	@Override
	public String doMatchHistoryWithMember(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().selectOne("MemberDAO.doMatchHistoryWithMember", loginHistoryVO);
	}

	@Override
	public void doRequestIpHistory(int lgiHtrId) {
		getSqlSession().update("MemberDAO.doRequestIpHistory", lgiHtrId);
	}

	@Override
	public int doCheckIp(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().selectOne("MemberDAO.doCheckIp",loginHistoryVO);
	}

	@Override
	public LoginHistoryVO checkIpInfo(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().selectOne("MemberDAO.checkIpInfo", loginHistoryVO);
	}

	@Override
	public List<EducationHistoryVO> getAllEducationHistoryListById(String id) {
		return getSqlSession().selectList("MemberDAO.getAllEducationHistoryListById", id);
	}

	@Override
	public void ipCheckCountUpdate(LoginHistoryVO loginHistoryVO) {
		getSqlSession().update("MemberDAO.ipCheckCountUpdate", loginHistoryVO);
	}

	@Override
	public List<EducationHistoryVO> getJoinEducationList(String memberId) {
		return getSqlSession().selectList("MemberDAO.getJoinEducationList", memberId);
	}

	@Override
	public int isAdmin(String id) {
		return getSqlSession().selectOne("MemberDAO.isAdmin", id);
	}

	@Override
	public List<EducationStateVO> getStatList() {
		return getSqlSession().selectList("MemberDAO.getStatList");
	}

	@Override
	public List<EducationCostVO> getCostList() {
		return getSqlSession().selectList("MemberDAO.getCostList");
	}

	@Override
	public EducationHistoryVO getOneEducationByIdAndEducationId(String educationId, String id) {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().selectOne("MemberDAO.getOneEducationByIdAndEducationId", paramMap);
	}

	@Override
	public int dropCourseApply(EducationHistoryVO educationHistory) {
		return getSqlSession().update("MemberDAO.dropCourseApply", educationHistory);
	}
	
	@Override
	public int getCourseCountById(String id) {
		return getSqlSession().selectOne("MemberDAO.getCourseCountById", id);
	}

	@Override
	public List<GraduationTypeVO> getGraduationTypes() {
		return getSqlSession().selectList("MemberDAO.getGraduationTypes");
	}

	@Override
	public List<HighestEducationLevelVO> getHighestEducationLevels() {
		return getSqlSession().selectList("MemberDAO.getHighestEducationLevels");
	}

	@Override
	public List<MemberTypeVO> getMemberTypes() {
		return getSqlSession().selectList("MemberDAO.getMemberTypes");
	}

	@Override
	public String getSelectMemberTypeCodeName(String memberType) {
		return getSqlSession().selectOne("MemberDAO.getSelectMemberTypeCodeName", memberType);
	}

	@Override
	public List<LoginHistoryVO> getLoginHistoryListByMemberId(String id) {
		return getSqlSession().selectList("MemberDAO.getLoginHistoryListByMemberId", id);
	}

	@Override
	public List<EducationHistoryVO> getEducationHistoryListByMemberId(String id) {
		return getSqlSession().selectList("MemberDAO.getEducationHistoryListByMemberId", id);
	}

	@Override
	public List<QNAVO> getQnaListByMemberId(String id) {
		return getSqlSession().selectList("MemberDAO.getQnaListByMemberId", id);
	}

	@Override
	public int checkRegistState(String id) {
		return getSqlSession().selectOne("MemberDAO.checkRegistState", id);
	}

	@Override
	public int checkValidationCourseAccess(String memberId) {
		return getSqlSession().selectOne("MemberDAO.checkValidationCourseAccess", memberId);
	}

	@Override
	public List<EducationVO> getCourseList(String memberId) {
		return getSqlSession().selectList("MemberDAO.getCourseList", memberId);
	}

	@Override
	public List<AttendVO> getAllAttendHistoryListById(Map<String,String> eduIdAndMemberId) {
		return getSqlSession().selectList("MemberDAO.getAllAttendHistoryListById", eduIdAndMemberId);
	}

	@Override
	public int isVerifyLeave(String id) {
		return getSqlSession().selectOne("MemberDAO.isVerifyLeave", id);
	}

	@Override
	public int updateLeaveClass(AttendVO attendVO) {
		String memberId = attendVO.getMemberId();
		return getSqlSession().update("MemberDAO.updateLeaveClass", memberId);
	}

	@Override
	public AttendVO getNowClassInfoById(String memberId) {
		return getSqlSession().selectOne("MemberDAO.getNowClassInfoById", memberId);
	}

	@Override
	public EducationVO getOneEducationInfo(Map<String, String> eduIdAndMemberId) {
		return getSqlSession().selectOne("MemberDAO.getOneEducationInfo", eduIdAndMemberId);
	}

	@Override
	public List<ReportReplyVO> getReportReplyListByMemberId(String id) {
		return getSqlSession().selectList("MemberDAO.getReportReplyListByMemberId", id);
	}

   @Override
   public void deleteJunitTestStampLoginTime(int lgiHtrId) {
      getSqlSession().delete("MemberDAO.deleteJunitTestStampLoginTime", lgiHtrId);
   }

   @Override
   public int currentLoginHistorySeq() {
      return getSqlSession().selectOne("MemberDAO.currentLoginHistorySeq");
   }
	
	@Override
	public List<EducationVO> getPreCourseList(EducationSearchVO educationSearchVO) {
		return getSqlSession().selectList("MemberDAO.getPreCourseList", educationSearchVO);
	}
}
