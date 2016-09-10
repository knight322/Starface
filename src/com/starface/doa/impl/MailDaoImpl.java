package com.starface.doa.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starface.dao.MailDao;
import com.starface.domain.EmailVerifyCode;
import com.starface.frame.core.utils.DBHelper;

@Repository
public class MailDaoImpl implements MailDao {
	@Autowired
	private DBHelper db;
	@Autowired(required = true)
	private SqlSession sqlSessionTemplate;

	@Override
	public int saveEmailVerifyCode(EmailVerifyCode emailVerifyCode) {
		return db.update("com.starface.domain.EmailVerifyCodeMapper.insertSelective", emailVerifyCode);
	}

	@Override
	public EmailVerifyCode getEmailVerifyCode(EmailVerifyCode emailVerifyCode) {
		return (EmailVerifyCode)sqlSessionTemplate.selectOne(
				"com.starface.domain.EmailVerifyCodeMapper.selectByUseridCode", emailVerifyCode);
	}
	
	@Override
	public EmailVerifyCode getVerificationEmailCode(
			EmailVerifyCode emailVerifyCode) {
		
		return (EmailVerifyCode)sqlSessionTemplate.selectOne(
				"com.starface.domain.EmailVerifyCodeMapper.selectByEmailCode", emailVerifyCode);
	}
	
	
	
	

}
