package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.ISiteUserDAO;
import com.EcommerceWeb.mapper.SiteUserMapper;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.utils.Helper;

public class SiteUserDAO extends AbstractDAO<SiteUser> implements ISiteUserDAO{
	@Override
    public SiteUser findByUserNameAndPassword(String userName, String passWord) {
        String sql = "SELECT * FROM SiteUser WHERE email = ? AND password = ?";
        passWord = Helper.toMd5(passWord);
        try {
            return query(sql, new SiteUserMapper(), userName, passWord).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SiteUser findByEmail(String email) {
        String sql = "SELECT * FROM SiteUser WHERE email = ?";
        try {
            return query(sql, new SiteUserMapper(), email).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int register(SiteUser siteUser) {
        String sql = "INSERT INTO SiteUser(displayname, password, email, role) VALUES(?, ?, ?, ?)";
        return insert(sql, siteUser.getDisplayName(), Helper.toMd5(siteUser.getPassword()), siteUser.getEmail(), siteUser.getRole());
    }

    @Override
    public void update(SiteUser siteUser) {
        String sql = "UPDATE SiteUser SET password = ?, email = ?, PhoneNumber = ?, role = ? WHERE id = ?";
        update(sql, siteUser.getPassword(), siteUser.getEmail(), siteUser.getPhoneNumber(), siteUser.getRole(), siteUser.getID());
    }

    @Override
    public SiteUser findOne(Long id) {
        String sql = "SELECT * FROM SiteUser WHERE id = ?";
        try {
            return query(sql, new SiteUserMapper(), id).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM SiteUser WHERE role = 'Khách hàng'";
        return count(sql);
    }
}
