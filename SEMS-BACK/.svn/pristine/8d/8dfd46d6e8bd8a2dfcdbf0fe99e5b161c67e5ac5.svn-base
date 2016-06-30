package com.ktds.sems.member.biz.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.sems.common.LoginStore;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

import kr.co.hucloud.utilities.SHA256Util;

public class MemberBizImpl implements MemberBiz {

	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean isExistId(String id) {
		String memberId = memberDAO.isExistId(id);
		if (memberId == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAccountLock(String id) {
		return memberDAO.isAccountLock(id);
	}

	@Override
	public boolean loginSuccess(String id) {
		return memberDAO.loginSuccess(id) > 0;
	}

	@Override
	public boolean needToChangPassword(String id) {
		String checkStr = memberDAO.needToChangPassword(id);
		if (checkStr != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean plusLoginFailCount(String id) {
		return memberDAO.plusLoginFailCount(id) > 0;
	}

	@Override
	public boolean updateAccountLock(String id) {
		return memberDAO.updateAccountLock(id) > 0;
	}

	@Override
	public boolean login(HttpSession session, MemberVO loginVO) {

		// SHA256 이용해 암호화
		String memberSalt = memberDAO.getSaltById(loginVO.getId());
		String inputPassword = loginVO.getPassword();
		String newPassword = SHA256Util.getEncrypt(inputPassword, memberSalt);
		loginVO.setPassword(newPassword);

		MemberVO memberVO = memberDAO.login(loginVO);

		if (memberVO != null) {

			// 이미 로그인 되어 있다면, 기존 로그인 세션을 종료
			LoginStore loginStore = LoginStore.getInstance();
			if (loginStore.get(loginVO.getId()) != null) {
				loginStore.logout(loginVO.getId());
			}

			session.setAttribute(Session.MEMBER, memberVO);
			session.setAttribute(Session.MEMBER_TYPE, memberVO.getMemberType());

			// 로그인 세션 유지 시간 10분
			session.setMaxInactiveInterval(10 * 60);

			// 새로운 로그인 세션 입력
			loginStore.add(loginVO.getId(), session);
		}

		return memberVO != null;
	}

	@Override
	public boolean isResign(String id) {
		
		String checkStr = memberDAO.isResign(id);
		
		if (checkStr != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean stampLoginTime(HttpSession session, HttpServletRequest request, MemberVO loginVO) {
		// 새로운 loginHistoryVO 생성해서 넣기 (1개의 object만 파라미터로 줄 수 있기 때문)

		// ID 저장해서 logout 시에 사용  
		int nextLoginHistoryId = memberDAO.nextLoginHistorySeq();
		LoginHistoryVO newLoginHistoryVO = new LoginHistoryVO();

		newLoginHistoryVO.setLgiHtrId(nextLoginHistoryId);
		newLoginHistoryVO.setId(loginVO.getId());
		newLoginHistoryVO.setLgiIp(request.getRemoteHost());

		// 세션 생성 - 로그아웃을 위한
		session.setAttribute(Session.LOGIN_HISTORY, newLoginHistoryVO);

		return memberDAO.stampLoginTime(newLoginHistoryVO) > 0;
	}
	
	@Override
	public boolean stampLogoutTime(HttpSession session) {
		LoginHistoryVO newLoginHistoryVO = new LoginHistoryVO();
		newLoginHistoryVO = (LoginHistoryVO) session.getAttribute("_LOGIN_HISTORY_");

		// 찍고 세션 없애기 
		session.removeAttribute("_LOGIN_HISTORY_");
		
		return memberDAO.stampLogoutTime(newLoginHistoryVO) > 0;
	}

	@Override
	public List<LoginHistoryVO> getAllMemberHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return memberDAO.getAllMemberHistory(loginHistorySearchVO);
	}
	
	@Override
	public List<LoginHistoryVO> getAllAdminHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return memberDAO.getAllAdminHistory(loginHistorySearchVO);
	}

	@Override
	public int getTotalAdminHistoryCount(LoginHistorySearchVO loginHistorySearchVO) {
		return memberDAO.getTotalAdminHistoryCount(loginHistorySearchVO);
	}


	@Override
	public int getTotalMemberCount(MemberSearchVO memberSearchVO) {
		return memberDAO.getTotalMemberCount(memberSearchVO);
	}

	@Override
	public List<MemberVO> getAllMemberList(MemberSearchVO searchVO) {
		return memberDAO.getAllMemberList(searchVO);
	}

	@Override
	public boolean isVerifyId(String id) {
		String idPolicy = "((?=.*[a-zA-Z])(?=.*[0-9]).{5,20})";
		Pattern pattern = Pattern.compile(idPolicy);
		Matcher matcher = pattern.matcher(id);
		boolean isVerify = matcher.matches();
		
		idPolicy = "(^[A-Za-z0-9]*$)";
		pattern = Pattern.compile(idPolicy);
		matcher = pattern.matcher(id);
		isVerify = isVerify && matcher.matches();
		
		return isVerify;
	}

	@Override
	public boolean isDuplicationId(String id) {
		
		String memberId = memberDAO.isExistId(id);

		if (memberId != null) {
			return true;
		} 
		else {
			return false;
		}
	}

	@Override
	public boolean isVerifyEmail(String email) {
		//String emailPolicy = "(^[a-z\\d][\\w\\d\\_\\.-]+@[a-z\\d][\\w\\d-]+[\\.][a-z\\.]{2,8}$)";
		String emailPolicy = "(^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$)";
		Pattern pattern = Pattern.compile(emailPolicy);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@Override
	public boolean isExistEmail(String email) {
		return memberDAO.isExistEmail(email) != null;
	}

	@Override
	public boolean isVerifyPhoneNumber(String phoneNumber) {
		String phoneNumberPolicy = "^\\d{2,3}-\\d{3,4}-\\d{4}$";		
		Pattern pattern = Pattern.compile(phoneNumberPolicy);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	@Override
	public int getTotalMemberHistoryCount(LoginHistorySearchVO loginHistorySearchVO) {
		return memberDAO.getTotalMemberHistoryCount(loginHistorySearchVO);
	}

	@Override
	public boolean massiveDeleteMember(String memberId) {
		return memberDAO.massiveDeleteMember(memberId) > 0;
	}

	@Override
	public MemberVO getMemberDetailById(String id) {
		return memberDAO.getMemberDetailById(id);
	}

	@Override
	public void addNewMember(MemberVO member) {
		memberDAO.addNewMember(member);
	}

	@Override
	public String randomValue(int size) {
		Random random = new Random();
		StringBuilder salt = null;
		String newPassword = null;
		
		char[] choices = ("abcdefghijklmnopqrstuvwxyz"
				+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
				+ "0123456789"
				+ "!@#$%^&*()").toCharArray();
		
		while (true) {
			salt = new StringBuilder(size);
			for (int i = 0; i < size; ++i) {
				salt.append(choices[random.nextInt(choices.length)]);
			}
			
			newPassword = salt.toString();
			if ( isVerifyPassword(newPassword) ) {
				break;
			}
		}
		
		return newPassword;
	}
	
	@Override
	public boolean isVerifyPassword(String password) {
		String passwordPolicy = "((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).{10,20})";
		Pattern pattern = Pattern.compile(passwordPolicy);
		Matcher matcher = pattern.matcher(password);
		boolean isVerify = matcher.matches();
		
		passwordPolicy = "(^[A-Za-z0-9!@#$%^&*()]*$)";
		pattern = Pattern.compile(passwordPolicy);
		matcher = pattern.matcher(password);
		isVerify = isVerify && matcher.matches();
		
		return isVerify;
	}

	@Override
	public boolean changePassword(MemberVO member) {
		return memberDAO.changePassword(member) > 0;
	}

	@Override
	public boolean modifyMemberTypeById(Map<String, String> modifyMemberType) {
		return memberDAO.modifyMemberTypeById(modifyMemberType) > 0;
	}

	@Override
	public boolean doWriteMemberDetailInfo(PersonalInfoReadVO personalInfoReadVO) {
		return memberDAO.doWriteMemberDetailInfo(personalInfoReadVO) > 0;
	}

	@Override
	public int getPersonalInfoIdSeq() {
		return memberDAO.getPersonalInfoIdSeq();
	}

	@Override
	public String getSysdate() {
		return memberDAO.getSysdate();
	}

	@Override
	public String getTargetMemberEmail(String targetMemberId) {
		return memberDAO.getTargetMemberEmail(targetMemberId);
	}

	@Override
	public List<MemberTypeVO> getTypeList() {
		return memberDAO.getTypeList();
	}
	
	@Override
	public MemberVO getOneMember(String memberId) {
		return memberDAO.getOneMember(memberId);
	}
	
	@Override
	public List<MemberTypeVO> getMemberTypes() {
		return memberDAO.getMemberTypes();
	}

	@Override
	public List<HighestEducationLevelVO> getHighestEducationLevels() {
		return memberDAO.getHighestEducationLevels();
	}

	@Override
	public List<GraduationTypeVO> getGraduationTypes() {
		return memberDAO.getGraduationTypes();
	}

	@Override
	public MemberVO requestMemberDetail(String memberId) {
		return memberDAO.getMemberInfo(memberId);
	}
}
