package com.mf.service;

import com.mf.entity.Register;

public interface IRegisterService {

	/**
	 * 检测注册信息
	 * @param id
	 * @return
	 */
	String registerTest();
	
	/**
	 * 注册
	 * @param register
	 * @return
	 */
	boolean register(Register register);
	
	/**
	 * 试用
	 * @param register
	 * @return
	 */
	String onTrial(Register register);
	
}
