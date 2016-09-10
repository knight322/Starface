package com.starface.domain.po;

public class UserPo {

	
	private Integer id;
	private String userName;
	private String nickName;
	private String codeName;
	private Integer emailCertified;
	private String provinceName;
	private String cityName;
	private String areaName;
	private String userIcon;
	private String sign;
	private String remark;
	private Integer age;
	private String createTime;
	private String school;
	private String hometownProvinceName;
	private String hometownCityName;
	private String hometownAreaName;
	private Integer gender;
	private Integer hometownProvinceId;
	private Integer hometownCityId;
	private Integer hometownAreaId;
	private Integer provinceId;
	private Integer cityId;
	private Integer areaId;
	private Integer guanzhu = 0;
	private Integer beiguanzhu = 0;
	private Integer haoyou = 0;
	private Integer dis;
	private String mobile;
    
    private String longitude;
    private String latitude;
    private String pet ;
    private String books ;
    private String movie ;
    private String sport ;
    private String music ;
    private Integer isLock;
    private Integer isSaymsg;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the emailCertified
	 */
	public Integer getEmailCertified() {
		return emailCertified;
	}
	/**
	 * @param emailCertified the emailCertified to set
	 */
	public void setEmailCertified(Integer emailCertified) {
		this.emailCertified = emailCertified;
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
	 * @return the userIcon
	 */
	public String getUserIcon() {
		return userIcon;
	}
	/**
	 * @param userIcon the userIcon to set
	 */
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
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
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
	 * @return the guanzhu
	 */
	public Integer getGuanzhu() {
		return guanzhu;
	}
	/**
	 * @param guanzhu the guanzhu to set
	 */
	public void setGuanzhu(Integer guanzhu) {
		this.guanzhu = guanzhu;
	}
	/**
	 * @return the beiguanzhu
	 */
	public Integer getBeiguanzhu() {
		return beiguanzhu;
	}
	/**
	 * @param beiguanzhu the beiguanzhu to set
	 */
	public void setBeiguanzhu(Integer beiguanzhu) {
		this.beiguanzhu = beiguanzhu;
	}
	/**
	 * @return the haoyou
	 */
	public Integer getHaoyou() {
		return haoyou;
	}
	/**
	 * @param haoyou the haoyou to set
	 */
	public void setHaoyou(Integer haoyou) {
		this.haoyou = haoyou;
	}
	/**
	 * @return the dis
	 */
	public Integer getDis() {
		return dis;
	}
	/**
	 * @param dis the dis to set
	 */
	public void setDis(Integer dis) {
		this.dis = dis;
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
