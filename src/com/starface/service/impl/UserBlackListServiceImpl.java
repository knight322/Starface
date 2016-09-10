package com.starface.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.starface.dao.UserBlackListDao;
import com.starface.demo.EasemobIMUsers;
import com.starface.domain.UserBlacklist;
import com.starface.domain.po.UserPo;
import com.starface.frame.core.utils.ClientUtils;
import com.starface.service.UserBlackListService;

@Service
public class UserBlackListServiceImpl implements UserBlackListService{

	@Autowired
	private UserBlackListDao userBlackListDao;
	@Autowired
	private EasemobIMUsers easemobIMUsers;
	
	@Override
	public String addBlackList(UserBlacklist userBlacklist) {
		//判断是否已经存在于黑名单中
		UserBlacklist userBl = userBlackListDao.getBlackList(userBlacklist);
		if(null != userBl){
			return ClientUtils.failure("已经存在于黑名单中");
		}
		userBlackListDao.addBlackList(userBlacklist);
		//解除好友关系
		easemobIMUsers.deleteFriendSingle(userBlacklist.getUserId().toString(), userBlacklist.getBlacklistUser().toString());
		return ClientUtils.success("添加成功");
	}

	@Override
	public String blackListList(UserBlacklist userBlacklist) {
		List <UserPo>list = userBlackListDao.blackListList(userBlacklist);
		Map <String, List <UserPo>>result = new HashMap<String, List <UserPo>>();
		result.put("userBlackList", list);
		return ClientUtils.success("查询成功", result);
	}

	@Override
	public String deleteBlackList(UserBlacklist userBlacklist) {
		userBlackListDao.deleteBlackList(userBlacklist);
		return ClientUtils.success("删除成功");
	}
	
	
	
	

}
