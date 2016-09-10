package com.starface.domain;

import java.util.Date;

public class Inform {

	
	private Integer id;
	private Integer illegalId;
	private Integer objId;
	private Integer userId;
	private Integer type;
	private String remark;
	private Date createTime;
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
	 * @return the illegalId
	 */
	public Integer getIllegalId() {
		return illegalId;
	}
	/**
	 * @param illegalId the illegalId to set
	 */
	public void setIllegalId(Integer illegalId) {
		this.illegalId = illegalId;
	}
	/**
	 * @return the objId
	 */
	public Integer getObjId() {
		return objId;
	}
	/**
	 * @param objId the objId to set
	 */
	public void setObjId(Integer objId) {
		this.objId = objId;
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
