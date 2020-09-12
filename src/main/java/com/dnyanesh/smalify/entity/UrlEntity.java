package com.dnyanesh.smalify.entity;

import java.util.Date;

import javax.persistence.Column;
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
	@Column(length = 400)
	private String longUrl;
	private Date urlCreatedOn;
	private Integer urlUseCount;
	private Date urlLastUsedOn;
}
