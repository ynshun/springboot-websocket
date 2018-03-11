package com.ynshun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ynshun.config.cache.MemoryCache;

@Controller
public class IndexController {

	@RequestMapping(value = { "", "/", "index" })
	public ModelAndView pubShareMsg() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		modelAndView.addObject("friends", MemoryCache.getAllUser());
		
		return modelAndView;
	}

}