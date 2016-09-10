package com.starface.doa.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starface.dao.GroupDao;
import com.starface.domain.Group;
import com.starface.domain.GroupLabel;
import com.starface.domain.GroupType;
import com.starface.domain.GroupUser;
import com.starface.domain.vo.GroupUserVo;
import com.starface.domain.vo.GroupVo;

@Repository
public class GroupDaoImpl implements GroupDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAME_SPACE = "com.starface.domain.GroupMapper.";
	
	@Override
	public int insertGroup(Group group) {
		return sqlSessionTemplate.insert(NAME_SPACE+"insertGroup", group);
	}

	@Override
	public int updateGroupUserCount(Group group) {
		
		return sqlSessionTemplate.update(NAME_SPACE+"updateGroupUserCount", group);
	}

	@Override
	public int deleteGroupById(Group group) {
		
		return sqlSessionTemplate.delete(NAME_SPACE+"deleteGroupById", group);
	}

	@Override
	public int insertGroupUser(GroupUserVo groupUserVo) {
		
		return sqlSessionTemplate.insert(NAME_SPACE+"insertGroupUser", groupUserVo);
	}
	
	@Override
	public int insertGroupOneUser(GroupUserVo groupUserVo) {
		
		return sqlSessionTemplate.insert(NAME_SPACE+"insertGroupOneUser", groupUserVo);
	}

	@Override
	public int deleteGroupUserById(com.starface.domain.GroupUser groupUser) {
		
		return sqlSessionTemplate.delete(NAME_SPACE+"deleteGroupUserById", groupUser);
	}

	@Override
	public int deleteGroupUserByGroupId(com.starface.domain.GroupUser groupUser) {
		
		return sqlSessionTemplate.delete(NAME_SPACE+"deleteGroupUserByGroupId", groupUser);
	}

	@Override
	public Group getGroupById(Group group) {
		
		return sqlSessionTemplate.selectOne(NAME_SPACE+"getGroupById",group);
	}
	
	@Override
	public Group getGroupByGroupId(Group group) {
		
		return sqlSessionTemplate.selectOne(NAME_SPACE+"getGroupByGroupId",group);
	}

	@Override
	public List<GroupType> selectGroupTypeList(GroupType groupType) {
		
		return this.sqlSessionTemplate.selectList(NAME_SPACE+"selectGroupTypeList",groupType);
	}
	
	@Override
	public int updateGroup(Group group) {
		
		return sqlSessionTemplate.update(NAME_SPACE+"updateGroup", group);
	}

	@Override
	public int groupAddLabel(GroupLabel groupLabel) {
		
		return sqlSessionTemplate.update(NAME_SPACE+"groupAddLabel", groupLabel);
	}

	@Override
	public int deleteGroupLabel(GroupLabel groupLabel) {
		return sqlSessionTemplate.delete(NAME_SPACE+"deleteGroupLabel",groupLabel);
	}

	@Override
	public List<GroupUser> selectGroupUserInUserId(GroupUserVo groupUserVo) {
		
		return sqlSessionTemplate.selectList(NAME_SPACE+"selectGroupUserInUserId", groupUserVo);
	}
	
	@Override
	public Integer selectGroupUserCount(GroupUserVo groupUserVo) {
		
		return (Integer)sqlSessionTemplate.selectOne(NAME_SPACE+"selectGroupUserCount", groupUserVo);
	}

	@Override
	public int deleteGroupUser(GroupUserVo groupUserVo) {
		return sqlSessionTemplate.delete(NAME_SPACE+"deleteGroupUser", groupUserVo);
	}
	
	@Override
	public int deleteGroupByGroupIdAndUserId(GroupUserVo groupUserVo) {
		return sqlSessionTemplate.delete(NAME_SPACE+"deleteGroupByGroupIdAndUserId", groupUserVo);
	}

	@Override
	public List<Group> selectGroupListByUserId(GroupUserVo groupUserVo) {
		return sqlSessionTemplate.selectList(NAME_SPACE+"selectGroupListByUserId", groupUserVo);
	}

	@Override
	public List<GroupUserVo> selectGroupUserList(GroupUserVo groupUserVo) {
		
		return sqlSessionTemplate.selectList(NAME_SPACE+"selectGroupUserList", groupUserVo);
	}

	@Override
	public int deleteGroupLabelByGroupId(GroupLabel groupLabel) {
		
		return sqlSessionTemplate.delete(NAME_SPACE+"deleteGroupLabelByGroupId", groupLabel);
	}
	
	@Override
	public List searchGroup(GroupVo groupVo) {
		
		return sqlSessionTemplate.selectList(NAME_SPACE+"searchGroup", groupVo);
	}
	
	@Override
	public Integer searchGroupCount(GroupVo groupVo) {
		
		return (Integer)sqlSessionTemplate.selectOne(NAME_SPACE+"searchGroupCount", groupVo);
	}
	
	@Override
	public Integer uploadLocal(GroupVo groupVo) {
		
		return (Integer)sqlSessionTemplate.update(NAME_SPACE+"uploadLocal", groupVo);
	}
	
}