<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ include file="../common/cmnCntxtPath.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<sec:csrfMetaTags/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>${appTitle} | All Roles</title>
	<!-- Font Awesome Icons -->
	<link rel="stylesheet" href="${contextPath}resources/font-awesome/css/all.min.css">
	<!-- IonIcons -->
	<link rel="stylesheet" href="${contextPath}resources/ionicons/css/ionicons.min.css">
	<!-- overlayScrollbars -->
	<link rel="stylesheet" href="${contextPath}resources/overlayScrollbars/css/OverlayScrollbars.min.css">
	<!-- DataTables -->
	<link rel="stylesheet" href="${contextPath}resources/datatables-bs4/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="${contextPath}resources/datatables-responsive/css/responsive.bootstrap4.min.css">
	<!-- Semantic Checkbox -->
	<link rel="stylesheet" href="${contextPath}resources/semantic-ui/css/checkbox.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="${contextPath}css/adminlte.min.css">
	<!-- Google Font: Source Sans Pro -->
	<link rel="stylesheet" href="${contextPath}resources/google-fonts/google-fonts.css">
	<link rel="stylesheet" href="${contextPath}css/override.css">
	<link rel="shortcut icon" href="${contextPath}images/favicon.ico" />
</head>
<body class="hold-transition sidebar-mini text-sm layout-fixed layout-navbar-fixed layout-footer-fixed">
	<div class="wrapper">
		<%@include file="../common/header.jsp"%>

		<%@include file="../common/leftSidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div id="loaderRightContainer">
				<div id="loaderRight"></div>
			</div>
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0 text-dark">Role Listing</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item">
									<a href="${contextPath}home">Home</a>
								</li>
								<c:if test="${appAuthorization.isAccessAllowed('role/list')}">
								<li class="breadcrumb-item">
									<a href="${contextPath}role/list">Role</a>
								</li>
								</c:if>
								<li class="breadcrumb-item active">List Roles</li>
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
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">List Roles</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<table id="rolesTable" class="table table-bordered table-striped">
								</table>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
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
	<script src="${contextPath}resources/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${contextPath}resources/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Bootbox -->
	<script src="${contextPath}resources/bootbox/bootbox.min.js"></script>
	<!-- overlayScrollbars -->
	<script src="${contextPath}resources/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- DataTables -->
	<script src="${contextPath}resources/datatables/jquery.dataTables.min.js"></script>
	<script src="${contextPath}resources/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script src="${contextPath}resources/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script src="${contextPath}resources/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<!-- Semantic Checkbox -->
	<script src="${contextPath}resources/semantic-ui/js/checkbox.min.js"></script>
	<!-- AdminLTE -->
	<script src="${contextPath}js/adminlte.min.js"></script>

	<!-- OPTIONAL SCRIPTS -->
	<script src="${contextPath}js/demo.js"></script>
	<!-- page script -->
	<script>
		var contextPath = "${contextPath}";
		var activationIsAllowed = ${appAuthorization.isAccessAllowed('role/activation')};
		var updateIsAllowed = ${appAuthorization.isAccessAllowed('role/update')};
	</script>
	<script src="${contextPath}js/common.js"></script>
	<script src="${contextPath}js/pages/role/list.js"></script>
</body>
</html>