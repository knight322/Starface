package com.starface.doa.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starface.dao.WeblogDao;
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

@Repository
public class WeblogDaoImpl implements WeblogDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String nameSpace = "com.starface.domain.WebolgMapper.";
	
	@Override
	public int insertWeblog(Weblog weblog) {
		return sqlSessionTemplate.insert(nameSpace+"insertWeblog",weblog);
	}

	@Override
	public int insertWeblogFile(WeblogFile weblogFile) {
		
		return sqlSessionTemplate.insert(nameSpace+"insertWeblogFile",weblogFile);
	}

	@Override
	public int insertWeblogComment(WeblogComment weblogComment) {
		return sqlSessionTemplate.insert(nameSpace+"insertWeblogComment",weblogComment);
	}

	@Override
	public int updateWeblog(Weblog weblog) {
		
		return sqlSessionTemplate.update(nameSpace+"updateWeblog",weblog);
	}

	@Override
	public int updateWeblogCommentCount(Weblog weblog) {
		
		return sqlSessionTemplate.update(nameSpace+"updateWeblogCommentCount",weblog);
	}

	@Override
	public int deleteWeblogComment(WeblogComment weblogComment) {
		return sqlSessionTemplate.delete(nameSpace+"deleteWeblogComment", weblogComment);
	}

	@Override
	public WeblogComment selectWeblogCommentById(WeblogComment weblogComment) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogCommentById", weblogComment);
	}

	@Override
	public int insertWeblogPraise(WeblogPraise weblogPraise) {
		
		return sqlSessionTemplate.insert(nameSpace+"insertWeblogPraise", weblogPraise);
	}

	@Override
	public int updateWeblogPraiseCount(Weblog weblog) {
		
		return sqlSessionTemplate.update(nameSpace+"updateWeblogPraiseCount",weblog);
	}

	@Override
	public WeblogPraise selectWeblogPraiseById(WeblogPraise weblogPraise) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogPraiseById", weblogPraise);
	}

	@Override
	public int deleteWeblogPraise(WeblogPraise weblogPraise) {
		
		return sqlSessionTemplate.delete(nameSpace+"deleteWeblogPraise", weblogPraise);
	}

	@Override
	public Weblog selectWeblogById(Weblog weblog) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogById", weblog);
	}
	
	@Override
	public WeblogManagerVo selectWeblogByIdForVo(Weblog weblog) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogByIdForVo", weblog);
	}

	@Override
	public int deleteWeblogPraiseByWeblogId(Weblog weblog) {
		
		return sqlSessionTemplate.delete(nameSpace+"deleteWeblogPraiseByWeblogId", weblog);
	}

	@Override
	public int deleteWeblogCommentByWeblogId(Weblog weblog) {
		
		return sqlSessionTemplate.delete(nameSpace+"deleteWeblogCommentByWeblogId", weblog);
	}

	@Override
	public int deleteWeblogById(Weblog weblog) {
		
		return sqlSessionTemplate.delete(nameSpace+"deleteWeblogById", weblog);
	}

	@Override
	public int insertWeblogFavorite(WeblogFavorite weblogFavorite) {
		
		return sqlSessionTemplate.insert(nameSpace+"insertWeblogFavorite", weblogFavorite);
	}

	@Override
	public WeblogFavorite selectWeblogFavorite(WeblogFavorite weblogFavorite) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogFavorite", weblogFavorite);
	}

	@Override
	public int deleteWeblogFavorite(WeblogFavorite weblogFavorite) {
		
		return sqlSessionTemplate.delete(nameSpace+"deleteWeblogFavorite", weblogFavorite);
	}

	@Override
	public List<Weblog> selectWeblogListByUserId(Map param) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogListByUserId", param);
	}
	
	@Override
	public List<Weblog> selectFriendWeblogListByOwnerId(UserRelationData userRelationData) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectFriendWeblogListByOwnerId", userRelationData);
	}

	@Override
	public List<WeblogFriendVo> selectWeblogListByFriend(Map param) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogListByFriend", param);
	}
	
	@Override
	public List<WeblogFriendVo> selectWeblogListByAll(Map param) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogListByAll", param);
	}

	@Override
	public List<WeblogCommentListVo> selectWeblogCommentListByWeblogid(Map param) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogCommentListByWeblogid", param);
	}
	
	
	@Override
	public Integer selectWeblogCommentNextId(Map param) {
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogCommentNextId", param);
	}
	

	@Override
	public Integer selectWeblogPraiseMaxId(Integer userId) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogPraiseMaxId", userId);
	}

	@Override
	public List<WeblogPraiseListVo> selectWeblogPraiseListByWeblogid(Map param) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogPraiseListByWeblogid", param);
	}
	
	
	@Override
	public Integer selectWeblogPraiseNextId(Map param) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogPraiseNextId", param);
	}

	@Override
	public List<WeblogFile> selectWeblogFileListByWeblogId(Weblog weblog) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogFileListByWeblogId", weblog);
	}

	@Override
	public Integer selectWeblogMaxId(Integer userId) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogMaxId", userId);
	}
	
	
	@Override
	public Integer selectFriendWeblogMaxIdByOwnerId(UserRelationData userRelationData) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectFriendWeblogMaxIdByOwnerId", userRelationData);
	}

	@Override
	public Integer selectFriendWeblogMaxId(Integer userId) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectFriendWeblogMaxId", userId);
	}

	@Override
	public Integer selectWeblogNextId(Map param) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogNextId", param);
	}
	
	@Override
	public Integer selectFriendWeblogNextIdByOwnerId(UserRelationData userRelationData) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectFriendWeblogNextIdByOwnerId", userRelationData);
	}

	@Override
	public Integer selectWeblogFriendNextId(Map param) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogFriendNextId", param);
	}
	
	@Override
	public Integer selectWeblogAllNextId(Map param) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogAllNextId", param);
	}

	@Override
	public List<WeblogFavoriteVo> selectFavoriteList(Map param) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectFavoriteList",  param);
	}
	
	
	@Override
	public Integer selectWeblogCommentMaxId(Integer weblogId) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogCommentMaxId", weblogId);
	}

	@Override
	public Integer selectFavoriteMaxId(WeblogVo weblogVo) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectFavoriteMaxId", weblogVo);
	}
	
	@Override
	public Integer selectFavoriteNextId(Map param) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectFavoriteNextId", param);
	}

	@Override
	public Integer selectFavoriteMinId(Integer nextCursor) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectFavoriteMinId", nextCursor);
	}
	
	@Override
	public Integer selectAttentionWeblogMaxId(Integer userId) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectAttentionWeblogMaxId", userId);
	}

	@Override
	public List<WeblogFriendVo> selectWeblogListByAttention(Map param) {
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogListByAttention", param);
	}

	@Override
	public Integer selectWeblogAttentionNextId(Map param) {
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogAttentionNextId", param);
	}
	
	@Override
	public Integer selectBeiAttentionWeblogMaxId(Integer userId) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectBeiAttentionWeblogMaxId", userId);
	}

	@Override
	public List<WeblogFriendVo> selectWeblogListByBeiAttention(Map param) {
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogListByBeiAttention", param);
	}

	@Override
	public Integer selectWeblogBeiAttentionNextId(Map param) {
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogBeiAttentionNextId", param);
	}

	@Override
	public List<PraiseListVo> selectWeblogPraiseListByWeblogId(Weblog weblog) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogPraiseListByWeblogId", weblog);
	}

	@Override
	public Integer selectMonthWeblogMaxId(Map param) {
		Integer maxId = (Integer)sqlSessionTemplate.selectOne(nameSpace+"selectMonthWeblogMaxId",param);
		return maxId;
	}

	@Override
	public List selectWeblogListByMonth(Map param) {
		
		return sqlSessionTemplate.selectList(nameSpace+"selectWeblogListByMonth", param);
	}
	
	@Override
	public Integer selectWeblogMonthNextId(Map param) {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectWeblogMonthNextId",param);
	}
	@Override
	public List getTheMonthDays(Map param) {
		
		return sqlSessionTemplate.selectList(nameSpace+"getTheMonthDays",param);
	}
	
	@Override
	public Integer selectAllWeblogMaxId() {
		
		return sqlSessionTemplate.selectOne(nameSpace+"selectAllWeblogMaxId");
	}
	@Override
	public List weblogListByPraise(Map pararm) {
		
		return sqlSessionTemplate.selectList(nameSpace+"weblogListByPraise",pararm);
	}

	@Override
	public List<WeblogManagerVo> sysWeblogList(WeblogManagerVo weblogManagerVo) {
		return sqlSessionTemplate.selectList(nameSpace+"sysWeblogList",weblogManagerVo);
	}

	@Override
	public Integer sysWeblogListCount(WeblogManagerVo weblogManagerVo) {
		return sqlSessionTemplate.selectOne(nameSpace+"sysWeblogListCount",weblogManagerVo);
	}

	@Override
	public Integer deleteWeblog(WeblogVo weblogVo) {
		return this.sqlSessionTemplate.update(nameSpace+"deleteWeblog", weblogVo);
	}
	
	
}