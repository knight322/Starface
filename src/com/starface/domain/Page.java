package com.starface.domain;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author 作者 : chenxuan
 * @version 创建时间：2016年1月15日 下午5:00:07 
 * 类说明 
 */
public class Page {
	
	private Integer totalRow;
	private List<Integer> pageList = new ArrayList<Integer>();
	private Integer currentPage;
	private Integer limit;
	private Integer totalPage;
	public Page(Integer totalRow,Integer currentPage,Integer limit){
		this.currentPage = currentPage;
		this.limit = limit;
		totalPage = totalRow/limit;
		for(int i = 0;i<totalPage;i++){
			pageList.add(i);
		}
		int mod = totalRow%limit;
		if(mod>=1){
			pageList.add(totalPage);
		}
	}
	/**
	 * @return the totalRow
	 */
	public Integer getTotalRow() {
		return totalRow;
	}
	/**
	 * @param totalRow the totalRow to set
	 */
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}
	/**
	 * @return the pageList
	 */
	public List<Integer> getPageList() {
		return pageList;
	}
	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	/**
	 * @return the totalPage
	 */
	public Integer getTotalPage() {
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
