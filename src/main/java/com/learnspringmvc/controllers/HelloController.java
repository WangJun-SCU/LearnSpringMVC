package com.learnspringmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//控制器注解
@Controller
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping("/showdemo")
	public String getString() {
		
		// 必须在配置的view路径下存在demo.jsp,否则返回404
		return "demo";
	}

}