<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/7/2024
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
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
                    <div class="col-12" style="margin-left: 5%;">
                        <h2 class="tm-block-title d-inline-block">SỬA DANH MỤC</h2>
                    </div>
                </div>
                <form class="row tm-edit-product-row col-12" style="margin: 0;"
                      id="productForm" action="<c:url value='/admin-editCategory/${category.ID}'/>" method="post" >
                <div class="col-xl-12 col-lg-12 col-md-12">
                        <div  class="tm-edit-product-form">
                            <div class="form-group mb-3">
                                <label
                                        for="name"
                                >Tên danh mục
                                </label>
                                <input
                                        id="name"
                                        name="name"
                                        type="text"
                                        class="form-control validate"
                                        value="${category.categoryName}"
                                        required
                                />
                            </div>

                            <div class="form-group mb-3">
                                <label
                                        for="category"
                                >Danh mục cha</label>
                                <input
                                        id="category"
                                        name="category"
                                        type="text"
                                        class="form-control validate"
                                        value="${parentName}"
                                        readonly
                                />
<%--                                <select--%>
<%--                                        class="custom-select tm-select-accounts"--%>
<%--                                        id="category"--%>
<%--                                >--%>
<%--                                    <c:forEach var="item" items="${listParent}">--%>
<%--                                        <option value="">${item.categoryName}</option>--%>
<%--                                    </c:forEach>--%>


<%--                                </select>--%>
                            </div>
                        </div>

                    </div>
                    <div class="col-12" style="margin-top: 7%;">
                        <button type="submit" class="btn btn-primary btn-block text-uppercase">CẬP NHẬT DANH MỤC</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
