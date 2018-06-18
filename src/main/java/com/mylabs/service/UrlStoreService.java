package com.mylabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylabs.model.URLStore;
import com.mylabs.repository.URLRepository;


@Service
public class UrlStoreService implements IUrlStoreService{

    @Autowired
    private URLRepository urlRepository;


    @Override
    public String findUrlById(String id) {
    	return 	urlRepository.findByShortUrlUID(id).getLongUrl();
    }

    @Override
    public void storeUrl(URLStore url) {
           	urlRepository.save(url);
    }
}
