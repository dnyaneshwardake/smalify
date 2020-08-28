package com.dnyanesh.smalify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
	public String test(@RequestParam String longUrl) {

		System.out.println(longUrl);
		return longUrl;
	}

	@GetMapping("{md5Value}")
	public String getLongUrl(@PathVariable String md5Value) {

		return urlservice.getLongUrl(md5Value);

	}

}
