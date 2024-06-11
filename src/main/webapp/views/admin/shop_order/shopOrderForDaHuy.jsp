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
        .sortable:hover {
            cursor: pointer;
        }
    </style>
</head>
<body>

<!-- Search Bar Start -->
<div class="container py-4">
    <div class="input-group mb-3">
        <input type="text" class="form-control" id="searchInput" placeholder="Tìm kiếm theo họ tên, số điện thoại hoặc email">
    </div>
</div>
<!-- Search Bar End -->

<!-- Cart Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="table-responsive">
            <table class="table" id="ordersTable">
                <thead>
                <tr>
                    <th scope="col">Số thứ tự</th>
                    <th scope="col">Họ tên</th>
                    <th scope="col">Số điện thoại</th>
                    <th scope="col">Email</th>
                    <th scope="col" class="sortable" onclick="sortTableByDate()" title="Sắp xếp theo thời gian giảm dần">Thời gian đặt <i id="sortIcon" class="fas fa-sort-down"></i></th>
                    <th scope="col">Giá trị đơn hàng</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${shopOrderModelList}" varStatus="status">
                    <tr>
                        <td class="order-index">${status.index + 1}</td>
                        <td>${item.siteUser.displayName}</td>
                        <td>${item.siteUser.phoneNumber}</td>
                        <td>${item.siteUser.email}</td>
                        <td class="order-date"><fmt:formatDate value="${item.orderDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                        <td>${utils:formatCurrency(item.orderTotal)}</td>
                        <td class="action-icons">
                            <button style="padding-top: 1px;padding-bottom: 1px" class="btn btn-info btn-sm" onclick="handleAction('detail', ${item.ID})" title="Chi tiết">
                                <i class="fas fa-info-circle" style="font-size: 10px;"></i>
                            </button>
                                <%--                            <button style="padding-top: 1px;padding-bottom: 1px"  class="btn btn-success btn-sm" onclick="handleAction('accept', ${item.ID})" title="Giao hàng thành công">--%>
                                <%--                                <i class="fas fa-check" style="font-size: 10px;"></i>--%>
                                <%--                            </button>--%>
                                <%--                            <button style="padding-top: 1px;padding-bottom: 1px"  class="btn btn-danger btn-sm" onclick="handleAction('delete', ${item.ID})" title="Giao hàng thất bại">--%>
                                <%--                                <i class="fas fa-times" style="font-size: 10px;"></i>--%>
                                <%--                            </button>--%>
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
    let sortOrder = 'desc';

    function searchOrders() {
        const input = document.getElementById("searchInput");
        const filter = input.value.toLowerCase();
        const table = document.getElementById("ordersTable");
        const tr = table.getElementsByTagName("tr");

        for (let i = 1; i < tr.length; i++) {
            let tdName = tr[i].getElementsByTagName("td")[1];
            let tdPhone = tr[i].getElementsByTagName("td")[2];
            let tdEmail = tr[i].getElementsByTagName("td")[3];
            if (tdName || tdPhone || tdEmail) {
                const txtValueName = tdName.textContent || tdName.innerText;
                const txtValuePhone = tdPhone.textContent || tdPhone.innerText;
                const txtValueEmail = tdEmail.textContent || tdEmail.innerText;
                if (txtValueName.toLowerCase().indexOf(filter) > -1 ||
                    txtValuePhone.toLowerCase().indexOf(filter) > -1 ||
                    txtValueEmail.toLowerCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
        updateOrderIndexes();
    }

    function sortTableByDate() {
        const table = document.getElementById("ordersTable");
        const tbody = table.getElementsByTagName("tbody")[0];
        const rows = Array.from(tbody.getElementsByTagName("tr"));

        rows.sort((a, b) => {
            const dateA = new Date(a.getElementsByClassName("order-date")[0].innerText);
            const dateB = new Date(b.getElementsByClassName("order-date")[0].innerText);
            return sortOrder === 'asc' ? dateA - dateB : dateB - dateA;
        });

        sortOrder = sortOrder === 'asc' ? 'desc' : 'asc';
        document.getElementById("sortIcon").className = sortOrder === 'asc' ? 'fas fa-sort-up' : 'fas fa-sort-down';
        document.querySelector('.sortable').title = sortOrder === 'asc' ? 'Sắp xếp theo thời gian tăng dần' : 'Sắp xếp theo thời gian giảm dần';

        rows.forEach(row => tbody.appendChild(row));
        updateOrderIndexes();
    }

    function updateOrderIndexes() {
        const table = document.getElementById("ordersTable");
        const tr = table.getElementsByTagName("tr");

        let index = 1;
        for (let i = 1; i < tr.length; i++) {
            if (tr[i].style.display !== "none") {
                tr[i].getElementsByClassName("order-index")[0].innerText = index++;
            }
        }
    }

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
        if(action==="detail"){
            form.submit();
        }
    }

    function getContextPath() {
        return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    }

    document.getElementById("searchInput").addEventListener("input", searchOrders);
    sortTableByDate();
</script>

</body>
</html>
