package com.starface.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starface.dao.GroupDao;
import com.starface.domain.Group;
import com.starface.domain.GroupLabel;
import com.starface.domain.GroupType;
import com.starface.domain.GroupUser;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.BaseQueryVo;
import com.starface.domain.vo.GroupUserVo;
import com.starface.domain.vo.GroupVo;
import com.starface.domain.vo.PageVo;
import com.starface.frame.core.utils.ClientUtils;
import com.starface.frame.core.utils.FileUtils;
import com.starface.service.CommonService;
import com.starface.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{

	
	private Logger log = Logger.getLogger(GroupServiceImpl.class);
	@Autowired
	private GroupDao groupDao;
	@Autowired  
	private FileUtils fileUtils;
	@Autowired
	private CommonService commonService;
	
	@Override
	@Transactional
	public String createGroup(Group group) {
		if(group.getGroupName()==null){
			return ClientUtils.failure("群组名称不能为空");
		}
//		if(group.getGroupType()==null){
//			return ClientUtils.failure("群组类型不能为空");
//		}
		if(group.getGroupCreateUser()==null){
			return ClientUtils.failure("群组创建人不能为空");
		}
		
		if(group.getGroupId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		
		Group gr = groupDao.getGroupByGroupId(group);
		if(gr !=null){
			return ClientUtils.failure("群组ID"+group.getGroupId()+"已经存在");
		}
		
		group.setGroupUserCount(0);
		group.setGroupCreateTime(System.currentTimeMillis());
		groupDao.insertGroup(group);
		log.info(group.getId());
		
		
		GroupUserVo guv = new GroupUserVo();
		guv.setGroupId(group.getId());
		List <Integer>userIds = new ArrayList<Integer>();
		userIds.add(group.getGroupCreateUser());
		guv.setUserIds(userIds);
		guv.setIsManage(1);//群组创建者
		guv.setCreateTime(System.currentTimeMillis());
		groupDao.insertGroupUser(guv);
		
		//更新群组用户数
		groupDao.updateGroupUserCount(group);
		
		Group g = groupDao.getGroupById(group);
		if(g!=null){
			Map result = new HashMap();
			result.put("group", g);
			return ClientUtils.success("群组创建成功",result);
		}else{
			return ClientUtils.failure("群组创建失败");
		}
	}
	@Override
	public String getGroupTypeList(GroupType groupType) {
		Map result = new HashMap();
		result.put("groupTypeList", groupDao.selectGroupTypeList(groupType));
		return ClientUtils.success("查询成功", result);
	}
	
	@Override
	public String modifyIcon(GroupVo groupVo) {
		if(groupVo.getId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		if(groupVo.getIcon()==null){
			return ClientUtils.failure("头像不能为空");
		}
		String path = fileUtils.fileUpload(groupVo.getIcon(), "groupIcon");
		if(path == null){
			return ClientUtils.failure("头像上传失败");
		}
		//更新头像
		Group group = new Group();
		group.setGroupIcon(path);
		group.setId(groupVo.getId());
		groupDao.updateGroup(group);
		
		Map map = new HashMap();
		map.put("groupIcon", path);
		return ClientUtils.success("上传成功", map);
	}
	
	@Override
	public String modifyGroupInfo(GroupVo groupVo) {
		if(groupVo.getId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		
		if(groupVo.getProvinceId()!=null){
			groupVo.setProvinceName(commonService.getAreaName(groupVo.getProvinceId()));
		}
		if(groupVo.getCityId()!=null){
			groupVo.setCityName(commonService.getAreaName(groupVo.getCityId()));
		}
		if(groupVo.getAreaId()!=null){
			groupVo.setAreaName(commonService.getAreaName(groupVo.getAreaId()));
		}
		
		groupDao.updateGroup(groupVo);
		Map map = new HashMap();
		Group g=groupDao.getGroupById(groupVo);
		map.put("group", g);
		return ClientUtils.success("更新成功", map);
	}
	
	@Override
	public String groupAddLabel(GroupLabel groupLabel) {
		if(groupLabel.getGroupId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		if(groupLabel.getLabelName()==null){
			return ClientUtils.failure("标签名称不能为空");
		}
		groupLabel.setCreateTime(System.currentTimeMillis());
		groupDao.groupAddLabel(groupLabel);
		Map result = new HashMap();
		result.put("groupLabel", groupLabel);
		return ClientUtils.success("添加成功",result);
	}
	
	@Override
	public String deleteGroupLabel(GroupLabel groupLabel) {
		if(groupLabel.getId() == null){
			return ClientUtils.failure("标签ID不能为空");
		}
		groupDao.deleteGroupLabel(groupLabel);
		return ClientUtils.success("删除成功");
	}
	
	@Override
	public String groupAddUser(GroupUserVo groupUserVo) {
		if(groupUserVo.getGroupId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		if(groupUserVo.getUserId()==null){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(groupUserVo.getUserIds()==null || groupUserVo.getUserIds().isEmpty()){
			return ClientUtils.failure("被添加用户不能为空");
		}
		
		Group g = new Group();
//		g.setId(groupUserVo.getGroupId());
		g.setGroupId(groupUserVo.getGroupId().toString());
//		Group gp = groupDao.getGroupById(g);
		Group gp = groupDao.getGroupByGroupId(g);
		if(gp == null){
			return ClientUtils.failure("群组不存在");
		}
		
		if(gp.getGroupCreateUser()!=groupUserVo.getUserId()){
			return ClientUtils.failure("您没有权限添加用户");
		}
		
		//检查将要添加的用户是否已经存在
		List <GroupUser>list = groupDao.selectGroupUserInUserId(groupUserVo);
		if(!list.isEmpty()){
			for(GroupUser gu :list){
				groupUserVo.getUserIds().remove(gu.getUserId());
			}
		}
		if(groupUserVo.getUserIds()!=null && !groupUserVo.getUserIds().isEmpty()){
			groupUserVo.setCreateTime(System.currentTimeMillis());
			groupUserVo.setIsManage(0);
			groupDao.insertGroupUser(groupUserVo);
		}
		//更新群组用户数
//		Group group = new Group();
//		group.setId(groupUserVo.getGroupId());
//		group.setGroupId(groupUserVo.getGroupId().toString());
		groupDao.updateGroupUserCount(gp);
		String msg = "";
		if(list.size()>=1){
			msg+=","+list.size()+"位用户已经存在于群组中";
		}
		
		Map result = new HashMap();
		List userList  = new ArrayList();
		if(groupUserVo.getUserIds().size()>0){
			userList = groupDao.selectGroupUserList(groupUserVo);
		}
		result.put("userList", userList);
//		Group group2 = groupDao.getGroupById(gp);
		result.put("group", gp);
		return ClientUtils.success("成功添加"+groupUserVo.getUserIds().size()+"位用户"+msg,result);
	}
	
	@Override
	public String joinGroup(GroupUserVo groupUserVo) {
		if(groupUserVo.getGroupId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		if(groupUserVo.getUserId()==null){
			return ClientUtils.failure("用户ID不能为空");
		}
		
		Group grp = new Group();
		grp.setGroupId(groupUserVo.getGroupId().toString());
		Group gg = groupDao.getGroupByGroupId(grp);
		
		Group gp = groupDao.getGroupById(gg);
		if(gp == null){
			return ClientUtils.failure("群组不存在");
		}
		
		//检查用户是否已经存在群组中
		GroupUserVo guv = new GroupUserVo();
		guv.setId(gp.getId());
		guv.setUserId(groupUserVo.getUserId());
		Integer isExists = groupDao.selectGroupUserCount(guv);
		if(isExists != null && isExists > 0){
			return ClientUtils.failure("已经存在于群组中,不能重复添加");
		}
		
		groupDao.insertGroupOneUser(guv);
		
		//更新群组用户数
		groupDao.updateGroupUserCount(gg);
		
		return ClientUtils.success("成功添加");
	}
	
	@Override
	public String deleteGroupUser(GroupUserVo groupUserVo) {
		
		if(groupUserVo.getGroupId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		
		if(groupUserVo.getUserIds()==null || groupUserVo.getUserIds().isEmpty()){
			return ClientUtils.failure("被用户用户不能为空");
		}
		
		groupDao.deleteGroupUser(groupUserVo);
		
		//更新群组用户数
		Group group = new Group();
		group.setId(groupUserVo.getGroupId());
		groupDao.updateGroupUserCount(group);
		
		return ClientUtils.success("删除成功");
	}
	
	
	
	@Override
	public String quitGroup(GroupUserVo groupUserVo) {
		if(groupUserVo.getGroupId() == null || groupUserVo.getGroupId() == 0){
			return ClientUtils.failure("群组ID不能为空");
		}
		if(groupUserVo.getUserId() == null || groupUserVo.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		
		Group g = new Group();
		g.setGroupId(groupUserVo.getGroupId().toString());
		Group group = groupDao.getGroupByGroupId(g);
		
		GroupUserVo guv = new GroupUserVo();
		guv.setGroupId(group.getId());
		guv.setUserId(groupUserVo.getUserId());
		
		groupDao.deleteGroupByGroupIdAndUserId(guv);
		String info = "退出成功";
		if(groupUserVo.getOwnerId() != null && groupUserVo.getOwnerId() != 0){
			info = "删除成功";
		}
		
		groupDao.updateGroupUserCount(group);
		
		return ClientUtils.success(info);
	}
	
	@Override
	public String selectGroupListByUserId(GroupUserVo groupUserVo) {
		List list = groupDao.selectGroupListByUserId(groupUserVo);
		Map result = new HashMap();
		result.put("groupList", list);
		return ClientUtils.success("查询成功", result);
	}
	
	@Override
	public String selectGroupUserList(GroupUserVo groupUserVo) {
		if(groupUserVo.getGroupId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		Group group = new Group();
		group.setGroupId(groupUserVo.getGroupId().toString());
		Group g = groupDao.getGroupByGroupId(group);
		GroupUserVo guv = new GroupUserVo();
		guv.setGroupId(g.getId());
		List list = groupDao.selectGroupUserList(guv);
		Map result = new HashMap();
		result.put("userList", list);
		return ClientUtils.success("查询成功", result);
	}
	
	@Override
	public String disbandGroup(GroupVo groupVo) {
		if(groupVo.getId()==null){
			return ClientUtils.failure("群组ID不能为空");
		}
		if(groupVo.getUserId()==null){
			return ClientUtils.failure("用户ID不能为空");
		}
		Group g = new Group();
		g.setId(groupVo.getId());
		Group group = groupDao.getGroupById(g);
		if(group == null){
			return ClientUtils.failure("群组不存在");
		}
		if(group.getGroupCreateUser()!=groupVo.getUserId()){
			return ClientUtils.failure("您没有权限解散该群组");
		}
		
		//删除群组标签
		GroupLabel groupLabel = new GroupLabel();
		groupLabel.setGroupId(g.getId());
		groupDao.deleteGroupLabelByGroupId(groupLabel);
		
		//删除群组用户
		GroupUser groupUser = new GroupUser();
		groupUser.setGroupId(g.getId());
		groupDao.deleteGroupUserByGroupId(groupUser);
		//删除群组
		groupDao.deleteGroupById(g);
		
		return ClientUtils.success("解散成功");
	}
	
	@Override
	public String searchGroup(GroupVo groupVo) {
		
		if(groupVo.getGroupName() == null && groupVo.getGroupType() == null ){
			return ClientUtils.failure("搜索条件不能为空");
		}
		PageVo pv = new PageVo();
		pv.setPageSize(groupVo.getPageSize());
		System.out.println(groupDao.searchGroupCount(groupVo));
		pv.setTotalRecord(groupDao.searchGroupCount(groupVo));
		groupVo.setCurrentRecodNum(groupVo.getCurrentPage() * groupVo.getPageSize());
		List groupList = groupDao.searchGroup(groupVo);
		pv.setList(groupList);
		return ClientUtils.success("查询成功", "groupList", pv);
	}
	
	
	@Override
	public String uploadLocal(GroupVo groupVo) {
		if(groupVo.getId() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(groupVo.getLongitude() == null){
			return ClientUtils.failure("经度不能为空");
		}
		if(groupVo.getLatitude() == null){
			return ClientUtils.failure("纬度不能为空");
		}
		groupDao.uploadLocal(groupVo);
		return ClientUtils.success("更新成功");
	}
}