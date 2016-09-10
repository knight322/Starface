package com.starface.domain.vo;



import java.util.List;

import com.starface.domain.Weblog;
import com.starface.domain.WeblogFile;
import com.starface.domain.WeblogPraise;

public class WeblogListVo extends Weblog{
	
//	private List <WeblogCommentListVo> weblogCommentListVo;
	
//	private List<WeblogPraiseListVo> weblogPraiseListVo;
	
	private List <WeblogFile> weblogFiles;
	
	private List <WeblogPraise>praiseList;


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
	
}