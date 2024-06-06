<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://example.com/utils" prefix="utils" %>
<html>
<head>
    <title>Giỏ hàng</title>
    <style>
        #update-cart-button {
            display: none; /* Ẩn nút cập nhật giỏ hàng */
        }
    </style>
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
                    <th scope="col">Số lượng mua</th>
                    <th scope="col">Số lượng tồn</th>
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
                                    <button type="button" class="btn btn-sm btn-minus rounded-circle bg-light border quantity-button">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <input type="text" class="form-control form-control-sm text-center border-0 item-quantity" value="${item.quantity}" data-original-value="${item.quantity}" data-max-quantity="${item.productItem.quantityInStock}" data-price="${item.productItem.price}">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-sm btn-plus rounded-circle bg-light border quantity-button">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td>
                            <p class="mb-0 mt-4">${item.productItem.quantityInStock}</p>
                        </td>
                        <td>
                            <p class="mb-0 mt-4 item-total">${utils:formatCurrency(item.productItem.price * item.quantity)}</p>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mt-5 d-flex justify-content-end">
            <!-- Nút Cập Nhật Giỏ Hàng -->
            <button class="btn btn-primary me-2" id="update-cart-button">Cập nhật giỏ hàng</button>

            <!-- Tổng tiền -->
            <div class="d-flex align-items-center">
                <h5 class="mb-0">Tổng tiền:</h5>
                <p class="mb-0 ms-2" id="totalAmount">${utils:formatCurrency(0)}</p>
            </div>
        </div>
        <div class="mt-5">
            <button class="btn btn-danger" id="delete-selected-items">Xóa các sản phẩm đã chọn</button>
            <button class="btn btn-success" id="buy-selected-items">Mua các sản phẩm đã chọn</button>
        </div>

        <form id="buyForm" method="post" action="${pageContext.request.contextPath}/shop-order">
            <input type="hidden" name="selectedIds" id="selectedIds">
        </form>
    </div>
</div>
<!-- Cart Page End -->

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const deleteButton = document.getElementById("delete-selected-items");
        const checkboxes = document.querySelectorAll(".delete-item");
        const totalAmountElement = document.getElementById("totalAmount");
        const buyButton = document.getElementById("buy-selected-items");
        const updateCartButton = document.getElementById("update-cart-button");
        const buyForm = document.getElementById("buyForm");
        const selectedIdsInput = document.getElementById("selectedIds");
        const itemQuantities = document.querySelectorAll(".item-quantity");
        const quantityButtons = document.querySelectorAll(".quantity-button");

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

        function showUpdateButton() {
            updateCartButton.style.display = 'block';
        }

        function hideUpdateButton() {
            updateCartButton.style.display = 'none';
        }

        function checkForQuantityChange() {
            let hasChanged = false;
            itemQuantities.forEach(quantityInput => {
                const originalValue = quantityInput.getAttribute('data-original-value');
                const currentValue = quantityInput.value;
                if (originalValue !== currentValue) {
                    hasChanged = true;
                }
            });
            if (hasChanged) {
                showUpdateButton();
            } else {
                hideUpdateButton();
            }
        }

        function validateQuantity(input) {
            const maxQuantity = parseInt(input.getAttribute('data-max-quantity'));
            let value = parseInt(input.value);

            if (isNaN(value) || value < 1) {
                value = 1;
            } else if (value > maxQuantity) {
                value = maxQuantity;
            }

            input.value = value;
            updateItemTotal(input);
        }

        function updateItemTotal(input) {
            const itemRow = input.closest('tr');
            const price = parseFloat(input.getAttribute('data-price'));
            const quantity = parseInt(input.value);
            const itemTotal = itemRow.querySelector('.item-total');
            const total = price * quantity;
            itemTotal.innerText = formatCurrency(total);
            calculateTotal();
        }

        itemQuantities.forEach(quantityInput => {
            quantityInput.addEventListener('input', function() {
                validateQuantity(this);
                checkForQuantityChange();
            });
        });

        quantityButtons.forEach(button => {
            button.addEventListener('click', function() {
                const input = this.closest('.quantity').querySelector('.item-quantity');
                let value = parseInt(input.value);
                const maxQuantity = parseInt(input.getAttribute('data-max-quantity'));

                if (this.classList.contains('btn-minus')) {
                    value=value+1;
                    value = value > 1 ? value - 1 : 1;
                } else if (this.classList.contains('btn-plus')) {
                    value=value-1;
                    value = value < maxQuantity ? value + 1 : maxQuantity;
                }

                input.value = value;
                validateQuantity(input);
                checkForQuantityChange();
            });
        });

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
                selectedIdsInput.value = selectedIds.join(',');

                buyForm.submit();
            } else {
                alert("Vui lòng chọn ít nhất một mục để mua.");
            }
        });


        //cap nhat gio hang
        updateCartButton.addEventListener("click", function() {
            alert("Cập nhật giỏ hàng thành công!");
            hideUpdateButton();
        });

        checkForQuantityChange();
    });
</script>
</body>
</html>
