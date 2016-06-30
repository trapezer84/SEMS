package com.ktds.sems.pc.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.EducationPlaceSearchVO;
import com.ktds.sems.education.vo.EducationPlaceVO;
import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public class PcDAOImpl extends SqlSessionDaoSupport implements PcDAO {

	@Override
	public int getTotalUsedPcCount(UsedPcSearchVO usedPcSearchVO) {
		return getSqlSession().selectOne("PcDAO.getTotalUsedPcCount", usedPcSearchVO);
	}

	@Override
	public List<UsedPcVO> getUsedPcList(UsedPcSearchVO usedPcSearchVO) {
		return getSqlSession().selectList("PcDAO.getUsedPcList", usedPcSearchVO);
	}

	@Override
	public int getTotalReportedPcCount(ReportedPcSearchVO reportedPcSearchVO) {
		return getSqlSession().selectOne("PcDAO.getTotalReportedPcCount", reportedPcSearchVO);
	}

	@Override
	public List<ReportedPcVO> getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO) {
		return getSqlSession().selectList("PcDAO.getReportedPcListWithPaging", reportedPcSearchVO);
	}

	@Override
	public int changeReportedState(ReportedPcVO reportedPcVO) {
		return getSqlSession().update("PcDAO.changeReportedState", reportedPcVO);
	}

	@Override
	public int nextEducationPlaceSequence() {
		return getSqlSession().selectOne("PcDAO.nextEducationPlaceSequence");
	}

	@Override
	public int nextPcSequence() {
		return getSqlSession().selectOne("PcDAO.nextPcSequence");
	}

	@Override
	public List<EducationPlaceVO> getEducationPlaceList(EducationPlaceSearchVO eduPlaceSearchVO) {
		return getSqlSession().selectList("PcDAO.getEducationPlaceList", eduPlaceSearchVO);
	}

	@Override
	public String getSysdate() {
		return getSqlSession().selectOne("PcDAO.getSysdate");
	}

	@Override
	public void doRegistEduPlace(PcVO pcVO) {
		getSqlSession().insert("PcDAO.doRegistEduPlace",pcVO);
	}

	@Override
	public void doRegistPC(PcVO pcVO) {
		getSqlSession().insert("PcDAO.doRegistPC",pcVO);
	}

	@Override
	public int getTotalEduPlaceCount(EducationPlaceSearchVO eduPlaceSearchVO) {
		return getSqlSession().selectOne("PcDAO.getTotalEduPlaceCount", eduPlaceSearchVO);
	}

	@Override
	public void doActionDeleteEduPlaceBeforePCFKUpdate(String educationPlaceId) {
		getSqlSession().update("PcDAO.doActionDeleteEduPlaceBeforePCFKUpdate", educationPlaceId);
	}

	@Override
	public void doActionDeleteEduPlace(String educationPlaceId) {
		getSqlSession().delete("PcDAO.doActionDeleteEduPlace", educationPlaceId);
	}

	@Override
	public void doActionDeleteEduPC(String pcId) {
		getSqlSession().delete("PcDAO.doActionDeleteEduPC", pcId);
	}

	@Override
	public PcVO callIdJunitTest(PcVO pcVO) {
		return getSqlSession().selectOne("PcDAO.callIdJunitTest", pcVO);
	}

}
