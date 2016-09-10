package com.starface.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.starface.dao.UserDao;
import com.starface.dao.WeblogDao;
import com.starface.domain.UserRelation;
import com.starface.domain.Weblog;
import com.starface.domain.WeblogComment;
import com.starface.domain.WeblogFavorite;
import com.starface.domain.WeblogFile;
import com.starface.domain.WeblogPraise;
import com.starface.domain.po.UserPo;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.UserRelationData;
import com.starface.domain.vo.WeblogCommentListVo;
import com.starface.domain.vo.WeblogFriendVo;
import com.starface.domain.vo.WeblogListVo;
import com.starface.domain.vo.WeblogManagerVo;
import com.starface.domain.vo.WeblogPraiseListVo;
import com.starface.domain.vo.WeblogVo;
import com.starface.frame.core.utils.ClientUtils;
import com.starface.frame.core.utils.DateUtils;
import com.starface.frame.core.utils.FileBean;
import com.starface.frame.core.utils.FileUtils;
import com.starface.service.UserService;
import com.starface.service.WeblogService;

@Service
public class WeblogServiceImpl implements WeblogService{

	@Autowired
	private WeblogDao weblogDao;
	@Autowired  
	private FileUtils fileUtils;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	
	@Override
	public String createBlog(WeblogVo weblogVo) {
		
		if(weblogVo.getUserId() == null || weblogVo.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		UserPo userPo =  userDao.getUserById(weblogVo.getUserId());
		if(userPo.getIsLock() == 1){
			return ClientUtils.failure("您的账号已经被禁用");
		}
		if(userPo.getIsSaymsg() == 1){
			return ClientUtils.failure("您的账号已经被禁言");
		}
		if(weblogVo.getType() == null ){
			weblogVo.setType(0);
		}
		
		if(weblogVo.getIsPrivate() == null){
			weblogVo.setIsPrivate(0);
		}
		
		if(weblogVo.getType() == 1 || weblogVo.getType() == 2 || weblogVo.getType() == 3){
			if(weblogVo.getFile() == null || weblogVo.getFile().isEmpty()){
				return ClientUtils.failure("文件不能为空");
			}
		}
		
		weblogVo.setCreateTime(System.currentTimeMillis());
		weblogDao.insertWeblog(weblogVo);
		
		if(weblogVo.getType() == 1 || weblogVo.getType() == 2 || weblogVo.getType() == 3){
			if(weblogVo.getFile() == null || weblogVo.getFile().isEmpty()){
				return ClientUtils.failure("文件不能为空");
			}
			for(MultipartFile file : weblogVo.getFile()){
				if(!file.isEmpty()){
					FileBean path = fileUtils.fileUploadAndResized(file, "weblog");
					if(path == null){
						return ClientUtils.failure("文件上传失败");
					}
					//保存文件
					WeblogFile wf = new WeblogFile();
					wf.setPath(path.getPath());
					wf.setType(weblogVo.getType());
					wf.setWeblogId(weblogVo.getId());
					wf.setCreateTime(System.currentTimeMillis());
					weblogDao.insertWeblogFile(wf);
				}
			}
		}
		return ClientUtils.success("创建成功");
	}

	@Override
	public String comment(WeblogComment weblogComment) {
		if(weblogComment.getWeblogId() == null || weblogComment.getWeblogId() == 0){
			return ClientUtils.failure("日志ID不能为空");
		}
		if(weblogComment.getUserId() == null || weblogComment.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(weblogComment.getContent() == null  || weblogComment.getContent().equals("")){
			return ClientUtils.failure("评论内容不能为空");
		}
		weblogComment.setCreateTime(System.currentTimeMillis());
		//保存评论信息
		weblogDao.insertWeblogComment(weblogComment);
		//更新评论数
		Weblog weblog = new Weblog();
		weblog.setId(weblogComment.getWeblogId());
		weblogDao.updateWeblogCommentCount(weblog);
		
		return ClientUtils.success("评论成功");
	}

	@Override
	public String deleteComment(WeblogComment weblogComment) {
		
		if(weblogComment.getUserId() == null || weblogComment.getUserId() == 0){
			return ClientUtils.failure("userId不能为空");
		}
		if(weblogComment.getId() == null || weblogComment.getId() == 0){
			return ClientUtils.failure("评论ID不能为空");
		}
		
		WeblogComment wc = weblogDao.selectWeblogCommentById(weblogComment);
		if(wc == null){
			return ClientUtils.failure("评论信息不存在");
		}
		
		//删除评论
		weblogDao.deleteWeblogComment(weblogComment);
		
		//更新评论数
		Weblog weblog = new Weblog();
		weblog.setId(wc.getWeblogId());
		weblogDao.updateWeblogCommentCount(weblog);
		
		return ClientUtils.success("删除成功");
	}

	@Override
	public String praise(WeblogPraise weblogPraise) {
		if(weblogPraise.getWeblogId() == null || weblogPraise.getWeblogId() == 0){
			return ClientUtils.failure("日志ID不能为空");
		}
		if(weblogPraise.getUserId() == null || weblogPraise.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(weblogPraise.getType() == null || weblogPraise.getType() == 0){
			return ClientUtils.failure("点赞类型不能为空");
		}
		
		WeblogPraise wp = weblogDao.selectWeblogPraiseById(weblogPraise);
		if(wp != null){
			return ClientUtils.failure("已经点过赞啦");
		}
		
		weblogPraise.setCreateTime(System.currentTimeMillis());
		weblogDao.insertWeblogPraise(weblogPraise);
		
		//更新点赞数
		Weblog weblog = new Weblog();
		weblog.setId(weblogPraise.getWeblogId());
		weblogDao.updateWeblogPraiseCount(weblog);
		
		return ClientUtils.success("点赞成功");
	}

	@Override
	public String cannelPraise(WeblogPraise weblogPraise) {
		if(weblogPraise.getWeblogId() == null || weblogPraise.getWeblogId() == 0){
			return ClientUtils.failure("日志ID不能为空");
		}
		if(weblogPraise.getUserId() == null || weblogPraise.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		
		WeblogPraise wp = weblogDao.selectWeblogPraiseById(weblogPraise);
		if(wp == null){
			return ClientUtils.failure("已经取消");
		}
		
		//删除点赞记录
		weblogDao.deleteWeblogPraise(weblogPraise);
		
		//更新点赞数
		Weblog weblog = new Weblog();
		weblog.setId(weblogPraise.getWeblogId());
		weblogDao.updateWeblogPraiseCount(weblog);
		
		return ClientUtils.success("取消成功");
	}

	@Override
	public String delete(WeblogVo weblogVo) {
	
		if(weblogVo.getId() == null || weblogVo.getId() == 0){
			return ClientUtils.failure("日志ID不能为空");
		}
		if(weblogVo.getUserId() == null || weblogVo.getUserId() == 0){
			return ClientUtils.failure("userId不能为空");
		}
		
		Weblog weblog = weblogDao.selectWeblogById(weblogVo);
		if(weblog == null){
			return ClientUtils.failure("日志不存在");
		}
		if(weblog.getUserId().intValue() != weblogVo.getUserId().intValue()){
			return ClientUtils.failure("该日志不属于您,没权限删除");
		}
		
		//删除评论记录
		weblogDao.deleteWeblogCommentByWeblogId(weblogVo);
		//删除点赞记录
		weblogDao.deleteWeblogPraiseByWeblogId(weblogVo);
		//删除日志
		weblogDao.deleteWeblogById(weblogVo);
		
		return ClientUtils.success("删除成功");
	}
	
	@Override
	public String favorite(WeblogFavorite weblogFavorite) {
		if(weblogFavorite.getUserId() == null || weblogFavorite.getUserId() == 0){
			return ClientUtils.failure("userId不能为空");
		}
		if(weblogFavorite.getWeblogId() == null || weblogFavorite.getWeblogId() == 0){
			return ClientUtils.failure("日志ID不能为空");
		}
		if(weblogFavorite.getIsPrivate() == null){
			weblogFavorite.setIsPrivate(0);
		}
		WeblogFavorite wFavorite = weblogDao.selectWeblogFavorite(weblogFavorite);
		if(wFavorite != null){
			return ClientUtils.failure("该日志已经收藏过啦");
		}
		Weblog weblog = new Weblog();
		weblog.setId(weblogFavorite.getWeblogId());
		Weblog  blog = weblogDao.selectWeblogById(weblog);
		if(blog == null){
			return ClientUtils.failure("收藏失败,该日志不存在");
		}
		weblogFavorite.setCreateTime(System.currentTimeMillis());
		weblogDao.insertWeblogFavorite(weblogFavorite);
		return ClientUtils.success("收藏成功");
	}
	
	@Override
	public String deleteFavorite(WeblogFavorite weblogFavorite) {
		if(weblogFavorite.getUserId() == null || weblogFavorite.getUserId() == 0){
			return ClientUtils.failure("userId不能为空");
		}
		if(weblogFavorite.getWeblogId() == null || weblogFavorite.getWeblogId() == 0){
			return ClientUtils.failure("日志ID不能为空");
		}
		weblogDao.deleteWeblogFavorite(weblogFavorite);
		return ClientUtils.success("删除成功");
	}

	//如果friend_username 不为空，则查询好友的日志列表
	//如果friend_username没传，则查看自己的日志列表
	@Override
	public String myWeblogList(WeblogVo weblogVo,Integer nextCursor,Integer friendId) {
		
		if(weblogVo.getUserId() == null || weblogVo.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(friendId != null && friendId != 0){
			return friendWeblogList(weblogVo.getUserId(),friendId,nextCursor);
		}
		if(nextCursor == null){
			nextCursor = weblogDao.selectWeblogMaxId(weblogVo.getUserId());
		}
		Map param = new HashMap();
		param.put("weblogVo", weblogVo);
		param.put("nextCursor", nextCursor);
		if(friendId != null){
			UserRelation userRelation1 = new UserRelation ();
			userRelation1.setOwner_username(weblogVo.getUserId());
			userRelation1.setFriend_username(friendId);
			UserRelation uv = userDao.getFriend(userRelation1);
			if(null == uv){
				Map result = new HashMap();
				result.put("list", new ArrayList());
				result.put("nextCursor", null);
				return ClientUtils.failure("获取失败,不是好友关系",result);
			}else{
				weblogVo.setUserId(friendId);
				param.put("friend", 1);
			}
		}
		List<Weblog> list = weblogDao.selectWeblogListByUserId(param);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogListVo>resultList = new ArrayList<WeblogListVo>();
		for(Weblog weblog : list ){
			WeblogListVo wlv = new WeblogListVo();
			//copy属性
			wlv.setId(weblog.getId());
			wlv.setUserId(weblog.getUserId());
			wlv.setIsPrivate(weblog.getIsPrivate());
			wlv.setType(weblog.getType());
			wlv.setContent(weblog.getContent());
			wlv.setCommentCount(weblog.getCommentCount());
			wlv.setCreateTime(weblog.getCreateTime());
			wlv.setAddress(weblog.getAddress());
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			for(WeblogFile wf:fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			wlv.setWeblogFiles(fileList);
			wlv.setUserIcon(weblog.getUserIcon());
			wlv.setNickName(weblog.getNickName());
			wlv.setCodeName(weblog.getCodeName());
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			wlv.setPraiseList(praiseList);
			resultList.add(wlv);
		}
		Map result = new HashMap();
		result.put("list", resultList);
		Integer nextId = weblogDao.selectWeblogNextId(param);
		result.put("nextCursor", nextId);
		
		return ClientUtils.success("查询成功", result);
	}
	
	
	
	
	private String friendWeblogList(Integer ownerUserId,Integer friendId,Integer nextCursor) {
		
		if(ownerUserId == null || ownerUserId == 0){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("owner Id 不能为空",result);
		}
		if(friendId == null || friendId == 0){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("好友ID不能为空",result);
		}
		Integer relation = userService.verifyRelation(ownerUserId, friendId);
		UserRelationData userRelationData = new UserRelationData();
		userRelationData.setOwnerUserId(ownerUserId);
		userRelationData.setFriendUserId(friendId);
		userRelationData.setRelation(relation);
		if(nextCursor == null){
			nextCursor = weblogDao.selectFriendWeblogMaxIdByOwnerId(userRelationData);
		}
		userRelationData.setNextCursor(nextCursor);
		//获取日志列表
		List<Weblog> list = weblogDao.selectFriendWeblogListByOwnerId(userRelationData);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogListVo>resultList = new ArrayList<WeblogListVo>();
		for(Weblog weblog : list ){
			WeblogListVo wlv = new WeblogListVo();
			//copy属性
			wlv.setId(weblog.getId());
			wlv.setUserId(weblog.getUserId());
			wlv.setIsPrivate(weblog.getIsPrivate());
			wlv.setType(weblog.getType());
			wlv.setContent(weblog.getContent());
			wlv.setCommentCount(weblog.getCommentCount());
			wlv.setCreateTime(weblog.getCreateTime());
			wlv.setAddress(weblog.getAddress());
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			for(WeblogFile wf:fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			wlv.setWeblogFiles(fileList);
			wlv.setUserIcon(weblog.getUserIcon());
			wlv.setNickName(weblog.getNickName());
			wlv.setCodeName(weblog.getCodeName());
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			wlv.setPraiseList(praiseList);
			resultList.add(wlv);
		}
		Map result = new HashMap();
		result.put("list", resultList);
		Integer nextId = weblogDao.selectFriendWeblogNextIdByOwnerId(userRelationData);
		result.put("nextCursor", nextId);
		
		return ClientUtils.success("查询成功", result);
	}
	
	@Override
	public String friendWeblogList(WeblogVo weblogVo,Integer nextCursor) {
		
		if(weblogVo.getUserId() == null || weblogVo.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		
		if(nextCursor == null){
			nextCursor = weblogDao.selectFriendWeblogMaxId(weblogVo.getUserId());
		}
		
		Map param = new HashMap();
		param.put("weblogVo", weblogVo);
		param.put("nextCursor", nextCursor);
		List<WeblogFriendVo> list = weblogDao.selectWeblogListByFriend(param);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogFriendVo>resultList = new ArrayList<WeblogFriendVo>();
		for(WeblogFriendVo weblog : list ){
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			for(WeblogFile wf:fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			weblog.setWeblogFiles(fileList);
			
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			weblog.setPraiseList(praiseList);
			
			resultList.add(weblog);
		}
		Map result = new HashMap();
		result.put("list", resultList);
		Integer nextId = weblogDao.selectWeblogFriendNextId(param);
		result.put("nextCursor", nextId);
		
		return ClientUtils.success("查询成功", result);
	}

	@Override
	public String commentList(WeblogVo weblogVo,Integer nextCursor) {
		Map result = new HashMap();
		if(weblogVo.getId() == null ){
			return ClientUtils.failure("日志ID不能为空");
		}
		if(null == nextCursor || nextCursor == 0){
			nextCursor = weblogDao.selectWeblogCommentMaxId(weblogVo.getId());
		}
		//添加回复列表
		Map param = new HashMap();
		param.put("weblogVo", weblogVo);
		param.put("nextCursor", nextCursor);
		List<WeblogCommentListVo> commentList = weblogDao.selectWeblogCommentListByWeblogid(param);
		result.put("commentList", commentList);
		Integer nextId = weblogDao.selectWeblogCommentNextId(param);
		result.put("nextCursor", nextId);
		return ClientUtils.success("查询成功",result);
	}

	@Override
	public String praiseList(WeblogVo weblogVo,Integer nextCursor) {
		if(weblogVo.getId() == null ){
			return ClientUtils.failure("日志ID不能为空");
		}
		if(null == nextCursor || nextCursor == 0){
			nextCursor = weblogDao.selectWeblogPraiseMaxId(weblogVo.getId());
		}
		Map param = new HashMap();
		param.put("weblogVo", weblogVo);
		param.put("nextCursor", nextCursor);
		//添加点赞列表
		List<WeblogPraiseListVo> praiseList = weblogDao.selectWeblogPraiseListByWeblogid(param);
		Map result = new HashMap();
		result.put("commentList", praiseList);
		result.put("nextCursor", weblogDao.selectWeblogPraiseNextId(param));
		return ClientUtils.success("查询成功",result);
	}

	@Override
	public String favoriteList(WeblogVo weblogVo,Integer nextCursor) {
		
		if(weblogVo.getUserId() == null || weblogVo.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		//如果没传表示第一次请求
		if(null == nextCursor || nextCursor == 0){
			nextCursor = weblogDao.selectFavoriteMaxId(weblogVo);
		}
		Map param = new HashMap();
		param.put("weblogVo", weblogVo);
		param.put("nextCursor", nextCursor);
		Map result = new HashMap();
		List favoriteList = weblogDao.selectFavoriteList(param);
		
		Integer currentCursor = weblogDao.selectFavoriteNextId(param);
		result.put("nextCursor", currentCursor);
		result.put("favoriteList", favoriteList);
		return ClientUtils.success("查询成功", result);
	}
	
	@Override
	public String attentionWeblogList(UsersQuery usersQuery,Integer nextCursor) {
		
		if(usersQuery.getOwner_username() == null){
			return ClientUtils.failure("当前用户ID不能为空");
		}
		
		if(nextCursor == null){
			nextCursor = weblogDao.selectAttentionWeblogMaxId(Integer.parseInt(usersQuery.getOwner_username()));
		}
		
		Map param = new HashMap();
		param.put("usersQuery", usersQuery);
		param.put("nextCursor", nextCursor);
		List<WeblogFriendVo> list = weblogDao.selectWeblogListByAttention(param);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogFriendVo>resultList = new ArrayList<WeblogFriendVo>();
		for(WeblogFriendVo weblog : list ){
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			for(WeblogFile wf:fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			weblog.setWeblogFiles(fileList);
			
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			weblog.setPraiseList(praiseList);
			
			resultList.add(weblog);
		}
		Map result = new HashMap();
		result.put("list", resultList);
		Integer nextId = weblogDao.selectWeblogAttentionNextId(param);
		result.put("nextCursor", nextId);
		
		return ClientUtils.success("查询成功", result);
	}
	
	@Override
	public String beiAttentionWeblogList(UsersQuery usersQuery,Integer nextCursor) {
		
		if(usersQuery.getOwner_username() == null){
			return ClientUtils.failure("当前用户ID不能为空");
		}
		usersQuery.setFriend_username(usersQuery.getOwner_username());
		
		if(nextCursor == null){
			nextCursor = weblogDao.selectBeiAttentionWeblogMaxId(Integer.parseInt(usersQuery.getOwner_username()));
		}
		
		Map param = new HashMap();
		param.put("usersQuery", usersQuery);
		param.put("nextCursor", nextCursor);
		List<WeblogFriendVo> list = weblogDao.selectWeblogListByBeiAttention(param);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogFriendVo>resultList = new ArrayList<WeblogFriendVo>();
		for(WeblogFriendVo weblog : list ){
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			weblog.setWeblogFiles(fileList);
			for(WeblogFile wf : fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			weblog.setPraiseList(praiseList);
			
			resultList.add(weblog);
		}
		Map result = new HashMap();
		result.put("list", resultList);
		Integer nextId = weblogDao.selectWeblogBeiAttentionNextId(param);
		result.put("nextCursor", nextId);
		
		return ClientUtils.success("查询成功", result);
	}

	/**
	 * 如果是好友关系就取公开和好友可见日志，如果不是好友只取公开日志
	 */
	@Override
	public String monthWeblogList(UsersQuery usersQuery,Integer nextCursor) {
		if(usersQuery.getFriend_username() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(usersQuery.getOwner_username() == null){
			return ClientUtils.failure("当前用户ID不能为空");
		}
		if(usersQuery.getMonth() == null || usersQuery.getMonth().equals("")){
			return ClientUtils.failure("月份不能为空");
		}
		Date month = DateUtils.StrToDate(usersQuery.getMonth(), "yyyyMM");
		Long beginTime = DateUtils.getFirstDayOfMonth(month).getTime();
		Long endTime = DateUtils.getLastDayOfMonth(month).getTime();
		
		//校验是否好友关系
		UserRelation userRelation1 = new UserRelation ();
		userRelation1.setOwner_username(Integer.parseInt(usersQuery.getOwner_username()));
		userRelation1.setFriend_username(Integer.parseInt(usersQuery.getFriend_username()));
		UserRelation uv = userDao.getFriend(userRelation1);
		
		Map param = new HashMap();
		param.put("userId", usersQuery.getFriend_username());
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		//如果不是好友
		if(uv == null){
			param.put("isFriend", null);
		}else{
			param.put("isFriend", 1);
		}
		//如果nextCursor为空,取最大ID
		if(nextCursor == null || nextCursor == 0){
			nextCursor = weblogDao.selectMonthWeblogMaxId(param);
		}
		param.put("nextCursor", nextCursor);
		
		List<WeblogFriendVo> list = weblogDao.selectWeblogListByMonth(param);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogFriendVo>resultList = new ArrayList<WeblogFriendVo>();
		for(WeblogFriendVo weblog : list ){
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			for(WeblogFile wf:fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			weblog.setWeblogFiles(fileList);
			
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			weblog.setPraiseList(praiseList);
			
			resultList.add(weblog);
		}
		
		//查询当月有哪些天有日志
		
		
		Map result = new HashMap();
		result.put("list", resultList);
		Integer nextId = weblogDao.selectWeblogBeiAttentionNextId(param);
		result.put("nextCursor", nextId);
		result.put("days", weblogDao.getTheMonthDays(param));
		
		return ClientUtils.success("查询成功", result);
	}
	
	/**
	 * 如果是好友关系就取公开和好友可见日志，如果不是好友只取公开日志
	 */
	@Override
	public String dayWeblogList(UsersQuery usersQuery,Integer nextCursor) {
		if(usersQuery.getFriend_username() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(usersQuery.getOwner_username() == null){
			return ClientUtils.failure("当前用户ID不能为空");
		}
		if(usersQuery.getDay() == null || usersQuery.getDay().equals("")){
			return ClientUtils.failure("日期不能为空");
		}
		Date month = DateUtils.StrToDate(usersQuery.getDay(), "yyyyMMdd");
		Long beginTime = DateUtils.getFirstDay(month).getTime();
		Long endTime = DateUtils.getLastDay(month).getTime();
		
		//校验是否好友关系
		UserRelation userRelation1 = new UserRelation ();
		userRelation1.setOwner_username(Integer.parseInt(usersQuery.getOwner_username()));
		userRelation1.setFriend_username(Integer.parseInt(usersQuery.getFriend_username()));
		UserRelation uv = userDao.getFriend(userRelation1);
		
		Map param = new HashMap();
		param.put("userId", usersQuery.getFriend_username());
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		//如果不是好友
		if(uv == null){
			param.put("isFriend", null);
		}else{
			param.put("isFriend", 1);
		}
		//如果nextCursor为空,取最大ID
		if(nextCursor == null || nextCursor == 0){
			nextCursor = weblogDao.selectMonthWeblogMaxId(param);
		}
		param.put("nextCursor", nextCursor);
		
		List<WeblogFriendVo> list = weblogDao.selectWeblogListByMonth(param);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogFriendVo>resultList = new ArrayList<WeblogFriendVo>();
		for(WeblogFriendVo weblog : list ){
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			for(WeblogFile wf:fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			weblog.setWeblogFiles(fileList);
			
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			weblog.setPraiseList(praiseList);
			
			resultList.add(weblog);
		}
		Map result = new HashMap();
		result.put("list", resultList);
		Integer nextId = weblogDao.selectWeblogBeiAttentionNextId(param);
		result.put("nextCursor", nextId);
		
		return ClientUtils.success("查询成功", result);
	}
	
	@Override
	public String allWeblogList(WeblogVo weblogVo,Integer nextCursor) {
		
		if(weblogVo.getUserId() == null || weblogVo.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		
		if(nextCursor == null){
			nextCursor = weblogDao.selectAllWeblogMaxId();
		}
		
		Map param = new HashMap();
		param.put("weblogVo", weblogVo);
		param.put("nextCursor", nextCursor);
		List<WeblogFriendVo> list = weblogDao.selectWeblogListByAll(param);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogFriendVo>resultList = new ArrayList<WeblogFriendVo>();
		for(WeblogFriendVo weblog : list ){
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			for(WeblogFile wf:fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			weblog.setWeblogFiles(fileList);
			
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			weblog.setPraiseList(praiseList);
			
			resultList.add(weblog);
		}
		Map result = new HashMap();
		result.put("list", resultList);
		Integer nextId = weblogDao.selectWeblogAllNextId(param);
		result.put("nextCursor", nextId);
		
		return ClientUtils.success("查询成功", result);
	}

	@Override
	public String weblogListByPraise(WeblogVo weblogVo, Integer nextCursor) {
		if(weblogVo.getUserId() == null || weblogVo.getUserId() == 0){
			return ClientUtils.failure("用户ID不能为空");
		}
		
		if(nextCursor == null){
			nextCursor = 0;
		}
		
		Map param = new HashMap();
		param.put("weblogVo", weblogVo);
		param.put("nextCursor", nextCursor);
		List<WeblogFriendVo> list = weblogDao.weblogListByPraise(param);
		if(list.isEmpty()){
			Map result = new HashMap();
			result.put("list", new ArrayList());
			result.put("nextCursor", null);
			return ClientUtils.failure("没有日志",result);
		}
		List <WeblogFriendVo>resultList = new ArrayList<WeblogFriendVo>();
		for(WeblogFriendVo weblog : list ){
			List<WeblogFile> fileList = weblogDao.selectWeblogFileListByWeblogId(weblog);
			for(WeblogFile wf:fileList){
				wf.setPathSmall(wf.getPath().replaceAll("\\.", "_small."));
			}
			weblog.setWeblogFiles(fileList);
			
			List praiseList = weblogDao.selectWeblogPraiseListByWeblogId(weblog);
			weblog.setPraiseList(praiseList);
			
			resultList.add(weblog);
		}
		Map result = new HashMap();
		result.put("list", resultList);
		result.put("nextCursor", nextCursor+15);
		
		return ClientUtils.success("查询成功", result);
	}

	@Override
	public List<WeblogManagerVo> sysWeblogList(WeblogManagerVo weblogManagerVo) {
		
		return weblogDao.sysWeblogList(weblogManagerVo);
	}

	@Override
	public Integer sysWeblogListCount(WeblogManagerVo weblogManagerVo) {
		return weblogDao.sysWeblogListCount(weblogManagerVo);
	}

	@Override
	public String deteleWeblog(WeblogVo weblogVo) {
		//删除评论记录
		weblogDao.deleteWeblogCommentByWeblogId(weblogVo);
		//删除点赞记录
		weblogDao.deleteWeblogPraiseByWeblogId(weblogVo);
		//删除日志
		weblogDao.deleteWeblogById(weblogVo);
		return null;
	}

	@Override
	public Map webolgDetail(WeblogManagerVo weblogManagerVo) {
		
		Map result = new HashMap();
		Weblog weblog = new Weblog();
		weblog.setId(weblogManagerVo.getId());
		WeblogManagerVo weblog2 =weblogDao.selectWeblogByIdForVo(weblog);
		String ctime = DateUtils.DateToStr(new Date(weblog2.getCreateTime()), "yyyy-MM-dd HH:mm:ss");
		weblog2.setCreateTimeView(ctime); 
		result.put("weblog", weblog2);
		result.put("weblogImg", weblogDao.selectWeblogFileListByWeblogId(weblog));
		return result;
	}
	
	
}