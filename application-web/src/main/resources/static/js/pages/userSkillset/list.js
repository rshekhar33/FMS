var ListController = {
	elements: {},
	userSkillsetsDataTable: null,

	findElements: function () {
		this.elements.userSkillsetsTable = $("#userSkillsetsTable");
		this.elements.linkId = $("#linkId");
		this.elements.linkForm = $("#linkForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.userSkillsetsTable.on("click", "input.actionActivate", function () {
			base.activationFun(this);
		});

		base.elements.userSkillsetsTable.on("click", "a.actionEdit", function () {
			base.editFun(this);
		});

		return base;
	},
	initDT: function () {
		this.userSkillsetsDataTable = this.elements.userSkillsetsTable.DataTable({
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
				title: "Module Names",
				data: "moduleNames"
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
		var url = "userSkillset/fetchDetails";
		var doneCallbackFun = function (responseObj) {
			var userSkillsets = responseObj;
			var userSkillsetsData = [];
			for (var i = 0; i < userSkillsets.length; i++) {
				var userSkillset = userSkillsets[i];
				var isActiveStr = '';
				if (activationIsAllowed) {
					isActiveStr = '<div class="ui fitted toggle checkbox"><input type="checkbox" class="actionActivate" ';
					isActiveStr += (userSkillset.usIsActive == 1) ? 'checked="checked" ' : (userSkillset.usIsActive == 0) ? '' : 'disabled="disabled" ';
					isActiveStr += 'data-user-id="' + userSkillset.userId + '" /><label></label></div>';
				}
				var isActionStr = '';
				if (updateIsAllowed) {
					isActionStr = '<a class="btn actionEdit" data-user-id="' + userSkillset.userId + '"><i class="fas fa-edit"></i></a>';
				}
				userSkillsetsData.push({
					userName: userSkillset.userName,
					fullName: userSkillset.fullName,
					moduleNames: userSkillset.moduleNames,
					isActive: isActiveStr,
					action: isActionStr
				});
			}
			base.userSkillsetsDataTable.clear().rows.add(userSkillsetsData).draw();
			Loader.showLoaderRight(false);

			return userSkillsets;
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
		var url = "userSkillset/activation";
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
		this.elements.linkForm.attr("action", contextPath + "userSkillset/update");
		this.elements.linkForm.submit();
	},
	initialize: function () {
		this.findElements().addClickEvents().initDT().loadDataFun();
	}
};

$(function () {
	ListController.initialize();
});