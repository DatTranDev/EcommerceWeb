<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/5/2024
  Time: 2:56 AM
  To change this template use File | Settings | File Templates.
--%>
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
                        <h2 class="tm-block-title d-inline-block">THÊM SẢN PHẨM</h2>
                    </div>
                </div>
                <form class="row tm-edit-product-row" id="productForm" action="<c:url value='/admin-product/add'/>" method="post" onsubmit="submitForm()">
                    <div class="col-xl-6 col-lg-6 col-md-12">
                        <div class="tm-edit-product-form">
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
                                        required
                                />
                            </div>
                            <div class="form-group mb-3">
                                <label
                                        for="description"
                                >Mô tả</label
                                >
                                <textarea
                                        name="description"
                                        class="form-control validate"
                                        rows="3"
                                        required
                                ></textarea>
                            </div>
                            <div class="form-group mb-3">
                                <label
                                        for="category"
                                >Danh mục</label
                                >
                                <select
                                        class="custom-select tm-select-accounts"
                                        id="category"
                                        name="category"
                                >
                                    <c:forEach var="item" items="${listCategory}">
                                        <option value="${item.id}">${item.name}${item.parent}</option>
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
                            <button type="submit" class="btn btn-primary btn-block text-uppercase">THÊM SẢN PHẨM</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    let currentImageIndex = 0;
    let images = [];

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
    function submitForm() {
        const imageUrlsInput = document.getElementById('imageUrlsInput');
        imageUrlsInput.value = JSON.stringify(images);
        document.getElementById('productForm').submit();
    }

</script>
</body>
</html>
