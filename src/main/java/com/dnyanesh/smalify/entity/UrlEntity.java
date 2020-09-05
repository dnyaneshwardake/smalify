package com.dnyanesh.smalify.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "smalify_url")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlEntity {

	@GeneratedValue
	@Id
	private Long id;
	private String shortUrl;
	private String longUrl;
	private Integer hitCount;

}
