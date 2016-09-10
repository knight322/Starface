package com.starface.domain.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 *
 * @createTime : Jun 8, 2012 2:15:44 PM
 * @version : 1.0
 * @description :
 * <p>目的：方便统一所有查询Vo（目前parameters参数在php端并不支持复杂对象传入）</p>
 * <p>
 * <b>注意PHP端使用规范：</b>
 * <p>例如如下：
 * <p>
 * 	 <code>	
 * 	  $queryVo = new com_mall_life_core_vo_BaseQueryVo();
 *    
 *    $queryVo->parameters = array(
 *			"test1" => 'test1', //字符串
 *			"test2" => 2,       //数字
 *			"test3" => '3',     //数字
 *			"test4"=>array("A"=>'A',"B"=>'B',"C"=>'1339586124088',"D"=>'5.5'), //数组 （这种方式会失败）
 *		    "test5"=>array('a','b','1339586124088','5.5') //数组 （可行）
 *		);
 *    </code>
 * <p> parameters 中传数组时，只可以上面'test5'方式处理传入  
 *    
 * 
 * <p> 对应RPC调用而言，可以改进getParameters()方法，方便支持复杂对象及更深层次的嵌套数组（递归解析，但目前没必要）
 * 
 * 
 * <p>
 * <b>注意java端数据类型转换时： </b>
 * 	 <li>方式1： Long shopId = (Long)params.get("shopId"); 可能发生类型转换错误 ，不推荐此种方式
 *   <li>方式2： Long shopId = Long.valueOf(params.get("shopId").toString()); 可行(NullPointerException另行检测)
 *   <li>方式3： Long shopId = Long.parseLong(params.get("shopId").toString()); 可行(NullPointerException另行检测)
 */
public  class BaseQueryVo implements Serializable {
	private static final long serialVersionUID = 64137257767887446L;
	/**
	 * 
	 */

	/**其他查询条件列表*/
	private Map<String,Object> parameters;
	
	/**当前查询时间*/
	private long now = System.currentTimeMillis();
	
	/**结果排序条件*/
	private String orderBy;



	/**结果排序*/
	private String ascOrDesc;
	
	/**当前页开始*/
	private Integer offset;
	
	/**页大小*/
	private Integer limit;

    /**
     * 是否forupdate，只能为"" 或者 " for update"//注意加空格，防止sql语法错误
     */
    private String forUpdate = "";

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}
	public String getAscOrDesc() {
		return ascOrDesc;
	}

	public void setAscOrDesc(String ascOrDesc) {
		this.ascOrDesc = ascOrDesc;
	}
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}



	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    private transient boolean isFirst = true;
	private boolean isFromPHP = false;

	@SuppressWarnings("unchecked")
	public Map<String, Object> getParameters() {
		
		if(parameters == null){
			parameters = new HashMap<String,Object>();
			return  parameters;
		}
		return this.parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

    public String getForUpdate() {
        return forUpdate;
    }

    public void setForUpdate(String forUpdate) {
        this.forUpdate = forUpdate;
    }

    @Override
    public String toString() {
        return "BaseQueryVo{" +
                "parameters=" + parameters +
                ", now=" + now +
                ", orderBy='" + orderBy + '\'' +
                ", ascOrDesc='" + ascOrDesc + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", forUpdate='" + forUpdate + '\'' +
                ", isFirst=" + isFirst +
                ", isFromPHP=" + isFromPHP +
                '}';
    }

    /**
	 * TEST
	 * @param args
	 */
	public static void main(String args[]){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("a", 1);
		System.out.println((Long)map.get("a"));//Error
	}

}
