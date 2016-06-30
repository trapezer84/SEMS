package com.ktds.sems.pc.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public interface PcBiz {

	public List<EducationVO> getEduListByMember(MemberVO memberVO);

	public List<UsedPcVO> getUsedPcListByMember(MemberVO memberVO);

	public boolean reportProblemPc(ReportedPcVO reportedPcVO);

	public String getPcIdByIp(String usedPcIp);

	public int doRegisterMyPc(UsedPcVO usedPcVO);

	public void doDeleteMyPc(String pcId);

	public List<EducationVO> getEduListExceptUsed(MemberVO memberVO);
	
	public String getNowDate();

	public int getNextReportedPcIdSeq();

	public int getTotalMyReportedPcCount(ReportedPcSearchVO reportedPcSearchVO);

	public List<ReportedPcVO> getMyReportedPcList(ReportedPcSearchVO reportedPcSearchVO);

}
