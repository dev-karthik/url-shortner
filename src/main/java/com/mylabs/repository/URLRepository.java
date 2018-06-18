package com.mylabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mylabs.model.URLStore;


public interface URLRepository extends JpaRepository<URLStore, String>{
	URLStore findByShortUrlUID(String uid);
}
