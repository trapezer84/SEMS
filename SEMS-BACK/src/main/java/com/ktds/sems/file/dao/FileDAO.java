package com.ktds.sems.file.dao;

import com.ktds.sems.file.vo.FileVO;

public interface FileDAO {

	public int doWriteFile(FileVO fileVO);

	public int updateFile(FileVO fileVO);

}
