package com.starface.domain.vo;

import com.starface.domain.Inform;

public class InformVo extends Inform {

	
	private Integer start;
	private Integer limit = 20;
	private String userName;
	private String informType;
	private String illegalName;
	private String createTimeView;
	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the informType
	 */
	public String getInformType() {
		return informType;
	}
	/**
	 * @param informType the informType to set
	 */
	public void setInformType(String informType) {
		this.informType = informType;
	}
	/**
	 * @return the illegalName
	 */
	public String getIllegalName() {
		return illegalName;
	}
	/**
	 * @param illegalName the illegalName to set
	 */
	public void setIllegalName(String illegalName) {
		this.illegalName = illegalName;
	}
	/**
	 * @return the createTimeView
	 */
	public String getCreateTimeView() {
		return createTimeView;
	}
	/**
	 * @param createTimeView the createTimeView to set
	 */
	public void setCreateTimeView(String createTimeView) {
		this.createTimeView = createTimeView;
	}
	
}
