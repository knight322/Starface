package com.starface.dao;

import java.util.List;

import com.starface.domain.Illegal;
import com.starface.domain.Inform;
import com.starface.domain.SystemCity;
import com.starface.domain.vo.BaseQueryVo;
import com.starface.domain.vo.InformVo;

public interface CommonDao {
	
	/**
	 * 获取省/市/区(县)
	 * @param queryVo
	 * @return
	 */
	public List<SystemCity> getSystemCities(BaseQueryVo queryVo);
	
	/**
	 * 根据ID获取省/市/区(县)名称
	 * @param id
	 * @return
	 */
	public String getAreaName(Integer id);

	public List<SystemCity> getAllArea();
	
	public List<Illegal> illegalList();
	
	public Integer saveInform(Inform inform);
	
	public List<InformVo> informManageList(InformVo informVo);
	
	public Integer informManageListCount(InformVo informVo);
	
}
