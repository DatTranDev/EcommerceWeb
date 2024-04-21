package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.SiteUser;

public interface ISiteUserDAO extends GenericDAO<SiteUser>{
    SiteUser login(String userName, String passWord);
    SiteUser findByEmail(String email);
    int register(SiteUser siteUser);
    void update(SiteUser siteUser);
    SiteUser findOne(Long id);
    int count();
}
