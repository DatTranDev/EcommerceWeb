<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://example.com/utils" prefix="utils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${statusShopOder}</title>
</head>
<body>

<!-- Single Page Header End -->

<!-- Cart Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Số thứ tự</th>
                    <th scope="col">Họ tên</th>
                    <th scope="col">Số điện thoại</th>
                    <th scope="col">Email</th>
                    <th scope="col">Thời gian đặt</th>
                    <th scope="col">Giá trị đơn hàng</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${shopOrderModelList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${item.siteUser.displayName}</td>
                        <td>${item.siteUser.phoneNumber}</td>
                        <td>${item.siteUser.email}</td>
                        <td><fmt:formatDate value="${item.orderDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                        <td>${item.orderTotal}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Cart Page End -->

<script>

</script>
</body>
</html>
