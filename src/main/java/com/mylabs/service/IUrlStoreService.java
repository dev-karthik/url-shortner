package com.mylabs.service;

import com.mylabs.model.URLStore;

public interface IUrlStoreService {
    String findUrlById(String id);

    void storeUrl(URLStore url);
}
