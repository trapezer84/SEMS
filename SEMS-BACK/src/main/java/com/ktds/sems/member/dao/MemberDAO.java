package com.ktds.sems.member.dao;

import java.util.List;
import java.util.Map;

import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

public interface MemberDAO {

	public String isExistId(String id);

	public boolean isAccountLock(String id);

	public int loginSuccess(String id);

	public String needToChangPassword(String id);

	public int plusLoginFailCount(String id);

	public int updateAccountLock(String id);

	public String getSaltById(String id);

	public MemberVO login(MemberVO loginVO);

	public String isResign(String id);

	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO);

	public int nextLoginHistorySeq();

	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO);

	public List<LoginHistoryVO> getAllMemberHistory(LoginHistorySearchVO loginHistorySearchVO);

	public int getTotalMemberCount(MemberSearchVO memberSearchVO);

	public List<MemberVO> getAllMemberList(MemberSearchVO searchVO);

	public String isExistEmail(String email);

	public int getTotalMemberHistoryCount(LoginHistorySearchVO loginHistorySearchVO);

	public int massiveDeleteMember(String memberId);
	
	public MemberVO getMemberDetailById(String id);

	public List<String> getHighestEducationLevelCodeNames();

	public List<String> getGraduationType();

	public void addNewMember(MemberVO member);

	public String getHelCodeId(String highestEducationLevel);

	public String getGraduationTypeCodeId(String graduationType);

	public int changePassword(MemberVO member);

	public List<String> getMemberType();
	
	public List<String> getMemberTypeCodeNameList();

	public String getMemberTypeCodeId(String memberType);

	public int doWriteMemberDetailInfo(PersonalInfoReadVO personalInfoReadVO);
	
	public int getPersonalInfoIdSeq();
	
	public String getSysdate();

	public String getTargetMemberEmail(String targetMemberId);

	public List<MemberTypeVO> getTypeList();
	
	public List<LoginHistoryVO> getAllAdminHistory(LoginHistorySearchVO loginHistorySearchVO);
	
	public int getTotalAdminHistoryCount(LoginHistorySearchVO loginHistorySearchVO);

	public MemberVO getOneMember(String memberId);

	public String getMemberTypeCode(String memberType);

	public int modifyMemberTypeById(Map<String, String> modifyMemberType);
	
	public List<GraduationTypeVO> getGraduationTypes();

	public List<HighestEducationLevelVO> getHighestEducationLevels();

	public List<MemberTypeVO> getMemberTypes();

	public void deleteMemberDetailInfo(String memberId);

	public MemberVO getMemberInfo(String memberId);

}
