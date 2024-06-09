<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/9/2024
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    // Hàm hiển thị hộp thoại xác nhận
    function confirmDelete(message,url) {
        if(url==null)
        {
            return confirm(message);
        }
        else {
            if (confirm(message)) {
                window.location.href = url;
            }
        }

    }
</script>
<div class="container mt-5" style="margin-left: 20%;">

    <div class=" tm-block-col"
         style="display: flex; flex-direction: row; width: 700px" >
        <div class="tm-bg-primary-dark tm-block tm-block-product-categories" >
            <h2 class="tm-block-title">SIZE</h2>
            <div class="tm-product-table-container">
                <table style="width: 250px" class="table tm-table-small tm-product-table">
                    <tbody>
                    <c:forEach var="item" items="${listSize}">
                        <tr style="cursor: pointer;">
                            <td onclick="openEditCategoryTab(${item.ID})"
                                class="tm-product-name">${item.value}
                            </td>
                            <td class="text-center">
                                <a onclick="confirmDelete('Bạn có chắc muốn xóa mục này ?', '${pageContext.request.contextPath}/admin-deleteSize/${item.ID}')"
                                   class="tm-product-delete-link">
                                    <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="${pageContext.request.contextPath}/admin-addSize/size"
               class="btn btn-primary btn-block text-uppercase mb-3">THÊM SIZE</a>
            <!-- table container -->
        </div>
        <div class="tm-bg-primary-dark tm-block tm-block-product-categories" style="margin-left: 10%;">
            <h2 class="tm-block-title">MÀU</h2>
            <div class="tm-product-table-container">
                <table style="width: 250px" class="table tm-table-small tm-product-table">
                    <tbody>
                    <c:forEach var="item" items="${listColor}">
                        <tr style="cursor: pointer; " >
                            <td  onclick="openEditCategoryTab(${item.ID})"
                                class="tm-product-name">${item.value}
                            </td>
                            <td class="text-center">
                                <a onclick="confirmDelete('Bạn có chắc muốn xóa mục này ?', '${pageContext.request.contextPath}/admin-deleteSize/${item.ID}')"
                                   class="tm-product-delete-link">
                                    <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="${pageContext.request.contextPath}/admin-addSize/color"
               class="btn btn-primary btn-block text-uppercase mb-3">THÊM MÀU</a>
            <!-- table container -->
        </div>
    </div>

</div>
<script>
    function openEditCategoryTab(itemId) {
        const editCategoryUrl =`${pageContext.request.contextPath}/admin-editSize/`+itemId;
        window.location.href = editCategoryUrl;
    }
</script>
</body>
</html>
