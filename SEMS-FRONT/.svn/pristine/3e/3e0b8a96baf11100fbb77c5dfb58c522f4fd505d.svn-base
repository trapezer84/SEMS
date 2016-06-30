package com.ktds.sems.file.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.ktds.sems.file.dao.FileDAO;
import com.ktds.sems.file.vo.FileVO;

public class FileDAOImpl extends SqlSessionDaoSupport implements FileDAO {
	
	@Override
	public List<FileVO> getOneFileId(String educationId) {
		return getSqlSession().selectList("FileDAO.getOneFileId",educationId);
	}

	@Override
	public int insertFile(FileVO fileVO) {
		return getSqlSession().insert("FileDAO.insertFile", fileVO);
	}

	@Override
	public int doWriteFile(FileVO fileVO) {
		
		return getSqlSession().insert("FileDAO.doWriteFile", fileVO);
	}

	@Override
	public int updateFile(FileVO fileVO) {
		return getSqlSession().update("FileDAO.updateFile", fileVO);
	}

	@Override
	public List<FileVO> getAllFilesByArticleId(String articleId) {
		return getSqlSession().selectList("FileDAO.getAllFilesByArticleId", articleId);
	}

	@Override
	public FileVO getOneFileByFileId(String fileId) {
		return getSqlSession().selectOne("FileDAO.getOneFileByFileId", fileId);
	}

	@Override
	public int deleteFile(String articleId) {
		return getSqlSession().update("FileDAO.deleteFile", articleId);
	}

	@Override
	public List<FileVO> getFileList(FileVO file) {
		return getSqlSession().selectList("FileDAO.getFileList", file);
	}

}
