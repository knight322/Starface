package com.starface.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.util.MD5;
import com.easemob.common.Constants;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.starface.dao.MailDao;
import com.starface.dao.UserDao;
import com.starface.demo.EasemobIMUsers;
import com.starface.domain.EmailVerifyCode;
import com.starface.domain.UserRelation;
import com.starface.domain.UserSimpleData;
import com.starface.domain.Users;
import com.starface.domain.po.UserPo;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.BaseQueryVo;
import com.starface.frame.core.exception.ServiceException;
import com.starface.frame.core.utils.ClientUtils;
import com.starface.frame.core.utils.FileBean;
import com.starface.frame.core.utils.FileUtils;
import com.starface.frame.core.utils.StringEx;
import com.starface.service.CommonService;
import com.starface.service.MailService;
import com.starface.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired(required = true)
	private UserDao userDao;
	@Autowired
	private EasemobIMUsers easemobIMUsers;
	@Autowired
	private MailService mailService;
	@Autowired
	private MailDao mailDao;
	@Autowired  
	private FileUtils fileUtils;
	@Autowired
	private CommonService commonService;
	
	@Override
	public boolean checkUserIsExists(UsersQuery usersQuery) {
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("userName", usersQuery.getUserName());
		query.getParameters().put("mobile", usersQuery.getMobile());
		return userDao.userIsExixts(query) > 0 ? true : false;
	}

	@Override
	@Transactional
	public String register(UsersQuery usersQuery) {
		//校验用户是否为空
		if(usersQuery.getType() == 1){
			if(null == usersQuery.getMobile() || "".equals(usersQuery.getMobile().trim())){
				return ClientUtils.failure("用户名不能为空.");
			}
		}else if(usersQuery.getType() == 2){
			if(null == usersQuery.getUserName() || "".equals(usersQuery.getUserName().trim())){
				return ClientUtils.failure("用户名不能为空.");
			}
		}else{
			return ClientUtils.failure("Type错误.");
		}
		//校验用户名是否存在
		if(checkUserIsExists(usersQuery)){
			if(usersQuery.getType() == 1){
				return ClientUtils.failure("用户名 "+usersQuery.getMobile()+" 已经存在.");
			}else if(usersQuery.getType() == 2){
				return ClientUtils.failure("用户名 "+usersQuery.getUserName()+" 已经存在.");
			}
			
			return ClientUtils.failure("用户名 "+usersQuery.getUserName()+" 已经存在.");
			
		}
		//随机生成代号
		String codeName = StringEx.getRandomString(10);
		usersQuery.setCodeName(codeName);
		usersQuery.setPwd(usersQuery.getPwd());
		//保存用户
		userDao.saveUser(usersQuery);
		int userId = usersQuery.getId();
		//同步数据到环信
		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username",userId);
        datanode.put("password", usersQuery.getPwd());//Constants.DEFAULT_PASSWORD
        ObjectNode createNewIMUserSingleNode = easemobIMUsers.createNewIMUserSingle(datanode);
        if (null != createNewIMUserSingleNode) {
//        	System.out.println("注册IM用户[单个]: " + createNewIMUserSingleNode.toString());
            //LOGGER.info("注册IM用户[单个]: " + createNewIMUserSingleNode.toString());
        }else{
        	throw new ServiceException("同步环信数据错误"+createNewIMUserSingleNode.toString());
        }
        //发送邮件到邮箱
        if(usersQuery.getType() == 2){
        	mailService.sendUserVerificationLink(usersQuery.getUserName(),userId);
        }
		return ClientUtils.success("注册成功");
	}

	@Override
	public String login(UsersQuery usersQuery) {
		//校验用户密码是否为空
		if(null == usersQuery.getUserName() || "".equals(usersQuery.getUserName().trim()) 
				|| null == usersQuery.getPwd() || "".equals(usersQuery.getPwd().trim())){
			return ClientUtils.failure("用户名或密码不能为空.");
		}
		usersQuery.setMobile(usersQuery.getUserName());
		usersQuery.setPwd(usersQuery.getPwd());
		//校验用户密码是否正确
		if(!checkUserIsExists(usersQuery)){
			return ClientUtils.failure("用户不存在");
		}
		
		Users user =   userDao.login(usersQuery);  //getUser(usersQuery);
		if(null == user){
			return ClientUtils.failure(4003,"密码错误");
		}
		if(user.getIsLock() == 1){
			return ClientUtils.failure(4003,"用户已经被锁定");
		}
		/**
         * IM用户登录
         */
//        ObjectNode imUserLoginNode = easemobIMUsers.imUserLogin(user.getId().toString(),Constants.DEFAULT_PASSWORD);
//        if (null == imUserLoginNode) {
////        	throw new ServiceException("IM登录失败");
//        	return ClientUtils.failure("IM登录失败");
//        	
//        }
        Map result = new HashMap();
        user.setPwd(null);
        BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id",  user.getId());
		query.setOffset(0);
		query.setLimit(20);
		List userPo = userDao.searchUser(query);
		result.put("user", userPo.get(0));
        //result.put("IM", imUserLoginNode.toString());
		return ClientUtils.success("登录成功",result);
	}

	@Override
	public String retrievePassword(UsersQuery usersQuery) {
		
		return null;
	}

	@Override
	public String verificationEmail(UsersQuery usersQuery) {
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id", usersQuery.getUser_id());
		query.getParameters().put("emailCertified", 1);
		//更新邮箱已验证
		userDao.updateUser(query);
		return null;
	}

	@Override
	public String modifyPwd(UsersQuery usersQuery) {
		Users user = null;
		BaseQueryVo query = new BaseQueryVo();
		if(null != usersQuery.getType() && usersQuery.getType() == 2){
			if(usersQuery.getMobile() == null || usersQuery.getMobile().trim().equals("")){
				return ClientUtils.failure("手机号码不能为空");
			}
			query.getParameters().put("mobile", usersQuery.getMobile());
			user = userDao.getUserByMobile(query);
			if(user == null){
				return ClientUtils.failure("用户不存在");
			}
			query.getParameters().put("pwd", usersQuery.getPwd());
			userDao.modifyPwdByMobile(query);
			
		}else{
			if(usersQuery.getCode() == null || "".equals(usersQuery.getCode())){
				return ClientUtils.failure("验证码不能为空");
			}
			//验证验证码是否正确
			EmailVerifyCode evc = new EmailVerifyCode();
			evc.setEmail(usersQuery.getUserName());
			evc.setCode(usersQuery.getCode());
			evc.setValidityTime(86400);//有效期一天
			EmailVerifyCode emailCode = mailDao.getVerificationEmailCode(evc);
			if(null == emailCode){
				return ClientUtils.failure("验证码错误");
			}
			
			query.getParameters().put("userName", usersQuery.getUserName());
			user = userDao.getUser(query);
			if(user == null){
				return ClientUtils.failure("用户不存在");
			}
			
//			MD5 md5 = new MD5();
//			//密码md5加密
//			String newPwd = md5.toDigest(usersQuery.getPwd());
			query.getParameters().put("pwd", usersQuery.getPwd());
			userDao.modifyPwdByEmail(query);
		}
		
		
		//修改环信密码
		String username = ""+user.getId();
        ObjectNode json2 = JsonNodeFactory.instance.objectNode();
        json2.put("newpassword", usersQuery.getPwd());
        ObjectNode modifyIMUserPasswordWithAdminTokenNode = easemobIMUsers.modifyIMUserPasswordWithAdminToken(username, json2);
        
        Map result = new HashMap();
        result.put("user", user);
		return ClientUtils.success("密码修改成功",result);
	}
	
	
	@Override
	public String changePwd(UsersQuery usersQuery) {
		
		if(usersQuery.getId() == null){
			return ClientUtils.failure("ID为能为空");
		}
		if(usersQuery.getOldPwd() == null){
			return ClientUtils.failure("原始密码不能为空");
		}
		if(usersQuery.getPwd() == null){
			return ClientUtils.failure("新密码不能为空");
		}
		
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id", usersQuery.getId());
		Users user = userDao.getUser(query);
		if(null == user){
			return ClientUtils.failure("用户不存在");
		}
		if(!user.getPwd().equals(usersQuery.getOldPwd())){
			return ClientUtils.failure("原始密码错误");
		}
		query.getParameters().put("pwd", usersQuery.getPwd());
		userDao.modifyPwdById(query);
		
		//修改环信密码
		String username = ""+user.getId();
        ObjectNode json2 = JsonNodeFactory.instance.objectNode();
        json2.put("newpassword", usersQuery.getPwd());
        ObjectNode modifyIMUserPasswordWithAdminTokenNode = easemobIMUsers.modifyIMUserPasswordWithAdminToken(username, json2);
		
		Map result = new HashMap();
		result.put("user", user);
		return ClientUtils.success("修改成功", result);
	}

	@Override
	public Users getUser(UsersQuery usersQuery) {
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("userName", usersQuery.getUserName());
		query.getParameters().put("mobile", usersQuery.getUserName());
		query.getParameters().put("pwd", usersQuery.getPwd());
		query.getParameters().put("id", usersQuery.getUser_id());
		Users user = userDao.getUser(query);
		return user;
	}

	@Override
	public String addFriend(UsersQuery usersQuery) {
		Map result = new HashMap();
		
		if(usersQuery.getOwner_username() == null || null==usersQuery.getFriend_username()){
			return ClientUtils.failure("缺少必要参数");
		}
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id", usersQuery.getOwner_username());
		Users users = userDao.getUser(query);
		if(null == users){
			return ClientUtils.failure("用户"+usersQuery.getOwner_username()+"不存在");
		}
		query.getParameters().put("id", usersQuery.getFriend_username());
		users = userDao.getUser(query);
		if(null == users){
			return ClientUtils.failure("用户"+usersQuery.getFriend_username()+"不存在");
		}
//		easemobIMUsers.addFriendSingle(usersQuery.getOwner_username(),usersQuery.getFriend_username());
		UserRelation userRelation1 = new UserRelation ();
		userRelation1.setOwner_username(Integer.parseInt(usersQuery.getOwner_username()));
		userRelation1.setFriend_username(Integer.parseInt(usersQuery.getFriend_username()));
		UserRelation uv = userDao.getFriend(userRelation1);
		if(null == uv){
			userRelation1.setStatus(1);
			userDao.addFriend(userRelation1);
			//调用环信添加好友
		}else{
			query.getParameters().put("id",  usersQuery.getFriend_username());
			query.setOffset(0);
			query.setLimit(20);
			List userPo = userDao.searchUser(query);
			
			if(null != userPo && userPo.size() > 0){
				result.put("user", userPo.get(0));
			}else{
				result.put("user", null);
			}
			return ClientUtils.failure(4001,"已经是好友了",result);
		}
		UserRelation userRelation2 = new UserRelation ();
		userRelation2.setOwner_username(Integer.parseInt(usersQuery.getFriend_username()));
		userRelation2.setFriend_username(Integer.parseInt(usersQuery.getOwner_username()));
		UserRelation uv1 = userDao.getFriend(userRelation2);
		if(null == uv1){
			userRelation2.setStatus(1);
			userDao.addFriend(userRelation2);
			
		}else{
			query.getParameters().put("id",  usersQuery.getFriend_username());
			query.setOffset(0);
			query.setLimit(20);
			List userPo = userDao.searchUser(query);
			
			if(null != userPo && userPo.size() > 0){
				result.put("user", userPo.get(0));
			}else{
				result.put("user", null);
			}
			return ClientUtils.failure(4001,"已经是好友了",result);
		}
		
		query.getParameters().put("id",  usersQuery.getFriend_username());
		query.setOffset(0);
		query.setLimit(20);
		List userPo = userDao.searchUser(query);
		
		if(null != userPo && userPo.size() > 0){
			result.put("user", userPo.get(0));
		}else{
			result.put("user", null);
		}
		return ClientUtils.success("添加好友成功",result);
	}
	
	public String addAttention(UsersQuery usersQuery) {
		Map result = new HashMap();
		
		if(usersQuery.getOwner_username() == null || null==usersQuery.getFriend_username()){
			return ClientUtils.failure("缺少必要参数");
		}
		if(usersQuery.getOwner_username().equals(usersQuery.getFriend_username())){
			return ClientUtils.failure("自己不能关注自己");
		}
		
		UserRelation userRelation1 = new UserRelation ();
		userRelation1.setOwner_username(Integer.parseInt(usersQuery.getOwner_username()));
		userRelation1.setFriend_username(Integer.parseInt(usersQuery.getFriend_username()));
		UserRelation uv = userDao.getAttention(userRelation1);
		if(null == uv){
			userRelation1.setStatus(1);
			userDao.addAttention(userRelation1);
		}else{
			return ClientUtils.failure("已经关注了");
		}
		
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id",  usersQuery.getFriend_username());
		query.setOffset(0);
		query.setLimit(20);
		List userPo = userDao.searchUser(query);
		
		if(null != userPo && userPo.size() > 0){
			result.put("user", userPo.get(0));
		}else{
			result.put("user", null);
		}
		return ClientUtils.success("关注成功",result);
	}

	@Override
	public String sendSmsVerifyCode(UsersQuery usersQuery) {
		//校验用户是否为空
		if(null == usersQuery.getUserName() || "".equals(usersQuery.getUserName().trim())){
			return ClientUtils.failure("用户名不能为空.");
		}
		//校验用户名是否存在
		if(checkUserIsExists(usersQuery)){
			return ClientUtils.failure("用户名 "+usersQuery.getUserName()+" 已经存在.");
		}
		//随机生成代号
		String codeName = StringEx.getRandomInteger(6);
//		Map map = new HashMap();
//		map.put("smsCode", codeName);
		// TODO 保存到数据库
		
		return ClientUtils.success("操作成功");
	}

	@Override
	public String searchFriend(UsersQuery usersQuery) {
		/**
		if((null == usersQuery.getSearchName() || "".equals(usersQuery.getSearchName().trim())) 
				&& (usersQuery.getGender() == null || usersQuery.getGender() == 0)
				&&  usersQuery.getId() == null){
			return ClientUtils.failure("搜索条件不能为空");
		}**/
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("searchName", usersQuery.getSearchName());
		query.getParameters().put("gender", usersQuery.getGender());
		query.getParameters().put("id", usersQuery.getId());
		int beginAge = 0;
		int endAge = 0;
		if(null != usersQuery.getAgeBracket() && 0!=usersQuery.getAgeBracket()){
			switch (usersQuery.getAgeBracket()) {
			case 1:
				endAge = 18;
				break;
			case 2:
				beginAge = 18;
				endAge = 22;
				break;
			case 3:
				beginAge = 22;
				endAge = 26;
				break;
			case 4:
				beginAge = 26;
				endAge = 35;
				break;
			case 5:
				beginAge = 35;
				endAge = 200;
				break;
			default:
				break;
			}
		}
		query.getParameters().put("beginAge", beginAge);
		query.getParameters().put("endAge", beginAge);
		query.getParameters().put("provinceId", usersQuery.getProvinceId());
		query.getParameters().put("cityId", usersQuery.getCityId());
		query.getParameters().put("areaId", usersQuery.getAreaId());
		
		query.getParameters().put("hometownProvinceId", usersQuery.getHometownProvinceId());
		query.getParameters().put("hometownCityId", usersQuery.getHometownCityId());
		query.getParameters().put("hometownAreaId", usersQuery.getHometownAreaId()); 
		query.setOffset((null == usersQuery.getOffset() ? 0 : usersQuery.getOffset()) * 20);
		query.setLimit(20);
		
		List <UserPo>list = userDao.searchUser(query);
		if(null != list && !list.isEmpty()){
			for(UserPo up: list){
				
				//查询当前用户与被查询用户是否好友关系
				UserRelation userRelation = new UserRelation();
				userRelation.setOwner_username(usersQuery.getCurrentUser());
				userRelation.setFriend_username(up.getId());
				UserRelation ur = userDao.getFriend(userRelation);
				if(null != ur){
					up.setHaoyou(1);
				}
				//查看是否已经关注过
				UserRelation ru1 = userDao.getAttention(userRelation);
				if(null != ru1){
					up.setGuanzhu(1);
				}
				
				userRelation.setOwner_username(up.getId() );
				userRelation.setFriend_username(usersQuery.getCurrentUser());
				//查看是否已经被关注过
				UserRelation ru2 = userDao.getAttention(userRelation);
				if(null != ru2){
					up.setBeiguanzhu(1);
				}
			}
		}
		Map map = new HashMap();
		map.put("userList", list);
		map.put("offset", usersQuery.getOffset());
		map.put("limit", 20);
		map.put("userListCount", userDao.searchUserCount(query));
		return ClientUtils.success("搜索成功",map);
	}

	/**
     * 查看好友
     */
	@Override
	public String friendList(UsersQuery usersQuery) {
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id", usersQuery.getId());
		query.setOffset(usersQuery.getOffset());
		Users user = userDao.getUser(query);
		if(null == user){
			return ClientUtils.failure("用户不存在");
		}
		
		UserRelation userRelation = new UserRelation();
		userRelation.setOwner_username(usersQuery.getId());
		List list = userDao.getFriendList(userRelation);
		
		List an = new ArrayList<Object>();
		
		for(int i =0;i<list.size();i++){
			UserRelation relation= (UserRelation)list.get(i);
			BaseQueryVo bqv = new BaseQueryVo();
			
			UserSimpleData userPo = userDao.getUserInfoSimple(query);
			
    		bqv.getParameters().put("id",  relation.getFriend_username());
    		UserSimpleData userSimpleData = userDao.getUserInfoSimple(bqv);
    		an.add(userSimpleData);
    		
		}
		
		Map result = new HashMap();
	    result.put("friendList", an);
		
		return ClientUtils.success("获取成功",result);
	}
	
	/**
     * 关注列表
     */
	@Override
	public String attentionList(UsersQuery usersQuery,Integer nextCursor) {
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id", usersQuery.getId());
		query.setOffset(usersQuery.getOffset());
		Users user = userDao.getUser(query);
		if(null == user){
			return ClientUtils.failure("用户不存在");
		}
		Integer nextId = null;
		List list = new ArrayList();
		//我的关注列表
		if(usersQuery.getPassivity() == null || usersQuery.getPassivity() == 0){
			UserRelation userRelation = new UserRelation();
			userRelation.setOwner_username(usersQuery.getId());
			if(null == nextCursor || nextCursor == 0){
				nextCursor = userDao.getAttentionMaxId(userRelation);
			}
			Map param = new HashMap();
			param.put("userRelation", userRelation);
			param.put("nextCursor", nextCursor);
			list = userDao.getAttentionList(param);
			nextId = userDao.getAttentionNextId(param);
		}else{//关注我的列表
			UserRelation userRelation = new UserRelation();
			userRelation.setFriend_username(usersQuery.getId()); 
			if(null == nextCursor || nextCursor == 0){
				nextCursor = userDao.getAttentionBeiMaxId(userRelation);
			}
			Map param = new HashMap();
			param.put("userRelation", userRelation);
			param.put("nextCursor", nextCursor);
			list = userDao.getAttentionBeiList(param);
			nextId = userDao.getAttentionBeiNextId(param);
		}
		
		List an = new ArrayList<Object>();
		
		for(int i =0;i<list.size();i++){
			UserRelation relation= (UserRelation)list.get(i);
			BaseQueryVo bqv = new BaseQueryVo();
			if(usersQuery.getPassivity() == null || usersQuery.getPassivity() == 0){
				bqv.getParameters().put("id",  relation.getFriend_username());
			}else{
				bqv.getParameters().put("id",  relation.getOwner_username());
			}
    		bqv.setOffset(0);
    		bqv.setLimit(20);
    		List userPo = userDao.searchUser(bqv);
    		if(null!=userPo && !userPo.isEmpty()){
    			an.add(userPo.get(0));
    		}
		}
		Map result = new HashMap();
	    result.put("attentionList", an);
	    result.put("nextCursor", nextId);
		return ClientUtils.success("获取成功",result);
	}


	@Override
	public String modifyIcon(UsersQuery usersQuery) {
		FileBean path = fileUtils.fileUploadAndResized(usersQuery.getIcon(), "userIcon");
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("userIcon", path.getPath());
		query.getParameters().put("id", usersQuery.getId());
		userDao.updateUser(query);
		Map map = new HashMap();
		map.put("userIcon", path.getPath());
		map.put("suserIconSmall", path.getSmallPath());
		return ClientUtils.success("上传成功", map);
	}

	@Override
	public String modifyBaseInfo(UsersQuery usersQuery) {
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id", usersQuery.getId());
		query.getParameters().put("name", usersQuery.getName());
		query.getParameters().put("nickName", usersQuery.getNickName());
		query.getParameters().put("age",usersQuery.getAge());
		query.getParameters().put("birthday",usersQuery.getBirthday());
		query.getParameters().put("sign",usersQuery.getSign());
		query.getParameters().put("company",usersQuery.getCompany());
		query.getParameters().put("school",usersQuery.getSchool());
		query.getParameters().put("hometown",usersQuery.getHometown());
		query.getParameters().put("remark",usersQuery.getRemark());
		query.getParameters().put("gender",usersQuery.getGender());
		query.getParameters().put("provinceId",usersQuery.getProvinceId());
		query.getParameters().put("provinceName",commonService.getAreaName(usersQuery.getProvinceId()));
		query.getParameters().put("cityId",usersQuery.getCityId());
		query.getParameters().put("cityName",commonService.getAreaName(usersQuery.getCityId()));
		query.getParameters().put("areaId",usersQuery.getAreaId());
		query.getParameters().put("areaName",commonService.getAreaName(usersQuery.getAreaId()));
		
		query.getParameters().put("hometownProvinceId",usersQuery.getHometownProvinceId());
		query.getParameters().put("hometownProvinceName",commonService.getAreaName(usersQuery.getHometownProvinceId()));
		query.getParameters().put("hometownCityId",usersQuery.getHometownCityId());
		query.getParameters().put("hometownCityName",commonService.getAreaName(usersQuery.getHometownCityId()));
		query.getParameters().put("hometownAreaId",usersQuery.getHometownAreaId());
		query.getParameters().put("hometownAreaName",commonService.getAreaName(usersQuery.getHometownAreaId()));
		
		query.getParameters().put("pet",usersQuery.getPet());
		query.getParameters().put("books",usersQuery.getBooks());
		query.getParameters().put("movie",usersQuery.getMovie());
		query.getParameters().put("sport",usersQuery.getSport());
		query.getParameters().put("music",usersQuery.getMusic());
		query.getParameters().put("mobile",usersQuery.getMobile());
		
		userDao.updateUser(query);
		return ClientUtils.success("保存成功");
	}

	/**
     * 解除好友关系
     **/
	@Override
	public String deleteFriend(UsersQuery usersQuery) {
		
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("id", usersQuery.getOwner_username());
		Users users = userDao.getUser(query);
		if(null == users){
			return ClientUtils.failure("用户"+usersQuery.getOwner_username()+"不存在");
		}
		query.getParameters().put("id", usersQuery.getFriend_username());
		users = userDao.getUser(query);
		if(null == users){
			return ClientUtils.failure("用户"+usersQuery.getFriend_username()+"不存在");
		}
		
//        ObjectNode deleteFriendSingleNode = easemobIMUsers.deleteFriendSingle(usersQuery.getOwner_username(), usersQuery.getFriend_username());
        
		UserRelation userRelation1 = new UserRelation ();
		userRelation1.setOwner_username(Integer.parseInt(usersQuery.getOwner_username()));
		userRelation1.setFriend_username(Integer.parseInt(usersQuery.getFriend_username()));
		userDao.deleteFriend(userRelation1);
		
		UserRelation userRelation2 = new UserRelation ();
		userRelation2.setOwner_username(Integer.parseInt(usersQuery.getFriend_username()));
		userRelation2.setFriend_username(Integer.parseInt(usersQuery.getOwner_username()));
		userDao.deleteFriend(userRelation2);
		
		return ClientUtils.success("删除成功");
	}
	
	/**
     * 取消关注
     **/
	@Override
	public String cancelAttention(UsersQuery usersQuery) {
		
		UserRelation userRelation1 = new UserRelation ();
		userRelation1.setOwner_username(Integer.parseInt(usersQuery.getOwner_username()));
		userRelation1.setFriend_username(Integer.parseInt(usersQuery.getFriend_username()));
		userDao.cancelAttention(userRelation1);
		
		return ClientUtils.success("取消成功");
	}

	@Override
	public String getUserInfo(UsersQuery usersQuery) {
		BaseQueryVo query = new BaseQueryVo();
		if(usersQuery.getId() == null && usersQuery.getIds().isEmpty()){
			return ClientUtils.failure("ids不能为空");
		}
		if(usersQuery.getCurrentUser() == null){
			return ClientUtils.failure("当前登录用户ID不能为空");
		}
		query.getParameters().put("id", usersQuery.getId());
		query.getParameters().put("userName", usersQuery.getUserName());
		query.getParameters().put("codeName", usersQuery.getCodeName());
		UserPo userPo = userDao.getUserInfo(query);
		
		if(null != userPo){
			//查询当前用户与被查询用户是否好友关系
			UserRelation userRelation = new UserRelation();
			userRelation.setOwner_username(usersQuery.getCurrentUser());
			userRelation.setFriend_username(usersQuery.getId());
			UserRelation ur = userDao.getFriend(userRelation);
			if(null != ur){
				userPo.setHaoyou(1);
			}
			//查看是否已经关注过
			UserRelation ru1 = userDao.getAttention(userRelation);
			if(null != ru1){
				userPo.setGuanzhu(1);
			}
			
			userRelation.setOwner_username(usersQuery.getId() );
			userRelation.setFriend_username(usersQuery.getCurrentUser());
			//查看是否已经被关注过
			UserRelation ru2 = userDao.getAttention(userRelation);
			if(null != ru2){
				userPo.setBeiguanzhu(1);
			}
		}
		
		
		if(null==userPo){
			return ClientUtils.failure("未查询到结果");
		}
		Map map = new HashMap();
		map.put("user", userPo);
		return ClientUtils.success("获取成功",map);
		
	}
	
	@Override
	public String getUserSimpleInfo(UsersQuery usersQuery) {
		BaseQueryVo query = new BaseQueryVo();
		if(usersQuery.getId() == null && usersQuery.getIds().isEmpty()){
			return ClientUtils.failure("ids不能为空");
		}
		if(usersQuery.getCurrentUser() == null){
			return ClientUtils.failure("当前登录用户ID不能为空");
		}
		query.getParameters().put("id", usersQuery.getId());
		query.getParameters().put("userName", usersQuery.getUserName());
		query.getParameters().put("codeName", usersQuery.getCodeName());
		UserSimpleData userPo = userDao.getUserInfoSimple(query);
		
		if(null != userPo){
			//查询当前用户与被查询用户是否好友关系
			UserRelation userRelation = new UserRelation();
			userRelation.setOwner_username(usersQuery.getCurrentUser());
			userRelation.setFriend_username(usersQuery.getId());
			UserRelation ur = userDao.getFriend(userRelation);
			if(null != ur){
				userPo.setHaoyou(1);
			}
			//查看是否已经关注过
			UserRelation ru1 = userDao.getAttention(userRelation);
			if(null != ru1){
				userPo.setGuanzhu(1);
			}
			
			userRelation.setOwner_username(usersQuery.getId() );
			userRelation.setFriend_username(usersQuery.getCurrentUser());
			//查看是否已经被关注过
			UserRelation ru2 = userDao.getAttention(userRelation);
			if(null != ru2){
				userPo.setBeiguanzhu(1);
			}
		}
		
		
		if(null==userPo){
			return ClientUtils.failure("未查询到结果");
		}
		Map map = new HashMap();
		map.put("user", userPo);
		return ClientUtils.success("获取成功",map);
		
	}
	
	
	@Override
	public String getUserInfoByIds(UsersQuery usersQuery) {
		BaseQueryVo query = new BaseQueryVo();
		if(usersQuery.getIds() == null){
			return ClientUtils.failure("ids不能为空");
		}
		if(usersQuery.getCurrentUser() == null){
			return ClientUtils.failure("当前登录用户ID不能为空");
		}
		query.getParameters().put("ids", usersQuery.getIds());
		query.getParameters().put("userName", usersQuery.getUserName());
		query.getParameters().put("codeName", usersQuery.getCodeName());
		List<UserPo> userPoList = userDao.getUserInfoByIds(query);
		if(null != userPoList && !userPoList.isEmpty()){
			for(UserPo userPo :userPoList){
				if(null != userPo){
					//查询当前用户与被查询用户是否好友关系
					UserRelation userRelation = new UserRelation();
					userRelation.setOwner_username(usersQuery.getCurrentUser());
					userRelation.setFriend_username(userPo.getId());
					UserRelation ur = userDao.getFriend(userRelation);
					if(null != ur){
						userPo.setHaoyou(1);
					}
					//查看是否已经关注过
					UserRelation ru1 = userDao.getAttention(userRelation);
					if(null != ru1){
						userPo.setGuanzhu(1);
					}
					userRelation.setOwner_username(userPo.getId() );
					userRelation.setFriend_username(usersQuery.getCurrentUser());
					//查看是否已经被关注过
					UserRelation ru2 = userDao.getAttention(userRelation);
					if(null != ru2){
						userPo.setBeiguanzhu(1);
					}
				}
			}
		}else{
			return ClientUtils.failure("未查询到结果");
		}
		Map map = new HashMap();
		map.put("users", userPoList);
		return ClientUtils.success("获取成功",map);
		
	}
	

	@Override
	public String uploadLocal(UsersQuery usersQuery) {
		if(usersQuery.getId() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(usersQuery.getLongitude() == null){
			return ClientUtils.failure("经度不能为空");
		}
		if(usersQuery.getLatitude() == null){
			return ClientUtils.failure("纬度不能为空");
		}
		userDao.uploadLocal(usersQuery);
		return ClientUtils.success("更新成功");
	}

	@Override
	public String nearby(UsersQuery usersQuery) {
		
		if(usersQuery.getLatitude() == null || usersQuery.getLongitude() == null){
			return ClientUtils.failure("经度或纬度不能为空");
		}
		if(usersQuery.getCurrentUser() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("latitude", usersQuery.getLatitude());
		query.getParameters().put("longitude", usersQuery.getLongitude());
		int beginAge = 0;
		int endAge = 0;
		if(null != usersQuery.getAgeBracket() && 0!=usersQuery.getAgeBracket()){
			switch (usersQuery.getAgeBracket()) {
			case 1:
				endAge = 18;
				break;
			case 2:
				beginAge = 18;
				endAge = 22;
				break;
			case 3:
				beginAge = 22;
				endAge = 26;
				break;
			case 4:
				beginAge = 26;
				endAge = 35;
				break;
			case 5:
				beginAge = 35;
				endAge = 200;
				break;
			default:
				break;
			}
		}
		query.getParameters().put("beginAge", beginAge);
		query.getParameters().put("endAge", beginAge);
		query.getParameters().put("provinceId", usersQuery.getProvinceId());
		query.getParameters().put("cityId", usersQuery.getCityId());
		query.getParameters().put("areaId", usersQuery.getAreaId());
		
		query.getParameters().put("hometownProvinceId", usersQuery.getHometownProvinceId());
		query.getParameters().put("hometownCityId", usersQuery.getHometownCityId());
		query.getParameters().put("hometownAreaId", usersQuery.getHometownAreaId()); 
		query.setOffset((null == usersQuery.getOffset() ? 0 : usersQuery.getOffset()) * 20);
		query.setLimit(20);
		
		List <UserPo>list = userDao.nearbyUser(query);
		if(null != list && !list.isEmpty()){
			for(UserPo up: list){
				
				//查询当前用户与被查询用户是否好友关系
				UserRelation userRelation = new UserRelation();
				userRelation.setOwner_username(usersQuery.getCurrentUser());
				userRelation.setFriend_username(up.getId());
				UserRelation ur = userDao.getFriend(userRelation);
				if(null != ur){
					up.setHaoyou(1);
				}
				//查看是否已经关注过
				UserRelation ru1 = userDao.getAttention(userRelation);
				if(null != ru1){
					up.setGuanzhu(1);
				}
				
				userRelation.setOwner_username(up.getId() );
				userRelation.setFriend_username(usersQuery.getCurrentUser());
				//查看是否已经被关注过
				UserRelation ru2 = userDao.getAttention(userRelation);
				if(null != ru2){
					up.setBeiguanzhu(1);
				}
			}
		}
		Map map = new HashMap();
		map.put("userList", list);
		map.put("offset", usersQuery.getOffset());
		map.put("limit", 20);
		map.put("userListCount", userDao.nearbyUserCount(query));
		return ClientUtils.success("搜索成功",map);
	}

	@Override
	public String discover(UsersQuery usersQuery) {
		
		if(usersQuery.getCurrentUser() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		
		BaseQueryVo query = new BaseQueryVo();
		int beginAge = 0;
		int endAge = 0;
		if(null != usersQuery.getAgeBracket() && 0!=usersQuery.getAgeBracket()){
			switch (usersQuery.getAgeBracket()) {
			case 1:
				endAge = 18;
				break;
			case 2:
				beginAge = 18;
				endAge = 22;
				break;
			case 3:
				beginAge = 22;
				endAge = 26;
				break;
			case 4:
				beginAge = 26;
				endAge = 35;
				break;
			case 5:
				beginAge = 35;
				endAge = 200;
				break;
			default:
				break;
			}
		}
		query.getParameters().put("beginAge", beginAge);
		query.getParameters().put("endAge", beginAge);
		query.getParameters().put("provinceId", usersQuery.getProvinceId());
		query.getParameters().put("cityId", usersQuery.getCityId());
		query.getParameters().put("areaId", usersQuery.getAreaId());
		
		query.getParameters().put("hometownProvinceId", usersQuery.getHometownProvinceId());
		query.getParameters().put("hometownCityId", usersQuery.getHometownCityId());
		query.getParameters().put("hometownAreaId", usersQuery.getHometownAreaId()); 
		query.setOffset((null == usersQuery.getOffset() ? 0 : usersQuery.getOffset()) * 20);
		query.setLimit(20);
		List <UserPo>list = userDao.discover(query);
		if(null != list && !list.isEmpty()){
			for(UserPo up: list){
				
				//查询当前用户与被查询用户是否好友关系
				UserRelation userRelation = new UserRelation();
				userRelation.setOwner_username(usersQuery.getCurrentUser());
				userRelation.setFriend_username(up.getId());
				UserRelation ur = userDao.getFriend(userRelation);
				if(null != ur){
					up.setHaoyou(1);
				}
				//查看是否已经关注过
				UserRelation ru1 = userDao.getAttention(userRelation);
				if(null != ru1){
					up.setGuanzhu(1);
				}
				
				userRelation.setOwner_username(up.getId() );
				userRelation.setFriend_username(usersQuery.getCurrentUser());
				//查看是否已经被关注过
				UserRelation ru2 = userDao.getAttention(userRelation);
				if(null != ru2){
					up.setBeiguanzhu(1);
				}
			}
		}
		Map map = new HashMap();
		map.put("userList", list);
//		map.put("offset", usersQuery.getOffset());
//		map.put("limit", 20);
//		map.put("userListCount", userDao.nearbyUserCount(query));
		return ClientUtils.success("搜索成功",map);
	}

	@Override
	public String sysLogin(UsersQuery usersQuery) {
		if(null != usersQuery.getPwd()){
			MD5 md5 = new MD5();
			usersQuery.setPwd(md5.toDigest(usersQuery.getPwd()));
		}
		Integer count = userDao.sysLogin(usersQuery);
		if(count==null || count.intValue()==0){
			return "用户名或密码错误";
		}
		return null;
	}

	@Override
	public String thirdpartyLogin(UsersQuery usersQuery) {
		if(null == usersQuery.getOpenType() || usersQuery.getOpenType() == 0){
			return ClientUtils.failure("第三方登录标识不能为空");
		}
		if(usersQuery.getOpenId() == null || usersQuery.getOpenId().trim().isEmpty()){
			return ClientUtils.failure("第三方授权码不能为空");
		}
		//检查是否存在


		BaseQueryVo query = new BaseQueryVo();
		query.getParameters().put("openType", usersQuery.getOpenType());
		query.getParameters().put("openId", usersQuery.getOpenId());
		Users users =  userDao.getUserByOpenTypeAndOpenId(query);
		//如果为空就注册用户
		if(null == users){
			//随机生成代号
			String codeName = StringEx.getRandomString(10);
			usersQuery.setCodeName(codeName);
			//保存用户
			userDao.saveUser(usersQuery);
			users = new Users();
			users.setId(usersQuery.getId());
			//同步数据到环信
			ObjectNode datanode = JsonNodeFactory.instance.objectNode();
	        datanode.put("username",users.getId());
	        datanode.put("password", Constants.DEFAULT_PASSWORD);//Constants.DEFAULT_PASSWORD
	        ObjectNode createNewIMUserSingleNode = easemobIMUsers.createNewIMUserSingle(datanode);
	        if (null != createNewIMUserSingleNode) {
//	        	System.out.println("注册IM用户[单个]: " + createNewIMUserSingleNode.toString());
	            //LOGGER.info("注册IM用户[单个]: " + createNewIMUserSingleNode.toString());
	        }else{
	        	throw new ServiceException("同步环信数据错误"+createNewIMUserSingleNode.toString());
	        }
		}
		Map result = new HashMap();
		BaseQueryVo query1 = new BaseQueryVo();
		query1.getParameters().put("id", users.getId());
		query1.setOffset(0);
		query1.setLimit(20);
		List userPo = userDao.searchUser(query1);
		result.put("user", userPo.get(0));
		return ClientUtils.success("登录成功",result);
	}

	@Override
	public Integer verifyRelation(Integer ownerUserId, Integer friendUserId) {
		//查询当前用户与被查询用户是否好友关系
		UserRelation userRelation = new UserRelation();
		userRelation.setOwner_username(ownerUserId);
		userRelation.setFriend_username(friendUserId);
		UserRelation ur = userDao.getFriend(userRelation);
		//好友关系
		if(null != ur){
			return 1;
		}
		//查看是否已经被关注过
		UserRelation ru2 = userDao.getAttention(userRelation);
		if(null != ru2){
			return 2;
		}
		return 0;
	}

	@Override
	public List<UsersQuery> sysUserList(UsersQuery usersQuery) {
		
		return userDao.sysUserList(usersQuery);
	}
	@Override
	public Integer sysUserListCount(UsersQuery usersQuery) {
		
		return userDao.sysUserListCount(usersQuery);
	}

	@Override
	public String lockUser(UsersQuery usersQuery) {
		userDao.lockUser(usersQuery);
		return ClientUtils.success("登录成功");
	}

	@Override
	public String unlockUser(UsersQuery usersQuery) {
		userDao.unlockUser(usersQuery);
		return ClientUtils.success("登录成功");
	}
	@Override
	public String saymsgUser(UsersQuery usersQuery) {
		userDao.saymsgUser(usersQuery);
		return ClientUtils.success("登录成功");
	}

	@Override
	public String unsaymsgUser(UsersQuery usersQuery) {
		userDao.unsaymsgUser(usersQuery);
		return ClientUtils.success("登录成功");
	}
	
	
}