package com.dnyanesh.smalify.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dnyanesh.smalify.beans.Url;
import com.dnyanesh.smalify.service.UrlService;

@Controller
@RequestMapping("/")
public class UrlController {

	@Autowired
	private UrlService urlservice;

	@GetMapping
	public String home() {
		return "index";
	}

	@GetMapping("shortit")
	public String showShortUrlPage(Url url) {
		return "shorturls";
	}

	@PostMapping
	public String getShortUrl(@Valid Url url, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "index";
		}
		String shortUrl = urlservice.getShortUrl(url);
		model.addAttribute("url", new Url(url.getLongUrl(), shortUrl));
		return "index";
	}

	@GetMapping("{md5Value}")
	public ModelAndView getLongUrl(@PathVariable String md5Value) {
		return new ModelAndView("redirect:" + urlservice.getLongUrl(md5Value));
	}

	@GetMapping("errorpage")
	public String errorPage(Url url) {
		return "errorpage";
	}

	@GetMapping("favicon.ico")
	@ResponseBody
	void returnNoFavicon() {
	}
	
	@GetMapping("privacy-policy")
	public String privacyPolicy() {
		return "privacy-policy";
	}
}
