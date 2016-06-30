package com.ktds.sems.teacher.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.biz.TeacherBiz;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public class TeacherBizImpl implements TeacherBiz {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherDAO teacherDAO;
	
	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
	
	@Override
	public TeacherVO getTeacherInfo(String memberId) {
		return teacherDAO.getTeacherInfo(memberId);
	}

	@Override
	public List<EducationHistoryVO> getTeacherEducationHistory(String memberId) {
		return teacherDAO.getTeacherEducationHistory(memberId);
	}

	@Override
	public List<ProjectHistoryVO> getTeacherProjectHistory(String memberId) {
		return teacherDAO.getTeacherProjectHistory(memberId);
	}

	@Override
	public List<TeacherBookVO> getTeacherBook(String memberId) {
		return teacherDAO.getTeacherBook(memberId);
	}

	@Override
	public double getTeacherEducationGrade(String memberId) {
		return teacherDAO.getTeacherEducationGrade(memberId);
	}

	@Override
	public List<EducationVO> getEducationHistory(String memberId) {
		return teacherDAO.getEducationHistory(memberId);
	}

	@Override
	public int getTotalTeacherCount(HttpServletRequest request) {
		
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		Map<String,String> searchInfo = new HashMap<String,String>();
		searchInfo.put("searchType", searchType);
		searchInfo.put("searchKeyword", searchKeyword);
		
		return teacherDAO.getTotalTeacherCount(searchInfo);
	}

	@Override
	public List<TeacherVO> getAllTeacher(TeacherSearchVO searchVO) {
		return teacherDAO.getAllTeacher(searchVO);
	}


	@Override
	public boolean doDeleteTeacher(String memberId) {
		return  teacherDAO.doDeleteTeacher(memberId) > 0;
	}

	@Override
	public boolean doDeleteProjectHistory(String memberId) {
		
		return teacherDAO.doDeleteProjectHistory(memberId) > 0;
	}

	@Override
	public boolean doDeleteEducationHistory(String memberId) {
		return teacherDAO.doDeleteEducationHistory(memberId) > 0;
	}

	@Override
	public boolean doDeleteTeacherBook(String memberId) {
		return teacherDAO.doDeleteTeacherBook(memberId) > 0; 
	}


	@Override
	public TeacherVO getOneTeacherInfo(String memberId) {
		return teacherDAO.getOneTeacherInfo(memberId);
	}

	@Override
	public List<TeacherBookVO> getOneTeacherBookInfo(String memberId) {
		return teacherDAO.getOneTeacherBookInfo(memberId);
	}

	@Override
	public List<ProjectHistoryVO> getOneTeacherProjectHistoryVO(String memberId) {
		return teacherDAO.getOneTeacherProjectHistoryVO(memberId);
	}

	@Override
	public List<EducationHistoryVO> getOneEducationHistoryVO(String memberId) {
		return teacherDAO.getOneEducationHistoryVO(memberId);
	}

	@Override
	public boolean doTeacherInfoModifyAction(TeacherVO teacherVO) {
		
		int result = 0;
		
		if ( teacherVO.getTeacherBookList() != null ) {
			List<TeacherBookVO> teacherBookList = teacherVO.getTeacherBookList();
			for (TeacherBookVO teacherBookVO : teacherBookList) {
				if(teacherBookVO.getId() == null ) {
					teacherBookVO.setMemberId(teacherVO.getMemberId());
					teacherBookVO.setId(getLpadeId(1));
					result = teacherDAO.doInsertTeacherBookHis(teacherBookVO);
					
				}else{
					result = teacherDAO.doTeacherBookModifyAction(teacherBookVO);
				}			
				if( result < 1 ){
					return false;
				}
			}
		}
		if ( teacherVO.getProjectHistoryList() != null ) {
			List<ProjectHistoryVO> projectHistoryList = teacherVO.getProjectHistoryList();
			for (ProjectHistoryVO projectHistoryVO : projectHistoryList) {	
				if(projectHistoryVO.getId() == null ) {
					projectHistoryVO.setMemberId(teacherVO.getMemberId());
					projectHistoryVO.setId(getLpadeId(2));
					result = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
				}else{
					result = teacherDAO.doTeacherProjectModifyAction(projectHistoryVO);
				}
				if( result < 1 ){
					return false;
				}
			}
		}
		if ( teacherVO.getEducationHistoryList() != null ) {
			List<EducationHistoryVO> educationHistoryList = teacherVO.getEducationHistoryList();
			for (EducationHistoryVO teacherEduHistoryVO : educationHistoryList) {
				if(teacherEduHistoryVO.getId() == null ) {
					teacherEduHistoryVO.setMemberId(teacherVO.getMemberId());
					teacherEduHistoryVO.setId(getLpadeId(3));
					result = teacherDAO.doInsertTeacherEduHis(teacherEduHistoryVO);
				}else{
					result = teacherDAO.doTeacherEducationModifyAction(teacherEduHistoryVO);
				}			
				if( result < 1 ){
					return false;
				}
			}
		}		
		return teacherDAO.doTeacherInfoModifyAction(teacherVO) > 0;
	}

	@Override
	public boolean deleteTeacherBookEduProHistory(String id, String type) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return teacherDAO.deleteTeacherBookEduProHistory(map) > 0;
	}

	@Override
	public List<MemberVO> getTeacherMemberInfo() {
		return teacherDAO.getTeacherMemberInfo();
	}

	@Override
	public boolean doInsertNewTeacher(TeacherVO teacherVO) {
		
		int result = 0;
		
		if ( teacherVO.getTeacherBookList() != null ) {
			List<TeacherBookVO> teacherBookList = teacherVO.getTeacherBookList();
			for (TeacherBookVO teacherBookVO : teacherBookList) {
				teacherBookVO.setMemberId(teacherVO.getMemberId());
				teacherBookVO.setId(getLpadeId(1));
				result = teacherDAO.doInsertTeacherBookHis(teacherBookVO);
				if( result < 1 ){
					return false;
				}
			}
		}
		if ( teacherVO.getProjectHistoryList() != null ) {
			List<ProjectHistoryVO> projectHistoryList = teacherVO.getProjectHistoryList();
			for (ProjectHistoryVO projectHistoryVO : projectHistoryList) {
				projectHistoryVO.setMemberId(teacherVO.getMemberId());
				projectHistoryVO.setId(getLpadeId(2));
				result = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
				if( result < 1 ){
					return false;
				}
			}
		}
		if ( teacherVO.getEducationHistoryList() != null ) {
			List<EducationHistoryVO> educationHistoryList = teacherVO.getEducationHistoryList();
			for (EducationHistoryVO teacherEduHistoryVO : educationHistoryList) {
				teacherEduHistoryVO.setMemberId(teacherVO.getMemberId());
				teacherEduHistoryVO.setId(getLpadeId(3));
				result = teacherDAO.doInsertTeacherEduHis(teacherEduHistoryVO);
				if( result < 1 ){
					return false;
				}
			}
		}
		
		return teacherDAO.doInsertNewTeacher(teacherVO) > 0;
	}
	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;
		
	}
	private String getLpadeId(int num) {
		int nextSeq = 0;
		String id = "";
		String nowDate = teacherDAO.nowDate();
		
		if ( num == 1 ) {
			nextSeq = teacherDAO.bookNextSeq();
			id = "TB-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		}else if ( num == 2 ) {
			nextSeq = teacherDAO.projHisNextSeq();
			id = "TPH-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		}else if ( num == 3 ) {
			nextSeq = teacherDAO.eduHisNextSeq();
			id = "TEH-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		}
		return id;
	}

}
