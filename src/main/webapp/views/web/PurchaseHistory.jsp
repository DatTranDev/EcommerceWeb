<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://example.com/utils" prefix="utils" %>
<html>
<head>
    <title>Lịch sử mua hàng</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .nav-link.active {
            background-color: white !important;
            border-bottom: 3px solid red !important;
        }

        .product-list {
            display: none;
        }

        .product-list.active {
            display: block;
        }

        .order {
            margin-bottom: 2rem; /* Tạo khoảng cách giữa các đơn hàng */
            padding: 1rem;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .order img {
            width: 80px;
            height: 80px;
        }

        .order-separator {
            background-color: #f7f7f7;
            padding: 2rem; /* Adjusted padding for better visibility */
            margin-bottom: 2rem; /* Margin bottom to separate from next order */
            border-radius: 5px;
        }

        .order-separator hr {
            border-top: 1px solid #ddd;
            margin: 0;
        }

        .order-total {
            text-align: right;
            font-size: 1.2rem;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .order-actions {
            text-align: right;
        }

        .order-actions button {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Lịch sử mua hàng</h1>
</div>
<!-- Single Page Header End -->

<!-- Order Details Start -->
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs">
                        <li class="nav-item col text-center">
                            <a class="nav-link active" href="#" data-target="tat-ca">Tất cả</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" href="#" data-target="dang-chuan-bi">Đang chuẩn bị</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" href="#" data-target="dang-van-chuyen">Đang vận chuyển</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" href="#" data-target="giao-hang-thanh-cong">Giao hàng thành công</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" href="#" data-target="da-huy">Đã hủy</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <input type="text" class="form-control w-50"
                               placeholder="Bạn có thể tìm kiếm theo tên Shop, ID đơn hàng hoặc Tên Sản phẩm">
                    </div>

                    <!-- List đơn hàng: Tất cả -->
                    <div class="product-list active" id="tat-ca">
                        <c:forEach var="order" items="${shopOrderModelList}">
                            <div class="order-separator">
                                <div class="order mb-3">
                                    <c:forEach var="item" items="${order.listOrderLine}">
                                        <div class="row align-items-center mb-3" data-id="${item.ID}">
                                            <div class="col-md-2">
                                                <img src="${item.getProductItem().getProduct().getProductImage()}"
                                                     class="img-fluid rounded-circle" alt="Product Image">
                                            </div>
                                            <div class="col-md-6">
                                                <h5 class="mt-0">${item.getProductItem().getProduct().getDisplayName()}</h5>
                                                <p>Số lượng: ${item.getQuantity()}</p>
                                                <p>${utils:formatVariation(item.getProductItem().getListProductConfig())}</p>
                                            </div>
                                            <div class="col-md-4 text-right">
                                                <p class="text-danger">${utils:formatCurrency(item.getProductItem().getPrice())}</p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <div class="row">
                                        <div class="col-md-12 text-right">
                                            <p class="order-total">Thành tiền: ${utils:formatCurrency(order.getOrderTotal())}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 text-right order-actions">
                                            <button type="button" class="btn btn-primary">Mua Lại</button>
                                            <button type="button" class="btn btn-secondary">Xem Chi Tiết Hủy Đơn</button>
                                            <button type="button" class="btn btn-secondary">Liên Hệ Người Bán</button>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Order Details End -->
<script>
    $(document).ready(function () {
        $('.nav-link').click(function (e) {
            e.preventDefault();
            $('.nav-link').removeClass('active');
            $(this).addClass('active');

            var target = $(this).data('target');
            $('.product-list').removeClass('active');
            $('#' + target).addClass('active');
        });
    });
</script>
</body>
</html>
