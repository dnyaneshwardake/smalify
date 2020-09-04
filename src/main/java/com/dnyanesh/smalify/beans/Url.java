package com.dnyanesh.smalify.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Url {

	private String longUrl;
	private String shortUrl;
}
