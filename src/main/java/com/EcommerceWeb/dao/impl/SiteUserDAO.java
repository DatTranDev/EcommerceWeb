package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.ISiteUserDAO;
import com.EcommerceWeb.mapper.SiteUserMapper;
import com.EcommerceWeb.model.SiteUser;

public class SiteUserDAO extends AbstractDAO<SiteUser> implements ISiteUserDAO{
	@Override
    public SiteUser login(String userName, String passWord) {
        String sql = "SELECT * FROM site_user WHERE username = ? AND password = ?";
        try {
            return query(sql, new SiteUserMapper(), userName, passWord).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SiteUser findByEmail(String email) {
        String sql = "SELECT * FROM site_user WHERE email = ?";
        try {
            return query(sql, new SiteUserMapper(), email).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int register(SiteUser siteUser) {
        String sql = "INSERT INTO site_user(username, password, email, phone, role) VALUES(?, ?, ?, ?, ?)";
        return insert(sql, siteUser.getUserName(), siteUser.getPassword(), siteUser.getEmail(), siteUser.getPhoneNumber(), siteUser.getRole());
    }

    @Override
    public void update(SiteUser siteUser) {
        String sql = "UPDATE site_user SET username = ?, password = ?, email = ?, phone = ?, role = ? WHERE id = ?";
        update(sql, siteUser.getUserName(), siteUser.getPassword(), siteUser.getEmail(), siteUser.getPhoneNumber(), siteUser.getRole(), siteUser.getID());
    }

    @Override
    public SiteUser findOne(Long id) {
        String sql = "SELECT * FROM site_user WHERE id = ?";
        try {
            return query(sql, new SiteUserMapper(), id).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM site_user";
        return count(sql);
    }
}
