package com.starface.domain.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.starface.domain.Weblog;
import com.starface.domain.WeblogFile;
import com.starface.domain.WeblogPraise;

public class WeblogManagerVo extends Weblog{
	
	
	private String name;
	private String nickName;
	private String codeName;
	private List <WeblogPraise>praiseList;
	private Integer start;
	private Integer limit = 20;
	private String createTimeView;

	private List <WeblogFile> weblogFiles;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	 * @return the weblogFiles
	 */
	public List<WeblogFile> getWeblogFiles() {
		return weblogFiles;
	}

	/**
	 * @param weblogFiles the weblogFiles to set
	 */
	public void setWeblogFiles(List<WeblogFile> weblogFiles) {
		this.weblogFiles = weblogFiles;
	}

	/**
	 * @return the praiseList
	 */
	public List<WeblogPraise> getPraiseList() {
		return praiseList;
	}

	/**
	 * @param praiseList the praiseList to set
	 */
	public void setPraiseList(List<WeblogPraise> praiseList) {
		this.praiseList = praiseList;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getCreateTimeView() {
		return createTimeView;
	}

	public void setCreateTimeView(String createTimeView) {
		this.createTimeView = createTimeView;
	}
	
	
	
}