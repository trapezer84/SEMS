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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.Session;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberListVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.AjaxUtil;
import kr.co.hucloud.utilities.web.Paging;

public class MemberServiceImpl implements MemberService{

	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberBiz memberBiz;

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	/* 로그인 */
	@Override
	public String login(MemberVO loginVO, Errors errors, HttpSession session, HttpServletRequest request) {

		// 아이디 있는지 확인
		if ( memberBiz.isExistId(loginVO.getId()) ) {
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

		boolean isLoginSuccess = memberBiz.login(session, loginVO);
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
				
				// 로그인 내역 남기기 
				memberBiz.stampLoginTime(session, request, loginVO);
				
				if(memberBiz.needToChangPassword(loginVO.getId())) {
					return "CNGPW";
				} else {
					
					String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
					if(memberType != null && !memberType.equals("ADM")) {
						return "NOADM";
					}
					return "OK";
				}
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
	public void logout(HttpSession session) {
		// 세션 없애기
		session.removeAttribute("_MEMBER_");
		
		//로그아웃 stamp 찍기 위해서.. 
		memberBiz.stampLogoutTime(session);
	}
	
	@Override
	public ModelAndView getAllAdminHistory(LoginHistorySearchVO loginHistorySearchVO, int pageNo) {
		
		LoginHistoryListVO loginHistoryListVO = new LoginHistoryListVO();
		Paging paging = new Paging(20, 20);
		
		loginHistoryListVO.setPaging(paging);
		int totalHistoryCount = memberBiz.getTotalAdminHistoryCount(loginHistorySearchVO);
		//logger.info("totalHistoryCount"+totalHistoryCount);
		//System.out.println("카운트" + totalHistoryCount);
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalHistoryCount);
		
		loginHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		loginHistorySearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<LoginHistoryVO> loginHistory = memberBiz.getAllAdminHistory(loginHistorySearchVO);
		loginHistoryListVO.setLoginHistoryList(loginHistory);
		
		//logger.info("loginHistorySize"+loginHistory.size());
		ModelAndView view = new ModelAndView();
		view.setViewName("member/adminHistory");

		view.addObject("loginHistoryListVO", loginHistoryListVO);
		view.addObject("loginHistorySearchVO", loginHistorySearchVO);
		return view;
	}

	@Override
	public ModelAndView getAllMemberHistory(LoginHistorySearchVO loginHistorySearchVO, int pageNo, HttpSession session) {
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		
		LoginHistoryListVO loginHistoryListVO = new LoginHistoryListVO();
		Paging paging = new Paging(20,20);
		
		loginHistoryListVO.setPaging(paging);
		int totalHistoryCount = memberBiz.getTotalMemberHistoryCount(loginHistorySearchVO);
		
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalHistoryCount);
		
		loginHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		loginHistorySearchVO.setEndIndex(paging.getEndArticleNumber());

		List<LoginHistoryVO> loginHistory = memberBiz.getAllMemberHistory(loginHistorySearchVO);
		loginHistoryListVO.setLoginHistoryList(loginHistory);
		
		List<MemberTypeVO> typeList = memberBiz.getTypeList();
		
		ModelAndView view = new ModelAndView();
		
		if( memberType.equals("ADM") || memberType == null ){
			view.setViewName("member/memberHistory");
			view.addObject("typeList", typeList);
			view.addObject("loginHistoryListVO", loginHistoryListVO);
			view.addObject("loginHistorySearchVO", loginHistorySearchVO);
		}
		else{
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}

	@Override
	public ModelAndView getAllMemberList(MemberSearchVO memberSearchVO, int pageNo) {

		MemberListVO memberListVO = new MemberListVO();
		Paging paging = new Paging();
		
		memberListVO.setPaging(paging);
		int totalMemberCount = memberBiz.getTotalMemberCount(memberSearchVO);
		
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalMemberCount);
		
		memberSearchVO.setStartIndex(paging.getStartArticleNumber());
		memberSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<MemberVO> memberList = memberBiz.getAllMemberList(memberSearchVO);
		memberListVO.setMemberList(memberList);
		
		List<MemberTypeVO> memberTypeList = memberBiz.getMemberTypes();
		List<String> searchTypeList = searchTypeList();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/memberListPage");
		view.addObject("memberListVO", memberListVO);
		view.addObject("memberSearchVO", memberSearchVO);
		logger.info("memberSearchVO.searchType" + memberSearchVO.getSearchType());
		logger.info("memberSearchVO.getOptionSelected" + memberSearchVO.getOptionSelected());
		view.addObject("memberTypeList", memberTypeList);
		view.addObject("searchTypeList", searchTypeList);
		return view;
	}
	
