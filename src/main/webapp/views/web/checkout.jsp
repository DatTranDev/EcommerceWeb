<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://example.com/utils" prefix="utils" %>
<html>
<head>
    <title>Đặt hàng</title>
</head>
<body>
<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Đặt hàng</h1>
</div>
<!-- Single Page Header End -->

<!-- Checkout Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <h1 class="mb-4">Thông tin hóa đơn</h1>
        <div class="row g-5">
            <div class="col-md-12 col-lg-5 col-xl-4">
                <!-- Phần thông tin hóa đơn -->
                <div class="bg-light rounded">
                    <div class="p-4">
                        <h1 class="display-6 mb-4">Thanh toán</h1>

                        <!-- Hiển thị tên người dùng và số điện thoại -->
                        <div class="mb-4">
                            <div class="d-flex align-items-center">
                                <h5 class="mb-0 me-2">Họ tên:</h5>
                                <p class="mb-0">${siteUser.displayName}</p>
                            </div>
                        </div>
                        <div class="mb-4">
                            <div class="d-flex align-items-center">
                                <h5 class="mb-0 me-2">Số điện thoại:</h5>
                                <p class="mb-0">${siteUser.phoneNumber}</p>
                            </div>
                        </div>

                        <!-- Ghi chú -->
                        <div class="mb-4">
                            <h5 class="mb-0">Ghi chú:</h5>
                            <textarea class="form-control" rows="4">${shopOrder.description}</textarea>
                        </div>

                        <!-- Phần phương thức thanh toán -->
                        <h5 class="mb-4">Phương thức thanh toán:</h5>
                        <c:forEach var="paymentMethod" items="${paymentMethodList}" varStatus="status">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="paymentMethod"
                                       id="paymentMethod${paymentMethod.ID}"
                                       value="${paymentMethod.ID}"
                                       <c:if test="${paymentMethod.isDeleted()}">disabled</c:if>
                                       <c:if test="${status.first && !paymentMethod.isDeleted()}">checked</c:if>>
                                <label class="form-check-label" for="paymentMethod${paymentMethod.ID}">
                                        ${paymentMethod.displayName}
                                </label>
                            </div>
                        </c:forEach>

                        <!-- Phần địa chỉ giao hàng -->
                        <h5 class="mb-4 mt-4">Địa chỉ giao hàng:</h5>
                        <c:forEach var="address" items="${userAddressList}" varStatus="status">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="shippingAddress" id="shippingAddress${address.addressID}" value="${address.addressID}" <c:if test="${status.first}">checked</c:if>>
                                <label class="form-check-label" for="shippingAddress${address.addressID}">
                                        ${address.address.value}
                                </label>
                            </div>
                        </c:forEach>

                        <!-- Phần phương thức vận chuyển -->
                        <h5 class="mb-4 mt-4">Phương thức vận chuyển:</h5>
                        <c:forEach var="shippingMethod" items="${shippingMethodList}" varStatus="status">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="shippingMethod"
                                       id="shippingMethod${shippingMethod.ID}"
                                       value="${shippingMethod.ID}"
                                       data-shipping-fee="${shippingMethod.price}"
                                       <c:if test="${shippingMethod.isDeleted()}">disabled</c:if>
                                       <c:if test="${status.first && !shippingMethod.isDeleted()}">checked</c:if>>
                                <label class="form-check-label" for="shippingMethod${shippingMethod.ID}">
                                        ${shippingMethod.displayName}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                        <h5 class="mb-0 ps-4 me-4">Tổng tiền</h5>
                        <p class="mb-0 pe-4" id="totalAmount">${utils:formatCurrency(0)}</p>
                    </div>
                    <a href="${pageContext.request.contextPath}/checkout" class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4">Đặt hàng</a>
                </div>
            </div>

            <div class="col-md-12 col-lg-7 col-xl-8">
                <div class="table-responsive">
                    <table class="table">
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
                        <tbody>
                        <c:forEach var="item" items="${shoppingCartItemModelList}">
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
                                        <div class="input-group-btn">
                                        </div>
                                        <input type="text" class="form-control form-control-sm text-center border-0" value="${item.quantity}" readonly style="background-color: #f9f9f9; color: #6c757d; cursor: not-allowed;">
                                        <div class="input-group-btn">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4 item-total" data-item-total="${item.productItem.price * item.quantity}">${utils:formatCurrency(item.productItem.price * item.quantity)}</p>
                                </td>
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

<script>
    window.onload = function() {
        const shippingMethods = document.querySelectorAll('input[name="shippingMethod"]');
        const totalAmountElement = document.getElementById('totalAmount');
        const itemTotalElements = document.querySelectorAll('.item-total');

        function calculateTotal() {
            let itemTotal = 0;
            itemTotalElements.forEach(element => {
                itemTotal += parseFloat(element.getAttribute('data-item-total'));
            });

            const selectedShippingMethod = document.querySelector('input[name="shippingMethod"]:checked');
            const shippingFee = parseFloat(selectedShippingMethod.getAttribute('data-shipping-fee'));

            const totalAmount = itemTotal + shippingFee;
            totalAmountElement.textContent = totalAmount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        }

        shippingMethods.forEach(shippingMethod => {
            shippingMethod.addEventListener('change', calculateTotal);
        });

        // Initial calculation with the default selected shipping method
        calculateTotal();
    };
</script>

</body>
</html>
