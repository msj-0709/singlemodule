package com.example.singlemodule.controller;


import com.example.singlemodule.domain.UserInfo;

import com.example.singlemodule.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping
@Slf4j
@Controller

public class HomeController {
	@Autowired
	private UserService userService;

	@GetMapping
	public String home() {
		return "/index";
	}


	@GetMapping("/test")
	public String layout() {
		return "fragments/common";
	}


	@GetMapping({"board/list"})
	public String list() {
		return "board/list";
	}


	@ResponseBody
	@GetMapping("user/allUser")
	public List<UserInfo> allBySort(){
		List<UserInfo> userinfoList = this.userService.allBySort();
		return userinfoList;
	}

}
