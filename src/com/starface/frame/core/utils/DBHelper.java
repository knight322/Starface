package com.starface.frame.core.utils;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starface.domain.vo.BaseQueryVo;
import com.starface.frame.core.exception.DaoException;

@Component(value = "db")
public class DBHelper {

	@Autowired(required = true)
	private SqlSession sqlSessionTemplate;
	
	public int queryForIntByMap(String sqlName,BaseQueryVo query){
		return sqlSessionTemplate.selectOne(sqlName, query);
	}
	
	public int update(String sqlName,Object obj){
		
		return sqlSessionTemplate.update(sqlName, obj);
		
	}
	
	public Object queryForObject(String sqlName,BaseQueryVo query){
		try {
			return sqlSessionTemplate.selectOne(sqlName, query);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", sqlName, e));
		}
	}
	
	
	
}
