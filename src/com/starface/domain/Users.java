package com.starface.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Users {
    private Integer id;

    private String userName;

    private String pwd;

    private String nickName;

    private Date createTime;

    private Integer isStop;

    private String codeName;

    private Integer emailCertified;

    private String name;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String sign;

    private String company;

    private String school;

    private Integer cityId;

    private String hometown;

    private String remark;

    private String userIcon;
    
    private Integer provinceId;
	
	private Integer areaId;
	
	private String provinceName;
	
	private String cityName;
	private String areaName;
	
	private Integer hometownProvinceId;
	private Integer hometownCityId;
	private Integer hometownAreaId;
	private String hometownProvinceName;
	private String hometownCityName;
	private String hometownAreaName;
	
	private String mobile;
	private Integer isLock;
	private Integer isSaymsg;
    /**
     * 0:未填写
     * 1:男
     * 2:女
     */
    private Integer gender;
    
    private String longitude;
    private String latitude;
    private String pet ;
    private String books ;
    private String movie ;
    private String sport ;
    private String music ;
    private String openId;
    private Integer openType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    public Integer getEmailCertified() {
        return emailCertified;
    }

    public void setEmailCertified(Integer emailCertified) {
        this.emailCertified = emailCertified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown == null ? null : hometown.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return the provinceId
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * @param provinceId the provinceId to set
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the hometownProvinceId
	 */
	public Integer getHometownProvinceId() {
		return hometownProvinceId;
	}

	/**
	 * @param hometownProvinceId the hometownProvinceId to set
	 */
	public void setHometownProvinceId(Integer hometownProvinceId) {
		this.hometownProvinceId = hometownProvinceId;
	}

	/**
	 * @return the hometownCityId
	 */
	public Integer getHometownCityId() {
		return hometownCityId;
	}

	/**
	 * @param hometownCityId the hometownCityId to set
	 */
	public void setHometownCityId(Integer hometownCityId) {
		this.hometownCityId = hometownCityId;
	}

	/**
	 * @return the hometownAreaId
	 */
	public Integer getHometownAreaId() {
		return hometownAreaId;
	}

	/**
	 * @param hometownAreaId the hometownAreaId to set
	 */
	public void setHometownAreaId(Integer hometownAreaId) {
		this.hometownAreaId = hometownAreaId;
	}

	/**
	 * @return the hometownProvinceName
	 */
	public String getHometownProvinceName() {
		return hometownProvinceName;
	}

	/**
	 * @param hometownProvinceName the hometownProvinceName to set
	 */
	public void setHometownProvinceName(String hometownProvinceName) {
		this.hometownProvinceName = hometownProvinceName;
	}

	/**
	 * @return the hometownCityName
	 */
	public String getHometownCityName() {
		return hometownCityName;
	}

	/**
	 * @param hometownCityName the hometownCityName to set
	 */
	public void setHometownCityName(String hometownCityName) {
		this.hometownCityName = hometownCityName;
	}

	/**
	 * @return the hometownAreaName
	 */
	public String getHometownAreaName() {
		return hometownAreaName;
	}

	/**
	 * @param hometownAreaName the hometownAreaName to set
	 */
	public void setHometownAreaName(String hometownAreaName) {
		this.hometownAreaName = hometownAreaName;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the pet
	 */
	public String getPet() {
		return pet;
	}

	/**
	 * @param pet the pet to set
	 */
	public void setPet(String pet) {
		this.pet = pet;
	}

	/**
	 * @return the books
	 */
	public String getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(String books) {
		this.books = books;
	}

	/**
	 * @return the movie
	 */
	public String getMovie() {
		return movie;
	}

	/**
	 * @param movie the movie to set
	 */
	public void setMovie(String movie) {
		this.movie = movie;
	}

	/**
	 * @return the sport
	 */
	public String getSport() {
		return sport;
	}

	/**
	 * @param sport the sport to set
	 */
	public void setSport(String sport) {
		this.sport = sport;
	}

	/**
	 * @return the music
	 */
	public String getMusic() {
		return music;
	}

	/**
	 * @param music the music to set
	 */
	public void setMusic(String music) {
		this.music = music;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the openType
	 */
	public Integer getOpenType() {
		return openType;
	}

	/**
	 * @param openType the openType to set
	 */
	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getIsSaymsg() {
		return isSaymsg;
	}

	public void setIsSaymsg(Integer isSaymsg) {
		this.isSaymsg = isSaymsg;
	}
	
    
}