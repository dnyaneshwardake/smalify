package com.dnyanesh.smalify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dnyanesh.smalify.beans.LongUrl;
import com.dnyanesh.smalify.service.UrlService;

@RestController
@RequestMapping("/")
public class UrlController {

	@Autowired
	private UrlService urlservice;

	@PostMapping
	public String getShortUrl(@RequestBody LongUrl longUrl) {

		return urlservice.getShortUrl(longUrl);
	}

	@GetMapping("get")
	public String getShortUrl1(@RequestParam String longUrl) {
		LongUrl url = new LongUrl(longUrl);
		return urlservice.getShortUrl(url);
	}

	@GetMapping("{md5Value}")
	public ModelAndView getLongUrl(@PathVariable String md5Value) {

		if (null != md5Value && !md5Value.equals("favicon.ico")) {
			return new ModelAndView("redirect:" + urlservice.getLongUrl(md5Value));
		}

		return null;

	}

}
