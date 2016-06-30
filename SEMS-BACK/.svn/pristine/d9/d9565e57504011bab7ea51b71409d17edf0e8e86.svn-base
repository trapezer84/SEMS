package com.ktds.sems.teacher.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public interface TeacherBiz {
	
	public TeacherVO getTeacherInfo(String memberId);

	public List<EducationHistoryVO> getTeacherEducationHistory(String memberId);

	public List<ProjectHistoryVO> getTeacherProjectHistory(String memberId);

	public List<TeacherBookVO> getTeacherBook(String memberId);

	public double getTeacherEducationGrade(String memberId);

	public List<EducationVO> getEducationHistory(String memberId);

	public int getTotalTeacherCount(HttpServletRequest request);

	public List<TeacherVO> getAllTeacher(TeacherSearchVO searchVO);

	public boolean doDeleteTeacher(String memberId);

	public boolean doDeleteProjectHistory(String memberId);

	public boolean doDeleteEducationHistory(String memberId);

	public boolean doDeleteTeacherBook(String memberId);

	public TeacherVO getOneTeacherInfo(String memberId);

	public List<TeacherBookVO> getOneTeacherBookInfo(String memberId);

	public List<ProjectHistoryVO> getOneTeacherProjectHistoryVO(String memberId);

	public List<EducationHistoryVO> getOneEducationHistoryVO(String memberId);

	public boolean doTeacherInfoModifyAction(TeacherVO teacherVO);

	public boolean deleteTeacherBookEduProHistory(String id, String type);
	
	public List<MemberVO> getTeacherMemberInfo();

	public boolean doInsertNewTeacher(TeacherVO teacherVO);

}
