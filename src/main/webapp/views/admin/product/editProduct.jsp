<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/6/2024
  Time: 2:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<% List<String> imageUrls = (List<String>) request.getAttribute("listImage"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container tm-mt-big tm-mb-big">
    <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
            <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                <div class="row">
                    <div class="col-12">
                        <h2 class="tm-block-title d-inline-block" style="margin-top: 0;">SỬA SẢN PHẨM</h2>
                    </div>
                </div>
                <div class="row tm-edit-product-row">
                    <div class="col-xl-6 col-lg-6 col-md-12">
                        <form action="" class="tm-edit-product-form">
                            <div class="form-group mb-3">
                                <label
                                        for="name"
                                >Tên sản phẩm
                                </label>
                                <input
                                        id="name"
                                        name="name"
                                        type="text"
                                        class="form-control validate"
                                        value="${product.displayName}"
                                        required
                                />
                            </div>
                            <div class="form-group mb-3">
                                <label
                                        for="description"
                                >Mô tả</label
                                >
                                <textarea
                                        class="form-control validate"
                                        rows="3"
                                        required
                                >${product.description}</textarea>
                            </div>
                            <div class="form-group mb-3">
                                <label
                                        for="category"
                                >Danh mục</label
                                >
                                <select
                                        class="custom-select tm-select-accounts"
                                        id="category"
                                >
                                    <c:forEach var="item" items="${listCategory}">
                                        <option value="">${item.name}${item.parent}</option>
                                    </c:forEach>


                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">

                        <div class="tm-product-img-dummy mx-auto" id="imageContainer" style="position: relative;">
                            <i class="fas fa-cloud-upload-alt tm-upload-icon" id="uploadIcon" onclick="document.getElementById('fileInput').click();"></i>
                            <img id="previewImage" src="#" alt="Preview Image" style="display:none; width: auto; height: 100%;"/>
                            <button id="prevButton" style="display:none; position: absolute; top: 50%; left: 10px; transform: translateY(-50%); background-color: white; border: none; cursor: pointer;">←</button>
                            <button id="nextButton" style="display:none; position: absolute; top: 50%; right: 10px; transform: translateY(-50%); background-color: white; border: none; cursor: pointer;">→</button>
                        </div>
                        <div class="custom-file mt-3 mb-3">
                            <input id="fileInput" type="file" style="display:none;" accept="image/*" multiple onchange="displayImages(event)" />
                            <input type="button" class="btn btn-primary btn-block mx-auto" value="THÊM ẢNH" onclick="document.getElementById('fileInput').click();" />
                        </div>
                    </div>

                    <div class="tm-product-table-container col-12" style="align-self:center; justify-content: center; align-items: center;">
                        <label style="margin-top: 10px; margin-bottom: 25px;"
                               for="category"
                        >DANH SÁCH MÃ SẢN PHẨM</label>
                        <table class="table table-hover tm-table-small tm-product-table" style="align-self:center; justify-content: center; align-items: center;">
                            <thead>
                            <tr>
                                <th scope="col">&nbsp;</th>
                                <th scope="col">MÃ SẢN PHẨM</th>
                                <th scope="col">TỒN KHO</th>
                                <th scope="col">GIÁ</th>
                                <th scope="col">&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${listProductItem}" >
                                <tr  style="cursor: pointer;">
                                    <th  scope="row"><input type="checkbox"></th>
                                    <td onclick="openEditTab(${item.ID})" class="tm-product-name">${item.SKU}</td>
                                    <td onclick="openEditTab(${item.ID})" style="width:180px; padding-left: 30px "> ${item.quantityInStock} </td>
                                    <td onclick="openEditTab(${item.ID})"> ${item.price}</td>
                                    <td>
                                        <a href="#" class="tm-product-delete-link">
                                            <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>


                    <div class="col-12 d-flex justify-content-between" >
                        <a  href="${pageContext.request.contextPath}/admin-addProductItem/${product.ID}" class="btn btn-primary btn-block text-uppercase btn-custom" style="max-width: 40%;">THÊM MÃ SẢN PHẨM</a>

                        <button type="submit" class="btn btn-primary btn-block text-uppercase btn-custom" style="max-width: 40%; margin-top: 0;height: 100%;">Xóa mục đã chọn</button>
                    </div>

                    <div class="col-12" style="margin-top: 20px;">
                        <button type="submit" class="btn btn-primary btn-block text-uppercase">Cập nhật sản phẩm</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    let currentImageIndex = 0;
    let images = [];
    // Lấy danh sách URL ảnh từ JSP

    <% for (String url : imageUrls) { %>
    images.push("<%= url %>");
    <% } %>

    document.addEventListener("DOMContentLoaded", function() {
        if (images.length > 0) {
            displayImageAtIndex(0);
        }
    });

    function displayImages(event) {
        const files = event.target.files;
        images = [];
        currentImageIndex = 0;
        let loadedImagesCount = 0;


        // Clear previous images
        document.getElementById('imageContainer').innerHTML = '<i class="fas fa-cloud-upload-alt tm-upload-icon" id="uploadIcon" onclick="document.getElementById(\'fileInput\').click();"></i>';

        for (let i = 0; i < files.length; i++) {
            const reader = new FileReader();
            reader.onload = (e) => {
                images.push(e.target.result);
                loadedImagesCount++;
                if (loadedImagesCount === files.length) {
                    displayImageAtIndex(0);
                }
            };
            reader.readAsDataURL(files[i]);
        }
    }

    function displayImageAtIndex(index) {
        const imageContainer = document.getElementById('imageContainer');
        imageContainer.innerHTML = '<img id="previewImage" src="' + images[index] + '" alt="Preview Image" style="display:block; width: auto; height: 100%;"/>' +
            '<button id="prevButton" style="position: absolute; top: 50%; left: 10px; transform: translateY(-50%); background-color: white; border: none; cursor: pointer;">←</button>' +
            '<button id="nextButton" style="position: absolute; top: 50%; right: 10px; transform: translateY(-50%); background-color: white; border: none; cursor: pointer;">→</button>';


        const prevButton = document.getElementById('prevButton');
        const nextButton = document.getElementById('nextButton');

        if (images.length > 1) {
            prevButton.style.display = 'block';
            nextButton.style.display = 'block';
        } else {
            prevButton.style.display = 'none';
            nextButton.style.display = 'none';
        }

        prevButton.disabled = index === 0;
        nextButton.disabled = index === images.length - 1;

        prevButton.addEventListener('click', () => {
            if (currentImageIndex > 0) {
                currentImageIndex--;
                displayImageAtIndex(currentImageIndex);
            }
        });

        nextButton.addEventListener('click', () => {
            if (currentImageIndex < images.length - 1) {
                currentImageIndex++;
                displayImageAtIndex(currentImageIndex);
            }
        });
    }

</script>
<script>
    function openEditTab(itemId) {
        // Thay đổi đường dẫn theo URL trang chỉnh sửa sản phẩm của bạn
        const editUrl =`${pageContext.request.contextPath}/admin-editProductItem/`+itemId;
        window.location.href = editUrl;
    }
</script>
</body>
</html>
