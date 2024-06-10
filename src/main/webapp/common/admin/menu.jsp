<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<!-- Sidebar -->
<ul
		class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
		id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
			class="sidebar-brand d-flex align-items-center justify-content-center"
			href="#">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-laugh-wink"></i>
		</div>
		<div class="sidebar-brand-text mx-3">
			Admin<sup>1.0</sup>
		</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/admin-home">
		<i class="fas fa-fw fa-tachometer-alt"></i> <span>Thống kê</span>
	</a></li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">Table</div>

	<!-- Nav Item - Utilities Collapse Menu -->


	<li class="nav-item"><a class="nav-link collapsed" href="#"
							data-toggle="collapse" data-target="#collapseUtilities"
							aria-expanded="true" aria-controls="collapseUtilities">
		<i class="fas fa-fw  fa-solid fa-shoe-prints"></i>
		<span>Quản lý sản phẩm</span>
	</a>
		<div id="collapseUtilities" class="collapse"
			 aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Thao tác</h6>
				<a class="collapse-item" href="${pageContext.request.contextPath}/admin-product">Danh sách sản phẩm</a>
				<a class="collapse-item" href="${pageContext.request.contextPath}/admin-product">Danh mục sản phẩm</a>
				<a class="collapse-item" href="${pageContext.request.contextPath}/admin-size">Size</a>
				<div class="collapse-divider"></div>
				<h6 class="collapse-header">Khuyến mãi</h6>
				<a class="collapse-item" href="#">Quản lý khuyến mãi</a>
			</div>
		</div></li>
	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item">
		<a class="nav-link collapsed" href="#"
		   data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
		   aria-controls="collapseTwo">
			<i class="fas fa-fw  fa-solid fa-truck"></i>
			<span>Quản lý đơn hàng</span>
		</a>
		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
			 data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Đơn hàng</h6>
				<a class="collapse-item nav-status-shop-oder" href="admin-shop-order?status=waiting">Chờ xác nhận</a>
				<a class="collapse-item nav-status-shop-oder" href="admin-shop-order?status=shipping">Đang vận chuyển</a>
				<a class="collapse-item nav-status-shop-oder" href="admin-shop-order?status=delivered">Giao hàng thành công</a>
				<a class="collapse-item nav-status-shop-oder" href="admin-shop-order?status=failed">Giao hàng thất bại</a>
				<a class="collapse-item nav-status-shop-oder" href="admin-shop-order?status=canceled">Đã hủy</a>
			</div>
		</div>
	</li>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>
<!-- End of Sidebar -->

<script>
	document.querySelectorAll('.nav-status-shop-oder').forEach(function(element) {
		element.addEventListener('click', function(event) {
			event.preventDefault();
			var status = this.getAttribute('href').split('=')[1];
			window.location.href = 'admin-shop-order?status=' + status;
		});
	});
</script>
