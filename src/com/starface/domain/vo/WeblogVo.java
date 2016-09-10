package com.starface.domain.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.starface.domain.Weblog;

public class WeblogVo extends Weblog{
	
	private List <MultipartFile> file;

	/**
	 * @return the file
	 */
	public List<MultipartFile> getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	
}