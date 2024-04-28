package com.EcommerceWeb.filter;

import com.EcommerceWeb.constant.SystemConstant;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String uri = req.getServletPath();
        if(uri.startsWith("/admin")) {
            SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");
            if(siteUser == null) {
                res.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=Vui lòng đăng nhập tài khoản admin&alert=danger");
            } else {
                if(siteUser.getRole().equals(SystemConstant.ADMIN))
                    chain.doFilter(request, response);
                else if(siteUser.getRole().equals(SystemConstant.USER))
                    res.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=Vui lòng đăng nhập tài khoản admin&alert=danger");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
