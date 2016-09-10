package com.starface.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easemob.common.Constants;
import com.starface.dao.MailDao;
import com.starface.domain.EmailVerifyCode;
import com.starface.domain.Users;
import com.starface.domain.query.UsersQuery;
import com.starface.frame.core.utils.ClientUtils;
import com.starface.frame.core.utils.MailSender;
import com.starface.frame.core.utils.MailSenderInfo;
import com.starface.frame.core.utils.StringEx;
import com.starface.service.MailService;
import com.starface.service.UserService;

@Service
public class EmailServiceImpl implements MailService{

	@Autowired
	private MailDao mailDao;
	@Autowired
	private UserService userService;
	
	@Override
	public void sendUserVerificationLink(String email,int userId) {
		// TODO Auto-generated method stub
		//发送邮件到邮箱
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(email);
		String varCode = StringEx.getRandomString(20);
		mailInfo.setSubject("星脸联盟-邮箱验证");
		mailInfo.setContent("<html><head></head><body>亲爱的用户,点击下面的链接验证邮箱:<br/>"
				+ "<a style='color:blue;' href='"+Constants.HOST_PATH+"/user/verificationEmail?code="
				+varCode+"&user_id="+userId+"'>"+Constants.HOST_PATH+"/user/verificationEmail?code="
				+varCode+"&user_id="+userId+"</a>,<br />欢迎使用,谢谢.</body>");
		//这个类主要来发送邮件
		MailSender sms = new MailSender();
		sms.sendHtmlMail(mailInfo);
		EmailVerifyCode emailVerifyCode = new EmailVerifyCode();
		emailVerifyCode.setEmail(email);
		emailVerifyCode.setCode(varCode);
		emailVerifyCode.setUserId(userId);
		mailDao.saveEmailVerifyCode(emailVerifyCode);
	}

	@Override
	public void sendUserRetrievePasswordCode(String email) {
		//发送邮件到邮箱
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(email);
		String varCode = StringEx.getRandomString(6);
		mailInfo.setSubject("星脸联盟-找回密码");
		mailInfo.setContent("<html><head></head><body>亲爱的用户,本次找回密码验证码为:"+varCode+",有效期24小时。<br/><br />欢迎使用,谢谢.</body>");
		//这个类主要来发送邮件
		MailSender sms = new MailSender();
		sms.sendHtmlMail(mailInfo);
		EmailVerifyCode emailVerifyCode = new EmailVerifyCode();
		emailVerifyCode.setEmail(email);
		emailVerifyCode.setCode(varCode);
		mailDao.saveEmailVerifyCode(emailVerifyCode);
	}

	@Override
	public EmailVerifyCode verificationEmail(UsersQuery userQuery) {
		EmailVerifyCode evc = new EmailVerifyCode();
		evc.setUserId(userQuery.getUser_id());
		evc.setCode(userQuery.getCode());
		return mailDao.getEmailVerifyCode(evc);
	}

	@Override
	public String verificationEmailCode(UsersQuery userQuery) {
		EmailVerifyCode evc = new EmailVerifyCode();
		evc.setEmail(userQuery.getUserName());
		evc.setCode(userQuery.getCode());
		evc.setValidityTime(86400);//有效期一天
		EmailVerifyCode emailCode = mailDao.getVerificationEmailCode(evc);
		if(null == emailCode){
			return ClientUtils.failure("验证码错误");
		}
		return ClientUtils.success("校验成功");
	}
	

}
