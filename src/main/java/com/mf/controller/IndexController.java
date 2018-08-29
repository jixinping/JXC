package com.mf.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 首页Controller
 * @author Administrator
 *
 */
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String root(HttpServletResponse response,RedirectAttributes model){
		return "redirect:/login.html";
	}
}
