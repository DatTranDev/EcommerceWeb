<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>${Product.displayName}</title>


    <style>
        .input-group.quantity {
            display: flex !important;
            align-items: center !important;
            flex-wrap: nowrap;
        }

        .input-group.quantity .form-control {
            width: 50px !important;
            flex: 0 1 auto;
        }

        #totalPriceContainer {
            margin-left: 10px !important;
            white-space: nowrap;
        }

        #totalPrice {
            margin-left: 10px !important;
            font-weight: bold !important;
            white-space: nowrap;
        }

        #input_QuantityByDTT{
            width: 1px;
        }

    </style>

</head>
<body>
<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Chi tiết</h1>
</div>
<!-- Single Page Header End -->

<!-- Single Product Start -->
<div class="container-fluid py-5 mt-5">
    <div class="container py-5">
        <div class="row g-4 mb-5">
            <div class="col-lg-8 col-xl-9">
                <div class="row g-4">
                    <div class="col-lg-6">
                        <div class="border rounded">
                            <a href="#">
                                <img src="${Product.productImage}" class="img-fluid rounded" alt="Image">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <h4 class="fw-bold mb-3">${Product.displayName}</h4>
                        <p class="mb-3">Danh mục: ${Product.category.categoryName}</p>
                        <h5 id="productPrice" class="fw-bold mb-3">${formattedPrice}</h5>
                        <h5 class="fw-bold mb-3">${formattedPrice}</h5>
                        <div class="d-flex mb-4">
                            <c:forEach var="i" begin="1" end="${RoundAvg}">
                                <i class="fas fa-star text-secondary" style="margin-top: 4px"></i>
                            </c:forEach>
                            <c:forEach var="i" begin="1" end="${5 - RoundAvg}">
                                <i class="fas fa-star" style="margin-top: 4px"></i>
                            </c:forEach>
                            <p style="margin-left: 12px">(${AvgRating})</p>
                        </div>
                        <p class="mb-4">${Product.description}</p>

                        <c:set var="hasSize" value="false" />
                        <c:set var="hasColor" value="false" />

                        <c:forEach var="item" items="${productItemList}">
                            <c:forEach var="config" items="${item.listProductConfig}">
                                <c:if test="${config.variationOption.variation.displayName == 'Size'}">
                                    <c:set var="hasSize" value="true" />
                                </c:if>
                                <c:if test="${config.variationOption.variation.displayName == 'Màu'}">
                                    <c:set var="hasColor" value="true" />
                                </c:if>
                            </c:forEach>
                        </c:forEach>

                        <c:if test="${hasSize}">
                            <div class="mb-4">
                                <label for="sizeSelect">Chọn kích cỡ:</label>
                                <select id="sizeSelect" class="form-select">
                                    <c:forEach var="item" items="${productItemList}">
                                        <c:forEach var="config" items="${item.listProductConfig}">
                                            <c:if test="${config.variationOption.variation.displayName == 'Size'}">
                                                <option value="${item.ID}_${config.variationOption.value}">${config.variationOption.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>

                        <c:if test="${hasColor}">
                            <div class="mb-4">
                                <label for="colorSelect">Chọn màu sắc:</label>
                                <select id="colorSelect" class="form-select">
                                    <c:forEach var="item" items="${productItemList}">
                                        <c:forEach var="config" items="${item.listProductConfig}">
                                            <c:if test="${config.variationOption.variation.displayName == 'Màu'}">
                                                <option value="${item.ID}_${config.variationOption.value}">${config.variationOption.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>

                        <div class="mb-4">
                            <label for="quantityInStock">Số lượng tồn:</label>
                            <span id="quantityInStock">${productItemList[0].quantityInStock}</span>
                        </div>
                        <div class="input-group quantity mt-4" style="width: auto;">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-sm btn-minus rounded-circle bg-light border quantity-button">
                                    <i class="fa fa-minus"></i>
                                </button>
                            </div>
                            <input style="width: 50px !important;"id="input_QuantityByDTT" type="text" class="text-center border-0 item-quantity" value="1" data-max-quantity="${productItemList[0].quantityInStock}" data-price="${Product.minPrice}">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-sm btn-plus rounded-circle bg-light border quantity-button">
                                    <i class="fa fa-plus"></i>
                                </button>
                            </div>
                            <div id="totalPriceContainer" class="d-flex align-items-center ms-3">
                                <h5 id="totalPrice" class="fw-bold mb-0 ms-2">Tổng tiền: ${formattedPrice}</h5>
                            </div>
                        </div>

                        <a href="#" id="addToCartButton" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary" style="margin-top: 20px;">
                            <i class="fa fa-shopping-bag me-2 text-primary"></i> Thêm vào giỏ hàng
                        </a>

                    </div>
                    <div class="col-lg-12">
                        <nav>
                            <div class="nav nav-tabs mb-3">
                                <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                                        id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                                        aria-controls="nav-about" aria-selected="true">Mô tả</button>
                                <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                                        id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
                                        aria-controls="nav-mission" aria-selected="false">Đánh giá từ khách hàng (${CountRating}) </button>
                            </div>
                        </nav>
                        <div class="tab-content mb-5">
                            <div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                                <p>Quý khách vui lòng tham khảo bảng thông số dưới đây để lựa chọn size phù hợp </p>
                                <img src="${pageContext.request.contextPath}/template/web/img/sizechart.png" class="img-fluid w-100 rounded" alt="">
                            </div>
                            <div class="tab-pane" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">
                                <c:forEach var="item" items="${UserReview}">
                                    <div class="d-flex">
                                        <img src="${pageContext.request.contextPath}/template/web/img/avatar.jpg" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
                                        <div style="display: flex;flex-direction: column;justify-content: center">
                                            <div class="d-flex">
                                                <h5 style="margin-right: 8px">${item.username}</h5>
                                                <div class="d-flex mb-3">
                                                    <c:forEach var="i" begin="1" end="${item.ratingValue}">
                                                        <i class="fas fa-star text-secondary" style="margin-top: 4px"></i>
                                                    </c:forEach>
                                                    <c:forEach var="i" begin="1" end="${5 - item.ratingValue}">
                                                        <i class="fas fa-star" style="margin-top: 4px"></i>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                           <p>${item.comment}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not IsReviewed}">
                        <form id="reviewForm" >
                            <h4 class="mb-5 fw-bold">Để lại đánh giá</h4>
                            <div class="row g-4">
                                <div class="col-lg-12">
                                    <div class="border-bottom rounded my-4">
                                        <textarea name="comment" id="" class="form-control border-0" cols="30" rows="8" placeholder="Đánh giá *" spellcheck="false"></textarea>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="d-flex justify-content-between py-3 mb-5">
                                        <div class="d-flex align-items-center">
                                            <p class="mb-0 me-3">Rating:</p>
                                            <div class="d-flex align-items-center" style="font-size: 12px;">
                                                <i id="star1" class="fa fa-star text-secondary"></i>
                                                <i id="star2" class="fa fa-star text-secondary"></i>
                                                <i id="star3" class="fa fa-star text-secondary"></i>
                                                <i id="star4" class="fa fa-star text-secondary"></i>
                                                <i id="star5" class="fa fa-star text-secondary"></i>
                                            </div>
                                            <input type="hidden" id="ratingValue" name="ratingValue">
                                            <input type="hidden" id="orderlineid" name="orderedProductID">
                                        </div>
                                        <button id="reviewButton" type="button" class="btn border border-secondary text-primary rounded-pill px-4 py-3">Đánh giá</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:if>

                </div>
            </div>
            <div class="col-lg-4 col-xl-3">
                <div class="row g-4 fruite">
                    <div class="col-lg-12">
                        <div class="input-group w-100 mx-auto d-flex mb-4">
                            <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                            <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                        </div>
                        <div class="mb-4">
                            <h4>Danh mục</h4>
                            <ul class="list-unstyled fruite-categorie">
                                <c:forEach var="item" items="${ProductCategory}">
                                    <li>
                                        <div class="d-flex justify-content-between fruite-name">
                                            <a href="${pageContext.request.contextPath}/product-collections?categoryID=${item.ID}"><i class="fas fa-link me-2"></i>${item.categoryName}</a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="position-relative">
                            <img src="${pageContext.request.contextPath}/template/web/img/banner.png" class="img-fluid w-100 rounded" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <h1 class="fw-bold mb-0">Sản phẩm liên quan</h1>
        <div class="vesitable">
            <div class="owl-carousel vegetable-carousel justify-content-center">
                <c:forEach var="item" items="${ProductList}">
                    <a href="${pageContext.request.contextPath}/products/${item.ID}" class="text-decoration-none text-dark">
                        <div class="rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="${item.productImage}" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="p-4 border rounded-bottom border-secondary border-top-0">
                                <h4 class="product-name">${item.displayName}</h4>
                                <p class="description">${item.description}</p>
                                <div class="d-flex justify-content-between flex-lg-wrap">
                                    <fmt:formatNumber value="${item.minPrice}" pattern="#,##0" var="formattedPrice" />
                                    <p class="text-dark fs-5 fw-bold mb-0">${formattedPrice}đ</p>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>



</div>
<!-- Single Product End -->



<!--fix size mau bi trung-->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        function removeDuplicateOptions(selectElement) {
            var options = selectElement.options;
            var values = new Set();
            for (var i = options.length - 1; i >= 0; i--) {
                var optionValue = options[i].text;
                if (values.has(optionValue)) {
                    selectElement.remove(i);
                } else {
                    values.add(optionValue);
                }
            }
        }

        var sizeSelect = document.getElementById('sizeSelect');
        if (sizeSelect) {
            removeDuplicateOptions(sizeSelect);
        }

        var colorSelect = document.getElementById('colorSelect');
        if (colorSelect) {
            removeDuplicateOptions(colorSelect);
        }
    });
