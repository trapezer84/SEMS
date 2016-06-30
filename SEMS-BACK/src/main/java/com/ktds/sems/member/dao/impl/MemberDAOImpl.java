package com.ktds.sems.member.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	private Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Override
	public String isExistId(String id) {
		return getSqlSession().selectOne("MemberDAO.isExistId", id);
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
	public String needToChangPassword(String id) {
		return getSqlSession().selectOne("MemberDAO.needToChangPassword", id);
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
	public String getSaltById(String id) {
		return getSqlSession().selectOne("MemberDAO.getSaltById", id);
	}

	@Override
	public MemberVO login(MemberVO loginVO) {
		return getSqlSession().selectOne("MemberDAO.login", loginVO);
	}

	@Override
	public String isResign(String id) {
		return getSqlSession().selectOne("MemberDAO.isResign", id);
	}

	@Override
	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().update("MemberDAO.stampLogoutTime", newLoginHistoryVO);
	}

	@Override
	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().insert("MemberDAO.stampLoginTime", newLoginHistoryVO);
	}

	@Override
	public int nextLoginHistorySeq() {
		return getSqlSession().selectOne("MemberDAO.nextLoginHistorySeq");
	}

	@Override
	public List<LoginHistoryVO> getAllMemberHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectList("MemberDAO.getAllMemberHistory", loginHistorySearchVO);
	}

	@Override
	public int getTotalMemberCount(MemberSearchVO memberSearchVO) {
		return getSqlSession().selectOne("MemberDAO.getTotalMemberCount", memberSearchVO);
	}

	@Override
	public List<MemberVO> getAllMemberList(MemberSearchVO searchVO) {
		System.out.println("=="+searchVO.getSearchType());
		return getSqlSession().selectList("MemberDAO.getAllMemberList", searchVO);
	}

	@Override
	public String isExistEmail(String email) {
		return getSqlSession().selectOne("MemberDAO.isExistEmail", email);
	}

	@Override
	public int getTotalMemberHistoryCount(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectOne("MemberDAO.getTotalMemberHistoryCount", loginHistorySearchVO);
	}

	@Override
	public int massiveDeleteMember(String memberId) {
		return getSqlSession().delete("MemberDAO.massiveDeleteMember", memberId);
	}

	@Override
	public MemberVO getMemberDetailById(String id) {
		return getSqlSession().selectOne("MemberDAO.getMemberDetailById", id);
	}

	@Override
	public List<String> getHighestEducationLevelCodeNames() {
		return getSqlSession().selectList("MemberDAO.getHighestEducationLevelCodeNames");
	}

	@Override
	public List<String> getGraduationType() {
		return getSqlSession().selectList("MemberDAO.getGraduationType");
	}

	@Override
	public void addNewMember(MemberVO member) {
		getSqlSession().insert("MemberDAO.addNewMember", member);
	}

	@Override
	public String getHelCodeId(String highestEducationLevel) {
		return getSqlSession().selectOne("MemberDAO.getHelCodeId", highestEducationLevel);
	}

	@Override
	public String getGraduationTypeCodeId(String graduationType) {
		return getSqlSession().selectOne("MemberDAO.getGraduationTypeCodeId", graduationType);
	}

	@Override
	public int changePassword(MemberVO member) {
		return getSqlSession().update("MemberDAO.changePassword", member);
	}

	@Override
	public List<String> getMemberType() {
		return getSqlSession().selectList("MemberDAO.getMemberType");
	}

	@Override
	public String getMemberTypeCode(String memberType) {
		return getSqlSession().selectOne("MemberDAO.getMemberTypeCode", memberType);
	}

	@Override
	public int modifyMemberTypeById(Map<String, String> modifyMemberType) {
		return getSqlSession().update("MemberDAO.modifyMemberTypeById", modifyMemberType);
	}

	@Override
	public List<String> getMemberTypeCodeNameList() {
		return getSqlSession().selectList("MemberDAO.getMemberTypeCodeNameList");
	}

	@Override
	public String getMemberTypeCodeId(String memberType) {
		return getSqlSession().selectOne("MemberDAO.getMemberTypeCodeId", memberType);
	}

	@Override
	public int doWriteMemberDetailInfo(PersonalInfoReadVO personalInfoReadVO) {
		return getSqlSession().insert("MemberDAO.doWriteMemberDetailInfo", personalInfoReadVO);
	}

	@Override
	public int getPersonalInfoIdSeq() {
		return getSqlSession().selectOne("MemberDAO.getPersonalInfoIdSeq");
	}

	@Override
	public String getSysdate() {
		return getSqlSession().selectOne("MemberDAO.getSysdate");
	}

	@Override
	public String getTargetMemberEmail(String targetMemberId) {
		return getSqlSession().selectOne("MemberDAO.getTargetMemberEmail", targetMemberId);
	}

	@Override
	public List<MemberTypeVO> getTypeList() {
		return getSqlSession().selectList("MemberDAO.getTypeList");
	}
	
	@Override
	public List<LoginHistoryVO> getAllAdminHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectList("MemberDAO.getAllAdminHistory", loginHistorySearchVO);
	}

	@Override
	public int getTotalAdminHistoryCount(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectOne("MemberDAO.getTotalAdminHistoryCount", loginHistorySearchVO);
	}
	@Override
	public MemberVO getOneMember(String memberId) {
		return getSqlSession().selectOne("MemberDAO.getOneMember", memberId);
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
	public void deleteMemberDetailInfo(String memberId) {
		getSqlSession().delete("MemberDAO.deleteMemberDetailInfo", memberId);
	}

	@Override
	public MemberVO getMemberInfo(String memberId) {
		return getSqlSession().selectOne("MemberDAO.getMemberInfo", memberId);
	}

}
