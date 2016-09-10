package com.starface.domain;

public class WeblogFile {
	
	private Integer id;
	private Integer weblogId;
	private String path;
	private String pathSmall;
	private Integer type;
	private Long createTime;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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
	 * @return the pathSmall
	 */
	public String getPathSmall() {
		return pathSmall;
	}
	/**
	 * @param pathSmall the pathSmall to set
	 */
	public void setPathSmall(String pathSmall) {
		this.pathSmall = pathSmall;
	}
	
	
	
}