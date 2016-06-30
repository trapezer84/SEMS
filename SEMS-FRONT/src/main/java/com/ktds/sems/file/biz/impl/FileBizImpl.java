package com.ktds.sems.file.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.dao.FileDAO;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.team.biz.impl.TeamBizImpl;

import kr.co.hucloud.utilities.SHA256Util;

public class FileBizImpl implements FileBiz {
	private Logger logger = LoggerFactory.getLogger(FileBizImpl.class);
	private FileDAO fileDAO;
	
	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	@Override
	public List<FileVO> getOneFileId(String educationId) {
		return fileDAO.getOneFileId(educationId);
	}

	@Override
	public void doUploadAndWriteFiles(MultipartHttpServletRequest request, String articleId) {
		List<MultipartFile> files = request.getFiles("file");
		
		FileVO fileVO = null;
		
		if (files != null && files.size() > 0 && !files.get(0).getOriginalFilename().equals("")) {
			for (MultipartFile multipartFile : files) {
				
				fileVO = new FileVO();
				setFileNameAndPath(fileVO, multipartFile);
				fileVO.setArticleId(articleId);
				logger.info("파일테스트 : " + multipartFile);
				File uploadFile = new File(fileVO.getFileLocation());
				fileDAO.insertFile(fileVO);
				try {
					multipartFile.transferTo(uploadFile);
				} catch (IllegalStateException e) {
					throw new RuntimeException(e.getMessage());
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}

	private void setFileNameAndPath(FileVO fileVO, MultipartFile multipartFile) {
		String salt = SHA256Util.generateSalt();
		String originalFileName = multipartFile.getOriginalFilename();
		fileVO.setFileName(originalFileName);
		
		String[] fileName = originalFileName.split("\\."); 
		String fileExtension = "." + fileName[fileName.length-1];
		String newFilePath = "D:\\" + SHA256Util.getEncrypt(originalFileName, salt) + fileExtension;
		fileVO.setFileLocation(newFilePath);
	}

	@Override
	public boolean doWriteFile(FileVO fileVO) {
		return fileDAO.doWriteFile(fileVO) > 0  ;
	}

	@Override
	public boolean updateFile(FileVO fileVO) {
		return fileDAO.updateFile(fileVO) > 0;
	}

	@Override
	public List<FileVO> getAllFilesByArticleId(String articleId) {
		return fileDAO.getAllFilesByArticleId(articleId);
	}

	@Override
	public FileVO getOneFileByFileId(String fileId) {
		return fileDAO.getOneFileByFileId(fileId);
	}

	@Override
	public boolean deleteFile(String articleId) {
		return fileDAO.deleteFile(articleId) > 0;
	}

	@Override
	public List<FileVO> getFileList(FileVO file) {
		return fileDAO.getFileList(file);
	}

}