	private List<String> searchTypeList() {
		List<String> searchTypeList = new ArrayList<String>();
		searchTypeList.add("Y");
		searchTypeList.add("N");
		return searchTypeList;
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

		boolean isExistId = memberBiz.isDuplicationId(id);
		if (isExistId) {
			message = "EXIST";
		}
		System.out.println(isExistId);
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
	public void checkValidationByPhoneNumber(String phoneNumber, HttpServletResponse response) {
		String message = "NO";
		logger.info("Test"+phoneNumber);
		boolean isVerifyPhoneNumber = memberBiz.isVerifyPhoneNumber(phoneNumber);
		logger.info("TestVE"+isVerifyPhoneNumber);
		if (isVerifyPhoneNumber) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public String massiveDeleteMember(String[] deleteMemberIds) {

		for(String memberId : deleteMemberIds){
			memberBiz.massiveDeleteMember(memberId);
		}
		
		return "redirect:/memberManage/memberList";
	}


	public ModelAndView getMemberDetailById(String id) {
		ModelAndView view = new ModelAndView();
		
		if ( id == null || id.length() == 0 ) {
			view.setViewName("redirect:/memberManage/memberList");
		}
		else {
			MemberVO memberVO = memberBiz.getMemberDetailById(id);
			
			if (memberVO == null) {
				view.setViewName("redirect:/memberManage/memberList");
			}
			else {
				view.addObject("member", memberVO);
				view.setViewName("member/memberDetailPage");
			}
		}
		
		return view;
	}

	@Override
	public ModelAndView addNewMember(MemberVO member, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		boolean isNotError = true;
		String selectMemberTypeCodeId = null;

		String memberType = member.getMemberType();
		isNotError = isAllValidValue(member, view);
		selectMemberTypeCodeId = member.getMemberType();

		if (sessionMember == null) {
			throw new RuntimeException("유효한 접근이 아닙니다.");
		} else if (errors.hasErrors() || !isNotError) {
			
			List<HighestEducationLevelVO> highestEducationLevelList = memberBiz.getHighestEducationLevels();
			List<GraduationTypeVO> graduationTypeList = memberBiz.getGraduationTypes();

			view.addObject("graduationTypeList", graduationTypeList);
			view.addObject("highestEducationLevelList", highestEducationLevelList);

			view.addObject("member", member);
		} else if (isNotError) {
			if (memberType.equals("MBR") || memberType.equals("STD")) {
				String graduationType = member.getGraduationType();
				String highestEducationLevel = member.getHighestEducationLevel();
				
				member.setGraduationType(graduationType);
				member.setHighestEducationLevel(highestEducationLevel);
				member.setUniversityName(member.getUniversityName());
				member.setMajorName(member.getMajorName());
			}
			
			member.setMemberType(selectMemberTypeCodeId);
			setSaltAndPassword(member);
			memberBiz.addNewMember(member);
			view.setViewName("member/memberManagePage");
		} else {
			throw new RuntimeException("잘 못 된 입력 : 회원 종류");
		}

		return view;
	}
	
	private boolean isAllValidValue(MemberVO member, ModelAndView view) {

		boolean isNotError = true;
		String memberType = member.getMemberType();

		if (member.getId() != null) {
			isNotError = memberBiz.isVerifyId(member.getId());
		}

		if (member.getPassword() != null) {
			isNotError = memberBiz.isVerifyPassword(member.getPassword());
		}
		
		if ( member.getPhoneNumber() != null ) {
			isNotError = memberBiz.isVerifyPhoneNumber(member.getPhoneNumber());
		}
		
		if ( memberType == null) {
			view.setViewName("redirect:/");
			isNotError = false;
		} else if (memberType.equals("수강생") || memberType.equals("일반회원")) {
			if (member.getGraduationType() == null) {
				view.addObject("isEmptyGraduationType", "true");
				isNotError = false;
			}

			if (member.getHighestEducationLevel() == null) {
				view.addObject("isEmptyHighestEducationLevel", "true");
				isNotError = false;
			}

			if (member.getMajorName() == null || member.getMajorName().equals("")) {
				view.addObject("isEmptyMajorName", "true");
				isNotError = false;
			}

			if (member.getUniversityName() == null || member.getUniversityName().equals("")) {
				view.addObject("isEmptyUniversityName", "true");
				isNotError = false;
			}
			view.setViewName("member/registerStudent");
		} else if (memberType.equals("TR")) {
			view.setViewName("member/registerTeacher");
		}

		view.addObject("member", member);

		return isNotError;
	}
	
	private void setSaltAndPassword(MemberVO member) {
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);

		String newPassword = SHA256Util.getEncrypt(member.getPassword(), salt);
		member.setPassword(newPassword);
	}

	@Override
	public ModelAndView changeMemberPassword(String id) {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/changeMemberPassword");
		view.addObject("id", id);
		return view;
	}

	@Override
	public void sendAndChangePassword(String memberId, HttpServletResponse response) {
		String message = "NO";
		
		SendMail sendMail = new SendMail();
		MailVO mailVO = new MailVO();		
		MemberVO member = memberBiz.getMemberDetailById(memberId);
		
		String password = memberBiz.randomValue(10);
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);

		String newPassword = SHA256Util.getEncrypt(password, salt);
		member.setPassword(newPassword);

		boolean isChangedPassword = memberBiz.changePassword(member);
		if ( isChangedPassword ) {
			mailVO.setFromId("testForSendEmailKtds@gmail.com");
			mailVO.setFromPassword("123qwe!@#qwe");
			mailVO.setSubject("임시 비밀 번호입니다.");
			mailVO.setText("<html><body>임시 비밀번호는 : " + password + "<a href='http://localhost/sems/'>로그인</a></body></html>");
			mailVO.setToId(member.getEmail());
			
			// TODO 이메일 테스트
			// sendMail.sendMailToCustomer(mailVO);
			
			message = "OK";
		}

		AjaxUtil.sendResponse(response, message);
	}

