<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://example.com/utils" prefix="utils" %>
<html>
<head>
    <title>Chi tiết đơn hàng</title>
</head>
<body>
    <!-- Single Page Header start -->
    <div class="container-fluid page-header py-5">
        <h1 class="text-center text-white display-6">Chi tiết đơn hàng</h1>
    </div>
    <!-- Single Page Header End -->
    <!-- Cart Page Start -->
    <div class="container-fluid py-5">
        <div class="container py-5">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Hình ảnh</th>
                        <th scope="col">Tên</th>
                        <th scope="col">Loại</th>
                        <th scope="col">Đơn giá</th>
                        <th scope="col">Số lượng mua</th>
                        <th scope="col">Thành tiền</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${shopOrderModel.getListOrderLine()}">
                        <tr>
                            <th scope="row">
                                <div class="d-flex align-items-center">
                                    <img src="${item.productItem.product.productImage}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                </div>
                            </th>
                            <td>
                                <p class="mb-0 mt-4">${item.productItem.product.displayName}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${utils:formatVariation(item.productItem.listProductConfig)}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${utils:formatCurrency(item.productItem.price)}</p>
                            </td>
                            <td>
                                <div class="input-group quantity mt-4" style="width: 100px;">
                                    <input type="text" class="form-control form-control-sm text-center border-0 item-quantity" value="${item.quantity}" data-original-value="${item.quantity}" data-max-quantity="${item.productItem.quantityInStock}" data-price="${item.productItem.price}">
                                </div>
                            </td>
                            <td>
                                <p class="mb-0 mt-4 item-total">${utils:formatCurrency(item.productItem.price * item.quantity)}</p>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="mt-5 row">
                <!-- Phương thức thanh toán -->
                <div class="col-md-6 d-flex align-items-center justify-content-start">
                    <h5 class="mb-0">Phương thức thanh toán:</h5>
                    <p class="mb-0 ms-2" id="payMethod">${shopOrderModel.getPaymentMethod().getDisplayName()}</p>
                </div>
                <!-- Chi phí vận chuyển -->
                <div class="col-md-6 d-flex align-items-center justify-content-end">
                    <h5 class="mb-0">Chi phí vận chuyển:</h5>
                    <p class="mb-0 ms-2 text-danger" id="chiPhiVanChuyen">${utils:formatCurrency(chiPhiVanChuyen)}</p>
                </div>
                <!-- Tổng tiền sản phẩm -->
                <div class="col-md-6 d-flex align-items-center justify-content-end">
                    <h5 class="mb-0">Tổng tiền sản phẩm:</h5>
                    <p class="mb-0 ms-2 text-danger" id="tongTienSP">${utils:formatCurrency(shopOrderModel.getOrderTotal()-chiPhiVanChuyen)}</p>
                </div>
                <!-- Tổng tiền -->
                <div class="col-md-6 d-flex align-items-center justify-content-end">
                    <h5 class="mb-0">Thành tiền:</h5>
                    <p class="mb-0 ms-2 text-danger" id="totalAmount">${utils:formatCurrency(shopOrderModel.getOrderTotal())}</p>
                </div>
            </div>
            <div class="mt-5 d-flex justify-content-start">
                <!-- Địa chỉ giao hàng -->
                <div class="d-flex align-items-center">
                    <h5 class="mb-0">Địa chỉ giao hàng:</h5>
                    <p class="mb-0 ms-2" id="Address">${shopOrderModel.getShippingAddress().getValue()}</p>
                </div>
            </div>
            <div class="mt-5 d-flex justify-content-start">
                <!-- Phương thức vận chuyển -->
                <div class="d-flex align-items-center">
                    <h5 class="mb-0">Phương thức vận chuyển:</h5>
                    <p class="mb-0 ms-2" id="deliveryMethod">${shopOrderModel.getShippingMethod().getDisplayName()}</p>
                </div>
            </div>
            <div class="mt-5">
            </div>

            <form id="buyForm" method="post" action="${pageContext.request.contextPath}/shop-order">
                <input type="hidden" name="selectedIds" id="selectedIds">
            </form>
        </div>
    </div>
    <!-- Cart Page End -->
<script>

</script>

</body>
</html>
