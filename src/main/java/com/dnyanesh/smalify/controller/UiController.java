package com.dnyanesh.smalify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

	@GetMapping("/")
	public String homePage() {
		return "index";
	}

}
