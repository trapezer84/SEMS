package com.ktds.sems.pc.vo;

import com.ktds.sems.education.vo.EducationPlaceVO;

public class PcVO extends EducationPlaceVO {

	private String pcId;
	private String pcName;
	private String ip;

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
