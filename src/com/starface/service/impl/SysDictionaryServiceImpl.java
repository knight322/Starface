package com.starface.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starface.dao.SysDictionaryDao;
import com.starface.domain.SysDictionary;
import com.starface.frame.core.dao.BaseDao;
import com.starface.frame.core.dao.impl.BaseServiceImpl;
import com.starface.service.SysDictionaryService;


/**
 * 字典信息服务类接口实现
 * @author Xuan.Chen
 * @date 2014年3月7日下午2:27:08
 */
@Service
public class SysDictionaryServiceImpl extends BaseServiceImpl<SysDictionary> implements SysDictionaryService {
	@Autowired
	private SysDictionaryDao sysDictionaryDao;

	@Override
	protected BaseDao<SysDictionary> getBaseDao() {
		return sysDictionaryDao;
	}

}
