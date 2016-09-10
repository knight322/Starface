package com.starface.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starface.dao.CommonDao;
import com.starface.domain.Illegal;
import com.starface.domain.Inform;
import com.starface.domain.SystemCity;
import com.starface.domain.vo.BaseQueryVo;
import com.starface.domain.vo.InformVo;
import com.starface.domain.vo.SystemCityVo;
import com.starface.frame.core.utils.ClientUtils;
import com.starface.service.CommonService;

@Service
@Transactional
public class CommonServiceImpl implements CommonService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public String getSystemCities(SystemCityVo systemCityVo) {
		BaseQueryVo queryVo = new BaseQueryVo();
		queryVo.getParameters().put("classType",systemCityVo.getClassType() );
		queryVo.getParameters().put("classParentId",systemCityVo.getClassParentId());
		List<SystemCity> list = commonDao.getSystemCities(queryVo);
		Map result  = new HashMap();
		result.put("re", list);
		return ClientUtils.success("操作成功",result);
	}

	@Override
	public String getAreaName(Integer id) {
		
		return commonDao.getAreaName(id);
	}

	@Override
	public String getAllArea() {
		
		List<SystemCity> list = commonDao.getAllArea();
		Map result  = new HashMap();
		result.put("list", list);
		return ClientUtils.success("操作成功",result);
	}

	/* (non-Javadoc)
	 * @see com.starface.service.CommonService#illegalList()
	 */
	@Override
	public String illegalList() {
		
		List<Illegal> list = commonDao.illegalList();
		Map result  = new HashMap();
		result.put("list", list);
		return ClientUtils.success("操作成功",result);
	}

	@Override
	public String informBlog(Inform inform) {
		if(inform.getUserId() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(inform.getObjId() == null){
			return ClientUtils.failure("日志ID不能为空");
		}
		inform.setType(1);
		commonDao.saveInform(inform);
		return ClientUtils.success("举报成功");
	}

	@Override
	public String informMsg(Inform inform) {
		Map result  = new HashMap();
		if(inform.getUserId() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(inform.getObjId() == null){
			return ClientUtils.failure("日志ID不能为空");
		}
		inform.setType(2);
		commonDao.saveInform(inform);
		return ClientUtils.success("举报成功",result);
	}
	@Override
	public String informUser(Inform inform) {
		Map result  = new HashMap();
		if(inform.getUserId() == null){
			return ClientUtils.failure("用户ID不能为空");
		}
		if(inform.getObjId() == null){
			return ClientUtils.failure("被举报用户ID不能为空");
		}
		inform.setType(3);
		commonDao.saveInform(inform);
		return ClientUtils.success("举报成功",result);
	}

	@Override
	public List<InformVo> informManageList(InformVo informVo) {
		
		return commonDao.informManageList(informVo);
	}
	@Override
	public Integer informManageListCount(InformVo informVo) {
		
		return commonDao.informManageListCount(informVo);
	}
	
}
