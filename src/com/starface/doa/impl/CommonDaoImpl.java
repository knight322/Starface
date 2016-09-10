package com.starface.doa.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starface.dao.CommonDao;
import com.starface.domain.Illegal;
import com.starface.domain.Inform;
import com.starface.domain.SystemCity;
import com.starface.domain.vo.BaseQueryVo;
import com.starface.domain.vo.InformVo;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<SystemCity> getSystemCities(BaseQueryVo queryVo) {
		
		return sqlSessionTemplate.selectList("com.starface.domain.SystemCityMapper.selectByQueryVo", queryVo);
	}

	@Override
	public String getAreaName(Integer id) {
		String name = (String)sqlSessionTemplate.selectOne("com.starface.domain.SystemCityMapper.selectByIdForClassName",id);
		return name;
	}

	@Override
	public List<SystemCity> getAllArea() {
		return sqlSessionTemplate.selectList("com.starface.domain.SystemCityMapper.getAllArea");
	}

	@Override
	public List<Illegal> illegalList() {
		
		return sqlSessionTemplate.selectList("com.starface.domain.SystemCityMapper.illegalList");
	}

	@Override
	public Integer saveInform(Inform inform) {
		
		return sqlSessionTemplate.insert("com.starface.domain.SystemCityMapper.saveInform", inform);
	}

	@Override
	public List<InformVo> informManageList(InformVo informVo) {

		return sqlSessionTemplate.selectList("com.starface.domain.SystemCityMapper.informManageList", informVo);
	}
	
	@Override
	public Integer informManageListCount(InformVo informVo) {

		return sqlSessionTemplate.selectOne("com.starface.domain.SystemCityMapper.informManageListCount", informVo);
	}
	
	
	
}