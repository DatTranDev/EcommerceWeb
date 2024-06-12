<%--
  Created by IntelliJ IDEA.
  User: DAT
  Date: 12/06/2024
  Time: 1:12 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đặt lại mật khẩu</title>
</head>
<body>
<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-2">Quên mật khẩu?</h1>
                                    <p class="mb-4">Nhập mã xấc nhận tại đây</p>
                                </div>
                                <form class="user" method="post" action="<c:url value='/dang-nhap'/>">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" required="required"
                                               id="exampleInputEmail" aria-describedby="emailHelp" name="codeverify"
                                               placeholder="Mã xác nhận" style="margin-bottom: 10px">
                                        <input type="password" class="form-control form-control-user" required="required"
                                               name="Password" style="margin-bottom: 10px"
                                               id="exampleInputPassword" placeholder="Password">
                                        <input type="password" class="form-control form-control-user" required="required"
                                               name="RepeatPassword" style="margin-bottom: 10px"
                                               id="exampleRepeatPassword" placeholder="Repeat Password">
                                        <input type="hidden" value="resetpassword" name="action">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        Xác nhận
                                    </button>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/dang-nhap?action=register">Tạo tài khoản!</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/dang-nhap?action=login">  Đã có tài khoản? Đăng nhập!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
</body>
</html>
