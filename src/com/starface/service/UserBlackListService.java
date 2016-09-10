package com.starface.service;

import java.util.List;

import com.starface.domain.UserBlacklist;
import com.starface.domain.po.UserPo;

/**
 * 黑名单
 * @author chancore
 *
 */
public interface UserBlackListService {
	
	/**
	 * 添加黑名单
	 * @param userBlacklist
	 * @return
	 */
	public String addBlackList(UserBlacklist userBlacklist);
	
	/**
	 * 黑名单列表
	 * @param userBlacklist
	 * @return
	 */
	public String  blackListList(UserBlacklist userBlacklist);
	
	
	/**
	 * 移除黑名单
	 * @param userBlacklist
	 * @return
	 */
	public String deleteBlackList(UserBlacklist userBlacklist);
}
