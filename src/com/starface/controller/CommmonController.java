package com.starface.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starface.domain.Inform;
import com.starface.domain.Page;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.InformVo;
import com.starface.domain.vo.SystemCityVo;
import com.starface.service.CommonService;

@Controller
@RequestMapping(value = "/common")
public class CommmonController {

	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/getCities",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getCities(SystemCityVo systemCityVo){
		return commonService.getSystemCities(systemCityVo);
	}
	
	/**
	 * 获取所有的省市区
	 * @param systemCityVo
	 * @return
	 */
	@RequestMapping(value="/getAllArea",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllArea(){
		return commonService.getAllArea();
	}
	
	/**
	 * 不文明词汇列表
	 * @return
	 */
	@RequestMapping(value="/illegal_list", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String illegalList(UsersQuery usersQuery){
		
		return commonService.illegalList();
	}
	/**
	 * 举报日志
	 * @return
	 */
	@RequestMapping(value="/inform_blog", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String informBlog(Inform inform){
		
		return commonService.informBlog(inform);
	}
	/**
	 * 举报聊天
	 * @return
	 */
	@RequestMapping(value="/inform_msg", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String informMsg(Inform inform){
		
		return commonService.informMsg(inform);
	}
	
	/**
	 * 举报用户
	 * @return
	 */
	@RequestMapping(value="/inform_user", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String informUser(Inform inform){
		
		return commonService.informUser(inform);
	}
	
	/**
	 * 用户举报列表
	 * @return
	 */
	@RequestMapping(value="/inform/list", produces="text/html;charset=UTF-8")
	public String sysUserList(InformVo informVo,Map<String, Object> model,HttpSession httpSession){
		Object object = httpSession.getAttribute("user");
		if(null == object){
			model.put("errorMsg", "登录已失效,请重新登录");
			return "index";
		}
		List<InformVo> list = commonService.informManageList(informVo);
		Integer totalRow = commonService.informManageListCount(informVo);
		model.put("list", list);
		Page page = new Page(totalRow, informVo.getStart(), informVo.getLimit());
		model.put("page", page);
		return  "informManager";
	}
	
}