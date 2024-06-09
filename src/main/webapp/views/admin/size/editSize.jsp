<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/9/2024
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container tm-mt-big tm-mb-big" style="width: 700px;padding: 0px;">
    <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
            <div class="tm-bg-primary-dark tm-block tm-block-h-auto" style="padding: 5% 3%;">
                <div class="row">
                    <c:choose>
                        <c:when test="${type==1}">
                            <div class="col-12" style="margin-left: 5%;">
                                <h2 class="tm-block-title d-inline-block text-uppercase">SỬA SIZE</h2>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-12" style="margin-left: 5%;">
                                <h2 class="tm-block-title d-inline-block text-uppercase">SỬA MÀU</h2>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:choose>
                    <c:when test="${type == 1}">
                        <c:set var="url" value="size" />
                        <c:set var="text" value="number" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="url" value="color" />
                        <c:set var="text" value="text" />
                    </c:otherwise>
                </c:choose>
                <form class="row tm-edit-product-row col-12" style="margin: 0;"
                      id="productForm" action="<c:url value='/admin-editSize/${variationOption.ID}'/>" method="post">
                    <div class="col-xl-12 col-lg-12 col-md-12">
                        <div class="tm-edit-product-form">
                            <div class="form-group mb-3">
                                <label
                                        for="name"
                                >Tên
                                </label>
                                <input
                                        id="name"
                                        name="name"
                                        type="text"
                                        class="form-control validate"
                                        VALUE="${variationOption.value}"
                                        required
                                />
                            </div>
                        </div>
                        <div class="col-12" style="margin-top: 7%;">
                            <button type="submit" class="btn btn-primary btn-block text-uppercase">SỬA</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
