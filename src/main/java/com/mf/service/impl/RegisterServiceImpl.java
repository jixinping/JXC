package com.mf.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import com.mf.entity.Register;
import com.mf.repository.RegisterRepository;
import com.mf.service.IRegisterService;
import com.mf.util.StringUtil;

import sun.misc.BASE64Encoder;

@Service("registerServiceImpl")
public class RegisterServiceImpl implements IRegisterService {

	@Resource
	private RegisterRepository registerRepository;
	
	@Override
	public String registerTest() {
		try {
			Register register = registerRepository.findRegister();
			
			if(StringUtil.isNotEmpty(register.getSecretKey()) 
					&& register.getSecretKey().equals(encoderByMd5(register.getCompanyName()))
					&& StringUtil.isNotEmpty(register.getRegisterTime().toString())) {
				//已正常注册
				return null;
			}
			
			if(StringUtil.isNotEmpty(register.getTrialTime().toString())) {
				//试用期已到
				return "";
			}
			register.setTrialTime(getForwardMoth());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(register.getTrialTime());
		} catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean register(Register register) {
		try {
			if(StringUtil.isEmpty(register.getSecretKey())) {
				return false;
			}
			String key = encoderByMd5(register.getCompanyName());
			if(key.equals(register.getSecretKey())) {
				register.setRegisterTime(new Date());
				registerRepository.save(register);
				return true;
			}
			return false;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public String onTrial(Register register) {
		if(StringUtil.isNotEmpty(register.getTrialTime().toString())) {
			//已试用，不允许再次试用
			return "";
		}
		register.setTrialTime(getForwardMoth());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(register.getTrialTime());
	}

	
	private String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		return base64en.encode(md5.digest(str.getBytes("utf-8")));		
	}

	private Date getForwardMoth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}

}
