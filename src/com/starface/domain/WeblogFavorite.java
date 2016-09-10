package com.starface.domain;

public class WeblogFavorite {
	
	private Integer id;
	private Integer userId;
	private Integer weblogId;
	private Long createTime;
	private Integer isPrivate;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	/**
	 * @return the weblogId
	 */
	public Integer getWeblogId() {
		return weblogId;
	}
	/**
	 * @param weblogId the weblogId to set
	 */
	public void setWeblogId(Integer weblogId) {
		this.weblogId = weblogId;
	}
	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the isPrivate
	 */
	public Integer getIsPrivate() {
		return isPrivate;
	}
	/**
	 * @param isPrivate the isPrivate to set
	 */
	public void setIsPrivate(Integer isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	
}