<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    <title>Đăng ký</title>
<div class="container">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Tạo tài khoản!</h1>
                        </div>
                        <form class="user" method="post" action="<c:url value='/dang-nhap'/>" accept-charset="UTF-8">
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="exampleFirstName" required="required"
                                       name="DisplayName"
                                       placeholder="Your name">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control form-control-user" id="exampleInputEmail" required="required"
                                       aria-describedby="email" name="Email"
                                       placeholder="Email Address">
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" class="form-control form-control-user" required="required"
                                           name="Password"
                                           id="exampleInputPassword" placeholder="Password">
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user" required="required"
                                           name="RepeatPassword"
                                           id="exampleRepeatPassword" placeholder="Repeat Password">
                                </div>
                            </div>
                            <input type="hidden" value="register" name="action">
                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                Đăng ký
                            </button>
                            <hr>
                            <a href="#" class="btn btn-google btn-user btn-block">
                                <i class="fab fa-google fa-fw"></i> Đăng ký với Google
                            </a>
                            <a href="#" class="btn btn-facebook btn-user btn-block">
                                <i class="fab fa-facebook-f fa-fw"></i> Đăng ký với Facebook
                            </a>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="${pageContext.request.contextPath}/dang-nhap?action=forgotpassword">Quên mật khẩu?</a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="${pageContext.request.contextPath}/dang-nhap?action=login">Đã có tài khoản? Đăng nhập!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
