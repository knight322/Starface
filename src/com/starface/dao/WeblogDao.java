package com.starface.dao;

import java.util.List;
import java.util.Map;

import com.starface.domain.Weblog;
import com.starface.domain.WeblogComment;
import com.starface.domain.WeblogFavorite;
import com.starface.domain.WeblogFile;
import com.starface.domain.WeblogPraise;
import com.starface.domain.vo.PraiseListVo;
import com.starface.domain.vo.UserRelationData;
import com.starface.domain.vo.WeblogCommentListVo;
import com.starface.domain.vo.WeblogFavoriteVo;
import com.starface.domain.vo.WeblogFriendVo;
import com.starface.domain.vo.WeblogManagerVo;
import com.starface.domain.vo.WeblogPraiseListVo;
import com.starface.domain.vo.WeblogVo;

public interface WeblogDao {
	
	/**
	 * 保存日志
	 * @param weblog
	 * @return
	 */
	public int insertWeblog(Weblog weblog);
	
	/**
	 * 保存日志文件
	 * @param weblogFile
	 * @return
	 */
	public int insertWeblogFile(WeblogFile weblogFile);
	/**
	 * 保存日志评论信息
	 * @param weblogComment
	 * @return
	 */
	public int insertWeblogComment(WeblogComment weblogComment);
	/**
	 * 更新日志
	 * @param weblog
	 * @return
	 */
	public int updateWeblog(Weblog weblog);
	/**
	 * 更新日志评论数
	 * @param weblog
	 * @return
	 */
	public int updateWeblogCommentCount(Weblog weblog);
	/**
	 * 删除日志评论
	 * @param weblogComment
	 * @return
	 */
	public int deleteWeblogComment(WeblogComment weblogComment);
	/**
	 * 根据ID获取评论
	 * @param weblogComment
	 * @return
	 */
	public WeblogComment selectWeblogCommentById(WeblogComment weblogComment);
	/**
	 * 保存点赞
	 * @param weblogPraise
	 * @return
	 */
	public int insertWeblogPraise(WeblogPraise weblogPraise);
	
	/**
	 * 更新日志点赞数
	 * @param weblog
	 * @return
	 */
	public int updateWeblogPraiseCount(Weblog weblog);
	
	/**
	 * 根据ID获取点赞
	 * @param weblogComment
	 * @return
	 */
	public WeblogPraise selectWeblogPraiseById(WeblogPraise weblogPraise);
	/**
	 * 删除点赞信息
	 * @param weblogPraise
	 * @return
	 */
	public int deleteWeblogPraise(WeblogPraise weblogPraise);
	/**
	 * 获取日志--根据ID
	 * @param weblog
	 * @return
	 */
	public Weblog selectWeblogById(Weblog weblog);
	
	/**
	 * 获取日志--根据ID
	 * @param weblog
	 * @return
	 */
	public WeblogManagerVo selectWeblogByIdForVo(Weblog weblog);
	/**
	 * 删除点赞记录--根据日志ID
	 * @param weblog
	 * @return
	 */
	public int deleteWeblogPraiseByWeblogId(Weblog weblog);
	/**
	 * 删除日志评论--根据日志ID
	 * @param weblog
	 * @return
	 */
	public int deleteWeblogCommentByWeblogId(Weblog weblog);
	/**
	 * 删除日志
	 * @param weblog
	 * @return
	 */
	public int deleteWeblogById(Weblog weblog);
	/**
	 * 收藏日志
	 * @param weblogFavorite
	 * @return
	 */
	public int insertWeblogFavorite(WeblogFavorite weblogFavorite);
	/**
	 * 查询收藏记录--根据用户ID、日志ID
	 * @param weblogFavorite
	 * @return
	 */
	public WeblogFavorite selectWeblogFavorite(WeblogFavorite weblogFavorite);
	/**
	 * 删除收藏
	 * @param weblogFavorite
	 * @return
	 */
	public int deleteWeblogFavorite(WeblogFavorite weblogFavorite);
	/**
	 * 根据用户ID查询日志列表
	 * @param weblog
	 * @return
	 */
	public List<Weblog> selectWeblogListByUserId(Map param);
	/**
	 * 获取好友日志列表
	 * @param userRelationData
	 * @return
	 */
	public List<Weblog> selectFriendWeblogListByOwnerId(UserRelationData userRelationData);
	
	/**
	 * 好友日志列表
	 * @param weblog
	 * @return
	 */
	public List<WeblogFriendVo> selectWeblogListByFriend(Map param);
	
	/**
	 * 最新日志列表
	 * @param weblog
	 * @return
	 */
	public List<WeblogFriendVo> selectWeblogListByAll(Map param);
	/**
	 * 根据日志ID查询评论列表
	 * @param weblog
	 * @return
	 */
	public List<WeblogCommentListVo> selectWeblogCommentListByWeblogid(Map param);
	/**
	 * 获取日志评论下一个ID
	 * @param weblog
	 * @return
	 */
	public Integer selectWeblogCommentNextId(Map param);
	