	@Override
	public ModelAndView modifyMemberType(String memberType, List<String> memberIds) {
		ModelAndView view = new ModelAndView();
		if ( memberType != null && memberIds != null ) {
			Map<String, String> modifyMemberType = new HashMap<String, String> ();
			modifyMemberType.put("memberTypeId", memberType);
			
			for (String memberId : memberIds ) {
				modifyMemberType.put("memberId", memberId);
				memberBiz.modifyMemberTypeById (modifyMemberType);
			}
		}
		view.setViewName("redirect:/memberManage/memberList");
		
		return view;
	}

	@Override
	public ModelAndView memberDeleteById(String id) {
		ModelAndView view = new ModelAndView();
		
		if ( id == null || id.length() == 0) {
			view.setViewName("redirect:/memberManage/memberList");
		}
		else {
			boolean isdeleteMember = memberBiz.massiveDeleteMember(id);
			
			if (!isdeleteMember) {
				view.addObject("massage", "삭제 실패!");
				view.setViewName("member/memberDetailPage");
			}
			else {
				view.addObject("massage", "삭제 성공!");
				view.setViewName("redirect:/memberManage/memberList");
			}
		}
		return view;
	}

	@Override
	public ModelAndView requestMemberDetail(String memberId) {
		
		ModelAndView view = new ModelAndView();
		
		MemberVO member = memberBiz.requestMemberDetail(memberId);
		logger.info("member" + member.getId());
		logger.info("memberType" + member.getMemberType());
		view.addObject("member", member);
		view.setViewName("member/requestMemberDetailPage");
		return view;
	}

