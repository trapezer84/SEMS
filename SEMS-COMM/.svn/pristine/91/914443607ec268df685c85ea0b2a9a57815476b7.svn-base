package com.ktds.sems.member.dao;

import java.util.List;

import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberDAO {

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

	public int doHighestEduDelete(String cdId);

	public int isExistHighestEduData(HighestEduTpVO highestEduTpVO);

	public int doHighestEduInsert(HighestEduTpVO highestEduTpVO);

	public int doHighestEduModify(HighestEduTpVO highestEduTpVO);

	public int isExistCdNmData(GrdtTpVO grdtTpVO);

	public String isExistId(String id);

	public boolean isAccountLock(String id);

	public int loginSuccess(String id);

	public String needToChangPassword(String id);

	public int plusLoginFailCount(String id);

	public int updateAccountLock(String id);

	public String getSaltById(String id);

	public MemberVO login(MemberVO loginVO);

	public int isExistMbrTpData(MbrTpVO newMbrTpVO);

	public String isResign(String id);

	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO);

	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO);

	public int nextLoginHistorySeq();

	int stampLogoutTimeByMemberId(String memberId);

	public List<CodeMngVO> getAllCodeMngList();

	public int doCodeMngDelete(String cdId);

	public int doCodeMngModify(CodeMngVO codeMngVO);

	public int doCodeMngInsert(CodeMngVO codeMngVO);

}
