<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://example.com/utils" prefix="utils" %>
<html>
<head>
    <title>Giỏ hàng</title>
</head>
<body>
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
                            <p class="mb-0 mt-4 item-total">${utils:formatCurrency(item.productItem.price * item.quantity)}</p>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mt-5">
            <!-- Tổng tiền -->
            <div class="d-flex justify-content-end mb-4">
                <h5 class="mb-0">Tổng tiền:</h5>
                <p class="mb-0 ms-2" id="totalAmount">${utils:formatCurrency(0)}</p>
            </div>
            <button class="btn btn-danger" id="delete-selected-items">Xóa các sản phẩm đã chọn</button>
            <button class="btn btn-success" id="buy-selected-items">Mua các sản phẩm đã chọn</button>
        </div>
    </div>
</div>
<!-- Cart Page End -->

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const deleteButton = document.getElementById("delete-selected-items");
        const checkboxes = document.querySelectorAll(".delete-item");
        const totalAmountElement = document.getElementById("totalAmount");
        const buyButton = document.getElementById("buy-selected-items");


        function formatCurrency(value) {
            return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
        }

        function calculateTotal() {
            let totalAmount = 0;
            checkboxes.forEach(checkbox => {
                if (checkbox.checked) {
                    const itemRow = checkbox.closest('tr');
                    const itemTotal = parseFloat(itemRow.querySelector('.item-total').innerText.replace(/[^\d]/g, ''));
                    totalAmount += itemTotal;
                }
            });
            totalAmountElement.innerText = formatCurrency(totalAmount);
        }

        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function() {
                calculateTotal();
            });
        });

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


        buyButton.addEventListener("click", function() {
            const selectedIds = [];
            checkboxes.forEach(checkbox => {
                if (checkbox.checked) {
                    selectedIds.push(checkbox.getAttribute("data-id"));
                }
            });

            if (selectedIds.length > 0) {
                const urlAPI = `${pageContext.request.contextPath}/shop-order`;
                var data = { ids: selectedIds };

                $.ajax({
                    url: urlAPI,
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function (result) {
                        if (result.success) {
                            window.location.href = `${pageContext.request.contextPath}/checkout`;
                        } else {
                            console.log("vao day");
                            alert("Có lỗi xảy ra!");
                        }
                    },
                    error: function (error) {
                        console.log(error);
                        alert("Có lỗi xảy ra!");
                    }
                });
            } else {
                alert("Vui lòng chọn ít nhất một mục để mua.");
            }
        });
    });
</script>
</body>
</html>