	/**
	 * 获取最大ID
	 * @param weblog
	 * @return
	 */
	public Integer selectWeblogCommentMaxId(Integer weblogId);
	/**
	 * 根据日志查询点赞列表
	 * @param weblog
	 * @return
	 */
	public List<WeblogPraiseListVo> selectWeblogPraiseListByWeblogid(Map param);
	
	/**
	 * 获取点赞列表下一个ID
	 * @param weblog
	 * @return
	 */
	public Integer selectWeblogPraiseNextId(Map param);
	
	/**
	 * 获取日志点赞最小ID
	 * @param weblog
	 * @return
	 */
	public Integer selectWeblogPraiseMaxId(Integer userId);
	/**
	 * 根据日志ID获取日志文件列表
	 * @param weblog
	 * @return
	 */
	public List<WeblogFile> selectWeblogFileListByWeblogId(Weblog weblog);
	/**
	 * 获取日志最大ID
	 * @param userId
	 * @return
	 */
	public Integer selectWeblogMaxId(Integer userId);
	
	/**
	 * 获取好友日志最大ID,根据前前用户ID
	 * @param param
	 * @return
	 */
	public Integer selectFriendWeblogMaxIdByOwnerId(UserRelationData userRelationData);
	
	/**
	 * 获取下一个ID
	 * @param userId
	 * @return
	 */
	public Integer selectWeblogNextId(Map param);
	/**
	 * 好友日志列表下一个坐标ID
	 * @param userRelationData
	 * @return
	 */
	public Integer selectFriendWeblogNextIdByOwnerId(UserRelationData userRelationData);
	
	/**
	 * 获取好友日志列表下一个ID
	 * @param userId
	 * @return
	 */
	public Integer selectWeblogFriendNextId(Map param);
	
	/**
	 * 获取好友日志列表下一个ID
	 * @param userId
	 * @return
	 */
	public Integer selectWeblogAllNextId(Map param);
	/**
	 * 好友日志最大ID
	 * @param userId
	 * @return
	 */
	public Integer selectFriendWeblogMaxId(Integer userId);
	
	/**
	 * 收藏列表
	 * @param weblogVo
	 * @return
	 */
	public List<WeblogFavoriteVo> selectFavoriteList(Map param);
	/**
	 * 获取收藏列表最大ID
	 * @param weblogVo
	 * @return
	 */
	public Integer selectFavoriteMaxId(WeblogVo weblogVo);
	/**
	 * 获取收藏列表下一个ID
	 * @param weblogVo
	 * @return
	 */
	public Integer selectFavoriteNextId(Map param);
	
	/**
	 * 获取下一个ID
	 * @param weblogVo
	 * @return
	 */
	public Integer selectFavoriteMinId(Integer nextCursor);
	
	/**
	 * 好友日志最大ID
	 * @param userId
	 * @return
	 */
	public Integer selectAttentionWeblogMaxId(Integer userId);
	
	/**
	 * 我关注的人日志列表
	 * @param weblog
	 * @return
	 */
	public List<WeblogFriendVo> selectWeblogListByAttention(Map param);
	/**
	 * 获取我关注的用户日志列表下一个ID
	 * @param userId
	 * @return
	 */
	public Integer selectWeblogAttentionNextId(Map param);
	
	/**
	 * 好友日志最大ID
	 * @param userId
	 * @return
	 */
	public Integer selectBeiAttentionWeblogMaxId(Integer userId);
	
	/**
	 * 我关注的人日志列表
	 * @param weblog
	 * @return
	 */
	public List<WeblogFriendVo> selectWeblogListByBeiAttention(Map param);
	/**
	 * 获取我关注的用户日志列表下一个ID
	 * @param userId
	 * @return
	 */
	public Integer selectWeblogBeiAttentionNextId(Map param);
	
	/**
	 * 点赞类型统计
	 * @param weblog
	 * @return
	 */
	public List<PraiseListVo> selectWeblogPraiseListByWeblogId(Weblog weblog);
	
	/**
	 * 某个月最大ID
	 * @param userId
	 * @return
	 */
	public Integer selectMonthWeblogMaxId(Map param);
	/**
	 * 获取某人某月日志
	 * @param param
	 * @return
	 */
	public List selectWeblogListByMonth(Map param);
	
	/**
	 * 某人某月日志列表下一个ID
	 * @param userId
	 * @return
	 */
	public Integer selectWeblogMonthNextId(Map param);

	/**
	 * 查询当月哪几天有日志
	 * @param param
	 * @return
	 */
	public List getTheMonthDays(Map param);
	
	/**
	 * 最新日志列表
	 * @return
	 */
	public Integer selectAllWeblogMaxId();
	
	/**
	 * 最新日志列表
	 * @return
	 */
	public List<WeblogFriendVo> weblogListByPraise(Map param);
	
	
	public List<WeblogManagerVo> sysWeblogList(WeblogManagerVo weblogManagerVo);
	
	public Integer sysWeblogListCount(WeblogManagerVo weblogManagerVo);
	
	public Integer deleteWeblog(WeblogVo weblogVo);
}