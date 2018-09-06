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
				return "success";
			}
			if(null == register.getTrialTime() || StringUtil.isEmpty(register.getTrialTime().toString())) {
				//未点击试用
				return "unuse";
			}
			if(register.getTrialTime().getTime()<new Date().getTime()) {
				//试用期已到
				return "expired";
			}
			return "trialing";
		} catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean register(String secretKey) {
		try {
			if(StringUtil.isEmpty(secretKey)) {
				return false;
			}
			Register register = registerRepository.findRegister();
			String key = encoderByMd5(register.getCompanyName());
			if(secretKey.equals(key)) {
				register.setSecretKey(key);
				register.setRegisterTime(new Date());
				registerRepository.save(register);
				return true;
			} else {
				System.out.println("sys key="+key);
			}
			return false;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public String onTrial() {
		Register register = registerRepository.findRegister();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null == register.getTrialTime() || "".equals(register.getTrialTime().toString())) {
			register.setTrialTime(getForwardMoth());
			registerRepository.save(register);
			return format.format(register.getTrialTime());
		}
		if(StringUtil.isNotEmpty(register.getTrialTime().toString())) {
			//已试用，不允许再次试用
			return format.format(register.getTrialTime());
		}
		
		return null;
	}

	@Override
	public String getCompanyName() {
		try {
			Register register = registerRepository.findRegister();
			return register.getCompanyName();
		} catch(Exception e) {
			return null;
		}
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
