package com.ktds.sems.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.Session;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.education.vo.EducationCostVO;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationStateVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.AjaxUtil;
import kr.co.hucloud.utilities.web.Paging;

public class MemberServiceImpl implements MemberService {

	private MemberBiz memberBiz;
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	@Override
	public String addNewMember(MemberVO member, String repeatPassword, Errors errors, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		String memberType = member.getMemberType();
		if (memberType == null) {
			return "NO";
		}
		
		List<HighestEducationLevelVO> highestEducationLevelList = new ArrayList<HighestEducationLevelVO>(); 
		List<GraduationTypeVO> graduationTypeList = new ArrayList<GraduationTypeVO>();

		boolean isNotError = true;
		isNotError = isAllValidValue(member, repeatPassword, view);
		if (errors.hasErrors() || !isNotError) {
			highestEducationLevelList = memberBiz.getHighestEducationLevels();
			graduationTypeList = memberBiz.getGraduationTypes();
			return "NO";
		} else if (isNotError) {
			setSaltAndPassword(member);
			if (memberBiz.addNewMember(member)) {
				return "OK";
			} else {
				return "NO";
			}
		} else {
			throw new RuntimeException("잘 못 된 입력 : 회원 종류");
		}
	}

	/**
	 * 준호 > 기연 SM
	 * @param member
	 * @param repeatPassword
	 * @param view
	 * @return
	 */
	private boolean isAllValidValue(MemberVO member, String repeatPassword, ModelAndView view) {

		boolean isNotError = true;
		int errorCount = 0;
		String memberType = member.getMemberType();

		if (repeatPassword == null) {
			view.addObject("isEmptyRepeatPassword", "true");
			errorCount++;
		}

		if (repeatPassword != null && member.getPassword() != null && !member.getPassword().equals(repeatPassword)) {
			view.addObject("isEqualsPassword", "true");
			errorCount++;
		}

		if (member.getId() != null && !memberBiz.isVerifyId(member.getId())) {
			errorCount++;
		}

		if (member.getName() != null && !memberBiz.checkValidationByName(member.getName())) {
			errorCount++;
		}

		if (member.getPassword() != null && !memberBiz.isVerifyPassword(member.getPassword())) {
			errorCount++;
		}

		if (member.getPhoneNumber() != null && !memberBiz.isVerifyPhoneNumber(member.getPhoneNumber())) {
			errorCount++;
		}
		
		if (memberType.equals("MBR")) {
			
			if (member.getGraduationType() == null) {
				view.addObject("isEmptyGraduationType", "true");
				errorCount++;
			}

			if (member.getHighestEducationLevel() == null) {
				view.addObject("isEmptyHighestEducationLevel", "true");
				errorCount++;
			}

			String majorName = member.getMajorName();
			if (majorName == null || majorName.trim().equals("") || !memberBiz.checkValidationByMajorName(majorName)) {
				view.addObject("isEmptyMajorName", "true");
				errorCount++;
			}

			String universityName = member.getUniversityName();
			if (universityName == null || universityName.trim().equals("") || !memberBiz.checkValidationByUniversityName(universityName)) {
				view.addObject("isEmptyUniversityName", "true");
				errorCount++;
			}
			view.setViewName("member/registerStudent");
		} else if (memberType.equals("TR")) {
			view.setViewName("member/registerTeacher");
		}

		if (errorCount > 0) {
			isNotError = false;
		}

		return isNotError;
	}

	private void setSaltAndPassword(MemberVO member) {
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);

