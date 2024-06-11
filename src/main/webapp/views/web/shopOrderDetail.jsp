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
                    <c:forEach var="item" items="${shoppingCartItemModelList}">
                        <tr>
                            <th scope="row">
                                <div class="d-flex align-items-center">
                                    <img src="${item.productItem.productImage}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                </div>
                            </th>
                            <td>
                                <p class="mb-0 mt-4">${item.product.displayName}</p>
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
                                <p class="mb-0 mt-4 item-total">${utils:formatCurrency(item.productItem.price * item.quantity)}</p>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="mt-5 d-flex justify-content-sm-between">
                <!-- Time -->
                <div class="d-flex align-items-center" style="align-self: flex-end">
                    <h5 class="mb-0">Thời gian: </h5>
                    <p class="mb-0 ms-2" >${shopOrderModel.orderDate}</p>
                </div>
                <!-- Tổng tiền -->
                <div class="d-flex align-items-center">
                    <h5 class="mb-0">Tổng tiền:</h5>
                    <p class="mb-0 ms-2" id="totalAmount">${utils:formatCurrency(0)}</p>
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
    document.addEventListener('DOMContentLoaded', function (){
        let itemTotal = document.querySelectorAll('.item-total');
        let totalAmount = document.getElementById('totalAmount');
        var total = 0;
        itemTotal.forEach(function (item){
            console.log(item.innerText);
            total += parseInt(item.innerText.replace(/[^0-9]/g, ''));
        });
        totalAmount.innerText = total.toLocaleString('vi-VN', {style : 'currency', currency : 'VND'});

    })
</script>

</body>
</html>
