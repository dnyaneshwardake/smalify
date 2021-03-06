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
	private String baseUrl;

	@Autowired
	private UrlRepository repository;

	public String getShortUrl(Url url) {
		String longUrl = url.getLongUrl().trim();
		String shortUrl = createShortUrl(longUrl);
		if (null == repository.findByShortUrl(shortUrl)) {
			UrlEntity urlEntiry = UrlEntity.builder().shortUrl(shortUrl).longUrl(longUrl).urlCreatedOn(new Date())
					.build();
			repository.save(urlEntiry);
		}
		return shortUrl;
	}

	private String createShortUrl(String longUrl) {
		String md5 = MD5.getMd5(longUrl);
		return baseUrl + md5.substring(0, 7);
	}

	public String getLongUrl(String md5Value) {
		String url = baseUrl + md5Value;
		UrlEntity urlEntity = repository.findByShortUrl(url);
		if (null != urlEntity) {
			updateUrlHitCount(urlEntity);
			return urlEntity.getLongUrl();
		}
		return getErrorPageUrl();
	}

	private void updateUrlHitCount(UrlEntity urlEntity) {
		urlEntity.setUrlLastUsedOn(new Date());
		if (null != urlEntity.getUrlUseCount()) {
			Integer hitCount = urlEntity.getUrlUseCount() + 1;
			urlEntity.setUrlUseCount(hitCount);
		} else {
			urlEntity.setUrlUseCount(1);
		}
		repository.save(urlEntity);
	}

	public String getErrorPageUrl() {
		return baseUrl + "errorpage";
	}
}
