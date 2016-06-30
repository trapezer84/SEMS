package com.ktds.sems.pc.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationPlaceListVO;
import com.ktds.sems.education.vo.EducationPlaceSearchVO;
import com.ktds.sems.education.vo.EducationPlaceVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.service.PcService;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcListVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcListVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

import kr.co.hucloud.utilities.web.Paging;

public class PcServiceImpl implements PcService {

	private PcBiz pcBiz;

	private Logger logger = LoggerFactory.getLogger(PcServiceImpl.class);

	public void setPcBiz(PcBiz pcBiz) {
		this.pcBiz = pcBiz;
	}

	@Override
	public ModelAndView getUsedPcList(UsedPcSearchVO usedPcSearchVO) {

		UsedPcListVO usedPcListVO = new UsedPcListVO();
		Paging paging = new Paging();
		usedPcListVO.setPaging(paging);

		if (usedPcSearchVO == null) {
			usedPcSearchVO = new UsedPcSearchVO();
			usedPcSearchVO.setPageNo(0);
		}

		paging.setPageNumber(usedPcSearchVO.getPageNo() + "");

		int totalCount = pcBiz.getTotalUsedPcCount(usedPcSearchVO);
		paging.setTotalArticleCount(totalCount);

		usedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		usedPcSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<UsedPcVO> usedPcList = pcBiz.getUsedPcList(usedPcSearchVO);
		usedPcListVO.setUsedPcList(usedPcList);

		ModelAndView view = new ModelAndView();
		view.setViewName("pc/usedPcList");
		view.addObject("usedPcListVO", usedPcListVO);
		view.addObject("usedPcSearchVO", usedPcSearchVO);

		return view;
	}

	@Override
	public ModelAndView getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO) {

		ReportedPcListVO reportedPcListVO = new ReportedPcListVO();
		Paging paging = new Paging();
		reportedPcListVO.setPaging(paging);

		if (reportedPcSearchVO == null) {
			reportedPcSearchVO = new ReportedPcSearchVO();
			reportedPcSearchVO.setPageNo(0);
		}
		paging.setPageNumber(reportedPcSearchVO.getPageNo() + "");

		int totalCount = pcBiz.getTotalReportedPcCount(reportedPcSearchVO);
		paging.setTotalArticleCount(totalCount);

		reportedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportedPcSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<ReportedPcVO> reportedPcList = pcBiz.getReportedPcListWithPaging(reportedPcSearchVO);
		reportedPcListVO.setReportedPcList(reportedPcList);

		ModelAndView view = new ModelAndView();
		view.setViewName("pc/reportedPcList");
		view.addObject("reportedPcListVO", reportedPcListVO);
		view.addObject("reportedPcSearchVO", reportedPcSearchVO);

		return view;
	}

	@Override
	public String changeReportedState(ReportedPcVO reportedPcVO) {

		String reportedState = reportedPcVO.getReportedState();

		if (reportedState.equals("PC_WT_IN")) {
			reportedPcVO.setReportedState("PC_PB_CH");
		} else if (reportedState.equals("PC_PB_CH")) {
			reportedPcVO.setReportedState("PC_AC_IN");
		} else if (reportedState.equals("PC_AC_IN")) {
			reportedPcVO.setReportedState("PC_AC_CP");
		} else {
			return "NO";
		}

		if (pcBiz.changeReportedState(reportedPcVO)) {
			return "OK";
		} else {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}

	@Override
	public ModelAndView educationPlaceSetting(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("pc/eduPlaceSet");
		return view;
	}

	@Override
	public ModelAndView doRegistClass(PcVO pcVO, HttpSession session) {
		
		ModelAndView view = new ModelAndView();
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
	
		if (memberVO.getMemberType().equals("ADM")) {
			
			// 강의실 등록
			pcVO.setEducationPlaceId(pcBiz.doRegistEduPlace(pcVO));
			
			// PC 등록
			if ( pcVO.getPcList().size() > 0 ) {
				pcBiz.doRegistPC(pcVO);
			}
			
			// TODO 공용 자원 등록
			
			view.setViewName("redirect:/eduPlaceList");
		} 
		else {
			throw new RuntimeException("등록권한이 없습니다.");
		}
		
		return view;
	}

	@Override
	public ModelAndView viewEducationPlaceList(EducationPlaceSearchVO eduPlaceSearchVO, int pageNo) {
		
		ModelAndView view = new ModelAndView();
		EducationPlaceListVO eduListVO = new EducationPlaceListVO();
		Paging paging = new Paging();
		eduListVO.setPaging(paging);
		
		int totalPlaceCount = pcBiz.getTotalEduPlaceCount(eduPlaceSearchVO);
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalPlaceCount);
		
		eduPlaceSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduPlaceSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EducationPlaceVO> eduPlaceList = pcBiz.getEducationPlaceList(eduPlaceSearchVO);
		eduListVO.setEduPlaceList(eduPlaceList);

		view.setViewName("education/eduPlaceList");
		view.addObject("eduListVO", eduListVO);

		return view;
	}

	@Override
	public ModelAndView doActionDeleteEduPlace(String educationPlaceId, HttpSession session, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		if ( memberVO.getId() != null) {
			if(memberVO.getMemberType().equals("ADM")){
				if(educationPlaceId != null) {
					pcBiz.doActionDeleteEduPlace(educationPlaceId);
					view.setViewName("education/eduPlaceList");
				} else {
					throw new RuntimeException("올바른 값을 적용시켜주세요.");
				}
			} else {
				try {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "권한없는 접속.");
				} catch (IOException io) {
					throw new RuntimeException(io.getMessage());
				}
			}
		} else {
			return new ModelAndView("redirect:/backend/");
		}
		return view;
	}

	@Override
	public ModelAndView doActionDeleteEduPC(String pcId, HttpSession session, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		if ( memberVO.getId() != null) {
			if(memberVO.getMemberType().equals("ADM")){
				if(pcId != null) {
					pcBiz.doActionDeleteEduPC(pcId);
					view.setViewName("education/eduPlaceList");
				} else {
					throw new RuntimeException("올바른 값을 적용시켜주세요.");
				}
			} else {
				try {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "권한없는 접속.");
				} catch (IOException io) {
					throw new RuntimeException(io.getMessage());
				}
			}
		} else {
			return new ModelAndView("redirect:/backend/");
		}
		return view;
	}

}
