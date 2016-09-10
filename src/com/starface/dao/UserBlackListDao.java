package com.starface.dao;

import java.util.List;

import com.starface.domain.UserBlacklist;
import com.starface.domain.po.UserPo;

/**
 * 黑名单
 * @author chancore
 *
 */
public interface UserBlackListDao {
	
	/**
	 * 添加黑名单
	 * @param userBlacklist
	 * @return
	 */
	public int addBlackList(UserBlacklist userBlacklist);
	
	/**
	 * 查找黑名单
	 * @param userBlacklist
	 * @return
	 */
	public UserBlacklist getBlackList(UserBlacklist userBlacklist);
	
	/**
	 * 黑名单列表
	 * @param userBlacklist
	 * @return
	 */
	public List<UserPo>  blackListList(UserBlacklist userBlacklist);
	
	/**
	 * 移除黑名单
	 * @param userBlacklist
	 * @return
	 */
	public int deleteBlackList(UserBlacklist userBlacklist);

}
