package com.dnyanesh.smalify.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dnyanesh.smalify.beans.Url;
import com.dnyanesh.smalify.entity.UrlEntity;
import com.dnyanesh.smalify.repository.UrlRepository;
import com.dnyanesh.smalify.utils.MD5;

@Service
public class UrlService {

	@Value("${smalify.baseurl}")
	private String BASE_URL;

	@Autowired
	private UrlRepository repository;

	public String getShortUrl(Url longUrl) {
		String shortUrl = createShortUrl(longUrl);
		if (null == repository.findByShortUrl(shortUrl)) {
			UrlEntity urlEntiry = UrlEntity.builder().shortUrl(shortUrl).longUrl(longUrl.getLongUrl())
					.creationDate(new Date()).build();
			repository.save(urlEntiry);
			return shortUrl;
		}
		return shortUrl;
	}

	private String createShortUrl(Url longUrl) {
		String md5 = MD5.getMd5(longUrl.getLongUrl());
		return BASE_URL + md5.substring(0, 7);
	}

	public String getLongUrl(String md5Value) {
		String url = BASE_URL + md5Value;
		UrlEntity urlEntity = repository.findByShortUrl(url);
		if (null != urlEntity) {
			updateUrlHitCount(urlEntity);
			return urlEntity.getLongUrl();
		}
		return getErrorPageUrl();
	}

	private void updateUrlHitCount(UrlEntity urlEntity) {
		urlEntity.setLastUrlHitDate(new Date());
		if (null != urlEntity.getUrlHitCount()) {
			Integer hitCount = urlEntity.getUrlHitCount() + 1;
			urlEntity.setUrlHitCount(hitCount);
		} else {
			urlEntity.setUrlHitCount(1);
		}
		repository.save(urlEntity);
	}

	public String getErrorPageUrl() {
		return BASE_URL + "errorpage";
	}

}
