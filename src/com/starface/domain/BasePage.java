package com.starface.domain;

public class BasePage {

	private Integer currentPage;//当前第几页 默认1
	private Integer pageSize = 1;//每页多少条数据
	private Integer currentRecodNum;//当前条数
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		if(this.currentPage == null ){
			this.currentPage = 0;
		}
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
		return (pageSize ==null || pageSize ==0) ? 10 :pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the currentRecodNum
	 */
	public Integer getCurrentRecodNum() {
		return currentRecodNum;
	}
	/**
	 * @param currentRecodNum the currentRecodNum to set
	 */
	public void setCurrentRecodNum(Integer currentRecodNum) {
		this.currentRecodNum = currentRecodNum;
	}
	
	
}
