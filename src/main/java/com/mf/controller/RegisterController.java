package com.mf.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mf.service.IRegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Resource
	private IRegisterService registerService;

	@ResponseBody
	@PostMapping("/registerTest")
	public Map<String, String> registerTest()throws Exception{
		String value = registerService.registerTest();
		Map<String, String> map = new HashMap<>();
		map.put("status", value);
		return map;
	}
	
	@ResponseBody
	@PostMapping("/register")
	public Map<String, Object> register(String secretKey) {
		boolean flag = registerService.register(secretKey);
		Map<String, Object> map = new HashMap<>();
		map.put("status", flag);
		return map;
	}
	
	@ResponseBody
	@PostMapping("/onTrial")
	public Map<String, Object> onTrial() {
		String str = registerService.onTrial();
		Map<String, Object> map = new HashMap<>();
		map.put("status", true);
		map.put("time", str);
		return map;
	}
}
