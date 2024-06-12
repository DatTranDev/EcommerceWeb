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
            margin-bottom: 2rem;
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
            padding: 2rem;
            margin-bottom: 2rem;
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

        .nav-tabs .nav-link {
            border: none;
            color: #555;
        }

        .nav-tabs .nav-link.active {
            color: #d9534f;
            border-bottom: 3px solid #d9534f;
            background-color: white;
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
                            <a class="nav-link active" id="tab-tat-ca" href="#">Tất cả</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" id="tab-dang-chuan-bi" href="#">Đang chuẩn bị</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" id="tab-dang-van-chuyen" href="#">Đang vận chuyển</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" id="tab-giao-hang-thanh-cong" href="#">Giao hàng thành công</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" id="tab-giao-hang-that-bai" href="#">Giao hàng thất bại</a>
                        </li>
                        <li class="nav-item col text-center">
                            <a class="nav-link" id="tab-da-huy" href="#">Đã hủy</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body tab-content">
                    <!-- List đơn hàng: Tất cả -->
                    <div class="product-list active tab-pane" role="tabpanel" id="tat-ca">
                        <c:forEach var="order" items="${shopOrderModelList}">
                            <div class="order-separator">
                                <div class="order mb-3">
                                    <div class="row">
                                        <div  class="col-md-12" id="xxx" >
                                            <p class="text-danger" style="width:100%;text-align: right" >${order.getStatusName()}</p>
                                        </div>
                                    </div>
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
                                            <button type="button" class="btn btn-primary order-status btn-mua-lai" onclick="redirectToCart(${order.ID},'${order.getDescribeOrder()}')">${order.getDescribeOrder()}</button>
                                            <button type="button" class="btn btn-secondary" onclick="redirectToOrderDetail(${order.ID})" >Chi tiết đơn hàng</button>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- List đơn hàng: Đang chuẩn bị -->
                    <div class="product-list tab-pane" role="tabpanel" id="dang-chuan-bi">
                        <c:forEach var="order" items="${prepareShopOrderList}">
                            <div class="order-separator">
                                <div class="order mb-3">
                                    <c:forEach var="item" items="${order.getListOrderLine()}">
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
                                            <button type="button" class="btn btn-primary">Hủy đơn hàng</button>
                                            <button type="button" class="btn btn-secondary" onclick="redirectToOrderDetail(${order.ID})" >Chi tiết đơn hàng</button>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- List đơn hàng: Đang vận chuyển -->
                    <div class="product-list tab-pane" role="tabpanel" id="dang-van-chuyen">
                        <c:forEach var="order" items="${deliveryShopOrderList}">
                            <div class="order-separator">
                                <div class="order mb-3">
                                    <c:forEach var="item" items="${order.getListOrderLine()}">
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
                                            <button type="button" class="btn btn-primary">Đang vận chuyển</button>
                                            <button type="button" class="btn btn-secondary" onclick="redirectToOrderDetail(${order.ID})" >Chi tiết đơn hàng</button>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- List đơn hàng: Giao hàng thành công -->
                    <div class="product-list tab-pane" role="tabpanel" id="giao-hang-thanh-cong">
                        <c:forEach var="order" items="${successShopOrderList}">
                            <div class="order-separator">
                                <div class="order mb-3">
                                    <c:forEach var="item" items="${order.getListOrderLine()}">
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
                                            <button type="button" class="btn btn-primary">Mua lại</button>
                                            <button type="button" class="btn btn-secondary" onclick="redirectToOrderDetail(${order.ID})" >Chi tiết đơn hàng</button>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- List đơn hàng: Đã hủy -->
                    <div class="product-list tab-pane" role="tabpanel" id="da-huy">
                        <c:forEach var="order" items="${cancelShopOrderList}">
                            <div class="order-separator">
                                <div class="order mb-3">
                                    <c:forEach var="item" items="${order.getListOrderLine()}">
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
                                            <button type="button" class="btn btn-primary">Mua lại</button>
                                            <button type="button" class="btn btn-secondary" onclick="redirectToOrderDetail(${order.ID})" >Xem chi tiết đơn hàng</button>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- List đơn hàng: Giao hàng thất bại -->
                    <div class="product-list tab-pane" role="tabpanel" id="giao-hang-that-bai">
                        <c:forEach var="order" items="${failShopOrderList}">
                            <div class="order-separator">
                                <div class="order mb-3">
                                    <c:forEach var="item" items="${order.getListOrderLine()}">
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
                                            <button type="button" class="btn btn-primary">Mua lại</button>
                                            <button type="button" class="btn btn-secondary" onclick="redirectToOrderDetail(${order.ID})" >Xem chi tiết đơn hàng</button>
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
    document.addEventListener('DOMContentLoaded', function () {
        const $ = document.querySelector.bind(document);
        const $$ = document.querySelectorAll.bind(document);

        const tabs = $$('.nav-link');
        const tabContents = $$('.product-list');

        tabs.forEach(tab => {
            tab.addEventListener('click', function (e) {
                e.preventDefault(); // Loại bỏ sự kiện click mặc định của Bootstrap

                tabs.forEach(t => t.classList.remove('active'));
                tabContents.forEach(content => content.classList.remove('active'));

                this.classList.add('active');
                const target = this.id.replace('tab-', '');
                document.getElementById(target).classList.add('active');
            });
        });

        // // Lắng nghe sự kiện click cho các nút "Mua lại"
        // const muaLaiButtons = $$('.btn-mua-lai');
        // muaLaiButtons.forEach(button => {
        //     button.addEventListener('click', function () {
        //         const orderId = this.getAttribute('data-order-id');
        //         // Thực hiện công việc bạn muốn khi click vào nút "Mua lại"
        //         alert(`Mua lại đơn hàng với ID:`+orderId);
        //         // Ví dụ: gọi hàm mua lại với orderId
        //         // muaLaiOrder(orderId);
        //     });
        // });
    });

    function redirectToOrderDetail(orderId) {
        // Redirect to order detail page with orderId
        let url = "${pageContext.request.contextPath}/detail-shop-order?orderId="+orderId;
        window.location.href = url;
    }
    function redirectToCart(orderId,Text) {
        let string2 = "Mua lại";
        let string3 = "Hủy đơn hàng";
        if(Text.trim() === string2.trim()){
            let url = "${pageContext.request.contextPath}/cart?orderId="+orderId;
            window.location.href = url;
        }else if(Text.trim() === string2.trim()){

        }
    }


</script>
</body>
</html>

