package com.ktds.sems.member.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

import com.ktds.sems.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	private MemberBiz memberBiz;

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	/* Grtd */

	@Override
	public ModelAndView viewGrdtPage() {

		ModelAndView view = new ModelAndView();
		view.setViewName("member/grdtPage");
		view.addObject("grtdTpList", memberBiz.getAllGrtdList());
		return view;
	}

	@Override
	public String doGrdtDelete(String cdId) {
		memberBiz.doGrdtDelete(cdId);

		// WEB-INF/view/member/grdtPage.jsp
		return "redirect:/grdtPage";
	}

	@Override
	public String doGrdtModify(String cdId, String cdNm) {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId(cdId);
		grdtTpVO.setCdNm(cdNm);

		if (memberBiz.isExistCdNmData(grdtTpVO) > 0) {
			return "FAIL";
		}

		else {
			boolean data = memberBiz.doGrdtModify(grdtTpVO) > 0;

			if (!data) {
				return "FAIL";
			}
			return "OK";
		}
	}

	@Override
	public String doGrdtInsert(String cdId, String cdNm) {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId(cdId);
		grdtTpVO.setCdNm(cdNm);

		if (memberBiz.isExistData(grdtTpVO) > 0) {
			return "FAIL";
		}

		else {
			boolean data = memberBiz.doGrdtInsert(grdtTpVO) > 0;

			if (!data) {
				return "FAIL";
			}
			return "OK";
		}
	}

	/* Highest Edu */
	@Override
	public ModelAndView viewHighestEduPage() {
		List<HighestEduTpVO> highestEduTpList = memberBiz.getAllHighestEduList();

		ModelAndView view = new ModelAndView();
		view.setViewName("member/highestEduLv");
		view.addObject("highestEduTpList", highestEduTpList);
		return view;
	}

	@Override
	public String doHighestEduDelete(String cdId) {
		if (memberBiz.doHighestEduDelete(cdId)) {
			return "redirect:/highestEduPage";
		} else {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}

	@Override
	public void doHighestEduModify(String cdId, String cdNm) {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId(cdId);
		highestEduTpVO.setCdNm(cdNm);

		if (!memberBiz.doHighestEduModify(highestEduTpVO)) {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}

	@Override
	public String doHighestEduInsert(String cdId, String cdNm) {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId(cdId);
		highestEduTpVO.setCdNm(cdNm);

		if (memberBiz.isExistHighestEduData(highestEduTpVO) > 0) {
			System.out.println("1");
			return "FAIL";
		}

		else {
			boolean data = memberBiz.doHighestEduInsert(highestEduTpVO) > 0;

			if (!data) {
				System.out.println("2");
				return "FAIL";
			}
			System.out.println("3");
			return "OK";
		}
	}

	/* MbrTp */
	@Override
	public ModelAndView viewMbrTpPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/mbrTp");
		List<MbrTpVO> mbrTpVOList = memberBiz.getAllMbrTpList();
		view.addObject("mbrTpVOList", mbrTpVOList);

		return view;
	}

	@Override
	public String doInsertMbrTp(String cdId, String cdNm) {
		MbrTpVO newMbrTpVO = new MbrTpVO();
		newMbrTpVO.setCdId(cdId);
		newMbrTpVO.setCdNm(cdNm);

		if (memberBiz.isExistMbrTpData(newMbrTpVO) > 0) {
			return "FAIL";
		}

		else {
			boolean data = memberBiz.doInsertMbrTp(newMbrTpVO) > 0;

			if (!data) {
				return "FAIL";
			}
			return "OK";
		}
	}

	@Override
	public String doMbrTpDelete(String cdId) {
		memberBiz.doMbrTpDelete(cdId);
		return "redirect:/mbrTpPage";
	}

	@Override
	public String doMbrTpModify(String cdId, String cdNm) {
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId(" ");
		mbrTpVO.setCdNm(cdNm);

		if (memberBiz.isExistMbrTpData(mbrTpVO) > 0) {
			return "FAIL";
		} else {
			mbrTpVO.setCdId(cdId);
			boolean data = memberBiz.doMbrTpModify(mbrTpVO) > 0;

			if (!data) {
				return "FAIL";
			}
			return "OK";
		}
	}

	/* 로그인 */
	@Override
	public String login(MemberVO loginVO, Errors errors, HttpSession session, HttpServletRequest request) {

		// 아이디 있는지 확인
		if (memberBiz.isExistId(loginVO.getId())) {
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
				 * 로그인한 회원이 글을 작성하는 write.jsp 에 아래 코드를 추가해야함! <input
				 * type="hidden" name="csrfToken"
				 * value="${sessionScope._CSRF_TOKEN_}" />
				 */
				String csrfToken = UUID.randomUUID().toString();
				session.setAttribute(Session.CSRF_TOKEN, csrfToken);

				// 로그인 내역 남기기
				memberBiz.stampLoginTime(session, request, loginVO);

				if (memberBiz.needToChangPassword(loginVO.getId())) {
					return "CNGPW";
				} else {

					String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);

					if (memberType != null && !memberType.equals("ADM")) {
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

		// 로그아웃 stamp 찍기 위해서..
		memberBiz.stampLogoutTime(session);
	}

	/* Code Mng */
	
	@Override
	public ModelAndView viewCodeMngPage() {
		
		List<CodeMngVO> codeMngList = memberBiz.getAllCodeMngList();

		ModelAndView view = new ModelAndView();
		view.setViewName("member/codeMngPage");
		view.addObject("codeMngList", codeMngList);
		return view;
	}

	@Override
	public String doCodeMngDelete(String cdId) {
		if (memberBiz.doCodeMngDelete(cdId)) {
			return "redirect:/codeMngPage";
		} else {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}

	@Override
	public String doCodeMngModify(CodeMngVO codeMngVO) {
		if (memberBiz.doCodeMngModify(codeMngVO) ) {
			return "OK";
		} else {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}

	@Override
	public String doCodeMngInsert(CodeMngVO codeMngVO) {
		
		if (memberBiz.doCodeMngInsert(codeMngVO)) {
			return "redirect:/codeMngPage";
		} else {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}
}
