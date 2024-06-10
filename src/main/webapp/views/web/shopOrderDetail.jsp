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

    <!-- List đơn hàng: Tất cả -->
    <div class="product-list active tab-pane" role="tabpanel" id="tat-ca">
        <c:forEach var="order" items="${shopOrderModelList}">
            <div class="order-separator">
                <div class="order mb-3">
                    <div class="row">
                        <div class="col-md-12 status-right">
                            <p class="text-danger">${order.getStatusName()}</p>
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
                                <%--                                            <div class="col-md-4 text-right">--%>
                                <%--                                                <p class="text-danger">${order.getStatusName()}</p>--%>
                                <%--                                            </div>--%>
                        </div>
                    </c:forEach>
                    <div class="row">
                        <div class="col-md-12 text-right">
                            <p class="order-total">Thành tiền: ${utils:formatCurrency(order.getOrderTotal())}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 text-right order-actions">
                            <button type="button" class="btn btn-primary order-status">${order.getDescribeOrder()}</button>
                            <button type="button" class="btn btn-secondary">Xem Chi Tiết Hủy Đơn</button>
                            <button type="button" class="btn btn-secondary">Liên Hệ Người Bán</button>
                        </div>
                    </div>
                </div>
                <hr>
            </div>
        </c:forEach>
    </div>
<script>

</script>

</body>
</html>
