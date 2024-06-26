<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title><dec:title default="Admin"/></title>

<!-- Custom fonts for this template-->
<link
	href="<c:url value='/template/admin/vendor/fontawesome-free/css/all.min.css' />"
	rel="stylesheet" type="text/css" media="all">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" type="text/css" media="all">
	<link
			rel="stylesheet"
			href="https://fonts.googleapis.com/css?family=Roboto:400,700"
	/>
<%--	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">--%>
<%--	<!-- Font Awesome CSS -->--%>
<%--	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">--%>
	<link href="<c:url value='/template/admin/css/template_product_style.css' />"
		  rel="stylesheet" type="text/css" media="all">

<!-- Custom styles for this template-->
<link href="<c:url value='/template/admin/css/sb-admin-2.min.css' />"
	rel="stylesheet" type="text/css" media="all">
<link href="<c:url value='/template/admin/css/style.css' />"
	rel="stylesheet" type="text/css" media="all">

</head>
<body id="page-top">
	<div id="wrapper">
		 <!-- Sidebar -->
		<%@ include file="/common/admin/menu.jsp"%>
		<!-- End of Sidebar -->
		
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<%@include file="/common/admin/header.jsp"%>

				<dec:body />
			</div>
			<!-- End of Main Content -->

			<%@ include file="/common/admin/footer.jsp"%>
		</div>
		<!-- End of Content Wrapper -->
	</div>

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Bạn có chắc chắn muốn đăng xuất</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Hủy</button>
					<a class="btn btn-primary" href="<c:url value="/thoat?action=logout"/>">Đăng xuất</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="<c:url value='/template/admin/vendor/jquery/jquery.min.js' />"
		type="text/javascript" ></script>
	<script
		src="<c:url value='/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"
		type="text/javascript" ></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<c:url value='/template/admin/vendor/jquery-easing/jquery.easing.min.js'/>" type="text/javascript" ></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<c:url value='/template/admin/js/sb-admin-2.min.js' />" type="text/javascript"></script>
	<script
		src="<c:url value='/template/admin/js/main.js'/>" type="text/javascript"></script>

	<!-- Page level plugins -->
	<script
		src="<c:url value='/template/admin/vendor/chart.js/Chart.min.js'/>" type="text/javascript"></script>

	<!-- Page level custom scripts -->
	<script
		src="<c:url value='/template/admin/js/demo/chart-area-demo.js'/>" type="text/javascript"></script>
	<script
		src="<c:url value='/template/admin/js/demo/chart-pie-demo.js'/>" type="text/javascript"></script>
<%--	Duc--%>
<%--	<!-- jQuery -->--%>
<%--	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>--%>
<%--	<!-- Popper.js -->--%>
<%--	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>--%>
<%--	<!-- Bootstrap JS -->--%>
<%--	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>--%>
</body>
</html>