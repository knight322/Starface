package com.starface.domain;

import java.util.Date;

/**
 * 用户关系
 * @author chancore
 *
 */
public class UserRelation {
	
	private int id;
	/**添加人ID**/
	private int owner_username;
	/**被添加人ID**/
	private int friend_username;
	/**状态,0:未同意,1:已同意,2:删除**/
	private int status;
	/**创建时间**/
	private Date creatTime;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the owner_username
	 */
	public int getOwner_username() {
		return owner_username;
	}
	/**
	 * @param owner_username the owner_username to set
	 */
	public void setOwner_username(int owner_username) {
		this.owner_username = owner_username;
	}
	/**
	 * @return the friend_username
	 */
	public int getFriend_username() {
		return friend_username;
	}
	/**
	 * @param friend_username the friend_username to set
	 */
	public void setFriend_username(int friend_username) {
		this.friend_username = friend_username;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the creatTime
	 */
	public Date getCreatTime() {
		return creatTime;
	}
	/**
	 * @param creatTime the creatTime to set
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
	

}
