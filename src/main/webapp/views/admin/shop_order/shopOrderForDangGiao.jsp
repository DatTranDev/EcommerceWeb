<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://example.com/utils" prefix="utils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${statusShopOder}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        .action-icons {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 5px;
        }
        .action-icons .btn {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 5px;
            margin: 0;
        }
    </style>
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
                    <th scope="col">Hành động</th>
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
                        <td>${utils:formatCurrency(item.orderTotal)}</td>
                        <td class="action-icons">
                            <button style="padding-top: 1px;padding-bottom: 1px" class="btn btn-info btn-sm" onclick="handleAction('detail', ${item.ID})" title="Chi tiết">
                                <i class="fas fa-info-circle" style="font-size: 10px;"></i>
                            </button>
                            <button style="padding-top: 1px;padding-bottom: 1px"  class="btn btn-success btn-sm" onclick="handleAction('accept', ${item.ID})" title="Chấp nhận">
                                <i class="fas fa-check" style="font-size: 10px;"></i>
                            </button>
                            <button style="padding-top: 1px;padding-bottom: 1px"  class="btn btn-danger btn-sm" onclick="handleAction('delete', ${item.ID})" title="Xóa">
                                <i class="fas fa-times" style="font-size: 10px;"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Cart Page End -->
<script>
    function handleAction(action, id) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = getContextPath() + '/admin-shop-order';

        const actionInput = document.createElement('input');
        actionInput.type = 'hidden';
        actionInput.name = 'action';
        actionInput.value = action;
        form.appendChild(actionInput);

        const idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = 'id';
        idInput.value = id;
        form.appendChild(idInput);

        document.body.appendChild(form);
        console.log(action);
        if(action==="detail"){
            console.log("vao");
            form.submit();
        }
        else{
            if(action==="accept"){
                confirmAccept(id);
            }
            else{
                confirmDelete(id);
            }
        }
    }

    function confirmDelete(id) {
        if (confirm("Bạn có chắc chắn muốn hủy đơn hàng này không?")) {
            console.log(id);

            var notes =  `deleteOnShopOrder.jsp`;
            const dataDelete = {
                orderId:id,
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
    }

    function confirmAccept(id) {
        if (confirm("Bạn muốn chuyển đơn hàng sang trạng thái đang giao hàng?")) {
            console.log(id);

            const dataAccept = {
                orderId:id,
            };

            fetch(`${pageContext.request.contextPath}/api-admin-shop-order`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dataAccept)
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    if (data.success) {
                        alert('Chuyển trạng thái đơn hàng thành công!');
                        let url = `${pageContext.request.contextPath}/admin-shop-order?status=waiting`;
                        window.location.href = url;
                    } else {
                        alert('Chuyển trạng thái đơn hàng thất bại!');
                    }
                })
                .catch(error => {
                    alert('Chuyển trạng thái đơn hàng thất bại!');
                });


        }
    }


    function getContextPath() {
        return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    }
</script>

</body>
</html>
