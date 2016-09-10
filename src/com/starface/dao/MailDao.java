package com.starface.dao;

import com.starface.domain.EmailVerifyCode;

public interface MailDao {
	
	/**
	 * 保存验证码
	 * @param emailVerifyCode
	 * @return
	 */
	public int saveEmailVerifyCode(EmailVerifyCode emailVerifyCode);
	
	public EmailVerifyCode getEmailVerifyCode(EmailVerifyCode  emailVerifyCode);
	
	public EmailVerifyCode getVerificationEmailCode(EmailVerifyCode  emailVerifyCode);
	

}
