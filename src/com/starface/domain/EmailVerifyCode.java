package com.starface.domain;

import java.util.Date;

public class EmailVerifyCode {
    private Integer id;

    private String email;

    private String code;

    private Date createTime;
    
    private int userId;
    /**
     * 有效期时长
     */
    private Integer validityTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the validityTime
	 */
	public Integer getValidityTime() {
		return validityTime;
	}

	/**
	 * @param validityTime the validityTime to set
	 */
	public void setValidityTime(Integer validityTime) {
		this.validityTime = validityTime;
	}
	
    
    
}