package com.ktds.sems.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberService {

	public ModelAndView viewGrdtPage();

	public String doGrdtDelete(String cdId);

	public String doGrdtModify(String cdId, String cdNm);

	public String doGrdtInsert(String cdId, String cdNm);

	public ModelAndView viewMbrTpPage();

	public String doInsertMbrTp(String cdId, String cdNm);

	public String doMbrTpDelete(String cdId);

	public String doMbrTpModify(String cdId, String cdNm);

	public ModelAndView viewHighestEduPage();

	public String doHighestEduDelete(String cdId);

	public void doHighestEduModify(String cdId, String cdNm);

	public String doHighestEduInsert(String cdId, String cdNm);

	public String login(MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request);

	public void logout(HttpSession session);

	public ModelAndView viewCodeMngPage();

	public String doCodeMngDelete(String cdId);

	public String doCodeMngModify(CodeMngVO codeMngVO);

	public String doCodeMngInsert(CodeMngVO codeMngVO);

}
