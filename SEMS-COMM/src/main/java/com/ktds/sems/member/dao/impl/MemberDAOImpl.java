package com.ktds.sems.member.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	@Override
	public List<GrdtTpVO> getAllGrtdList() {
		return getSqlSession().selectList("MemberDAO.getAllGrtdList");
	}

	@Override
	public List<MbrTpVO> getAllMbrTpList() {
		return getSqlSession().selectList("MemberDAO.getAllMbrTp");
	}

	@Override
	public int doInsertMbrTp(MbrTpVO newMbrTpVO) {
		return getSqlSession().insert("MemberDAO.doInsertMbrTp", newMbrTpVO);
	}

	@Override
	public int doGrdtDelete(String cdId) {
		return getSqlSession().delete("MemberDAO.doGrdtDelete", cdId);
	}

	@Override
	public int doGrdtModify(GrdtTpVO grdtTpVO) {
		return getSqlSession().update("MemberDAO.doGrdtModify", grdtTpVO);

	}

	@Override
	public int doGrdtInsert(GrdtTpVO grdtTpVO) {
		return getSqlSession().insert("MemberDAO.doGrdtInsert", grdtTpVO);
	}

	@Override
	public int isExistMbrTpData(MbrTpVO newMbrTpVO) {
		return getSqlSession().selectOne("MemberDAO.isExistMbrTpData", newMbrTpVO);
	}

	public int doMbrTpDelete(String cdId) {
		return getSqlSession().delete("MemberDAO.doMbrTpDelete", cdId);

	}

	@Override
	public int doMbrTpModify(MbrTpVO mbrTpVO) {
		return getSqlSession().update("MemberDAO.doMbrTpModify", mbrTpVO);

	}

	@Override
	public int isExistData(GrdtTpVO grdtTpVO) {
		return getSqlSession().selectOne("MemberDAO.isExistData", grdtTpVO);
	}

	/* Highest Edu */
	@Override
	public List<HighestEduTpVO> getAllHighestEduList() {
		return getSqlSession().selectList("MemberDAO.getAllHighestEduList");
	}

	@Override
	public int isExistHighestEduData(HighestEduTpVO highestEduTpVO) {
		
		System.out.println(highestEduTpVO.getCdId());
		System.out.println(highestEduTpVO.getCdNm());
		
		return getSqlSession().selectOne("MemberDAO.isExistHighestEduData", highestEduTpVO);
	}

	@Override
	public int doHighestEduInsert(HighestEduTpVO highestEduTpVO) {
		return getSqlSession().insert("MemberDAO.doHighestEduInsert", highestEduTpVO);
	}

	@Override
	public int doHighestEduModify(HighestEduTpVO highestEduTpVO) {
		return getSqlSession().update("MemberDAO.doHighestEduModify", highestEduTpVO);
	}

	@Override
	public int doHighestEduDelete(String cdId) {
		return getSqlSession().delete("MemberDAO.doHighestEduDelete", cdId);
	}

	@Override
	public int isExistCdNmData(GrdtTpVO grdtTpVO) {
		return getSqlSession().selectOne("MemberDAO.isExistCdNmData", grdtTpVO);
	}

	/* 로그인 */

	@Override
	public String isExistId(String id) {
		return getSqlSession().selectOne("MemberDAO.isExistId", id);
	}

	@Override
	public boolean isAccountLock(String id) {
		return ("Y").equals(getSqlSession().selectOne("MemberDAO.isAccountLock", id));
	}

	@Override
	public int loginSuccess(String id) {
		return getSqlSession().update("MemberDAO.loginSuccess", id);
	}

	@Override
	public String needToChangPassword(String id) {
		return getSqlSession().selectOne("MemberDAO.needToChangPassword", id);
	}

	@Override
	public int plusLoginFailCount(String id) {
		return getSqlSession().update("MemberDAO.plusLoginFailCount", id);
	}

	@Override
	public int updateAccountLock(String id) {
		return getSqlSession().update("MemberDAO.updateAccountLock", id);
	}

	@Override
	public String getSaltById(String id) {
		return getSqlSession().selectOne("MemberDAO.getSaltById", id);
	}

	@Override
	public MemberVO login(MemberVO loginVO) {
		return getSqlSession().selectOne("MemberDAO.login", loginVO);
	}

	@Override
	public String isResign(String id) {
		return getSqlSession().selectOne("MemberDAO.isResign", id);
	}
	
	@Override
	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().update("MemberDAO.stampLogoutTime", newLoginHistoryVO);
	}
	
	@Override
	public int stampLogoutTimeByMemberId(String memberId) {
		return getSqlSession().update("MemberDAO.stampLogoutTimeByMemberId", memberId);
	}
	
	@Override
	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().insert("MemberDAO.stampLoginTime", newLoginHistoryVO);
	}
	
	@Override
	public int nextLoginHistorySeq() {
		return getSqlSession().selectOne("MemberDAO.nextLoginHistorySeq");
	}

	@Override
	public List<CodeMngVO> getAllCodeMngList() {
		return getSqlSession().selectList("MemberDAO.getAllCodeMngList");
	}

	@Override
	public int doCodeMngDelete(String cdId) {
		return getSqlSession().delete("MemberDAO.doCodeMngDelete", cdId);
	}

	@Override
	public int doCodeMngModify(CodeMngVO codeMngVO) {
		return getSqlSession().update("MemberDAO.doCodeMngModify", codeMngVO);
	}

	@Override
	public int doCodeMngInsert(CodeMngVO codeMngVO) {
		return getSqlSession().insert("MemberDAO.doCodeMngInsert", codeMngVO);
	}

}
