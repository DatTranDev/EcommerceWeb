<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
</head>
<body>
    <!-- Single Page Header start -->
    <div class="container-fluid page-header py-5">
        <h1 class="text-center text-white display-6">Shop</h1>
    </div>
    <!-- Fruits Shop Start-->
    <div class="container-fluid fruite py-5">
        <div class="container py-5">
            <h1 class="mb-4">ShoesStore Shop</h1>
            <div class="row g-4">
                <div class="col-lg-12">
                    <div class="row g-4">
                        <div class="col-xl-3">
                            <div class="input-group w-100 mx-auto d-flex">
                                <input id="searchBox" type="search" class="form-control p-3" placeholder="Tìm kiếm" aria-describedby="search-icon-1">
                                <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                            </div>
                        </div>
                        <div class="col-6"></div>
                        <div class="col-xl-3">
                            <div class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
                                <label for="fruits">Sắp xếp :</label>
                                <select id="fruits" name="fruitlist" class="border-0 form-select-sm bg-light me-3" form="fruitform">
                                    <option value="volvo">Mặc định</option>
                                    <option value="saab">Giá tăng dần</option>
                                    <option value="opel">Giá giảm dần</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row g-4">
                        <div class="col-lg-3">
                            <div class="row g-4">
                                <div class="col-lg-12">
                                    <div class="mb-3">
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
                                    <div class="mb-3">
                                        <h4 class="mb-2">Giá cao hơn</h4>
                                        <input type="range" class="form-range w-100" id="rangeInput" name="rangeInput" min="0" max="500" value="0" oninput="amount.value=rangeInput.value">
                                        <output id="amount" name="amount" min-velue="0" max-value="500" for="rangeInput">0</output>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <div class="row g-4" id="prdShow">
                                <c:forEach var="item" items="${Product}">
                                    <div class="col-md-6 col-lg-6 col-xl-4">
                                        <a href="${pageContext.request.contextPath}/products/${item.ID}" class="text-decoration-none text-dark">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src="${item.productImage}" class="img-fluid w-100 rounded-top" alt="">
                                                </div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4 class="product-name">${item.displayName}</h4>
                                                    <p class="description">${item.description}</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <fmt:formatNumber value="${item.minPrice}" pattern="#,##0" var="formattedPrice" />
                                                        <p class="text-dark fs-5 fw-bold mb-0">${formattedPrice}đ</p>
                                                            <%--                                                <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-cart-plus text-primary"></i></a>--%>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Fruits Shop End-->

    <script>
        document.addEventListener('DOMContentLoaded', function (){
            console.log('DOM loaded');
            var products = JSON.parse(`${json}`);
            console.log(products);
            // Get the search input
            var searchInput = document.getElementById('searchBox');

            // Add 'input' event listener to the search input
            searchInput.addEventListener('input', function() {
                // Filter the products array to get the products that contain the input text
                var filteredProducts = products.filter(function(product) {
                    return product.displayName.toLowerCase().includes(this.value.toLowerCase());
                }.bind(this));

                // Update the product list on the page with the filtered products
                updateProductList(filteredProducts);
            });

            // Function to update the product list on the page
            function updateProductList(filteredProducts) {
                // Get the product list element
                var productList = document.getElementById('prdShow');

                // Clear the current product list
                productList.innerHTML = '';

                // Add the filtered products to the product list
                filteredProducts.forEach(function(product) {
                    var productElement = document.createElement('div');
                    const id = product.id;
                    const productImage = product.productImage;
                    const productName = product.displayName;
                    productElement.className = 'col-md-6 col-lg-6 col-xl-4';
                    productElement.innerHTML = `
                            <a href="${pageContext.request.contextPath}/products/` +id+`" class="text-decoration-none text-dark">
                                <div class="rounded position-relative fruite-item">
                                    <div class="fruite-img">
                                        <img src="`+productImage+ `" class="img-fluid w-100 rounded-top" alt="">
                                    </div>
                                    <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                        <h4 class="product-name">`+productName+`</h4>
                                        <p class="description">`+product.description+`</p>
                                        <div class="d-flex justify-content-between flex-lg-wrap">
                                            <p class="text-dark fs-5 fw-bold mb-0">`+product.minPrice.toLocaleString()+`đ</p>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        `;
                    productList.appendChild(productElement);
                });
            }

            var selectOption = document.getElementById('fruits');
            selectOption.addEventListener('change', function() {
                var selectedOption = selectOption.value;
                if (selectedOption === 'volvo') {
                    products.sort(function(a, b) {
                        return a.id - b.id;
                    });
                } else if (selectedOption === 'saab') {
                    products.sort(function(a, b) {
                        return a.minPrice - b.minPrice;
                    });
                } else if (selectedOption === 'opel') {
                    products.sort(function(a, b) {
                        return b.minPrice - a.minPrice;
                    });
                }
                updateProductList(products);
            });

            var rangeInput = document.getElementById('rangeInput');

            rangeInput.addEventListener('input', function() {
                var amount = document.getElementById('amount');
                amount.value = rangeInput.value;
                var filteredProducts = products.filter(function(product) {
                    return product.minPrice >= rangeInput.value*1000;
                });
                updateProductList(filteredProducts);
            });
        });
    </script>
</body>
</html>
