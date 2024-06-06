<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/4/2024
  Time: 2:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container mt-5">
    <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
            <div class="tm-bg-primary-dark tm-b
            lock tm-block-products">
                <div class="tm-product-table-container">
                    <table class="table table-hover tm-table-small tm-product-table">
                        <thead>
                        <tr>
                            <th scope="col">&nbsp;</th>
                            <th scope="col">TÊN SẢN PHẨM</th>
                            <th scope="col">TỒN KHO</th>
                            <th scope="col">DANH MỤC</th>
                            <th scope="col">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${listProduct}" >
                            <tr style="cursor: pointer;">
                                <th  scope="row"><input type="checkbox"></th>
                                <td onclick="openEditTab(${item.id})" class="tm-product-name">${item.name}</td>
                                <td onclick="openEditTab(${item.id})" style="text-align: center; vertical-align: middle;">${item.quantity}</td>
                                <td onclick="openEditTab(${item.id})">${item.category}</td>
                                <td>
                                    <a href="#" class="tm-product-delete-link">
                                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>
                </div>
                <!-- table container -->
                <a  href="${pageContext.request.contextPath}/admin-product/add" class="btn btn-primary btn-block text-uppercase mb-3">THÊM SẢN PHẨM</a>
                <button class="btn btn-primary btn-block text-uppercase">
                    XÓA SẢN PHẨM ĐÃ CHỌN
                </button>
            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-product-categories">
                <h2 class="tm-block-title">Product Categories</h2>
                <div class="tm-product-table-container">
                    <table class="table tm-table-small tm-product-table">
                        <tbody>
                        <c:forEach var="item" items="${listCategory}" >
                            <tr>
                                <td class="tm-product-name">${item.name}${item.parent}</td>
                                <td class="text-center">
                                    <a href="#" class="tm-product-delete-link">
                                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                    </a>
                                </td>
                            </tr>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- table container -->
                <button class="btn btn-primary btn-block text-uppercase mb-3">
                    THÊM DANH MỤC
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    function openEditTab(itemId) {
        // Thay đổi đường dẫn theo URL trang chỉnh sửa sản phẩm của bạn
        const editUrl =`${pageContext.request.contextPath}/admin-editProduct/`+itemId;
        window.location.href = editUrl;
    }
</script>
</body>
</html>
