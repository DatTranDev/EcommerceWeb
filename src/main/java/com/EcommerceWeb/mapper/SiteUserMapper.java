package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.SiteUser;

public class SiteUserMapper implements RowMapper<SiteUser> {
    @Override
    public SiteUser mapRow(ResultSet rs) {
        SiteUser user = new SiteUser();
        try {
        	user.setID(rs.getInt("ID"));
            user.setDisplayName(rs.getString("DisplayName"));
            user.setEmail(rs.getString("Email"));
            user.setPhoneNumber(rs.getString("PhoneNumber"));
            user.setUserName(rs.getString("UserName"));
            user.setPassword(rs.getString("PassWord"));
            user.setBirthDay(rs.getDate("BirthDay"));
            user.setGender(rs.getString("Gender"));
            user.setRole(rs.getString("Role"));
            user.setDeleted(rs.getBoolean("IsDeleted"));
            return user;
        }catch (SQLException e){
        	return null;
        }
        
    }
}
