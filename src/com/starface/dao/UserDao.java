package com.starface.dao;


import java.util.List;
import java.util.Map;

import com.starface.domain.UserRelation;
import com.starface.domain.UserSimpleData;
import com.starface.domain.Users;
import com.starface.domain.po.UserPo;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.BaseQueryVo;

public interface UserDao {
	
	/**
	 * 校验用户是否已经存在
	 * @param user
	 * @return
	 */
	public int checkUserIsExists(BaseQueryVo query);
	
	
	public int userIsExixts(BaseQueryVo query);
	
	public Users getUserByOpenTypeAndOpenId(BaseQueryVo query);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public int saveUser(Users user);
	
	/**
	 * 获取用户
	 * @param query
	 * @return
	 */
	public Users getUser(BaseQueryVo query);
	
	public Users login(UsersQuery query) ;
	
	/**
	 * 修改用户
	 * @param query
	 * @return
	 */
	public int updateUser(BaseQueryVo query);
	
	/**
	 * 根据邮箱修改密码
	 * @param usersQuery
	 * @return
	 */
	public int modifyPwdByEmail(BaseQueryVo query);
	
	/**
	 * 根据邮箱修改密码
	 * @param usersQuery
	 * @return
	 */
	public int modifyPwdByMobile(BaseQueryVo query);
	
	/**
	 * 根据ID修改密码
	 * @param usersQuery
	 * @return
	 */
	public int modifyPwdById(BaseQueryVo query);
	
	/**
	 * 搜索好友
	 * @param query
	 * @return
	 */
	public List searchUser(BaseQueryVo query);
	/**
	 * 搜索好友总数
	 * @param query
	 * @return
	 */
	public int searchUserCount(BaseQueryVo query);
	
	/**
	 * 获取好友信息
	 * @param query
	 * @return
	 */
	public UserPo getUserInfo(BaseQueryVo query);
	
	public UserSimpleData getUserInfoSimple(BaseQueryVo query);
	
	/**
	 * 获取好友信息
	 * @param query
	 * @return
	 */
	public List<UserPo> getUserInfoByIds(BaseQueryVo query);
	
	/**
	 * 获取好友信息
	 * @param query
	 * @return
	 */
	public Users getUserByMobile(BaseQueryVo query);
	/**
	 * 添加好友
	 * @param usersQuery
	 * @return
	 */
	public int addFriend(UserRelation userRelation);
	
	/**
	 * 添加关注
	 * @param usersQuery
	 * @return
	 */
	public int addAttention(UserRelation userRelation);
	
	/**
	 * 删除好友
	 * @param usersQuery
	 * @return
	 */
	public int deleteFriend(UserRelation userRelation);
	
	/**
	 * 取消关注
	 * @param usersQuery
	 * @return
	 */
	public int cancelAttention(UserRelation userRelation);
	
	
	/**
	 * 获取好友列表
	 * @param usersQuery
	 * @return
	 */
	public List getFriendList(UserRelation userRelation);
	
	/**
	 * 获取关注列表
	 * @param usersQuery
	 * @return
	 */
	public List getAttentionList(Map param);
	
	/**
	 * 获取关注列表
	 * @param usersQuery
	 * @return
	 */
	public Integer getAttentionNextId(Map param);
	/**
	 * 获取被关注列表
	 * @param usersQuery
	 * @return
	 */
	public Integer getAttentionBeiNextId(Map param);
	
	/**
	 * 获取我关注列表最大ID
	 * @param usersQuery
	 * @return
	 */
	public Integer getAttentionMaxId(UserRelation userRelation);
	
	/**
	 * 获取被关注列表最大ID
	 * @param usersQuery
	 * @return
	 */
	public Integer getAttentionBeiMaxId(UserRelation userRelation);
	
	/**
	 * 获取被关注列表
	 * @param usersQuery
	 * @return
	 */
	public List getAttentionBeiList(Map param);
	/**
	 * 查询是否已经是好友关系
	 * @param userRelation
	 * @return
	 */
	public UserRelation getFriend(UserRelation userRelation);
	
	/**
	 * 查询是否已经是关注过
	 * @param userRelation
	 * @return
	 */
	public UserRelation getAttention(UserRelation userRelation);
	/**
	 * 更新经纬度
	 * @param usersQuery
	 */
	public int uploadLocal(UsersQuery usersQuery);
	
	/**
	 * 附近的人
	 * @param query
	 * @return
	 */
	public List nearbyUser(BaseQueryVo query);
	/**
	 * 附近的人总数
	 * @param query
	 * @return
	 */
	public int nearbyUserCount(BaseQueryVo query);
	/**
	 * 发现
	 * @param query
	 * @return
	 */
	public List discover(BaseQueryVo query);

	/**
	 * 系统用户登录
	 * @param usersQuery
	 * @return
	 */
	public int sysLogin(UsersQuery usersQuery);
	/**
	 * 用户管理 
	 * @param usersQuery
	 * @return
	 */
	public List<UsersQuery> sysUserList(UsersQuery usersQuery);
	
	/**
	 * 用户管理 
	 * @param usersQuery
	 * @return
	 */
	public Integer sysUserListCount(UsersQuery usersQuery);
	/**
	 * 锁定用户
	 * @param usersQuery
	 * @return
	 */
	public Integer lockUser(UsersQuery usersQuery);
	/**
	 * 解锁用户
	 * @param usersQuery
	 * @return
	 */
	public Integer unlockUser(UsersQuery usersQuery);
	/**
	 * 锁定用户
	 * @param usersQuery
	 * @return
	 */
	public Integer saymsgUser(UsersQuery usersQuery);
	/**
	 * 解锁用户
	 * @param usersQuery
	 * @return
	 */
	public Integer unsaymsgUser(UsersQuery usersQuery);
	
	public UserPo getUserById(Integer userId);
}
