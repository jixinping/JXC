package com.mf.service;


public interface IRegisterService {

	/**
	 * 检测注册信息
	 * @param id
	 * @return
	 */
	String registerTest();
	
	/**
	 * 注册
	 * @param secretKey
	 * @return
	 */
	boolean register(String secretKey);
	
	/**
	 * 试用
	 * @param register
	 * @return
	 */
	String onTrial();
	
}
