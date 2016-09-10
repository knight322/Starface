package com.starface.domain;

/**
 * 
 *
 * @author xuan.chen
 * @date 2015-6-6
 *
 */
public class WeblogComment {
    /**  */
    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 日志ID */
    private Integer weblogId;

    /** 评论内容 */
    private String content;

    /** 评论时间 */
    private Long createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWeblogId() {
        return weblogId;
    }

    public void setWeblogId(Integer weblogId) {
        this.weblogId = weblogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}