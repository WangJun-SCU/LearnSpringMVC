package com.learnspringmvc.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.learnspringmvc.vo.AccountVO;

//控制器注解
@Controller
@RequestMapping("/hello")
public class HelloController {
	
	Gson gson = new Gson();

	// 返回页面demo.jsp
	@RequestMapping("/showdemo")
	public String getString() {
		
		// 必须在配置的view路径下存在demo.jsp,否则返回404
		return "demo";
	}
	
	// 返回可以数据交互的页面
	@RequestMapping("/showdemo2")
	public ModelAndView getPage2(ModelMap modelMap) {
		modelMap.addAttribute("name", "Niklaus");
		return new ModelAndView("demo2", modelMap);
	}
	
	// 返回字符串
	@RequestMapping("/showString")
	public void getStr(HttpServletRequest request, HttpServletResponse response) {
		render(response, "text/plain;charset=UTF-8", "返回的字符串");
	}
	
	// 返回json对象
	@RequestMapping("/showJsonObject")
	public void getJsonObject(HttpServletRequest request, HttpServletResponse response) {
		AccountVO a = new AccountVO("zhangsan", 20, "chengdu");
		
		render(response, "text/plain;charset=UTF-8", gson.toJson(a));
	}
	
	// 返回json数组
	@RequestMapping("/showJsonArray")
	public void getJsonArray(HttpServletRequest request, HttpServletResponse response) {
		List<AccountVO> list = new ArrayList<AccountVO>();
		
		list.add(new AccountVO("zhangsan", 20, "chengdu"));
		list.add(new AccountVO("lisi", 25, "beijing"));
		list.add(new AccountVO("wangwu", 18, "nanjing"));
		
		render(response, "text/plain;charset=UTF-8", gson.toJson(list));
	}
	
	public static void render(HttpServletResponse response, String contentType, String text) {
		response.setContentType(contentType);
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
		}
	}

}