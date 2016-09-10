package com.starface.domain.vo;
/** 
 * @author 作者 : chenxuan
 * @version 创建时间：2015年12月26日 下午2:11:37 
 * 类说明 
 */
public class UserRelationData {
	
	private Integer ownerUserId;
	private Integer friendUserId;
	/**
	 * 0:没有关系
	 * 1:好友关系
	 * 2:关注关系
	 */
	private Integer relation;
	private Integer nextCursor;
	/**
	 * @return the ownerUserId
	 */
	public Integer getOwnerUserId() {
		return ownerUserId;
	}
	/**
	 * @param ownerUserId the ownerUserId to set
	 */
	public void setOwnerUserId(Integer ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	/**
	 * @return the friendUserId
	 */
	public Integer getFriendUserId() {
		return friendUserId;
	}
	/**
	 * @param friendUserId the friendUserId to set
	 */
	public void setFriendUserId(Integer friendUserId) {
		this.friendUserId = friendUserId;
	}
	/**
	 * @return the relation
	 */
	public Integer getRelation() {
		return relation;
	}
	/**
	 * @param relation the relation to set
	 */
	public void setRelation(Integer relation) {
		this.relation = relation;
	}
	/**
	 * @return the nextCursor
	 */
	public Integer getNextCursor() {
		return nextCursor;
	}
	/**
	 * @param nextCursor the nextCursor to set
	 */
	public void setNextCursor(Integer nextCursor) {
		this.nextCursor = nextCursor;
	}
	

}
