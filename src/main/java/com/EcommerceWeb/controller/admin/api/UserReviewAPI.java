package com.EcommerceWeb.controller.admin.api;

import com.EcommerceWeb.model.UserReview;
import com.EcommerceWeb.service.IUserReviewService;
import com.EcommerceWeb.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin/userreview"})
public class UserReviewAPI extends HttpServlet {
    @Inject
    private IUserReviewService iUserReviewService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserReview userReview = HttpUtil.of(request.getReader()).toModel(UserReview.class);
        userReview.setID(iUserReviewService.add(userReview));
        mapper.writeValue(response.getOutputStream(), userReview);
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserReview userReview = HttpUtil.of(request.getReader()).toModel(UserReview.class);
        iUserReviewService.delete(userReview.getID());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserReview userReview = HttpUtil.of(request.getReader()).toModel(UserReview.class);
        iUserReviewService.update(userReview);
        mapper.writeValue(response.getOutputStream(), userReview);
    }
}
