package com.starface.domain.query;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.starface.domain.Users;

public class UsersQuery extends Users{

	/**
	 * 确认密码
	 */
	private String rePwd;
	
	private String oldPwd;
	
	private String code;
	
	private String owner_username;
	
	private String friend_username;
	
	private Integer user_id;
	
	private Integer registerType;
	
	public String searchName ;
	
	private Integer ageBracket;
	
	private Integer passivity;
	
	private Integer currentUser;
	
	private String createTimeView;
	
	
	public MultipartFile icon;
	/**
	 * 有效期
	 */
	private Integer validityTime;
	
	private Integer offset;
	
	private Integer hometownProvinceId;
	private Integer hometownCityId;
	private Integer hometownAreaId;
	
	private String month;
	
	private String day;
	
	private Integer type;
	
	private String mobile;
	
	private List<Integer> ids;
	
	private Integer start;
	
	private Integer limit = 20 ;
		
	public String getRePwd() {
		return rePwd;
	}

	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOwner_username() {
		return owner_username;
	}

	public void setOwner_username(String owner_username) {
		this.owner_username = owner_username;
	}

	public String getFriend_username() {
		return friend_username;
	}

	public void setFriend_username(String friend_username) {
		this.friend_username = friend_username;
	}

	/**
	 * @return the user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the registerType
	 */
	public Integer getRegisterType() {
		return registerType;
	}

	/**
	 * @param registerType the registerType to set
	 */
	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
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

	/**
	 * @return the searchName
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * @param searchName the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * @return the icon
	 */
	public MultipartFile getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(MultipartFile icon) {
		this.icon = icon;
	}

	/**
	 * @return the ageBracket
	 */
	public Integer getAgeBracket() {
		return ageBracket;
	}

	/**
	 * @param ageBracket the ageBracket to set
	 */
	public void setAgeBracket(Integer ageBracket) {
		this.ageBracket = ageBracket;
	}

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return null== offset ? 0 : offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
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
	 * @return the passivity
	 */
	public Integer getPassivity() {
		return passivity;
	}

	/**
	 * @param passivity the passivity to set
	 */
	public void setPassivity(Integer passivity) {
		this.passivity = passivity;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the currentUser
	 */
	public Integer getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(Integer currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the oldPwd
	 */
	public String getOldPwd() {
		return oldPwd;
	}

	/**
	 * @param oldPwd the oldPwd to set
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
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
	 * @return the ids
	 */
	public List<Integer> getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
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
	 * @return the createTimeView
	 */
	public String getCreateTimeView() {
		return createTimeView;
	}

	/**
	 * @param createTimeView the createTimeView to set
	 */
	public void setCreateTimeView(String createTimeView) {
		this.createTimeView = createTimeView;
	}

	
	
}