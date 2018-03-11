package com.ynshun.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ynshun.config.cache.MemoryCache;
import com.ynshun.domain.UserInfo;

@Controller
public class loginController {

	@ResponseBody
	@RequestMapping(value = "getUsers")
	public List<UserInfo> getAllUser() {
		return MemoryCache.getAllUser();
	}

	@RequestMapping(value = { "login", "sign" })
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@PostMapping(value = { "login", "sign" })
	public ModelAndView login(String username, String password) {
		if (!MemoryCache.userExists(username)) {
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("message", "不存在的用户信息！");
			return modelAndView;
		}

		if (!MemoryCache.login(username, password)) {
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("message", "用户名或密码错误！");
			return modelAndView;
		}

		MemoryCache.currentUser(MemoryCache.getUserInfo(username));

		return new ModelAndView("redirect:/");

	}

}