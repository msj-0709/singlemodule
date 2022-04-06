package com.example.singlemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RequestMapping
@Controller
@Slf4j
public class HomeController {

	@GetMapping
	public String home() {
		return "index";
	}

	@GetMapping("/list")
	public String login() {
		return "list";
	}

	@GetMapping("/layout")
	public String common() {
		return "common/layout";
	}

	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
