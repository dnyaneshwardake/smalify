package com.dnyanesh.smalify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnyanesh.smalify.entity.UrlEntity;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

	UrlEntity findByShortUrl(String shorturl);

}
