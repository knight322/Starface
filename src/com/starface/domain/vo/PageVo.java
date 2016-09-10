package com.starface.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class PageVo<T> {

	private Integer totalPage;//总页数
	private List<T> list = new ArrayList<T>();//每页显示集合
	private Integer totalRecord;//总条数
	private Integer currentPage;//当前第几页 默认1
	private Integer pageSize ;//每页多少条数据
	/**
	 * @return the totalPage
	 */
	public Integer getTotalPage() {
		if(null == this.totalRecord || this.totalRecord==0) {
			this.totalPage = 0;
		}else{
			this.totalPage = this.totalRecord / this.getPageSize();
			int tmp = this.totalRecord % this.getPageSize() > 0 ? 1:0;
			this.totalPage = this.totalPage + tmp;
		}
		return totalPage;
	}
	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	/**
	 * @return the totalRecord
	 */
	public Integer getTotalRecord() {
		return totalRecord;
	}
	/**
	 * @param totalRecord the totalRecord to set
	 */
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
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
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}