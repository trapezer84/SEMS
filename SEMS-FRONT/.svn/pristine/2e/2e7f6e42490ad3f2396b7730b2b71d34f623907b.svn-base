package com.ktds.sems.pc.biz.impl;

import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public class PcBizImpl implements PcBiz {
	private PcDAO pcDAO;

	public void setPcDAO(PcDAO pcDAO) {
		this.pcDAO = pcDAO;
	}

	@Override
	public List<EducationVO> getEduListByMember(MemberVO memberVO) {
		return pcDAO.getEduListByMember(memberVO);
	}

	/**
	 * PC 고장 신고하는 기능
	 * 이기연
	 */
	@Override
	public boolean reportProblemPc(ReportedPcVO reportedPcVO) {
		return pcDAO.reportProblemPc(reportedPcVO) > 0;
	}

	@Override
	public String getNowDate() {
		return pcDAO.getNowDate();
	}

	@Override
	public int getNextReportedPcIdSeq() {
		return pcDAO.getNextReportedPcIdSeq();
	}

	@Override
	public List<UsedPcVO> getUsedPcListByMember(MemberVO memberVO) {
		return pcDAO.getUsedPcListByMember(memberVO);
	}

	@Override
	public String getPcIdByIp(String pcIp) {
		return pcDAO.getPcIdByIp(pcIp);
	}

	@Override
	public int doRegisterMyPc(UsedPcVO usedPcVO) {
		return pcDAO.doRegisterMyPc(usedPcVO);
	}

	@Override
	public void doDeleteMyPc(String pcId) {
		 pcDAO.doDeleteMyPc(pcId);		
	}

	@Override
	public List<EducationVO> getEduListExceptUsed(MemberVO memberVO) {
		return pcDAO.getEduListExceptUsed(memberVO);
	}

	@Override
	public int getTotalMyReportedPcCount(ReportedPcSearchVO reportedPcSearchVO) {
		return pcDAO.getTotalMyReportedPcCount(reportedPcSearchVO);
	}

	@Override
	public List<ReportedPcVO> getMyReportedPcList(ReportedPcSearchVO reportedPcSearchVO) {
		return pcDAO.getMyReportedPcList(reportedPcSearchVO);
	}

}
