package com.ktds.sems.teacher.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public interface TeacherDAO {
	
	public TeacherVO getTeacherInfo(String memberId);

	public List<EducationHistoryVO> getTeacherEducationHistory(String memberId);

	public List<ProjectHistoryVO> getTeacherProjectHistory(String memberId);

	public List<TeacherBookVO> getTeacherBook(String memberId);

	public List<EducationVO> getEducationHistory(String memberId);

	public double getTeacherEducationGrade(String memberId);

	public int getTotalTeacherCount(Map<String,String> searchInfo);

	public List<TeacherVO> getAllTeacher(TeacherSearchVO searchVO);

	public int doDeleteTeacher(String memberId);

	public int doDeleteProjectHistory(String memberId);

	public int doDeleteEducationHistory(String memberId);

	public int doDeleteTeacherBook(String memberId);

	public TeacherVO getOneTeacherInfo(String memberId);

	public List<TeacherBookVO> getOneTeacherBookInfo(String memberId);

	public List<ProjectHistoryVO> getOneTeacherProjectHistoryVO(String memberId);

	public List<EducationHistoryVO> getOneEducationHistoryVO(String memberId);

	public int doTeacherInfoModifyAction(TeacherVO teacherVO);
	
	public int doTeacherBookModifyAction(TeacherBookVO teacherBookVO);
	
	public int doTeacherProjectModifyAction(ProjectHistoryVO projectHistoryVO);
	
	public int doTeacherEducationModifyAction(EducationHistoryVO teacherEduHistoryVO);

	public int deleteTeacherBookEduProHistory(HashMap<String, Object> map);

	public String getOneTeacherId();
	
	public List<MemberVO> getTeacherMemberInfo();

	public int doInsertTeacherBookHis(TeacherBookVO teacherBookVO);

	public int doInsertTeacherProHis(ProjectHistoryVO projectHistoryVO);

	public int doInsertTeacherEduHis(EducationHistoryVO teacherEduHistoryVO);

	public int doInsertNewTeacher(TeacherVO teacherVO);

	public int bookNextSeq();

	public int projHisNextSeq();

	public int eduHisNextSeq();
	
	public String nowDate();

}
