package com.starface.service;

import java.util.List;

import com.starface.domain.Users;
import com.starface.domain.query.UsersQuery;

public interface UserService {
	
	/**
	 * 校验用户是否已经存在
	 * @param user
	 * @return
	 */
	public boolean checkUserIsExists(UsersQuery usersQuery);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public String register(UsersQuery usersQuery);
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public String login(UsersQuery usersQuery);
	/**
	 * 找回密码
	 * @param user
	 * @return
	 */
	public String retrievePassword(UsersQuery usersQuery);
	/**
	 * 邮箱认证
	 * @return
	 */
	public String verificationEmail(UsersQuery usersQuery);
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public String modifyPwd(UsersQuery usersQuery);
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public String changePwd(UsersQuery usersQuery);
	
	/**
	 * 获取用户信息
	 * @param usersQuery
	 * @return
	 */
	public Users getUser(UsersQuery usersQuery);
	
	/**
	 * 添加好友
	 * @return
	 */
	public String addFriend(UsersQuery usersQuery);
	
	/**
	 * 添加好友
	 * @return
	 */
	public String addAttention(UsersQuery usersQuery);
	
	
	/**
	 * 发送短信验证码
	 * @param usersQuery
	 * @return
	 */
	public String sendSmsVerifyCode(UsersQuery usersQuery);
	
	/**
	 * 搜索好友
	 * @return
	 */
	public String searchFriend(UsersQuery usersQuery);
	/**
	 * 好友列表
	 * @return
	 */
	public String friendList(UsersQuery usersQuery);
	/**
	 * 关注列表
	 * @return
	 */
	public String attentionList(UsersQuery usersQuery,Integer nextCursor);
	
	/**
	 * 修改头像
	 * @param usersQuery
	 * @return
	 */
	public String modifyIcon(UsersQuery usersQuery);
	
	/**
	 * 用户更新基本信息
	 * @param usersQuery
	 * @return
	 */
	public String modifyBaseInfo(UsersQuery usersQuery);
	
	/**
	 * 删除好友
	 * @return
	 */
	public String deleteFriend(UsersQuery usersQuery);
	
	/**
	 * 取消关注
	 * @return
	 */
	public String cancelAttention(UsersQuery usersQuery);
	
	
	/**
	 * 获取好友信息
	 * @return
	 */
	public String getUserInfo(UsersQuery usersQuery);
	
	/**
	 * 获取好友信息
	 * @return
	 */
	public String getUserSimpleInfo(UsersQuery usersQuery);
	
	
	/**
	 * 获取好友信息
	 * @return
	 */
	public String getUserInfoByIds(UsersQuery usersQuery);
	/**
	 * 上报经纬度
	 * @param usersQuery
	 * @return
	 */
	public String uploadLocal(UsersQuery usersQuery);
	
	/**
	 * 搜索好友
	 * @return
	 */
	public String nearby(UsersQuery usersQuery);
	
	
	/**
	 * 搜索好友
	 * @return
	 */
	public String discover(UsersQuery usersQuery);
	/**
	 * 系统用户登录
	 * @param usersQuery
	 * @return
	 */
	public String sysLogin(UsersQuery usersQuery);
	/**
	 * 第三方登录
	 * @param usersQuery
	 * @return
	 */
	public String thirdpartyLogin(UsersQuery usersQuery);
	
	/**
	 * 验证用户关系
	 * @param ownerUserId
	 * @param friendUserId
	 * @return 0:没有关系 ,1:好友关系 2:关注关系
	 */ 
	public Integer verifyRelation(Integer ownerUserId,Integer friendUserId);
	/**
	 * 系统用户
	 * @param usersQuery
	 * @return
	 */
	public List<UsersQuery> sysUserList(UsersQuery usersQuery);
	
	public Integer sysUserListCount(UsersQuery usersQuery);
	
	/**
	 * 锁定用户
	 * @param usersQuery
	 * @return
	 */
	public String lockUser(UsersQuery usersQuery);
	/***
	 * 解锁用户
	 * @param usersQuery
	 * @return
	 */
	public String unlockUser(UsersQuery usersQuery);
	
	/**
	 * 锁定用户
	 * @param usersQuery
	 * @return
	 */
	public String saymsgUser(UsersQuery usersQuery);
	/***
	 * 解锁用户
	 * @param usersQuery
	 * @return
	 */
	public String unsaymsgUser(UsersQuery usersQuery);
	

}
