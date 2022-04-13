package com.example.singlemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RequestMapping
@Controller
@Slf4j
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login/login";
	}



}
