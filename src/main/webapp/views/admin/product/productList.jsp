<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/4/2024
  Time: 2:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<script>
    // Hàm hiển thị hộp thoại xác nhận
    function confirmDelete(message,url) {
        if(url!=null)
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
<div class="container mt-5">
    <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
            <div class="tm-bg-primary-dark tm-b
            lock tm-block-products">
                <div class="tm-product-table-container">
                    <table class="table table-hover tm-table-small tm-product-table">
                        <thead>
                        <tr>
                            <th scope="col">&nbsp;</th>
                            <th scope="col">TÊN SẢN PHẨM</th>
                            <th scope="col">TỒN KHO</th>
                            <th scope="col">DANH MỤC</th>
                            <th scope="col">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${listProduct}" >
                            <tr style="cursor: pointer;">
                                <th onclick="listDelete(${item.id})" scope="row"><input type="checkbox"></th>
                                <td onclick="openEditProductTab(${item.id})" class="tm-product-name">${item.name}</td>
                                <td onclick="openEditProductTab(${item.id})" style="text-align: center; vertical-align: middle;">${item.quantity}</td>
                                <td onclick="openEditProductTab(${item.id})">${item.category}</td>
                                <td onclick="if(confirmDelete('Bạn có chắc muốn xóa mục này không?')) { deleteOne(${item.id}); } return false;" >
                                    <a href="#" class="tm-product-delete-link">
                                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- table container -->
                <a  href="${pageContext.request.contextPath}/admin-product/add" class="btn btn-primary btn-block text-uppercase mb-3">THÊM SẢN PHẨM</a>
                <form id="deleteProduct" action="<c:url value='/admin-product'/>" method="post" onsubmit="submitForm()"
                      >
                    <input type="hidden" id="delete" name="listId"/>
                    <button class="btn btn-primary btn-block text-uppercase" type="submit">
                        XÓA SẢN PHẨM ĐÃ CHỌN
                    </button>
                </form>

            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-product-categories">
                <h2 class="tm-block-title">Product Categories</h2>
                <div class="tm-product-table-container">
                    <table class="table tm-table-small tm-product-table">
                        <tbody>
                        <c:forEach var="item" items="${listCategory}" >
                            <tr  style="cursor: pointer;">
                                <td  onclick="openEditCategoryTab(${item.id})" class="tm-product-name">${item.name}${item.parent}</td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/admin-deleteCategory/${item.id}" class="tm-product-delete-link"
                                       onclick="confirmDelete('Bạn có chắc muốn xóa mục này không?','${pageContext.request.contextPath}/admin-deleteCategory/${item.id}')">
                                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <a  href="${pageContext.request.contextPath}/admin-addCategory" class="btn btn-primary btn-block text-uppercase mb-3">THÊM DANH MỤC</a>
            </div>
        </div>
    </div>
</div>
<script>
    function openEditProductTab(itemId) {
        // Thay đổi đường dẫn theo URL trang chỉnh sửa sản phẩm của bạn
        const editUrl =`${pageContext.request.contextPath}/admin-editProduct/`+itemId;
        window.location.href = editUrl;
    }
</script>
<script>
    function openEditCategoryTab(itemId) {
        const editCategoryUrl =`${pageContext.request.contextPath}/admin-editCategory/`+itemId;
        window.location.href = editCategoryUrl;
    }
</script>
<script>
    let listId=[];
    function listDelete(number) {
        var index = listId.indexOf(number);
        if (index !== -1) {
            listId.splice(index, 1);
        } else {
            listId.push(number);
        }
    }
    function submitForm() {
        if(confirmDelete("Bạn có chắc muốn xóa những mục đã chọn hay không ?"))
        {
            var deleteString = listId.join(", ");
            const imageUrlsInput = document.getElementById('delete');
            imageUrlsInput.value = deleteString;
            document.getElementById('deleteProduct').submit();
        }

    }
    function  deleteOne(number){
        const imageUrlsInput = document.getElementById('delete');
        imageUrlsInput.value = number;
        document.getElementById('deleteProduct').submit();
    }
</script>

</body>
</html>
