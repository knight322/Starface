package com.starface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starface.domain.Group;
import com.starface.domain.GroupLabel;
import com.starface.domain.GroupType;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.GroupUserVo;
import com.starface.domain.vo.GroupVo;
import com.starface.service.GroupService;

@Controller
@RequestMapping(value="group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	/**
	 *  获取群组类型
	 * @param group
	 * http://localhost:8080/Starface/group/getGroupTypeList
	 * @return
	 */
	@RequestMapping(value="/getGroupTypeList",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getGroupTypeList(GroupType groupType){
		return groupService.getGroupTypeList(groupType);
	}
	
	/**
	 *  创建群组
	 * @param group
	 * http://localhost:8080/Starface/group/createGroup?groupName=天下第一&groupType=1&groupCreateUser=12
	 * @return
	 */
	@RequestMapping(value="/createGroup",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String createGroup(Group group){
		return groupService.createGroup(group);
	}
	
	/**
	 * 修改群组头像
	 * @return
	 */
	@RequestMapping(value="/modifyIcon", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String modifyIcon(GroupVo groupVo){
		
		return groupService.modifyIcon(groupVo);
	}
	
	/**
	 * 更新群组信息
	 * @return
	 */
	@RequestMapping(value="/modifyGroupInfo", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String modifyGroupInfo(GroupVo groupVo){
		
		return groupService.modifyGroupInfo(groupVo);
	}
	
	/**
	 * 群组添加标签
	 * @return
	 */
	@RequestMapping(value="/groupAddLabel", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String groupAddLabel(GroupLabel groupLabel){
		
		return groupService.groupAddLabel(groupLabel);
	}
	
	/**
	 * 删除群组标签
	 * @return
	 */
	@RequestMapping(value="/deleteGroupLabel", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteGroupLabel(GroupLabel groupLabel){
		
		return groupService.deleteGroupLabel(groupLabel);
	}
	
	
	/**
	 * 群组添加用户
	 * http://localhost:8080/Starface/group/groupAddUser?groupId=1&userIds=1&userIds=2
	 * @return
	 */
	@RequestMapping(value="/groupAddUser", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String groupAddUser(GroupUserVo groupUserVo){
		
		return groupService.groupAddUser(groupUserVo);
	}
	
	/**
	 * 群组添加用户
	 * http://localhost:8080/Starface/group/joinGroup?groupId=1&userId=1
	 * @return
	 */
	@RequestMapping(value="/joinGroup", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String joinGroup(GroupUserVo groupUserVo){
		
		return groupService.joinGroup(groupUserVo);
	}
	
	/**
	 * 群组删除用户
	 * http://localhost:8080/Starface/group/deleteGroupUser?groupId=1&userIds=1&userIds=2
	 * @return
	 */
	@RequestMapping(value="/deleteGroupUser", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteGroupUser(GroupUserVo groupUserVo){
		
		return groupService.deleteGroupUser(groupUserVo);
	}
	
	/**
	 * 退出群组/删除群组用户
	 * http://localhost:8080/Starface/group/quitGroup?groupId=1&userId=0&ownerId=12
	 * @return
	 */
	@RequestMapping(value="/quitGroup", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String quitGroup(GroupUserVo groupUserVo){
		
		return groupService.quitGroup(groupUserVo);
	}
	
	/**
	 * 获取群组列表
	 * http://localhost:8080/Starface/group/getGroupList?userId=1
	 * @return
	 */
	@RequestMapping(value="/getGroupList", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getGroupList(GroupUserVo groupUserVo){
		
		return groupService.selectGroupListByUserId(groupUserVo);
	}
	
	/**
	 * 获取群组用户列表
	 * http://localhost:8080/Starface/group/selectGroupUserList?groupId=1
	 * @return
	 */
	@RequestMapping(value="/selectGroupUserList", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String selectGroupUserList(GroupUserVo groupUserVo){
		
		return groupService.selectGroupUserList(groupUserVo);
	}
	
	/**
	 * 解散群组
	 * http://localhost:8080/Starface/group/disbandGroup?id=4&userId=13
	 * @return
	 */
	@RequestMapping(value="/disbandGroup", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String disbandGroup(GroupVo groupVo){
		
		return groupService.disbandGroup(groupVo);
	}

	/**
	 * 查找群组
	 * http://localhost:8080/Starface/group/searchGroup?groupName=天下第&currentPage=1
	 * @return
	 */
	@RequestMapping(value="/searchGroup", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String searchGroup(GroupVo groupVo){
		return groupService.searchGroup(groupVo);
	}
	
	/**
	 * 上报经纬度
	 * @return
	 */
	@RequestMapping(value="/uploadLocal", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String uploadLocal(GroupVo groupVo){
		
		return groupService.uploadLocal(groupVo);
	}
	
}