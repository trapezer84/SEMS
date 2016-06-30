package com.ktds.sems.member.biz;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

public interface MemberBiz {

	public boolean isExistId(String id);

	public boolean isAccountLock(String id);

	public boolean loginSuccess(String id);

	public boolean needToChangPassword(String id);

	public boolean plusLoginFailCount(String id);

	public boolean updateAccountLock(String id);

	public boolean login(HttpSession session, MemberVO loginVO);

	public boolean isResign(String id);

	public boolean stampLogoutTime(HttpSession session);

	public boolean stampLoginTime(HttpSession session, HttpServletRequest request, MemberVO loginVO);

	public List<LoginHistoryVO> getAllMemberHistory(LoginHistorySearchVO loginHistorySearchVO);

	public int getTotalMemberCount(MemberSearchVO memberSearchVO);

	public List<MemberVO> getAllMemberList(MemberSearchVO searchVO);

	public boolean isVerifyId(String id);

	public boolean isDuplicationId(String id);

	public boolean isVerifyPassword(String password);

	public boolean isVerifyEmail(String email);

	public boolean isExistEmail(String email);

	public boolean isVerifyPhoneNumber(String phoneNumber);

	public int getTotalMemberHistoryCount(LoginHistorySearchVO loginHistorySearchVO);

	public boolean massiveDeleteMember(String memberId);

	public MemberVO getMemberDetailById(String id);

	public void addNewMember(MemberVO member);

	public String randomValue(int i);

	public boolean changePassword(MemberVO member);

	public boolean modifyMemberTypeById(Map<String, String> modifyMemberType);

	public boolean doWriteMemberDetailInfo(PersonalInfoReadVO personalInfoReadVO);

	public int getPersonalInfoIdSeq();
	
	public String getSysdate();

	public String getTargetMemberEmail(String targetMemberId);

	public List<MemberTypeVO> getTypeList();
	
	public List<LoginHistoryVO> getAllAdminHistory(LoginHistorySearchVO loginHistorySearchVO);
	
	public int getTotalAdminHistoryCount(LoginHistorySearchVO loginHistorySearchVO);

	public MemberVO getOneMember(String memberId);
	
	public List<MemberTypeVO> getMemberTypes();
	
	public List<HighestEducationLevelVO> getHighestEducationLevels();

	public List<GraduationTypeVO> getGraduationTypes();

	public MemberVO requestMemberDetail(String memberId);

}
