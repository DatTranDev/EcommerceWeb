<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://example.com/utils" prefix="utils" %>
<html>
<head>
    <title>Thông tin đơn hàng</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Checkout Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <h1 class="mb-4 text-center">Thông tin hóa đơn</h1>
        <div class="row g-5">
            <div class="col-md-12 col-lg-5 col-xl-4">
                <!-- Phần thông tin hóa đơn -->
                <div class="bg-light rounded shadow-sm p-4 mb-4">
                    <h2 class="h5 mb-4">Thanh toán</h2>

                    <!-- Hiển thị tên người dùng và số điện thoại -->
                    <div class="mb-3 row">
                        <label class="col-sm-5 col-form-label font-weight-bold">Họ tên:</label>
                        <div class="col-sm-7">
                            <p class="form-control-plaintext mb-0">${shopOrderModel.siteUser.displayName}</p>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-5 col-form-label font-weight-bold">Số điện thoại:</label>
                        <div class="col-sm-7">
                            <p class="form-control-plaintext mb-0">${shopOrderModel.siteUser.phoneNumber}</p>
                        </div>
                    </div>

                    <!-- Ghi chú -->
                    <div class="mb-3">
                        <label class="font-weight-bold">Ghi chú:</label>
                        <textarea class="form-control" id="description" rows="4">${shopOrderModel.description}</textarea>
                    </div>
                    <!-- Phần phương thức thanh toán -->
                    <div class="mb-3">
                        <label class="font-weight-bold">Phương thức thanh toán:</label>
                        <p>${shopOrderModel.paymentMethod.displayName}</p>
                    </div>

                    <!-- Phần địa chỉ giao hàng -->
                    <div class="mb-3">
                        <label class="font-weight-bold">Địa chỉ giao hàng:</label>
                        <p>${shopOrderModel.shippingAddress.value}</p>
                    </div>

                    <!-- Phần phương thức vận chuyển -->
                    <div class="mb-3">
                        <label class="font-weight-bold">Phương thức vận chuyển:</label>
                        <p>${shopOrderModel.shippingMethod.displayName}  (${utils:formatCurrency(shopOrderModel.shippingMethod.price)}) </p>
                    </div>

                    <div class="border-top pt-3 mt-3 row">
                        <h5 class="font-weight-bold col-sm-5 mb-0">Tổng tiền:</h5>
                        <h4 class="text-primary col-sm-7 mb-0">${utils:formatCurrency(shopOrderModel.orderTotal)}</h4>
                    </div>
                    <div class="d-flex justify-content-between mt-4">
                        <button id="cancelOrderBtn" class="btn btn-danger rounded-pill">Hủy</button>
                        <button id="placeOrderBtn" class="btn btn-primary rounded-pill">Giao hàng</button>
                    </div>
                </div>
            </div>

            <div class="col-md-12 col-lg-7 col-xl-8">
                <div class="table-responsive bg-light rounded shadow-sm p-4">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Hình ảnh</th>
                            <th scope="col">Tên</th>
                            <th scope="col">Loại</th>
                            <th scope="col">Đơn giá</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Thành tiền</th>
                        </tr>
                        </thead>
                        <tbody id="cartItems">
                        <c:forEach var="item" items="${shopOrderModel.listOrderLine}">
                            <tr data-id="${item.ID}">
                                <td>
                                    <img src="${item.productItem.product.productImage}" class="img-fluid rounded-circle" style="width: 80px; height: 80px;" alt="">
                                </td>
                                <td>${item.productItem.product.displayName}</td>
                                <td>${utils:formatVariation(item.productItem.listProductConfig)}</td>
                                <td>${utils:formatCurrency(item.productItem.price)}</td>
                                <td>
                                    <input type="text" class="form-control form-control-sm text-center border-0" value="${item.quantity}" readonly style="background-color: #f9f9f9; color: #6c757d; cursor: not-allowed;">
                                </td>
                                <td>${utils:formatCurrency(item.productItem.price * item.quantity)}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Checkout Page End -->

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.getElementById('cancelOrderBtn').addEventListener('click', function() {
        var orderId = '${shopOrderModel.ID}';
        var notes = document.getElementById('description').value;


        if(confirm("Bạn có muốn hủy đơn hàng này không?")) {

            const dataDelete = {
                orderId,
                notes,
            };

            fetch(`${pageContext.request.contextPath}/api-admin-shop-order`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dataDelete)
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    if (data.success) {
                        alert('Hủy đơn hàng thành công!');
                        let url = `${pageContext.request.contextPath}/admin-shop-order?status=waiting`;
                        window.location.href = url;
                    } else {
                        alert('Hủy đơn hàng thất bại!');
                    }
                })
                .catch(error => {
                    alert('Hủy đơn hàng thất bại!');
                });
        }
    });

    document.getElementById('placeOrderBtn').addEventListener('click', function() {
        var orderId = '${shopOrderModel.ID}';
        var notes = document.getElementById('description').value;
        alert('Đơn hàng đã được đặt để giao!\nID: ' + orderId + '\nGhi chú: ' + notes);
    });
</script>

</body>
</html>
