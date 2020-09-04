package com.dnyanesh.smalify.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dnyanesh.smalify.beans.Url;
import com.dnyanesh.smalify.service.UrlService;

@Controller
@RequestMapping("/")
public class UrlController {

	@Autowired
	private UrlService urlservice;

	@GetMapping
	public String homePage() {
		return "index";
	}

	@PostMapping
	public String getShortUrl(@Valid Url url, BindingResult result, Model model) {
		String shortUrl = urlservice.getShortUrl(new Url("https://github.com/RameshMF/springboot-thymeleaf-crud-tutorial", ""));
		model.addAttribute("url", new Url(url.getLongUrl(), shortUrl));
		return "index";
	}

	@GetMapping("{md5Value}")
	public ModelAndView getLongUrl(@PathVariable String md5Value) {

		if (null != md5Value && !md5Value.equals("favicon.ico")) {
			return new ModelAndView("redirect:" + urlservice.getLongUrl(md5Value));
		}
		return null;

	}

}
