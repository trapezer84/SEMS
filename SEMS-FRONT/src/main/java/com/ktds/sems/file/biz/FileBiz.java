package com.ktds.sems.file.biz;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ktds.sems.file.vo.FileVO;

public interface FileBiz {

	public List<FileVO> getOneFileId(String educationId);
	
	public boolean doWriteFile(FileVO fileVO);
	
	public boolean updateFile(FileVO fileVO);
	
	public void doUploadAndWriteFiles(MultipartHttpServletRequest request, String articleId);
	
	public List<FileVO> getAllFilesByArticleId(String articleId);

	public FileVO getOneFileByFileId(String fileId);

	public boolean deleteFile(String articleId);

	public List<FileVO> getFileList(FileVO file);
}
