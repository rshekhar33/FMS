<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="${contextPath}" class="brand-link">
		<img src="${contextPath}static/images/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
		<span class="brand-text font-weight-light">AdminLTE 3</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- SidebarSearch Form -->
		<div class="form-inline">
			<div class="input-group" data-widget="sidebar-search">
				<input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
				<div class="input-group-append">
					<button class="btn btn-sidebar">
						<i class="fas fa-search fa-fw"></i>
					</button>
				</div>
			</div>
		</div>
		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column nav-child-indent nav-legacy nav-flat" data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class with font-awesome or any other icon font library -->
				<c:if test="${appAuthorization.isAccessAllowed('dashboard/dashboard')}">
				<li class="nav-item">
					<a href="${contextPath}dashboard/dashboard" class="nav-link ${dashboardActiveCls}">
						<i class="nav-icon fas fa-tachometer-alt"></i>
						<p>Dashboard</p>
					</a>
				</li>
				</c:if>
				<c:if test="${appAuthorization.isAccessAllowed('user/list') || appAuthorization.isAccessAllowed('user/add')}">
				<li class="nav-item ${userMenuOpenCls}">
					<a href="#" class="nav-link">
						<i class="nav-icon fas fa-users"></i>
						<p>
							User <i class="right fas fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<c:if test="${appAuthorization.isAccessAllowed('user/list')}">
						<li class="nav-item">
							<a href="${contextPath}user/list" class="nav-link ${usersActiveCls}">
								<i class="fas fa-list nav-icon"></i>
								<p>List Users</p>
							</a>
						</li>
						</c:if>
						<c:if test="${appAuthorization.isAccessAllowed('user/add')}">
						<li class="nav-item">
							<a href="${contextPath}user/add" class="nav-link ${userCrudActiveCls}">
								<i class="fas fa-user-plus nav-icon"></i>
								<p>Add User</p>
							</a>
						</li>
						</c:if>
					</ul>
				</li>
				</c:if>
				<c:if test="${appAuthorization.isAccessAllowed('module/list') || appAuthorization.isAccessAllowed('module/add')}">
				<li class="nav-item ${moduleMenuOpenCls}">
					<a href="#" class="nav-link">
						<i class="nav-icon fas fa-book"></i>
						<p>
							Module <i class="right fas fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<c:if test="${appAuthorization.isAccessAllowed('module/list')}">
						<li class="nav-item">
							<a href="${contextPath}module/list" class="nav-link ${modulesActiveCls}">
								<i class="fas fa-list nav-icon"></i>
								<p>List Modules</p>
							</a>
						</li>
						</c:if>
						<c:if test="${appAuthorization.isAccessAllowed('module/add')}">
						<li class="nav-item">
							<a href="${contextPath}module/add" class="nav-link ${moduleCrudActiveCls}">
								<i class="fas fa-plus nav-icon"></i>
								<p>Add Module</p>
							</a>
						</li>
						</c:if>
					</ul>
				</li>
				</c:if>
				<c:if test="${appAuthorization.isAccessAllowed('courseType/list') || appAuthorization.isAccessAllowed('courseType/add')}">
				<li class="nav-item ${courseTypeMenuOpenCls}">
					<a href="#" class="nav-link">
						<i class="nav-icon far fa-file-alt"></i>
						<p>
							Course Type <i class="right fas fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<c:if test="${appAuthorization.isAccessAllowed('courseType/list')}">
						<li class="nav-item">
							<a href="${contextPath}courseType/list" class="nav-link ${courseTypesActiveCls}">
								<i class="fas fa-list nav-icon"></i>
								<p>List Course Types</p>
							</a>
						</li>
						</c:if>
						<c:if test="${appAuthorization.isAccessAllowed('courseType/add')}">
						<li class="nav-item">
							<a href="${contextPath}courseType/add" class="nav-link ${courseTypeCrudActiveCls}">
								<i class="fas fa-plus nav-icon"></i>
								<p>Add Course Type</p>
							</a>
						</li>
						</c:if>
					</ul>
				</li>
				</c:if>
				<c:if test="${appAuthorization.isAccessAllowed('role/list') || appAuthorization.isAccessAllowed('role/add')}">
				<li class="nav-item ${roleMenuOpenCls}">
					<a href="#" class="nav-link">
						<i class="nav-icon fas fa-wrench"></i>
						<p>
							Role <i class="right fas fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<c:if test="${appAuthorization.isAccessAllowed('role/list')}">
						<li class="nav-item">
							<a href="${contextPath}role/list" class="nav-link ${rolesActiveCls}">
								<i class="fas fa-list nav-icon"></i>
								<p>List Roles</p>
							</a>
						</li>
						</c:if>
						<c:if test="${appAuthorization.isAccessAllowed('role/add')}">
						<li class="nav-item">
							<a href="${contextPath}role/add" class="nav-link ${roleCrudActiveCls}">
								<i class="fas fa-plus nav-icon"></i>
								<p>Add Role</p>
							</a>
						</li>
						</c:if>
					</ul>
				</li>
				</c:if>
				<c:if test="${appAuthorization.isAccessAllowed('facultySkillset/list') || appAuthorization.isAccessAllowed('facultySkillset/add')}">
				<li class="nav-item ${facultySkillsetMenuOpenCls}">
					<a href="#" class="nav-link">
						<i class="nav-icon fas fa-wrench"></i>
						<p>
							Faculty Skillset <i class="right fas fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<c:if test="${appAuthorization.isAccessAllowed('facultySkillset/list')}">
						<li class="nav-item">
							<a href="${contextPath}facultySkillset/list" class="nav-link ${facultySkillsetsActiveCls}">
								<i class="fas fa-list nav-icon"></i>
								<p>List Faculty Skillsets</p>
							</a>
						</li>
						</c:if>
						<c:if test="${appAuthorization.isAccessAllowed('facultySkillset/add')}">
						<li class="nav-item">
							<a href="${contextPath}facultySkillset/add" class="nav-link ${facultySkillsetCrudActiveCls}">
								<i class="fas fa-plus nav-icon"></i>
								<p>Add Faculty Skillset</p>
							</a>
						</li>
						</c:if>
					</ul>
				</li>
				</c:if>
			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>