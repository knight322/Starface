package com.starface.service;


import java.util.List;

import com.starface.domain.Group;
import com.starface.domain.GroupLabel;
import com.starface.domain.GroupType;
import com.starface.domain.vo.GroupUserVo;
import com.starface.domain.vo.GroupVo;

public interface GroupService {
	
	/**
	 * 获取群组列表
	 * @param groupType
	 * @return
	 */
	public String getGroupTypeList(GroupType groupType);
	/**
	 * 创建群组
	 * @param group
	 * @return
	 */
	public String createGroup(Group group);
	/**
	 * 上传头像
	 * @param groupVo
	 * @return
	 */
	public String modifyIcon(GroupVo groupVo);
	/**
	 * 更新群组信息
	 * @param groupVo
	 * @return
	 */
	public String modifyGroupInfo(GroupVo groupVo);
	/**
	 * 群组添加照片
	 * @param groupLabel
	 * @return
	 */
	public String groupAddLabel(GroupLabel groupLabel);
	/**
	 * 删除群组标签
	 * @param groupLabel
	 * @return
	 */
	public String deleteGroupLabel(GroupLabel groupLabel);
	/**
	 * 群组添加用户
	 * @param groupUserVo
	 * @return
	 */
	public String groupAddUser(GroupUserVo groupUserVo);
	
	/**
	 * 群组添加用户
	 * @param groupUserVo
	 * @return
	 */
	public String joinGroup(GroupUserVo groupUserVo);
	
	/**
	 * 删除群组中用户
	 * @param groupUserVo
	 * @return
	 */
	public String deleteGroupUser(GroupUserVo groupUserVo);
	
	/**
	 * 退出群组
	 * @param quitGroup
	 * @return
	 */
	public String quitGroup(GroupUserVo groupUserVo);
	/**
	 * 获取自己群组列表
	 * @param groupUserVo
	 * @return
	 */
	public String selectGroupListByUserId(GroupUserVo groupUserVo);
	
	/**
	 * 获取群组用户列表
	 * @param groupUserVo
	 * @return
	 */
	public String selectGroupUserList(GroupUserVo groupUserVo);
	/**
	 * 解散群组
	 * @return
	 */
	public String disbandGroup(GroupVo groupVo);
	/**
	 * 查的群组
	 * @param groupVo
	 * @return
	 */
	public String searchGroup(GroupVo groupVo);
	
	
	public String uploadLocal(GroupVo groupVo);

}
