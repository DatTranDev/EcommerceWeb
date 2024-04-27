package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.ISiteUserDAO;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.ISiteUserService;

import javax.inject.Inject;

public class SiteUserService implements ISiteUserService {
    @Inject
    private ISiteUserDAO siteUserDAO;

    public SiteUser findByUserNameAndPassword(String userName, String passWord) {
        return siteUserDAO.findByUserNameAndPassword(userName, passWord);
    }
    public SiteUser findByEmail(String email) {
        return siteUserDAO.findByEmail(email);
    }
    public int register(SiteUser siteUser) {
        return siteUserDAO.register(siteUser);
    }
    public void update(SiteUser siteUser) {
        siteUserDAO.update(siteUser);
    }
    public SiteUser findOne(Long id) {
        return siteUserDAO.findOne(id);
    }
    public int count() {
        return siteUserDAO.count();
    }
}
