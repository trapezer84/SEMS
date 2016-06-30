package com.ktds.sems.pc.biz.impl;

import java.util.List;

import com.ktds.sems.education.vo.EducationPlaceSearchVO;
import com.ktds.sems.education.vo.EducationPlaceVO;
import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public class PcBizImpl implements PcBiz {

	private PcDAO pcDAO;

	public void setPcDAO(PcDAO pcDAO) {
		this.pcDAO = pcDAO;
	}

	@Override
	public int getTotalUsedPcCount(UsedPcSearchVO usedPcSearchVO) {
		return pcDAO.getTotalUsedPcCount(usedPcSearchVO);
	}

	@Override
	public List<UsedPcVO> getUsedPcList(UsedPcSearchVO usedPcSearchVO) {
		return pcDAO.getUsedPcList(usedPcSearchVO);
	}

	@Override
	public int getTotalReportedPcCount(ReportedPcSearchVO reportedPcSearchVO) {
		return pcDAO.getTotalReportedPcCount(reportedPcSearchVO);
	}

	@Override
	public List<ReportedPcVO> getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO) {
		return pcDAO.getReportedPcListWithPaging(reportedPcSearchVO);
	}

	@Override
	public boolean changeReportedState(ReportedPcVO reportedPcVO) {
		return pcDAO.changeReportedState(reportedPcVO) > 0;
	}

	@Override
	public String doRegistEduPlace(PcVO pcVO) {
		pcVO.setEducationPlaceId(eduPlaceIDReconstructor());
		pcDAO.doRegistEduPlace(pcVO);
		
		return pcVO.getEducationPlaceId();
	}

	@Override
	public void doRegistPC(PcVO pcVO) {
		
		String educationPlaceId = pcVO.getEducationPlaceId();
		
		for(PcVO writePc : pcVO.getPcList() ) {
			
			writePc.setPcId(pcIDReconstructor());
			writePc.setEducationPlaceId(educationPlaceId);
			
			pcDAO.doRegistPC(writePc);
		}
	}

	@Override
	public List<EducationPlaceVO> getEducationPlaceList(EducationPlaceSearchVO eduPlaceSearchVO) {
		return pcDAO.getEducationPlaceList(eduPlaceSearchVO);
	}
	
	private String eduPlaceIDReconstructor() {
		String resultId = "";
		String sysdate = pcDAO.getSysdate();
		int eduPlaceSeq = pcDAO.nextEducationPlaceSequence();
		
		resultId = "EP-"+sysdate+"-"+lpad(eduPlaceSeq, 6);
		return resultId;
	}
	
	private String pcIDReconstructor() {
		String resultId = "";
		String sysdate = pcDAO.getSysdate();
		int pcSeq = pcDAO.nextPcSequence();
		
		resultId = "PI-"+sysdate+"-"+lpad(pcSeq, 6);
		return resultId;
	}
	
	private String lpad(int allSequencesReConstructor, int size) {
		String sequences = String.valueOf(allSequencesReConstructor);
		int length = sequences.length();
		int needLength = size - length;
		
		for (int i = 0; i < needLength; i++) {
			sequences = "0"+sequences;
		}
		return sequences;
	}

	@Override
	public int getTotalEduPlaceCount(EducationPlaceSearchVO eduPlaceSearchVO) {
		return pcDAO.getTotalEduPlaceCount(eduPlaceSearchVO);
	}

	@Override
	public void doActionDeleteEduPlace(String educationPlaceId) {
		pcDAO.doActionDeleteEduPlaceBeforePCFKUpdate(educationPlaceId);
		pcDAO.doActionDeleteEduPlace(educationPlaceId);
	}

	@Override
	public void doActionDeleteEduPC(String pcId) {
		pcDAO.doActionDeleteEduPC(pcId);
	}

	@Override
	public PcVO callIdJunitTest(PcVO pcVO) {
		return pcDAO.callIdJunitTest(pcVO);
	}
}
