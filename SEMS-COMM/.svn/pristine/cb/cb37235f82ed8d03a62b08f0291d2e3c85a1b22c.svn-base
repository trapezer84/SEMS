package com.ktds.sems.member.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberBiz {

	public List<GrdtTpVO> getAllGrtdList();

	public List<MbrTpVO> getAllMbrTpList();

	public int doGrdtDelete(String cdId);

	public int doGrdtModify(GrdtTpVO grdtTpVO);

	public int doGrdtInsert(GrdtTpVO grdtTpVO);

	public int isExistData(GrdtTpVO grdtTpVO);
	
	public int doInsertMbrTp(MbrTpVO newMbrTpVO);

	public int doMbrTpDelete(String cdId);

	public int doMbrTpModify(MbrTpVO mbrTpVO);

	public List<HighestEduTpVO> getAllHighestEduList();

	public boolean doHighestEduDelete(String cdId);

	public boolean doHighestEduModify(HighestEduTpVO highestEduTpVO);

	public int isExistHighestEduData(HighestEduTpVO highestEduTpVO);

	public int doHighestEduInsert(HighestEduTpVO highestEduTpVO);

	public int isExistCdNmData(GrdtTpVO grdtTpVO);

	public boolean isExistId(String id);

	public boolean isAccountLock(String id);

	public boolean loginSuccess(String id);

	public boolean needToChangPassword(String id);

	public boolean plusLoginFailCount(String id);

	public boolean updateAccountLock(String id);

	public boolean login(HttpSession session, MemberVO loginVO);

	public int isExistMbrTpData(MbrTpVO newMbrTpVO);

	public boolean isResign(String id);

	public boolean stampLoginTime(HttpSession session, HttpServletRequest request, MemberVO loginVO);

	public boolean stampLogoutTime(HttpSession session);

	public List<CodeMngVO> getAllCodeMngList();

	public boolean doCodeMngDelete(String cdId);

	public boolean doCodeMngModify(CodeMngVO codeMngVO);

	public boolean doCodeMngInsert(CodeMngVO codeMngVO);

}
