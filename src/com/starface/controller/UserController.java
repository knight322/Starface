package com.starface.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starface.domain.EmailVerifyCode;
import com.starface.domain.Page;
import com.starface.domain.UserBlacklist;
import com.starface.domain.Users;
import com.starface.domain.query.UsersQuery;
import com.starface.frame.core.utils.ClientUtils;
import com.starface.service.MailService;
import com.starface.service.UserBlackListService;
import com.starface.service.UserService;
import com.starface.service.impl.EmailServiceImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@Scope("prototype")
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired(required = true)
	private UserService userService;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserBlackListService userBlackListService;
	
	/**
	 * 发送短信验证码
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/sendSmsVerifyCode",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String sendSmsVerifyCode(UsersQuery usersQuery){
		
		return userService.sendSmsVerifyCode(usersQuery);
		
	}
	
	/**
	 * 用户注册
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/register",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String register(UsersQuery usersQuery){
		
		return userService.register(usersQuery);
		
	}
	/**
	 * 登录
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/login",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(UsersQuery usersQuery){
		
		return userService.login(usersQuery);
	}
	/**
	 * 验证邮箱
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/verificationEmail")
	public String verificationEmail(UsersQuery usersQuery,Model model){
		Map result = new HashedMap();
		//校验用户是否存在
		Users users = userService.getUser(usersQuery);
		if(users == null){
			model.addAttribute("into", "该用户不存在");
		}else{
			//如果用户存在，校验验证码是否存在
			int emailCertified = users.getEmailCertified() == null ? 0 : users.getEmailCertified();
			if(emailCertified == 1){
				model.addAttribute("into", "您的邮箱已经验证过了=^_^=");
			}else{
				//查询校验码是否存在于数据库
				EmailVerifyCode evc = mailService.verificationEmail(usersQuery);
				if(null == evc){
					model.addAttribute("into", "验证码错误");
				}else{
					//更新用户邮箱为已认证状态
					userService.verificationEmail(usersQuery);
					model.addAttribute("into", "邮箱验证成功");
				}
			}
		}
		
		return "user/verificationEmail";
	}
	
	/**
	 * 忘记密码，发送验证码到邮箱
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/forgotPwdForEmail",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String forgotPwdForEmail(UsersQuery usersQuery){
		Users users = userService.getUser(usersQuery);
		if(users == null){
			return ClientUtils.failure("用户不存在");
		}
		mailService.sendUserRetrievePasswordCode(usersQuery.getUserName());
		return ClientUtils.success("验证码已发送到您的邮箱");
	}
	
	/**
	 * 校验 邮箱验证码
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/verificationEmailCode",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String verificationEmailCode(UsersQuery usersQuery){
		
		return mailService.verificationEmailCode(usersQuery);
	}
	
	/**
	 * 修改密码
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/changePwd",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String changePwd(UsersQuery usersQuery){
		
		return userService.changePwd(usersQuery);
	}
	
	/**
	 * 修改密码
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/modifyPwd",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyPwd(UsersQuery usersQuery){
		
		return userService.modifyPwd(usersQuery);
	}
	
	/**
	 * 添加好友
	 * @param usersQuery
	 * @see http://localhost:8080/Starface/user/addFriend?owner_username=1&friend_username=2
	 * @return
	 */
	@RequestMapping(value="/addFriend", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addFriend(UsersQuery usersQuery){
		
		return userService.addFriend(usersQuery);
	}
	
	/**
	 * 添加关注
	 * @param usersQuery
	 * @see http://localhost:8080/Starface/user/addAttention?owner_username=1&friend_username=2
	 * @return
	 */
	@RequestMapping(value="/addAttention", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addAttention(UsersQuery usersQuery){
		
		return userService.addAttention(usersQuery);
	}
	
	/**
	 * 搜索好友 
	 * @return
	 */
	@RequestMapping(value="/searchFriend", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String searchFriend(UsersQuery usersQuery){
		
		return userService.searchFriend(usersQuery);
	}
	
	/**
	 * 好友列表
	 * @see http://localhost:8080/Starface/user/friendList?id=1
	 * @return
	 */
	@RequestMapping(value="/friendList", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String friendList(UsersQuery usersQuery){
		
		return userService.friendList(usersQuery);
	}
	
	/**
	 * 关注列表
	 * @see http://localhost:8080/Starface/user/attentionList?id=1
	 * @return
	 */
	@RequestMapping(value="/attentionList", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String attentionList(UsersQuery usersQuery,Integer nextCursor){
		
		return userService.attentionList(usersQuery,nextCursor);
	}
	
	/**
	 * 上传头像
	 * @return
	 */
	@RequestMapping(value="/modifyIcon", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String modifyIcon(UsersQuery usersQuery){
		
		return userService.modifyIcon(usersQuery);
	} 
	
	/**
	 * 用户更新基本信息
	 * @return
	 */
	@RequestMapping(value="/modifyBaseInfo", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String modifyBaseInfo(UsersQuery usersQuery){
		
		return userService.modifyBaseInfo(usersQuery);
	}
	
	/**
	 * 删除好友
	 * @param usersQuery
	 * @see http://localhost:8080/Starface/user/deleteFriend?owner_username=1&friend_username=2
	 * @return
	 */
	@RequestMapping(value="/deleteFriend", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteFriend(UsersQuery usersQuery){
		
		return userService.deleteFriend(usersQuery);
	}
	
	/**
	 * 取消关注
	 * @param usersQuery
	 * @see http://localhost:8080/Starface/user/cancelAttention?owner_username=1&friend_username=2
	 * @return
	 */
	@RequestMapping(value="/cancelAttention", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String cancelAttention(UsersQuery usersQuery){
		
		return userService.cancelAttention(usersQuery);
	}
	
	/**
	 * 添加黑名单
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/addBlackList", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addBlackList(UserBlacklist userBlacklist){
		
		return userBlackListService.addBlackList(userBlacklist);
	}
	/**
	 * 黑名单列表
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/blackList", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String blackList(UserBlacklist userBlacklist){
		
		return userBlackListService.blackListList(userBlacklist);
	}
	/**
	 * 移除黑名单
	 * @param usersQuery
	 * @return
	 */
	@RequestMapping(value="/deleteBlackList", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteBlackList(UserBlacklist userBlacklist){
		
		return userBlackListService.deleteBlackList(userBlacklist);
	}
	
	/**
	 * 获取好友信息
	 * @return
	 */
	@RequestMapping(value="/getUserInfo", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserInfo(UsersQuery usersQuery){
		
		return userService.getUserInfo(usersQuery);
	}
	
	/**
	 * 获取好友信息
	 * @return
	 */
	@RequestMapping(value="/getUserSimpleInfo", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserSimpleInfo(UsersQuery usersQuery){
		
		return userService.getUserSimpleInfo(usersQuery);
	}
	/**
	 * 获取好友信息
	 * @return
	 */
	@RequestMapping(value="/getUserInfoByIds", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserInfoByIds(UsersQuery usersQuery){
		
		return userService.getUserInfoByIds(usersQuery);
	}
	
	/**
	 * 上报经纬度
	 * @return
	 */
	@RequestMapping(value="/uploadLocal", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String uploadLocal(UsersQuery usersQuery){
		
		return userService.uploadLocal(usersQuery);
	}
	
	/**
	 * 附近的人
	 * @return
	 */
	@RequestMapping(value="/nearby", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String nearby(UsersQuery usersQuery){
		
		return userService.nearby(usersQuery);
	}
	
	/**
	 * 附近的人
	 * @return
	 */
	@RequestMapping(value="/discover", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String discover(UsersQuery usersQuery){
		
		return userService.discover(usersQuery);
	}
	
	/**
	 * 后台页面跳转
	 * @return
	 */
	@RequestMapping(value="/sys/index", produces="text/html;charset=UTF-8")
	public String sysIndex(Users user){
		
		return "login";
	}
	
	/**
	 * 后台页面跳转
	 * @return
	 */
	@RequestMapping(value="/sys/login", produces="text/html;charset=UTF-8")
	public String sysLogin(Model model,UsersQuery usersQuery){
		userService.sysLogin(usersQuery);
		return "index";
	}
	
	/**
	 * 第三方登录
	 * @return
	 */
	@RequestMapping(value="/thirdparty/login", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String thirdpartyLogin(UsersQuery usersQuery){
		return  userService.thirdpartyLogin(usersQuery);
	}
	
	/**
	 * 后台用户登录
	 * @return
	 */
	@RequestMapping(value="/sys_login", produces="text/html;charset=UTF-8")
	public String sysLogin(UsersQuery usersQuery,Map<String, Object> model,HttpSession httpSession){
		String result = userService.sysLogin(usersQuery);
		if(null != result){
			model.put("errorMsg", result);
			return "index";
		}
		httpSession.setAttribute("user", usersQuery);
		httpSession.setAttribute("imgBasePath", "http://114.215.172.198/");
		return  "frame";
	}
	
	/**
	 * 后台用户登录
	 * @return
	 */
	@RequestMapping(value="/manager/list", produces="text/html;charset=UTF-8")
	public String sysUserList(UsersQuery usersQuery,Map<String, Object> model,HttpSession httpSession){
		Object object = httpSession.getAttribute("user");
		if(null == object){
			model.put("errorMsg", "登录已失效,请重新登录");
			return "index";
		}
		List<UsersQuery> list = userService.sysUserList(usersQuery);
		Integer totalRow = userService.sysUserListCount(usersQuery);
		model.put("list", list);
		model.put("user", usersQuery);
		Page page = new Page(totalRow, usersQuery.getStart(), usersQuery.getLimit());
		model.put("page", page);
		return  "userManager";
	}
	
	/**
	 * 锁定用户
	 * @return
	 */
	@RequestMapping(value="/lock_user", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String lockUser(UsersQuery usersQuery){
		return  userService.lockUser(usersQuery);
	}
	
	/**
	 * 解锁用户
	 * @return
	 */
	@RequestMapping(value="/unlock_user", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String unlockUser(UsersQuery usersQuery){
		return  userService.unlockUser(usersQuery);
	}
	
	
	
	/**
	 * 锁定用户
	 * @return
	 */
	@RequestMapping(value="/saymsg_user", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String saymsgUser(UsersQuery usersQuery){
		return  userService.saymsgUser(usersQuery);
	}
	
	/**
	 * 解锁用户
	 * @return
	 */
	@RequestMapping(value="/unsaymsg_user", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String unsaymsgUser(UsersQuery usersQuery){
		return  userService.unsaymsgUser(usersQuery);
	}
	
	
}