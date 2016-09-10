package com.starface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.starface.domain.SysDictionary;
import com.starface.domain.query.SysDictionaryQuery;
import com.starface.frame.core.dao.BaseService;
import com.starface.frame.core.web.controller.BaseControllerImpl;
import com.starface.service.SysDictionaryService;


/**
 * 字典信息的基本操作
 * @author Xuan.Chen
 * @date 2014年3月5日上午10:30:16
 */
@Controller
@RequestMapping("/sys/dictionary")
public class SysDictionaryController extends BaseControllerImpl<SysDictionary, SysDictionaryQuery> {
	@Autowired
	private SysDictionaryService sysDictionaryService;

	@Override
	protected BaseService<SysDictionary> getBaseService() {
		return sysDictionaryService;
	}

}