</script>

<!--  json -->
<script>
    var productItems = [
        <c:forEach var="item" items="${productItemList}">
        {
            id: '${item.ID}',
            price: '${item.price}',
            configs: [
                <c:forEach var="config" items="${item.listProductConfig}">
                {
                    type: '${config.variationOption.variation.displayName}',
                    value: '${config.variationOption.value}',
                    quantityInStock: ${item.quantityInStock}
                },
                </c:forEach>
            ]
        },
        </c:forEach>
    ];
    console.log("Product items: ", productItems);
</script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var selectedProductItemId = null;

        function updateQuantity() {
            var sizeSelect = document.getElementById('sizeSelect');
            var colorSelect = document.getElementById('colorSelect');
            var selectedSize = sizeSelect ? sizeSelect.value.split('_')[1] : null;
            var selectedColor = colorSelect ? colorSelect.value.split('_')[1] : null;
            var matchedProductItem = null;

            productItems.forEach(function(item) {
                var hasSize = false;
                var hasColor = false;

                item.configs.forEach(function(config) {
                    if (config.type === 'Size' && config.value === selectedSize) {
                        hasSize = true;
                    }
                    if (config.type === 'Màu' && config.value === selectedColor) {
                        hasColor = true;
                    }
                });

                if (!sizeSelect) {//phong trong truong hop khong co size
                    hasSize = true;
                }
                if (!colorSelect) {//phong trong truong hop khong co mau
                    hasColor = true;
                }

                if (hasSize && hasColor) {
                    matchedProductItem = item;
                }
            });

            var quantityInStock = document.getElementById('quantityInStock');
            var quantityInput = document.querySelector(".item-quantity");
            var addToCartButton = document.getElementById('addToCartButton');
            var priceDisplay = document.getElementById('productPrice');
            var totalPriceDisplay = document.getElementById('totalPrice');

            if (matchedProductItem) {
                var stock = matchedProductItem.configs[0].quantityInStock;
                quantityInStock.textContent = stock;
                quantityInput.setAttribute('data-max-quantity', stock);
                quantityInput.value = 1;

                selectedProductItemId = matchedProductItem.id;

                // cap nhat gia tien tuong ung
                priceDisplay.textContent = new Intl.NumberFormat('vi-VN').format(matchedProductItem.price) + 'đ';
                var totalPrice = parseInt(matchedProductItem.price) * parseInt(quantityInput.value);
                totalPriceDisplay.textContent = 'Tổng tiền: ' + new Intl.NumberFormat('vi-VN').format(totalPrice) + 'đ';
                // Kiểm tra số lượng tồn kho và cập nhật trạng thái của nút "Thêm vào giỏ hàng"
                if (stock > 0) {
                    addToCartButton.classList.remove('disabled');
                    addToCartButton.classList.add('text-primary');
                } else {
                    addToCartButton.classList.add('disabled');
                    addToCartButton.classList.remove('text-primary');
                }
            } else {
                quantityInStock.textContent = '0';
                quantityInput.setAttribute('data-max-quantity', '0');
                quantityInput.value = 1;

                // Vô hiệu hóa nút "Thêm vào giỏ hàng" khi không có hàng tồn kho
                addToCartButton.classList.add('disabled');
                addToCartButton.classList.remove('text-primary');
            }

            updateTotalPrice();
        }

        function updateTotalPrice() {
            var quantityInput = document.querySelector(".item-quantity");
            var quantity = parseInt(quantityInput.value);
            var priceDisplay = document.getElementById('productPrice');
            var totalPriceDisplay = document.getElementById('totalPrice');

            var price = parseInt(priceDisplay.textContent.replace(/[^0-9]/g, ''));
            var totalPrice = price * quantity;
            totalPriceDisplay.textContent = 'Tổng tiền: ' + new Intl.NumberFormat('vi-VN').format(totalPrice) + 'đ';

            const maxQuantity = parseInt(quantityInput.getAttribute('data-max-quantity'));
            if(maxQuantity===0){
                totalPriceDisplay.textContent = '';
            }
        }

        // Tự động cập nhật khi trang tải
        updateQuantity();

        if (document.getElementById('sizeSelect')) {
            document.getElementById('sizeSelect').addEventListener('change', updateQuantity);
        }
        if (document.getElementById('colorSelect')) {
            document.getElementById('colorSelect').addEventListener('change', updateQuantity);
        }

        //tang giam so luong
        const quantityButtons = document.querySelectorAll(".quantity-button");
        const quantityInput = document.querySelector(".item-quantity");

        quantityButtons.forEach(button => {
            button.addEventListener('click', function() {
                let value = parseInt(quantityInput.value);
                const maxQuantity = parseInt(quantityInput.getAttribute('data-max-quantity'));

                if (this.classList.contains('btn-minus')) {
                    value += 1;
                    value = value > 1 ? value - 1 : 1;
                } else if (this.classList.contains('btn-plus')) {
                    value -= 1;
                    value = value < maxQuantity ? value + 1 : maxQuantity;
                }

                quantityInput.value = value;
                validateQuantity(quantityInput);
                updateTotalPrice();
            });
        });

        quantityInput.addEventListener('input', function() {
            validateQuantity(quantityInput);
            updateTotalPrice();
        });

        function validateQuantity(input) {
            const maxQuantity = parseInt(input.getAttribute('data-max-quantity'));
            let value = parseInt(input.value);

            if (isNaN(value) || value < 1) {
                value = 1;
            } else if (value > maxQuantity) {
                value = maxQuantity;
            }

            input.value = value;
        }

        // them vao gio hang
        const addToCartButton = document.getElementById('addToCartButton');
        addToCartButton.addEventListener('click', function(event) {
            event.preventDefault();
            const quantityInput = document.querySelector(".item-quantity");

            const quantity = quantityInput ? quantityInput.value : 0;

            const data = {
                productItemId: selectedProductItemId,
                quantity,
            };

            fetch('${pageContext.request.contextPath}/api-add_product_to_cart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        if (!data.typeAddTocart) {
                            if (confirm("Sản phẩm đã có trong giỏ hàng với số lượng là " + data.soluongdangco + ",bạn muốn đặt thêm số lượng không?")) {
                                const maxQuantity = parseInt(quantityInput.getAttribute('data-max-quantity'));

                                let denta = parseInt(maxQuantity) - (parseInt(quantity) + parseInt(data.soluongdangco));

                                console.log(denta);
                                console.log(maxQuantity);
                                console.log(quantity);
                                console.log(data.soluongdangco);
                                if ( parseInt(denta) < 0) {
                                    const msg = "Bạn chỉ có thể mua thêm " + ( parseInt(maxQuantity) - data.soluongdangco) +" sản phẩm";
                                    alert(msg);
                                    return;
                                }

                                const dataUpdate = {
                                    productItemId: selectedProductItemId,
                                    quantity,
                                };


                                fetch('${pageContext.request.contextPath}/api-add_product_to_cart', {
                                    method: 'PUT',
                                    headers: {
                                        'Content-Type': 'application/json'
                                    },
                                    body: JSON.stringify(dataUpdate)
                                })
                                    .then(response => response.json())
                                    .then(dataForUpdate => {
                                        if (dataForUpdate.success) {
                                            updateCartItemCount();
                                            alert('Thêm sản phẩm vào giỏ hàng thành công!');
                                        } else {
                                            alert('Thêm sản phẩm vào giỏ hàng thất bại!');
                                        }
                                    })
                                    .catch(error => {
                                        alert('Thêm sản phẩm vào giỏ hàng thất bại!');
                                    });

                            }
                        } else {
                            updateCartItemCount();
                            alert('Thêm sản phẩm vào giỏ hàng thành công!');
                        }
                    } else {
                        if(!data.isLogin) {
                            alert('Bạn cần đăng nhập để thực hiện chức năng này!');
                        }
                        else
                            alert('Thêm sản phẩm vào giỏ hàng thất bại!');
                    }
                })
                .catch(error => {
                    alert('Thêm sản phẩm vào giỏ hàng thất bại!');
                });
        });

        //comment
        const reviewButton = document.getElementById('reviewButton');
        const reviewForm = document.getElementById('reviewForm');

        reviewButton.addEventListener('click', function(event) {
            event.preventDefault();

            // Get the review data from the form
            const formData = {
                comment: reviewForm.comment.value,
                ratingValue: reviewForm.ratingValue.value,
                orderedProductID: reviewForm.orderedProductID.value,
                userID: '${SITEUSER.ID}'

            };
            console.log(formData);
            // Send the review data to the server
            fetch('${pageContext.request.contextPath}/api-admin/userreview', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.status==='success') {
                        alert('Đánh giá thành công!');
                        // Reload the page
                        location.reload();
                    } else {
                        alert('Đánh giá thất bại!');
                    }
                })
                .catch(error => {
                    alert('Đánh giá thất bại!');
                });
        });

        //rating

        const stars = [
            document.getElementById('star1'),
            document.getElementById('star2'),
            document.getElementById('star3'),
            document.getElementById('star4'),
            document.getElementById('star5')
        ];
        const ratingValue = document.getElementById('ratingValue');
        ratingValue.value = 5;
        const orderlineid = document.getElementById('orderlineid');
        orderlineid.value = '${OrderLineID}';
        stars.forEach((star, index) => {
            star.addEventListener('click', function() {
                // Set the rating value
                ratingValue.value = index + 1;

                // Update the star colors
                stars.forEach((star, starIndex) => {
                    star.className = starIndex <= index ? 'fa fa-star text-secondary' : 'fa fa-star';
                });
            });
        });
    });
</script>


</body>
</html>
