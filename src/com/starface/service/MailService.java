package com.starface.service;

import com.starface.domain.EmailVerifyCode;
import com.starface.domain.query.UsersQuery;

public interface MailService {
	
	/**
	 * 发送用户邮箱验证链接
	 */
	public void sendUserVerificationLink(String email,int userId);
	
	/**
	 * 发送用户找回密码--验证码
	 */
	public void sendUserRetrievePasswordCode(String email);
	
	/**
	 * 验证邮箱 
	 * @param userQuery
	 * @return
	 */
	public EmailVerifyCode verificationEmail(UsersQuery userQuery);
	
	/**
	 * 验证发送到邮箱的校验码
	 * @param userQuery
	 * @return
	 */
	public String verificationEmailCode(UsersQuery userQuery);
	
	

}
