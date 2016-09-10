package com.starface.domain.vo;

import org.springframework.web.multipart.MultipartFile;

import com.starface.domain.Group;

public class GroupVo extends Group{

	private MultipartFile icon;
	
	private Integer userId;

	/**
	 * @return the icon
	 */
	public MultipartFile getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(MultipartFile icon) {
		this.icon = icon;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}