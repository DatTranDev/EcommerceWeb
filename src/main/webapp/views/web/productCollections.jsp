<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
</head>
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
                            <input type="search" class="form-control p-3" placeholder="Tìm kiếm" aria-describedby="search-icon-1">
                            <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                        </div>
                    </div>
                    <div class="col-6"></div>
                    <div class="col-xl-3">
                        <div class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
                            <label for="fruits">Sắp xếp :</label>
                            <select id="fruits" name="fruitlist" class="border-0 form-select-sm bg-light me-3" form="fruitform">
                                <option value="volvo">Mặc định</option>
                                <option value="saab">Giá</option>
                                <option value="opel">Bán chạy</option>
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
                                    <h4 class="mb-2">Price</h4>
                                    <input type="range" class="form-range w-100" id="rangeInput" name="rangeInput" min="0" max="500" value="0" oninput="amount.value=rangeInput.value">
                                    <output id="amount" name="amount" min-velue="0" max-value="500" for="rangeInput">0</output>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="row g-4">
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