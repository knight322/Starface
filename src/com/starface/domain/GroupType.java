package com.starface.domain;

public class GroupType {

	private Integer id;
	/**父类ID**/
	private Integer parentId;
	/**级别s**/
	private Integer level;
	/**类别名称**/
	private String label;
	/**是否停用,0:否,1:是**/
	private Integer isStop;
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
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the isStop
	 */
	public Integer getIsStop() {
		return isStop;
	}
	/**
	 * @param isStop the isStop to set
	 */
	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
	}
	
}
