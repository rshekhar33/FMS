var ListController = {
	elements: {},
	rolesDataTable: null,

	findElements: function () {
		this.elements.rolesTable = $("#rolesTable");
		this.elements.linkId = $("#linkId");
		this.elements.linkForm = $("#linkForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.rolesTable.on("click", "input.actionActivate", function () {
			base.activationFun(this);
		});

		base.elements.rolesTable.on("click", "a.actionEdit", function () {
			base.editFun(this);
		});

		return base;
	},
	initDT: function () {
		this.rolesDataTable = this.elements.rolesTable.DataTable({
			ordering: true,
			info: true,
			autoWidth: false,
			paging: true,
			responsive: true,
			pagingType: "full_numbers",
			columns: [{
				title: "Role Name",
				data: "roleName"
			}, {
				title: "Active",
				data: "isActive",
				orderable: false,
				searchable: false
			}, {
				title: "Action",
				data: "action",
				orderable: false,
				searchable: false
			}]
		});

		return this;
	},
	loadDataFun: function () {
		var base = this;

		Loader.showLoaderRight(true);
		var url = "role/fetchDetails";
		var doneCallbackFun = function (responseObj) {
			var roles = responseObj;
			var rolesData = [];
			for (var i = 0; i < roles.length; i++) {
				var role = roles[i];
				var isActiveStr = '';
				if (activationIsAllowed) {
					isActiveStr = '<div class="ui fitted toggle checkbox"><input type="checkbox" class="actionActivate" ';
					isActiveStr += (role.isActive == 1) ? 'checked="checked" ' : '';
					isActiveStr += 'data-role-id="' + role.roleId + '" /><label></label></div>';
				}
				var isActionStr = '';
				if (updateIsAllowed) {
					isActionStr = '<a class="btn actionEdit" data-role-id="' + role.roleId + '"><i class="fas fa-edit"></i></a>';
				}
				rolesData.push({
					roleName: role.roleName,
					isActive: isActiveStr,
					action: isActionStr
				});
			}
			base.rolesDataTable.clear().rows.add(rolesData).draw();
			Loader.showLoaderRight(false);

			return roles;
		};

		return AjaxFn.callPostFun(url, null, doneCallbackFun, ErrorFn.errorFun1);
	},
	activationFun: function (checkBoxObj) {
		var roleId = $(checkBoxObj).data("roleId");

		Loader.showLoaderRight(true);
		var isActive = 0;
		if (checkBoxObj.checked) {
			isActive = 1;
		}
		var url = "role/activation";
		var data = {
			roleId: roleId,
			isActive: isActive
		};
		var doneCallbackFun = function (responseObj) {
			if (responseObj.status == "success") {
				bootbox.alert({
					message: responseObj.msg,
					backdrop: true
				});
			}
			Loader.showLoaderRight(false);

			return responseObj;
		};

		return AjaxFn.callPostFun(url, data, doneCallbackFun, ErrorFn.errorFun1);
	},
	editFun: function (anchorObj) {
		var roleId = $(anchorObj).data("roleId");

		this.elements.linkId.val(roleId);
		this.elements.linkForm.attr("action", contextPath + "role/update");
		this.elements.linkForm.submit();
	},
	initialize: function () {
		this.findElements().addClickEvents().initDT().loadDataFun();
	}
};

$(function () {
	ListController.initialize();
});