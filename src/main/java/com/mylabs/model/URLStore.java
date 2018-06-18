package com.mylabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "urlstore")
public class URLStore {

	@Id
	@Column(name = "UID",unique=true, nullable = false)
	private String shortUrlUID;

	private String longUrl;
	
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	public String getShortUrl() {
		return shortUrlUID;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrlUID = shortUrl;
	}

 
}
