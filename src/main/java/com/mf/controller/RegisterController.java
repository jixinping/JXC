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
	public Map<String, String> registerTest(HttpSession session)throws Exception{
		String value = registerService.registerTest();
		Map<String, String> map = new HashMap<>();
		map.put("status", value);
		return map;
	}
	
	
	
}
