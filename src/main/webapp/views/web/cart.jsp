<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://example.com/utils" prefix="utils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Giỏ hàng</title>
</head>
<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Giỏ hàng</h1>
</div>
<!-- Single Page Header End -->

<!-- Cart Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Chọn</th>
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
                        <td>
                            <br>
                            <input type="checkbox" class="delete-item" data-id="${item.ID}">
                        </td>
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
                                    <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <input type="text" class="form-control form-control-sm text-center border-0" value="${item.quantity}">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td>
                            <p class="mb-0 mt-4">${utils:formatCurrency(item.productItem.price * item.quantity)}</p>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mt-5">
            <input type="text" class="border-0 border-bottom rounded me-5 py-3 mb-4" placeholder="Coupon Code">
            <button class="btn border-secondary rounded-pill px-4 py-3 text-primary" type="button">Apply Coupon</button>
            <button class="btn btn-danger" id="delete-selected-items">Xóa các mục đã chọn</button>
        </div>
        <div class="row g-4 justify-content-end">
            <div class="col-8"></div>
            <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
                <div class="bg-light rounded">
                    <div class="p-4">
                        <h1 class="display-6 mb-4">Cart <span class="fw-normal">Total</span></h1>
                        <div class="d-flex justify-content-between mb-4">
                            <h5 class="mb-0 me-4">Subtotal:</h5>
                            <p class="mb-0">${utils:formatCurrency(totalPrice)}</p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h5 class="mb-0 me-4">Shipping</h5>
                            <div class="">
                                <p class="mb-0">${utils:formatCurrency(shippingPrice)}</p>
                            </div>
                        </div>
                        <p class="mb-0 text-end">Shipping to Vietnam.</p>
                    </div>
                    <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                        <h5 class="mb-0 ps-4 me-4">Total</h5>
                        <p class="mb-0 pe-4">${utils:formatCurrency(totalPrice + shippingPrice)}</p>
                    </div>
                    <a href="${pageContext.request.contextPath}/checkout" class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4">Proceed Checkout</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Cart Page End -->

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const deleteButton = document.getElementById("delete-selected-items");
        const checkboxes = document.querySelectorAll(".delete-item");

        deleteButton.addEventListener("click", function() {
            const selectedIds = [];
            checkboxes.forEach(checkbox => {
                if (checkbox.checked) {
                    selectedIds.push(checkbox.getAttribute("data-id"));
                }
            });

            if (selectedIds.length > 0 && confirm("Bạn có chắc chắn muốn xóa các sản phẩm đã chọn không?")) {
                const urlAPI = `${pageContext.request.contextPath}/api-cart`;
                var data = { ids: selectedIds };

                $.ajax({
                    url: urlAPI,
                    type: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function (result) {
                        if (result.success) {
                            window.location.reload();
                        } else {
                            alert("Có lỗi xảy ra!");
                        }
                    },
                    error: function (error) {
                        alert("Có lỗi xảy ra!");
                    }
                });
            } else if (selectedIds.length === 0) {
                alert("Vui lòng chọn ít nhất một mục để xóa.");
            }
        });
    });


</script>

</html>
