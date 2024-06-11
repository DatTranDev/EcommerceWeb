<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/7/2024
  Time: 3:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<% List<String> imageUrls = (List<String>) request.getAttribute("listImage"); %>
<%
    int size = (Integer) request.getAttribute("size");
%>
<%
    int color = (Integer) request.getAttribute("color");
%>
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
                        <h2 class="tm-block-title d-inline-block">SỬA MÃ SẢN PHẨM</h2>
                    </div>
                </div>
                <form class="row tm-edit-product-row" id="productItemForm" action="<c:url value='/admin-editProductItem/${productItem.ID}'/>" method="post" onsubmit="submitForm()">
                    <div class="col-xl-6 col-lg-6 col-md-12">
                        <div class="tm-edit-product-form">
                            <div class="form-group mb-3">
                                <label
                                        for="name"
                                >Mã sản phẩm
                                </label>
                                <input
                                        id="name"
                                        name="name"
                                        type="text"
                                        class="form-control validate"
                                        value="${productItem.SKU}"
                                        required
                                />
                            </div>
                            <div class="form-group mb-3">
                                <label
                                        for="name"
                                >Số lượng trong kho
                                </label>
                                <input
                                        id="quantity"
                                        name="quantity"
                                        type="number"
                                        class="form-control validate"
                                        value="${productItem.quantityInStock}"
                                        required
                                />
                            </div>
                            <div class="form-group mb-3">
                                <label
                                        for="name"
                                >Giá
                                </label>
                                <input
                                        id="price"
                                        name="price"
                                        type="number"
                                        class="form-control validate"
                                        value="${productItem.price}"
                                        required
                                />
                            </div>

                        <c:if test="${type == 1}">
                            <div class="form-group mb-3">
                                <label style="font-weight: bold;">Phân loại (chọn ít nhất một phân loại)</label>
                            </div>
                        </c:if>

                        <div class="form-group mb-3">
                            <label
                            >Size</label
                            >
                            <select
                                    class="custom-select tm-select-accounts"
                                    id="size"
                                    name="size"
                            >
                                <c:if test="${type == 1}">
                                    <option value="-1">Không có</option>
                                </c:if>

                                <c:forEach var="item" items="${listSize}">
                                    <c:choose>
                                        <c:when test="${item.ID == size}">
                                            <option value="${item.ID}" selected >${item.value}</option>
                                        </c:when>
                                        <c:otherwise>

                                                <option value="${item.ID}" >${item.value}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group mb-3">
                            <label

                            >Màu</label
                            >
                            <select
                                    class="custom-select tm-select-accounts"
                                    id="color"
                                    name="color"
                            >
                                <c:if test="${type == 1}">
                                    <option value="-1">Không có</option>
                                </c:if>
                                <c:forEach var="item" items="${listColor}">
                                    <c:choose>
                                        <c:when test="${item.ID == color}">
                                            <option value="${item.ID}" selected >${item.value}</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="${item.ID}" >${item.value}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                        <div class="tm-product-img-dummy mx-auto" id="imageContainer" style="position: relative;">
                            <i class="fas fa-cloud-upload-alt tm-upload-icon" id="uploadIcon"
                               onclick="document.getElementById('fileInput').click();"></i>
                            <img id="previewImage" src="#" alt="Preview Image"
                                 style="display:none; width: auto; height: 100%;"/>
                            <button id="prevButton"
                                    style="display:none; position: absolute; top: 50%; left: 10px; transform: translateY(-50%); background-color: white; border: none; cursor: pointer;">
                                ←
                            </button>
                            <button id="nextButton"
                                    style="display:none; position: absolute; top: 50%; right: 10px; transform: translateY(-50%); background-color: white; border: none; cursor: pointer;">
                                →
                            </button>
                        </div>
                        <div class="custom-file mt-3 mb-3">
                            <input id="fileInput" type="file" style="display:none;" accept="image/*" multiple
                                   onchange="displayImages(event)"/>
                            <input type="button" class="btn btn-primary btn-block mx-auto" value="THÊM ẢNH"
                                   onclick="document.getElementById('fileInput').click();"/>
                        </div>
                    </div>
                    <div class="col-12">
                        <input type="hidden" id="imageUrlsInput" name="listImage"/>
                        <button type="submit" class="btn btn-primary btn-block text-uppercase">CẬP NHẬT MÃ SẢN PHẨM
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%
    String alertMessage = (String) session.getAttribute("alert");
    if (alertMessage != null) {
        alertMessage = java.net.URLDecoder.decode(alertMessage, "UTF-8");
        session.removeAttribute("alert");
%>
<script>
    alert("<%=alertMessage %>");
</script>
<%
    }
%>
<script>
    let currentImageIndex = 0;
    let images = [];
    // Lấy danh sách URL ảnh từ JSP

    <% for (String url : imageUrls) { %>
    images.push("<%= url %>");
    <% } %>

    document.addEventListener("DOMContentLoaded", function () {
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
    //
    function submitForm() {
        const imageUrlsInput = document.getElementById('imageUrlsInput');
        imageUrlsInput.value = JSON.stringify(images);
        document.getElementById('productItemForm').submit();
    }

</script>

</body>
</html>
