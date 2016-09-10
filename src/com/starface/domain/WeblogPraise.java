package com.starface.domain;

/**
 * 
 *
 * @author xuan.chen
 * @date 2015-6-6
 *
 */
public class WeblogPraise {
    /**  */
    private Integer id;

    /** 点赞人ID */
    private Integer userId;

    /** 日志ID */
    private Integer weblogId;
    /**
     * 点赞类型
     */
    private Integer type;

    /** 点赞时间 */
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

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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