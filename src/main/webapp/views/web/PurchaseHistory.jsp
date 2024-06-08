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
</head>
<body>
<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Giỏ hàng</h1>
</div>
<!-- Single Page Header End -->

<!-- Order Details Start -->
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Tất cả</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Chờ thanh toán</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Vận chuyển</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Chờ giao hàng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Hoàn thành</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Đã hủy</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Trả hàng/Hoàn tiền</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <input type="text" class="form-control w-50" placeholder="Bạn có thể tìm kiếm theo tên Shop, ID đơn hàng hoặc Tên Sản phẩm">
                    </div>
                    <div class="media mb-3">
                        <img src="path_to_image" class="mr-3" alt="Product Image" style="width: 100px;">
                        <div class="media-body">
                            <h5 class="mt-0">Váy Laly dress dáng dài cổ thuyền xixeoshop - V22</h5>
                            <p>Phân loại hàng: Trắng kem, S<br>x1</p>
                            <p>Trả hàng miễn phí 15 ngày</p>
                        </div>
                        <div class="text-right">
                            <p class="text-danger"><del>₫220.000</del> ₫132.000</p>
                        </div>
                    </div>
                    <div class="d-flex justify-content-between align-items-center mt-3">
                        <button type="button" class="btn btn-primary">Mua Lại</button>
                        <button type="button" class="btn btn-secondary">Xem Chi Tiết Hủy Đơn</button>
                        <button type="button" class="btn btn-secondary">Liên Hệ Người Bán</button>
                        <div class="text-right">
                            <p>Thành tiền: ₫182.100</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Order Details End -->
<Script>

</Script>
</body>
</html>