	@Override
	public ModelAndView doWriteMemberDetailInfo(PersonalInfoReadVO personalInfoReadVO, Errors errors) {
		
		ModelAndView view = new ModelAndView();
		
		// 로그인한 멤버와 보려는 개인정보가 일치 할 시 
		if(personalInfoReadVO.getTargetMemberId().equals( personalInfoReadVO.getId() )){
			
		}else{//로그인한 멤버도 아니고 다른 관리자의 정보를 보려할 시 detail 페이지로 다시 이동
			logger.info("멤버타입"+personalInfoReadVO.getMemberType());
			if( personalInfoReadVO.getMemberType().equals("ADM") ){ // 관리자
				String targetId = personalInfoReadVO.getTargetMemberId();
				personalInfoReadVO.setId(targetId);
				
				view.addObject("member", personalInfoReadVO);
				view.setViewName("member/requestMemberDetailPage");
				return view;
			}
			
		}
		
		if ( errors.hasErrors() ) {
			view.addObject("id", personalInfoReadVO.getTargetMemberId());
			view.setViewName("member/requestMemberDetailPage");
		}
		else {
			personalInfoReadVO.setId(personalInfoReadVO());
			boolean isWrite = memberBiz.doWriteMemberDetailInfo(personalInfoReadVO);
			if ( isWrite ) {
			// TODO  EMAIL TEST	
			//	sendMail(personalInfoReadVO);
				view = getMemberDetailById(personalInfoReadVO.getTargetMemberId());
			}
		}
		
		return view;
	}
	
	private String personalInfoReadVO() {
		String resultId = null;
		String sysdate = memberBiz.getSysdate();
		int personalInfoSeq = memberBiz.getPersonalInfoIdSeq();
		
		resultId = "PI-"+sysdate+"-"+lpad(personalInfoSeq, 6);
		return resultId;
	}
	
	private String lpad(int personalInfoSeq, int size) {
		String personalInfo = String.valueOf(personalInfoSeq);
		int length = personalInfo.length();
		int needLength = size - length;
		
		for (int i = 0; i < needLength; i++) {
			personalInfo = "0"+personalInfo;
		}
		return personalInfo;
	}
	
	private void sendMail(PersonalInfoReadVO personalInfoReadVO) {
	      SendMail sendMail = new SendMail();
	      MailVO mailVO = new MailVO();

	      mailVO.setFromId("testForSendEmailKtds@gmail.com");
	      mailVO.setFromPassword("123qwe!@#qwe");
	      mailVO.setSubject(personalInfoReadVO.getMemberId()+"님께서 "+
	    		  personalInfoReadVO.getTargetMemberId()+"님의 상세 정보를 열람 신청했습니다.");
	      mailVO.setText(personalInfoReadVO.getMemberId()+"님께서 "+
	    		  personalInfoReadVO.getTargetMemberId()+"님의 상세 정보를 열람 신청했습니다."+
	    		  "열람 사유"+personalInfoReadVO.getDescription());
	      mailVO.setToId(memberBiz.getTargetMemberEmail(personalInfoReadVO.getTargetMemberId()));
	
	      sendMail.sendMailToCustomer(mailVO);
	}

	@Override
	public ModelAndView loginHistoryInit() {
		ModelAndView view = new ModelAndView();
		
		view.addObject("startDate", null);
		view.addObject("endDate", null);
		view.addObject("searchKeyword", null);
		view.addObject("searchType", null);
		view.setViewName("redirect:/memberHistory");
		
		return view;
	}
	

	@Override
	public ModelAndView memberListInit() {
		ModelAndView view = new ModelAndView();
		
		view.addObject("searchKeyword", null);
		view.addObject("searchType", null);
		view.setViewName("redirect:/memberManage/memberList");
		
		return view;
	}

	@Override
	public ModelAndView adminHistoryInit() {
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("startDate", null);
		view.addObject("endDate", null);
		view.setViewName("redirect:/adminHistory");
		
		return view;
	}

	@Override
	public List<MemberTypeVO> getMemberTypes() {
		return memberBiz.getMemberTypes();
	}

	@Override
	public List<HighestEducationLevelVO> getHighestEducationLevels() {
		return memberBiz.getHighestEducationLevels();
	}

	@Override
	public List<GraduationTypeVO> getGraduationTypes() {
		return memberBiz.getGraduationTypes();
	}

}
