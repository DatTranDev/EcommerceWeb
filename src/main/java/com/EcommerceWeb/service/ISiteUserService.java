package com.EcommerceWeb.service;

import com.EcommerceWeb.model.SiteUser;

public interface ISiteUserService {
    SiteUser findByUserNameAndPassword(String userName, String passWord);
    SiteUser findByEmail(String email);
    int register(SiteUser siteUser);
    void update(SiteUser siteUser);
    SiteUser findOne(Long id);
    int count();
}
