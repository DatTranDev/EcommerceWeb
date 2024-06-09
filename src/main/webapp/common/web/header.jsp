<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<div>
	<!-- Spinner Start -->
	<div id="spinner"
		class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->


	<!-- Navbar start -->
	<div class="container-fluid fixed-top">
		<div class="container topbar bg-primary d-none d-lg-block">
			<div class="d-flex justify-content-between">
				<div class="top-info ps-2">
					<small class="me-3"><i
						class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#"
						class="text-white">UIT, VietNam</a></small> <small class="me-3"><i
						class="fas fa-envelope me-2 text-secondary"></i><a href="#"
						class="text-white">appservice.uit.se@gmail.com</a></small>
				</div>
				<div class="top-link pe-2">
					<a href="#" class="text-white"><small class="text-white mx-2">Chính sách bảo mật</small>/</a> <a href="#" class="text-white"><small
						class="text-white mx-2">Điều khoản sử dụng</small>/</a> <a href="#"
						class="text-white"><small class="text-white ms-2">Chính sách hoàn tiền</small></a>
				</div>
			</div>
		</div>
		<div class="container px-0">
			<nav class="navbar navbar-light bg-white navbar-expand-xl">
				<a href="${pageContext.request.contextPath}/trang-chu" class="navbar-brand">
					<h1 class="text-primary display-6">ShoesStore</h1>
				</a>
				<button class="navbar-toggler py-2 px-3" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
					<span class="fa fa-bars text-primary"></span>
				</button>
				<div class="collapse navbar-collapse bg-white" id="navbarCollapse">
					<div class="navbar-nav mx-auto">
						<a href="${pageContext.request.contextPath}/trang-chu" class="nav-item nav-link active">Trang chủ</a>
						<a href="${pageContext.request.contextPath}/product-collections" class="nav-item nav-link">Sản phẩm</a>
						<div class="nav-item dropdown">
							<a href="#" class="nav-link dropdown-toggle"
							   data-bs-toggle="dropdown">Giày dép</a>
							<div class="dropdown-menu m-0 bg-secondary rounded-0">
								<c:forEach var="item" items="${ShoesCategory}">
									<a href="#" class="dropdown-item">${item.categoryName}</a>
								</c:forEach>
							</div>
						</div>
						<div class="nav-item dropdown">
							<a href="#" class="nav-link dropdown-toggle"
								data-bs-toggle="dropdown">Phụ kiện</a>
							<div class="dropdown-menu m-0 bg-secondary rounded-0">
								<c:forEach var="item" items="${AccessoriesCategory}">
									<a href="#" class="dropdown-item">${item.categoryName}</a>
								</c:forEach>
							</div>
						</div>
						<a href="#" class="nav-item nav-link">Liên hệ</a>
					</div>
					<div class="d-flex m-3 me-0">
						<button
							class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4"
							data-bs-toggle="modal" data-bs-target="#searchModal">
							<i class="fas fa-search text-primary"></i>
						</button>
						<a href="${pageContext.request.contextPath}/cart" class="position-relative me-4 my-auto">
							<i class="fa fa-shopping-bag fa-2x"></i>
							<span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
							style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
						</a>
						<c:if test="${empty SITEUSER}">
							<a href="${pageContext.request.contextPath}/dang-nhap?action=login" class="my-auto">
								<i class="bi bi-box-arrow-in-left fa-2x"></i>
							</a>
						</c:if>
						<c:if test="${not empty SITEUSER}">
							<div class="nav-item dropdown">
								<a href="#" class="my-auto nav-link dropdown-toggle"
								   style="color:var(--bs-primary) !important; "
								   data-bs-toggle="dropdown">
									<i class="fas fa-user fa-2x"></i>
								</a>
								<div class="dropdown-menu m-0 bg-secondary rounded-0">
									<div class="dropdown-item">
										<i class="bi bi-person" style = "color: var(--bs-primary);"></i>
										<a id="accountButton" href="#" class="">Tài khoản</a>
									</div>
									<div class="dropdown-item">
										<i class="bi bi-clock-history" style = "color: var(--bs-primary);"></i>
										<a href="${pageContext.request.contextPath}/purchase-history" class="">Lịch sử</a>
									</div>
									<div class="dropdown-item">
										<i class="bi bi-box-arrow-left" style = "color: var(--bs-primary);"></i>
										<a href="<c:url value="/thoat?action=logout"/>" class="">Đăng xuất</a>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->


	<!-- Modal Search Start -->
	<div class="modal fade" id="searchModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-fullscreen">
			<div class="modal-content rounded-0">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Search by
						keyword</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body d-flex align-items-center">
					<div class="input-group w-75 mx-auto d-flex">
						<input type="search" class="form-control p-3"
							placeholder="keywords" aria-describedby="search-icon-1">
						<span id="search-icon-1" class="input-group-text p-3"><i
							class="fa fa-search"></i></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Search End -->
</div>

<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function() {
		var accountButton = document.getElementById("accountButton");
		accountButton.addEventListener("click", function(event) {
			event.preventDefault();
			window.location.href = 'site-user';
		});
	});
</script>
