package com.ktds.sems.member.biz.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;

import com.ktds.sems.common.LoginStore;
import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.Session;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.education.vo.EduClassVO;
import com.ktds.sems.education.vo.EducationCostVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationStateVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.excel.option.WriteOption;
import kr.co.hucloud.utilities.excel.write.ExcelWrite;

public class MemberBizImpl implements MemberBiz {

	private JavaMailSender mailSender;
	private MemberDAO memberDAO;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean addNewMember(MemberVO member) {
		return memberDAO.addNewMember(member) > 0;
	}
	
	@Override
	public boolean checkRegistState(String id) {
		return memberDAO.checkRegistState(id) > 0;
	}

	@Override
	public boolean login(HttpSession session, MemberVO loginVO, HttpServletRequest request) {

		// SHA256 이용해 암호화
		String memberSalt = memberDAO.getSaltById(loginVO.getId());
		String inputPassword = loginVO.getPassword();
		String newPassword = SHA256Util.getEncrypt(inputPassword, memberSalt);
		loginVO.setPassword(newPassword);

		MemberVO memberVO = memberDAO.login(loginVO);

		if (memberVO != null) {

			// 이미 로그인 되어 있다면, 기존 로그인 세션을 종료
			LoginStore loginStore = LoginStore.getInstance();
			HttpSession loginedSession = loginStore.get(loginVO.getId());
			if (loginedSession != null) {
				loginStore.logout(loginVO.getId());
				stampLogoutTime(session);
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
	public boolean isAccountLock(String id) {
		return memberDAO.isAccountLock(id);
	}

	@Override
	public boolean loginSuccess(String id) {
		return memberDAO.loginSuccess(id) > 0;
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
	public MemberVO getOneMember(String id) {
		return memberDAO.getOneMember(id);
	}

	@Override
	public void resetModifyLockAndCount(String id) {
		memberDAO.resetModifyLockAndCount(id);
	}

	@Override
	public void plusModifyFailCount(String sessionId) {
		memberDAO.plusModifyFailCount(sessionId);
	}

	@Override
	public void updateModifyAccountLock(String sessionId) {
		memberDAO.updateModifyAccountLock(sessionId);
	}

	@Override
	public boolean isModifyAccountLock(String sessionId) {
		return memberDAO.isModifyAccountLock(sessionId) > 0;
	}

	@Override
	public String getNowDate() {
		return memberDAO.getNowDate();
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
	public boolean isVerifyPhoneNumber (String phoneNumber) {
		String phoneNumberPolicy = "(^0([0-9]){1,2}-([0-9]{3,4})-([0-9]{4})$)";
		Pattern pattern = Pattern.compile(phoneNumberPolicy);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}
	
	@Override
	public boolean isVerifyEmail (String email) {
		//이메일 정규표현식 수정 by SM Team
		String emailPolicy = "(^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$)";
		Pattern pattern = Pattern.compile(emailPolicy);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * SM 기연 (이름에 특수문자 들어가지 않게 체크)
	 */
	@Override
	public boolean checkValidationByName(String name) {
		String namePolicy = "(^[가-힣]*$)";
		Pattern pattern = Pattern.compile(namePolicy);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	@Override
	public boolean checkValidationByUniversityName(String universityName) {
		String universityNamePolicy = "((?=.*[a-zA-Z가-힣]))(^[가-힣a-zA-Z0-9]*$)";
		Pattern pattern = Pattern.compile(universityNamePolicy);
		Matcher matcher = pattern.matcher(universityName);
		return matcher.matches();
	}
	
	@Override
	public boolean checkValidationByMajorName(String majorName) {
		String universityNamePolicy = "(^[가-힣a-zA-Z]*$)";
		Pattern pattern = Pattern.compile(universityNamePolicy);
		Matcher matcher = pattern.matcher(majorName);
		return matcher.matches();
	}
	
	/**
	 * @author 이기연
	 */
	@Override
	public void saveLoginHistoryAsExcel(String memberId) {

		WriteOption wo = new WriteOption();
		wo.setSheetName("로그인 내역");
		wo.setFileName("로그인 내역.xlsx");
		wo.setFilePath("D:\\");
		List<String> titles = new ArrayList<String>();

		titles.add("로그인 내역 순번");
		titles.add("로그인 아이디");
		titles.add("로그인 IP");
		titles.add("로그인 시간");
		titles.add("로그아웃 시간");
		wo.setTitles(titles);

		List<String[]> contents = new ArrayList<String[]>();

		// LoginHistory 만들기
		try {
			List<LoginHistoryVO> loginHistory = memberDAO.saveLoginHistoryAsExcel(memberId);
			Iterator<LoginHistoryVO> tempIterator = loginHistory.iterator();

			// TODO while문으로 null을 만날 때 까지 while문을 돌려야 할 것 같다
			while (tempIterator.hasNext())

			{
				// TODO String[] 타입인데... 이걸 수정해바야 할 것 같다.
				// 하나씩 String[]에 담는 것 그리고 add
				LoginHistoryVO tempLoginHistoryVO = new LoginHistoryVO();
				tempLoginHistoryVO = tempIterator.next();

				String[] content = new String[5];

				content[0] = tempLoginHistoryVO.getLgiHtrId() + "";
				content[1] = tempLoginHistoryVO.getId();
				content[2] = tempLoginHistoryVO.getLgiIp();
				content[3] = tempLoginHistoryVO.getLgiDt();
				content[4] = tempLoginHistoryVO.getLgoDt();

				contents.add(content);
			}

			wo.setContents(contents);

			File excelFile = ExcelWrite.write(wo);

		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	/**
	 * @author 206-025 이기연
	 */
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

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public boolean stampLogoutTime(HttpSession session) {
		LoginHistoryVO newLoginHistoryVO = new LoginHistoryVO();
		newLoginHistoryVO = (LoginHistoryVO) session.getAttribute("_LOGIN_HISTORY_");

		// 찍고 세션 없애기
		session.removeAttribute("_LOGIN_HISTORY_");

		return memberDAO.stampLogoutTime(newLoginHistoryVO) > 0;
	}

	@Override
	public int getTotalLoginHistoryCount(LoginHistorySearchVO loginHistorySearchVO) {
		return memberDAO.getTotalLoginHistoryCount(loginHistorySearchVO);
	}

	@Override
	public boolean isExistEmail(String email) {
		return memberDAO.isExistEmail(email) != null;
	}

	@Override
	public void attendCheck(MemberVO loginVO) {

		Map<String, String> eduIdAndMemberId = new HashMap<String, String>();

		/* 회원별 강의 */
		List<EducationVO> eduListByMember = new ArrayList<EducationVO>();
		eduListByMember = memberDAO.getEduListByMember(loginVO);
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		

		/* 현재 시간 */
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat onlyDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(date);
		long calTodayTime = cal3.getTimeInMillis();
		String nowTime = dateFormat.format(date);
		
		// 강의 리스트에서 StartDate ~ EndDate 맞는 강의 가져옴
		for (EducationVO educationVO : eduListByMember) {

			String startDate = educationVO.getStartDate() + " " + educationVO.getStartTime();
			String endDate = educationVO.getEndDate() + " " + educationVO.getEndTime();

			eduIdAndMemberId.put("educationId", educationVO.getEducationId());
			eduIdAndMemberId.put("memberId", loginVO.getId());

			String lastDate = memberDAO.getLastDate(eduIdAndMemberId);
			String nowDate = onlyDateFormat.format(date);
			
			try {
				Date eduStartDate = dateFormat.parse(startDate);
				Date eduEndDate = dateFormat.parse(endDate);

				cal.setTime(eduStartDate);
				long calEduStartDate = cal.getTimeInMillis();
				cal2.setTime(eduEndDate);
				long calEduEndDate = cal2.getTimeInMillis();

				// 출석시간 체크하기 위한 변수 선언
				Calendar cal4 = Calendar.getInstance();
				Date eduStartTime = timeFormat.parse(educationVO.getStartTime());
				cal4.setTime(eduStartTime);
				long calEduStartTime = cal4.getTimeInMillis();

				Calendar cal5 = Calendar.getInstance();
				Date eduEndTime = timeFormat.parse(educationVO.getEndTime());
				cal5.setTime(eduEndTime);
				long calEduEndTime = cal5.getTimeInMillis();

				cal4.add(Calendar.HOUR, -1);
				long calEduBeforeOneHour = cal4.getTimeInMillis();
				long calEduHalfTime = calEduEndTime - ((calEduEndTime - calEduStartTime) / 2);

				Date todayTime = timeFormat.parse(timeFormat.format(date));
				cal3.setTime(todayTime);
				long calNowTime = cal3.getTimeInMillis();
				
				// 현재 날짜와 강의 기간 날짜 체크
				if (calEduStartDate < calTodayTime && calTodayTime < calEduEndDate) {

					// 시간 체크 StartTime-1 ~ (EndTime-StartTime)/2 맞는지 체크
					if ((calEduBeforeOneHour < calNowTime)
							&& (calNowTime < calEduHalfTime)) {

						// 하루에 한 번만 출석 체크
						if (!lastDate.equals(nowDate)) {
							AttendVO attendVO = new AttendVO();
							attendVO.setEducationId(educationVO.getEducationId());
							attendVO.setMemberId(loginVO.getId());
							attendVO.setAttendTime(nowTime);

							memberDAO.insertAttendByMember(attendVO);
						}
						break;
					}
				}

			} catch (ParseException e) {

			}
		}
	}

	@Override
	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO) {
		List<LoginHistoryVO> loginHistoryVO = memberDAO.getAllLoginHistory(loginHistorySearchVO);
				
		for ( LoginHistoryVO history : loginHistoryVO ) {
			String lgiIp = history.getLgiIp();
			history.setLgiIp(hideIp(lgiIp));
		}
		
		return loginHistoryVO;
	}
	
	@Override
	public void modifyMemberInfo(MemberVO member) {
		memberDAO.modifyMemberInfo(member);
	}

	@Override
	public boolean isExistId(String id) {

		String memberId = memberDAO.isExistId(id);

		if (memberId != null) {
			return true;
		} 
		else {
			return false;
		}
	}

	@Override
	public String getSaltById(String id) {
		return memberDAO.getSaltById(id);
	}

	@Override
	public String getPasswordById(String id) {
		return memberDAO.getPasswordById(id);
	}

	@Override
	public boolean isResign(String id) {
		String memberId = memberDAO.isResign(id);

		if (memberId != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void sendEmailForResign(String email, String id, String uuid) {

		SendMail sendMail = new SendMail();
		MailVO mailVO = new MailVO();

		mailVO.setFromId("testForSendEmailKtds@gmail.com");
		mailVO.setFromPassword("123qwe!@#qwe");
		mailVO.setSubject("탈퇴기능 테스트입니다.");
		mailVO.setText("<html><body>탈퇴하시겠습니까? <a href='http://localhost/sems/member/loginForResign/" + uuid + "/" + id
				+ "'>예</a></body></html>");

		mailVO.setToId(email);

		sendMail.sendMailToCustomer(mailVO);
	}
	
	@Override
	public void insertUuidForResign(MemberVO member) {
		memberDAO.insertUuidForResign(member);
	}
	
	@Override
	public boolean doDeleteMember(String id) {
		return memberDAO.doDeleteMember(id) > 0;
	}


	@Override
	public boolean changePassword(MemberVO memberVO) {
		return memberDAO.changePassword(memberVO) > 0;
	}

	@Override
	public List<MenuManageVO> getMenuCategoryList() {
		return memberDAO.getMenuCategoryList();
	}

	@Override
	public boolean isTeacher(String id) {
		return memberDAO.isTeacher(id) > 0;
	}

	@Override
	public boolean doResign(MemberVO loginVO) {
		
		String memberSalt = memberDAO.getSaltById(loginVO.getId());
		String inputPassword = loginVO.getPassword();
		String newPassword = SHA256Util.getEncrypt(inputPassword, memberSalt);
		loginVO.setPassword(newPassword);
		
		MemberVO memberVO = memberDAO.login(loginVO);

		return memberVO != null;
	}

	// 준호
	@Override
	public int delectJunitTestMember(String id) {
		return memberDAO.delectJunitTestMember(id);
	}

	@Override
	public int getTotalEducationHistoryCountById(EducationHistorySearchVO educationHistorySearchVO) {
		return memberDAO.getTotalEducationHistoryCountById(educationHistorySearchVO);
	}

	@Override
	public List<EducationHistoryVO> getAllEducationHistoryListByIdWithPaging(EducationHistorySearchVO educationHistorySearchVO) {
		
		return memberDAO.getAllEducationHistoryListByIdWithPaging(educationHistorySearchVO);
	}

	@Override
	public boolean doMatchHistoryWithMember(LoginHistoryVO loginHistoryVO) {
		
		if ( memberDAO.doMatchHistoryWithMember(loginHistoryVO).equals("Y") ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void doRequestIpHistory(int lgiHtrId) {
		memberDAO.doRequestIpHistory(lgiHtrId);
	}

	@Override
	public boolean doCheckIp(LoginHistoryVO loginHistoryVO) {
		if(memberDAO.doCheckIp(loginHistoryVO) == 1) {
			loginHistoryVO.setChkCnt(memberDAO.doCheckIp(loginHistoryVO));
			return true;
		}else {
			return false;
		}
	}

	@Override
	public LoginHistoryVO checkIpInfo(LoginHistoryVO loginHistoryVO) {
		
		memberDAO.ipCheckCountUpdate(loginHistoryVO);
		
		return memberDAO.checkIpInfo(loginHistoryVO);
	}
	
	/**
	 * IP Masking
	 * 
	 * @author 이기연
	 * 
	 * @param ip 마스킹 처리할 IP
	 * @return 마스킹 IP
	 */
	private String hideIp(String ip) {
		String hidedIp = "";
		String number = "^[0-9]$";

		for (int i = 0; i < ip.length(); i++) {
			String temp = ip.charAt(i) + "";
			if (temp.matches(number)) {
				hidedIp = hidedIp + "*";
			}
			else {
				hidedIp = hidedIp + temp;
			}
		}
		
		return hidedIp;
	}

	@Override
	public boolean eduationHistoryExportExcel(String id) {
		
		WriteOption wo = new WriteOption();
		wo.setSheetName("나의 교육 이력");
		wo.setFileName("나의교육이력.xlsx");
		wo.setFilePath("D:\\");
		List<String> titles = new ArrayList<String>();

		// 13개
		titles.add("교육 이력 아이디");
		titles.add("교육 아이디");
		titles.add("교육명");
		titles.add("비용");
		titles.add("수강생 아이디");
		titles.add("신청 날짜");
		titles.add("신청 상태 아이디");
		titles.add("신청 IP");
		titles.add("코멘트");
		titles.add("피드백");
		titles.add("교육시작일자");
		titles.add("교육종료일자");
		titles.add("신청 상태");
		wo.setTitles(titles);

		List<String[]> contents = new ArrayList<String[]>();
		List<EducationHistoryVO> educationHistoryList = new ArrayList<EducationHistoryVO>();

		// educationHistoryList 만들기
		try {
			educationHistoryList = memberDAO.getAllEducationHistoryListById(id);
			
			Iterator<EducationHistoryVO> tempIterator = educationHistoryList.iterator();

			// TODO while문으로 null을 만날 때 까지 while문을 돌려야 할 것 같다
			while (tempIterator.hasNext())

			{
				// TODO String[] 타입인데... 이걸 수정해바야 할 것 같다.
				// 하나씩 String[]에 담는 것 그리고 add
				EducationHistoryVO tempEducationHistoryVO = new EducationHistoryVO();
				tempEducationHistoryVO = tempIterator.next();

				// 13개
				String[] content = new String[13];

				content[0] = tempEducationHistoryVO.getEducationHistoryId();
				content[1] = tempEducationHistoryVO.getEducationId();
				content[2] = tempEducationHistoryVO.getEducationTitle();
				content[3] = tempEducationHistoryVO.getCost();
				content[4] = tempEducationHistoryVO.getMemberId();
				content[5] = tempEducationHistoryVO.getEducationHistoryDate();
				content[6] = tempEducationHistoryVO.getState();
				content[7] = tempEducationHistoryVO.getIp();
				content[8] = tempEducationHistoryVO.getCmnt();
				content[9] = tempEducationHistoryVO.getFdbk();
				content[10] = tempEducationHistoryVO.getStartDate();
				content[11] = tempEducationHistoryVO.getEndDate();
				content[12] = tempEducationHistoryVO.getCdNm();
				
				contents.add(content);
			}

			wo.setContents(contents);

			File excelFile = ExcelWrite.write(wo);

		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return educationHistoryList != null;
	}

	@Override
	public List<EducationHistoryVO> getJoinEducationList(String memberId) {
		return memberDAO.getJoinEducationList(memberId);
	}

	@Override
	public boolean isAdmin(String id) {
		return memberDAO.isAdmin(id) > 0;
	}

	@Override
	public List<EducationStateVO> getStatList() {
		return memberDAO.getStatList();
	}

	@Override
	public List<EducationCostVO> getCostList() {
		return memberDAO.getCostList();
	}

	@Override
	public EducationHistoryVO getOneEducationByIdAndEducationId(String educationId, String id) {
		return memberDAO.getOneEducationByIdAndEducationId(educationId, id);
	}

	@Override
	public boolean dropCourseApply(EducationHistoryVO educationHistory) {
		return memberDAO.dropCourseApply(educationHistory) > 0;
	}

	@Override
	public int getCourseCountById(String id) {
		return memberDAO.getCourseCountById(id);
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
	public String getSelectMemberTypeCodeName(String memberType) {
		return memberDAO.getSelectMemberTypeCodeName(memberType);
	}
	
	
	
	/* 출결이력 : 1. 강의 리스트 불러오기 */
	@Override
	public List<EducationVO> getAllAttendClassListById(MemberVO loginVO) {	
		
		List<EducationVO> eduListByMember = new ArrayList<EducationVO>();
		eduListByMember = memberDAO.getEduListByMember(loginVO);
		
		return eduListByMember;
	}

	@Override
	public Map<String, List<String>> getAllAttendHistory(MemberVO memberVO, String educationId) {
		
		Map<String, String> eduIdAndMemberId = new HashMap<String, String>();

		eduIdAndMemberId.put("educationId", educationId);
		eduIdAndMemberId.put("memberId", memberVO.getId());
		EducationVO educationVO = memberDAO.getOneEducationInfo(eduIdAndMemberId);
		List<AttendVO> attendList = memberDAO.getAllAttendHistoryListById(eduIdAndMemberId);
		Map<String, List<String>> attendHistoryList= getStateByEachClass(educationVO, attendList);
			
		return attendHistoryList;
	}
	
	
	private Map<String, List<String>> getStateByEachClass(EducationVO educationVO, List<AttendVO> attendList) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		/* 강의 날짜 구간 */
		String classStartDate = educationVO.getStartDate();
		String classEndDate = educationVO.getEndDate();

		int sYear = Integer.parseInt(classStartDate.substring(0, 4));
		int sMonth = Integer.parseInt(classStartDate.substring(5, 7));
		int sDate = Integer.parseInt(classStartDate.substring(8, 10));
		
		int eYear =  Integer.parseInt(classEndDate.substring(0, 4));
		int eMonth = Integer.parseInt(classEndDate.substring(5, 7));
		int eDate = Integer.parseInt(classEndDate.substring(8, 10));
		
		//Date startDate = new Date(sYear, sMonth-1, sDate);
		//Date endDate = new Date(eYear, eMonth-1, eDate);

		Calendar cal1 = Calendar.getInstance();
		cal1.set(sYear, sMonth - 1 , sDate);
		Date startDate = cal1.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(eYear, eMonth - 1, eDate + 1);
		Date endDate = cal2.getTime();

		/* 어제 구하기 */
		Date today = new Date();
		Date beforeDate = new Date();
		beforeDate.setTime( today.getTime() - ( (long) 1000 * 60 * 60 * 24 ) );

		/* 날짜들이 들어있는 List */
		List<Date> dates = getDaysBetweenDates(startDate, endDate);
		
		/* 어제까지의 날짜들이 들어있는 List */
		List<Date> datesUntilToday = getDaysBetweenDates(startDate, beforeDate);
		
		/* 출석 정보를 담는 Map <날짜, 출석결과> */
		Map<String, List<String>> attendHistoryMap = new HashMap<String, List<String>> ();
		
		/* 하루 하루 */
		String eachDate = null;
		
		/* 회원이 출석, 퇴근한 날짜 */
		String memberAttendDate = null;
		String memberLeaveDate = null;
		List<String> statusAndTime;
		
		for (Date date : dates) {
			statusAndTime = new ArrayList<String>();
			statusAndTime.add("-");
			statusAndTime.add(" ");
			eachDate = dateFormat.format(date);
			attendHistoryMap.put(eachDate, statusAndTime);
		}
		
		for(Date date: datesUntilToday) { 
			statusAndTime = new ArrayList<String>();
			statusAndTime.add("X");
			statusAndTime.add(" ");
			eachDate = dateFormat.format(date);
			attendHistoryMap.put(eachDate, statusAndTime);
		}
		
		try {
			String classStartTime = educationVO.getStartTime();
			Calendar classStartCal = Calendar.getInstance();
			Date classStartDateFormat = timeFormat.parse(classStartTime);
			classStartCal.setTime(classStartDateFormat);
			long calEduStartSecond = classStartCal.getTimeInMillis();
			
			String classEndTime = educationVO.getEndTime();
			Calendar classEndCal = Calendar.getInstance();
			Date classEndDateFormat = timeFormat.parse(classEndTime);
			classEndCal.setTime(classEndDateFormat);
			long calEduEndSecond = classEndCal.getTimeInMillis();
			
			Calendar attendCal = Calendar.getInstance();
			Calendar leaveCal = Calendar.getInstance();
			for (Date date : dates) {
				for (int i = 0; i < attendList.size(); i++) {
					eachDate = dateFormat.format(date);
					
					memberAttendDate = attendList.get(i).getAttendTime();
					String memberDate = memberAttendDate.substring(0, 10);
					memberAttendDate = memberAttendDate.substring(11, memberAttendDate.length()); 
					Date memberAttendDateFormat = timeFormat.parse(memberAttendDate);
					attendCal.setTime(memberAttendDateFormat);
					long calMemberAttendSecond = attendCal.getTimeInMillis();
					
					memberLeaveDate = attendList.get(i).getLeaveTime();
					memberLeaveDate = memberLeaveDate.substring(11, memberLeaveDate.length()); 
					Date memberLeaveDateFormat = timeFormat.parse(memberLeaveDate);
					leaveCal.setTime(memberLeaveDateFormat);
					long calMemberLeaveSecond = leaveCal.getTimeInMillis();
					
					if(memberDate.equals(eachDate)) {
						// 결석
						if ( ((calEduEndSecond - calEduStartSecond)/2 - (calMemberLeaveSecond - calMemberAttendSecond)) > 0 ) {
							statusAndTime = new ArrayList<String>();
							statusAndTime.add("X");
							statusAndTime.add(" ");
							attendHistoryMap.put(eachDate, statusAndTime);
						}
						// 출석
						else if ( (calEduStartSecond - calMemberAttendSecond) >= 0 && (calMemberLeaveSecond - calEduEndSecond) >= 0 ) {
							statusAndTime = new ArrayList<String>();
							statusAndTime.add("○");
							statusAndTime.add(memberAttendDate + " ~ " + memberLeaveDate);
							attendHistoryMap.put(eachDate, statusAndTime);
						}
						// 지각
						else if ( (calEduStartSecond - calMemberAttendSecond) < 0 && (calMemberLeaveSecond - calEduEndSecond) >= 0 ) {
							statusAndTime = new ArrayList<String>();
							statusAndTime.add("△");
							statusAndTime.add(memberAttendDate + " ~ " + memberLeaveDate);
							attendHistoryMap.put(eachDate, statusAndTime);
						}
						// 조퇴
						else if ( (calMemberLeaveSecond - calEduEndSecond) <= 0 ) {
							statusAndTime = new ArrayList<String>();
							statusAndTime.add("●");
							statusAndTime.add(memberAttendDate + " ~ " + memberLeaveDate);
							attendHistoryMap.put(eachDate, statusAndTime);
						}
					}
				}
			}
		} catch (ParseException e) {
			System.out.println("형식 변환 오류");
		}

		Map<String, List<String>> treeMap = new TreeMap<String, List<String>>(attendHistoryMap);
		Iterator<String> treeMapIter = treeMap.keySet().iterator();
		String key = null;
		while(treeMapIter.hasNext()){
			key = treeMapIter.next();
			System.out.println("날짜 : " + key + " 값 : "+ attendHistoryMap.get(key));
		}
		
		return treeMap;
		
	}
	
	
	private List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
	@Override
	public List<EducationVO> getEduListByMember(MemberVO memberVO) {
		return memberDAO.getEduListByMember(memberVO);
	}

	@Override

	public List<LoginHistoryVO> getLoginHistoryListByMemberId(String id) {
		return memberDAO.getLoginHistoryListByMemberId(id);
	}

	@Override
	public List<EducationHistoryVO> getEducationHistoryListByMemberId(String id) {
		return memberDAO.getEducationHistoryListByMemberId(id);
	}

	@Override
	public List<QNAVO> getQnaListByMemberId(String id) {
		return memberDAO.getQnaListByMemberId(id);
	}


	/**
	 * 수강 포기 신청 사유 text 중 특수문자가 있는 지 확인
	 * 한글, 영어, 숫자는 OK
	 * 이기연
	 */
	@Override
	public boolean isValidCourseDropReason(String courseDropReason) {
		String courseDropReasonPolicy = "(^[가-힣a-zA-Z0-9]*$)";
		Pattern pattern = Pattern.compile(courseDropReasonPolicy);
		Matcher matcher = pattern.matcher(courseDropReason);
		return matcher.matches();
	}

	@Override
	public boolean checkValidationCourseAccess(String memberId) {
		int checkNumber = memberDAO.checkValidationCourseAccess(memberId);
		if (checkNumber != 0) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public List<EducationVO> getCourseList(String memberId) {
		return memberDAO.getCourseList(memberId);
	}
	
	@Override
	public boolean isVerifyLeave(String id) {
		return memberDAO.isVerifyLeave(id) > 0;
	}


	@Override
	public boolean updateLeaveClass(String memberId) {
		
		AttendVO attendVO = memberDAO.getNowClassInfoById(memberId);
		System.out.println(attendVO.getMemberId());
		return memberDAO.updateLeaveClass(attendVO) > 0;
	}

	@Override
	public List<ReportReplyVO> getReportReplyListByMemberId(String id) {
		return memberDAO.getReportReplyListByMemberId(id);
	}

	@Override
	public List<EducationVO> getPreCourseList(EducationSearchVO educationSearchVO) {
		return memberDAO.getPreCourseList(educationSearchVO);
	}

	@Override
	public EducationVO getOneEducationInfo(Map<String, String> eduIdAndMemberId) {
		return memberDAO.getOneEducationInfo(eduIdAndMemberId);
	}

}
