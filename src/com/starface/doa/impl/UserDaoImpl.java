package com.starface.doa.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starface.dao.UserDao;
import com.starface.domain.UserRelation;
import com.starface.domain.UserSimpleData;
import com.starface.domain.Users;
import com.starface.domain.po.UserPo;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.BaseQueryVo;
import com.starface.frame.core.utils.DBHelper;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired(required = true)
	private DBHelper db;
	@Autowired
	private SqlSession sqlSessionTemplate;
	
	
	@Override
	public int checkUserIsExists(BaseQueryVo query) {
		return db.queryForIntByMap("com.starface.domain.UsersMapper.count", query);
	}
	
	@Override
	public int userIsExixts(BaseQueryVo query) {
		return db.queryForIntByMap("com.starface.domain.UsersMapper.userIsExixts", query);
	}
	
	
	@Override
	public Users getUserByOpenTypeAndOpenId(BaseQueryVo query) {
		return this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getUserByOpenTypeAndOpenId",query);
	}

	@Override
	public int saveUser(Users user) {
		
		return db.update("com.starface.domain.UsersMapper.insertSelective", user);
	}

	@Override
	public Users getUser(BaseQueryVo query) {
		
		return (Users)db.queryForObject("com.starface.domain.UsersMapper.selectByUsers", query);
	}
	
	@Override
	public Users login(UsersQuery query) {
		
		return (Users)sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.login", query);
	}

	@Override
	public int updateUser(BaseQueryVo query) {
		db.update("com.starface.domain.UsersMapper.updateByPrimaryKeySelective", query);
		return 0;
	}

	@Override
	public int modifyPwdByEmail(BaseQueryVo query) {
		
		return db.update("com.starface.domain.UsersMapper.modifyPwdByEmail", query);
	}
	
	@Override
	public int modifyPwdByMobile(BaseQueryVo query) {
		
		return db.update("com.starface.domain.UsersMapper.modifyPwdByMobile", query);
	}
	
	@Override
	public int modifyPwdById(BaseQueryVo query) {
		
		return db.update("com.starface.domain.UsersMapper.modifyPwdById", query);
	}

	@Override
	public List searchUser(BaseQueryVo query) {
		List list = this.sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.searchUser", query);
		return list;
	}

	@Override
	public int searchUserCount(BaseQueryVo query) {
		int count = this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.searchUserCount", query);
		return count;
	}

	@Override
	public UserPo getUserInfo(BaseQueryVo query) {
		return (UserPo)db.queryForObject("com.starface.domain.UsersMapper.getUserInfo", query);
	}
	
	@Override
	public UserSimpleData getUserInfoSimple(BaseQueryVo query) {
		return (UserSimpleData)db.queryForObject("com.starface.domain.UsersMapper.getUserInfoSimple", query);
	}
	@Override
	public List<UserPo> getUserInfoByIds(BaseQueryVo query) {
		return this.sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.getUserInfo", query);
	}
	@Override
	public Users getUserByMobile(BaseQueryVo query) {
		return this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getUserByMobile", query);
	}
	@Override
	public int addFriend(UserRelation userRelation) {
		
		return sqlSessionTemplate.insert("com.starface.domain.UsersMapper.addFriend",userRelation);
	}
	
	@Override
	public int addAttention(UserRelation userRelation) {
		
		return sqlSessionTemplate.insert("com.starface.domain.UsersMapper.addAttention",userRelation);
	}
	
	@Override
	public int deleteFriend(UserRelation userRelation) {
		
		return sqlSessionTemplate.delete("com.starface.domain.UsersMapper.deleteFriend",userRelation);
	}
	
	@Override
	public int cancelAttention(UserRelation userRelation) {
		
		return sqlSessionTemplate.delete("com.starface.domain.UsersMapper.cancelAttention",userRelation);
	}

	@Override
	public List getFriendList(UserRelation userRelation) {
		List list = this.sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.getFriendList", userRelation);
		return list;
	}
	
	@Override
	public List getAttentionList(Map param) {
		List list = this.sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.getAttentionList", param);
		return list;
	}
	
	@Override
	public Integer getAttentionNextId(Map param) {
		
		return sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getAttentionNextId", param);
	}
	
	@Override
	public Integer getAttentionBeiNextId(Map param) {
		
		return sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getAttentionBeiNextId", param);
	}

	@Override
	public List getAttentionBeiList(Map param) {
		List list = this.sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.getAttentionBeiList", param);
		return list;
	}

	@Override
	public UserRelation getFriend(UserRelation userRelation) {
		UserRelation user = (UserRelation)this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getFriend", userRelation);
		return user;
	}
	
	@Override
	public UserRelation getAttention(UserRelation userRelation) {
		UserRelation user = (UserRelation)this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getAttention", userRelation);
		return user;
	}

	@Override
	public Integer getAttentionMaxId(UserRelation userRelation) {
		
		return sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getAttentionMaxId", userRelation);
	}
	@Override
	public Integer getAttentionBeiMaxId(UserRelation userRelation) {
		
		return sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getAttentionBeiMaxId", userRelation);
	}

	@Override
	public int uploadLocal(UsersQuery usersQuery) {
		
		return sqlSessionTemplate.update("com.starface.domain.UsersMapper.uploadLocal",usersQuery);
	}

	@Override
	public List nearbyUser(BaseQueryVo query) {
		List list = this.sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.nearbyUser", query);
		return list;
	}

	@Override
	public int nearbyUserCount(BaseQueryVo query) {
		int count = this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.nearbyUserCount", query);
		return count;
	}

	@Override
	public List discover(BaseQueryVo query) {
		List list = this.sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.discover", query);
		return list;
	}

	@Override
	public int sysLogin(UsersQuery usersQuery) {
		int count = this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.sysLogin",usersQuery);
		return count;
	}

	@Override
	public List<UsersQuery> sysUserList(UsersQuery usersQuery) {
		
		List<UsersQuery> list = this.sqlSessionTemplate.selectList("com.starface.domain.UsersMapper.sysUserList", usersQuery);
		return list;
	}
	
	@Override
	public Integer sysUserListCount(UsersQuery usersQuery) {
		
		Integer count = this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.sysUserListCount", usersQuery);
		return count;
	}

	@Override
	public Integer lockUser(UsersQuery usersQuery) {
		return this.sqlSessionTemplate.update("com.starface.domain.UsersMapper.lockUser", usersQuery);
	}

	@Override
	public Integer unlockUser(UsersQuery usersQuery) {
		return this.sqlSessionTemplate.update("com.starface.domain.UsersMapper.unlockUser", usersQuery);
	}

	@Override
	public Integer saymsgUser(UsersQuery usersQuery) {
		return this.sqlSessionTemplate.update("com.starface.domain.UsersMapper.saymsgUser", usersQuery);
	}

	@Override
	public Integer unsaymsgUser(UsersQuery usersQuery) {
		return this.sqlSessionTemplate.update("com.starface.domain.UsersMapper.unsaymsgUser", usersQuery);
	}
	@Override
	public UserPo getUserById(Integer userId) {
		return this.sqlSessionTemplate.selectOne("com.starface.domain.UsersMapper.getUserById", userId);
	}
	
	

}