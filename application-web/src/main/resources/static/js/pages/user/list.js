var ListController = {
	elements: {},
	usersDataTable: null,

	findElements: function () {
		this.elements.usersTable = $("#usersTable");
		this.elements.linkId = $("#linkId");
		this.elements.linkForm = $("#linkForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.usersTable.on("click", "input.actionActivate", function () {
			base.activationFun(this);
		});

		base.elements.usersTable.on("click", "a.actionEdit", function () {
			base.editFun(this);
		});

		return base;
	},
	initDT: function () {
		this.usersDataTable = this.elements.usersTable.DataTable({
			ordering: true,
			info: true,
			autoWidth: false,
			paging: true,
			responsive: true,
			pagingType: "full_numbers",
			columns: [{
				title: "UserName",
				data: "userName"
			}, {
				title: "Name",
				data: "fullName"
			}, {
				title: "Email ID",
				data: "emailId"
			}, {
				title: "Mobile No",
				data: "mobileNo"
			}, {
				title: "Roles",
				data: "roleNames"
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
		var url = "user/fetchDetails";
		var doneCallbackFun = function (responseObj) {
			var users = responseObj;
			var usersData = [];
			for (var i = 0; i < users.length; i++) {
				var user = users[i];
				var isActiveStr = '';
				if (activationIsAllowed) {
					isActiveStr = '<div class="ui fitted toggle checkbox"><input type="checkbox" class="actionActivate" ';
					isActiveStr += (user.isActive == 1) ? 'checked="checked" ' : '';
					isActiveStr += 'data-user-id="' + user.userId + '" /><label></label></div>';
				}
				var isActionStr = '';
				if (updateIsAllowed) {
					isActionStr = '<a class="btn actionEdit" data-user-id="' + user.userId + '"><i class="fas fa-edit"></i></a>';
				}
				usersData.push({
					userName: user.userName,
					fullName: user.fullName,
					emailId: user.emailId,
					mobileNo: user.mobileNo,
					roleNames: user.roleNames,
					isActive: isActiveStr,
					action: isActionStr
				});
			}
			base.usersDataTable.clear().rows.add(usersData).draw();
			Loader.showLoaderRight(false);

			return users;
		};

		return AjaxFn.callPostFun(url, null, doneCallbackFun, ErrorFn.errorFun1);
	},
	activationFun: function (checkBoxObj) {
		var userId = $(checkBoxObj).data("userId");

		Loader.showLoaderRight(true);
		var isActive = 0;
		if (checkBoxObj.checked) {
			isActive = 1;
		}
		var url = "user/activation";
		var data = {
			userId: userId,
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
		var userId = $(anchorObj).data("userId");

		this.elements.linkId.val(userId);
		this.elements.linkForm.attr("action", contextPath + "user/update");
		this.elements.linkForm.submit();
	},
	initialize: function () {
		this.findElements().addClickEvents().initDT().loadDataFun();
	}
};

$(function () {
	ListController.initialize();
});