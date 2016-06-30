package com.ktds.sems.file.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.file.dao.FileDAO;
import com.ktds.sems.file.vo.FileVO;

public class FileDAOImpl  extends SqlSessionDaoSupport implements FileDAO {

	@Override
	public int doWriteFile(FileVO fileVO) {
		
		return getSqlSession().insert("FileDAO.doWriteFile", fileVO);
				
				
	}

	@Override
	public int updateFile(FileVO fileVO) {
		return getSqlSession().update("FileDAO.updateFile", fileVO);
	}

	
}
