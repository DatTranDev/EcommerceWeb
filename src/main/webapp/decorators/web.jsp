<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><dec:title default="Trang chủ" /></title>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	type="text/css" media="all" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet" type="text/css" media="all">

<!-- Libraries Stylesheet -->
<link
	href="${pageContext.request.contextPath}/template/web/lib/lightbox/css/lightbox.min.css"
	rel="stylesheet" type="text/css" media="all">
<link	
	href="${pageContext.request.contextPath}/template/web/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet" type="text/css" media="all">


<!-- Customized Bootstrap Stylesheet -->
<link href="${pageContext.request.contextPath}/template/web/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all">

<!-- Template Stylesheet -->
<link href="${pageContext.request.contextPath}/template/web/css/style.css"
	rel="stylesheet" type="text/css" media="all">
</head>
<body>
	<!-- Header -->
	<%@ include file="/common/web/header.jsp"%>
	<!-- Header -->

	<div class="container">
		<dec:body />
	</div>

	<!-- Footer -->
	<%@ include file="/common/web/footer.jsp"%>
	<!-- Footer -->
	
	<!-- Back to Top -->
	<a href="#"
		class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
		class="fa fa-arrow-up"></i></a>
	<!-- JavaScript Libraries -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="<c:url value='/template/web/lib/easing/easing.min.js' />"></script>
	<script
		src="<c:url value='/template/web/lib/waypoints/waypoints.min.js' />"
		type="text/javascript"></script>
	<script
		src="<c:url value='/template/web/lib/lightbox/js/lightbox.min.js' />"
		type="text/javascript"></script>
	<script
		src="<c:url value='/template/web/lib/owlcarousel/owl.carousel.min.js' />"
		type="text/javascript"></script>

	<!-- Template Javascript -->
	<script src="<c:url value='/template/web/js/main.js' /> "
		type="text/javascript"></script>
</body>
</html>