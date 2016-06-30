package com.ktds.sems.pc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public class PcDAOImpl extends SqlSessionDaoSupport implements PcDAO {

	@Override
	public List<EducationVO> getEduListByMember(MemberVO memberVO) {
		return getSqlSession().selectList("PcDAO.getEduListByMember", memberVO);
	}

	/**
	 * PC 고장 신고하는 기능
	 * 이기연
	 */
	public int reportProblemPc(ReportedPcVO reportedPcVO) {
		return getSqlSession().insert("PcDAO.reportProblemPc", reportedPcVO);
	}
	
	@Override
	public int getNextReportedPcIdSeq() {
		return getSqlSession().selectOne("PcDAO.getNextReportedPcIdSeq");
	}
	
	@Override
	public String getNowDate() {
		return getSqlSession().selectOne("PcDAO.getNowDate");
	}

	@Override
	public List<UsedPcVO> getUsedPcListByMember(MemberVO memberVO) {
		return getSqlSession().selectList("PcDAO.getUsedPcListByMember", memberVO);
	}

	@Override
	public String getPcIdByIp(String pcIp) {
		return getSqlSession().selectOne("PcDAO.getPcIdByIp", pcIp);
	}

	@Override
	public int doRegisterMyPc(UsedPcVO usedPcVO) {
		return getSqlSession().insert("PcDAO.doRegisterMyPc", usedPcVO);
	}

	@Override
	public void doDeleteMyPc(String pcId) {
		getSqlSession().delete("PcDAO.doDeleteMyPc", pcId);
	}

	@Override
	public List<EducationVO> getEduListExceptUsed(MemberVO memberVO) {
		return getSqlSession().selectList("PcDAO.getEduListExceptUsed", memberVO);
	}

	@Override
	public int getTotalMyReportedPcCount(ReportedPcSearchVO reportedPcSearchVO) {
		return getSqlSession().selectOne("PcDAO.getTotalMyReportedPcCount", reportedPcSearchVO);
	}

	@Override
	public List<ReportedPcVO> getMyReportedPcList(ReportedPcSearchVO reportedPcSearchVO) {
		return getSqlSession().selectList("PcDAO.getMyReportedPcList", reportedPcSearchVO);
	}

}