		String newPassword = SHA256Util.getEncrypt(member.getPassword(), salt);
		member.setPassword(newPassword);
	}

	@Override
	public void checkValidationById(String id, HttpServletResponse response) {
		String message = "OK";
		boolean isVerifyId = memberBiz.isVerifyId(id);
		if (!isVerifyId) {
			message = "NO";
			AjaxUtil.sendResponse(response, message);
			return;
		}

		boolean isExistId = memberBiz.isExistId(id);
		if (isExistId) {
			message = "EXIST";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByPassword(String password, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyPassword = memberBiz.isVerifyPassword(password);
		if (isVerifyPassword) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByRepeatPassword(String password, String repeatPassword, HttpServletResponse response) {
		String message = "NO";
		boolean isEquals = password.equals(repeatPassword);
		if (isEquals) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByPhoneNumber(String phoneNumber, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyPhoneNumber = memberBiz.isVerifyPhoneNumber(phoneNumber);
		if (isVerifyPhoneNumber) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	/**
	 * 준호 
	 * 수정시 적길 바람
	 */
	@Override
	public void checkValidationByEmail(String email, HttpServletResponse response) {
		String message = "OK";
		
		boolean isVerifyEmail = memberBiz.isVerifyEmail(email);
		if (!isVerifyEmail) {
			message = "NO";
			AjaxUtil.sendResponse(response, message);
			return;
		}

		boolean isExistEmail = memberBiz.isExistEmail(email);
		if (isExistEmail) {
			message = "EXIST";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public String login(MemberVO loginVO, Errors errors, HttpSession session, HttpServletRequest request) {

		// 아이디 있는지 확인
		if (!memberBiz.isExistId(loginVO.getId())) {
			return "NO";
		}

		// 탈퇴한 회원인지 확인
		if (memberBiz.isResign(loginVO.getId())) {
			return "RSN";
		}

		// 잠긴 계정은 로그인 못하도록 막는다.
		if (memberBiz.isAccountLock(loginVO.getId())) {
			return "OVER";
		}
		
		//회원정보수정 3번실패 시 잠금됬기 때문에 로그인 못하도록 막는다.
		if (memberBiz.isModifyAccountLock(loginVO.getId())) {
			return "OVER";
		}

		// 로그인 30일 경과 계정
		if (memberBiz.needToChangPassword(loginVO.getId())) {
			return "CNGPW";
		}

		boolean isLoginSuccess = memberBiz.login(session, loginVO, request);
		
		// 로그인 횟수 제한 방어코드 작성
		if (isLoginSuccess) {
			/*
			 * 1. LOGIN_FAIL_COUNT를 0으로 초기화한다. 2. IS_ACCOUNT_LOCK을 'N'으로 초기화 한다.
			 * 3. LATEST_LOGIN_DATE를 현재시간으로 수정한다.
			 */
			// Token 값 생성 및 등록 코드 작성
			if (memberBiz.loginSuccess(loginVO.getId())) {
				/*
				 * 로그인한 회원이 글을 작성하는 write.jsp 에 아래 코드를 추가해야함!
				 * <input type="hidden" name="csrfToken" value="${sessionScope._CSRF_TOKEN_}" />
				 */
				String csrfToken = UUID.randomUUID().toString();
				session.setAttribute(Session.CSRF_TOKEN, csrfToken);

				// 출석 체크
				memberBiz.attendCheck(loginVO);

				// 로그인 내역 남기기
				memberBiz.stampLoginTime(session, request, loginVO);

				return "OK";

			} else {
				return "NO";
			}

		} else {

			/*
			 * 1. LOGIN_FAIL_COUNT를 1 증가 시킨다.
			 */
			if (!memberBiz.plusLoginFailCount(loginVO.getId())) {
				return "NO";
			}
			/*
			 * 1. LOGIN_FAIL_COUNT를 5 이상이면 IS_ACCOUNT_LOCK을 'Y'로 수정한다.
			 */
			if (!memberBiz.updateAccountLock(loginVO.getId())) {
				return "NO";
			}
			/*
			 * 1. IS_ACCOUNT_LOCK이 'Y'라면 브라우저에게 'OVER'라고 보낸다. 'OVER'를 응답으로 받은
			 * 브라우저는 "로그인이 지속 실패하여, 계정이 잠겼습니다. 운영자에게 문의하세요!" 를 출력한다.
			 */
			boolean isLock = memberBiz.isAccountLock(loginVO.getId());

			if (isLock) {
				return "OVER";
			}
			return "NO";
		}
	}

	@Override
	public ModelAndView modifySuccess(String id) {

		ModelAndView view = new ModelAndView();

		MemberVO member = memberBiz.getOneMember(id);

		// 졸업 구분 값들을 보낸다. 유저가 회원가입시 선택한 졸업구분을 보낸다.
		List<GraduationTypeVO> graduationTypeList = memberBiz.getGraduationTypes();
		String selectedGraduationTypeCodeId = member.getGraduationType();
		
		// 최종학력 구분 값들을 보낸다. 유저가 회원가입시 선택한 최종학력을 보낸다.
		List<HighestEducationLevelVO> highestEducationLevelList = memberBiz.getHighestEducationLevels();
		String selectedHighestEducationLevelCodeId = member.getHighestEducationLevel();
		
		String selectedMemberTypeCodeId = member.getMemberType();
		String selectedMemberTypeCodeName = memberBiz.getSelectMemberTypeCodeName(member.getMemberType());

		// 강사인지 아닌지 체크
		boolean isTeacher = memberBiz.isTeacher(id);
		// 관리자인지 아닌지 체크
		boolean isAdmin = memberBiz.isAdmin(id);
	

		view.addObject("member", member);
		view.addObject("graduationTypeList", graduationTypeList);
		view.addObject("highestEducationLevelList", highestEducationLevelList);
		view.addObject("selectedGraduationTypeCodeId", selectedGraduationTypeCodeId);
		view.addObject("selectedHighestEducationLevelCodeId", selectedHighestEducationLevelCodeId);
		view.addObject("selectedMemberTypeCodeId", selectedMemberTypeCodeId);
		view.addObject("selectedMemberTypeCodeName", selectedMemberTypeCodeName);
		
		if (!isTeacher) {
			view.addObject("isTeacher", "F");
		} else {
			view.addObject("isTeacher", "T");
		}
		if (!isAdmin) {
			view.addObject("isAdmin", "F");
		} else {
			view.addObject("isAdmin", "T");
		}
		
		view.setViewName("member/modifyMyInfo");
		return view;

	}

	@Override
	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors, String graduationType, String helCodeName) {

		int changeCount = 0;
		MemberVO changeMember = new MemberVO();
		String inputPassword = member.getPassword();

		MemberVO originMember = memberBiz.getOneMember(member.getId());
		
		if (inputPassword != "") {
			changeCount++;

			String salt = SHA256Util.generateSalt();
			String newPassword = SHA256Util.getEncrypt(inputPassword, salt);

			changeMember.setPassword(newPassword);
			changeMember.setSalt(salt);
		}

		if (!originMember.getName().equals(member.getName())) {
			changeCount++;
			changeMember.setName(member.getName());
		}

		if (!originMember.getEmail().equals(member.getEmail())) {
			changeCount++;
			changeMember.setEmail(member.getEmail());
		}
		
		if (!graduationType.equals("") || !helCodeName.equals("") ) {
			
			String selectGraduationTypeCodeId = graduationType;
			String selecthelCodeId = helCodeName;
		
			if (!originMember.getHighestEducationLevel().equals(selecthelCodeId)) {
				changeCount++;
				changeMember.setHighestEducationLevel(selecthelCodeId);
			}
	
			if (!originMember.getGraduationType().equals(selectGraduationTypeCodeId)) {
				changeCount++;
				changeMember.setGraduationType(selectGraduationTypeCodeId);
			}
			
		}
		
		if (!originMember.getPhoneNumber().equals(member.getPhoneNumber())) {
			changeCount++;
			changeMember.setPhoneNumber(member.getPhoneNumber());
		}

		if (!originMember.getBirthDate().equals(member.getBirthDate())) {
			changeCount++;
			changeMember.setBirthDate(member.getBirthDate());
		}
		if (changeCount == 0) {
		} else {
			changeMember.setId(member.getId());
			// 회원정보 수정
			memberBiz.modifyMemberInfo(changeMember);
		}

		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/member/myPage");

		return view;
	}

	/**
	 * @author 이기연
	 */
	@Override
	public ModelAndView saveLoginHistoryAsExcel(HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		String memberId = sessionMember.getId();

		// 로그인된 멤버의 로그인 내역만 저장시키기 위해서 보낸다.
		// boolean 값으로 받아와 excel 변환 여부를 체크한다.
		memberBiz.saveLoginHistoryAsExcel(memberId);
		view.setViewName("redirect:/member/loginHistory");
		return view;
	}

	@Override
	public void plusModifyFailCount(String sessionId) {
		memberBiz.plusModifyFailCount(sessionId);
	}

	@Override
	public void updateModifyAccountLock(String sessionId) {
		memberBiz.updateModifyAccountLock(sessionId);
	}

	@Override
	public void resetModifyLockAndCount(String sessionId) {
		memberBiz.resetModifyLockAndCount(sessionId);
	}

	@Override
	public boolean isModifyAccountLock(String sessionId) {
		return memberBiz.isModifyAccountLock(sessionId);
	}

	/**
	 * @author 206-025 김동규 > 이기연 (수정)
	 * 
	 */
	@Override
	public ModelAndView viewLoginHistoryPage(LoginHistorySearchVO loginHistorySearchVO, int pageNo,
			HttpSession session, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		
		if ( loginHistorySearchVO.getBeginTime() != null && loginHistorySearchVO.getBeginDate() != null ) {
			if ( loginHistorySearchVO.getBeginTime() != "" && loginHistorySearchVO.getBeginDate() !="" ) {
				loginHistorySearchVO.setBeginDateTime(loginHistorySearchVO.getBeginDate() + " " +  loginHistorySearchVO.getBeginTime());
				loginHistorySearchVO.setCloseDateTime(loginHistorySearchVO.getCloseDate() + " " + loginHistorySearchVO.getCloseTime());
				logger.info("??");
			}
		}
		logger.info("startTime" + loginHistorySearchVO.getBeginTime());
		logger.info("closeTime" + loginHistorySearchVO.getCloseTime());
		
		int totalLoginHistoryCount = 0;
		List<LoginHistoryVO> loginHistoryList = null;
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		loginHistorySearchVO.setId(memberVO.getId());
		
		totalLoginHistoryCount = memberBiz.getTotalLoginHistoryCount(loginHistorySearchVO);
		logger.info("toalLoginHistoryCount" + totalLoginHistoryCount);
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(totalLoginHistoryCount);
		paging.setPageNumber(pageNo + "");

		loginHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		loginHistorySearchVO.setEndIndex(paging.getEndArticleNumber());

		loginHistoryList = memberBiz.getAllLoginHistory(loginHistorySearchVO);
		
		LoginHistoryListVO loginHistoryListVO = new LoginHistoryListVO();
		loginHistoryListVO.setLoginHistoryList(loginHistoryList);
		loginHistoryListVO.setPaging(paging);
		
		view.setViewName("member/loginHistory");
		view.addObject("loginHistoryListVO", loginHistoryListVO);
		view.addObject("loginHistorySearchVO", loginHistorySearchVO);
		
		return view;
	}
	
	@Override
	public void sendBlockAccountEmail(String id) {

		SendMail sendMail = new SendMail();
		MailVO mail = new MailVO();
		MemberVO member = memberBiz.getOneMember(id);

		mail.setFromId("testForSendEmailKtds@gmail.com");
		mail.setFromPassword("123qwe!@#qwe");
		mail.setSubject("[SEMS] 계정 차단 알림");
		mail.setText("비밀번호 3회 이상 오류로, 계정이 차단되었습니다. 문의사항은 관리자에게 연락하세요.");
		mail.setToId(member.getEmail());

		// TODO 이메일 테스트 시 주석 처리 해제
		// sendMail.sendMailToCustomer(mail);
	}

	@Override
	public String getSaltById(String id) {
		return memberBiz.getSaltById(id);
	}

	@Override
	public String getPasswordById(String id) {
		return memberBiz.getPasswordById(id);
	}

	@Override
	public void logout(HttpSession session) {
		// 세션 없애기
		session.removeAttribute("_MEMBER_");

		// 로그아웃 stamp 찍기 위해서..
		memberBiz.stampLogoutTime(session);
	}

	@Override
	public ModelAndView changePassword(MemberVO memberVO) {

		ModelAndView view = new ModelAndView();

		String originSalt = memberBiz.getSaltById(memberVO.getId());
		String inputPassword = SHA256Util.getEncrypt(memberVO.getPrevPassword(), originSalt);

		String originPassword = memberBiz.getPasswordById(memberVO.getId());

		if (inputPassword.equals(originPassword)) {

			// 입력한 현재 비밀번호가 맞은 경우
			String newSalt = SHA256Util.generateSalt();
			memberVO.setSalt(newSalt);

			String newPassword = SHA256Util.getEncrypt(memberVO.getPassword(), newSalt);
			memberVO.setPassword(newPassword);

			memberBiz.changePassword(memberVO);

			view.setViewName("redirect:/");

			return view;

		} else {

			// 입력한 현재 비밀번호가 틀렸을 경우
			view.setViewName("redirect:/changePassword/" + memberVO.getId());
			return view;
		}
	}

	@Override
	public ModelAndView registerStudent() {
		ModelAndView view = new ModelAndView();
		
		List<HighestEducationLevelVO> highestEducationLevelList = memberBiz.getHighestEducationLevels();
		List<GraduationTypeVO> graduationTypeList = memberBiz.getGraduationTypes();
		view.setViewName("member/registerStudent");
		view.addObject("graduationTypeList", graduationTypeList);
		view.addObject("highestEducationLevelList", highestEducationLevelList);

		return view;
	}
	
	@Override
	public String registerTeacher() {
		return "member/registerTeacher";
	}

	@Override
	public String insertUuidForResign(HttpSession session) {

		MemberVO memeber = (MemberVO) session.getAttribute("_MEMBER_");
		String uuid = UUID.randomUUID().toString();

		memeber.setUuid(uuid);

		memberBiz.insertUuidForResign(memeber);

		return uuid;
	}

	@Override
	public ModelAndView sendEmailForResign(HttpSession session, String uuid) {
		ModelAndView view = new ModelAndView();

		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");

		member = memberBiz.getOneMember(member.getId());
		memberBiz.sendEmailForResign(member.getEmail(), member.getId(), uuid);
		// memberBiz.sendEmailForResign("abonno@naver.com", uuid);

		view.setViewName("member/myPage");

		return view;
	}

	@Override
	public ModelAndView loginForResign(String resignCode, String id) {
		ModelAndView view = new ModelAndView();

		view.addObject("resignCode", resignCode);
		view.addObject("id", id);

		view.setViewName("member/loginForResign");

		return view;
	}

	@Override
	public String doResign(MemberVO loginVO, Errors errors, String resignCode) {

		boolean isSuccess = memberBiz.doResign(loginVO);
		MemberVO memberVO = memberBiz.getOneMember(loginVO.getId());

		if (isSuccess) {
			if (memberVO.getUuid() != null && memberVO.getUuid().equals(resignCode)) {

				boolean isDeleteSuccess = memberBiz.doDeleteMember(memberVO.getId());
				if (isDeleteSuccess) {
					return "OK";
				}
				return "NO";
			} else {
				return "FAIL";
			}
		} else {
			return "NO";
		}
	}

	@Override
	public String doCheckPrevPassword(String id, String prevPassword, HttpServletRequest request) {

		String originSalt = memberBiz.getSaltById(id);
		String inputPassword = SHA256Util.getEncrypt(prevPassword, originSalt);

		String originPassword = memberBiz.getPasswordById(id);

		if (!inputPassword.equals(originPassword)) {
			return "NO";
		}

		return "OK";
	}

	//TODO
	@Override
	public ModelAndView viewMyPageMenu(HttpSession session) {
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_"); 
		
		ModelAndView view = new ModelAndView();
		List<MenuManageVO> menuList = memberBiz.getMenuCategoryList();
		List<LoginHistoryVO> loginHistoryList = memberBiz.getLoginHistoryListByMemberId(member.getId());
		List<EducationHistoryVO> educationHistoryList = memberBiz.getEducationHistoryListByMemberId(member.getId());
		List<QNAVO> qnaList = memberBiz.getQnaListByMemberId(member.getId());
		List<ReportReplyVO> reportReplyList = memberBiz.getReportReplyListByMemberId(member.getId());
		
		//퇴근 가능 여부
		String message = "NO";
		boolean isVerifyLeave = memberBiz.isVerifyLeave(member.getId());
		if (isVerifyLeave) {
			message = "OK";
		}
		
		view.setViewName("member/myPage");
		view.addObject("menuList", menuList);
		view.addObject("loginHistoryList", loginHistoryList);
		view.addObject("educationHistoryList", educationHistoryList);
		view.addObject("qnaList", qnaList);
		view.addObject("reportReplyList", reportReplyList);
		view.addObject("leaveVerified", message);

		return view;
	}

	@Override
	public ModelAndView getAllEducationHistoryListByIdWithPaging(EducationHistorySearchVO educationHistorySearchVO, int pageNo, HttpSession session) {
		
		EducationHistoryListVO educationHistoryListVO = new EducationHistoryListVO();
		Paging paging = new Paging();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		paging.setPageNumber(pageNo + "");
		
		educationHistoryListVO.setPaging(paging);
		
		educationHistorySearchVO.setPageNo(pageNo);
		educationHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		educationHistorySearchVO.setEndIndex(paging.getEndArticleNumber());
		educationHistorySearchVO.setMemberId(memberVO.getId());

		
		int totalEducationHistoryCountById = memberBiz.getTotalEducationHistoryCountById(educationHistorySearchVO);
		paging.setTotalArticleCount(totalEducationHistoryCountById);

		List<EducationHistoryVO> educationHistoryList = memberBiz.getAllEducationHistoryListByIdWithPaging(educationHistorySearchVO);
		List<EducationHistoryVO> joinEducationList = memberBiz.getJoinEducationList(educationHistorySearchVO.getMemberId());
		List<EducationStateVO> statList = memberBiz.getStatList();
		List<EducationCostVO> costList = memberBiz.getCostList();
		educationHistoryListVO.setEducationHistoryList(educationHistoryList);
		educationHistoryListVO.setCostList(costList);
		educationHistoryListVO.setStatList(statList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/educationHistory");
		view.addObject("educationHistoryListVO", educationHistoryListVO);
		view.addObject("educationHistorySearchVO", educationHistorySearchVO);
		view.addObject("joinEducationList", joinEducationList);
		
		return view;
	}
	
	@Override
	public ModelAndView getAllAttendClassListById(HttpSession session) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);

		/* 회원별 강의 */
		List<EducationVO> attendClassList = memberBiz.getAllAttendClassListById(memberVO);
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("member/attendHistory");
		view.addObject("attendClassList", attendClassList);
		
		return view;
	}
	
	

	@Override
	public String registerPolicy(HttpSession session) {
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( sessionMember != null ) {
			return "member/registErrorPage";
		}
		else {
			return "member/registerPolicy";
		}
	}

	@Override
	public ModelAndView loginHistoryInit() {
		ModelAndView view = new ModelAndView();
		
		view.addObject("beginDate", null);
		view.addObject("closeDate", null);
		view.setViewName("redirect:/member/loginHistory");
		
		return view;
	}

	@Override
	public ModelAndView doRequestIpHistory(int lgiHtrId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute(Session.MEMBER);
					
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId(sessionMember.getId());
		loginHistoryVO.setLgiHtrId(lgiHtrId);
		
		boolean memberCheck = memberBiz.doMatchHistoryWithMember(loginHistoryVO);
		
		if(memberCheck) {
			memberBiz.doRequestIpHistory(lgiHtrId);
			view.setViewName("redirect:/member/loginHistory");
			return view;
		}else {
			view.setViewName("redirect:/");
			return view;
		}
	}

	@Override
	public ModelAndView doCheckIp(int lgiHtrId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute(Session.MEMBER);
		
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId(sessionMember.getId());
		loginHistoryVO.setLgiHtrId(lgiHtrId);
		
		boolean checkIp = memberBiz.doCheckIp(loginHistoryVO);
		boolean memberCheck = memberBiz.doMatchHistoryWithMember(loginHistoryVO);
		
		if(checkIp && memberCheck) {
			LoginHistoryVO loginHistory = memberBiz.checkIpInfo(loginHistoryVO);
			view.setViewName("member/checkIP");
			view.addObject("loginHistory", loginHistory);
			return view;
		}else {
			return new ModelAndView("redirect:/");
		}
	}

	@Override
	public String eduationHistoryExportExcel(HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		boolean isSuccess = memberBiz.eduationHistoryExportExcel(memberVO.getId());
		
		if(isSuccess) {
			return "redirect:/member/myPage/educationHistory";
		} else {
			return "redirect:/member/myPage";
		}
	}


	/**
	 * SM 기연
	 */
	@Override
	public void checkValidationByName(String name, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyName = memberBiz.checkValidationByName(name);
		if (isVerifyName) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	/**
	 * SM 기연
	 */
	@Override
	public void checkValidationByUniversityName(String universityName, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyName = memberBiz.checkValidationByUniversityName(universityName);
		if (isVerifyName) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByMajorName(String majorName, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyName = memberBiz.checkValidationByMajorName(majorName);
		if (isVerifyName) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}
	
	@Override
	public ModelAndView writeResignCourse(String educationId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		EducationHistoryVO educationHistory = memberBiz.getOneEducationByIdAndEducationId(educationId, memberVO.getId());
		view.addObject("educationHistory", educationHistory);
		view.setViewName("myPage/resignCourseWrite");
		
		return view;
	}

	@Override
	public String dropCourseApply(String educationId, HttpSession session, String courseDropReason) {

		if (!this.isValidCourseDropReason(courseDropReason)) {
			return "NO";
		}
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		EducationHistoryVO educationHistory = new EducationHistoryVO();
		
		educationHistory.setCmnt(courseDropReason);
		educationHistory.setMemberId(memberVO.getId());
		educationHistory.setEducationId(educationId);
		
		boolean dropCourseApply = memberBiz.dropCourseApply(educationHistory);
		if(dropCourseApply){
			return "OK";
		}
		
		return "OK";
	}
	
	/**
	 * 강의 수강 포기 사유 특수문자 체크
	 * 이기연(SM)
	 * @param courseDropReason
	 * @return
	 */
	private boolean isValidCourseDropReason(String courseDropReason) {
		return memberBiz.isValidCourseDropReason(courseDropReason);
	}
	
	
	/**
	 * 모든 수강신청 내역을 paging 하기 위한 total number 받아오기
	 * 구본호 > 이기연(SM)
	 */
	@Override
	public ModelAndView getCourseList(HttpSession session, int pageNo, EducationSearchVO educationSearchVO) {

		EducationListVO preEducationListVO = new EducationListVO();
		Paging paging = new Paging();
		preEducationListVO.setPaging(paging);

		paging.setPageNumber(pageNo + "");
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		int getPreCourseCountById = memberBiz.getCourseCountById(memberVO.getId());
		paging.setTotalArticleCount(getPreCourseCountById);

		educationSearchVO.setPageNo(pageNo);
		educationSearchVO.setStartIndex(paging.getStartArticleNumber());
		educationSearchVO.setEndIndex(paging.getEndArticleNumber());
		educationSearchVO.setMemberId(memberVO.getId());
		
		List<EducationVO> myEducationList = memberBiz.getCourseList(memberVO.getId());
		List<EducationVO> myPreEducationList = memberBiz.getPreCourseList(educationSearchVO);
		preEducationListVO.setEducationList(myPreEducationList);

		ModelAndView view = new ModelAndView();
		
		// SM 이기연 수정 
		if ( myEducationList.size() >= 0 ) {
			view.setViewName("myPage/myEduCourseInfo");
			view.addObject("educationListVO", myEducationList);
			view.addObject("educationSearchVO", educationSearchVO);
			view.addObject("preEducationListVO", preEducationListVO);
		} 
		else {
			List<MenuManageVO> menuList = memberBiz.getMenuCategoryList();

			view.setViewName("member/myPage");
			view.addObject("menuList", menuList);
		}
		return view;
	}

	@Override
	public void checkRegistState(String id, HttpServletResponse response) {
		String message = "OK";
		
		boolean checkRegistState = memberBiz.checkRegistState(id);
		if (!checkRegistState) {
			message = "NO";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationCourseAccess(HttpServletResponse response, HttpSession session) {
		String message = "OK";

		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		
		boolean checkValidationCourseAccess = memberBiz.checkValidationCourseAccess(memberId);
		if (checkValidationCourseAccess) {
			message = "NO";
		}
		AjaxUtil.sendResponse(response, message);
	}

	@Override
	public ModelAndView getMyEduCourseInfo(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/myEduCourseInfo");
		return view;
	}

	@Override
	public void updateLeaveClass(HttpServletResponse response, HttpSession session) {
		
		String message = "NO";
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		
		boolean updateLeaveClassSuccess = memberBiz.updateLeaveClass(memberId);
		if(updateLeaveClassSuccess) { 
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		
		//TODO 중복 클릭 처리
		//TODO 출결이력 업데이트
		
	}

	@Override
	public ModelAndView getAllAttendHistory(HttpSession session, String educationId) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		Map<String, String> eduIdAndMemberId = new HashMap<String, String>();

		eduIdAndMemberId.put("educationId", educationId);
		eduIdAndMemberId.put("memberId", memberVO.getId());
		EducationVO eduInfo = memberBiz.getOneEducationInfo(eduIdAndMemberId);
		
		Map<String, List<String>> attendList = memberBiz.getAllAttendHistory(memberVO, educationId);
		
		ModelAndView view = new ModelAndView();
		view.addObject("eduInfo", eduInfo);
		view.addObject("attendList", attendList);
		view.setViewName("member/attendHistoryDetail");
		
		return view;
	}
	
	
}
