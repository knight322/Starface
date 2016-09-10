package com.starface.service;

import java.util.List;
import java.util.Map;

import com.starface.domain.WeblogComment;
import com.starface.domain.WeblogFavorite;
import com.starface.domain.WeblogPraise;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.WeblogManagerVo;
import com.starface.domain.vo.WeblogVo;

public interface WeblogService {

	/**
	 * 保存日志
	 * @param weblog
	 * @return
	 */
	public String createBlog(WeblogVo weblogVo);
	/**
	 * 评论日志
	 * @param weblogComment
	 * @return
	 */
	public String comment(WeblogComment weblogComment);
	/**
	 * 删除评论
	 * @param weblogComment
	 * @return
	 */
	public String deleteComment(WeblogComment weblogComment);
	/**
	 * 日志点赞
	 * @param weblogPraise
	 * @return
	 */
	public String praise(WeblogPraise weblogPraise);
	/**
	 * 取消点赞
	 * @param weblogPraise
	 * @return
	 */
	public String cannelPraise(WeblogPraise weblogPraise);
	/**
	 * 删除日志
	 * @param weblogVo
	 * @return
	 */
	public String delete(WeblogVo weblogVo);
	/**
	 * 收藏日志
	 * @param weblogFavorite
	 * @return
	 */
	public String favorite(WeblogFavorite weblogFavorite);
	/**
	 * 删除收藏
	 * @param weblogFavorite
	 * @return
	 */
	public String deleteFavorite(WeblogFavorite weblogFavorite);
	/**
	 * 我的日志列表
	 * @param weblogVo
	 * @return
	 */
	public String myWeblogList(WeblogVo weblogVo,Integer nextCursor,Integer friendId);
	
	/**
	 * 好友日志列表
	 * @param weblogVo
	 * @return
	 */
	public String friendWeblogList(WeblogVo weblogVo,Integer nextCursor);
	/**
	 * 最新日志列表
	 * @param weblogVo
	 * @return
	 */
	public String allWeblogList(WeblogVo weblogVo,Integer nextCursor);
	/**
	 * 最新日志列表
	 * @param weblogVo
	 * @return
	 */
	public String weblogListByPraise(WeblogVo weblogVo,Integer nextCursor);
	
	/**
	 * 评论列表
	 * @param weblogVo
	 * @return
	 */
	public String commentList(WeblogVo weblogVo,Integer nextCursor);
	
	/**
	 * 点赞人列表
	 * @param weblogVo
	 * @return
	 */
	public String praiseList(WeblogVo weblogVo,Integer nextCursor);
	
	/**
	 * 收藏列表
	 * @param weblogVo
	 * @return
	 */
	public String favoriteList(WeblogVo weblogVo,Integer nextCursor);
	/**
	 * 我关注的人日志列表
	 */
	public String attentionWeblogList(UsersQuery usersQuery,Integer nextCursor);
	
	/**
	 * 我关注的人日志列表
	 */
	public String beiAttentionWeblogList(UsersQuery usersQuery,Integer nextCursor);
	/**
	 * 某个人某个月日志列表
	 * @param usersQuery
	 * @param nextCursor
	 * @return
	 */
	public String monthWeblogList(UsersQuery usersQuery,Integer nextCursor);
	
	/**
	 * 某个人某个月日志列表
	 * @param usersQuery
	 * @param nextCursor
	 * @return
	 */
	public String dayWeblogList(UsersQuery usersQuery,Integer nextCursor);
	
	public List<WeblogManagerVo> sysWeblogList(WeblogManagerVo weblogManagerVo);
	
	public Integer sysWeblogListCount(WeblogManagerVo weblogManagerVo);
	
	public String deteleWeblog(WeblogVo weblogVo);
	
	public Map webolgDetail(WeblogManagerVo weblogManagerVo);
}