package com.starface.doa.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starface.dao.UserBlackListDao;
import com.starface.domain.UserBlacklist;
import com.starface.domain.po.UserPo;

@Repository
public class UserBlackListDaoImpl implements UserBlackListDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int addBlackList(UserBlacklist userBlacklist) {
		return sqlSessionTemplate.insert("com.starface.domain.UserBlacklistMapper.insert",userBlacklist);
	}

	@Override
	public UserBlacklist getBlackList(UserBlacklist userBlacklist) {
		return sqlSessionTemplate.selectOne("com.starface.domain.UserBlacklistMapper.selectByPrimaryUserBlacklist", userBlacklist);
	}

	@Override
	public List<UserPo> blackListList(UserBlacklist userBlacklist) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.selectByPrimaryUserBlacklistList", userBlacklist);
	}

	@Override
	public int deleteBlackList(UserBlacklist userBlacklist) {
		return sqlSessionTemplate.delete("com.starface.domain.UserBlacklistMapper.deleteByPrimaryUserBlacklist", userBlacklist);
	}
	
	
	
	
	
	

}
