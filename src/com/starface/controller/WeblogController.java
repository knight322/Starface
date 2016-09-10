package com.starface.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starface.domain.Page;
import com.starface.domain.WeblogComment;
import com.starface.domain.WeblogFavorite;
import com.starface.domain.WeblogPraise;
import com.starface.domain.query.UsersQuery;
import com.starface.domain.vo.WeblogManagerVo;
import com.starface.domain.vo.WeblogVo;
import com.starface.frame.core.utils.DateUtils;
import com.starface.service.WeblogService;

/**
 * 日志表
 * @author chancore
 *
 */
@Controller
@RequestMapping(value="/weblog")
public class WeblogController {
	
	@Autowired
	private WeblogService weblogService;
	
	/**
	 * 创建日志
	 * http://localhost:8080/Starface/weblog/createWeblog?userId=12&content=好吃&type=1
	 * @return
	 */
	@RequestMapping(value="/createWeblog", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String create(WeblogVo weblogVo){
		
		return weblogService.createBlog(weblogVo);
	}
	
	/**
	 * 评论日志
	 * http://localhost:8080/Starface/weblog/comment?userId=12&weblogId=1&content=好吃
	 * @return
	 */
	@RequestMapping(value="/comment", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String comment(WeblogComment weblogComment){
		
		return weblogService.comment(weblogComment);
	}
	
	/**
	 * 评论日志
	 * http://localhost:8080/Starface/weblog/deleteComment?userId=12&id=1
	 * @return
	 */
	@RequestMapping(value="/deleteComment", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String deleteComment(WeblogComment weblogComment){
		
		return weblogService.deleteComment(weblogComment);
	}
	
	/**
	 * 日志-点赞
	 * http://localhost:8080/Starface/weblog/praise?userId=12&weblogId=1
	 * @return
	 */
	@RequestMapping(value="/praise", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String praise(WeblogPraise weblogPraise){
		
		return weblogService.praise(weblogPraise);
	}
	
	/**
	 * 日志-取消点赞
	 * http://localhost:8080/Starface/weblog/cannelPraise?userId=12&weblogId=1
	 * @return
	 */
	@RequestMapping(value="/cannelPraise", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String cannelPraise(WeblogPraise weblogPraise){
		
		return weblogService.cannelPraise(weblogPraise);
	}
	
	/**
	 * 删除日志
	 * http://localhost:8080/Starface/weblog/delete?id=12&userId=1
	 * @return
	 */
	@RequestMapping(value="/delete", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String delete(WeblogVo weblogVo){
		
		return weblogService.delete(weblogVo);
	}
	
	/**
	 * 收藏日志
	 * http://localhost:8080/Starface/weblog/favorite?userId=1&weblogId=1
	 * @return
	 */
	@RequestMapping(value="/favorite", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String favorite(WeblogFavorite weblogFavorite){
		
		return weblogService.favorite(weblogFavorite);
	}
	
	/**
	 * 收藏日志
	 * http://localhost:8080/Starface/weblog/deleteFavorite?userId=1&weblogId=1
	 * @return
	 */
	@RequestMapping(value="/deleteFavorite", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String deleteFavorite(WeblogFavorite weblogFavorite){
		
		return weblogService.deleteFavorite(weblogFavorite);
	}
	
	/**
	 * 我的日志列表
	 * http://localhost:8080/Starface/weblog/myWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/myWeblogList", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String myWeblogList(WeblogVo weblog,Integer nextCursor,Integer friendId){
		
		return weblogService.myWeblogList(weblog,nextCursor,friendId);
	}
	
	/**
	 * 日志评论列表
	 * http://localhost:8080/Starface/weblog/commentList?id=12
	 * @return
	 */
	@RequestMapping(value="/commentList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String commentList(WeblogVo weblog,Integer nextCursor){
		
		return weblogService.commentList(weblog,nextCursor);
	}
	
	/**
	 * 日志评论列表
	 * http://localhost:8080/Starface/weblog/praiseList?id=12
	 * @return
	 */
	@RequestMapping(value="/praiseList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String praiseList(WeblogVo weblog,Integer nextCursor){
		
		return weblogService.praiseList(weblog,nextCursor);
	}
	/**
	 * 收藏列表
	 * http://localhost:8080/Starface/weblog/favoriteList?userId=2
	 * @return
	 */
	@RequestMapping(value="/favoriteList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String favoriteList(WeblogVo weblog,Integer nextCursor){
		
		return weblogService.favoriteList(weblog,nextCursor);
	}
	
	/**
	 * 好友日志列表
	 * http://localhost:8080/Starface/weblog/friendWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/friendWeblogList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String friendWeblogList(WeblogVo weblog,Integer nextCursor){
		
		return weblogService.friendWeblogList(weblog,nextCursor);
	}
	
	/**
	 * 我关注的人日志列表
	 * http://localhost:8080/Starface/weblog/attentionWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/attentionWeblogList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String attentionWeblogList(UsersQuery usersQuery,Integer nextCursor){
		
		return weblogService.attentionWeblogList(usersQuery,nextCursor);
	}
	
	/**
	 * 关注我的人日志列表
	 * http://localhost:8080/Starface/weblog/attentionWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/beiAttentionWeblogList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String beiAttentionWeblogList(UsersQuery usersQuery,Integer nextCursor){
		
		return weblogService.beiAttentionWeblogList(usersQuery,nextCursor);
	}
	
	/**
	 * 某个人某个月日志列表
	 * http://localhost:8080/Starface/weblog/monthWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/monthWeblogList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String monthWeblogList(UsersQuery usersQuery,Integer nextCursor){
		
		return weblogService.monthWeblogList(usersQuery,nextCursor);
	}
	/**
	 * 某个人某天日志列表
	 * http://localhost:8080/Starface/weblog/monthWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/dayWeblogList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String dayWeblogList(UsersQuery usersQuery,Integer nextCursor){
		
		return weblogService.dayWeblogList(usersQuery,nextCursor);
	}
	
	/**
	 * 所有用户最新日志列表
	 * http://localhost:8080/Starface/weblog/allWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/allWeblogList", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String allWeblogList(WeblogVo weblog,Integer nextCursor){
		
		return weblogService.allWeblogList(weblog,nextCursor);
	}
	
	/**
	 * 按评论最多的排序日志列表
	 * http://localhost:8080/Starface/weblog/allWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/weblogListByPraise", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String weblogListByPraise(WeblogVo weblog,Integer nextCursor){
		
		return weblogService.weblogListByPraise(weblog,nextCursor);
	}
	
	
	/**
	 * 后台日志列表
	 * @return
	 */
	@RequestMapping(value="/manager/list", produces="text/html;charset=UTF-8")
	public String sysUserList(WeblogManagerVo weblogManagerVo,Map<String, Object> model,HttpSession httpSession){
		Object object = httpSession.getAttribute("user");
		if(null == object){
			model.put("errorMsg", "登录已失效,请重新登录");
			return "index";
		}
		List<WeblogManagerVo> list = weblogService.sysWeblogList(weblogManagerVo);
		for(WeblogManagerVo wmv : list){
			long creTime = wmv.getCreateTime();
			String ctime = DateUtils.DateToStr(new Date(creTime), "yyyy-MM-dd HH:mm:ss");
			wmv.setCreateTimeView(ctime); 
		}
		Integer totalRow = weblogService.sysWeblogListCount(weblogManagerVo);
		model.put("list", list);
		model.put("weblog", weblogManagerVo);
		Page page = new Page(totalRow, weblogManagerVo.getStart(), weblogManagerVo.getLimit());
		model.put("page", page);
		return  "weblogManager";
	}
	
	/**
	 * 按评论最多的排序日志列表
	 * http://localhost:8080/Starface/weblog/allWeblogList?userId=12
	 * @return
	 */
	@RequestMapping(value="/delete_weblog", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String deleteWeblog(WeblogVo weblogVo){
		
		return weblogService.deteleWeblog(weblogVo);
	}
	
	/**
	 * 后台日志列表
	 * @return
	 */
	@RequestMapping(value="/detail", produces="text/html;charset=UTF-8")
	public String weblogDeail(WeblogManagerVo weblogManagerVo,Map<String, Object> model,HttpSession httpSession){
		Object object = httpSession.getAttribute("user");
		if(null == object){
			model.put("errorMsg", "登录已失效,请重新登录");
			return "index";
		}
		model.put("result", weblogService.webolgDetail(weblogManagerVo));
		return  "weblogDetail";
	}
	
}