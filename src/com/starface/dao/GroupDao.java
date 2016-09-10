package com.starface.dao;

import java.util.List;

import com.starface.domain.Group;
import com.starface.domain.GroupLabel;
import com.starface.domain.GroupType;
import com.starface.domain.GroupUser;
import com.starface.domain.vo.GroupUserVo;
import com.starface.domain.vo.GroupVo;

public interface GroupDao {
	
	/**
	 * 创建组
	 * @param group
	 * @return
	 */
	public int insertGroup(Group group);
	/**
	 * 更新群组用户数量
	 * @param group
	 * @return
	 */
	public int updateGroupUserCount(Group group);
	
	/**
	 * 删除群组
	 * @param group
	 * @return
	 */
	public int deleteGroupById(Group group);
	/**
	 * 群组指添加用户
	 * @param groupUser
	 * @return
	 */
	public int insertGroupUser(GroupUserVo groupUserVo);
	
	/**
	 * 群组添加一个用户
	 * @param groupUser
	 * @return
	 */
	public int insertGroupOneUser(GroupUserVo groupUserVo);
	/**
	 * 删除群组中的用户 根据ID
	 * @param groupUser
	 * @return
	 */
	public int deleteGroupUserById(GroupUser groupUser);
	/**
	 * 删除群组中的用户 根据群组ID
	 * @param groupUser
	 * @return
	 */
	public int deleteGroupUserByGroupId(GroupUser groupUser);
	/**
	 * 根据ID获取群组
	 * @param group
	 * @return
	 */
	public Group getGroupById(Group group);
	/**
	 * 根据GroupID获取群组
	 * @param group
	 * @return
	 */
	public Group getGroupByGroupId(Group group);
	/**
	 * 获取群组类别列表
	 * @param groupType
	 * @return
	 */
	public List<GroupType> selectGroupTypeList(GroupType groupType);
	/**
	 * 更新群组
	 * @param group
	 * @return
	 */
	public int updateGroup(Group group);
	/**
	 * 群组添加标签
	 * @return
	 */
	public int groupAddLabel(GroupLabel groupLabel);
	/**
	 * 删除群组标签
	 * @return
	 */
	public int deleteGroupLabel(GroupLabel groupLabel);
	/**
	 * 根据用户ID IN获取列表
	 * @param groupUserVo
	 * @return
	 */
	public List<GroupUser> selectGroupUserInUserId(GroupUserVo groupUserVo);
	
	/**
	 * 检查用户是否存在于群组中
	 * @param groupUserVo
	 * @return
	 */
	public Integer selectGroupUserCount(GroupUserVo groupUserVo);
	
	/**
	 * 删除群组中的用户
	 * @param groupUserVo
	 * @return
	 */
	public int deleteGroupUser(GroupUserVo groupUserVo);
	
	/**
	 * 删除群组中的用户
	 * @param groupUserVo
	 * @return
	 */
	public int deleteGroupByGroupIdAndUserId(GroupUserVo groupUserVo);
	/**
	 * 根据用户ID获取群组列表
	 * @param groupUserVo
	 * @return
	 */
	public List<Group> selectGroupListByUserId(GroupUserVo groupUserVo);
	
	/**
	 * 获取用户群组列表
	 * @param groupUserVo
	 * @return
	 */
	public List<GroupUserVo> selectGroupUserList(GroupUserVo groupUserVo);
	/**
	 * 根据群组ID删除群组标签
	 * @param groupLabel
	 * @return
	 */
	public int deleteGroupLabelByGroupId(GroupLabel groupLabel);
	/**
	 * 查找群组
	 * @param groupVo
	 * @return
	 */
	public List searchGroup(GroupVo groupVo);
	
	/**
	 * 查找群组总数
	 * @param groupVo
	 * @return
	 */
	public Integer searchGroupCount(GroupVo groupVo);
	
	public Integer uploadLocal(GroupVo groupVo);
	
}