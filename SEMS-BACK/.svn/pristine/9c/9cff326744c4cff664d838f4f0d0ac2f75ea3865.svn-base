package com.ktds.sems.file.biz.impl;

import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.dao.FileDAO;
import com.ktds.sems.file.vo.FileVO;

public class FileBizImpl implements FileBiz {

	private FileDAO fileDAO;

	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	@Override
	public boolean doWriteFile(FileVO fileVO) {
		return fileDAO.doWriteFile(fileVO) > 0  ;
	}

	@Override
	public boolean updateFile(FileVO fileVO) {
		return fileDAO.updateFile(fileVO) > 0;
	}
	
	
}
