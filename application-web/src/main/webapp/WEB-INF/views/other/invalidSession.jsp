<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/cmnCntxtPath.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${appTitle} | Invalid Session</title>
	<!-- Google Font: Source Sans Pro -->
	<link rel="stylesheet" href="${contextPath}static/resources/google-fonts/google-fonts.css">
	<!-- Font Awesome Icons -->
	<link rel="stylesheet" href="${contextPath}static/resources/font-awesome/css/all.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="${contextPath}static/css/adminlte.min.css">
	<link rel="shortcut icon" href="${contextPath}static/images/favicon.ico" />
</head>
<body class="hold-transition sidebar-mini text-sm layout-fixed layout-navbar-fixed layout-footer-fixed">
	<div class="wrapper">
		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="${contextPath}" class="brand-link">
				<img src="${contextPath}static/images/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light">AdminLTE 3</span>
			</a>
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>Invalid Session</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item">
									<a href="${contextPath}home">Home</a>
								</li>
								<li class="breadcrumb-item active">Error</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<div class="content">
				<div class="error-page">
					<h2 class="headline text-danger">401</h2>

					<div class="error-content">
						<h3>
							<i class="fas fa-exclamation-triangle text-danger"></i> Invalid Session.
						</h3>

						<p>
							Invalid Session or Session Expired!
						</p>
					</div>
				</div>
				<!-- /.error-page -->
		
			</div>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@include file="../common/rightSidebar.jsp"%>

		<%@include file="../common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
	<script src="${contextPath}static/resources/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${contextPath}static/resources/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE -->
	<script src="${contextPath}static/js/adminlte.min.js"></script>
	<script src="${contextPath}static/js/demo.js"></script>
	<script>
		var contextPath = "${contextPath}";
		setTimeout(function() {
			window.location.replace(contextPath);
		}, 1000);
	</script>
</body>
</html>